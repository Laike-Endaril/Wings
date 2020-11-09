package me.paulf.wings.server.asm;

import me.paulf.wings.util.Access;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemMap;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.Event;

import java.lang.invoke.MethodHandle;

public final class WingsHooksClient
{
    private static int selectedItemSlot = 0;

    private WingsHooksClient()
    {
    }

    public static void onTurn(Entity entity, float deltaYaw)
    {
        if (entity instanceof EntityLivingBase)
        {
            EntityLivingBase living = (EntityLivingBase) entity;
            float theta = MathHelper.wrapDegrees(living.rotationYaw - living.renderYawOffset);
            GetLivingHeadLimitEvent ev = GetLivingHeadLimitEvent.create(living);
            MinecraftForge.EVENT_BUS.post(ev);
            float limit = ev.getHardLimit();
            if (theta < -limit || theta > limit)
            {
                living.renderYawOffset += deltaYaw;
                living.prevRenderYawOffset += deltaYaw;
            }
        }
    }

    public static boolean onCheckRenderEmptyHand(boolean isMainHand, ItemStack itemStackMainHand)
    {
        return isMainHand || !isMap(itemStackMainHand);
    }

    public static boolean onCheckDoReequipAnimation(ItemStack from, ItemStack to, int slot)
    {
        boolean fromEmpty = from.isEmpty(), toEmpty = to.isEmpty();
        boolean isOffHand = slot == -1;
        if (toEmpty && isOffHand)
        {
            Minecraft mc = Minecraft.getMinecraft();
            EntityPlayerSP player = mc.player;
            if (player == null)
            {
                return true;
            }
            boolean fromMap = isMap(GetItemStackMainHand.invoke(mc.getItemRenderer()));
            boolean toMap = isMap(player.getHeldItemMainhand());
            if (fromMap || toMap)
            {
                return fromMap != toMap;
            }
            if (fromEmpty)
            {
                EmptyOffHandPresentEvent ev = new EmptyOffHandPresentEvent(player);
                MinecraftForge.EVENT_BUS.post(ev);
                return ev.getResult() != Event.Result.ALLOW;
            }
        }
        if (fromEmpty || toEmpty)
        {
            return fromEmpty != toEmpty;
        }
        boolean hasSlotChange = !isOffHand && selectedItemSlot != (selectedItemSlot = slot);
        return from.getItem().shouldCauseReequipAnimation(from, to, hasSlotChange);
    }

    private static boolean isMap(ItemStack stack)
    {
        return stack.getItem() instanceof ItemMap;
    }

    private static final class GetItemStackMainHand
    {
        private static final MethodHandle MH = Access.getter(ItemRenderer.class)
                .name("field_187467_d", "itemStackMainHand")
                .type(ItemStack.class);

        private GetItemStackMainHand()
        {
        }

        private static ItemStack invoke(ItemRenderer instance)
        {
            try
            {
                return (ItemStack) MH.invokeExact(instance);
            }
            catch (Throwable t)
            {
                throw Access.rethrow(t);
            }
        }
    }
}

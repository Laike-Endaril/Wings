package me.paulf.wings.server.sound;

import me.paulf.wings.WingsMod;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

@Mod.EventBusSubscriber(modid = WingsMod.MODID)
public final class WingsSounds
{
    private static final SoundEvent NIL = SoundEvents.ENTITY_PIG_AMBIENT;
    @GameRegistry.ObjectHolder(WingsMod.MODID + ":item.armor.equip_wings")
    public static final SoundEvent ITEM_ARMOR_EQUIP_WINGS = NIL;
    @GameRegistry.ObjectHolder(WingsMod.MODID + ":item.wings.flying")
    public static final SoundEvent ITEM_WINGS_FLYING = NIL;

    private WingsSounds()
    {
    }

    @SubscribeEvent
    public static void register(RegistryEvent.Register<SoundEvent> event)
    {
        event.getRegistry().registerAll(
                create("item.armor.equip_wings"),
                create("item.wings.flying")
        );
    }

    private static SoundEvent create(String name)
    {
        return new SoundEvent(new ResourceLocation(WingsMod.MODID, name)).setRegistryName(name);
    }
}

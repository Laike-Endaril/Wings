package me.paulf.wings.client;

import me.paulf.wings.WingsMod;
import me.paulf.wings.client.audio.WingsSound;
import me.paulf.wings.client.flight.FlightView;
import me.paulf.wings.client.flight.FlightViews;
import me.paulf.wings.server.apparatus.FlightApparatuses;
import me.paulf.wings.server.asm.EmptyOffHandPresentEvent;
import me.paulf.wings.server.asm.GetCameraEyeHeightEvent;
import me.paulf.wings.server.flight.Flight;
import me.paulf.wings.server.flight.Flights;
import me.paulf.wings.util.Mth;
import net.ilexiconn.llibrary.client.event.ApplyRenderRotationsEvent;
import net.ilexiconn.llibrary.client.event.PlayerModelEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelPlayer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.client.event.EntityViewRenderEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.Side;

@Mod.EventBusSubscriber(value = Side.CLIENT, modid = WingsMod.ID)
public final class ClientEventHandler
{
    private ClientEventHandler()
    {
    }

    @SubscribeEvent
    public static void onSetRotationAngles(PlayerModelEvent.SetRotationAngles event)
    {
        EntityPlayer player = event.getEntityPlayer();
        float delta = event.getRotation() - player.ticksExisted;
        Flight flight = Flights.get(player);
        float amt;
        if (flight != null && (amt = flight.getFlyingAmount(delta)) > 0.0F)
        {
            ModelBiped model = event.getModel();
            float pitch = event.getRotationPitch();
            model.bipedHead.rotateAngleX = Mth.toRadians(Mth.lerp(pitch, pitch / 4.0F - 90.0F, amt));
            model.bipedLeftArm.rotateAngleX = Mth.lerp(model.bipedLeftArm.rotateAngleX, -3.2F, amt);
            model.bipedRightArm.rotateAngleX = Mth.lerp(model.bipedRightArm.rotateAngleX, -3.2F, amt);
            model.bipedLeftLeg.rotateAngleX = Mth.lerp(model.bipedLeftLeg.rotateAngleX, 0.0F, amt);
            model.bipedRightLeg.rotateAngleX = Mth.lerp(model.bipedRightLeg.rotateAngleX, 0.0F, amt);
            ModelBase.copyModelAngles(model.bipedHead, model.bipedHeadwear);
            if (model instanceof ModelPlayer)
            {
                ModelPlayer playerModel = (ModelPlayer) model;
                ModelBase.copyModelAngles(playerModel.bipedLeftLeg, playerModel.bipedLeftLegwear);
                ModelBase.copyModelAngles(playerModel.bipedRightLeg, playerModel.bipedRightLegwear);
                ModelBase.copyModelAngles(playerModel.bipedLeftArm, playerModel.bipedLeftArmwear);
                ModelBase.copyModelAngles(playerModel.bipedRightArm, playerModel.bipedRightArmwear);
            }
        }
    }

    @SubscribeEvent
    public static void onApplyRenderRotations(ApplyRenderRotationsEvent.Post event)
    {
        Flights.ifPlayer(event.getEntity(), (player, flight) ->
        {
            float delta = event.getPartialTicks();
            float amt = flight.getFlyingAmount(delta);
            if (amt > 0.0F)
            {
                float roll = Mth.lerpDegrees(
                        player.prevRenderYawOffset - player.prevRotationYaw,
                        player.renderYawOffset - player.rotationYaw,
                        delta
                );
                float pitch = -Mth.lerpDegrees(player.prevRotationPitch, player.rotationPitch, delta) - 90;
                GlStateManager.rotate(Mth.lerpDegrees(0.0F, roll, amt), 0.0F, 0.0F, 1.0F);
                GlStateManager.rotate(Mth.lerpDegrees(0.0F, pitch, amt), 1.0F, 0.0F, 0.0F);
                GlStateManager.translate(0.0F, -1.2F * Mth.easeInOut(amt), 0.0F);
            }
        });
    }

    @SubscribeEvent
    public static void onGetCameraEyeHeight(GetCameraEyeHeightEvent event)
    {
        Entity entity = event.getEntity();
        FlightView flight;
        if (entity instanceof AbstractClientPlayer && (flight = FlightViews.get((AbstractClientPlayer) entity)) != null)
        {
            flight.tickEyeHeight(event.getValue(), event.getDelta(), event::setValue);
        }
    }

    @SubscribeEvent
    public static void onCameraSetup(EntityViewRenderEvent.CameraSetup event)
    {
        Flights.ifPlayer(event.getEntity(), (player, flight) ->
        {
            float delta = (float) event.getRenderPartialTicks();
            float amt = flight.getFlyingAmount(delta);
            if (amt > 0.0F)
            {
                float roll = Mth.lerpDegrees(
                        player.prevRenderYawOffset - player.prevRotationYaw,
                        player.renderYawOffset - player.rotationYaw,
                        delta
                );
                event.setRoll(Mth.lerpDegrees(0.0F, -roll * 0.25F, amt));
            }
        });
    }

    @SubscribeEvent
    public static void onEmptyOffHandPresentEvent(EmptyOffHandPresentEvent event)
    {
        Flight flight = Flights.get(event.getPlayer());
        if (flight != null && flight.getFlyingAmount(1.0F) > 0.0F)
        {
            event.setResult(Event.Result.ALLOW);
        }
    }

    @SubscribeEvent
    public static void onEntityJoinWorld(EntityJoinWorldEvent event)
    {
        Flights.ifPlayer(event.getEntity(), EntityPlayer::isUser, (player, flight) ->
                Minecraft.getMinecraft().getSoundHandler().playSound(new WingsSound(player, flight))
        );
    }

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event)
    {
        EntityPlayer entity;
        if (event.phase == TickEvent.Phase.END && (entity = event.player) instanceof AbstractClientPlayer)
        {
            AbstractClientPlayer player = (AbstractClientPlayer) entity;
            FlightView flight = FlightViews.get(player);
            if (flight != null)
            {
                flight.tick(player, FlightApparatuses.find(player));
            }
        }
    }
}

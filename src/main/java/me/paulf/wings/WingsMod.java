package me.paulf.wings;

import me.paulf.wings.server.flight.Flight;
import me.paulf.wings.util.CapabilityProviders;
import me.paulf.wings.util.ItemAccessor;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import java.util.function.Consumer;

@Mod(modid = WingsMod.MODID, name = WingsMod.NAME, version = WingsMod.VERSION, dependencies = "required-after:llibrary@[1.7,1.8);required-after:llibrary@[1.7,1.8)")
public final class WingsMod
{
    public static final String MODID = "wings";
    public static final String NAME = "Wings";
    public static final String VERSION = "L1-1.12.2.002";

    @SidedProxy(
            clientSide = "me.paulf.wings.client.ClientProxy",
            serverSide = "me.paulf.wings.server.ServerProxy"
    )
    private static Proxy proxy;

    @Mod.InstanceFactory
    public static WingsMod instance()
    {
        return Holder.INSTANCE;
    }

    @Mod.EventHandler
    public void init(FMLPreInitializationEvent event)
    {
        requireProxy().preinit();
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event)
    {
        requireProxy().init();
    }

    public void addFlightListeners(EntityPlayer player, Flight instance)
    {
        requireProxy().addFlightListeners(player, instance);
    }

    public ItemAccessor<EntityPlayer> getWingsAccessor()
    {
        return requireProxy().getWingsAccessor();
    }

    public Consumer<CapabilityProviders.CompositeBuilder> createAvianWings(String name)
    {
        return requireProxy().createAvianWings(name);
    }

    public Consumer<CapabilityProviders.CompositeBuilder> createInsectoidWings(String name)
    {
        return requireProxy().createInsectoidWings(name);
    }

    private Proxy requireProxy()
    {
        if (proxy == null)
        {
            throw new IllegalStateException("Proxy not initialized");
        }
        return proxy;
    }

    private static final class Holder
    {
        private static final WingsMod INSTANCE = new WingsMod();
    }
}

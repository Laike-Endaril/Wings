package me.paulf.wings.server.net.clientbound;

import me.paulf.wings.config.WingsConfig;
import me.paulf.wings.server.item.ImmutableWingSettings;
import me.paulf.wings.server.item.ItemWings;
import me.paulf.wings.server.item.WingSettings;
import me.paulf.wings.server.net.Message;
import net.minecraft.item.Item;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

import java.util.ArrayList;

public final class MessageSetWingSettings extends Message
{
    public ArrayList<WingSettings> wingSettings = new ArrayList<>();

    public MessageSetWingSettings()
    {
        wingSettings.add(WingsConfig.serverSettings.items.blueButterflyWings);
        wingSettings.add(WingsConfig.serverSettings.items.monarchWings);
        wingSettings.add(WingsConfig.serverSettings.items.fairyWings);
        wingSettings.add(WingsConfig.serverSettings.items.batWings);
        wingSettings.add(WingsConfig.serverSettings.items.dragonWings);
        wingSettings.add(WingsConfig.serverSettings.items.slimeWings);
        wingSettings.add(WingsConfig.serverSettings.items.fireWings);
        wingSettings.add(WingsConfig.serverSettings.items.angelWings);
        wingSettings.add(WingsConfig.serverSettings.items.evilWings);
    }

    @Override
    protected void serialize(PacketBuffer buf)
    {
        buf.writeInt(wingSettings.size());
        for (WingSettings settings : wingSettings)
        {
            buf.writeResourceLocation(settings.getResourceLocation());
            buf.writeInt(settings.getRequiredFlightSatiation());
            buf.writeFloat(settings.getFlyingExertion());
            buf.writeInt(settings.getRequiredLandSatiation());
            buf.writeFloat(settings.getLandingExertion());
            buf.writeShort(settings.getItemDurability());
        }
    }

    @Override
    protected void deserialize(PacketBuffer buf)
    {
        wingSettings.clear();
        for (int i = buf.readInt(); i-- > 0; )
        {
            wingSettings.add(ImmutableWingSettings.of(buf.readResourceLocation(), buf.readInt(), buf.readFloat(), buf.readInt(), buf.readFloat(), buf.readShort()));
        }
    }

    @Override
    protected void process(MessageContext ctx)
    {
        for (WingSettings settings : wingSettings)
        {
            Item item = ForgeRegistries.ITEMS.getValue(settings.getResourceLocation());
            if (item instanceof ItemWings) ((ItemWings) item).setSettings(settings);
        }
    }
}

package me.paulf.wings.util;

import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public final class ModConfigSaver
{
    private final String id;

    private ModConfigSaver(String id)
    {
        this.id = id;
    }

    public static ModConfigSaver create(String id)
    {
        return new ModConfigSaver(id);
    }

    @SubscribeEvent
    public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event)
    {
        if (this.id.equals(event.getModID()))
        {
            ConfigManager.sync(this.id, Config.Type.INSTANCE);
        }
    }
}

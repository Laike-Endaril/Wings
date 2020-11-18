package me.paulf.wings.config;

import me.paulf.wings.config.client.ClientConfig;
import me.paulf.wings.config.server.ServerConfig;
import net.minecraftforge.common.config.Config;

import static me.paulf.wings.WingsMod.MODID;

@Config(modid = MODID)
public final class WingsConfig
{
    @Config.LangKey(MODID + ".config.clientSettings")
    public static ClientConfig clientSettings = new ClientConfig();

    @Config.LangKey(MODID + ".config.serverSettings")
    public static ServerConfig serverSettings = new ServerConfig();
}

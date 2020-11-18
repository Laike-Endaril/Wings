package me.paulf.wings.config.server;

import net.minecraftforge.common.config.Config;

import static me.paulf.wings.WingsMod.MODID;

public class ServerConfig
{
    @Config.LangKey(MODID + ".config.ores")
    public OreConfig ores = new OreConfig();

    @Config.LangKey(MODID + ".config.items")
    public ItemsConfig items = new ItemsConfig();

    @Config.LangKey(MODID + ".config.wearObstructions")
    public String[] wearObstructions = new String[]
            {
                    "minecraft:elytra",
                    "wearablebackpacks:backpack"
            };
}

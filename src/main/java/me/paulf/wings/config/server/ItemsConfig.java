package me.paulf.wings.config.server;

import me.paulf.wings.server.item.WingsItems;
import net.minecraftforge.common.config.Config;

import static me.paulf.wings.WingsMod.MODID;

public final class ItemsConfig
{
    @Config.LangKey(MODID + ".config.angelWings")
    @Config.RequiresMcRestart
    public ConfigWingSettings angelWings = new ConfigWingSettings(WingsItems.Names.ANGEL, 960);

    @Config.LangKey(MODID + ".config.slimeWings")
    @Config.RequiresMcRestart
    public ConfigWingSettings slimeWings = new ConfigWingSettings(WingsItems.Names.SLIME, 960);

    @Config.LangKey(MODID + ".config.blueButterflyWings")
    @Config.RequiresMcRestart
    public ConfigWingSettings blueButterflyWings = new ConfigWingSettings(WingsItems.Names.BLUE_BUTTERFLY, 960);

    @Config.LangKey(MODID + ".config.monarchWings")
    @Config.RequiresMcRestart
    public ConfigWingSettings monarchWings = new ConfigWingSettings(WingsItems.Names.MONARCH_BUTTERFLY, 960);

    @Config.LangKey(MODID + ".config.fairyWings")
    @Config.RequiresMcRestart
    public ConfigWingSettings fairyWings = new ConfigWingSettings(WingsItems.Names.FAIRY, 960);

    @Config.LangKey(MODID + ".config.fireWings")
    @Config.RequiresMcRestart
    public ConfigWingSettings fireWings = new ConfigWingSettings(WingsItems.Names.FIRE, 1920);

    @Config.LangKey(MODID + ".config.batWings")
    @Config.RequiresMcRestart
    public ConfigWingSettings batWings = new ConfigWingSettings(WingsItems.Names.BAT, 1920);

    @Config.LangKey(MODID + ".config.evilWings")
    @Config.RequiresMcRestart
    public ConfigWingSettings evilWings = new ConfigWingSettings(WingsItems.Names.EVIL, 1920);

    @Config.LangKey(MODID + ".config.dragonWings")
    @Config.RequiresMcRestart
    public ConfigWingSettings dragonWings = new ConfigWingSettings(WingsItems.Names.DRAGON, 2880);
}

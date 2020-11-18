package me.paulf.wings.config.server;

import net.minecraftforge.common.config.Config;

import static me.paulf.wings.WingsMod.MODID;

public final class OreConfig
{
    @Config.LangKey(MODID + ".config.fairyDustOreGen")
    @Config.RequiresMcRestart
    public VeinSettings fairyDustOreGen = new VeinSettings(9, 10, 0, 64);

    @Config.LangKey(MODID + ".config.amethystOreGen")
    @Config.RequiresMcRestart
    public VeinSettings amethystOreGen = new VeinSettings(8, 1, 0, 16);
}

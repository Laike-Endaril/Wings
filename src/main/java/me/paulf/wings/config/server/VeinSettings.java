package me.paulf.wings.config.server;

import net.minecraftforge.common.config.Config;

import static me.paulf.wings.WingsMod.MODID;

public final class VeinSettings
{
    @Config.LangKey(MODID + ".config.veinSize")
    @Config.RangeInt(min = 8, max = 32)
    public int veinSize;

    @Config.LangKey(MODID + ".config.veinCount")
    @Config.RangeInt(min = 0, max = 128)
    public int veinCount;

    @Config.LangKey(MODID + ".config.veinMinHeight")
    public int veinMinHeight;

    @Config.LangKey(MODID + ".config.veinMaxHeight")
    public int veinMaxHeight;

    VeinSettings(int veinSize, int veinCount, int veinMinHeight, int veinMaxHeight)
    {
        this.veinSize = veinSize;
        this.veinCount = veinCount;
        this.veinMinHeight = veinMinHeight;
        this.veinMaxHeight = veinMaxHeight;
    }
}

package me.paulf.wings.config.server;

import me.paulf.wings.server.item.ImmutableWingSettings;
import me.paulf.wings.server.item.WingSettings;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.config.Config;

import static me.paulf.wings.WingsMod.MODID;

public final class ConfigWingSettings implements WingSettings
{
    private final ResourceLocation resourceLocation;

    @Config.LangKey(MODID + ".config.requiredFlightSatiation")
    @Config.RangeInt(min = 0, max = 20)
    public int requiredFlightSatiation;

    @Config.LangKey(MODID + ".config.flyingExertion")
    @Config.RangeDouble(min = 0.0D, max = 10.0D)
    public double flyingExertion;

    @Config.LangKey(MODID + ".config.requiredLandSatiation")
    @Config.RangeInt(min = 0, max = 20)
    public int requiredLandSatiation;

    @Config.LangKey(MODID + ".config.landingExertion")
    @Config.RangeDouble(min = 0.0D, max = 10.0D)
    public double landingExertion;

    @Config.LangKey(MODID + ".config.durability")
    @Config.RangeInt(min = 0, max = Short.MAX_VALUE)
    public int itemDurability;

    ConfigWingSettings(ResourceLocation resourceLocation, int itemDurability)
    {
        this(resourceLocation, 7, 0.001D, 2, 0.08D, itemDurability);
    }

    private ConfigWingSettings(ResourceLocation resourceLocation, int requiredFlightSatiation, double flyingExertion, int requiredLandSatiation, double landingExertion, int itemDurability)
    {
        this.resourceLocation = resourceLocation;
        this.requiredFlightSatiation = requiredFlightSatiation;
        this.flyingExertion = flyingExertion;
        this.requiredLandSatiation = requiredLandSatiation;
        this.landingExertion = landingExertion;
        this.itemDurability = itemDurability;
    }

    @Override
    public ResourceLocation getResourceLocation()
    {
        return resourceLocation;
    }

    @Override
    public int getRequiredFlightSatiation()
    {
        return requiredFlightSatiation;
    }

    @Override
    public float getFlyingExertion()
    {
        return (float) flyingExertion;
    }

    @Override
    public int getRequiredLandSatiation()
    {
        return requiredLandSatiation;
    }

    @Override
    public float getLandingExertion()
    {
        return (float) landingExertion;
    }

    @Override
    public int getItemDurability()
    {
        return itemDurability;
    }

    public WingSettings toImmutable()
    {
        return ImmutableWingSettings.of(getResourceLocation(), getRequiredFlightSatiation(), getFlyingExertion(), getRequiredLandSatiation(), getLandingExertion(), getItemDurability());
    }
}

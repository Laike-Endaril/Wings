package me.paulf.wings.server.item;

import net.minecraft.util.ResourceLocation;

public final class ImmutableWingSettings implements WingSettings
{
    private final ResourceLocation resourceLocation;

    private final int requiredFlightSatiation;

    private final float flyingExertion;

    private final int requiredLandSatiation;

    private final float landingExertion;

    private final int itemDurability;

    private ImmutableWingSettings(ResourceLocation resourceLocation, int requiredFlightSatiation, float flyingExertion, int requiredLandSatiation, float landingExertion, int itemDurability)
    {
        this.resourceLocation = resourceLocation;
        this.requiredFlightSatiation = requiredFlightSatiation;
        this.flyingExertion = flyingExertion;
        this.requiredLandSatiation = requiredLandSatiation;
        this.landingExertion = landingExertion;
        this.itemDurability = itemDurability;
    }

    public static ImmutableWingSettings of(ResourceLocation resourceLocation, int requiredFlightSatiation, float flyingExertion, int requiredLandSatiation, float landingExertion, int durability)
    {
        return new ImmutableWingSettings(resourceLocation, requiredFlightSatiation, flyingExertion, requiredLandSatiation, landingExertion, durability);
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
        return flyingExertion;
    }

    @Override
    public int getRequiredLandSatiation()
    {
        return requiredLandSatiation;
    }

    @Override
    public float getLandingExertion()
    {
        return landingExertion;
    }

    @Override
    public int getItemDurability()
    {
        return itemDurability;
    }
}

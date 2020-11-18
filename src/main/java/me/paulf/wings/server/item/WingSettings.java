package me.paulf.wings.server.item;

import net.minecraft.util.ResourceLocation;

public interface WingSettings
{
    ResourceLocation getResourceLocation();

    int getRequiredFlightSatiation();

    float getFlyingExertion();

    int getRequiredLandSatiation();

    float getLandingExertion();

    int getItemDurability();
}

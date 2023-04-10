package dev.toma.pubgmc.common.items.equipment;

import net.minecraft.util.ResourceLocation;

public interface NightVisionGoggles {

    ResourceLocation getOverlayTexture();

    ResourceLocation getHotbarIconPath(boolean active);

    // TODO implement
    float getBrightnessValue();
}

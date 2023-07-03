package dev.toma.pubgmc.api.item;

import dev.toma.pubgmc.api.capability.SpecialEquipmentSlot;
import net.minecraft.util.ResourceLocation;

public interface NightVisionGoggles extends SpecialInventoryItem {

    ResourceLocation getOverlayTexture();

    ResourceLocation getHotbarIconPath(boolean active);

    float getBrightnessValue();

    float getLightExposureSensitivity();

    @Override
    default SpecialEquipmentSlot getSlotType() {
        return SpecialEquipmentSlot.NIGHT_VISION;
    }
}

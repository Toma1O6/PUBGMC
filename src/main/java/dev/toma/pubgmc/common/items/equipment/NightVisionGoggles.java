package dev.toma.pubgmc.common.items.equipment;

import dev.toma.pubgmc.common.capability.player.SpecialEquipmentSlot;
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

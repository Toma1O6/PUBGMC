package dev.toma.pubgmc.api.item;

import dev.toma.pubgmc.api.capability.SpecialEquipmentSlot;
import net.minecraft.util.ResourceLocation;

public interface Backpack extends SpecialInventoryItem {

    int unlockSlotCount();

    ResourceLocation getHotbarIconPath();

    @Override
    default SpecialEquipmentSlot getSlotType() {
        return SpecialEquipmentSlot.BACKPACK;
    }
}

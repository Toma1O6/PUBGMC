package dev.toma.pubgmc.common.items.equipment;

import dev.toma.pubgmc.common.capability.player.SpecialEquipmentSlot;
import net.minecraft.util.ResourceLocation;

public interface Backpack extends SpecialInventoryItem {

    int unlockSlotCount();

    ResourceLocation getHotbarIconPath();

    @Override
    default SpecialEquipmentSlot getSlotType() {
        return SpecialEquipmentSlot.BACKPACK;
    }
}

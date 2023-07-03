package dev.toma.pubgmc.api.item;

import dev.toma.pubgmc.api.capability.SpecialEquipmentSlot;
import net.minecraft.item.ItemStack;

public interface GhillieSuit extends SpecialInventoryItem {

    int getColor(ItemStack stack);

    @Override
    default SpecialEquipmentSlot getSlotType() {
        return SpecialEquipmentSlot.GHILLIE;
    }
}

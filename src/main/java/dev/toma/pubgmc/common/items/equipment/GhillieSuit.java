package dev.toma.pubgmc.common.items.equipment;

import dev.toma.pubgmc.common.capability.player.SpecialEquipmentSlot;
import net.minecraft.item.ItemStack;

public interface GhillieSuit extends SpecialInventoryItem {

    int getColor(ItemStack stack);

    @Override
    default SpecialEquipmentSlot getSlotType() {
        return SpecialEquipmentSlot.GHILLIE;
    }
}

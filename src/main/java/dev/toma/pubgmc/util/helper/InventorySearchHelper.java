package dev.toma.pubgmc.util.helper;

import dev.toma.pubgmc.common.items.heal.ItemHealing;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

public class InventorySearchHelper {

    public static ItemStack findHealingItem(IInventory inventory) {
        for (int i = 0; i < inventory.getSizeInventory(); i++) {
            ItemStack stack = inventory.getStackInSlot(i);
            if (stack.getItem() instanceof ItemHealing) {
                return stack;
            }
        }
        return ItemStack.EMPTY;
    }
}

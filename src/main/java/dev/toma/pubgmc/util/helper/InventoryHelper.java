package dev.toma.pubgmc.util.helper;

import dev.toma.pubgmc.api.item.AIHealingItem;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

public class InventoryHelper {

    public static ItemStack findHealingItemForAi(IInventory inventory) {
        for (int i = 0; i < inventory.getSizeInventory(); i++) {
            ItemStack stack = inventory.getStackInSlot(i);
            if (stack.getItem() instanceof AIHealingItem) {
                return stack;
            }
        }
        return ItemStack.EMPTY;
    }

    public static ItemStack findExact(ItemStack stack, IInventory inventory) {
        for (int i = 0; i < inventory.getSizeInventory(); i++) {
            ItemStack itemStack = inventory.getStackInSlot(i);
            if (ItemStack.areItemStacksEqual(stack, itemStack)) {
                return itemStack;
            }
        }
        return ItemStack.EMPTY;
    }

    public static ItemStack store(IInventory inventory, ItemStack stack) {
        for (int i = 0; i < inventory.getSizeInventory(); i++) {
            ItemStack itemStack = inventory.getStackInSlot(i);
            if (itemStack.isEmpty()) {
                inventory.setInventorySlotContents(i, stack.copy());
                return ItemStack.EMPTY;
            }
        }
        return stack;
    }
}

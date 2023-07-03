package dev.toma.pubgmc.api.inventory;

import dev.toma.pubgmc.api.capability.SpecialEquipmentSlot;
import net.minecraft.item.ItemStack;

/**
 * Simple itemstack provider when you want to add ability for custom entities
 * to be able to have backpacks etc
 *
 * @author Toma
 * @since 1.8.0
 */
public interface SpecialInventoryProvider {

    /**
     * Used to retrieve equipped itemstack or empty when no item is equipped.
     *
     * @param slot Equipment slot type
     * @return Currently equipped item
     */
    ItemStack getSpecialItemFromSlot(SpecialEquipmentSlot slot);

    /**
     * Used to set provided itemstack to equipment inventory
     *
     * @param slot Equipment slot type
     * @param stack Itemstack to be set into inventory
     */
    void setSpecialItemToSlot(SpecialEquipmentSlot slot, ItemStack stack);
}

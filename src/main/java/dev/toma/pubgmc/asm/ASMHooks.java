package dev.toma.pubgmc.asm;

import dev.toma.pubgmc.Pubgmc;
import dev.toma.pubgmc.common.capability.player.IPlayerData;
import dev.toma.pubgmc.common.capability.player.PlayerData;
import dev.toma.pubgmc.common.capability.player.SpecialEquipmentSlot;
import dev.toma.pubgmc.common.items.equipment.Backpack;
import dev.toma.pubgmc.config.ConfigPMC;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;

public final class ASMHooks {

    public static final ResourceLocation LOCKED_SLOT_ICON = Pubgmc.getResource("slot/locked");

    public static boolean isItemValid(Slot slot, ItemStack stack) {
        return !isSlotLockedByBackpackLimit(slot);
    }

    public static String getSlotTexture(@Nullable String texture, Slot slot) {
        return isSlotLockedByBackpackLimit(slot) ? LOCKED_SLOT_ICON.toString() : texture;
    }

    public static boolean isSlotLocked(int slotIndex, InventoryPlayer playerInventory) {
        if (slotIndex < 9) {
            return false;
        }
        if (!ConfigPMC.common.players.inventoryRestrictions.get()) {
            return false;
        }
        IPlayerData data = PlayerData.get(playerInventory.player);
        if (data != null) {
            ItemStack stack = data.getSpecialItemFromSlot(SpecialEquipmentSlot.BACKPACK);
            if (stack.getItem() instanceof Backpack) {
                Backpack backpack = (Backpack) stack.getItem();
                int unlockedSlotCount = backpack.unlockSlotCount();
                return slotIndex >= 9 + unlockedSlotCount;
            }
        }
        return true;
    }

    private static boolean isSlotLockedByBackpackLimit(Slot slot) {
        if (slot.inventory instanceof InventoryPlayer) {
            int slotIndex = slot.getSlotIndex();
            if (slotIndex < 9) {
                return false;
            }
            InventoryPlayer playerInventory = (InventoryPlayer) slot.inventory;
            return isSlotLocked(slotIndex, playerInventory);
        }
        return false;
    }
}

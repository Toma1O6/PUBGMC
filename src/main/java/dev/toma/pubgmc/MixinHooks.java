package dev.toma.pubgmc;

import dev.toma.pubgmc.api.capability.IPlayerData;
import dev.toma.pubgmc.api.capability.PlayerDataProvider;
import dev.toma.pubgmc.api.capability.SpecialEquipmentSlot;
import dev.toma.pubgmc.api.item.Backpack;
import dev.toma.pubgmc.util.helper.GameHelper;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;

public final class MixinHooks {

    public static final ResourceLocation LOCKED_SLOT_ICON = Pubgmc.getResource("slot/locked");

    public static boolean isItemValid(Slot slot, ItemStack stack) {
        return !isSlotLockedByBackpackLimit(slot);
    }

    public static String getSlotTexture(@Nullable String texture, Slot slot) {
        return isSlotLockedByBackpackLimit(slot) ? LOCKED_SLOT_ICON.toString() : texture;
    }

    public static boolean isSlotLocked(int slotIndex, InventoryPlayer playerInventory) {
        if (slotIndex < 9 || playerInventory.player.isCreative()) {
            return false;
        }
        if (!GameHelper.hasRestrictedInventory(playerInventory.player.world)) {
            return false;
        }
        IPlayerData data = PlayerDataProvider.get(playerInventory.player);
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

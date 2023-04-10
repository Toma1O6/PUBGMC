package dev.toma.pubgmc.common.container;

import dev.toma.pubgmc.common.capability.player.IPlayerData;
import dev.toma.pubgmc.common.capability.player.PlayerData;
import dev.toma.pubgmc.common.capability.player.SpecialEquipmentSlot;
import dev.toma.pubgmc.common.items.equipment.Backpack;
import dev.toma.pubgmc.common.items.equipment.GhillieSuit;
import dev.toma.pubgmc.common.items.equipment.NightVisionGoggles;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

public class ContainerPlayerEquipment extends Container {

    private final IInventory equipmentInventory;

    public ContainerPlayerEquipment(InventoryPlayer playerInventory) {
        IPlayerData data = PlayerData.get(playerInventory.player);
        if (data == null) {
            this.equipmentInventory = null;
            return;
        }
        this.equipmentInventory = data.getEquipmentInventory();
        addSlotToContainer(new Slot(equipmentInventory, 0, 97, 8) {
            @Override
            public boolean isItemValid(ItemStack stack) {
                return stack.getItem() instanceof NightVisionGoggles;
            }
        });
        addSlotToContainer(new Slot(equipmentInventory, 1, 97, 26) {
            @Override
            public boolean isItemValid(ItemStack stack) {
                return stack.getItem() instanceof Backpack;
            }
        });
        addSlotToContainer(new Slot(equipmentInventory, 2, 97, 44) {
            @Override
            public boolean isItemValid(ItemStack stack) {
                return stack.getItem() instanceof GhillieSuit;
            }
        });

        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 9; x++) {
                addSlotToContainer(new Slot(playerInventory, 9 + (y * 9) + x, 8 + x * 18, 84 + y * 18));
            }
        }
        for (int x = 0; x < 9; x++) {
            addSlotToContainer(new Slot(playerInventory, x, 8 + x * 18, 142));
        }

        addListener(new SlotListener(data));
    }

    @Override
    public boolean canInteractWith(EntityPlayer playerIn) {
        return true;
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer playerIn, int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.inventorySlots.get(index);

        if (slot != null && slot.getHasStack()) {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            if (index < this.equipmentInventory.getSizeInventory()) {
                if (!this.mergeItemStack(itemstack1, this.equipmentInventory.getSizeInventory(), this.inventorySlots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.mergeItemStack(itemstack1, 0, this.equipmentInventory.getSizeInventory(), false)) {
                return ItemStack.EMPTY;
            }

            if (itemstack1.isEmpty()) {
                slot.putStack(ItemStack.EMPTY);
            } else {
                slot.onSlotChanged();
            }
        }

        return itemstack;
    }

    private static class SlotListener implements IContainerListener {

        private final IPlayerData data;

        public SlotListener(IPlayerData data) {
            this.data = data;
        }

        @Override
        public void sendAllContents(Container containerToSend, NonNullList<ItemStack> itemsList) {
        }

        @Override
        public void sendSlotContents(Container containerToSend, int slotInd, ItemStack stack) {
            if (slotInd < SpecialEquipmentSlot.values().length) {
                data.sync();
            }
        }

        @Override
        public void sendWindowProperty(Container containerIn, int varToUpdate, int newValue) {
        }

        @Override
        public void sendAllWindowProperties(Container containerIn, IInventory inventory) {
        }
    }
}

package com.toma.pubgmc.common.container;


import com.toma.pubgmc.common.tileentity.TileEntityAirdrop;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerAirdrop extends Container {
    private final TileEntityAirdrop tileentity;

    public ContainerAirdrop(InventoryPlayer player, TileEntityAirdrop tileentity) {
        this.tileentity = tileentity;

        this.addSlotToContainer(new Slot(tileentity, 0, 8, 20));
        this.addSlotToContainer(new Slot(tileentity, 1, 26, 20));
        this.addSlotToContainer(new Slot(tileentity, 2, 44, 20));
        this.addSlotToContainer(new Slot(tileentity, 3, 62, 20));
        this.addSlotToContainer(new Slot(tileentity, 4, 80, 20));
        this.addSlotToContainer(new Slot(tileentity, 5, 98, 20));
        this.addSlotToContainer(new Slot(tileentity, 6, 116, 20));
        this.addSlotToContainer(new Slot(tileentity, 7, 134, 20));
        this.addSlotToContainer(new Slot(tileentity, 8, 152, 20));

        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 9; x++) {
                this.addSlotToContainer(new Slot(player, x + y * 9 + 9, 8 + x * 18, 51 + y * 18));
            }
        }

        for (int x = 0; x < 9; x++) {
            this.addSlotToContainer(new Slot(player, x, 8 + x * 18, 109));
        }
    }

    @Override
    public void addListener(IContainerListener listener) {
        super.addListener(listener);
        listener.sendAllWindowProperties(this, this.tileentity);
    }

    @Override
    public void detectAndSendChanges() {
        super.detectAndSendChanges();

        for (int i = 0; i < this.listeners.size(); ++i) {
            IContainerListener listener = (IContainerListener) this.listeners.get(i);
        }
    }

    @Override
    public boolean canInteractWith(EntityPlayer playerIn) {
        return this.tileentity.isUsableByPlayer(playerIn);
    }

    public ItemStack transferStackInSlot(EntityPlayer playerIn, int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.inventorySlots.get(index);

        if (slot != null && slot.getHasStack()) {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            if (index < this.tileentity.getSizeInventory()) {
                if (!this.mergeItemStack(itemstack1, this.tileentity.getSizeInventory(), this.inventorySlots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.mergeItemStack(itemstack1, 0, this.tileentity.getSizeInventory(), false)) {
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
}

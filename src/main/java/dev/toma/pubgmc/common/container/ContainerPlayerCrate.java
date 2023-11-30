package dev.toma.pubgmc.common.container;

import dev.toma.pubgmc.common.tileentity.TileEntityPlayerCrate;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerPlayerCrate extends Container {
    private final TileEntityPlayerCrate te;

    public ContainerPlayerCrate(InventoryPlayer player, TileEntityPlayerCrate te) {
        this.te = te;
        for (int x = 0; x < 9; x++) {
            this.addSlotToContainer(new Slot(te, x, x * 18 + 8, 9));
        }
        for (int x = 0; x < 9; x++) {
            this.addSlotToContainer(new Slot(te, x + 9, x * 18 + 8, 27));
        }
        for (int x = 0; x < 9; x++) {
            this.addSlotToContainer(new Slot(te, x + 18, x * 18 + 8, 45));
        }
        for (int x = 0; x < 9; x++) {
            this.addSlotToContainer(new Slot(te, x + 27, x * 18 + 8, 63));
        }
        for (int x = 0; x < 9; x++) {
            this.addSlotToContainer(new Slot(te, x + 36, x * 18 + 8, 81));
        }

        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 9; x++) {
                this.addSlotToContainer(new Slot(player, x + y * 9 + 9, 8 + x * 18, 111 + y * 18));
            }
        }

        for (int x = 0; x < 9; x++) {
            this.addSlotToContainer(new Slot(player, x, 8 + x * 18, 169));
        }
    }

    @Override
    public void addListener(IContainerListener listener) {
        super.addListener(listener);
        listener.sendAllWindowProperties(this, this.te);
    }

    @Override
    public boolean canInteractWith(EntityPlayer playerIn) {
        return this.te.isUsableByPlayer(playerIn);
    }

    public ItemStack transferStackInSlot(EntityPlayer playerIn, int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.inventorySlots.get(index);

        if (slot != null && slot.getHasStack()) {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            if (index < this.te.getSizeInventory()) {
                if (!this.mergeItemStack(itemstack1, this.te.getSizeInventory(), this.inventorySlots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.mergeItemStack(itemstack1, 0, this.te.getSizeInventory(), false)) {
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

package dev.toma.pubgmc.common.container;

import dev.toma.pubgmc.common.tileentity.TileEntityLootCrate;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerLootCrate extends Container {

    private final TileEntityLootCrate tileEntity;

    public ContainerLootCrate(InventoryPlayer inventory, TileEntityLootCrate tileEntity) {
        this.tileEntity = tileEntity;
        for (int x = 0; x < 9; x++) {
            addSlotToContainer(new Slot(tileEntity, x, x * 18 + 8, 5));
        }

        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 9; x++) {
                addSlotToContainer(new Slot(inventory, x + y * 9 + 9, 8 + x * 18, 33 + y * 18));
            }
        }

        for (int x = 0; x < 9; x++) {
            addSlotToContainer(new Slot(inventory, x, 8 + x * 18, 91));
        }
    }

    @Override
    public void addListener(IContainerListener listener) {
        super.addListener(listener);
        listener.sendAllWindowProperties(this, this.tileEntity);
    }

    @Override
    public boolean canInteractWith(EntityPlayer playerIn) {
        return tileEntity.isUsableByPlayer(playerIn);
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer playerIn, int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.inventorySlots.get(index);

        if (slot != null && slot.getHasStack()) {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            if (index < this.tileEntity.getSizeInventory()) {
                if (!this.mergeItemStack(itemstack1, this.tileEntity.getSizeInventory(), this.inventorySlots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.mergeItemStack(itemstack1, 0, this.tileEntity.getSizeInventory(), false)) {
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

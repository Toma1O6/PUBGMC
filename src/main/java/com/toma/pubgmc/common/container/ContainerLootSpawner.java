package com.toma.pubgmc.common.container;

import com.toma.pubgmc.common.tileentity.TileEntityLootSpawner;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ContainerLootSpawner extends Container {
    private final TileEntityLootSpawner tileentity;

    public ContainerLootSpawner(InventoryPlayer player, TileEntityLootSpawner tileentity) {
        this.tileentity = tileentity;

        for (int x = 0; x < 9; x++) {
            this.addSlotToContainer(new Slot(tileentity, x, x * 18 + 8, 5));
        }

        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 9; x++) {
                this.addSlotToContainer(new Slot(player, x + y * 9 + 9, 8 + x * 18, 33 + y * 18));
            }
        }

        for (int x = 0; x < 9; x++) {
            this.addSlotToContainer(new Slot(player, x, 8 + x * 18, 91));
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
            IContainerListener listener = this.listeners.get(i);
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

    //To keep everything synced with client, i.e. to prevent rendering items which are no longer in inventory
    @Override
    public void onContainerClosed(EntityPlayer playerIn) {
        World world = playerIn.world;
        world.notifyBlockUpdate(tileentity.getPos(), world.getBlockState(tileentity.getPos()), world.getBlockState(tileentity.getPos()), 3);
    }
}

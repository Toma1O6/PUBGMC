package com.toma.pubgmc.common.items.guns.attachments;

import com.toma.pubgmc.common.items.guns.GunBase;
import com.toma.pubgmc.common.items.guns.attachments.IAttachment.Type;
import com.toma.pubgmc.common.network.PacketHandler;
import com.toma.pubgmc.common.network.sp.PacketUpdateAttachmentGUI;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerAttachments extends Container {
    private InventoryAttachments inv;
    private InventoryPlayer playerInv;
    private EntityPlayer invUser;
    public ItemStack stack;
    private short i;

    public ContainerAttachments(InventoryPlayer playerInv, EntityPlayer player) {
        this.playerInv = playerInv;
        this.invUser = player;
        this.stack = player.getHeldItemMainhand();
        GunBase gun = (GunBase) stack.getItem();
        inv = new InventoryAttachments(5, gun);
        int[] xPos = {20, 42, 80, 124, 80};
        int[] yPos = {31, 50, 50, 31, 12};
        for(i = 0; i < Type.values().length; i++) {
            addSlotToContainer(new Slot(inv, i, xPos[i], yPos[i]) {
                @Override
                public boolean isItemValid(ItemStack stack) {
                    return stack.getItem() instanceof ItemAttachment && ((ItemAttachment)stack.getItem()).getType() == Type.values()[ContainerAttachments.this.i];
                }

                @Override
                public boolean isEnabled() {
                    int j = ContainerAttachments.this.i;
                    if(!(ContainerAttachments.this.stack.getItem() instanceof GunBase)) {
                        return false;
                    }
                    switch(j) {
                        case 0: return ((GunBase)ContainerAttachments.this.stack.getItem()).getBarrelAttachments().length > 0;
                        case 1: return ((GunBase)ContainerAttachments.this.stack.getItem()).getGripAttachments().length > 0;
                        case 2: return ((GunBase)ContainerAttachments.this.stack.getItem()).getMagazineAttachments().length > 0;
                        case 3: return ((GunBase)ContainerAttachments.this.stack.getItem()).getStockAttachments().length > 0;
                        case 4: return ((GunBase)ContainerAttachments.this.stack.getItem()).getScopeAttachments().length > 0;
                        default: return false;
                    }
                }
            });
        }
        // Vanilla inv
        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 9; x++) {
                addSlotToContainer(new Slot(playerInv, x + y * 9 + 9, 8 + x * 18, 88 + y * 18));
            }
        }
        for (int x = 0; x < 9; x++) {
            addSlotToContainer(new Slot(playerInv, x, x * 18 + 8, 146));
        }
        inv.openInventory(player);
    }

    @Override
    public boolean canInteractWith(EntityPlayer playerIn) {
        return true;
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer playerIn, int index) {
        ItemStack stack = ItemStack.EMPTY;
        Slot slot = inventorySlots.get(index);

        if (slot != null && slot.getHasStack()) {
            ItemStack stack1 = slot.getStack();
            stack = stack1.copy();

            //Getting the weapons and attachments into right place in the container
            if (index >= 7 && index <= 42) {
                if (slot.getStack().getItem() instanceof GunBase) {
                    if (inventorySlots.get(0).getHasStack()) {
                        return ItemStack.EMPTY;
                    } else {
                        inventorySlots.get(0).putStack(stack);
                        slot.putStack(ItemStack.EMPTY);
                    }
                } else if (slot.getStack().getItem() instanceof ItemAttachment) {
                    if (((ItemAttachment) slot.getStack().getItem()).getType() == Type.SCOPE) {
                        if (inventorySlots.get(1).getHasStack()) {
                            return ItemStack.EMPTY;
                        } else {
                            inventorySlots.get(1).putStack(stack);
                            slot.putStack(ItemStack.EMPTY);
                        }
                    } else if (((ItemAttachment) slot.getStack().getItem()).getType() == Type.BARREL) {
                        if (inventorySlots.get(2).getHasStack()) {
                            return ItemStack.EMPTY;
                        } else {
                            inventorySlots.get(2).putStack(stack);
                            slot.putStack(ItemStack.EMPTY);
                        }
                    } else if (((ItemAttachment) slot.getStack().getItem()).getType() == Type.GRIP) {
                        if (inventorySlots.get(3).getHasStack()) {
                            return ItemStack.EMPTY;
                        } else {
                            inventorySlots.get(3).putStack(stack);
                            slot.putStack(ItemStack.EMPTY);
                        }
                    } else if (((ItemAttachment) slot.getStack().getItem()).getType() == Type.MAGAZINE) {
                        if (inventorySlots.get(4).getHasStack()) {
                            return ItemStack.EMPTY;
                        } else {
                            inventorySlots.get(4).putStack(stack);
                            slot.putStack(ItemStack.EMPTY);
                        }
                    } else if (((ItemAttachment) slot.getStack().getItem()).getType() == Type.STOCK) {
                        if (inventorySlots.get(5).getHasStack()) {
                            return ItemStack.EMPTY;
                        } else {
                            inventorySlots.get(5).putStack(stack);
                            slot.putStack(ItemStack.EMPTY);
                        }
                    } else return ItemStack.EMPTY;
                }
            } else {
                if (!this.mergeItemStack(stack1, 7, 43, false)) {
                    return ItemStack.EMPTY;
                }

                slot.onSlotChange(stack1, stack);
            }

            //Getting items from the gui
            if (index >= 0 && index <= 6) {
                if (!this.mergeItemStack(stack1, 7, 43, true)) {
                    return ItemStack.EMPTY;
                }

                slot.onSlotChange(stack1, stack);
            }
        }

        return ItemStack.EMPTY;
    }

    @Override
    public void detectAndSendChanges() {
        if (!invUser.world.isRemote)
            PacketHandler.sendToClient(new PacketUpdateAttachmentGUI(inv), (EntityPlayerMP) invUser);
        super.detectAndSendChanges();
    }

    @Override
    public void onContainerClosed(EntityPlayer playerIn) {
        if (!playerIn.world.isRemote) {
            InventoryHelper.dropInventoryItems(playerIn.world, playerIn, inv);
        }
    }

    public InventoryAttachments getAttachmentInventory() {
        return inv;
    }
}

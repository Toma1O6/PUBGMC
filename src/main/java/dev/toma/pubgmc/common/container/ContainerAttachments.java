package dev.toma.pubgmc.common.container;

import dev.toma.pubgmc.common.inventory.InventoryAttachments;
import dev.toma.pubgmc.common.items.attachment.AttachmentType;
import dev.toma.pubgmc.common.items.attachment.ItemAttachment;
import dev.toma.pubgmc.common.items.guns.GunAttachments;
import dev.toma.pubgmc.common.items.guns.GunBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerAttachments extends Container {

    final InventoryAttachments inventory;
    public final ItemStack stack;

    public ContainerAttachments(EntityPlayer player) {
        this.inventory = new InventoryAttachments(player);
        inventory.openInventory(player);
        this.stack = player.getHeldItemMainhand();
        for (AttachmentType<?> type : AttachmentType.allTypes) {
            addSlotToContainer(new AttachmentSlot(inventory, stack, type));
        }
        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 9; x++) {
                addSlotToContainer(new Slot(player.inventory, x + y * 9 + 9, 8 + x * 18, 115 + y * 18));
            }
        }
        for (int x = 0; x < 9; x++) {
            addSlotToContainer(new Slot(player.inventory, x, 8 + x * 18, 173));
        }
    }

    public InventoryAttachments getAttachments() {
        return inventory;
    }

    @Override
    public void onContainerClosed(EntityPlayer playerIn) {
        inventory.closeInventory(playerIn);
    }

    @Override
    public boolean canInteractWith(EntityPlayer playerIn) {
        return true;
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer player, int index) {
        ItemStack itemStack;
        Slot slot = inventorySlots.get(index);
        if (slot != null && slot.getHasStack()) {
            ItemStack itemStack1 = slot.getStack();
            itemStack = itemStack1.copy();
            if (index <= 4) {
                if (!this.mergeItemStack(itemStack1, 5, 41, false)) {
                    return ItemStack.EMPTY;
                }
                slot.onSlotChange(itemStack, itemStack1);
            } else {
                boolean handleInventoryTransfer = true;
                if (itemStack1.getItem() instanceof ItemAttachment) {
                    ItemAttachment item = (ItemAttachment) itemStack1.getItem();
                    int id = item.getType().getIndex();
                    if (!(stack.getItem() instanceof GunBase)) {
                        return ItemStack.EMPTY;
                    }
                    GunAttachments attachments = ((GunBase) stack.getItem()).getAttachments();
                    if (attachments.supports(item)) {
                        handleInventoryTransfer = false;
                        Slot target = inventorySlots.get(id);
                        if (target != null) {
                            ItemStack copy = null;
                            if (target.getHasStack()) {
                                copy = target.getStack();
                            }
                            target.putStack(itemStack1.copy());
                            slot.getStack().setCount(0);
                            if (copy != null) slot.putStack(copy);
                            return ItemStack.EMPTY;
                        }
                    }
                }
                if (handleInventoryTransfer) {
                    if (index > 4 && index < 32) {
                        if (!mergeItemStack(itemStack1, 32, 41, false)) {
                            return ItemStack.EMPTY;
                        }
                        slot.onSlotChange(itemStack1, itemStack);
                    } else if (index >= 32) {
                        if (!mergeItemStack(itemStack1, 5, 32, false)) {
                            return ItemStack.EMPTY;
                        }
                        slot.onSlotChange(itemStack1, itemStack);
                    }
                }
            }
        }
        return ItemStack.EMPTY;
    }

    public static class AttachmentSlot extends Slot {

        final ItemStack stack;
        final AttachmentType<?> type;

        public AttachmentSlot(InventoryAttachments inventory, ItemStack stack, AttachmentType<?> type) {
            super(inventory, type.getIndex(), type.getX(), type.getY());
            this.stack = stack;
            this.type = type;
        }

        boolean isSupported() {
            if (stack.getItem() instanceof GunBase) {
                return ((GunBase) stack.getItem()).getAttachments().supportsType(type);
            }
            return false;
        }

        @Override
        public int getSlotStackLimit() {
            return 1;
        }

        @Override
        public boolean isItemValid(ItemStack stack) {
            if (this.stack.getItem() instanceof GunBase && stack.getItem() instanceof ItemAttachment) {
                GunAttachments attachments = ((GunBase) this.stack.getItem()).getAttachments();
                ItemAttachment attachment = (ItemAttachment) stack.getItem();
                return attachment.getType() == this.type && attachments.supports(attachment);
            }
            return false;
        }
    }
}

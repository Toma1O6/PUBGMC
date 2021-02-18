package dev.toma.pubgmc.common.items.guns.attachments;

import dev.toma.pubgmc.DevUtil;
import dev.toma.pubgmc.common.items.guns.GunBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
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
        inv = new InventoryAttachments(5, stack);
        int[] xPos = {20, 48, 80, 135, 90};
        int[] yPos = {31, 60, 65, 31, 12};
        for(i = 0; i < IAttachment.Type.values().length; i++) {
            addSlotToContainer(this.new AttachmentSlot(inv, i, xPos[i], yPos[i], stack));

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
        ItemStack stack;
        Slot slot = inventorySlots.get(index);

        if (slot != null && slot.getHasStack()) {
            ItemStack stack1 = slot.getStack();
            stack = stack1.copy();

            if (index >= 5 && index <= 40) {
                if (stack.getItem() instanceof ItemAttachment) {
                    for(IAttachment.Type type : IAttachment.Type.values()) {
                        if(((ItemAttachment)stack.getItem()).getType() == type) {
                            if(!canSwitchItems(slot)) {
                                return ItemStack.EMPTY;
                            }
                        }
                    }
                    return ItemStack.EMPTY;
                }
            } else {
                if (!this.mergeItemStack(stack1, 5, 41, false)) {
                    return ItemStack.EMPTY;
                }
                slot.onSlotChange(stack1, stack);
            }
        }
        return ItemStack.EMPTY;
    }

    @Override
    public void onContainerClosed(EntityPlayer playerIn) {
        inv.closeInventory(playerIn);
    }

    public InventoryAttachments getAttachmentInventory() {
        return inv;
    }

    private boolean canSwitchItems(Slot slot) {
        ItemStack stack = slot.getStack();
        if(stack.isEmpty() || (slot instanceof AttachmentSlot && !((AttachmentSlot) slot).isSlotAvailable())) {
            return false;
        }
        Slot toSwitch = this.inventorySlots.get(this.getAppropriateSlot((ItemAttachment) slot.getStack().getItem()));
        ItemStack stackToSwitch = toSwitch.getStack();
        if(stackToSwitch.isEmpty()) {
            if(!this.canAttachTo((GunBase)this.stack.getItem(), (ItemAttachment)stack.getItem())) {
                return false;
            }

            toSwitch.putStack(stack.copy());
            slot.putStack(ItemStack.EMPTY);
        } else {
            if(!this.canAttachTo((GunBase)this.stack.getItem(), (ItemAttachment)stack.getItem())) {
                return false;
            }

            ItemStack help = stackToSwitch.copy();
            toSwitch.putStack(stack.copy());
            slot.putStack(help.copy());
        }
        return true;
    }

    private boolean canAttachTo(GunBase gun, ItemAttachment attachment) {
        switch(attachment.getType()) {
            case BARREL:
                return DevUtil.containsD(gun.getBarrelAttachments(), attachment);
            case GRIP:
                return DevUtil.containsD(gun.getGripAttachments(), attachment);
            case MAGAZINE:
                return DevUtil.containsD(gun.getMagazineAttachments(), attachment);
            case SCOPE:
                return DevUtil.containsD(gun.getScopeAttachments(), attachment);
            case STOCK:
                return DevUtil.containsD(gun.getStockAttachments(), attachment);
            default: return false;
        }
    }

    private int getAppropriateSlot(ItemAttachment item) {
        return item.getType().ordinal();
    }

    class AttachmentSlot extends Slot {

        private boolean enabled;

        public AttachmentSlot(IInventory inv, int i, int x, int y, ItemStack stack) {
            super(inv, i, x, y);
            this.updateStatus(stack);
        }

        private void updateStatus(ItemStack stack) {
            enabled = this.enabled(stack);
        }

        private boolean enabled(ItemStack stack) {
            if(!(stack.getItem() instanceof GunBase)) {
                return false;
            }
            switch(this.getSlotIndex()) {
                case 0: return ((GunBase)stack.getItem()).getBarrelAttachments().length > 0;
                case 1: return ((GunBase)stack.getItem()).getGripAttachments().length > 0;
                case 2: return ((GunBase)stack.getItem()).getMagazineAttachments().length > 0;
                case 3: return ((GunBase)stack.getItem()).getStockAttachments().length > 0;
                case 4: return ((GunBase)stack.getItem()).getScopeAttachments().length > 0;
                default: return false;
            }
        }

        @Override
        public boolean isItemValid(ItemStack stack) {
            return stack.getItem() instanceof ItemAttachment && ((ItemAttachment)stack.getItem()).getType() == IAttachment.Type.values()[this.getSlotIndex()];
        }

        public boolean isSlotAvailable() {
            return enabled;
        }

        @Override
        public boolean isEnabled() {
            return enabled;
        }
    }
}

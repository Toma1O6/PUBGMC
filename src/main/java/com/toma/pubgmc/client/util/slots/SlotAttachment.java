package com.toma.pubgmc.client.util.slots;

import com.toma.pubgmc.common.items.guns.attachments.IAttachment.Type;
import com.toma.pubgmc.common.items.guns.attachments.InventoryAttachments;
import com.toma.pubgmc.common.items.guns.attachments.ItemAttachment;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotAttachment extends Slot {
    private Type type;

    public SlotAttachment(Type type, int id) {
        super(new InventoryAttachments(), id, 0, 0);
        this.type = type;
    }

    public SlotAttachment(InventoryAttachments inv, int id, int x, int y, Type type) {
        super(inv, id, x, y);
        this.type = type;
    }

    @Override
    public boolean isItemValid(ItemStack stack) {
        if (stack.getItem() instanceof ItemAttachment) {
            ItemAttachment attach = (ItemAttachment) stack.getItem();
            if (attach.getType() == type) {
                return true;
            }
        }

        return false;
    }
}

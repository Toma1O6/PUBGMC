package dev.toma.pubgmc.common.inventory;

import dev.toma.pubgmc.common.items.attachment.AttachmentType;
import dev.toma.pubgmc.common.items.attachment.ItemAttachment;
import dev.toma.pubgmc.common.items.guns.GunAttachments;
import dev.toma.pubgmc.common.items.guns.GunBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class InventoryAttachments extends InventoryBasic {

    final EntityPlayer player;
    ItemStack displayItem;

    public InventoryAttachments(EntityPlayer player) {
        super("inventory.attachments", false, AttachmentType.allTypes.length);
        this.player = player;
    }

    @Override
    public void openInventory(EntityPlayer player) {
        ItemStack stack = player.getHeldItemMainhand();
        this.displayItem = stack.copy();
        if (validate(player, stack)) {
            GunBase gun = (GunBase) stack.getItem();
            NBTTagCompound nbt = gun.getOrCreateGunData(stack);
            NBTTagCompound att;
            if (!nbt.hasKey("attachments", Constants.NBT.TAG_COMPOUND)) {
                att = new NBTTagCompound();
                nbt.setTag("attachments", att);
            } else {
                att = nbt.getCompoundTag("attachments");
            }
            for (AttachmentType<?> type : AttachmentType.allTypes) {
                ItemStack itemStack = detach(att, type);
                if (itemStack.getItem() instanceof ItemAttachment) {
                    ItemAttachment attachment = (ItemAttachment) itemStack.getItem();
                    if (attachment.getType() == type) {
                        setInventorySlotContents(type.getIndex(), itemStack);
                    }
                }
            }
        }
    }

    @Override
    public void closeInventory(EntityPlayer player) {
        ItemStack stack = player.getHeldItemMainhand();
        if (validate(player, stack)) {
            GunBase gun = (GunBase) stack.getItem();
            GunAttachments attachments = gun.getAttachments();
            NBTTagCompound tag = gun.getOrCreateGunData(stack);
            NBTTagCompound nbt;
            if (!tag.hasKey("attachments", Constants.NBT.TAG_COMPOUND)) {
                nbt = new NBTTagCompound();
                tag.setTag("attachments", nbt);
            } else {
                nbt = tag.getCompoundTag("attachments");
            }
            for (AttachmentType<?> type : AttachmentType.allTypes) {
                ItemStack itemStack = this.getStackInSlot(type.getIndex());
                if (!itemStack.isEmpty()) {
                    attach(nbt, itemStack, attachments, false);
                }
            }
        }
    }

    @Override
    public void markDirty() {
        ItemStack held = player.getHeldItemMainhand();
        if (validate(player, held)) {
            GunAttachments attachments = ((GunBase) held.getItem()).getAttachments();
            NBTTagCompound att = new NBTTagCompound();
            NBTTagCompound nbt = new NBTTagCompound();
            nbt.setTag("attachments", att);
            displayItem.setTagCompound(nbt);
            for (AttachmentType<?> type : AttachmentType.allTypes) {
                ItemStack stack = this.getStackInSlot(type.getIndex());
                if (!stack.isEmpty()) {
                    attach(att, stack, attachments, true);
                }
            }
        }
        super.markDirty();
    }

    public ItemStack getDisplayItem() {
        return displayItem;
    }

    ItemStack detach(NBTTagCompound nbt, AttachmentType<?> type) {
        String key = type.getName();
        if (nbt.hasKey(key, Constants.NBT.TAG_STRING)) {
            ResourceLocation location = new ResourceLocation(nbt.getString(key));
            nbt.removeTag(key);
            Item item = ForgeRegistries.ITEMS.getValue(location);
            return new ItemStack(item);
        }
        return ItemStack.EMPTY;
    }

    void attach(NBTTagCompound nbt, ItemStack stack, GunAttachments attachments, boolean simulate) {
        if (stack.getItem() instanceof ItemAttachment) {
            ItemAttachment attachment = (ItemAttachment) stack.getItem();
            AttachmentType<?> type = attachment.getType();
            String key = type.getName();
            if (!attachments.supports(attachment)) {
                if (!simulate) {
                    dropItem(stack);
                }
                return;
            }
            nbt.setString(key, attachment.getRegistryName().toString());
        } else if (!simulate) {
            dropItem(stack);
        }
    }

    boolean validate(EntityPlayer player, ItemStack stack) {
        if (!(stack.getItem() instanceof GunBase)) {
            for (int i = 0; i < getSizeInventory(); i++) {
                ItemStack itemStack = getStackInSlot(i);
                if (!itemStack.isEmpty()) {
                    dropItem(itemStack);
                }
            }
            return false;
        }
        return true;
    }

    void dropItem(ItemStack stack) {
        if (!player.world.isRemote) {
            EntityItem item = new EntityItem(player.world, player.posX, player.posY, player.posZ, stack.copy());
            stack.setCount(0);
            item.setPickupDelay(40);
            player.world.spawnEntity(item);
        }
    }
}

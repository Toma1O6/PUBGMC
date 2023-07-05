package dev.toma.pubgmc.common.items.guns;

import dev.toma.pubgmc.DevUtil;
import dev.toma.pubgmc.common.items.attachment.AttachmentType;
import dev.toma.pubgmc.common.items.attachment.ItemAttachment;
import dev.toma.pubgmc.util.LazyLoad;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.Constants;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public class GunAttachments {

    Map<AttachmentType<?>, LazyLoad<List<ItemAttachment>>> uninitialized;
    Map<AttachmentType<?>, List<ItemAttachment>> initializedAttachments;

    GunAttachments(Builder builder) {
        this.uninitialized = builder.map;
    }

    @SuppressWarnings("unchecked")
    public <T extends ItemAttachment> List<T> getList(AttachmentType<T> type) {
        if(!this.isLoaded()) {
            load();
        }
        return (List<T>) initializedAttachments.get(type);
    }

    public boolean supportsType(AttachmentType<?> type) {
        List<?> list = getList(type);
        return list != null && !list.isEmpty();
    }

    public boolean supports(ItemAttachment attachment) {
        AttachmentType<?> type = attachment.getType();
        List<?> list = getList(type);
        if(list != null && !list.isEmpty()) {
            return DevUtil.contains(attachment, list, (attachment1, o) -> o == attachment1);
        }
        return false;
    }

    public void attach(ItemStack gunStack, ItemAttachment attachment) {
        if(gunStack.getItem() instanceof GunBase) {
            GunBase gunBase = (GunBase) gunStack.getItem();
            NBTTagCompound nbt = gunBase.getOrCreateGunData(gunStack);
            NBTTagCompound attachmentTag;
            if(!nbt.hasKey("attachments")) {
                attachmentTag = new NBTTagCompound();
                nbt.setTag("attachments", attachmentTag);
            } else {
                attachmentTag = nbt.getCompoundTag("attachments");
            }
            String key = attachment.getType().getName();
            attachmentTag.setString(key, attachment.getRegistryName().toString());
        }
    }

    public boolean hasAttachment(ItemStack gunStack, AttachmentType<?> attachmentType) {
        if (gunStack.getItem() instanceof GunBase) {
            NBTTagCompound nbt = gunStack.getTagCompound();
            if (nbt == null || !nbt.hasKey("attachments", Constants.NBT.TAG_COMPOUND)) {
                return false;
            }
            NBTTagCompound attachmentsTag = nbt.getCompoundTag("attachments");
            String tag = attachmentType.getName();
            return attachmentsTag.hasKey(tag, Constants.NBT.TAG_STRING);
        }
        return false;
    }

    public Map<AttachmentType<?>, List<ItemAttachment>> getCompatibilityMap() {
        return initializedAttachments;
    }

    public boolean isLoaded() {
        return uninitialized == null;
    }

    public void load() {
        initializedAttachments = new HashMap<>();
        uninitialized.forEach((k, v) -> initializedAttachments.put(k, v.get()));
        uninitialized = null;
    }

    public static class Builder {

        final GunBuilder gunBuilder;
        Map<AttachmentType<?>, LazyLoad<List<ItemAttachment>>> map = new HashMap<>();

        public Builder(GunBuilder builder) {
            this.gunBuilder = builder;
        }

        public <T extends ItemAttachment> Builder addForType(AttachmentType<T> type, Supplier<T[]> supplier) {
            map.put(type, new LazyLoad<>(() -> Arrays.asList(supplier.get())));
            return this;
        }

        public GunBuilder build() {
            return gunBuilder.setAttachments(new GunAttachments(this));
        }
    }
}

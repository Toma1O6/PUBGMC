package dev.toma.pubgmc.common.items.guns;

import dev.toma.pubgmc.DevUtil;
import dev.toma.pubgmc.common.items.attachment.AttachmentType;
import dev.toma.pubgmc.common.items.attachment.ItemAttachment;
import dev.toma.pubgmc.util.LazyLoad;
import net.minecraft.item.ItemStack;
import net.minecraft.util.LazyLoadBase;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public class GunAttachments {

    Map<AttachmentType<?>, LazyLoad<List<?>>> uninitialized;
    Map<AttachmentType<?>, List<?>> initializedAttachments;

    GunAttachments(Builder builder) {
        this.uninitialized = builder.map;
    }

    public <T> List<T> getList(AttachmentType<T> type) {
        if(uninitialized != null) {
            initializedAttachments = new HashMap<>();
            uninitialized.forEach((k, v) -> initializedAttachments.put(k, v.get()));
            uninitialized = null;
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
        // TODO
    }

    public static class Builder {

        final GunBuilder gunBuilder;
        Map<AttachmentType<?>, LazyLoad<List<?>>> map = new HashMap<>();

        public Builder(GunBuilder builder) {
            this.gunBuilder = builder;
        }

        public <T> Builder addForType(AttachmentType<T> type, Supplier<T[]> supplier) {
            map.put(type, new LazyLoad<>(() -> Arrays.asList(supplier.get())));
            return this;
        }

        public GunBuilder build() {
            return gunBuilder.setAttachments(new GunAttachments(this));
        }
    }
}

package dev.toma.pubgmc.common.items.guns;

import dev.toma.pubgmc.common.items.attachment.AttachmentType;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GunAttachments {

    final Map<AttachmentType<?>, List<?>> typeListMap;

    GunAttachments(Builder builder) {
        this.typeListMap = builder.map;
    }

    public static class Builder {

        Map<AttachmentType<?>, List<?>> map = new HashMap<>();

        public <T> Builder addForType(AttachmentType<T> type, T... attachments) {
            map.put(type, Arrays.asList(attachments));
            return this;
        }

        public GunAttachments build() {
            return new GunAttachments(this);
        }
    }
}

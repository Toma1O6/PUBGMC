package com.toma.pubgmc.common.items.guns.attachments;

import com.toma.pubgmc.common.items.PMCItem;

public abstract class ItemAttachmentNew extends PMCItem implements Attachment {

    public static final int SLOT_BARREL = 0;
    public static final int SLOT_GRIP = 1;
    public static final int SLOT_MAGAZINE = 2;
    public static final int SLOT_STOCK = 3;
    public static final int SLOT_SCOPE = 4;

    public ItemAttachmentNew(String name) {
        super(name);
    }
}

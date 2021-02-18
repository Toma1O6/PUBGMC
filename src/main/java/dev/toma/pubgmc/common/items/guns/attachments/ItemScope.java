package dev.toma.pubgmc.common.items.guns.attachments;

public class ItemScope extends ItemAttachmentNew {

    public final float min_zoom, max_zoom;

    public ItemScope(String name, float zoom) {
        this(name, zoom, zoom);
    }

    public ItemScope(String name, float fovMin, float fovMax) {
        super(name);
        this.min_zoom = fovMin;
        this.max_zoom = fovMax;
    }

    @Override
    public int getSlot() {
        return SLOT_SCOPE;
    }

    @Override
    public AttachmentLootOptions getLootOptions() {
        return AttachmentLootOptions.from(0);
    }
}

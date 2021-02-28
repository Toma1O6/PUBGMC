package dev.toma.pubgmc.common.items.attachment;

public class ItemMagazine extends ItemAttachment implements Magazine {

    final boolean extended;
    final boolean quickdraw;

    public ItemMagazine(String name, boolean extended, boolean quickdraw) {
        super(name);
        this.extended = extended;
        this.quickdraw = quickdraw;
    }

    @Override
    public boolean isExtended() {
        return extended;
    }

    @Override
    public boolean isQuickdraw() {
        return quickdraw;
    }
}

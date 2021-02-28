package dev.toma.pubgmc.common.items.attachment;

public class ItemStock extends ItemAttachment implements Stock {

    final boolean fastReload;
    final float ads;

    public ItemStock(String name, boolean fastReload, float ads) {
        super(name);
        this.fastReload = fastReload;
        this.ads = ads;
    }

    @Override
    public boolean isFasterReload() {
        return fastReload;
    }

    @Override
    public float applyAdsSpeedMultiplier(float in) {
        return in * ads;
    }
}

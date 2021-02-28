package dev.toma.pubgmc.common.items.attachment;

public class ItemGrip extends ItemAttachment implements Grip {

    final float verticalRecoil;
    final float horizontalRecoil;
    final float ads;

    public ItemGrip(String name, float verticalRecoil, float horizontalRecoil) {
        this(name, verticalRecoil, horizontalRecoil, 1.0F);
    }

    public ItemGrip(String name, float verticalRecoil, float horizontalRecoil, float ads) {
        super(name);
        this.verticalRecoil = verticalRecoil;
        this.horizontalRecoil = horizontalRecoil;
        this.ads = ads;
    }

    @Override
    public float applyVerticalRecoilMultiplier(float in) {
        return in * verticalRecoil;
    }

    @Override
    public float applyHorizontalRecoilMultiplier(float in) {
        return in * horizontalRecoil;
    }

    @Override
    public float applyAdsSpeedMultiplier(float in) {
        return in * ads;
    }
}

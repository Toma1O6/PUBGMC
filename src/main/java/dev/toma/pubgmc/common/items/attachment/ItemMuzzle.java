package dev.toma.pubgmc.common.items.attachment;

public class ItemMuzzle extends ItemAttachment implements Muzzle {

    final float verticalRecoil;
    final float horizontalRecoil;
    final boolean silent;

    public ItemMuzzle(String name, boolean silent) {
        this(name, 1.0F, 1.0F, silent);
    }

    public ItemMuzzle(String name, float verticalRecoil, float horizontalRecoil) {
        this(name, verticalRecoil, horizontalRecoil, false);
    }

    public ItemMuzzle(String name, float verticalRecoil, float horizontalRecoil, boolean silent) {
        super(name);
        this.verticalRecoil = verticalRecoil;
        this.horizontalRecoil = horizontalRecoil;
        this.silent = silent;
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
    public boolean isSilenced() {
        return silent;
    }
}

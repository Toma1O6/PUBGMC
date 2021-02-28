package dev.toma.pubgmc.common.items.attachment;

public interface Muzzle {

    float applyVerticalRecoilMultiplier(float in);

    float applyHorizontalRecoilMultiplier(float in);

    boolean isSilenced();
}

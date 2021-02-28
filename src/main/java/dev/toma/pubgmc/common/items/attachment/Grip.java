package dev.toma.pubgmc.common.items.attachment;

public interface Grip {

    float applyVerticalRecoilMultiplier(float in);

    float applyHorizontalRecoilMultiplier(float in);

    float applyAdsSpeedMultiplier(float in);
}

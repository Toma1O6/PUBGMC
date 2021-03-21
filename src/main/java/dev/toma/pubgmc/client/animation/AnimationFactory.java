package dev.toma.pubgmc.client.animation;

import dev.toma.pubgmc.client.animation.impl.MultiFrameAnimation;
import dev.toma.pubgmc.client.animation.impl.TickableAnimation;

public class AnimationFactory {

    public static TickableAnimation createRecoilAnimation(float yaw, float pitch) {
        float scale = Math.min(Math.abs(yaw * pitch) * 0.05F, 0.1F);
        return new MultiFrameAnimation(4, AnimationSpec.jump(AnimationElement.ITEM_AND_HANDS, 0.0, 0.0, scale, pitch, yaw, 0.0));
    }
}

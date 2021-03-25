package dev.toma.pubgmc.client.animation;

import dev.toma.pubgmc.client.animation.impl.AimAnimation;
import dev.toma.pubgmc.client.animation.impl.MultiFrameAnimation;
import dev.toma.pubgmc.client.animation.impl.TickableAnimation;
import dev.toma.pubgmc.client.renderer.item.gun.WeaponRenderer;
import dev.toma.pubgmc.common.items.guns.GunBase;
import net.minecraft.item.ItemStack;

public class AnimationDispatcher {

    public static TickableAnimation dispatchRecoilAnimation(float yaw, float pitch) {
        float scale = Math.min(Math.abs(yaw * pitch) * 0.05F, 0.1F);
        return new MultiFrameAnimation(2, AnimationSpec.jump(AnimationElement.ITEM_AND_HANDS, 0.0, 0.0, scale, pitch, yaw, 0.0));
    }

    public static AimAnimation dispatchAimAnimation(GunBase item, ItemStack stack) {
        AnimationSpec spec = ((WeaponRenderer) item.getTileEntityItemStackRenderer()).getAimAnimation(stack);
        return new AimAnimation(spec);
    }
}

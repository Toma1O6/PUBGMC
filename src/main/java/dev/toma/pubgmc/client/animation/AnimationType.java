package dev.toma.pubgmc.client.animation;

import dev.toma.pubgmc.client.animation.impl.AimAnimation;
import dev.toma.pubgmc.client.animation.impl.TickableAnimation;
import dev.toma.pubgmc.client.animation.interfaces.Animation;
import dev.toma.pubgmc.common.items.guns.GunBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

import java.util.function.Function;

public class AnimationType<A extends Animation> {

    public static final AnimationType<TickableAnimation> RECOIL_ANIMATION_TYPE = create(player -> AnimationDispatcher.dispatchRecoilAnimation(player.rotationYaw - player.prevRotationYaw, player.rotationPitch - player.prevRotationYaw));
    public static final AnimationType<AimAnimation> AIM_ANIMATION_TYPE = create(player -> {
        ItemStack stack = player.getHeldItemMainhand();
        if(stack.getItem() instanceof GunBase) {
            return AnimationDispatcher.dispatchAimAnimation((GunBase) stack.getItem(), stack);
        }
        return new AimAnimation(AnimationSpec.EMPTY_SPEC);
    });

    final Function<EntityPlayer, A> factory;

    AnimationType(Function<EntityPlayer, A> factory) {
        this.factory = factory;
    }

    public A createAnimation(EntityPlayer player) {
        return factory.apply(player);
    }

    public static <A extends Animation> AnimationType<A> create(Function<EntityPlayer, A> factory) {
        return new AnimationType<>(factory);
    }
}

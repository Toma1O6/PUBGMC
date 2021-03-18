package dev.toma.pubgmc.client.animation;

import dev.toma.pubgmc.client.animation.impl.TickableAnimation;
import dev.toma.pubgmc.client.animation.interfaces.Animation;
import net.minecraft.entity.player.EntityPlayer;

import java.util.function.Function;

public class AnimationType<A extends Animation> {

    public static final AnimationType<TickableAnimation> RECOIL_ANIMATION_TYPE = create(player -> AnimationFactory.createRecoilAnimation(player.rotationYaw - player.prevRotationYaw, player.rotationPitch - player.prevRotationYaw));

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

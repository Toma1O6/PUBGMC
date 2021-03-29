package dev.toma.pubgmc.client.animation;

import dev.toma.pubgmc.client.animation.impl.AimAnimation;
import dev.toma.pubgmc.client.animation.impl.AnimatorAnimation;
import dev.toma.pubgmc.client.animation.impl.TickableAnimation;
import dev.toma.pubgmc.client.animation.interfaces.Animation;
import net.minecraft.entity.player.EntityPlayer;

public class AnimationType<A extends Animation> {

    public static final AnimationType<AnimatorAnimation> ANIMATOR_TYPE = create(AnimatorAnimation.class, (processor, type, player) -> processor.play(type, new AnimatorAnimation(40)));
    public static final AnimationType<TickableAnimation> RECOIL_ANIMATION_TYPE = create(TickableAnimation.class, AnimationDispatcher::dispatchRecoilAnimationDefault);
    public static final AnimationType<AimAnimation> AIM_ANIMATION_TYPE = create(AimAnimation.class, AnimationDispatcher::dispatchAimAnimationDefault);

    final Class<A> animationClass;
    final Dispatcher<A> dispatcher;

    AnimationType(Class<A> animationClass, Dispatcher<A> dispatcher) {
        this.animationClass = animationClass;
        this.dispatcher = dispatcher;
    }

    public Dispatcher<A> getDispatcher() {
        return dispatcher;
    }

    public Class<A> getAnimationClass() {
        return animationClass;
    }

    public static <A extends Animation> AnimationType<A> create(Class<A> animationClass, Dispatcher<A> dispatcher) {
        return new AnimationType<>(animationClass, dispatcher);
    }

    public interface Dispatcher<A extends Animation> {
        void dispatch(AnimationProcessor processor, AnimationType<A> type, EntityPlayer player);
    }
}

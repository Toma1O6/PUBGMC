package dev.toma.pubgmc.client.animation;

import dev.toma.pubgmc.client.animation.impl.*;
import dev.toma.pubgmc.client.animation.interfaces.Animation;
import net.minecraft.entity.player.EntityPlayer;

public class AnimationType<A extends Animation> {

    public static final AnimationType<AnimatorAnimation> ANIMATOR_TYPE = create((processor, type, player) -> processor.play(type, new AnimatorAnimation(40)));
    public static final AnimationType<TickableAnimation> RECOIL_ANIMATION_TYPE = create(AnimationDispatcher::dispatchRecoilAnimationDefault);
    public static final AnimationType<AimAnimation> AIM_ANIMATION_TYPE = create(AnimationDispatcher::dispatchAimAnimationDefault);
    public static final AnimationType<MultiFrameAnimation> RELOAD_ANIMATION_TYPE = create(AnimationDispatcher::dispatchReloadAnimationDefault);
    public static final AnimationType<MultiFrameAnimation> SHOOT_ANIMATION_TYPE = create(AnimationDispatcher::dispatchShootAnimationDefault);
    public static final AnimationType<EquipAnimation> EQUIP_ANIMATION_TYPE = create(AnimationDispatcher::dispatchEquipAnimation);
    public static final AnimationType<HeldAnimation> HELD_ANIMATION_TYPE = create(AnimationDispatcher::dispatchHeldAnimation);
    private static final AnimationType<?>[] TYPES = new AnimationType[7];
    static int id;
    final Dispatcher<A> dispatcher;
    final int index = id++;

    AnimationType(Dispatcher<A> dispatcher) {
        this.dispatcher = dispatcher;
        TYPES[index] = this;
    }

    private static <A extends Animation> AnimationType<A> create(Dispatcher<A> dispatcher) {
        return new AnimationType<>(dispatcher);
    }

    @SuppressWarnings("unchecked")
    public static <A extends Animation> AnimationType<A> getFromID(int id) {
        return (AnimationType<A>) TYPES[id];
    }

    public Dispatcher<A> getDispatcher() {
        return dispatcher;
    }

    public int getIndex() {
        return index;
    }

    protected interface Dispatcher<A extends Animation> {
        void dispatch(AnimationProcessor processor, AnimationType<A> type, EntityPlayer player);
    }
}

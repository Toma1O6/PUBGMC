package dev.toma.pubgmc.client.animation;

import dev.toma.pubgmc.client.animation.interfaces.Animation;
import dev.toma.pubgmc.client.animation.interfaces.KeyFrame;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.Vec3d;

import java.util.*;

public class AnimationProcessor {

    private static final AnimationProcessor INSTANCE = new AnimationProcessor();
    private final List<ScheduledAnimation<?>> scheduledAnimations = new ArrayList<>();
    private final Map<AnimationType<?>, Animation> animations = new HashMap<>();

    AnimationProcessor() {
        play(AnimationType.HELD_ANIMATION_TYPE);
    }

    public static void processKeyFrame(KeyFrame currentFrame, float pct) {
        // movement
        Vec3d staticMove = currentFrame.getPositionStart();
        Vec3d smoothMove = currentFrame.moveTarget();
        Vec3d staticRotate = currentFrame.getRotationStart();
        Vec3d smoothRotate = currentFrame.rotateTarget();
        boolean shouldMove = !staticMove.equals(Vec3d.ZERO) || !smoothMove.equals(Vec3d.ZERO);
        boolean shouldRotate = !staticRotate.equals(Vec3d.ZERO) || !smoothRotate.equals(Vec3d.ZERO);
        if (!shouldMove && !shouldRotate)
            return;
        GlStateManager.translate(staticMove.x + smoothMove.x * pct, staticMove.y + smoothMove.y * pct, staticMove.z + smoothMove.z * pct);
        // rotation
        if (shouldRotate) {
            if (staticRotate.x != 0.0 || smoothRotate.x != 0.0) {
                GlStateManager.rotate((float) (staticRotate.x + smoothRotate.x * pct), 1.0F, 0.0F, 0.0F);
            }
            if (staticRotate.y != 0.0 || smoothRotate.y != 0.0) {
                GlStateManager.rotate((float) (staticRotate.y + smoothRotate.y * pct), 0.0F, 1.0F, 0.0F);
            }
            if (staticRotate.z != 0.0 || smoothRotate.z != 0.0) {
                GlStateManager.rotate((float) (staticRotate.z + smoothRotate.z * pct), 0.0F, 0.0F, 1.0F);
            }
        }
    }

    public static AnimationProcessor instance() {
        return INSTANCE;
    }

    public <A extends Animation> void play(AnimationType<A> type) {
        EntityPlayer player = Minecraft.getMinecraft().player;
        AnimationType.Dispatcher<A> dispatcher = type.getDispatcher();
        dispatcher.dispatch(this, type, player);
    }

    public <A extends Animation> void play(AnimationType<A> type, A animation) {
        if (animation != null)
            animations.put(type, animation);
    }

    public <A extends Animation> void schedule(AnimationType<A> type, A animation, int ticks) {
        scheduledAnimations.add(new ScheduledAnimation<>(type, animation, ticks));
    }

    public void stop(AnimationType<?> type) {
        animations.remove(type);
    }

    @SuppressWarnings("unchecked")
    public <A extends Animation> Optional<A> getAnimation(AnimationType<A> type) {
        return (Optional<A>) Optional.ofNullable(animations.get(type));
    }

    public boolean isPlaying(AnimationType<?> type) {
        return animations.containsKey(type);
    }

    public <A extends Animation> void processTick() {
        Iterator<ScheduledAnimation<?>> iterator = scheduledAnimations.iterator();
        while (iterator.hasNext()) {
            ScheduledAnimation<A> scheduledAnimation = (ScheduledAnimation<A>) iterator.next();
            scheduledAnimation.tick();
            if (scheduledAnimation.ticksRemaining == 0) {
                play(scheduledAnimation.type, scheduledAnimation.animation);
                iterator.remove();
            }
        }
        Iterator<Animation> itr = animations.values().iterator();
        while (itr.hasNext()) {
            Animation animation = itr.next();
            if (animation.shouldRemove())
                itr.remove();
            animation.tick();
        }
    }

    public void processFrame(float partialTicks) {
        animations.values().forEach(anim -> anim.renderTick(partialTicks));
    }

    public void process(AnimationElement element) {
        animations.values().forEach(anim -> anim.animateElement(element));
    }

    public boolean isItemRenderBlocked() {
        for (Animation animation : animations.values()) {
            if (animation.blocksItemRender()) {
                return true;
            }
        }
        return false;
    }

    private static class ScheduledAnimation<A extends Animation> {

        final AnimationType<A> type;
        final A animation;
        int ticksRemaining;

        private ScheduledAnimation(AnimationType<A> type, A animation, int ticks) {
            this.type = type;
            this.animation = animation;
            this.ticksRemaining = ticks;
        }

        private void tick() {
            --ticksRemaining;
        }
    }
}

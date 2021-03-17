package dev.toma.pubgmc.client.animation;

import dev.toma.pubgmc.client.animation.interfaces.Animation;
import dev.toma.pubgmc.client.animation.interfaces.KeyFrame;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.Vec3d;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Optional;

public class AnimationProcessor {

    private static final AnimationProcessor INSTANCE = new AnimationProcessor();
    private final Map<AnimationType<?>, Animation> animations = new HashMap<>();

    AnimationProcessor() {}

    public void insert(AnimationType<?> type) {
        EntityPlayer player = Minecraft.getMinecraft().player;
        Animation animation = type.createAnimation(player);
        if(animation != null)
            animations.put(type, animation);
    }

    public void stop(AnimationType<?> type) {
        animations.remove(type);
    }

    @SuppressWarnings("unchecked")
    public <A extends Animation> Optional<A> getAnimation(AnimationType<A> type) {
        return (Optional<A>) Optional.ofNullable(animations.get(type));
    }

    public void processTick() {
        Iterator<Animation> itr = animations.values().iterator();
        while (itr.hasNext()) {
            Animation animation = itr.next();
            if(animation.shouldRemove())
                itr.remove();
            animation.tick();
        }
    }

    public void processFrame(float partialTicks) {
        animations.values().forEach(anim -> anim.renderTick(partialTicks));
    }

    public void process(String element) {
        animations.values().forEach(anim -> anim.animateElement(element));
    }

    public static void processKeyFrame(KeyFrame frame, float pct) {
        processKeyFrame(KeyFrame.EMPTY_FRAME, frame, pct);
    }

    public static void processKeyFrame(KeyFrame prevFrame, KeyFrame currentFrame, float pct) {
        // movement
        Vec3d staticMove = prevFrame.moveTarget();
        Vec3d smoothMove = currentFrame.moveTarget();
        GlStateManager.translate(staticMove.x + smoothMove.x * pct, staticMove.y + smoothMove.y * pct, staticMove.z + smoothMove.z * pct);
        // rotation
        Vec3d staticRotate = prevFrame.rotateTarget();
        Vec3d smoothRotate = currentFrame.rotateTarget();
        if(!staticRotate.equals(Vec3d.ZERO) || !smoothRotate.equals(Vec3d.ZERO)) {
            if(staticRotate.x != 0.0 || smoothRotate.x != 0) {
                GlStateManager.rotate((float) (staticRotate.x + smoothRotate.x * pct), 1.0F, 0.0F, 0.0F);
            }
            if(staticRotate.y != 0.0 || smoothRotate.y != 0) {
                GlStateManager.rotate((float) (staticRotate.x + smoothRotate.x * pct), 1.0F, 0.0F, 0.0F);
            }
            if(staticRotate.z != 0.0 || smoothRotate.z != 0) {
                GlStateManager.rotate((float) (staticRotate.x + smoothRotate.x * pct), 1.0F, 0.0F, 0.0F);
            }
        }
    }

    public static AnimationProcessor instance() {
        return INSTANCE;
    }
}

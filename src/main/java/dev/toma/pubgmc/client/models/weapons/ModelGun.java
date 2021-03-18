package dev.toma.pubgmc.client.models.weapons;

import dev.toma.pubgmc.animation_old.AimingAnimation;
import dev.toma.pubgmc.animation_old.Animation;
import dev.toma.pubgmc.animation_old.HeldAnimation;
import dev.toma.pubgmc.animation_old.HeldAnimation.HeldStyle;
import dev.toma.pubgmc.animation_old.ReloadAnimation;
import dev.toma.pubgmc.animation_old.ReloadAnimation.ReloadStyle;
import dev.toma.pubgmc.client.animation.interfaces.HandAnimate;
import dev.toma.pubgmc.client.renderer.item.IRenderConfig;
import dev.toma.pubgmc.common.items.attachment.AttachmentType;
import dev.toma.pubgmc.common.items.attachment.ItemAttachment;
import dev.toma.pubgmc.common.items.attachment.ItemMagazine;
import dev.toma.pubgmc.common.items.attachment.ScopeData;
import dev.toma.pubgmc.common.items.guns.GunBase;
import dev.toma.pubgmc.init.PMCItems;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHandSide;

import javax.vecmath.Vector3f;
import java.util.Objects;
import java.util.function.Predicate;

public abstract class ModelGun extends ModelBase {

    public static final Vector3f[] DEFAULT_PART_ANIMATION = {
            new Vector3f(0f, 15f, 0f),
            new Vector3f(15f, 30f, 15f),
            new Vector3f(15f, 30f, 50f),
            new Vector3f(15f, 20f, 15f),
            new Vector3f(0f, 0f, 0f)
    };
    public AimingAnimation aimAnimation;
    public HeldAnimation heldAnimation;
    public ReloadAnimation reloadAnimation;
    public Animation[] animations;
    private float[] aimStates = new float[]{0f, 0f, 0f};

    public ModelGun() {
        aimAnimation = new AimingAnimation(0f, 0f, 0f);
        initAimingAnimationStates(0f, 0f, 0f);
        heldAnimation = new HeldAnimation(HeldStyle.NORMAL);
        reloadAnimation = new ReloadAnimation(null, ReloadStyle.MAGAZINE).initMovement(DEFAULT_PART_ANIMATION);
        animations = new Animation[]{aimAnimation, heldAnimation, reloadAnimation};
    }

    public abstract void render(ItemStack stack, ItemCameraTransforms.TransformType transformType);

    public void renderArm(EnumHandSide side) {
        HandAnimate.renderHand(side, IRenderConfig.empty());
    }

    @Deprecated
    public abstract void initAnimations();

    /**
     * Manipulates with Y value of aim animation to react to different scopes
     * <p><b>Call inside the render(ItemStack) function!</b></p>
     *
     * @param stack - item
     */
    @Deprecated
    public void preRender(ItemStack stack) {
        boolean redDot = hasRedDot(stack);
        boolean hologr = hasHoloSight(stack);
        if (!redDot && !hologr && aimAnimation.getFinalState().y != aimStates[0]) {
            initAimAnimation(aimAnimation.getFinalState().x, aimStates[0], aimAnimation.getFinalState().z);
        } else if (redDot && aimAnimation.getFinalState().y != aimStates[1]) {
            initAimAnimation(aimAnimation.getFinalState().x, aimStates[1], aimAnimation.getFinalState().z);
        } else if (hologr && aimAnimation.getFinalState().y != aimStates[2])
            initAimAnimation(aimAnimation.getFinalState().x, aimStates[2], aimAnimation.getFinalState().z);
    }

    public boolean hasRedDot(ItemStack stack) {
        return has(stack, AttachmentType.SCOPE, scope -> scope == PMCItems.RED_DOT);
    }

    public boolean hasHoloSight(ItemStack stack) {
        return has(stack, AttachmentType.SCOPE, scope -> scope == PMCItems.HOLOGRAPHIC);
    }

    public boolean enableADS(ItemStack stack) {
        if(stack.getItem() instanceof GunBase) {
            GunBase gunBase = (GunBase) stack.getItem();
            ScopeData data = gunBase.getScopeData(stack);
            return data == null || data.isBuiltInRenderer();
        }
        return false;
    }

    public boolean hasScopeAtachment(ItemStack stack) {
        return has(stack, AttachmentType.SCOPE, Objects::nonNull);
    }

    public boolean hasExtendedMagazine(ItemStack stack) {
        return has(stack, AttachmentType.MAGAZINE, ItemMagazine::isExtended);
    }

    public <I extends ItemAttachment> boolean has(ItemStack stack, AttachmentType<I> type, Predicate<I> predicate) {
        if(stack.getItem() instanceof GunBase) {
            GunBase gunBase = (GunBase) stack.getItem();
            I i = gunBase.getAttachment(type, stack);
            return i != null && predicate.test(i);
        }
        return false;
    }

    @Deprecated
    public void processAnimations(boolean aim, boolean reload) {
        aimAnimation.processAnimation(aim);
        heldAnimation.processAnimation();
        reloadAnimation.processAnimation(reload);
    }

    @Deprecated
    public AimingAnimation getAimAnimation() {
        return aimAnimation;
    }

    @Deprecated
    public HeldAnimation getHeldAnimation() {
        return heldAnimation;
    }

    @Deprecated
    public ReloadAnimation getReloadAnimation() {
        return reloadAnimation;
    }

    /**
     * Initialize aiming animation for model
     * Default animation speed is 3.0F
     * <b>Not a single parameter can be == 0!</b>
     *
     * @param x - final x location of the model
     * @param y - final y location of the model (this is changed inside the initAimAnimationStates method for more options)
     * @param z - final z location of the model
     */
    @Deprecated
    public void initAimAnimation(float x, float y, float z) {
        aimAnimation = new AimingAnimation(x, y, z);
    }

    /**
     * Initialize aiming animation for model with specific speed level
     *
     * @param x     - final x location of the model
     * @param y     - final y location of the model (this changed inside the initAimAnimationStates method for more options)
     * @param z     - final z location of the model
     * @param speed - speed of the animation; Default: 3.0F
     */
    @Deprecated
    public void initAimAnimation(float x, float y, float z, float speed) {
        aimAnimation = new AimingAnimation(x, y, z, speed);
    }

    /**
     * Initialize the final y-height of aiming animation based on scope attachment
     * f[0] - ironsight, f[1] - red dot, f[2] - holographic
     */
    @Deprecated
    public void initAimingAnimationStates(float... f) {
        if (f == null) {
            aimStates[0] = aimAnimation.getFinalState().y;
            aimStates[1] = aimStates[0];
            aimStates[2] = aimStates[0];
        } else if (f.length == 1) {
            aimStates[0] = f[0];
            aimStates[1] = aimStates[0];
            aimStates[2] = aimStates[0];
        } else {
            aimStates[0] = f[0];
            aimStates[1] = f[1];
            aimStates[2] = f[2];
        }
    }
}

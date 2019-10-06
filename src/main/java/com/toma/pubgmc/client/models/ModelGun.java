package com.toma.pubgmc.client.models;

import com.toma.pubgmc.animation.AimingAnimation;
import com.toma.pubgmc.animation.Animation;
import com.toma.pubgmc.animation.HeldAnimation;
import com.toma.pubgmc.animation.HeldAnimation.HeldStyle;
import com.toma.pubgmc.animation.ReloadAnimation;
import com.toma.pubgmc.animation.ReloadAnimation.ReloadStyle;
import com.toma.pubgmc.client.models.atachments.*;
import com.toma.pubgmc.client.util.ModelTransformationHelper;
import com.toma.pubgmc.init.PMCRegistry.PMCItems;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.item.ItemStack;

import javax.vecmath.Vector3f;

public abstract class ModelGun extends ModelBase {
    public static final Vector3f[] DEFAULT_PART_ANIMATION = {
            new Vector3f(0f, 15f, 0f),
            new Vector3f(15f, 30f, 15f),
            new Vector3f(15f, 30f, 50f),
            new Vector3f(15f, 20f, 15f),
            new Vector3f(0f, 0f, 0f)
    };

    private static final AttachmentSuppressorPistol pistolSupp = new AttachmentSuppressorPistol();
    private static final AttachmentSuppressorSMG smgSupp = new AttachmentSuppressorSMG();
    private static final AttachmentSuppressorAR arSupp = new AttachmentSuppressorAR();
    private static final AttachmentSuppressorSR srSupp = new AttachmentSuppressorSR();
    private static final AttachmentGripVertical grip_vertical = new AttachmentGripVertical();
    private static final AttachmentGripAngled grip_angled = new AttachmentGripAngled();
    private static final AttachmentScopeRDS red_dot = new AttachmentScopeRDS();
    private static final AttachmentScopeHolo holo = new AttachmentScopeHolo();
    private static final AttachmentScope2x scope2x = new AttachmentScope2x();
    private static final AttachmentScope4x scope4x = new AttachmentScope4x();
    private static final AttachmentScope8x scope8x = new AttachmentScope8x();
    private static final AttachmentScope15x scope15x = new AttachmentScope15x();

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

    public static void rotateModelForADSRendering() {
        GlStateManager.rotate(0.2f, 0, 1f, 0);
        GlStateManager.rotate(0.5f, 1f, 0, 0);
    }

    public abstract void render(ItemStack stack);

    public abstract String textureName();

    public abstract void initAnimations();

    /**
     * Manipulates with Y value of aim animation to react to different scopes
     * <p><b>Call inside the render(ItemStack) function!</b></p>
     *
     * @param stack - item
     */
    public void preRender(ItemStack stack) {
        if ((!hasScopeAtachment(stack) || this.getScopeLevel(stack) > 2) && aimAnimation.getFinalState().y != aimStates[0]) {
            initAimAnimation(aimAnimation.getFinalState().x, aimStates[0], aimAnimation.getFinalState().z);
        } else if (hasRedDot(stack) && aimAnimation.getFinalState().y != aimStates[1]) {
            initAimAnimation(aimAnimation.getFinalState().x, aimStates[1], aimAnimation.getFinalState().z);
        } else if (hasHoloSight(stack) && aimAnimation.getFinalState().y != aimStates[2])
            initAimAnimation(aimAnimation.getFinalState().x, aimStates[2], aimAnimation.getFinalState().z);
    }

    public int getScopeLevel(ItemStack stack) {
        return stack.hasTagCompound() ? stack.getTagCompound().getInteger("scope") : 0;
    }

    public boolean hasRedDot(ItemStack stack) {
        return stack.hasTagCompound() && stack.getTagCompound().getInteger("scope") == 1;
    }

    public boolean hasHoloSight(ItemStack stack) {
        return stack.hasTagCompound() && stack.getTagCompound().getInteger("scope") == 2;
    }

    public boolean has2X(ItemStack stack) {
        return stack.hasTagCompound() && stack.getTagCompound().getInteger("scope") == 3;
    }

    public boolean has4X(ItemStack stack) {
        return stack.hasTagCompound() && stack.getTagCompound().getInteger("scope") == 4;
    }

    public boolean has8X(ItemStack stack) {
        return stack.hasTagCompound() && stack.getTagCompound().getInteger("scope") == 5;
    }

    public boolean has15X(ItemStack stack) {
        return stack.hasTagCompound() && stack.getTagCompound().getInteger("scope") == 6;
    }

    public boolean hasSilencer(ItemStack stack) {
        return stack.hasTagCompound() && stack.getTagCompound().getInteger("barrel") == 1;
    }

    public boolean hasVerticalGrip(ItemStack stack) {
        return stack.hasTagCompound() && stack.getTagCompound().getInteger("grip") == 1;
    }

    public boolean hasAngledGrip(ItemStack stack) {
        return stack.hasTagCompound() && stack.getTagCompound().getInteger("grip") == 2;
    }

    public boolean enableADS(ItemStack stack) {
        return stack.hasTagCompound() && stack.getTagCompound().getInteger("scope") < 3 && stack.getItem() != PMCItems.VSS;
    }

    public boolean hasScopeAtachment(ItemStack stack) {
        return stack.hasTagCompound() && stack.getTagCompound().getInteger("scope") > 0;
    }

    public boolean hasExtendedMagazine(ItemStack stack) {
        return stack.hasTagCompound() && stack.getTagCompound().getInteger("magazine") > 1;
    }

    public void renderRedDot(double x, double y, double z, float scale, ItemStack stack) {
        if (hasRedDot(stack)) {
            GlStateManager.pushMatrix();
            ModelTransformationHelper.defaultPistolRedDotTransform();
            GlStateManager.scale(scale, scale, scale);
            GlStateManager.translate(x, y, z);
            red_dot.render();
            GlStateManager.popMatrix();
        }
    }

    public void renderHolo(double x, double y, double z, float scale, ItemStack stack) {
        if (hasHoloSight(stack)) {
            GlStateManager.pushMatrix();
            ModelTransformationHelper.defaultHoloTransform();
            GlStateManager.scale(scale, scale, scale);
            GlStateManager.translate(x, y, z);
            holo.render();
            GlStateManager.popMatrix();
        }
    }

    public void renderScope2X(double x, double y, double z, float scale, ItemStack stack) {
        if (has2X(stack)) {
            GlStateManager.pushMatrix();
            ModelTransformationHelper.default2XTransform();
            GlStateManager.scale(scale, scale, scale);
            GlStateManager.translate(x, y, z);
            scope2x.render();
            GlStateManager.popMatrix();
        }
    }

    public void renderScope4X(double x, double y, double z, float scale, ItemStack stack) {
        if (has4X(stack)) {
            GlStateManager.pushMatrix();
            ModelTransformationHelper.default4XTransform();
            GlStateManager.scale(scale, scale, scale);
            GlStateManager.translate(x, y, z);
            scope4x.render();
            GlStateManager.popMatrix();
        }
    }

    public void renderScope8X(double x, double y, double z, float scale, ItemStack stack) {
        if (has8X(stack)) {
            GlStateManager.pushMatrix();
            ModelTransformationHelper.default8XTransform();
            GlStateManager.scale(scale, scale, scale);
            GlStateManager.translate(x, y, z);
            scope8x.render();
            GlStateManager.popMatrix();
        }
    }

    public void renderScope15X(double x, double y, double z, float scale, ItemStack stack) {
        if (has15X(stack)) {
            GlStateManager.pushMatrix();
            ModelTransformationHelper.default15XTransform();
            GlStateManager.scale(scale, scale, scale);
            GlStateManager.translate(x, y, z);
            scope15x.render();
            GlStateManager.popMatrix();
        }
    }

    public void renderPistolSilencer(double x, double y, double z, float scale, ItemStack stack) {
        if (hasSilencer(stack)) {
            GlStateManager.pushMatrix();
            ModelTransformationHelper.defaultPistolSilencerTransform();
            GlStateManager.scale(scale, scale, scale);
            GlStateManager.translate(x, y, z);
            pistolSupp.render();
            GlStateManager.popMatrix();
        }
    }

    public void renderSMGSilencer(double x, double y, double z, float scale, ItemStack stack) {
        if (hasSilencer(stack)) {
            GlStateManager.pushMatrix();
            ModelTransformationHelper.silencerSMGTransform();
            GlStateManager.scale(scale, scale, scale);
            GlStateManager.translate(x, y, z);
            smgSupp.render();
            GlStateManager.popMatrix();
        }
    }

    public void renderARSilencer(double x, double y, double z, float scale, ItemStack stack) {
        if (hasSilencer(stack)) {
            GlStateManager.pushMatrix();
            ModelTransformationHelper.silencerARTransform();
            GlStateManager.scale(scale, scale, scale);
            GlStateManager.translate(x, y, z);
            arSupp.render();
            GlStateManager.popMatrix();
        }
    }

    public void renderSniperSilencer(double x, double y, double z, float scale, ItemStack stack) {
        if (hasSilencer(stack)) {
            GlStateManager.pushMatrix();
            ModelTransformationHelper.silencerSRTransform();
            GlStateManager.scale(scale, scale, scale);
            GlStateManager.translate(x, y, z);
            srSupp.render();
            GlStateManager.popMatrix();
        }
    }

    public void renderVerticalGrip(double x, double y, double z, float scale, ItemStack stack) {
        if (hasVerticalGrip(stack)) {
            GlStateManager.pushMatrix();
            ModelTransformationHelper.verticalGripTransform();
            GlStateManager.scale(scale, scale, scale);
            GlStateManager.translate(x, y, z);
            grip_vertical.render();
            GlStateManager.popMatrix();
        }
    }

    public void renderAngledGrip(double x, double y, double z, float scale, ItemStack stack) {
        if (hasAngledGrip(stack)) {
            GlStateManager.pushMatrix();
            ModelTransformationHelper.angledGripTransform();
            GlStateManager.scale(scale, scale, scale);
            GlStateManager.translate(x, y, z);
            grip_angled.render();
            GlStateManager.popMatrix();
        }
    }

    public void processAnimations(boolean aim, boolean reload) {
        aimAnimation.processAnimation(aim);
        heldAnimation.processAnimation();
        reloadAnimation.processAnimation(reload);
    }

    public AimingAnimation getAimAnimation() {
        return aimAnimation;
    }

    public HeldAnimation getHeldAnimation() {
        return heldAnimation;
    }

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
    public void initAimAnimation(float x, float y, float z, float speed) {
        aimAnimation = new AimingAnimation(x, y, z, speed);
    }

    /**
     * Initialize the final y-height of aiming animation based on scope attachment
     * f[0] - ironsight, f[1] - red dot, f[2] - holographic
     */
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

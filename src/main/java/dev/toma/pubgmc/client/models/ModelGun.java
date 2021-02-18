package dev.toma.pubgmc.client.models;

import dev.toma.pubgmc.animation.AimingAnimation;
import dev.toma.pubgmc.animation.Animation;
import dev.toma.pubgmc.animation.HeldAnimation;
import dev.toma.pubgmc.animation.HeldAnimation.HeldStyle;
import dev.toma.pubgmc.animation.ReloadAnimation;
import dev.toma.pubgmc.animation.ReloadAnimation.ReloadStyle;
import dev.toma.pubgmc.client.util.ModelTransformationHelper;
import dev.toma.pubgmc.init.PMCRegistry.PMCItems;
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
            AttachmentModels.RED_DOT.render();
            GlStateManager.popMatrix();
        }
    }

    public void renderHolo(double x, double y, double z, float scale, ItemStack stack) {
        if (hasHoloSight(stack)) {
            GlStateManager.pushMatrix();
            ModelTransformationHelper.defaultHoloTransform();
            GlStateManager.scale(scale, scale, scale);
            GlStateManager.translate(x, y, z);
            AttachmentModels.HOLOGRAPHIC.render();
            GlStateManager.popMatrix();
        }
    }

    public void renderScope2X(double x, double y, double z, float scale, ItemStack stack) {
        if (has2X(stack)) {
            GlStateManager.pushMatrix();
            ModelTransformationHelper.default2XTransform();
            GlStateManager.scale(scale, scale, scale);
            GlStateManager.translate(x, y, z);
            AttachmentModels.SCOPE_2X.render();
            GlStateManager.popMatrix();
        }
    }

    public void renderScope4X(double x, double y, double z, float scale, ItemStack stack) {
        if (has4X(stack)) {
            GlStateManager.pushMatrix();
            ModelTransformationHelper.default4XTransform();
            GlStateManager.scale(scale, scale, scale);
            GlStateManager.translate(x, y, z);
            AttachmentModels.SCOPE_4X.render();
            GlStateManager.popMatrix();
        }
    }

    public void renderScope8X(double x, double y, double z, float scale, ItemStack stack) {
        if (has8X(stack)) {
            GlStateManager.pushMatrix();
            ModelTransformationHelper.default8XTransform();
            GlStateManager.scale(scale, scale, scale);
            GlStateManager.translate(x, y, z);
            AttachmentModels.SCOPE_8X.render();
            GlStateManager.popMatrix();
        }
    }

    public void renderScope15X(double x, double y, double z, float scale, ItemStack stack) {
        if (has15X(stack)) {
            GlStateManager.pushMatrix();
            ModelTransformationHelper.default15XTransform();
            GlStateManager.scale(scale, scale, scale);
            GlStateManager.translate(x, y, z);
            AttachmentModels.SCOPE_15X.render();
            GlStateManager.popMatrix();
        }
    }

    public void renderPistolSilencer(double x, double y, double z, float scale, ItemStack stack) {
        if (hasSilencer(stack)) {
            GlStateManager.pushMatrix();
            ModelTransformationHelper.defaultPistolSilencerTransform();
            GlStateManager.scale(scale, scale, scale);
            GlStateManager.translate(x, y, z);
            AttachmentModels.SUPPRESSOR_PISTOL.render();
            GlStateManager.popMatrix();
        }
    }

    public void renderSMGSilencer(double x, double y, double z, float scale, ItemStack stack) {
        if (hasSilencer(stack)) {
            GlStateManager.pushMatrix();
            ModelTransformationHelper.silencerSMGTransform();
            GlStateManager.scale(scale, scale, scale);
            GlStateManager.translate(x, y, z);
            AttachmentModels.SUPPRESSOR_SMG.render();
            GlStateManager.popMatrix();
        }
    }

    public void renderARSilencer(double x, double y, double z, float scale, ItemStack stack) {
        if (hasSilencer(stack)) {
            GlStateManager.pushMatrix();
            ModelTransformationHelper.silencerARTransform();
            GlStateManager.scale(scale, scale, scale);
            GlStateManager.translate(x, y, z);
            AttachmentModels.SUPPRESSOR_AR.render();
            GlStateManager.popMatrix();
        }
    }

    public void renderSniperSilencer(double x, double y, double z, float scale, ItemStack stack) {
        if (hasSilencer(stack)) {
            GlStateManager.pushMatrix();
            ModelTransformationHelper.silencerSRTransform();
            GlStateManager.scale(scale, scale, scale);
            GlStateManager.translate(x, y, z);
            AttachmentModels.SUPPRESSOR_SR.render();
            GlStateManager.popMatrix();
        }
    }

    public void renderVerticalGrip(double x, double y, double z, float scale, ItemStack stack) {
        if (hasVerticalGrip(stack)) {
            GlStateManager.pushMatrix();
            ModelTransformationHelper.verticalGripTransform();
            GlStateManager.scale(scale, scale, scale);
            GlStateManager.translate(x, y, z);
            AttachmentModels.GRIP_VERTICAL.render();
            GlStateManager.popMatrix();
        }
    }

    public void renderAngledGrip(double x, double y, double z, float scale, ItemStack stack) {
        if (hasAngledGrip(stack)) {
            GlStateManager.pushMatrix();
            ModelTransformationHelper.angledGripTransform();
            GlStateManager.scale(scale, scale, scale);
            GlStateManager.translate(x, y, z);
            AttachmentModels.GRIP_ANGLED.render();
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

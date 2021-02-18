package dev.toma.pubgmc.client.models.weapons;

import dev.toma.pubgmc.animation.ReloadAnimation;
import dev.toma.pubgmc.client.models.ModelGun;
import dev.toma.pubgmc.client.util.ModelTransformationHelper;
import dev.toma.pubgmc.common.capability.IPlayerData;
import dev.toma.pubgmc.common.capability.IPlayerData.PlayerDataProvider;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.item.ItemStack;

public class ModelM16A4 extends ModelGun {

    private final ModelRenderer bone;
    private final ModelRenderer bone10;
    private final ModelRenderer bone11;
    private final ModelRenderer bone18;
    private final ModelRenderer bone19;
    private final ModelRenderer bone20;
    private final ModelRenderer bone15;
    private final ModelRenderer bone16;
    private final ModelRenderer bone17;
    private final ModelRenderer bone12;
    private final ModelRenderer bone4;
    private final ModelRenderer bone5;
    private final ModelRenderer bone8;
    private final ModelRenderer bone9;
    private final ModelRenderer ironsights;
    private final ModelRenderer bone24;
    private final ModelRenderer bone25;
    private final ModelRenderer bone26;
    private final ModelRenderer bone27;
    private final ModelRenderer bone28;
    private final ModelRenderer bone29;
    private final ModelRenderer bone30;
    private final ModelRenderer bone31;
    private final ModelRenderer bone23;
    private final ModelRenderer bone22;
    private final ModelRenderer bone21;
    private final ModelRenderer bone13;
    private final ModelRenderer bone14;
    private final ModelRenderer bone7;
    private final ModelRenderer bone6;
    private final ModelRenderer magazine;
    private final ModelRenderer bone2;
    private final ModelRenderer bone3;

    @Override
    public String textureName() {
        return "m16a4";
    }

    @Override
    public void initAnimations() {
        initAimAnimation(-0.56f, 0.195f, 0.215f);
        initAimingAnimationStates(0.195f, 0.18f, 0.145f);
        reloadAnimation = new ReloadAnimation(magazine, ReloadAnimation.ReloadStyle.MAGAZINE).initMovement(DEFAULT_PART_ANIMATION, 90);
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }

    @Override
    public void render(ItemStack stack) {
        EntityPlayerSP player = Minecraft.getMinecraft().player;

        if (player != null && player.hasCapability(PlayerDataProvider.PLAYER_DATA, null)) {
            IPlayerData data = player.getCapability(PlayerDataProvider.PLAYER_DATA, null);
            boolean aim = data.isAiming();

            GlStateManager.pushMatrix();
            {
                renderM16A4(aim, stack);
            }
            GlStateManager.popMatrix();
        }
    }

    private void renderM16A4(boolean aim, ItemStack stack) {
        GlStateManager.pushMatrix();
        GlStateManager.rotate(90, 0, 1, 0);
        ModelTransformationHelper.defaultARTransform();
        GlStateManager.translate(21.0, 1.975, -4.6000023);
        if (aim && enableADS(stack)) {
            rotateModelForADSRendering();
        }
        ironsights.offsetY = hasScopeAtachment(stack) ? 10 : 0;
        bone.render(1f);
        GlStateManager.popMatrix();
        renderARSilencer(0, -7.075, -33, 1.2f, stack);
        renderRedDot(-0.15, -2.775, -17, 1.3f, stack);
        renderHolo(-0.1, -4.25, -7, 1.3f, stack);
        renderScope2X(0, -2, -9, 1.2f, stack);
        renderScope4X(-0.15, -2, -15, 1.2f, stack);
    }

    public ModelM16A4() {
        super();
        textureWidth = 128;
        textureHeight = 128;

        bone = new ModelRenderer(this);
        bone.setRotationPoint(0.0F, 24.0F, 0.0F);
        bone.cubeList.add(new ModelBox(bone, 47, 12, -56.832F, -27.9414F, -0.5F, 1, 3, 1, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 32, 10, -57.6133F, -28.3314F, 1.3515F, 3, 2, 1, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 32, 10, -57.6133F, -28.3314F, -2.3515F, 3, 2, 1, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 76, 13, -10.5F, -18.0F, -5.0F, 6, 8, 10, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 73, 25, -4.5F, -12.0F, -5.0F, 15, 2, 10, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 64, 18, 10.5F, -18.0F, -5.0F, 22, 8, 10, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 64, 21, 48.5F, -16.7F, -4.0F, 21, 6, 8, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 64, 21, 60.5F, -11.4305F, -4.0F, 9, 10, 8, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 64, 21, 60.5F, -1.4305F, -3.0F, 9, 1, 6, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 64, 21, 32.5F, -16.7F, -4.0F, 16, 4, 8, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 64, 21, 32.5F, -17.7F, -3.0F, 23, 1, 6, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 64, 21, 55.5F, -17.7F, -3.0F, 14, 1, 6, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 34, 38, 34.5F, -20.0F, -4.0F, 2, 2, 8, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 34, 38, 17.5F, -20.0F, -2.0F, 17, 2, 4, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 69, 16, -4.5F, -18.0F, -5.0F, 15, 2, 10, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 75, 20, -4.5F, -16.0F, -5.0F, 15, 4, 1, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 0, 10, -4.5F, -16.0F, 3.2578F, 15, 4, 1, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 0, 10, -3.5F, -15.0F, 3.7578F, 2, 2, 1, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 32, 7, -18.6133F, -14.3008F, -3.0F, 8, 2, 6, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 32, 7, -26.6133F, -14.3008F, -3.0F, 8, 2, 6, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 32, 7, -34.6133F, -14.3008F, -3.0F, 8, 2, 6, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 32, 7, -42.6133F, -14.3008F, -3.0F, 8, 2, 6, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 32, 7, -50.6133F, -14.3008F, -3.0F, 8, 2, 6, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 64, 38, -40.6133F, -17.4414F, -1.0F, 30, 2, 2, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 32, 10, -53.6133F, -17.4414F, -1.0F, 13, 2, 2, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 32, 10, -66.6133F, -17.4414F, -1.0F, 13, 2, 2, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 32, 10, -56.6133F, -15.4414F, -1.0F, 5, 1, 2, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 32, 10, -70.6133F, -17.4414F, -1.0F, 4, 2, 2, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 32, 10, -53.6133F, -16.9414F, -1.5F, 13, 1, 3, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 32, 10, -66.6133F, -16.9414F, -1.5F, 13, 1, 3, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 32, 10, -57.6133F, -24.9414F, -1.5F, 1, 8, 3, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 32, 10, -56.6133F, -24.9414F, -1.5F, 2, 1, 3, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 32, 10, -56.6133F, -21.5781F, -1.5F, 3, 1, 3, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 32, 10, -58.2734F, -19.5781F, -0.5F, 6, 1, 1, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 32, 10, -70.6133F, -16.9414F, -1.5F, 4, 1, 3, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 32, 10, -53.6133F, -17.9414F, -0.5F, 13, 3, 1, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 32, 10, -66.6133F, -17.9414F, -0.5F, 13, 3, 1, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 32, 10, -70.6133F, -17.9414F, -0.5F, 4, 3, 1, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 32, 7, -18.6133F, -20.095F, -1.5F, 8, 2, 3, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 32, 7, -26.6133F, -20.095F, -1.5F, 8, 2, 3, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 32, 7, -34.6133F, -20.095F, -1.5F, 8, 2, 3, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 32, 7, -42.6133F, -20.095F, -1.5F, 8, 2, 3, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 32, 7, -50.6133F, -20.095F, -1.5F, 8, 2, 3, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 82, 24, -10.6289F, -16.0117F, -3.0F, 1, 5, 6, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 68, 30, -10.5F, -10.0F, -4.0F, 22, 6, 8, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 73, 28, -10.5F, -19.0F, -4.0F, 18, 1, 8, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 66, 30, 7.5F, -19.0F, -4.0F, 23, 1, 8, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 68, 31, 23.5F, -20.5234F, -2.0F, 7, 2, 4, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 1, 72, 13.5F, -21.5234F, -2.0F, 10, 3, 4, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 1, 72, -7.5F, -21.5234F, -2.0F, 11, 3, 4, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 1, 72, 3.5F, -21.5234F, -2.0F, 10, 3, 4, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 4, 69, 22.5F, -22.3711F, -1.5F, 1, 1, 3, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 4, 69, 20.5F, -22.3711F, -1.5F, 1, 1, 3, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 4, 69, 18.5F, -22.3711F, -1.5F, 1, 1, 3, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 4, 69, 16.5F, -22.3711F, -1.5F, 1, 1, 3, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 4, 69, 14.5F, -22.3711F, -1.5F, 1, 1, 3, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 4, 69, 12.5F, -22.3711F, -1.5F, 1, 1, 3, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 4, 69, 10.5F, -22.3711F, -1.5F, 1, 1, 3, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 4, 69, 8.5F, -22.3711F, -1.5F, 1, 1, 3, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 4, 69, 6.5F, -22.3711F, -1.5F, 1, 1, 3, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 4, 69, 4.5F, -22.3711F, -1.5F, 1, 1, 3, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 4, 69, 2.5F, -22.3711F, -1.5F, 1, 1, 3, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 4, 69, 0.5F, -22.3711F, -1.5F, 1, 1, 3, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 4, 69, -1.5F, -22.3711F, -1.5F, 1, 1, 3, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 4, 69, -3.5F, -22.3711F, -1.5F, 1, 1, 3, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 4, 69, -5.5F, -22.3711F, -1.5F, 1, 1, 3, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 4, 69, -7.5F, -22.3711F, -1.5F, 1, 1, 3, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 4, 69, 21.5F, -21.8711F, -0.634F, 1, 0, 3, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 4, 69, 19.5F, -21.8711F, -0.634F, 1, 0, 3, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 4, 69, 17.5F, -21.8711F, -0.634F, 1, 0, 3, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 4, 69, 15.5F, -21.8711F, -0.634F, 1, 0, 3, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 4, 69, 13.5F, -21.8711F, -0.634F, 1, 0, 3, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 4, 69, 11.5F, -21.8711F, -0.634F, 1, 0, 3, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 4, 69, 9.5F, -21.8711F, -0.634F, 1, 0, 3, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 4, 69, 7.5F, -21.8711F, -0.634F, 1, 0, 3, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 4, 69, 5.5F, -21.8711F, -0.634F, 1, 0, 3, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 4, 69, 3.5F, -21.8711F, -0.634F, 1, 0, 3, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 4, 69, 1.5F, -21.8711F, -0.634F, 1, 0, 3, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 4, 69, -0.5F, -21.8711F, -0.634F, 1, 0, 3, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 4, 69, -2.5F, -21.8711F, -0.634F, 1, 0, 3, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 4, 69, -4.5F, -21.8711F, -0.634F, 1, 0, 3, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 4, 69, -6.5F, -21.8711F, -0.634F, 1, 0, 3, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 4, 69, 21.5F, -21.8711F, -2.366F, 1, 0, 2, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 4, 69, 19.5F, -21.8711F, -2.366F, 1, 0, 2, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 4, 69, 17.5F, -21.8711F, -2.366F, 1, 0, 2, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 4, 69, 15.5F, -21.8711F, -2.366F, 1, 0, 2, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 4, 69, 13.5F, -21.8711F, -2.366F, 1, 0, 2, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 4, 69, 11.5F, -21.8711F, -2.366F, 1, 0, 2, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 4, 69, 9.5F, -21.8711F, -2.366F, 1, 0, 2, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 4, 69, 7.5F, -21.8711F, -2.366F, 1, 0, 2, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 4, 69, 5.5F, -21.8711F, -2.366F, 1, 0, 2, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 4, 69, 3.5F, -21.8711F, -2.366F, 1, 0, 2, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 4, 69, 1.5F, -21.8711F, -2.366F, 1, 0, 2, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 4, 69, -0.5F, -21.8711F, -2.366F, 1, 0, 2, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 4, 69, -2.5F, -21.8711F, -2.366F, 1, 0, 2, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 4, 69, -4.5F, -21.8711F, -2.366F, 1, 0, 2, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 4, 69, -6.5F, -21.8711F, -2.366F, 1, 0, 2, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 68, 31, -10.5F, -20.5234F, -2.0F, 3, 2, 4, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 82, 30, 11.5F, -10.0F, -4.0F, 11, 2, 8, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 82, 30, 11.0F, -8.0F, -2.0F, 1, 5, 4, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 82, 30, 15.9F, -2.6F, -2.0F, 6, 1, 4, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 82, 30, 22.5F, -10.0F, -4.0F, 8, 4, 8, 0.0F, false));

        bone10 = new ModelRenderer(this);
        bone10.setRotationPoint(-65.1133F, -22.6367F, -1.0F);
        setRotationAngle(bone10, 0.4363F, 0.0F, 0.0F);
        bone.addChild(bone10);
        bone10.cubeList.add(new ModelBox(bone10, 32, 10, 7.5F, -3.9197F, 0.3365F, 3, 2, 1, 0.0F, false));

        bone11 = new ModelRenderer(this);
        bone11.setRotationPoint(-65.1133F, -22.6367F, 1.0F);
        setRotationAngle(bone11, -0.4363F, 0.0F, 0.0F);
        bone.addChild(bone11);
        bone11.cubeList.add(new ModelBox(bone11, 32, 10, 7.5F, -3.9197F, -1.3365F, 3, 2, 1, 0.0F, false));

        bone18 = new ModelRenderer(this);
        bone18.setRotationPoint(40.5F, -5.7F, 0.5F);
        setRotationAngle(bone18, 0.0F, 0.0F, 0.3491F);
        bone.addChild(bone18);
        bone18.cubeList.add(new ModelBox(bone18, 64, 21, -10.0F, -7.0F, -4.5F, 16, 4, 8, 0.0F, false));
        bone18.cubeList.add(new ModelBox(bone18, 64, 21, 6.0F, -12.0F, -4.5F, 14, 9, 8, 0.0F, false));
        bone18.cubeList.add(new ModelBox(bone18, 64, 21, -10.0F, -3.0F, -3.5F, 31, 1, 6, 0.0F, false));
        bone18.cubeList.add(new ModelBox(bone18, 64, 21, -2.0F, -10.0F, -4.5F, 8, 3, 8, 0.0F, false));

        bone19 = new ModelRenderer(this);
        bone19.setRotationPoint(62.5F, -17.2F, -7.0F);
        setRotationAngle(bone19, 0.7854F, 0.0F, 0.0F);
        bone.addChild(bone19);
        bone19.cubeList.add(new ModelBox(bone19, 70, 26, -21.0F, 2.3464F, 2.0119F, 28, 1, 1, 0.0F, false));
        bone19.cubeList.add(new ModelBox(bone19, 70, 26, -30.0F, 2.3464F, 2.0119F, 9, 1, 1, 0.0F, false));

        bone20 = new ModelRenderer(this);
        bone20.setRotationPoint(62.5F, -17.2F, 7.0F);
        setRotationAngle(bone20, -0.7854F, 0.0F, 0.0F);
        bone.addChild(bone20);
        bone20.cubeList.add(new ModelBox(bone20, 70, 26, -21.0F, 2.3464F, -3.0119F, 28, 1, 1, 0.0F, false));
        bone20.cubeList.add(new ModelBox(bone20, 70, 26, -30.0F, 2.3464F, -3.0119F, 9, 1, 1, 0.0F, false));

        bone15 = new ModelRenderer(this);
        bone15.setRotationPoint(31.5F, -19.0F, 9.0F);
        setRotationAngle(bone15, 0.0F, -0.3491F, 0.0F);
        bone.addChild(bone15);
        bone15.cubeList.add(new ModelBox(bone15, 34, 38, 0.9884F, -1.0F, -6.4086F, 2, 2, 1, 0.0F, false));
        bone15.cubeList.add(new ModelBox(bone15, 34, 38, -1.1562F, -1.0F, -11.9923F, 1, 2, 1, 0.0F, false));

        bone16 = new ModelRenderer(this);
        bone16.setRotationPoint(31.5F, -19.0F, -9.0F);
        setRotationAngle(bone16, 0.0F, 0.3491F, 0.0F);
        bone.addChild(bone16);
        bone16.cubeList.add(new ModelBox(bone16, 34, 38, 0.9884F, -1.0F, 5.4086F, 2, 2, 1, 0.0F, false));
        bone16.cubeList.add(new ModelBox(bone16, 34, 38, -1.1562F, -1.0F, 10.9923F, 1, 2, 1, 0.0F, false));

        bone17 = new ModelRenderer(this);
        bone17.setRotationPoint(-0.5F, -14.0F, 4.2578F);
        setRotationAngle(bone17, 0.0F, 0.3491F, 0.0F);
        bone.addChild(bone17);
        bone17.cubeList.add(new ModelBox(bone17, 0, 10, -1.1107F, -1.0F, -0.8722F, 2, 2, 1, 0.0F, false));

        bone12 = new ModelRenderer(this);
        bone12.setRotationPoint(-61.1133F, -16.6367F, 0.0F);
        setRotationAngle(bone12, 0.0F, 0.0F, -0.5236F);
        bone.addChild(bone12);
        bone12.cubeList.add(new ModelBox(bone12, 32, 10, 8.4324F, -3.6667F, -1.5F, 1, 9, 3, 0.0F, false));

        bone4 = new ModelRenderer(this);
        bone4.setRotationPoint(-6.5F, 12.0F, 0.0F);
        setRotationAngle(bone4, 0.5236F, 0.0F, 0.0F);
        bone.addChild(bone4);
        bone4.cubeList.add(new ModelBox(bone4, 32, 7, -12.1133F, -25.5451F, 9.5523F, 8, 3, 2, 0.0F, false));
        bone4.cubeList.add(new ModelBox(bone4, 32, 7, -20.1133F, -25.5451F, 9.5523F, 8, 3, 2, 0.0F, false));
        bone4.cubeList.add(new ModelBox(bone4, 32, 7, -28.1133F, -25.5451F, 9.5523F, 8, 3, 2, 0.0F, false));
        bone4.cubeList.add(new ModelBox(bone4, 32, 7, -36.1133F, -25.5451F, 9.5523F, 8, 3, 2, 0.0F, false));
        bone4.cubeList.add(new ModelBox(bone4, 32, 7, -44.1133F, -25.5451F, 9.5523F, 8, 3, 2, 0.0F, false));
        bone4.cubeList.add(new ModelBox(bone4, 32, 7, -12.1133F, -27.0451F, 15.3465F, 8, 6, 2, 0.0F, false));
        bone4.cubeList.add(new ModelBox(bone4, 37, 7, -20.1133F, -27.0451F, 15.3465F, 8, 6, 2, 0.0F, false));
        bone4.cubeList.add(new ModelBox(bone4, 31, 13, -28.1133F, -27.0451F, 15.3465F, 8, 6, 2, 0.0F, false));
        bone4.cubeList.add(new ModelBox(bone4, 32, 5, -36.1133F, -27.0451F, 15.3465F, 8, 6, 2, 0.0F, false));
        bone4.cubeList.add(new ModelBox(bone4, 32, 5, -44.1133F, -27.0451F, 15.3465F, 8, 6, 2, 0.0F, false));

        bone5 = new ModelRenderer(this);
        bone5.setRotationPoint(-6.5F, 12.0F, 0.0F);
        setRotationAngle(bone5, -0.5236F, 0.0F, 0.0F);
        bone.addChild(bone5);
        bone5.cubeList.add(new ModelBox(bone5, 32, 7, -12.1133F, -25.5451F, -11.5523F, 8, 3, 2, 0.0F, false));
        bone5.cubeList.add(new ModelBox(bone5, 32, 7, -20.1133F, -25.5451F, -11.5523F, 8, 3, 2, 0.0F, false));
        bone5.cubeList.add(new ModelBox(bone5, 32, 7, -28.1133F, -25.5451F, -11.5523F, 8, 3, 2, 0.0F, false));
        bone5.cubeList.add(new ModelBox(bone5, 32, 7, -36.1133F, -25.5451F, -11.5523F, 8, 3, 2, 0.0F, false));
        bone5.cubeList.add(new ModelBox(bone5, 32, 7, -44.1133F, -25.5451F, -11.5523F, 8, 3, 2, 0.0F, false));
        bone5.cubeList.add(new ModelBox(bone5, 35, 12, -12.1133F, -27.0451F, -17.3465F, 8, 6, 2, 0.0F, false));
        bone5.cubeList.add(new ModelBox(bone5, 32, 14, -20.1133F, -27.0451F, -17.3465F, 8, 6, 2, 0.0F, false));
        bone5.cubeList.add(new ModelBox(bone5, 45, 15, -28.1133F, -27.0451F, -17.3465F, 8, 6, 2, 0.0F, false));
        bone5.cubeList.add(new ModelBox(bone5, 32, 3, -36.1133F, -27.0451F, -17.3465F, 8, 6, 2, 0.0F, false));
        bone5.cubeList.add(new ModelBox(bone5, 32, 3, -44.1133F, -27.0451F, -17.3465F, 8, 6, 2, 0.0F, false));

        bone8 = new ModelRenderer(this);
        bone8.setRotationPoint(-1.5F, -26.0F, 0.0F);
        setRotationAngle(bone8, -0.5236F, 0.0F, 0.0F);
        bone.addChild(bone8);
        bone8.cubeList.add(new ModelBox(bone8, 64, 27, -9.0F, 4.3981F, 6.3234F, 24, 1, 2, 0.0F, false));
        bone8.cubeList.add(new ModelBox(bone8, 64, 27, -9.0F, 4.0622F, 3.9641F, 24, 1, 3, 0.0F, false));
        bone8.cubeList.add(new ModelBox(bone8, 64, 27, 15.0F, 4.3981F, 6.3234F, 17, 1, 2, 0.0F, false));
        bone8.cubeList.add(new ModelBox(bone8, 64, 27, 15.0F, 4.0622F, 3.9641F, 17, 1, 3, 0.0F, false));
        bone8.cubeList.add(new ModelBox(bone8, 64, 27, -9.0F, 15.3661F, 3.6866F, 24, 1, 2, 0.0F, false));
        bone8.cubeList.add(new ModelBox(bone8, 64, 27, 15.0F, 15.3661F, 3.6866F, 13, 1, 2, 0.0F, false));

        bone9 = new ModelRenderer(this);
        bone9.setRotationPoint(-1.5F, -26.0F, 0.0F);
        setRotationAngle(bone9, 0.5236F, 0.0F, 0.0F);
        bone.addChild(bone9);
        bone9.cubeList.add(new ModelBox(bone9, 64, 27, -9.0F, 4.3981F, -8.3234F, 24, 1, 2, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 64, 27, -9.0F, 4.0622F, -6.9641F, 24, 1, 3, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 64, 27, 15.0F, 4.3981F, -8.3234F, 17, 1, 2, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 64, 27, 15.0F, 4.0622F, -6.9641F, 17, 1, 3, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 64, 27, -9.0F, 15.3661F, -5.6866F, 24, 1, 2, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 64, 27, 15.0F, 15.3661F, -5.6866F, 13, 1, 2, 0.0F, false));

        ironsights = new ModelRenderer(this);
        ironsights.setRotationPoint(-18.5F, 12.0F, 0.0F);
        bone.addChild(ironsights);
        ironsights.cubeList.add(new ModelBox(ironsights, 64, 0, 10.0F, -33.5234F, -3.0F, 26, 2, 6, 0.0F, false));
        ironsights.cubeList.add(new ModelBox(ironsights, 78, 0, 36.0F, -33.5234F, -3.0F, 10, 2, 6, 0.0F, false));
        ironsights.cubeList.add(new ModelBox(ironsights, 68, 31, 11.0F, -34.5234F, -2.0F, 31, 1, 4, 0.0F, false));
        ironsights.cubeList.add(new ModelBox(ironsights, 66, 0, 17.0F, -38.5234F, -3.0F, 25, 1, 6, 0.0F, false));
        ironsights.cubeList.add(new ModelBox(ironsights, 66, 0, 14.0F, -38.5234F, -3.0F, 3, 1, 6, 0.0F, false));
        ironsights.cubeList.add(new ModelBox(ironsights, 68, 31, 41.0F, -37.5234F, -3.0F, 4, 1, 6, 0.0F, false));
        ironsights.cubeList.add(new ModelBox(ironsights, 81, 31, 42.0F, -38.668F, -3.0F, 3, 1, 6, 0.0F, false));
        ironsights.cubeList.add(new ModelBox(ironsights, 13, 72, 41.9F, -38.1602F, -2.9F, 3, 1, 3, 0.0F, false));
        ironsights.cubeList.add(new ModelBox(ironsights, 13, 72, 41.9F, -38.1602F, -0.1F, 3, 1, 3, 0.0F, false));
        ironsights.cubeList.add(new ModelBox(ironsights, 39, 40, 42.7891F, -39.6328F, -1.0F, 1, 2, 2, 0.0F, false));
        ironsights.cubeList.add(new ModelBox(ironsights, 39, 40, 42.7891F, -42.3649F, -0.5F, 1, 1, 1, 0.0F, false));
        ironsights.cubeList.add(new ModelBox(ironsights, 39, 40, 42.7891F, -40.9988F, -2.866F, 1, 1, 2, 0.0F, false));
        ironsights.cubeList.add(new ModelBox(ironsights, 39, 40, 42.7891F, -40.9988F, 0.866F, 1, 1, 2, 0.0F, false));
        ironsights.cubeList.add(new ModelBox(ironsights, 39, 40, 42.5547F, -36.4141F, -3.1289F, 2, 1, 1, 0.0F, false));
        ironsights.cubeList.add(new ModelBox(ironsights, 39, 40, 43.0547F, -36.4141F, -3.2836F, 1, 1, 1, 0.0F, false));
        ironsights.cubeList.add(new ModelBox(ironsights, 81, 31, 42.0F, -40.668F, 2.0F, 3, 2, 1, 0.0F, false));
        ironsights.cubeList.add(new ModelBox(ironsights, 81, 31, 42.0F, -40.668F, -3.0F, 3, 2, 1, 0.0F, false));
        ironsights.cubeList.add(new ModelBox(ironsights, 81, 31, 42.0F, -41.1719F, -3.0F, 2, 1, 1, 0.0F, false));
        ironsights.cubeList.add(new ModelBox(ironsights, 81, 31, 42.0F, -41.1719F, 2.0F, 2, 1, 1, 0.0F, false));
        ironsights.cubeList.add(new ModelBox(ironsights, 68, 31, 30.0F, -41.1094F, -3.0F, 12, 2, 1, 0.0F, false));
        ironsights.cubeList.add(new ModelBox(ironsights, 68, 31, 14.0F, -39.5234F, -3.0F, 28, 1, 1, 0.0F, false));
        ironsights.cubeList.add(new ModelBox(ironsights, 68, 31, 14.0F, -39.5234F, 2.0F, 28, 1, 1, 0.0F, false));
        ironsights.cubeList.add(new ModelBox(ironsights, 68, 31, 30.0F, -41.1094F, 2.0F, 12, 2, 1, 0.0F, false));

        bone24 = new ModelRenderer(this);
        bone24.setRotationPoint(36.5586F, -42.5781F, 2.0F);
        setRotationAngle(bone24, 0.5236F, 0.0F, 0.0F);
        ironsights.addChild(bone24);
        bone24.cubeList.add(new ModelBox(bone24, 39, 40, 6.2305F, 1.6667F, -3.2717F, 1, 1, 1, 0.0F, false));
        bone24.cubeList.add(new ModelBox(bone24, 39, 40, 6.2305F, -1.0653F, -3.2717F, 1, 1, 1, 0.0F, false));
        bone24.cubeList.add(new ModelBox(bone24, 39, 40, 6.2305F, 0.3007F, -1.9057F, 1, 1, 1, 0.0F, false));
        bone24.cubeList.add(new ModelBox(bone24, 39, 40, 6.2305F, 0.3007F, -4.6377F, 1, 1, 1, 0.0F, false));

        bone25 = new ModelRenderer(this);
        bone25.setRotationPoint(36.5586F, -42.5781F, -2.0F);
        setRotationAngle(bone25, -0.5236F, 0.0F, 0.0F);
        ironsights.addChild(bone25);
        bone25.cubeList.add(new ModelBox(bone25, 39, 40, 6.2305F, 1.6667F, 2.2717F, 1, 1, 1, 0.0F, false));
        bone25.cubeList.add(new ModelBox(bone25, 39, 40, 6.2305F, -1.0653F, 2.2717F, 1, 1, 1, 0.0F, false));
        bone25.cubeList.add(new ModelBox(bone25, 39, 40, 6.2305F, 0.3007F, 3.6377F, 1, 1, 1, 0.0F, false));
        bone25.cubeList.add(new ModelBox(bone25, 39, 40, 6.2305F, 0.3007F, 0.9057F, 1, 1, 1, 0.0F, false));

        bone26 = new ModelRenderer(this);
        bone26.setRotationPoint(43.4023F, -39.668F, 3.6F);
        ironsights.addChild(bone26);
        bone26.cubeList.add(new ModelBox(bone26, 19, 71, -0.5F, -0.5F, -0.4F, 1, 1, 1, 0.0F, false));
        bone26.cubeList.add(new ModelBox(bone26, 19, 71, -1.0F, -1.0F, -0.6F, 2, 2, 1, 0.0F, false));

        bone27 = new ModelRenderer(this);
        bone27.setRotationPoint(43.4023F, -39.668F, 3.6F);
        setRotationAngle(bone27, 0.0F, 0.0F, 0.2618F);
        ironsights.addChild(bone27);
        bone27.cubeList.add(new ModelBox(bone27, 19, 71, -0.5F, -0.5F, -0.4F, 1, 1, 1, 0.0F, false));
        bone27.cubeList.add(new ModelBox(bone27, 19, 71, -1.0F, -1.0F, -0.6F, 2, 2, 1, 0.0F, false));

        bone28 = new ModelRenderer(this);
        bone28.setRotationPoint(43.4023F, -39.668F, 3.6F);
        setRotationAngle(bone28, 0.0F, 0.0F, 0.5236F);
        ironsights.addChild(bone28);
        bone28.cubeList.add(new ModelBox(bone28, 19, 71, -0.5F, -0.5F, -0.4F, 1, 1, 1, 0.0F, false));
        bone28.cubeList.add(new ModelBox(bone28, 19, 71, -1.0F, -1.0F, -0.6F, 2, 2, 1, 0.0F, false));

        bone29 = new ModelRenderer(this);
        bone29.setRotationPoint(43.4023F, -39.668F, 3.6F);
        setRotationAngle(bone29, 0.0F, 0.0F, 0.7854F);
        ironsights.addChild(bone29);
        bone29.cubeList.add(new ModelBox(bone29, 19, 71, -0.5F, -0.5F, -0.4F, 1, 1, 1, 0.0F, false));
        bone29.cubeList.add(new ModelBox(bone29, 19, 71, -1.0F, -1.0F, -0.6F, 2, 2, 1, 0.0F, false));

        bone30 = new ModelRenderer(this);
        bone30.setRotationPoint(43.4023F, -39.668F, 3.6F);
        setRotationAngle(bone30, 0.0F, 0.0F, 1.0472F);
        ironsights.addChild(bone30);
        bone30.cubeList.add(new ModelBox(bone30, 19, 71, -0.5F, -0.5F, -0.4F, 1, 1, 1, 0.0F, false));
        bone30.cubeList.add(new ModelBox(bone30, 19, 71, -1.0F, -1.0F, -0.6F, 2, 2, 1, 0.0F, false));

        bone31 = new ModelRenderer(this);
        bone31.setRotationPoint(43.4023F, -39.668F, 3.6F);
        setRotationAngle(bone31, 0.0F, 0.0F, 1.309F);
        ironsights.addChild(bone31);
        bone31.cubeList.add(new ModelBox(bone31, 19, 71, -0.5F, -0.5F, -0.4F, 1, 1, 1, 0.0F, false));
        bone31.cubeList.add(new ModelBox(bone31, 19, 71, -1.0F, -1.0F, -0.6F, 2, 2, 1, 0.0F, false));

        bone23 = new ModelRenderer(this);
        bone23.setRotationPoint(4.1055F, 11.7344F, 0.0F);
        setRotationAngle(bone23, 0.0F, 0.0F, -0.0873F);
        ironsights.addChild(bone23);
        bone23.cubeList.add(new ModelBox(bone23, 68, 31, 40.9734F, -44.9133F, -3.0F, 4, 5, 6, 0.0F, false));

        bone22 = new ModelRenderer(this);
        bone22.setRotationPoint(-3.2734F, -2.3438F, 0.0F);
        setRotationAngle(bone22, 0.0F, 0.0F, 0.0873F);
        ironsights.addChild(bone22);
        bone22.cubeList.add(new ModelBox(bone22, 68, 31, 14.0F, -37.5234F, -3.0F, 3, 7, 6, 0.0F, false));

        bone21 = new ModelRenderer(this);
        bone21.setRotationPoint(-6.5039F, -3.1367F, 0.0F);
        setRotationAngle(bone21, 0.0F, 0.0F, -0.0873F);
        ironsights.addChild(bone21);
        bone21.cubeList.add(new ModelBox(bone21, 68, 31, 23.7298F, -34.5715F, 2.0F, 17, 2, 1, 0.0F, false));
        bone21.cubeList.add(new ModelBox(bone21, 68, 31, 23.7298F, -34.5715F, -3.0F, 17, 2, 1, 0.0F, false));

        bone13 = new ModelRenderer(this);
        bone13.setRotationPoint(22.5F, -25.8711F, 0.0F);
        setRotationAngle(bone13, 0.5236F, 0.0F, 0.0F);
        bone.addChild(bone13);
        bone13.cubeList.add(new ModelBox(bone13, 4, 69, 0.0F, 2.2811F, -4.049F, 1, 1, 1, 0.0F, false));
        bone13.cubeList.add(new ModelBox(bone13, 4, 69, -2.0F, 2.2811F, -4.049F, 1, 1, 1, 0.0F, false));
        bone13.cubeList.add(new ModelBox(bone13, 4, 69, -4.0F, 2.2811F, -4.049F, 1, 1, 1, 0.0F, false));
        bone13.cubeList.add(new ModelBox(bone13, 4, 69, -6.0F, 2.2811F, -4.049F, 1, 1, 1, 0.0F, false));
        bone13.cubeList.add(new ModelBox(bone13, 4, 69, -8.0F, 2.2811F, -4.049F, 1, 1, 1, 0.0F, false));
        bone13.cubeList.add(new ModelBox(bone13, 4, 69, -10.0F, 2.2811F, -4.049F, 1, 1, 1, 0.0F, false));
        bone13.cubeList.add(new ModelBox(bone13, 4, 69, -12.0F, 2.2811F, -4.049F, 1, 1, 1, 0.0F, false));
        bone13.cubeList.add(new ModelBox(bone13, 4, 69, -14.0F, 2.2811F, -4.049F, 1, 1, 1, 0.0F, false));
        bone13.cubeList.add(new ModelBox(bone13, 4, 69, -16.0F, 2.2811F, -4.049F, 1, 1, 1, 0.0F, false));
        bone13.cubeList.add(new ModelBox(bone13, 4, 69, -18.0F, 2.2811F, -4.049F, 1, 1, 1, 0.0F, false));
        bone13.cubeList.add(new ModelBox(bone13, 4, 69, -20.0F, 2.2811F, -4.049F, 1, 1, 1, 0.0F, false));
        bone13.cubeList.add(new ModelBox(bone13, 4, 69, -22.0F, 2.2811F, -4.049F, 1, 1, 1, 0.0F, false));
        bone13.cubeList.add(new ModelBox(bone13, 4, 69, -24.0F, 2.2811F, -4.049F, 1, 1, 1, 0.0F, false));
        bone13.cubeList.add(new ModelBox(bone13, 4, 69, -26.0F, 2.2811F, -4.049F, 1, 1, 1, 0.0F, false));
        bone13.cubeList.add(new ModelBox(bone13, 4, 69, -28.0F, 2.2811F, -4.049F, 1, 1, 1, 0.0F, false));
        bone13.cubeList.add(new ModelBox(bone13, 4, 69, -30.0F, 2.2811F, -4.049F, 1, 1, 1, 0.0F, false));
        bone13.cubeList.add(new ModelBox(bone13, 4, 69, -1.0F, 2.2811F, -4.049F, 1, 1, 0, 0.0F, false));
        bone13.cubeList.add(new ModelBox(bone13, 4, 69, -3.0F, 2.2811F, -4.049F, 1, 1, 0, 0.0F, false));
        bone13.cubeList.add(new ModelBox(bone13, 4, 69, -5.0F, 2.2811F, -4.049F, 1, 1, 0, 0.0F, false));
        bone13.cubeList.add(new ModelBox(bone13, 4, 69, -7.0F, 2.2811F, -4.049F, 1, 1, 0, 0.0F, false));
        bone13.cubeList.add(new ModelBox(bone13, 4, 69, -9.0F, 2.2811F, -4.049F, 1, 1, 0, 0.0F, false));
        bone13.cubeList.add(new ModelBox(bone13, 4, 69, -11.0F, 2.2811F, -4.049F, 1, 1, 0, 0.0F, false));
        bone13.cubeList.add(new ModelBox(bone13, 4, 69, -13.0F, 2.2811F, -4.049F, 1, 1, 0, 0.0F, false));
        bone13.cubeList.add(new ModelBox(bone13, 4, 69, -15.0F, 2.2811F, -4.049F, 1, 1, 0, 0.0F, false));
        bone13.cubeList.add(new ModelBox(bone13, 4, 69, -17.0F, 2.2811F, -4.049F, 1, 1, 0, 0.0F, false));
        bone13.cubeList.add(new ModelBox(bone13, 4, 69, -19.0F, 2.2811F, -4.049F, 1, 1, 0, 0.0F, false));
        bone13.cubeList.add(new ModelBox(bone13, 4, 69, -21.0F, 2.2811F, -4.049F, 1, 1, 0, 0.0F, false));
        bone13.cubeList.add(new ModelBox(bone13, 4, 69, -23.0F, 2.2811F, -4.049F, 1, 1, 0, 0.0F, false));
        bone13.cubeList.add(new ModelBox(bone13, 4, 69, -25.0F, 2.2811F, -4.049F, 1, 1, 0, 0.0F, false));
        bone13.cubeList.add(new ModelBox(bone13, 4, 69, -27.0F, 2.2811F, -4.049F, 1, 1, 0, 0.0F, false));
        bone13.cubeList.add(new ModelBox(bone13, 4, 69, -29.0F, 2.2811F, -4.049F, 1, 1, 0, 0.0F, false));

        bone14 = new ModelRenderer(this);
        bone14.setRotationPoint(22.5F, -25.8711F, 0.0F);
        setRotationAngle(bone14, -0.5236F, 0.0F, 0.0F);
        bone.addChild(bone14);
        bone14.cubeList.add(new ModelBox(bone14, 4, 69, -1.0F, 2.2811F, 4.049F, 1, 1, 0, 0.0F, false));
        bone14.cubeList.add(new ModelBox(bone14, 4, 69, -3.0F, 2.2811F, 4.049F, 1, 1, 0, 0.0F, false));
        bone14.cubeList.add(new ModelBox(bone14, 4, 69, -5.0F, 2.2811F, 4.049F, 1, 1, 0, 0.0F, false));
        bone14.cubeList.add(new ModelBox(bone14, 4, 69, -7.0F, 2.2811F, 4.049F, 1, 1, 0, 0.0F, false));
        bone14.cubeList.add(new ModelBox(bone14, 4, 69, -9.0F, 2.2811F, 4.049F, 1, 1, 0, 0.0F, false));
        bone14.cubeList.add(new ModelBox(bone14, 4, 69, -11.0F, 2.2811F, 4.049F, 1, 1, 0, 0.0F, false));
        bone14.cubeList.add(new ModelBox(bone14, 4, 69, -13.0F, 2.2811F, 4.049F, 1, 1, 0, 0.0F, false));
        bone14.cubeList.add(new ModelBox(bone14, 4, 69, -15.0F, 2.2811F, 4.049F, 1, 1, 0, 0.0F, false));
        bone14.cubeList.add(new ModelBox(bone14, 4, 69, -17.0F, 2.2811F, 4.049F, 1, 1, 0, 0.0F, false));
        bone14.cubeList.add(new ModelBox(bone14, 4, 69, -19.0F, 2.2811F, 4.049F, 1, 1, 0, 0.0F, false));
        bone14.cubeList.add(new ModelBox(bone14, 4, 69, -21.0F, 2.2811F, 4.049F, 1, 1, 0, 0.0F, false));
        bone14.cubeList.add(new ModelBox(bone14, 4, 69, -23.0F, 2.2811F, 4.049F, 1, 1, 0, 0.0F, false));
        bone14.cubeList.add(new ModelBox(bone14, 4, 69, -25.0F, 2.2811F, 4.049F, 1, 1, 0, 0.0F, false));
        bone14.cubeList.add(new ModelBox(bone14, 4, 69, -27.0F, 2.2811F, 4.049F, 1, 1, 0, 0.0F, false));
        bone14.cubeList.add(new ModelBox(bone14, 4, 69, -29.0F, 2.2811F, 4.049F, 1, 1, 0, 0.0F, false));
        bone14.cubeList.add(new ModelBox(bone14, 4, 69, 0.0F, 2.2811F, 3.049F, 1, 1, 1, 0.0F, false));
        bone14.cubeList.add(new ModelBox(bone14, 4, 69, -2.0F, 2.2811F, 3.049F, 1, 1, 1, 0.0F, false));
        bone14.cubeList.add(new ModelBox(bone14, 4, 69, -4.0F, 2.2811F, 3.049F, 1, 1, 1, 0.0F, false));
        bone14.cubeList.add(new ModelBox(bone14, 4, 69, -6.0F, 2.2811F, 3.049F, 1, 1, 1, 0.0F, false));
        bone14.cubeList.add(new ModelBox(bone14, 4, 69, -8.0F, 2.2811F, 3.049F, 1, 1, 1, 0.0F, false));
        bone14.cubeList.add(new ModelBox(bone14, 4, 69, -10.0F, 2.2811F, 3.049F, 1, 1, 1, 0.0F, false));
        bone14.cubeList.add(new ModelBox(bone14, 4, 69, -12.0F, 2.2811F, 3.049F, 1, 1, 1, 0.0F, false));
        bone14.cubeList.add(new ModelBox(bone14, 4, 69, -14.0F, 2.2811F, 3.049F, 1, 1, 1, 0.0F, false));
        bone14.cubeList.add(new ModelBox(bone14, 4, 69, -16.0F, 2.2811F, 3.049F, 1, 1, 1, 0.0F, false));
        bone14.cubeList.add(new ModelBox(bone14, 4, 69, -18.0F, 2.2811F, 3.049F, 1, 1, 1, 0.0F, false));
        bone14.cubeList.add(new ModelBox(bone14, 4, 69, -20.0F, 2.2811F, 3.049F, 1, 1, 1, 0.0F, false));
        bone14.cubeList.add(new ModelBox(bone14, 4, 69, -22.0F, 2.2811F, 3.049F, 1, 1, 1, 0.0F, false));
        bone14.cubeList.add(new ModelBox(bone14, 4, 69, -24.0F, 2.2811F, 3.049F, 1, 1, 1, 0.0F, false));
        bone14.cubeList.add(new ModelBox(bone14, 4, 69, -26.0F, 2.2811F, 3.049F, 1, 1, 1, 0.0F, false));
        bone14.cubeList.add(new ModelBox(bone14, 4, 69, -28.0F, 2.2811F, 3.049F, 1, 1, 1, 0.0F, false));
        bone14.cubeList.add(new ModelBox(bone14, 4, 69, -30.0F, 2.2811F, 3.049F, 1, 1, 1, 0.0F, false));

        bone7 = new ModelRenderer(this);
        bone7.setRotationPoint(11.5F, 2.5F, 0.0F);
        setRotationAngle(bone7, 0.0F, 0.0F, -1.309F);
        bone.addChild(bone7);
        bone7.cubeList.add(new ModelBox(bone7, 82, 30, 5.0875F, -1.8429F, -2.0F, 1, 5, 4, 0.0F, false));

        bone6 = new ModelRenderer(this);
        bone6.setRotationPoint(32.2506F, 24.4183F, 0.0F);
        setRotationAngle(bone6, 0.0F, 0.0F, -0.0873F);
        bone.addChild(bone6);
        bone6.cubeList.add(new ModelBox(bone6, 82, 30, -7.0624F, -31.1524F, -4.0F, 7, 14, 8, 0.0F, false));
        bone6.cubeList.add(new ModelBox(bone6, 82, 30, -0.0625F, -31.1543F, -3.0F, 1, 14, 6, 0.0F, false));
        bone6.cubeList.add(new ModelBox(bone6, 82, 30, -8.0625F, -34.1543F, -3.0F, 2, 17, 6, 0.0F, false));

        magazine = new ModelRenderer(this);
        magazine.setRotationPoint(-15.2656F, 35.0F, -1.8F);
        bone.addChild(magazine);
        magazine.cubeList.add(new ModelBox(magazine, 32, 41, 15.5313F, -46.0F, -1.2F, 10, 14, 6, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 32, 41, 20.5313F, -46.0F, -1.45F, 3, 14, 1, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 32, 41, 20.5313F, -46.0F, 4.1F, 3, 14, 1, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 32, 41, 14.0313F, -46.0F, -1.45F, 3, 14, 1, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 32, 41, 14.0313F, -46.0F, 4.1F, 3, 14, 1, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 32, 41, 7.5313F, -46.0F, -1.45F, 3, 14, 1, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 32, 41, 7.5313F, -46.0F, 4.1F, 3, 14, 1, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 32, 41, 5.5313F, -46.0F, -1.2F, 10, 14, 6, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 34, 42, 5.5313F, -48.0F, 2.8F, 20, 2, 2, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 34, 42, 5.5313F, -48.0F, -1.2F, 20, 2, 2, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 2, 105, 6.3828F, -48.375F, 0.8F, 4, 2, 2, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 0, 101, 9.9297F, -49.375F, -0.2F, 12, 4, 4, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 7, 101, 22.8281F, -49.375F, -0.2F, 2, 4, 4, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 13, 108, 20.8281F, -48.375F, 0.8F, 2, 2, 2, 0.0F, false));

        bone2 = new ModelRenderer(this);
        bone2.setRotationPoint(-0.7344F, 0.0F, 0.0F);
        setRotationAngle(bone2, 0.0F, 0.0F, 0.0873F);
        magazine.addChild(bone2);
        bone2.cubeList.add(new ModelBox(bone2, 32, 42, 13.3767F, -34.1674F, -1.2F, 10, 12, 6, 0.0F, false));
        bone2.cubeList.add(new ModelBox(bone2, 32, 42, 18.3767F, -34.1674F, -1.45F, 3, 12, 1, 0.0F, false));
        bone2.cubeList.add(new ModelBox(bone2, 32, 42, 18.3767F, -34.1674F, 4.1F, 3, 12, 1, 0.0F, false));
        bone2.cubeList.add(new ModelBox(bone2, 32, 42, 5.3767F, -32.9174F, -1.45F, 3, 12, 1, 0.0F, false));
        bone2.cubeList.add(new ModelBox(bone2, 32, 42, 5.3767F, -32.9174F, 4.1F, 3, 12, 1, 0.0F, false));
        bone2.cubeList.add(new ModelBox(bone2, 32, 42, 11.8767F, -33.6674F, -1.45F, 3, 12, 1, 0.0F, false));
        bone2.cubeList.add(new ModelBox(bone2, 32, 42, 11.8767F, -33.6674F, 4.1F, 3, 12, 1, 0.0F, false));
        bone2.cubeList.add(new ModelBox(bone2, 32, 42, 3.3767F, -34.1674F, -1.2F, 10, 12, 6, 0.0F, false));

        bone3 = new ModelRenderer(this);
        bone3.setRotationPoint(0.0F, 0.0F, 0.0F);
        setRotationAngle(bone3, 0.0F, 0.0F, 0.0873F);
        bone2.addChild(bone3);
        bone3.cubeList.add(new ModelBox(bone3, 32, 38, 11.3557F, -24.1205F, -1.2F, 10, 12, 6, 0.0F, false));
        bone3.cubeList.add(new ModelBox(bone3, 32, 38, 16.3557F, -24.1205F, -1.45F, 3, 12, 1, 0.0F, false));
        bone3.cubeList.add(new ModelBox(bone3, 32, 38, 16.3557F, -24.1205F, 4.1F, 3, 12, 1, 0.0F, false));
        bone3.cubeList.add(new ModelBox(bone3, 32, 38, 9.8557F, -23.1205F, -1.45F, 3, 11, 1, 0.0F, false));
        bone3.cubeList.add(new ModelBox(bone3, 32, 38, 9.8557F, -23.1205F, 4.1F, 3, 11, 1, 0.0F, false));
        bone3.cubeList.add(new ModelBox(bone3, 32, 38, 3.5557F, -22.1205F, -1.45F, 3, 10, 1, 0.0F, false));
        bone3.cubeList.add(new ModelBox(bone3, 32, 38, 3.5557F, -22.1205F, 4.1F, 3, 10, 1, 0.0F, false));
        bone3.cubeList.add(new ModelBox(bone3, 32, 42, 1.3557F, -24.1205F, -1.2F, 10, 12, 6, 0.0F, false));
        this.initAnimations();
    }
}

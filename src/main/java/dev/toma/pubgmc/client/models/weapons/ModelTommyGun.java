package dev.toma.pubgmc.client.models.weapons;

import dev.toma.pubgmc.animation_old.HeldAnimation;
import dev.toma.pubgmc.animation_old.HeldAnimation.HeldStyle;
import dev.toma.pubgmc.animation_old.ReloadAnimation;
import dev.toma.pubgmc.client.util.ModelTransformationHelper;
import dev.toma.pubgmc.common.capability.player.PlayerDataProvider;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.item.ItemStack;

public class ModelTommyGun extends ModelGun {

    private final ModelRenderer thompson;
    private final ModelRenderer bone15;
    private final ModelRenderer bone19;
    private final ModelRenderer bone16;
    private final ModelRenderer bone17;
    private final ModelRenderer bone18;
    private final ModelRenderer bone24;
    private final ModelRenderer bone25;
    private final ModelRenderer bone23;
    private final ModelRenderer bone22;
    private final ModelRenderer bone27;
    private final ModelRenderer bone20;
    private final ModelRenderer bone21;
    private final ModelRenderer bone7;
    private final ModelRenderer bone14;
    private final ModelRenderer bone12;
    private final ModelRenderer bone26;
    private final ModelRenderer bone13;
    private final ModelRenderer bone35;
    private final ModelRenderer bone5;
    private final ModelRenderer bone6;
    private final ModelRenderer bone3;
    private final ModelRenderer bone4;
    private final ModelRenderer magazine1;
    private final ModelRenderer bone;
    private final ModelRenderer bone2;
    private final ModelRenderer magazine2;
    private final ModelRenderer bone28;
    private final ModelRenderer bone29;
    private final ModelRenderer bone30;
    private final ModelRenderer bone31;
    private final ModelRenderer bone32;
    private final ModelRenderer bone33;
    private final ModelRenderer bone34;
    private final ModelRenderer charging_handle;
    private final ModelRenderer bone11;
    private final ModelRenderer bone10;
    private final ModelRenderer bone9;
    private final ModelRenderer bone8;

    @Override
    public void initAnimations() {
        initAimAnimation(-0.56f, 0.39f, 0.15f);
        initAimingAnimationStates(0.39f);
        heldAnimation = new HeldAnimation(HeldStyle.SMALL);
        reloadAnimation = new ReloadAnimation(magazine1, ReloadAnimation.ReloadStyle.MAGAZINE).withSpeed(1.2F);
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }

    @Override
    public void render(ItemStack stack, ItemCameraTransforms.TransformType transformType) {
        EntityPlayerSP player = Minecraft.getMinecraft().player;

        if (player != null && player.hasCapability(PlayerDataProvider.PLAYER_DATA, null)) {
            if(hasExtendedMagazine(stack)) {
                if(isSameRenderer(reloadAnimation.getPart(), magazine1)) {
                    reloadAnimation = new ReloadAnimation(magazine2, ReloadAnimation.ReloadStyle.MAGAZINE).initMovement(DEFAULT_PART_ANIMATION);
                }
            } else {
                if(isSameRenderer(reloadAnimation.getPart(), magazine2)) {
                    reloadAnimation = new ReloadAnimation(magazine1, ReloadAnimation.ReloadStyle.MAGAZINE).initMovement(DEFAULT_PART_ANIMATION);
                }
            }
            GlStateManager.pushMatrix();
            renderTommyGun(stack);
            GlStateManager.popMatrix();
        }
    }

    private void renderTommyGun(ItemStack stack) {
        GlStateManager.pushMatrix();
        ModelTransformationHelper.defaultSMGTransform();
        GlStateManager.scale(0.7, 0.7, 0.7);
        GlStateManager.translate(-0.05, 15.975, 0);
        thompson.render(1f);
        charging_handle.render(1.0F);
        if(hasExtendedMagazine(stack)) {
            magazine2.render(1f);
        } else magazine1.render(1f);
        GlStateManager.popMatrix();
//        renderSMGSilencer(0.07, -6.75, -17, 1f, stack);
    }

    public ModelTommyGun() {
        textureWidth = 512;
        textureHeight = 512;

        thompson = new ModelRenderer(this);
        thompson.setRotationPoint(0.0F, 24.0F, 0.0F);
        thompson.cubeList.add(new ModelBox(thompson, 8, 70, -0.082F, -24.5391F, -20.0F, 2, 1, 7, 0.0F, false));
        thompson.cubeList.add(new ModelBox(thompson, 8, 70, -1.918F, -24.5391F, -20.0F, 2, 1, 7, 0.0F, true));
        thompson.cubeList.add(new ModelBox(thompson, 85, 37, -2.5F, -26.5508F, -20.0F, 5, 3, 7, 0.0F, false));
        thompson.cubeList.add(new ModelBox(thompson, 85, 37, -2.5F, -26.5508F, -26.0F, 5, 4, 6, 0.0F, false));
        thompson.cubeList.add(new ModelBox(thompson, 64, 25, -1.0F, -26.5352F, -42.0F, 2, 2, 13, 0.0F, false));
        thompson.cubeList.add(new ModelBox(thompson, 67, 15, -2.5F, -26.5508F, -13.0F, 5, 5, 22, 0.0F, false));
        thompson.cubeList.add(new ModelBox(thompson, 67, 15, -2.5F, -26.5508F, 9.0F, 5, 5, 12, 0.0F, false));
        thompson.cubeList.add(new ModelBox(thompson, 165, 156, -2.0F, -21.5508F, 16.0F, 4, 4, 7, 0.0F, false));
        thompson.cubeList.add(new ModelBox(thompson, 165, 156, -2.0F, -17.8433F, 39.9118F, 4, 11, 2, 0.0F, false));
        thompson.cubeList.add(new ModelBox(thompson, 165, 156, -2.0F, -21.5508F, 9.0F, 4, 4, 7, 0.0F, false));
        thompson.cubeList.add(new ModelBox(thompson, 165, 156, -2.0F, -18.1366F, 10.4142F, 4, 2, 10, 0.0F, false));
        thompson.cubeList.add(new ModelBox(thompson, 67, 15, -1.5F, -26.9648F, 21.0F, 3, 6, 1, 0.0F, false));
        thompson.cubeList.add(new ModelBox(thompson, 5, 17, 1.0F, -26.5508F, 20.5352F, 1, 1, 1, 0.0F, false));
        thompson.cubeList.add(new ModelBox(thompson, 5, 17, -2.0F, -26.5508F, 20.5352F, 1, 1, 1, 0.0F, true));
        thompson.cubeList.add(new ModelBox(thompson, 67, 15, 0.7929F, -27.2579F, -17.0F, 1, 1, 26, 0.0F, false));
        thompson.cubeList.add(new ModelBox(thompson, 67, 15, 0.9648F, -29.961F, 3.4258F, 1, 3, 5, 0.0F, false));
        thompson.cubeList.add(new ModelBox(thompson, 67, 15, -1.9648F, -29.961F, 3.4258F, 1, 3, 5, 0.0F, true));
        thompson.cubeList.add(new ModelBox(thompson, 67, 15, -1.0F, -28.6641F, 6.2852F, 2, 2, 2, 0.0F, true));
        thompson.cubeList.add(new ModelBox(thompson, 67, 15, -1.7929F, -27.2579F, -17.0F, 1, 1, 26, 0.0F, true));
        thompson.cubeList.add(new ModelBox(thompson, 67, 15, -1.0F, -27.2579F, 3.0F, 2, 1, 6, 0.0F, true));
        thompson.cubeList.add(new ModelBox(thompson, 67, 15, -1.7929F, -27.2579F, -26.0F, 2, 1, 9, 0.0F, true));
        thompson.cubeList.add(new ModelBox(thompson, 67, 15, -1.7929F, -27.2579F, 9.0F, 2, 1, 12, 0.0F, true));
        thompson.cubeList.add(new ModelBox(thompson, 67, 15, -1.7929F, -22.8437F, -26.0F, 2, 1, 6, 0.0F, true));
        thompson.cubeList.add(new ModelBox(thompson, 67, 15, -0.2071F, -27.2579F, -26.0F, 2, 1, 9, 0.0F, false));
        thompson.cubeList.add(new ModelBox(thompson, 67, 15, -0.2071F, -27.2579F, 9.0F, 2, 1, 12, 0.0F, false));
        thompson.cubeList.add(new ModelBox(thompson, 67, 15, -0.2071F, -22.8437F, -26.0F, 2, 1, 6, 0.0F, false));
        thompson.cubeList.add(new ModelBox(thompson, 152, 152, -1.5F, -24.8437F, -38.0F, 3, 3, 11, 0.0F, false));
        thompson.cubeList.add(new ModelBox(thompson, 152, 152, -1.5F, -24.8437F, -49.0F, 3, 3, 11, 0.0F, false));
        thompson.cubeList.add(new ModelBox(thompson, 152, 152, -0.2071F, -22.1366F, -49.0F, 1, 1, 11, 0.0F, false));
        thompson.cubeList.add(new ModelBox(thompson, 152, 152, -0.2071F, -22.1366F, -38.0F, 1, 1, 11, 0.0F, false));
        thompson.cubeList.add(new ModelBox(thompson, 152, 152, -0.7929F, -22.1366F, -49.0F, 1, 1, 11, 0.0F, true));
        thompson.cubeList.add(new ModelBox(thompson, 152, 152, -0.7929F, -22.1366F, -38.0F, 1, 1, 11, 0.0F, true));
        thompson.cubeList.add(new ModelBox(thompson, 90, 77, -1.0F, -26.7384F, -7.0F, 2, 1, 10, 0.0F, true));
        thompson.cubeList.add(new ModelBox(thompson, 90, 77, -1.0F, -26.7384F, -17.0F, 2, 1, 10, 0.0F, true));
        thompson.cubeList.add(new ModelBox(thompson, 67, 15, -2.0F, -21.5508F, -13.0F, 4, 5, 3, 0.0F, false));
        thompson.cubeList.add(new ModelBox(thompson, 67, 15, -2.0F, -21.5508F, -10.0F, 4, 1, 9, 0.0F, false));
        thompson.cubeList.add(new ModelBox(thompson, 67, 15, -2.0F, -21.5508F, -1.0F, 4, 2, 6, 0.0F, false));
        thompson.cubeList.add(new ModelBox(thompson, 67, 15, -1.0F, -20.5508F, -3.0F, 2, 2, 2, 0.0F, false));
        thompson.cubeList.add(new ModelBox(thompson, 67, 15, -1.0F, -21.2109F, -4.0F, 2, 3, 1, 0.0F, false));
        thompson.cubeList.add(new ModelBox(thompson, 165, 156, -2.0F, -19.5508F, -1.0F, 4, 5, 7, 0.0F, false));
        thompson.cubeList.add(new ModelBox(thompson, 67, 15, -2.0F, -15.5508F, -11.0F, 4, 1, 3, 0.0F, false));
        thompson.cubeList.add(new ModelBox(thompson, 67, 15, -1.5F, -15.5508F, -8.0F, 3, 1, 7, 0.0F, false));

        bone15 = new ModelRenderer(this);
        bone15.setRotationPoint(0.0F, -25.5352F, -35.4333F);
        thompson.addChild(bone15);
        bone15.cubeList.add(new ModelBox(bone15, 64, 25, -0.5F, -1.5F, 6.4333F, 1, 3, 3, 0.0F, false));
        bone15.cubeList.add(new ModelBox(bone15, 64, 25, -0.5F, -1.5F, 5.2333F, 1, 3, 1, 0.0F, false));
        bone15.cubeList.add(new ModelBox(bone15, 64, 25, -0.5F, -1.5F, 4.0333F, 1, 3, 1, 0.0F, false));
        bone15.cubeList.add(new ModelBox(bone15, 64, 25, -0.5F, -1.5F, 2.8333F, 1, 3, 1, 0.0F, false));
        bone15.cubeList.add(new ModelBox(bone15, 64, 25, -0.5F, -1.5F, 1.6333F, 1, 3, 1, 0.0F, false));
        bone15.cubeList.add(new ModelBox(bone15, 64, 25, -0.5F, -1.5F, 0.4333F, 1, 3, 1, 0.0F, false));
        bone15.cubeList.add(new ModelBox(bone15, 64, 25, -0.5F, -1.5F, -0.7667F, 1, 3, 1, 0.0F, false));
        bone15.cubeList.add(new ModelBox(bone15, 64, 25, -0.5F, -1.5F, -1.9667F, 1, 3, 1, 0.0F, false));
        bone15.cubeList.add(new ModelBox(bone15, 64, 25, -0.5F, -1.5F, -4.3667F, 1, 3, 1, 0.0F, false));
        bone15.cubeList.add(new ModelBox(bone15, 64, 25, -0.5F, -1.5F, -3.1667F, 1, 3, 1, 0.0F, false));
        bone15.cubeList.add(new ModelBox(bone15, 64, 25, -0.5F, -1.5F, -5.5667F, 1, 3, 1, 0.0F, false));
        bone15.cubeList.add(new ModelBox(bone15, 64, 25, -0.5F, -1.5F, -16.7667F, 1, 3, 11, 0.0F, false));
        bone15.cubeList.add(new ModelBox(bone15, 64, 25, -0.5F, -2.5F, -16.1729F, 1, 1, 2, 0.0F, false));
        bone15.cubeList.add(new ModelBox(bone15, 64, 25, -0.5F, -4.4319F, -15.6553F, 1, 2, 1, 0.0F, false));

        bone19 = new ModelRenderer(this);
        bone19.setRotationPoint(0.0F, -3.0F, -15.6729F);
        bone15.addChild(bone19);
        setRotationAngle(bone19, -0.2618F, 0.0F, 0.0F);
        bone19.cubeList.add(new ModelBox(bone19, 64, 25, -0.5F, -1.3876F, -0.3536F, 1, 2, 1, 0.0F, false));

        bone16 = new ModelRenderer(this);
        bone16.setRotationPoint(0.0F, -25.5352F, -35.4333F);
        thompson.addChild(bone16);
        setRotationAngle(bone16, 0.0F, 0.0F, -0.7854F);
        bone16.cubeList.add(new ModelBox(bone16, 64, 25, -0.5F, -1.5F, 6.4333F, 1, 3, 3, 0.0F, false));
        bone16.cubeList.add(new ModelBox(bone16, 64, 25, -0.5F, -1.5F, 5.2333F, 1, 3, 1, 0.0F, false));
        bone16.cubeList.add(new ModelBox(bone16, 64, 25, -0.5F, -1.5F, 4.0333F, 1, 3, 1, 0.0F, false));
        bone16.cubeList.add(new ModelBox(bone16, 64, 25, -0.5F, -1.5F, 2.8333F, 1, 3, 1, 0.0F, false));
        bone16.cubeList.add(new ModelBox(bone16, 64, 25, -0.5F, -1.5F, 1.6333F, 1, 3, 1, 0.0F, false));
        bone16.cubeList.add(new ModelBox(bone16, 64, 25, -0.5F, -1.5F, 0.4333F, 1, 3, 1, 0.0F, false));
        bone16.cubeList.add(new ModelBox(bone16, 64, 25, -0.5F, -1.5F, -0.7667F, 1, 3, 1, 0.0F, false));
        bone16.cubeList.add(new ModelBox(bone16, 64, 25, -0.5F, -1.5F, -1.9667F, 1, 3, 1, 0.0F, false));
        bone16.cubeList.add(new ModelBox(bone16, 64, 25, -0.5F, -1.5F, -4.3667F, 1, 3, 1, 0.0F, false));
        bone16.cubeList.add(new ModelBox(bone16, 64, 25, -0.5F, -1.5F, -3.1667F, 1, 3, 1, 0.0F, false));
        bone16.cubeList.add(new ModelBox(bone16, 64, 25, -0.5F, -1.5F, -5.5667F, 1, 3, 1, 0.0F, false));
        bone16.cubeList.add(new ModelBox(bone16, 64, 25, -0.5F, -1.5F, -16.7667F, 1, 3, 11, 0.0F, false));

        bone17 = new ModelRenderer(this);
        bone17.setRotationPoint(0.0F, -25.5352F, -35.4333F);
        thompson.addChild(bone17);
        setRotationAngle(bone17, 0.0F, 0.0F, -1.5708F);
        bone17.cubeList.add(new ModelBox(bone17, 64, 25, -0.5F, -1.5F, 6.4333F, 1, 3, 3, 0.0F, false));
        bone17.cubeList.add(new ModelBox(bone17, 64, 25, -0.5F, -1.5F, 5.2333F, 1, 3, 1, 0.0F, false));
        bone17.cubeList.add(new ModelBox(bone17, 64, 25, -0.5F, -1.5F, 4.0333F, 1, 3, 1, 0.0F, false));
        bone17.cubeList.add(new ModelBox(bone17, 64, 25, -0.5F, -1.5F, 2.8333F, 1, 3, 1, 0.0F, false));
        bone17.cubeList.add(new ModelBox(bone17, 64, 25, -0.5F, -1.5F, 1.6333F, 1, 3, 1, 0.0F, false));
        bone17.cubeList.add(new ModelBox(bone17, 64, 25, -0.5F, -1.5F, 0.4333F, 1, 3, 1, 0.0F, false));
        bone17.cubeList.add(new ModelBox(bone17, 64, 25, -0.5F, -1.5F, -0.7667F, 1, 3, 1, 0.0F, false));
        bone17.cubeList.add(new ModelBox(bone17, 64, 25, -0.5F, -1.5F, -1.9667F, 1, 3, 1, 0.0F, false));
        bone17.cubeList.add(new ModelBox(bone17, 64, 25, -0.5F, -1.5F, -4.3667F, 1, 3, 1, 0.0F, false));
        bone17.cubeList.add(new ModelBox(bone17, 64, 25, -0.5F, -1.5F, -3.1667F, 1, 3, 1, 0.0F, false));
        bone17.cubeList.add(new ModelBox(bone17, 64, 25, -0.5F, -1.5F, -5.5667F, 1, 3, 1, 0.0F, false));
        bone17.cubeList.add(new ModelBox(bone17, 64, 25, -0.5F, -1.5F, -16.7667F, 1, 3, 11, 0.0F, false));

        bone18 = new ModelRenderer(this);
        bone18.setRotationPoint(0.0F, -25.5352F, -35.4333F);
        thompson.addChild(bone18);
        setRotationAngle(bone18, 0.0F, 0.0F, -2.3562F);
        bone18.cubeList.add(new ModelBox(bone18, 64, 25, -0.5F, -1.5F, 6.4333F, 1, 3, 3, 0.0F, false));
        bone18.cubeList.add(new ModelBox(bone18, 64, 25, -0.5F, -1.5F, 5.2333F, 1, 3, 1, 0.0F, false));
        bone18.cubeList.add(new ModelBox(bone18, 64, 25, -0.5F, -1.5F, 4.0333F, 1, 3, 1, 0.0F, false));
        bone18.cubeList.add(new ModelBox(bone18, 64, 25, -0.5F, -1.5F, 2.8333F, 1, 3, 1, 0.0F, false));
        bone18.cubeList.add(new ModelBox(bone18, 64, 25, -0.5F, -1.5F, 1.6333F, 1, 3, 1, 0.0F, false));
        bone18.cubeList.add(new ModelBox(bone18, 64, 25, -0.5F, -1.5F, 0.4333F, 1, 3, 1, 0.0F, false));
        bone18.cubeList.add(new ModelBox(bone18, 64, 25, -0.5F, -1.5F, -0.7667F, 1, 3, 1, 0.0F, false));
        bone18.cubeList.add(new ModelBox(bone18, 64, 25, -0.5F, -1.5F, -1.9667F, 1, 3, 1, 0.0F, false));
        bone18.cubeList.add(new ModelBox(bone18, 64, 25, -0.5F, -1.5F, -4.3667F, 1, 3, 1, 0.0F, false));
        bone18.cubeList.add(new ModelBox(bone18, 64, 25, -0.5F, -1.5F, -3.1667F, 1, 3, 1, 0.0F, false));
        bone18.cubeList.add(new ModelBox(bone18, 64, 25, -0.5F, -1.5F, -5.5667F, 1, 3, 1, 0.0F, false));
        bone18.cubeList.add(new ModelBox(bone18, 64, 25, -0.5F, -1.5F, -16.7667F, 1, 3, 11, 0.0F, false));

        bone24 = new ModelRenderer(this);
        bone24.setRotationPoint(0.0F, -10.9722F, 40.0212F);
        thompson.addChild(bone24);
        setRotationAngle(bone24, -0.0873F, 0.0F, 0.0F);
        bone24.cubeList.add(new ModelBox(bone24, 165, 156, -2.0F, -3.8773F, -4.7491F, 4, 8, 5, 0.0F, false));

        bone25 = new ModelRenderer(this);
        bone25.setRotationPoint(0.0F, -10.9722F, 34.0212F);
        thompson.addChild(bone25);
        setRotationAngle(bone25, -0.5236F, 0.0F, 0.0F);
        bone25.cubeList.add(new ModelBox(bone25, 165, 156, -2.0F, -6.2565F, -8.3657F, 4, 9, 11, 0.0F, false));
        bone25.cubeList.add(new ModelBox(bone25, 165, 156, -2.0F, -3.2565F, -12.3657F, 4, 6, 4, 0.0F, false));

        bone23 = new ModelRenderer(this);
        bone23.setRotationPoint(0.0F, -39.5449F, 7.1166F);
        thompson.addChild(bone23);
        setRotationAngle(bone23, -1.0472F, 0.0F, 0.0F);
        bone23.cubeList.add(new ModelBox(bone23, 165, 156, -2.0F, -9.3251F, 25.3284F, 4, 4, 3, 0.0F, false));

        bone22 = new ModelRenderer(this);
        bone22.setRotationPoint(-1.0F, 0.0F, 0.0F);
        thompson.addChild(bone22);
        setRotationAngle(bone22, -0.2618F, 0.0F, 0.0F);
        bone22.cubeList.add(new ModelBox(bone22, 165, 156, -1.0F, -26.7693F, 16.6385F, 4, 2, 3, 0.0F, false));
        bone22.cubeList.add(new ModelBox(bone22, 165, 156, -1.0F, -24.9021F, 11.6385F, 4, 5, 8, 0.0F, false));
        bone22.cubeList.add(new ModelBox(bone22, 165, 156, -1.0F, -28.7233F, 21.1428F, 4, 4, 11, 0.0F, false));
        bone22.cubeList.add(new ModelBox(bone22, 165, 156, -1.0F, -28.7233F, 32.1428F, 4, 5, 4, 0.0F, false));

        bone27 = new ModelRenderer(this);
        bone27.setRotationPoint(-1.0F, 0.0F, 0.0F);
        thompson.addChild(bone27);
        setRotationAngle(bone27, -0.7854F, 0.0F, 0.0F);
        bone27.cubeList.add(new ModelBox(bone27, 165, 156, -1.0F, -20.7742F, -6.0463F, 4, 2, 2, 0.0F, false));

        bone20 = new ModelRenderer(this);
        bone20.setRotationPoint(1.4648F, -30.9571F, 6.0F);
        thompson.addChild(bone20);
        setRotationAngle(bone20, 0.1745F, 0.0F, 0.0F);
        bone20.cubeList.add(new ModelBox(bone20, 67, 15, -0.5F, 1.4022F, 1.216F, 1, 3, 1, 0.0F, false));
        bone20.cubeList.add(new ModelBox(bone20, 67, 15, -3.4295F, 1.4022F, 1.216F, 1, 3, 1, 0.0F, true));

        bone21 = new ModelRenderer(this);
        bone21.setRotationPoint(1.4648F, -30.9571F, 4.375F);
        thompson.addChild(bone21);
        setRotationAngle(bone21, -0.1745F, 0.0F, 0.0F);
        bone21.cubeList.add(new ModelBox(bone21, 67, 15, -0.5F, 1.1458F, -0.7618F, 1, 3, 1, 0.0F, false));
        bone21.cubeList.add(new ModelBox(bone21, 67, 15, -3.4295F, 1.1458F, -0.7618F, 1, 3, 1, 0.0F, true));

        bone7 = new ModelRenderer(this);
        bone7.setRotationPoint(2.0F, -27.0508F, -2.0F);
        thompson.addChild(bone7);
        setRotationAngle(bone7, 0.0F, 0.0F, -0.7854F);
        bone7.cubeList.add(new ModelBox(bone7, 65, 14, -1.0F, -0.2929F, -18.0F, 1, 1, 29, 0.0F, false));
        bone7.cubeList.add(new ModelBox(bone7, 65, 14, -1.0F, -0.2929F, 11.0F, 1, 1, 12, 0.0F, false));
        bone7.cubeList.add(new ModelBox(bone7, 65, 14, -1.0F, -0.2929F, -24.0F, 1, 1, 6, 0.0F, false));
        bone7.cubeList.add(new ModelBox(bone7, 65, 14, -3.8284F, 2.5355F, -24.0F, 1, 1, 6, 0.0F, false));
        bone7.cubeList.add(new ModelBox(bone7, 65, 14, -3.5355F, -2.8284F, -18.0F, 1, 1, 29, 0.0F, false));
        bone7.cubeList.add(new ModelBox(bone7, 65, 14, -3.5355F, -2.8284F, 11.0F, 1, 1, 12, 0.0F, false));
        bone7.cubeList.add(new ModelBox(bone7, 65, 14, -3.5355F, -2.8284F, -24.0F, 1, 1, 6, 0.0F, false));
        bone7.cubeList.add(new ModelBox(bone7, 65, 14, -6.364F, 0.0F, -24.0F, 1, 1, 6, 0.0F, false));
        bone7.cubeList.add(new ModelBox(bone7, 152, 152, -6.1569F, 1.2071F, -47.0F, 1, 1, 11, 0.0F, false));
        bone7.cubeList.add(new ModelBox(bone7, 152, 152, -5.0355F, 2.3284F, -47.0F, 1, 1, 11, 0.0F, false));
        bone7.cubeList.add(new ModelBox(bone7, 152, 152, -6.1569F, 1.2071F, -36.0F, 1, 1, 11, 0.0F, false));
        bone7.cubeList.add(new ModelBox(bone7, 152, 152, -5.0355F, 2.3284F, -36.0F, 1, 1, 11, 0.0F, false));

        bone14 = new ModelRenderer(this);
        bone14.setRotationPoint(0.0F, -16.3359F, -4.5F);
        thompson.addChild(bone14);
        setRotationAngle(bone14, -0.3491F, 0.0F, 0.0F);
        bone14.cubeList.add(new ModelBox(bone14, 67, 15, -1.0F, -2.275F, -0.2317F, 2, 2, 1, 0.0F, false));

        bone12 = new ModelRenderer(this);
        bone12.setRotationPoint(-1.5F, 2.9023F, 8.8242F);
        thompson.addChild(bone12);
        setRotationAngle(bone12, 0.3491F, 0.0F, 0.0F);
        bone12.cubeList.add(new ModelBox(bone12, 165, 156, -0.5F, -19.5508F, -1.0F, 4, 8, 5, 0.0F, false));

        bone26 = new ModelRenderer(this);
        bone26.setRotationPoint(-0.5F, 0.0F, 0.0F);
        bone12.addChild(bone26);
        setRotationAngle(bone26, 0.3491F, 0.0F, 0.0F);
        bone26.cubeList.add(new ModelBox(bone26, 165, 156, 0.0F, -11.4861F, 2.7094F, 4, 2, 5, 0.0F, false));
        bone26.cubeList.add(new ModelBox(bone26, 165, 156, 0.0F, -9.4861F, 3.7094F, 4, 1, 3, 0.0F, false));

        bone13 = new ModelRenderer(this);
        bone13.setRotationPoint(-1.5F, -7.0313F, 13.2969F);
        thompson.addChild(bone13);
        setRotationAngle(bone13, 0.9599F, 0.0F, 0.0F);
        bone13.cubeList.add(new ModelBox(bone13, 165, 156, -0.5F, -14.5508F, -1.0F, 4, 1, 1, 0.0F, false));

        bone35 = new ModelRenderer(this);
        bone35.setRotationPoint(-1.0F, 0.0F, 0.0F);
        thompson.addChild(bone35);
        setRotationAngle(bone35, -0.7854F, 0.0F, 0.0F);
        bone35.cubeList.add(new ModelBox(bone35, 67, 15, -1.0F, -4.5108F, -20.0671F, 4, 2, 2, 0.0F, false));
        bone35.cubeList.add(new ModelBox(bone35, 67, 15, -1.0F, -3.5108F, -20.8956F, 4, 1, 1, 0.0F, false));

        bone5 = new ModelRenderer(this);
        bone5.setRotationPoint(0.0F, -16.0508F, -9.5F);
        thompson.addChild(bone5);
        setRotationAngle(bone5, -0.5236F, 0.0F, 0.0F);
        bone5.cubeList.add(new ModelBox(bone5, 67, 15, -2.0F, -0.317F, -0.451F, 4, 1, 2, 0.0F, false));
        bone5.cubeList.add(new ModelBox(bone5, 67, 15, -2.0F, -4.1124F, -2.9084F, 4, 2, 1, 0.0F, false));
        bone5.cubeList.add(new ModelBox(bone5, 67, 15, -2.0F, -8.4151F, 2.1112F, 4, 2, 4, 0.0F, false));

        bone6 = new ModelRenderer(this);
        bone6.setRotationPoint(0.0F, -16.0508F, -9.5F);
        thompson.addChild(bone6);
        setRotationAngle(bone6, -1.0472F, 0.0F, 0.0F);
        bone6.cubeList.add(new ModelBox(bone6, 67, 15, -2.0F, -0.2945F, -1.857F, 4, 1, 2, 0.0F, false));
        bone6.cubeList.add(new ModelBox(bone6, 67, 15, -2.0F, -3.6232F, -4.3826F, 4, 2, 1, 0.0F, false));

        bone3 = new ModelRenderer(this);
        bone3.setRotationPoint(0.0F, -15.0508F, -5.0F);
        thompson.addChild(bone3);
        setRotationAngle(bone3, 0.0F, -0.3491F, 0.0F);
        bone3.cubeList.add(new ModelBox(bone3, 67, 15, -0.1467F, -0.5F, -3.5031F, 1, 1, 2, 0.0F, false));

        bone4 = new ModelRenderer(this);
        bone4.setRotationPoint(0.0F, -15.0508F, -5.0F);
        thompson.addChild(bone4);
        setRotationAngle(bone4, 0.0F, 0.3491F, 0.0F);
        bone4.cubeList.add(new ModelBox(bone4, 67, 15, -0.8533F, -0.5F, -3.5031F, 1, 1, 2, 0.0F, true));

        magazine1 = new ModelRenderer(this);
        magazine1.setRotationPoint(0.0F, 21.0742F, 0.0F);
        magazine1.cubeList.add(new ModelBox(magazine1, 20, 146, -2.0F, -1.0F, -20.0F, 4, 2, 7, 0.0F, false));
        magazine1.cubeList.add(new ModelBox(magazine1, 20, 146, 1.0F, -24.0F, -20.0F, 1, 3, 7, 0.0F, false));
        magazine1.cubeList.add(new ModelBox(magazine1, 20, 146, -2.0F, -24.0F, -20.0F, 1, 3, 7, 0.0F, false));
        magazine1.cubeList.add(new ModelBox(magazine1, 20, 146, -1.0F, -23.0F, -14.0F, 2, 2, 1, 0.0F, false));
        magazine1.cubeList.add(new ModelBox(magazine1, 6, 505, -1.0F, -23.418F, -15.0F, 2, 2, 1, 0.0F, false));
        magazine1.cubeList.add(new ModelBox(magazine1, 6, 505, -1.0F, -23.418F, -17.3828F, 2, 2, 2, 0.0F, false));
        magazine1.cubeList.add(new ModelBox(magazine1, 6, 505, -0.9F, -23.318F, -18.2836F, 1, 1, 1, 0.0F, false));
        magazine1.cubeList.add(new ModelBox(magazine1, 6, 505, -0.7F, -22.718F, -18.8437F, 1, 1, 1, 0.0F, false));
        magazine1.cubeList.add(new ModelBox(magazine1, 6, 505, -0.7F, -23.118F, -18.8437F, 1, 1, 1, 0.0F, false));
        magazine1.cubeList.add(new ModelBox(magazine1, 6, 505, -0.3F, -22.718F, -18.8437F, 1, 1, 1, 0.0F, false));
        magazine1.cubeList.add(new ModelBox(magazine1, 6, 505, -0.3F, -23.118F, -18.8437F, 1, 1, 1, 0.0F, false));
        magazine1.cubeList.add(new ModelBox(magazine1, 20, 146, -0.9F, -22.518F, -18.2836F, 1, 1, 1, 0.0F, false));
        magazine1.cubeList.add(new ModelBox(magazine1, 6, 505, -0.1F, -23.318F, -18.2836F, 1, 1, 1, 0.0F, false));
        magazine1.cubeList.add(new ModelBox(magazine1, 20, 146, -0.1F, -22.518F, -18.2836F, 1, 1, 1, 0.0F, false));
        magazine1.cubeList.add(new ModelBox(magazine1, 6, 505, -0.8F, -23.218F, -15.7305F, 1, 1, 1, 0.0F, false));
        magazine1.cubeList.add(new ModelBox(magazine1, 6, 505, -0.8F, -22.618F, -15.7305F, 1, 1, 1, 0.0F, false));
        magazine1.cubeList.add(new ModelBox(magazine1, 6, 505, -0.2F, -23.218F, -15.7305F, 1, 1, 1, 0.0F, true));
        magazine1.cubeList.add(new ModelBox(magazine1, 6, 505, -0.2F, -22.618F, -15.7305F, 1, 1, 1, 0.0F, true));
        magazine1.cubeList.add(new ModelBox(magazine1, 20, 146, -1.0F, -23.0F, -20.0F, 2, 2, 1, 0.0F, false));
        magazine1.cubeList.add(new ModelBox(magazine1, 20, 146, -2.0F, -21.0F, -14.0F, 4, 20, 1, 0.0F, false));
        magazine1.cubeList.add(new ModelBox(magazine1, 20, 146, -1.5F, -21.0F, -15.0F, 3, 20, 1, 0.0F, false));
        magazine1.cubeList.add(new ModelBox(magazine1, 20, 146, -2.0F, -21.0F, -20.0F, 4, 20, 5, 0.0F, false));

        bone = new ModelRenderer(this);
        bone.setRotationPoint(0.0F, 0.0F, 0.0F);
        magazine1.addChild(bone);
        setRotationAngle(bone, 0.0F, 0.0F, 0.2618F);
        bone.cubeList.add(new ModelBox(bone, 20, 146, -4.5033F, -20.8021F, -15.0F, 1, 2, 1, 0.0F, false));

        bone2 = new ModelRenderer(this);
        bone2.setRotationPoint(0.0F, 0.0F, 0.0F);
        magazine1.addChild(bone2);
        setRotationAngle(bone2, 0.0F, 0.0F, -0.2618F);
        bone2.cubeList.add(new ModelBox(bone2, 20, 146, 3.5033F, -20.8021F, -15.0F, 1, 2, 1, 0.0F, true));

        magazine2 = new ModelRenderer(this);
        magazine2.setRotationPoint(0.0F, 21.5938F, 0.0F);
        magazine2.cubeList.add(new ModelBox(magazine2, 29, 169, 1.0F, -24.0F, -20.0F, 1, 3, 7, 0.0F, false));
        magazine2.cubeList.add(new ModelBox(magazine2, 29, 169, -2.0F, -24.0F, -20.0F, 1, 3, 7, 0.0F, false));
        magazine2.cubeList.add(new ModelBox(magazine2, 29, 169, -1.0F, -23.0F, -14.0F, 2, 2, 1, 0.0F, false));
        magazine2.cubeList.add(new ModelBox(magazine2, 29, 169, -2.0F, -21.0F, -20.0F, 4, 8, 7, 0.0F, false));
        magazine2.cubeList.add(new ModelBox(magazine2, 29, 169, -8.3374F, -10.5158F, -20.0F, 2, 1, 7, 0.0F, false));
        magazine2.cubeList.add(new ModelBox(magazine2, 29, 169, 6.3374F, -10.5158F, -20.0F, 2, 1, 7, 0.0F, true));
        magazine2.cubeList.add(new ModelBox(magazine2, 0, 497, -1.0F, -23.418F, -15.0F, 2, 2, 1, 0.0F, false));
        magazine2.cubeList.add(new ModelBox(magazine2, 0, 497, -1.0F, -23.418F, -17.3828F, 2, 2, 2, 0.0F, false));
        magazine2.cubeList.add(new ModelBox(magazine2, 0, 497, -0.9F, -23.318F, -18.2836F, 1, 1, 1, 0.0F, false));
        magazine2.cubeList.add(new ModelBox(magazine2, 0, 497, -0.7F, -22.718F, -18.8437F, 1, 1, 1, 0.0F, false));
        magazine2.cubeList.add(new ModelBox(magazine2, 0, 497, -0.7F, -23.118F, -18.8437F, 1, 1, 1, 0.0F, false));
        magazine2.cubeList.add(new ModelBox(magazine2, 0, 497, -0.3F, -22.718F, -18.8437F, 1, 1, 1, 0.0F, false));
        magazine2.cubeList.add(new ModelBox(magazine2, 0, 497, -0.3F, -23.118F, -18.8437F, 1, 1, 1, 0.0F, false));
        magazine2.cubeList.add(new ModelBox(magazine2, 0, 497, -0.9F, -22.518F, -18.2836F, 1, 1, 1, 0.0F, false));
        magazine2.cubeList.add(new ModelBox(magazine2, 0, 497, -0.1F, -23.318F, -18.2836F, 1, 1, 1, 0.0F, false));
        magazine2.cubeList.add(new ModelBox(magazine2, 0, 497, -0.1F, -22.518F, -18.2836F, 1, 1, 1, 0.0F, false));
        magazine2.cubeList.add(new ModelBox(magazine2, 0, 497, -0.8F, -23.218F, -15.7305F, 1, 1, 1, 0.0F, false));
        magazine2.cubeList.add(new ModelBox(magazine2, 0, 497, -0.8F, -22.618F, -15.7305F, 1, 1, 1, 0.0F, false));
        magazine2.cubeList.add(new ModelBox(magazine2, 0, 497, -0.2F, -23.218F, -15.7305F, 1, 1, 1, 0.0F, true));
        magazine2.cubeList.add(new ModelBox(magazine2, 0, 497, -0.2F, -22.618F, -15.7305F, 1, 1, 1, 0.0F, true));
        magazine2.cubeList.add(new ModelBox(magazine2, 29, 169, -1.0F, -23.0F, -20.0F, 2, 2, 1, 0.0F, false));

        bone28 = new ModelRenderer(this);
        bone28.setRotationPoint(0.0F, 0.0F, 0.0F);
        magazine2.addChild(bone28);
        setRotationAngle(bone28, 0.0F, 0.0F, 0.2618F);


        bone29 = new ModelRenderer(this);
        bone29.setRotationPoint(0.0F, 0.0F, 0.0F);
        magazine2.addChild(bone29);
        setRotationAngle(bone29, 0.0F, 0.0F, -0.2618F);


        bone30 = new ModelRenderer(this);
        bone30.setRotationPoint(0.0F, 0.0F, 0.0F);
        magazine2.addChild(bone30);
        bone30.cubeList.add(new ModelBox(bone30, 29, 169, -2.5F, -18.0F, -20.0F, 5, 16, 7, 0.0F, false));
        bone30.cubeList.add(new ModelBox(bone30, 78, 24, -3.0F, -13.0F, -20.4414F, 6, 6, 8, 0.0F, false));
        bone30.cubeList.add(new ModelBox(bone30, 78, 24, -2.0F, -12.0F, -21.4414F, 4, 4, 10, 0.0F, false));

        bone31 = new ModelRenderer(this);
        bone31.setRotationPoint(0.0F, -10.0F, -16.5F);
        magazine2.addChild(bone31);
        setRotationAngle(bone31, 0.0F, 0.0F, 0.6109F);
        bone31.cubeList.add(new ModelBox(bone31, 29, 169, -2.5F, -8.0F, -3.5F, 5, 16, 7, 0.0F, false));

        bone32 = new ModelRenderer(this);
        bone32.setRotationPoint(0.0F, -10.0F, -16.5F);
        magazine2.addChild(bone32);
        setRotationAngle(bone32, 0.0F, 0.0F, -0.6109F);
        bone32.cubeList.add(new ModelBox(bone32, 29, 169, -2.5F, -8.0F, -3.5F, 5, 16, 7, 0.0F, false));

        bone33 = new ModelRenderer(this);
        bone33.setRotationPoint(0.0F, -10.0F, -16.5F);
        magazine2.addChild(bone33);
        setRotationAngle(bone33, 0.0F, 0.0F, -1.2217F);
        bone33.cubeList.add(new ModelBox(bone33, 29, 169, -2.5F, -8.0F, -3.5F, 5, 16, 7, 0.0F, false));

        bone34 = new ModelRenderer(this);
        bone34.setRotationPoint(0.0F, -10.0F, -16.5F);
        magazine2.addChild(bone34);
        setRotationAngle(bone34, 0.0F, 0.0F, 1.2217F);
        bone34.cubeList.add(new ModelBox(bone34, 29, 169, -2.5F, -8.0F, -3.5F, 5, 16, 7, 0.0F, false));

        charging_handle = new ModelRenderer(this);
        charging_handle.setRotationPoint(0.7929F, 24.0F, 0.0F);
        charging_handle.cubeList.add(new ModelBox(charging_handle, 49, 45, -0.8929F, -28.7462F, -14.9547F, 1, 1, 1, 0.0F, true));
        charging_handle.cubeList.add(new ModelBox(charging_handle, 49, 45, -1.6929F, -28.7462F, -14.9547F, 1, 1, 1, 0.0F, false));
        charging_handle.cubeList.add(new ModelBox(charging_handle, 49, 45, -1.6929F, -28.7462F, -14.1547F, 1, 1, 1, 0.0F, false));
        charging_handle.cubeList.add(new ModelBox(charging_handle, 49, 45, -0.8929F, -28.7462F, -14.1547F, 1, 1, 1, 0.0F, true));

        bone11 = new ModelRenderer(this);
        bone11.setRotationPoint(-0.7929F, -27.4845F, -14.0547F);
        charging_handle.addChild(bone11);
        setRotationAngle(bone11, 0.0F, 0.0F, -0.1745F);
        bone11.cubeList.add(new ModelBox(bone11, 51, 48, -0.3339F, -0.898F, -0.5F, 1, 2, 1, 0.0F, false));

        bone10 = new ModelRenderer(this);
        bone10.setRotationPoint(-0.7929F, -27.4845F, -14.0547F);
        charging_handle.addChild(bone10);
        setRotationAngle(bone10, 0.0F, 0.0F, 0.1745F);
        bone10.cubeList.add(new ModelBox(bone10, 51, 48, -0.6661F, -0.898F, -0.5F, 1, 2, 1, 0.0F, true));

        bone9 = new ModelRenderer(this);
        bone9.setRotationPoint(-0.7929F, -27.4845F, -14.0547F);
        charging_handle.addChild(bone9);
        setRotationAngle(bone9, -0.1745F, 0.0F, 0.0F);
        bone9.cubeList.add(new ModelBox(bone9, 51, 48, -0.5F, -0.898F, -0.6661F, 1, 2, 1, 0.0F, true));

        bone8 = new ModelRenderer(this);
        bone8.setRotationPoint(-0.7929F, -27.4845F, -14.0547F);
        charging_handle.addChild(bone8);
        setRotationAngle(bone8, 0.1745F, 0.0F, 0.0F);
        bone8.cubeList.add(new ModelBox(bone8, 51, 48, -0.5F, -0.898F, -0.3339F, 1, 2, 1, 0.0F, true));
        this.initAnimations();
    }

    // simple workaround for magazine animation
    private boolean isSameRenderer(ModelRenderer r0, ModelRenderer r1) {
        return r0.cubeList.size() == r1.cubeList.size();
    }
}

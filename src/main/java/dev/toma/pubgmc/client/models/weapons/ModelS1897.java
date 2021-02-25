package dev.toma.pubgmc.client.models.weapons;

import dev.toma.pubgmc.animation.ReloadAnimation;
import dev.toma.pubgmc.animation.ReloadAnimation.ReloadStyle;
import dev.toma.pubgmc.client.models.ModelGun;
import dev.toma.pubgmc.client.util.ModelTransformationHelper;
import dev.toma.pubgmc.common.capability.player.PlayerDataProvider;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.item.ItemStack;

public class ModelS1897 extends ModelGun {

    private final ModelRenderer bone;
    private final ModelRenderer bone2;
    private final ModelRenderer bone13;
    private final ModelRenderer bone8;
    private final ModelRenderer bone10;
    private final ModelRenderer bone9;
    private final ModelRenderer bone11;
    private final ModelRenderer bone12;
    private final ModelRenderer bone14;
    private final ModelRenderer bone15;
    private final ModelRenderer bone16;
    private final ModelRenderer bone23;
    private final ModelRenderer bone24;
    private final ModelRenderer bone25;
    private final ModelRenderer bone17;
    private final ModelRenderer bone18;
    private final ModelRenderer bone19;
    private final ModelRenderer bone20;
    private final ModelRenderer bone21;
    private final ModelRenderer bone22;
    private final ModelRenderer bone6;
    private final ModelRenderer bone7;
    private final ModelRenderer bone4;
    private final ModelRenderer bone5;
    private final ModelRenderer bone3;

    @Override
    public String textureName() {
        return "sawedoff";
    }

    @Override
    public void initAnimations() {
        initAimAnimation(-0.525f, 0.31f, 0.315f);
        initAimingAnimationStates(0.31f);
        reloadAnimation = new ReloadAnimation(null, ReloadStyle.SINGLE);
    }

    @Override
    public void render(ItemStack stack) {
        EntityPlayerSP player = Minecraft.getMinecraft().player;
        if (player != null && player.hasCapability(PlayerDataProvider.PLAYER_DATA, null)) {
            GlStateManager.pushMatrix();
            {
                renderS1897(stack);
            }
            GlStateManager.popMatrix();
        }
    }

    private void renderS1897(ItemStack stack) {
        GlStateManager.pushMatrix();
        {
            ModelTransformationHelper.defaultShotgunTransform();
            GlStateManager.translate(-0.050000004, -2.8250031, -19.0);
            bone.render(1f);
        }
        GlStateManager.popMatrix();
    }

    public ModelS1897() {
        textureWidth = 128;
        textureHeight = 128;

        bone = new ModelRenderer(this);
        bone.setRotationPoint(0.0F, 24.0F, 0.0F);
        bone.cubeList.add(new ModelBox(bone, 78, 34, -2.5F, -1.0F, -1.0F, 1, 1, 8, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 78, 34, 1.5F, -1.0F, -1.0F, 1, 1, 8, 0.0F, true));
        bone.cubeList.add(new ModelBox(bone, 74, 26, -2.5F, -1.0F, 7.0F, 5, 1, 6, 0.0F, true));
        bone.cubeList.add(new ModelBox(bone, 74, 26, -2.5F, -10.4142F, -11.0F, 5, 1, 17, 0.0F, true));
        bone.cubeList.add(new ModelBox(bone, 50, 16, -1.5F, -11.0142F, -9.984F, 3, 1, 1, 0.0F, true));
        bone.cubeList.add(new ModelBox(bone, 54, 17, -1.5F, -11.0142F, 19.016F, 3, 1, 1, 0.0F, true));
        bone.cubeList.add(new ModelBox(bone, 39, 13, -1.4329F, -12.233F, 19.5F, 1, 2, 1, 0.0F, true));
        bone.cubeList.add(new ModelBox(bone, 39, 13, 0.4329F, -12.233F, 19.5F, 1, 2, 1, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 39, 13, -0.5F, -11.653F, 19.5F, 1, 1, 1, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 50, 16, -1.5F, -11.0142F, -9.016F, 3, 1, 1, 0.0F, true));
        bone.cubeList.add(new ModelBox(bone, 46, 16, -1.5F, -11.0142F, 19.984F, 3, 1, 1, 0.0F, true));
        bone.cubeList.add(new ModelBox(bone, 45, 42, -0.5F, -11.8142F, -9.8F, 1, 2, 2, 0.0F, true));
        bone.cubeList.add(new ModelBox(bone, 45, 42, -0.5F, -11.615F, -8.6703F, 1, 1, 1, 0.0F, true));
        bone.cubeList.add(new ModelBox(bone, 45, 42, -0.5F, -12.5213F, -9.0929F, 1, 1, 1, 0.0F, true));
        bone.cubeList.add(new ModelBox(bone, 74, 26, -2.5F, -10.4142F, 6.0F, 5, 1, 17, 0.0F, true));
        bone.cubeList.add(new ModelBox(bone, 74, 26, -2.5F, -10.3142F, 22.2F, 5, 1, 1, 0.0F, true));
        bone.cubeList.add(new ModelBox(bone, 74, 26, -2.5F, -3.0F, 16.0F, 5, 1, 7, 0.0F, true));
        bone.cubeList.add(new ModelBox(bone, 74, 26, 2.2071F, -9.7071F, 6.0F, 1, 9, 7, 0.0F, true));
        bone.cubeList.add(new ModelBox(bone, 74, 26, 2.2071F, -9.7071F, 13.0F, 1, 7, 10, 0.0F, true));
        bone.cubeList.add(new ModelBox(bone, 74, 26, 2.2071F, -9.7071F, -11.0F, 1, 9, 17, 0.0F, true));
        bone.cubeList.add(new ModelBox(bone, 73, 17, -3.2071F, -4.7071F, 0.0F, 1, 4, 13, 0.0F, true));
        bone.cubeList.add(new ModelBox(bone, 73, 17, -3.2071F, -9.7071F, 8.0F, 1, 7, 15, 0.0F, true));
        bone.cubeList.add(new ModelBox(bone, 73, 17, -3.2071F, -9.7071F, 0.0F, 1, 2, 8, 0.0F, true));
        bone.cubeList.add(new ModelBox(bone, 4, 7, -2.8071F, -7.7071F, 0.0F, 1, 3, 8, 0.0F, true));
        bone.cubeList.add(new ModelBox(bone, 4, 7, -3.0071F, -6.7071F, 0.3F, 1, 1, 2, 0.0F, true));
        bone.cubeList.add(new ModelBox(bone, 74, 26, -3.2071F, -9.7071F, -11.0F, 1, 9, 11, 0.0F, true));
        bone.cubeList.add(new ModelBox(bone, 78, 29, -2.5F, -10.0F, -11.0F, 5, 10, 10, 0.0F, true));
        bone.cubeList.add(new ModelBox(bone, 69, 15, -1.0F, -6.5F, -33.0F, 2, 1, 22, 0.0F, true));
        bone.cubeList.add(new ModelBox(bone, 69, 15, -1.0F, -6.5F, -53.0F, 2, 1, 20, 0.0F, true));
        bone.cubeList.add(new ModelBox(bone, 10, 71, -1.5F, -9.5F, -14.6F, 3, 3, 1, 0.0F, true));
        bone.cubeList.add(new ModelBox(bone, 69, 15, -1.0F, -1.3F, -30.0F, 2, 1, 19, 0.0F, true));
        bone.cubeList.add(new ModelBox(bone, 69, 15, -1.0F, -1.3F, -41.0F, 2, 1, 11, 0.0F, true));
        bone.cubeList.add(new ModelBox(bone, 69, 15, -1.0F, -2.3F, -42.6F, 2, 2, 1, 0.0F, true));
        bone.cubeList.add(new ModelBox(bone, 32, 66, -1.5F, -0.8F, -40.4F, 3, 1, 10, 0.0F, true));
        bone.cubeList.add(new ModelBox(bone, 32, 66, -1.5F, -0.8F, -30.4F, 3, 1, 10, 0.0F, true));
        bone.cubeList.add(new ModelBox(bone, 32, 67, 1.8927F, -4.1927F, -40.4F, 1, 3, 10, 0.0F, true));
        bone.cubeList.add(new ModelBox(bone, 32, 67, 1.8927F, -4.1927F, -30.4F, 1, 3, 10, 0.0F, true));
        bone.cubeList.add(new ModelBox(bone, 32, 67, -2.8927F, -4.1927F, -40.4F, 1, 3, 10, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 32, 67, -2.8927F, -4.1927F, -30.4F, 1, 3, 10, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 69, 15, -1.0F, -10.2321F, -33.0F, 2, 1, 22, 0.0F, true));
        bone.cubeList.add(new ModelBox(bone, 69, 15, -1.0F, -10.2321F, -53.0F, 2, 1, 20, 0.0F, true));
        bone.cubeList.add(new ModelBox(bone, 69, 15, -1.0F, -5.0321F, -30.0F, 2, 1, 19, 0.0F, true));
        bone.cubeList.add(new ModelBox(bone, 69, 15, -1.0F, -5.0321F, -41.0F, 2, 1, 11, 0.0F, true));
        bone.cubeList.add(new ModelBox(bone, 69, 15, -1.0F, -5.0321F, -42.6F, 2, 3, 1, 0.0F, true));
        bone.cubeList.add(new ModelBox(bone, 69, 15, -1.5F, -6.2321F, -25.3F, 3, 2, 2, 0.0F, true));
        bone.cubeList.add(new ModelBox(bone, 69, 15, -1.5F, -6.2321F, -15.3F, 3, 2, 2, 0.0F, true));
        bone.cubeList.add(new ModelBox(bone, 69, 15, -1.5F, -6.2321F, -20.3F, 3, 2, 2, 0.0F, true));
        bone.cubeList.add(new ModelBox(bone, 69, 15, -1.5F, -6.2321F, -30.3F, 3, 2, 2, 0.0F, true));
        bone.cubeList.add(new ModelBox(bone, 69, 15, -1.5F, -6.2321F, -35.3F, 3, 2, 2, 0.0F, true));
        bone.cubeList.add(new ModelBox(bone, 69, 15, -1.5F, -6.2321F, -40.3F, 3, 2, 2, 0.0F, true));
        bone.cubeList.add(new ModelBox(bone, 69, 15, 1.366F, -8.866F, -33.0F, 1, 2, 22, 0.0F, true));
        bone.cubeList.add(new ModelBox(bone, 69, 15, 1.366F, -8.866F, -53.0F, 1, 2, 20, 0.0F, true));
        bone.cubeList.add(new ModelBox(bone, 69, 15, 1.366F, -3.666F, -30.0F, 1, 2, 19, 0.0F, true));
        bone.cubeList.add(new ModelBox(bone, 69, 15, 1.366F, -3.666F, -41.0F, 1, 2, 11, 0.0F, true));
        bone.cubeList.add(new ModelBox(bone, 69, 15, 0.366F, -3.666F, -42.6F, 2, 2, 1, 0.0F, true));
        bone.cubeList.add(new ModelBox(bone, 69, 15, -1.5F, -4.666F, -41.8F, 3, 4, 1, 0.0F, true));
        bone.cubeList.add(new ModelBox(bone, 69, 15, -2.366F, -8.866F, -33.0F, 1, 2, 22, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 69, 15, -2.366F, -8.866F, -53.0F, 1, 2, 20, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 69, 15, -2.366F, -3.666F, -30.0F, 1, 2, 19, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 69, 15, -2.366F, -3.666F, -41.0F, 1, 2, 11, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 69, 15, -2.366F, -3.666F, -42.6F, 2, 2, 1, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 69, 15, -1.0F, -3.666F, -42.9F, 2, 2, 1, 0.0F, false));

        bone2 = new ModelRenderer(this);
        bone2.setRotationPoint(-0.5F, 0.2F, 0.1F);
        setRotationAngle(bone2, -0.0349F, 0.0F, 0.0F);
        bone.addChild(bone2);
        bone2.cubeList.add(new ModelBox(bone2, 0, 0, -1.0F, -1.7F, -2.0F, 3, 1, 9, 0.0F, false));

        bone13 = new ModelRenderer(this);
        bone13.setRotationPoint(0.5F, 6.7F, 0.0F);
        setRotationAngle(bone13, 0.5236F, 0.0F, 0.0F);
        bone.addChild(bone13);
        bone13.cubeList.add(new ModelBox(bone13, 74, 26, -3.0F, -0.3024F, 14.6083F, 5, 1, 6, 0.0F, true));
        bone13.cubeList.add(new ModelBox(bone13, 74, 26, -3.5F, -3.9086F, 14.9583F, 6, 4, 6, 0.0F, true));

        bone8 = new ModelRenderer(this);
        bone8.setRotationPoint(0.3F, -11.5142F, -9.0F);
        setRotationAngle(bone8, 0.0F, 0.0F, 0.3491F);
        bone.addChild(bone8);
        bone8.cubeList.add(new ModelBox(bone8, 48, 13, 0.7744F, -0.5999F, -1.0F, 1, 2, 2, 0.0F, true));
        bone8.cubeList.add(new ModelBox(bone8, 48, 13, 0.7744F, -0.5999F, 28.0F, 1, 2, 2, 0.0F, true));

        bone10 = new ModelRenderer(this);
        bone10.setRotationPoint(-0.3F, -11.5142F, -9.0F);
        setRotationAngle(bone10, 0.0F, 0.0F, -0.3491F);
        bone.addChild(bone10);
        bone10.cubeList.add(new ModelBox(bone10, 48, 13, -1.7744F, -0.5999F, -1.0F, 1, 2, 2, 0.0F, false));
        bone10.cubeList.add(new ModelBox(bone10, 48, 13, -1.7744F, -0.5999F, 28.0F, 1, 2, 2, 0.0F, false));

        bone9 = new ModelRenderer(this);
        bone9.setRotationPoint(-0.3F, -11.5142F, -9.0F);
        setRotationAngle(bone9, 0.0F, 0.0F, -0.3491F);
        bone.addChild(bone9);

        bone11 = new ModelRenderer(this);
        bone11.setRotationPoint(0.0F, -12.3142F, -8.3F);
        setRotationAngle(bone11, 0.7854F, 0.0F, 0.0F);
        bone.addChild(bone11);
        bone11.cubeList.add(new ModelBox(bone11, 45, 42, -0.5F, -0.7071F, -1.4142F, 1, 1, 1, 0.0F, true));
        bone11.cubeList.add(new ModelBox(bone11, 45, 42, -0.5F, 0.6468F, -1.7562F, 1, 1, 1, 0.0F, true));

        bone12 = new ModelRenderer(this);
        bone12.setRotationPoint(0.0F, -12.3142F, -8.3F);
        setRotationAngle(bone12, 0.4363F, 0.0F, 0.0F);
        bone.addChild(bone12);
        bone12.cubeList.add(new ModelBox(bone12, 45, 42, -0.5F, -0.1002F, -0.7248F, 1, 1, 1, 0.0F, true));

        bone14 = new ModelRenderer(this);
        bone14.setRotationPoint(0.5F, 0.0F, 0.0F);
        setRotationAngle(bone14, -0.2618F, 0.0F, 0.0F);
        bone.addChild(bone14);
        bone14.cubeList.add(new ModelBox(bone14, 73, 17, -3.7071F, -15.3292F, 19.7039F, 1, 7, 9, 0.0F, true));
        bone14.cubeList.add(new ModelBox(bone14, 74, 26, -3.0F, -16.0363F, 19.7039F, 5, 1, 9, 0.0F, true));
        bone14.cubeList.add(new ModelBox(bone14, 74, 26, -2.5F, -16.5539F, 22.6358F, 4, 1, 8, 0.0F, true));
        bone14.cubeList.add(new ModelBox(bone14, 38, 42, -2.0F, -16.7383F, 21.6193F, 3, 1, 5, 0.0F, true));
        bone14.cubeList.add(new ModelBox(bone14, 38, 42, -2.0F, -17.0194F, 20.1775F, 3, 2, 2, 0.0F, true));
        bone14.cubeList.add(new ModelBox(bone14, 86, 33, 0.5F, -16.8194F, 20.6775F, 1, 1, 2, 0.0F, true));
        bone14.cubeList.add(new ModelBox(bone14, 86, 33, -2.5F, -16.8194F, 20.3775F, 1, 1, 2, 0.0F, false));
        bone14.cubeList.add(new ModelBox(bone14, 38, 42, -1.5F, -17.0194F, 22.1775F, 2, 1, 2, 0.0F, true));
        bone14.cubeList.add(new ModelBox(bone14, 38, 42, -1.0F, -17.0194F, 24.1775F, 1, 1, 2, 0.0F, true));
        bone14.cubeList.add(new ModelBox(bone14, 74, 26, -3.0F, -8.6221F, 19.7039F, 5, 1, 9, 0.0F, true));
        bone14.cubeList.add(new ModelBox(bone14, 74, 26, 1.7071F, -15.3292F, 19.7039F, 1, 7, 9, 0.0F, true));

        bone15 = new ModelRenderer(this);
        bone15.setRotationPoint(0.0F, 0.0F, 0.0F);
        setRotationAngle(bone15, 0.0F, 0.0F, -0.7854F);
        bone14.addChild(bone15);
        bone15.cubeList.add(new ModelBox(bone15, 74, 26, 8.218F, -13.4607F, 19.7039F, 1, 1, 9, 0.0F, true));
        bone15.cubeList.add(new ModelBox(bone15, 74, 26, 11.7536F, -9.9252F, 19.7039F, 1, 1, 9, 0.0F, true));

        bone16 = new ModelRenderer(this);
        bone16.setRotationPoint(0.0F, 7.0F, 0.0F);
        setRotationAngle(bone16, 0.0F, 0.0F, -0.7854F);
        bone14.addChild(bone16);
        bone16.cubeList.add(new ModelBox(bone16, 74, 26, 8.218F, -13.4607F, 19.7039F, 1, 1, 9, 0.0F, true));
        bone16.cubeList.add(new ModelBox(bone16, 74, 26, 11.7536F, -9.9252F, 19.7039F, 1, 1, 9, 0.0F, true));

        bone23 = new ModelRenderer(this);
        bone23.setRotationPoint(0.5F, 7.5F, 10.2F);
        bone.addChild(bone23);
        bone23.cubeList.add(new ModelBox(bone23, 73, 17, -3.7071F, -15.3292F, 19.7039F, 1, 5, 20, 0.0F, true));
        bone23.cubeList.add(new ModelBox(bone23, 73, 17, -3.7071F, -10.3292F, 36.7039F, 1, 4, 3, 0.0F, true));
        bone23.cubeList.add(new ModelBox(bone23, 74, 26, -3.0F, -16.0363F, 19.7039F, 5, 1, 20, 0.0F, true));
        bone23.cubeList.add(new ModelBox(bone23, 74, 26, -3.0F, -15.1363F, 37.7239F, 5, 9, 2, 0.0F, true));
        bone23.cubeList.add(new ModelBox(bone23, 74, 26, 1.7071F, -15.3292F, 19.7039F, 1, 5, 20, 0.0F, true));
        bone23.cubeList.add(new ModelBox(bone23, 74, 26, 1.7071F, -10.3292F, 36.7039F, 1, 4, 3, 0.0F, true));

        bone24 = new ModelRenderer(this);
        bone24.setRotationPoint(0.0F, 0.0F, 0.0F);
        setRotationAngle(bone24, 0.0F, 0.0F, -0.7854F);
        bone23.addChild(bone24);
        bone24.cubeList.add(new ModelBox(bone24, 74, 26, 8.218F, -13.4607F, 19.7039F, 1, 1, 20, 0.0F, true));
        bone24.cubeList.add(new ModelBox(bone24, 74, 26, 11.7536F, -9.9252F, 19.7039F, 1, 1, 20, 0.0F, true));

        bone25 = new ModelRenderer(this);
        bone25.setRotationPoint(0.0F, 7.0F, 0.0F);
        setRotationAngle(bone25, 0.0F, 0.0F, -0.7854F);
        bone23.addChild(bone25);

        bone17 = new ModelRenderer(this);
        bone17.setRotationPoint(0.5F, -1.3F, -11.9F);
        setRotationAngle(bone17, -0.0873F, 0.0F, 0.0F);
        bone.addChild(bone17);
        bone17.cubeList.add(new ModelBox(bone17, 73, 17, -3.7071F, -7.2183F, 41.7142F, 1, 4, 19, 0.0F, true));
        bone17.cubeList.add(new ModelBox(bone17, 74, 26, -3.0F, -3.5112F, 41.7142F, 5, 1, 19, 0.0F, true));
        bone17.cubeList.add(new ModelBox(bone17, 74, 26, 1.7071F, -7.2183F, 41.7142F, 1, 4, 19, 0.0F, true));

        bone18 = new ModelRenderer(this);
        bone18.setRotationPoint(0.0F, 0.0F, 0.0F);
        setRotationAngle(bone18, 0.0F, 0.0F, -0.7854F);
        bone17.addChild(bone18);

        bone19 = new ModelRenderer(this);
        bone19.setRotationPoint(0.0F, 7.0F, 0.0F);
        setRotationAngle(bone19, 0.0F, 0.0F, -0.7854F);
        bone17.addChild(bone19);
        bone19.cubeList.add(new ModelBox(bone19, 74, 26, 4.6041F, -9.8467F, 41.7142F, 1, 1, 19, 0.0F, true));
        bone19.cubeList.add(new ModelBox(bone19, 74, 26, 8.1396F, -6.3112F, 41.7142F, 1, 1, 19, 0.0F, true));

        bone20 = new ModelRenderer(this);
        bone20.setRotationPoint(0.5F, -31.2F, 12.1F);
        setRotationAngle(bone20, -1.309F, 0.0F, 0.0F);
        bone.addChild(bone20);
        bone20.cubeList.add(new ModelBox(bone20, 73, 17, -3.2071F, -8.2183F, 29.7142F, 1, 5, 12, 0.0F, true));
        bone20.cubeList.add(new ModelBox(bone20, 74, 26, -2.5F, -8.9254F, 29.7142F, 4, 1, 12, 0.0F, true));
        bone20.cubeList.add(new ModelBox(bone20, 74, 26, -2.5F, -3.5112F, 29.7142F, 4, 1, 12, 0.0F, true));
        bone20.cubeList.add(new ModelBox(bone20, 13, 78, -2.5F, -8.3112F, 40.4142F, 4, 5, 1, 0.0F, true));
        bone20.cubeList.add(new ModelBox(bone20, 74, 26, 1.2071F, -8.2183F, 29.7142F, 1, 5, 12, 0.0F, true));

        bone21 = new ModelRenderer(this);
        bone21.setRotationPoint(0.0F, 0.0F, 0.0F);
        setRotationAngle(bone21, 0.0F, 0.0F, -0.7854F);
        bone20.addChild(bone21);
        bone21.cubeList.add(new ModelBox(bone21, 74, 26, 3.5434F, -8.079F, 29.7142F, 1, 1, 12, 0.0F, true));
        bone21.cubeList.add(new ModelBox(bone21, 74, 26, 6.3718F, -5.2505F, 29.7142F, 1, 1, 12, 0.0F, true));

        bone22 = new ModelRenderer(this);
        bone22.setRotationPoint(0.0F, 7.0F, 0.0F);
        setRotationAngle(bone22, 0.0F, 0.0F, -0.7854F);
        bone20.addChild(bone22);
        bone22.cubeList.add(new ModelBox(bone22, 74, 26, 4.9576F, -9.4932F, 29.7142F, 1, 1, 12, 0.0F, true));
        bone22.cubeList.add(new ModelBox(bone22, 74, 26, 7.7861F, -6.6647F, 29.7142F, 1, 1, 12, 0.0F, true));

        bone6 = new ModelRenderer(this);
        bone6.setRotationPoint(1.5F, 0.0F, 0.0F);
        setRotationAngle(bone6, 0.0F, 0.0F, -0.6109F);
        bone.addChild(bone6);
        bone6.cubeList.add(new ModelBox(bone6, 32, 66, -0.1147F, -0.8362F, -40.4F, 1, 1, 10, 0.0F, true));
        bone6.cubeList.add(new ModelBox(bone6, 32, 66, -0.1147F, -0.8362F, -30.4F, 1, 1, 10, 0.0F, true));
        bone6.cubeList.add(new ModelBox(bone6, 32, 66, -2.9142F, -3.4966F, -40.4F, 1, 1, 10, 0.0F, true));
        bone6.cubeList.add(new ModelBox(bone6, 32, 66, -2.9142F, -3.4966F, -30.4F, 1, 1, 10, 0.0F, true));

        bone7 = new ModelRenderer(this);
        bone7.setRotationPoint(-1.5F, 0.0F, 0.0F);
        setRotationAngle(bone7, 0.0F, 0.0F, 0.6109F);
        bone.addChild(bone7);
        bone7.cubeList.add(new ModelBox(bone7, 32, 66, -0.8853F, -0.8362F, -40.4F, 1, 1, 10, 0.0F, false));
        bone7.cubeList.add(new ModelBox(bone7, 32, 66, -0.8853F, -0.8362F, -30.4F, 1, 1, 10, 0.0F, false));
        bone7.cubeList.add(new ModelBox(bone7, 32, 66, 1.9142F, -3.4966F, -40.4F, 1, 1, 10, 0.0F, false));
        bone7.cubeList.add(new ModelBox(bone7, 32, 66, 1.9142F, -3.4966F, -30.4F, 1, 1, 10, 0.0F, false));

        bone4 = new ModelRenderer(this);
        bone4.setRotationPoint(1.0F, 0.0F, 0.0F);
        setRotationAngle(bone4, 0.0F, 0.0F, 0.5236F);
        bone.addChild(bone4);
        bone4.cubeList.add(new ModelBox(bone4, 69, 15, -5.4821F, -4.7631F, -33.0F, 1, 1, 22, 0.0F, true));
        bone4.cubeList.add(new ModelBox(bone4, 69, 15, -5.4821F, -4.7631F, -53.0F, 1, 1, 20, 0.0F, true));
        bone4.cubeList.add(new ModelBox(bone4, 69, 15, -2.8821F, -0.2598F, -30.0F, 1, 1, 19, 0.0F, true));
        bone4.cubeList.add(new ModelBox(bone4, 69, 15, -2.8821F, -0.2598F, -41.0F, 1, 1, 11, 0.0F, true));
        bone4.cubeList.add(new ModelBox(bone4, 69, 15, -2.8821F, -0.2598F, -42.6F, 1, 1, 1, 0.0F, true));
        bone4.cubeList.add(new ModelBox(bone4, 69, 15, -3.25F, -6.6292F, -33.0F, 1, 1, 22, 0.0F, true));
        bone4.cubeList.add(new ModelBox(bone4, 69, 15, -3.25F, -6.6292F, -53.0F, 1, 1, 20, 0.0F, true));
        bone4.cubeList.add(new ModelBox(bone4, 69, 15, -0.65F, -2.1258F, -30.0F, 1, 1, 19, 0.0F, true));
        bone4.cubeList.add(new ModelBox(bone4, 69, 15, -0.65F, -2.1258F, -41.0F, 1, 1, 11, 0.0F, true));
        bone4.cubeList.add(new ModelBox(bone4, 69, 15, -1.65F, -2.1258F, -42.6F, 2, 1, 1, 0.0F, true));
        bone4.cubeList.add(new ModelBox(bone4, 69, 15, -7.3481F, -6.9952F, -33.0F, 1, 1, 22, 0.0F, true));
        bone4.cubeList.add(new ModelBox(bone4, 69, 15, -7.3481F, -6.9952F, -53.0F, 1, 1, 20, 0.0F, true));
        bone4.cubeList.add(new ModelBox(bone4, 69, 15, -4.7481F, -2.4919F, -30.0F, 1, 1, 19, 0.0F, true));
        bone4.cubeList.add(new ModelBox(bone4, 69, 15, -4.7481F, -2.4919F, -41.0F, 1, 1, 11, 0.0F, true));
        bone4.cubeList.add(new ModelBox(bone4, 69, 15, -4.7481F, -2.4919F, -42.6F, 2, 1, 1, 0.0F, true));
        bone4.cubeList.add(new ModelBox(bone4, 69, 15, -5.116F, -8.8612F, -33.0F, 1, 1, 22, 0.0F, true));
        bone4.cubeList.add(new ModelBox(bone4, 69, 15, -5.116F, -8.8612F, -53.0F, 1, 1, 20, 0.0F, true));
        bone4.cubeList.add(new ModelBox(bone4, 69, 15, -2.516F, -4.3579F, -30.0F, 1, 1, 19, 0.0F, true));
        bone4.cubeList.add(new ModelBox(bone4, 69, 15, -2.516F, -4.3579F, -41.0F, 1, 1, 11, 0.0F, true));
        bone4.cubeList.add(new ModelBox(bone4, 69, 15, -2.516F, -4.3579F, -42.6F, 1, 1, 1, 0.0F, true));

        bone5 = new ModelRenderer(this);
        bone5.setRotationPoint(-1.0F, 0.0F, 0.0F);
        setRotationAngle(bone5, 0.0F, 0.0F, -0.5236F);
        bone.addChild(bone5);
        bone5.cubeList.add(new ModelBox(bone5, 69, 15, 4.4821F, -4.7631F, -33.0F, 1, 1, 22, 0.0F, false));
        bone5.cubeList.add(new ModelBox(bone5, 69, 15, 4.4821F, -4.7631F, -53.0F, 1, 1, 20, 0.0F, false));
        bone5.cubeList.add(new ModelBox(bone5, 69, 15, 1.8821F, -0.2598F, -30.0F, 1, 1, 19, 0.0F, false));
        bone5.cubeList.add(new ModelBox(bone5, 69, 15, 1.8821F, -0.2598F, -41.0F, 1, 1, 11, 0.0F, false));
        bone5.cubeList.add(new ModelBox(bone5, 69, 15, 1.8821F, -0.2598F, -42.6F, 1, 1, 1, 0.0F, false));
        bone5.cubeList.add(new ModelBox(bone5, 69, 15, 2.25F, -6.6292F, -33.0F, 1, 1, 22, 0.0F, false));
        bone5.cubeList.add(new ModelBox(bone5, 69, 15, 2.25F, -6.6292F, -53.0F, 1, 1, 20, 0.0F, false));
        bone5.cubeList.add(new ModelBox(bone5, 69, 15, -0.35F, -2.1258F, -30.0F, 1, 1, 19, 0.0F, false));
        bone5.cubeList.add(new ModelBox(bone5, 69, 15, -0.35F, -2.1258F, -41.0F, 1, 1, 11, 0.0F, false));
        bone5.cubeList.add(new ModelBox(bone5, 69, 15, -0.35F, -2.1258F, -42.6F, 2, 1, 1, 0.0F, false));
        bone5.cubeList.add(new ModelBox(bone5, 69, 15, 6.3481F, -6.9952F, -33.0F, 1, 1, 22, 0.0F, false));
        bone5.cubeList.add(new ModelBox(bone5, 69, 15, 6.3481F, -6.9952F, -53.0F, 1, 1, 20, 0.0F, false));
        bone5.cubeList.add(new ModelBox(bone5, 69, 15, 3.7481F, -2.4919F, -30.0F, 1, 1, 19, 0.0F, false));
        bone5.cubeList.add(new ModelBox(bone5, 69, 15, 3.7481F, -2.4919F, -41.0F, 1, 1, 11, 0.0F, false));
        bone5.cubeList.add(new ModelBox(bone5, 69, 15, 2.7481F, -2.4919F, -42.6F, 2, 1, 1, 0.0F, false));
        bone5.cubeList.add(new ModelBox(bone5, 69, 15, 4.116F, -8.8612F, -33.0F, 1, 1, 22, 0.0F, false));
        bone5.cubeList.add(new ModelBox(bone5, 69, 15, 4.116F, -8.8612F, -53.0F, 1, 1, 20, 0.0F, false));
        bone5.cubeList.add(new ModelBox(bone5, 69, 15, 1.516F, -4.3579F, -30.0F, 1, 1, 19, 0.0F, false));
        bone5.cubeList.add(new ModelBox(bone5, 69, 15, 1.516F, -4.3579F, -41.0F, 1, 1, 11, 0.0F, false));
        bone5.cubeList.add(new ModelBox(bone5, 69, 15, 1.516F, -4.3579F, -42.6F, 1, 1, 1, 0.0F, false));

        bone3 = new ModelRenderer(this);
        bone3.setRotationPoint(0.5F, 0.0F, 0.0F);
        setRotationAngle(bone3, 0.0F, 0.0F, 0.7854F);
        bone.addChild(bone3);
        bone3.cubeList.add(new ModelBox(bone3, 78, 34, -3.1213F, 1.1213F, -11.0F, 1, 1, 17, 0.0F, true));
        bone3.cubeList.add(new ModelBox(bone3, 78, 34, 0.4142F, -2.4142F, -11.0F, 1, 1, 17, 0.0F, true));
        bone3.cubeList.add(new ModelBox(bone3, 78, 34, -5.9497F, -8.7782F, -11.0F, 1, 1, 17, 0.0F, true));
        bone3.cubeList.add(new ModelBox(bone3, 78, 34, -5.9497F, -8.7782F, 6.0F, 1, 1, 17, 0.0F, true));
        bone3.cubeList.add(new ModelBox(bone3, 78, 34, -1.0F, -3.8284F, 13.0F, 1, 1, 10, 0.0F, true));
        bone3.cubeList.add(new ModelBox(bone3, 78, 34, -9.4853F, -5.2426F, -11.0F, 1, 1, 17, 0.0F, true));
        bone3.cubeList.add(new ModelBox(bone3, 78, 34, -9.4853F, -5.2426F, 6.0F, 1, 1, 17, 0.0F, true));
        bone3.cubeList.add(new ModelBox(bone3, 78, 34, -4.5355F, -0.2929F, 13.0F, 1, 1, 10, 0.0F, true));
        bone3.cubeList.add(new ModelBox(bone3, 78, 34, -3.1213F, 1.1213F, 6.0F, 1, 1, 7, 0.0F, true));
        bone3.cubeList.add(new ModelBox(bone3, 78, 34, 0.4142F, -2.4142F, 6.0F, 1, 1, 7, 0.0F, true));
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}

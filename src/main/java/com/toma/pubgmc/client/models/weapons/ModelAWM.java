package com.toma.pubgmc.client.models.weapons;

import com.toma.pubgmc.animation.ReloadAnimation;
import com.toma.pubgmc.animation.ReloadAnimation.ReloadStyle;
import com.toma.pubgmc.client.models.ModelGun;
import com.toma.pubgmc.client.util.ModelTransformationHelper;
import com.toma.pubgmc.common.capability.IPlayerData;
import com.toma.pubgmc.common.capability.IPlayerData.PlayerDataProvider;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.item.ItemStack;

public class ModelAWM extends ModelGun {

    private final ModelRenderer awm;
    private final ModelRenderer bolt;
    private final ModelRenderer bone;
    private final ModelRenderer bone12;
    private final ModelRenderer bone4;
    private final ModelRenderer bone2;
    private final ModelRenderer bone3;
    private final ModelRenderer rotatingPart;
    private final ModelRenderer _catch;
    private final ModelRenderer bone7;
    private final ModelRenderer bone8;
    private final ModelRenderer bone6;
    private final ModelRenderer rotatingPart3;
    private final ModelRenderer bone5;
    private final ModelRenderer bone10;
    private final ModelRenderer bone33;
    private final ModelRenderer bone28;
    private final ModelRenderer bone30;
    private final ModelRenderer bone31;
    private final ModelRenderer bone32;
    private final ModelRenderer bone11;
    private final ModelRenderer grip;
    private final ModelRenderer bone21;
    private final ModelRenderer bone27;
    private final ModelRenderer bone22;
    private final ModelRenderer bone13;
    private final ModelRenderer bone14;
    private final ModelRenderer bone15;
    private final ModelRenderer bone16;
    private final ModelRenderer bone17;
    private final ModelRenderer bone18;
    private final ModelRenderer bone19;
    private final ModelRenderer bone20;
    private final ModelRenderer bone23;
    private final ModelRenderer bone24;
    private final ModelRenderer bone25;
    private final ModelRenderer bone26;
    private final ModelRenderer magazine;
    private final ModelRenderer bone34;
    private final ModelRenderer bone35;

    @Override
    public String textureName() {
        return "awm";
    }

    @Override
    public void initAnimations() {
        initAimAnimation(-0.557f, 0.255f, 0.2f);
        initAimingAnimationStates(0.255f, 0.17f, 0.119f);
        reloadAnimation = new ReloadAnimation(magazine, ReloadStyle.MAGAZINE).initMovement(DEFAULT_PART_ANIMATION, -90)
                .withSpeed(0.8f);
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
            GlStateManager.pushMatrix(); {
                renderAWM(data.isAiming(), stack);
            }
            GlStateManager.popMatrix();
        }
    }

    private void renderAWM(boolean aim, ItemStack stack) {
        GlStateManager.pushMatrix();
        ModelTransformationHelper.defaultSRTransform();
        GlStateManager.rotate(-90f, 0f, 1f, 0f);
        GlStateManager.scale(0.9, 0.9, 0.9);
        GlStateManager.translate(-7.0, 8.699999, 0.0);

        if (aim && enableADS(stack)) {
            rotateModelForADSRendering();
        }

        awm.render(1f);
        GlStateManager.popMatrix();

        renderSniperSilencer(0, 0, 0, 1f, stack);
        renderRedDot(0, -1.15, -10, 1f, stack);
        renderHolo(0, -0.65, -7, 1f, stack);
        renderScope2X(0, -1, -6, 1f, stack);
        renderScope4X(0, -1, -8, 1f, stack);
        renderScope8X(0, 0, 0, 1f, stack);
        renderScope15X(0, 0, 0, 1f, stack);
    }

    public ModelAWM() {
        textureWidth = 128;
        textureHeight = 128;

        awm = new ModelRenderer(this);
        awm.setRotationPoint(0.0F, 24.0F, 0.0F);
        awm.cubeList.add(new ModelBox(awm, 33, 38, -6.0F, -29.5F, -3.5F, 8, 5, 7, 0.0F, true));
        awm.cubeList.add(new ModelBox(awm, 33, 40, 4.0F, -24.5F, -3.5F, 8, 4, 7, 0.0F, true));
        awm.cubeList.add(new ModelBox(awm, 33, 47, -4.0F, -24.5F, -3.5F, 8, 4, 7, 0.0F, true));
        awm.cubeList.add(new ModelBox(awm, 33, 35, -12.0F, -24.5F, -3.5F, 8, 4, 7, 0.0F, true));
        awm.cubeList.add(new ModelBox(awm, 34, 42, -20.0F, -24.5F, -3.5F, 8, 4, 7, 0.0F, true));
        awm.cubeList.add(new ModelBox(awm, 36, 40, -25.0F, -24.5F, -3.5F, 5, 4, 7, 0.0F, true));
        awm.cubeList.add(new ModelBox(awm, 32, 38, 12.0F, -24.5F, -3.5F, 9, 4, 7, 0.0F, true));
        awm.cubeList.add(new ModelBox(awm, 32, 43, 4.0F, -20.5F, -3.5F, 6, 3, 7, 0.0F, true));
        awm.cubeList.add(new ModelBox(awm, 32, 43, -1.0F, -20.5F, -3.5F, 5, 3, 7, 0.0F, true));
        awm.cubeList.add(new ModelBox(awm, 43, 14, 0.0F, -17.5F, -1.5F, 1, 3, 3, 0.0F, true));
        awm.cubeList.add(new ModelBox(awm, 43, 14, 0.7071F, -14.7929F, -1.5F, 6, 1, 3, 0.0F, true));
        awm.cubeList.add(new ModelBox(awm, 34, 43, -10.0F, -20.5F, -3.5F, 9, 3, 1, 0.0F, true));
        awm.cubeList.add(new ModelBox(awm, 35, 42, -10.0F, -20.5F, 2.5F, 9, 3, 1, 0.0F, false));
        awm.cubeList.add(new ModelBox(awm, 34, 37, 11.0F, -20.5F, -3.5F, 8, 1, 7, 0.0F, false));
        awm.cubeList.add(new ModelBox(awm, 34, 40, 12.0F, -19.5F, -3.5F, 6, 1, 7, 0.0F, true));
        awm.cubeList.add(new ModelBox(awm, 35, 32, 12.7F, -18.5F, -3.5F, 4, 1, 7, 0.0F, false));
        awm.cubeList.add(new ModelBox(awm, 34, 41, 13.2F, -17.5F, -3.5F, 2, 1, 7, 0.0F, true));
        awm.cubeList.add(new ModelBox(awm, 34, 41, 32.0F, -26.5F, -3.5F, 8, 6, 7, 0.0F, false));
        awm.cubeList.add(new ModelBox(awm, 27, 39, 21.0F, -26.5F, -3.5F, 11, 6, 7, 0.0F, false));
        awm.cubeList.add(new ModelBox(awm, 32, 38, 31.0F, -20.5F, -3.5F, 9, 14, 7, 0.0F, false));
        awm.cubeList.add(new ModelBox(awm, 32, 33, 40.0F, -26.5F, -3.5F, 9, 20, 7, 0.0F, false));
        awm.cubeList.add(new ModelBox(awm, 0, 65, 49.0F, -27.5F, -3.5F, 2, 23, 7, 0.0F, true));
        awm.cubeList.add(new ModelBox(awm, 35, 36, 25.0F, -8.5F, -3.5F, 6, 4, 7, 0.0F, false));
        awm.cubeList.add(new ModelBox(awm, 35, 41, 19.0F, -8.5F, -3.5F, 6, 4, 7, 0.0F, false));
        awm.cubeList.add(new ModelBox(awm, 32, 40, 12.0F, -25.0F, -3.5F, 9, 1, 7, 0.0F, false));
        awm.cubeList.add(new ModelBox(awm, 32, 40, 5.0F, -25.0F, -3.5F, 7, 1, 7, 0.0F, false));
        awm.cubeList.add(new ModelBox(awm, 32, 40, 2.0F, -25.0F, -3.5F, 3, 1, 7, 0.0F, false));
        awm.cubeList.add(new ModelBox(awm, 39, 39, -20.0F, -29.5F, -3.5F, 5, 5, 7, 0.0F, true));
        awm.cubeList.add(new ModelBox(awm, 39, 39, -25.0F, -29.5F, -3.5F, 5, 5, 7, 0.0F, true));
        awm.cubeList.add(new ModelBox(awm, 32, 34, -34.0F, -26.5F, -3.5F, 9, 4, 7, 0.0F, true));
        awm.cubeList.add(new ModelBox(awm, 32, 41, -43.0F, -26.5F, -3.5F, 9, 4, 7, 0.0F, true));
        awm.cubeList.add(new ModelBox(awm, 32, 37, -47.0F, -26.5F, -3.5F, 4, 4, 7, 0.0F, true));
        awm.cubeList.add(new ModelBox(awm, 34, 40, -54.0F, -26.5F, -3.5F, 7, 3, 7, 0.0F, false));
        awm.cubeList.add(new ModelBox(awm, 64, 44, -56.0F, -25.0F, -1.0F, 31, 1, 2, 0.0F, false));
        awm.cubeList.add(new ModelBox(awm, 64, 44, -87.0F, -25.0F, -1.0F, 31, 1, 2, 0.0F, false));
        awm.cubeList.add(new ModelBox(awm, 64, 44, -105.0F, -25.0F, -1.0F, 18, 1, 2, 0.0F, false));
        awm.cubeList.add(new ModelBox(awm, 6, 74, -77.0F, -27.9F, -1.5F, 1, 3, 3, 0.0F, false));
        awm.cubeList.add(new ModelBox(awm, 64, 44, -56.0F, -28.8284F, -1.0F, 31, 1, 2, 0.0F, false));
        awm.cubeList.add(new ModelBox(awm, 64, 44, -87.0F, -28.8284F, -1.0F, 31, 1, 2, 0.0F, false));
        awm.cubeList.add(new ModelBox(awm, 64, 44, -105.0F, -28.8284F, -1.0F, 18, 1, 2, 0.0F, false));
        awm.cubeList.add(new ModelBox(awm, 64, 44, -56.0F, -27.4142F, 1.4142F, 31, 2, 1, 0.0F, false));
        awm.cubeList.add(new ModelBox(awm, 64, 44, -87.0F, -27.4142F, 1.4142F, 31, 2, 1, 0.0F, false));
        awm.cubeList.add(new ModelBox(awm, 64, 44, -105.0F, -27.4142F, 1.4142F, 18, 2, 1, 0.0F, false));
        awm.cubeList.add(new ModelBox(awm, 64, 44, -56.0F, -27.4142F, -2.4142F, 31, 2, 1, 0.0F, false));
        awm.cubeList.add(new ModelBox(awm, 64, 44, -87.0F, -27.4142F, -2.4142F, 31, 2, 1, 0.0F, false));
        awm.cubeList.add(new ModelBox(awm, 64, 44, -105.0F, -27.4142F, -2.4142F, 18, 2, 1, 0.0F, false));
        awm.cubeList.add(new ModelBox(awm, 35, 38, -15.0F, -29.5F, -3.5F, 9, 5, 4, 0.0F, true));
        awm.cubeList.add(new ModelBox(awm, 32, 46, -11.0F, -30.2071F, -2.7929F, 13, 1, 3, 0.0F, false));
        awm.cubeList.add(new ModelBox(awm, 6, 77, 0.0F, -32.0071F, -1.5F, 1, 1, 3, 0.0F, true));
        awm.cubeList.add(new ModelBox(awm, 6, 77, -2.0F, -32.0071F, -1.5F, 1, 1, 3, 0.0F, true));
        awm.cubeList.add(new ModelBox(awm, 6, 77, -4.0F, -32.0071F, -1.5F, 1, 1, 3, 0.0F, true));
        awm.cubeList.add(new ModelBox(awm, 6, 76, -6.0F, -32.0071F, -1.5F, 1, 1, 3, 0.0F, true));
        awm.cubeList.add(new ModelBox(awm, 6, 76, -8.0F, -32.0071F, -1.5F, 1, 1, 3, 0.0F, true));
        awm.cubeList.add(new ModelBox(awm, 6, 77, -10.0F, -32.0071F, -1.5F, 1, 1, 3, 0.0F, true));
        awm.cubeList.add(new ModelBox(awm, 6, 76, -12.0F, -32.0071F, -1.5F, 1, 1, 3, 0.0F, true));
        awm.cubeList.add(new ModelBox(awm, 6, 76, -14.0F, -32.0071F, -1.5F, 1, 1, 3, 0.0F, true));
        awm.cubeList.add(new ModelBox(awm, 6, 76, -16.0F, -32.0071F, -1.5F, 1, 1, 3, 0.0F, true));
        awm.cubeList.add(new ModelBox(awm, 6, 77, -18.0F, -32.0071F, -1.5F, 1, 1, 3, 0.0F, true));
        awm.cubeList.add(new ModelBox(awm, 5, 77, -20.0F, -32.0071F, -1.5F, 1, 1, 3, 0.0F, true));
        awm.cubeList.add(new ModelBox(awm, 5, 77, -22.0F, -32.0071F, -1.5F, 1, 1, 3, 0.0F, true));
        awm.cubeList.add(new ModelBox(awm, 5, 77, -24.0F, -32.0071F, -1.5F, 1, 1, 3, 0.0F, true));
        awm.cubeList.add(new ModelBox(awm, 5, 77, -23.0F, -31.5071F, -2.366F, 1, 0, 3, 0.0F, true));
        awm.cubeList.add(new ModelBox(awm, 5, 77, -19.0F, -31.5071F, -2.366F, 1, 0, 3, 0.0F, true));
        awm.cubeList.add(new ModelBox(awm, 5, 77, -15.0F, -31.5071F, -2.366F, 1, 0, 3, 0.0F, true));
        awm.cubeList.add(new ModelBox(awm, 6, 76, -11.0F, -31.5071F, -2.366F, 1, 0, 3, 0.0F, true));
        awm.cubeList.add(new ModelBox(awm, 6, 76, -7.0F, -31.5071F, -2.366F, 1, 0, 3, 0.0F, true));
        awm.cubeList.add(new ModelBox(awm, 8, 77, -3.0F, -31.5071F, -2.366F, 1, 0, 3, 0.0F, true));
        awm.cubeList.add(new ModelBox(awm, 5, 77, -21.0F, -31.5071F, -2.366F, 1, 0, 3, 0.0F, true));
        awm.cubeList.add(new ModelBox(awm, 5, 77, -17.0F, -31.5071F, -2.366F, 1, 0, 3, 0.0F, true));
        awm.cubeList.add(new ModelBox(awm, 6, 76, -13.0F, -31.5071F, -2.366F, 1, 0, 3, 0.0F, true));
        awm.cubeList.add(new ModelBox(awm, 6, 76, -9.0F, -31.5071F, -2.366F, 1, 0, 3, 0.0F, true));
        awm.cubeList.add(new ModelBox(awm, 0, 72, -5.0F, -31.5071F, -2.366F, 1, 0, 3, 0.0F, true));
        awm.cubeList.add(new ModelBox(awm, 6, 77, -1.0F, -31.5071F, -2.366F, 1, 0, 3, 0.0F, true));
        awm.cubeList.add(new ModelBox(awm, 5, 77, -23.0F, -31.5071F, 0.366F, 1, 0, 2, 0.0F, false));
        awm.cubeList.add(new ModelBox(awm, 5, 77, -19.0F, -31.5071F, 0.366F, 1, 0, 2, 0.0F, false));
        awm.cubeList.add(new ModelBox(awm, 5, 77, -15.0F, -31.5071F, 0.366F, 1, 0, 2, 0.0F, false));
        awm.cubeList.add(new ModelBox(awm, 6, 76, -11.0F, -31.5071F, 0.366F, 1, 0, 2, 0.0F, false));
        awm.cubeList.add(new ModelBox(awm, 6, 76, -7.0F, -31.5071F, 0.366F, 1, 0, 2, 0.0F, false));
        awm.cubeList.add(new ModelBox(awm, 6, 76, -3.0F, -31.5071F, 0.366F, 1, 0, 2, 0.0F, false));
        awm.cubeList.add(new ModelBox(awm, 5, 77, -21.0F, -31.5071F, 0.366F, 1, 0, 2, 0.0F, false));
        awm.cubeList.add(new ModelBox(awm, 5, 77, -17.0F, -31.5071F, 0.366F, 1, 0, 2, 0.0F, false));
        awm.cubeList.add(new ModelBox(awm, 6, 76, -13.0F, -31.5071F, 0.366F, 1, 0, 2, 0.0F, false));
        awm.cubeList.add(new ModelBox(awm, 6, 77, -9.0F, -31.5071F, 0.366F, 1, 0, 2, 0.0F, false));
        awm.cubeList.add(new ModelBox(awm, 6, 77, -5.0F, -31.5071F, 0.366F, 1, 0, 2, 0.0F, false));
        awm.cubeList.add(new ModelBox(awm, 6, 76, -1.0F, -31.5071F, 0.366F, 1, 0, 2, 0.0F, false));
        awm.cubeList.add(new ModelBox(awm, 2, 79, -8.0F, -31.0071F, -2.0F, 9, 1, 4, 0.0F, true));
        awm.cubeList.add(new ModelBox(awm, 0, 71, -16.0F, -31.0071F, -2.0F, 8, 1, 4, 0.0F, true));
        awm.cubeList.add(new ModelBox(awm, 0, 67, -24.0F, -31.0071F, -2.0F, 8, 1, 4, 0.0F, true));
        awm.cubeList.add(new ModelBox(awm, 30, 45, -25.0F, -30.2071F, -2.7929F, 14, 1, 3, 0.0F, true));
        awm.cubeList.add(new ModelBox(awm, 32, 40, -11.0F, -30.2071F, -0.2071F, 13, 1, 3, 0.0F, false));
        awm.cubeList.add(new ModelBox(awm, 30, 46, -25.0F, -30.2071F, -0.2071F, 14, 1, 3, 0.0F, true));
        awm.cubeList.add(new ModelBox(awm, 38, 54, -15.0F, -29.5F, 0.5F, 9, 1, 3, 0.0F, true));
        awm.cubeList.add(new ModelBox(awm, 37, 45, -15.0F, -25.5F, 0.5F, 9, 1, 3, 0.0F, true));

        bolt = new ModelRenderer(this);
        bolt.setRotationPoint(0.0F, 0.0F, 0.0F);
        awm.addChild(bolt);

        bone = new ModelRenderer(this);
        bone.setRotationPoint(0.0F, 0.0F, 0.0F);
        bolt.addChild(bone);
        bone.cubeList.add(new ModelBox(bone, 36, 11, 2.0F, -29.0F, -3.0F, 6, 4, 6, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 0, 0, -6.0F, -28.8F, -1.5F, 8, 3, 3, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 36, 109, -15.001F, -28.5F, 2.0F, 9, 3, 1, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 38, 10, 9.0F, -28.0F, 2.0F, 2, 3, 1, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 38, 10, 8.0F, -28.0F, -3.0F, 3, 3, 1, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 46, 5, 8.0F, -29.0F, -3.0F, 3, 1, 6, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 46, 12, 11.0F, -29.0F, -2.0F, 1, 4, 4, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 75, 58, 11.1914F, -28.0F, -0.5F, 1, 2, 1, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 34, 12, 2.0F, -30.0F, -2.0F, 9, 1, 4, 0.0F, false));

        bone12 = new ModelRenderer(this);
        bone12.setRotationPoint(-12.5F, -4.5F, 3.5F);
        setRotationAngle(bone12, 0.0F, 0.2618F, 0.0F);
        bone.addChild(bone12);
        bone12.cubeList.add(new ModelBox(bone12, 45, 111, -1.1416F, -23.5F, -1.4514F, 3, 2, 1, 0.0F, false));

        bone4 = new ModelRenderer(this);
        bone4.setRotationPoint(11.5F, -6.5F, 0.0F);
        setRotationAngle(bone4, 0.0F, 0.0F, -0.7854F);
        bone.addChild(bone4);
        bone4.cubeList.add(new ModelBox(bone4, 42, 15, 15.2635F, -16.9706F, -2.0F, 1, 1, 4, 0.0F, false));
        bone4.cubeList.add(new ModelBox(bone4, 42, 15, 15.2635F, -16.5563F, -2.0F, 1, 1, 4, 0.0F, false));

        bone2 = new ModelRenderer(this);
        bone2.setRotationPoint(11.5F, -4.0F, -2.5F);
        setRotationAngle(bone2, 0.0F, 0.7854F, 0.0F);
        bone.addChild(bone2);
        bone2.cubeList.add(new ModelBox(bone2, 42, 15, -1.0F, -25.0F, -0.2929F, 1, 4, 1, 0.0F, false));
        bone2.cubeList.add(new ModelBox(bone2, 42, 15, -3.8284F, -25.0F, 2.5355F, 1, 4, 1, 0.0F, false));
        bone2.cubeList.add(new ModelBox(bone2, 42, 15, -1.0F, -25.0F, -0.7071F, 1, 4, 1, 0.0F, false));
        bone2.cubeList.add(new ModelBox(bone2, 42, 15, -4.2426F, -25.0F, 2.5355F, 1, 4, 1, 0.0F, false));

        bone3 = new ModelRenderer(this);
        bone3.setRotationPoint(6.5F, -6.5F, -2.5F);
        setRotationAngle(bone3, -0.7854F, 0.0F, 0.0F);
        bone.addChild(bone3);
        bone3.cubeList.add(new ModelBox(bone3, 42, 15, -4.5F, -16.9706F, -16.2635F, 9, 1, 1, 0.0F, false));
        bone3.cubeList.add(new ModelBox(bone3, 42, 15, -4.5F, -16.5563F, -16.2635F, 9, 1, 1, 0.0F, false));
        bone3.cubeList.add(new ModelBox(bone3, 42, 15, -4.5F, -19.799F, -13.0208F, 9, 1, 1, 0.0F, false));
        bone3.cubeList.add(new ModelBox(bone3, 42, 15, -4.5F, -19.799F, -13.435F, 9, 1, 1, 0.0F, false));

        rotatingPart = new ModelRenderer(this);
        rotatingPart.setRotationPoint(8.5F, -26.8F, 0.0F);
        setRotationAngle(rotatingPart, -0.2793F, 0.0F, 0.0F);
        bolt.addChild(rotatingPart);
        rotatingPart.cubeList.add(new ModelBox(rotatingPart, 15, 76, -0.5F, -0.5344F, 0.0F, 1, 1, 4, 0.0F, false));

		_catch = new ModelRenderer(this);
		_catch.setRotationPoint(0.0F, 21.8743F, 7.1074F);
        rotatingPart.addChild(_catch);
		_catch.cubeList.add(new ModelBox(_catch, 15, 76, -0.5F, -22.4087F, -3.5074F, 1, 1, 1, 0.0F, false));
		_catch.cubeList.add(new ModelBox(_catch, 15, 76, -0.5F, -22.4087F, -2.0932F, 1, 1, 1, 0.0F, false));
		_catch.cubeList.add(new ModelBox(_catch, 15, 76, 0.2071F, -22.4087F, -2.8003F, 1, 1, 1, 0.0F, false));
		_catch.cubeList.add(new ModelBox(_catch, 15, 76, -1.2071F, -22.4087F, -2.8003F, 1, 1, 1, 0.0F, true));
		_catch.cubeList.add(new ModelBox(_catch, 15, 76, -0.5F, -23.1158F, -2.8003F, 1, 1, 1, 0.0F, false));
		_catch.cubeList.add(new ModelBox(_catch, 15, 76, -0.5F, -21.7016F, -2.8003F, 1, 1, 1, 0.0F, false));

        bone7 = new ModelRenderer(this);
        bone7.setRotationPoint(1.5F, 3.6F, 3.6F);
		_catch.addChild(bone7);

        bone8 = new ModelRenderer(this);
        bone8.setRotationPoint(0.0F, 0.0F, 0.0F);
        setRotationAngle(bone8, 0.0F, -0.7854F, 0.0F);
        bone7.addChild(bone8);
        bone8.cubeList.add(new ModelBox(bone8, 15, 76, -5.7328F, -26.0087F, -2.9044F, 1, 1, 1, 0.0F, false));
        bone8.cubeList.add(new ModelBox(bone8, 15, 76, -5.0257F, -26.0087F, -3.6115F, 1, 1, 1, 0.0F, false));
        bone8.cubeList.add(new ModelBox(bone8, 15, 76, -6.4399F, -26.0087F, -3.6115F, 1, 1, 1, 0.0F, true));
        bone8.cubeList.add(new ModelBox(bone8, 15, 76, -5.7328F, -26.0087F, -4.3186F, 1, 1, 1, 0.0F, false));

        bone6 = new ModelRenderer(this);
        bone6.setRotationPoint(0.0F, -0.7415F, 4.8071F);
        setRotationAngle(bone6, 0.0F, 0.0F, -0.7854F);
		_catch.addChild(bone6);
        bone6.cubeList.add(new ModelBox(bone6, 15, 76, 15.1746F, -15.4675F, -7.6074F, 1, 1, 1, 0.0F, false));
        bone6.cubeList.add(new ModelBox(bone6, 15, 76, 14.4675F, -14.7604F, -7.6074F, 1, 1, 1, 0.0F, false));
        bone6.cubeList.add(new ModelBox(bone6, 15, 76, 14.4675F, -16.1746F, -7.6074F, 1, 1, 1, 0.0F, false));
        bone6.cubeList.add(new ModelBox(bone6, 15, 76, 13.7604F, -15.4675F, -7.6074F, 1, 1, 1, 0.0F, true));

        rotatingPart3 = new ModelRenderer(this);
        rotatingPart3.setRotationPoint(0.0F, 21.8743F, 11.7074F);
        rotatingPart.addChild(rotatingPart3);

        bone5 = new ModelRenderer(this);
        bone5.setRotationPoint(0.0F, 0.0F, 0.0F);
        setRotationAngle(bone5, -0.7854F, 0.0F, 0.0F);
        rotatingPart3.addChild(bone5);
        bone5.cubeList.add(new ModelBox(bone5, 15, 76, -0.5F, -11.1125F, -21.5781F, 1, 1, 1, 0.0F, false));
        bone5.cubeList.add(new ModelBox(bone5, 15, 76, -0.5F, -11.8196F, -20.871F, 1, 1, 1, 0.0F, false));
        bone5.cubeList.add(new ModelBox(bone5, 15, 76, -0.5F, -10.4054F, -20.871F, 1, 1, 1, 0.0F, false));
        bone5.cubeList.add(new ModelBox(bone5, 15, 76, -0.5F, -11.1125F, -20.1639F, 1, 1, 1, 0.0F, false));

        bone10 = new ModelRenderer(this);
        bone10.setRotationPoint(-27.5F, -22.5F, 0.0F);
        setRotationAngle(bone10, 0.0F, 0.0F, 0.1047F);
        awm.addChild(bone10);
        bone10.cubeList.add(new ModelBox(bone10, 32, 40, -5.3046F, -0.2723F, -3.5F, 8, 2, 7, 0.0F, true));
        bone10.cubeList.add(new ModelBox(bone10, 32, 40, -13.3046F, -0.2723F, -3.5F, 8, 2, 7, 0.0F, true));
        bone10.cubeList.add(new ModelBox(bone10, 37, 42, -17.3046F, -0.2723F, -3.5F, 4, 2, 7, 0.0F, true));

        bone33 = new ModelRenderer(this);
        bone33.setRotationPoint(0.3F, -13.0F, 0.0F);
        setRotationAngle(bone33, 0.0F, 0.0F, -0.7854F);
        awm.addChild(bone33);
        bone33.cubeList.add(new ModelBox(bone33, 43, 14, 0.8485F, -1.2728F, -1.5F, 1, 1, 3, 0.0F, true));

        bone28 = new ModelRenderer(this);
        bone28.setRotationPoint(-15.5F, -19.0F, 0.0F);
        setRotationAngle(bone28, 0.0F, 0.0F, 0.3491F);
        awm.addChild(bone28);
        bone28.cubeList.add(new ModelBox(bone28, 44, 46, -0.3187F, -3.4716F, -3.5F, 6, 3, 1, 0.0F, true));
        bone28.cubeList.add(new ModelBox(bone28, 37, 42, -3.3187F, -3.4716F, -3.5F, 3, 3, 7, 0.0F, true));
        bone28.cubeList.add(new ModelBox(bone28, 37, 42, -33.7974F, 6.4847F, -3.5F, 3, 1, 7, 0.0F, true));
        bone28.cubeList.add(new ModelBox(bone28, 36, 39, -0.3187F, -3.4716F, 2.5F, 6, 3, 1, 0.0F, false));

        bone30 = new ModelRenderer(this);
        bone30.setRotationPoint(-63.0F, -24.5F, 0.0F);
        setRotationAngle(bone30, -0.7854F, 0.0F, 0.0F);
        awm.addChild(bone30);
        bone30.cubeList.add(new ModelBox(bone30, 64, 44, 7.0F, 0.0607F, -2.3536F, 31, 1, 2, 0.0F, false));
        bone30.cubeList.add(new ModelBox(bone30, 64, 44, -24.0F, 0.0607F, -2.3536F, 31, 1, 2, 0.0F, false));
        bone30.cubeList.add(new ModelBox(bone30, 64, 44, -42.0F, 0.0607F, -2.3536F, 18, 1, 2, 0.0F, false));
        bone30.cubeList.add(new ModelBox(bone30, 64, 44, 7.0F, -2.3536F, 0.0607F, 31, 2, 1, 0.0F, false));
        bone30.cubeList.add(new ModelBox(bone30, 64, 44, -24.0F, -2.3536F, 0.0607F, 31, 2, 1, 0.0F, false));
        bone30.cubeList.add(new ModelBox(bone30, 64, 44, -42.0F, -2.3536F, 0.0607F, 18, 2, 1, 0.0F, false));
        bone30.cubeList.add(new ModelBox(bone30, 64, 44, 7.0F, -3.7678F, -2.3536F, 31, 1, 2, 0.0F, false));
        bone30.cubeList.add(new ModelBox(bone30, 64, 44, -24.0F, -3.7678F, -2.3536F, 31, 1, 2, 0.0F, false));
        bone30.cubeList.add(new ModelBox(bone30, 64, 44, -42.0F, -3.7678F, -2.3536F, 18, 1, 2, 0.0F, false));
        bone30.cubeList.add(new ModelBox(bone30, 64, 44, 7.0F, -2.3536F, -3.7678F, 31, 2, 1, 0.0F, false));
        bone30.cubeList.add(new ModelBox(bone30, 64, 44, -24.0F, -2.3536F, -3.7678F, 31, 2, 1, 0.0F, false));
        bone30.cubeList.add(new ModelBox(bone30, 64, 44, -42.0F, -2.3536F, -3.7678F, 18, 2, 1, 0.0F, false));

        bone31 = new ModelRenderer(this);
        bone31.setRotationPoint(1.5F, -32.7071F, -4.0F);
        setRotationAngle(bone31, -0.5236F, 0.0F, 0.0F);
        awm.addChild(bone31);
        bone31.cubeList.add(new ModelBox(bone31, 6, 77, -1.5F, -2.1438F, 5.1131F, 1, 1, 1, 0.0F, true));
        bone31.cubeList.add(new ModelBox(bone31, 6, 77, -3.5F, -2.1438F, 5.1131F, 1, 1, 1, 0.0F, true));
        bone31.cubeList.add(new ModelBox(bone31, 6, 77, -5.5F, -2.1438F, 5.1131F, 1, 1, 1, 0.0F, true));
        bone31.cubeList.add(new ModelBox(bone31, 6, 77, -7.5F, -2.1438F, 5.1131F, 1, 1, 1, 0.0F, true));
        bone31.cubeList.add(new ModelBox(bone31, 6, 77, -9.5F, -2.1438F, 5.1131F, 1, 1, 1, 0.0F, true));
        bone31.cubeList.add(new ModelBox(bone31, 6, 77, -11.5F, -2.1438F, 5.1131F, 1, 1, 1, 0.0F, true));
        bone31.cubeList.add(new ModelBox(bone31, 6, 77, -13.5F, -2.1438F, 5.1131F, 1, 1, 1, 0.0F, true));
        bone31.cubeList.add(new ModelBox(bone31, 6, 77, -15.5F, -2.1438F, 5.1131F, 1, 1, 1, 0.0F, true));
        bone31.cubeList.add(new ModelBox(bone31, 6, 77, -17.5F, -2.1438F, 5.1131F, 1, 1, 1, 0.0F, true));
        bone31.cubeList.add(new ModelBox(bone31, 5, 77, -19.5F, -2.1438F, 5.1131F, 1, 1, 1, 0.0F, true));
        bone31.cubeList.add(new ModelBox(bone31, 5, 77, -21.5F, -2.1438F, 5.1131F, 1, 1, 1, 0.0F, true));
        bone31.cubeList.add(new ModelBox(bone31, 5, 77, -23.5F, -2.1438F, 5.1131F, 1, 1, 1, 0.0F, true));
        bone31.cubeList.add(new ModelBox(bone31, 5, 77, -25.5F, -2.1438F, 5.1131F, 1, 1, 1, 0.0F, true));
        bone31.cubeList.add(new ModelBox(bone31, 13, 77, -24.5F, -2.1438F, 6.1131F, 1, 1, 0, 0.0F, true));
        bone31.cubeList.add(new ModelBox(bone31, 13, 77, -20.5F, -2.1438F, 6.1131F, 1, 1, 0, 0.0F, true));
        bone31.cubeList.add(new ModelBox(bone31, 6, 76, -16.5F, -2.1438F, 6.1131F, 1, 1, 0, 0.0F, true));
        bone31.cubeList.add(new ModelBox(bone31, 6, 77, -12.5F, -2.1438F, 6.1131F, 1, 1, 0, 0.0F, true));
        bone31.cubeList.add(new ModelBox(bone31, 6, 76, -8.5F, -2.1438F, 6.1131F, 1, 1, 0, 0.0F, true));
        bone31.cubeList.add(new ModelBox(bone31, 6, 76, -4.5F, -2.1438F, 6.1131F, 1, 1, 0, 0.0F, true));
        bone31.cubeList.add(new ModelBox(bone31, 13, 77, -22.5F, -2.1438F, 6.1131F, 1, 1, 0, 0.0F, true));
        bone31.cubeList.add(new ModelBox(bone31, 13, 77, -18.5F, -2.1438F, 6.1131F, 1, 1, 0, 0.0F, true));
        bone31.cubeList.add(new ModelBox(bone31, 6, 76, -14.5F, -2.1438F, 6.1131F, 1, 1, 0, 0.0F, true));
        bone31.cubeList.add(new ModelBox(bone31, 6, 77, -10.5F, -2.1438F, 6.1131F, 1, 1, 0, 0.0F, true));
        bone31.cubeList.add(new ModelBox(bone31, 6, 76, -6.5F, -2.1438F, 6.1131F, 1, 1, 0, 0.0F, true));
        bone31.cubeList.add(new ModelBox(bone31, 6, 77, -2.5F, -2.1438F, 6.1131F, 1, 1, 0, 0.0F, true));

        bone32 = new ModelRenderer(this);
        bone32.setRotationPoint(0.5F, -35.3901F, 1.0131F);
        setRotationAngle(bone32, 0.5236F, 0.0F, 0.0F);
        awm.addChild(bone32);
        bone32.cubeList.add(new ModelBox(bone32, 5, 77, -0.5F, 1.6732F, -4.8679F, 1, 1, 1, 0.0F, true));
        bone32.cubeList.add(new ModelBox(bone32, 5, 77, -2.5F, 1.6732F, -4.8679F, 1, 1, 1, 0.0F, true));
        bone32.cubeList.add(new ModelBox(bone32, 5, 77, -4.5F, 1.6732F, -4.8679F, 1, 1, 1, 0.0F, true));
        bone32.cubeList.add(new ModelBox(bone32, 5, 77, -6.5F, 1.6732F, -4.8679F, 1, 1, 1, 0.0F, true));
        bone32.cubeList.add(new ModelBox(bone32, 5, 77, -8.5F, 1.6732F, -4.8679F, 1, 1, 1, 0.0F, true));
        bone32.cubeList.add(new ModelBox(bone32, 5, 77, -10.5F, 1.6732F, -4.8679F, 1, 1, 1, 0.0F, true));
        bone32.cubeList.add(new ModelBox(bone32, 5, 77, -12.5F, 1.6732F, -4.8679F, 1, 1, 1, 0.0F, true));
        bone32.cubeList.add(new ModelBox(bone32, 5, 77, -14.5F, 1.6732F, -4.8679F, 1, 1, 1, 0.0F, true));
        bone32.cubeList.add(new ModelBox(bone32, 5, 77, -16.5F, 1.6732F, -4.8679F, 1, 1, 1, 0.0F, true));
        bone32.cubeList.add(new ModelBox(bone32, 5, 77, -18.5F, 1.6732F, -4.8679F, 1, 1, 1, 0.0F, true));
        bone32.cubeList.add(new ModelBox(bone32, 5, 77, -20.5F, 1.6732F, -4.8679F, 1, 1, 1, 0.0F, true));
        bone32.cubeList.add(new ModelBox(bone32, 5, 77, -22.5F, 1.6732F, -4.8679F, 1, 1, 1, 0.0F, true));
        bone32.cubeList.add(new ModelBox(bone32, 5, 77, -24.5F, 1.6732F, -4.8679F, 1, 1, 1, 0.0F, true));
        bone32.cubeList.add(new ModelBox(bone32, 5, 77, -23.5F, 1.6732F, -4.8679F, 1, 1, 0, 0.0F, true));
        bone32.cubeList.add(new ModelBox(bone32, 5, 77, -19.5F, 1.6732F, -4.8679F, 1, 1, 0, 0.0F, true));
        bone32.cubeList.add(new ModelBox(bone32, 5, 77, -15.5F, 1.6732F, -4.8679F, 1, 1, 0, 0.0F, true));
        bone32.cubeList.add(new ModelBox(bone32, 5, 77, -11.5F, 1.6732F, -4.8679F, 1, 1, 0, 0.0F, true));
        bone32.cubeList.add(new ModelBox(bone32, 5, 77, -7.5F, 1.6732F, -4.8679F, 1, 1, 0, 0.0F, true));
        bone32.cubeList.add(new ModelBox(bone32, 5, 77, -3.5F, 1.6732F, -4.8679F, 1, 1, 0, 0.0F, true));
        bone32.cubeList.add(new ModelBox(bone32, 5, 77, -21.5F, 1.6732F, -4.8679F, 1, 1, 0, 0.0F, true));
        bone32.cubeList.add(new ModelBox(bone32, 15, 76, -17.5F, 1.6732F, -4.8679F, 1, 1, 0, 0.0F, true));
        bone32.cubeList.add(new ModelBox(bone32, 5, 77, -13.5F, 1.6732F, -4.8679F, 1, 1, 0, 0.0F, true));
        bone32.cubeList.add(new ModelBox(bone32, 5, 77, -9.5F, 1.6732F, -4.8679F, 1, 1, 0, 0.0F, true));
        bone32.cubeList.add(new ModelBox(bone32, 5, 77, -5.5F, 1.6732F, -4.8679F, 1, 1, 0, 0.0F, true));
        bone32.cubeList.add(new ModelBox(bone32, 5, 77, -1.5F, 1.6732F, -4.8679F, 1, 1, 0, 0.0F, true));

        bone11 = new ModelRenderer(this);
        bone11.setRotationPoint(-10.5F, -7.0F, 2.0F);
        setRotationAngle(bone11, -0.7854F, 0.0F, 0.0F);
        awm.addChild(bone11);
        bone11.cubeList.add(new ModelBox(bone11, 32, 45, -0.5F, -16.9706F, -15.8492F, 13, 1, 1, 0.0F, true));
        bone11.cubeList.add(new ModelBox(bone11, 32, 45, -0.5F, -13.0208F, -19.799F, 13, 1, 1, 0.0F, true));
        bone11.cubeList.add(new ModelBox(bone11, 32, 45, -14.5F, -16.9706F, -15.8492F, 14, 1, 1, 0.0F, true));
        bone11.cubeList.add(new ModelBox(bone11, 32, 45, -14.5F, -13.0208F, -19.799F, 14, 1, 1, 0.0F, true));

        grip = new ModelRenderer(this);
        grip.setRotationPoint(7.0F, 0.0F, 0.0F);
        awm.addChild(grip);

        bone21 = new ModelRenderer(this);
        bone21.setRotationPoint(4.0F, -11.6964F, 0.0F);
        setRotationAngle(bone21, 0.0F, 0.0F, -2.0944F);
        grip.addChild(bone21);
        bone21.cubeList.add(new ModelBox(bone21, 32, 41, -9.645F, -2.0146F, -3.5F, 9, 7, 7, 0.0F, true));
        bone21.cubeList.add(new ModelBox(bone21, 32, 39, -0.645F, -2.0146F, -3.5F, 9, 7, 7, 0.0F, true));
        bone21.cubeList.add(new ModelBox(bone21, 36, 41, -7.645F, 4.6925F, -0.2071F, 9, 1, 3, 0.0F, false));
        bone21.cubeList.add(new ModelBox(bone21, 0, 5, -9.645F, -2.7217F, -0.2071F, 18, 1, 3, 0.0F, false));
        bone21.cubeList.add(new ModelBox(bone21, 36, 41, -7.645F, 4.6925F, -2.7929F, 9, 1, 3, 0.0F, false));
        bone21.cubeList.add(new ModelBox(bone21, 0, 5, -9.645F, -2.7217F, -2.7929F, 18, 1, 3, 0.0F, false));

        bone27 = new ModelRenderer(this);
        bone27.setRotationPoint(-4.0F, 11.6964F, -0.0F);
        setRotationAngle(bone27, 0.0F, 0.0F, 0.1745F);
        bone21.addChild(bone27);
        bone27.cubeList.add(new ModelBox(bone27, 43, 39, -7.9401F, -12.5225F, -3.5F, 2, 7, 7, 0.0F, false));

        bone22 = new ModelRenderer(this);
        bone22.setRotationPoint(0.0F, -3.3036F, 0.0F);
        setRotationAngle(bone22, 0.7854F, 0.0F, 0.0F);
        bone21.addChild(bone22);
        bone22.cubeList.add(new ModelBox(bone22, 33, 41, -7.645F, 7.3361F, -4.3863F, 14, 1, 1, 0.0F, false));
        bone22.cubeList.add(new ModelBox(bone22, 35, 42, -9.645F, 2.3863F, 0.5634F, 12, 1, 1, 0.0F, false));
        bone22.cubeList.add(new ModelBox(bone22, 35, 42, -9.645F, -1.5634F, -3.3863F, 12, 1, 1, 0.0F, false));
        bone22.cubeList.add(new ModelBox(bone22, 35, 42, 2.355F, 2.3863F, 0.5634F, 6, 1, 1, 0.0F, false));
        bone22.cubeList.add(new ModelBox(bone22, 35, 42, 2.355F, -1.5634F, -3.3863F, 6, 1, 1, 0.0F, false));
        bone22.cubeList.add(new ModelBox(bone22, 32, 43, -7.645F, 3.3863F, -8.3361F, 14, 1, 1, 0.0F, false));

        bone13 = new ModelRenderer(this);
        bone13.setRotationPoint(12.0F, -2.6964F, 0.0F);
        setRotationAngle(bone13, 0.0F, 0.0F, -2.8798F);
        grip.addChild(bone13);
        bone13.cubeList.add(new ModelBox(bone13, 34, 45, -3.4666F, 4.3832F, -3.5F, 7, 2, 7, 0.0F, false));
        bone13.cubeList.add(new ModelBox(bone13, 37, 40, -3.4666F, 6.0903F, -0.2071F, 7, 1, 3, 0.0F, false));
        bone13.cubeList.add(new ModelBox(bone13, 37, 46, -3.4666F, 6.0903F, -2.7929F, 7, 1, 3, 0.0F, false));

        bone14 = new ModelRenderer(this);
        bone14.setRotationPoint(0.0F, -3.3036F, 0.0F);
        setRotationAngle(bone14, 0.7854F, 0.0F, 0.0F);
        bone13.addChild(bone14);
        bone14.cubeList.add(new ModelBox(bone14, 39, 45, -3.4666F, 8.3244F, -5.3747F, 7, 1, 1, 0.0F, false));
        bone14.cubeList.add(new ModelBox(bone14, 42, 36, -3.4666F, 4.3747F, -9.3244F, 7, 1, 1, 0.0F, false));

        bone15 = new ModelRenderer(this);
        bone15.setRotationPoint(20.3F, -3.1964F, 0.0F);
        setRotationAngle(bone15, 0.0F, 0.0F, 2.618F);
        grip.addChild(bone15);
        bone15.cubeList.add(new ModelBox(bone15, 33, 41, -5.7431F, 3.0835F, -3.5F, 7, 3, 7, 0.0F, false));
        bone15.cubeList.add(new ModelBox(bone15, 34, 37, -10.8561F, -0.7211F, -3.5F, 7, 6, 7, 0.0F, false));
        bone15.cubeList.add(new ModelBox(bone15, 39, 37, -5.7431F, 5.7906F, -0.2071F, 7, 1, 3, 0.0F, false));
        bone15.cubeList.add(new ModelBox(bone15, 38, 39, -5.7431F, 5.7906F, -2.7929F, 7, 1, 3, 0.0F, false));

        bone16 = new ModelRenderer(this);
        bone16.setRotationPoint(0.0F, -3.3036F, 0.0F);
        setRotationAngle(bone16, 0.7854F, 0.0F, 0.0F);
        bone15.addChild(bone16);
        bone16.cubeList.add(new ModelBox(bone16, 39, 46, -5.7431F, 8.1125F, -5.1628F, 7, 1, 1, 0.0F, false));
        bone16.cubeList.add(new ModelBox(bone16, 37, 44, -5.7431F, 4.1628F, -9.1125F, 7, 1, 1, 0.0F, false));

        bone17 = new ModelRenderer(this);
        bone17.setRotationPoint(25.8F, -8.9964F, 0.0F);
        setRotationAngle(bone17, 0.0F, 0.0F, 1.8326F);
        grip.addChild(bone17);
        bone17.cubeList.add(new ModelBox(bone17, 33, 37, -7.1409F, 2.2619F, -3.5F, 7, 2, 7, 0.0F, false));
        bone17.cubeList.add(new ModelBox(bone17, 39, 40, -7.1409F, 3.969F, -0.2071F, 7, 1, 3, 0.0F, false));
        bone17.cubeList.add(new ModelBox(bone17, 40, 43, -13.8531F, 14.9282F, -3.5F, 2, 1, 7, 0.0F, true));
        bone17.cubeList.add(new ModelBox(bone17, 38, 37, -7.1409F, 3.969F, -2.7929F, 7, 1, 3, 0.0F, false));

        bone18 = new ModelRenderer(this);
        bone18.setRotationPoint(0.0F, -3.3036F, 0.0F);
        setRotationAngle(bone18, 0.7854F, 0.0F, 0.0F);
        bone17.addChild(bone18);
        bone18.cubeList.add(new ModelBox(bone18, 37, 41, -7.1409F, 6.8244F, -3.8747F, 7, 1, 1, 0.0F, false));
        bone18.cubeList.add(new ModelBox(bone18, 43, 49, -7.1409F, 2.8747F, -7.8244F, 7, 1, 1, 0.0F, false));

        bone19 = new ModelRenderer(this);
        bone19.setRotationPoint(24.3F, -16.9964F, 0.0F);
        setRotationAngle(bone19, 0.0F, 0.0F, 0.7854F);
        grip.addChild(bone19);
        bone19.cubeList.add(new ModelBox(bone19, 38, 38, -4.5644F, -2.6359F, -3.5F, 4, 2, 7, 0.0F, false));
        bone19.cubeList.add(new ModelBox(bone19, 34, 39, -6.3644F, -0.6359F, -3.5F, 7, 2, 7, 0.0F, false));
        bone19.cubeList.add(new ModelBox(bone19, 38, 38, -6.3644F, 1.0712F, -0.2071F, 7, 1, 3, 0.0F, false));
        bone19.cubeList.add(new ModelBox(bone19, 37, 40, -6.3644F, 1.0712F, -2.7929F, 7, 1, 3, 0.0F, false));

        bone20 = new ModelRenderer(this);
        bone20.setRotationPoint(0.0F, -3.3036F, 0.0F);
        setRotationAngle(bone20, 0.7854F, 0.0F, 0.0F);
        bone19.addChild(bone20);
        bone20.cubeList.add(new ModelBox(bone20, 38, 44, -6.3644F, 4.7754F, -1.8257F, 7, 1, 1, 0.0F, false));
        bone20.cubeList.add(new ModelBox(bone20, 39, 42, -6.3644F, 0.8257F, -5.7754F, 7, 1, 1, 0.0F, false));

        bone23 = new ModelRenderer(this);
        bone23.setRotationPoint(16.7F, -20.3964F, 0.0F);
        setRotationAngle(bone23, 0.0F, 0.0F, -0.0873F);
        grip.addChild(bone23);
        bone23.cubeList.add(new ModelBox(bone23, 34, 40, -4.9816F, -0.5032F, -3.5F, 8, 1, 7, 0.0F, false));
        bone23.cubeList.add(new ModelBox(bone23, 37, 46, -4.9816F, 0.2039F, -0.2071F, 8, 1, 3, 0.0F, false));
        bone23.cubeList.add(new ModelBox(bone23, 35, 42, -4.9816F, 0.2039F, -2.7929F, 8, 1, 3, 0.0F, false));

        bone24 = new ModelRenderer(this);
        bone24.setRotationPoint(0.0F, -3.3036F, 0.0F);
        setRotationAngle(bone24, 0.7854F, 0.0F, 0.0F);
        bone23.addChild(bone24);
        bone24.cubeList.add(new ModelBox(bone24, 39, 44, -4.9816F, 4.1622F, -1.2124F, 8, 1, 1, 0.0F, false));
        bone24.cubeList.add(new ModelBox(bone24, 42, 38, -4.9816F, 0.2124F, -5.1622F, 8, 1, 1, 0.0F, false));

        bone25 = new ModelRenderer(this);
        bone25.setRotationPoint(9.1F, -18.1964F, 0.0F);
        setRotationAngle(bone25, 0.0F, 0.0F, -0.6109F);
        grip.addChild(bone25);
        bone25.cubeList.add(new ModelBox(bone25, 34, 44, -2.8223F, 0.028F, -3.5F, 7, 1, 7, 0.0F, true));
        bone25.cubeList.add(new ModelBox(bone25, 39, 40, -3.5223F, 0.7351F, -0.2071F, 8, 1, 3, 0.0F, false));
        bone25.cubeList.add(new ModelBox(bone25, 34, 37, -3.5223F, 0.7351F, -2.7929F, 8, 1, 3, 0.0F, false));

        bone26 = new ModelRenderer(this);
        bone26.setRotationPoint(0.0F, -3.3036F, 0.0F);
        setRotationAngle(bone26, 0.7854F, 0.0F, 0.0F);
        bone25.addChild(bone26);
        bone26.cubeList.add(new ModelBox(bone26, 41, 40, -3.5223F, 4.5377F, -1.588F, 8, 1, 1, 0.0F, false));
        bone26.cubeList.add(new ModelBox(bone26, 38, 44, -3.5223F, 0.588F, -5.5377F, 8, 1, 1, 0.0F, false));

        magazine = new ModelRenderer(this);
        magazine.setRotationPoint(-6.6929F, -15.3929F, 0.0F);
        setRotationAngle(magazine, 0.0F, 0.0F, 0.0873F);
        awm.addChild(magazine);
        magazine.cubeList.add(new ModelBox(magazine, 103, 36, 0.0929F, 4.6929F, -2.5F, 5, 1, 5, 0.0F, true));
        magazine.cubeList.add(new ModelBox(magazine, 81, 12, -2.9071F, 3.6929F, -2.5F, 8, 1, 5, 0.0F, true));
        magazine.cubeList.add(new ModelBox(magazine, 74, 40, -5.9071F, 2.6929F, -2.5F, 11, 1, 5, 0.0F, true));
        magazine.cubeList.add(new ModelBox(magazine, 80, 22, -8.9071F, -3.3071F, -2.5F, 14, 6, 5, 0.0F, true));
        magazine.cubeList.add(new ModelBox(magazine, 78, 30, -8.9071F, -7.3071F, -2.5F, 14, 4, 1, 0.0F, true));
        magazine.cubeList.add(new ModelBox(magazine, 79, 26, 4.0929F, -6.3071F, -1.5F, 1, 3, 3, 0.0F, true));
        magazine.cubeList.add(new ModelBox(magazine, 11, 100, 3.0929F, -7.0071F, -1.5F, 1, 3, 3, 0.0F, true));
        magazine.cubeList.add(new ModelBox(magazine, 11, 100, -4.9071F, -7.0071F, -1.5F, 7, 3, 3, 0.0F, true));
        magazine.cubeList.add(new ModelBox(magazine, 11, 100, -6.9071F, -6.5071F, -1.0F, 10, 2, 2, 0.0F, true));
        magazine.cubeList.add(new ModelBox(magazine, 11, 100, -7.5071F, -5.9071F, -0.5F, 1, 1, 1, 0.0F, true));
        magazine.cubeList.add(new ModelBox(magazine, 79, 26, -8.9071F, -7.3071F, 1.5F, 14, 4, 1, 0.0F, true));
        magazine.cubeList.add(new ModelBox(magazine, 82, 28, -8.9071F, -5.3071F, -1.5F, 1, 2, 3, 0.0F, true));

        bone34 = new ModelRenderer(this);
        bone34.setRotationPoint(-1.9071F, -7.8071F, 2.0F);
        setRotationAngle(bone34, 0.4363F, 0.0F, 0.0F);
        magazine.addChild(bone34);
        bone34.cubeList.add(new ModelBox(bone34, 79, 26, -7.0F, -0.3355F, -0.7582F, 14, 1, 1, 0.0F, true));

        bone35 = new ModelRenderer(this);
        bone35.setRotationPoint(-1.9071F, -7.8071F, 2.0F);
        setRotationAngle(bone35, -0.4363F, 0.0F, 0.0F);
        magazine.addChild(bone35);
        bone35.cubeList.add(new ModelBox(bone35, 79, 26, -7.0F, 1.3549F, -3.8671F, 14, 1, 1, 0.0F, true));
        this.initAnimations();
    }
}

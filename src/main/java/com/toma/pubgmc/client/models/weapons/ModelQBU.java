package com.toma.pubgmc.client.models.weapons;

import com.toma.pubgmc.animation.ReloadAnimation;
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

public class ModelQBU extends ModelGun {

    private final ModelRenderer qbu;
    private final ModelRenderer bone;
    private final ModelRenderer bone18;
    private final ModelRenderer bone17;
    private final ModelRenderer bone15;
    private final ModelRenderer bone14;
    private final ModelRenderer bone16;
    private final ModelRenderer bone19;
    private final ModelRenderer bone20;
    private final ModelRenderer bone13;
    private final ModelRenderer bone11;
    private final ModelRenderer bone12;
    private final ModelRenderer bone10;
    private final ModelRenderer bone7;
    private final ModelRenderer bone8;
    private final ModelRenderer bone9;
    private final ModelRenderer bone21;
    private final ModelRenderer bone22;
    private final ModelRenderer bone23;
    private final ModelRenderer bone24;
    private final ModelRenderer bone4;
    private final ModelRenderer bone29;
    private final ModelRenderer bone30;
    private final ModelRenderer bone27;
    private final ModelRenderer bone28;
    private final ModelRenderer bone25;
    private final ModelRenderer bone26;
    private final ModelRenderer bone5;
    private final ModelRenderer bone6;
    private final ModelRenderer magazine;
    private final ModelRenderer bone2;
    private final ModelRenderer bone3;
    private final ModelRenderer ironsights;
    private final ModelRenderer bone35;
    private final ModelRenderer bone36;
    private final ModelRenderer bone37;
    private final ModelRenderer bone38;
    private final ModelRenderer bone39;
    private final ModelRenderer bone40;
    private final ModelRenderer bone34;
    private final ModelRenderer bone33;
    private final ModelRenderer bone31;
    private final ModelRenderer bone32;

    @Override
    public String textureName() {
        return "qbu";
    }

    @Override
    public void initAnimations() {
        initAimAnimation(-0.56f, 0.305f, 0.22f);
        initAimingAnimationStates(0.305f, 0.3f, 0.2625f);
        reloadAnimation = new ReloadAnimation(magazine, ReloadAnimation.ReloadStyle.MAGAZINE).initMovement(DEFAULT_PART_ANIMATION);
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

            GlStateManager.pushMatrix();
            {
                renderQBU(data.isAiming(), stack);
            }
            GlStateManager.popMatrix();
        }
    }

    private void renderQBU(boolean aim, ItemStack stack) {
        GlStateManager.pushMatrix();
        ModelTransformationHelper.defaultSRTransform();
        GlStateManager.scale(0.6999999, 0.6999999, 0.6999999);
        GlStateManager.translate(0.0, 11.0, 0.0);
        renderParts(hasScopeAtachment(stack));
        GlStateManager.popMatrix();
        //attachments
        renderSniperSilencer(-0.025, -14, 17, 1.4f, stack);
        renderRedDot(0, 10, -6, 1.1f, stack);
        renderHolo(-0.05, 1.9, -5, 1.1f, stack);
        renderScope2X(0, 4, -3, 1.1f, stack);
        renderScope4X(0, 6, -12, 1.1f, stack);
        renderScope8X(0, 8, -4, 1f, stack);
        renderScope15X(0, 8, 0, 1f, stack);
    }

    private void renderParts(boolean hasScope) {
        qbu.render(1f);
        magazine.render(1f);
        if(!hasScope)ironsights.render(1f);
    }

    public ModelQBU() {
        textureWidth = 128;
        textureHeight = 128;

        qbu = new ModelRenderer(this);
        qbu.setRotationPoint(-0.5F, 24.0F, 0.0F);
        qbu.cubeList.add(new ModelBox(qbu, 78, 20, -4.0F, -13.0F, 0.0F, 9, 3, 17, 0.0F, false));
        qbu.cubeList.add(new ModelBox(qbu, 69, 24, -4.0F, -18.0F, 17.0F, 9, 8, 16, 0.0F, false));
        qbu.cubeList.add(new ModelBox(qbu, 70, 19, -3.0F, -10.0F, 17.0F, 7, 8, 17, 0.0F, false));
        qbu.cubeList.add(new ModelBox(qbu, 70, 19, -3.5F, -10.0F, 17.0F, 8, 1, 17, 0.0F, false));
        qbu.cubeList.add(new ModelBox(qbu, 37, 8, -2.0F, -2.9961F, 16.0F, 5, 1, 17, 0.0F, false));
        qbu.cubeList.add(new ModelBox(qbu, 37, 8, -0.5F, -2.6953F, 27.5352F, 2, 3, 2, 0.0F, false));
        qbu.cubeList.add(new ModelBox(qbu, 46, 42, 1.5F, -2.6953F, 16.5352F, 2, 3, 2, 0.0F, false));
        qbu.cubeList.add(new ModelBox(qbu, 46, 42, -2.5F, -2.6953F, 16.5352F, 2, 3, 2, 0.0F, true));
        qbu.cubeList.add(new ModelBox(qbu, 44, 15, 0.0F, -1.7695F, 29.5352F, 1, 2, 2, 0.0F, false));
        qbu.cubeList.add(new ModelBox(qbu, 70, 19, -3.5F, -10.0F, 0.0F, 8, 5, 2, 0.0F, false));
        qbu.cubeList.add(new ModelBox(qbu, 64, 19, -3.0F, -10.0F, -26.0F, 7, 2, 22, 0.0F, false));
        qbu.cubeList.add(new ModelBox(qbu, 64, 19, -2.5F, -8.8264F, -24.9848F, 6, 3, 8, 0.0F, false));
        qbu.cubeList.add(new ModelBox(qbu, 38, 39, -1.5F, -9.1154F, -25.9848F, 4, 7, 2, 0.0F, false));
        qbu.cubeList.add(new ModelBox(qbu, 38, 39, -1.5F, -3.1154F, -32.9848F, 4, 1, 7, 0.0F, false));
        qbu.cubeList.add(new ModelBox(qbu, 38, 39, -1.5F, -7.5296F, -34.399F, 4, 4, 1, 0.0F, false));
        qbu.cubeList.add(new ModelBox(qbu, 43, 13, -0.5F, -8.5218F, -29.1686F, 2, 3, 1, 0.0F, false));
        qbu.cubeList.add(new ModelBox(qbu, 38, 39, -1.5F, -10.7795F, -33.9848F, 4, 3, 8, 0.0F, false));
        qbu.cubeList.add(new ModelBox(qbu, 70, 19, -3.5F, -10.0F, 2.0F, 1, 5, 14, 0.0F, false));
        qbu.cubeList.add(new ModelBox(qbu, 70, 19, -3.5F, -5.0F, 12.0F, 1, 3, 4, 0.0F, false));
        qbu.cubeList.add(new ModelBox(qbu, 85, 15, 3.5F, -10.0F, 2.0F, 1, 5, 14, 0.0F, false));
        qbu.cubeList.add(new ModelBox(qbu, 85, 15, 3.5F, -5.0F, 12.0F, 1, 3, 4, 0.0F, false));
        qbu.cubeList.add(new ModelBox(qbu, 70, 19, -3.5F, -10.0F, 16.0F, 8, 8, 1, 0.0F, false));
        qbu.cubeList.add(new ModelBox(qbu, 70, 19, -4.0F, -18.0F, 33.0F, 9, 8, 13, 0.0F, false));
        qbu.cubeList.add(new ModelBox(qbu, 45, 12, 4.3828F, -12.0F, 40.0F, 1, 2, 3, 0.0F, true));
        qbu.cubeList.add(new ModelBox(qbu, 6, 70, 5.125F, -11.5F, 39.5F, 1, 1, 4, 0.0F, true));
        qbu.cubeList.add(new ModelBox(qbu, 6, 70, 5.125F, -9.0858F, 39.5F, 1, 1, 4, 0.0F, true));
        qbu.cubeList.add(new ModelBox(qbu, 6, 70, 5.125F, -10.7929F, 43.2071F, 1, 2, 1, 0.0F, true));
        qbu.cubeList.add(new ModelBox(qbu, 6, 70, 5.125F, -10.7929F, 38.7929F, 1, 2, 1, 0.0F, true));
        qbu.cubeList.add(new ModelBox(qbu, 70, 19, -4.0F, -10.0F, 34.0F, 9, 7, 12, 0.0F, false));
        qbu.cubeList.add(new ModelBox(qbu, 70, 19, -2.0F, -3.5359F, 34.0F, 5, 4, 12, 0.0F, false));
        qbu.cubeList.add(new ModelBox(qbu, 77, 42, -4.0F, -18.0F, -4.0F, 9, 8, 4, 0.0F, false));
        qbu.cubeList.add(new ModelBox(qbu, 64, 9, -4.0F, -18.0F, -33.0F, 2, 1, 29, 0.0F, false));
        qbu.cubeList.add(new ModelBox(qbu, 68, 10, 1.0F, -18.0F, -20.0F, 4, 8, 16, 0.0F, false));
        qbu.cubeList.add(new ModelBox(qbu, 67, 14, 1.0F, -18.0F, -33.0F, 4, 8, 13, 0.0F, false));
        qbu.cubeList.add(new ModelBox(qbu, 66, 13, -4.0F, -15.0F, -33.0F, 2, 1, 29, 0.0F, false));
        qbu.cubeList.add(new ModelBox(qbu, 64, 16, -4.0F, -12.0F, -33.0F, 3, 2, 29, 0.0F, false));
        qbu.cubeList.add(new ModelBox(qbu, 64, 16, -4.0F, -18.0F, -44.0F, 9, 8, 11, 0.0F, false));
        qbu.cubeList.add(new ModelBox(qbu, 67, 21, -4.0F, -16.0F, -58.0F, 9, 6, 14, 0.0F, false));
        qbu.cubeList.add(new ModelBox(qbu, 67, 21, -4.0F, -16.0F, -72.0F, 2, 6, 2, 0.0F, false));
        qbu.cubeList.add(new ModelBox(qbu, 67, 21, 3.0F, -16.0F, -72.0F, 2, 6, 2, 0.0F, false));
        qbu.cubeList.add(new ModelBox(qbu, 67, 21, -4.0F, -16.0F, -70.0F, 9, 6, 12, 0.0F, false));
        qbu.cubeList.add(new ModelBox(qbu, 66, 21, 4.2143F, -11.9361F, -70.0F, 2, 2, 29, 0.0F, false));
        qbu.cubeList.add(new ModelBox(qbu, 66, 21, -5.2143F, -11.9361F, -70.0F, 2, 2, 29, 0.0F, true));
        qbu.cubeList.add(new ModelBox(qbu, 65, 23, 0.369F, -10.1235F, -70.0F, 5, 2, 26, 0.0F, true));
        qbu.cubeList.add(new ModelBox(qbu, 80, 50, 0.369F, -10.1235F, -44.0F, 5, 2, 3, 0.0F, true));
        qbu.cubeList.add(new ModelBox(qbu, 65, 20, -4.369F, -10.1235F, -70.0F, 5, 2, 26, 0.0F, false));
        qbu.cubeList.add(new ModelBox(qbu, 65, 20, -4.369F, -10.1235F, -44.0F, 5, 2, 3, 0.0F, false));
        qbu.cubeList.add(new ModelBox(qbu, 64, 16, -4.0F, -18.0F, -51.0F, 9, 2, 4, 0.0F, false));
        qbu.cubeList.add(new ModelBox(qbu, 64, 16, -4.0F, -18.0F, -58.0F, 9, 2, 4, 0.0F, false));
        qbu.cubeList.add(new ModelBox(qbu, 64, 16, -4.0F, -18.0F, -65.0F, 9, 2, 4, 0.0F, false));
        qbu.cubeList.add(new ModelBox(qbu, 64, 16, -4.0F, -18.0F, -72.0F, 2, 2, 4, 0.0F, false));
        qbu.cubeList.add(new ModelBox(qbu, 64, 16, 3.0F, -18.0F, -72.0F, 2, 2, 4, 0.0F, false));
        qbu.cubeList.add(new ModelBox(qbu, 64, 29, -4.0F, -20.0F, -37.0F, 9, 2, 23, 0.0F, false));
        qbu.cubeList.add(new ModelBox(qbu, 64, 29, -4.0F, -20.0F, -14.0F, 9, 2, 11, 0.0F, false));
        qbu.cubeList.add(new ModelBox(qbu, 0, 65, -2.5F, -21.6914F, -10.1055F, 6, 4, 10, 0.0F, false));
        qbu.cubeList.add(new ModelBox(qbu, 0, 65, -1.5F, -23.5547F, -2.1055F, 4, 2, 2, 0.0F, false));
        qbu.cubeList.add(new ModelBox(qbu, 0, 65, -1.5F, -23.5547F, -10.1055F, 4, 2, 2, 0.0F, false));
        qbu.cubeList.add(new ModelBox(qbu, 0, 65, -1.5F, -23.5547F, -18.1055F, 4, 2, 2, 0.0F, false));
        qbu.cubeList.add(new ModelBox(qbu, 0, 65, -1.5F, -23.5547F, -26.1055F, 4, 2, 2, 0.0F, false));
        qbu.cubeList.add(new ModelBox(qbu, 0, 65, -1.5F, -23.5547F, -34.1055F, 4, 2, 2, 0.0F, false));
        qbu.cubeList.add(new ModelBox(qbu, 0, 65, -1.5F, -23.5547F, -42.1055F, 4, 2, 2, 0.0F, false));
        qbu.cubeList.add(new ModelBox(qbu, 0, 65, -1.5F, -23.5547F, -50.1055F, 4, 2, 2, 0.0F, false));
        qbu.cubeList.add(new ModelBox(qbu, 0, 65, -1.5F, -23.5547F, -58.1055F, 4, 2, 2, 0.0F, false));
        qbu.cubeList.add(new ModelBox(qbu, 0, 65, -1.5F, -23.5547F, -66.1055F, 4, 2, 2, 0.0F, false));
        qbu.cubeList.add(new ModelBox(qbu, 0, 65, -1.5F, -23.5547F, -6.1055F, 4, 2, 2, 0.0F, false));
        qbu.cubeList.add(new ModelBox(qbu, 0, 65, -1.5F, -23.5547F, -14.1055F, 4, 2, 2, 0.0F, false));
        qbu.cubeList.add(new ModelBox(qbu, 0, 65, -1.5F, -23.5547F, -22.1055F, 4, 2, 2, 0.0F, false));
        qbu.cubeList.add(new ModelBox(qbu, 0, 65, -1.5F, -23.5547F, -30.1055F, 4, 2, 2, 0.0F, false));
        qbu.cubeList.add(new ModelBox(qbu, 0, 65, -1.5F, -23.5547F, -38.1055F, 4, 2, 2, 0.0F, false));
        qbu.cubeList.add(new ModelBox(qbu, 0, 65, -1.5F, -23.5547F, -46.1055F, 4, 2, 2, 0.0F, false));
        qbu.cubeList.add(new ModelBox(qbu, 0, 65, -1.5F, -23.5547F, -54.1055F, 4, 2, 2, 0.0F, false));
        qbu.cubeList.add(new ModelBox(qbu, 0, 65, -1.5F, -23.5547F, -62.1055F, 4, 2, 2, 0.0F, false));
        qbu.cubeList.add(new ModelBox(qbu, 0, 65, -1.5F, -23.5547F, -70.1055F, 4, 2, 2, 0.0F, false));
        qbu.cubeList.add(new ModelBox(qbu, 0, 65, 0.2312F, -22.5542F, -4.1055F, 4, 0, 2, 0.0F, false));
        qbu.cubeList.add(new ModelBox(qbu, 0, 65, 0.2312F, -22.5542F, -12.1055F, 4, 0, 2, 0.0F, false));
        qbu.cubeList.add(new ModelBox(qbu, 0, 65, 0.2312F, -22.5542F, -20.1055F, 4, 0, 2, 0.0F, false));
        qbu.cubeList.add(new ModelBox(qbu, 0, 65, 0.2312F, -22.5542F, -28.1055F, 4, 0, 2, 0.0F, false));
        qbu.cubeList.add(new ModelBox(qbu, 0, 65, 0.2312F, -22.5542F, -36.1055F, 4, 0, 2, 0.0F, false));
        qbu.cubeList.add(new ModelBox(qbu, 0, 65, 0.2312F, -22.5542F, -44.1055F, 4, 0, 2, 0.0F, false));
        qbu.cubeList.add(new ModelBox(qbu, 0, 65, 0.2312F, -22.5542F, -52.1055F, 4, 0, 2, 0.0F, false));
        qbu.cubeList.add(new ModelBox(qbu, 0, 65, 0.2312F, -22.5542F, -60.1055F, 4, 0, 2, 0.0F, false));
        qbu.cubeList.add(new ModelBox(qbu, 0, 65, 0.2312F, -22.5542F, -68.1055F, 4, 0, 2, 0.0F, false));
        qbu.cubeList.add(new ModelBox(qbu, 0, 65, 0.2312F, -22.5542F, -8.1055F, 4, 0, 2, 0.0F, false));
        qbu.cubeList.add(new ModelBox(qbu, 0, 65, 0.2312F, -22.5542F, -16.1055F, 4, 0, 2, 0.0F, false));
        qbu.cubeList.add(new ModelBox(qbu, 0, 65, 0.2312F, -22.5542F, -24.1055F, 4, 0, 2, 0.0F, false));
        qbu.cubeList.add(new ModelBox(qbu, 0, 65, 0.2312F, -22.5542F, -32.1055F, 4, 0, 2, 0.0F, false));
        qbu.cubeList.add(new ModelBox(qbu, 0, 65, 0.2312F, -22.5542F, -40.1055F, 4, 0, 2, 0.0F, false));
        qbu.cubeList.add(new ModelBox(qbu, 0, 65, 0.2312F, -22.5542F, -48.1055F, 4, 0, 2, 0.0F, false));
        qbu.cubeList.add(new ModelBox(qbu, 0, 65, 0.2312F, -22.5542F, -56.1055F, 4, 0, 2, 0.0F, false));
        qbu.cubeList.add(new ModelBox(qbu, 0, 65, 0.2312F, -22.5542F, -64.1055F, 4, 0, 2, 0.0F, false));
        qbu.cubeList.add(new ModelBox(qbu, 0, 65, -3.2312F, -22.5542F, -4.1055F, 4, 0, 2, 0.0F, true));
        qbu.cubeList.add(new ModelBox(qbu, 0, 65, -3.2312F, -22.5542F, -12.1055F, 4, 0, 2, 0.0F, true));
        qbu.cubeList.add(new ModelBox(qbu, 0, 65, -3.2312F, -22.5542F, -20.1055F, 4, 0, 2, 0.0F, true));
        qbu.cubeList.add(new ModelBox(qbu, 0, 65, -3.2312F, -22.5542F, -28.1055F, 4, 0, 2, 0.0F, true));
        qbu.cubeList.add(new ModelBox(qbu, 0, 65, -3.2312F, -22.5542F, -36.1055F, 4, 0, 2, 0.0F, true));
        qbu.cubeList.add(new ModelBox(qbu, 0, 65, -3.2312F, -22.5542F, -44.1055F, 4, 0, 2, 0.0F, true));
        qbu.cubeList.add(new ModelBox(qbu, 0, 65, -3.2312F, -22.5542F, -52.1055F, 4, 0, 2, 0.0F, true));
        qbu.cubeList.add(new ModelBox(qbu, 0, 65, -3.2312F, -22.5542F, -60.1055F, 4, 0, 2, 0.0F, true));
        qbu.cubeList.add(new ModelBox(qbu, 0, 65, -3.2312F, -22.5542F, -68.1055F, 4, 0, 2, 0.0F, true));
        qbu.cubeList.add(new ModelBox(qbu, 0, 65, -3.2312F, -22.5542F, -8.1055F, 4, 0, 2, 0.0F, true));
        qbu.cubeList.add(new ModelBox(qbu, 0, 65, -3.2312F, -22.5542F, -16.1055F, 4, 0, 2, 0.0F, true));
        qbu.cubeList.add(new ModelBox(qbu, 0, 65, -3.2312F, -22.5542F, -24.1055F, 4, 0, 2, 0.0F, true));
        qbu.cubeList.add(new ModelBox(qbu, 0, 65, -3.2312F, -22.5542F, -32.1055F, 4, 0, 2, 0.0F, true));
        qbu.cubeList.add(new ModelBox(qbu, 0, 65, -3.2312F, -22.5542F, -40.1055F, 4, 0, 2, 0.0F, true));
        qbu.cubeList.add(new ModelBox(qbu, 0, 65, -3.2312F, -22.5542F, -48.1055F, 4, 0, 2, 0.0F, true));
        qbu.cubeList.add(new ModelBox(qbu, 0, 65, -3.2312F, -22.5542F, -56.1055F, 4, 0, 2, 0.0F, true));
        qbu.cubeList.add(new ModelBox(qbu, 0, 65, -3.2312F, -22.5542F, -64.1055F, 4, 0, 2, 0.0F, true));
        qbu.cubeList.add(new ModelBox(qbu, 0, 65, -2.5F, -21.6914F, -30.1055F, 6, 4, 10, 0.0F, false));
        qbu.cubeList.add(new ModelBox(qbu, 0, 65, -2.5F, -21.6914F, -20.1055F, 6, 4, 10, 0.0F, false));
        qbu.cubeList.add(new ModelBox(qbu, 0, 65, -2.5F, -21.6914F, -40.1055F, 6, 4, 10, 0.0F, false));
        qbu.cubeList.add(new ModelBox(qbu, 0, 65, -2.5F, -21.6914F, -50.1055F, 6, 4, 10, 0.0F, false));
        qbu.cubeList.add(new ModelBox(qbu, 0, 65, -2.5F, -21.6914F, -60.1055F, 6, 4, 10, 0.0F, false));
        qbu.cubeList.add(new ModelBox(qbu, 0, 65, -2.5F, -21.6914F, -70.1055F, 6, 4, 10, 0.0F, false));
        qbu.cubeList.add(new ModelBox(qbu, 64, 10, -4.8242F, -14.0F, -35.0F, 1, 2, 31, 0.0F, false));
        qbu.cubeList.add(new ModelBox(qbu, 35, 29, -3.3594F, -17.0F, -33.0F, 1, 2, 29, 0.0F, false));
        qbu.cubeList.add(new ModelBox(qbu, 32, 37, -5.3594F, -17.0F, -32.2F, 2, 2, 1, 0.0F, false));
        qbu.cubeList.add(new ModelBox(qbu, 89, 16, 4.0F, -17.0F, 0.0F, 1, 4, 17, 0.0F, false));
        qbu.cubeList.add(new ModelBox(qbu, 71, 16, -4.0F, -18.0F, 0.0F, 9, 1, 17, 0.0F, false));
        qbu.cubeList.add(new ModelBox(qbu, 82, 21, -3.0F, -19.7321F, -3.0F, 7, 1, 25, 0.0F, false));
        qbu.cubeList.add(new ModelBox(qbu, 66, 5, -3.0F, -19.7321F, 22.0F, 7, 2, 24, 0.0F, false));
        qbu.cubeList.add(new ModelBox(qbu, 64, 15, -3.0F, -19.7321F, -57.0F, 7, 1, 20, 0.0F, false));
        qbu.cubeList.add(new ModelBox(qbu, 64, 15, -3.0F, -19.7321F, -71.0F, 7, 1, 14, 0.0F, false));
        qbu.cubeList.add(new ModelBox(qbu, 64, 15, -3.0F, -19.7321F, -72.0F, 7, 2, 1, 0.0F, false));
        qbu.cubeList.add(new ModelBox(qbu, 64, 15, -2.0F, -11.7321F, -72.0F, 5, 2, 22, 0.0F, false));
        qbu.cubeList.add(new ModelBox(qbu, 64, 15, -2.0F, -11.7321F, -50.0F, 5, 2, 15, 0.0F, false));
        qbu.cubeList.add(new ModelBox(qbu, 40, 14, -2.0F, -17.7321F, -71.0F, 5, 6, 1, 0.0F, false));
        qbu.cubeList.add(new ModelBox(qbu, 32, 6, -1.0F, -15.0563F, -83.8047F, 3, 3, 13, 0.0F, false));
        qbu.cubeList.add(new ModelBox(qbu, 32, 6, -0.5F, -17.443F, -83.8047F, 2, 2, 13, 0.0F, false));
        qbu.cubeList.add(new ModelBox(qbu, 32, 6, 0.7266F, -17.6969F, -82.0859F, 1, 3, 1, 0.0F, false));
        qbu.cubeList.add(new ModelBox(qbu, 32, 6, -0.7266F, -17.6969F, -82.0859F, 2, 3, 1, 0.0F, true));
        qbu.cubeList.add(new ModelBox(qbu, 32, 6, 0.0F, -16.943F, -86.8047F, 1, 1, 3, 0.0F, false));
        qbu.cubeList.add(new ModelBox(qbu, 32, 11, -1.0F, -15.0563F, -94.8047F, 3, 3, 11, 0.0F, false));
        qbu.cubeList.add(new ModelBox(qbu, 32, 11, -1.0F, -15.0563F, -101.8047F, 3, 3, 7, 0.0F, false));
        qbu.cubeList.add(new ModelBox(qbu, 32, 11, -1.5F, -15.5563F, -79.5195F, 4, 4, 4, 0.0F, false));
        qbu.cubeList.add(new ModelBox(qbu, 32, 11, -1.5F, -14.5563F, -108.5195F, 1, 3, 7, 0.0F, false));
        qbu.cubeList.add(new ModelBox(qbu, 32, 11, 1.5F, -15.5563F, -108.5195F, 1, 3, 7, 0.0F, false));
        qbu.cubeList.add(new ModelBox(qbu, 32, 11, -1.5F, -15.5563F, -108.5195F, 3, 1, 7, 0.0F, false));
        qbu.cubeList.add(new ModelBox(qbu, 32, 11, -0.5F, -12.5563F, -108.5195F, 3, 1, 7, 0.0F, true));
        qbu.cubeList.add(new ModelBox(qbu, 32, 11, -6.4141F, -9.5563F, -75.6914F, 3, 3, 1, 0.0F, false));
        qbu.cubeList.add(new ModelBox(qbu, 32, 11, 4.4141F, -9.5563F, -75.6914F, 3, 3, 1, 0.0F, true));
        qbu.cubeList.add(new ModelBox(qbu, 32, 11, -5.4141F, -8.5563F, -75.457F, 1, 1, 1, 0.0F, false));
        qbu.cubeList.add(new ModelBox(qbu, 32, 11, 5.4141F, -8.5563F, -75.457F, 1, 1, 1, 0.0F, true));
        qbu.cubeList.add(new ModelBox(qbu, 32, 11, -5.9141F, -9.0563F, -76.5117F, 2, 2, 1, 0.0F, false));
        qbu.cubeList.add(new ModelBox(qbu, 32, 11, 4.9141F, -9.0563F, -76.5117F, 2, 2, 1, 0.0F, true));
        qbu.cubeList.add(new ModelBox(qbu, 11, 11, -5.9141F, -9.0563F, -84.9883F, 2, 2, 5, 0.0F, false));
        qbu.cubeList.add(new ModelBox(qbu, 11, 11, 4.9141F, -9.0563F, -84.9883F, 2, 2, 5, 0.0F, true));
        qbu.cubeList.add(new ModelBox(qbu, 11, 11, -5.9141F, -9.0563F, -101.3203F, 2, 2, 5, 0.0F, false));
        qbu.cubeList.add(new ModelBox(qbu, 11, 11, 4.9141F, -9.0563F, -101.3203F, 2, 2, 5, 0.0F, true));
        qbu.cubeList.add(new ModelBox(qbu, 43, 10, -5.9141F, -9.0563F, -105.9922F, 2, 2, 2, 0.0F, false));
        qbu.cubeList.add(new ModelBox(qbu, 43, 10, 4.9141F, -9.0563F, -105.9922F, 2, 2, 2, 0.0F, true));
        qbu.cubeList.add(new ModelBox(qbu, 32, 11, -6.4141F, -9.5563F, -80.0273F, 3, 3, 4, 0.0F, false));
        qbu.cubeList.add(new ModelBox(qbu, 32, 11, 4.4141F, -9.5563F, -80.0273F, 3, 3, 4, 0.0F, true));
        qbu.cubeList.add(new ModelBox(qbu, 32, 11, -6.4141F, -9.5563F, -104.6953F, 3, 3, 4, 0.0F, false));
        qbu.cubeList.add(new ModelBox(qbu, 32, 11, 4.4141F, -9.5563F, -104.6953F, 3, 3, 4, 0.0F, true));
        qbu.cubeList.add(new ModelBox(qbu, 32, 11, -6.4141F, -9.5563F, -96.6445F, 3, 3, 12, 0.0F, false));
        qbu.cubeList.add(new ModelBox(qbu, 32, 11, 4.4141F, -9.5563F, -96.6445F, 3, 3, 12, 0.0F, true));
        qbu.cubeList.add(new ModelBox(qbu, 0, 71, -3.0F, -18.7321F, -54.0F, 7, 3, 10, 0.0F, false));
        qbu.cubeList.add(new ModelBox(qbu, 0, 71, -3.0F, -18.7321F, -68.0F, 7, 3, 10, 0.0F, false));
        qbu.cubeList.add(new ModelBox(qbu, 0, 8, -3.7F, -17.0F, 0.0F, 1, 4, 17, 0.0F, false));

        bone = new ModelRenderer(this);
        bone.setRotationPoint(-1.5F, 0.0391F, 17.5352F);
        setRotationAngle(bone, 0.3491F, 0.0F, 0.0F);
        qbu.addChild(bone);
        bone.cubeList.add(new ModelBox(bone, 46, 42, 1.0F, -2.5F, -1.0F, 2, 5, 2, 0.0F, true));

        bone18 = new ModelRenderer(this);
        bone18.setRotationPoint(0.5F, 0.4782F, -28.6686F);
        setRotationAngle(bone18, -0.3491F, 0.0F, 0.0F);
        qbu.addChild(bone18);
        bone18.cubeList.add(new ModelBox(bone18, 43, 13, -1.0F, -5.8092F, -2.5823F, 2, 2, 1, 0.0F, false));

        bone17 = new ModelRenderer(this);
        bone17.setRotationPoint(0.5F, -1.6154F, -38.9848F);
        setRotationAngle(bone17, -0.7854F, 0.0F, 0.0F);
        qbu.addChild(bone17);
        bone17.cubeList.add(new ModelBox(bone17, 38, 39, -2.0F, -5.5962F, 1.8891F, 4, 1, 2, 0.0F, false));
        bone17.cubeList.add(new ModelBox(bone17, 38, 39, -2.0F, -9.4246F, -4.9393F, 4, 2, 5, 0.0F, false));

        bone15 = new ModelRenderer(this);
        bone15.setRotationPoint(0.5F, 1.0F, -20.0F);
        setRotationAngle(bone15, 0.1745F, 0.0F, 0.0F);
        qbu.addChild(bone15);
        bone15.cubeList.add(new ModelBox(bone15, 64, 19, -3.0F, -7.5882F, -3.7237F, 6, 17, 8, 0.0F, false));
        bone15.cubeList.add(new ModelBox(bone15, 64, 19, 1.0F, 8.3862F, -4.8713F, 2, 2, 11, 0.0F, false));
        bone15.cubeList.add(new ModelBox(bone15, 64, 19, -3.0F, 8.3862F, -4.8713F, 2, 2, 11, 0.0F, false));
        bone15.cubeList.add(new ModelBox(bone15, 64, 19, -1.0F, 8.5854F, -4.8713F, 2, 2, 2, 0.0F, false));
        bone15.cubeList.add(new ModelBox(bone15, 64, 19, -1.0F, 8.3862F, 3.1287F, 2, 2, 3, 0.0F, false));
        bone15.cubeList.add(new ModelBox(bone15, 0, 72, -1.0F, 9.1284F, -2.8713F, 2, 1, 6, 0.0F, false));

        bone14 = new ModelRenderer(this);
        bone14.setRotationPoint(0.5F, -9.0F, -30.0F);
        setRotationAngle(bone14, -0.1745F, 0.0F, 0.0F);
        qbu.addChild(bone14);
        bone14.cubeList.add(new ModelBox(bone14, 64, 19, -3.5F, -1.7098F, -7.8871F, 7, 2, 12, 0.0F, false));

        bone16 = new ModelRenderer(this);
        bone16.setRotationPoint(0.5F, -9.0F, -30.0F);
        setRotationAngle(bone16, 0.2618F, 0.0F, 0.0F);
        qbu.addChild(bone16);
        bone16.cubeList.add(new ModelBox(bone16, 73, 18, -3.0F, 10.2472F, 6.432F, 6, 11, 5, 0.0F, false));
        bone16.cubeList.add(new ModelBox(bone16, 73, 18, -2.0F, 4.2472F, 11.432F, 4, 17, 1, 0.0F, false));

        bone19 = new ModelRenderer(this);
        bone19.setRotationPoint(-2.0F, 9.0F, 30.0F);
        setRotationAngle(bone19, 0.0F, 0.7854F, 0.0F);
        bone16.addChild(bone19);
        bone19.cubeList.add(new ModelBox(bone19, 73, 18, 15.6651F, -1.7528F, -14.5941F, 1, 14, 5, 0.0F, false));
        bone19.cubeList.add(new ModelBox(bone19, 73, 18, 15.2509F, -4.7528F, -14.5941F, 1, 17, 5, 0.0F, false));

        bone20 = new ModelRenderer(this);
        bone20.setRotationPoint(2.0F, 9.0F, 30.0F);
        setRotationAngle(bone20, 0.0F, -0.7854F, 0.0F);
        bone16.addChild(bone20);
        bone20.cubeList.add(new ModelBox(bone20, 73, 18, -16.6651F, -1.7528F, -14.5941F, 1, 14, 5, 0.0F, true));
        bone20.cubeList.add(new ModelBox(bone20, 73, 18, -16.2509F, -4.7528F, -14.5941F, 1, 17, 5, 0.0F, true));

        bone13 = new ModelRenderer(this);
        bone13.setRotationPoint(-0.5F, 0.0F, -18.0F);
        setRotationAngle(bone13, -0.5236F, 0.0F, 0.0F);
        qbu.addChild(bone13);
        bone13.cubeList.add(new ModelBox(bone13, 70, 19, -3.001F, -19.7321F, 21.9808F, 1, 3, 3, 0.0F, false));
        bone13.cubeList.add(new ModelBox(bone13, 70, 19, -3.001F, -18.7321F, 18.9808F, 1, 2, 3, 0.0F, false));
        bone13.cubeList.add(new ModelBox(bone13, 70, 19, -2.5F, -17.3301F, 7.0885F, 7, 4, 6, 0.0F, false));
        bone13.cubeList.add(new ModelBox(bone13, 85, 15, 4.001F, -19.7321F, 21.9808F, 1, 3, 3, 0.0F, false));
        bone13.cubeList.add(new ModelBox(bone13, 85, 15, 4.001F, -18.7321F, 18.9808F, 1, 2, 3, 0.0F, false));

        bone11 = new ModelRenderer(this);
        bone11.setRotationPoint(-0.5F, 0.0F, 0.0F);
        setRotationAngle(bone11, 0.0F, 0.5236F, 0.0F);
        qbu.addChild(bone11);
        bone11.cubeList.add(new ModelBox(bone11, 70, 19, -11.0981F, -10.0F, 13.2224F, 1, 8, 2, 0.0F, false));
        bone11.cubeList.add(new ModelBox(bone11, 70, 19, 3.3301F, -13.0F, 1.5F, 1, 5, 1, 0.0F, false));

        bone12 = new ModelRenderer(this);
        bone12.setRotationPoint(1.5F, 0.0F, 0.0F);
        setRotationAngle(bone12, 0.0F, -0.5236F, 0.0F);
        qbu.addChild(bone12);
        bone12.cubeList.add(new ModelBox(bone12, 70, 19, 10.0981F, -10.0F, 13.2224F, 1, 8, 2, 0.0F, true));
        bone12.cubeList.add(new ModelBox(bone12, 70, 19, -4.3301F, -10.0F, 1.5F, 1, 2, 1, 0.0F, true));

        bone10 = new ModelRenderer(this);
        bone10.setRotationPoint(-4.625F, -11.0F, 46.5F);
        setRotationAngle(bone10, -0.7854F, 0.0F, 0.0F);
        qbu.addChild(bone10);
        bone10.cubeList.add(new ModelBox(bone10, 6, 70, 9.75F, 1.7678F, -2.4749F, 1, 1, 1, 0.0F, true));
        bone10.cubeList.add(new ModelBox(bone10, 6, 70, 9.75F, 4.5962F, -5.3033F, 1, 1, 1, 0.0F, true));
        bone10.cubeList.add(new ModelBox(bone10, 6, 70, 9.75F, 3.182F, -1.0607F, 1, 1, 1, 0.0F, true));
        bone10.cubeList.add(new ModelBox(bone10, 6, 70, 9.75F, 6.0104F, -3.8891F, 1, 1, 1, 0.0F, true));

        bone7 = new ModelRenderer(this);
        bone7.setRotationPoint(0.5F, -1.5359F, 28.0F);
        setRotationAngle(bone7, -1.0472F, 0.0F, 0.0F);
        qbu.addChild(bone7);
        bone7.cubeList.add(new ModelBox(bone7, 70, 19, -2.5F, -8.1962F, 1.7321F, 5, 4, 3, 0.0F, false));
        bone7.cubeList.add(new ModelBox(bone7, 70, 19, -4.5001F, -9.6603F, -5.7015F, 9, 2, 5, 0.0F, false));

        bone8 = new ModelRenderer(this);
        bone8.setRotationPoint(0.0F, -6.1962F, -1.2679F);
        setRotationAngle(bone8, 0.0F, 0.0F, -0.5236F);
        bone7.addChild(bone8);
        bone8.cubeList.add(new ModelBox(bone8, 70, 19, -3.1651F, -3.5179F, -5.9922F, 5, 4, 9, 0.0F, false));

        bone9 = new ModelRenderer(this);
        bone9.setRotationPoint(0.0F, -6.1962F, -1.2679F);
        setRotationAngle(bone9, 0.0F, 0.0F, 0.5236F);
        bone7.addChild(bone9);
        bone9.cubeList.add(new ModelBox(bone9, 70, 19, -1.8349F, -3.5179F, -5.9492F, 5, 4, 9, 0.0F, true));

        bone21 = new ModelRenderer(this);
        bone21.setRotationPoint(-3.5313F, -11.9961F, -42.5F);
        setRotationAngle(bone21, 0.0F, 0.0F, 0.4363F);
        qbu.addChild(bone21);
        bone21.cubeList.add(new ModelBox(bone21, 67, 21, -1.5F, -2.2344F, -22.5F, 3, 3, 4, 0.0F, true));
        bone21.cubeList.add(new ModelBox(bone21, 67, 21, -1.5F, -2.2344F, -27.5F, 3, 3, 2, 0.0F, true));
        bone21.cubeList.add(new ModelBox(bone21, 67, 21, -1.5F, -2.2344F, -15.5F, 3, 3, 4, 0.0F, true));
        bone21.cubeList.add(new ModelBox(bone21, 64, 26, 6.703F, -2.2517F, -27.5F, 3, 2, 29, 0.0F, true));
        bone21.cubeList.add(new ModelBox(bone21, 67, 21, -1.5F, -2.2344F, -8.5F, 3, 3, 4, 0.0F, true));
        bone21.cubeList.add(new ModelBox(bone21, 67, 21, -1.5F, -2.2344F, -1.5F, 3, 3, 3, 0.0F, true));

        bone22 = new ModelRenderer(this);
        bone22.setRotationPoint(4.5313F, -11.9961F, -42.5F);
        setRotationAngle(bone22, 0.0F, 0.0F, -0.4363F);
        qbu.addChild(bone22);
        bone22.cubeList.add(new ModelBox(bone22, 67, 21, -1.5F, -2.2344F, -22.5F, 3, 3, 4, 0.0F, false));
        bone22.cubeList.add(new ModelBox(bone22, 67, 21, -1.5F, -2.2344F, -27.5F, 3, 3, 2, 0.0F, false));
        bone22.cubeList.add(new ModelBox(bone22, 67, 21, -1.5F, -2.2344F, -15.5F, 3, 3, 4, 0.0F, false));
        bone22.cubeList.add(new ModelBox(bone22, 64, 21, -9.703F, -2.2517F, -27.5F, 3, 2, 29, 0.0F, false));
        bone22.cubeList.add(new ModelBox(bone22, 67, 21, -1.5F, -2.2344F, -8.5F, 3, 3, 4, 0.0F, false));
        bone22.cubeList.add(new ModelBox(bone22, 67, 21, -1.5F, -2.2344F, -1.5F, 3, 3, 3, 0.0F, false));

        bone23 = new ModelRenderer(this);
        bone23.setRotationPoint(8.5F, -23.8633F, -7.0F);
        setRotationAngle(bone23, 0.0F, 0.0F, 0.5236F);
        qbu.addChild(bone23);
        bone23.cubeList.add(new ModelBox(bone23, 0, 65, -5.0419F, 3.2673F, 4.8945F, 2, 2, 2, 0.0F, false));
        bone23.cubeList.add(new ModelBox(bone23, 0, 65, -5.0419F, 3.2673F, -3.1055F, 2, 2, 2, 0.0F, false));
        bone23.cubeList.add(new ModelBox(bone23, 0, 65, -5.0419F, 3.2673F, -11.1055F, 2, 2, 2, 0.0F, false));
        bone23.cubeList.add(new ModelBox(bone23, 0, 65, -5.0419F, 3.2673F, -19.1055F, 2, 2, 2, 0.0F, false));
        bone23.cubeList.add(new ModelBox(bone23, 0, 65, -5.0419F, 3.2673F, -27.1055F, 2, 2, 2, 0.0F, false));
        bone23.cubeList.add(new ModelBox(bone23, 0, 65, -5.0419F, 3.2673F, -35.1055F, 2, 2, 2, 0.0F, false));
        bone23.cubeList.add(new ModelBox(bone23, 0, 65, -5.0419F, 3.2673F, -43.1055F, 2, 2, 2, 0.0F, false));
        bone23.cubeList.add(new ModelBox(bone23, 0, 65, -5.0419F, 3.2673F, -51.1055F, 2, 2, 2, 0.0F, false));
        bone23.cubeList.add(new ModelBox(bone23, 0, 65, -5.0419F, 3.2673F, -59.1055F, 2, 2, 2, 0.0F, false));
        bone23.cubeList.add(new ModelBox(bone23, 0, 65, -5.0419F, 3.2673F, 0.8945F, 2, 2, 2, 0.0F, false));
        bone23.cubeList.add(new ModelBox(bone23, 0, 65, -5.0419F, 3.2673F, -7.1055F, 2, 2, 2, 0.0F, false));
        bone23.cubeList.add(new ModelBox(bone23, 0, 65, -5.0419F, 3.2673F, -15.1055F, 2, 2, 2, 0.0F, false));
        bone23.cubeList.add(new ModelBox(bone23, 0, 65, -5.0419F, 3.2673F, -23.1055F, 2, 2, 2, 0.0F, false));
        bone23.cubeList.add(new ModelBox(bone23, 0, 65, -5.0419F, 3.2673F, -31.1055F, 2, 2, 2, 0.0F, false));
        bone23.cubeList.add(new ModelBox(bone23, 0, 65, -5.0419F, 3.2673F, -39.1055F, 2, 2, 2, 0.0F, false));
        bone23.cubeList.add(new ModelBox(bone23, 0, 65, -5.0419F, 3.2673F, -47.1055F, 2, 2, 2, 0.0F, false));
        bone23.cubeList.add(new ModelBox(bone23, 0, 65, -5.0419F, 3.2673F, -55.1055F, 2, 2, 2, 0.0F, false));
        bone23.cubeList.add(new ModelBox(bone23, 0, 65, -5.0419F, 3.2673F, -63.1055F, 2, 2, 2, 0.0F, false));
        bone23.cubeList.add(new ModelBox(bone23, 0, 65, -3.0419F, 3.2673F, 2.8945F, 0, 2, 2, 0.0F, false));
        bone23.cubeList.add(new ModelBox(bone23, 0, 65, -3.0419F, 3.2673F, -5.1055F, 0, 2, 2, 0.0F, false));
        bone23.cubeList.add(new ModelBox(bone23, 0, 65, -3.0419F, 3.2673F, -13.1055F, 0, 2, 2, 0.0F, false));
        bone23.cubeList.add(new ModelBox(bone23, 0, 65, -3.0419F, 3.2673F, -21.1055F, 0, 2, 2, 0.0F, false));
        bone23.cubeList.add(new ModelBox(bone23, 0, 65, -3.0419F, 3.2673F, -29.1055F, 0, 2, 2, 0.0F, false));
        bone23.cubeList.add(new ModelBox(bone23, 0, 65, -3.0419F, 3.2673F, -37.1055F, 0, 2, 2, 0.0F, false));
        bone23.cubeList.add(new ModelBox(bone23, 0, 65, -3.0419F, 3.2673F, -45.1055F, 0, 2, 2, 0.0F, false));
        bone23.cubeList.add(new ModelBox(bone23, 0, 65, -3.0419F, 3.2673F, -53.1055F, 0, 2, 2, 0.0F, false));
        bone23.cubeList.add(new ModelBox(bone23, 0, 65, -3.0419F, 3.2673F, -61.1055F, 0, 2, 2, 0.0F, false));
        bone23.cubeList.add(new ModelBox(bone23, 0, 65, -3.0419F, 3.2673F, -1.1055F, 0, 2, 2, 0.0F, false));
        bone23.cubeList.add(new ModelBox(bone23, 0, 65, -3.0419F, 3.2673F, -9.1055F, 0, 2, 2, 0.0F, false));
        bone23.cubeList.add(new ModelBox(bone23, 0, 65, -3.0419F, 3.2673F, -17.1055F, 0, 2, 2, 0.0F, false));
        bone23.cubeList.add(new ModelBox(bone23, 0, 65, -3.0419F, 3.2673F, -25.1055F, 0, 2, 2, 0.0F, false));
        bone23.cubeList.add(new ModelBox(bone23, 0, 65, -3.0419F, 3.2673F, -33.1055F, 0, 2, 2, 0.0F, false));
        bone23.cubeList.add(new ModelBox(bone23, 0, 65, -3.0419F, 3.2673F, -41.1055F, 0, 2, 2, 0.0F, false));
        bone23.cubeList.add(new ModelBox(bone23, 0, 65, -3.0419F, 3.2673F, -49.1055F, 0, 2, 2, 0.0F, false));
        bone23.cubeList.add(new ModelBox(bone23, 0, 65, -3.0419F, 3.2673F, -57.1055F, 0, 2, 2, 0.0F, false));

        bone24 = new ModelRenderer(this);
        bone24.setRotationPoint(-7.5F, -23.8633F, -7.0F);
        setRotationAngle(bone24, 0.0F, 0.0F, -0.5236F);
        qbu.addChild(bone24);
        bone24.cubeList.add(new ModelBox(bone24, 0, 65, 3.0419F, 3.2673F, 4.8945F, 2, 2, 2, 0.0F, true));
        bone24.cubeList.add(new ModelBox(bone24, 0, 65, 3.0419F, 3.2673F, -3.1055F, 2, 2, 2, 0.0F, true));
        bone24.cubeList.add(new ModelBox(bone24, 0, 65, 3.0419F, 3.2673F, -11.1055F, 2, 2, 2, 0.0F, true));
        bone24.cubeList.add(new ModelBox(bone24, 0, 65, 3.0419F, 3.2673F, -19.1055F, 2, 2, 2, 0.0F, true));
        bone24.cubeList.add(new ModelBox(bone24, 0, 65, 3.0419F, 3.2673F, -27.1055F, 2, 2, 2, 0.0F, true));
        bone24.cubeList.add(new ModelBox(bone24, 0, 65, 3.0419F, 3.2673F, -35.1055F, 2, 2, 2, 0.0F, true));
        bone24.cubeList.add(new ModelBox(bone24, 0, 65, 3.0419F, 3.2673F, -43.1055F, 2, 2, 2, 0.0F, true));
        bone24.cubeList.add(new ModelBox(bone24, 0, 65, 3.0419F, 3.2673F, -51.1055F, 2, 2, 2, 0.0F, true));
        bone24.cubeList.add(new ModelBox(bone24, 0, 65, 3.0419F, 3.2673F, -59.1055F, 2, 2, 2, 0.0F, true));
        bone24.cubeList.add(new ModelBox(bone24, 0, 65, 3.0419F, 3.2673F, 0.8945F, 2, 2, 2, 0.0F, true));
        bone24.cubeList.add(new ModelBox(bone24, 0, 65, 3.0419F, 3.2673F, -7.1055F, 2, 2, 2, 0.0F, true));
        bone24.cubeList.add(new ModelBox(bone24, 0, 65, 3.0419F, 3.2673F, -15.1055F, 2, 2, 2, 0.0F, true));
        bone24.cubeList.add(new ModelBox(bone24, 0, 65, 3.0419F, 3.2673F, -23.1055F, 2, 2, 2, 0.0F, true));
        bone24.cubeList.add(new ModelBox(bone24, 0, 65, 3.0419F, 3.2673F, -31.1055F, 2, 2, 2, 0.0F, true));
        bone24.cubeList.add(new ModelBox(bone24, 0, 65, 3.0419F, 3.2673F, -39.1055F, 2, 2, 2, 0.0F, true));
        bone24.cubeList.add(new ModelBox(bone24, 0, 65, 3.0419F, 3.2673F, -47.1055F, 2, 2, 2, 0.0F, true));
        bone24.cubeList.add(new ModelBox(bone24, 0, 65, 3.0419F, 3.2673F, -55.1055F, 2, 2, 2, 0.0F, true));
        bone24.cubeList.add(new ModelBox(bone24, 0, 65, 3.0419F, 3.2673F, -63.1055F, 2, 2, 2, 0.0F, true));
        bone24.cubeList.add(new ModelBox(bone24, 0, 65, 3.0419F, 3.2673F, 2.8945F, 0, 2, 2, 0.0F, true));
        bone24.cubeList.add(new ModelBox(bone24, 0, 65, 3.0419F, 3.2673F, -5.1055F, 0, 2, 2, 0.0F, true));
        bone24.cubeList.add(new ModelBox(bone24, 0, 65, 3.0419F, 3.2673F, -13.1055F, 0, 2, 2, 0.0F, true));
        bone24.cubeList.add(new ModelBox(bone24, 0, 65, 3.0419F, 3.2673F, -21.1055F, 0, 2, 2, 0.0F, true));
        bone24.cubeList.add(new ModelBox(bone24, 0, 65, 3.0419F, 3.2673F, -29.1055F, 0, 2, 2, 0.0F, true));
        bone24.cubeList.add(new ModelBox(bone24, 0, 65, 3.0419F, 3.2673F, -37.1055F, 0, 2, 2, 0.0F, true));
        bone24.cubeList.add(new ModelBox(bone24, 0, 65, 3.0419F, 3.2673F, -45.1055F, 0, 2, 2, 0.0F, true));
        bone24.cubeList.add(new ModelBox(bone24, 0, 65, 3.0419F, 3.2673F, -53.1055F, 0, 2, 2, 0.0F, true));
        bone24.cubeList.add(new ModelBox(bone24, 0, 65, 3.0419F, 3.2673F, -61.1055F, 0, 2, 2, 0.0F, true));
        bone24.cubeList.add(new ModelBox(bone24, 0, 65, 3.0419F, 3.2673F, -1.1055F, 0, 2, 2, 0.0F, true));
        bone24.cubeList.add(new ModelBox(bone24, 0, 65, 3.0419F, 3.2673F, -9.1055F, 0, 2, 2, 0.0F, true));
        bone24.cubeList.add(new ModelBox(bone24, 0, 65, 3.0419F, 3.2673F, -17.1055F, 0, 2, 2, 0.0F, true));
        bone24.cubeList.add(new ModelBox(bone24, 0, 65, 3.0419F, 3.2673F, -25.1055F, 0, 2, 2, 0.0F, true));
        bone24.cubeList.add(new ModelBox(bone24, 0, 65, 3.0419F, 3.2673F, -33.1055F, 0, 2, 2, 0.0F, true));
        bone24.cubeList.add(new ModelBox(bone24, 0, 65, 3.0419F, 3.2673F, -41.1055F, 0, 2, 2, 0.0F, true));
        bone24.cubeList.add(new ModelBox(bone24, 0, 65, 3.0419F, 3.2673F, -49.1055F, 0, 2, 2, 0.0F, true));
        bone24.cubeList.add(new ModelBox(bone24, 0, 65, 3.0419F, 3.2673F, -57.1055F, 0, 2, 2, 0.0F, true));

        bone4 = new ModelRenderer(this);
        bone4.setRotationPoint(0.0F, 0.0F, 0.0F);
        setRotationAngle(bone4, 0.0F, -0.3491F, 0.0F);
        qbu.addChild(bone4);
        bone4.cubeList.add(new ModelBox(bone4, 32, 37, -16.0492F, -17.0F, -28.4251F, 3, 2, 1, 0.0F, false));

        bone29 = new ModelRenderer(this);
        bone29.setRotationPoint(-0.6406F, -12.5563F, -76.1914F);
        setRotationAngle(bone29, 0.0F, 0.0F, 0.5236F);
        qbu.addChild(bone29);
        bone29.cubeList.add(new ModelBox(bone29, 32, 11, -2.0F, -2.0F, -2.9688F, 2, 7, 3, 0.0F, false));
        bone29.cubeList.add(new ModelBox(bone29, 32, 11, -1.0F, 5.0F, -2.9688F, 1, 1, 3, 0.0F, false));

        bone30 = new ModelRenderer(this);
        bone30.setRotationPoint(1.6406F, -12.5563F, -76.1914F);
        setRotationAngle(bone30, 0.0F, 0.0F, -0.5236F);
        qbu.addChild(bone30);
        bone30.cubeList.add(new ModelBox(bone30, 32, 11, 0.0F, -2.0F, -2.9688F, 2, 7, 3, 0.0F, true));
        bone30.cubeList.add(new ModelBox(bone30, 32, 11, 0.0F, 5.0F, -2.9688F, 1, 1, 3, 0.0F, true));

        bone27 = new ModelRenderer(this);
        bone27.setRotationPoint(-6.9141F, -8.0563F, -104.9922F);
        setRotationAngle(bone27, 0.0F, 0.3491F, 0.0F);
        qbu.addChild(bone27);
        bone27.cubeList.add(new ModelBox(bone27, 43, 10, 0.2817F, -1.0F, -0.5977F, 1, 2, 1, 0.0F, false));
        bone27.cubeList.add(new ModelBox(bone27, 43, 10, 10.4568F, -1.0F, 3.1058F, 1, 2, 1, 0.0F, true));

        bone28 = new ModelRenderer(this);
        bone28.setRotationPoint(7.9141F, -8.0563F, -104.9922F);
        setRotationAngle(bone28, 0.0F, -0.3491F, 0.0F);
        qbu.addChild(bone28);
        bone28.cubeList.add(new ModelBox(bone28, 43, 10, -11.4568F, -1.0F, 3.1058F, 1, 2, 1, 0.0F, true));
        bone28.cubeList.add(new ModelBox(bone28, 43, 10, -1.2817F, -1.0F, -0.5977F, 1, 2, 1, 0.0F, false));

        bone25 = new ModelRenderer(this);
        bone25.setRotationPoint(-6.9141F, -13.0563F, -104.9922F);
        setRotationAngle(bone25, -0.3491F, 0.0F, 0.0F);
        qbu.addChild(bone25);
        bone25.cubeList.add(new ModelBox(bone25, 43, 10, 1.0F, 3.1008F, 0.4284F, 2, 1, 1, 0.0F, false));
        bone25.cubeList.add(new ModelBox(bone25, 43, 10, 11.8281F, 3.1008F, 0.4284F, 2, 1, 1, 0.0F, true));

        bone26 = new ModelRenderer(this);
        bone26.setRotationPoint(-6.9141F, -2.9437F, -104.9922F);
        setRotationAngle(bone26, 0.3491F, 0.0F, 0.0F);
        qbu.addChild(bone26);
        bone26.cubeList.add(new ModelBox(bone26, 43, 10, 1.0F, -4.2065F, 0.4669F, 2, 1, 1, 0.0F, false));
        bone26.cubeList.add(new ModelBox(bone26, 43, 10, 11.8281F, -4.2065F, 0.4669F, 2, 1, 1, 0.0F, true));

        bone5 = new ModelRenderer(this);
        bone5.setRotationPoint(0.0F, 0.0F, 0.0F);
        setRotationAngle(bone5, 0.0F, 0.0F, 0.5236F);
        qbu.addChild(bone5);
        bone5.cubeList.add(new ModelBox(bone5, 71, 16, -12.4641F, -15.5885F, -3.0F, 1, 2, 25, 0.0F, false));
        bone5.cubeList.add(new ModelBox(bone5, 71, 16, -12.4641F, -15.5885F, 22.0F, 1, 2, 24, 0.0F, false));
        bone5.cubeList.add(new ModelBox(bone5, 87, 25, 0.8301F, -5.0981F, 34.0F, 2, 4, 12, 0.0F, false));
        bone5.cubeList.add(new ModelBox(bone5, 71, 16, -12.4641F, -15.5885F, -72.0F, 1, 1, 35, 0.0F, false));
        bone5.cubeList.add(new ModelBox(bone5, 71, 16, -12.4641F, -14.5885F, -44.0F, 1, 1, 7, 0.0F, false));
        bone5.cubeList.add(new ModelBox(bone5, 71, 16, -12.4641F, -14.5885F, -51.0F, 1, 1, 4, 0.0F, false));
        bone5.cubeList.add(new ModelBox(bone5, 71, 16, -12.4641F, -14.5885F, -58.0F, 1, 1, 4, 0.0F, false));
        bone5.cubeList.add(new ModelBox(bone5, 71, 16, -12.4641F, -14.5885F, -65.0F, 1, 1, 4, 0.0F, false));
        bone5.cubeList.add(new ModelBox(bone5, 71, 16, -12.4641F, -14.5885F, -72.0F, 1, 1, 4, 0.0F, false));

        bone6 = new ModelRenderer(this);
        bone6.setRotationPoint(0.0F, 0.0F, 0.0F);
        setRotationAngle(bone6, 0.0F, 0.0F, -0.5236F);
        qbu.addChild(bone6);
        bone6.cubeList.add(new ModelBox(bone6, 71, 16, 12.3301F, -15.0885F, -3.0F, 1, 2, 25, 0.0F, true));
        bone6.cubeList.add(new ModelBox(bone6, 71, 16, 12.3301F, -15.0885F, 22.0F, 1, 2, 24, 0.0F, true));
        bone6.cubeList.add(new ModelBox(bone6, 79, 11, -1.9641F, -4.5981F, 34.0F, 2, 4, 12, 0.0F, true));
        bone6.cubeList.add(new ModelBox(bone6, 71, 16, 12.3301F, -15.0885F, -72.0F, 1, 1, 35, 0.0F, true));
        bone6.cubeList.add(new ModelBox(bone6, 71, 16, 12.3301F, -14.0885F, -44.0F, 1, 1, 7, 0.0F, true));
        bone6.cubeList.add(new ModelBox(bone6, 71, 16, 12.3301F, -14.0885F, -51.0F, 1, 1, 4, 0.0F, true));
        bone6.cubeList.add(new ModelBox(bone6, 71, 16, 12.3301F, -14.0885F, -58.0F, 1, 1, 4, 0.0F, true));
        bone6.cubeList.add(new ModelBox(bone6, 71, 16, 12.3301F, -14.0885F, -65.0F, 1, 1, 4, 0.0F, true));
        bone6.cubeList.add(new ModelBox(bone6, 71, 16, 12.3301F, -14.0885F, -72.0F, 1, 1, 4, 0.0F, true));

        magazine = new ModelRenderer(this);
        magazine.setRotationPoint(0.5F, 30.0F, 0.0F);
        magazine.cubeList.add(new ModelBox(magazine, 8, 104, -1.5F, -18.5F, 3.0F, 2, 2, 2, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 34, 8, -3.0F, -16.0F, 5.0F, 5, 10, 8, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 35, 3, -3.0F, -6.0F, 5.0F, 5, 8, 8, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 34, 8, -3.0F, 2.0F, 8.0F, 5, 1, 5, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 34, 8, -3.0F, 3.0F, 12.0F, 5, 1, 1, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 34, 8, -3.5F, -16.0F, 13.0F, 6, 10, 3, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 43, 9, -3.5F, -6.0F, 13.0F, 6, 10, 3, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 37, 8, -3.5F, -16.0F, 2.0F, 6, 10, 3, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 40, 10, -3.5F, -6.0F, 2.0F, 6, 8, 3, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 34, 8, -3.5F, -16.0F, 6.0F, 6, 10, 2, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 37, 8, -3.5F, -6.0F, 6.0F, 6, 9, 2, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 34, 8, -3.5F, -16.0F, 10.0F, 6, 10, 2, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 45, 8, -3.5F, -6.0F, 10.0F, 6, 10, 2, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 34, 8, -3.0F, -18.0F, 2.0F, 5, 2, 1, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 34, 8, -3.0F, -18.0F, 15.0F, 5, 2, 1, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 34, 8, 1.0F, -18.0F, 3.0F, 1, 2, 12, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 34, 8, -3.0F, -18.0F, 3.0F, 1, 2, 12, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 8, 104, -2.0F, -19.0F, 14.0F, 3, 3, 1, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 8, 104, -2.0F, -19.0F, 8.0F, 3, 3, 5, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 8, 104, -1.5F, -18.5F, 13.0F, 2, 2, 1, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 8, 104, -1.5F, -19.0F, 5.0F, 2, 3, 3, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 8, 104, -2.0F, -18.5F, 5.0F, 3, 2, 3, 0.0F, false));

        bone2 = new ModelRenderer(this);
        bone2.setRotationPoint(2.0F, -17.5F, 11.0F);
        setRotationAngle(bone2, 0.0F, 0.0F, -0.5236F);
        magazine.addChild(bone2);
        bone2.cubeList.add(new ModelBox(bone2, 34, 8, -1.317F, 0.549F, -1.0F, 1, 1, 2, 0.0F, false));
        bone2.cubeList.add(new ModelBox(bone2, 34, 8, -1.317F, 0.549F, -5.0F, 1, 1, 2, 0.0F, false));
        bone2.cubeList.add(new ModelBox(bone2, 34, 8, -1.317F, 0.549F, -9.0F, 1, 1, 3, 0.0F, false));
        bone2.cubeList.add(new ModelBox(bone2, 34, 8, -1.317F, 0.549F, 2.0F, 1, 1, 3, 0.0F, false));

        bone3 = new ModelRenderer(this);
        bone3.setRotationPoint(-3.0F, -17.5F, 11.0F);
        setRotationAngle(bone3, 0.0F, 0.0F, 0.5236F);
        magazine.addChild(bone3);
        bone3.cubeList.add(new ModelBox(bone3, 34, 8, 0.317F, 0.549F, -1.0F, 1, 1, 2, 0.0F, true));
        bone3.cubeList.add(new ModelBox(bone3, 34, 8, 0.317F, 0.549F, -5.0F, 1, 1, 2, 0.0F, true));
        bone3.cubeList.add(new ModelBox(bone3, 34, 8, 0.317F, 0.549F, -9.0F, 1, 1, 3, 0.0F, true));
        bone3.cubeList.add(new ModelBox(bone3, 34, 8, 0.317F, 0.549F, 2.0F, 1, 1, 3, 0.0F, true));

        ironsights = new ModelRenderer(this);
        ironsights.setRotationPoint(0.0F, 24.0F, 0.0F);
        ironsights.cubeList.add(new ModelBox(ironsights, 45, 14, 3.0F, -23.4531F, -5.6836F, 1, 3, 4, 0.0F, false));
        ironsights.cubeList.add(new ModelBox(ironsights, 34, 8, 3.0F, -23.4531F, -61.6836F, 1, 3, 4, 0.0F, false));
        ironsights.cubeList.add(new ModelBox(ironsights, 45, 14, -4.0F, -23.4531F, -5.6836F, 1, 3, 4, 0.0F, true));
        ironsights.cubeList.add(new ModelBox(ironsights, 34, 8, -4.0F, -23.4531F, -61.6836F, 1, 3, 4, 0.0F, true));
        ironsights.cubeList.add(new ModelBox(ironsights, 36, 8, -4.0F, -24.4531F, -5.6836F, 8, 1, 4, 0.0F, true));
        ironsights.cubeList.add(new ModelBox(ironsights, 34, 8, -4.0F, -24.4531F, -61.6836F, 8, 1, 4, 0.0F, true));
        ironsights.cubeList.add(new ModelBox(ironsights, 45, 14, -3.0F, -24.4531F, -9.6836F, 6, 1, 7, 0.0F, true));
        ironsights.cubeList.add(new ModelBox(ironsights, 34, 8, -3.0F, -24.4531F, -65.6836F, 6, 1, 7, 0.0F, true));
        ironsights.cubeList.add(new ModelBox(ironsights, 40, 13, -3.0F, -29.0977F, -9.6836F, 6, 3, 4, 0.0F, true));
        ironsights.cubeList.add(new ModelBox(ironsights, 0, 0, -2.0F, -30.0977F, -8.6836F, 4, 1, 1, 0.0F, true));
        ironsights.cubeList.add(new ModelBox(ironsights, 47, 44, -1.5F, -30.4961F, -9.1836F, 3, 2, 2, 0.0F, true));
        ironsights.cubeList.add(new ModelBox(ironsights, 47, 44, -1.5F, -33.4961F, -9.1836F, 3, 1, 2, 0.0F, true));
        ironsights.cubeList.add(new ModelBox(ironsights, 47, 44, -1.9336F, -32.4961F, -9.1836F, 1, 2, 2, 0.0F, true));
        ironsights.cubeList.add(new ModelBox(ironsights, 47, 44, 0.9336F, -32.4961F, -9.1836F, 1, 2, 2, 0.0F, false));
        ironsights.cubeList.add(new ModelBox(ironsights, 47, 44, -0.5F, -31.4961F, -65.1836F, 1, 2, 2, 0.0F, false));
        ironsights.cubeList.add(new ModelBox(ironsights, 47, 44, -1.0F, -29.7852F, -65.1836F, 2, 1, 2, 0.0F, false));
        ironsights.cubeList.add(new ModelBox(ironsights, 34, 8, -3.0F, -29.0977F, -65.6836F, 6, 3, 4, 0.0F, true));
        ironsights.cubeList.add(new ModelBox(ironsights, 40, 16, -2.0F, -26.1758F, -8.6836F, 4, 2, 3, 0.0F, true));
        ironsights.cubeList.add(new ModelBox(ironsights, 34, 8, -2.0F, -26.1758F, -64.6836F, 4, 2, 3, 0.0F, true));
        ironsights.cubeList.add(new ModelBox(ironsights, 46, 12, -3.0F, -32.5618F, -9.6836F, 1, 4, 2, 0.0F, false));
        ironsights.cubeList.add(new ModelBox(ironsights, 34, 8, -3.0F, -32.5618F, -65.6836F, 1, 4, 2, 0.0F, false));
        ironsights.cubeList.add(new ModelBox(ironsights, 45, 14, 2.0F, -32.5618F, -9.6836F, 1, 4, 2, 0.0F, true));
        ironsights.cubeList.add(new ModelBox(ironsights, 34, 8, 2.0F, -32.5618F, -65.6836F, 1, 4, 2, 0.0F, true));

        bone35 = new ModelRenderer(this);
        bone35.setRotationPoint(3.5F, -28.9531F, -7.6445F);
        setRotationAngle(bone35, -0.3491F, 0.0F, 0.0F);
        ironsights.addChild(bone35);
        bone35.cubeList.add(new ModelBox(bone35, 11, 69, -0.5F, -1.5F, -1.5F, 1, 3, 3, 0.0F, true));

        bone36 = new ModelRenderer(this);
        bone36.setRotationPoint(3.5F, -28.9531F, -7.6445F);
        setRotationAngle(bone36, -0.6109F, 0.0F, 0.0F);
        ironsights.addChild(bone36);
        bone36.cubeList.add(new ModelBox(bone36, 11, 69, -0.5F, -1.5F, -1.5F, 1, 3, 3, 0.0F, true));

        bone37 = new ModelRenderer(this);
        bone37.setRotationPoint(3.5F, -28.9531F, -7.6445F);
        setRotationAngle(bone37, -0.8727F, 0.0F, 0.0F);
        ironsights.addChild(bone37);
        bone37.cubeList.add(new ModelBox(bone37, 11, 69, -0.5F, -1.5F, -1.5F, 1, 3, 3, 0.0F, true));

        bone38 = new ModelRenderer(this);
        bone38.setRotationPoint(3.5F, -28.9531F, -7.6445F);
        setRotationAngle(bone38, -0.0873F, 0.0F, 0.0F);
        ironsights.addChild(bone38);
        bone38.cubeList.add(new ModelBox(bone38, 11, 69, -0.5F, -1.5F, -1.5F, 1, 3, 3, 0.0F, true));

        bone39 = new ModelRenderer(this);
        bone39.setRotationPoint(3.5F, -28.9531F, -7.6445F);
        setRotationAngle(bone39, 0.1745F, 0.0F, 0.0F);
        ironsights.addChild(bone39);
        bone39.cubeList.add(new ModelBox(bone39, 11, 69, -0.5F, -1.5F, -1.5F, 1, 3, 3, 0.0F, true));

        bone40 = new ModelRenderer(this);
        bone40.setRotationPoint(3.5F, -28.9531F, -7.6445F);
        setRotationAngle(bone40, 0.4363F, 0.0F, 0.0F);
        ironsights.addChild(bone40);
        bone40.cubeList.add(new ModelBox(bone40, 11, 69, -0.5F, -1.5F, -1.5F, 1, 3, 3, 0.0F, true));

        bone34 = new ModelRenderer(this);
        bone34.setRotationPoint(-2.5F, -31.0977F, -5.6836F);
        setRotationAngle(bone34, 0.5236F, 0.0F, 0.0F);
        ironsights.addChild(bone34);
        bone34.cubeList.add(new ModelBox(bone34, 45, 14, -0.5F, -2.2679F, -3.0F, 1, 4, 2, 0.0F, false));
        bone34.cubeList.add(new ModelBox(bone34, 34, 8, -0.5F, -30.2679F, -51.4974F, 1, 4, 2, 0.0F, false));
        bone34.cubeList.add(new ModelBox(bone34, 45, 14, 4.5F, -2.2679F, -3.0F, 1, 4, 2, 0.0F, true));
        bone34.cubeList.add(new ModelBox(bone34, 34, 8, 4.5F, -30.2679F, -51.4974F, 1, 4, 2, 0.0F, true));

        bone33 = new ModelRenderer(this);
        bone33.setRotationPoint(0.0F, -30.9531F, -7.6836F);
        setRotationAngle(bone33, 0.7854F, 0.0F, 0.0F);
        ironsights.addChild(bone33);
        bone33.cubeList.add(new ModelBox(bone33, 45, 14, -3.0F, 3.182F, -3.3536F, 6, 4, 3, 0.0F, true));
        bone33.cubeList.add(new ModelBox(bone33, 34, 8, -3.0F, -36.416F, -42.9515F, 6, 4, 3, 0.0F, true));
        bone33.cubeList.add(new ModelBox(bone33, 10, 68, -1.5F, 3.9164F, -2.9942F, 3, 4, 3, 0.0F, true));
        bone33.cubeList.add(new ModelBox(bone33, 10, 68, -1.5F, -35.6816F, -42.5922F, 3, 4, 3, 0.0F, true));
        bone33.cubeList.add(new ModelBox(bone33, 45, 14, -3.0F, 6.8388F, -2.3536F, 6, 2, 2, 0.0F, true));
        bone33.cubeList.add(new ModelBox(bone33, 34, 8, -3.0F, -32.7591F, -41.9515F, 6, 2, 2, 0.0F, true));

        bone31 = new ModelRenderer(this);
        bone31.setRotationPoint(0.0F, -27.9531F, -12.1836F);
        setRotationAngle(bone31, 0.0F, 0.3491F, 0.0F);
        ironsights.addChild(bone31);
        bone31.cubeList.add(new ModelBox(bone31, 45, 14, 0.5356F, 3.5F, 4.4761F, 1, 1, 3, 0.0F, true));
        bone31.cubeList.add(new ModelBox(bone31, 0, 0, 19.6888F, 3.5F, -48.1467F, 1, 1, 3, 0.0F, true));

        bone32 = new ModelRenderer(this);
        bone32.setRotationPoint(0.0F, -27.9531F, -12.1836F);
        setRotationAngle(bone32, 0.0F, -0.3491F, 0.0F);
        ironsights.addChild(bone32);
        bone32.cubeList.add(new ModelBox(bone32, 40, 16, -1.5356F, 3.5F, 4.4761F, 1, 1, 3, 0.0F, false));
        bone32.cubeList.add(new ModelBox(bone32, 34, 8, -20.6888F, 3.5F, -48.1467F, 1, 1, 3, 0.0F, false));
        this.initAnimations();
    }
}

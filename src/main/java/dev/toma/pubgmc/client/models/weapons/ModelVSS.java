package dev.toma.pubgmc.client.models.weapons;

import dev.toma.pubgmc.animation.ReloadAnimation;
import dev.toma.pubgmc.client.util.ModelTransformationHelper;
import dev.toma.pubgmc.common.capability.player.PlayerDataProvider;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.item.ItemStack;

public class ModelVSS extends ModelGun {

    private final ModelRenderer charging_handle;
    private final ModelRenderer bone28;
    private final ModelRenderer magazine;
    private final ModelRenderer bone4;
    private final ModelRenderer bone2;
    private final ModelRenderer bone3;
    private final ModelRenderer vss;
    private final ModelRenderer bone15;
    private final ModelRenderer bone16;
    private final ModelRenderer bone12;
    private final ModelRenderer bone11;
    private final ModelRenderer bone13;
    private final ModelRenderer bone18;
    private final ModelRenderer bone17;
    private final ModelRenderer bone9;
    private final ModelRenderer bone8;
    private final ModelRenderer bone6;
    private final ModelRenderer bone19;
    private final ModelRenderer bone20;
    private final ModelRenderer bone23;
    private final ModelRenderer bone21;
    private final ModelRenderer bone22;
    private final ModelRenderer bone26;
    private final ModelRenderer bone27;
    private final ModelRenderer bone24;
    private final ModelRenderer bone25;
    private final ModelRenderer bone14;
    private final ModelRenderer bone7;
    private final ModelRenderer bone10;
    private final ModelRenderer bone;
    private final ModelRenderer bone5;

    @Override
    public void initAnimations() {
        this.reloadAnimation = new ReloadAnimation(magazine, ReloadAnimation.ReloadStyle.MAGAZINE).withSpeed(1.6F);
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
            GlStateManager.pushMatrix();
            {
                renderVSS();
            }
            GlStateManager.popMatrix();
        }
    }

    private void renderVSS() {
        GlStateManager.pushMatrix();
        ModelTransformationHelper.defaultARTransform();
        GlStateManager.scale(1.1F, 1.1F, 1.1F);
        GlStateManager.translate(0.0, 13.0, -6.0);
        vss.render(1f);
        magazine.render(1f);
        charging_handle.render(1.0F);
        GlStateManager.popMatrix();
    }

    public ModelVSS() {
        textureWidth = 512;
        textureHeight = 512;

        charging_handle = new ModelRenderer(this);
        charging_handle.setRotationPoint(1.0F, 24.0F, 0.0F);
        charging_handle.cubeList.add(new ModelBox(charging_handle, 85, 161, -5.6F, -31.544F, -3.276F, 2, 1, 1, 0.0F, false));

        bone28 = new ModelRenderer(this);
        bone28.setRotationPoint(-4.6F, -31.044F, -2.776F);
        charging_handle.addChild(bone28);
        setRotationAngle(bone28, 0.0F, -0.4363F, 0.0F);
        bone28.cubeList.add(new ModelBox(bone28, 85, 161, -1.1176F, -0.5F, -0.0305F, 3, 1, 1, 0.0F, false));

        magazine = new ModelRenderer(this);
        magazine.setRotationPoint(0.0F, 26.072F, 0.0F);
        magazine.cubeList.add(new ModelBox(magazine, 93, 34, -1.5F, -31.0F, 4.0F, 3, 2, 1, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 0, 510, -1.5F, -32.7227F, 3.0234F, 3, 3, 1, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 0, 510, -1.5F, -32.7227F, -0.5766F, 3, 3, 3, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 0, 510, -1.5F, -31.7227F, -2.5766F, 3, 1, 2, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 0, 510, -0.5F, -32.7227F, -2.5766F, 1, 3, 2, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 0, 510, -0.5F, -31.7227F, -3.6742F, 1, 1, 1, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 0, 510, -1.0F, -32.2227F, 2.0234F, 2, 2, 1, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 0, 510, -1.0F, -32.2227F, -3.0766F, 2, 2, 3, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 93, 34, -1.5F, -30.0F, -4.0F, 3, 1, 1, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 93, 34, -2.5F, -32.0F, -4.0F, 1, 3, 9, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 93, 34, 1.5F, -32.0F, -4.0F, 1, 3, 9, 0.0F, true));
        magazine.cubeList.add(new ModelBox(magazine, 93, 34, -2.5F, -29.0F, -4.0F, 5, 1, 9, 0.0F, true));
        magazine.cubeList.add(new ModelBox(magazine, 93, 34, -2.5F, -26.0F, -4.0F, 5, 1, 9, 0.0F, true));
        magazine.cubeList.add(new ModelBox(magazine, 93, 34, -2.5F, -28.0F, -3.0F, 5, 2, 1, 0.0F, true));
        magazine.cubeList.add(new ModelBox(magazine, 93, 34, -2.5F, -25.0F, -3.0F, 5, 2, 1, 0.0F, true));
        magazine.cubeList.add(new ModelBox(magazine, 93, 34, -2.0F, -28.0F, -4.0F, 4, 2, 9, 0.0F, true));
        magazine.cubeList.add(new ModelBox(magazine, 93, 34, -2.0F, -25.0F, -4.0F, 4, 2, 9, 0.0F, true));
        magazine.cubeList.add(new ModelBox(magazine, 93, 34, -1.0F, -28.0F, -5.0F, 2, 5, 1, 0.0F, true));
        magazine.cubeList.add(new ModelBox(magazine, 93, 34, -2.5F, -28.0F, 0.0F, 5, 2, 1, 0.0F, true));
        magazine.cubeList.add(new ModelBox(magazine, 93, 34, -2.5F, -25.0F, 0.0F, 5, 2, 1, 0.0F, true));
        magazine.cubeList.add(new ModelBox(magazine, 93, 34, -2.5F, -28.0F, 3.0F, 5, 2, 1, 0.0F, true));
        magazine.cubeList.add(new ModelBox(magazine, 93, 34, -2.5F, -25.0F, 3.0F, 5, 2, 1, 0.0F, true));

        bone4 = new ModelRenderer(this);
        bone4.setRotationPoint(0.0F, 1.0F, -5.0F);
        magazine.addChild(bone4);
        setRotationAngle(bone4, 0.6109F, 0.0F, 0.0F);
        bone4.cubeList.add(new ModelBox(bone4, 93, 34, -1.0F, -23.7554F, 16.6337F, 2, 1, 2, 0.0F, true));

        bone2 = new ModelRenderer(this);
        bone2.setRotationPoint(-0.5F, 6.0F, 5.0F);
        magazine.addChild(bone2);
        setRotationAngle(bone2, -0.0873F, 0.0F, 0.0F);
        bone2.cubeList.add(new ModelBox(bone2, 93, 34, -2.0F, -25.8896F, -7.5275F, 5, 2, 1, 0.0F, true));
        bone2.cubeList.add(new ModelBox(bone2, 93, 34, -2.0F, -25.8896F, -4.5275F, 5, 2, 1, 0.0F, true));
        bone2.cubeList.add(new ModelBox(bone2, 93, 34, -2.0F, -25.8896F, -10.5275F, 5, 2, 1, 0.0F, true));
        bone2.cubeList.add(new ModelBox(bone2, 93, 34, -1.5F, -25.8896F, -11.5275F, 4, 2, 9, 0.0F, true));
        bone2.cubeList.add(new ModelBox(bone2, 93, 34, -0.5F, -28.8896F, -12.5275F, 2, 5, 1, 0.0F, true));
        bone2.cubeList.add(new ModelBox(bone2, 93, 34, -2.0F, -26.8896F, -11.5275F, 5, 1, 9, 0.0F, true));
        bone2.cubeList.add(new ModelBox(bone2, 93, 34, -1.5F, -28.8896F, -11.5275F, 4, 2, 9, 0.0F, true));
        bone2.cubeList.add(new ModelBox(bone2, 93, 34, -2.0F, -28.8896F, -10.5275F, 5, 2, 1, 0.0F, true));
        bone2.cubeList.add(new ModelBox(bone2, 93, 34, -2.0F, -28.8896F, -7.5275F, 5, 2, 1, 0.0F, true));
        bone2.cubeList.add(new ModelBox(bone2, 93, 34, -2.0F, -28.8896F, -4.5275F, 5, 2, 1, 0.0F, true));

        bone3 = new ModelRenderer(this);
        bone3.setRotationPoint(-0.5F, 6.0F, 5.0F);
        magazine.addChild(bone3);
        setRotationAngle(bone3, -0.1745F, 0.0F, 0.0F);
        bone3.cubeList.add(new ModelBox(bone3, 93, 34, -2.0F, -20.5785F, -9.6F, 5, 2, 1, 0.0F, true));
        bone3.cubeList.add(new ModelBox(bone3, 93, 34, -2.0F, -20.5785F, -6.6F, 5, 2, 1, 0.0F, true));
        bone3.cubeList.add(new ModelBox(bone3, 93, 34, -2.0F, -20.5785F, -12.6F, 5, 2, 1, 0.0F, true));
        bone3.cubeList.add(new ModelBox(bone3, 93, 34, -1.5F, -20.5785F, -13.6F, 4, 2, 9, 0.0F, true));
        bone3.cubeList.add(new ModelBox(bone3, 93, 34, -0.5F, -23.5785F, -14.6F, 2, 5, 1, 0.0F, true));
        bone3.cubeList.add(new ModelBox(bone3, 93, 34, -2.0F, -21.5785F, -13.6F, 5, 1, 9, 0.0F, true));
        bone3.cubeList.add(new ModelBox(bone3, 93, 34, -1.5F, -23.5785F, -13.6F, 4, 2, 9, 0.0F, true));
        bone3.cubeList.add(new ModelBox(bone3, 93, 34, -2.0F, -23.5785F, -12.6F, 5, 2, 1, 0.0F, true));
        bone3.cubeList.add(new ModelBox(bone3, 93, 34, -2.0F, -23.5785F, -9.6F, 5, 2, 1, 0.0F, true));
        bone3.cubeList.add(new ModelBox(bone3, 93, 34, -2.0F, -23.5785F, -6.6F, 5, 2, 1, 0.0F, true));

        vss = new ModelRenderer(this);
        vss.setRotationPoint(0.0F, 24.0F, 0.0F);
        vss.cubeList.add(new ModelBox(vss, 14, 86, -3.0F, -30.0F, -5.5F, 6, 5, 11, 0.0F, false));
        vss.cubeList.add(new ModelBox(vss, 14, 86, -3.0F, -32.0F, -5.5F, 6, 2, 2, 0.0F, false));
        vss.cubeList.add(new ModelBox(vss, 14, 86, -3.0F, -32.0F, 3.5F, 6, 2, 2, 0.0F, false));
        vss.cubeList.add(new ModelBox(vss, 85, 161, -2.6F, -32.0F, -3.5F, 1, 2, 7, 0.0F, false));
        vss.cubeList.add(new ModelBox(vss, 20, 158, 2.232F, -38.344F, 1.268F, 1, 8, 2, 0.0F, false));
        vss.cubeList.add(new ModelBox(vss, 20, 158, 3.064F, -36.0F, -4.14F, 1, 7, 2, 0.0F, false));
        vss.cubeList.add(new ModelBox(vss, 20, 158, 2.064F, -38.0F, -4.14F, 2, 2, 2, 0.0F, false));
        vss.cubeList.add(new ModelBox(vss, 20, 158, 2.824F, -31.0F, 0.668F, 1, 3, 3, 0.0F, false));
        vss.cubeList.add(new ModelBox(vss, 20, 158, 2.728F, -30.5F, -4.332F, 1, 2, 5, 0.0F, false));
        vss.cubeList.add(new ModelBox(vss, 14, 86, -2.0F, -32.0F, -5.5F, 5, 2, 11, 0.0F, false));
        vss.cubeList.add(new ModelBox(vss, 14, 86, -0.866F, -34.2321F, -5.5F, 2, 2, 11, 0.0F, false));
        vss.cubeList.add(new ModelBox(vss, 14, 86, -1.134F, -34.2321F, -5.5F, 1, 2, 11, 0.0F, true));
        vss.cubeList.add(new ModelBox(vss, 14, 86, -2.0F, -33.0F, 5.5F, 4, 2, 9, 0.0F, false));
        vss.cubeList.add(new ModelBox(vss, 14, 86, -0.366F, -34.366F, -5.5F, 1, 2, 20, 0.0F, false));
        vss.cubeList.add(new ModelBox(vss, 14, 86, -0.634F, -34.366F, -5.5F, 1, 2, 20, 0.0F, true));
        vss.cubeList.add(new ModelBox(vss, 14, 86, -2.5F, -32.0F, -16.5F, 5, 2, 11, 0.0F, false));
        vss.cubeList.add(new ModelBox(vss, 14, 86, -0.9142F, -33.4142F, -16.5F, 2, 2, 11, 0.0F, false));
        vss.cubeList.add(new ModelBox(vss, 14, 86, -1.0858F, -33.4142F, -16.5F, 1, 2, 11, 0.0F, true));
        vss.cubeList.add(new ModelBox(vss, 14, 86, -2.0F, -31.0F, 5.5F, 4, 3, 5, 0.0F, false));
        vss.cubeList.add(new ModelBox(vss, 14, 86, -1.5F, -28.7F, 5.375F, 3, 1, 5, 0.0F, false));
        vss.cubeList.add(new ModelBox(vss, 14, 86, -1.5F, -27.7F, 5.375F, 1, 1, 1, 0.0F, false));
        vss.cubeList.add(new ModelBox(vss, 14, 86, 0.5F, -27.7F, 5.375F, 1, 1, 1, 0.0F, false));
        vss.cubeList.add(new ModelBox(vss, 14, 86, -2.0F, -31.0F, 10.5F, 4, 1, 5, 0.0F, false));
        vss.cubeList.add(new ModelBox(vss, 162, 156, -2.0F, -30.0F, 10.5F, 4, 2, 12, 0.0F, false));
        vss.cubeList.add(new ModelBox(vss, 162, 156, -2.0F, -29.9904F, 22.2506F, 4, 2, 7, 0.0F, false));
        vss.cubeList.add(new ModelBox(vss, 162, 156, -2.0F, -29.9904F, 29.2506F, 4, 7, 2, 0.0F, false));
        vss.cubeList.add(new ModelBox(vss, 162, 156, -2.0F, -29.9904F, 31.2506F, 4, 8, 1, 0.0F, false));
        vss.cubeList.add(new ModelBox(vss, 162, 156, -1.0F, -30.9904F, 31.2506F, 2, 1, 1, 0.0F, false));
        vss.cubeList.add(new ModelBox(vss, 162, 156, -0.3663F, -31.3536F, 31.2506F, 1, 1, 1, 0.0F, false));
        vss.cubeList.add(new ModelBox(vss, 162, 156, -0.6337F, -31.3536F, 31.2506F, 1, 1, 1, 0.0F, true));
        vss.cubeList.add(new ModelBox(vss, 162, 156, -0.3663F, -31.3536F, 23.2506F, 1, 1, 8, 0.0F, false));
        vss.cubeList.add(new ModelBox(vss, 162, 156, -0.6337F, -31.3536F, 23.2506F, 1, 1, 8, 0.0F, true));
        vss.cubeList.add(new ModelBox(vss, 162, 156, -0.3663F, -31.3536F, 15.2506F, 1, 1, 8, 0.0F, false));
        vss.cubeList.add(new ModelBox(vss, 162, 156, -0.6337F, -31.3536F, 15.2506F, 1, 1, 8, 0.0F, true));
        vss.cubeList.add(new ModelBox(vss, 162, 156, -2.0F, -22.9904F, 12.2506F, 4, 1, 13, 0.0F, false));
        vss.cubeList.add(new ModelBox(vss, 162, 156, -2.0F, -22.9904F, 25.2506F, 4, 1, 6, 0.0F, false));
        vss.cubeList.add(new ModelBox(vss, 162, 156, -1.5F, -23.4904F, 14.2506F, 3, 1, 11, 0.0F, false));
        vss.cubeList.add(new ModelBox(vss, 162, 156, -1.5F, -28.6904F, 14.2506F, 3, 1, 11, 0.0F, false));
        vss.cubeList.add(new ModelBox(vss, 162, 156, -1.5F, -23.4904F, 25.2506F, 3, 1, 5, 0.0F, false));
        vss.cubeList.add(new ModelBox(vss, 162, 156, -1.5F, -28.6904F, 25.2506F, 3, 1, 5, 0.0F, false));
        vss.cubeList.add(new ModelBox(vss, 84, 21, -1.866F, -31.5547F, -38.5766F, 1, 1, 15, 0.0F, false));
        vss.cubeList.add(new ModelBox(vss, 88, 81, -1.866F, -37.3907F, 3.3034F, 1, 1, 10, 0.0F, false));
        vss.cubeList.add(new ModelBox(vss, 84, 21, -1.866F, -31.5547F, -23.5766F, 1, 1, 15, 0.0F, false));
        vss.cubeList.add(new ModelBox(vss, 84, 21, 0.866F, -31.5547F, -38.5766F, 1, 1, 15, 0.0F, true));
        vss.cubeList.add(new ModelBox(vss, 88, 81, 0.866F, -37.3907F, 3.3034F, 1, 1, 10, 0.0F, true));
        vss.cubeList.add(new ModelBox(vss, 88, 81, 1.4142F, -37.8907F, -4.8726F, 1, 2, 9, 0.0F, true));
        vss.cubeList.add(new ModelBox(vss, 88, 81, 1.4142F, -37.8907F, 13.1274F, 1, 2, 4, 0.0F, true));
        vss.cubeList.add(new ModelBox(vss, 88, 81, -1.0F, -37.8907F, -14.6566F, 2, 2, 1, 0.0F, true));
        vss.cubeList.add(new ModelBox(vss, 86, 90, -1.0F, -37.8907F, 9.5114F, 2, 2, 1, 0.0F, true));
        vss.cubeList.add(new ModelBox(vss, 88, 81, -1.0F, -38.5978F, -14.8726F, 2, 1, 10, 0.0F, true));
        vss.cubeList.add(new ModelBox(vss, 88, 81, -1.0F, -36.1836F, -14.8726F, 2, 1, 10, 0.0F, true));
        vss.cubeList.add(new ModelBox(vss, 88, 81, 0.7071F, -37.8907F, -14.8726F, 1, 2, 10, 0.0F, true));
        vss.cubeList.add(new ModelBox(vss, 88, 81, -2.4142F, -37.8907F, -4.8726F, 1, 2, 9, 0.0F, false));
        vss.cubeList.add(new ModelBox(vss, 88, 81, -2.4142F, -37.8907F, 13.1274F, 1, 2, 4, 0.0F, false));
        vss.cubeList.add(new ModelBox(vss, 88, 81, -1.7071F, -37.8907F, -14.8726F, 1, 2, 10, 0.0F, false));
        vss.cubeList.add(new ModelBox(vss, 77, 33, -3.4142F, -37.8907F, -1.3726F, 1, 2, 2, 0.0F, false));
        vss.cubeList.add(new ModelBox(vss, 77, 33, -3.8142F, -38.3907F, -1.8726F, 1, 3, 3, 0.0F, false));
        vss.cubeList.add(new ModelBox(vss, 77, 33, -1.0F, -40.3049F, -1.3726F, 2, 1, 2, 0.0F, true));
        vss.cubeList.add(new ModelBox(vss, 77, 33, -1.5F, -40.7049F, -1.8726F, 3, 1, 3, 0.0F, true));
        vss.cubeList.add(new ModelBox(vss, 88, 81, -1.0F, -39.3049F, -4.8726F, 2, 1, 9, 0.0F, true));
        vss.cubeList.add(new ModelBox(vss, 88, 81, -1.0F, -39.3049F, 13.1274F, 2, 1, 4, 0.0F, true));
        vss.cubeList.add(new ModelBox(vss, 88, 81, -1.0F, -35.4765F, -4.8726F, 2, 1, 9, 0.0F, true));
        vss.cubeList.add(new ModelBox(vss, 88, 81, -1.0F, -35.4765F, 13.1274F, 2, 1, 4, 0.0F, true));
        vss.cubeList.add(new ModelBox(vss, 84, 21, 0.866F, -31.5547F, -23.5766F, 1, 1, 15, 0.0F, true));
        vss.cubeList.add(new ModelBox(vss, 84, 21, -0.5F, -32.9207F, -38.5766F, 1, 1, 15, 0.0F, false));
        vss.cubeList.add(new ModelBox(vss, 88, 81, -0.5F, -38.7567F, 3.3034F, 1, 1, 10, 0.0F, false));
        vss.cubeList.add(new ModelBox(vss, 14, 86, -0.5F, -33.4407F, -37.5766F, 1, 1, 1, 0.0F, false));
        vss.cubeList.add(new ModelBox(vss, 14, 86, -0.5F, -34.8307F, -36.825F, 1, 3, 1, 0.0F, false));
        vss.cubeList.add(new ModelBox(vss, 84, 21, -0.5F, -32.9207F, -23.5766F, 1, 1, 15, 0.0F, false));
        vss.cubeList.add(new ModelBox(vss, 84, 21, -0.5F, -30.1887F, -38.5766F, 1, 1, 15, 0.0F, false));
        vss.cubeList.add(new ModelBox(vss, 88, 81, -0.5F, -36.0247F, 3.3034F, 1, 1, 10, 0.0F, false));
        vss.cubeList.add(new ModelBox(vss, 84, 21, -0.5F, -30.1887F, -23.5766F, 1, 1, 15, 0.0F, false));

        bone15 = new ModelRenderer(this);
        bone15.setRotationPoint(0.0F, -34.5F, 0.0F);
        vss.addChild(bone15);
        setRotationAngle(bone15, 0.0F, 0.0F, -0.5236F);
        bone15.cubeList.add(new ModelBox(bone15, 14, 86, -0.6519F, 1.6651F, -5.5F, 2, 2, 11, 0.0F, false));
        bone15.cubeList.add(new ModelBox(bone15, 14, 86, -1.0179F, 1.299F, 4.5F, 2, 1, 10, 0.0F, false));
        bone15.cubeList.add(new ModelBox(bone15, 14, 86, -1.616F, -0.201F, -5.5F, 1, 1, 20, 0.0F, false));
        bone15.cubeList.add(new ModelBox(bone15, 14, 86, -2.116F, -0.3349F, -5.5F, 1, 2, 11, 0.0F, false));

        bone16 = new ModelRenderer(this);
        bone16.setRotationPoint(0.0F, -34.5F, 0.0F);
        vss.addChild(bone16);
        setRotationAngle(bone16, 0.0F, 0.0F, 0.5236F);
        bone16.cubeList.add(new ModelBox(bone16, 14, 86, -1.3481F, 1.6651F, -5.5F, 2, 2, 11, 0.0F, true));
        bone16.cubeList.add(new ModelBox(bone16, 14, 86, -0.9821F, 1.299F, 5.5F, 2, 1, 9, 0.0F, true));
        bone16.cubeList.add(new ModelBox(bone16, 14, 86, 0.616F, -0.201F, -5.5F, 1, 1, 20, 0.0F, true));
        bone16.cubeList.add(new ModelBox(bone16, 14, 86, 1.116F, -0.3349F, -5.5F, 1, 2, 11, 0.0F, true));

        bone12 = new ModelRenderer(this);
        bone12.setRotationPoint(0.0F, -36.0F, -11.0F);
        vss.addChild(bone12);
        setRotationAngle(bone12, 0.0F, 0.0F, -0.7854F);
        bone12.cubeList.add(new ModelBox(bone12, 14, 86, -3.0607F, 2.5962F, -5.5F, 2, 2, 11, 0.0F, false));
        bone12.cubeList.add(new ModelBox(bone12, 14, 86, -4.5962F, 1.0607F, -5.5F, 2, 2, 11, 0.0F, false));

        bone11 = new ModelRenderer(this);
        bone11.setRotationPoint(0.0F, -25.0F, -11.0F);
        vss.addChild(bone11);
        setRotationAngle(bone11, -0.1745F, 0.0F, 0.0F);
        bone11.cubeList.add(new ModelBox(bone11, 14, 86, -2.5F, -4.969F, -6.2847F, 5, 1, 5, 0.0F, false));
        bone11.cubeList.add(new ModelBox(bone11, 14, 86, -2.5F, -5.969F, -1.2847F, 5, 2, 7, 0.0F, false));
        bone11.cubeList.add(new ModelBox(bone11, 14, 86, -1.0858F, -4.5548F, -6.2847F, 2, 2, 12, 0.0F, false));
        bone11.cubeList.add(new ModelBox(bone11, 14, 86, 0.0858F, -4.5548F, -6.2847F, 1, 2, 12, 0.0F, true));

        bone13 = new ModelRenderer(this);
        bone13.setRotationPoint(0.0F, -3.4842F, -3.611F);
        bone11.addChild(bone13);
        setRotationAngle(bone13, 0.0F, 0.0F, 0.7854F);
        bone13.cubeList.add(new ModelBox(bone13, 14, 86, -0.575F, -2.1106F, -2.6736F, 2, 2, 12, 0.0F, false));
        bone13.cubeList.add(new ModelBox(bone13, 14, 86, -2.1106F, -0.575F, -2.6736F, 2, 2, 12, 0.0F, false));

        bone18 = new ModelRenderer(this);
        bone18.setRotationPoint(0.0F, -26.6F, 6.075F);
        vss.addChild(bone18);
        setRotationAngle(bone18, 0.2618F, 0.0F, 0.0F);
        bone18.cubeList.add(new ModelBox(bone18, 14, 86, -0.5F, -1.0F, -0.5F, 1, 2, 1, 0.0F, false));

        bone17 = new ModelRenderer(this);
        bone17.setRotationPoint(0.0F, -35.5F, 13.0F);
        vss.addChild(bone17);
        setRotationAngle(bone17, 0.2618F, 0.0F, 0.0F);
        bone17.cubeList.add(new ModelBox(bone17, 14, 86, -1.0F, 1.9937F, 0.1501F, 2, 1, 1, 0.0F, false));
        bone17.cubeList.add(new ModelBox(bone17, 14, 86, -2.0F, 2.9937F, 0.2501F, 4, 2, 1, 0.0F, false));

        bone9 = new ModelRenderer(this);
        bone9.setRotationPoint(-1.0F, -30.4904F, 174.0006F);
        vss.addChild(bone9);
        setRotationAngle(bone9, 0.0F, 0.0F, 0.5236F);
        bone9.cubeList.add(new ModelBox(bone9, 162, 156, -0.616F, -0.067F, -149.75F, 1, 1, 7, 0.0F, true));
        bone9.cubeList.add(new ModelBox(bone9, 162, 156, -0.616F, -0.067F, -142.75F, 1, 1, 1, 0.0F, true));
        bone9.cubeList.add(new ModelBox(bone9, 162, 156, 0.9794F, -1.5643F, -149.75F, 1, 1, 7, 0.0F, true));
        bone9.cubeList.add(new ModelBox(bone9, 162, 156, 0.9832F, -1.5644F, -142.75F, 1, 1, 1, 0.0F, true));
        bone9.cubeList.add(new ModelBox(bone9, 162, 156, 0.9794F, -1.5643F, -158.75F, 1, 1, 9, 0.0F, true));
        bone9.cubeList.add(new ModelBox(bone9, 162, 156, -0.616F, -0.067F, -158.75F, 1, 1, 9, 0.0F, true));

        bone8 = new ModelRenderer(this);
        bone8.setRotationPoint(1.0F, -30.4904F, 174.0006F);
        vss.addChild(bone8);
        setRotationAngle(bone8, 0.0F, 0.0F, -0.5236F);
        bone8.cubeList.add(new ModelBox(bone8, 162, 156, -0.384F, -0.067F, -149.75F, 1, 1, 7, 0.0F, false));
        bone8.cubeList.add(new ModelBox(bone8, 162, 156, -0.3876F, -0.068F, -142.75F, 1, 1, 1, 0.0F, false));
        bone8.cubeList.add(new ModelBox(bone8, 162, 156, -1.9821F, -1.567F, -149.75F, 1, 1, 7, 0.0F, false));
        bone8.cubeList.add(new ModelBox(bone8, 162, 156, -1.9871F, -1.5649F, -142.75F, 1, 1, 1, 0.0F, false));
        bone8.cubeList.add(new ModelBox(bone8, 162, 156, -1.9821F, -1.567F, -158.75F, 1, 1, 9, 0.0F, false));
        bone8.cubeList.add(new ModelBox(bone8, 162, 156, -0.3876F, -0.068F, -158.75F, 1, 1, 9, 0.0F, false));

        bone6 = new ModelRenderer(this);
        bone6.setRotationPoint(-1.0F, 0.0F, 0.0F);
        vss.addChild(bone6);
        setRotationAngle(bone6, 0.1745F, 0.0F, 0.0F);
        bone6.cubeList.add(new ModelBox(bone6, 162, 156, -1.0F, -26.5594F, 15.5358F, 4, 7, 3, 0.0F, false));
        bone6.cubeList.add(new ModelBox(bone6, 162, 156, -0.5F, -24.2223F, 24.1188F, 3, 6, 3, 0.0F, false));
        bone6.cubeList.add(new ModelBox(bone6, 162, 156, -0.5F, -22.6984F, 32.7615F, 3, 5, 1, 0.0F, false));
        bone6.cubeList.add(new ModelBox(bone6, 162, 156, -0.5F, -26.5594F, 15.0358F, 3, 8, 4, 0.0F, false));

        bone19 = new ModelRenderer(this);
        bone19.setRotationPoint(-1.366F, -37.8547F, -0.3726F);
        vss.addChild(bone19);
        setRotationAngle(bone19, 0.0F, 0.0F, -0.7854F);
        bone19.cubeList.add(new ModelBox(bone19, 88, 81, 1.6985F, 0.6476F, -4.5F, 1, 2, 9, 0.0F, false));
        bone19.cubeList.add(new ModelBox(bone19, 88, 81, 1.6985F, 0.6476F, 13.5F, 1, 2, 4, 0.0F, false));
        bone19.cubeList.add(new ModelBox(bone19, 88, 81, 1.1985F, 1.1476F, -14.5F, 1, 1, 10, 0.0F, false));
        bone19.cubeList.add(new ModelBox(bone19, 88, 81, -0.2157F, -0.2666F, -14.5F, 1, 1, 10, 0.0F, false));
        bone19.cubeList.add(new ModelBox(bone19, 88, 81, -0.2157F, 2.5618F, -14.5F, 1, 1, 10, 0.0F, false));
        bone19.cubeList.add(new ModelBox(bone19, 88, 81, -1.6299F, 1.1476F, -14.5F, 1, 1, 10, 0.0F, false));
        bone19.cubeList.add(new ModelBox(bone19, 88, 81, -0.7157F, 3.0618F, -4.5F, 2, 1, 9, 0.0F, false));
        bone19.cubeList.add(new ModelBox(bone19, 88, 81, -0.7157F, 3.0618F, 13.5F, 2, 1, 4, 0.0F, false));
        bone19.cubeList.add(new ModelBox(bone19, 88, 81, -0.7157F, -0.7666F, -4.5F, 2, 1, 9, 0.0F, false));
        bone19.cubeList.add(new ModelBox(bone19, 88, 81, -0.7157F, -0.7666F, 13.5F, 2, 1, 4, 0.0F, false));
        bone19.cubeList.add(new ModelBox(bone19, 88, 81, -2.1299F, 0.6476F, -4.5F, 1, 2, 9, 0.0F, false));
        bone19.cubeList.add(new ModelBox(bone19, 88, 81, -2.1299F, 0.6476F, 13.5F, 1, 2, 4, 0.0F, false));

        bone20 = new ModelRenderer(this);
        bone20.setRotationPoint(1.9142F, -36.8907F, 17.6274F);
        vss.addChild(bone20);
        setRotationAngle(bone20, 0.0F, 0.2618F, 0.0F);
        bone20.cubeList.add(new ModelBox(bone20, 88, 81, 0.6476F, -1.0F, -8.2173F, 1, 2, 4, 0.0F, true));

        bone23 = new ModelRenderer(this);
        bone23.setRotationPoint(1.9142F, -36.8907F, 17.6274F);
        vss.addChild(bone23);
        setRotationAngle(bone23, 0.0F, 0.2618F, -0.7854F);
        bone23.cubeList.add(new ModelBox(bone23, 88, 81, 1.1892F, -2.3536F, -8.0721F, 1, 2, 4, 0.0F, true));

        bone21 = new ModelRenderer(this);
        bone21.setRotationPoint(-1.9142F, -36.8907F, 17.6274F);
        vss.addChild(bone21);
        setRotationAngle(bone21, 0.0F, -0.2618F, 0.0F);
        bone21.cubeList.add(new ModelBox(bone21, 88, 81, -1.6476F, -1.0F, -8.2173F, 1, 2, 4, 0.0F, false));

        bone22 = new ModelRenderer(this);
        bone22.setRotationPoint(-1.9142F, -36.8907F, 17.6274F);
        vss.addChild(bone22);
        setRotationAngle(bone22, 0.0F, -0.2618F, 0.7854F);
        bone22.cubeList.add(new ModelBox(bone22, 88, 81, -2.1892F, -2.3536F, -8.0721F, 1, 2, 4, 0.0F, false));

        bone26 = new ModelRenderer(this);
        bone26.setRotationPoint(-1.9142F, 36.8907F, 17.6274F);
        vss.addChild(bone26);
        setRotationAngle(bone26, 0.0F, -0.2618F, -0.7854F);
        bone26.cubeList.add(new ModelBox(bone26, 88, 81, 48.2044F, -51.8178F, -21.5751F, 1, 2, 4, 0.0F, false));

        bone27 = new ModelRenderer(this);
        bone27.setRotationPoint(1.9142F, 36.8907F, 17.6274F);
        vss.addChild(bone27);
        setRotationAngle(bone27, 0.0F, 0.2618F, 0.7854F);
        bone27.cubeList.add(new ModelBox(bone27, 88, 81, -49.2044F, -51.8178F, -21.5751F, 1, 2, 4, 0.0F, true));

        bone24 = new ModelRenderer(this);
        bone24.setRotationPoint(0.0F, -38.8049F, 17.6274F);
        vss.addChild(bone24);
        setRotationAngle(bone24, 0.2618F, 0.0F, 0.0F);
        bone24.cubeList.add(new ModelBox(bone24, 88, 81, -1.0F, -1.6476F, -8.2173F, 2, 1, 4, 0.0F, true));

        bone25 = new ModelRenderer(this);
        bone25.setRotationPoint(0.0F, -44.1951F, 17.6274F);
        vss.addChild(bone25);
        setRotationAngle(bone25, -0.2618F, 0.0F, 0.0F);
        bone25.cubeList.add(new ModelBox(bone25, 88, 81, -1.0F, 9.5522F, -5.8313F, 2, 1, 4, 0.0F, true));

        bone14 = new ModelRenderer(this);
        bone14.setRotationPoint(0.0F, -33.4207F, -37.0766F);
        vss.addChild(bone14);
        setRotationAngle(bone14, -0.4363F, 0.0F, 0.0F);
        bone14.cubeList.add(new ModelBox(bone14, 14, 86, -0.5F, -1.8068F, -0.4616F, 1, 2, 1, 0.0F, false));

        bone7 = new ModelRenderer(this);
        bone7.setRotationPoint(-2.0F, -31.0547F, -32.5766F);
        vss.addChild(bone7);
        setRotationAngle(bone7, 0.0F, 0.0F, 0.5236F);
        bone7.cubeList.add(new ModelBox(bone7, 84, 21, -0.134F, -1.5F, -6.0F, 1, 1, 15, 0.0F, false));
        bone7.cubeList.add(new ModelBox(bone7, 88, 81, -3.052F, -6.5541F, 35.88F, 1, 1, 10, 0.0F, false));
        bone7.cubeList.add(new ModelBox(bone7, 84, 21, -0.134F, -1.5F, 9.0F, 1, 1, 15, 0.0F, false));
        bone7.cubeList.add(new ModelBox(bone7, 84, 21, 1.2321F, -2.866F, -6.0F, 1, 1, 15, 0.0F, false));
        bone7.cubeList.add(new ModelBox(bone7, 88, 81, -1.6859F, -7.9201F, 35.88F, 1, 1, 10, 0.0F, false));
        bone7.cubeList.add(new ModelBox(bone7, 84, 21, 1.2321F, -2.866F, 9.0F, 1, 1, 15, 0.0F, false));
        bone7.cubeList.add(new ModelBox(bone7, 84, 21, 2.5981F, -1.5F, -6.0F, 1, 1, 15, 0.0F, false));
        bone7.cubeList.add(new ModelBox(bone7, 88, 81, -0.3199F, -6.5541F, 35.88F, 1, 1, 10, 0.0F, false));
        bone7.cubeList.add(new ModelBox(bone7, 84, 21, 2.5981F, -1.5F, 9.0F, 1, 1, 15, 0.0F, false));
        bone7.cubeList.add(new ModelBox(bone7, 84, 21, 1.2321F, -0.134F, -6.0F, 1, 1, 15, 0.0F, false));
        bone7.cubeList.add(new ModelBox(bone7, 88, 81, -1.6859F, -5.1881F, 35.88F, 1, 1, 10, 0.0F, false));
        bone7.cubeList.add(new ModelBox(bone7, 84, 21, 1.2321F, -0.134F, 9.0F, 1, 1, 15, 0.0F, false));

        bone10 = new ModelRenderer(this);
        bone10.setRotationPoint(2.0F, -31.0547F, -32.5766F);
        vss.addChild(bone10);
        setRotationAngle(bone10, 0.0F, 0.0F, -0.5236F);
        bone10.cubeList.add(new ModelBox(bone10, 84, 21, -2.2321F, -2.866F, -6.0F, 1, 1, 15, 0.0F, true));
        bone10.cubeList.add(new ModelBox(bone10, 88, 81, 0.6859F, -7.9201F, 35.88F, 1, 1, 10, 0.0F, true));
        bone10.cubeList.add(new ModelBox(bone10, 84, 21, -2.2321F, -2.866F, 9.0F, 1, 1, 15, 0.0F, true));
        bone10.cubeList.add(new ModelBox(bone10, 84, 21, -0.866F, -1.5F, -6.0F, 1, 1, 15, 0.0F, true));
        bone10.cubeList.add(new ModelBox(bone10, 88, 81, 2.052F, -6.5541F, 35.88F, 1, 1, 10, 0.0F, true));
        bone10.cubeList.add(new ModelBox(bone10, 84, 21, -0.866F, -1.5F, 9.0F, 1, 1, 15, 0.0F, true));
        bone10.cubeList.add(new ModelBox(bone10, 84, 21, -3.5981F, -1.5F, -6.0F, 1, 1, 15, 0.0F, true));
        bone10.cubeList.add(new ModelBox(bone10, 88, 81, -0.6801F, -6.5541F, 35.88F, 1, 1, 10, 0.0F, true));
        bone10.cubeList.add(new ModelBox(bone10, 84, 21, -3.5981F, -1.5F, 9.0F, 1, 1, 15, 0.0F, true));
        bone10.cubeList.add(new ModelBox(bone10, 84, 21, -2.2321F, -0.134F, -6.0F, 1, 1, 15, 0.0F, true));
        bone10.cubeList.add(new ModelBox(bone10, 88, 81, 0.6859F, -5.1881F, 35.88F, 1, 1, 10, 0.0F, true));
        bone10.cubeList.add(new ModelBox(bone10, 84, 21, -2.2321F, -0.134F, 9.0F, 1, 1, 15, 0.0F, true));

        bone = new ModelRenderer(this);
        bone.setRotationPoint(-1.0F, 0.0F, 0.0F);
        vss.addChild(bone);
        setRotationAngle(bone, 0.0F, -0.4363F, 0.0F);
        bone.cubeList.add(new ModelBox(bone, 14, 86, 4.9496F, -31.0F, 3.2942F, 1, 3, 3, 0.0F, false));

        bone5 = new ModelRenderer(this);
        bone5.setRotationPoint(1.0F, 0.0F, 0.0F);
        vss.addChild(bone5);
        setRotationAngle(bone5, 0.0F, 0.4363F, 0.0F);
        bone5.cubeList.add(new ModelBox(bone5, 14, 86, -5.9496F, -31.0F, 3.1942F, 1, 3, 3, 0.0F, true));
        this.initAnimations();
    }
}

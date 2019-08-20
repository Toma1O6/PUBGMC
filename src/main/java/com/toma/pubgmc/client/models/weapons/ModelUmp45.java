package com.toma.pubgmc.client.models.weapons;

import com.toma.pubgmc.animation.HeldAnimation;
import com.toma.pubgmc.animation.HeldAnimation.HeldStyle;
import com.toma.pubgmc.animation.ReloadAnimation;
import com.toma.pubgmc.animation.ReloadAnimation.ReloadStyle;
import com.toma.pubgmc.client.models.ModelGun;
import com.toma.pubgmc.client.util.ModelHelper;
import com.toma.pubgmc.client.util.ModelTransformationHelper;
import com.toma.pubgmc.common.capability.IPlayerData;
import com.toma.pubgmc.common.capability.IPlayerData.PlayerDataProvider;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.item.ItemStack;

public class ModelUmp45 extends ModelGun {
    private final ModelRenderer receiver;
    private final ModelRenderer bone;
    private final ModelRenderer bone2;
    private final ModelRenderer bone3;
    private final ModelRenderer grip;
    private final ModelRenderer bone4;
    private final ModelRenderer bone5;
    private final ModelRenderer bone6;
    private final ModelRenderer magazine;
    private final ModelRenderer stock;
    private final ModelRenderer bone11;
    private final ModelRenderer bone7;
    private final ModelRenderer bone8;
    private final ModelRenderer bone9;
    private final ModelRenderer sights;
    private final ModelRenderer bone10;
    private final ModelRenderer bone12;
    private final ModelRenderer bone13;
    private final ModelRenderer bone14;
    private final ModelRenderer barrel;

    // Created by OfficialMajonaise
    public ModelUmp45() {
        textureWidth = 256;
        textureHeight = 256;

        receiver = new ModelRenderer(this);
        receiver.setRotationPoint(0.0F, 24.0F, 0.0F);

        bone = new ModelRenderer(this);
        bone.setRotationPoint(0.0F, 0.0F, 0.0F);
        receiver.addChild(bone);
        bone.cubeList.add(new ModelBox(bone, 69, 10, -2.0F, -9.5F, -13.0F, 4, 2, 23, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 69, 10, -1.5F, -8.0F, -4.0F, 3, 4, 9, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 69, 10, -1.5F, -7.5F, 7.0F, 3, 1, 3, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 69, 10, -2.0F, -12.5F, -24.0F, 4, 4, 11, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 69, 10, -1.5F, -13.0F, -24.0F, 3, 5, 11, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 69, 10, -2.0F, -11.5F, -13.0F, 4, 2, 7, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 69, 10, -2.0F, -11.5F, 1.0F, 4, 2, 9, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 69, 10, -1.0F, -11.5F, -6.0F, 3, 2, 7, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 19, 20, -1.85F, -11.5F, -5.8F, 1, 2, 7, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 69, 10, -2.0F, -13.5F, -14.0F, 4, 2, 24, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 69, 10, -1.5F, -14.0F, -13.75F, 3, 1, 22, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 4, 89, -1.7F, -11.7F, -12.6F, 4, 4, 22, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 4, 89, -2.3F, -8.7F, -12.6F, 4, 1, 22, 0.0F, true));
        bone.cubeList.add(new ModelBox(bone, 4, 89, -2.3F, -11.7F, 1.4F, 4, 3, 8, 0.0F, true));
        bone.cubeList.add(new ModelBox(bone, 4, 89, -2.3F, -11.7F, -12.6F, 4, 3, 6, 0.0F, true));

        bone2 = new ModelRenderer(this);
        bone2.setRotationPoint(0.0F, 0.0F, -7.0F);
        setRotationAngle(bone2, -0.1745F, 0.0F, 0.0F);
        receiver.addChild(bone2);
        bone2.cubeList.add(new ModelBox(bone2, 69, 10, -2.0F, -8.3755F, -3.3077F, 4, 6, 6, 0.0F, false));
        bone2.cubeList.add(new ModelBox(bone2, 69, 10, -1.5F, -5.3755F, 2.6923F, 3, 3, 3, 0.0F, false));
        bone2.cubeList.add(new ModelBox(bone2, 36, 146, -1.0F, -2.3755F, 2.4923F, 2, 2, 1, 0.0F, false));

        bone3 = new ModelRenderer(this);
        bone3.setRotationPoint(0.0F, 0.0F, 0.0F);
        setRotationAngle(bone3, 0.6109F, 0.0F, 0.0F);
        receiver.addChild(bone3);
        bone3.cubeList.add(new ModelBox(bone3, 69, 10, -1.5F, -3.4087F, 6.3901F, 3, 3, 5, 0.0F, false));

        grip = new ModelRenderer(this);
        grip.setRotationPoint(0.0F, 24.0F, 0.0F);

        bone4 = new ModelRenderer(this);
        bone4.setRotationPoint(0.0F, -1.0F, 0.75F);
        setRotationAngle(bone4, 0.4363F, 0.0F, 0.0F);
        grip.addChild(bone4);
        bone4.cubeList.add(new ModelBox(bone4, 105, 48, -1.0F, -2.2289F, 2.8445F, 2, 7, 4, 0.0F, false));
        bone4.cubeList.add(new ModelBox(bone4, 105, 48, -0.5F, -0.4789F, 0.8445F, 1, 1, 2, 0.0F, false));

        bone5 = new ModelRenderer(this);
        bone5.setRotationPoint(0.0F, -1.0F, 0.75F);
        setRotationAngle(bone5, -0.0873F, 0.0F, 0.0F);
        grip.addChild(bone5);
        bone5.cubeList.add(new ModelBox(bone5, 105, 48, -0.5F, -0.9709F, -2.0081F, 1, 1, 3, 0.0F, false));

        bone6 = new ModelRenderer(this);
        bone6.setRotationPoint(0.0F, -1.0F, 0.75F);
        setRotationAngle(bone6, -0.6109F, 0.0F, 0.0F);
        grip.addChild(bone6);
        bone6.cubeList.add(new ModelBox(bone6, 105, 48, -0.5F, 0.0292F, -2.7245F, 1, 1, 1, 0.0F, false));

        magazine = new ModelRenderer(this);
        magazine.setRotationPoint(0.0F, 24.0F, -7.0F);
        setRotationAngle(magazine, -0.1745F, 0.0F, 0.0F);
        magazine.cubeList.add(new ModelBox(magazine, 89, 94, -1.5F, -9.3755F, -2.8077F, 3, 20, 5, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 89, 94, -1.5F, 9.6245F, -3.8077F, 3, 1, 1, 0.0F, false));

        stock = new ModelRenderer(this);
        stock.setRotationPoint(0.0F, 23.0F, 0.0F);

        bone11 = new ModelRenderer(this);
        bone11.setRotationPoint(-9.8F, 0.8F, 6.7F);
        setRotationAngle(bone11, 0.0F, 0.7854F, 0.0F);
        stock.addChild(bone11);
        bone11.cubeList.add(new ModelBox(bone11, 25, 93, 4.5F, -11.2F, 10.0F, 1, 3, 1, 0.0F, false));

        bone7 = new ModelRenderer(this);
        bone7.setRotationPoint(0.0F, 0.0F, 0.0F);
        stock.addChild(bone7);
        bone7.cubeList.add(new ModelBox(bone7, 25, 93, -1.5F, -11.2F, 10.0F, 3, 4, 1, 0.0F, false));
        bone7.cubeList.add(new ModelBox(bone7, 25, 93, -1.0F, -8.4F, 13.1641F, 2, 2, 1, 0.0F, false));
        bone7.cubeList.add(new ModelBox(bone7, 25, 93, -1.0F, -8.4F, 14.1641F, 2, 3, 1, 0.0F, false));
        bone7.cubeList.add(new ModelBox(bone7, 25, 93, -1.5F, -9.4F, 15.1641F, 3, 1, 8, 0.0F, false));
        bone7.cubeList.add(new ModelBox(bone7, 25, 93, -1.0F, -8.4F, 18.1641F, 2, 5, 1, 0.0F, false));
        bone7.cubeList.add(new ModelBox(bone7, 25, 93, -1.5F, -9.4F, 23.1641F, 3, 8, 1, 0.0F, false));
        bone7.cubeList.add(new ModelBox(bone7, 25, 93, -1.5F, -2.7417F, 21.6743F, 3, 1, 2, 0.0F, false));
        bone7.cubeList.add(new ModelBox(bone7, 25, 93, -1.5F, -9.4F, 13.1641F, 3, 1, 2, 0.0F, false));

        bone8 = new ModelRenderer(this);
        bone8.setRotationPoint(0.0F, -4.2F, 13.5F);
        setRotationAngle(bone8, 1.0472F, 0.0F, 0.0F);
        stock.addChild(bone8);
        bone8.cubeList.add(new ModelBox(bone8, 25, 93, -1.5F, -5.6651F, 2.8122F, 3, 4, 2, 0.0F, false));
        bone8.cubeList.add(new ModelBox(bone8, 25, 93, -1.5F, -4.6651F, 1.3481F, 3, 3, 2, 0.0F, false));
        bone8.cubeList.add(new ModelBox(bone8, 25, 93, -1.5F, -1.6651F, 1.3481F, 3, 3, 1, 0.0F, false));

        bone9 = new ModelRenderer(this);
        bone9.setRotationPoint(0.0F, -4.2F, 13.5F);
        setRotationAngle(bone9, 1.1345F, 0.0F, 0.0F);
        stock.addChild(bone9);
        bone9.cubeList.add(new ModelBox(bone9, 25, 93, -1.5F, 1.4473F, 1.2266F, 3, 7, 1, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 25, 93, -1.0F, -4.4304F, -0.0694F, 2, 1, 2, 0.0F, false));

        sights = new ModelRenderer(this);
        sights.setRotationPoint(0.0F, 24.0F, 0.0F);
        sights.cubeList.add(new ModelBox(sights, 87, 41, -2.0F, -14.5F, 7.6F, 4, 1, 2, 0.0F, false));
        sights.cubeList.add(new ModelBox(sights, 87, 41, 0.9F, -15.5F, 7.6F, 1, 1, 2, 0.0F, false));
        sights.cubeList.add(new ModelBox(sights, 87, 41, 0.3F, -15.2F, 8.2F, 1, 1, 1, 0.0F, false));
        sights.cubeList.add(new ModelBox(sights, 87, 41, -1.3F, -15.2F, 8.2F, 1, 1, 1, 0.0F, false));
        sights.cubeList.add(new ModelBox(sights, 87, 41, -1.9F, -15.5F, 7.6F, 1, 1, 2, 0.0F, false));
        sights.cubeList.add(new ModelBox(sights, 87, 41, -0.5F, -15.5962F, -22.8128F, 1, 2, 1, 0.0F, false));

        bone10 = new ModelRenderer(this);
        bone10.setRotationPoint(1.4F, -10.0F, 8.6F);
        setRotationAngle(bone10, -1.309F, 0.0F, 0.0F);
        sights.addChild(bone10);
        bone10.cubeList.add(new ModelBox(bone10, 87, 41, -0.5F, -2.3894F, -5.0538F, 1, 1, 2, 0.0F, false));
        bone10.cubeList.add(new ModelBox(bone10, 87, 41, -3.3F, -2.3894F, -5.0538F, 1, 1, 2, 0.0F, false));

        bone12 = new ModelRenderer(this);
        bone12.setRotationPoint(0.0F, -9.0F, -22.9F);
        setRotationAngle(bone12, 1.1345F, 0.0F, 0.0F);
        sights.addChild(bone12);
        bone12.cubeList.add(new ModelBox(bone12, 87, 41, -0.5F, -1.4604F, 4.4979F, 1, 1, 1, 0.0F, false));

        bone13 = new ModelRenderer(this);
        bone13.setRotationPoint(0.0F, -9.0F, -22.9F);
        setRotationAngle(bone13, 0.5236F, 0.0F, 0.0F);
        sights.addChild(bone13);
        bone13.cubeList.add(new ModelBox(bone13, 87, 41, -0.5F, -3.5306F, 2.2396F, 1, 3, 2, 0.0F, false));

        bone14 = new ModelRenderer(this);
        bone14.setRotationPoint(0.0F, -9.0F, -22.4F);
        setRotationAngle(bone14, -0.0873F, 0.0F, 0.0F);
        sights.addChild(bone14);
        bone14.cubeList.add(new ModelBox(bone14, 87, 41, -0.5F, -6.5351F, -0.9862F, 1, 4, 1, 0.0F, false));

        barrel = new ModelRenderer(this);
        barrel.setRotationPoint(0.0F, 24.0F, 0.0F);
        barrel.cubeList.add(new ModelBox(barrel, 30, 139, -1.5F, -11.0F, -31.0F, 2, 2, 7, 0.0F, false));
        barrel.cubeList.add(new ModelBox(barrel, 30, 139, -2.0F, -11.5F, -26.5F, 3, 3, 2, 0.0F, false));
        this.initAnimations();
    }

    @Override
    public String textureName() {
        return "ump45";
    }

    @Override
    public void initAnimations() {
        initAimAnimation(-0.56f, 0.2f, 0.14f);
        initAimingAnimationStates(0.2f, 0.12f, 0.08f);
        heldAnimation = new HeldAnimation(HeldStyle.SMALL);
        reloadAnimation = new ReloadAnimation(magazine, ReloadStyle.MAGAZINE).initMovement(DEFAULT_PART_ANIMATION);
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
            renderUmp(data.isAiming(), stack);
            GlStateManager.popMatrix();
        }
    }

    private void renderUmp(boolean aim, ItemStack stack) {
        GlStateManager.pushMatrix();
        ModelTransformationHelper.defaultSMGTransform();
        GlStateManager.translate(0.0, -8.0, 0.0);

        if (aim && enableADS(stack)) {
            rotateModelForADSRendering();
        }

        renderAll();
        GlStateManager.popMatrix();

        renderSMGSilencer(0, 0, 0, 1f, stack);
        renderVerticalGrip(0, -13, 21, 0.7f, stack);
        renderAngledGrip(0, -8, 20, 0.8f, stack);
        renderRedDot(0, -5.95, 16, 1f, stack);
        renderHolo(-0.05, -0.05, 3, 0.9f, stack);
        renderScope2X(0, -3, 7, 1f, stack);
        renderScope4X(0, -3.95, 5, 1f, stack);
    }

    private void renderAll() {
        receiver.render(1f);
        grip.render(1f);
        magazine.render(1f);
        stock.render(1f);
        sights.render(1f);
        barrel.render(1f);
    }
}

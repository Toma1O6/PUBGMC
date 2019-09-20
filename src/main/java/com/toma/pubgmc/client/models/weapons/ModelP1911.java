package com.toma.pubgmc.client.models.weapons;

import com.toma.pubgmc.animation.HeldAnimation;
import com.toma.pubgmc.animation.HeldAnimation.HeldStyle;
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

public class ModelP1911 extends ModelGun {

    private final ModelRenderer bone;
    private final ModelRenderer magazine;

    @Override
    public String textureName() {
        return "p92";
    }

    @Override
    public void initAnimations() {
        initAimAnimation(-0.56f, 0.275f, -0.01f, 2.5F);
        initAimingAnimationStates(0.275f, 0.185f, 0f);
        heldAnimation = new HeldAnimation(HeldStyle.SMALL);
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
                renderP1911(data.isAiming(), stack);
            }
            GlStateManager.popMatrix();
        }
    }

    private void renderP1911(boolean aim, ItemStack stack) {
        GlStateManager.pushMatrix();
        {
            ModelTransformationHelper.defaultPistolTransform();
            GlStateManager.translate(-0.03, -8.94, -15.0);
            renderAll();
        }
        GlStateManager.popMatrix();

        renderRedDot(0, 0.81, 1, 1f, stack);
        renderPistolSilencer(-1, -1, -5, 1f, stack);
    }

    private void renderAll() {
        bone.render(1f);
        magazine.render(1f);
    }

    public ModelP1911() {
        super();
        textureWidth = 128;
        textureHeight = 128;

        bone = new ModelRenderer(this);
        bone.setRotationPoint(0.0F, 24.0F, 0.0F);
        bone.cubeList.add(new ModelBox(bone, 69, 13, 0.6523F, -2.0742F, -10.0F, 1, 2, 9, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 69, 13, -1.6523F, -2.0742F, -10.0F, 1, 2, 9, 0.0F, true));
        bone.cubeList.add(new ModelBox(bone, 69, 13, -0.9452F, -0.3671F, -10.0F, 1, 1, 9, 0.0F, true));
        bone.cubeList.add(new ModelBox(bone, 69, 13, -0.0548F, -0.3671F, -10.0F, 1, 1, 9, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 69, 13, -2.0F, -0.975F, -1.0F, 4, 2, 3, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 69, 13, -1.0F, 4.6231F, 2.7321F, 2, 1, 7, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 69, 13, -1.5F, 1.025F, -0.5F, 3, 1, 1, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 69, 13, -1.5F, 1.5602F, -0.0508F, 3, 1, 1, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 91, 22, -2.0F, -0.9219F, 2.0F, 4, 1, 16, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 91, 22, -2.0F, 0.0781F, 8.625F, 4, 1, 7, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 91, 22, -2.0F, 1.0781F, 8.625F, 4, 1, 7, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 34, 40, -1.0F, -5.9F, -10.3516F, 2, 1, 11, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 35, 40, -1.0F, -5.9F, 0.6484F, 2, 1, 4, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 42, 45, 0.0F, -5.9F, 4.6484F, 1, 1, 7, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 13, 74, -0.8062F, -5.6688F, 4.6484F, 1, 1, 7, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 44, 48, -1.0F, -5.9F, 11.6484F, 2, 1, 7, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 3, 76, -1.5F, -6.318F, 16.6484F, 3, 1, 1, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 3, 76, 0.5F, -6.918F, 16.6484F, 1, 1, 1, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 3, 76, -1.5F, -6.918F, 16.6484F, 1, 1, 1, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 3, 76, -0.5F, -6.7867F, -9.7227F, 1, 1, 1, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 36, 40, -1.5F, -2.1679F, -10.3516F, 3, 2, 6, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 36, 40, -0.2071F, -0.4608F, -10.3516F, 1, 1, 10, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 36, 40, -0.7929F, -0.4608F, -10.3516F, 1, 1, 10, 0.0F, true));
        bone.cubeList.add(new ModelBox(bone, 34, 38, -2.366F, -4.534F, -10.3516F, 1, 4, 11, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 35, 40, -2.366F, -4.534F, 0.6484F, 1, 4, 4, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 35, 40, -2.366F, -3.534F, 4.6484F, 1, 3, 7, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 13, 74, -2.1723F, -4.3027F, 4.6484F, 1, 1, 7, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 44, 48, -2.366F, -4.534F, 11.6484F, 1, 4, 7, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 42, 43, 0.2148F, -3.1956F, 18.748F, 1, 2, 1, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 42, 43, -1.2148F, -3.1956F, 18.748F, 1, 2, 1, 0.0F, true));
        bone.cubeList.add(new ModelBox(bone, 42, 43, -0.7852F, -1.534F, 18.748F, 2, 1, 1, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 42, 43, -0.5F, -5.0082F, 17.9028F, 1, 1, 1, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 42, 43, -1.2148F, -1.534F, 18.748F, 1, 1, 1, 0.0F, true));
        bone.cubeList.add(new ModelBox(bone, 50, 50, -1.5F, -3.534F, 18.1609F, 3, 3, 1, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 42, 43, -2.0F, -3.534F, 17.9297F, 4, 3, 1, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 42, 43, 1.366F, -4.534F, -10.3516F, 1, 4, 11, 0.0F, true));
        bone.cubeList.add(new ModelBox(bone, 40, 9, -1.866F, -3.934F, -10.8516F, 1, 1, 1, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 40, 9, 0.866F, -3.934F, -10.8516F, 1, 1, 1, 0.0F, true));
        bone.cubeList.add(new ModelBox(bone, 40, 9, -0.5F, -5.3F, -10.8516F, 1, 1, 1, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 40, 9, -0.5F, -2.5679F, -10.8516F, 1, 1, 1, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 42, 43, 1.366F, -4.534F, 0.6484F, 1, 4, 8, 0.0F, true));
        bone.cubeList.add(new ModelBox(bone, 42, 43, 1.366F, -4.534F, 8.6484F, 1, 4, 5, 0.0F, true));
        bone.cubeList.add(new ModelBox(bone, 43, 42, 1.366F, -4.534F, 13.6484F, 1, 4, 5, 0.0F, true));
        bone.cubeList.add(new ModelBox(bone, 12, 78, -1.0F, -4.5F, -10.4492F, 2, 2, 1, 0.0F, false));

        ModelRenderer bone2 = new ModelRenderer(this);
        bone2.setRotationPoint(0.0F, 0.0F, 0.0F);
        setRotationAngle(bone2, 0.0F, 0.0F, -0.5236F);
        bone.addChild(bone2);
        bone2.cubeList.add(new ModelBox(bone2, 38, 43, 1.084F, -5.6095F, -10.3516F, 1, 1, 11, 0.0F, false));
        bone2.cubeList.add(new ModelBox(bone2, 46, 10, -0.149F, -3.4739F, -10.8516F, 1, 1, 1, 0.0F, false));
        bone2.cubeList.add(new ModelBox(bone2, 46, 10, 1.217F, -4.8399F, -10.8516F, 1, 1, 1, 0.0F, false));
        bone2.cubeList.add(new ModelBox(bone2, 46, 10, 1.217F, -2.1079F, -10.8516F, 1, 1, 1, 0.0F, false));
        bone2.cubeList.add(new ModelBox(bone2, 46, 10, 2.583F, -3.4739F, -10.8516F, 1, 1, 1, 0.0F, false));
        bone2.cubeList.add(new ModelBox(bone2, 35, 40, 1.084F, -5.6095F, 0.6484F, 1, 1, 4, 0.0F, false));
        bone2.cubeList.add(new ModelBox(bone2, 44, 48, 1.084F, -5.6095F, 11.6484F, 1, 1, 7, 0.0F, false));
        bone2.cubeList.add(new ModelBox(bone2, 13, 74, 1.1361F, -5.3124F, 4.6484F, 1, 1, 7, 0.0F, false));
        bone2.cubeList.add(new ModelBox(bone2, 36, 41, 3.316F, -3.7435F, -10.3516F, 1, 1, 11, 0.0F, false));
        bone2.cubeList.add(new ModelBox(bone2, 42, 43, 3.316F, -3.7435F, 0.6484F, 1, 1, 8, 0.0F, false));
        bone2.cubeList.add(new ModelBox(bone2, 42, 43, 3.316F, -3.7435F, 8.6484F, 1, 1, 5, 0.0F, false));
        bone2.cubeList.add(new ModelBox(bone2, 43, 42, 3.316F, -3.7435F, 13.6484F, 1, 1, 5, 0.0F, false));
        bone2.cubeList.add(new ModelBox(bone2, 36, 40, 1.45F, -1.5115F, -10.3516F, 1, 1, 6, 0.0F, false));
        bone2.cubeList.add(new ModelBox(bone2, 36, 40, -0.7821F, -3.3775F, -10.3516F, 1, 2, 6, 0.0F, false));

        ModelRenderer bone3 = new ModelRenderer(this);
        bone3.setRotationPoint(0.0F, 0.0F, 0.0F);
        setRotationAngle(bone3, 0.0F, 0.0F, 0.5236F);
        bone.addChild(bone3);
        bone3.cubeList.add(new ModelBox(bone3, 34, 38, -4.316F, -3.7435F, -10.3516F, 1, 1, 11, 0.0F, true));
        bone3.cubeList.add(new ModelBox(bone3, 46, 10, -3.583F, -3.4739F, -10.8516F, 1, 1, 1, 0.0F, true));
        bone3.cubeList.add(new ModelBox(bone3, 46, 10, -2.217F, -4.8399F, -10.8516F, 1, 1, 1, 0.0F, true));
        bone3.cubeList.add(new ModelBox(bone3, 46, 10, -0.851F, -3.4739F, -10.8516F, 1, 1, 1, 0.0F, true));
        bone3.cubeList.add(new ModelBox(bone3, 46, 10, -2.217F, -2.1079F, -10.8516F, 1, 1, 1, 0.0F, true));
        bone3.cubeList.add(new ModelBox(bone3, 35, 40, -4.316F, -3.7435F, 0.6484F, 1, 1, 4, 0.0F, true));
        bone3.cubeList.add(new ModelBox(bone3, 44, 48, -4.316F, -3.7435F, 11.6484F, 1, 1, 7, 0.0F, true));
        bone3.cubeList.add(new ModelBox(bone3, 13, 74, -4.0326F, -3.6401F, 4.6484F, 1, 1, 7, 0.0F, true));
        bone3.cubeList.add(new ModelBox(bone3, 36, 40, -0.2179F, -3.3775F, -10.3516F, 1, 2, 6, 0.0F, true));
        bone3.cubeList.add(new ModelBox(bone3, 36, 40, -2.45F, -1.5115F, -10.3516F, 1, 1, 6, 0.0F, true));
        bone3.cubeList.add(new ModelBox(bone3, 34, 40, -2.084F, -5.6095F, -10.3516F, 1, 1, 11, 0.0F, true));
        bone3.cubeList.add(new ModelBox(bone3, 42, 44, -2.084F, -5.6095F, 0.6484F, 1, 1, 8, 0.0F, true));
        bone3.cubeList.add(new ModelBox(bone3, 40, 40, -2.084F, -5.6095F, 8.6484F, 1, 1, 5, 0.0F, true));
        bone3.cubeList.add(new ModelBox(bone3, 43, 42, -2.084F, -5.6095F, 13.6484F, 1, 1, 5, 0.0F, true));

        ModelRenderer bone4 = new ModelRenderer(this);
        bone4.setRotationPoint(0.0F, -3.034F, 18.8711F);
        setRotationAngle(bone4, 0.2618F, 0.0F, 0.0F);
        bone.addChild(bone4);
        bone4.cubeList.add(new ModelBox(bone4, 42, 43, -1.5F, -2.3725F, -0.5898F, 3, 2, 1, 0.0F, false));
        bone4.cubeList.add(new ModelBox(bone4, 42, 43, -2.0F, -1.4678F, -0.814F, 4, 1, 1, 0.0F, false));
        bone4.cubeList.add(new ModelBox(bone4, 42, 42, -2.0F, -1.9678F, -0.814F, 4, 1, 1, 0.0F, false));

        ModelRenderer bone5 = new ModelRenderer(this);
        bone5.setRotationPoint(0.0F, -2.746F, 18.8722F);
        setRotationAngle(bone5, 0.4363F, 0.0F, 0.0F);
        bone.addChild(bone5);
        bone5.cubeList.add(new ModelBox(bone5, 42, 43, 0.2148F, -2.0373F, -0.0163F, 1, 2, 1, 0.0F, false));
        bone5.cubeList.add(new ModelBox(bone5, 42, 43, -1.2148F, -2.0373F, -0.0163F, 1, 2, 1, 0.0F, true));

        ModelRenderer bone6 = new ModelRenderer(this);
        bone6.setRotationPoint(0.0F, -2.2268F, 19.1777F);
        setRotationAngle(bone6, -0.3491F, 0.0F, 0.0F);
        bone.addChild(bone6);
        bone6.cubeList.add(new ModelBox(bone6, 17, 79, -0.5F, -2.2598F, -0.7766F, 1, 2, 1, 0.0F, false));
        bone6.cubeList.add(new ModelBox(bone6, 41, 42, 0.2148F, -2.5196F, -2.2096F, 1, 1, 1, 0.0F, false));
        bone6.cubeList.add(new ModelBox(bone6, 41, 42, -1.2148F, -2.5196F, -2.2096F, 2, 1, 1, 0.0F, true));

        ModelRenderer bone7 = new ModelRenderer(this);
        bone7.setRotationPoint(0.0F, -2.2268F, 19.1777F);
        setRotationAngle(bone7, -0.1745F, 0.0F, 0.0F);
        bone.addChild(bone7);
        bone7.cubeList.add(new ModelBox(bone7, 17, 79, -0.5F, -2.3603F, -0.3723F, 1, 1, 1, 0.0F, false));

        ModelRenderer bone8 = new ModelRenderer(this);
        bone8.setRotationPoint(-1.5664F, 0.0F, 0.0F);
        setRotationAngle(bone8, 0.0F, 0.0F, -0.7854F);
        bone.addChild(bone8);
        bone8.cubeList.add(new ModelBox(bone8, 36, 40, 0.1657F, -0.0718F, -10.3516F, 1, 1, 10, 0.0F, false));
        bone8.cubeList.add(new ModelBox(bone8, 36, 40, 1.287F, 1.0495F, -10.3516F, 1, 1, 10, 0.0F, false));

        ModelRenderer bone9 = new ModelRenderer(this);
        bone9.setRotationPoint(1.1523F, 2.3828F, -5.5F);
        setRotationAngle(bone9, 0.0F, 0.0F, -0.7854F);
        bone.addChild(bone9);
        bone9.cubeList.add(new ModelBox(bone9, 69, 13, 1.0909F, -2.3838F, -4.5F, 1, 1, 9, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 69, 13, -0.2458F, -3.7206F, -4.5F, 1, 1, 9, 0.0F, false));

        ModelRenderer bone10 = new ModelRenderer(this);
        bone10.setRotationPoint(-1.0F, 8.0F, 0.0F);
        setRotationAngle(bone10, 0.6109F, 0.0F, 0.0F);
        bone.addChild(bone10);
        bone10.cubeList.add(new ModelBox(bone10, 91, 22, -1.0F, 2.2921F, 16.7695F, 4, 1, 2, 0.0F, false));

        ModelRenderer bone11 = new ModelRenderer(this);
        bone11.setRotationPoint(0.0F, 10.7921F, 6.7695F);
        setRotationAngle(bone11, 0.7854F, 0.0F, 0.0F);
        bone.addChild(bone11);
        bone11.cubeList.add(new ModelBox(bone11, 91, 22, -2.0F, -11.2789F, 3.5338F, 4, 1, 2, 0.0F, false));
        bone11.cubeList.add(new ModelBox(bone11, 91, 22, -2.0F, -7.5567F, 8.1808F, 4, 2, 1, 0.0F, false));

        ModelRenderer bone12 = new ModelRenderer(this);
        bone12.setRotationPoint(0.5F, 0.0F, 0.0F);
        setRotationAngle(bone12, 0.5236F, 0.0F, 0.0F);
        bone.addChild(bone12);
        bone12.cubeList.add(new ModelBox(bone12, 69, 13, -2.0F, 1.5037F, -1.4455F, 3, 1, 1, 0.0F, false));
        bone12.cubeList.add(new ModelBox(bone12, 69, 13, -1.5F, 2.5037F, -1.4455F, 2, 2, 1, 0.0F, false));
        bone12.cubeList.add(new ModelBox(bone12, 69, 13, -1.5F, 8.2358F, 4.7506F, 2, 1, 1, 0.0F, false));
        bone12.cubeList.add(new ModelBox(bone12, 69, 13, -2.0F, 0.8327F, -0.7875F, 3, 1, 2, 0.0F, false));

        ModelRenderer bone13 = new ModelRenderer(this);
        bone13.setRotationPoint(0.5F, 0.0F, 0.0F);
        setRotationAngle(bone13, -0.5236F, 0.0F, 0.0F);
        bone.addChild(bone13);
        bone13.cubeList.add(new ModelBox(bone13, 69, 13, -1.5F, 2.5037F, 3.1776F, 2, 1, 2, 0.0F, false));
        bone13.cubeList.add(new ModelBox(bone13, 69, 13, -2.0F, 0.406F, 0.8343F, 3, 1, 1, 0.0F, false));

        ModelRenderer bone14 = new ModelRenderer(this);
        bone14.setRotationPoint(0.0F, 2.7781F, 12.125F);
        setRotationAngle(bone14, 0.2094F, 0.0F, 0.0F);
        bone.addChild(bone14);
        bone14.cubeList.add(new ModelBox(bone14, 91, 22, -1.5F, -1.5F, -3.0F, 3, 2, 6, 0.0F, false));
        bone14.cubeList.add(new ModelBox(bone14, 91, 22, -2.0F, 0.1F, -3.5F, 4, 7, 7, 0.0F, false));

        magazine = new ModelRenderer(this);
        magazine.setRotationPoint(-1.5F, 45.0F, 0.0F);
        setRotationAngle(magazine, 0.1047F, 0.0F, 0.0F);
        magazine.cubeList.add(new ModelBox(magazine, 0, 69, 0.0F, -9.1594F, 10.1863F, 3, 2, 7, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 0, 69, 0.0F, -20.1594F, 12.1863F, 3, 11, 5, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 0, 69, 1.0F, -20.8594F, 16.0863F, 1, 1, 1, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 8, 104, 1.0F, -21.4594F, 13.1863F, 1, 1, 3, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 0, 69, 1.0F, -20.9594F, 12.4863F, 1, 1, 1, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 0, 69, 2.0F, -21.1594F, 12.1863F, 1, 1, 5, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 0, 69, 0.0F, -21.1594F, 12.1863F, 1, 1, 5, 0.0F, false));

        ModelRenderer bone16 = new ModelRenderer(this);
        bone16.setRotationPoint(-0.5F, 0.0F, 0.0F);
        setRotationAngle(bone16, 0.0F, 0.0F, 0.1745F);
        magazine.addChild(bone16);
        bone16.cubeList.add(new ModelBox(bone16, 0, 69, -3.1819F, -21.9248F, 12.1863F, 1, 1, 5, 0.0F, false));

        ModelRenderer bone17 = new ModelRenderer(this);
        bone17.setRotationPoint(-0.5F, 0.0F, 0.0F);
        setRotationAngle(bone17, 0.0F, 0.0F, -0.1745F);
        magazine.addChild(bone17);
        bone17.cubeList.add(new ModelBox(bone17, 0, 69, 6.1211F, -21.2302F, 12.1863F, 1, 1, 5, 0.0F, false));

        ModelRenderer bone15 = new ModelRenderer(this);
        bone15.setRotationPoint(-1.5F, 0.0F, 0.0F);
        setRotationAngle(bone15, 0.1047F, 0.0F, 0.0F);
        bone.addChild(bone15);
        bone15.cubeList.add(new ModelBox(bone15, 91, 22, -0.5F, 2.9781F, 8.325F, 4, 2, 7, 0.0F, false));
        bone15.cubeList.add(new ModelBox(bone15, 91, 22, -0.5F, 10.7256F, 8.9912F, 4, 2, 7, 0.0F, false));
        initAnimations();
    }
}

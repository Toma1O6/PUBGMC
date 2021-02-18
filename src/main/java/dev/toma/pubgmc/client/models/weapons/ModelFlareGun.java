package dev.toma.pubgmc.client.models.weapons;

import dev.toma.pubgmc.animation.HeldAnimation;
import dev.toma.pubgmc.animation.HeldAnimation.HeldStyle;
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

public class ModelFlareGun extends ModelGun {

    private final ModelRenderer bone;
    private final ModelRenderer bone6;
    private final ModelRenderer bone7;
    private final ModelRenderer bone8;
    private final ModelRenderer bone5;
    private final ModelRenderer bone18;
    private final ModelRenderer hammer;
    private final ModelRenderer bone14;
    private final ModelRenderer bone15;
    private final ModelRenderer bone17;
    private final ModelRenderer bone16;
    private final ModelRenderer bone12;
    private final ModelRenderer bone13;
    private final ModelRenderer bone9;
    private final ModelRenderer bone10;
    private final ModelRenderer bone11;
    private final ModelRenderer bone4;
    private final ModelRenderer bone2;
    private final ModelRenderer bone3;

    @Override
    public String textureName() {
        return "flaregun";
    }

    @Override
    public void initAnimations() {
        initAimAnimation(-0.57f, 0.215f, 0.225f);
        initAimingAnimationStates(0.215f);
        heldAnimation = new HeldAnimation(HeldStyle.SMALL);
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
                renderFlareGun();
            }
            GlStateManager.popMatrix();
        }
    }

    private void renderFlareGun() {
        GlStateManager.pushMatrix();
        ModelTransformationHelper.defaultPistolTransform();
        GlStateManager.scale(0.39999992, 0.39999992, 0.39999992);
        GlStateManager.translate(-0.8750001, 8.4249935, 0.0);
        bone.render(1f);
        GlStateManager.popMatrix();
    }

    public ModelFlareGun() {
        textureWidth = 128;
        textureHeight = 128;

        bone = new ModelRenderer(this);
        bone.setRotationPoint(0.0F, 24.0F, 0.0F);
        bone.cubeList.add(new ModelBox(bone, 0, 0, -2.0F, -1.0F, -53.0F, 4, 1, 20, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 0, 0, -2.0F, -1.0F, -23.0F, 4, 1, 24, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 0, 0, -3.0F, -1.0F, -33.0F, 6, 4, 10, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 76, 93, -4.5F, 2.1367F, -27.0F, 9, 2, 2, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 0, 0, -3.0F, 3.0F, -33.0F, 6, 4, 6, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 0, 0, -3.5F, 0.0F, -23.0F, 7, 5, 4, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 0, 0, -3.5F, 0.0F, -19.0F, 7, 7, 2, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 0, 0, -2.0F, 7.0F, -19.0F, 4, 4, 2, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 0, 0, -2.0F, 10.4142F, -17.5858F, 4, 2, 16, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 99, 111, -1.5F, 0.4142F, -4.5858F, 3, 4, 1, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 0, 0, -4.5F, 0.4142F, -1.5858F, 9, 12, 14, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 0, 0, -5.5F, 0.4142F, -0.5858F, 11, 12, 12, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 0, 0, -6.0F, 0.4142F, 2.4142F, 12, 11, 7, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 0, 0, -6.5F, 0.0F, -2.0F, 13, 1, 15, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 0, 0, -6.5F, -3.0F, 1.0F, 13, 2, 17, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 0, 0, -5.5F, -3.4039F, 17.7354F, 11, 2, 4, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 0, 0, -6.5F, -12.0F, 1.0F, 13, 9, 5, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 0, 0, -3.5F, -15.0F, 1.0F, 7, 3, 5, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 85, 104, -2.5F, -16.0F, 1.0F, 5, 1, 5, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 85, 104, 1.5F, -17.0F, 1.0F, 1, 1, 5, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 85, 104, -2.5F, -17.0F, 1.0F, 1, 1, 5, 0.0F, true));
        bone.cubeList.add(new ModelBox(bone, 85, 104, 0.9766F, -17.0F, 1.0F, 1, 1, 5, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 85, 104, -1.9766F, -17.0F, 1.0F, 1, 1, 5, 0.0F, true));
        bone.cubeList.add(new ModelBox(bone, 85, 104, -1.5F, -16.8086F, -51.0F, 3, 2, 5, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 0, 0, -6.5F, -1.0F, 1.0F, 13, 1, 12, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 0, 0, -3.5F, 0.0F, -17.0F, 7, 1, 15, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 0, 0, -2.0F, -14.9282F, -53.0F, 4, 1, 54, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 0, 0, 6.4641F, -9.4641F, -53.0F, 1, 4, 54, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 0, 0, -7.4641F, -9.4641F, -53.0F, 1, 4, 54, 0.0F, true));

        bone6 = new ModelRenderer(this);
        bone6.setRotationPoint(1.0F, 0.0F, 0.0F);
        setRotationAngle(bone6, 0.0F, 0.0F, -0.2618F);
        bone.addChild(bone6);
        bone6.cubeList.add(new ModelBox(bone6, 0, 0, -2.3969F, 7.1406F, -19.0F, 2, 2, 2, 0.0F, false));
        bone6.cubeList.add(new ModelBox(bone6, 0, 0, -6.1584F, 5.5968F, -19.0F, 2, 2, 2, 0.0F, false));

        bone7 = new ModelRenderer(this);
        bone7.setRotationPoint(-1.0F, 0.0F, 0.0F);
        setRotationAngle(bone7, 0.0F, 0.0F, 0.2618F);
        bone.addChild(bone7);
        bone7.cubeList.add(new ModelBox(bone7, 0, 0, 0.3969F, 7.1406F, -19.0F, 2, 2, 2, 0.0F, true));
        bone7.cubeList.add(new ModelBox(bone7, 0, 0, 4.1584F, 5.5968F, -19.0F, 2, 2, 2, 0.0F, true));

        bone8 = new ModelRenderer(this);
        bone8.setRotationPoint(-1.0F, 0.0F, 0.0F);
        setRotationAngle(bone8, 0.0F, 0.0F, 0.2618F);
        bone.addChild(bone8);
        bone8.cubeList.add(new ModelBox(bone8, 0, 0, 4.1584F, 5.5968F, -19.0F, 2, 2, 2, 0.0F, true));
        bone8.cubeList.add(new ModelBox(bone8, 0, 0, 4.1584F, 5.5968F, -19.0F, 2, 2, 2, 0.0F, true));

        bone5 = new ModelRenderer(this);
        bone5.setRotationPoint(0.0F, 15.0F, -18.0F);
        setRotationAngle(bone5, 0.7854F, 0.0F, 0.0F);
        bone.addChild(bone5);
        bone5.cubeList.add(new ModelBox(bone5, 0, 0, -2.0F, -3.5355F, 2.1213F, 4, 2, 2, 0.0F, false));

        bone18 = new ModelRenderer(this);
        bone18.setRotationPoint(1.5F, 0.0F, 0.0F);
        setRotationAngle(bone18, -0.3491F, 0.0F, 0.0F);
        bone.addChild(bone18);
        bone18.cubeList.add(new ModelBox(bone18, 99, 111, -3.0F, 5.3744F, -2.8598F, 3, 2, 1, 0.0F, false));

        hammer = new ModelRenderer(this);
        hammer.setRotationPoint(-5.0F, 0.0F, 0.0F);
        setRotationAngle(hammer, -0.2443F, 0.0F, 0.0F);
        bone.addChild(hammer);
        hammer.cubeList.add(new ModelBox(hammer, 81, 93, 3.5F, -16.6774F, 9.4307F, 3, 5, 1, 0.0F, false));
        hammer.cubeList.add(new ModelBox(hammer, 81, 93, 4.0F, -16.3141F, 6.4463F, 2, 3, 3, 0.0F, false));
        hammer.cubeList.add(new ModelBox(hammer, 81, 93, 2.5F, -11.6774F, 8.9307F, 5, 4, 4, 0.0F, false));
        hammer.cubeList.add(new ModelBox(hammer, 81, 93, 2.5F, -13.6774F, 9.9307F, 5, 2, 10, 0.0F, false));
        hammer.cubeList.add(new ModelBox(hammer, 81, 93, 2.5F, -13.9782F, 18.9307F, 5, 2, 2, 0.0F, false));
        hammer.cubeList.add(new ModelBox(hammer, 81, 93, 2.5F, -13.9782F, 16.9307F, 5, 2, 1, 0.0F, false));
        hammer.cubeList.add(new ModelBox(hammer, 81, 93, 2.5F, -13.9782F, 14.9307F, 5, 2, 1, 0.0F, false));
        hammer.cubeList.add(new ModelBox(hammer, 81, 93, 2.5F, -16.6774F, 9.9307F, 5, 3, 4, 0.0F, false));

        bone14 = new ModelRenderer(this);
        bone14.setRotationPoint(-2.5F, 0.0F, 0.0F);
        setRotationAngle(bone14, 0.4363F, 0.0F, 0.0F);
        bone.addChild(bone14);
        bone14.cubeList.add(new ModelBox(bone14, 0, 0, -1.0F, -11.0589F, 8.7771F, 7, 14, 3, 0.0F, false));

        bone15 = new ModelRenderer(this);
        bone15.setRotationPoint(-2.5F, 2.8555F, 0.0F);
        setRotationAngle(bone15, 0.9599F, 0.0F, 0.0F);
        bone.addChild(bone15);
        bone15.cubeList.add(new ModelBox(bone15, 0, 0, -4.0F, -3.1566F, 12.4802F, 13, 14, 2, 0.0F, false));
        bone15.cubeList.add(new ModelBox(bone15, 0, 0, -3.0F, -0.8636F, 13.0193F, 11, 12, 2, 0.0F, false));
        bone15.cubeList.add(new ModelBox(bone15, 0, 0, -4.0F, -1.8519F, 9.4802F, 13, 10, 3, 0.0F, false));
        bone15.cubeList.add(new ModelBox(bone15, 0, 0, -4.0F, 0.5075F, 7.4802F, 13, 3, 2, 0.0F, false));

        bone17 = new ModelRenderer(this);
        bone17.setRotationPoint(1.0F, 0.0F, 0.0F);
        setRotationAngle(bone17, 0.3491F, 0.0F, 0.0F);
        bone15.addChild(bone17);
        bone17.cubeList.add(new ModelBox(bone17, 0, 0, -4.0F, 7.3747F, 9.6717F, 11, 12, 2, 0.0F, false));

        bone16 = new ModelRenderer(this);
        bone16.setRotationPoint(0.0F, 0.0F, 0.0F);
        setRotationAngle(bone16, 0.3491F, 0.0F, 0.0F);
        bone15.addChild(bone16);
        bone16.cubeList.add(new ModelBox(bone16, 0, 0, -3.0F, 13.8712F, 8.3147F, 11, 2, 2, 0.0F, false));

        bone12 = new ModelRenderer(this);
        bone12.setRotationPoint(-2.5F, 0.0F, 0.0F);
        setRotationAngle(bone12, 0.0F, 0.0F, 0.7854F);
        bone.addChild(bone12);
        bone12.cubeList.add(new ModelBox(bone12, 0, 0, -6.364F, -14.8492F, 1.0F, 4, 3, 5, 0.0F, false));
        bone12.cubeList.add(new ModelBox(bone12, 0, 0, -3.1213F, -14.8492F, 1.0F, 1, 1, 5, 0.0F, false));

        bone13 = new ModelRenderer(this);
        bone13.setRotationPoint(2.5F, 0.0F, 0.0F);
        setRotationAngle(bone13, 0.0F, 0.0F, -0.7854F);
        bone.addChild(bone13);
        bone13.cubeList.add(new ModelBox(bone13, 0, 0, 2.364F, -14.8492F, 1.0F, 4, 3, 5, 0.0F, true));
        bone13.cubeList.add(new ModelBox(bone13, 0, 0, 2.1213F, -14.8492F, 1.0F, 1, 1, 5, 0.0F, true));

        bone9 = new ModelRenderer(this);
        bone9.setRotationPoint(-2.5F, 0.0F, 0.0F);
        setRotationAngle(bone9, 0.4363F, 0.0F, 0.0F);
        bone.addChild(bone9);
        bone9.cubeList.add(new ModelBox(bone9, 0, 0, -2.0F, 10.5809F, -6.6837F, 9, 6, 15, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 0, 0, -3.0F, 10.5809F, -5.6837F, 11, 6, 13, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 0, 0, -3.5F, 10.5809F, -2.6837F, 12, 6, 8, 0.0F, false));

        bone10 = new ModelRenderer(this);
        bone10.setRotationPoint(-2.5F, 7.0F, 0.0F);
        setRotationAngle(bone10, 0.1745F, 0.0F, 0.0F);
        bone.addChild(bone10);
        bone10.cubeList.add(new ModelBox(bone10, 0, 0, -2.0F, 6.9699F, -1.4601F, 9, 16, 15, 0.0F, false));
        bone10.cubeList.add(new ModelBox(bone10, 0, 0, -3.0F, 6.9699F, -0.4601F, 11, 16, 13, 0.0F, false));
        bone10.cubeList.add(new ModelBox(bone10, 0, 0, -3.5F, 6.9699F, 2.5399F, 12, 16, 8, 0.0F, false));

        bone11 = new ModelRenderer(this);
        bone11.setRotationPoint(-2.5F, 7.0F, 0.0F);
        setRotationAngle(bone11, 0.0873F, 0.0F, 0.0F);
        bone.addChild(bone11);
        bone11.cubeList.add(new ModelBox(bone11, 0, 0, -2.0F, 21.7024F, 0.4904F, 9, 3, 15, 0.0F, false));
        bone11.cubeList.add(new ModelBox(bone11, 0, 0, -3.0F, 21.7024F, 1.4904F, 11, 3, 13, 0.0F, false));
        bone11.cubeList.add(new ModelBox(bone11, 0, 0, -3.5F, 21.7024F, 4.4904F, 12, 3, 8, 0.0F, false));

        bone4 = new ModelRenderer(this);
        bone4.setRotationPoint(0.0F, 0.0F, 0.0F);
        setRotationAngle(bone4, 0.7854F, 0.0F, 0.0F);
        bone.addChild(bone4);
        bone4.cubeList.add(new ModelBox(bone4, 0, 0, -3.0F, -18.1421F, -24.0416F, 6, 4, 5, 0.0F, false));
        bone4.cubeList.add(new ModelBox(bone4, 0, 0, -3.0F, -15.1421F, -19.3848F, 6, 1, 1, 0.0F, false));

        bone2 = new ModelRenderer(this);
        bone2.setRotationPoint(0.0F, 0.0F, 0.0F);
        setRotationAngle(bone2, 0.0F, 0.0F, -0.5236F);
        bone.addChild(bone2);
        bone2.cubeList.add(new ModelBox(bone2, 0, 0, 1.7321F, -0.0F, -53.0F, 4, 1, 54, 0.0F, false));
        bone2.cubeList.add(new ModelBox(bone2, 0, 0, 1.7321F, -13.9282F, -53.0F, 4, 1, 54, 0.0F, false));
        bone2.cubeList.add(new ModelBox(bone2, 0, 0, 10.1962F, -8.4641F, -53.0F, 1, 4, 54, 0.0F, false));
        bone2.cubeList.add(new ModelBox(bone2, 0, 0, -3.7321F, -8.4641F, -53.0F, 1, 4, 54, 0.0F, false));

        bone3 = new ModelRenderer(this);
        bone3.setRotationPoint(0.0F, 0.0F, 0.0F);
        setRotationAngle(bone3, 0.0F, 0.0F, -1.0472F);
        bone.addChild(bone3);
        bone3.cubeList.add(new ModelBox(bone3, 0, 0, 4.4641F, 2.7321F, -53.0F, 4, 1, 54, 0.0F, false));
        bone3.cubeList.add(new ModelBox(bone3, 0, 0, -1.0F, -5.7321F, -53.0F, 1, 4, 54, 0.0F, false));
        bone3.cubeList.add(new ModelBox(bone3, 0, 0, 12.9282F, -5.7321F, -53.0F, 1, 4, 54, 0.0F, false));
        bone3.cubeList.add(new ModelBox(bone3, 0, 0, 4.4641F, -11.1962F, -53.0F, 4, 1, 54, 0.0F, false));
        this.initAnimations();
    }
}

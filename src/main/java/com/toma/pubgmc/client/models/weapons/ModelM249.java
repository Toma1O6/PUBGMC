package com.toma.pubgmc.client.models.weapons;

import com.toma.pubgmc.animation.ReloadAnimation;
import com.toma.pubgmc.client.models.ModelGun;
import com.toma.pubgmc.client.util.ModelTransformationHelper;
import com.toma.pubgmc.common.capability.IPlayerData.PlayerDataProvider;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.item.ItemStack;

public class ModelM249 extends ModelGun {

    private final ModelRenderer magazine;
    private final ModelRenderer belt;
    private final ModelRenderer bullet2;
    private final ModelRenderer bone6;
    private final ModelRenderer bone7;
    private final ModelRenderer bone8;
    private final ModelRenderer bone9;
    private final ModelRenderer bullet3;
    private final ModelRenderer bone10;
    private final ModelRenderer bone11;
    private final ModelRenderer bone12;
    private final ModelRenderer bone13;
    private final ModelRenderer bullet4;
    private final ModelRenderer bone14;
    private final ModelRenderer bone15;
    private final ModelRenderer bone16;
    private final ModelRenderer bone17;
    private final ModelRenderer bullet5;
    private final ModelRenderer bone18;
    private final ModelRenderer bone19;
    private final ModelRenderer bone20;
    private final ModelRenderer bone21;
    private final ModelRenderer bullet6;
    private final ModelRenderer bone22;
    private final ModelRenderer bone23;
    private final ModelRenderer bone24;
    private final ModelRenderer bone25;
    private final ModelRenderer bone2;
    private final ModelRenderer bone43;
    private final ModelRenderer m249;
    private final ModelRenderer bone;
    private final ModelRenderer bone77;
    private final ModelRenderer bone46;
    private final ModelRenderer bone47;
    private final ModelRenderer bone44;
    private final ModelRenderer bone45;
    private final ModelRenderer bone48;
    private final ModelRenderer bone87;
    private final ModelRenderer bone41;
    private final ModelRenderer bone42;
    private final ModelRenderer bone32;
    private final ModelRenderer bone33;
    private final ModelRenderer bone34;
    private final ModelRenderer bone35;
    private final ModelRenderer bone36;
    private final ModelRenderer bone28;
    private final ModelRenderer bone54;
    private final ModelRenderer bone30;
    private final ModelRenderer bone53;
    private final ModelRenderer bone31;
    private final ModelRenderer bone56;
    private final ModelRenderer bone29;
    private final ModelRenderer bone55;
    private final ModelRenderer bone51;
    private final ModelRenderer bone52;
    private final ModelRenderer bone49;
    private final ModelRenderer bone50;
    private final ModelRenderer bone5;
    private final ModelRenderer bone39;
    private final ModelRenderer bone40;
    private final ModelRenderer bone3;
    private final ModelRenderer bone4;
    private final ModelRenderer bone26;
    private final ModelRenderer bone27;
    private final ModelRenderer bone59;
    private final ModelRenderer bone60;
    private final ModelRenderer bone61;
    private final ModelRenderer bone62;
    private final ModelRenderer bone63;
    private final ModelRenderer bone64;
    private final ModelRenderer bone65;
    private final ModelRenderer bone66;
    private final ModelRenderer bone67;
    private final ModelRenderer bone68;
    private final ModelRenderer bone69;
    private final ModelRenderer bone70;
    private final ModelRenderer bone71;
    private final ModelRenderer bone72;
    private final ModelRenderer bone73;
    private final ModelRenderer bone74;
    private final ModelRenderer bone75;
    private final ModelRenderer bone76;
    private final ModelRenderer bone78;
    private final ModelRenderer bone79;
    private final ModelRenderer bone84;
    private final ModelRenderer bone85;
    private final ModelRenderer bone82;
    private final ModelRenderer bone83;
    private final ModelRenderer bone80;
    private final ModelRenderer bone81;
    private final ModelRenderer bone57;
    private final ModelRenderer bone58;
    private final ModelRenderer bone86;
    private final ModelRenderer bone37;
    private final ModelRenderer bone38;

    @Override
    public String textureName() {
        return "m249";
    }

    @Override
    public void initAnimations() {
        initAimAnimation(-0.56f, 0.265f, 0.125f);
        initAimingAnimationStates(0.265f, 0.18f, 0.16f);
        // TODO test the animation speed
        reloadAnimation = new ReloadAnimation(magazine, ReloadAnimation.ReloadStyle.MAGAZINE).withSpeed(0.55F);
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
            GlStateManager.pushMatrix();
            {
                renderM249(stack);
            }
            GlStateManager.popMatrix();
        }
    }

    private void renderM249(ItemStack stack) {
        GlStateManager.pushMatrix();
        ModelTransformationHelper.defaultARTransform();
        GlStateManager.translate(0, -0.25, -9.0);

        m249.render(1f);
        magazine.render(1f);
        GlStateManager.popMatrix();

        renderRedDot(0, 0, 18, 1.0F, stack);
        renderHolo(-0.075, 3, 5, 0.9F, stack);
        renderScope2X(0, 0, 9, 1.0F, stack);
        renderScope4X(0, 0, 10, 1.0F, stack);
    }

    public ModelM249() {
        textureWidth = 128;
        textureHeight = 128;

        magazine = new ModelRenderer(this);
        magazine.setRotationPoint(1.3F, 24.0F, 0.0F);
        magazine.cubeList.add(new ModelBox(magazine, 0, 0, 9.0F, -9.79F, -3.0F, 1, 12, 11, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 0, 0, 9.0F, -10.4971F, 2.2929F, 1, 1, 5, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 0, 0, 9.0F, 1.9171F, 2.2929F, 1, 1, 5, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 0, 0, 9.0F, -10.4971F, -2.2929F, 1, 1, 5, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 0, 0, 9.0F, 1.9171F, -2.2929F, 1, 1, 5, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 0, 0, 1.0F, -10.14F, -3.0F, 5, 3, 11, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 0, 0, 1.728F, -11.14F, -1.0F, 2, 1, 7, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 0, 0, -4.896F, -11.14F, -1.0F, 1, 1, 7, 0.0F, true));
        magazine.cubeList.add(new ModelBox(magazine, 0, 0, 2.228F, -11.388F, -2.0F, 1, 1, 9, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 0, 0, 0.984F, -10.564F, -1.0F, 1, 1, 7, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 0, 0, -3.936F, -10.932F, -1.0F, 2, 1, 7, 0.0F, true));
        magazine.cubeList.add(new ModelBox(magazine, 0, 0, -4.0F, -10.14F, -3.0F, 5, 3, 11, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 0, 0, -5.5721F, -10.14F, -3.0F, 2, 2, 11, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 0, 0, -2.8F, -11.14F, -3.0F, 3, 1, 11, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 0, 0, -2.3F, -11.92F, -2.5F, 2, 1, 2, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 0, 0, -2.3F, -11.92F, 5.5F, 2, 1, 2, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 0, 0, -1.8F, -11.232F, -0.5F, 1, 1, 6, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 0, 0, 1.0F, 0.86F, -2.0F, 8, 2, 9, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 0, 0, -12.0F, 1.86F, -2.0F, 13, 1, 9, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 0, 0, -12.0F, -2.14F, -2.0F, 1, 4, 9, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 0, 0, -12.0F, -2.4796F, -3.0F, 1, 1, 11, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 0, 0, 1.0F, -7.14F, -3.0F, 8, 9, 1, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 0, 0, -4.0F, -7.14F, -3.0F, 5, 9, 1, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 0, 0, -12.0F, -2.14F, -3.0F, 8, 4, 1, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 0, 0, -7.92F, -6.028F, -3.0F, 4, 4, 1, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 0, 0, 1.0F, -7.14F, 7.0F, 8, 9, 1, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 0, 0, -4.0F, -7.14F, 7.0F, 5, 9, 1, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 0, 0, -12.0F, -2.14F, 7.0F, 8, 4, 1, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 0, 0, -7.92F, -6.028F, 7.0F, 4, 4, 1, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 0, 72, 6.0F, -7.46F, -2.0F, 3, 1, 9, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 0, 0, 6.0F, -9.4F, -3.0F, 3, 3, 1, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 0, 0, 6.0F, -9.4F, 7.0F, 3, 3, 1, 0.0F, false));

        belt = new ModelRenderer(this);
        belt.setRotationPoint(0.0F, 0.0F, 0.0F);
        magazine.addChild(belt);

        bullet2 = new ModelRenderer(this);
        bullet2.setRotationPoint(9.85F, -3.41F, -3.3F);
        setRotationAngle(bullet2, 0.0F, 0.0F, -0.0873F);
        belt.addChild(bullet2);
        bullet2.cubeList.add(new ModelBox(bullet2, 0, 96, -2.0924F, -6.0743F, 3.24F, 1, 1, 6, 0.0F, false));
        bullet2.cubeList.add(new ModelBox(bullet2, 0, 96, -2.9924F, -6.1743F, 3.68F, 2, 2, 5, 0.0F, false));
        bullet2.cubeList.add(new ModelBox(bullet2, 0, 96, -2.9924F, -6.1743F, 9.04F, 2, 2, 1, 0.0F, false));
        bullet2.cubeList.add(new ModelBox(bullet2, 0, 96, -2.8924F, -6.0743F, 3.24F, 1, 1, 6, 0.0F, false));
        bullet2.cubeList.add(new ModelBox(bullet2, 0, 96, -2.0924F, -5.2743F, 3.24F, 1, 1, 6, 0.0F, false));
        bullet2.cubeList.add(new ModelBox(bullet2, 0, 96, -2.8924F, -5.2743F, 3.24F, 1, 1, 6, 0.0F, false));
        bullet2.cubeList.add(new ModelBox(bullet2, 0, 96, -2.4924F, -5.6743F, 1.57F, 1, 1, 1, 0.0F, false));
        bullet2.cubeList.add(new ModelBox(bullet2, 0, 96, -2.4924F, -5.6743F, 1.57F, 1, 1, 1, 0.0F, false));
        bullet2.cubeList.add(new ModelBox(bullet2, 11, 81, -2.4924F, -7.6743F, 4.9F, 1, 2, 3, 0.0F, false));
        bullet2.cubeList.add(new ModelBox(bullet2, 0, 96, -2.4924F, -6.0163F, 2.6497F, 1, 1, 1, 0.0F, false));
        bullet2.cubeList.add(new ModelBox(bullet2, 0, 96, -2.8344F, -5.6743F, 2.6497F, 1, 1, 1, 0.0F, false));
        bullet2.cubeList.add(new ModelBox(bullet2, 0, 96, -2.1504F, -5.6743F, 2.6497F, 1, 1, 1, 0.0F, false));
        bullet2.cubeList.add(new ModelBox(bullet2, 0, 96, -2.4924F, -5.3323F, 2.6497F, 1, 1, 1, 0.0F, false));

        bone6 = new ModelRenderer(this);
        bone6.setRotationPoint(0.0F, -5.5F, 1.71F);
        setRotationAngle(bone6, 0.3491F, 0.0F, 0.0F);
        bullet2.addChild(bone6);
        bone6.cubeList.add(new ModelBox(bone6, 0, 96, -2.4924F, -0.1638F, 0.0596F, 1, 1, 1, 0.0F, false));

        bone7 = new ModelRenderer(this);
        bone7.setRotationPoint(0.0F, -4.5F, 1.71F);
        setRotationAngle(bone7, -0.3491F, 0.0F, 0.0F);
        bullet2.addChild(bone7);
        bone7.cubeList.add(new ModelBox(bone7, 0, 96, -2.4924F, -1.1638F, -0.0596F, 1, 1, 1, 0.0F, false));

        bone8 = new ModelRenderer(this);
        bone8.setRotationPoint(0.5F, -5.0F, 1.71F);
        setRotationAngle(bone8, 0.0F, 0.3491F, 0.0F);
        bullet2.addChild(bone8);
        bone8.cubeList.add(new ModelBox(bone8, 0, 96, -2.8722F, -0.6743F, -0.6814F, 1, 1, 1, 0.0F, false));

        bone9 = new ModelRenderer(this);
        bone9.setRotationPoint(-0.5F, -5.0F, 1.71F);
        setRotationAngle(bone9, 0.0F, -0.3491F, 0.0F);
        bullet2.addChild(bone9);
        bone9.cubeList.add(new ModelBox(bone9, 0, 96, -1.8722F, -0.6743F, 0.6814F, 1, 1, 1, 0.0F, false));

        bullet3 = new ModelRenderer(this);
        bullet3.setRotationPoint(10.66F, -5.99F, -3.3F);
        setRotationAngle(bullet3, 0.0F, 0.0F, -0.3491F);
        belt.addChild(bullet3);
        bullet3.cubeList.add(new ModelBox(bullet3, 0, 96, -1.9794F, -6.584F, 3.24F, 1, 1, 6, 0.0F, false));
        bullet3.cubeList.add(new ModelBox(bullet3, 0, 96, -2.8794F, -6.684F, 3.68F, 2, 2, 5, 0.0F, false));
        bullet3.cubeList.add(new ModelBox(bullet3, 0, 96, -2.8794F, -6.684F, 9.04F, 2, 2, 1, 0.0F, false));
        bullet3.cubeList.add(new ModelBox(bullet3, 0, 96, -2.7794F, -6.584F, 3.24F, 1, 1, 6, 0.0F, false));
        bullet3.cubeList.add(new ModelBox(bullet3, 0, 96, -1.9794F, -5.784F, 3.24F, 1, 1, 6, 0.0F, false));
        bullet3.cubeList.add(new ModelBox(bullet3, 0, 96, -2.7794F, -5.784F, 3.24F, 1, 1, 6, 0.0F, false));
        bullet3.cubeList.add(new ModelBox(bullet3, 0, 96, -2.3794F, -6.184F, 1.57F, 1, 1, 1, 0.0F, false));
        bullet3.cubeList.add(new ModelBox(bullet3, 0, 96, -2.3794F, -6.184F, 1.57F, 1, 1, 1, 0.0F, false));
        bullet3.cubeList.add(new ModelBox(bullet3, 11, 81, -2.3794F, -8.184F, 4.9F, 1, 2, 3, 0.0F, false));
        bullet3.cubeList.add(new ModelBox(bullet3, 0, 96, -2.3794F, -6.5261F, 2.6497F, 1, 1, 1, 0.0F, false));
        bullet3.cubeList.add(new ModelBox(bullet3, 0, 96, -2.7214F, -6.184F, 2.6497F, 1, 1, 1, 0.0F, false));
        bullet3.cubeList.add(new ModelBox(bullet3, 0, 96, -2.0374F, -6.184F, 2.6497F, 1, 1, 1, 0.0F, false));
        bullet3.cubeList.add(new ModelBox(bullet3, 0, 96, -2.3794F, -5.842F, 2.6497F, 1, 1, 1, 0.0F, false));

        bone10 = new ModelRenderer(this);
        bone10.setRotationPoint(0.0F, -5.5F, 1.71F);
        setRotationAngle(bone10, 0.3491F, 0.0F, 0.0F);
        bullet3.addChild(bone10);
        bone10.cubeList.add(new ModelBox(bone10, 0, 96, -2.3794F, -0.6428F, 0.234F, 1, 1, 1, 0.0F, false));

        bone11 = new ModelRenderer(this);
        bone11.setRotationPoint(0.0F, -4.5F, 1.71F);
        setRotationAngle(bone11, -0.3491F, 0.0F, 0.0F);
        bullet3.addChild(bone11);
        bone11.cubeList.add(new ModelBox(bone11, 0, 96, -2.3794F, -1.6428F, -0.234F, 1, 1, 1, 0.0F, false));

        bone12 = new ModelRenderer(this);
        bone12.setRotationPoint(0.5F, -5.0F, 1.71F);
        setRotationAngle(bone12, 0.0F, 0.3491F, 0.0F);
        bullet3.addChild(bone12);
        bone12.cubeList.add(new ModelBox(bone12, 0, 96, -2.766F, -1.184F, -0.6428F, 1, 1, 1, 0.0F, false));

        bone13 = new ModelRenderer(this);
        bone13.setRotationPoint(-0.5F, -5.0F, 1.71F);
        setRotationAngle(bone13, 0.0F, -0.3491F, 0.0F);
        bullet3.addChild(bone13);
        bone13.cubeList.add(new ModelBox(bone13, 0, 96, -1.766F, -1.184F, 0.6428F, 1, 1, 1, 0.0F, false));

        bullet4 = new ModelRenderer(this);
        bullet4.setRotationPoint(10.83F, -8.86F, -3.3F);
        setRotationAngle(bullet4, 0.0F, 0.0F, -0.6109F);
        belt.addChild(bullet4);
        bullet4.cubeList.add(new ModelBox(bullet4, 0, 96, -1.7383F, -7.0472F, 3.24F, 1, 1, 6, 0.0F, false));
        bullet4.cubeList.add(new ModelBox(bullet4, 0, 96, -2.6383F, -7.1472F, 3.68F, 2, 2, 5, 0.0F, false));
        bullet4.cubeList.add(new ModelBox(bullet4, 0, 96, -2.6383F, -7.1472F, 9.04F, 2, 2, 1, 0.0F, false));
        bullet4.cubeList.add(new ModelBox(bullet4, 0, 96, -2.5383F, -7.0472F, 3.24F, 1, 1, 6, 0.0F, false));
        bullet4.cubeList.add(new ModelBox(bullet4, 0, 96, -1.7383F, -6.2472F, 3.24F, 1, 1, 6, 0.0F, false));
        bullet4.cubeList.add(new ModelBox(bullet4, 0, 96, -2.5383F, -6.2472F, 3.24F, 1, 1, 6, 0.0F, false));
        bullet4.cubeList.add(new ModelBox(bullet4, 0, 96, -2.1383F, -6.6472F, 1.57F, 1, 1, 1, 0.0F, false));
        bullet4.cubeList.add(new ModelBox(bullet4, 0, 96, -2.1383F, -6.6472F, 1.57F, 1, 1, 1, 0.0F, false));
        bullet4.cubeList.add(new ModelBox(bullet4, 11, 81, -2.1383F, -8.6472F, 4.9F, 1, 2, 3, 0.0F, false));
        bullet4.cubeList.add(new ModelBox(bullet4, 0, 96, -2.1383F, -6.9892F, 2.6497F, 1, 1, 1, 0.0F, false));
        bullet4.cubeList.add(new ModelBox(bullet4, 0, 96, -2.4803F, -6.6472F, 2.6497F, 1, 1, 1, 0.0F, false));
        bullet4.cubeList.add(new ModelBox(bullet4, 0, 96, -1.7963F, -6.6472F, 2.6497F, 1, 1, 1, 0.0F, false));
        bullet4.cubeList.add(new ModelBox(bullet4, 0, 96, -2.1383F, -6.3051F, 2.6497F, 1, 1, 1, 0.0F, false));

        bone14 = new ModelRenderer(this);
        bone14.setRotationPoint(0.0F, -5.5F, 1.71F);
        setRotationAngle(bone14, 0.3491F, 0.0F, 0.0F);
        bullet4.addChild(bone14);
        bone14.cubeList.add(new ModelBox(bone14, 0, 96, -2.1383F, -1.078F, 0.3923F, 1, 1, 1, 0.0F, false));

        bone15 = new ModelRenderer(this);
        bone15.setRotationPoint(0.0F, -4.5F, 1.71F);
        setRotationAngle(bone15, -0.3491F, 0.0F, 0.0F);
        bullet4.addChild(bone15);
        bone15.cubeList.add(new ModelBox(bone15, 0, 96, -2.1383F, -2.078F, -0.3923F, 1, 1, 1, 0.0F, false));

        bone16 = new ModelRenderer(this);
        bone16.setRotationPoint(0.5F, -5.0F, 1.71F);
        setRotationAngle(bone16, 0.0F, 0.3491F, 0.0F);
        bullet4.addChild(bone16);
        bone16.cubeList.add(new ModelBox(bone16, 0, 96, -2.5395F, -1.6472F, -0.5603F, 1, 1, 1, 0.0F, false));

        bone17 = new ModelRenderer(this);
        bone17.setRotationPoint(-0.5F, -5.0F, 1.71F);
        setRotationAngle(bone17, 0.0F, -0.3491F, 0.0F);
        bullet4.addChild(bone17);
        bone17.cubeList.add(new ModelBox(bone17, 0, 96, -1.5395F, -1.6472F, 0.5603F, 1, 1, 1, 0.0F, false));

        bullet5 = new ModelRenderer(this);
        bullet5.setRotationPoint(10.04F, -11.52F, -3.3F);
        setRotationAngle(bullet5, 0.0F, 0.0F, -0.7854F);
        belt.addChild(bullet5);
        bullet5.cubeList.add(new ModelBox(bullet5, 0, 96, -1.5142F, -7.3142F, 3.24F, 1, 1, 6, 0.0F, false));
        bullet5.cubeList.add(new ModelBox(bullet5, 0, 96, -2.4142F, -7.4142F, 3.68F, 2, 2, 5, 0.0F, false));
        bullet5.cubeList.add(new ModelBox(bullet5, 0, 96, -2.4142F, -7.4142F, 9.04F, 2, 2, 1, 0.0F, false));
        bullet5.cubeList.add(new ModelBox(bullet5, 0, 96, -2.3142F, -7.3142F, 3.24F, 1, 1, 6, 0.0F, false));
        bullet5.cubeList.add(new ModelBox(bullet5, 0, 96, -1.5142F, -6.5142F, 3.24F, 1, 1, 6, 0.0F, false));
        bullet5.cubeList.add(new ModelBox(bullet5, 0, 96, -2.3142F, -6.5142F, 3.24F, 1, 1, 6, 0.0F, false));
        bullet5.cubeList.add(new ModelBox(bullet5, 0, 96, -1.9142F, -6.9142F, 1.57F, 1, 1, 1, 0.0F, false));
        bullet5.cubeList.add(new ModelBox(bullet5, 0, 96, -1.9142F, -6.9142F, 1.57F, 1, 1, 1, 0.0F, false));
        bullet5.cubeList.add(new ModelBox(bullet5, 11, 81, -1.9808F, -8.8242F, 4.9F, 1, 2, 3, 0.0F, false));
        bullet5.cubeList.add(new ModelBox(bullet5, 0, 96, -1.9142F, -7.2562F, 2.6497F, 1, 1, 1, 0.0F, false));
        bullet5.cubeList.add(new ModelBox(bullet5, 0, 96, -2.2562F, -6.9142F, 2.6497F, 1, 1, 1, 0.0F, false));
        bullet5.cubeList.add(new ModelBox(bullet5, 0, 96, -1.5722F, -6.9142F, 2.6497F, 1, 1, 1, 0.0F, false));
        bullet5.cubeList.add(new ModelBox(bullet5, 0, 96, -1.9142F, -6.5722F, 2.6497F, 1, 1, 1, 0.0F, false));

        bone18 = new ModelRenderer(this);
        bone18.setRotationPoint(0.0F, -5.5F, 1.71F);
        setRotationAngle(bone18, 0.3491F, 0.0F, 0.0F);
        bullet5.addChild(bone18);
        bone18.cubeList.add(new ModelBox(bone18, 0, 96, -1.9142F, -1.3289F, 0.4837F, 1, 1, 1, 0.0F, false));

        bone19 = new ModelRenderer(this);
        bone19.setRotationPoint(0.0F, -4.5F, 1.71F);
        setRotationAngle(bone19, -0.3491F, 0.0F, 0.0F);
        bullet5.addChild(bone19);
        bone19.cubeList.add(new ModelBox(bone19, 0, 96, -1.9142F, -2.3289F, -0.4837F, 1, 1, 1, 0.0F, false));

        bone20 = new ModelRenderer(this);
        bone20.setRotationPoint(0.5F, -5.0F, 1.71F);
        setRotationAngle(bone20, 0.0F, 0.3491F, 0.0F);
        bullet5.addChild(bone20);
        bone20.cubeList.add(new ModelBox(bone20, 0, 96, -2.3289F, -1.9142F, -0.4837F, 1, 1, 1, 0.0F, false));

        bone21 = new ModelRenderer(this);
        bone21.setRotationPoint(-0.5F, -5.0F, 1.71F);
        setRotationAngle(bone21, 0.0F, -0.3491F, 0.0F);
        bullet5.addChild(bone21);
        bone21.cubeList.add(new ModelBox(bone21, 0, 96, -1.3289F, -1.9142F, 0.4837F, 1, 1, 1, 0.0F, false));

        bullet6 = new ModelRenderer(this);
        bullet6.setRotationPoint(8.81F, -14.64F, -3.3F);
        setRotationAngle(bullet6, 0.0F, 0.0F, -1.1345F);
        belt.addChild(bullet6);
        bullet6.cubeList.add(new ModelBox(bullet6, 0, 96, -0.9452F, -7.7126F, 3.24F, 1, 1, 6, 0.0F, false));
        bullet6.cubeList.add(new ModelBox(bullet6, 0, 96, -1.8452F, -7.8126F, 3.68F, 2, 2, 5, 0.0F, false));
        bullet6.cubeList.add(new ModelBox(bullet6, 0, 96, -1.8452F, -7.8126F, 9.04F, 2, 2, 1, 0.0F, false));
        bullet6.cubeList.add(new ModelBox(bullet6, 0, 96, -1.7452F, -7.7126F, 3.24F, 1, 1, 6, 0.0F, false));
        bullet6.cubeList.add(new ModelBox(bullet6, 0, 96, -0.9452F, -6.9126F, 3.24F, 1, 1, 6, 0.0F, false));
        bullet6.cubeList.add(new ModelBox(bullet6, 0, 96, -1.7452F, -6.9126F, 3.24F, 1, 1, 6, 0.0F, false));
        bullet6.cubeList.add(new ModelBox(bullet6, 0, 96, -1.3452F, -7.3126F, 1.57F, 1, 1, 1, 0.0F, false));
        bullet6.cubeList.add(new ModelBox(bullet6, 0, 96, -1.3452F, -7.3126F, 1.57F, 1, 1, 1, 0.0F, false));
        bullet6.cubeList.add(new ModelBox(bullet6, 0, 96, -1.3452F, -7.6546F, 2.6497F, 1, 1, 1, 0.0F, false));
        bullet6.cubeList.add(new ModelBox(bullet6, 0, 96, -1.6873F, -7.3126F, 2.6497F, 1, 1, 1, 0.0F, false));
        bullet6.cubeList.add(new ModelBox(bullet6, 0, 96, -1.0032F, -7.3126F, 2.6497F, 1, 1, 1, 0.0F, false));
        bullet6.cubeList.add(new ModelBox(bullet6, 0, 96, -1.3452F, -6.9706F, 2.6497F, 1, 1, 1, 0.0F, false));

        bone22 = new ModelRenderer(this);
        bone22.setRotationPoint(0.0F, -5.5F, 1.71F);
        setRotationAngle(bone22, 0.3491F, 0.0F, 0.0F);
        bullet6.addChild(bone22);
        bone22.cubeList.add(new ModelBox(bone22, 0, 96, -1.3452F, -1.7033F, 0.62F, 1, 1, 1, 0.0F, false));

        bone23 = new ModelRenderer(this);
        bone23.setRotationPoint(0.0F, -4.5F, 1.71F);
        setRotationAngle(bone23, -0.3491F, 0.0F, 0.0F);
        bullet6.addChild(bone23);
        bone23.cubeList.add(new ModelBox(bone23, 0, 96, -1.3452F, -2.7033F, -0.62F, 1, 1, 1, 0.0F, false));

        bone24 = new ModelRenderer(this);
        bone24.setRotationPoint(0.5F, -5.0F, 1.71F);
        setRotationAngle(bone24, 0.0F, 0.3491F, 0.0F);
        bullet6.addChild(bone24);
        bone24.cubeList.add(new ModelBox(bone24, 0, 96, -1.7943F, -2.3126F, -0.2891F, 1, 1, 1, 0.0F, false));

        bone25 = new ModelRenderer(this);
        bone25.setRotationPoint(-0.5F, -5.0F, 1.71F);
        setRotationAngle(bone25, 0.0F, -0.3491F, 0.0F);
        bullet6.addChild(bone25);
        bone25.cubeList.add(new ModelBox(bone25, 0, 96, -0.7943F, -2.3126F, 0.2891F, 1, 1, 1, 0.0F, false));

        bone2 = new ModelRenderer(this);
        bone2.setRotationPoint(9.5F, -9.8F, 7.28F);
        setRotationAngle(bone2, -0.7854F, 0.0F, 0.0F);
        magazine.addChild(bone2);
        bone2.cubeList.add(new ModelBox(bone2, 0, 0, -0.5F, -0.502F, -0.4838F, 1, 1, 1, 0.0F, false));
        bone2.cubeList.add(new ModelBox(bone2, 0, 0, -0.5F, 7.9833F, 8.0015F, 1, 1, 1, 0.0F, false));
        bone2.cubeList.add(new ModelBox(bone2, 0, 0, -8.5F, 8.15F, 7.754F, 8, 1, 1, 0.0F, false));
        bone2.cubeList.add(new ModelBox(bone2, 0, 0, -21.5F, 8.15F, 7.754F, 13, 1, 1, 0.0F, false));
        bone2.cubeList.add(new ModelBox(bone2, 0, 0, -8.5F, 14.5139F, 1.39F, 8, 1, 1, 0.0F, false));
        bone2.cubeList.add(new ModelBox(bone2, 0, 0, -21.5F, 14.5139F, 1.39F, 13, 1, 1, 0.0F, false));
        bone2.cubeList.add(new ModelBox(bone2, 0, 0, -8.5F, 14.5139F, 0.9758F, 8, 1, 1, 0.0F, false));
        bone2.cubeList.add(new ModelBox(bone2, 0, 0, -21.5F, 14.5139F, 0.9758F, 13, 1, 1, 0.0F, false));
        bone2.cubeList.add(new ModelBox(bone2, 0, 0, -8.5F, 7.7357F, 7.754F, 8, 1, 1, 0.0F, false));
        bone2.cubeList.add(new ModelBox(bone2, 0, 0, -21.5F, 7.7357F, 7.754F, 13, 1, 1, 0.0F, false));
        bone2.cubeList.add(new ModelBox(bone2, 0, 0, -0.5F, 6.2761F, -7.262F, 1, 1, 1, 0.0F, false));
        bone2.cubeList.add(new ModelBox(bone2, 0, 0, -0.5F, 14.7614F, 1.2233F, 1, 1, 1, 0.0F, false));

        bone43 = new ModelRenderer(this);
        bone43.setRotationPoint(-0.0F, 0.0F, 0.0F);
        setRotationAngle(bone43, 0.0F, 0.0F, -0.8727F);
        magazine.addChild(bone43);
        bone43.cubeList.add(new ModelBox(bone43, 0, 0, -5.814F, -10.7864F, -3.0F, 10, 4, 11, 0.0F, false));

        m249 = new ModelRenderer(this);
        m249.setRotationPoint(3.3F, 24.0F, 0.0F);
        m249.cubeList.add(new ModelBox(m249, 69, 9, -1.5F, -19.0F, -3.0F, 3, 1, 11, 0.0F, false));
        m249.cubeList.add(new ModelBox(m249, 69, 9, -1.3794F, -19.684F, -3.0F, 1, 1, 11, 0.0F, false));
        m249.cubeList.add(new ModelBox(m249, 0, 75, -0.87F, -18.0F, -2.0F, 1, 3, 9, 0.0F, false));
        m249.cubeList.add(new ModelBox(m249, 69, 9, -0.5F, -18.0F, 7.0F, 2, 1, 1, 0.0F, false));
        m249.cubeList.add(new ModelBox(m249, 69, 9, -0.69F, -19.0F, 7.19F, 2, 2, 1, 0.0F, false));
        m249.cubeList.add(new ModelBox(m249, 69, 9, -0.69F, -19.0F, -3.29F, 2, 2, 1, 0.0F, false));
        m249.cubeList.add(new ModelBox(m249, 69, 9, -1.49F, -17.0F, 7.19F, 2, 2, 1, 0.0F, false));
        m249.cubeList.add(new ModelBox(m249, 69, 9, -1.49F, -17.0F, -3.29F, 2, 2, 1, 0.0F, false));
        m249.cubeList.add(new ModelBox(m249, 69, 9, -1.91F, -15.0F, 7.19F, 2, 1, 1, 0.0F, false));
        m249.cubeList.add(new ModelBox(m249, 69, 9, -1.91F, -15.0F, -3.29F, 2, 1, 1, 0.0F, false));
        m249.cubeList.add(new ModelBox(m249, 69, 9, -1.8681F, -14.2412F, 7.0F, 2, 1, 1, 0.0F, false));
        m249.cubeList.add(new ModelBox(m249, 69, 9, -1.8681F, -14.2412F, -3.0F, 2, 1, 1, 0.0F, false));
        m249.cubeList.add(new ModelBox(m249, 69, 9, -0.5F, -18.0F, -3.0F, 2, 1, 1, 0.0F, false));
        m249.cubeList.add(new ModelBox(m249, 69, 9, -1.5F, -19.0F, 7.0F, 1, 5, 6, 0.0F, false));
        m249.cubeList.add(new ModelBox(m249, 69, 9, -1.5F, -19.0F, 13.0F, 1, 7, 11, 0.0F, false));
        m249.cubeList.add(new ModelBox(m249, 69, 9, -5.3F, -13.0F, 23.0F, 4, 1, 1, 0.0F, false));
        m249.cubeList.add(new ModelBox(m249, 69, 9, -5.8F, -12.0F, 0.0F, 5, 1, 24, 0.0F, false));
        m249.cubeList.add(new ModelBox(m249, 69, 9, -5.8F, -12.0F, -13.0F, 5, 1, 13, 0.0F, false));
        m249.cubeList.add(new ModelBox(m249, 69, 9, -3.8F, -16.888F, -24.0F, 1, 2, 12, 0.0F, false));
        m249.cubeList.add(new ModelBox(m249, 69, 9, -3.8F, -16.888F, -36.0F, 1, 2, 12, 0.0F, false));
        m249.cubeList.add(new ModelBox(m249, 69, 9, -3.8F, -16.888F, -51.0F, 1, 2, 15, 0.0F, false));
        m249.cubeList.add(new ModelBox(m249, 69, 9, -3.8F, -16.888F, -54.0F, 1, 2, 1, 0.0F, false));
        m249.cubeList.add(new ModelBox(m249, 69, 9, -3.8F, -16.888F, -57.0F, 1, 2, 1, 0.0F, false));
        m249.cubeList.add(new ModelBox(m249, 69, 9, -3.8F, -14.184F, -24.0F, 1, 2, 12, 0.0F, false));
        m249.cubeList.add(new ModelBox(m249, 69, 9, -3.8F, -14.184F, -36.0F, 1, 2, 12, 0.0F, false));
        m249.cubeList.add(new ModelBox(m249, 69, 9, -3.8F, -14.184F, -48.0F, 1, 2, 12, 0.0F, false));
        m249.cubeList.add(new ModelBox(m249, 69, 9, -4.5071F, -16.5951F, -24.0F, 1, 1, 12, 0.0F, false));
        m249.cubeList.add(new ModelBox(m249, 69, 9, -4.5071F, -16.5951F, -36.0F, 1, 1, 12, 0.0F, false));
        m249.cubeList.add(new ModelBox(m249, 69, 9, -4.5071F, -16.5951F, -51.0F, 1, 1, 15, 0.0F, false));
        m249.cubeList.add(new ModelBox(m249, 69, 9, -4.5071F, -16.5951F, -54.0F, 1, 1, 1, 0.0F, false));
        m249.cubeList.add(new ModelBox(m249, 69, 9, -4.5071F, -16.5951F, -57.0F, 1, 1, 1, 0.0F, false));
        m249.cubeList.add(new ModelBox(m249, 69, 9, -4.5071F, -13.8911F, -24.0F, 1, 1, 12, 0.0F, false));
        m249.cubeList.add(new ModelBox(m249, 69, 9, -4.5071F, -13.8911F, -36.0F, 1, 1, 12, 0.0F, false));
        m249.cubeList.add(new ModelBox(m249, 69, 9, -4.5071F, -13.8911F, -48.0F, 1, 1, 12, 0.0F, false));
        m249.cubeList.add(new ModelBox(m249, 69, 9, -3.8F, -17.3022F, -24.0F, 1, 1, 12, 0.0F, false));
        m249.cubeList.add(new ModelBox(m249, 69, 9, -3.8F, -17.3022F, -36.0F, 1, 1, 12, 0.0F, false));
        m249.cubeList.add(new ModelBox(m249, 69, 9, -3.8F, -17.3022F, -51.0F, 1, 1, 15, 0.0F, false));
        m249.cubeList.add(new ModelBox(m249, 69, 9, -3.8F, -17.3022F, -54.0F, 1, 1, 1, 0.0F, false));
        m249.cubeList.add(new ModelBox(m249, 69, 9, -3.8F, -17.3022F, -57.0F, 1, 1, 1, 0.0F, false));
        m249.cubeList.add(new ModelBox(m249, 69, 9, -3.8F, -14.5982F, -24.0F, 1, 1, 12, 0.0F, false));
        m249.cubeList.add(new ModelBox(m249, 69, 9, -3.8F, -14.5982F, -36.0F, 1, 1, 12, 0.0F, false));
        m249.cubeList.add(new ModelBox(m249, 69, 9, -3.8F, -14.5982F, -48.0F, 1, 1, 12, 0.0F, false));
        m249.cubeList.add(new ModelBox(m249, 69, 9, -3.0929F, -16.5951F, -24.0F, 1, 1, 12, 0.0F, false));
        m249.cubeList.add(new ModelBox(m249, 69, 9, -3.0929F, -16.5951F, -36.0F, 1, 1, 12, 0.0F, false));
        m249.cubeList.add(new ModelBox(m249, 69, 9, -3.0929F, -16.5951F, -51.0F, 1, 1, 15, 0.0F, false));
        m249.cubeList.add(new ModelBox(m249, 69, 9, -3.0929F, -16.5951F, -54.0F, 1, 1, 1, 0.0F, false));
        m249.cubeList.add(new ModelBox(m249, 69, 9, -3.0929F, -16.5951F, -57.0F, 1, 1, 1, 0.0F, false));
        m249.cubeList.add(new ModelBox(m249, 69, 9, -3.0929F, -13.8911F, -24.0F, 1, 1, 12, 0.0F, false));
        m249.cubeList.add(new ModelBox(m249, 69, 9, -3.0929F, -13.8911F, -36.0F, 1, 1, 12, 0.0F, false));
        m249.cubeList.add(new ModelBox(m249, 69, 9, -3.0929F, -13.8911F, -48.0F, 1, 1, 12, 0.0F, false));
        m249.cubeList.add(new ModelBox(m249, 69, 9, -4.3F, -15.7151F, -35.552F, 2, 2, 2, 0.0F, false));
        m249.cubeList.add(new ModelBox(m249, 69, 9, -4.3F, -15.7151F, -47.552F, 2, 2, 2, 0.0F, false));
        m249.cubeList.add(new ModelBox(m249, 69, 9, -5.3F, -11.134F, 12.0F, 4, 1, 12, 0.0F, false));
        m249.cubeList.add(new ModelBox(m249, 69, 9, -5.3F, -10.2882F, 17.8637F, 4, 3, 5, 0.0F, false));
        m249.cubeList.add(new ModelBox(m249, 69, 9, -4.8F, -10.1171F, 17.3939F, 3, 3, 6, 0.0F, false));
        m249.cubeList.add(new ModelBox(m249, 69, 9, -5.3F, -11.134F, 10.0F, 4, 3, 2, 0.0F, false));
        m249.cubeList.add(new ModelBox(m249, 69, 9, -4.3F, -8.4019F, 10.0F, 2, 2, 2, 0.0F, false));
        m249.cubeList.add(new ModelBox(m249, 69, 9, -4.3F, -8.4019F, 11.184F, 2, 2, 2, 0.0F, false));
        m249.cubeList.add(new ModelBox(m249, 69, 9, -4.3F, -7.1619F, 15.776F, 2, 1, 2, 0.0F, false));
        m249.cubeList.add(new ModelBox(m249, 42, 81, -4.3F, -8.8859F, 8.016F, 2, 2, 1, 0.0F, false));
        m249.cubeList.add(new ModelBox(m249, 42, 81, -3.8F, -8.3859F, 9.264F, 1, 1, 1, 0.0F, false));
        m249.cubeList.add(new ModelBox(m249, 43, 78, -2.984F, -8.3859F, 12.064F, 1, 1, 1, 0.0F, false));
        m249.cubeList.add(new ModelBox(m249, 43, 78, -3.272F, -8.3859F, 11.064F, 1, 1, 1, 0.0F, false));
        m249.cubeList.add(new ModelBox(m249, 43, 78, -4.328F, -8.3859F, 11.064F, 1, 1, 1, 0.0F, true));
        m249.cubeList.add(new ModelBox(m249, 43, 78, -4.616F, -8.3859F, 12.064F, 1, 1, 1, 0.0F, true));
        m249.cubeList.add(new ModelBox(m249, 42, 81, -4.8F, -9.3859F, 8.264F, 3, 3, 1, 0.0F, false));
        m249.cubeList.add(new ModelBox(m249, 69, 9, -4.3F, -10.134F, 11.184F, 2, 2, 2, 0.0F, false));
        m249.cubeList.add(new ModelBox(m249, 69, 9, -4.3F, -10.854F, 13.136F, 2, 1, 5, 0.0F, false));
        m249.cubeList.add(new ModelBox(m249, 69, 9, -6.1F, -19.0F, 16.0F, 1, 7, 8, 0.0F, true));
        m249.cubeList.add(new ModelBox(m249, 69, 9, -6.6092F, -17.5444F, 16.0F, 1, 1, 8, 0.0F, true));
        m249.cubeList.add(new ModelBox(m249, 69, 9, -0.9908F, -17.5444F, 8.0F, 1, 1, 16, 0.0F, false));
        m249.cubeList.add(new ModelBox(m249, 69, 9, -6.6092F, -17.0936F, 16.0F, 1, 1, 8, 0.0F, true));
        m249.cubeList.add(new ModelBox(m249, 69, 9, -0.9908F, -17.0936F, 8.0F, 1, 1, 16, 0.0F, false));
        m249.cubeList.add(new ModelBox(m249, 69, 9, -6.1F, -19.0F, -6.0F, 1, 7, 2, 0.0F, true));
        m249.cubeList.add(new ModelBox(m249, 53, 78, -6.916F, -18.36F, -7.088F, 2, 2, 2, 0.0F, true));
        m249.cubeList.add(new ModelBox(m249, 53, 78, -1.684F, -18.36F, -7.088F, 2, 2, 2, 0.0F, false));
        m249.cubeList.add(new ModelBox(m249, 53, 78, -7.176F, -17.86F, -6.588F, 1, 1, 1, 0.0F, true));
        m249.cubeList.add(new ModelBox(m249, 53, 78, -0.424F, -17.86F, -6.588F, 1, 1, 1, 0.0F, false));
        m249.cubeList.add(new ModelBox(m249, 69, 9, -6.1F, -15.0F, 7.0F, 1, 3, 9, 0.0F, true));
        m249.cubeList.add(new ModelBox(m249, 69, 9, -6.1F, -15.0F, -4.0F, 1, 3, 1, 0.0F, true));
        m249.cubeList.add(new ModelBox(m249, 69, 9, -6.1F, -12.5F, -3.0F, 1, 1, 10, 0.0F, true));
        m249.cubeList.add(new ModelBox(m249, 69, 9, -1.5F, -12.5F, -3.0F, 1, 2, 10, 0.0F, false));
        m249.cubeList.add(new ModelBox(m249, 69, 9, -6.1F, -15.4F, -3.0F, 1, 1, 10, 0.0F, true));
        m249.cubeList.add(new ModelBox(m249, 37, 73, -5.876F, -14.4F, -3.0F, 1, 2, 10, 0.0F, true));
        m249.cubeList.add(new ModelBox(m249, 37, 73, -6.252F, -13.9F, -2.6F, 1, 1, 1, 0.0F, true));
        m249.cubeList.add(new ModelBox(m249, 37, 73, -5.91F, -13.9F, -1.6603F, 1, 1, 1, 0.0F, true));
        m249.cubeList.add(new ModelBox(m249, 34, 77, -7.52F, -17.316F, -2.5F, 2, 1, 2, 0.0F, true));
        m249.cubeList.add(new ModelBox(m249, 34, 77, -8.08F, -17.316F, -2.0F, 1, 1, 1, 0.0F, true));
        m249.cubeList.add(new ModelBox(m249, 34, 77, -7.88F, -17.816F, -2.5F, 1, 2, 2, 0.0F, true));
        m249.cubeList.add(new ModelBox(m249, 34, 77, -7.76F, -17.316F, -3.0F, 1, 1, 3, 0.0F, true));
        m249.cubeList.add(new ModelBox(m249, 0, 69, -5.6F, -18.0F, 7.0F, 1, 3, 11, 0.0F, true));
        m249.cubeList.add(new ModelBox(m249, 0, 69, -5.6F, -18.0F, -4.0F, 1, 3, 11, 0.0F, true));
        m249.cubeList.add(new ModelBox(m249, 69, 9, -5.8F, -19.15F, 23.37F, 5, 7, 1, 0.0F, true));
        m249.cubeList.add(new ModelBox(m249, 71, 19, -5.3F, -18.15F, 24.37F, 4, 5, 1, 0.0F, true));
        m249.cubeList.add(new ModelBox(m249, 71, 19, -5.3F, -12.9873F, 35.7165F, 4, 5, 7, 0.0F, true));
        m249.cubeList.add(new ModelBox(m249, 71, 19, -5.3F, -12.9873F, 43.1725F, 4, 5, 1, 0.0F, true));
        m249.cubeList.add(new ModelBox(m249, 71, 19, -5.3F, -12.9873F, 30.7165F, 4, 2, 5, 0.0F, true));
        m249.cubeList.add(new ModelBox(m249, 71, 19, -5.3F, -11.8113F, 31.4285F, 4, 2, 5, 0.0F, true));
        m249.cubeList.add(new ModelBox(m249, 71, 19, -5.3F, -9.9713F, 32.1965F, 4, 1, 4, 0.0F, true));
        m249.cubeList.add(new ModelBox(m249, 71, 19, -5.3F, -16.9873F, 30.7165F, 4, 4, 12, 0.0F, true));
        m249.cubeList.add(new ModelBox(m249, 71, 19, -5.3F, -16.9873F, 43.1725F, 4, 4, 1, 0.0F, true));
        m249.cubeList.add(new ModelBox(m249, 13, 70, -4.8F, -16.6033F, 42.1725F, 3, 8, 1, 0.0F, true));
        m249.cubeList.add(new ModelBox(m249, 71, 19, -3.2063F, -17.41F, 30.7165F, 1, 1, 12, 0.0F, true));
        m249.cubeList.add(new ModelBox(m249, 71, 19, -3.2063F, -17.41F, 43.1725F, 1, 1, 1, 0.0F, true));
        m249.cubeList.add(new ModelBox(m249, 71, 19, -4.3937F, -17.41F, 30.7165F, 2, 1, 12, 0.0F, false));
        m249.cubeList.add(new ModelBox(m249, 71, 19, -4.3937F, -17.41F, 43.1725F, 2, 1, 1, 0.0F, false));
        m249.cubeList.add(new ModelBox(m249, 69, 9, -5.3F, -20.15F, 9.2F, 4, 1, 15, 0.0F, true));
        m249.cubeList.add(new ModelBox(m249, 7, 80, -4.3F, -22.0416F, 8.67F, 2, 1, 1, 0.0F, true));
        m249.cubeList.add(new ModelBox(m249, 7, 80, -4.3F, -22.0416F, -1.33F, 2, 1, 1, 0.0F, true));
        m249.cubeList.add(new ModelBox(m249, 7, 80, -4.3F, -22.0416F, 10.67F, 2, 1, 1, 0.0F, true));
        m249.cubeList.add(new ModelBox(m249, 7, 80, -4.3F, -22.0416F, 0.67F, 2, 1, 1, 0.0F, true));
        m249.cubeList.add(new ModelBox(m249, 7, 80, -4.3F, -22.0416F, 12.67F, 2, 1, 1, 0.0F, true));
        m249.cubeList.add(new ModelBox(m249, 7, 80, -4.3F, -22.0416F, 2.67F, 2, 1, 1, 0.0F, true));
        m249.cubeList.add(new ModelBox(m249, 7, 80, -4.3F, -22.0416F, 14.67F, 2, 1, 1, 0.0F, true));
        m249.cubeList.add(new ModelBox(m249, 7, 80, -4.3F, -22.0416F, 4.67F, 2, 1, 1, 0.0F, true));
        m249.cubeList.add(new ModelBox(m249, 7, 80, -4.3F, -22.0416F, -5.33F, 2, 1, 1, 0.0F, true));
        m249.cubeList.add(new ModelBox(m249, 7, 80, -5.1669F, -21.5401F, -4.33F, 2, 0, 1, 0.0F, true));
        m249.cubeList.add(new ModelBox(m249, 7, 80, -5.1669F, -21.5401F, -0.33F, 2, 0, 1, 0.0F, true));
        m249.cubeList.add(new ModelBox(m249, 7, 80, -5.1669F, -21.5401F, 3.67F, 2, 0, 1, 0.0F, true));
        m249.cubeList.add(new ModelBox(m249, 7, 80, -5.1669F, -21.5401F, 7.67F, 2, 0, 1, 0.0F, true));
        m249.cubeList.add(new ModelBox(m249, 7, 80, -5.1669F, -21.5401F, 11.67F, 2, 0, 1, 0.0F, true));
        m249.cubeList.add(new ModelBox(m249, 7, 80, -5.1669F, -21.5401F, 15.67F, 2, 0, 1, 0.0F, true));
        m249.cubeList.add(new ModelBox(m249, 7, 80, -5.1669F, -21.5401F, -2.33F, 2, 0, 1, 0.0F, true));
        m249.cubeList.add(new ModelBox(m249, 7, 80, -5.1669F, -21.5401F, 1.67F, 2, 0, 1, 0.0F, true));
        m249.cubeList.add(new ModelBox(m249, 7, 80, -5.1669F, -21.5401F, 5.67F, 2, 0, 1, 0.0F, true));
        m249.cubeList.add(new ModelBox(m249, 7, 80, -5.1669F, -21.5401F, 9.67F, 2, 0, 1, 0.0F, true));
        m249.cubeList.add(new ModelBox(m249, 7, 80, -5.1669F, -21.5401F, 13.67F, 2, 0, 1, 0.0F, true));
        m249.cubeList.add(new ModelBox(m249, 7, 80, -3.434F, -21.5416F, -4.33F, 2, 0, 1, 0.0F, false));
        m249.cubeList.add(new ModelBox(m249, 7, 80, -3.434F, -21.5416F, -0.33F, 2, 0, 1, 0.0F, false));
        m249.cubeList.add(new ModelBox(m249, 7, 80, -3.434F, -21.5416F, 3.67F, 2, 0, 1, 0.0F, false));
        m249.cubeList.add(new ModelBox(m249, 7, 80, -3.434F, -21.5416F, 7.67F, 2, 0, 1, 0.0F, false));
        m249.cubeList.add(new ModelBox(m249, 7, 80, -3.434F, -21.5416F, 11.67F, 2, 0, 1, 0.0F, false));
        m249.cubeList.add(new ModelBox(m249, 7, 80, -3.434F, -21.5416F, 15.67F, 2, 0, 1, 0.0F, false));
        m249.cubeList.add(new ModelBox(m249, 7, 80, -3.434F, -21.5416F, -2.33F, 2, 0, 1, 0.0F, false));
        m249.cubeList.add(new ModelBox(m249, 7, 80, -3.434F, -21.5416F, 1.67F, 2, 0, 1, 0.0F, false));
        m249.cubeList.add(new ModelBox(m249, 7, 80, -3.434F, -21.5416F, 5.67F, 2, 0, 1, 0.0F, false));
        m249.cubeList.add(new ModelBox(m249, 7, 80, -3.434F, -21.5416F, 9.67F, 2, 0, 1, 0.0F, false));
        m249.cubeList.add(new ModelBox(m249, 7, 80, -3.434F, -21.5416F, 13.67F, 2, 0, 1, 0.0F, false));
        m249.cubeList.add(new ModelBox(m249, 7, 80, -4.3F, -22.0416F, 16.67F, 2, 1, 1, 0.0F, true));
        m249.cubeList.add(new ModelBox(m249, 0, 72, -4.8F, -21.0416F, 6.67F, 3, 1, 11, 0.0F, true));
        m249.cubeList.add(new ModelBox(m249, 0, 74, -5.8F, -21.0416F, 18.77F, 5, 2, 5, 0.0F, true));
        m249.cubeList.add(new ModelBox(m249, 0, 74, -4.8F, -22.0416F, 18.77F, 3, 1, 1, 0.0F, true));
        m249.cubeList.add(new ModelBox(m249, 45, 78, -4.8F, -21.1696F, 22.17F, 3, 1, 1, 0.0F, true));
        m249.cubeList.add(new ModelBox(m249, 0, 74, -1.8F, -22.0416F, 18.77F, 1, 1, 5, 0.0F, true));
        m249.cubeList.add(new ModelBox(m249, 0, 74, -5.8F, -22.0416F, 18.77F, 1, 1, 5, 0.0F, true));
        m249.cubeList.add(new ModelBox(m249, 0, 74, -1.8F, -23.5216F, 18.77F, 1, 2, 1, 0.0F, true));
        m249.cubeList.add(new ModelBox(m249, 0, 74, -1.8F, -23.4576F, 20.514F, 1, 1, 2, 0.0F, true));
        m249.cubeList.add(new ModelBox(m249, 0, 74, -5.8F, -23.5216F, 18.77F, 1, 2, 1, 0.0F, true));
        m249.cubeList.add(new ModelBox(m249, 0, 74, -5.8F, -23.4576F, 20.514F, 1, 1, 2, 0.0F, true));
        m249.cubeList.add(new ModelBox(m249, 0, 72, -4.8F, -21.0416F, -5.33F, 3, 1, 12, 0.0F, true));
        m249.cubeList.add(new ModelBox(m249, 7, 80, -4.3F, -22.0416F, 6.67F, 2, 1, 1, 0.0F, true));
        m249.cubeList.add(new ModelBox(m249, 7, 80, -4.3F, -22.0416F, -3.33F, 2, 1, 1, 0.0F, true));
        m249.cubeList.add(new ModelBox(m249, 69, 9, -5.3F, -20.15F, -5.8F, 4, 1, 15, 0.0F, true));
        m249.cubeList.add(new ModelBox(m249, 69, 9, -1.88F, -20.0F, 8.0F, 1, 1, 16, 0.0F, false));
        m249.cubeList.add(new ModelBox(m249, 69, 9, -5.72F, -20.0F, 9.0F, 1, 1, 15, 0.0F, true));
        m249.cubeList.add(new ModelBox(m249, 69, 9, -5.72F, -20.0F, -6.0F, 1, 1, 15, 0.0F, true));
        m249.cubeList.add(new ModelBox(m249, 69, 9, -1.88F, -20.0F, -6.0F, 1, 1, 14, 0.0F, false));
        m249.cubeList.add(new ModelBox(m249, 69, 9, -1.5F, -19.0F, -6.0F, 1, 5, 4, 0.0F, false));
        m249.cubeList.add(new ModelBox(m249, 69, 9, -5.3F, -19.936F, -6.176F, 4, 3, 1, 0.0F, false));
        m249.cubeList.add(new ModelBox(m249, 69, 9, -1.5F, -17.0F, -13.0F, 1, 5, 7, 0.0F, false));
        m249.cubeList.add(new ModelBox(m249, 69, 9, -3.6472F, -18.6383F, -8.0F, 2, 1, 2, 0.0F, false));
        m249.cubeList.add(new ModelBox(m249, 69, 9, -3.6472F, -18.6383F, -13.0F, 2, 1, 2, 0.0F, false));
        m249.cubeList.add(new ModelBox(m249, 69, 9, -2.0736F, -17.8192F, -11.0F, 1, 1, 3, 0.0F, false));
        m249.cubeList.add(new ModelBox(m249, 69, 9, -5.5264F, -17.8192F, -11.0F, 1, 1, 3, 0.0F, true));
        m249.cubeList.add(new ModelBox(m249, 0, 75, -4.3F, -17.9792F, -11.0F, 2, 1, 3, 0.0F, true));
        m249.cubeList.add(new ModelBox(m249, 69, 9, -4.9528F, -18.6383F, -8.0F, 2, 1, 2, 0.0F, true));
        m249.cubeList.add(new ModelBox(m249, 69, 9, -4.9528F, -18.6383F, -13.0F, 2, 1, 2, 0.0F, true));
        m249.cubeList.add(new ModelBox(m249, 69, 9, -6.1F, -17.0F, -13.0F, 1, 5, 7, 0.0F, true));
        m249.cubeList.add(new ModelBox(m249, 69, 9, -6.42F, -17.24F, -15.0F, 1, 4, 2, 0.0F, true));
        m249.cubeList.add(new ModelBox(m249, 69, 9, -6.42F, -17.24F, -19.0F, 1, 4, 2, 0.0F, true));
        m249.cubeList.add(new ModelBox(m249, 69, 9, -6.42F, -17.24F, -23.0F, 1, 4, 2, 0.0F, true));
        m249.cubeList.add(new ModelBox(m249, 69, 9, -6.42F, -17.24F, -27.0F, 1, 4, 2, 0.0F, true));
        m249.cubeList.add(new ModelBox(m249, 69, 9, -6.42F, -17.24F, -31.0F, 1, 4, 2, 0.0F, true));
        m249.cubeList.add(new ModelBox(m249, 69, 9, -6.42F, -17.24F, -17.0F, 1, 1, 2, 0.0F, true));
        m249.cubeList.add(new ModelBox(m249, 69, 9, -6.42F, -17.24F, -21.0F, 1, 1, 2, 0.0F, true));
        m249.cubeList.add(new ModelBox(m249, 69, 9, -6.42F, -17.24F, -25.0F, 1, 1, 2, 0.0F, true));
        m249.cubeList.add(new ModelBox(m249, 69, 9, -6.42F, -17.24F, -29.0F, 1, 1, 2, 0.0F, true));
        m249.cubeList.add(new ModelBox(m249, 69, 9, -6.42F, -14.24F, -17.0F, 1, 1, 2, 0.0F, true));
        m249.cubeList.add(new ModelBox(m249, 69, 9, -6.42F, -14.24F, -21.0F, 1, 1, 2, 0.0F, true));
        m249.cubeList.add(new ModelBox(m249, 69, 9, -6.42F, -14.24F, -25.0F, 1, 1, 2, 0.0F, true));
        m249.cubeList.add(new ModelBox(m249, 69, 9, -6.42F, -14.24F, -29.0F, 1, 1, 2, 0.0F, true));
        m249.cubeList.add(new ModelBox(m249, 69, 9, -1.18F, -17.24F, -15.0F, 1, 4, 2, 0.0F, false));
        m249.cubeList.add(new ModelBox(m249, 69, 9, -1.18F, -17.24F, -19.0F, 1, 4, 2, 0.0F, false));
        m249.cubeList.add(new ModelBox(m249, 69, 9, -1.18F, -17.24F, -23.0F, 1, 4, 2, 0.0F, false));
        m249.cubeList.add(new ModelBox(m249, 69, 9, -1.18F, -17.24F, -27.0F, 1, 4, 2, 0.0F, false));
        m249.cubeList.add(new ModelBox(m249, 69, 9, -1.18F, -17.24F, -31.0F, 1, 4, 2, 0.0F, false));
        m249.cubeList.add(new ModelBox(m249, 69, 9, -0.48F, -14.74F, -31.0F, 1, 5, 13, 0.0F, false));
        m249.cubeList.add(new ModelBox(m249, 69, 9, -0.168F, -13.236F, -32.0F, 1, 1, 4, 0.0F, false));
        m249.cubeList.add(new ModelBox(m249, 34, 74, -7.8F, -11.804F, -29.608F, 9, 2, 1, 0.0F, false));
        m249.cubeList.add(new ModelBox(m249, 34, 74, -7.8F, -10.438F, -28.242F, 9, 1, 1, 0.0F, false));
        m249.cubeList.add(new ModelBox(m249, 34, 74, -7.8F, -10.438F, -30.974F, 9, 1, 1, 0.0F, false));
        m249.cubeList.add(new ModelBox(m249, 34, 74, -7.8F, -10.0719F, -29.608F, 9, 2, 1, 0.0F, false));
        m249.cubeList.add(new ModelBox(m249, 69, 9, -7.432F, -13.236F, -32.0F, 1, 1, 4, 0.0F, true));
        m249.cubeList.add(new ModelBox(m249, 69, 9, -0.168F, -10.5039F, -26.2679F, 1, 1, 1, 0.0F, false));
        m249.cubeList.add(new ModelBox(m249, 69, 9, -7.432F, -10.5039F, -26.2679F, 1, 1, 1, 0.0F, true));
        m249.cubeList.add(new ModelBox(m249, 69, 9, -6.0178F, -9.0897F, -26.2679F, 3, 1, 1, 0.0F, true));
        m249.cubeList.add(new ModelBox(m249, 69, 9, -4.8F, -9.128F, -32.0F, 3, 1, 6, 0.0F, true));
        m249.cubeList.add(new ModelBox(m249, 69, 9, -3.5822F, -9.0897F, -26.2679F, 3, 1, 1, 0.0F, false));
        m249.cubeList.add(new ModelBox(m249, 69, 9, -0.48F, -14.74F, -18.0F, 1, 5, 13, 0.0F, false));
        m249.cubeList.add(new ModelBox(m249, 69, 9, -1.8F, -13.028F, -32.0F, 2, 1, 1, 0.0F, false));
        m249.cubeList.add(new ModelBox(m249, 69, 9, -6.8F, -12.028F, -32.0F, 7, 2, 1, 0.0F, false));
        m249.cubeList.add(new ModelBox(m249, 69, 9, -6.3F, -10.028F, -32.0F, 6, 1, 1, 0.0F, false));
        m249.cubeList.add(new ModelBox(m249, 69, 9, -5.7058F, -9.3258F, -31.0F, 3, 1, 13, 0.0F, false));
        m249.cubeList.add(new ModelBox(m249, 69, 9, -5.7058F, -9.3258F, -18.0F, 3, 1, 13, 0.0F, false));
        m249.cubeList.add(new ModelBox(m249, 69, 9, -2.8942F, -9.3258F, -31.0F, 2, 1, 13, 0.0F, true));
        m249.cubeList.add(new ModelBox(m249, 69, 9, -2.8942F, -9.3258F, -18.0F, 2, 1, 13, 0.0F, true));
        m249.cubeList.add(new ModelBox(m249, 69, 9, -6.8F, -13.028F, -32.0F, 2, 1, 1, 0.0F, true));
        m249.cubeList.add(new ModelBox(m249, 69, 9, -7.12F, -14.74F, -31.0F, 1, 5, 13, 0.0F, true));
        m249.cubeList.add(new ModelBox(m249, 69, 9, -7.12F, -14.74F, -18.0F, 1, 5, 13, 0.0F, true));
        m249.cubeList.add(new ModelBox(m249, 69, 9, -1.18F, -17.24F, -17.0F, 1, 1, 2, 0.0F, false));
        m249.cubeList.add(new ModelBox(m249, 69, 9, -2.5942F, -18.6542F, -15.0F, 1, 1, 2, 0.0F, false));
        m249.cubeList.add(new ModelBox(m249, 69, 9, -5.0058F, -18.6542F, -15.0F, 1, 1, 2, 0.0F, true));
        m249.cubeList.add(new ModelBox(m249, 69, 9, -1.18F, -17.24F, -21.0F, 1, 1, 2, 0.0F, false));
        m249.cubeList.add(new ModelBox(m249, 69, 9, -2.5942F, -18.6542F, -19.0F, 1, 1, 2, 0.0F, false));
        m249.cubeList.add(new ModelBox(m249, 69, 9, -5.0058F, -18.6542F, -19.0F, 1, 1, 2, 0.0F, true));
        m249.cubeList.add(new ModelBox(m249, 69, 9, -1.18F, -17.24F, -25.0F, 1, 1, 2, 0.0F, false));
        m249.cubeList.add(new ModelBox(m249, 69, 9, -2.5942F, -18.6542F, -23.0F, 1, 1, 2, 0.0F, false));
        m249.cubeList.add(new ModelBox(m249, 69, 9, -5.0058F, -18.6542F, -23.0F, 1, 1, 2, 0.0F, true));
        m249.cubeList.add(new ModelBox(m249, 69, 9, -1.18F, -17.24F, -29.0F, 1, 1, 2, 0.0F, false));
        m249.cubeList.add(new ModelBox(m249, 69, 9, -2.5942F, -18.6542F, -27.0F, 1, 1, 2, 0.0F, false));
        m249.cubeList.add(new ModelBox(m249, 69, 9, -5.0058F, -18.6542F, -27.0F, 1, 1, 2, 0.0F, true));
        m249.cubeList.add(new ModelBox(m249, 69, 9, -2.5942F, -18.6542F, -31.0F, 1, 1, 2, 0.0F, false));
        m249.cubeList.add(new ModelBox(m249, 6, 72, -4.8F, -19.6542F, -30.0F, 3, 1, 1, 0.0F, false));
        m249.cubeList.add(new ModelBox(m249, 6, 72, -4.8F, -20.6542F, -30.0F, 3, 1, 4, 0.0F, false));
        m249.cubeList.add(new ModelBox(m249, 18, 78, -4.3F, -21.6542F, -29.0F, 2, 1, 2, 0.0F, false));
        m249.cubeList.add(new ModelBox(m249, 48, 83, -3.8F, -22.6542F, -28.5F, 1, 1, 1, 0.0F, false));
        m249.cubeList.add(new ModelBox(m249, 18, 78, -4.3F, -25.4826F, -29.0F, 2, 1, 2, 0.0F, false));
        m249.cubeList.add(new ModelBox(m249, 18, 78, -1.8858F, -24.0684F, -29.0F, 1, 2, 2, 0.0F, false));
        m249.cubeList.add(new ModelBox(m249, 18, 78, -5.7142F, -24.0684F, -29.0F, 1, 2, 2, 0.0F, true));
        m249.cubeList.add(new ModelBox(m249, 6, 72, -4.8F, -19.6542F, -27.0F, 3, 1, 1, 0.0F, false));
        m249.cubeList.add(new ModelBox(m249, 69, 9, -3.8F, -19.6542F, -29.0F, 1, 1, 2, 0.0F, false));
        m249.cubeList.add(new ModelBox(m249, 69, 9, -5.0058F, -18.6542F, -31.0F, 1, 1, 2, 0.0F, true));
        m249.cubeList.add(new ModelBox(m249, 69, 9, -4.0058F, -18.6542F, -31.0F, 1, 1, 18, 0.0F, true));
        m249.cubeList.add(new ModelBox(m249, 6, 72, -4.0058F, -19.6542F, -29.0F, 1, 1, 2, 0.0F, true));
        m249.cubeList.add(new ModelBox(m249, 69, 9, -3.5942F, -18.6542F, -31.0F, 1, 1, 18, 0.0F, false));
        m249.cubeList.add(new ModelBox(m249, 6, 72, -3.5942F, -19.6542F, -29.0F, 1, 1, 2, 0.0F, false));
        m249.cubeList.add(new ModelBox(m249, 69, 9, -4.3F, -12.1187F, -31.0F, 2, 1, 18, 0.0F, false));
        m249.cubeList.add(new ModelBox(m249, 69, 9, -1.18F, -14.24F, -17.0F, 1, 1, 2, 0.0F, false));
        m249.cubeList.add(new ModelBox(m249, 69, 9, -1.18F, -14.24F, -21.0F, 1, 1, 2, 0.0F, false));
        m249.cubeList.add(new ModelBox(m249, 69, 9, -1.18F, -14.24F, -25.0F, 1, 1, 2, 0.0F, false));
        m249.cubeList.add(new ModelBox(m249, 69, 9, -1.18F, -14.24F, -29.0F, 1, 1, 2, 0.0F, false));
        m249.cubeList.add(new ModelBox(m249, 69, 9, -5.3F, -18.0F, -12.056F, 4, 6, 1, 0.0F, true));
        m249.cubeList.add(new ModelBox(m249, 69, 9, -1.5F, -14.0F, -6.0F, 1, 2, 19, 0.0F, false));
        m249.cubeList.add(new ModelBox(m249, 69, 9, -1.21F, -15.2F, 8.4F, 1, 4, 10, 0.0F, false));
        m249.cubeList.add(new ModelBox(m249, 69, 9, -6.39F, -14.528F, 13.632F, 1, 2, 10, 0.0F, true));
        m249.cubeList.add(new ModelBox(m249, 8, 77, -4.3F, -22.3756F, 21.0F, 2, 2, 1, 0.0F, true));
        m249.cubeList.add(new ModelBox(m249, 8, 77, -2.8F, -23.3756F, 21.0F, 1, 1, 1, 0.0F, true));
        m249.cubeList.add(new ModelBox(m249, 8, 77, -4.8F, -23.3756F, 21.0F, 1, 1, 1, 0.0F, true));

        bone = new ModelRenderer(this);
        bone.setRotationPoint(0.5F, -17.0F, 7.5F);
        setRotationAngle(bone, 0.0F, 0.0F, 0.3491F);
        m249.addChild(bone);
        bone.cubeList.add(new ModelBox(bone, 69, 9, -1.0603F, -0.342F, -0.5F, 2, 4, 1, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 69, 9, -1.0603F, 1.658F, -9.5F, 2, 2, 9, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 69, 9, -1.7444F, -2.2214F, -10.5F, 2, 1, 11, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 69, 9, -1.0603F, -0.342F, -10.5F, 2, 4, 1, 0.0F, false));

        bone77 = new ModelRenderer(this);
        bone77.setRotationPoint(-3.3F, -13.5F, -18.5F);
        setRotationAngle(bone77, 0.0F, 0.0F, -0.7854F);
        m249.addChild(bone77);
        bone77.cubeList.add(new ModelBox(bone77, 69, 9, 0.6279F, -2.335F, -5.5F, 1, 1, 12, 0.0F, false));
        bone77.cubeList.add(new ModelBox(bone77, 69, 9, 0.6279F, -2.335F, -17.5F, 1, 1, 12, 0.0F, false));
        bone77.cubeList.add(new ModelBox(bone77, 69, 9, 0.6279F, -2.335F, -32.5F, 1, 1, 15, 0.0F, false));
        bone77.cubeList.add(new ModelBox(bone77, 69, 9, 0.6279F, -2.335F, -38.5F, 1, 1, 6, 0.0F, false));
        bone77.cubeList.add(new ModelBox(bone77, 69, 9, -1.2841F, -0.423F, -5.5F, 1, 1, 12, 0.0F, false));
        bone77.cubeList.add(new ModelBox(bone77, 69, 9, -1.2841F, -0.423F, -17.5F, 1, 1, 12, 0.0F, false));
        bone77.cubeList.add(new ModelBox(bone77, 69, 9, -1.2841F, -0.423F, -29.5F, 1, 1, 12, 0.0F, false));
        bone77.cubeList.add(new ModelBox(bone77, 69, 9, 1.335F, -3.0421F, -5.5F, 1, 1, 12, 0.0F, false));
        bone77.cubeList.add(new ModelBox(bone77, 69, 9, 1.335F, -3.0421F, -17.5F, 1, 1, 12, 0.0F, false));
        bone77.cubeList.add(new ModelBox(bone77, 69, 9, 1.335F, -3.0421F, -32.5F, 1, 1, 15, 0.0F, false));
        bone77.cubeList.add(new ModelBox(bone77, 69, 9, 1.335F, -3.0421F, -38.5F, 1, 1, 6, 0.0F, false));
        bone77.cubeList.add(new ModelBox(bone77, 69, 9, -0.577F, -1.1301F, -5.5F, 1, 1, 12, 0.0F, false));
        bone77.cubeList.add(new ModelBox(bone77, 69, 9, -0.577F, -1.1301F, -17.5F, 1, 1, 12, 0.0F, false));
        bone77.cubeList.add(new ModelBox(bone77, 69, 9, -0.577F, -1.1301F, -29.5F, 1, 1, 12, 0.0F, false));
        bone77.cubeList.add(new ModelBox(bone77, 69, 9, 1.335F, -1.6279F, -5.5F, 1, 1, 12, 0.0F, true));
        bone77.cubeList.add(new ModelBox(bone77, 69, 9, 1.335F, -1.6279F, -17.5F, 1, 1, 12, 0.0F, true));
        bone77.cubeList.add(new ModelBox(bone77, 69, 9, 1.335F, -1.6279F, -32.5F, 1, 1, 15, 0.0F, true));
        bone77.cubeList.add(new ModelBox(bone77, 69, 9, 1.335F, -1.6279F, -38.5F, 1, 1, 6, 0.0F, true));
        bone77.cubeList.add(new ModelBox(bone77, 69, 9, -0.577F, 0.2841F, -5.5F, 1, 1, 12, 0.0F, true));
        bone77.cubeList.add(new ModelBox(bone77, 69, 9, -0.577F, 0.2841F, -17.5F, 1, 1, 12, 0.0F, true));
        bone77.cubeList.add(new ModelBox(bone77, 69, 9, -0.577F, 0.2841F, -29.5F, 1, 1, 12, 0.0F, true));
        bone77.cubeList.add(new ModelBox(bone77, 69, 9, 2.0421F, -2.335F, -5.5F, 1, 1, 12, 0.0F, true));
        bone77.cubeList.add(new ModelBox(bone77, 69, 9, 2.0421F, -2.335F, -17.5F, 1, 1, 12, 0.0F, true));
        bone77.cubeList.add(new ModelBox(bone77, 69, 9, 2.0421F, -2.335F, -32.5F, 1, 1, 15, 0.0F, true));
        bone77.cubeList.add(new ModelBox(bone77, 69, 9, 2.0421F, -2.335F, -38.5F, 1, 1, 6, 0.0F, true));
        bone77.cubeList.add(new ModelBox(bone77, 69, 9, 0.1301F, -0.423F, -5.5F, 1, 1, 12, 0.0F, true));
        bone77.cubeList.add(new ModelBox(bone77, 69, 9, 0.1301F, -0.423F, -17.5F, 1, 1, 12, 0.0F, true));
        bone77.cubeList.add(new ModelBox(bone77, 69, 9, 0.1301F, -0.423F, -29.5F, 1, 1, 12, 0.0F, true));

        bone46 = new ModelRenderer(this);
        bone46.setRotationPoint(-3.3F, -5.586F, 22.476F);
        setRotationAngle(bone46, 0.3491F, 0.0F, 0.0F);
        m249.addChild(bone46);
        bone46.cubeList.add(new ModelBox(bone46, 69, 9, -2.0F, -3.177F, -3.7519F, 4, 7, 5, 0.0F, false));
        bone46.cubeList.add(new ModelBox(bone46, 69, 9, 1.0F, 3.823F, -3.7519F, 1, 2, 5, 0.0F, false));
        bone46.cubeList.add(new ModelBox(bone46, 69, 9, -2.0F, 3.823F, -3.7519F, 1, 2, 5, 0.0F, false));

        bone47 = new ModelRenderer(this);
        bone47.setRotationPoint(-3.3F, -5.586F, 22.476F);
        setRotationAngle(bone47, 0.3491F, 0.0F, 0.0F);
        m249.addChild(bone47);
        bone47.cubeList.add(new ModelBox(bone47, 69, 9, -1.5F, -3.177F, -4.2519F, 3, 7, 6, 0.0F, false));
        bone47.cubeList.add(new ModelBox(bone47, 69, 9, -1.5F, 3.823F, -4.2519F, 3, 2, 1, 0.0F, false));
        bone47.cubeList.add(new ModelBox(bone47, 69, 9, -1.5F, 3.823F, 0.7481F, 3, 2, 1, 0.0F, false));
        bone47.cubeList.add(new ModelBox(bone47, 69, 9, -1.0F, 4.463F, -3.2519F, 2, 1, 4, 0.0F, false));

        bone44 = new ModelRenderer(this);
        bone44.setRotationPoint(-3.3F, -10.5F, 18.0F);
        setRotationAngle(bone44, 0.0F, 0.0F, 0.5236F);
        m249.addChild(bone44);
        bone44.cubeList.add(new ModelBox(bone44, 69, 9, 0.9151F, -1.683F, -6.0F, 1, 1, 12, 0.0F, false));
        bone44.cubeList.add(new ModelBox(bone44, 69, 9, 1.9151F, 1.049F, -8.0F, 1, 2, 2, 0.0F, false));

        bone45 = new ModelRenderer(this);
        bone45.setRotationPoint(-3.3F, -10.5F, 18.0F);
        setRotationAngle(bone45, 0.0F, 0.0F, -0.5236F);
        m249.addChild(bone45);
        bone45.cubeList.add(new ModelBox(bone45, 69, 9, -1.9151F, -1.683F, -6.0F, 1, 1, 12, 0.0F, true));
        bone45.cubeList.add(new ModelBox(bone45, 69, 9, -2.9151F, 1.049F, -8.0F, 1, 2, 2, 0.0F, true));

        bone48 = new ModelRenderer(this);
        bone48.setRotationPoint(-3.3F, -6.6619F, 14.484F);
        setRotationAngle(bone48, -0.0873F, 0.0F, 0.0F);
        m249.addChild(bone48);
        bone48.cubeList.add(new ModelBox(bone48, 69, 9, -1.0F, -0.6145F, -1.6693F, 2, 1, 3, 0.0F, false));

        bone87 = new ModelRenderer(this);
        bone87.setRotationPoint(-5.752F, -13.4F, -2.1F);
        setRotationAngle(bone87, 0.0F, 0.3491F, 0.0F);
        m249.addChild(bone87);
        bone87.cubeList.add(new ModelBox(bone87, 37, 73, -0.6409F, -0.5F, 0.2988F, 1, 1, 1, 0.0F, true));

        bone41 = new ModelRenderer(this);
        bone41.setRotationPoint(-5.6F, -12.0F, 2.0F);
        setRotationAngle(bone41, 0.4363F, 0.0F, 0.0F);
        m249.addChild(bone41);
        bone41.cubeList.add(new ModelBox(bone41, 69, 9, -0.5F, 1.5662F, 4.3202F, 1, 1, 2, 0.0F, true));
        bone41.cubeList.add(new ModelBox(bone41, 69, 9, 4.1F, 1.4726F, 3.8976F, 1, 2, 2, 0.0F, false));

        bone42 = new ModelRenderer(this);
        bone42.setRotationPoint(-5.6F, -12.0F, -2.0F);
        setRotationAngle(bone42, -0.4363F, 0.0F, 0.0F);
        m249.addChild(bone42);
        bone42.cubeList.add(new ModelBox(bone42, 69, 9, -0.5F, -0.1242F, -2.695F, 1, 1, 2, 0.0F, true));
        bone42.cubeList.add(new ModelBox(bone42, 69, 9, 4.1F, -0.2179F, -3.2724F, 1, 2, 3, 0.0F, false));

        bone32 = new ModelRenderer(this);
        bone32.setRotationPoint(-7.36F, -16.64F, -1.5F);
        setRotationAngle(bone32, -0.5236F, 0.0F, 0.0F);
        m249.addChild(bone32);
        bone32.cubeList.add(new ModelBox(bone32, 34, 77, -0.4F, -0.6524F, -1.588F, 1, 1, 3, 0.0F, true));

        bone33 = new ModelRenderer(this);
        bone33.setRotationPoint(-7.36F, -16.64F, -1.5F);
        setRotationAngle(bone33, -1.0472F, 0.0F, 0.0F);
        m249.addChild(bone33);
        bone33.cubeList.add(new ModelBox(bone33, 34, 77, -0.4F, -0.588F, -1.6524F, 1, 1, 3, 0.0F, true));

        bone34 = new ModelRenderer(this);
        bone34.setRotationPoint(-7.36F, -16.64F, -1.5F);
        setRotationAngle(bone34, -1.5708F, 0.0F, 0.0F);
        m249.addChild(bone34);
        bone34.cubeList.add(new ModelBox(bone34, 34, 77, -0.4F, -0.5F, -1.676F, 1, 1, 3, 0.0F, true));

        bone35 = new ModelRenderer(this);
        bone35.setRotationPoint(-7.36F, -16.64F, -1.5F);
        setRotationAngle(bone35, -2.0944F, 0.0F, 0.0F);
        m249.addChild(bone35);
        bone35.cubeList.add(new ModelBox(bone35, 34, 77, -0.4F, -0.412F, -1.6524F, 1, 1, 3, 0.0F, true));

        bone36 = new ModelRenderer(this);
        bone36.setRotationPoint(-7.36F, -16.64F, -1.5F);
        setRotationAngle(bone36, -2.618F, 0.0F, 0.0F);
        m249.addChild(bone36);
        bone36.cubeList.add(new ModelBox(bone36, 34, 77, -0.4F, -0.3476F, -1.588F, 1, 1, 3, 0.0F, true));

        bone28 = new ModelRenderer(this);
        bone28.setRotationPoint(-8.13F, -18.0F, 6.0F);
        setRotationAngle(bone28, 0.0F, 0.0F, 0.1745F);
        m249.addChild(bone28);
        bone28.cubeList.add(new ModelBox(bone28, 69, 9, 1.5768F, -0.8154F, -10.0F, 1, 1, 28, 0.0F, true));

        bone54 = new ModelRenderer(this);
        bone54.setRotationPoint(1.53F, -18.0F, 6.0F);
        setRotationAngle(bone54, 0.0F, 0.0F, -0.1745F);
        m249.addChild(bone54);
        bone54.cubeList.add(new ModelBox(bone54, 69, 9, -2.5768F, -0.8154F, 2.0F, 1, 1, 16, 0.0F, false));

        bone30 = new ModelRenderer(this);
        bone30.setRotationPoint(-7.94F, -18.54F, 6.0F);
        setRotationAngle(bone30, 0.0F, 0.0F, 0.6109F);
        m249.addChild(bone30);
        bone30.cubeList.add(new ModelBox(bone30, 69, 9, 1.2385F, -1.8541F, -10.0F, 1, 1, 28, 0.0F, true));

        bone53 = new ModelRenderer(this);
        bone53.setRotationPoint(1.34F, -18.54F, 6.0F);
        setRotationAngle(bone53, 0.0F, 0.0F, -0.6109F);
        m249.addChild(bone53);
        bone53.cubeList.add(new ModelBox(bone53, 69, 9, -2.2385F, -1.8541F, 2.0F, 1, 1, 16, 0.0F, false));

        bone31 = new ModelRenderer(this);
        bone31.setRotationPoint(-7.94F, -14.83F, 6.0F);
        setRotationAngle(bone31, 0.0F, 0.0F, -0.6109F);
        m249.addChild(bone31);
        bone31.cubeList.add(new ModelBox(bone31, 69, 9, 1.3922F, 0.6345F, -10.0F, 1, 1, 28, 0.0F, true));

        bone56 = new ModelRenderer(this);
        bone56.setRotationPoint(1.34F, -14.83F, 6.0F);
        setRotationAngle(bone56, 0.0F, 0.0F, 0.6109F);
        m249.addChild(bone56);
        bone56.cubeList.add(new ModelBox(bone56, 69, 9, -2.3922F, 0.6345F, 2.0F, 1, 1, 16, 0.0F, false));

        bone29 = new ModelRenderer(this);
        bone29.setRotationPoint(-8.13F, -15.0F, 6.0F);
        setRotationAngle(bone29, 0.0F, 0.0F, -0.1745F);
        m249.addChild(bone29);
        bone29.cubeList.add(new ModelBox(bone29, 69, 9, 1.6876F, -0.8129F, -10.0F, 1, 1, 28, 0.0F, true));

        bone55 = new ModelRenderer(this);
        bone55.setRotationPoint(1.53F, -15.0F, 6.0F);
        setRotationAngle(bone55, 0.0F, 0.0F, 0.1745F);
        m249.addChild(bone55);
        bone55.cubeList.add(new ModelBox(bone55, 69, 9, -2.6876F, -0.8129F, 2.0F, 1, 1, 16, 0.0F, false));

        bone51 = new ModelRenderer(this);
        bone51.setRotationPoint(-3.3F, -17.4873F, 36.7165F);
        setRotationAngle(bone51, 0.0F, 0.0F, -0.4363F);
        m249.addChild(bone51);
        bone51.cubeList.add(new ModelBox(bone51, 71, 19, -2.0239F, -0.3921F, -6.0F, 1, 1, 12, 0.0F, true));
        bone51.cubeList.add(new ModelBox(bone51, 71, 19, -2.0239F, -0.3921F, 6.456F, 1, 1, 1, 0.0F, true));

        bone52 = new ModelRenderer(this);
        bone52.setRotationPoint(-3.3F, -17.4873F, 36.7165F);
        setRotationAngle(bone52, 0.0F, 0.0F, 0.4363F);
        m249.addChild(bone52);
        bone52.cubeList.add(new ModelBox(bone52, 71, 19, 1.0239F, -0.3921F, -6.0F, 1, 1, 12, 0.0F, false));
        bone52.cubeList.add(new ModelBox(bone52, 71, 19, 1.0239F, -0.3921F, 6.456F, 1, 1, 1, 0.0F, false));

        bone49 = new ModelRenderer(this);
        bone49.setRotationPoint(-3.3F, -15.65F, 24.87F);
        setRotationAngle(bone49, -0.2618F, 0.0F, 0.0F);
        m249.addChild(bone49);
        bone49.cubeList.add(new ModelBox(bone49, 71, 19, -2.0F, -2.5442F, -0.1641F, 4, 5, 7, 0.0F, true));
        bone49.cubeList.add(new ModelBox(bone49, 71, 19, -2.0F, 3.5943F, 8.4602F, 4, 1, 4, 0.0F, true));

        bone50 = new ModelRenderer(this);
        bone50.setRotationPoint(-3.3F, -16.85F, 29.37F);
        setRotationAngle(bone50, -0.9599F, 0.0F, 0.0F);
        m249.addChild(bone50);
        bone50.cubeList.add(new ModelBox(bone50, 71, 19, -2.0F, 1.4558F, 3.8359F, 4, 1, 4, 0.0F, true));
        bone50.cubeList.add(new ModelBox(bone50, 71, 19, -2.0F, -1.1818F, 0.6598F, 4, 1, 1, 0.0F, true));
        bone50.cubeList.add(new ModelBox(bone50, 71, 19, -0.9063F, -1.4242F, 0.3137F, 2, 1, 1, 0.0F, true));
        bone50.cubeList.add(new ModelBox(bone50, 71, 19, -1.0937F, -1.4242F, 0.3137F, 1, 1, 1, 0.0F, false));

        bone5 = new ModelRenderer(this);
        bone5.setRotationPoint(-3.3F, -22.2416F, 20.79F);
        setRotationAngle(bone5, -0.3491F, 0.0F, 0.0F);
        m249.addChild(bone5);
        bone5.cubeList.add(new ModelBox(bone5, 0, 74, -2.5F, -0.8504F, -1.2648F, 1, 2, 4, 0.0F, true));
        bone5.cubeList.add(new ModelBox(bone5, 0, 74, 1.5F, -0.8504F, -1.2648F, 1, 2, 4, 0.0F, true));

        bone39 = new ModelRenderer(this);
        bone39.setRotationPoint(-5.3F, -22.5216F, 19.27F);
        setRotationAngle(bone39, 0.0F, 0.0F, -0.5236F);
        m249.addChild(bone39);
        bone39.cubeList.add(new ModelBox(bone39, 0, 74, -0.067F, -0.616F, -0.5F, 1, 2, 1, 0.0F, true));

        bone40 = new ModelRenderer(this);
        bone40.setRotationPoint(-1.3F, -22.5216F, 19.27F);
        setRotationAngle(bone40, 0.0F, 0.0F, 0.5236F);
        m249.addChild(bone40);
        bone40.cubeList.add(new ModelBox(bone40, 0, 74, -0.933F, -0.616F, -0.5F, 1, 2, 1, 0.0F, false));

        bone3 = new ModelRenderer(this);
        bone3.setRotationPoint(-3.3F, -21.65F, 7.27F);
        setRotationAngle(bone3, 0.0F, 0.0F, 0.5236F);
        m249.addChild(bone3);
        bone3.cubeList.add(new ModelBox(bone3, 0, 78, 0.6702F, -0.8392F, 1.4F, 1, 1, 1, 0.0F, true));
        bone3.cubeList.add(new ModelBox(bone3, 0, 78, 0.6702F, -0.8392F, -8.6F, 1, 1, 1, 0.0F, true));
        bone3.cubeList.add(new ModelBox(bone3, 0, 78, 0.6702F, -0.8392F, 3.4F, 1, 1, 1, 0.0F, true));
        bone3.cubeList.add(new ModelBox(bone3, 0, 78, 0.6702F, -0.8392F, -6.6F, 1, 1, 1, 0.0F, true));
        bone3.cubeList.add(new ModelBox(bone3, 0, 78, 0.6702F, -0.8392F, 7.4F, 1, 1, 1, 0.0F, true));
        bone3.cubeList.add(new ModelBox(bone3, 0, 78, 0.6702F, -0.8392F, -2.6F, 1, 1, 1, 0.0F, true));
        bone3.cubeList.add(new ModelBox(bone3, 0, 78, 0.6702F, -0.8392F, -12.6F, 1, 1, 1, 0.0F, true));
        bone3.cubeList.add(new ModelBox(bone3, 0, 78, 1.6702F, -0.8392F, -11.6F, 0, 1, 1, 0.0F, true));
        bone3.cubeList.add(new ModelBox(bone3, 0, 78, 1.6702F, -0.8392F, -7.6F, 0, 1, 1, 0.0F, true));
        bone3.cubeList.add(new ModelBox(bone3, 0, 78, 1.6702F, -0.8392F, -3.6F, 0, 1, 1, 0.0F, true));
        bone3.cubeList.add(new ModelBox(bone3, 0, 78, 1.6702F, -0.8392F, 0.4F, 0, 1, 1, 0.0F, true));
        bone3.cubeList.add(new ModelBox(bone3, 0, 78, 1.6702F, -0.8392F, 4.4F, 0, 1, 1, 0.0F, true));
        bone3.cubeList.add(new ModelBox(bone3, 0, 78, 1.6702F, -0.8392F, 8.4F, 0, 1, 1, 0.0F, true));
        bone3.cubeList.add(new ModelBox(bone3, 0, 78, 1.6702F, -0.8392F, -9.6F, 0, 1, 1, 0.0F, true));
        bone3.cubeList.add(new ModelBox(bone3, 0, 78, 1.6702F, -0.8392F, -5.6F, 0, 1, 1, 0.0F, true));
        bone3.cubeList.add(new ModelBox(bone3, 0, 78, 1.6702F, -0.8392F, -1.6F, 0, 1, 1, 0.0F, true));
        bone3.cubeList.add(new ModelBox(bone3, 0, 78, 1.6702F, -0.8392F, 2.4F, 0, 1, 1, 0.0F, true));
        bone3.cubeList.add(new ModelBox(bone3, 0, 78, 1.6702F, -0.8392F, 6.4F, 0, 1, 1, 0.0F, true));
        bone3.cubeList.add(new ModelBox(bone3, 0, 78, 0.6702F, -0.8392F, 9.4F, 1, 1, 1, 0.0F, true));
        bone3.cubeList.add(new ModelBox(bone3, 0, 78, 0.6702F, -0.8392F, -0.6F, 1, 1, 1, 0.0F, true));
        bone3.cubeList.add(new ModelBox(bone3, 0, 78, 0.6702F, -0.8392F, -10.6F, 1, 1, 1, 0.0F, true));
        bone3.cubeList.add(new ModelBox(bone3, 0, 78, 0.6702F, -0.8392F, 5.4F, 1, 1, 1, 0.0F, true));
        bone3.cubeList.add(new ModelBox(bone3, 0, 78, 0.6702F, -0.8392F, -4.6F, 1, 1, 1, 0.0F, true));

        bone4 = new ModelRenderer(this);
        bone4.setRotationPoint(-3.3F, -21.65F, 7.27F);
        setRotationAngle(bone4, 0.0F, 0.0F, -0.5236F);
        m249.addChild(bone4);
        bone4.cubeList.add(new ModelBox(bone4, 0, 78, -1.6702F, -0.8392F, 1.4F, 1, 1, 1, 0.0F, false));
        bone4.cubeList.add(new ModelBox(bone4, 0, 78, -1.6702F, -0.8392F, -8.6F, 1, 1, 1, 0.0F, false));
        bone4.cubeList.add(new ModelBox(bone4, 0, 78, -1.6702F, -0.8392F, 3.4F, 1, 1, 1, 0.0F, false));
        bone4.cubeList.add(new ModelBox(bone4, 0, 78, -1.6702F, -0.8392F, -6.6F, 1, 1, 1, 0.0F, false));
        bone4.cubeList.add(new ModelBox(bone4, 0, 78, -1.6702F, -0.8392F, 5.4F, 1, 1, 1, 0.0F, false));
        bone4.cubeList.add(new ModelBox(bone4, 0, 78, -1.6702F, -0.8392F, -4.6F, 1, 1, 1, 0.0F, false));
        bone4.cubeList.add(new ModelBox(bone4, 0, 78, -1.6702F, -0.8392F, 7.4F, 1, 1, 1, 0.0F, false));
        bone4.cubeList.add(new ModelBox(bone4, 0, 78, -1.6702F, -0.8392F, -2.6F, 1, 1, 1, 0.0F, false));
        bone4.cubeList.add(new ModelBox(bone4, 0, 78, -1.6702F, -0.8392F, -12.6F, 1, 1, 1, 0.0F, false));
        bone4.cubeList.add(new ModelBox(bone4, 0, 78, -1.6702F, -0.8392F, -11.6F, 0, 1, 1, 0.0F, false));
        bone4.cubeList.add(new ModelBox(bone4, 0, 78, -1.6702F, -0.8392F, -7.6F, 0, 1, 1, 0.0F, false));
        bone4.cubeList.add(new ModelBox(bone4, 0, 78, -1.6702F, -0.8392F, -3.6F, 0, 1, 1, 0.0F, false));
        bone4.cubeList.add(new ModelBox(bone4, 0, 78, -1.6702F, -0.8392F, 0.4F, 0, 1, 1, 0.0F, false));
        bone4.cubeList.add(new ModelBox(bone4, 0, 78, -1.6702F, -0.8392F, 4.4F, 0, 1, 1, 0.0F, false));
        bone4.cubeList.add(new ModelBox(bone4, 0, 78, -1.6702F, -0.8392F, 8.4F, 0, 1, 1, 0.0F, false));
        bone4.cubeList.add(new ModelBox(bone4, 0, 78, -1.6702F, -0.8392F, -9.6F, 0, 1, 1, 0.0F, false));
        bone4.cubeList.add(new ModelBox(bone4, 0, 78, -1.6702F, -0.8392F, -5.6F, 0, 1, 1, 0.0F, false));
        bone4.cubeList.add(new ModelBox(bone4, 0, 78, -1.6702F, -0.8392F, -1.6F, 0, 1, 1, 0.0F, false));
        bone4.cubeList.add(new ModelBox(bone4, 0, 78, -1.6702F, -0.8392F, 2.4F, 0, 1, 1, 0.0F, false));
        bone4.cubeList.add(new ModelBox(bone4, 0, 78, -1.6702F, -0.8392F, 6.4F, 0, 1, 1, 0.0F, false));
        bone4.cubeList.add(new ModelBox(bone4, 0, 78, -1.6702F, -0.8392F, 9.4F, 1, 1, 1, 0.0F, false));
        bone4.cubeList.add(new ModelBox(bone4, 0, 78, -1.6702F, -0.8392F, -0.6F, 1, 1, 1, 0.0F, false));
        bone4.cubeList.add(new ModelBox(bone4, 0, 78, -1.6702F, -0.8392F, -10.6F, 1, 1, 1, 0.0F, false));

        bone26 = new ModelRenderer(this);
        bone26.setRotationPoint(1.28F, -19.7F, 18.2F);
        setRotationAngle(bone26, 0.0F, 0.0F, -0.5236F);
        m249.addChild(bone26);
        bone26.cubeList.add(new ModelBox(bone26, 69, 9, -2.8915F, -1.2838F, -10.2F, 1, 1, 16, 0.0F, true));
        bone26.cubeList.add(new ModelBox(bone26, 69, 9, -2.8915F, -1.2838F, -24.2F, 1, 1, 3, 0.0F, true));

        bone27 = new ModelRenderer(this);
        bone27.setRotationPoint(-7.88F, -19.7F, 18.2F);
        setRotationAngle(bone27, 0.0F, 0.0F, 0.5236F);
        m249.addChild(bone27);
        bone27.cubeList.add(new ModelBox(bone27, 69, 9, 1.8915F, -1.2838F, -10.2F, 1, 1, 16, 0.0F, false));
        bone27.cubeList.add(new ModelBox(bone27, 69, 9, 1.8915F, -1.2838F, -24.2F, 1, 1, 14, 0.0F, false));

        bone59 = new ModelRenderer(this);
        bone59.setRotationPoint(-3.3F, -17.4792F, -9.5F);
        setRotationAngle(bone59, 0.0F, 0.0F, -0.1745F);
        m249.addChild(bone59);
        bone59.cubeList.add(new ModelBox(bone59, 0, 75, -1.0F, -0.5F, -1.5F, 2, 1, 3, 0.0F, true));

        bone60 = new ModelRenderer(this);
        bone60.setRotationPoint(-3.3F, -17.4792F, -9.5F);
        setRotationAngle(bone60, 0.0F, 0.0F, -0.3491F);
        m249.addChild(bone60);
        bone60.cubeList.add(new ModelBox(bone60, 0, 75, -1.0F, -0.5F, -1.5F, 2, 1, 3, 0.0F, true));

        bone61 = new ModelRenderer(this);
        bone61.setRotationPoint(-3.3F, -17.4792F, -9.5F);
        setRotationAngle(bone61, 0.0F, 0.0F, -0.5236F);
        m249.addChild(bone61);
        bone61.cubeList.add(new ModelBox(bone61, 0, 75, -1.0F, -0.5F, -1.5F, 2, 1, 3, 0.0F, true));

        bone62 = new ModelRenderer(this);
        bone62.setRotationPoint(-3.3F, -17.4792F, -9.5F);
        setRotationAngle(bone62, 0.0F, 0.0F, -0.6981F);
        m249.addChild(bone62);
        bone62.cubeList.add(new ModelBox(bone62, 0, 75, -1.0F, -0.5F, -1.5F, 2, 1, 3, 0.0F, true));

        bone63 = new ModelRenderer(this);
        bone63.setRotationPoint(-3.3F, -17.4792F, -9.5F);
        setRotationAngle(bone63, 0.0F, 0.0F, -0.8727F);
        m249.addChild(bone63);
        bone63.cubeList.add(new ModelBox(bone63, 0, 75, -1.0F, -0.5F, -1.5F, 2, 1, 3, 0.0F, true));

        bone64 = new ModelRenderer(this);
        bone64.setRotationPoint(-3.3F, -17.4792F, -9.5F);
        setRotationAngle(bone64, 0.0F, 0.0F, -1.0472F);
        m249.addChild(bone64);
        bone64.cubeList.add(new ModelBox(bone64, 0, 75, -1.0F, -0.5F, -1.5F, 2, 1, 3, 0.0F, true));

        bone65 = new ModelRenderer(this);
        bone65.setRotationPoint(-3.3F, -17.4792F, -9.5F);
        setRotationAngle(bone65, 0.0F, 0.0F, -1.2217F);
        m249.addChild(bone65);
        bone65.cubeList.add(new ModelBox(bone65, 0, 75, -1.0F, -0.5F, -1.5F, 2, 1, 3, 0.0F, true));

        bone66 = new ModelRenderer(this);
        bone66.setRotationPoint(-3.3F, -17.4792F, -9.5F);
        setRotationAngle(bone66, 0.0F, 0.0F, -1.3963F);
        m249.addChild(bone66);
        bone66.cubeList.add(new ModelBox(bone66, 0, 75, -1.0F, -0.5F, -1.5F, 2, 1, 3, 0.0F, true));

        bone67 = new ModelRenderer(this);
        bone67.setRotationPoint(-3.3F, -17.4792F, -9.5F);
        setRotationAngle(bone67, 0.0F, 0.0F, -1.5708F);
        m249.addChild(bone67);
        bone67.cubeList.add(new ModelBox(bone67, 0, 75, -1.0F, -0.5F, -1.5F, 2, 1, 3, 0.0F, true));

        bone68 = new ModelRenderer(this);
        bone68.setRotationPoint(-3.3F, -17.4792F, -9.5F);
        setRotationAngle(bone68, 0.0F, 0.0F, -1.7453F);
        m249.addChild(bone68);
        bone68.cubeList.add(new ModelBox(bone68, 0, 75, -1.0F, -0.5F, -1.5F, 2, 1, 3, 0.0F, true));

        bone69 = new ModelRenderer(this);
        bone69.setRotationPoint(-3.3F, -17.4792F, -9.5F);
        setRotationAngle(bone69, 0.0F, 0.0F, -1.9199F);
        m249.addChild(bone69);
        bone69.cubeList.add(new ModelBox(bone69, 0, 75, -1.0F, -0.5F, -1.5F, 2, 1, 3, 0.0F, true));

        bone70 = new ModelRenderer(this);
        bone70.setRotationPoint(-3.3F, -17.4792F, -9.5F);
        setRotationAngle(bone70, 0.0F, 0.0F, -2.0944F);
        m249.addChild(bone70);
        bone70.cubeList.add(new ModelBox(bone70, 0, 75, -1.0F, -0.5F, -1.5F, 2, 1, 3, 0.0F, true));

        bone71 = new ModelRenderer(this);
        bone71.setRotationPoint(-3.3F, -17.4792F, -9.5F);
        setRotationAngle(bone71, 0.0F, 0.0F, -2.2689F);
        m249.addChild(bone71);
        bone71.cubeList.add(new ModelBox(bone71, 0, 75, -1.0F, -0.5F, -1.5F, 2, 1, 3, 0.0F, true));
        bone71.cubeList.add(new ModelBox(bone71, 0, 75, 1.0F, -0.5F, -1.196F, 5, 1, 1, 0.0F, true));
        bone71.cubeList.add(new ModelBox(bone71, 0, 75, 5.872F, -1.0F, 5.584F, 2, 2, 1, 0.0F, true));
        bone71.cubeList.add(new ModelBox(bone71, 0, 75, 6.972F, -0.5F, 5.584F, 1, 1, 1, 0.0F, true));
        bone71.cubeList.add(new ModelBox(bone71, 0, 75, 5.772F, -0.5F, 5.584F, 1, 1, 1, 0.0F, true));
        bone71.cubeList.add(new ModelBox(bone71, 0, 75, 6.372F, 0.1F, 5.584F, 1, 1, 1, 0.0F, true));
        bone71.cubeList.add(new ModelBox(bone71, 0, 75, 6.372F, -1.1F, 5.584F, 1, 1, 1, 0.0F, true));
        bone71.cubeList.add(new ModelBox(bone71, 0, 75, 5.872F, -1.0F, 2.284F, 2, 2, 1, 0.0F, true));
        bone71.cubeList.add(new ModelBox(bone71, 0, 75, 6.972F, -0.5F, 2.284F, 1, 1, 1, 0.0F, true));
        bone71.cubeList.add(new ModelBox(bone71, 0, 75, 5.772F, -0.5F, 2.284F, 1, 1, 1, 0.0F, true));
        bone71.cubeList.add(new ModelBox(bone71, 0, 75, 6.372F, 0.1F, 2.284F, 1, 1, 1, 0.0F, true));
        bone71.cubeList.add(new ModelBox(bone71, 0, 75, 6.372F, -1.1F, 2.284F, 1, 1, 1, 0.0F, true));
        bone71.cubeList.add(new ModelBox(bone71, 0, 75, 5.872F, -1.0F, -1.016F, 2, 2, 1, 0.0F, true));
        bone71.cubeList.add(new ModelBox(bone71, 0, 75, 6.972F, -0.5F, -1.016F, 1, 1, 1, 0.0F, true));
        bone71.cubeList.add(new ModelBox(bone71, 0, 75, 5.772F, -0.5F, -1.016F, 1, 1, 1, 0.0F, true));
        bone71.cubeList.add(new ModelBox(bone71, 0, 75, 6.372F, 0.1F, -1.016F, 1, 1, 1, 0.0F, true));
        bone71.cubeList.add(new ModelBox(bone71, 0, 75, 6.372F, -1.1F, -1.016F, 1, 1, 1, 0.0F, true));
        bone71.cubeList.add(new ModelBox(bone71, 0, 75, 5.872F, -1.0F, 3.384F, 2, 2, 1, 0.0F, true));
        bone71.cubeList.add(new ModelBox(bone71, 0, 75, 6.972F, -0.5F, 3.384F, 1, 1, 1, 0.0F, true));
        bone71.cubeList.add(new ModelBox(bone71, 0, 75, 5.772F, -0.5F, 3.384F, 1, 1, 1, 0.0F, true));
        bone71.cubeList.add(new ModelBox(bone71, 0, 75, 6.372F, 0.1F, 3.384F, 1, 1, 1, 0.0F, true));
        bone71.cubeList.add(new ModelBox(bone71, 0, 75, 6.372F, -1.1F, 3.384F, 1, 1, 1, 0.0F, true));
        bone71.cubeList.add(new ModelBox(bone71, 0, 75, 5.872F, -1.0F, 0.084F, 2, 2, 1, 0.0F, true));
        bone71.cubeList.add(new ModelBox(bone71, 0, 75, 6.972F, -0.5F, 0.084F, 1, 1, 1, 0.0F, true));
        bone71.cubeList.add(new ModelBox(bone71, 0, 75, 5.772F, -0.5F, 0.084F, 1, 1, 1, 0.0F, true));
        bone71.cubeList.add(new ModelBox(bone71, 0, 75, 6.372F, 0.1F, 0.084F, 1, 1, 1, 0.0F, true));
        bone71.cubeList.add(new ModelBox(bone71, 0, 75, 6.372F, -1.1F, 0.084F, 1, 1, 1, 0.0F, true));
        bone71.cubeList.add(new ModelBox(bone71, 0, 75, 5.872F, -1.0F, 4.484F, 2, 2, 1, 0.0F, true));
        bone71.cubeList.add(new ModelBox(bone71, 0, 75, 6.972F, -0.5F, 4.484F, 1, 1, 1, 0.0F, true));
        bone71.cubeList.add(new ModelBox(bone71, 0, 75, 5.772F, -0.5F, 4.484F, 1, 1, 1, 0.0F, true));
        bone71.cubeList.add(new ModelBox(bone71, 0, 75, 6.372F, 0.1F, 4.484F, 1, 1, 1, 0.0F, true));
        bone71.cubeList.add(new ModelBox(bone71, 0, 75, 6.372F, -1.1F, 4.484F, 1, 1, 1, 0.0F, true));
        bone71.cubeList.add(new ModelBox(bone71, 0, 75, 5.872F, -1.0F, 1.184F, 2, 2, 1, 0.0F, true));
        bone71.cubeList.add(new ModelBox(bone71, 0, 75, 6.972F, -0.5F, 1.184F, 1, 1, 1, 0.0F, true));
        bone71.cubeList.add(new ModelBox(bone71, 0, 75, 5.772F, -0.5F, 1.184F, 1, 1, 1, 0.0F, true));
        bone71.cubeList.add(new ModelBox(bone71, 0, 75, 6.372F, 0.1F, 1.184F, 1, 1, 1, 0.0F, true));
        bone71.cubeList.add(new ModelBox(bone71, 0, 75, 6.372F, -1.1F, 1.184F, 1, 1, 1, 0.0F, true));
        bone71.cubeList.add(new ModelBox(bone71, 0, 75, 5.872F, -1.0F, -2.116F, 2, 2, 1, 0.0F, true));
        bone71.cubeList.add(new ModelBox(bone71, 0, 75, 6.972F, -0.5F, -2.116F, 1, 1, 1, 0.0F, true));
        bone71.cubeList.add(new ModelBox(bone71, 0, 75, 5.772F, -0.5F, -2.116F, 1, 1, 1, 0.0F, true));
        bone71.cubeList.add(new ModelBox(bone71, 0, 75, 6.372F, 0.1F, -2.116F, 1, 1, 1, 0.0F, true));
        bone71.cubeList.add(new ModelBox(bone71, 0, 75, 6.372F, -1.1F, -2.116F, 1, 1, 1, 0.0F, true));
        bone71.cubeList.add(new ModelBox(bone71, 0, 75, 6.772F, -0.1F, -1.74F, 1, 1, 8, 0.0F, true));
        bone71.cubeList.add(new ModelBox(bone71, 0, 75, 5.972F, -0.1F, -1.74F, 1, 1, 8, 0.0F, true));
        bone71.cubeList.add(new ModelBox(bone71, 0, 75, 6.772F, -0.9F, -1.74F, 1, 1, 8, 0.0F, true));
        bone71.cubeList.add(new ModelBox(bone71, 0, 75, 5.972F, -0.9F, -1.74F, 1, 1, 8, 0.0F, true));

        bone72 = new ModelRenderer(this);
        bone72.setRotationPoint(-3.3F, -17.4792F, -9.5F);
        setRotationAngle(bone72, 0.0F, 0.0F, -2.4435F);
        m249.addChild(bone72);
        bone72.cubeList.add(new ModelBox(bone72, 0, 75, -1.0F, -0.5F, -1.5F, 2, 1, 3, 0.0F, true));

        bone73 = new ModelRenderer(this);
        bone73.setRotationPoint(-3.3F, -17.4792F, -9.5F);
        setRotationAngle(bone73, 0.0F, 0.0F, -2.618F);
        m249.addChild(bone73);
        bone73.cubeList.add(new ModelBox(bone73, 0, 75, -1.0F, -0.5F, -1.5F, 2, 1, 3, 0.0F, true));

        bone74 = new ModelRenderer(this);
        bone74.setRotationPoint(-3.3F, -17.4792F, -9.5F);
        setRotationAngle(bone74, 0.0F, 0.0F, -2.7925F);
        m249.addChild(bone74);
        bone74.cubeList.add(new ModelBox(bone74, 0, 75, -1.0F, -0.5F, -1.5F, 2, 1, 3, 0.0F, true));

        bone75 = new ModelRenderer(this);
        bone75.setRotationPoint(-3.3F, -17.4792F, -9.5F);
        setRotationAngle(bone75, 0.0F, 0.0F, -2.9671F);
        m249.addChild(bone75);
        bone75.cubeList.add(new ModelBox(bone75, 0, 75, -1.0F, -0.5F, -1.5F, 2, 1, 3, 0.0F, true));

        bone76 = new ModelRenderer(this);
        bone76.setRotationPoint(-3.3F, -17.4792F, -9.5F);
        setRotationAngle(bone76, 0.0F, 0.0F, -2.9671F);
        m249.addChild(bone76);
        bone76.cubeList.add(new ModelBox(bone76, 0, 75, -1.0F, -0.5F, -1.5F, 2, 1, 3, 0.0F, true));

        bone78 = new ModelRenderer(this);
        bone78.setRotationPoint(-5.92F, -18.74F, -28.0F);
        setRotationAngle(bone78, 0.0F, 0.0F, 0.7854F);
        m249.addChild(bone78);
        bone78.cubeList.add(new ModelBox(bone78, 69, 9, 0.7071F, 0.4142F, -3.0F, 1, 1, 18, 0.0F, true));
        bone78.cubeList.add(new ModelBox(bone78, 69, 9, 5.5154F, 6.2125F, -3.0F, 2, 1, 13, 0.0F, true));
        bone78.cubeList.add(new ModelBox(bone78, 69, 9, 5.4617F, 6.6F, 1.7321F, 2, 1, 1, 0.0F, true));
        bone78.cubeList.add(new ModelBox(bone78, 69, 9, 5.5154F, 6.2125F, 10.0F, 2, 1, 13, 0.0F, true));
        bone78.cubeList.add(new ModelBox(bone78, 69, 9, 3.5355F, 3.2426F, -3.0F, 3, 1, 18, 0.0F, true));
        bone78.cubeList.add(new ModelBox(bone78, 69, 9, 0.7071F, -0.5858F, 13.0F, 1, 1, 2, 0.0F, true));
        bone78.cubeList.add(new ModelBox(bone78, 69, 9, 0.7071F, -0.5858F, 9.0F, 1, 1, 2, 0.0F, true));
        bone78.cubeList.add(new ModelBox(bone78, 69, 9, 0.7071F, -0.5858F, 5.0F, 1, 1, 2, 0.0F, true));
        bone78.cubeList.add(new ModelBox(bone78, 69, 9, 0.7071F, -0.5858F, 1.0F, 1, 1, 2, 0.0F, true));
        bone78.cubeList.add(new ModelBox(bone78, 69, 9, 0.7071F, -0.5858F, -3.0F, 1, 1, 2, 0.0F, true));

        bone79 = new ModelRenderer(this);
        bone79.setRotationPoint(-0.68F, -18.74F, -28.0F);
        setRotationAngle(bone79, 0.0F, 0.0F, -0.7854F);
        m249.addChild(bone79);
        bone79.cubeList.add(new ModelBox(bone79, 69, 9, -1.7071F, 0.4142F, -3.0F, 1, 1, 18, 0.0F, false));
        bone79.cubeList.add(new ModelBox(bone79, 69, 9, -7.5154F, 6.2125F, -3.0F, 2, 1, 13, 0.0F, false));
        bone79.cubeList.add(new ModelBox(bone79, 69, 9, -7.4617F, 6.6F, 1.7321F, 2, 1, 1, 0.0F, false));
        bone79.cubeList.add(new ModelBox(bone79, 69, 9, -7.5154F, 6.2125F, 10.0F, 2, 1, 13, 0.0F, false));
        bone79.cubeList.add(new ModelBox(bone79, 69, 9, -6.5355F, 3.2426F, -3.0F, 3, 1, 18, 0.0F, false));
        bone79.cubeList.add(new ModelBox(bone79, 69, 9, -1.7071F, -0.5858F, 13.0F, 1, 1, 2, 0.0F, false));
        bone79.cubeList.add(new ModelBox(bone79, 69, 9, -1.7071F, -0.5858F, 9.0F, 1, 1, 2, 0.0F, false));
        bone79.cubeList.add(new ModelBox(bone79, 69, 9, -1.7071F, -0.5858F, 5.0F, 1, 1, 2, 0.0F, false));
        bone79.cubeList.add(new ModelBox(bone79, 69, 9, -1.7071F, -0.5858F, 1.0F, 1, 1, 2, 0.0F, false));
        bone79.cubeList.add(new ModelBox(bone79, 69, 9, -1.7071F, -0.5858F, -3.0F, 1, 1, 2, 0.0F, false));

        bone84 = new ModelRenderer(this);
        bone84.setRotationPoint(0.332F, -12.736F, -30.0F);
        setRotationAngle(bone84, -0.5236F, 0.0F, 0.0F);
        m249.addChild(bone84);
        bone84.cubeList.add(new ModelBox(bone84, 69, 9, -0.5F, -1.433F, 1.4821F, 1, 1, 2, 0.0F, false));
        bone84.cubeList.add(new ModelBox(bone84, 34, 74, -8.132F, 0.1111F, 1.6715F, 9, 1, 1, 0.0F, false));
        bone84.cubeList.add(new ModelBox(bone84, 34, 74, -8.132F, 1.4772F, 0.3055F, 9, 1, 2, 0.0F, false));
        bone84.cubeList.add(new ModelBox(bone84, 34, 74, -8.132F, 1.4772F, 2.0375F, 9, 1, 2, 0.0F, false));
        bone84.cubeList.add(new ModelBox(bone84, 34, 74, -8.132F, 2.8432F, 1.6715F, 9, 1, 1, 0.0F, false));
        bone84.cubeList.add(new ModelBox(bone84, 69, 9, -7.764F, -1.433F, 1.4821F, 1, 1, 2, 0.0F, true));

        bone85 = new ModelRenderer(this);
        bone85.setRotationPoint(0.332F, -12.736F, -30.0F);
        setRotationAngle(bone85, -1.0472F, 0.0F, 0.0F);
        m249.addChild(bone85);
        bone85.cubeList.add(new ModelBox(bone85, 69, 9, -0.5F, -2.9821F, 2.299F, 1, 1, 2, 0.0F, false));
        bone85.cubeList.add(new ModelBox(bone85, 34, 74, -8.132F, -1.2395F, 2.3692F, 9, 2, 1, 0.0F, false));
        bone85.cubeList.add(new ModelBox(bone85, 34, 74, -8.132F, 0.1265F, 1.0031F, 9, 1, 1, 0.0F, false));
        bone85.cubeList.add(new ModelBox(bone85, 34, 74, -8.132F, 0.4925F, 2.3692F, 9, 2, 1, 0.0F, false));
        bone85.cubeList.add(new ModelBox(bone85, 34, 74, -8.132F, 0.1265F, 3.7352F, 9, 1, 1, 0.0F, false));
        bone85.cubeList.add(new ModelBox(bone85, 69, 9, -7.764F, -2.9821F, 2.299F, 1, 1, 2, 0.0F, true));

        bone82 = new ModelRenderer(this);
        bone82.setRotationPoint(0.02F, -13.584F, -10.044F);
        setRotationAngle(bone82, -0.3491F, 0.0F, 0.0F);
        m249.addChild(bone82);
        bone82.cubeList.add(new ModelBox(bone82, 69, 9, -6.32F, -4.5F, 2.5F, 6, 7, 4, 0.0F, false));

        bone83 = new ModelRenderer(this);
        bone83.setRotationPoint(-3.3F, -24.1542F, -29.0F);
        setRotationAngle(bone83, 0.0F, 0.0F, 0.7854F);
        m249.addChild(bone83);
        bone83.cubeList.add(new ModelBox(bone83, 18, 78, -0.2322F, 2.182F, 0.0F, 2, 1, 2, 0.0F, false));
        bone83.cubeList.add(new ModelBox(bone83, 18, 78, -1.6464F, -0.2322F, 0.0F, 1, 2, 2, 0.0F, false));
        bone83.cubeList.add(new ModelBox(bone83, 18, 78, 2.182F, -0.2322F, 0.0F, 1, 2, 2, 0.0F, false));
        bone83.cubeList.add(new ModelBox(bone83, 18, 78, -0.2322F, -1.6464F, 0.0F, 2, 1, 2, 0.0F, false));

        bone80 = new ModelRenderer(this);
        bone80.setRotationPoint(-3.3F, -22.1542F, -28.0F);
        setRotationAngle(bone80, 0.0F, 0.0F, -0.5236F);
        m249.addChild(bone80);
        bone80.cubeList.add(new ModelBox(bone80, 6, 72, -0.951F, 1.9151F, -1.0F, 1, 1, 2, 0.0F, false));

        bone81 = new ModelRenderer(this);
        bone81.setRotationPoint(-3.3F, -22.1542F, -28.0F);
        setRotationAngle(bone81, 0.0F, 0.0F, 0.5236F);
        m249.addChild(bone81);
        bone81.cubeList.add(new ModelBox(bone81, 6, 72, -0.049F, 1.9151F, -1.0F, 1, 1, 2, 0.0F, true));

        bone57 = new ModelRenderer(this);
        bone57.setRotationPoint(-5.6F, -16.5F, -9.5F);
        setRotationAngle(bone57, 0.0F, 0.0F, 0.6109F);
        m249.addChild(bone57);
        bone57.cubeList.add(new ModelBox(bone57, 69, 9, -0.6964F, -2.1228F, 1.5F, 1, 2, 1, 0.0F, true));
        bone57.cubeList.add(new ModelBox(bone57, 69, 9, -0.6964F, -2.1228F, -3.5F, 1, 2, 2, 0.0F, true));
        bone57.cubeList.add(new ModelBox(bone57, 69, 9, -0.6964F, -1.1228F, -1.5F, 1, 1, 3, 0.0F, true));

        bone58 = new ModelRenderer(this);
        bone58.setRotationPoint(-1.0F, -16.5F, -9.5F);
        setRotationAngle(bone58, 0.0F, 0.0F, -0.6109F);
        m249.addChild(bone58);
        bone58.cubeList.add(new ModelBox(bone58, 69, 9, -0.3036F, -2.1228F, 1.5F, 1, 2, 1, 0.0F, false));
        bone58.cubeList.add(new ModelBox(bone58, 69, 9, -0.3036F, -2.1228F, -3.5F, 1, 2, 2, 0.0F, false));
        bone58.cubeList.add(new ModelBox(bone58, 69, 9, -0.3036F, -1.1228F, -1.5F, 1, 1, 3, 0.0F, false));

        bone86 = new ModelRenderer(this);
        bone86.setRotationPoint(-0.71F, -13.2F, 13.4F);
        setRotationAngle(bone86, 0.0F, -0.1745F, 0.0F);
        m249.addChild(bone86);
        bone86.cubeList.add(new ModelBox(bone86, 69, 9, 0.3606F, -2.0F, 4.8372F, 1, 4, 4, 0.0F, false));

        bone37 = new ModelRenderer(this);
        bone37.setRotationPoint(-4.3F, -21.8756F, 21.5F);
        setRotationAngle(bone37, 0.0F, 0.0F, -0.5236F);
        m249.addChild(bone37);
        bone37.cubeList.add(new ModelBox(bone37, 8, 77, -0.183F, -0.683F, -0.5F, 1, 1, 1, 0.0F, false));

        bone38 = new ModelRenderer(this);
        bone38.setRotationPoint(-2.3F, -21.8756F, 21.5F);
        setRotationAngle(bone38, 0.0F, 0.0F, 0.5236F);
        m249.addChild(bone38);
        bone38.cubeList.add(new ModelBox(bone38, 8, 77, -0.817F, -0.683F, -0.5F, 1, 1, 1, 0.0F, true));
        this.initAnimations();
    }
}

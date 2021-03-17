package dev.toma.pubgmc.client.models.weapons;

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

public class ModelQBZ extends ModelGun {

    private final ModelRenderer magazine;
    private final ModelRenderer bullet;
    private final ModelRenderer magrot1;
    private final ModelRenderer magrot2;
    private final ModelRenderer magrot3;
    private final ModelRenderer magrot4;
    private final ModelRenderer magrot5;
    private final ModelRenderer magrot6;
    private final ModelRenderer magrot7;
    private final ModelRenderer magrot8;
    private final ModelRenderer gun;
    private final ModelRenderer stock;
    private final ModelRenderer cube_r1;
    private final ModelRenderer cube_r2;
    private final ModelRenderer cube_r3;
    private final ModelRenderer cube_r4;
    private final ModelRenderer cube_r5;
    private final ModelRenderer cube_r6;
    private final ModelRenderer cube_r7;
    private final ModelRenderer cube_r8;
    private final ModelRenderer cube_r9;
    private final ModelRenderer cube_r10;
    private final ModelRenderer cube_r11;
    private final ModelRenderer cube_r12;
    private final ModelRenderer cube_r13;
    private final ModelRenderer cube_r14;
    private final ModelRenderer cube_r15;
    private final ModelRenderer cube_r16;
    private final ModelRenderer receiver;
    private final ModelRenderer cube_r17;
    private final ModelRenderer cube_r18;
    private final ModelRenderer cube_r19;
    private final ModelRenderer cube_r20;
    private final ModelRenderer cube_r21;
    private final ModelRenderer cube_r22;
    private final ModelRenderer cube_r23;
    private final ModelRenderer cube_r24;
    private final ModelRenderer cube_r25;
    private final ModelRenderer cube_r26;
    private final ModelRenderer cube_r27;
    private final ModelRenderer cube_r28;
    private final ModelRenderer cube_r29;
    private final ModelRenderer cube_r30;
    private final ModelRenderer cube_r31;
    private final ModelRenderer cube_r32;
    private final ModelRenderer cube_r33;
    private final ModelRenderer cube_r34;
    private final ModelRenderer grip;
    private final ModelRenderer cube_r35;
    private final ModelRenderer cube_r36;
    private final ModelRenderer cube_r37;
    private final ModelRenderer cube_r38;
    private final ModelRenderer grip2;
    private final ModelRenderer cube_r39;
    private final ModelRenderer cube_r40;
    private final ModelRenderer cube_r41;
    private final ModelRenderer cube_r42;
    private final ModelRenderer handguard;
    private final ModelRenderer handguard2;
    private final ModelRenderer rail2;
    private final ModelRenderer bone3;
    private final ModelRenderer bone4;
    private final ModelRenderer rail3;
    private final ModelRenderer bone5;
    private final ModelRenderer bone6;
    private final ModelRenderer bolt;
    private final ModelRenderer cube_r43;
    private final ModelRenderer cube_r44;
    private final ModelRenderer cube_r45;
    private final ModelRenderer rail;
    private final ModelRenderer bone;
    private final ModelRenderer bone2;

    @Override
    public void initAnimations() {
        initAimAnimation(-0.56f, 0.25f, 0.35f);
        initAimingAnimationStates(0.25f, 0.07f, 0.078f);
        this.reloadAnimation = new ReloadAnimation(magazine, ReloadAnimation.ReloadStyle.MAGAZINE);
    }

    @Override
    public void render(ItemStack stack, ItemCameraTransforms.TransformType transformType) {
        EntityPlayerSP player = Minecraft.getMinecraft().player;
        if (player != null && player.hasCapability(PlayerDataProvider.PLAYER_DATA, null)) {
            GlStateManager.pushMatrix();
            {
                renderQBZ(stack);
            }
            GlStateManager.popMatrix();
        }
    }

    private void renderQBZ(ItemStack stack) {
        GlStateManager.pushMatrix();
        ModelTransformationHelper.defaultARTransform();
        GlStateManager.translate(-0.025000004, 11.875002, -11.0);
        gun.render(1f);
        magazine.render(1f);
        bolt.render(1.0F);
        if(hasScopeAtachment(stack))
            rail.render(1f);
        GlStateManager.popMatrix();
        /*this.renderARSilencer(0.15, -11, 21, 1.2F, stack);
        this.renderRedDot(-0.05, 8, -8, 0.8F, stack);
        this.renderHolo(-0.05, 7, -6, 0.8F, stack);
        this.renderScope2X(0, 0.75, -4, 1.0F, stack);
        this.renderScope4X(0, 2, -8, 1.0F, stack);
        this.renderVerticalGrip(0, 0, 0, 0.8F, stack);
        this.renderAngledGrip(0, 4, 11, 0.8F, stack);*/
    }

    public ModelQBZ() {
        textureWidth = 512;
        textureHeight = 512;

        magazine = new ModelRenderer(this);
        magazine.setRotationPoint(0.0F, 21.0F, 0.0F);
        magazine.cubeList.add(new ModelBox(magazine, 79, 90, -1.5F, -1.0F, -8.0F, 3, 3, 9, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 79, 90, -2.0F, -3.0F, -8.0F, 1, 2, 9, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 79, 90, 1.0F, -3.0F, -8.0F, 1, 2, 9, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 79, 90, -1.0F, -2.0F, 0.0F, 2, 1, 1, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 79, 90, -1.0F, -2.0F, -8.0F, 2, 1, 1, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 79, 90, -2.0F, -1.0F, -1.0F, 4, 3, 1, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 79, 90, -2.0F, -1.0F, -7.0F, 4, 3, 1, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 79, 90, -2.0F, -1.0F, -4.0F, 4, 3, 1, 0.0F, false));

        bullet = new ModelRenderer(this);
        bullet.setRotationPoint(2.0F, 0.0F, 0.0F);
        magazine.addChild(bullet);
        bullet.cubeList.add(new ModelBox(bullet, 5, 496, -2.8F, -3.8F, -6.0F, 1, 1, 5, 0.0F, true));
        bullet.cubeList.add(new ModelBox(bullet, 5, 496, -2.2F, -3.8F, -6.0F, 1, 1, 5, 0.0F, false));
        bullet.cubeList.add(new ModelBox(bullet, 5, 496, -2.2F, -3.2F, -6.0F, 1, 1, 5, 0.0F, false));
        bullet.cubeList.add(new ModelBox(bullet, 5, 496, -2.8F, -3.2F, -6.0F, 1, 1, 5, 0.0F, true));
        bullet.cubeList.add(new ModelBox(bullet, 5, 496, -3.0F, -4.0F, -4.2F, 2, 2, 3, 0.0F, false));
        bullet.cubeList.add(new ModelBox(bullet, 5, 496, -2.5F, -3.5F, -6.9F, 1, 1, 1, 0.0F, false));
        bullet.cubeList.add(new ModelBox(bullet, 5, 496, -3.0F, -4.0F, -1.0F, 2, 2, 1, 0.0F, false));

        magrot1 = new ModelRenderer(this);
        magrot1.setRotationPoint(1.0F, 4.0F, 0.0F);
        magazine.addChild(magrot1);
        setRotationAngle(magrot1, -0.0873F, 0.0F, 0.0F);
        magrot1.cubeList.add(new ModelBox(magrot1, 79, 90, -3.0F, -2.0795F, -4.1781F, 4, 3, 1, 0.0F, false));
        magrot1.cubeList.add(new ModelBox(magrot1, 79, 90, -2.5F, -2.0795F, -8.1781F, 3, 3, 9, 0.0F, false));
        magrot1.cubeList.add(new ModelBox(magrot1, 79, 90, -3.0F, -2.0795F, -7.1781F, 4, 3, 1, 0.0F, false));
        magrot1.cubeList.add(new ModelBox(magrot1, 79, 90, -3.0F, -2.0795F, -1.1781F, 4, 3, 1, 0.0F, false));

        magrot2 = new ModelRenderer(this);
        magrot2.setRotationPoint(1.0F, 0.0F, 0.0F);
        magazine.addChild(magrot2);
        setRotationAngle(magrot2, -0.1745F, 0.0F, 0.0F);
        magrot2.cubeList.add(new ModelBox(magrot2, 79, 90, -3.0F, 4.7846F, -3.4064F, 4, 3, 1, 0.0F, false));
        magrot2.cubeList.add(new ModelBox(magrot2, 79, 90, -2.5F, 4.7846F, -7.4064F, 3, 3, 9, 0.0F, false));
        magrot2.cubeList.add(new ModelBox(magrot2, 79, 90, -3.0F, 4.7846F, -6.4064F, 4, 3, 1, 0.0F, false));
        magrot2.cubeList.add(new ModelBox(magrot2, 79, 90, -3.0F, 4.7846F, -0.4064F, 4, 3, 1, 0.0F, false));

        magrot3 = new ModelRenderer(this);
        magrot3.setRotationPoint(1.0F, 0.0F, 0.0F);
        magazine.addChild(magrot3);
        setRotationAngle(magrot3, -0.2618F, 0.0F, 0.0F);
        magrot3.cubeList.add(new ModelBox(magrot3, 79, 90, -3.0F, 7.616F, -2.734F, 4, 3, 1, 0.0F, false));
        magrot3.cubeList.add(new ModelBox(magrot3, 79, 90, -2.5F, 7.616F, -6.734F, 3, 3, 9, 0.0F, false));
        magrot3.cubeList.add(new ModelBox(magrot3, 79, 90, -3.0F, 7.616F, -5.734F, 4, 3, 1, 0.0F, false));
        magrot3.cubeList.add(new ModelBox(magrot3, 79, 90, -3.0F, 7.616F, 0.266F, 4, 3, 1, 0.0F, false));

        magrot4 = new ModelRenderer(this);
        magrot4.setRotationPoint(1.0F, 0.0F, 0.0F);
        magazine.addChild(magrot4);
        setRotationAngle(magrot4, -0.3491F, 0.0F, 0.0F);
        magrot4.cubeList.add(new ModelBox(magrot4, 79, 90, -3.0F, 10.3781F, -1.8174F, 4, 3, 1, 0.0F, false));
        magrot4.cubeList.add(new ModelBox(magrot4, 79, 90, -2.5F, 10.3781F, -5.8174F, 3, 3, 9, 0.0F, false));
        magrot4.cubeList.add(new ModelBox(magrot4, 79, 90, -3.0F, 10.3781F, -4.8174F, 4, 3, 1, 0.0F, false));
        magrot4.cubeList.add(new ModelBox(magrot4, 79, 90, -3.0F, 10.3781F, 1.1826F, 4, 3, 1, 0.0F, false));

        magrot5 = new ModelRenderer(this);
        magrot5.setRotationPoint(1.0F, 0.0F, 0.0F);
        magazine.addChild(magrot5);
        setRotationAngle(magrot5, -0.4363F, 0.0F, 0.0F);
        magrot5.cubeList.add(new ModelBox(magrot5, 79, 90, -3.0F, 13.0499F, -0.6635F, 4, 3, 1, 0.0F, false));
        magrot5.cubeList.add(new ModelBox(magrot5, 79, 90, -2.5F, 13.0499F, -4.6635F, 3, 3, 9, 0.0F, false));
        magrot5.cubeList.add(new ModelBox(magrot5, 79, 90, -3.0F, 13.0499F, -3.6635F, 4, 3, 1, 0.0F, false));
        magrot5.cubeList.add(new ModelBox(magrot5, 79, 90, -3.0F, 13.0499F, 2.3365F, 4, 3, 1, 0.0F, false));

        magrot6 = new ModelRenderer(this);
        magrot6.setRotationPoint(1.0F, 0.0F, 0.0F);
        magazine.addChild(magrot6);
        setRotationAngle(magrot6, -0.5236F, 0.0F, 0.0F);
        magrot6.cubeList.add(new ModelBox(magrot6, 79, 90, -3.0F, 15.6108F, 0.7188F, 4, 3, 1, 0.0F, false));
        magrot6.cubeList.add(new ModelBox(magrot6, 79, 90, -2.5F, 15.6108F, -3.2812F, 3, 3, 9, 0.0F, false));
        magrot6.cubeList.add(new ModelBox(magrot6, 79, 90, -3.0F, 15.6108F, -2.2812F, 4, 3, 1, 0.0F, false));
        magrot6.cubeList.add(new ModelBox(magrot6, 79, 90, -3.0F, 15.6108F, 3.7188F, 4, 3, 1, 0.0F, false));

        magrot7 = new ModelRenderer(this);
        magrot7.setRotationPoint(1.0F, 6.0F, 0.0F);
        magazine.addChild(magrot7);
        setRotationAngle(magrot7, -0.6109F, 0.0F, 0.0F);
        magrot7.cubeList.add(new ModelBox(magrot7, 79, 90, -3.0F, 13.1267F, -1.1224F, 4, 3, 1, 0.0F, false));
        magrot7.cubeList.add(new ModelBox(magrot7, 79, 90, -2.5F, 13.1267F, -5.1224F, 3, 3, 9, 0.0F, false));
        magrot7.cubeList.add(new ModelBox(magrot7, 79, 90, -3.0F, 13.1267F, -4.1224F, 4, 3, 1, 0.0F, false));
        magrot7.cubeList.add(new ModelBox(magrot7, 79, 90, -3.0F, 13.1267F, 1.8776F, 4, 3, 1, 0.0F, false));

        magrot8 = new ModelRenderer(this);
        magrot8.setRotationPoint(1.0F, 6.0F, 0.0F);
        magazine.addChild(magrot8);
        setRotationAngle(magrot8, -0.6981F, 0.0F, 0.0F);
        magrot8.cubeList.add(new ModelBox(magrot8, 79, 90, -3.0F, 15.7274F, 0.2684F, 4, 3, 1, 0.0F, false));
        magrot8.cubeList.add(new ModelBox(magrot8, 79, 90, -2.5F, 15.7274F, -3.7316F, 3, 3, 9, 0.0F, false));
        magrot8.cubeList.add(new ModelBox(magrot8, 79, 90, -3.0F, 15.7274F, -2.7316F, 4, 3, 1, 0.0F, false));
        magrot8.cubeList.add(new ModelBox(magrot8, 79, 90, -3.0F, 15.7274F, 3.2684F, 4, 3, 1, 0.0F, false));

        gun = new ModelRenderer(this);
        gun.setRotationPoint(0.0F, 24.0F, 0.0F);


        stock = new ModelRenderer(this);
        stock.setRotationPoint(0.5F, -1.8891F, 0.318F);
        gun.addChild(stock);
        stock.cubeList.add(new ModelBox(stock, 6, 79, -3.5F, 2.8891F, -9.086F, 1, 1, 12, 0.0F, false));
        stock.cubeList.add(new ModelBox(stock, 6, 79, 1.5F, 2.8891F, -9.086F, 1, 1, 12, 0.0F, true));
        stock.cubeList.add(new ModelBox(stock, 6, 79, -3.5F, 1.7678F, 3.6211F, 6, 1, 14, 0.0F, false));
        stock.cubeList.add(new ModelBox(stock, 6, 79, -3.5F, -7.2322F, 17.6211F, 6, 11, 2, 0.0F, false));
        stock.cubeList.add(new ModelBox(stock, 6, 79, -2.8F, -8.1109F, 1.6211F, 1, 3, 16, 0.0F, false));
        stock.cubeList.add(new ModelBox(stock, 6, 79, -2.8F, -8.1109F, -8.3789F, 1, 1, 10, 0.0F, false));
        stock.cubeList.add(new ModelBox(stock, 6, 79, 0.8F, -8.1109F, 2.6211F, 1, 3, 15, 0.0F, true));
        stock.cubeList.add(new ModelBox(stock, 6, 79, 0.8F, -8.1109F, -12.3789F, 1, 3, 15, 0.0F, true));
        stock.cubeList.add(new ModelBox(stock, 6, 79, -2.8F, -8.1109F, -12.3789F, 1, 3, 4, 0.0F, false));
        stock.cubeList.add(new ModelBox(stock, 6, 79, -2.5F, 2.7678F, 15.6211F, 4, 3, 4, 0.0F, false));
        stock.cubeList.add(new ModelBox(stock, 6, 79, -2.5F, -9.2322F, 11.6211F, 4, 3, 8, 0.0F, false));
        stock.cubeList.add(new ModelBox(stock, 6, 79, -1.5F, 2.7678F, 14.814F, 2, 2, 1, 0.0F, false));
        stock.cubeList.add(new ModelBox(stock, 6, 79, -3.0F, -0.0769F, -11.318F, 5, 3, 15, 0.0F, false));
        stock.cubeList.add(new ModelBox(stock, 6, 79, -3.0F, -0.0769F, 3.682F, 5, 2, 14, 0.0F, false));
        stock.cubeList.add(new ModelBox(stock, 6, 79, -3.5F, 2.0231F, -11.318F, 6, 1, 1, 0.0F, false));
        stock.cubeList.add(new ModelBox(stock, 6, 79, -3.5F, -2.1109F, -12.318F, 6, 5, 1, 0.0F, false));
        stock.cubeList.add(new ModelBox(stock, 0, 81, -3.5F, -1.9769F, -11.318F, 6, 2, 27, 0.0F, false));
        stock.cubeList.add(new ModelBox(stock, 0, 81, -3.7819F, -4.2135F, -8.318F, 1, 1, 10, 0.0F, false));
        stock.cubeList.add(new ModelBox(stock, 0, 81, -3.1582F, -6.7509F, 0.682F, 1, 1, 1, 0.0F, false));
        stock.cubeList.add(new ModelBox(stock, 0, 81, -3.1582F, -6.7509F, -8.318F, 1, 1, 1, 0.0F, false));
        stock.cubeList.add(new ModelBox(stock, 0, 81, -3.1582F, -7.7509F, -8.318F, 1, 1, 10, 0.0F, false));
        stock.cubeList.add(new ModelBox(stock, 0, 81, -0.9071F, -8.818F, 2.6211F, 2, 1, 15, 0.0F, false));
        stock.cubeList.add(new ModelBox(stock, 0, 81, -0.9071F, -8.818F, -12.3789F, 2, 1, 15, 0.0F, false));
        stock.cubeList.add(new ModelBox(stock, 0, 81, -2.0929F, -8.818F, 2.6211F, 2, 1, 15, 0.0F, true));
        stock.cubeList.add(new ModelBox(stock, 0, 81, -2.0929F, -8.818F, -12.3789F, 2, 1, 15, 0.0F, true));
        stock.cubeList.add(new ModelBox(stock, 6, 79, -3.5F, -4.1109F, 2.682F, 6, 2, 15, 0.0F, false));
        stock.cubeList.add(new ModelBox(stock, 6, 79, -3.5F, -5.1109F, 13.682F, 6, 1, 4, 0.0F, false));
        stock.cubeList.add(new ModelBox(stock, 78, 105, -4.0F, -4.6109F, 14.182F, 7, 2, 2, 0.0F, false));
        stock.cubeList.add(new ModelBox(stock, 6, 79, -3.5F, -4.1109F, -12.318F, 6, 2, 15, 0.0F, false));
        stock.cubeList.add(new ModelBox(stock, 0, 94, -3.0F, -2.3109F, 3.682F, 5, 1, 14, 0.0F, false));
        stock.cubeList.add(new ModelBox(stock, 0, 94, -3.0F, -2.3109F, -11.318F, 5, 1, 15, 0.0F, false));

        cube_r1 = new ModelRenderer(this);
        cube_r1.setRotationPoint(0.0F, 0.0F, -0.2679F);
        stock.addChild(cube_r1);
        setRotationAngle(cube_r1, -0.7854F, 0.0F, 0.0F);
        cube_r1.cubeList.add(new ModelBox(cube_r1, 6, 79, -3.5F, -1.5F, 4.0F, 6, 2, 1, 0.0F, false));

        cube_r2 = new ModelRenderer(this);
        cube_r2.setRotationPoint(0.0F, 0.9561F, -13.1661F);
        stock.addChild(cube_r2);
        setRotationAngle(cube_r2, -0.5236F, 0.0F, 0.0F);
        cube_r2.cubeList.add(new ModelBox(cube_r2, 6, 79, -3.5F, -0.5F, 3.0F, 6, 1, 1, 0.0F, false));
        cube_r2.cubeList.add(new ModelBox(cube_r2, 6, 79, 1.5F, -0.5F, 4.0F, 1, 1, 1, 0.0F, false));
        cube_r2.cubeList.add(new ModelBox(cube_r2, 6, 79, -3.5F, -0.5F, 4.0F, 1, 1, 1, 0.0F, false));

        cube_r3 = new ModelRenderer(this);
        cube_r3.setRotationPoint(-1.7088F, -0.9769F, 29.3672F);
        stock.addChild(cube_r3);
        setRotationAngle(cube_r3, 0.0F, -0.0873F, 0.0F);
        cube_r3.cubeList.add(new ModelBox(cube_r3, 6, 79, 2.0F, -1.0F, -14.0F, 1, 2, 2, 0.0F, true));

        cube_r4 = new ModelRenderer(this);
        cube_r4.setRotationPoint(0.7088F, -0.9769F, 29.3672F);
        stock.addChild(cube_r4);
        setRotationAngle(cube_r4, 0.0F, 0.0873F, 0.0F);
        cube_r4.cubeList.add(new ModelBox(cube_r4, 6, 79, -3.0F, -1.0F, -14.0F, 1, 2, 2, 0.0F, false));

        cube_r5 = new ModelRenderer(this);
        cube_r5.setRotationPoint(-0.6611F, -4.0245F, 3.182F);
        stock.addChild(cube_r5);
        setRotationAngle(cube_r5, 0.0F, 0.0F, -0.3491F);
        cube_r5.cubeList.add(new ModelBox(cube_r5, 6, 79, 2.0F, -1.0F, -15.5F, 1, 2, 15, 0.0F, true));
        cube_r5.cubeList.add(new ModelBox(cube_r5, 6, 79, 2.0F, -1.0F, -0.5F, 1, 2, 15, 0.0F, true));

        cube_r6 = new ModelRenderer(this);
        cube_r6.setRotationPoint(-0.3389F, -4.0245F, 3.182F);
        stock.addChild(cube_r6);
        setRotationAngle(cube_r6, 0.0F, 0.0F, 0.3491F);
        cube_r6.cubeList.add(new ModelBox(cube_r6, 6, 79, -3.3F, -1.0F, -11.5F, 1, 2, 1, 0.0F, false));
        cube_r6.cubeList.add(new ModelBox(cube_r6, 6, 79, -3.3F, -1.0F, -2.5F, 1, 2, 1, 0.0F, false));
        cube_r6.cubeList.add(new ModelBox(cube_r6, 6, 79, -3.0F, -1.0F, -15.5F, 1, 2, 4, 0.0F, false));
        cube_r6.cubeList.add(new ModelBox(cube_r6, 6, 79, -3.0F, -1.0F, -1.5F, 1, 2, 16, 0.0F, false));

        cube_r7 = new ModelRenderer(this);
        cube_r7.setRotationPoint(0.5981F, -1.0877F, 18.1211F);
        stock.addChild(cube_r7);
        setRotationAngle(cube_r7, 0.0F, 0.0F, 0.5236F);
        cube_r7.cubeList.add(new ModelBox(cube_r7, 6, 79, -3.0F, 1.0F, -0.5F, 1, 2, 1, 0.0F, false));

        cube_r8 = new ModelRenderer(this);
        cube_r8.setRotationPoint(-1.5981F, -8.3303F, 18.1211F);
        stock.addChild(cube_r8);
        setRotationAngle(cube_r8, 0.0F, 0.0F, -0.5236F);
        cube_r8.cubeList.add(new ModelBox(cube_r8, 6, 79, 2.0F, 1.0F, -0.5F, 1, 2, 2, 0.0F, true));

        cube_r9 = new ModelRenderer(this);
        cube_r9.setRotationPoint(-1.5981F, 4.8658F, 18.1211F);
        stock.addChild(cube_r9);
        setRotationAngle(cube_r9, 0.0F, 0.0F, 0.5236F);
        cube_r9.cubeList.add(new ModelBox(cube_r9, 6, 79, 2.0F, -3.0F, -0.5F, 1, 2, 2, 0.0F, true));

        cube_r10 = new ModelRenderer(this);
        cube_r10.setRotationPoint(0.5981F, -8.3303F, 18.1211F);
        stock.addChild(cube_r10);
        setRotationAngle(cube_r10, 0.0F, 0.0F, 0.5236F);
        cube_r10.cubeList.add(new ModelBox(cube_r10, 6, 79, -3.0F, 1.0F, -0.5F, 1, 2, 2, 0.0F, false));

        cube_r11 = new ModelRenderer(this);
        cube_r11.setRotationPoint(0.5981F, 4.8658F, 18.1211F);
        stock.addChild(cube_r11);
        setRotationAngle(cube_r11, 0.0F, 0.0F, -0.5236F);
        cube_r11.cubeList.add(new ModelBox(cube_r11, 6, 79, -3.0F, -3.0F, -0.5F, 1, 2, 2, 0.0F, false));

        cube_r12 = new ModelRenderer(this);
        cube_r12.setRotationPoint(-0.5F, 3.7678F, 14.3211F);
        stock.addChild(cube_r12);
        setRotationAngle(cube_r12, 0.0F, 0.0F, 0.7854F);
        cube_r12.cubeList.add(new ModelBox(cube_r12, 120, 108, -0.5F, -0.5F, -0.5071F, 1, 1, 1, 0.0F, false));

        cube_r13 = new ModelRenderer(this);
        cube_r13.setRotationPoint(-0.5F, -5.9332F, 8.907F);
        stock.addChild(cube_r13);
        setRotationAngle(cube_r13, 0.5236F, 0.0F, 0.0F);
        cube_r13.cubeList.add(new ModelBox(cube_r13, 6, 79, -2.0F, -1.5F, 2.0F, 4, 1, 2, 0.0F, false));

        cube_r14 = new ModelRenderer(this);
        cube_r14.setRotationPoint(-2.0929F, -8.1109F, 0.6211F);
        stock.addChild(cube_r14);
        setRotationAngle(cube_r14, 0.0F, 0.0F, 0.7854F);
        cube_r14.cubeList.add(new ModelBox(cube_r14, 6, 79, -0.5F, -0.5F, -13.0F, 1, 1, 15, 0.0F, false));
        cube_r14.cubeList.add(new ModelBox(cube_r14, 6, 79, -0.5F, -0.5F, 2.0F, 1, 1, 15, 0.0F, false));

        cube_r15 = new ModelRenderer(this);
        cube_r15.setRotationPoint(1.0929F, -8.1109F, 0.6211F);
        stock.addChild(cube_r15);
        setRotationAngle(cube_r15, 0.0F, 0.0F, -0.7854F);
        cube_r15.cubeList.add(new ModelBox(cube_r15, 6, 79, -0.5F, -0.5F, -13.0F, 1, 1, 15, 0.0F, true));
        cube_r15.cubeList.add(new ModelBox(cube_r15, 6, 79, -0.5F, -0.5F, 2.0F, 1, 1, 15, 0.0F, true));

        cube_r16 = new ModelRenderer(this);
        cube_r16.setRotationPoint(-1.0F, 1.8891F, -0.586F);
        stock.addChild(cube_r16);
        setRotationAngle(cube_r16, 0.2618F, 0.0F, 0.0F);
        cube_r16.cubeList.add(new ModelBox(cube_r16, 86, 44, -1.0F, 2.3F, 1.2F, 3, 2, 2, 0.0F, false));

        receiver = new ModelRenderer(this);
        receiver.setRotationPoint(0.0F, 0.0F, -18.0F);
        gun.addChild(receiver);
        receiver.cubeList.add(new ModelBox(receiver, 100, 27, -3.0F, -6.0F, 2.0F, 6, 7, 4, 0.0F, false));
        receiver.cubeList.add(new ModelBox(receiver, 65, 12, -3.0F, -6.0F, -23.0F, 6, 3, 25, 0.0F, false));
        receiver.cubeList.add(new ModelBox(receiver, 67, 7, -2.0F, -3.2679F, -23.0F, 4, 3, 25, 0.0F, false));
        receiver.cubeList.add(new ModelBox(receiver, 67, 7, -2.0F, -0.2679F, -23.0F, 4, 1, 4, 0.0F, false));
        receiver.cubeList.add(new ModelBox(receiver, 65, 21, -3.0F, -6.0F, -48.0F, 6, 1, 25, 0.0F, false));
        receiver.cubeList.add(new ModelBox(receiver, 65, 21, -2.0F, -7.0F, -48.0F, 4, 1, 1, 0.0F, false));
        receiver.cubeList.add(new ModelBox(receiver, 0, 79, -3.0F, -5.0F, -48.0F, 6, 1, 25, 0.0F, false));
        receiver.cubeList.add(new ModelBox(receiver, 0, 79, -3.0F, -1.2679F, -48.0F, 6, 1, 25, 0.0F, false));
        receiver.cubeList.add(new ModelBox(receiver, 6, 100, -3.5F, -4.134F, -30.0F, 7, 3, 7, 0.0F, false));
        receiver.cubeList.add(new ModelBox(receiver, 6, 100, -3.5F, -4.134F, -33.0F, 7, 3, 1, 0.0F, false));
        receiver.cubeList.add(new ModelBox(receiver, 6, 100, -3.5F, -4.134F, -39.0F, 7, 3, 1, 0.0F, false));
        receiver.cubeList.add(new ModelBox(receiver, 6, 100, -3.5F, -4.134F, -36.0F, 7, 3, 1, 0.0F, false));
        receiver.cubeList.add(new ModelBox(receiver, 6, 100, -3.5F, -4.134F, -42.0F, 7, 3, 1, 0.0F, false));
        receiver.cubeList.add(new ModelBox(receiver, 6, 100, -3.5F, -4.134F, -48.0F, 7, 3, 4, 0.0F, false));
        receiver.cubeList.add(new ModelBox(receiver, 73, 84, -2.5F, -4.134F, -44.0F, 5, 3, 14, 0.0F, false));
        receiver.cubeList.add(new ModelBox(receiver, 100, 27, -2.5F, -11.1F, 2.0F, 5, 4, 4, 0.0F, false));
        receiver.cubeList.add(new ModelBox(receiver, 71, 19, -2.5F, -11.1F, -23.0F, 1, 4, 25, 0.0F, false));
        receiver.cubeList.add(new ModelBox(receiver, 92, 24, -2.0F, -11.1F, -23.866F, 4, 2, 1, 0.0F, false));
        receiver.cubeList.add(new ModelBox(receiver, 71, 19, 1.5F, -11.1F, -23.0F, 1, 4, 25, 0.0F, false));
        receiver.cubeList.add(new ModelBox(receiver, 66, 10, -1.5F, -10.4F, -23.0F, 3, 1, 25, 0.0F, false));
        receiver.cubeList.add(new ModelBox(receiver, 66, 13, -2.5F, -10.1F, -48.0F, 5, 3, 25, 0.0F, false));
        receiver.cubeList.add(new ModelBox(receiver, 66, 13, -2.0F, -10.966F, -43.0F, 4, 1, 20, 0.0F, false));
        receiver.cubeList.add(new ModelBox(receiver, 29, 78, -1.5F, -10.1F, -49.0F, 3, 3, 1, 0.0F, false));
        receiver.cubeList.add(new ModelBox(receiver, 29, 78, -1.0F, -9.6F, -50.0F, 2, 3, 1, 0.0F, false));
        receiver.cubeList.add(new ModelBox(receiver, 14, 100, -1.5F, -6.1F, -66.0F, 3, 3, 10, 0.0F, false));
        receiver.cubeList.add(new ModelBox(receiver, 29, 78, -2.5F, -7.1F, -56.0F, 5, 5, 1, 0.0F, false));
        receiver.cubeList.add(new ModelBox(receiver, 29, 78, -0.5F, -7.1F, -55.0F, 1, 1, 5, 0.0F, false));
        receiver.cubeList.add(new ModelBox(receiver, 29, 78, -2.0F, -6.6F, -55.0F, 4, 4, 7, 0.0F, false));
        receiver.cubeList.add(new ModelBox(receiver, 66, 13, -2.0F, -13.1F, -48.0F, 4, 3, 5, 0.0F, false));
        receiver.cubeList.add(new ModelBox(receiver, 66, 13, -1.5F, -17.5795F, -48.3816F, 3, 2, 4, 0.0F, false));
        receiver.cubeList.add(new ModelBox(receiver, 66, 13, -2.4142F, -19.9937F, -48.3816F, 1, 2, 4, 0.0F, false));
        receiver.cubeList.add(new ModelBox(receiver, 66, 13, 1.4142F, -19.9937F, -48.3816F, 1, 2, 4, 0.0F, true));
        receiver.cubeList.add(new ModelBox(receiver, 66, 13, -1.0F, -21.4205F, -48.3816F, 2, 1, 4, 0.0F, false));
        receiver.cubeList.add(new ModelBox(receiver, 90, 95, -2.5F, -12.6F, -44.5F, 5, 1, 1, 0.0F, false));
        receiver.cubeList.add(new ModelBox(receiver, 90, 95, -2.5F, -12.6F, -47.5F, 5, 1, 1, 0.0F, false));
        receiver.cubeList.add(new ModelBox(receiver, 100, 27, 1.0F, -17.1F, 4.0F, 1, 6, 2, 0.0F, false));
        receiver.cubeList.add(new ModelBox(receiver, 100, 27, -1.0F, -17.1F, 5.0F, 2, 6, 2, 0.0F, false));
        receiver.cubeList.add(new ModelBox(receiver, 94, 37, -1.0F, -18.0F, 4.0F, 2, 1, 2, 0.0F, false));
        receiver.cubeList.add(new ModelBox(receiver, 71, 15, -1.5F, -17.1F, -9.0F, 3, 1, 14, 0.0F, false));
        receiver.cubeList.add(new ModelBox(receiver, 71, 15, -1.5F, -17.1F, -24.0F, 3, 1, 15, 0.0F, false));
        receiver.cubeList.add(new ModelBox(receiver, 74, 21, 1.0F, -18.1F, -11.0F, 1, 1, 17, 0.0F, false));
        receiver.cubeList.add(new ModelBox(receiver, 74, 21, 1.0F, -18.1F, -24.0F, 1, 1, 13, 0.0F, false));
        receiver.cubeList.add(new ModelBox(receiver, 74, 21, 1.0F, -19.0659F, 2.7412F, 1, 1, 3, 0.0F, false));
        receiver.cubeList.add(new ModelBox(receiver, 100, 27, -2.0F, -17.1F, 4.0F, 1, 6, 2, 0.0F, false));
        receiver.cubeList.add(new ModelBox(receiver, 74, 21, -2.0F, -18.1F, -11.0F, 1, 1, 17, 0.0F, false));
        receiver.cubeList.add(new ModelBox(receiver, 74, 21, -2.0F, -18.1F, -24.0F, 1, 1, 13, 0.0F, false));
        receiver.cubeList.add(new ModelBox(receiver, 74, 21, -2.0F, -19.0659F, 2.7412F, 1, 1, 3, 0.0F, false));

        cube_r17 = new ModelRenderer(this);
        cube_r17.setRotationPoint(-1.1981F, -7.9042F, -36.5F);
        receiver.addChild(cube_r17);
        setRotationAngle(cube_r17, 0.0F, 0.0F, -0.4363F);
        cube_r17.cubeList.add(new ModelBox(cube_r17, 69, 18, 2.0F, 1.5F, -11.5F, 1, 2, 12, 0.0F, true));
        cube_r17.cubeList.add(new ModelBox(cube_r17, 69, 18, 2.0F, 1.5F, 0.5F, 1, 2, 18, 0.0F, true));
        cube_r17.cubeList.add(new ModelBox(cube_r17, 69, 11, 2.0F, 1.5F, 18.5F, 1, 2, 20, 0.0F, true));
        cube_r17.cubeList.add(new ModelBox(cube_r17, 100, 27, 2.0F, 1.5F, 38.5F, 1, 2, 4, 0.0F, true));

        cube_r18 = new ModelRenderer(this);
        cube_r18.setRotationPoint(1.1981F, -7.9042F, -36.5F);
        receiver.addChild(cube_r18);
        setRotationAngle(cube_r18, 0.0F, 0.0F, 0.4363F);
        cube_r18.cubeList.add(new ModelBox(cube_r18, 69, 18, -3.0F, 1.5F, -11.5F, 1, 2, 12, 0.0F, false));
        cube_r18.cubeList.add(new ModelBox(cube_r18, 69, 18, -3.0F, 1.5F, 0.5F, 1, 2, 18, 0.0F, false));
        cube_r18.cubeList.add(new ModelBox(cube_r18, 69, 11, -3.0F, 1.5F, 18.5F, 1, 2, 20, 0.0F, false));
        cube_r18.cubeList.add(new ModelBox(cube_r18, 100, 27, -3.0F, 1.5F, 38.5F, 1, 2, 4, 0.0F, false));

        cube_r19 = new ModelRenderer(this);
        cube_r19.setRotationPoint(0.0F, -18.4536F, 5.3876F);
        receiver.addChild(cube_r19);
        setRotationAngle(cube_r19, 0.2618F, 0.0F, 0.0F);
        cube_r19.cubeList.add(new ModelBox(cube_r19, 74, 21, -2.0F, 3.5F, -1.5F, 1, 5, 2, 0.0F, false));
        cube_r19.cubeList.add(new ModelBox(cube_r19, 74, 21, -2.0F, -0.5F, -0.5F, 1, 4, 1, 0.0F, false));
        cube_r19.cubeList.add(new ModelBox(cube_r19, 74, 21, 0.9F, 3.5F, -1.5F, 1, 5, 2, 0.0F, false));
        cube_r19.cubeList.add(new ModelBox(cube_r19, 74, 21, 0.9F, -0.5F, -0.5F, 1, 4, 1, 0.0F, false));

        cube_r20 = new ModelRenderer(this);
        cube_r20.setRotationPoint(0.0F, -18.3436F, -5.7428F);
        receiver.addChild(cube_r20);
        setRotationAngle(cube_r20, 0.0262F, 0.0F, 0.0F);
        cube_r20.cubeList.add(new ModelBox(cube_r20, 66, 16, -2.0F, -0.5F, -18.5F, 1, 1, 27, 0.0F, false));
        cube_r20.cubeList.add(new ModelBox(cube_r20, 66, 16, 1.0F, -0.5F, -18.5F, 1, 1, 27, 0.0F, false));

        cube_r21 = new ModelRenderer(this);
        cube_r21.setRotationPoint(0.0F, -14.445F, -19.0362F);
        receiver.addChild(cube_r21);
        setRotationAngle(cube_r21, -0.5672F, 0.0F, 0.0F);
        cube_r21.cubeList.add(new ModelBox(cube_r21, 89, 19, -2.0F, -0.5F, -6.5F, 1, 9, 1, 0.0F, false));
        cube_r21.cubeList.add(new ModelBox(cube_r21, 89, 19, 1.0F, -0.5F, -6.5F, 1, 9, 1, 0.0F, false));

        cube_r22 = new ModelRenderer(this);
        cube_r22.setRotationPoint(0.0F, -12.6486F, -17.9432F);
        receiver.addChild(cube_r22);
        setRotationAngle(cube_r22, -0.5672F, 0.0F, 0.0F);
        cube_r22.cubeList.add(new ModelBox(cube_r22, 71, 15, -1.5F, -0.5F, -7.5F, 3, 8, 1, 0.0F, false));

        cube_r23 = new ModelRenderer(this);
        cube_r23.setRotationPoint(-1.3536F, -20.3598F, -46.3816F);
        receiver.addChild(cube_r23);
        setRotationAngle(cube_r23, 0.0F, 0.0F, -0.7854F);
        cube_r23.cubeList.add(new ModelBox(cube_r23, 66, 13, -1.0F, -0.5F, -2.0F, 2, 1, 4, 0.0F, true));

        cube_r24 = new ModelRenderer(this);
        cube_r24.setRotationPoint(-1.3536F, -17.6402F, -46.3816F);
        receiver.addChild(cube_r24);
        setRotationAngle(cube_r24, 0.0F, 0.0F, 0.7854F);
        cube_r24.cubeList.add(new ModelBox(cube_r24, 66, 13, -1.0F, -0.5F, -2.0F, 2, 1, 4, 0.0F, true));

        cube_r25 = new ModelRenderer(this);
        cube_r25.setRotationPoint(1.3536F, -20.3598F, -46.3816F);
        receiver.addChild(cube_r25);
        setRotationAngle(cube_r25, 0.0F, 0.0F, 0.7854F);
        cube_r25.cubeList.add(new ModelBox(cube_r25, 66, 13, -1.0F, -0.5F, -2.0F, 2, 1, 4, 0.0F, false));

        cube_r26 = new ModelRenderer(this);
        cube_r26.setRotationPoint(1.3536F, -17.6402F, -46.3816F);
        receiver.addChild(cube_r26);
        setRotationAngle(cube_r26, 0.0F, 0.0F, -0.7854F);
        cube_r26.cubeList.add(new ModelBox(cube_r26, 66, 13, -1.0F, -0.5F, -2.0F, 2, 1, 4, 0.0F, false));

        cube_r27 = new ModelRenderer(this);
        cube_r27.setRotationPoint(0.0F, -14.6F, -46.5F);
        receiver.addChild(cube_r27);
        setRotationAngle(cube_r27, 0.3054F, 0.0F, 0.0F);
        cube_r27.cubeList.add(new ModelBox(cube_r27, 66, 13, -1.5F, -1.5F, -1.5F, 3, 4, 4, 0.0F, false));

        cube_r28 = new ModelRenderer(this);
        cube_r28.setRotationPoint(0.5179F, -9.533F, -38.0F);
        receiver.addChild(cube_r28);
        setRotationAngle(cube_r28, 0.0F, 0.0F, -0.5236F);
        cube_r28.cubeList.add(new ModelBox(cube_r28, 66, 13, 1.0F, -0.5F, -10.0F, 1, 1, 25, 0.0F, true));

        cube_r29 = new ModelRenderer(this);
        cube_r29.setRotationPoint(-0.5179F, -9.533F, -38.0F);
        receiver.addChild(cube_r29);
        setRotationAngle(cube_r29, 0.0F, 0.0F, 0.5236F);
        cube_r29.cubeList.add(new ModelBox(cube_r29, 66, 13, -2.0F, -0.5F, -10.0F, 1, 1, 25, 0.0F, false));

        cube_r30 = new ModelRenderer(this);
        cube_r30.setRotationPoint(-4.183F, -9.1F, -33.5753F);
        receiver.addChild(cube_r30);
        setRotationAngle(cube_r30, 0.0F, 0.5236F, 0.0F);
        cube_r30.cubeList.add(new ModelBox(cube_r30, 71, 19, -0.5F, -2.0F, 11.5F, 1, 1, 1, 0.0F, true));

        cube_r31 = new ModelRenderer(this);
        cube_r31.setRotationPoint(4.183F, -9.1F, -33.5753F);
        receiver.addChild(cube_r31);
        setRotationAngle(cube_r31, 0.0F, -0.5236F, 0.0F);
        cube_r31.cubeList.add(new ModelBox(cube_r31, 71, 19, -0.5F, -2.0F, 11.5F, 1, 1, 1, 0.0F, false));

        cube_r32 = new ModelRenderer(this);
        cube_r32.setRotationPoint(0.9F, -1.5837F, -10.3217F);
        receiver.addChild(cube_r32);
        setRotationAngle(cube_r32, -0.0873F, 0.0F, 0.0F);
        cube_r32.cubeList.add(new ModelBox(cube_r32, 67, 7, -0.901F, -2.5F, -13.5F, 3, 4, 26, 0.0F, true));
        cube_r32.cubeList.add(new ModelBox(cube_r32, 67, 7, -3.899F, -2.5F, -13.5F, 3, 4, 26, 0.0F, false));

        cube_r33 = new ModelRenderer(this);
        cube_r33.setRotationPoint(1.0F, 0.2855F, -20.8387F);
        receiver.addChild(cube_r33);
        setRotationAngle(cube_r33, 0.3054F, 0.0F, 0.0F);
        cube_r33.cubeList.add(new ModelBox(cube_r33, 67, 7, -2.0F, 6.5F, 2.0F, 2, 1, 7, 0.0F, false));

        cube_r34 = new ModelRenderer(this);
        cube_r34.setRotationPoint(0.0F, 0.4286F, -20.9609F);
        receiver.addChild(cube_r34);
        setRotationAngle(cube_r34, 0.3927F, 0.0F, 0.0F);
        cube_r34.cubeList.add(new ModelBox(cube_r34, 67, 7, -2.0F, -0.5F, -2.0F, 4, 9, 4, 0.0F, false));

        grip = new ModelRenderer(this);
        grip.setRotationPoint(-3.5F, 16.7321F, 1.2679F);
        receiver.addChild(grip);
        grip.cubeList.add(new ModelBox(grip, 67, 7, 2.0F, -17.0F, -12.2679F, 3, 3, 1, 0.0F, false));
        grip.cubeList.add(new ModelBox(grip, 67, 7, 2.5F, -14.0F, -12.2679F, 2, 2, 1, 0.0F, false));
        grip.cubeList.add(new ModelBox(grip, 67, 7, 2.0F, -17.0F, -5.8038F, 3, 3, 1, 0.0F, false));
        grip.cubeList.add(new ModelBox(grip, 67, 7, 1.0F, -17.0F, -10.5359F, 1, 3, 4, 0.0F, false));
        grip.cubeList.add(new ModelBox(grip, 67, 7, 5.0F, -17.0F, -10.5359F, 1, 3, 4, 0.0F, true));

        cube_r35 = new ModelRenderer(this);
        cube_r35.setRotationPoint(3.951F, -15.5F, -5.9869F);
        grip.addChild(cube_r35);
        setRotationAngle(cube_r35, 0.0F, -0.5236F, 0.0F);
        cube_r35.cubeList.add(new ModelBox(cube_r35, 67, 7, 0.5F, -1.5F, -1.5F, 1, 3, 2, 0.0F, true));

        cube_r36 = new ModelRenderer(this);
        cube_r36.setRotationPoint(3.951F, -15.5F, -11.0849F);
        grip.addChild(cube_r36);
        setRotationAngle(cube_r36, 0.0F, 0.5236F, 0.0F);
        cube_r36.cubeList.add(new ModelBox(cube_r36, 67, 7, 0.5F, -1.5F, -0.5F, 1, 3, 2, 0.0F, true));

        cube_r37 = new ModelRenderer(this);
        cube_r37.setRotationPoint(3.049F, -15.5F, -5.9869F);
        grip.addChild(cube_r37);
        setRotationAngle(cube_r37, 0.0F, 0.5236F, 0.0F);
        cube_r37.cubeList.add(new ModelBox(cube_r37, 67, 7, -1.5F, -1.5F, -1.5F, 1, 3, 2, 0.0F, false));

        cube_r38 = new ModelRenderer(this);
        cube_r38.setRotationPoint(3.049F, -15.5F, -11.0849F);
        grip.addChild(cube_r38);
        setRotationAngle(cube_r38, 0.0F, -0.5236F, 0.0F);
        cube_r38.cubeList.add(new ModelBox(cube_r38, 67, 7, -1.5F, -1.5F, -0.5F, 1, 3, 2, 0.0F, false));

        grip2 = new ModelRenderer(this);
        grip2.setRotationPoint(0.0F, 1.2321F, -7.2679F);
        receiver.addChild(grip2);
        setRotationAngle(grip2, 0.3927F, 0.0F, 0.0F);
        grip2.cubeList.add(new ModelBox(grip2, 67, 7, -1.5F, -0.0424F, -4.022F, 3, 12, 1, 0.0F, false));
        grip2.cubeList.add(new ModelBox(grip2, 67, 7, -1.5F, -0.0424F, 2.4421F, 3, 12, 1, 0.0F, false));
        grip2.cubeList.add(new ModelBox(grip2, 67, 7, -2.5F, -0.0424F, -2.2899F, 1, 12, 4, 0.0F, false));
        grip2.cubeList.add(new ModelBox(grip2, 67, 7, 1.5F, -0.0424F, -2.2899F, 1, 12, 4, 0.0F, true));
        grip2.cubeList.add(new ModelBox(grip2, 19, 87, -1.5F, 10.7576F, -3.2899F, 3, 1, 6, 0.0F, true));

        cube_r39 = new ModelRenderer(this);
        cube_r39.setRotationPoint(0.451F, 1.4576F, 2.2591F);
        grip2.addChild(cube_r39);
        setRotationAngle(cube_r39, 0.0F, -0.5236F, 0.0F);
        cube_r39.cubeList.add(new ModelBox(cube_r39, 67, 7, 0.5F, -1.5F, -1.5F, 1, 12, 2, 0.0F, true));

        cube_r40 = new ModelRenderer(this);
        cube_r40.setRotationPoint(0.451F, 1.4576F, -2.839F);
        grip2.addChild(cube_r40);
        setRotationAngle(cube_r40, 0.0F, 0.5236F, 0.0F);
        cube_r40.cubeList.add(new ModelBox(cube_r40, 67, 7, 0.5F, -1.5F, -0.5F, 1, 12, 2, 0.0F, true));

        cube_r41 = new ModelRenderer(this);
        cube_r41.setRotationPoint(-0.451F, 1.4576F, 2.2591F);
        grip2.addChild(cube_r41);
        setRotationAngle(cube_r41, 0.0F, 0.5236F, 0.0F);
        cube_r41.cubeList.add(new ModelBox(cube_r41, 67, 7, -1.5F, -1.5F, -1.5F, 1, 12, 2, 0.0F, false));

        cube_r42 = new ModelRenderer(this);
        cube_r42.setRotationPoint(-0.451F, 1.4576F, -2.839F);
        grip2.addChild(cube_r42);
        setRotationAngle(cube_r42, 0.0F, -0.5236F, 0.0F);
        cube_r42.cubeList.add(new ModelBox(cube_r42, 67, 7, -1.5F, -1.5F, -0.5F, 1, 12, 2, 0.0F, false));

        handguard = new ModelRenderer(this);
        handguard.setRotationPoint(-4.0F, -4.5F, -36.75F);
        receiver.addChild(handguard);
        setRotationAngle(handguard, 0.0F, 0.0F, 0.5236F);
        handguard.cubeList.add(new ModelBox(handguard, 18, 92, 0.616F, -0.933F, 6.75F, 1, 1, 7, 0.0F, false));
        handguard.cubeList.add(new ModelBox(handguard, 18, 92, 7.1782F, -0.8349F, 6.75F, 1, 1, 7, 0.0F, false));
        handguard.cubeList.add(new ModelBox(handguard, 18, 92, 0.616F, -0.933F, 3.75F, 1, 1, 1, 0.0F, false));
        handguard.cubeList.add(new ModelBox(handguard, 18, 92, 7.1782F, -0.8349F, 3.75F, 1, 1, 1, 0.0F, false));
        handguard.cubeList.add(new ModelBox(handguard, 18, 92, 0.616F, -0.933F, 0.75F, 1, 1, 1, 0.0F, false));
        handguard.cubeList.add(new ModelBox(handguard, 18, 92, 7.1782F, -0.8349F, 0.75F, 1, 1, 1, 0.0F, false));
        handguard.cubeList.add(new ModelBox(handguard, 18, 92, 0.616F, -0.933F, -2.25F, 1, 1, 1, 0.0F, false));
        handguard.cubeList.add(new ModelBox(handguard, 18, 92, 7.1782F, -0.8349F, -2.25F, 1, 1, 1, 0.0F, false));
        handguard.cubeList.add(new ModelBox(handguard, 18, 92, 0.616F, -0.933F, -5.25F, 1, 1, 1, 0.0F, false));
        handguard.cubeList.add(new ModelBox(handguard, 18, 92, 7.1782F, -0.8349F, -5.25F, 1, 1, 1, 0.0F, false));
        handguard.cubeList.add(new ModelBox(handguard, 18, 92, 0.616F, -0.933F, -11.25F, 1, 1, 4, 0.0F, false));
        handguard.cubeList.add(new ModelBox(handguard, 18, 92, 7.1782F, -0.8349F, -11.25F, 1, 1, 4, 0.0F, false));

        handguard2 = new ModelRenderer(this);
        handguard2.setRotationPoint(4.0F, -4.5F, -36.75F);
        receiver.addChild(handguard2);
        setRotationAngle(handguard2, 0.0F, 0.0F, -0.5236F);
        handguard2.cubeList.add(new ModelBox(handguard2, 18, 90, -1.616F, -0.933F, 6.75F, 1, 1, 7, 0.0F, true));
        handguard2.cubeList.add(new ModelBox(handguard2, 18, 90, -8.1782F, -0.8349F, 6.75F, 1, 1, 7, 0.0F, true));
        handguard2.cubeList.add(new ModelBox(handguard2, 18, 90, -1.616F, -0.933F, 3.75F, 1, 1, 1, 0.0F, true));
        handguard2.cubeList.add(new ModelBox(handguard2, 18, 90, -8.1782F, -0.8349F, 3.75F, 1, 1, 1, 0.0F, true));
        handguard2.cubeList.add(new ModelBox(handguard2, 18, 90, -1.616F, -0.933F, 0.75F, 1, 1, 1, 0.0F, true));
        handguard2.cubeList.add(new ModelBox(handguard2, 18, 90, -8.1782F, -0.8349F, 0.75F, 1, 1, 1, 0.0F, true));
        handguard2.cubeList.add(new ModelBox(handguard2, 18, 90, -1.616F, -0.933F, -2.25F, 1, 1, 1, 0.0F, true));
        handguard2.cubeList.add(new ModelBox(handguard2, 18, 90, -8.1782F, -0.8349F, -2.25F, 1, 1, 1, 0.0F, true));
        handguard2.cubeList.add(new ModelBox(handguard2, 18, 90, -1.616F, -0.933F, -5.25F, 1, 1, 1, 0.0F, true));
        handguard2.cubeList.add(new ModelBox(handguard2, 18, 90, -8.1782F, -0.8349F, -5.25F, 1, 1, 1, 0.0F, true));
        handguard2.cubeList.add(new ModelBox(handguard2, 18, 90, -1.616F, -0.933F, -11.25F, 1, 1, 4, 0.0F, true));
        handguard2.cubeList.add(new ModelBox(handguard2, 18, 90, -8.1782F, -0.8349F, -11.25F, 1, 1, 4, 0.0F, true));

        rail2 = new ModelRenderer(this);
        rail2.setRotationPoint(-16.5F, -8.0F, -30.0F);
        gun.addChild(rail2);
        rail2.cubeList.add(new ModelBox(rail2, 68, 78, 19.0F, -2.1F, -33.0F, 1, 3, 19, 0.0F, false));
        rail2.cubeList.add(new ModelBox(rail2, 68, 78, 20.0F, -1.6F, -15.0F, 1, 2, 1, 0.0F, false));
        rail2.cubeList.add(new ModelBox(rail2, 68, 78, 20.0F, -1.6F, -17.0F, 1, 2, 1, 0.0F, false));
        rail2.cubeList.add(new ModelBox(rail2, 68, 78, 20.0F, -1.6F, -19.0F, 1, 2, 1, 0.0F, false));
        rail2.cubeList.add(new ModelBox(rail2, 68, 78, 20.0F, -1.6F, -25.0F, 1, 2, 1, 0.0F, false));
        rail2.cubeList.add(new ModelBox(rail2, 68, 78, 20.0F, -1.6F, -31.0F, 1, 2, 1, 0.0F, false));
        rail2.cubeList.add(new ModelBox(rail2, 68, 78, 20.0F, -1.6F, -21.0F, 1, 2, 1, 0.0F, false));
        rail2.cubeList.add(new ModelBox(rail2, 68, 78, 20.0F, -1.6F, -27.0F, 1, 2, 1, 0.0F, false));
        rail2.cubeList.add(new ModelBox(rail2, 68, 78, 20.0F, -1.6F, -33.0F, 1, 2, 1, 0.0F, false));
        rail2.cubeList.add(new ModelBox(rail2, 68, 78, 20.0F, -1.6F, -23.0F, 1, 2, 1, 0.0F, false));
        rail2.cubeList.add(new ModelBox(rail2, 68, 78, 20.0F, -1.6F, -29.0F, 1, 2, 1, 0.0F, false));
        rail2.cubeList.add(new ModelBox(rail2, 68, 78, 20.499F, -0.734F, -16.0F, 0, 2, 1, 0.0F, false));
        rail2.cubeList.add(new ModelBox(rail2, 68, 78, 20.499F, -0.734F, -18.0F, 0, 2, 1, 0.0F, false));
        rail2.cubeList.add(new ModelBox(rail2, 68, 78, 20.499F, -0.734F, -20.0F, 0, 2, 1, 0.0F, false));
        rail2.cubeList.add(new ModelBox(rail2, 68, 78, 20.499F, -0.734F, -26.0F, 0, 2, 1, 0.0F, false));
        rail2.cubeList.add(new ModelBox(rail2, 68, 78, 20.499F, -0.734F, -32.0F, 0, 2, 1, 0.0F, false));
        rail2.cubeList.add(new ModelBox(rail2, 68, 78, 20.499F, -0.734F, -22.0F, 0, 2, 1, 0.0F, false));
        rail2.cubeList.add(new ModelBox(rail2, 68, 78, 20.499F, -0.734F, -28.0F, 0, 2, 1, 0.0F, false));
        rail2.cubeList.add(new ModelBox(rail2, 68, 78, 20.499F, -0.734F, -24.0F, 0, 2, 1, 0.0F, false));
        rail2.cubeList.add(new ModelBox(rail2, 68, 78, 20.499F, -0.734F, -30.0F, 0, 2, 1, 0.0F, false));
        rail2.cubeList.add(new ModelBox(rail2, 68, 78, 20.499F, -2.466F, -16.0F, 0, 2, 1, 0.0F, true));
        rail2.cubeList.add(new ModelBox(rail2, 68, 78, 20.499F, -2.466F, -18.0F, 0, 2, 1, 0.0F, true));
        rail2.cubeList.add(new ModelBox(rail2, 68, 78, 20.499F, -2.466F, -20.0F, 0, 2, 1, 0.0F, true));
        rail2.cubeList.add(new ModelBox(rail2, 68, 78, 20.499F, -2.466F, -26.0F, 0, 2, 1, 0.0F, true));
        rail2.cubeList.add(new ModelBox(rail2, 68, 78, 20.499F, -2.466F, -32.0F, 0, 2, 1, 0.0F, true));
        rail2.cubeList.add(new ModelBox(rail2, 68, 78, 20.499F, -2.466F, -22.0F, 0, 2, 1, 0.0F, true));
        rail2.cubeList.add(new ModelBox(rail2, 68, 78, 20.499F, -2.466F, -28.0F, 0, 2, 1, 0.0F, true));
        rail2.cubeList.add(new ModelBox(rail2, 68, 78, 20.499F, -2.466F, -24.0F, 0, 2, 1, 0.0F, true));
        rail2.cubeList.add(new ModelBox(rail2, 68, 78, 20.499F, -2.466F, -30.0F, 0, 2, 1, 0.0F, true));

        bone3 = new ModelRenderer(this);
        bone3.setRotationPoint(0.0F, -20.75F, -15.0F);
        rail2.addChild(bone3);
        setRotationAngle(bone3, 0.0F, 0.0F, 0.5236F);
        bone3.cubeList.add(new ModelBox(bone3, 68, 78, 27.7615F, 7.8164F, 0.0F, 1, 1, 1, 0.0F, false));
        bone3.cubeList.add(new ModelBox(bone3, 68, 78, 27.7615F, 7.8164F, -2.0F, 1, 1, 1, 0.0F, false));
        bone3.cubeList.add(new ModelBox(bone3, 68, 78, 27.7615F, 7.8164F, -4.0F, 1, 1, 1, 0.0F, false));
        bone3.cubeList.add(new ModelBox(bone3, 68, 78, 27.7615F, 7.8164F, -10.0F, 1, 1, 1, 0.0F, false));
        bone3.cubeList.add(new ModelBox(bone3, 68, 78, 27.7615F, 7.8164F, -16.0F, 1, 1, 1, 0.0F, false));
        bone3.cubeList.add(new ModelBox(bone3, 68, 78, 27.7615F, 7.8164F, -6.0F, 1, 1, 1, 0.0F, false));
        bone3.cubeList.add(new ModelBox(bone3, 68, 78, 27.7615F, 7.8164F, -12.0F, 1, 1, 1, 0.0F, false));
        bone3.cubeList.add(new ModelBox(bone3, 68, 78, 27.7615F, 7.8164F, -18.0F, 1, 1, 1, 0.0F, false));
        bone3.cubeList.add(new ModelBox(bone3, 68, 78, 27.7615F, 7.8164F, -8.0F, 1, 1, 1, 0.0F, false));
        bone3.cubeList.add(new ModelBox(bone3, 68, 78, 27.7615F, 7.8164F, -14.0F, 1, 1, 1, 0.0F, false));
        bone3.cubeList.add(new ModelBox(bone3, 68, 78, 27.7615F, 8.8164F, -1.0F, 1, 0, 1, 0.0F, false));
        bone3.cubeList.add(new ModelBox(bone3, 68, 78, 27.7615F, 8.8164F, -3.0F, 1, 0, 1, 0.0F, false));
        bone3.cubeList.add(new ModelBox(bone3, 68, 78, 27.7615F, 8.8164F, -5.0F, 1, 0, 1, 0.0F, false));
        bone3.cubeList.add(new ModelBox(bone3, 68, 78, 27.7615F, 8.8164F, -11.0F, 1, 0, 1, 0.0F, false));
        bone3.cubeList.add(new ModelBox(bone3, 68, 78, 27.7615F, 8.8164F, -17.0F, 1, 0, 1, 0.0F, false));
        bone3.cubeList.add(new ModelBox(bone3, 68, 78, 27.7615F, 8.8164F, -7.0F, 1, 0, 1, 0.0F, false));
        bone3.cubeList.add(new ModelBox(bone3, 68, 78, 27.7615F, 8.8164F, -13.0F, 1, 0, 1, 0.0F, false));
        bone3.cubeList.add(new ModelBox(bone3, 68, 78, 27.7615F, 8.8164F, -9.0F, 1, 0, 1, 0.0F, false));
        bone3.cubeList.add(new ModelBox(bone3, 68, 78, 27.7615F, 8.8164F, -15.0F, 1, 0, 1, 0.0F, false));

        bone4 = new ModelRenderer(this);
        bone4.setRotationPoint(0.0F, -20.75F, -15.0F);
        rail2.addChild(bone4);
        setRotationAngle(bone4, 0.0F, 0.0F, -0.5236F);
        bone4.cubeList.add(new ModelBox(bone4, 68, 78, 7.6115F, 26.0844F, 0.0F, 1, 1, 1, 0.0F, true));
        bone4.cubeList.add(new ModelBox(bone4, 68, 78, 7.6115F, 26.0844F, -2.0F, 1, 1, 1, 0.0F, true));
        bone4.cubeList.add(new ModelBox(bone4, 68, 78, 7.6115F, 26.0844F, -4.0F, 1, 1, 1, 0.0F, true));
        bone4.cubeList.add(new ModelBox(bone4, 68, 78, 7.6115F, 26.0844F, -10.0F, 1, 1, 1, 0.0F, true));
        bone4.cubeList.add(new ModelBox(bone4, 68, 78, 7.6115F, 26.0844F, -16.0F, 1, 1, 1, 0.0F, true));
        bone4.cubeList.add(new ModelBox(bone4, 68, 78, 7.6115F, 26.0844F, -6.0F, 1, 1, 1, 0.0F, true));
        bone4.cubeList.add(new ModelBox(bone4, 68, 78, 7.6115F, 26.0844F, -12.0F, 1, 1, 1, 0.0F, true));
        bone4.cubeList.add(new ModelBox(bone4, 68, 78, 7.6115F, 26.0844F, -18.0F, 1, 1, 1, 0.0F, true));
        bone4.cubeList.add(new ModelBox(bone4, 68, 78, 7.6115F, 26.0844F, -8.0F, 1, 1, 1, 0.0F, true));
        bone4.cubeList.add(new ModelBox(bone4, 68, 78, 7.6115F, 26.0844F, -14.0F, 1, 1, 1, 0.0F, true));
        bone4.cubeList.add(new ModelBox(bone4, 68, 78, 7.6115F, 26.0844F, -1.0F, 1, 0, 1, 0.0F, true));
        bone4.cubeList.add(new ModelBox(bone4, 68, 78, 7.6115F, 26.0844F, -3.0F, 1, 0, 1, 0.0F, true));
        bone4.cubeList.add(new ModelBox(bone4, 68, 78, 7.6115F, 26.0844F, -5.0F, 1, 0, 1, 0.0F, true));
        bone4.cubeList.add(new ModelBox(bone4, 68, 78, 7.6115F, 26.0844F, -11.0F, 1, 0, 1, 0.0F, true));
        bone4.cubeList.add(new ModelBox(bone4, 68, 78, 7.6115F, 26.0844F, -17.0F, 1, 0, 1, 0.0F, true));
        bone4.cubeList.add(new ModelBox(bone4, 68, 78, 7.6115F, 26.0844F, -7.0F, 1, 0, 1, 0.0F, true));
        bone4.cubeList.add(new ModelBox(bone4, 68, 78, 7.6115F, 26.0844F, -13.0F, 1, 0, 1, 0.0F, true));
        bone4.cubeList.add(new ModelBox(bone4, 68, 78, 7.6115F, 26.0844F, -9.0F, 1, 0, 1, 0.0F, true));
        bone4.cubeList.add(new ModelBox(bone4, 68, 78, 7.6115F, 26.0844F, -15.0F, 1, 0, 1, 0.0F, true));

        rail3 = new ModelRenderer(this);
        rail3.setRotationPoint(16.5F, -8.0F, -30.0F);
        gun.addChild(rail3);
        rail3.cubeList.add(new ModelBox(rail3, 68, 78, -20.0F, -2.1F, -33.0F, 1, 3, 19, 0.0F, true));
        rail3.cubeList.add(new ModelBox(rail3, 68, 78, -21.0F, -1.6F, -15.0F, 1, 2, 1, 0.0F, true));
        rail3.cubeList.add(new ModelBox(rail3, 68, 78, -21.0F, -1.6F, -17.0F, 1, 2, 1, 0.0F, true));
        rail3.cubeList.add(new ModelBox(rail3, 68, 78, -21.0F, -1.6F, -19.0F, 1, 2, 1, 0.0F, true));
        rail3.cubeList.add(new ModelBox(rail3, 68, 78, -21.0F, -1.6F, -25.0F, 1, 2, 1, 0.0F, true));
        rail3.cubeList.add(new ModelBox(rail3, 68, 78, -21.0F, -1.6F, -31.0F, 1, 2, 1, 0.0F, true));
        rail3.cubeList.add(new ModelBox(rail3, 68, 78, -21.0F, -1.6F, -21.0F, 1, 2, 1, 0.0F, true));
        rail3.cubeList.add(new ModelBox(rail3, 68, 78, -21.0F, -1.6F, -27.0F, 1, 2, 1, 0.0F, true));
        rail3.cubeList.add(new ModelBox(rail3, 68, 78, -21.0F, -1.6F, -33.0F, 1, 2, 1, 0.0F, true));
        rail3.cubeList.add(new ModelBox(rail3, 68, 78, -21.0F, -1.6F, -23.0F, 1, 2, 1, 0.0F, true));
        rail3.cubeList.add(new ModelBox(rail3, 68, 78, -21.0F, -1.6F, -29.0F, 1, 2, 1, 0.0F, true));
        rail3.cubeList.add(new ModelBox(rail3, 68, 78, -20.499F, -0.734F, -16.0F, 0, 2, 1, 0.0F, true));
        rail3.cubeList.add(new ModelBox(rail3, 68, 78, -20.499F, -0.734F, -18.0F, 0, 2, 1, 0.0F, true));
        rail3.cubeList.add(new ModelBox(rail3, 68, 78, -20.499F, -0.734F, -20.0F, 0, 2, 1, 0.0F, true));
        rail3.cubeList.add(new ModelBox(rail3, 68, 78, -20.499F, -0.734F, -26.0F, 0, 2, 1, 0.0F, true));
        rail3.cubeList.add(new ModelBox(rail3, 68, 78, -20.499F, -0.734F, -32.0F, 0, 2, 1, 0.0F, true));
        rail3.cubeList.add(new ModelBox(rail3, 68, 78, -20.499F, -0.734F, -22.0F, 0, 2, 1, 0.0F, true));
        rail3.cubeList.add(new ModelBox(rail3, 68, 78, -20.499F, -0.734F, -28.0F, 0, 2, 1, 0.0F, true));
        rail3.cubeList.add(new ModelBox(rail3, 68, 78, -20.499F, -0.734F, -24.0F, 0, 2, 1, 0.0F, true));
        rail3.cubeList.add(new ModelBox(rail3, 68, 78, -20.499F, -0.734F, -30.0F, 0, 2, 1, 0.0F, true));
        rail3.cubeList.add(new ModelBox(rail3, 68, 78, -20.499F, -2.466F, -16.0F, 0, 2, 1, 0.0F, false));
        rail3.cubeList.add(new ModelBox(rail3, 68, 78, -20.499F, -2.466F, -18.0F, 0, 2, 1, 0.0F, false));
        rail3.cubeList.add(new ModelBox(rail3, 68, 78, -20.499F, -2.466F, -20.0F, 0, 2, 1, 0.0F, false));
        rail3.cubeList.add(new ModelBox(rail3, 68, 78, -20.499F, -2.466F, -26.0F, 0, 2, 1, 0.0F, false));
        rail3.cubeList.add(new ModelBox(rail3, 68, 78, -20.499F, -2.466F, -32.0F, 0, 2, 1, 0.0F, false));
        rail3.cubeList.add(new ModelBox(rail3, 68, 78, -20.499F, -2.466F, -22.0F, 0, 2, 1, 0.0F, false));
        rail3.cubeList.add(new ModelBox(rail3, 68, 78, -20.499F, -2.466F, -28.0F, 0, 2, 1, 0.0F, false));
        rail3.cubeList.add(new ModelBox(rail3, 68, 78, -20.499F, -2.466F, -24.0F, 0, 2, 1, 0.0F, false));
        rail3.cubeList.add(new ModelBox(rail3, 68, 78, -20.499F, -2.466F, -30.0F, 0, 2, 1, 0.0F, false));

        bone5 = new ModelRenderer(this);
        bone5.setRotationPoint(0.0F, -20.75F, -15.0F);
        rail3.addChild(bone5);
        setRotationAngle(bone5, 0.0F, 0.0F, -0.5236F);
        bone5.cubeList.add(new ModelBox(bone5, 68, 78, -28.7615F, 7.8164F, 0.0F, 1, 1, 1, 0.0F, true));
        bone5.cubeList.add(new ModelBox(bone5, 68, 78, -28.7615F, 7.8164F, -2.0F, 1, 1, 1, 0.0F, true));
        bone5.cubeList.add(new ModelBox(bone5, 68, 78, -28.7615F, 7.8164F, -4.0F, 1, 1, 1, 0.0F, true));
        bone5.cubeList.add(new ModelBox(bone5, 68, 78, -28.7615F, 7.8164F, -10.0F, 1, 1, 1, 0.0F, true));
        bone5.cubeList.add(new ModelBox(bone5, 68, 78, -28.7615F, 7.8164F, -16.0F, 1, 1, 1, 0.0F, true));
        bone5.cubeList.add(new ModelBox(bone5, 68, 78, -28.7615F, 7.8164F, -6.0F, 1, 1, 1, 0.0F, true));
        bone5.cubeList.add(new ModelBox(bone5, 68, 78, -28.7615F, 7.8164F, -12.0F, 1, 1, 1, 0.0F, true));
        bone5.cubeList.add(new ModelBox(bone5, 68, 78, -28.7615F, 7.8164F, -18.0F, 1, 1, 1, 0.0F, true));
        bone5.cubeList.add(new ModelBox(bone5, 68, 78, -28.7615F, 7.8164F, -8.0F, 1, 1, 1, 0.0F, true));
        bone5.cubeList.add(new ModelBox(bone5, 68, 78, -28.7615F, 7.8164F, -14.0F, 1, 1, 1, 0.0F, true));
        bone5.cubeList.add(new ModelBox(bone5, 68, 78, -28.7615F, 8.8164F, -1.0F, 1, 0, 1, 0.0F, true));
        bone5.cubeList.add(new ModelBox(bone5, 68, 78, -28.7615F, 8.8164F, -3.0F, 1, 0, 1, 0.0F, true));
        bone5.cubeList.add(new ModelBox(bone5, 68, 78, -28.7615F, 8.8164F, -5.0F, 1, 0, 1, 0.0F, true));
        bone5.cubeList.add(new ModelBox(bone5, 68, 78, -28.7615F, 8.8164F, -11.0F, 1, 0, 1, 0.0F, true));
        bone5.cubeList.add(new ModelBox(bone5, 68, 78, -28.7615F, 8.8164F, -17.0F, 1, 0, 1, 0.0F, true));
        bone5.cubeList.add(new ModelBox(bone5, 68, 78, -28.7615F, 8.8164F, -7.0F, 1, 0, 1, 0.0F, true));
        bone5.cubeList.add(new ModelBox(bone5, 68, 78, -28.7615F, 8.8164F, -13.0F, 1, 0, 1, 0.0F, true));
        bone5.cubeList.add(new ModelBox(bone5, 68, 78, -28.7615F, 8.8164F, -9.0F, 1, 0, 1, 0.0F, true));
        bone5.cubeList.add(new ModelBox(bone5, 68, 78, -28.7615F, 8.8164F, -15.0F, 1, 0, 1, 0.0F, true));

        bone6 = new ModelRenderer(this);
        bone6.setRotationPoint(0.0F, -20.75F, -15.0F);
        rail3.addChild(bone6);
        setRotationAngle(bone6, 0.0F, 0.0F, 0.5236F);
        bone6.cubeList.add(new ModelBox(bone6, 68, 78, -8.6115F, 26.0844F, 0.0F, 1, 1, 1, 0.0F, false));
        bone6.cubeList.add(new ModelBox(bone6, 68, 78, -8.6115F, 26.0844F, -2.0F, 1, 1, 1, 0.0F, false));
        bone6.cubeList.add(new ModelBox(bone6, 68, 78, -8.6115F, 26.0844F, -4.0F, 1, 1, 1, 0.0F, false));
        bone6.cubeList.add(new ModelBox(bone6, 68, 78, -8.6115F, 26.0844F, -10.0F, 1, 1, 1, 0.0F, false));
        bone6.cubeList.add(new ModelBox(bone6, 68, 78, -8.6115F, 26.0844F, -16.0F, 1, 1, 1, 0.0F, false));
        bone6.cubeList.add(new ModelBox(bone6, 68, 78, -8.6115F, 26.0844F, -6.0F, 1, 1, 1, 0.0F, false));
        bone6.cubeList.add(new ModelBox(bone6, 68, 78, -8.6115F, 26.0844F, -12.0F, 1, 1, 1, 0.0F, false));
        bone6.cubeList.add(new ModelBox(bone6, 68, 78, -8.6115F, 26.0844F, -18.0F, 1, 1, 1, 0.0F, false));
        bone6.cubeList.add(new ModelBox(bone6, 68, 78, -8.6115F, 26.0844F, -8.0F, 1, 1, 1, 0.0F, false));
        bone6.cubeList.add(new ModelBox(bone6, 68, 78, -8.6115F, 26.0844F, -14.0F, 1, 1, 1, 0.0F, false));
        bone6.cubeList.add(new ModelBox(bone6, 68, 78, -8.6115F, 26.0844F, -1.0F, 1, 0, 1, 0.0F, false));
        bone6.cubeList.add(new ModelBox(bone6, 68, 78, -8.6115F, 26.0844F, -3.0F, 1, 0, 1, 0.0F, false));
        bone6.cubeList.add(new ModelBox(bone6, 68, 78, -8.6115F, 26.0844F, -5.0F, 1, 0, 1, 0.0F, false));
        bone6.cubeList.add(new ModelBox(bone6, 68, 78, -8.6115F, 26.0844F, -11.0F, 1, 0, 1, 0.0F, false));
        bone6.cubeList.add(new ModelBox(bone6, 68, 78, -8.6115F, 26.0844F, -17.0F, 1, 0, 1, 0.0F, false));
        bone6.cubeList.add(new ModelBox(bone6, 68, 78, -8.6115F, 26.0844F, -7.0F, 1, 0, 1, 0.0F, false));
        bone6.cubeList.add(new ModelBox(bone6, 68, 78, -8.6115F, 26.0844F, -13.0F, 1, 0, 1, 0.0F, false));
        bone6.cubeList.add(new ModelBox(bone6, 68, 78, -8.6115F, 26.0844F, -9.0F, 1, 0, 1, 0.0F, false));
        bone6.cubeList.add(new ModelBox(bone6, 68, 78, -8.6115F, 26.0844F, -15.0F, 1, 0, 1, 0.0F, false));

        bolt = new ModelRenderer(this);
        bolt.setRotationPoint(0.0F, 24.0F, 0.0F);
        bolt.cubeList.add(new ModelBox(bolt, 19, 147, -2.9967F, -7.6402F, -6.9679F, 1, 2, 1, 0.0F, false));
        bolt.cubeList.add(new ModelBox(bolt, 0, 82, -1.0F, -13.666F, -32.5F, 2, 1, 3, 0.0F, false));
        bolt.cubeList.add(new ModelBox(bolt, 0, 82, -1.5F, -12.8F, -32.5F, 3, 3, 3, 0.0F, false));
        bolt.cubeList.add(new ModelBox(bolt, 0, 82, -1.5F, -10.6F, -29.5F, 3, 1, 14, 0.0F, false));

        cube_r43 = new ModelRenderer(this);
        cube_r43.setRotationPoint(-0.549F, -13.349F, -31.0F);
        bolt.addChild(cube_r43);
        setRotationAngle(cube_r43, 0.0F, 0.0F, -0.5236F);
        cube_r43.cubeList.add(new ModelBox(cube_r43, 0, 82, 0.5F, 0.5F, -1.5F, 1, 1, 3, 0.0F, true));

        cube_r44 = new ModelRenderer(this);
        cube_r44.setRotationPoint(0.549F, -13.349F, -31.0F);
        bolt.addChild(cube_r44);
        setRotationAngle(cube_r44, 0.0F, 0.0F, 0.5236F);
        cube_r44.cubeList.add(new ModelBox(cube_r44, 0, 82, -1.5F, 0.5F, -1.5F, 1, 1, 3, 0.0F, false));

        cube_r45 = new ModelRenderer(this);
        cube_r45.setRotationPoint(-0.5F, -14.5F, 0.2321F);
        bolt.addChild(cube_r45);
        setRotationAngle(cube_r45, 0.0F, 0.0F, 0.3491F);
        cube_r45.cubeList.add(new ModelBox(cube_r45, 19, 147, 0.0F, 5.3F, -7.2F, 1, 2, 1, 0.0F, false));
        cube_r45.cubeList.add(new ModelBox(cube_r45, 19, 147, 0.6F, 5.8F, -7.0F, 1, 3, 8, 0.0F, false));

        rail = new ModelRenderer(this);
        rail.setRotationPoint(0.0F, 24.0F, 0.0F);
        rail.cubeList.add(new ModelBox(rail, 68, 78, -1.5F, -20.0F, -39.0F, 3, 1, 25, 0.0F, false));
        rail.cubeList.add(new ModelBox(rail, 39, 171, -1.0F, -19.0F, -38.0F, 2, 1, 1, 0.0F, false));
        rail.cubeList.add(new ModelBox(rail, 39, 171, -1.0F, -19.0F, -16.0F, 2, 1, 1, 0.0F, false));
        rail.cubeList.add(new ModelBox(rail, 68, 78, -1.0F, -21.0F, -15.0F, 2, 1, 1, 0.0F, false));
        rail.cubeList.add(new ModelBox(rail, 68, 78, -1.0F, -21.0F, -17.0F, 2, 1, 1, 0.0F, false));
        rail.cubeList.add(new ModelBox(rail, 68, 78, -1.0F, -21.0F, -19.0F, 2, 1, 1, 0.0F, false));
        rail.cubeList.add(new ModelBox(rail, 68, 78, -1.0F, -21.0F, -25.0F, 2, 1, 1, 0.0F, false));
        rail.cubeList.add(new ModelBox(rail, 68, 78, -1.0F, -21.0F, -31.0F, 2, 1, 1, 0.0F, false));
        rail.cubeList.add(new ModelBox(rail, 68, 78, -1.0F, -21.0F, -37.0F, 2, 1, 1, 0.0F, false));
        rail.cubeList.add(new ModelBox(rail, 68, 78, -1.0F, -21.0F, -21.0F, 2, 1, 1, 0.0F, false));
        rail.cubeList.add(new ModelBox(rail, 68, 78, -1.0F, -21.0F, -27.0F, 2, 1, 1, 0.0F, false));
        rail.cubeList.add(new ModelBox(rail, 68, 78, -1.0F, -21.0F, -33.0F, 2, 1, 1, 0.0F, false));
        rail.cubeList.add(new ModelBox(rail, 68, 78, -1.0F, -21.0F, -39.0F, 2, 1, 1, 0.0F, false));
        rail.cubeList.add(new ModelBox(rail, 68, 78, -1.0F, -21.0F, -23.0F, 2, 1, 1, 0.0F, false));
        rail.cubeList.add(new ModelBox(rail, 68, 78, -1.0F, -21.0F, -29.0F, 2, 1, 1, 0.0F, false));
        rail.cubeList.add(new ModelBox(rail, 68, 78, -1.0F, -21.0F, -35.0F, 2, 1, 1, 0.0F, false));
        rail.cubeList.add(new ModelBox(rail, 68, 78, -0.134F, -20.499F, -16.0F, 2, 0, 1, 0.0F, false));
        rail.cubeList.add(new ModelBox(rail, 68, 78, -0.134F, -20.499F, -18.0F, 2, 0, 1, 0.0F, false));
        rail.cubeList.add(new ModelBox(rail, 68, 78, -0.134F, -20.499F, -20.0F, 2, 0, 1, 0.0F, false));
        rail.cubeList.add(new ModelBox(rail, 68, 78, -0.134F, -20.499F, -26.0F, 2, 0, 1, 0.0F, false));
        rail.cubeList.add(new ModelBox(rail, 68, 78, -0.134F, -20.499F, -32.0F, 2, 0, 1, 0.0F, false));
        rail.cubeList.add(new ModelBox(rail, 68, 78, -0.134F, -20.499F, -38.0F, 2, 0, 1, 0.0F, false));
        rail.cubeList.add(new ModelBox(rail, 68, 78, -0.134F, -20.499F, -22.0F, 2, 0, 1, 0.0F, false));
        rail.cubeList.add(new ModelBox(rail, 68, 78, -0.134F, -20.499F, -28.0F, 2, 0, 1, 0.0F, false));
        rail.cubeList.add(new ModelBox(rail, 68, 78, -0.134F, -20.499F, -34.0F, 2, 0, 1, 0.0F, false));
        rail.cubeList.add(new ModelBox(rail, 68, 78, -0.134F, -20.499F, -24.0F, 2, 0, 1, 0.0F, false));
        rail.cubeList.add(new ModelBox(rail, 68, 78, -0.134F, -20.499F, -30.0F, 2, 0, 1, 0.0F, false));
        rail.cubeList.add(new ModelBox(rail, 68, 78, -0.134F, -20.499F, -36.0F, 2, 0, 1, 0.0F, false));
        rail.cubeList.add(new ModelBox(rail, 68, 78, -1.866F, -20.499F, -16.0F, 2, 0, 1, 0.0F, true));
        rail.cubeList.add(new ModelBox(rail, 68, 78, -1.866F, -20.499F, -18.0F, 2, 0, 1, 0.0F, true));
        rail.cubeList.add(new ModelBox(rail, 68, 78, -1.866F, -20.499F, -20.0F, 2, 0, 1, 0.0F, true));
        rail.cubeList.add(new ModelBox(rail, 68, 78, -1.866F, -20.499F, -26.0F, 2, 0, 1, 0.0F, true));
        rail.cubeList.add(new ModelBox(rail, 68, 78, -1.866F, -20.499F, -32.0F, 2, 0, 1, 0.0F, true));
        rail.cubeList.add(new ModelBox(rail, 68, 78, -1.866F, -20.499F, -38.0F, 2, 0, 1, 0.0F, true));
        rail.cubeList.add(new ModelBox(rail, 68, 78, -1.866F, -20.499F, -22.0F, 2, 0, 1, 0.0F, true));
        rail.cubeList.add(new ModelBox(rail, 68, 78, -1.866F, -20.499F, -28.0F, 2, 0, 1, 0.0F, true));
        rail.cubeList.add(new ModelBox(rail, 68, 78, -1.866F, -20.499F, -34.0F, 2, 0, 1, 0.0F, true));
        rail.cubeList.add(new ModelBox(rail, 68, 78, -1.866F, -20.499F, -24.0F, 2, 0, 1, 0.0F, true));
        rail.cubeList.add(new ModelBox(rail, 68, 78, -1.866F, -20.499F, -30.0F, 2, 0, 1, 0.0F, true));
        rail.cubeList.add(new ModelBox(rail, 68, 78, -1.866F, -20.499F, -36.0F, 2, 0, 1, 0.0F, true));

        bone = new ModelRenderer(this);
        bone.setRotationPoint(0.0F, -20.75F, -15.0F);
        rail.addChild(bone);
        setRotationAngle(bone, 0.0F, 0.0F, 0.5236F);
        bone.cubeList.add(new ModelBox(bone, 68, 78, 0.741F, -0.7165F, 0.0F, 1, 1, 1, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 68, 78, 0.741F, -0.7165F, -2.0F, 1, 1, 1, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 68, 78, 0.741F, -0.7165F, -4.0F, 1, 1, 1, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 68, 78, 0.741F, -0.7165F, -10.0F, 1, 1, 1, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 68, 78, 0.741F, -0.7165F, -16.0F, 1, 1, 1, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 68, 78, 0.741F, -0.7165F, -22.0F, 1, 1, 1, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 68, 78, 0.741F, -0.7165F, -6.0F, 1, 1, 1, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 68, 78, 0.741F, -0.7165F, -12.0F, 1, 1, 1, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 68, 78, 0.741F, -0.7165F, -18.0F, 1, 1, 1, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 68, 78, 0.741F, -0.7165F, -24.0F, 1, 1, 1, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 68, 78, 0.741F, -0.7165F, -8.0F, 1, 1, 1, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 68, 78, 0.741F, -0.7165F, -14.0F, 1, 1, 1, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 68, 78, 0.741F, -0.7165F, -20.0F, 1, 1, 1, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 68, 78, 1.741F, -0.7165F, -1.0F, 0, 1, 1, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 68, 78, 1.741F, -0.7165F, -3.0F, 0, 1, 1, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 68, 78, 1.741F, -0.7165F, -5.0F, 0, 1, 1, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 68, 78, 1.741F, -0.7165F, -11.0F, 0, 1, 1, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 68, 78, 1.741F, -0.7165F, -17.0F, 0, 1, 1, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 68, 78, 1.741F, -0.7165F, -23.0F, 0, 1, 1, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 68, 78, 1.741F, -0.7165F, -7.0F, 0, 1, 1, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 68, 78, 1.741F, -0.7165F, -13.0F, 0, 1, 1, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 68, 78, 1.741F, -0.7165F, -19.0F, 0, 1, 1, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 68, 78, 1.741F, -0.7165F, -9.0F, 0, 1, 1, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 68, 78, 1.741F, -0.7165F, -15.0F, 0, 1, 1, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 68, 78, 1.741F, -0.7165F, -21.0F, 0, 1, 1, 0.0F, false));

        bone2 = new ModelRenderer(this);
        bone2.setRotationPoint(0.0F, -20.75F, -15.0F);
        rail.addChild(bone2);
        setRotationAngle(bone2, 0.0F, 0.0F, -0.5236F);
        bone2.cubeList.add(new ModelBox(bone2, 68, 78, -1.741F, -0.7165F, 0.0F, 1, 1, 1, 0.0F, true));
        bone2.cubeList.add(new ModelBox(bone2, 68, 78, -1.741F, -0.7165F, -2.0F, 1, 1, 1, 0.0F, true));
        bone2.cubeList.add(new ModelBox(bone2, 68, 78, -1.741F, -0.7165F, -4.0F, 1, 1, 1, 0.0F, true));
        bone2.cubeList.add(new ModelBox(bone2, 68, 78, -1.741F, -0.7165F, -10.0F, 1, 1, 1, 0.0F, true));
        bone2.cubeList.add(new ModelBox(bone2, 68, 78, -1.741F, -0.7165F, -16.0F, 1, 1, 1, 0.0F, true));
        bone2.cubeList.add(new ModelBox(bone2, 68, 78, -1.741F, -0.7165F, -22.0F, 1, 1, 1, 0.0F, true));
        bone2.cubeList.add(new ModelBox(bone2, 68, 78, -1.741F, -0.7165F, -6.0F, 1, 1, 1, 0.0F, true));
        bone2.cubeList.add(new ModelBox(bone2, 68, 78, -1.741F, -0.7165F, -12.0F, 1, 1, 1, 0.0F, true));
        bone2.cubeList.add(new ModelBox(bone2, 68, 78, -1.741F, -0.7165F, -18.0F, 1, 1, 1, 0.0F, true));
        bone2.cubeList.add(new ModelBox(bone2, 68, 78, -1.741F, -0.7165F, -24.0F, 1, 1, 1, 0.0F, true));
        bone2.cubeList.add(new ModelBox(bone2, 68, 78, -1.741F, -0.7165F, -8.0F, 1, 1, 1, 0.0F, true));
        bone2.cubeList.add(new ModelBox(bone2, 68, 78, -1.741F, -0.7165F, -14.0F, 1, 1, 1, 0.0F, true));
        bone2.cubeList.add(new ModelBox(bone2, 68, 78, -1.741F, -0.7165F, -20.0F, 1, 1, 1, 0.0F, true));
        bone2.cubeList.add(new ModelBox(bone2, 68, 78, -1.741F, -0.7165F, -1.0F, 0, 1, 1, 0.0F, true));
        bone2.cubeList.add(new ModelBox(bone2, 68, 78, -1.741F, -0.7165F, -3.0F, 0, 1, 1, 0.0F, true));
        bone2.cubeList.add(new ModelBox(bone2, 68, 78, -1.741F, -0.7165F, -5.0F, 0, 1, 1, 0.0F, true));
        bone2.cubeList.add(new ModelBox(bone2, 68, 78, -1.741F, -0.7165F, -11.0F, 0, 1, 1, 0.0F, true));
        bone2.cubeList.add(new ModelBox(bone2, 68, 78, -1.741F, -0.7165F, -17.0F, 0, 1, 1, 0.0F, true));
        bone2.cubeList.add(new ModelBox(bone2, 68, 78, -1.741F, -0.7165F, -23.0F, 0, 1, 1, 0.0F, true));
        bone2.cubeList.add(new ModelBox(bone2, 68, 78, -1.741F, -0.7165F, -7.0F, 0, 1, 1, 0.0F, true));
        bone2.cubeList.add(new ModelBox(bone2, 68, 78, -1.741F, -0.7165F, -13.0F, 0, 1, 1, 0.0F, true));
        bone2.cubeList.add(new ModelBox(bone2, 68, 78, -1.741F, -0.7165F, -19.0F, 0, 1, 1, 0.0F, true));
        bone2.cubeList.add(new ModelBox(bone2, 68, 78, -1.741F, -0.7165F, -9.0F, 0, 1, 1, 0.0F, true));
        bone2.cubeList.add(new ModelBox(bone2, 68, 78, -1.741F, -0.7165F, -15.0F, 0, 1, 1, 0.0F, true));
        bone2.cubeList.add(new ModelBox(bone2, 68, 78, -1.741F, -0.7165F, -21.0F, 0, 1, 1, 0.0F, true));
        this.initAnimations();
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}

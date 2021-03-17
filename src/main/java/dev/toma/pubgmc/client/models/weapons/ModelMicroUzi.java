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

public class ModelMicroUzi extends ModelGun {

    private final ModelRenderer magazine;
    private final ModelRenderer cube_r1;
    private final ModelRenderer cube_r2;
    private final ModelRenderer cube_r3;
    private final ModelRenderer cube_r4;
    private final ModelRenderer bullet;
    private final ModelRenderer gun;
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
    private final ModelRenderer grip;
    private final ModelRenderer cube_r17;
    private final ModelRenderer cube_r18;
    private final ModelRenderer cube_r19;
    private final ModelRenderer cube_r20;
    private final ModelRenderer cube_r21;
    private final ModelRenderer cube_r22;
    private final ModelRenderer bone4;
    private final ModelRenderer cube_r23;
    private final ModelRenderer cube_r24;
    private final ModelRenderer bone3;
    private final ModelRenderer cube_r25;
    private final ModelRenderer cube_r26;
    private final ModelRenderer bone2;
    private final ModelRenderer cube_r27;
    private final ModelRenderer cube_r28;
    private final ModelRenderer front;
    private final ModelRenderer cube_r29;
    private final ModelRenderer cube_r30;
    private final ModelRenderer cube_r31;
    private final ModelRenderer cube_r32;
    private final ModelRenderer cube_r33;
    private final ModelRenderer cube_r34;
    private final ModelRenderer charger;
    private final ModelRenderer cube_r35;
    private final ModelRenderer cube_r36;
    private final ModelRenderer cube_r37;
    private final ModelRenderer stock;
    private final ModelRenderer stock9;
    private final ModelRenderer stock10;
    private final ModelRenderer stock11;
    private final ModelRenderer stock12;
    private final ModelRenderer stock13;
    private final ModelRenderer stock14;
    private final ModelRenderer bone5;
    private final ModelRenderer toprail;
    private final ModelRenderer cube_r38;
    private final ModelRenderer bone;
    private final ModelRenderer bone6;
    private final ModelRenderer botrail;
    private final ModelRenderer bone7;
    private final ModelRenderer bone8;

    @Override
    public void initAnimations() {
        initAimAnimation(-0.56f, 0.21f, 0.23f);
        initAimingAnimationStates(0.21f, 0.055f, 0.05f);
        heldAnimation = new HeldAnimation(HeldStyle.SMALL);
        reloadAnimation = new ReloadAnimation(magazine, ReloadAnimation.ReloadStyle.MAGAZINE).withSpeed(1.2F);
    }

    @Override
    public void render(ItemStack stack, ItemCameraTransforms.TransformType transformType) {
        EntityPlayerSP player = Minecraft.getMinecraft().player;
        if (player != null && player.hasCapability(PlayerDataProvider.PLAYER_DATA, null)) {
            GlStateManager.pushMatrix();
            {
                renderUzi(stack);
            }
            GlStateManager.popMatrix();
        }
    }

    private void renderUzi(ItemStack stack) {
        GlStateManager.pushMatrix();
        ModelTransformationHelper.defaultSMGTransform();
        GlStateManager.translate(0.0, 0.85, -14.0);
        gun.render(1.0F);
        magazine.render(1.0F);
        charger.render(1.0F);
        stock.render(1.0F);
        if(hasScopeAtachment(stack))
            toprail.render(1.0F);
        botrail.render(1.0F);
        GlStateManager.popMatrix();

        /*renderSMGSilencer(0, -6, 0, 1.2F, stack);
        renderRedDot(-0.05, -6.25, -10, 1.2F, stack);
        renderHolo(-0.1, -2.475, -6, 1.1F, stack);*/
    }

    public ModelMicroUzi() {
        textureWidth = 512;
        textureHeight = 512;

        magazine = new ModelRenderer(this);
        magazine.setRotationPoint(-0.5F, 24.0F, -1.0F);
        magazine.cubeList.add(new ModelBox(magazine, 88, 32, -1.0F, -2.0F, 1.366F, 3, 2, 1, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 88, 32, -1.0F, -3.5F, 1.366F, 3, 1, 1, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 88, 32, -1.0F, -5.0F, 1.366F, 3, 1, 1, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 88, 32, -1.0F, -2.0F, -4.366F, 3, 2, 1, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 88, 32, -1.0F, -3.5F, -4.366F, 3, 1, 1, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 88, 32, -1.0F, -5.0F, -4.366F, 3, 1, 1, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 88, 32, -1.5F, -2.0F, -3.5F, 4, 2, 5, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 18, 147, -1.0F, -20.0F, -3.5F, 3, 18, 5, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 18, 147, -1.0F, -21.0F, -3.5F, 1, 1, 5, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 18, 147, 1.0F, -21.0F, -3.5F, 1, 1, 5, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 88, 32, -1.5F, -3.5F, -3.5F, 4, 1, 5, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 88, 32, -1.5F, -5.0F, -3.5F, 4, 1, 5, 0.0F, false));

        cube_r1 = new ModelRenderer(this);
        cube_r1.setRotationPoint(-1.201F, -3.0F, 0.9821F);
        magazine.addChild(cube_r1);
        setRotationAngle(cube_r1, 0.0F, -0.5236F, 0.0F);
        cube_r1.cubeList.add(new ModelBox(cube_r1, 88, 32, -2.5F, -0.5F, -4.732F, 1, 1, 1, 0.0F, true));
        cube_r1.cubeList.add(new ModelBox(cube_r1, 88, 32, -2.5F, -2.0F, -4.732F, 1, 1, 1, 0.0F, true));
        cube_r1.cubeList.add(new ModelBox(cube_r1, 88, 32, -2.5F, 1.0F, -4.732F, 1, 2, 1, 0.0F, true));

        cube_r2 = new ModelRenderer(this);
        cube_r2.setRotationPoint(-1.201F, -3.0F, 1.0179F);
        magazine.addChild(cube_r2);
        setRotationAngle(cube_r2, 0.0F, 0.5236F, 0.0F);
        cube_r2.cubeList.add(new ModelBox(cube_r2, 88, 32, -0.5F, -0.5F, 0.268F, 1, 1, 1, 0.0F, true));
        cube_r2.cubeList.add(new ModelBox(cube_r2, 88, 32, -0.5F, -2.0F, 0.268F, 1, 1, 1, 0.0F, true));
        cube_r2.cubeList.add(new ModelBox(cube_r2, 88, 32, -0.5F, 1.0F, 0.268F, 1, 2, 1, 0.0F, true));

        cube_r3 = new ModelRenderer(this);
        cube_r3.setRotationPoint(2.201F, -3.0F, 0.9821F);
        magazine.addChild(cube_r3);
        setRotationAngle(cube_r3, 0.0F, 0.5236F, 0.0F);
        cube_r3.cubeList.add(new ModelBox(cube_r3, 88, 32, 1.5F, -0.5F, -4.732F, 1, 1, 1, 0.0F, false));
        cube_r3.cubeList.add(new ModelBox(cube_r3, 88, 32, 1.5F, -2.0F, -4.732F, 1, 1, 1, 0.0F, false));
        cube_r3.cubeList.add(new ModelBox(cube_r3, 88, 32, 1.5F, 1.0F, -4.732F, 1, 2, 1, 0.0F, false));

        cube_r4 = new ModelRenderer(this);
        cube_r4.setRotationPoint(2.201F, -3.0F, 1.0179F);
        magazine.addChild(cube_r4);
        setRotationAngle(cube_r4, 0.0F, -0.5236F, 0.0F);
        cube_r4.cubeList.add(new ModelBox(cube_r4, 88, 32, -0.5F, -0.5F, 0.268F, 1, 1, 1, 0.0F, false));
        cube_r4.cubeList.add(new ModelBox(cube_r4, 88, 32, -0.5F, -2.0F, 0.268F, 1, 1, 1, 0.0F, false));
        cube_r4.cubeList.add(new ModelBox(cube_r4, 88, 32, -0.5F, 1.0F, 0.268F, 1, 2, 1, 0.0F, false));

        bullet = new ModelRenderer(this);
        bullet.setRotationPoint(0.5F, -20.5F, -4.366F);
        magazine.addChild(bullet);
        bullet.cubeList.add(new ModelBox(bullet, 11, 496, -1.0F, -1.6F, 4.766F, 2, 2, 1, 0.0F, false));
        bullet.cubeList.add(new ModelBox(bullet, 11, 496, -1.0F, -1.6F, 2.366F, 2, 2, 2, 0.0F, false));
        bullet.cubeList.add(new ModelBox(bullet, 11, 496, -0.2F, -1.4F, 1.066F, 1, 1, 2, 0.0F, false));
        bullet.cubeList.add(new ModelBox(bullet, 11, 496, -0.2F, -0.8F, 1.066F, 1, 1, 2, 0.0F, false));
        bullet.cubeList.add(new ModelBox(bullet, 11, 496, -0.8F, -1.4F, 1.066F, 1, 1, 2, 0.0F, false));
        bullet.cubeList.add(new ModelBox(bullet, 11, 496, -0.8F, -0.8F, 1.066F, 1, 1, 2, 0.0F, false));
        bullet.cubeList.add(new ModelBox(bullet, 11, 496, -0.1F, -0.7F, 3.766F, 1, 1, 1, 0.0F, false));
        bullet.cubeList.add(new ModelBox(bullet, 11, 496, -0.1F, -1.5F, 3.766F, 1, 1, 1, 0.0F, false));
        bullet.cubeList.add(new ModelBox(bullet, 11, 496, -0.9F, -0.7F, 3.766F, 1, 1, 1, 0.0F, false));
        bullet.cubeList.add(new ModelBox(bullet, 11, 496, -0.9F, -1.5F, 3.766F, 1, 1, 1, 0.0F, false));

        gun = new ModelRenderer(this);
        gun.setRotationPoint(0.0F, 22.0F, 0.0F);
        gun.cubeList.add(new ModelBox(gun, 73, 9, 1.3F, -18.0F, -15.0F, 1, 3, 24, 0.0F, false));
        gun.cubeList.add(new ModelBox(gun, 73, 9, -2.3F, -18.0F, -15.0F, 1, 3, 24, 0.0F, true));
        gun.cubeList.add(new ModelBox(gun, 17, 88, -1.5F, -15.9F, 1.0F, 3, 1, 8, 0.0F, true));
        gun.cubeList.add(new ModelBox(gun, 96, 29, -2.5F, -23.0F, 15.0F, 5, 5, 1, 0.0F, true));
        gun.cubeList.add(new ModelBox(gun, 17, 88, -1.5F, -15.9F, -15.0F, 3, 1, 10, 0.0F, true));
        gun.cubeList.add(new ModelBox(gun, 67, 22, 1.5F, -19.0F, -15.0F, 1, 1, 15, 0.0F, false));
        gun.cubeList.add(new ModelBox(gun, 67, 22, 1.5F, -23.0F, -15.0F, 1, 1, 15, 0.0F, false));
        gun.cubeList.add(new ModelBox(gun, 67, 22, -1.0F, -23.0F, -14.0F, 2, 1, 8, 0.0F, false));
        gun.cubeList.add(new ModelBox(gun, 67, 22, -2.5F, -23.0F, -15.0F, 1, 1, 15, 0.0F, true));
        gun.cubeList.add(new ModelBox(gun, 67, 22, -1.5F, -23.0F, -15.0F, 3, 2, 1, 0.0F, true));
        gun.cubeList.add(new ModelBox(gun, 87, 35, -1.0F, -23.0F, -18.9F, 2, 1, 2, 0.0F, true));
        gun.cubeList.add(new ModelBox(gun, 67, 22, -1.5F, -23.866F, -21.9F, 3, 2, 3, 0.0F, true));
        gun.cubeList.add(new ModelBox(gun, 67, 22, -1.5F, -23.866F, 6.1F, 3, 1, 3, 0.0F, true));
        gun.cubeList.add(new ModelBox(gun, 28, 104, -1.9F, -24.866F, -20.9F, 1, 1, 1, 0.0F, true));
        gun.cubeList.add(new ModelBox(gun, 28, 104, -1.9F, -24.866F, 7.1F, 1, 1, 1, 0.0F, true));
        gun.cubeList.add(new ModelBox(gun, 28, 104, -0.9F, -23.966F, -20.9F, 1, 1, 1, 0.0F, true));
        gun.cubeList.add(new ModelBox(gun, 28, 104, -0.9F, -23.966F, 7.1F, 1, 1, 1, 0.0F, true));
        gun.cubeList.add(new ModelBox(gun, 28, 104, -0.1F, -23.966F, -20.9F, 1, 1, 1, 0.0F, false));
        gun.cubeList.add(new ModelBox(gun, 28, 104, -0.1F, -23.966F, 7.1F, 1, 1, 1, 0.0F, false));
        gun.cubeList.add(new ModelBox(gun, 28, 104, 0.9F, -24.866F, -20.9F, 1, 1, 1, 0.0F, true));
        gun.cubeList.add(new ModelBox(gun, 28, 104, 0.9F, -24.866F, 7.1F, 1, 1, 1, 0.0F, true));
        gun.cubeList.add(new ModelBox(gun, 67, 22, 1.0F, -24.732F, 6.6F, 1, 1, 2, 0.0F, true));
        gun.cubeList.add(new ModelBox(gun, 67, 22, 1.0F, -24.732F, -21.4F, 1, 1, 2, 0.0F, true));
        gun.cubeList.add(new ModelBox(gun, 67, 22, -2.0F, -24.732F, 6.6F, 1, 1, 2, 0.0F, false));
        gun.cubeList.add(new ModelBox(gun, 67, 22, -2.0F, -24.732F, -21.4F, 1, 1, 2, 0.0F, false));
        gun.cubeList.add(new ModelBox(gun, 67, 22, 1.5F, -21.5F, -15.0F, 1, 2, 9, 0.0F, false));
        gun.cubeList.add(new ModelBox(gun, 67, 22, -2.5F, -21.5F, -15.0F, 1, 2, 9, 0.0F, true));
        gun.cubeList.add(new ModelBox(gun, 80, 88, 1.2F, -21.5F, -6.0F, 1, 2, 9, 0.0F, false));
        gun.cubeList.add(new ModelBox(gun, 80, 88, -2.2F, -21.5F, -6.0F, 1, 2, 9, 0.0F, true));
        gun.cubeList.add(new ModelBox(gun, 67, 22, -2.5F, -19.0F, -15.0F, 1, 1, 15, 0.0F, true));
        gun.cubeList.add(new ModelBox(gun, 67, 22, 1.5F, -19.0F, 0.0F, 1, 1, 15, 0.0F, false));
        gun.cubeList.add(new ModelBox(gun, 67, 22, 1.5F, -23.0F, 0.0F, 1, 1, 15, 0.0F, false));
        gun.cubeList.add(new ModelBox(gun, 67, 22, -1.0F, -23.0F, 1.0F, 2, 1, 14, 0.0F, false));
        gun.cubeList.add(new ModelBox(gun, 67, 22, -1.0F, -19.0F, 8.0F, 2, 1, 7, 0.0F, false));
        gun.cubeList.add(new ModelBox(gun, 67, 22, -2.5F, -23.0F, 0.0F, 1, 1, 15, 0.0F, true));
        gun.cubeList.add(new ModelBox(gun, 67, 22, 1.5F, -21.5F, 3.0F, 1, 2, 12, 0.0F, false));
        gun.cubeList.add(new ModelBox(gun, 67, 22, -2.5F, -21.5F, 3.0F, 1, 2, 12, 0.0F, true));
        gun.cubeList.add(new ModelBox(gun, 67, 22, -2.5F, -19.0F, 0.0F, 1, 1, 15, 0.0F, true));
        gun.cubeList.add(new ModelBox(gun, 67, 22, 1.5F, -18.0F, -12.0F, 1, 1, 9, 0.0F, false));
        gun.cubeList.add(new ModelBox(gun, 67, 22, -2.5F, -18.0F, -12.0F, 1, 1, 9, 0.0F, true));
        gun.cubeList.add(new ModelBox(gun, 15, 95, 1.7F, -17.5F, -14.5F, 1, 2, 2, 0.0F, false));
        gun.cubeList.add(new ModelBox(gun, 15, 95, 1.7F, -17.5F, 6.5F, 1, 2, 2, 0.0F, false));
        gun.cubeList.add(new ModelBox(gun, 15, 95, -2.7F, -17.5F, -14.5F, 1, 2, 2, 0.0F, true));
        gun.cubeList.add(new ModelBox(gun, 15, 95, -2.7F, -17.5F, 6.5F, 1, 2, 2, 0.0F, true));

        cube_r5 = new ModelRenderer(this);
        cube_r5.setRotationPoint(-1.939F, -19.0695F, 0.0F);
        gun.addChild(cube_r5);
        setRotationAngle(cube_r5, 0.0F, 0.0F, -0.1309F);
        cube_r5.cubeList.add(new ModelBox(cube_r5, 67, 22, -0.5F, -0.5F, 0.0F, 1, 1, 15, 0.0F, true));
        cube_r5.cubeList.add(new ModelBox(cube_r5, 67, 22, -0.5F, -0.5F, -15.0F, 1, 1, 15, 0.0F, true));

        cube_r6 = new ModelRenderer(this);
        cube_r6.setRotationPoint(1.939F, -19.0695F, 0.0F);
        gun.addChild(cube_r6);
        setRotationAngle(cube_r6, 0.0F, 0.0F, 0.1309F);
        cube_r6.cubeList.add(new ModelBox(cube_r6, 67, 22, -0.5F, -0.5F, 0.0F, 1, 1, 15, 0.0F, false));
        cube_r6.cubeList.add(new ModelBox(cube_r6, 67, 22, -0.5F, -0.5F, -15.0F, 1, 1, 15, 0.0F, false));

        cube_r7 = new ModelRenderer(this);
        cube_r7.setRotationPoint(1.4305F, -18.561F, 0.0F);
        gun.addChild(cube_r7);
        setRotationAngle(cube_r7, 0.0F, 0.0F, -0.1309F);
        cube_r7.cubeList.add(new ModelBox(cube_r7, 67, 22, -0.5F, -0.5F, 8.0F, 1, 1, 7, 0.0F, true));

        cube_r8 = new ModelRenderer(this);
        cube_r8.setRotationPoint(1.4305F, -22.439F, -19.0F);
        gun.addChild(cube_r8);
        setRotationAngle(cube_r8, 0.0F, 0.0F, 0.1309F);
        cube_r8.cubeList.add(new ModelBox(cube_r8, 67, 22, -0.5F, -0.5F, 5.0F, 1, 1, 8, 0.0F, true));
        cube_r8.cubeList.add(new ModelBox(cube_r8, 67, 22, -0.5F, -0.5F, 20.0F, 1, 1, 14, 0.0F, true));

        cube_r9 = new ModelRenderer(this);
        cube_r9.setRotationPoint(-1.939F, -21.9305F, 0.0F);
        gun.addChild(cube_r9);
        setRotationAngle(cube_r9, 0.0F, 0.0F, 0.1309F);
        cube_r9.cubeList.add(new ModelBox(cube_r9, 67, 22, -0.5F, -0.5F, 0.0F, 1, 1, 15, 0.0F, true));
        cube_r9.cubeList.add(new ModelBox(cube_r9, 67, 22, -0.5F, -0.5F, -15.0F, 1, 1, 15, 0.0F, true));

        cube_r10 = new ModelRenderer(this);
        cube_r10.setRotationPoint(-1.4305F, -18.561F, 0.0F);
        gun.addChild(cube_r10);
        setRotationAngle(cube_r10, 0.0F, 0.0F, 0.1309F);
        cube_r10.cubeList.add(new ModelBox(cube_r10, 67, 22, -0.5F, -0.5F, 8.0F, 1, 1, 7, 0.0F, false));

        cube_r11 = new ModelRenderer(this);
        cube_r11.setRotationPoint(-1.4305F, -22.439F, -19.0F);
        gun.addChild(cube_r11);
        setRotationAngle(cube_r11, 0.0F, 0.0F, -0.1309F);
        cube_r11.cubeList.add(new ModelBox(cube_r11, 67, 22, -0.5F, -0.5F, 5.0F, 1, 1, 8, 0.0F, false));
        cube_r11.cubeList.add(new ModelBox(cube_r11, 67, 22, -0.5F, -0.5F, 20.0F, 1, 1, 14, 0.0F, false));

        cube_r12 = new ModelRenderer(this);
        cube_r12.setRotationPoint(1.939F, -21.9305F, 0.0F);
        gun.addChild(cube_r12);
        setRotationAngle(cube_r12, 0.0F, 0.0F, -0.1309F);
        cube_r12.cubeList.add(new ModelBox(cube_r12, 67, 22, -0.5F, -0.5F, 0.0F, 1, 1, 15, 0.0F, false));
        cube_r12.cubeList.add(new ModelBox(cube_r12, 67, 22, -0.5F, -0.5F, -15.0F, 1, 1, 15, 0.0F, false));

        cube_r13 = new ModelRenderer(this);
        cube_r13.setRotationPoint(-1.317F, -23.549F, -19.9F);
        gun.addChild(cube_r13);
        setRotationAngle(cube_r13, 0.0F, 0.0F, -0.5236F);
        cube_r13.cubeList.add(new ModelBox(cube_r13, 67, 22, -0.5F, -0.5F, -1.5F, 1, 1, 2, 0.0F, false));
        cube_r13.cubeList.add(new ModelBox(cube_r13, 67, 22, -0.5F, -0.5F, 26.5F, 1, 1, 2, 0.0F, false));

        cube_r14 = new ModelRenderer(this);
        cube_r14.setRotationPoint(1.317F, -23.549F, -19.9F);
        gun.addChild(cube_r14);
        setRotationAngle(cube_r14, 0.0F, 0.0F, 0.5236F);
        cube_r14.cubeList.add(new ModelBox(cube_r14, 67, 22, -0.5F, -0.5F, -1.5F, 1, 1, 2, 0.0F, true));
        cube_r14.cubeList.add(new ModelBox(cube_r14, 67, 22, -0.5F, -0.5F, 26.5F, 1, 1, 2, 0.0F, true));

        cube_r15 = new ModelRenderer(this);
        cube_r15.setRotationPoint(0.0F, -22.4569F, -15.9228F);
        gun.addChild(cube_r15);
        setRotationAngle(cube_r15, -0.0436F, 0.0F, 0.0F);
        cube_r15.cubeList.add(new ModelBox(cube_r15, 102, 17, -1.0F, -0.5F, -1.0F, 2, 1, 2, 0.0F, true));

        cube_r16 = new ModelRenderer(this);
        cube_r16.setRotationPoint(-0.5F, -16.2F, 5.2F);
        gun.addChild(cube_r16);
        setRotationAngle(cube_r16, -0.0873F, 0.0F, 0.0F);
        cube_r16.cubeList.add(new ModelBox(cube_r16, 17, 88, -1.5F, -2.5F, 3.0F, 4, 3, 1, 0.0F, true));

        grip = new ModelRenderer(this);
        grip.setRotationPoint(2.0F, -3.0F, -2.5F);
        gun.addChild(grip);
        grip.cubeList.add(new ModelBox(grip, 39, 99, -4.0F, -6.0F, -2.0F, 1, 6, 5, 0.0F, false));
        grip.cubeList.add(new ModelBox(grip, 39, 99, -1.0F, -6.0F, -2.0F, 1, 6, 5, 0.0F, false));
        grip.cubeList.add(new ModelBox(grip, 39, 99, -4.0F, -13.0F, -2.5F, 1, 1, 6, 0.0F, false));
        grip.cubeList.add(new ModelBox(grip, 39, 99, -4.0F, -12.0F, -1.5F, 1, 6, 4, 0.0F, false));
        grip.cubeList.add(new ModelBox(grip, 39, 99, -1.0F, -12.0F, -1.5F, 1, 6, 4, 0.0F, false));
        grip.cubeList.add(new ModelBox(grip, 39, 99, -1.0F, -13.0F, -2.5F, 1, 1, 6, 0.0F, false));
        grip.cubeList.add(new ModelBox(grip, 39, 99, -3.5F, -4.0F, -2.866F, 3, 4, 1, 0.0F, false));
        grip.cubeList.add(new ModelBox(grip, 39, 99, -3.5F, -6.0F, 2.866F, 3, 6, 1, 0.0F, false));
        grip.cubeList.add(new ModelBox(grip, 39, 99, -3.5F, -11.0F, 2.366F, 3, 4, 1, 0.0F, false));

        cube_r17 = new ModelRenderer(this);
        cube_r17.setRotationPoint(-1.933F, -6.0F, 5.3481F);
        grip.addChild(cube_r17);
        setRotationAngle(cube_r17, 0.0F, -0.5236F, 0.0F);
        cube_r17.cubeList.add(new ModelBox(cube_r17, 39, 99, -0.5F, 0.0F, -3.0F, 1, 6, 1, 0.0F, false));

        cube_r18 = new ModelRenderer(this);
        cube_r18.setRotationPoint(-2.067F, -6.0F, 5.3481F);
        grip.addChild(cube_r18);
        setRotationAngle(cube_r18, 0.0F, 0.5236F, 0.0F);
        cube_r18.cubeList.add(new ModelBox(cube_r18, 39, 99, -0.5F, 0.0F, -3.0F, 1, 6, 1, 0.0F, true));

        cube_r19 = new ModelRenderer(this);
        cube_r19.setRotationPoint(-1.933F, -5.0F, -4.3481F);
        grip.addChild(cube_r19);
        setRotationAngle(cube_r19, 0.0F, 0.5236F, 0.0F);
        cube_r19.cubeList.add(new ModelBox(cube_r19, 39, 99, -0.5F, 1.0F, 2.0F, 1, 4, 1, 0.0F, false));

        cube_r20 = new ModelRenderer(this);
        cube_r20.setRotationPoint(-2.067F, -5.0F, -4.3481F);
        grip.addChild(cube_r20);
        setRotationAngle(cube_r20, 0.0F, -0.5236F, 0.0F);
        cube_r20.cubeList.add(new ModelBox(cube_r20, 39, 99, -0.5F, 1.0F, 2.0F, 1, 4, 1, 0.0F, true));

        cube_r21 = new ModelRenderer(this);
        cube_r21.setRotationPoint(-2.067F, -10.0F, 4.8481F);
        grip.addChild(cube_r21);
        setRotationAngle(cube_r21, 0.0F, 0.5236F, 0.0F);
        cube_r21.cubeList.add(new ModelBox(cube_r21, 39, 99, -0.5F, -1.0F, -3.0F, 1, 4, 1, 0.0F, true));

        cube_r22 = new ModelRenderer(this);
        cube_r22.setRotationPoint(-1.933F, -10.0F, 4.8481F);
        grip.addChild(cube_r22);
        setRotationAngle(cube_r22, 0.0F, -0.5236F, 0.0F);
        cube_r22.cubeList.add(new ModelBox(cube_r22, 39, 99, -0.5F, -1.0F, -3.0F, 1, 4, 1, 0.0F, false));

        bone4 = new ModelRenderer(this);
        bone4.setRotationPoint(-2.0F, -8.9616F, -1.8256F);
        grip.addChild(bone4);
        setRotationAngle(bone4, 0.0524F, 0.0F, 0.0F);
        bone4.cubeList.add(new ModelBox(bone4, 39, 99, 0.999F, -3.1149F, -0.5132F, 1, 10, 1, 0.0F, true));
        bone4.cubeList.add(new ModelBox(bone4, 39, 99, -1.5F, -3.1149F, -1.3793F, 3, 10, 1, 0.0F, false));
        bone4.cubeList.add(new ModelBox(bone4, 39, 99, -1.999F, -3.1149F, -0.5132F, 1, 10, 1, 0.0F, false));

        cube_r23 = new ModelRenderer(this);
        cube_r23.setRotationPoint(-0.067F, 5.968F, -1.3636F);
        bone4.addChild(cube_r23);
        setRotationAngle(cube_r23, 0.0F, -0.5236F, 0.0F);
        cube_r23.cubeList.add(new ModelBox(cube_r23, 39, 99, -1.2489F, -9.0829F, 0.7029F, 1, 10, 1, 0.0F, true));

        cube_r24 = new ModelRenderer(this);
        cube_r24.setRotationPoint(0.067F, 5.968F, -1.3636F);
        bone4.addChild(cube_r24);
        setRotationAngle(cube_r24, 0.0F, 0.5236F, 0.0F);
        cube_r24.cubeList.add(new ModelBox(cube_r24, 39, 99, 0.2489F, -9.0829F, 0.7029F, 1, 10, 1, 0.0F, false));

        bone3 = new ModelRenderer(this);
        bone3.setRotationPoint(-2.0F, -10.0F, 4.8124F);
        grip.addChild(bone3);
        setRotationAngle(bone3, -0.6109F, 0.0F, 0.0F);
        bone3.cubeList.add(new ModelBox(bone3, 39, 99, 0.999F, -1.3823F, -3.3789F, 1, 2, 1, 0.0F, true));
        bone3.cubeList.add(new ModelBox(bone3, 39, 99, -1.5F, -1.3823F, -2.5128F, 3, 2, 1, 0.0F, false));
        bone3.cubeList.add(new ModelBox(bone3, 39, 99, -1.999F, -1.3823F, -3.3789F, 1, 2, 1, 0.0F, false));

        cube_r25 = new ModelRenderer(this);
        cube_r25.setRotationPoint(-0.067F, 1.4706F, 1.6075F);
        bone3.addChild(cube_r25);
        setRotationAngle(cube_r25, 0.0F, 0.5236F, 0.0F);
        cube_r25.cubeList.add(new ModelBox(cube_r25, 39, 99, 0.3192F, -2.8528F, -4.4188F, 1, 2, 1, 0.0F, true));

        cube_r26 = new ModelRenderer(this);
        cube_r26.setRotationPoint(0.067F, 1.4706F, 1.6075F);
        bone3.addChild(cube_r26);
        setRotationAngle(cube_r26, 0.0F, -0.5236F, 0.0F);
        cube_r26.cubeList.add(new ModelBox(cube_r26, 39, 99, -1.3192F, -2.8528F, -4.4188F, 1, 2, 1, 0.0F, false));

        bone2 = new ModelRenderer(this);
        bone2.setRotationPoint(-2.0F, -8.0F, 4.8124F);
        grip.addChild(bone2);
        setRotationAngle(bone2, 0.3054F, 0.0F, 0.0F);
        bone2.cubeList.add(new ModelBox(bone2, 39, 99, 0.999F, -0.3772F, -3.37F, 1, 2, 1, 0.0F, true));
        bone2.cubeList.add(new ModelBox(bone2, 39, 99, -1.5F, -0.3772F, -2.504F, 3, 2, 1, 0.0F, false));
        bone2.cubeList.add(new ModelBox(bone2, 39, 99, -1.999F, -0.3772F, -3.37F, 1, 2, 1, 0.0F, false));

        cube_r27 = new ModelRenderer(this);
        cube_r27.setRotationPoint(-0.067F, -1.7757F, 1.8855F);
        bone2.addChild(cube_r27);
        setRotationAngle(cube_r27, 0.0F, 0.5236F, 0.0F);
        cube_r27.cubeList.add(new ModelBox(cube_r27, 39, 99, 0.4537F, 1.3986F, -4.6519F, 1, 2, 1, 0.0F, true));

        cube_r28 = new ModelRenderer(this);
        cube_r28.setRotationPoint(0.067F, -1.7757F, 1.8855F);
        bone2.addChild(cube_r28);
        setRotationAngle(cube_r28, 0.0F, -0.5236F, 0.0F);
        cube_r28.cubeList.add(new ModelBox(cube_r28, 39, 99, -1.4537F, 1.3986F, -4.6519F, 1, 2, 1, 0.0F, false));

        front = new ModelRenderer(this);
        front.setRotationPoint(-0.451F, -15.683F, -17.5F);
        gun.addChild(front);
        front.cubeList.add(new ModelBox(front, 14, 86, -1.549F, -1.5849F, -4.5F, 4, 2, 7, 0.0F, true));
        front.cubeList.add(new ModelBox(front, 90, 25, -1.549F, -5.317F, -7.5F, 4, 4, 8, 0.0F, true));
        front.cubeList.add(new ModelBox(front, 1, 149, -0.549F, -4.317F, -16.5F, 2, 2, 29, 0.0F, true));
        front.cubeList.add(new ModelBox(front, 15, 95, 2.451F, -3.317F, -4.5F, 1, 2, 7, 0.0F, true));
        front.cubeList.add(new ModelBox(front, 15, 95, 2.451F, -5.317F, -4.5F, 1, 2, 12, 0.0F, true));
        front.cubeList.add(new ModelBox(front, 15, 95, -2.549F, -3.317F, -4.5F, 1, 2, 7, 0.0F, false));
        front.cubeList.add(new ModelBox(front, 15, 95, -2.549F, -5.317F, -4.5F, 1, 2, 12, 0.0F, false));
        front.cubeList.add(new ModelBox(front, 15, 95, -2.049F, -6.183F, -4.5F, 5, 1, 7, 0.0F, false));

        cube_r29 = new ModelRenderer(this);
        cube_r29.setRotationPoint(-0.5F, -5.866F, 0.0F);
        front.addChild(cube_r29);
        setRotationAngle(cube_r29, 0.0F, 0.0F, 0.5236F);
        cube_r29.cubeList.add(new ModelBox(cube_r29, 14, 86, -1.5F, 0.5F, -4.5F, 1, 1, 7, 0.0F, true));

        cube_r30 = new ModelRenderer(this);
        cube_r30.setRotationPoint(-0.5F, -0.7679F, 0.0F);
        front.addChild(cube_r30);
        setRotationAngle(cube_r30, 0.0F, 0.0F, -0.5236F);
        cube_r30.cubeList.add(new ModelBox(cube_r30, 14, 86, -1.5F, -1.5F, -4.5F, 1, 2, 7, 0.0F, true));

        cube_r31 = new ModelRenderer(this);
        cube_r31.setRotationPoint(-0.4001F, -4.817F, 12.1281F);
        front.addChild(cube_r31);
        setRotationAngle(cube_r31, 0.0F, -0.6109F, 0.0F);
        cube_r31.cubeList.add(new ModelBox(cube_r31, 14, 115, -0.5F, -0.5F, -6.0F, 1, 2, 2, 0.0F, true));

        cube_r32 = new ModelRenderer(this);
        cube_r32.setRotationPoint(1.302F, -4.817F, 12.1281F);
        front.addChild(cube_r32);
        setRotationAngle(cube_r32, 0.0F, 0.6109F, 0.0F);
        cube_r32.cubeList.add(new ModelBox(cube_r32, 14, 115, -0.5F, -0.5F, -6.0F, 1, 2, 2, 0.0F, false));

        cube_r33 = new ModelRenderer(this);
        cube_r33.setRotationPoint(1.4019F, -5.866F, 0.0F);
        front.addChild(cube_r33);
        setRotationAngle(cube_r33, 0.0F, 0.0F, -0.5236F);
        cube_r33.cubeList.add(new ModelBox(cube_r33, 14, 86, 0.5F, 0.5F, -4.5F, 1, 1, 7, 0.0F, false));

        cube_r34 = new ModelRenderer(this);
        cube_r34.setRotationPoint(1.4019F, -0.7679F, 0.0F);
        front.addChild(cube_r34);
        setRotationAngle(cube_r34, 0.0F, 0.0F, 0.5236F);
        cube_r34.cubeList.add(new ModelBox(cube_r34, 14, 86, 0.5F, -1.5F, -4.5F, 1, 2, 7, 0.0F, false));

        charger = new ModelRenderer(this);
        charger.setRotationPoint(2.4F, 4.8F, 5.8F);
        charger.cubeList.add(new ModelBox(charger, 30, 99, 0.0F, -3.8F, -11.3F, 1, 1, 1, 0.0F, false));
        charger.cubeList.add(new ModelBox(charger, 30, 99, -5.8F, -3.8F, -11.3F, 1, 1, 1, 0.0F, true));
        charger.cubeList.add(new ModelBox(charger, 93, 40, -1.0F, -4.3F, -11.8F, 1, 2, 2, 0.0F, false));
        charger.cubeList.add(new ModelBox(charger, 93, 40, -4.8F, -4.3F, -11.8F, 1, 2, 2, 0.0F, true));
        charger.cubeList.add(new ModelBox(charger, 95, 35, -1.0F, -3.6F, -9.8F, 1, 1, 3, 0.0F, false));
        charger.cubeList.add(new ModelBox(charger, 95, 35, -4.8F, -3.6F, -9.8F, 1, 1, 3, 0.0F, true));
        charger.cubeList.add(new ModelBox(charger, 95, 35, -1.0F, -4.0F, -9.8F, 1, 1, 3, 0.0F, false));
        charger.cubeList.add(new ModelBox(charger, 95, 35, -4.8F, -4.0F, -9.8F, 1, 1, 3, 0.0F, true));
        charger.cubeList.add(new ModelBox(charger, 95, 35, -3.9F, -4.0F, -7.1F, 3, 1, 1, 0.0F, true));
        charger.cubeList.add(new ModelBox(charger, 31, 88, -3.5F, -3.16F, -9.3F, 2, 1, 3, 0.0F, true));
        charger.cubeList.add(new ModelBox(charger, 0, 22, -3.9F, -5.4F, -11.7F, 3, 1, 7, 0.0F, false));

        cube_r35 = new ModelRenderer(this);
        cube_r35.setRotationPoint(-3.5003F, 0.2F, -3.2292F);
        charger.addChild(cube_r35);
        setRotationAngle(cube_r35, 0.0F, 0.3491F, 0.0F);
        cube_r35.cubeList.add(new ModelBox(cube_r35, 95, 35, 0.0F, -4.0F, -3.8F, 1, 1, 1, 0.0F, true));

        cube_r36 = new ModelRenderer(this);
        cube_r36.setRotationPoint(-1.2997F, 0.2F, -3.2292F);
        charger.addChild(cube_r36);
        setRotationAngle(cube_r36, 0.0F, -0.3491F, 0.0F);
        cube_r36.cubeList.add(new ModelBox(cube_r36, 95, 35, -1.0F, -4.0F, -3.8F, 1, 1, 1, 0.0F, false));

        cube_r37 = new ModelRenderer(this);
        cube_r37.setRotationPoint(-6.7F, -3.3F, -10.8F);
        charger.addChild(cube_r37);
        setRotationAngle(cube_r37, -0.829F, 0.0F, 0.0F);
        cube_r37.cubeList.add(new ModelBox(cube_r37, 28, 102, -1.5F, -1.0F, -1.0F, 3, 2, 2, 0.0F, true));
        cube_r37.cubeList.add(new ModelBox(cube_r37, 28, 102, 7.1F, -1.0F, -1.0F, 3, 2, 2, 0.0F, false));

        stock = new ModelRenderer(this);
        stock.setRotationPoint(0.0F, 24.0F, 0.0F);
        stock.cubeList.add(new ModelBox(stock, 96, 35, -1.0F, -20.5F, 13.0F, 2, 3, 2, 0.0F, false));

        stock9 = new ModelRenderer(this);
        stock9.setRotationPoint(0.0F, -18.5F, 14.0F);
        stock.addChild(stock9);
        setRotationAngle(stock9, -2.8798F, 0.0F, 0.0F);
        stock9.cubeList.add(new ModelBox(stock9, 39, 20, -2.0F, -0.5F, -0.5F, 4, 1, 1, 0.0F, false));
        stock9.cubeList.add(new ModelBox(stock9, 39, 20, -2.0F, -0.5F, -4.5F, 1, 1, 4, 0.0F, false));
        stock9.cubeList.add(new ModelBox(stock9, 39, 20, 1.0F, -0.5F, -4.5F, 1, 1, 4, 0.0F, false));

        stock10 = new ModelRenderer(this);
        stock10.setRotationPoint(0.0F, 0.0F, -4.0F);
        stock9.addChild(stock10);
        setRotationAngle(stock10, 0.2618F, 0.0F, 0.0F);
        stock10.cubeList.add(new ModelBox(stock10, 39, 20, -1.0F, -0.5F, -0.5F, 2, 3, 1, 0.0F, false));

        stock11 = new ModelRenderer(this);
        stock11.setRotationPoint(0.0F, 2.0F, 0.0F);
        stock10.addChild(stock11);
        setRotationAngle(stock11, -0.6109F, 0.0F, 0.0F);
        stock11.cubeList.add(new ModelBox(stock11, 39, 20, -2.0F, -0.5F, -5.5F, 1, 1, 6, 0.0F, false));
        stock11.cubeList.add(new ModelBox(stock11, 39, 20, 1.0F, -0.5F, -5.5F, 1, 1, 6, 0.0F, false));

        stock12 = new ModelRenderer(this);
        stock12.setRotationPoint(0.0F, 0.0F, -5.0F);
        stock11.addChild(stock12);
        setRotationAngle(stock12, -1.5708F, 0.0F, 0.0F);
        stock12.cubeList.add(new ModelBox(stock12, 39, 20, -1.0F, -0.5F, -0.5F, 2, 3, 1, 0.0F, false));

        stock13 = new ModelRenderer(this);
        stock13.setRotationPoint(0.0F, 2.0F, 0.0F);
        stock12.addChild(stock13);
        setRotationAngle(stock13, -1.4835F, 0.0F, 0.0F);
        stock13.cubeList.add(new ModelBox(stock13, 21, 22, 1.0F, -0.5F, -0.5F, 1, 1, 12, 0.0F, false));
        stock13.cubeList.add(new ModelBox(stock13, 21, 22, -2.0F, -0.5F, -0.5F, 1, 1, 12, 0.0F, false));

        stock14 = new ModelRenderer(this);
        stock14.setRotationPoint(0.0F, 0.0F, 11.0F);
        stock13.addChild(stock14);
        setRotationAngle(stock14, -1.0036F, 0.0F, 0.0F);
        stock14.cubeList.add(new ModelBox(stock14, 21, 22, -1.0F, -2.5F, -0.5F, 2, 3, 1, 0.0F, false));

        bone5 = new ModelRenderer(this);
        bone5.setRotationPoint(0.0F, -2.0F, 0.0F);
        stock14.addChild(bone5);
        setRotationAngle(bone5, 2.5744F, 0.0F, 0.0F);
        bone5.cubeList.add(new ModelBox(bone5, 21, 22, 1.0F, -0.5F, -4.5F, 1, 1, 5, 0.0F, false));
        bone5.cubeList.add(new ModelBox(bone5, 21, 22, -2.0F, -0.5F, -4.5F, 1, 1, 5, 0.0F, false));
        bone5.cubeList.add(new ModelBox(bone5, 21, 22, -1.0F, -0.5F, -4.5F, 2, 1, 1, 0.0F, false));

        toprail = new ModelRenderer(this);
        toprail.setRotationPoint(0.0F, 24.0F, 0.0F);
        toprail.cubeList.add(new ModelBox(toprail, 90, 83, -1.0F, -26.0F, -14.0F, 2, 1, 2, 0.0F, false));
        toprail.cubeList.add(new ModelBox(toprail, 90, 83, -1.0F, -26.0F, 2.0F, 2, 1, 2, 0.0F, false));
        toprail.cubeList.add(new ModelBox(toprail, 90, 83, -1.0F, -26.0F, -9.0F, 2, 1, 2, 0.0F, false));
        toprail.cubeList.add(new ModelBox(toprail, 77, 82, -1.5F, -27.0F, -14.5F, 3, 1, 9, 0.0F, false));
        toprail.cubeList.add(new ModelBox(toprail, 77, 82, -1.5F, -27.0F, 0.5F, 3, 1, 4, 0.0F, false));
        toprail.cubeList.add(new ModelBox(toprail, 77, 82, 0.5F, -27.0F, -5.5F, 1, 1, 6, 0.0F, false));
        toprail.cubeList.add(new ModelBox(toprail, 90, 83, -0.134F, -27.5F, -5.5F, 2, 0, 1, 0.0F, false));
        toprail.cubeList.add(new ModelBox(toprail, 90, 83, -0.134F, -27.5F, -1.5F, 2, 0, 1, 0.0F, false));
        toprail.cubeList.add(new ModelBox(toprail, 90, 83, -0.134F, -27.5F, 2.5F, 2, 0, 1, 0.0F, false));
        toprail.cubeList.add(new ModelBox(toprail, 90, 83, -1.866F, -27.5F, -5.5F, 2, 0, 1, 0.0F, true));
        toprail.cubeList.add(new ModelBox(toprail, 90, 83, -1.866F, -27.5F, -1.5F, 2, 0, 1, 0.0F, true));
        toprail.cubeList.add(new ModelBox(toprail, 90, 83, -1.866F, -27.5F, 2.5F, 2, 0, 1, 0.0F, true));
        toprail.cubeList.add(new ModelBox(toprail, 90, 83, -1.0F, -28.0F, -10.5F, 2, 1, 1, 0.0F, false));
        toprail.cubeList.add(new ModelBox(toprail, 90, 83, -0.134F, -27.5F, -11.5F, 2, 0, 1, 0.0F, false));
        toprail.cubeList.add(new ModelBox(toprail, 90, 83, -1.866F, -27.5F, -11.5F, 2, 0, 1, 0.0F, true));
        toprail.cubeList.add(new ModelBox(toprail, 90, 83, -1.0F, -28.0F, -6.5F, 2, 1, 1, 0.0F, false));
        toprail.cubeList.add(new ModelBox(toprail, 90, 83, -1.0F, -28.0F, -2.5F, 2, 1, 1, 0.0F, false));
        toprail.cubeList.add(new ModelBox(toprail, 90, 83, -1.0F, -28.0F, 1.5F, 2, 1, 1, 0.0F, false));
        toprail.cubeList.add(new ModelBox(toprail, 90, 83, -1.0F, -28.0F, -4.5F, 2, 1, 1, 0.0F, false));
        toprail.cubeList.add(new ModelBox(toprail, 90, 83, -1.0F, -28.0F, -0.5F, 2, 1, 1, 0.0F, false));
        toprail.cubeList.add(new ModelBox(toprail, 90, 83, -1.0F, -28.0F, 3.5F, 2, 1, 1, 0.0F, false));
        toprail.cubeList.add(new ModelBox(toprail, 90, 83, -0.134F, -27.5F, -7.5F, 2, 0, 1, 0.0F, false));
        toprail.cubeList.add(new ModelBox(toprail, 90, 83, -0.134F, -27.5F, -3.5F, 2, 0, 1, 0.0F, false));
        toprail.cubeList.add(new ModelBox(toprail, 90, 83, -0.134F, -27.5F, 0.5F, 2, 0, 1, 0.0F, false));
        toprail.cubeList.add(new ModelBox(toprail, 90, 83, -1.866F, -27.5F, -7.5F, 2, 0, 1, 0.0F, true));
        toprail.cubeList.add(new ModelBox(toprail, 90, 83, -1.866F, -27.5F, -3.5F, 2, 0, 1, 0.0F, true));
        toprail.cubeList.add(new ModelBox(toprail, 90, 83, -1.866F, -27.5F, 0.5F, 2, 0, 1, 0.0F, true));
        toprail.cubeList.add(new ModelBox(toprail, 90, 83, -1.0F, -28.0F, -12.5F, 2, 1, 1, 0.0F, false));
        toprail.cubeList.add(new ModelBox(toprail, 90, 83, -0.134F, -27.5F, -13.5F, 2, 0, 1, 0.0F, false));
        toprail.cubeList.add(new ModelBox(toprail, 90, 83, -1.866F, -27.5F, -13.5F, 2, 0, 1, 0.0F, true));
        toprail.cubeList.add(new ModelBox(toprail, 90, 83, -1.0F, -28.0F, -8.5F, 2, 1, 1, 0.0F, false));
        toprail.cubeList.add(new ModelBox(toprail, 90, 83, -0.134F, -27.5F, -9.5F, 2, 0, 1, 0.0F, false));
        toprail.cubeList.add(new ModelBox(toprail, 90, 83, -1.866F, -27.5F, -9.5F, 2, 0, 1, 0.0F, true));
        toprail.cubeList.add(new ModelBox(toprail, 90, 83, -1.0F, -28.0F, -14.5F, 2, 1, 1, 0.0F, false));

        cube_r38 = new ModelRenderer(this);
        cube_r38.setRotationPoint(-0.262F, -26.8101F, -2.5F);
        toprail.addChild(cube_r38);
        setRotationAngle(cube_r38, 0.0F, 0.0F, 0.3054F);
        cube_r38.cubeList.add(new ModelBox(cube_r38, 77, 82, -1.0F, -0.5F, -3.0F, 2, 1, 6, 0.0F, false));

        bone = new ModelRenderer(this);
        bone.setRotationPoint(0.0F, -27.5F, -9.0F);
        toprail.addChild(bone);
        setRotationAngle(bone, 0.0F, 0.0F, 0.5236F);
        bone.cubeList.add(new ModelBox(bone, 90, 83, 0.616F, -0.933F, -5.5F, 1, 1, 1, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 90, 83, 0.616F, -0.933F, 4.5F, 1, 1, 1, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 90, 83, 0.616F, -0.933F, -3.5F, 1, 1, 1, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 90, 83, 0.616F, -0.933F, 6.5F, 1, 1, 1, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 90, 83, 1.616F, -0.933F, -4.5F, 0, 1, 1, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 90, 83, 1.616F, -0.933F, 5.5F, 0, 1, 1, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 90, 83, 0.616F, -0.933F, -1.5F, 1, 1, 1, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 90, 83, 0.616F, -0.933F, 8.5F, 1, 1, 1, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 90, 83, 1.616F, -0.933F, -2.5F, 0, 1, 1, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 90, 83, 1.616F, -0.933F, 7.5F, 0, 1, 1, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 90, 83, 0.616F, -0.933F, 0.5F, 1, 1, 1, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 90, 83, 0.616F, -0.933F, 10.5F, 1, 1, 1, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 90, 83, 1.616F, -0.933F, -0.5F, 0, 1, 1, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 90, 83, 1.616F, -0.933F, 9.5F, 0, 1, 1, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 90, 83, 0.616F, -0.933F, 2.5F, 1, 1, 1, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 90, 83, 0.616F, -0.933F, 12.5F, 1, 1, 1, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 90, 83, 1.616F, -0.933F, 1.5F, 0, 1, 1, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 90, 83, 1.616F, -0.933F, 11.5F, 0, 1, 1, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 90, 83, 1.616F, -0.933F, 3.5F, 0, 1, 1, 0.0F, false));

        bone6 = new ModelRenderer(this);
        bone6.setRotationPoint(0.0F, -27.5F, -9.0F);
        toprail.addChild(bone6);
        setRotationAngle(bone6, 0.0F, 0.0F, -0.5236F);
        bone6.cubeList.add(new ModelBox(bone6, 90, 83, -1.616F, -0.933F, -5.5F, 1, 1, 1, 0.0F, true));
        bone6.cubeList.add(new ModelBox(bone6, 90, 83, -1.616F, -0.933F, 4.5F, 1, 1, 1, 0.0F, true));
        bone6.cubeList.add(new ModelBox(bone6, 90, 83, -1.616F, -0.933F, -3.5F, 1, 1, 1, 0.0F, true));
        bone6.cubeList.add(new ModelBox(bone6, 90, 83, -1.616F, -0.933F, 6.5F, 1, 1, 1, 0.0F, true));
        bone6.cubeList.add(new ModelBox(bone6, 90, 83, -1.616F, -0.933F, -4.5F, 0, 1, 1, 0.0F, true));
        bone6.cubeList.add(new ModelBox(bone6, 90, 83, -1.616F, -0.933F, 5.5F, 0, 1, 1, 0.0F, true));
        bone6.cubeList.add(new ModelBox(bone6, 90, 83, -1.616F, -0.933F, -1.5F, 1, 1, 1, 0.0F, true));
        bone6.cubeList.add(new ModelBox(bone6, 90, 83, -1.616F, -0.933F, 8.5F, 1, 1, 1, 0.0F, true));
        bone6.cubeList.add(new ModelBox(bone6, 90, 83, -1.616F, -0.933F, -2.5F, 0, 1, 1, 0.0F, true));
        bone6.cubeList.add(new ModelBox(bone6, 90, 83, -1.616F, -0.933F, 7.5F, 0, 1, 1, 0.0F, true));
        bone6.cubeList.add(new ModelBox(bone6, 90, 83, -1.616F, -0.933F, 0.5F, 1, 1, 1, 0.0F, true));
        bone6.cubeList.add(new ModelBox(bone6, 90, 83, -1.616F, -0.933F, 10.5F, 1, 1, 1, 0.0F, true));
        bone6.cubeList.add(new ModelBox(bone6, 90, 83, -1.616F, -0.933F, -0.5F, 0, 1, 1, 0.0F, true));
        bone6.cubeList.add(new ModelBox(bone6, 90, 83, -1.616F, -0.933F, 9.5F, 0, 1, 1, 0.0F, true));
        bone6.cubeList.add(new ModelBox(bone6, 90, 83, -1.616F, -0.933F, 2.5F, 1, 1, 1, 0.0F, true));
        bone6.cubeList.add(new ModelBox(bone6, 90, 83, -1.616F, -0.933F, 12.5F, 1, 1, 1, 0.0F, true));
        bone6.cubeList.add(new ModelBox(bone6, 90, 83, -1.616F, -0.933F, 1.5F, 0, 1, 1, 0.0F, true));
        bone6.cubeList.add(new ModelBox(bone6, 90, 83, -1.616F, -0.933F, 11.5F, 0, 1, 1, 0.0F, true));
        bone6.cubeList.add(new ModelBox(bone6, 90, 83, -1.616F, -0.933F, 4.5F, 1, 1, 1, 0.0F, true));
        bone6.cubeList.add(new ModelBox(bone6, 90, 83, -1.616F, -0.933F, 3.5F, 0, 1, 1, 0.0F, true));

        botrail = new ModelRenderer(this);
        botrail.setRotationPoint(0.0F, -19.0F, -6.0F);
        botrail.cubeList.add(new ModelBox(botrail, 90, 83, -1.0F, 25.0F, -14.0F, 2, 1, 2, 0.0F, false));
        botrail.cubeList.add(new ModelBox(botrail, 90, 83, -1.5F, 26.0F, -14.5F, 3, 1, 11, 0.0F, false));
        botrail.cubeList.add(new ModelBox(botrail, 90, 83, -1.0F, 27.0F, -4.5F, 2, 1, 1, 0.0F, false));
        botrail.cubeList.add(new ModelBox(botrail, 90, 83, -0.134F, 27.5F, -5.5F, 2, 0, 1, 0.0F, false));
        botrail.cubeList.add(new ModelBox(botrail, 90, 83, -1.866F, 27.5F, -5.5F, 2, 0, 1, 0.0F, true));
        botrail.cubeList.add(new ModelBox(botrail, 90, 83, -1.0F, 27.0F, -10.5F, 2, 1, 1, 0.0F, false));
        botrail.cubeList.add(new ModelBox(botrail, 90, 83, -0.134F, 27.5F, -11.5F, 2, 0, 1, 0.0F, false));
        botrail.cubeList.add(new ModelBox(botrail, 90, 83, -1.866F, 27.5F, -11.5F, 2, 0, 1, 0.0F, true));
        botrail.cubeList.add(new ModelBox(botrail, 90, 83, -1.0F, 27.0F, -6.5F, 2, 1, 1, 0.0F, false));
        botrail.cubeList.add(new ModelBox(botrail, 90, 83, -0.134F, 27.5F, -7.5F, 2, 0, 1, 0.0F, false));
        botrail.cubeList.add(new ModelBox(botrail, 90, 83, -1.866F, 27.5F, -7.5F, 2, 0, 1, 0.0F, true));
        botrail.cubeList.add(new ModelBox(botrail, 90, 83, -1.0F, 27.0F, -12.5F, 2, 1, 1, 0.0F, false));
        botrail.cubeList.add(new ModelBox(botrail, 90, 83, -0.134F, 27.5F, -13.5F, 2, 0, 1, 0.0F, false));
        botrail.cubeList.add(new ModelBox(botrail, 90, 83, -1.866F, 27.5F, -13.5F, 2, 0, 1, 0.0F, true));
        botrail.cubeList.add(new ModelBox(botrail, 90, 83, -1.0F, 27.0F, -8.5F, 2, 1, 1, 0.0F, false));
        botrail.cubeList.add(new ModelBox(botrail, 90, 83, -0.134F, 27.5F, -9.5F, 2, 0, 1, 0.0F, false));
        botrail.cubeList.add(new ModelBox(botrail, 90, 83, -1.866F, 27.5F, -9.5F, 2, 0, 1, 0.0F, true));
        botrail.cubeList.add(new ModelBox(botrail, 90, 83, -1.0F, 27.0F, -14.5F, 2, 1, 1, 0.0F, false));

        bone7 = new ModelRenderer(this);
        bone7.setRotationPoint(0.0F, 27.5F, -9.0F);
        botrail.addChild(bone7);
        setRotationAngle(bone7, 0.0F, 0.0F, -0.5236F);
        bone7.cubeList.add(new ModelBox(bone7, 90, 83, 0.616F, -0.067F, -5.5F, 1, 1, 1, 0.0F, false));
        bone7.cubeList.add(new ModelBox(bone7, 90, 83, 0.616F, -0.067F, -3.5F, 1, 1, 1, 0.0F, false));
        bone7.cubeList.add(new ModelBox(bone7, 90, 83, 1.616F, -0.067F, -4.5F, 0, 1, 1, 0.0F, false));
        bone7.cubeList.add(new ModelBox(bone7, 90, 83, 0.616F, -0.067F, -1.5F, 1, 1, 1, 0.0F, false));
        bone7.cubeList.add(new ModelBox(bone7, 90, 83, 1.616F, -0.067F, -2.5F, 0, 1, 1, 0.0F, false));
        bone7.cubeList.add(new ModelBox(bone7, 90, 83, 0.616F, -0.067F, 0.5F, 1, 1, 1, 0.0F, false));
        bone7.cubeList.add(new ModelBox(bone7, 90, 83, 1.616F, -0.067F, -0.5F, 0, 1, 1, 0.0F, false));
        bone7.cubeList.add(new ModelBox(bone7, 90, 83, 0.616F, -0.067F, 2.5F, 1, 1, 1, 0.0F, false));
        bone7.cubeList.add(new ModelBox(bone7, 90, 83, 1.616F, -0.067F, 1.5F, 0, 1, 1, 0.0F, false));
        bone7.cubeList.add(new ModelBox(bone7, 90, 83, 0.616F, -0.067F, 4.5F, 1, 1, 1, 0.0F, false));
        bone7.cubeList.add(new ModelBox(bone7, 90, 83, 1.616F, -0.067F, 3.5F, 0, 1, 1, 0.0F, false));

        bone8 = new ModelRenderer(this);
        bone8.setRotationPoint(0.0F, 27.5F, -9.0F);
        botrail.addChild(bone8);
        setRotationAngle(bone8, 0.0F, 0.0F, 0.5236F);
        bone8.cubeList.add(new ModelBox(bone8, 90, 83, -1.616F, -0.067F, -5.5F, 1, 1, 1, 0.0F, true));
        bone8.cubeList.add(new ModelBox(bone8, 90, 83, -1.616F, -0.067F, -3.5F, 1, 1, 1, 0.0F, true));
        bone8.cubeList.add(new ModelBox(bone8, 90, 83, -1.616F, -0.067F, -4.5F, 0, 1, 1, 0.0F, true));
        bone8.cubeList.add(new ModelBox(bone8, 90, 83, -1.616F, -0.067F, -1.5F, 1, 1, 1, 0.0F, true));
        bone8.cubeList.add(new ModelBox(bone8, 90, 83, -1.616F, -0.067F, -2.5F, 0, 1, 1, 0.0F, true));
        bone8.cubeList.add(new ModelBox(bone8, 90, 83, -1.616F, -0.067F, 0.5F, 1, 1, 1, 0.0F, true));
        bone8.cubeList.add(new ModelBox(bone8, 90, 83, -1.616F, -0.067F, -0.5F, 0, 1, 1, 0.0F, true));
        bone8.cubeList.add(new ModelBox(bone8, 90, 83, -1.616F, -0.067F, 2.5F, 1, 1, 1, 0.0F, true));
        bone8.cubeList.add(new ModelBox(bone8, 90, 83, -1.616F, -0.067F, 1.5F, 0, 1, 1, 0.0F, true));
        bone8.cubeList.add(new ModelBox(bone8, 90, 83, -1.616F, -0.067F, 4.5F, 1, 1, 1, 0.0F, true));
        bone8.cubeList.add(new ModelBox(bone8, 90, 83, -1.616F, -0.067F, 3.5F, 0, 1, 1, 0.0F, true));
        this.initAnimations();
    }

    public void setRotationAngle(ModelRenderer r, float x, float y, float z) {
        r.rotateAngleX = x;
        r.rotateAngleY = y;
        r.rotateAngleZ = z;
    }
}

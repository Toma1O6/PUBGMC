package dev.toma.pubgmc.client.models.weapons;

import dev.toma.pubgmc.animation.ReloadAnimation;
import dev.toma.pubgmc.animation.ReloadAnimation.ReloadStyle;
import dev.toma.pubgmc.client.util.ModelTransformationHelper;
import dev.toma.pubgmc.common.capability.player.PlayerDataProvider;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.item.ItemStack;

public class ModelSLR extends ModelGun {
    private final ModelRenderer ironsights;
    private final ModelRenderer rearsight;
    private final ModelRenderer cube_r43;
    private final ModelRenderer cube_r44;
    private final ModelRenderer cube_r45;
    private final ModelRenderer cube_r46;
    private final ModelRenderer cube_r47;
    private final ModelRenderer frontsight;
    private final ModelRenderer receiver_r1;
    private final ModelRenderer receiver_r2;
    private final ModelRenderer receiver_r3;
    private final ModelRenderer receiver_r4;
    private final ModelRenderer slr;
    private final ModelRenderer receiver;
    private final ModelRenderer receiver_r5;
    private final ModelRenderer receiver_r6;
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
    private final ModelRenderer bone;
    private final ModelRenderer cube_r27;
    private final ModelRenderer cube_r28;
    private final ModelRenderer cube_r29;
    private final ModelRenderer cube_r30;
    private final ModelRenderer cube_r31;
    private final ModelRenderer bone2;
    private final ModelRenderer cube_r32;
    private final ModelRenderer cube_r33;
    private final ModelRenderer cube_r34;
    private final ModelRenderer cube_r35;
    private final ModelRenderer rail;
    private final ModelRenderer cube_r36;
    private final ModelRenderer cube_r37;
    private final ModelRenderer charger;
    private final ModelRenderer cube_r38;
    private final ModelRenderer cube_r39;
    private final ModelRenderer cube_r40;
    private final ModelRenderer cube_r41;
    private final ModelRenderer cube_r42;
    private final ModelRenderer barrel;
    private final ModelRenderer cube_r48;
    private final ModelRenderer cube_r49;
    private final ModelRenderer cube_r50;
    private final ModelRenderer cube_r51;
    private final ModelRenderer cube_r52;
    private final ModelRenderer cube_r53;
    private final ModelRenderer cube_r54;
    private final ModelRenderer cube_r55;
    private final ModelRenderer magazine;
    private final ModelRenderer cube_r56;
    private final ModelRenderer cube_r57;
    private final ModelRenderer bullet;
    private final ModelRenderer toprail;
    private final ModelRenderer cube_r58;
    private final ModelRenderer cube_r59;

    public ModelSLR() {
        textureWidth = 512;
        textureHeight = 512;

        ironsights = new ModelRenderer(this);
        ironsights.setRotationPoint(0.0F, 24.0F, 0.0F);


        rearsight = new ModelRenderer(this);
        rearsight.setRotationPoint(0.0F, 0.0F, 0.0F);
        ironsights.addChild(rearsight);
        rearsight.cubeList.add(new ModelBox(rearsight, 30, 165, -0.5F, -13.3765F, 16.7918F, 1, 1, 1, 0.0F, false));
        rearsight.cubeList.add(new ModelBox(rearsight, 30, 165, -1.5F, -14.3765F, 16.7918F, 3, 1, 1, 0.0F, false));
        rearsight.cubeList.add(new ModelBox(rearsight, 30, 165, 0.9617F, -16.2636F, 16.7918F, 1, 2, 1, 0.0F, false));
        rearsight.cubeList.add(new ModelBox(rearsight, 30, 165, -1.9617F, -16.2636F, 16.7918F, 1, 2, 1, 0.0F, true));
        rearsight.cubeList.add(new ModelBox(rearsight, 30, 165, -1.5F, -17.1506F, 16.7918F, 3, 1, 1, 0.0F, false));

        cube_r43 = new ModelRenderer(this);
        cube_r43.setRotationPoint(-1.2874F, -14.0509F, 17.2918F);
        rearsight.addChild(cube_r43);
        setRotationAngle(cube_r43, 0.0F, 0.0F, -0.48F);
        cube_r43.cubeList.add(new ModelBox(cube_r43, 30, 165, -0.5F, -0.5F, -0.5F, 1, 1, 1, 0.0F, false));

        cube_r44 = new ModelRenderer(this);
        cube_r44.setRotationPoint(-1.2874F, -16.4762F, 17.2918F);
        rearsight.addChild(cube_r44);
        setRotationAngle(cube_r44, 0.0F, 0.0F, 0.48F);
        cube_r44.cubeList.add(new ModelBox(cube_r44, 30, 165, -0.5F, -0.5F, -0.5F, 1, 1, 1, 0.0F, false));

        cube_r45 = new ModelRenderer(this);
        cube_r45.setRotationPoint(1.2874F, -14.0509F, 17.2918F);
        rearsight.addChild(cube_r45);
        setRotationAngle(cube_r45, 0.0F, 0.0F, 0.48F);
        cube_r45.cubeList.add(new ModelBox(cube_r45, 30, 165, -0.5F, -0.5F, -0.5F, 1, 1, 1, 0.0F, true));

        cube_r46 = new ModelRenderer(this);
        cube_r46.setRotationPoint(1.2874F, -16.4762F, 17.2918F);
        rearsight.addChild(cube_r46);
        setRotationAngle(cube_r46, 0.0F, 0.0F, -0.48F);
        cube_r46.cubeList.add(new ModelBox(cube_r46, 30, 165, -0.5F, -0.5F, -0.5F, 1, 1, 1, 0.0F, true));

        cube_r47 = new ModelRenderer(this);
        cube_r47.setRotationPoint(2.5F, 1.2862F, -1.8806F);
        rearsight.addChild(cube_r47);
        setRotationAngle(cube_r47, -0.2618F, 0.0F, 0.0F);
        cube_r47.cubeList.add(new ModelBox(cube_r47, 0, 0, -4.5F, -18.03F, 14.5F, 4, 1, 4, 0.0F, false));
        cube_r47.cubeList.add(new ModelBox(cube_r47, 32, 14, -3.0F, -19.03F, 15.5F, 1, 1, 3, 0.0F, false));
        cube_r47.cubeList.add(new ModelBox(cube_r47, 8, 8, -4.0F, -19.03F, 14.5F, 1, 1, 4, 0.0F, false));
        cube_r47.cubeList.add(new ModelBox(cube_r47, 17, 20, -2.0F, -19.03F, 14.5F, 1, 1, 4, 0.0F, false));

        frontsight = new ModelRenderer(this);
        frontsight.setRotationPoint(0.7929F, -9.6F, -30.5F);
        ironsights.addChild(frontsight);
        frontsight.cubeList.add(new ModelBox(frontsight, 15, 94, 0.8911F, -6.6723F, -11.0F, 1, 2, 2, 0.0F, true));
        frontsight.cubeList.add(new ModelBox(frontsight, 15, 94, -2.7929F, -8.5517F, -11.0F, 4, 1, 2, 0.0F, false));
        frontsight.cubeList.add(new ModelBox(frontsight, 15, 94, -3.4769F, -6.6723F, -11.0F, 1, 2, 2, 0.0F, false));
        frontsight.cubeList.add(new ModelBox(frontsight, 15, 94, -2.7929F, -3.7929F, -11.0F, 4, 1, 2, 0.0F, false));
        frontsight.cubeList.add(new ModelBox(frontsight, 15, 94, -1.2929F, -4.7929F, -10.5F, 1, 1, 1, 0.0F, false));

        receiver_r1 = new ModelRenderer(this);
        receiver_r1.setRotationPoint(-0.5013F, -3.9468F, -10.0F);
        frontsight.addChild(receiver_r1);
        setRotationAngle(receiver_r1, 0.0F, 0.0F, 0.3491F);
        receiver_r1.cubeList.add(new ModelBox(receiver_r1, 15, 94, 1.0F, -1.5F, -1.0F, 1, 2, 2, 0.0F, false));

        receiver_r2 = new ModelRenderer(this);
        receiver_r2.setRotationPoint(-1.0845F, -3.9468F, -10.0F);
        frontsight.addChild(receiver_r2);
        setRotationAngle(receiver_r2, 0.0F, 0.0F, -0.3491F);
        receiver_r2.cubeList.add(new ModelBox(receiver_r2, 15, 94, -2.0F, -1.5F, -1.0F, 1, 2, 2, 0.0F, true));

        receiver_r3 = new ModelRenderer(this);
        receiver_r3.setRotationPoint(-1.0845F, -7.3978F, -10.0F);
        frontsight.addChild(receiver_r3);
        setRotationAngle(receiver_r3, 0.0F, 0.0F, 0.3491F);
        receiver_r3.cubeList.add(new ModelBox(receiver_r3, 15, 94, -2.0F, -0.5F, -1.0F, 1, 2, 2, 0.0F, true));

        receiver_r4 = new ModelRenderer(this);
        receiver_r4.setRotationPoint(-0.5013F, -7.3978F, -10.0F);
        frontsight.addChild(receiver_r4);
        setRotationAngle(receiver_r4, 0.0F, 0.0F, -0.3491F);
        receiver_r4.cubeList.add(new ModelBox(receiver_r4, 15, 94, 1.0F, -0.5F, -1.0F, 1, 2, 2, 0.0F, false));

        slr = new ModelRenderer(this);
        slr.setRotationPoint(-1.0F, 15.4142F, -7.0F);


        receiver = new ModelRenderer(this);
        receiver.setRotationPoint(0.0F, 0.0F, 0.0F);
        slr.addChild(receiver);
        receiver.cubeList.add(new ModelBox(receiver, 1, 81, 0.0F, -1.9142F, 0.0F, 2, 1, 24, 0.0F, false));
        receiver.cubeList.add(new ModelBox(receiver, 1, 81, 0.0F, -1.9142F, -2.5F, 2, 1, 2, 0.0F, false));
        receiver.cubeList.add(new ModelBox(receiver, 1, 81, 0.0F, 2.0858F, -2.5F, 2, 1, 4, 0.0F, false));
        receiver.cubeList.add(new ModelBox(receiver, 4, 143, 0.0F, 1.4858F, -29.5F, 2, 1, 27, 0.0F, false));
        receiver.cubeList.add(new ModelBox(receiver, 4, 143, 0.0F, 1.4858F, -56.5F, 2, 1, 27, 0.0F, false));
        receiver.cubeList.add(new ModelBox(receiver, 1, 81, 0.5F, 1.2858F, -2.0F, 1, 1, 1, 0.0F, false));
        receiver.cubeList.add(new ModelBox(receiver, 1, 81, 0.0F, 0.5F, -2.5F, 2, 1, 4, 0.0F, false));
        receiver.cubeList.add(new ModelBox(receiver, 1, 81, 0.0F, 4.5F, -2.5F, 2, 1, 4, 0.0F, false));
        receiver.cubeList.add(new ModelBox(receiver, 4, 143, 0.0F, 3.9F, -29.5F, 2, 1, 27, 0.0F, false));
        receiver.cubeList.add(new ModelBox(receiver, 4, 143, 0.0F, 3.9F, -56.5F, 2, 1, 27, 0.0F, false));
        receiver.cubeList.add(new ModelBox(receiver, 1, 81, -0.5F, -2.4142F, -1.0F, 3, 1, 1, 0.0F, false));
        receiver.cubeList.add(new ModelBox(receiver, 1, 81, -0.5F, -2.4142F, 23.0F, 3, 1, 2, 0.0F, false));
        receiver.cubeList.add(new ModelBox(receiver, 1, 81, 3.2101F, 2.2842F, -1.0F, 1, 3, 1, 0.0F, false));
        receiver.cubeList.add(new ModelBox(receiver, 1, 81, -2.2101F, 2.2842F, -1.0F, 1, 3, 1, 0.0F, true));
        receiver.cubeList.add(new ModelBox(receiver, 1, 81, -1.5F, 4.7842F, -1.1F, 5, 1, 2, 0.0F, true));
        receiver.cubeList.add(new ModelBox(receiver, 1, 81, 3.2101F, 2.2842F, 23.0F, 1, 3, 2, 0.0F, false));
        receiver.cubeList.add(new ModelBox(receiver, 92, 26, -1.5F, 1.2842F, 25.0F, 5, 5, 7, 0.0F, false));
        receiver.cubeList.add(new ModelBox(receiver, 92, 26, -1.0F, 6.2842F, 28.0F, 4, 1, 4, 0.0F, false));
        receiver.cubeList.add(new ModelBox(receiver, 1, 81, -0.5F, 6.2842F, 13.0F, 3, 2, 15, 0.0F, false));
        receiver.cubeList.add(new ModelBox(receiver, 1, 81, -1.5F, 5.2842F, 12.0F, 5, 1, 13, 0.0F, false));
        receiver.cubeList.add(new ModelBox(receiver, 1, 81, -1.5F, 5.2842F, 12.0F, 5, 4, 1, 0.0F, false));
        receiver.cubeList.add(new ModelBox(receiver, 1, 81, -2.0F, 5.2842F, 11.0F, 6, 3, 1, 0.0F, false));
        receiver.cubeList.add(new ModelBox(receiver, 1, 81, -2.0F, 5.2842F, 0.0F, 6, 1, 1, 0.0F, false));
        receiver.cubeList.add(new ModelBox(receiver, 1, 81, 3.0F, 5.2842F, 1.0F, 1, 1, 10, 0.0F, false));
        receiver.cubeList.add(new ModelBox(receiver, 1, 81, -2.0F, 5.2842F, 1.0F, 1, 1, 10, 0.0F, false));
        receiver.cubeList.add(new ModelBox(receiver, 92, 26, -1.0F, -0.7158F, 25.0F, 4, 2, 2, 0.0F, false));
        receiver.cubeList.add(new ModelBox(receiver, 92, 26, -1.0F, 2.946F, 41.293F, 4, 2, 5, 0.0F, false));
        receiver.cubeList.add(new ModelBox(receiver, 1, 81, -2.2101F, 2.2842F, 23.0F, 1, 3, 2, 0.0F, true));
        receiver.cubeList.add(new ModelBox(receiver, 1, 81, 3.2101F, 3.2842F, 0.0F, 1, 2, 23, 0.0F, false));
        receiver.cubeList.add(new ModelBox(receiver, 1, 81, -2.2101F, 3.2842F, 0.0F, 1, 2, 23, 0.0F, true));
        receiver.cubeList.add(new ModelBox(receiver, 1, 81, 1.7071F, -1.2071F, 0.0F, 1, 2, 24, 0.0F, false));
        receiver.cubeList.add(new ModelBox(receiver, 1, 81, 1.7071F, -1.2071F, -2.5F, 1, 2, 2, 0.0F, false));
        receiver.cubeList.add(new ModelBox(receiver, 1, 81, 1.7071F, 2.7929F, -2.5F, 1, 2, 4, 0.0F, false));
        receiver.cubeList.add(new ModelBox(receiver, 4, 143, 1.7071F, 2.1929F, -29.5F, 1, 2, 27, 0.0F, false));
        receiver.cubeList.add(new ModelBox(receiver, 4, 143, 1.7071F, 2.1929F, -56.5F, 1, 2, 27, 0.0F, false));
        receiver.cubeList.add(new ModelBox(receiver, 15, 94, 0.7071F, 0.1929F, -34.5F, 2, 2, 2, 0.0F, false));
        receiver.cubeList.add(new ModelBox(receiver, 15, 94, 0.0F, -3.8071F, -34.5F, 2, 4, 2, 0.0F, false));
        receiver.cubeList.add(new ModelBox(receiver, 28, 155, 0.0F, -1.8071F, -35.5F, 2, 2, 10, 0.0F, false));
        receiver.cubeList.add(new ModelBox(receiver, 15, 94, -0.7071F, 0.1929F, -34.5F, 2, 2, 2, 0.0F, true));
        receiver.cubeList.add(new ModelBox(receiver, 1, 81, 2.5F, 1.5858F, 0.0F, 1, 2, 24, 0.0F, false));
        receiver.cubeList.add(new ModelBox(receiver, 1, 81, -1.5F, 1.5858F, 10.0F, 1, 2, 14, 0.0F, true));
        receiver.cubeList.add(new ModelBox(receiver, 1, 81, -1.5F, 1.5858F, 0.0F, 1, 2, 2, 0.0F, true));
        receiver.cubeList.add(new ModelBox(receiver, 1, 81, -1.5F, 2.5858F, 2.0F, 1, 1, 8, 0.0F, true));
        receiver.cubeList.add(new ModelBox(receiver, 1, 81, -0.7071F, -1.2071F, 10.0F, 1, 2, 14, 0.0F, true));
        receiver.cubeList.add(new ModelBox(receiver, 1, 81, -0.7071F, -1.2071F, 0.0F, 1, 2, 2, 0.0F, true));
        receiver.cubeList.add(new ModelBox(receiver, 1, 81, -0.7071F, -1.2071F, -2.5F, 1, 2, 2, 0.0F, true));
        receiver.cubeList.add(new ModelBox(receiver, 1, 81, -0.7071F, 2.7929F, -2.5F, 1, 2, 4, 0.0F, true));
        receiver.cubeList.add(new ModelBox(receiver, 4, 143, -0.7071F, 2.1929F, -29.5F, 1, 2, 27, 0.0F, true));
        receiver.cubeList.add(new ModelBox(receiver, 4, 143, -0.7071F, 2.1929F, -56.5F, 1, 2, 27, 0.0F, true));
        receiver.cubeList.add(new ModelBox(receiver, 1, 81, -0.7071F, -1.2071F, 2.0F, 1, 1, 8, 0.0F, true));

        receiver_r5 = new ModelRenderer(this);
        receiver_r5.setRotationPoint(2.0411F, -0.7051F, -33.5F);
        receiver.addChild(receiver_r5);
        setRotationAngle(receiver_r5, 0.0F, 0.0F, -0.1745F);
        receiver_r5.cubeList.add(new ModelBox(receiver_r5, 15, 94, -0.5F, -3.0F, -1.0F, 1, 4, 2, 0.0F, false));

        receiver_r6 = new ModelRenderer(this);
        receiver_r6.setRotationPoint(-0.0411F, -0.7051F, -33.5F);
        receiver.addChild(receiver_r6);
        setRotationAngle(receiver_r6, 0.0F, 0.0F, 0.1745F);
        receiver_r6.cubeList.add(new ModelBox(receiver_r6, 15, 94, -0.5F, -3.0F, -1.0F, 1, 4, 2, 0.0F, true));

        cube_r1 = new ModelRenderer(this);
        cube_r1.setRotationPoint(2.0F, 4.0858F, -1.5F);
        receiver.addChild(cube_r1);
        setRotationAngle(cube_r1, 0.0F, 0.0F, 0.7854F);
        cube_r1.cubeList.add(new ModelBox(cube_r1, 1, 81, 0.0F, 0.0F, -1.0F, 1, 1, 4, 0.0F, false));
        cube_r1.cubeList.add(new ModelBox(cube_r1, 4, 143, -0.4243F, -0.4243F, -28.0F, 1, 1, 27, 0.0F, false));
        cube_r1.cubeList.add(new ModelBox(cube_r1, 4, 143, -0.4243F, -0.4243F, -55.0F, 1, 1, 27, 0.0F, false));

        cube_r2 = new ModelRenderer(this);
        cube_r2.setRotationPoint(2.0F, 0.0858F, -1.5F);
        receiver.addChild(cube_r2);
        setRotationAngle(cube_r2, 0.0F, 0.0F, 0.7854F);
        cube_r2.cubeList.add(new ModelBox(cube_r2, 1, 81, 0.0F, 0.0F, -1.0F, 1, 1, 4, 0.0F, false));

        cube_r3 = new ModelRenderer(this);
        cube_r3.setRotationPoint(2.0F, 3.5F, -1.5F);
        receiver.addChild(cube_r3);
        setRotationAngle(cube_r3, 0.0F, 0.0F, -0.7854F);
        cube_r3.cubeList.add(new ModelBox(cube_r3, 1, 81, 0.0F, -1.0F, -1.0F, 1, 1, 4, 0.0F, false));
        cube_r3.cubeList.add(new ModelBox(cube_r3, 4, 143, 0.4243F, -1.4243F, -28.0F, 1, 1, 27, 0.0F, false));
        cube_r3.cubeList.add(new ModelBox(cube_r3, 4, 143, 0.4243F, -1.4243F, -55.0F, 1, 1, 27, 0.0F, false));

        cube_r4 = new ModelRenderer(this);
        cube_r4.setRotationPoint(2.0F, -0.5F, -1.5F);
        receiver.addChild(cube_r4);
        setRotationAngle(cube_r4, 0.0F, 0.0F, -0.7854F);
        cube_r4.cubeList.add(new ModelBox(cube_r4, 1, 81, 0.0F, -1.0F, -1.0F, 1, 1, 2, 0.0F, false));
        cube_r4.cubeList.add(new ModelBox(cube_r4, 1, 81, 0.0F, -1.0F, 1.5F, 1, 1, 24, 0.0F, false));

        cube_r5 = new ModelRenderer(this);
        cube_r5.setRotationPoint(0.0F, 4.0858F, -1.5F);
        receiver.addChild(cube_r5);
        setRotationAngle(cube_r5, 0.0F, 0.0F, 0.7854F);
        cube_r5.cubeList.add(new ModelBox(cube_r5, 1, 81, 0.0F, 0.0F, -1.0F, 1, 1, 4, 0.0F, false));
        cube_r5.cubeList.add(new ModelBox(cube_r5, 4, 143, -0.4243F, -0.4243F, -28.0F, 1, 1, 27, 0.0F, false));
        cube_r5.cubeList.add(new ModelBox(cube_r5, 4, 143, -0.4243F, -0.4243F, -55.0F, 1, 1, 27, 0.0F, false));

        cube_r6 = new ModelRenderer(this);
        cube_r6.setRotationPoint(0.0F, 0.0858F, -1.5F);
        receiver.addChild(cube_r6);
        setRotationAngle(cube_r6, 0.0F, 0.0F, 0.7854F);
        cube_r6.cubeList.add(new ModelBox(cube_r6, 1, 81, 0.0F, 0.0F, -1.0F, 1, 1, 4, 0.0F, false));

        cube_r7 = new ModelRenderer(this);
        cube_r7.setRotationPoint(0.0F, 3.5F, -1.5F);
        receiver.addChild(cube_r7);
        setRotationAngle(cube_r7, 0.0F, 0.0F, -0.7854F);
        cube_r7.cubeList.add(new ModelBox(cube_r7, 1, 81, 0.0F, -1.0F, -1.0F, 1, 1, 4, 0.0F, false));
        cube_r7.cubeList.add(new ModelBox(cube_r7, 4, 143, 0.4243F, -1.4243F, -28.0F, 1, 1, 27, 0.0F, false));
        cube_r7.cubeList.add(new ModelBox(cube_r7, 4, 143, 0.4243F, -1.4243F, -55.0F, 1, 1, 27, 0.0F, false));

        cube_r8 = new ModelRenderer(this);
        cube_r8.setRotationPoint(0.0F, -0.5F, -1.5F);
        receiver.addChild(cube_r8);
        setRotationAngle(cube_r8, 0.0F, 0.0F, -0.7854F);
        cube_r8.cubeList.add(new ModelBox(cube_r8, 1, 81, 0.0F, -1.0F, -1.0F, 1, 1, 2, 0.0F, false));
        cube_r8.cubeList.add(new ModelBox(cube_r8, 1, 81, 0.0F, -1.0F, 1.5F, 1, 1, 24, 0.0F, false));

        cube_r9 = new ModelRenderer(this);
        cube_r9.setRotationPoint(-0.134F, 1.2198F, 0.0F);
        receiver.addChild(cube_r9);
        setRotationAngle(cube_r9, 0.0F, 0.0F, 0.5236F);
        cube_r9.cubeList.add(new ModelBox(cube_r9, 1, 81, -1.0F, -1.0F, 0.0F, 1, 2, 2, 0.0F, true));
        cube_r9.cubeList.add(new ModelBox(cube_r9, 1, 81, -1.0F, -1.0F, 10.0F, 1, 2, 14, 0.0F, true));

        cube_r10 = new ModelRenderer(this);
        cube_r10.setRotationPoint(2.134F, 1.2198F, 0.0F);
        receiver.addChild(cube_r10);
        setRotationAngle(cube_r10, 0.0F, 0.0F, -0.5236F);
        cube_r10.cubeList.add(new ModelBox(cube_r10, 1, 81, 0.0F, -1.0F, 0.0F, 1, 2, 24, 0.0F, false));

        cube_r11 = new ModelRenderer(this);
        cube_r11.setRotationPoint(0.7385F, -1.4313F, 23.5F);
        receiver.addChild(cube_r11);
        setRotationAngle(cube_r11, 0.0F, 0.0F, 0.3491F);
        cube_r11.cubeList.add(new ModelBox(cube_r11, 1, 81, -1.5F, -0.5F, -0.5F, 1, 5, 2, 0.0F, true));
        cube_r11.cubeList.add(new ModelBox(cube_r11, 1, 81, -1.5F, -0.5F, -24.5F, 1, 5, 1, 0.0F, true));

        cube_r12 = new ModelRenderer(this);
        cube_r12.setRotationPoint(1.2615F, -1.4313F, 23.5F);
        receiver.addChild(cube_r12);
        setRotationAngle(cube_r12, 0.0F, 0.0F, -0.3491F);
        cube_r12.cubeList.add(new ModelBox(cube_r12, 1, 81, 0.5F, -0.5F, -0.5F, 1, 5, 2, 0.0F, false));
        cube_r12.cubeList.add(new ModelBox(cube_r12, 1, 81, 0.5F, -0.5F, -24.5F, 1, 5, 1, 0.0F, false));

        cube_r13 = new ModelRenderer(this);
        cube_r13.setRotationPoint(2.5F, -2.1573F, 25.5757F);
        receiver.addChild(cube_r13);
        setRotationAngle(cube_r13, -0.2618F, 0.0F, 0.0F);
        cube_r13.cubeList.add(new ModelBox(cube_r13, 1, 81, -3.0F, 0.8139F, -0.6459F, 3, 1, 2, 0.0F, false));

        cube_r14 = new ModelRenderer(this);
        cube_r14.setRotationPoint(3.0F, 0.8265F, 35.1277F);
        receiver.addChild(cube_r14);
        setRotationAngle(cube_r14, -0.3927F, 0.0F, 0.0F);
        cube_r14.cubeList.add(new ModelBox(cube_r14, 78, 21, -4.0F, 6.26F, 5.0F, 4, 2, 15, 0.0F, false));

        cube_r15 = new ModelRenderer(this);
        cube_r15.setRotationPoint(3.0F, -0.6292F, 27.3806F);
        receiver.addChild(cube_r15);
        setRotationAngle(cube_r15, -0.2618F, 0.0F, 0.0F);
        cube_r15.cubeList.add(new ModelBox(cube_r15, 92, 26, -4.0F, 6.26F, 5.0F, 4, 2, 7, 0.0F, false));

        cube_r16 = new ModelRenderer(this);
        cube_r16.setRotationPoint(3.0F, 3.3894F, 41.1377F);
        receiver.addChild(cube_r16);
        setRotationAngle(cube_r16, 0.0873F, 0.0F, 0.0F);
        cube_r16.cubeList.add(new ModelBox(cube_r16, 92, 26, -4.0F, 0.0F, 5.0F, 4, 2, 5, 0.0F, false));

        cube_r17 = new ModelRenderer(this);
        cube_r17.setRotationPoint(-1.0F, 2.2997F, 46.1044F);
        receiver.addChild(cube_r17);
        setRotationAngle(cube_r17, -0.0436F, 0.0F, 0.0F);
        cube_r17.cubeList.add(new ModelBox(cube_r17, 73, 18, 0.1F, 1.6F, -15.0F, 1, 5, 3, 0.0F, true));
        cube_r17.cubeList.add(new ModelBox(cube_r17, 73, 18, 2.9F, 1.6F, -15.0F, 1, 5, 3, 0.0F, false));
        cube_r17.cubeList.add(new ModelBox(cube_r17, 84, 21, 0.1F, 1.6F, -12.0F, 1, 6, 4, 0.0F, true));
        cube_r17.cubeList.add(new ModelBox(cube_r17, 84, 21, 2.9F, 1.6F, -12.0F, 1, 6, 4, 0.0F, false));
        cube_r17.cubeList.add(new ModelBox(cube_r17, 91, 22, 0.1F, 1.6F, -8.0F, 1, 7, 5, 0.0F, true));
        cube_r17.cubeList.add(new ModelBox(cube_r17, 91, 22, 2.9F, 1.6F, -8.0F, 1, 7, 5, 0.0F, false));
        cube_r17.cubeList.add(new ModelBox(cube_r17, 73, 18, 0.1F, 1.6F, -3.0F, 1, 9, 3, 0.0F, true));
        cube_r17.cubeList.add(new ModelBox(cube_r17, 73, 18, 2.9F, 1.6F, -3.0F, 1, 9, 3, 0.0F, false));
        cube_r17.cubeList.add(new ModelBox(cube_r17, 90, 18, 0.1F, 1.6F, 0.0F, 1, 10, 5, 0.0F, true));
        cube_r17.cubeList.add(new ModelBox(cube_r17, 90, 18, 2.9F, 1.6F, 0.0F, 1, 10, 5, 0.0F, false));
        cube_r17.cubeList.add(new ModelBox(cube_r17, 73, 18, 0.0F, 0.0F, 5.0F, 4, 14, 3, 0.0F, false));

        cube_r18 = new ModelRenderer(this);
        cube_r18.setRotationPoint(3.0F, 2.082F, 31.5053F);
        receiver.addChild(cube_r18);
        setRotationAngle(cube_r18, -0.0873F, 0.0F, 0.0F);
        cube_r18.cubeList.add(new ModelBox(cube_r18, 92, 26, -4.0F, 0.0F, 5.0F, 4, 2, 5, 0.0F, false));

        cube_r19 = new ModelRenderer(this);
        cube_r19.setRotationPoint(3.0F, -0.0098F, 27.1704F);
        receiver.addChild(cube_r19);
        setRotationAngle(cube_r19, -0.2618F, 0.0F, 0.0F);
        cube_r19.cubeList.add(new ModelBox(cube_r19, 92, 26, -4.0F, 0.0F, 5.0F, 4, 2, 5, 0.0F, false));

        cube_r20 = new ModelRenderer(this);
        cube_r20.setRotationPoint(3.0F, -2.6292F, 22.3806F);
        receiver.addChild(cube_r20);
        setRotationAngle(cube_r20, -0.0873F, 0.0F, 0.0F);
        cube_r20.cubeList.add(new ModelBox(cube_r20, 1, 81, -3.0F, 8.6F, 5.0F, 2, 2, 4, 0.0F, false));

        cube_r21 = new ModelRenderer(this);
        cube_r21.setRotationPoint(2.0F, -2.6292F, 22.3806F);
        receiver.addChild(cube_r21);
        setRotationAngle(cube_r21, -0.3927F, 0.0F, 0.0F);
        cube_r21.cubeList.add(new ModelBox(cube_r21, 92, 26, -3.0F, 0.0F, 5.0F, 4, 2, 5, 0.0F, false));

        cube_r22 = new ModelRenderer(this);
        cube_r22.setRotationPoint(-1.45F, 6.6402F, 6.1733F);
        receiver.addChild(cube_r22);
        setRotationAngle(cube_r22, -0.1309F, 0.0F, 0.0F);
        cube_r22.cubeList.add(new ModelBox(cube_r22, 1, 81, -0.6F, -1.0F, -6.0F, 1, 2, 11, 0.0F, true));
        cube_r22.cubeList.add(new ModelBox(cube_r22, 1, 81, 4.5F, -1.0F, -6.0F, 1, 2, 11, 0.0F, false));

        cube_r23 = new ModelRenderer(this);
        cube_r23.setRotationPoint(0.95F, 8.7629F, 13.4777F);
        receiver.addChild(cube_r23);
        setRotationAngle(cube_r23, 0.0436F, 0.0F, 0.0F);
        cube_r23.cubeList.add(new ModelBox(cube_r23, 1, 81, -0.4F, -0.5F, -0.5F, 2, 1, 15, 0.0F, true));
        cube_r23.cubeList.add(new ModelBox(cube_r23, 1, 81, -1.5F, -0.5F, -0.5F, 2, 1, 15, 0.0F, false));

        cube_r24 = new ModelRenderer(this);
        cube_r24.setRotationPoint(1.2321F, 7.0163F, 25.0F);
        receiver.addChild(cube_r24);
        setRotationAngle(cube_r24, 0.0F, 0.0F, -0.5236F);
        cube_r24.cubeList.add(new ModelBox(cube_r24, 1, 81, -2.0F, -2.0F, -12.0F, 1, 2, 15, 0.0F, true));

        cube_r25 = new ModelRenderer(this);
        cube_r25.setRotationPoint(0.7679F, 7.0163F, 25.0F);
        receiver.addChild(cube_r25);
        setRotationAngle(cube_r25, 0.0F, 0.0F, 0.5236F);
        cube_r25.cubeList.add(new ModelBox(cube_r25, 1, 81, 1.0F, -2.0F, -12.0F, 1, 2, 15, 0.0F, false));

        cube_r26 = new ModelRenderer(this);
        cube_r26.setRotationPoint(1.0F, 5.2701F, -0.1725F);
        receiver.addChild(cube_r26);
        setRotationAngle(cube_r26, 0.8727F, 0.0F, 0.0F);
        cube_r26.cubeList.add(new ModelBox(cube_r26, 1, 81, -2.0F, -0.38F, -0.99F, 4, 2, 1, 0.0F, true));

        bone = new ModelRenderer(this);
        bone.setRotationPoint(-0.7679F, 4.2842F, 23.2679F);
        receiver.addChild(bone);
        bone.cubeList.add(new ModelBox(bone, 1, 81, 0.2679F, 4.3015F, 3.4641F, 3, 3, 1, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 1, 81, 2.7679F, 4.3015F, -1.4019F, 1, 3, 5, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 1, 81, 0.2679F, 4.3015F, -2.2679F, 3, 3, 1, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 1, 81, 0.7679F, 7.3015F, -2.2679F, 2, 1, 1, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 1, 81, 0.7679F, 8.3015F, -5.2679F, 2, 1, 4, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 1, 81, 0.7679F, 4.6522F, -8.1968F, 2, 4, 1, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 1, 81, -0.2321F, 4.3015F, -1.4019F, 1, 3, 5, 0.0F, true));

        cube_r27 = new ModelRenderer(this);
        cube_r27.setRotationPoint(0.0F, 0.0F, 0.0F);
        bone.addChild(cube_r27);
        setRotationAngle(cube_r27, 0.0F, 0.5236F, 0.0F);
        cube_r27.cubeList.add(new ModelBox(cube_r27, 1, 81, -2.0F, 4.3015F, 3.0F, 1, 3, 1, 0.0F, true));

        cube_r28 = new ModelRenderer(this);
        cube_r28.setRotationPoint(0.0F, 0.0F, 2.1962F);
        bone.addChild(cube_r28);
        setRotationAngle(cube_r28, 0.0F, -0.5236F, 0.0F);
        cube_r28.cubeList.add(new ModelBox(cube_r28, 1, 81, -2.0F, 4.3015F, -4.0F, 1, 3, 1, 0.0F, true));

        cube_r29 = new ModelRenderer(this);
        cube_r29.setRotationPoint(1.7679F, 0.8698F, -0.3258F);
        bone.addChild(cube_r29);
        setRotationAngle(cube_r29, -0.2182F, 0.0F, 0.0F);
        cube_r29.cubeList.add(new ModelBox(cube_r29, 1, 81, -1.0F, 8.3015F, -6.0F, 2, 1, 3, 0.0F, false));

        cube_r30 = new ModelRenderer(this);
        cube_r30.setRotationPoint(3.5359F, 0.0F, 2.1962F);
        bone.addChild(cube_r30);
        setRotationAngle(cube_r30, 0.0F, 0.5236F, 0.0F);
        cube_r30.cubeList.add(new ModelBox(cube_r30, 1, 81, 1.0F, 4.3015F, -4.0F, 1, 3, 1, 0.0F, false));

        cube_r31 = new ModelRenderer(this);
        cube_r31.setRotationPoint(3.5359F, 0.0F, 0.0F);
        bone.addChild(cube_r31);
        setRotationAngle(cube_r31, 0.0F, -0.5236F, 0.0F);
        cube_r31.cubeList.add(new ModelBox(cube_r31, 1, 81, 1.0F, 4.3015F, 3.0F, 1, 3, 1, 0.0F, false));

        bone2 = new ModelRenderer(this);
        bone2.setRotationPoint(-0.7679F, 8.8632F, 25.2591F);
        receiver.addChild(bone2);
        setRotationAngle(bone2, 0.3054F, 0.0F, 0.0F);
        bone2.cubeList.add(new ModelBox(bone2, 1, 81, 0.2679F, 1.3158F, 0.8513F, 3, 9, 1, 0.0F, false));
        bone2.cubeList.add(new ModelBox(bone2, 1, 81, 2.7679F, 1.3158F, -4.0147F, 1, 9, 5, 0.0F, false));
        bone2.cubeList.add(new ModelBox(bone2, 1, 81, 0.2679F, 1.3158F, -4.8807F, 3, 9, 1, 0.0F, false));
        bone2.cubeList.add(new ModelBox(bone2, 1, 81, -0.2321F, 1.3158F, -4.0147F, 1, 9, 5, 0.0F, true));
        bone2.cubeList.add(new ModelBox(bone2, 90, 91, 0.7679F, 9.2158F, -4.0147F, 2, 1, 5, 0.0F, true));

        cube_r32 = new ModelRenderer(this);
        cube_r32.setRotationPoint(0.0F, -2.9857F, -2.6128F);
        bone2.addChild(cube_r32);
        setRotationAngle(cube_r32, 0.0F, 0.5236F, 0.0F);
        cube_r32.cubeList.add(new ModelBox(cube_r32, 1, 81, -2.0F, 4.3015F, 3.0F, 1, 9, 1, 0.0F, true));

        cube_r33 = new ModelRenderer(this);
        cube_r33.setRotationPoint(0.0F, -2.9857F, -0.4166F);
        bone2.addChild(cube_r33);
        setRotationAngle(cube_r33, 0.0F, -0.5236F, 0.0F);
        cube_r33.cubeList.add(new ModelBox(cube_r33, 1, 81, -2.0F, 4.3015F, -4.0F, 1, 9, 1, 0.0F, true));

        cube_r34 = new ModelRenderer(this);
        cube_r34.setRotationPoint(3.5359F, -2.9857F, -0.4166F);
        bone2.addChild(cube_r34);
        setRotationAngle(cube_r34, 0.0F, 0.5236F, 0.0F);
        cube_r34.cubeList.add(new ModelBox(cube_r34, 1, 81, 1.0F, 4.3015F, -4.0F, 1, 9, 1, 0.0F, false));

        cube_r35 = new ModelRenderer(this);
        cube_r35.setRotationPoint(3.5359F, -2.9857F, -2.6128F);
        bone2.addChild(cube_r35);
        setRotationAngle(cube_r35, 0.0F, -0.5236F, 0.0F);
        cube_r35.cubeList.add(new ModelBox(cube_r35, 1, 81, 1.0F, 4.3015F, 3.0F, 1, 9, 1, 0.0F, false));

        rail = new ModelRenderer(this);
        rail.setRotationPoint(2.5F, -2.3261F, 24.6098F);
        receiver.addChild(rail);
        setRotationAngle(rail, -0.2618F, 0.0F, 0.0F);
        rail.cubeList.add(new ModelBox(rail, 99, 95, -3.0F, -0.273F, -1.6692F, 3, 1, 5, 0.0F, false));
        rail.cubeList.add(new ModelBox(rail, 93, 92, -2.5F, -1.273F, 2.3308F, 2, 1, 1, 0.0F, false));
        rail.cubeList.add(new ModelBox(rail, 93, 92, -2.5F, -1.273F, -1.6692F, 2, 1, 1, 0.0F, false));
        rail.cubeList.add(new ModelBox(rail, 93, 92, -3.366F, -0.772F, -0.6692F, 2, 0, 1, 0.0F, false));
        rail.cubeList.add(new ModelBox(rail, 93, 92, -1.634F, -0.772F, -0.6692F, 2, 0, 1, 0.0F, true));
        rail.cubeList.add(new ModelBox(rail, 93, 92, -2.5F, -1.273F, 0.3308F, 2, 1, 1, 0.0F, false));
        rail.cubeList.add(new ModelBox(rail, 93, 92, -3.366F, -0.772F, 1.3308F, 2, 0, 1, 0.0F, false));
        rail.cubeList.add(new ModelBox(rail, 93, 92, -1.634F, -0.772F, 1.3308F, 2, 0, 1, 0.0F, true));

        cube_r36 = new ModelRenderer(this);
        cube_r36.setRotationPoint(-3.116F, -0.34F, 1.8308F);
        rail.addChild(cube_r36);
        setRotationAngle(cube_r36, 0.0F, 0.0F, -0.5236F);
        cube_r36.cubeList.add(new ModelBox(cube_r36, 93, 92, 0.0F, -0.5F, -0.5F, 0, 1, 1, 0.0F, true));
        cube_r36.cubeList.add(new ModelBox(cube_r36, 93, 92, 0.0F, -0.5F, -1.5F, 1, 1, 1, 0.0F, true));
        cube_r36.cubeList.add(new ModelBox(cube_r36, 93, 92, 0.0F, -0.5F, -2.5F, 0, 1, 1, 0.0F, true));
        cube_r36.cubeList.add(new ModelBox(cube_r36, 93, 92, 0.0F, -0.5F, -3.5F, 1, 1, 1, 0.0F, true));
        cube_r36.cubeList.add(new ModelBox(cube_r36, 93, 92, 0.0F, -0.5F, 0.5F, 1, 1, 1, 0.0F, true));

        cube_r37 = new ModelRenderer(this);
        cube_r37.setRotationPoint(0.116F, -0.34F, 1.8308F);
        rail.addChild(cube_r37);
        setRotationAngle(cube_r37, 0.0F, 0.0F, 0.5236F);
        cube_r37.cubeList.add(new ModelBox(cube_r37, 93, 92, 0.0F, -0.5F, -0.5F, 0, 1, 1, 0.0F, false));
        cube_r37.cubeList.add(new ModelBox(cube_r37, 93, 92, -1.0F, -0.5F, -1.5F, 1, 1, 1, 0.0F, false));
        cube_r37.cubeList.add(new ModelBox(cube_r37, 93, 92, 0.0F, -0.5F, -2.5F, 0, 1, 1, 0.0F, false));
        cube_r37.cubeList.add(new ModelBox(cube_r37, 93, 92, -1.0F, -0.5F, -3.5F, 1, 1, 1, 0.0F, false));
        cube_r37.cubeList.add(new ModelBox(cube_r37, 93, 92, -1.0F, -0.5F, 0.5F, 1, 1, 1, 0.0F, false));

        charger = new ModelRenderer(this);
        charger.setRotationPoint(1.0F, 8.5858F, 7.0F);
        slr.addChild(charger);
        charger.cubeList.add(new ModelBox(charger, 24, 171, 4.2101F, -4.5015F, 0.5F, 1, 1, 1, 0.0F, false));
        charger.cubeList.add(new ModelBox(charger, 24, 171, 3.2101F, -5.1015F, -6.5F, 1, 2, 10, 0.0F, false));
        charger.cubeList.add(new ModelBox(charger, 24, 171, 2.2101F, -4.2015F, 0.4F, 1, 1, 3, 0.0F, false));
        charger.cubeList.add(new ModelBox(charger, 24, 171, 4.2101F, -5.1015F, -6.5F, 1, 2, 1, 0.0F, false));
        charger.cubeList.add(new ModelBox(charger, 24, 171, 5.0761F, -4.6015F, -6.5F, 1, 1, 1, 0.0F, false));

        cube_r38 = new ModelRenderer(this);
        cube_r38.setRotationPoint(-0.8655F, 1.1485F, -8.0F);
        charger.addChild(cube_r38);
        setRotationAngle(cube_r38, 0.0F, 0.0F, -0.5236F);
        cube_r38.cubeList.add(new ModelBox(cube_r38, 24, 171, 7.3866F, -1.6428F, 1.5F, 1, 1, 1, 0.0F, false));

        cube_r39 = new ModelRenderer(this);
        cube_r39.setRotationPoint(-0.8655F, -7.7515F, -7.0F);
        charger.addChild(cube_r39);
        setRotationAngle(cube_r39, 0.0F, 0.0F, 0.5236F);
        cube_r39.cubeList.add(new ModelBox(cube_r39, 24, 171, 6.5866F, -0.7428F, 0.5F, 1, 1, 1, 0.0F, false));

        cube_r40 = new ModelRenderer(this);
        cube_r40.setRotationPoint(4.6101F, -3.3015F, 2.5F);
        charger.addChild(cube_r40);
        setRotationAngle(cube_r40, 0.0F, 0.0F, -0.7854F);
        cube_r40.cubeList.add(new ModelBox(cube_r40, 24, 171, 0.1364F, -0.995F, -8.0F, 1, 1, 6, 0.0F, false));

        cube_r41 = new ModelRenderer(this);
        cube_r41.setRotationPoint(-2.7899F, -4.3015F, -7.0F);
        charger.addChild(cube_r41);
        setRotationAngle(cube_r41, 0.0F, 0.0F, 0.3054F);
        cube_r41.cubeList.add(new ModelBox(cube_r41, 24, 31, -1.1F, -4.24F, 2.0F, 1, 2, 1, 0.0F, false));
        cube_r41.cubeList.add(new ModelBox(cube_r41, 24, 31, -0.1F, -4.84F, 2.0F, 1, 3, 8, 0.0F, false));

        cube_r42 = new ModelRenderer(this);
        cube_r42.setRotationPoint(-1.8903F, -4.3015F, -2.4146F);
        charger.addChild(cube_r42);
        setRotationAngle(cube_r42, 0.0F, -0.7854F, 0.0F);
        cube_r42.cubeList.add(new ModelBox(cube_r42, 24, 171, 6.7887F, -0.2F, -2.2527F, 1, 1, 2, 0.0F, false));

        barrel = new ModelRenderer(this);
        barrel.setRotationPoint(1.0F, 8.5858F, 1.0F);
        slr.addChild(barrel);
        barrel.cubeList.add(new ModelBox(barrel, 64, 6, -1.0F, -11.0F, -33.0F, 2, 1, 30, 0.0F, false));
        barrel.cubeList.add(new ModelBox(barrel, 65, 6, 0.2068F, -4.0217F, -33.0F, 1, 1, 30, 0.0F, false));
        barrel.cubeList.add(new ModelBox(barrel, 65, 6, -1.2068F, -4.0217F, -33.0F, 1, 1, 30, 0.0F, false));
        barrel.cubeList.add(new ModelBox(barrel, 69, 13, -0.5F, -4.1217F, -32.9F, 1, 1, 5, 0.0F, false));
        barrel.cubeList.add(new ModelBox(barrel, 69, 13, -0.5F, -4.1217F, -24.0F, 1, 1, 2, 0.0F, false));
        barrel.cubeList.add(new ModelBox(barrel, 68, 7, -0.5F, -4.1217F, -18.1F, 1, 1, 15, 0.0F, false));
        barrel.cubeList.add(new ModelBox(barrel, 65, 6, 1.5729F, -6.3877F, -33.0F, 1, 2, 30, 0.0F, false));
        barrel.cubeList.add(new ModelBox(barrel, 65, 6, -2.5729F, -6.3877F, -33.0F, 1, 2, 30, 0.0F, true));

        cube_r48 = new ModelRenderer(this);
        cube_r48.setRotationPoint(0.9568F, -3.4547F, -21.0F);
        barrel.addChild(cube_r48);
        setRotationAngle(cube_r48, 0.0F, 0.0F, -0.5236F);
        cube_r48.cubeList.add(new ModelBox(cube_r48, 65, 6, 0.0F, -0.5F, -12.0F, 1, 1, 30, 0.0F, false));

        cube_r49 = new ModelRenderer(this);
        cube_r49.setRotationPoint(-2.3229F, -3.9547F, -21.0F);
        barrel.addChild(cube_r49);
        setRotationAngle(cube_r49, 0.0F, 0.0F, -0.5236F);
        cube_r49.cubeList.add(new ModelBox(cube_r49, 65, 6, 0.0F, -0.5F, -12.0F, 1, 1, 30, 0.0F, false));

        cube_r50 = new ModelRenderer(this);
        cube_r50.setRotationPoint(-0.9568F, -3.4547F, -21.0F);
        barrel.addChild(cube_r50);
        setRotationAngle(cube_r50, 0.0F, 0.0F, 0.5236F);
        cube_r50.cubeList.add(new ModelBox(cube_r50, 65, 6, -1.0F, -0.5F, -12.0F, 1, 1, 30, 0.0F, true));

        cube_r51 = new ModelRenderer(this);
        cube_r51.setRotationPoint(2.3229F, -3.9547F, -21.0F);
        barrel.addChild(cube_r51);
        setRotationAngle(cube_r51, 0.0F, 0.0F, 0.5236F);
        cube_r51.cubeList.add(new ModelBox(cube_r51, 65, 6, -1.0F, -0.5F, -12.0F, 1, 1, 30, 0.0F, true));

        cube_r52 = new ModelRenderer(this);
        cube_r52.setRotationPoint(-0.6464F, -9.9393F, -21.0F);
        barrel.addChild(cube_r52);
        setRotationAngle(cube_r52, 0.0F, 0.0F, 0.7854F);
        cube_r52.cubeList.add(new ModelBox(cube_r52, 65, 6, -1.0F, -0.5F, -12.0F, 1, 1, 30, 0.0F, true));

        cube_r53 = new ModelRenderer(this);
        cube_r53.setRotationPoint(0.6464F, -9.9393F, -21.0F);
        barrel.addChild(cube_r53);
        setRotationAngle(cube_r53, 0.0F, 0.0F, -0.7854F);
        cube_r53.cubeList.add(new ModelBox(cube_r53, 65, 6, 0.0F, -0.5F, -12.0F, 1, 1, 30, 0.0F, false));

        cube_r54 = new ModelRenderer(this);
        cube_r54.setRotationPoint(-0.839F, -9.5883F, -14.0F);
        barrel.addChild(cube_r54);
        setRotationAngle(cube_r54, 0.0F, 0.0F, 0.2182F);
        cube_r54.cubeList.add(new ModelBox(cube_r54, 65, 6, -1.0F, 1.5F, -4.0F, 1, 2, 15, 0.0F, true));
        cube_r54.cubeList.add(new ModelBox(cube_r54, 69, 13, -1.0F, 1.5F, -10.0F, 1, 2, 2, 0.0F, true));
        cube_r54.cubeList.add(new ModelBox(cube_r54, 65, 6, -1.0F, 1.5F, -19.0F, 1, 2, 5, 0.0F, true));
        cube_r54.cubeList.add(new ModelBox(cube_r54, 65, 6, -1.0F, -0.5F, -19.0F, 1, 2, 30, 0.0F, true));

        cube_r55 = new ModelRenderer(this);
        cube_r55.setRotationPoint(0.839F, -9.5883F, -14.0F);
        barrel.addChild(cube_r55);
        setRotationAngle(cube_r55, 0.0F, 0.0F, -0.2182F);
        cube_r55.cubeList.add(new ModelBox(cube_r55, 68, 7, 0.0F, 1.5F, -4.0F, 1, 2, 15, 0.0F, false));
        cube_r55.cubeList.add(new ModelBox(cube_r55, 69, 13, 0.0F, 1.5F, -10.0F, 1, 2, 2, 0.0F, false));
        cube_r55.cubeList.add(new ModelBox(cube_r55, 65, 6, 0.0F, 1.5F, -19.0F, 1, 2, 5, 0.0F, false));
        cube_r55.cubeList.add(new ModelBox(cube_r55, 65, 6, 0.0F, -0.5F, -19.0F, 1, 2, 30, 0.0F, false));

        magazine = new ModelRenderer(this);
        magazine.setRotationPoint(0.0F, 21.0F, 0.0F);
        magazine.cubeList.add(new ModelBox(magazine, 17, 22, -2.5F, -2.0F, 2.5F, 1, 17, 2, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 17, 22, -2.25F, -1.5F, -4.5F, 1, 15, 7, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 17, 22, -2.5F, -2.0F, -6.5F, 1, 16, 2, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 17, 22, -2.5F, -2.0F, -3.5F, 1, 16, 2, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 17, 22, -2.5F, -2.0F, -0.5F, 1, 16, 2, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 17, 22, -1.5F, -1.0F, -6.5F, 3, 15, 1, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 17, 22, -1.5F, -1.5F, 3.3F, 3, 16, 1, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 17, 22, 1.5F, -2.0F, 2.5F, 1, 17, 2, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 17, 22, 1.25F, -1.5F, -4.5F, 1, 15, 7, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 17, 22, 1.5F, -2.0F, -6.5F, 1, 16, 2, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 17, 22, 1.5F, -2.0F, -3.5F, 1, 16, 2, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 17, 22, 1.5F, -2.0F, -0.5F, 1, 16, 2, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 87, 12, -1.0F, -1.0F, 3.5F, 2, 15, 1, 0.0F, false));

        cube_r56 = new ModelRenderer(this);
        cube_r56.setRotationPoint(0.0F, 14.4777F, 3.9787F);
        magazine.addChild(cube_r56);
        setRotationAngle(cube_r56, -0.0436F, 0.0F, 0.0F);
        cube_r56.cubeList.add(new ModelBox(cube_r56, 87, 44, -1.0F, -0.5F, -0.5F, 2, 1, 1, 0.0F, false));

        cube_r57 = new ModelRenderer(this);
        cube_r57.setRotationPoint(1.5F, 10.6605F, -5.0517F);
        magazine.addChild(cube_r57);
        setRotationAngle(cube_r57, -0.1309F, 0.0F, 0.0F);
        cube_r57.cubeList.add(new ModelBox(cube_r57, 17, 22, -3.5F, 1.5F, 3.0F, 4, 1, 5, 0.0F, false));
        cube_r57.cubeList.add(new ModelBox(cube_r57, 17, 22, -3.5F, 2.5F, -1.0F, 4, 1, 11, 0.0F, false));

        bullet = new ModelRenderer(this);
        bullet.setRotationPoint(0.3F, -2.62F, -2.8F);
        magazine.addChild(bullet);
        bullet.cubeList.add(new ModelBox(bullet, 5, 495, -1.0F, -0.4F, -0.5F, 2, 2, 6, 0.0F, false));
        bullet.cubeList.add(new ModelBox(bullet, 0, 507, -1.8F, -1.2F, 5.0F, 3, 3, 1, 0.0F, false));
        bullet.cubeList.add(new ModelBox(bullet, 0, 507, -1.8F, -1.2F, 2.5F, 3, 3, 2, 0.0F, false));
        bullet.cubeList.add(new ModelBox(bullet, 0, 507, -1.3F, -0.7F, -2.5F, 2, 2, 2, 0.0F, false));
        bullet.cubeList.add(new ModelBox(bullet, 5, 495, -1.6F, -1.0F, -0.5F, 2, 2, 6, 0.0F, false));
        bullet.cubeList.add(new ModelBox(bullet, 5, 495, -1.6F, -0.4F, -0.5F, 2, 2, 6, 0.0F, false));
        bullet.cubeList.add(new ModelBox(bullet, 5, 495, -1.0F, -1.0F, -0.5F, 2, 2, 6, 0.0F, false));

        toprail = new ModelRenderer(this);
        toprail.setRotationPoint(0.0F, 24.0F, 0.0F);
        toprail.cubeList.add(new ModelBox(toprail, 73, 88, -1.5F, -12.0F, -5.0F, 3, 1, 19, 0.0F, false));
        toprail.cubeList.add(new ModelBox(toprail, 73, 88, -1.0F, -13.0F, 13.0F, 2, 1, 1, 0.0F, false));
        toprail.cubeList.add(new ModelBox(toprail, 73, 88, -1.0F, -13.0F, 11.0F, 2, 1, 1, 0.0F, false));
        toprail.cubeList.add(new ModelBox(toprail, 73, 88, -1.0F, -13.0F, 9.0F, 2, 1, 1, 0.0F, false));
        toprail.cubeList.add(new ModelBox(toprail, 73, 88, -1.0F, -13.0F, 7.0F, 2, 1, 1, 0.0F, false));
        toprail.cubeList.add(new ModelBox(toprail, 73, 88, -1.0F, -13.0F, 5.0F, 2, 1, 1, 0.0F, false));
        toprail.cubeList.add(new ModelBox(toprail, 73, 88, -1.0F, -13.0F, 3.0F, 2, 1, 1, 0.0F, false));
        toprail.cubeList.add(new ModelBox(toprail, 73, 88, -1.0F, -13.0F, 1.0F, 2, 1, 1, 0.0F, false));
        toprail.cubeList.add(new ModelBox(toprail, 73, 88, -1.0F, -13.0F, -1.0F, 2, 1, 1, 0.0F, false));
        toprail.cubeList.add(new ModelBox(toprail, 73, 88, -1.0F, -13.0F, -3.0F, 2, 1, 1, 0.0F, false));
        toprail.cubeList.add(new ModelBox(toprail, 73, 88, -1.0F, -13.0F, -5.0F, 2, 1, 1, 0.0F, false));
        toprail.cubeList.add(new ModelBox(toprail, 73, 88, -0.1348F, -12.5005F, 12.0F, 2, 0, 1, 0.0F, false));
        toprail.cubeList.add(new ModelBox(toprail, 73, 88, -0.1348F, -12.5005F, 10.0F, 2, 0, 1, 0.0F, false));
        toprail.cubeList.add(new ModelBox(toprail, 73, 88, -0.1348F, -12.5005F, 8.0F, 2, 0, 1, 0.0F, false));
        toprail.cubeList.add(new ModelBox(toprail, 73, 88, -0.1348F, -12.5005F, 6.0F, 2, 0, 1, 0.0F, false));
        toprail.cubeList.add(new ModelBox(toprail, 73, 88, -0.1348F, -12.5005F, 4.0F, 2, 0, 1, 0.0F, false));
        toprail.cubeList.add(new ModelBox(toprail, 73, 88, -0.1348F, -12.5005F, 2.0F, 2, 0, 1, 0.0F, false));
        toprail.cubeList.add(new ModelBox(toprail, 73, 88, -0.1348F, -12.5005F, 0.0F, 2, 0, 1, 0.0F, false));
        toprail.cubeList.add(new ModelBox(toprail, 73, 88, -0.1348F, -12.5005F, -2.0F, 2, 0, 1, 0.0F, false));
        toprail.cubeList.add(new ModelBox(toprail, 73, 88, -0.1348F, -12.5005F, -4.0F, 2, 0, 1, 0.0F, false));
        toprail.cubeList.add(new ModelBox(toprail, 73, 88, -1.8652F, -12.5005F, 12.0F, 2, 0, 1, 0.0F, true));
        toprail.cubeList.add(new ModelBox(toprail, 73, 88, -1.8652F, -12.5005F, 10.0F, 2, 0, 1, 0.0F, true));
        toprail.cubeList.add(new ModelBox(toprail, 73, 88, -1.8652F, -12.5005F, 8.0F, 2, 0, 1, 0.0F, true));
        toprail.cubeList.add(new ModelBox(toprail, 73, 88, -1.8652F, -12.5005F, 6.0F, 2, 0, 1, 0.0F, true));
        toprail.cubeList.add(new ModelBox(toprail, 73, 88, -1.8652F, -12.5005F, 4.0F, 2, 0, 1, 0.0F, true));
        toprail.cubeList.add(new ModelBox(toprail, 73, 88, -1.8652F, -12.5005F, 2.0F, 2, 0, 1, 0.0F, true));
        toprail.cubeList.add(new ModelBox(toprail, 73, 88, -1.8652F, -12.5005F, 0.0F, 2, 0, 1, 0.0F, true));
        toprail.cubeList.add(new ModelBox(toprail, 73, 88, -1.8652F, -12.5005F, -2.0F, 2, 0, 1, 0.0F, true));
        toprail.cubeList.add(new ModelBox(toprail, 73, 88, -1.8652F, -12.5005F, -4.0F, 2, 0, 1, 0.0F, true));

        cube_r58 = new ModelRenderer(this);
        cube_r58.setRotationPoint(-1.2F, -9.91F, 10.5F);
        toprail.addChild(cube_r58);
        setRotationAngle(cube_r58, 0.0F, 0.0F, 0.3491F);
        cube_r58.cubeList.add(new ModelBox(cube_r58, 12, 165, -0.5F, -1.5F, -0.5F, 1, 5, 2, 0.0F, true));
        cube_r58.cubeList.add(new ModelBox(cube_r58, 90, 103, -0.9F, 2.0F, 0.0F, 1, 1, 1, 0.0F, true));

        cube_r59 = new ModelRenderer(this);
        cube_r59.setRotationPoint(1.2F, -9.91F, 10.5F);
        toprail.addChild(cube_r59);
        setRotationAngle(cube_r59, 0.0F, 0.0F, -0.3491F);
        cube_r59.cubeList.add(new ModelBox(cube_r59, 90, 103, -0.1F, 2.0F, 0.0F, 1, 1, 1, 0.0F, false));
        cube_r59.cubeList.add(new ModelBox(cube_r59, 12, 165, -0.5F, -1.5F, -0.5F, 1, 5, 2, 0.0F, false));
        cube_r59.cubeList.add(new ModelBox(cube_r59, 90, 103, -0.1F, 2.0F, -13.0F, 1, 1, 1, 0.0F, false));
        cube_r59.cubeList.add(new ModelBox(cube_r59, 12, 165, -0.5F, -1.5F, -13.5F, 1, 5, 2, 0.0F, false));
        this.initAnimations();
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }

    @Override
    public void initAnimations() {
        initAimAnimation(-0.56f, 0.335f, 0.1f);
        initAimingAnimationStates(0.335f, 0.325f, 0.275f);
        reloadAnimation = new ReloadAnimation(magazine, ReloadStyle.MAGAZINE).withSpeed(1.2F);
    }

    @Override
    public void render(ItemStack stack, ItemCameraTransforms.TransformType transformType) {
        EntityPlayerSP player = Minecraft.getMinecraft().player;
        if (player != null && player.hasCapability(PlayerDataProvider.PLAYER_DATA, null)) {
            GlStateManager.pushMatrix();
            renderSLR(stack);
            GlStateManager.popMatrix();
        }
    }

    private void renderSLR(ItemStack stack) {
        GlStateManager.pushMatrix();
        ModelTransformationHelper.defaultSRTransform();
        GlStateManager.translate(0, -9.0, -8.0);
        slr.render(1.0F);
        magazine.render(1.0F);
        if(hasScopeAtachment(stack))
            toprail.render(1.0F);
        else
            ironsights.render(1.0F);
        GlStateManager.popMatrix();
        /*renderRedDot(0, 14, 13, 1f, stack);
        renderHolo(0, 4.5, 0, 1f, stack);
        renderScope2X(0, 7, 7, 1f, stack);
        renderScope4X(0, 9.35, 10, 1f, stack);
        renderScope8X(-0.15, 8, 14, 1f, stack);
        renderScope15X(0, 7, 12, 1f, stack);
        renderSniperSilencer(0.1, -14.9, 13, 1.7f, stack);*/
    }
}

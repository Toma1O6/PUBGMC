package dev.toma.pubgmc.client.models.weapons;

import dev.toma.pubgmc.animation_old.ReloadAnimation;
import dev.toma.pubgmc.animation_old.ReloadAnimation.ReloadStyle;
import dev.toma.pubgmc.client.util.ModelTransformationHelper;
import dev.toma.pubgmc.common.capability.player.IPlayerData;
import dev.toma.pubgmc.common.capability.player.PlayerDataProvider;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.item.ItemStack;

public class ModelM24 extends ModelGun {

    private final ModelRenderer m24;
    private final ModelRenderer boltcase;
    private final ModelRenderer cube_r1;
    private final ModelRenderer cube_r2;
    private final ModelRenderer barrel;
    private final ModelRenderer body;
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
    private final ModelRenderer grip;
    private final ModelRenderer adapter;
    private final ModelRenderer cube_r14;
    private final ModelRenderer cube_r15;
    private final ModelRenderer cube_r16;
    private final ModelRenderer cube_r17;
    private final ModelRenderer cube_r18;
    private final ModelRenderer cube_r19;
    private final ModelRenderer gripPart1;
    private final ModelRenderer cube_r20;
    private final ModelRenderer cube_r21;
    private final ModelRenderer cube_r22;
    private final ModelRenderer cube_r23;
    private final ModelRenderer shrinkingBodyPart;
    private final ModelRenderer cube_r24;
    private final ModelRenderer cube_r25;
    private final ModelRenderer tacrail;
    private final ModelRenderer rrail1;
    private final ModelRenderer rrail2;
    private final ModelRenderer stock;
    private final ModelRenderer cube_r26;
    private final ModelRenderer cube_r27;
    private final ModelRenderer cube_r28;
    private final ModelRenderer cube_r29;
    private final ModelRenderer cube_r30;
    private final ModelRenderer cube_r31;
    private final ModelRenderer cube_r32;
    private final ModelRenderer cube_r33;
    private final ModelRenderer cube_r34;
    private final ModelRenderer bolt;
    private final ModelRenderer boltrot;
    private final ModelRenderer bullet;
    private final ModelRenderer magazine;
    private final ModelRenderer cube_r35;
    private final ModelRenderer cube_r36;
    private final ModelRenderer cube_r37;
    private final ModelRenderer cube_r38;
    private final ModelRenderer cube_r39;
    private final ModelRenderer cube_r40;
    private final ModelRenderer cube_r41;
    private final ModelRenderer cube_r42;
    private final ModelRenderer cube_r43;
    private final ModelRenderer cube_r44;
    private final ModelRenderer variants;
    private final ModelRenderer quickdraw;
    private final ModelRenderer cube_r45;
    private final ModelRenderer cube_r46;
    private final ModelRenderer belt;
    private final ModelRenderer cube_r47;
    private final ModelRenderer cube_r48;
    private final ModelRenderer cube_r49;
    private final ModelRenderer cube_r50;
    private final ModelRenderer cube_r51;
    private final ModelRenderer cube_r52;
    private final ModelRenderer cube_r53;
    private final ModelRenderer cube_r54;
    private final ModelRenderer extended;
    private final ModelRenderer cube_r55;
    private final ModelRenderer cube_r56;
    private final ModelRenderer cube_r57;
    private final ModelRenderer cube_r58;
    private final ModelRenderer cube_r59;
    private final ModelRenderer cube_r60;
    private final ModelRenderer cube_r61;
    private final ModelRenderer cube_r62;
    private final ModelRenderer quickdraw2;
    private final ModelRenderer cube_r63;
    private final ModelRenderer cube_r64;
    private final ModelRenderer belt2;
    private final ModelRenderer cube_r65;
    private final ModelRenderer cube_r66;
    private final ModelRenderer cube_r67;
    private final ModelRenderer cube_r68;
    private final ModelRenderer cube_r69;
    private final ModelRenderer cube_r70;
    private final ModelRenderer cube_r71;
    private final ModelRenderer cube_r72;
    private final ModelRenderer bullet2;

    public ModelM24() {
        textureWidth = 512;
        textureHeight = 512;

        m24 = new ModelRenderer(this);
        m24.setRotationPoint(0.0F, 13.0F, 0.0F);


        boltcase = new ModelRenderer(this);
        boltcase.setRotationPoint(0.817F, -1.049F, -3.0F);
        m24.addChild(boltcase);
        boltcase.cubeList.add(new ModelBox(boltcase, 19, 138, 0.183F, 1.049F, -7.0F, 1, 3, 16, 0.0F, false));
        boltcase.cubeList.add(new ModelBox(boltcase, 19, 138, 0.183F, 1.049F, -23.0F, 1, 3, 16, 0.0F, false));
        boltcase.cubeList.add(new ModelBox(boltcase, 19, 138, 0.183F, 4.049F, -9.0F, 1, 1, 12, 0.0F, false));
        boltcase.cubeList.add(new ModelBox(boltcase, 19, 138, -2.817F, 1.049F, 3.0F, 1, 3, 6, 0.0F, false));
        boltcase.cubeList.add(new ModelBox(boltcase, 0, 143, -2.817F, 4.049F, -23.0F, 4, 1, 14, 0.0F, false));
        boltcase.cubeList.add(new ModelBox(boltcase, 0, 143, -2.817F, 4.049F, 3.0F, 4, 1, 6, 0.0F, false));
        boltcase.cubeList.add(new ModelBox(boltcase, 19, 138, -2.817F, 1.049F, -23.0F, 1, 3, 14, 0.0F, false));
        boltcase.cubeList.add(new ModelBox(boltcase, 19, 138, -2.817F, 4.049F, -9.0F, 1, 1, 12, 0.0F, false));
        boltcase.cubeList.add(new ModelBox(boltcase, 92, 97, -1.817F, 4.51F, -9.0F, 2, 1, 12, 0.0F, false));
        boltcase.cubeList.add(new ModelBox(boltcase, 19, 138, -2.317F, 0.183F, 3.0F, 3, 1, 6, 0.0F, false));
        boltcase.cubeList.add(new ModelBox(boltcase, 19, 138, -2.317F, 0.183F, -23.0F, 3, 1, 14, 0.0F, false));

        cube_r1 = new ModelRenderer(this);
        cube_r1.setRotationPoint(0.0F, 0.0F, 0.0F);
        boltcase.addChild(cube_r1);
        setRotationAngle(cube_r1, 0.0F, 0.0F, -0.5236F);
        cube_r1.cubeList.add(new ModelBox(cube_r1, 19, 138, -0.5F, 0.5F, -7.0F, 1, 1, 16, 0.0F, true));
        cube_r1.cubeList.add(new ModelBox(cube_r1, 19, 138, -0.5F, 0.5F, -23.0F, 1, 1, 16, 0.0F, true));

        cube_r2 = new ModelRenderer(this);
        cube_r2.setRotationPoint(-1.634F, 0.0F, -12.0F);
        boltcase.addChild(cube_r2);
        setRotationAngle(cube_r2, 0.0F, 0.0F, 0.5236F);
        cube_r2.cubeList.add(new ModelBox(cube_r2, 19, 138, -0.5F, 0.5F, -11.0F, 1, 1, 14, 0.0F, false));
        cube_r2.cubeList.add(new ModelBox(cube_r2, 19, 138, -0.5F, 0.5F, 15.0F, 1, 1, 6, 0.0F, false));

        barrel = new ModelRenderer(this);
        barrel.setRotationPoint(-0.817F, 10.3481F, -24.9F);
        boltcase.addChild(barrel);
        barrel.cubeList.add(new ModelBox(barrel, 64, 13, -1.5F, -9.299F, -27.1F, 3, 3, 29, 0.0F, false));
        barrel.cubeList.add(new ModelBox(barrel, 64, 13, -1.5F, -9.299F, -56.1F, 3, 3, 29, 0.0F, false));

        body = new ModelRenderer(this);
        body.setRotationPoint(0.0F, 0.0F, 0.0F);
        m24.addChild(body);
        body.cubeList.add(new ModelBox(body, 0, 87, -3.0F, 2.5F, 0.1F, 6, 5, 7, 0.0F, false));
        body.cubeList.add(new ModelBox(body, 0, 87, -3.0F, 7.5F, 0.1F, 6, 4, 1, 0.0F, false));
        body.cubeList.add(new ModelBox(body, 0, 87, -3.0F, 7.5F, -13.9F, 6, 4, 1, 0.0F, false));
        body.cubeList.add(new ModelBox(body, 0, 87, -3.0F, 7.5F, -1.9F, 1, 4, 2, 0.0F, false));
        body.cubeList.add(new ModelBox(body, 0, 87, -3.0F, 7.5F, -12.9F, 1, 4, 2, 0.0F, false));
        body.cubeList.add(new ModelBox(body, 13, 78, -3.0F, 7.5F, -10.9F, 1, 3, 9, 0.0F, false));
        body.cubeList.add(new ModelBox(body, 0, 87, 2.0F, 7.5F, -1.9F, 1, 4, 2, 0.0F, false));
        body.cubeList.add(new ModelBox(body, 0, 87, 2.0F, 7.5F, -12.9F, 1, 4, 2, 0.0F, false));
        body.cubeList.add(new ModelBox(body, 0, 87, 2.0F, 7.5F, -10.9F, 1, 3, 9, 0.0F, false));
        body.cubeList.add(new ModelBox(body, 91, 90, -2.0F, 6.6F, -12.9F, 4, 1, 13, 0.0F, false));
        body.cubeList.add(new ModelBox(body, 0, 87, -3.0F, 2.5F, -25.9F, 1, 5, 26, 0.0F, false));
        body.cubeList.add(new ModelBox(body, 0, 87, 2.0F, 2.5F, -25.9F, 1, 5, 26, 0.0F, false));
        body.cubeList.add(new ModelBox(body, 0, 76, -3.0F, 2.5F, -49.9F, 6, 2, 24, 0.0F, false));
        body.cubeList.add(new ModelBox(body, 0, 76, -3.0F, 2.5F, -50.9F, 6, 2, 1, 0.0F, false));
        body.cubeList.add(new ModelBox(body, 0, 87, -2.5F, 4.366F, -27.9F, 5, 4, 2, 0.0F, false));
        body.cubeList.add(new ModelBox(body, 0, 87, -2.5F, 3.366F, -50.9F, 5, 4, 1, 0.0F, false));
        body.cubeList.add(new ModelBox(body, 0, 87, -2.0F, 7.2321F, 1.1F, 4, 2, 11, 0.0F, false));
        body.cubeList.add(new ModelBox(body, 0, 87, -2.0F, 7.2321F, -25.9F, 4, 2, 12, 0.0F, false));
        body.cubeList.add(new ModelBox(body, 0, 87, -2.0F, 8.2321F, -27.9F, 4, 1, 2, 0.0F, false));
        body.cubeList.add(new ModelBox(body, 0, 87, -2.0F, 7.2321F, -50.9F, 4, 1, 1, 0.0F, false));

        cube_r3 = new ModelRenderer(this);
        cube_r3.setRotationPoint(-2.683F, 8.049F, -56.4F);
        body.addChild(cube_r3);
        setRotationAngle(cube_r3, 0.0F, 0.0F, -0.5236F);
        cube_r3.cubeList.add(new ModelBox(cube_r3, 0, 87, 0.5F, -0.5F, 5.5F, 1, 1, 1, 0.0F, false));

        cube_r4 = new ModelRenderer(this);
        cube_r4.setRotationPoint(-2.683F, 9.049F, -29.4F);
        body.addChild(cube_r4);
        setRotationAngle(cube_r4, 0.0F, 0.0F, -0.5236F);
        cube_r4.cubeList.add(new ModelBox(cube_r4, 0, 87, 0.5F, -0.5F, 1.5F, 1, 1, 2, 0.0F, false));

        cube_r5 = new ModelRenderer(this);
        cube_r5.setRotationPoint(-2.683F, 9.049F, -12.4F);
        body.addChild(cube_r5);
        setRotationAngle(cube_r5, 0.0F, 0.0F, -0.5236F);
        cube_r5.cubeList.add(new ModelBox(cube_r5, 20, 73, 0.5F, -1.5F, -13.5F, 1, 2, 12, 0.0F, false));
        cube_r5.cubeList.add(new ModelBox(cube_r5, 0, 87, 0.5F, -1.5F, 13.5F, 1, 2, 6, 0.0F, false));

        cube_r6 = new ModelRenderer(this);
        cube_r6.setRotationPoint(-1.8355F, 7.866F, 13.3418F);
        body.addChild(cube_r6);
        setRotationAngle(cube_r6, 0.0F, 0.4363F, 0.0F);
        cube_r6.cubeList.add(new ModelBox(cube_r6, 0, 87, -0.5F, -0.5F, -0.5F, 1, 1, 2, 0.0F, true));

        cube_r7 = new ModelRenderer(this);
        cube_r7.setRotationPoint(-2.683F, 6.049F, -53.4F);
        body.addChild(cube_r7);
        setRotationAngle(cube_r7, 0.0F, 0.0F, -0.5236F);
        cube_r7.cubeList.add(new ModelBox(cube_r7, 0, 87, 0.5F, -1.5F, 2.5F, 1, 1, 1, 0.0F, false));
        cube_r7.cubeList.add(new ModelBox(cube_r7, 0, 87, 0.5F, -1.5F, 2.5F, 1, 1, 25, 0.0F, false));

        cube_r8 = new ModelRenderer(this);
        cube_r8.setRotationPoint(2.683F, 6.049F, -53.4F);
        body.addChild(cube_r8);
        setRotationAngle(cube_r8, 0.0F, 0.0F, 0.5236F);
        cube_r8.cubeList.add(new ModelBox(cube_r8, 0, 87, -1.5F, -1.5F, 2.5F, 1, 1, 1, 0.0F, true));
        cube_r8.cubeList.add(new ModelBox(cube_r8, 0, 87, -1.5F, -1.5F, 3.5F, 1, 1, 25, 0.0F, true));

        cube_r9 = new ModelRenderer(this);
        cube_r9.setRotationPoint(2.683F, 8.049F, -56.4F);
        body.addChild(cube_r9);
        setRotationAngle(cube_r9, 0.0F, 0.0F, 0.5236F);
        cube_r9.cubeList.add(new ModelBox(cube_r9, 0, 87, -1.5F, -0.5F, 5.5F, 1, 1, 1, 0.0F, true));

        cube_r10 = new ModelRenderer(this);
        cube_r10.setRotationPoint(2.683F, 9.049F, -29.4F);
        body.addChild(cube_r10);
        setRotationAngle(cube_r10, 0.0F, 0.0F, 0.5236F);
        cube_r10.cubeList.add(new ModelBox(cube_r10, 0, 87, -1.5F, -0.5F, 1.5F, 1, 1, 2, 0.0F, true));

        cube_r11 = new ModelRenderer(this);
        cube_r11.setRotationPoint(2.683F, 9.049F, -12.4F);
        body.addChild(cube_r11);
        setRotationAngle(cube_r11, 0.0F, 0.0F, 0.5236F);
        cube_r11.cubeList.add(new ModelBox(cube_r11, 20, 73, -1.5F, -1.5F, -13.5F, 1, 2, 12, 0.0F, true));
        cube_r11.cubeList.add(new ModelBox(cube_r11, 0, 87, -1.5F, -1.5F, 13.5F, 1, 2, 6, 0.0F, true));

        cube_r12 = new ModelRenderer(this);
        cube_r12.setRotationPoint(-0.001F, 8.884F, -8.3689F);
        body.addChild(cube_r12);
        setRotationAngle(cube_r12, 0.5236F, 0.0F, 0.0F);
        cube_r12.cubeList.add(new ModelBox(cube_r12, 0, 87, -2.999F, 0.0F, -3.5F, 1, 1, 2, 0.0F, true));
        cube_r12.cubeList.add(new ModelBox(cube_r12, 0, 87, 1.999F, 0.0F, -3.5F, 1, 1, 2, 0.0F, false));

        cube_r13 = new ModelRenderer(this);
        cube_r13.setRotationPoint(0.0F, 8.884F, -4.4311F);
        body.addChild(cube_r13);
        setRotationAngle(cube_r13, -0.5236F, 0.0F, 0.0F);
        cube_r13.cubeList.add(new ModelBox(cube_r13, 0, 87, -2.999F, 0.0F, 1.5F, 1, 1, 2, 0.0F, true));
        cube_r13.cubeList.add(new ModelBox(cube_r13, 0, 87, 1.999F, 0.0F, 1.5F, 1, 1, 2, 0.0F, false));

        grip = new ModelRenderer(this);
        grip.setRotationPoint(-1.5F, 1.5F, 18.1F);
        body.addChild(grip);


        adapter = new ModelRenderer(this);
        adapter.setRotationPoint(-0.5F, 6.7321F, 4.0F);
        grip.addChild(adapter);
        adapter.cubeList.add(new ModelBox(adapter, 0, 87, -0.5F, -2.866F, -15.0F, 5, 3, 6, 0.0F, false));
        adapter.cubeList.add(new ModelBox(adapter, 0, 87, 0.0F, -3.7321F, -15.0F, 4, 2, 7, 0.0F, false));
        adapter.cubeList.add(new ModelBox(adapter, 0, 87, 1.0F, -4.2321F, -15.0F, 2, 2, 6, 0.0F, false));

        cube_r14 = new ModelRenderer(this);
        cube_r14.setRotationPoint(-0.683F, 0.817F, -9.5F);
        adapter.addChild(cube_r14);
        setRotationAngle(cube_r14, 0.0F, 0.0F, -0.5236F);
        cube_r14.cubeList.add(new ModelBox(cube_r14, 0, 87, 0.5F, -0.5F, -5.5F, 1, 1, 5, 0.0F, false));

        cube_r15 = new ModelRenderer(this);
        cube_r15.setRotationPoint(1.049F, -2.549F, -8.5F);
        adapter.addChild(cube_r15);
        setRotationAngle(cube_r15, 0.0F, 0.0F, 0.5236F);
        cube_r15.cubeList.add(new ModelBox(cube_r15, 0, 87, -1.5F, -0.5F, -6.5F, 1, 1, 6, 0.0F, true));

        cube_r16 = new ModelRenderer(this);
        cube_r16.setRotationPoint(2.0F, -3.0256F, -8.0308F);
        adapter.addChild(cube_r16);
        setRotationAngle(cube_r16, -0.829F, 0.0F, 0.0F);
        cube_r16.cubeList.add(new ModelBox(cube_r16, 0, 87, -1.5F, -0.5F, -0.5F, 3, 1, 2, 0.0F, false));

        cube_r17 = new ModelRenderer(this);
        cube_r17.setRotationPoint(2.951F, -2.549F, -8.5F);
        adapter.addChild(cube_r17);
        setRotationAngle(cube_r17, 0.0F, 0.0F, -0.5236F);
        cube_r17.cubeList.add(new ModelBox(cube_r17, 0, 87, 0.5F, -0.5F, -6.5F, 1, 1, 6, 0.0F, false));

        cube_r18 = new ModelRenderer(this);
        cube_r18.setRotationPoint(4.683F, 0.817F, -9.5F);
        adapter.addChild(cube_r18);
        setRotationAngle(cube_r18, 0.0F, 0.0F, 0.5236F);
        cube_r18.cubeList.add(new ModelBox(cube_r18, 0, 87, -1.5F, -0.5F, -5.5F, 1, 1, 5, 0.0F, true));

        cube_r19 = new ModelRenderer(this);
        cube_r19.setRotationPoint(3.8355F, -2.366F, -8.7582F);
        adapter.addChild(cube_r19);
        setRotationAngle(cube_r19, 0.0F, -0.4363F, 0.0F);
        cube_r19.cubeList.add(new ModelBox(cube_r19, 0, 87, -0.5F, -0.5F, -0.5F, 1, 1, 2, 0.0F, false));

        gripPart1 = new ModelRenderer(this);
        gripPart1.setRotationPoint(4.183F, 6.0851F, -6.99F);
        grip.addChild(gripPart1);
        setRotationAngle(gripPart1, -1.2654F, 0.0F, 0.0F);
        gripPart1.cubeList.add(new ModelBox(gripPart1, 0, 87, -4.183F, -4.6529F, -0.6938F, 3, 1, 10, 0.0F, false));
        gripPart1.cubeList.add(new ModelBox(gripPart1, 0, 87, -4.183F, -0.1888F, 0.3062F, 3, 1, 9, 0.0F, false));
        gripPart1.cubeList.add(new ModelBox(gripPart1, 0, 87, -1.183F, -2.9208F, -0.6938F, 1, 2, 10, 0.0F, false));
        gripPart1.cubeList.add(new ModelBox(gripPart1, 0, 87, -5.183F, -2.9208F, -0.6938F, 1, 2, 10, 0.0F, true));

        cube_r20 = new ModelRenderer(this);
        cube_r20.setRotationPoint(0.0F, -0.2378F, 1.8062F);
        gripPart1.addChild(cube_r20);
        setRotationAngle(cube_r20, 0.0F, 0.0F, 0.5236F);
        cube_r20.cubeList.add(new ModelBox(cube_r20, 0, 87, -1.5F, -0.5F, -2.5F, 1, 2, 10, 0.0F, true));

        cube_r21 = new ModelRenderer(this);
        cube_r21.setRotationPoint(-3.134F, -3.4698F, 1.8062F);
        gripPart1.addChild(cube_r21);
        setRotationAngle(cube_r21, 0.0F, 0.0F, 0.5236F);
        cube_r21.cubeList.add(new ModelBox(cube_r21, 0, 87, -1.5F, -0.5F, -2.5F, 1, 2, 10, 0.0F, true));

        cube_r22 = new ModelRenderer(this);
        cube_r22.setRotationPoint(-5.366F, -0.2378F, 1.8062F);
        gripPart1.addChild(cube_r22);
        setRotationAngle(cube_r22, 0.0F, 0.0F, -0.5236F);
        cube_r22.cubeList.add(new ModelBox(cube_r22, 0, 87, 0.5F, -0.5F, -2.5F, 1, 2, 10, 0.0F, false));

        cube_r23 = new ModelRenderer(this);
        cube_r23.setRotationPoint(-2.2321F, -3.4698F, 1.8062F);
        gripPart1.addChild(cube_r23);
        setRotationAngle(cube_r23, 0.0F, 0.0F, -0.5236F);
        cube_r23.cubeList.add(new ModelBox(cube_r23, 0, 87, 0.5F, -0.5F, -2.5F, 1, 2, 10, 0.0F, false));

        shrinkingBodyPart = new ModelRenderer(this);
        shrinkingBodyPart.setRotationPoint(0.0F, 8.299F, -27.9F);
        body.addChild(shrinkingBodyPart);
        setRotationAngle(shrinkingBodyPart, -0.0436F, 0.0F, 0.0F);
        shrinkingBodyPart.cubeList.add(new ModelBox(shrinkingBodyPart, 0, 87, -2.5F, -3.9339F, -22.9593F, 5, 4, 23, 0.0F, false));
        shrinkingBodyPart.cubeList.add(new ModelBox(shrinkingBodyPart, 0, 87, -2.0F, -0.0679F, -22.9593F, 4, 1, 23, 0.0F, false));

        cube_r24 = new ModelRenderer(this);
        cube_r24.setRotationPoint(-2.683F, 0.7055F, -2.4603F);
        shrinkingBodyPart.addChild(cube_r24);
        setRotationAngle(cube_r24, 0.0F, 0.0F, -0.5236F);
        cube_r24.cubeList.add(new ModelBox(cube_r24, 0, 87, 0.4782F, -0.4622F, -20.499F, 1, 1, 23, 0.0F, false));

        cube_r25 = new ModelRenderer(this);
        cube_r25.setRotationPoint(2.683F, 0.7055F, -2.4603F);
        shrinkingBodyPart.addChild(cube_r25);
        setRotationAngle(cube_r25, 0.0F, 0.0F, 0.5236F);
        cube_r25.cubeList.add(new ModelBox(cube_r25, 0, 87, -1.4782F, -0.4622F, -20.499F, 1, 1, 23, 0.0F, true));

        tacrail = new ModelRenderer(this);
        tacrail.setRotationPoint(0.0F, -1.0F, 0.0F);
        m24.addChild(tacrail);
        tacrail.cubeList.add(new ModelBox(tacrail, 103, 97, -1.0F, -0.1F, 0.5F, 2, 1, 3, 0.0F, false));
        tacrail.cubeList.add(new ModelBox(tacrail, 103, 97, -1.0F, -0.1F, -15.5F, 2, 1, 3, 0.0F, false));
        tacrail.cubeList.add(new ModelBox(tacrail, 103, 97, -1.0F, -0.1F, -23.5F, 2, 1, 3, 0.0F, false));
        tacrail.cubeList.add(new ModelBox(tacrail, 79, 84, -1.5F, -1.1F, -5.5F, 3, 1, 9, 0.0F, false));
        tacrail.cubeList.add(new ModelBox(tacrail, 79, 84, -1.5F, -1.1F, -14.5F, 3, 1, 9, 0.0F, false));
        tacrail.cubeList.add(new ModelBox(tacrail, 79, 84, -1.5F, -1.1F, -23.5F, 3, 1, 9, 0.0F, false));
        tacrail.cubeList.add(new ModelBox(tacrail, 79, 84, -1.0F, -2.1F, 2.5F, 2, 1, 1, 0.0F, false));
        tacrail.cubeList.add(new ModelBox(tacrail, 79, 84, -1.0F, -2.1F, 0.5F, 2, 1, 1, 0.0F, false));
        tacrail.cubeList.add(new ModelBox(tacrail, 79, 84, -0.134F, -1.599F, 1.5F, 2, 0, 1, 0.0F, false));
        tacrail.cubeList.add(new ModelBox(tacrail, 79, 84, -1.866F, -1.599F, 1.5F, 2, 0, 1, 0.0F, true));
        tacrail.cubeList.add(new ModelBox(tacrail, 79, 84, -1.0F, -2.1F, -1.5F, 2, 1, 1, 0.0F, false));
        tacrail.cubeList.add(new ModelBox(tacrail, 79, 84, -0.134F, -1.599F, -0.5F, 2, 0, 1, 0.0F, false));
        tacrail.cubeList.add(new ModelBox(tacrail, 79, 84, -1.866F, -1.599F, -0.5F, 2, 0, 1, 0.0F, true));
        tacrail.cubeList.add(new ModelBox(tacrail, 79, 84, -1.0F, -2.1F, -3.5F, 2, 1, 1, 0.0F, false));
        tacrail.cubeList.add(new ModelBox(tacrail, 79, 84, -0.134F, -1.599F, -2.5F, 2, 0, 1, 0.0F, false));
        tacrail.cubeList.add(new ModelBox(tacrail, 79, 84, -1.866F, -1.599F, -2.5F, 2, 0, 1, 0.0F, true));
        tacrail.cubeList.add(new ModelBox(tacrail, 79, 84, -1.0F, -2.1F, -5.5F, 2, 1, 1, 0.0F, false));
        tacrail.cubeList.add(new ModelBox(tacrail, 79, 84, -0.134F, -1.599F, -4.5F, 2, 0, 1, 0.0F, false));
        tacrail.cubeList.add(new ModelBox(tacrail, 79, 84, -1.866F, -1.599F, -4.5F, 2, 0, 1, 0.0F, true));
        tacrail.cubeList.add(new ModelBox(tacrail, 79, 84, -1.0F, -2.1F, -7.5F, 2, 1, 1, 0.0F, false));
        tacrail.cubeList.add(new ModelBox(tacrail, 79, 84, -0.134F, -1.599F, -6.5F, 2, 0, 1, 0.0F, false));
        tacrail.cubeList.add(new ModelBox(tacrail, 79, 84, -1.866F, -1.599F, -6.5F, 2, 0, 1, 0.0F, true));
        tacrail.cubeList.add(new ModelBox(tacrail, 79, 84, -1.0F, -2.1F, -9.5F, 2, 1, 1, 0.0F, false));
        tacrail.cubeList.add(new ModelBox(tacrail, 79, 84, -0.134F, -1.599F, -8.5F, 2, 0, 1, 0.0F, false));
        tacrail.cubeList.add(new ModelBox(tacrail, 79, 84, -1.866F, -1.599F, -8.5F, 2, 0, 1, 0.0F, true));
        tacrail.cubeList.add(new ModelBox(tacrail, 79, 84, -1.0F, -2.1F, -11.5F, 2, 1, 1, 0.0F, false));
        tacrail.cubeList.add(new ModelBox(tacrail, 79, 84, -0.134F, -1.599F, -10.5F, 2, 0, 1, 0.0F, false));
        tacrail.cubeList.add(new ModelBox(tacrail, 79, 84, -1.866F, -1.599F, -10.5F, 2, 0, 1, 0.0F, true));
        tacrail.cubeList.add(new ModelBox(tacrail, 79, 84, -1.0F, -2.1F, -13.5F, 2, 1, 1, 0.0F, false));
        tacrail.cubeList.add(new ModelBox(tacrail, 79, 84, -0.134F, -1.599F, -12.5F, 2, 0, 1, 0.0F, false));
        tacrail.cubeList.add(new ModelBox(tacrail, 79, 84, -1.866F, -1.599F, -12.5F, 2, 0, 1, 0.0F, true));
        tacrail.cubeList.add(new ModelBox(tacrail, 79, 84, -1.0F, -2.1F, -15.5F, 2, 1, 1, 0.0F, false));
        tacrail.cubeList.add(new ModelBox(tacrail, 79, 84, -1.0F, -2.1F, -17.5F, 2, 1, 1, 0.0F, false));
        tacrail.cubeList.add(new ModelBox(tacrail, 79, 84, -1.0F, -2.1F, -19.5F, 2, 1, 1, 0.0F, false));
        tacrail.cubeList.add(new ModelBox(tacrail, 79, 84, -1.0F, -2.1F, -21.5F, 2, 1, 1, 0.0F, false));
        tacrail.cubeList.add(new ModelBox(tacrail, 79, 84, -1.0F, -2.1F, -23.5F, 2, 1, 1, 0.0F, false));
        tacrail.cubeList.add(new ModelBox(tacrail, 79, 84, -0.134F, -1.599F, -14.5F, 2, 0, 1, 0.0F, false));
        tacrail.cubeList.add(new ModelBox(tacrail, 79, 84, -0.134F, -1.599F, -16.5F, 2, 0, 1, 0.0F, false));
        tacrail.cubeList.add(new ModelBox(tacrail, 79, 84, -0.134F, -1.599F, -18.5F, 2, 0, 1, 0.0F, false));
        tacrail.cubeList.add(new ModelBox(tacrail, 79, 84, -0.134F, -1.599F, -20.5F, 2, 0, 1, 0.0F, false));
        tacrail.cubeList.add(new ModelBox(tacrail, 79, 84, -0.134F, -1.599F, -22.5F, 2, 0, 1, 0.0F, false));
        tacrail.cubeList.add(new ModelBox(tacrail, 79, 84, -1.866F, -1.599F, -14.5F, 2, 0, 1, 0.0F, true));
        tacrail.cubeList.add(new ModelBox(tacrail, 79, 84, -1.866F, -1.599F, -16.5F, 2, 0, 1, 0.0F, true));
        tacrail.cubeList.add(new ModelBox(tacrail, 79, 84, -1.866F, -1.599F, -18.5F, 2, 0, 1, 0.0F, true));
        tacrail.cubeList.add(new ModelBox(tacrail, 79, 84, -1.866F, -1.599F, -20.5F, 2, 0, 1, 0.0F, true));
        tacrail.cubeList.add(new ModelBox(tacrail, 79, 84, -1.866F, -1.599F, -22.5F, 2, 0, 1, 0.0F, true));

        rrail1 = new ModelRenderer(this);
        rrail1.setRotationPoint(0.0F, -1.6F, -6.0F);
        tacrail.addChild(rrail1);
        setRotationAngle(rrail1, 0.0F, 0.0F, 0.5236F);
        rrail1.cubeList.add(new ModelBox(rrail1, 79, 84, 0.616F, -0.933F, 8.5F, 1, 1, 1, 0.0F, false));
        rrail1.cubeList.add(new ModelBox(rrail1, 79, 84, 0.616F, -0.933F, 6.5F, 1, 1, 1, 0.0F, false));
        rrail1.cubeList.add(new ModelBox(rrail1, 79, 84, 1.616F, -0.933F, 7.5F, 0, 1, 1, 0.0F, false));
        rrail1.cubeList.add(new ModelBox(rrail1, 79, 84, 0.616F, -0.933F, 4.5F, 1, 1, 1, 0.0F, false));
        rrail1.cubeList.add(new ModelBox(rrail1, 79, 84, 1.616F, -0.933F, 5.5F, 0, 1, 1, 0.0F, false));
        rrail1.cubeList.add(new ModelBox(rrail1, 79, 84, 0.616F, -0.933F, 2.5F, 1, 1, 1, 0.0F, false));
        rrail1.cubeList.add(new ModelBox(rrail1, 79, 84, 1.616F, -0.933F, 3.5F, 0, 1, 1, 0.0F, false));
        rrail1.cubeList.add(new ModelBox(rrail1, 79, 84, 0.616F, -0.933F, 0.5F, 1, 1, 1, 0.0F, false));
        rrail1.cubeList.add(new ModelBox(rrail1, 79, 84, 1.616F, -0.933F, 1.5F, 0, 1, 1, 0.0F, false));
        rrail1.cubeList.add(new ModelBox(rrail1, 79, 84, 0.616F, -0.933F, -1.5F, 1, 1, 1, 0.0F, false));
        rrail1.cubeList.add(new ModelBox(rrail1, 79, 84, 1.616F, -0.933F, -0.5F, 0, 1, 1, 0.0F, false));
        rrail1.cubeList.add(new ModelBox(rrail1, 79, 84, 0.616F, -0.933F, -3.5F, 1, 1, 1, 0.0F, false));
        rrail1.cubeList.add(new ModelBox(rrail1, 79, 84, 1.616F, -0.933F, -2.5F, 0, 1, 1, 0.0F, false));
        rrail1.cubeList.add(new ModelBox(rrail1, 79, 84, 0.616F, -0.933F, -5.5F, 1, 1, 1, 0.0F, false));
        rrail1.cubeList.add(new ModelBox(rrail1, 79, 84, 1.616F, -0.933F, -4.5F, 0, 1, 1, 0.0F, false));
        rrail1.cubeList.add(new ModelBox(rrail1, 79, 84, 0.616F, -0.933F, -7.5F, 1, 1, 1, 0.0F, false));
        rrail1.cubeList.add(new ModelBox(rrail1, 79, 84, 1.616F, -0.933F, -6.5F, 0, 1, 1, 0.0F, false));
        rrail1.cubeList.add(new ModelBox(rrail1, 79, 84, 0.616F, -0.933F, -9.5F, 1, 1, 1, 0.0F, false));
        rrail1.cubeList.add(new ModelBox(rrail1, 79, 84, 0.616F, -0.933F, -11.5F, 1, 1, 1, 0.0F, false));
        rrail1.cubeList.add(new ModelBox(rrail1, 79, 84, 0.616F, -0.933F, -13.5F, 1, 1, 1, 0.0F, false));
        rrail1.cubeList.add(new ModelBox(rrail1, 79, 84, 0.616F, -0.933F, -15.5F, 1, 1, 1, 0.0F, false));
        rrail1.cubeList.add(new ModelBox(rrail1, 79, 84, 0.616F, -0.933F, -17.5F, 1, 1, 1, 0.0F, false));
        rrail1.cubeList.add(new ModelBox(rrail1, 79, 84, 1.616F, -0.933F, -8.5F, 0, 1, 1, 0.0F, false));
        rrail1.cubeList.add(new ModelBox(rrail1, 79, 84, 1.616F, -0.933F, -10.5F, 0, 1, 1, 0.0F, false));
        rrail1.cubeList.add(new ModelBox(rrail1, 79, 84, 1.616F, -0.933F, -12.5F, 0, 1, 1, 0.0F, false));
        rrail1.cubeList.add(new ModelBox(rrail1, 79, 84, 1.616F, -0.933F, -14.5F, 0, 1, 1, 0.0F, false));
        rrail1.cubeList.add(new ModelBox(rrail1, 79, 84, 1.616F, -0.933F, -16.5F, 0, 1, 1, 0.0F, false));

        rrail2 = new ModelRenderer(this);
        rrail2.setRotationPoint(0.0F, -1.6F, -6.0F);
        tacrail.addChild(rrail2);
        setRotationAngle(rrail2, 0.0F, 0.0F, -0.5236F);
        rrail2.cubeList.add(new ModelBox(rrail2, 79, 84, -1.616F, -0.933F, 8.5F, 1, 1, 1, 0.0F, true));
        rrail2.cubeList.add(new ModelBox(rrail2, 79, 84, -1.616F, -0.933F, 6.5F, 1, 1, 1, 0.0F, true));
        rrail2.cubeList.add(new ModelBox(rrail2, 79, 84, -1.616F, -0.933F, 7.5F, 0, 1, 1, 0.0F, true));
        rrail2.cubeList.add(new ModelBox(rrail2, 79, 84, -1.616F, -0.933F, 4.5F, 1, 1, 1, 0.0F, true));
        rrail2.cubeList.add(new ModelBox(rrail2, 79, 84, -1.616F, -0.933F, 5.5F, 0, 1, 1, 0.0F, true));
        rrail2.cubeList.add(new ModelBox(rrail2, 79, 84, -1.616F, -0.933F, 2.5F, 1, 1, 1, 0.0F, true));
        rrail2.cubeList.add(new ModelBox(rrail2, 79, 84, -1.616F, -0.933F, 3.5F, 0, 1, 1, 0.0F, true));
        rrail2.cubeList.add(new ModelBox(rrail2, 79, 84, -1.616F, -0.933F, 0.5F, 1, 1, 1, 0.0F, true));
        rrail2.cubeList.add(new ModelBox(rrail2, 79, 84, -1.616F, -0.933F, 1.5F, 0, 1, 1, 0.0F, true));
        rrail2.cubeList.add(new ModelBox(rrail2, 79, 84, -1.616F, -0.933F, -1.5F, 1, 1, 1, 0.0F, true));
        rrail2.cubeList.add(new ModelBox(rrail2, 79, 84, -1.616F, -0.933F, -0.5F, 0, 1, 1, 0.0F, true));
        rrail2.cubeList.add(new ModelBox(rrail2, 79, 84, -1.616F, -0.933F, -3.5F, 1, 1, 1, 0.0F, true));
        rrail2.cubeList.add(new ModelBox(rrail2, 79, 84, -1.616F, -0.933F, -2.5F, 0, 1, 1, 0.0F, true));
        rrail2.cubeList.add(new ModelBox(rrail2, 79, 84, -1.616F, -0.933F, -5.5F, 1, 1, 1, 0.0F, true));
        rrail2.cubeList.add(new ModelBox(rrail2, 79, 84, -1.616F, -0.933F, -4.5F, 0, 1, 1, 0.0F, true));
        rrail2.cubeList.add(new ModelBox(rrail2, 79, 84, -1.616F, -0.933F, -7.5F, 1, 1, 1, 0.0F, true));
        rrail2.cubeList.add(new ModelBox(rrail2, 79, 84, -1.616F, -0.933F, -6.5F, 0, 1, 1, 0.0F, true));
        rrail2.cubeList.add(new ModelBox(rrail2, 79, 84, -1.616F, -0.933F, -9.5F, 1, 1, 1, 0.0F, true));
        rrail2.cubeList.add(new ModelBox(rrail2, 79, 84, -1.616F, -0.933F, -11.5F, 1, 1, 1, 0.0F, true));
        rrail2.cubeList.add(new ModelBox(rrail2, 79, 84, -1.616F, -0.933F, -13.5F, 1, 1, 1, 0.0F, true));
        rrail2.cubeList.add(new ModelBox(rrail2, 79, 84, -1.616F, -0.933F, -15.5F, 1, 1, 1, 0.0F, true));
        rrail2.cubeList.add(new ModelBox(rrail2, 79, 84, -1.616F, -0.933F, -17.5F, 1, 1, 1, 0.0F, true));
        rrail2.cubeList.add(new ModelBox(rrail2, 79, 84, -1.616F, -0.933F, -8.5F, 0, 1, 1, 0.0F, true));
        rrail2.cubeList.add(new ModelBox(rrail2, 79, 84, -1.616F, -0.933F, -10.5F, 0, 1, 1, 0.0F, true));
        rrail2.cubeList.add(new ModelBox(rrail2, 79, 84, -1.616F, -0.933F, -12.5F, 0, 1, 1, 0.0F, true));
        rrail2.cubeList.add(new ModelBox(rrail2, 79, 84, -1.616F, -0.933F, -14.5F, 0, 1, 1, 0.0F, true));
        rrail2.cubeList.add(new ModelBox(rrail2, 79, 84, -1.616F, -0.933F, -16.5F, 0, 1, 1, 0.0F, true));

        stock = new ModelRenderer(this);
        stock.setRotationPoint(0.0F, 7.0F, 3.0F);
        m24.addChild(stock);
        stock.cubeList.add(new ModelBox(stock, 11, 67, -1.5F, -4.0F, 18.0F, 3, 2, 16, 0.0F, false));
        stock.cubeList.add(new ModelBox(stock, 11, 67, -1.5F, -2.0F, 18.0F, 3, 2, 16, 0.0F, false));
        stock.cubeList.add(new ModelBox(stock, 11, 67, -1.5F, 0.0F, 30.0F, 3, 5, 4, 0.0F, false));
        stock.cubeList.add(new ModelBox(stock, 11, 67, -1.5F, -3.0F, 34.8F, 3, 8, 1, 0.0F, false));
        stock.cubeList.add(new ModelBox(stock, 11, 67, -1.0F, -3.0F, 35.666F, 2, 8, 1, 0.0F, false));
        stock.cubeList.add(new ModelBox(stock, 17, 157, -1.0F, 2.5F, 34.0F, 2, 2, 1, 0.0F, false));
        stock.cubeList.add(new ModelBox(stock, 17, 157, -1.0F, -2.5F, 34.0F, 2, 2, 1, 0.0F, false));
        stock.cubeList.add(new ModelBox(stock, 11, 67, 1.366F, -3.5F, 18.0F, 1, 1, 16, 0.0F, false));
        stock.cubeList.add(new ModelBox(stock, 11, 67, -2.366F, -3.5F, 18.0F, 1, 1, 16, 0.0F, true));

        cube_r26 = new ModelRenderer(this);
        cube_r26.setRotationPoint(-2.549F, -3.183F, 18.5F);
        stock.addChild(cube_r26);
        setRotationAngle(cube_r26, 0.0F, 0.0F, 0.5236F);
        cube_r26.cubeList.add(new ModelBox(cube_r26, 11, 67, 0.5F, -0.5F, -0.5F, 1, 1, 16, 0.0F, true));

        cube_r27 = new ModelRenderer(this);
        cube_r27.setRotationPoint(-2.549F, -2.817F, 18.5F);
        stock.addChild(cube_r27);
        setRotationAngle(cube_r27, 0.0F, 0.0F, -0.5236F);
        cube_r27.cubeList.add(new ModelBox(cube_r27, 11, 67, 0.5F, -0.5F, -0.5F, 1, 1, 16, 0.0F, true));

        cube_r28 = new ModelRenderer(this);
        cube_r28.setRotationPoint(2.549F, -3.183F, 18.5F);
        stock.addChild(cube_r28);
        setRotationAngle(cube_r28, 0.0F, 0.0F, -0.5236F);
        cube_r28.cubeList.add(new ModelBox(cube_r28, 11, 67, -1.5F, -0.5F, -0.5F, 1, 1, 16, 0.0F, false));

        cube_r29 = new ModelRenderer(this);
        cube_r29.setRotationPoint(2.549F, -2.817F, 18.5F);
        stock.addChild(cube_r29);
        setRotationAngle(cube_r29, 0.0F, 0.0F, 0.5236F);
        cube_r29.cubeList.add(new ModelBox(cube_r29, 11, 67, -1.5F, -0.5F, -0.5F, 1, 1, 16, 0.0F, false));

        cube_r30 = new ModelRenderer(this);
        cube_r30.setRotationPoint(0.0F, 1.9249F, 29.1089F);
        stock.addChild(cube_r30);
        setRotationAngle(cube_r30, -0.3927F, 0.0F, 0.0F);
        cube_r30.cubeList.add(new ModelBox(cube_r30, 11, 67, -1.501F, -0.5F, -11.0F, 3, 3, 7, 0.0F, false));
        cube_r30.cubeList.add(new ModelBox(cube_r30, 11, 67, -1.501F, -2.5F, -4.0F, 3, 5, 6, 0.0F, false));

        cube_r31 = new ModelRenderer(this);
        cube_r31.setRotationPoint(-0.384F, 1.0F, 35.733F);
        stock.addChild(cube_r31);
        setRotationAngle(cube_r31, 0.0F, 0.5236F, 0.0F);
        cube_r31.cubeList.add(new ModelBox(cube_r31, 11, 67, -1.0F, -4.0F, -0.5F, 1, 8, 1, 0.0F, true));

        cube_r32 = new ModelRenderer(this);
        cube_r32.setRotationPoint(0.384F, 1.0F, 35.733F);
        stock.addChild(cube_r32);
        setRotationAngle(cube_r32, 0.0F, -0.5236F, 0.0F);
        cube_r32.cubeList.add(new ModelBox(cube_r32, 11, 67, 0.0F, -4.0F, -0.5F, 1, 8, 1, 0.0F, false));

        cube_r33 = new ModelRenderer(this);
        cube_r33.setRotationPoint(0.0F, 1.46F, 12.4F);
        stock.addChild(cube_r33);
        setRotationAngle(cube_r33, 0.2182F, 0.0F, 0.0F);
        cube_r33.cubeList.add(new ModelBox(cube_r33, 80, 26, -1.0F, 0.0F, 0.0F, 2, 2, 9, 0.0F, false));

        cube_r34 = new ModelRenderer(this);
        cube_r34.setRotationPoint(0.0F, 5.46F, 14.4F);
        stock.addChild(cube_r34);
        setRotationAngle(cube_r34, 0.2182F, 0.0F, 0.0F);
        cube_r34.cubeList.add(new ModelBox(cube_r34, 80, 26, -1.0F, 0.0F, 0.0F, 2, 2, 14, 0.0F, false));

        bolt = new ModelRenderer(this);
        bolt.setRotationPoint(2.0F, 14.0F, -12.0F);
        bolt.cubeList.add(new ModelBox(bolt, 0, 0, -3.5F, -1.0F, -0.9F, 3, 3, 1, 0.0F, false));
        bolt.cubeList.add(new ModelBox(bolt, 0, 3, -3.0F, -0.5F, 0.0F, 2, 2, 19, 0.0F, false));
        bolt.cubeList.add(new ModelBox(bolt, 0, 3, -3.0F, -0.5F, 20.0F, 2, 2, 2, 0.0F, false));
        bolt.cubeList.add(new ModelBox(bolt, 0, 3, -3.0F, -0.5F, 19.0F, 1, 0, 1, 0.0F, false));
        bolt.cubeList.add(new ModelBox(bolt, 0, 3, -3.0F, 1.5F, 19.0F, 1, 0, 1, 0.0F, false));
        bolt.cubeList.add(new ModelBox(bolt, 114, 103, -2.949F, -0.5F, 19.0F, 0, 2, 1, 0.0F, false));
        bolt.cubeList.add(new ModelBox(bolt, 0, 3, -2.0F, -0.5F, 19.0F, 1, 2, 1, 0.0F, false));

        boltrot = new ModelRenderer(this);
        boltrot.setRotationPoint(-2.5F, 0.5F, 19.6F);
        bolt.addChild(boltrot);
        setRotationAngle(boltrot, 0.0F, 0.0F, -0.5236F);
        boltrot.cubeList.add(new ModelBox(boltrot, 33, 19, -4.04F, -0.5F, -0.6F, 4, 1, 1, 0.0F, false));
        boltrot.cubeList.add(new ModelBox(boltrot, 33, 19, -6.04F, -1.0F, -1.1F, 2, 2, 2, 0.0F, false));

        bullet = new ModelRenderer(this);
        bullet.setRotationPoint(-1.9F, 0.1F, -1.0F);
        bolt.addChild(bullet);
        bullet.cubeList.add(new ModelBox(bullet, 0, 503, -1.5F, -1.0F, 0.0F, 3, 3, 1, 0.0F, false));
        bullet.cubeList.add(new ModelBox(bullet, 0, 503, -1.5F, -1.0F, -7.3F, 3, 3, 7, 0.0F, false));
        bullet.cubeList.add(new ModelBox(bullet, 111, 156, -1.0F, -0.5F, -12.3F, 2, 2, 2, 0.0F, false));
        bullet.cubeList.add(new ModelBox(bullet, 0, 503, -0.5F, -1.0F, -8.3F, 1, 3, 1, 0.0F, false));
        bullet.cubeList.add(new ModelBox(bullet, 0, 503, -1.5F, 0.0F, -8.3F, 3, 1, 1, 0.0F, false));
        bullet.cubeList.add(new ModelBox(bullet, 0, 503, -1.3F, -0.8F, -11.0F, 2, 2, 11, 0.0F, false));
        bullet.cubeList.add(new ModelBox(bullet, 0, 503, -1.3F, -0.2F, -11.0F, 2, 2, 11, 0.0F, false));
        bullet.cubeList.add(new ModelBox(bullet, 0, 503, -0.7F, -0.8F, -11.0F, 2, 2, 11, 0.0F, false));
        bullet.cubeList.add(new ModelBox(bullet, 0, 503, -0.7F, -0.2F, -11.0F, 2, 2, 11, 0.0F, false));

        magazine = new ModelRenderer(this);
        magazine.setRotationPoint(0.0F, 24.0F, 0.0F);
        magazine.cubeList.add(new ModelBox(magazine, 4, 135, -2.5F, -4.0F, -1.4F, 5, 8, 2, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 4, 135, -2.5F, -4.0F, -13.4F, 5, 8, 2, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 4, 135, -2.5F, -4.0F, -7.4F, 5, 8, 2, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 4, 135, -2.0F, -4.0F, -11.4F, 4, 8, 10, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 4, 135, -1.5F, -6.0F, -0.4F, 3, 2, 1, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 4, 135, -1.5F, -5.0F, -13.4F, 3, 1, 1, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 4, 135, 1.5F, -7.0F, -13.4F, 1, 3, 14, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 4, 135, -2.5F, -7.0F, -13.4F, 1, 3, 14, 0.0F, true));

        cube_r35 = new ModelRenderer(this);
        cube_r35.setRotationPoint(-0.0849F, -4.817F, -5.4F);
        magazine.addChild(cube_r35);
        setRotationAngle(cube_r35, 0.0F, 0.0F, -0.5236F);
        cube_r35.cubeList.add(new ModelBox(cube_r35, 4, 135, -2.5F, -0.5F, -6.0F, 1, 1, 4, 0.0F, true));
        cube_r35.cubeList.add(new ModelBox(cube_r35, 4, 135, -2.5F, -0.5F, 0.0F, 1, 1, 4, 0.0F, true));

        cube_r36 = new ModelRenderer(this);
        cube_r36.setRotationPoint(0.0849F, -4.817F, -5.4F);
        magazine.addChild(cube_r36);
        setRotationAngle(cube_r36, 0.0F, 0.0F, 0.5236F);
        cube_r36.cubeList.add(new ModelBox(cube_r36, 4, 135, 1.5F, -0.5F, -6.0F, 1, 1, 4, 0.0F, false));
        cube_r36.cubeList.add(new ModelBox(cube_r36, 4, 135, 1.5F, -0.5F, 0.0F, 1, 1, 4, 0.0F, false));

        cube_r37 = new ModelRenderer(this);
        cube_r37.setRotationPoint(0.1651F, -3.5F, -5.784F);
        magazine.addChild(cube_r37);
        setRotationAngle(cube_r37, 0.0F, 0.5236F, 0.0F);
        cube_r37.cubeList.add(new ModelBox(cube_r37, 4, 135, -2.5F, -0.5F, -1.0F, 1, 8, 1, 0.0F, true));

        cube_r38 = new ModelRenderer(this);
        cube_r38.setRotationPoint(0.1651F, -3.5F, -7.016F);
        magazine.addChild(cube_r38);
        setRotationAngle(cube_r38, 0.0F, -0.5236F, 0.0F);
        cube_r38.cubeList.add(new ModelBox(cube_r38, 4, 135, -2.5F, -0.5F, 0.0F, 1, 8, 1, 0.0F, true));

        cube_r39 = new ModelRenderer(this);
        cube_r39.setRotationPoint(-0.1651F, -3.5F, -5.784F);
        magazine.addChild(cube_r39);
        setRotationAngle(cube_r39, 0.0F, -0.5236F, 0.0F);
        cube_r39.cubeList.add(new ModelBox(cube_r39, 4, 135, 1.5F, -0.5F, -1.0F, 1, 8, 1, 0.0F, false));

        cube_r40 = new ModelRenderer(this);
        cube_r40.setRotationPoint(-0.1651F, -3.5F, -7.016F);
        magazine.addChild(cube_r40);
        setRotationAngle(cube_r40, 0.0F, 0.5236F, 0.0F);
        cube_r40.cubeList.add(new ModelBox(cube_r40, 4, 135, 1.5F, -0.5F, 0.0F, 1, 8, 1, 0.0F, false));

        cube_r41 = new ModelRenderer(this);
        cube_r41.setRotationPoint(0.1651F, -3.5F, -11.784F);
        magazine.addChild(cube_r41);
        setRotationAngle(cube_r41, 0.0F, 0.5236F, 0.0F);
        cube_r41.cubeList.add(new ModelBox(cube_r41, 4, 135, -2.5F, -0.5F, -1.0F, 1, 8, 1, 0.0F, true));

        cube_r42 = new ModelRenderer(this);
        cube_r42.setRotationPoint(0.1651F, -3.5F, -1.016F);
        magazine.addChild(cube_r42);
        setRotationAngle(cube_r42, 0.0F, -0.5236F, 0.0F);
        cube_r42.cubeList.add(new ModelBox(cube_r42, 4, 135, -2.5F, -0.5F, 0.0F, 1, 8, 1, 0.0F, true));

        cube_r43 = new ModelRenderer(this);
        cube_r43.setRotationPoint(-0.1651F, -3.5F, -11.784F);
        magazine.addChild(cube_r43);
        setRotationAngle(cube_r43, 0.0F, -0.5236F, 0.0F);
        cube_r43.cubeList.add(new ModelBox(cube_r43, 4, 135, 1.5F, -0.5F, -1.0F, 1, 8, 1, 0.0F, false));

        cube_r44 = new ModelRenderer(this);
        cube_r44.setRotationPoint(-0.1651F, -3.5F, -1.016F);
        magazine.addChild(cube_r44);
        setRotationAngle(cube_r44, 0.0F, 0.5236F, 0.0F);
        cube_r44.cubeList.add(new ModelBox(cube_r44, 4, 135, 1.5F, -0.5F, 0.0F, 1, 8, 1, 0.0F, false));

        variants = new ModelRenderer(this);
        variants.setRotationPoint(0.0F, 0.0F, 0.0F);
        magazine.addChild(variants);


        quickdraw = new ModelRenderer(this);
        quickdraw.setRotationPoint(0.0F, 0.0F, 0.0F);
        variants.addChild(quickdraw);
        quickdraw.cubeList.add(new ModelBox(quickdraw, 34, 173, -2.0F, 3.866F, -1.4F, 4, 1, 2, 0.0F, false));
        quickdraw.cubeList.add(new ModelBox(quickdraw, 34, 173, -2.0F, 3.866F, -13.4F, 4, 1, 2, 0.0F, false));
        quickdraw.cubeList.add(new ModelBox(quickdraw, 34, 173, 0.9F, 4.866F, -13.3F, 1, 1, 1, 0.0F, false));
        quickdraw.cubeList.add(new ModelBox(quickdraw, 34, 173, 0.9F, 4.866F, -0.5F, 1, 1, 1, 0.0F, false));
        quickdraw.cubeList.add(new ModelBox(quickdraw, 34, 173, -1.9F, 4.866F, -13.3F, 1, 1, 1, 0.0F, true));
        quickdraw.cubeList.add(new ModelBox(quickdraw, 34, 173, -1.9F, 4.866F, -0.5F, 1, 1, 1, 0.0F, true));

        cube_r45 = new ModelRenderer(this);
        cube_r45.setRotationPoint(-0.5179F, 3.433F, -12.4F);
        quickdraw.addChild(cube_r45);
        setRotationAngle(cube_r45, 0.0F, 0.0F, -0.5236F);
        cube_r45.cubeList.add(new ModelBox(cube_r45, 34, 173, -2.0F, -0.5F, -1.0F, 1, 1, 2, 0.0F, true));
        cube_r45.cubeList.add(new ModelBox(cube_r45, 34, 173, -2.0F, -0.5F, 11.0F, 1, 1, 2, 0.0F, true));

        cube_r46 = new ModelRenderer(this);
        cube_r46.setRotationPoint(0.5179F, 3.433F, -12.4F);
        quickdraw.addChild(cube_r46);
        setRotationAngle(cube_r46, 0.0F, 0.0F, 0.5236F);
        cube_r46.cubeList.add(new ModelBox(cube_r46, 34, 173, 1.0F, -0.5F, -1.0F, 1, 1, 2, 0.0F, false));
        cube_r46.cubeList.add(new ModelBox(cube_r46, 34, 173, 1.0F, -0.5F, 11.0F, 1, 1, 2, 0.0F, false));

        belt = new ModelRenderer(this);
        belt.setRotationPoint(0.0F, 5.366F, -12.4F);
        quickdraw.addChild(belt);
        belt.cubeList.add(new ModelBox(belt, 216, 40, -1.0F, 3.4809F, 4.9F, 2, 1, 2, 0.0F, true));

        cube_r47 = new ModelRenderer(this);
        cube_r47.setRotationPoint(0.0F, 1.549F, 0.529F);
        belt.addChild(cube_r47);
        setRotationAngle(cube_r47, 0.7854F, 0.0F, 0.0F);
        cube_r47.cubeList.add(new ModelBox(cube_r47, 216, 40, -1.0F, -0.5F, -0.5F, 2, 2, 1, 0.0F, true));

        cube_r48 = new ModelRenderer(this);
        cube_r48.setRotationPoint(0.0F, 3.6097F, 8.2195F);
        belt.addChild(cube_r48);
        setRotationAngle(cube_r48, -1.309F, 0.0F, 0.0F);
        cube_r48.cubeList.add(new ModelBox(cube_r48, 216, 40, -1.0F, -0.5F, -0.5F, 2, 2, 1, 0.0F, true));

        cube_r49 = new ModelRenderer(this);
        cube_r49.setRotationPoint(0.0F, 3.6097F, 3.5805F);
        belt.addChild(cube_r49);
        setRotationAngle(cube_r49, 1.309F, 0.0F, 0.0F);
        cube_r49.cubeList.add(new ModelBox(cube_r49, 216, 40, -1.0F, -0.5F, -0.5F, 2, 2, 1, 0.0F, true));

        cube_r50 = new ModelRenderer(this);
        cube_r50.setRotationPoint(0.0F, 2.7802F, 1.9191F);
        belt.addChild(cube_r50);
        setRotationAngle(cube_r50, 1.0472F, 0.0F, 0.0F);
        cube_r50.cubeList.add(new ModelBox(cube_r50, 216, 40, -1.0F, -0.5F, -0.5F, 2, 2, 1, 0.0F, true));

        cube_r51 = new ModelRenderer(this);
        cube_r51.setRotationPoint(0.0F, 2.7802F, 9.8809F);
        belt.addChild(cube_r51);
        setRotationAngle(cube_r51, -1.0472F, 0.0F, 0.0F);
        cube_r51.cubeList.add(new ModelBox(cube_r51, 216, 40, -1.0F, -0.5F, -0.5F, 2, 2, 1, 0.0F, true));

        cube_r52 = new ModelRenderer(this);
        cube_r52.setRotationPoint(0.0F, 1.549F, 11.271F);
        belt.addChild(cube_r52);
        setRotationAngle(cube_r52, -0.7854F, 0.0F, 0.0F);
        cube_r52.cubeList.add(new ModelBox(cube_r52, 216, 40, -1.0F, -0.5F, -0.5F, 2, 2, 1, 0.0F, true));

        cube_r53 = new ModelRenderer(this);
        cube_r53.setRotationPoint(0.0F, 0.0F, 12.2951F);
        belt.addChild(cube_r53);
        setRotationAngle(cube_r53, -0.5236F, 0.0F, 0.0F);
        cube_r53.cubeList.add(new ModelBox(cube_r53, 216, 40, -1.0F, -0.5F, -0.5F, 2, 2, 1, 0.0F, true));

        cube_r54 = new ModelRenderer(this);
        cube_r54.setRotationPoint(0.0F, 0.0F, -0.4951F);
        belt.addChild(cube_r54);
        setRotationAngle(cube_r54, 0.5236F, 0.0F, 0.0F);
        cube_r54.cubeList.add(new ModelBox(cube_r54, 216, 40, -1.0F, -0.5F, -0.5F, 2, 2, 1, 0.0F, true));

        extended = new ModelRenderer(this);
        extended.setRotationPoint(0.0F, 4.0F, 0.0F);
        variants.addChild(extended);
        extended.cubeList.add(new ModelBox(extended, 21, 163, -2.5F, 0.0F, -1.4F, 5, 4, 2, 0.0F, false));
        extended.cubeList.add(new ModelBox(extended, 21, 163, -2.5F, 0.0F, -13.4F, 5, 4, 2, 0.0F, false));
        extended.cubeList.add(new ModelBox(extended, 21, 163, -2.5F, 0.0F, -7.4F, 5, 4, 2, 0.0F, false));
        extended.cubeList.add(new ModelBox(extended, 21, 163, -2.0F, 0.0F, -11.4F, 4, 4, 10, 0.0F, false));

        cube_r55 = new ModelRenderer(this);
        cube_r55.setRotationPoint(0.1651F, 2.0F, -7.016F);
        extended.addChild(cube_r55);
        setRotationAngle(cube_r55, 0.0F, -0.5236F, 0.0F);
        cube_r55.cubeList.add(new ModelBox(cube_r55, 21, 163, -2.5F, -2.0F, 0.0F, 1, 4, 1, 0.0F, true));

        cube_r56 = new ModelRenderer(this);
        cube_r56.setRotationPoint(0.1651F, 2.0F, -5.784F);
        extended.addChild(cube_r56);
        setRotationAngle(cube_r56, 0.0F, 0.5236F, 0.0F);
        cube_r56.cubeList.add(new ModelBox(cube_r56, 21, 163, -2.5F, -2.0F, -1.0F, 1, 4, 1, 0.0F, true));

        cube_r57 = new ModelRenderer(this);
        cube_r57.setRotationPoint(-0.1651F, 2.0F, -7.016F);
        extended.addChild(cube_r57);
        setRotationAngle(cube_r57, 0.0F, 0.5236F, 0.0F);
        cube_r57.cubeList.add(new ModelBox(cube_r57, 21, 163, 1.5F, -2.0F, 0.0F, 1, 4, 1, 0.0F, false));

        cube_r58 = new ModelRenderer(this);
        cube_r58.setRotationPoint(-0.1651F, 2.0F, -5.784F);
        extended.addChild(cube_r58);
        setRotationAngle(cube_r58, 0.0F, -0.5236F, 0.0F);
        cube_r58.cubeList.add(new ModelBox(cube_r58, 21, 163, 1.5F, -2.0F, -1.0F, 1, 4, 1, 0.0F, false));

        cube_r59 = new ModelRenderer(this);
        cube_r59.setRotationPoint(0.1651F, 2.0F, -1.016F);
        extended.addChild(cube_r59);
        setRotationAngle(cube_r59, 0.0F, -0.5236F, 0.0F);
        cube_r59.cubeList.add(new ModelBox(cube_r59, 21, 163, -2.5F, -2.0F, 0.0F, 1, 4, 1, 0.0F, true));

        cube_r60 = new ModelRenderer(this);
        cube_r60.setRotationPoint(0.1651F, 2.0F, -11.784F);
        extended.addChild(cube_r60);
        setRotationAngle(cube_r60, 0.0F, 0.5236F, 0.0F);
        cube_r60.cubeList.add(new ModelBox(cube_r60, 21, 163, -2.5F, -2.0F, -1.0F, 1, 4, 1, 0.0F, true));

        cube_r61 = new ModelRenderer(this);
        cube_r61.setRotationPoint(-0.1651F, 2.0F, -1.016F);
        extended.addChild(cube_r61);
        setRotationAngle(cube_r61, 0.0F, 0.5236F, 0.0F);
        cube_r61.cubeList.add(new ModelBox(cube_r61, 21, 163, 1.5F, -2.0F, 0.0F, 1, 4, 1, 0.0F, false));

        cube_r62 = new ModelRenderer(this);
        cube_r62.setRotationPoint(-0.1651F, 2.0F, -11.784F);
        extended.addChild(cube_r62);
        setRotationAngle(cube_r62, 0.0F, -0.5236F, 0.0F);
        cube_r62.cubeList.add(new ModelBox(cube_r62, 21, 163, 1.5F, -2.0F, -1.0F, 1, 4, 1, 0.0F, false));

        quickdraw2 = new ModelRenderer(this);
        quickdraw2.setRotationPoint(0.0F, 0.0F, 0.0F);
        extended.addChild(quickdraw2);
        quickdraw2.cubeList.add(new ModelBox(quickdraw2, 34, 173, -2.0F, 3.866F, -1.4F, 4, 1, 2, 0.0F, false));
        quickdraw2.cubeList.add(new ModelBox(quickdraw2, 34, 173, -2.0F, 3.866F, -13.4F, 4, 1, 2, 0.0F, false));
        quickdraw2.cubeList.add(new ModelBox(quickdraw2, 34, 173, 0.9F, 4.866F, -13.3F, 1, 1, 1, 0.0F, false));
        quickdraw2.cubeList.add(new ModelBox(quickdraw2, 34, 173, 0.9F, 4.866F, -0.5F, 1, 1, 1, 0.0F, false));
        quickdraw2.cubeList.add(new ModelBox(quickdraw2, 34, 173, -1.9F, 4.866F, -13.3F, 1, 1, 1, 0.0F, true));
        quickdraw2.cubeList.add(new ModelBox(quickdraw2, 34, 173, -1.9F, 4.866F, -0.5F, 1, 1, 1, 0.0F, true));

        cube_r63 = new ModelRenderer(this);
        cube_r63.setRotationPoint(-0.5179F, 3.433F, -12.4F);
        quickdraw2.addChild(cube_r63);
        setRotationAngle(cube_r63, 0.0F, 0.0F, -0.5236F);
        cube_r63.cubeList.add(new ModelBox(cube_r63, 34, 173, -2.0F, -0.5F, -1.0F, 1, 1, 2, 0.0F, true));
        cube_r63.cubeList.add(new ModelBox(cube_r63, 34, 173, -2.0F, -0.5F, 11.0F, 1, 1, 2, 0.0F, true));

        cube_r64 = new ModelRenderer(this);
        cube_r64.setRotationPoint(0.5179F, 3.433F, -12.4F);
        quickdraw2.addChild(cube_r64);
        setRotationAngle(cube_r64, 0.0F, 0.0F, 0.5236F);
        cube_r64.cubeList.add(new ModelBox(cube_r64, 34, 173, 1.0F, -0.5F, -1.0F, 1, 1, 2, 0.0F, false));
        cube_r64.cubeList.add(new ModelBox(cube_r64, 34, 173, 1.0F, -0.5F, 11.0F, 1, 1, 2, 0.0F, false));

        belt2 = new ModelRenderer(this);
        belt2.setRotationPoint(0.0F, 5.366F, -12.4F);
        quickdraw2.addChild(belt2);
        belt2.cubeList.add(new ModelBox(belt2, 216, 40, -1.0F, 3.4809F, 4.9F, 2, 1, 2, 0.0F, true));

        cube_r65 = new ModelRenderer(this);
        cube_r65.setRotationPoint(0.0F, 1.549F, 0.529F);
        belt2.addChild(cube_r65);
        setRotationAngle(cube_r65, 0.7854F, 0.0F, 0.0F);
        cube_r65.cubeList.add(new ModelBox(cube_r65, 216, 40, -1.0F, -0.5F, -0.5F, 2, 2, 1, 0.0F, true));

        cube_r66 = new ModelRenderer(this);
        cube_r66.setRotationPoint(0.0F, 3.6097F, 8.2195F);
        belt2.addChild(cube_r66);
        setRotationAngle(cube_r66, -1.309F, 0.0F, 0.0F);
        cube_r66.cubeList.add(new ModelBox(cube_r66, 216, 40, -1.0F, -0.5F, -0.5F, 2, 2, 1, 0.0F, true));

        cube_r67 = new ModelRenderer(this);
        cube_r67.setRotationPoint(0.0F, 3.6097F, 3.5805F);
        belt2.addChild(cube_r67);
        setRotationAngle(cube_r67, 1.309F, 0.0F, 0.0F);
        cube_r67.cubeList.add(new ModelBox(cube_r67, 216, 40, -1.0F, -0.5F, -0.5F, 2, 2, 1, 0.0F, true));

        cube_r68 = new ModelRenderer(this);
        cube_r68.setRotationPoint(0.0F, 2.7802F, 1.9191F);
        belt2.addChild(cube_r68);
        setRotationAngle(cube_r68, 1.0472F, 0.0F, 0.0F);
        cube_r68.cubeList.add(new ModelBox(cube_r68, 216, 40, -1.0F, -0.5F, -0.5F, 2, 2, 1, 0.0F, true));

        cube_r69 = new ModelRenderer(this);
        cube_r69.setRotationPoint(0.0F, 2.7802F, 9.8809F);
        belt2.addChild(cube_r69);
        setRotationAngle(cube_r69, -1.0472F, 0.0F, 0.0F);
        cube_r69.cubeList.add(new ModelBox(cube_r69, 216, 40, -1.0F, -0.5F, -0.5F, 2, 2, 1, 0.0F, true));

        cube_r70 = new ModelRenderer(this);
        cube_r70.setRotationPoint(0.0F, 1.549F, 11.271F);
        belt2.addChild(cube_r70);
        setRotationAngle(cube_r70, -0.7854F, 0.0F, 0.0F);
        cube_r70.cubeList.add(new ModelBox(cube_r70, 216, 40, -1.0F, -0.5F, -0.5F, 2, 2, 1, 0.0F, true));

        cube_r71 = new ModelRenderer(this);
        cube_r71.setRotationPoint(0.0F, 0.0F, 12.2951F);
        belt2.addChild(cube_r71);
        setRotationAngle(cube_r71, -0.5236F, 0.0F, 0.0F);
        cube_r71.cubeList.add(new ModelBox(cube_r71, 216, 40, -1.0F, -0.5F, -0.5F, 2, 2, 1, 0.0F, true));

        cube_r72 = new ModelRenderer(this);
        cube_r72.setRotationPoint(0.0F, 0.0F, -0.4951F);
        belt2.addChild(cube_r72);
        setRotationAngle(cube_r72, 0.5236F, 0.0F, 0.0F);
        cube_r72.cubeList.add(new ModelBox(cube_r72, 216, 40, -1.0F, -0.5F, -0.5F, 2, 2, 1, 0.0F, true));

        bullet2 = new ModelRenderer(this);
        bullet2.setRotationPoint(0.1F, -6.43F, -11.95F);
        magazine.addChild(bullet2);
        bullet2.cubeList.add(new ModelBox(bullet2, 0, 503, -1.6F, -1.1F, 11.0F, 3, 3, 1, 0.0F, false));
        bullet2.cubeList.add(new ModelBox(bullet2, 0, 503, -1.6F, -1.1F, 3.7F, 3, 3, 7, 0.0F, false));
        bullet2.cubeList.add(new ModelBox(bullet2, 111, 156, -1.1F, -0.6F, -1.3F, 2, 2, 2, 0.0F, false));
        bullet2.cubeList.add(new ModelBox(bullet2, 0, 503, -0.6F, -1.1F, 2.7F, 1, 3, 1, 0.0F, false));
        bullet2.cubeList.add(new ModelBox(bullet2, 0, 503, -1.6F, -0.1F, 2.7F, 3, 1, 1, 0.0F, false));
        bullet2.cubeList.add(new ModelBox(bullet2, 0, 503, -1.4F, -0.9F, 0.0F, 2, 2, 11, 0.0F, false));
        bullet2.cubeList.add(new ModelBox(bullet2, 0, 503, -1.4F, -0.3F, 0.0F, 2, 2, 11, 0.0F, false));
        bullet2.cubeList.add(new ModelBox(bullet2, 0, 503, -0.8F, -0.9F, 0.0F, 2, 2, 11, 0.0F, false));
        bullet2.cubeList.add(new ModelBox(bullet2, 0, 503, -0.8F, -0.3F, 0.0F, 2, 2, 11, 0.0F, false));

        variants.isHidden = true;
    }

    @Override
    public void initAnimations() {
        initAimAnimation(-0.56f, 0.265f, 0.335f);
        initAimingAnimationStates(0.265f, 0.17f, 0.19f);
        reloadAnimation = new ReloadAnimation(magazine, ReloadStyle.MAGAZINE).initMovement(DEFAULT_PART_ANIMATION);
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
            IPlayerData data = player.getCapability(PlayerDataProvider.PLAYER_DATA, null);
            GlStateManager.pushMatrix();
            {
                renderM24(data.isAiming(), stack);
            }
            GlStateManager.popMatrix();
        }
    }

    private void renderM24(boolean aim, ItemStack stack) {
        GlStateManager.pushMatrix();
        {
            ModelTransformationHelper.defaultSRTransform();
            GlStateManager.scale(0.79999995, 0.79999995, 0.79999995);
            GlStateManager.translate(0.0, -15.300001, -18.0);
            renderParts();
        }
        GlStateManager.popMatrix();

        /*renderRedDot(0, 2, -19, 1f, stack);
        renderHolo(-0.025, 0.725, -8.15, 1f, stack);
        renderScope2X(0, 0, 5, 1f, stack);
        renderScope4X(0, 0, 8, 1f, stack);
        renderScope8X(0, 1.3, -2, 1f, stack);
        renderScope15X(0, -1.75, 0, 1f, stack);
        renderSniperSilencer(0.175, -6.45, 9, 1.46f, stack);*/
    }

    private void renderParts() {
        m24.render(1f);
        magazine.render(1f);
        bolt.render(1f);
    }
}

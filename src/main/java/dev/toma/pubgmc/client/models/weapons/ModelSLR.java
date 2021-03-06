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
    private final ModelRenderer _30;
    private final ModelRenderer _60;
    private final ModelRenderer SLR;
    private final ModelRenderer receiver;
    private final ModelRenderer bone;
    private final ModelRenderer bone8;
    private final ModelRenderer bone2;
    private final ModelRenderer bone3;
    private final ModelRenderer bone4;
    private final ModelRenderer bone5;
    private final ModelRenderer bone6;
    private final ModelRenderer bone9;
    private final ModelRenderer bone10;
    private final ModelRenderer bone11;
    private final ModelRenderer bone12;
    private final ModelRenderer bone18;
    private final ModelRenderer bone24;
    private final ModelRenderer bone23;
    private final ModelRenderer bone27;
    private final ModelRenderer magazine;
    private final ModelRenderer bone7;
    private final ModelRenderer charginghandle;
    private final ModelRenderer bone14;
    private final ModelRenderer bone13;
    private final ModelRenderer stock;
    private final ModelRenderer bone16;
    private final ModelRenderer bone15;
    private final ModelRenderer bone17;
    private final ModelRenderer bone19;
    private final ModelRenderer bone21;
    private final ModelRenderer bone22;
    private final ModelRenderer bone20;
    private final ModelRenderer ironsights;
    private final ModelRenderer bone25;
    private final ModelRenderer bone26;

    public ModelSLR() {
        super();
        textureWidth = 128;
        textureHeight = 128;

        SLR = new ModelRenderer(this);
        SLR.setRotationPoint(0.0F, 24.0F, 0.0F);

        receiver = new ModelRenderer(this);
        receiver.setRotationPoint(0.0F, 0.0F, 0.0F);
        SLR.addChild(receiver);
        receiver.cubeList.add(new ModelBox(receiver, 75, 36, -3.0F, -0.5F, -12.0F, 6, 1, 19, 0.0F, false));
        receiver.cubeList.add(new ModelBox(receiver, 78, 38, -3.0F, -0.5F, -7.0F, 6, 2, 14, 0.0F, false));
        receiver.cubeList.add(new ModelBox(receiver, 91, 42, -3.0F, 1.5F, 4.0F, 6, 3, 3, 0.0F, false));
        receiver.cubeList.add(new ModelBox(receiver, 99, 48, -1.5F, 3.875F, 5.9844F, 3, 1, 1, 0.0F, false));
        receiver.cubeList.add(new ModelBox(receiver, 84, 41, -1.5F, 6.6071F, 8.7164F, 3, 1, 6, 0.0F, false));
        receiver.cubeList.add(new ModelBox(receiver, 84, 41, -2.0F, 3.7321F, 14.0133F, 4, 4, 6, 0.0F, false));
        receiver.cubeList.add(new ModelBox(receiver, 84, 38, -3.0F, -0.5F, 7.0F, 6, 3, 13, 0.0F, false));
        receiver.cubeList.add(new ModelBox(receiver, 84, 41, -1.5F, 1.7321F, 13.5133F, 3, 5, 6, 0.0F, false));
        receiver.cubeList.add(new ModelBox(receiver, 75, 31, -3.5F, -5.5F, 5.0F, 7, 5, 15, 0.0F, false));
        receiver.cubeList.add(new ModelBox(receiver, 85, 43, -2.5F, -5.5F, 20.0F, 5, 6, 1, 0.0F, false));
        receiver.cubeList.add(new ModelBox(receiver, 83, 39, -3.5F, -5.5F, -12.0F, 7, 5, 8, 0.0F, false));
        receiver.cubeList.add(new ModelBox(receiver, 83, 37, -2.5F, -5.5F, -4.0F, 6, 5, 9, 0.0F, false));
        receiver.cubeList.add(new ModelBox(receiver, 77, 39, -3.5F, -1.5F, -4.0F, 1, 1, 9, 0.0F, false));
        receiver.cubeList.add(new ModelBox(receiver, 96, 38, -3.5F, -5.5F, -4.0F, 1, 1, 9, 0.0F, false));
        receiver.cubeList.add(new ModelBox(receiver, 32, 8, -2.5F, -5.9219F, -11.25F, 5, 1, 10, 0.0F, false));
        receiver.cubeList.add(new ModelBox(receiver, 32, 8, -2.0F, -6.7656F, -11.25F, 4, 1, 10, 0.0F, false));
        receiver.cubeList.add(new ModelBox(receiver, 32, 8, -2.0F, -7.0781F, 19.25F, 4, 1, 1, 0.0F, false));
        receiver.cubeList.add(new ModelBox(receiver, 32, 8, -2.0F, -7.0781F, 17.25F, 4, 1, 1, 0.0F, false));
        receiver.cubeList.add(new ModelBox(receiver, 32, 8, -2.0F, -7.0781F, 13.25F, 4, 1, 1, 0.0F, false));
        receiver.cubeList.add(new ModelBox(receiver, 32, 8, -2.0F, -7.0781F, 15.25F, 4, 1, 1, 0.0F, false));
        receiver.cubeList.add(new ModelBox(receiver, 32, 8, -2.0F, -7.0781F, 5.25F, 4, 1, 1, 0.0F, false));
        receiver.cubeList.add(new ModelBox(receiver, 32, 8, -2.0F, -7.0781F, 7.25F, 4, 1, 1, 0.0F, false));
        receiver.cubeList.add(new ModelBox(receiver, 32, 8, -2.0F, -7.0781F, 9.25F, 4, 1, 1, 0.0F, false));
        receiver.cubeList.add(new ModelBox(receiver, 32, 8, -2.0F, -7.0781F, 11.25F, 4, 1, 1, 0.0F, false));
        receiver.cubeList.add(new ModelBox(receiver, 32, 8, -2.0F, -7.0781F, -2.75F, 4, 1, 1, 0.0F, false));
        receiver.cubeList.add(new ModelBox(receiver, 32, 8, -2.0F, -7.0781F, -0.75F, 4, 1, 1, 0.0F, false));
        receiver.cubeList.add(new ModelBox(receiver, 32, 8, -2.0F, -7.0781F, 1.25F, 4, 1, 1, 0.0F, false));
        receiver.cubeList.add(new ModelBox(receiver, 32, 8, -2.0F, -7.0781F, 3.25F, 4, 1, 1, 0.0F, false));
        receiver.cubeList.add(new ModelBox(receiver, 32, 8, -2.0F, -7.0781F, -10.75F, 4, 1, 1, 0.0F, false));
        receiver.cubeList.add(new ModelBox(receiver, 32, 8, -2.0F, -7.0781F, -8.75F, 4, 1, 1, 0.0F, false));
        receiver.cubeList.add(new ModelBox(receiver, 32, 8, -2.0F, -7.0781F, -6.75F, 4, 1, 1, 0.0F, false));
        receiver.cubeList.add(new ModelBox(receiver, 32, 8, -2.0F, -7.0781F, -4.75F, 4, 1, 1, 0.0F, false));
        receiver.cubeList.add(new ModelBox(receiver, 32, 8, -2.0F, -6.7656F, -1.25F, 4, 1, 11, 0.0F, false));
        receiver.cubeList.add(new ModelBox(receiver, 32, 8, -2.5F, -5.9219F, -1.25F, 5, 1, 11, 0.0F, false));
        receiver.cubeList.add(new ModelBox(receiver, 32, 8, -2.0F, -6.7656F, 9.75F, 4, 1, 11, 0.0F, false));
        receiver.cubeList.add(new ModelBox(receiver, 32, 8, -2.5F, -5.9219F, 9.75F, 5, 1, 11, 0.0F, false));
        receiver.cubeList.add(new ModelBox(receiver, 89, 15, 2.6563F, -0.9688F, 7.875F, 1, 2, 2, 0.0F, false));
        receiver.cubeList.add(new ModelBox(receiver, 89, 15, 2.6563F, -0.2969F, 9.875F, 1, 1, 2, 0.0F, false));
        receiver.cubeList.add(new ModelBox(receiver, 89, 15, -3.5781F, -2.3594F, 8.5938F, 1, 2, 2, 0.0F, false));
        receiver.cubeList.add(new ModelBox(receiver, 83, 39, -1.5F, -5.0F, -13.0F, 3, 5, 1, 0.0F, false));
        receiver.cubeList.add(new ModelBox(receiver, 83, 39, -2.5F, -4.0F, -13.0F, 5, 3, 1, 0.0F, false));
        receiver.cubeList.add(new ModelBox(receiver, 33, 39, -2.0F, -0.1719F, -21.0F, 4, 1, 8, 0.0F, false));
        receiver.cubeList.add(new ModelBox(receiver, 33, 39, -3.7321F, -1.1719F, -21.0F, 3, 1, 8, 0.0F, false));
        receiver.cubeList.add(new ModelBox(receiver, 33, 39, 0.7321F, -1.1719F, -21.0F, 3, 1, 8, 0.0F, true));
        receiver.cubeList.add(new ModelBox(receiver, 33, 39, -0.1693F, -5.5356F, -21.0F, 2, 1, 8, 0.0F, true));
        receiver.cubeList.add(new ModelBox(receiver, 33, 39, -1.8307F, -5.5356F, -21.0F, 2, 1, 8, 0.0F, true));
        receiver.cubeList.add(new ModelBox(receiver, 33, 39, -3.7321F, -1.1719F, -29.0F, 3, 1, 8, 0.0F, false));
        receiver.cubeList.add(new ModelBox(receiver, 33, 39, -1.8307F, -5.5356F, -29.0F, 2, 1, 8, 0.0F, true));
        receiver.cubeList.add(new ModelBox(receiver, 33, 39, -0.1693F, -5.5356F, -29.0F, 2, 1, 8, 0.0F, true));
        receiver.cubeList.add(new ModelBox(receiver, 33, 39, 0.7321F, -1.1719F, -29.0F, 3, 1, 8, 0.0F, true));
        receiver.cubeList.add(new ModelBox(receiver, 33, 39, -2.0F, -0.1719F, -29.0F, 4, 1, 8, 0.0F, false));
        receiver.cubeList.add(new ModelBox(receiver, 33, 39, -3.7321F, -1.1719F, -39.0F, 3, 1, 10, 0.0F, false));
        receiver.cubeList.add(new ModelBox(receiver, 33, 39, -1.8307F, -5.5356F, -39.0F, 2, 1, 10, 0.0F, true));
        receiver.cubeList.add(new ModelBox(receiver, 33, 39, -0.1693F, -5.5356F, -39.0F, 2, 1, 10, 0.0F, true));
        receiver.cubeList.add(new ModelBox(receiver, 33, 39, 0.7321F, -1.1719F, -39.0F, 3, 1, 10, 0.0F, true));
        receiver.cubeList.add(new ModelBox(receiver, 33, 39, -2.0F, -0.1719F, -39.0F, 4, 1, 10, 0.0F, false));
        receiver.cubeList.add(new ModelBox(receiver, 76, 31, -1.0F, -3.5156F, -45.0F, 2, 2, 16, 0.0F, false));
        receiver.cubeList.add(new ModelBox(receiver, 76, 31, -1.0F, -3.5156F, -29.0F, 2, 2, 17, 0.0F, false));
        receiver.cubeList.add(new ModelBox(receiver, 76, 31, -1.5F, -4.0156F, -51.0F, 3, 3, 6, 0.0F, false));
        receiver.cubeList.add(new ModelBox(receiver, 76, 31, -1.0F, -3.5156F, -53.0F, 2, 2, 2, 0.0F, false));
        receiver.cubeList.add(new ModelBox(receiver, 76, 31, -1.0F, -10.0156F, -50.5F, 2, 6, 2, 0.0F, false));

        _30 = new ModelRenderer(this);
        _30.setRotationPoint(0.0F, 0.0F, 0.0F);
        setRotationAngle(_30, 0.0F, 0.0F, -0.5236F);
        receiver.addChild(_30);
        _30.cubeList.add(new ModelBox(_30, 33, 39, 1.318F, 0.7172F, -21.0F, 2, 1, 8, 0.0F, true));
        _30.cubeList.add(new ModelBox(_30, 33, 39, 0.1823F, -5.7093F, -21.0F, 1, 1, 8, 0.0F, true));
        _30.cubeList.add(new ModelBox(_30, 33, 39, 0.1823F, -5.7093F, -29.0F, 1, 1, 8, 0.0F, true));
        _30.cubeList.add(new ModelBox(_30, 33, 39, 1.318F, 0.7172F, -29.0F, 2, 1, 8, 0.0F, true));
        _30.cubeList.add(new ModelBox(_30, 33, 39, 0.1823F, -5.7093F, -39.0F, 1, 1, 10, 0.0F, true));
        _30.cubeList.add(new ModelBox(_30, 33, 39, 1.318F, 0.7172F, -39.0F, 2, 1, 10, 0.0F, true));

        _60 = new ModelRenderer(this);
        _60.setRotationPoint(0.0F, 0.0F, 0.0F);
        setRotationAngle(_60, 0.0F, 0.0F, 0.5236F);
        receiver.addChild(_60);
        _60.cubeList.add(new ModelBox(_60, 33, 39, -3.318F, 0.7172F, -21.0F, 2, 1, 8, 0.0F, false));
        _60.cubeList.add(new ModelBox(_60, 33, 39, -1.1823F, -5.7093F, -21.0F, 1, 1, 8, 0.0F, false));
        _60.cubeList.add(new ModelBox(_60, 33, 39, -1.1823F, -5.7093F, -29.0F, 1, 1, 8, 0.0F, false));
        _60.cubeList.add(new ModelBox(_60, 33, 39, -3.318F, 0.7172F, -29.0F, 2, 1, 8, 0.0F, false));
        _60.cubeList.add(new ModelBox(_60, 33, 39, -1.1823F, -5.7093F, -39.0F, 1, 1, 10, 0.0F, false));
        _60.cubeList.add(new ModelBox(_60, 33, 39, -3.318F, 0.7172F, -39.0F, 2, 1, 10, 0.0F, false));

        bone = new ModelRenderer(this);
        bone.setRotationPoint(0.0F, -3.0F, -16.5F);
        setRotationAngle(bone, -0.1745F, 0.0F, 0.0F);
        receiver.addChild(bone);
        bone.cubeList.add(new ModelBox(bone, 81, 36, -3.0F, 0.6951F, 7.8162F, 6, 3, 14, 0.0F, false));

        bone8 = new ModelRenderer(this);
        bone8.setRotationPoint(0.0F, -3.0F, -16.5F);
        setRotationAngle(bone8, -1.4835F, 0.0F, 0.0F);
        receiver.addChild(bone8);
        bone8.cubeList.add(new ModelBox(bone8, 96, 45, -3.0F, -9.5037F, 2.4931F, 6, 3, 3, 0.0F, false));

        bone2 = new ModelRenderer(this);
        bone2.setRotationPoint(0.0F, 0.0F, 0.0F);
        setRotationAngle(bone2, 0.5236F, 0.0F, 0.0F);
        receiver.addChild(bone2);
        bone2.cubeList.add(new ModelBox(bone2, 84, 41, -1.5F, 7.2141F, 2.7451F, 3, 2, 1, 0.0F, false));

        bone3 = new ModelRenderer(this);
        bone3.setRotationPoint(0.0F, 10.2093F, 7.049F);
        setRotationAngle(bone3, 1.0472F, 0.0F, 0.0F);
        receiver.addChild(bone3);
        bone3.cubeList.add(new ModelBox(bone3, 84, 41, -1.5F, -1.8571F, 3.0873F, 3, 2, 1, 0.0F, false));
        bone3.cubeList.add(new ModelBox(bone3, 84, 41, -1.0F, -0.154F, 6.5092F, 2, 1, 3, 0.0F, false));

        bone4 = new ModelRenderer(this);
        bone4.setRotationPoint(0.0F, 10.2093F, -7.049F);
        setRotationAngle(bone4, -1.0472F, 0.0F, 0.0F);
        receiver.addChild(bone4);
        bone4.cubeList.add(new ModelBox(bone4, 84, 41, -1.5F, -20.9408F, 6.801F, 3, 2, 1, 0.0F, false));

        bone5 = new ModelRenderer(this);
        bone5.setRotationPoint(0.0F, 5.2321F, 17.0133F);
        setRotationAngle(bone5, 0.5236F, 0.0F, 0.0F);
        receiver.addChild(bone5);
        bone5.cubeList.add(new ModelBox(bone5, 96, 38, -2.0F, 1.2299F, -0.25F, 4, 8, 3, 0.0F, false));
        bone5.cubeList.add(new ModelBox(bone5, 96, 38, -2.0F, 8.2299F, -2.25F, 4, 1, 2, 0.0F, false));
        bone5.cubeList.add(new ModelBox(bone5, 43, 72, 1.125F, 0.9486F, -1.5625F, 1, 8, 4, 0.0F, false));
        bone5.cubeList.add(new ModelBox(bone5, 43, 80, -2.125F, 0.9486F, -1.5625F, 1, 8, 4, 0.0F, true));

        bone6 = new ModelRenderer(this);
        bone6.setRotationPoint(0.0F, 5.2321F, 17.0133F);
        setRotationAngle(bone6, 0.4363F, 0.0F, 0.0F);
        receiver.addChild(bone6);
        bone6.cubeList.add(new ModelBox(bone6, 96, 38, -2.0F, 0.8282F, -1.5551F, 4, 8, 3, 0.0F, false));

        bone9 = new ModelRenderer(this);
        bone9.setRotationPoint(0.0F, 0.0F, 0.0F);
        setRotationAngle(bone9, 0.0F, 0.9599F, 0.0F);
        receiver.addChild(bone9);
        bone9.cubeList.add(new ModelBox(bone9, 108, 40, -16.3755F, -5.5F, 13.3386F, 2, 5, 1, 0.0F, false));

        bone10 = new ModelRenderer(this);
        bone10.setRotationPoint(0.0F, 0.0F, 0.0F);
        setRotationAngle(bone10, 0.0F, -0.9599F, 0.0F);
        receiver.addChild(bone10);
        bone10.cubeList.add(new ModelBox(bone10, 94, 42, 14.3755F, -5.5F, 13.3386F, 2, 5, 1, 0.0F, true));

        bone11 = new ModelRenderer(this);
        bone11.setRotationPoint(0.0F, 0.0F, 0.0F);
        setRotationAngle(bone11, 0.0F, 0.0F, 0.3491F);
        receiver.addChild(bone11);
        bone11.cubeList.add(new ModelBox(bone11, 80, 36, 2.0616F, -1.6864F, 4.8438F, 1, 1, 15, 0.0F, false));
        bone11.cubeList.add(new ModelBox(bone11, 80, 36, 2.0616F, -1.6864F, -10.1563F, 1, 1, 15, 0.0F, false));

        bone12 = new ModelRenderer(this);
        bone12.setRotationPoint(0.0F, 0.0F, 0.0F);
        setRotationAngle(bone12, 0.0F, 0.0F, -0.3491F);
        receiver.addChild(bone12);
        bone12.cubeList.add(new ModelBox(bone12, 80, 36, -3.0616F, -1.6864F, 4.8438F, 1, 1, 15, 0.0F, true));
        bone12.cubeList.add(new ModelBox(bone12, 80, 36, -3.0616F, -1.6864F, -10.1563F, 1, 1, 15, 0.0F, true));

        bone18 = new ModelRenderer(this);
        bone18.setRotationPoint(0.0F, 0.0F, 0.0F);
        setRotationAngle(bone18, 0.0F, 0.0F, 0.1745F);
        receiver.addChild(bone18);
        bone18.cubeList.add(new ModelBox(bone18, 89, 15, 2.5556F, -2.2107F, -3.1563F, 1, 2, 2, 0.0F, false));
        bone18.cubeList.add(new ModelBox(bone18, 89, 15, 2.7587F, -3.7576F, -3.1563F, 1, 2, 2, 0.0F, false));

        bone24 = new ModelRenderer(this);
        bone24.setRotationPoint(0.0F, 0.0F, 0.0F);
        setRotationAngle(bone24, 0.0F, 0.0F, -0.2618F);
        _60.addChild(bone24);
        bone24.cubeList.add(new ModelBox(bone24, 33, 39, -3.9082F, -2.166F, -21.0F, 2, 2, 8, 0.0F, false));
        bone24.cubeList.add(new ModelBox(bone24, 33, 39, -3.9082F, -4.166F, -21.0F, 2, 1, 8, 0.0F, false));
        bone24.cubeList.add(new ModelBox(bone24, 33, 39, -3.9082F, -3.166F, -15.0F, 2, 1, 2, 0.0F, false));
        bone24.cubeList.add(new ModelBox(bone24, 33, 39, -3.9082F, -3.166F, -19.0F, 2, 1, 2, 0.0F, false));
        bone24.cubeList.add(new ModelBox(bone24, 33, 39, -3.9082F, -2.166F, -29.0F, 2, 2, 8, 0.0F, false));
        bone24.cubeList.add(new ModelBox(bone24, 33, 39, -3.9082F, -3.166F, -23.0F, 2, 1, 2, 0.0F, false));
        bone24.cubeList.add(new ModelBox(bone24, 33, 39, -3.9082F, -3.166F, -27.0F, 2, 1, 2, 0.0F, false));
        bone24.cubeList.add(new ModelBox(bone24, 33, 39, -3.9082F, -4.166F, -29.0F, 2, 1, 8, 0.0F, false));
        bone24.cubeList.add(new ModelBox(bone24, 33, 39, -3.9082F, -2.166F, -39.0F, 2, 2, 10, 0.0F, false));
        bone24.cubeList.add(new ModelBox(bone24, 33, 39, -3.9082F, -3.166F, -31.0F, 2, 1, 2, 0.0F, false));
        bone24.cubeList.add(new ModelBox(bone24, 33, 39, -3.9082F, -3.166F, -35.0F, 2, 1, 2, 0.0F, false));
        bone24.cubeList.add(new ModelBox(bone24, 33, 39, -3.9082F, -4.166F, -39.0F, 2, 1, 10, 0.0F, false));
        bone24.cubeList.add(new ModelBox(bone24, 33, 39, -3.9082F, -3.166F, -39.0F, 2, 1, 2, 0.0F, false));
        bone24.cubeList.add(new ModelBox(bone24, 13, 76, -3.3082F, -3.166F, -37.0F, 2, 1, 2, 0.0F, false));
        bone24.cubeList.add(new ModelBox(bone24, 13, 76, -3.3082F, -3.166F, -33.0F, 2, 1, 2, 0.0F, false));
        bone24.cubeList.add(new ModelBox(bone24, 13, 76, -3.3082F, -3.166F, -25.0F, 2, 1, 2, 0.0F, false));
        bone24.cubeList.add(new ModelBox(bone24, 13, 76, -3.3082F, -3.166F, -29.0F, 2, 1, 2, 0.0F, false));
        bone24.cubeList.add(new ModelBox(bone24, 13, 76, -3.3082F, -3.166F, -17.0F, 2, 1, 2, 0.0F, false));
        bone24.cubeList.add(new ModelBox(bone24, 13, 76, -3.3082F, -3.166F, -21.0F, 2, 1, 2, 0.0F, false));

        bone23 = new ModelRenderer(this);
        bone23.setRotationPoint(0.0F, 0.0F, 0.0F);
        setRotationAngle(bone23, 0.0F, 0.0F, 0.2618F);
        _30.addChild(bone23);
        bone23.cubeList.add(new ModelBox(bone23, 33, 39, 1.9082F, -2.166F, -21.0F, 2, 2, 8, 0.0F, true));
        bone23.cubeList.add(new ModelBox(bone23, 33, 39, 1.9082F, -4.166F, -21.0F, 2, 1, 8, 0.0F, true));
        bone23.cubeList.add(new ModelBox(bone23, 33, 39, 1.9082F, -3.166F, -15.0F, 2, 1, 2, 0.0F, true));
        bone23.cubeList.add(new ModelBox(bone23, 33, 39, 1.9082F, -3.166F, -19.0F, 2, 1, 2, 0.0F, true));
        bone23.cubeList.add(new ModelBox(bone23, 33, 39, 1.9082F, -4.166F, -29.0F, 2, 1, 8, 0.0F, true));
        bone23.cubeList.add(new ModelBox(bone23, 33, 39, 1.9082F, -3.166F, -27.0F, 2, 1, 2, 0.0F, true));
        bone23.cubeList.add(new ModelBox(bone23, 33, 39, 1.9082F, -3.166F, -23.0F, 2, 1, 2, 0.0F, true));
        bone23.cubeList.add(new ModelBox(bone23, 33, 39, 1.9082F, -2.166F, -29.0F, 2, 2, 8, 0.0F, true));
        bone23.cubeList.add(new ModelBox(bone23, 33, 39, 1.9082F, -4.166F, -39.0F, 2, 1, 10, 0.0F, true));
        bone23.cubeList.add(new ModelBox(bone23, 33, 39, 1.9082F, -3.166F, -35.0F, 2, 1, 2, 0.0F, true));
        bone23.cubeList.add(new ModelBox(bone23, 33, 39, 1.9082F, -3.166F, -31.0F, 2, 1, 2, 0.0F, true));
        bone23.cubeList.add(new ModelBox(bone23, 33, 39, 1.9082F, -2.166F, -39.0F, 2, 2, 10, 0.0F, true));
        bone23.cubeList.add(new ModelBox(bone23, 33, 39, 1.9082F, -3.166F, -39.0F, 2, 1, 2, 0.0F, true));
        bone23.cubeList.add(new ModelBox(bone23, 7, 81, 1.3082F, -3.166F, -37.0F, 2, 1, 2, 0.0F, true));
        bone23.cubeList.add(new ModelBox(bone23, 7, 81, 1.3082F, -3.166F, -33.0F, 2, 1, 2, 0.0F, true));
        bone23.cubeList.add(new ModelBox(bone23, 7, 81, 1.3082F, -3.166F, -29.0F, 2, 1, 2, 0.0F, true));
        bone23.cubeList.add(new ModelBox(bone23, 7, 81, 1.3082F, -3.166F, -17.0F, 2, 1, 2, 0.0F, true));
        bone23.cubeList.add(new ModelBox(bone23, 7, 81, 1.3082F, -3.166F, -21.0F, 2, 1, 2, 0.0F, true));
        bone23.cubeList.add(new ModelBox(bone23, 7, 81, 1.3082F, -3.166F, -25.0F, 2, 1, 2, 0.0F, true));

        bone27 = new ModelRenderer(this);
        bone27.setRotationPoint(0.0F, -7.0156F, -49.5F);
        setRotationAngle(bone27, 0.4363F, 0.0F, 0.0F);
        receiver.addChild(bone27);
        bone27.cubeList.add(new ModelBox(bone27, 76, 31, -1.0F, -2.2963F, 0.1742F, 2, 7, 2, 0.0F, false));

        magazine = new ModelRenderer(this);
        magazine.setRotationPoint(0.0F, -16.625F, 1.1406F);
        setRotationAngle(magazine, -0.0524F, 0.0F, 0.0F);
        SLR.addChild(magazine);
        magazine.cubeList.add(new ModelBox(magazine, 0, 0, -2.5F, 20.6064F, 0.7426F, 5, 8, 2, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 0, 0, -2.0F, 20.6064F, -0.2574F, 4, 8, 1, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 0, 0, -2.5F, 20.6064F, -2.2574F, 5, 8, 2, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 0, 0, -2.0F, 20.6064F, -3.2574F, 4, 8, 1, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 0, 0, -2.5F, 20.6064F, -5.2574F, 5, 8, 2, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 0, 0, -2.0F, 20.6064F, -6.2574F, 4, 8, 1, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 0, 0, -2.5F, 20.6064F, -8.2574F, 5, 8, 2, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 0, 0, -2.5F, 28.6064F, -8.2574F, 5, 2, 11, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 0, 0, -2.5F, 30.6064F, -3.2574F, 5, 1, 6, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 0, 0, -2.5F, 31.3425F, -0.7399F, 5, 2, 2, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 0, 0, -2.5F, 31.2939F, 0.7426F, 5, 2, 2, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 0, 0, -2.5F, 16.6064F, -8.2574F, 5, 4, 1, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 0, 0, 1.5F, 16.6064F, -5.2574F, 1, 4, 2, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 0, 0, 1.5F, 16.6064F, -2.2574F, 1, 4, 2, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 0, 0, 1.0F, 16.6064F, -7.2574F, 1, 4, 9, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 0, 0, -2.5F, 16.6064F, 1.7426F, 5, 4, 1, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 0, 0, 1.5F, 16.6064F, 0.7426F, 1, 4, 1, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 0, 0, 1.5F, 16.6064F, -7.2574F, 1, 4, 1, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 0, 0, -2.0F, 16.6064F, -7.2574F, 1, 4, 9, 0.0F, true));
        magazine.cubeList.add(new ModelBox(magazine, 0, 0, -2.5F, 16.6064F, 0.7426F, 1, 4, 1, 0.0F, true));
        magazine.cubeList.add(new ModelBox(magazine, 0, 0, -2.5F, 16.6064F, -2.2574F, 1, 4, 2, 0.0F, true));
        magazine.cubeList.add(new ModelBox(magazine, 0, 0, -2.5F, 16.6064F, -5.2574F, 1, 4, 2, 0.0F, true));
        magazine.cubeList.add(new ModelBox(magazine, 0, 0, -2.5F, 16.6064F, -7.2574F, 1, 4, 1, 0.0F, true));
        magazine.cubeList.add(new ModelBox(magazine, 7, 109, -1.0F, 14.8407F, 0.3676F, 2, 2, 1, 0.0F, true));
        magazine.cubeList.add(new ModelBox(magazine, 7, 109, -1.0F, 14.8407F, -6.1324F, 2, 2, 6, 0.0F, true));
        magazine.cubeList.add(new ModelBox(magazine, 7, 109, -0.5F, 15.3407F, -6.6324F, 1, 1, 7, 0.0F, true));
        magazine.cubeList.add(new ModelBox(magazine, 7, 109, -1.0F, 17.3407F, -6.1324F, 2, 2, 6, 0.0F, true));
        magazine.cubeList.add(new ModelBox(magazine, 7, 109, -0.5F, 17.8407F, -6.6324F, 1, 1, 7, 0.0F, true));
        magazine.cubeList.add(new ModelBox(magazine, 7, 109, -1.0F, 17.3407F, 0.3676F, 2, 2, 1, 0.0F, true));
        magazine.cubeList.add(new ModelBox(magazine, 7, 109, -1.0F, 19.8407F, -6.1324F, 2, 2, 6, 0.0F, true));
        magazine.cubeList.add(new ModelBox(magazine, 7, 109, -0.5F, 20.3407F, -6.6324F, 1, 1, 7, 0.0F, true));
        magazine.cubeList.add(new ModelBox(magazine, 7, 109, -1.0F, 19.8407F, 0.3676F, 2, 2, 1, 0.0F, true));

        bone7 = new ModelRenderer(this);
        bone7.setRotationPoint(0.0F, 29.1071F, -2.7836F);
        setRotationAngle(bone7, -0.3491F, 0.0F, 0.0F);
        magazine.addChild(bone7);
        bone7.cubeList.add(new ModelBox(bone7, 0, 0, -2.5F, 1.2811F, -4.6309F, 5, 2, 8, 0.0F, false));

        charginghandle = new ModelRenderer(this);
        charginghandle.setRotationPoint(0.0F, 0.0F, 0.0F);
        SLR.addChild(charginghandle);
        charginghandle.cubeList.add(new ModelBox(charginghandle, 4, 13, -3.2969F, -4.5F, -3.875F, 1, 3, 9, 0.0F, false));

        bone14 = new ModelRenderer(this);
        bone14.setRotationPoint(0.0F, 0.0F, 0.0F);
        setRotationAngle(bone14, 0.0F, -0.2618F, 0.0F);
        charginghandle.addChild(bone14);
        bone14.cubeList.add(new ModelBox(bone14, 45, 49, -5.4585F, -4.0F, -2.5303F, 1, 2, 1, 0.0F, false));

        bone13 = new ModelRenderer(this);
        bone13.setRotationPoint(0.0F, 0.0F, 0.0F);
        setRotationAngle(bone13, 0.0F, -0.0873F, 0.0F);
        charginghandle.addChild(bone13);
        bone13.cubeList.add(new ModelBox(bone13, 45, 46, -4.125F, -4.0F, -3.2813F, 1, 2, 1, 0.0F, false));

        stock = new ModelRenderer(this);
        stock.setRotationPoint(0.0F, 0.0F, 0.0F);
        SLR.addChild(stock);
        stock.cubeList.add(new ModelBox(stock, 85, 43, -2.5F, -5.5F, 21.0F, 5, 5, 3, 0.0F, false));
        stock.cubeList.add(new ModelBox(stock, 90, 42, -2.5F, -2.9796F, 32.6433F, 5, 6, 9, 0.0F, false));
        stock.cubeList.add(new ModelBox(stock, 90, 42, -1.5F, -0.9796F, 19.6433F, 3, 2, 6, 0.0F, false));
        stock.cubeList.add(new ModelBox(stock, 90, 42, -2.0F, 3.0204F, 37.6433F, 4, 2, 4, 0.0F, false));
        stock.cubeList.add(new ModelBox(stock, 90, 42, -0.304F, -3.7268F, 29.6433F, 1, 1, 12, 0.0F, false));
        stock.cubeList.add(new ModelBox(stock, 90, 42, -0.696F, -3.7268F, 29.6433F, 1, 1, 12, 0.0F, true));
        stock.cubeList.add(new ModelBox(stock, 12, 73, -2.0F, 3.0204F, 41.6433F, 4, 2, 1, 0.0F, false));
        stock.cubeList.add(new ModelBox(stock, 12, 73, -2.5F, -2.9796F, 41.6433F, 5, 6, 1, 0.0F, false));
        stock.cubeList.add(new ModelBox(stock, 12, 73, -0.696F, -3.7268F, 41.6433F, 1, 1, 1, 0.0F, true));
        stock.cubeList.add(new ModelBox(stock, 12, 73, -0.304F, -3.7268F, 41.6433F, 1, 1, 1, 0.0F, false));

        bone16 = new ModelRenderer(this);
        bone16.setRotationPoint(0.0F, -3.5F, 22.5F);
        setRotationAngle(bone16, -0.2618F, 0.0F, 0.0F);
        stock.addChild(bone16);
        bone16.cubeList.add(new ModelBox(bone16, 74, 32, -2.5F, -2.3201F, 0.9312F, 5, 5, 11, 0.0F, false));
        bone16.cubeList.add(new ModelBox(bone16, 74, 32, -1.5F, 2.6729F, 3.4853F, 3, 1, 8, 0.0F, false));

        bone15 = new ModelRenderer(this);
        bone15.setRotationPoint(0.0F, 0.0F, 0.0F);
        setRotationAngle(bone15, 0.2618F, 0.0F, 0.0F);
        stock.addChild(bone15);
        bone15.cubeList.add(new ModelBox(bone15, 85, 43, -2.5F, 4.9182F, 20.155F, 5, 1, 4, 0.0F, false));

        bone17 = new ModelRenderer(this);
        bone17.setRotationPoint(0.0F, 0.0F, 0.0F);
        setRotationAngle(bone17, -0.6109F, 0.0F, 0.0F);
        stock.addChild(bone17);
        bone17.cubeList.add(new ModelBox(bone17, 74, 32, -2.5F, -17.2493F, 25.4723F, 5, 1, 3, 0.0F, false));
        bone17.cubeList.add(new ModelBox(bone17, 74, 32, -1.5F, -19.6507F, 29.7152F, 3, 2, 4, 0.0F, false));

        bone19 = new ModelRenderer(this);
        bone19.setRotationPoint(2.0F, -3.9796F, 37.1433F);
        setRotationAngle(bone19, 0.0F, 0.0F, -0.9599F);
        stock.addChild(bone19);
        bone19.cubeList.add(new ModelBox(bone19, 90, 42, -1.5324F, -0.0168F, -6.5F, 1, 1, 11, 0.0F, false));
        bone19.cubeList.add(new ModelBox(bone19, 12, 73, -1.5324F, -0.0168F, 4.5F, 1, 1, 1, 0.0F, false));

        bone21 = new ModelRenderer(this);
        bone21.setRotationPoint(2.0F, -3.9796F, 37.1433F);
        setRotationAngle(bone21, 0.0F, 0.0F, -1.3963F);
        stock.addChild(bone21);
        bone21.cubeList.add(new ModelBox(bone21, 90, 42, -1.4754F, -1.2403F, -7.5F, 1, 1, 12, 0.0F, false));
        bone21.cubeList.add(new ModelBox(bone21, 12, 73, -1.4754F, -1.2403F, 4.5F, 1, 1, 1, 0.0F, false));

        bone22 = new ModelRenderer(this);
        bone22.setRotationPoint(-2.0F, -3.9796F, 37.1433F);
        setRotationAngle(bone22, 0.0F, 0.0F, 1.3963F);
        stock.addChild(bone22);
        bone22.cubeList.add(new ModelBox(bone22, 90, 42, 0.4754F, -1.2403F, -7.5F, 1, 1, 12, 0.0F, true));
        bone22.cubeList.add(new ModelBox(bone22, 12, 73, 0.4754F, -1.2403F, 4.5F, 1, 1, 1, 0.0F, true));

        bone20 = new ModelRenderer(this);
        bone20.setRotationPoint(-2.0F, -3.9796F, 37.1433F);
        setRotationAngle(bone20, 0.0F, 0.0F, 0.9599F);
        stock.addChild(bone20);
        bone20.cubeList.add(new ModelBox(bone20, 90, 42, 0.5324F, -0.0168F, -6.5F, 1, 1, 11, 0.0F, true));
        bone20.cubeList.add(new ModelBox(bone20, 12, 73, 0.5324F, -0.0168F, 4.5F, 1, 1, 1, 0.0F, true));

        ironsights = new ModelRenderer(this);
        ironsights.setRotationPoint(0.0F, 0.0F, 0.0F);
        SLR.addChild(ironsights);
        ironsights.cubeList.add(new ModelBox(ironsights, 0, 0, -2.3F, -8.1563F, 15.0F, 2, 2, 2, 0.0F, false));
        ironsights.cubeList.add(new ModelBox(ironsights, 0, 0, 0.3F, -8.1563F, 15.0F, 2, 2, 2, 0.0F, true));
        ironsights.cubeList.add(new ModelBox(ironsights, 0, 0, -0.5F, -10.1563F, 15.5F, 1, 3, 1, 0.0F, true));
        ironsights.cubeList.add(new ModelBox(ironsights, 0, 0, 0.866F, -11.5223F, 15.5F, 1, 1, 1, 0.0F, true));
        ironsights.cubeList.add(new ModelBox(ironsights, 0, 0, -1.866F, -11.5223F, 15.5F, 1, 1, 1, 0.0F, true));
        ironsights.cubeList.add(new ModelBox(ironsights, 0, 0, -0.5F, -12.8883F, 15.5F, 1, 1, 1, 0.0F, true));

        bone25 = new ModelRenderer(this);
        bone25.setRotationPoint(0.0F, 0.0F, 0.0F);
        setRotationAngle(bone25, 0.0F, 0.0F, -1.0472F);
        ironsights.addChild(bone25);
        bone25.cubeList.add(new ModelBox(bone25, 0, 0, 7.6795F, -6.0111F, 15.5F, 1, 1, 1, 0.0F, true));
        bone25.cubeList.add(new ModelBox(bone25, 0, 0, 9.0456F, -4.6451F, 15.5F, 1, 1, 1, 0.0F, true));
        bone25.cubeList.add(new ModelBox(bone25, 0, 0, 9.0456F, -7.3772F, 15.5F, 1, 1, 1, 0.0F, true));
        bone25.cubeList.add(new ModelBox(bone25, 0, 0, 10.4116F, -6.0111F, 15.5F, 1, 1, 1, 0.0F, true));

        bone26 = new ModelRenderer(this);
        bone26.setRotationPoint(0.0F, 0.0F, 0.0F);
        setRotationAngle(bone26, 0.0F, 0.0F, -0.5236F);
        ironsights.addChild(bone26);
        bone26.cubeList.add(new ModelBox(bone26, 0, 0, 5.0111F, -8.6795F, 15.5F, 1, 1, 1, 0.0F, true));
        bone26.cubeList.add(new ModelBox(bone26, 0, 0, 3.6451F, -10.0456F, 15.5F, 1, 1, 1, 0.0F, true));
        bone26.cubeList.add(new ModelBox(bone26, 0, 0, 6.3772F, -10.0456F, 15.5F, 1, 1, 1, 0.0F, true));
        bone26.cubeList.add(new ModelBox(bone26, 0, 0, 5.0111F, -11.4116F, 15.5F, 1, 1, 1, 0.0F, true));
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
        ironsights.offsetY = hasScopeAtachment(stack) ? 6.5f : 0f;
        SLR.render(1f);
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

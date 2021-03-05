package dev.toma.pubgmc.client.models.weapons;

import dev.toma.pubgmc.animation.ReloadAnimation;
import dev.toma.pubgmc.animation.ReloadAnimation.ReloadStyle;
import dev.toma.pubgmc.client.util.ModelTransformationHelper;
import dev.toma.pubgmc.common.capability.player.IPlayerData;
import dev.toma.pubgmc.common.capability.player.PlayerData;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.item.ItemStack;

public class ModelAUG extends ModelGun {

    private final ModelRenderer ironsights;
    private final ModelRenderer bone29;
    private final ModelRenderer bone30;
    private final ModelRenderer bone27;
    private final ModelRenderer bone28;
    private final ModelRenderer magazine;
    private final ModelRenderer bone11;
    private final ModelRenderer bone;
    private final ModelRenderer bone2;
    private final ModelRenderer aug;
    private final ModelRenderer bone12;
    private final ModelRenderer bone25;
    private final ModelRenderer bone26;
    private final ModelRenderer bone16;
    private final ModelRenderer bone17;
    private final ModelRenderer bone15;
    private final ModelRenderer bone14;
    private final ModelRenderer bone23;
    private final ModelRenderer bone24;
    private final ModelRenderer bone21;
    private final ModelRenderer bone22;
    private final ModelRenderer bone18;
    private final ModelRenderer bone20;
    private final ModelRenderer bone19;
    private final ModelRenderer bone9;
    private final ModelRenderer bone8;
    private final ModelRenderer bone10;
    private final ModelRenderer bone7;
    private final ModelRenderer bone3;
    private final ModelRenderer bone5;
    private final ModelRenderer bone4;
    private final ModelRenderer bone6;
    private final ModelRenderer bone13;

    public ModelAUG() {
        textureWidth = 128;
        textureHeight = 128;

        ironsights = new ModelRenderer(this);
        ironsights.setRotationPoint(-4.5F, 24.0F, 0.0F);
        ironsights.cubeList.add(new ModelBox(ironsights, 96, 30, 3.5F, -25.864F, 9.75F, 2, 2, 1, 0.0F, true));
        ironsights.cubeList.add(new ModelBox(ironsights, 96, 30, 4.0F, -26.364F, 29.75F, 1, 2, 1, 0.0F, true));
        ironsights.cubeList.add(new ModelBox(ironsights, 96, 30, 3.5F, -28.064F, 9.75F, 2, 1, 1, 0.0F, true));
        ironsights.cubeList.add(new ModelBox(ironsights, 96, 30, 5.1F, -27.364F, 9.75F, 1, 2, 1, 0.0F, false));
        ironsights.cubeList.add(new ModelBox(ironsights, 96, 30, 2.9F, -27.364F, 9.75F, 1, 2, 1, 0.0F, true));
        ironsights.cubeList.add(new ModelBox(ironsights, 96, 30, 3.0F, -24.6F, 9.25F, 3, 1, 2, 0.0F, true));
        ironsights.cubeList.add(new ModelBox(ironsights, 96, 30, 3.0F, -24.6F, 29.25F, 3, 1, 2, 0.0F, true));
        ironsights.cubeList.add(new ModelBox(ironsights, 96, 30, 2.5774F, -23.6937F, 9.25F, 1, 1, 2, 0.0F, false));
        ironsights.cubeList.add(new ModelBox(ironsights, 96, 30, 2.5774F, -23.6937F, 29.25F, 1, 1, 2, 0.0F, false));
        ironsights.cubeList.add(new ModelBox(ironsights, 96, 30, 5.4226F, -23.6937F, 9.25F, 1, 1, 2, 0.0F, true));
        ironsights.cubeList.add(new ModelBox(ironsights, 96, 30, 5.4226F, -23.6937F, 29.25F, 1, 1, 2, 0.0F, true));

        bone29 = new ModelRenderer(this);
        bone29.setRotationPoint(-12.8F, -5.9F, 0.0F);
        setRotationAngle(bone29, 0.0F, 0.0F, 0.5236F);
        ironsights.addChild(bone29);

        bone30 = new ModelRenderer(this);
        bone30.setRotationPoint(21.8F, -5.9F, 0.0F);
        setRotationAngle(bone30, 0.0F, 0.0F, -0.5236F);
        ironsights.addChild(bone30);

        bone27 = new ModelRenderer(this);
        bone27.setRotationPoint(3.0F, -26.4F, 10.25F);
        setRotationAngle(bone27, 0.0F, 0.0F, 0.4363F);
        ironsights.addChild(bone27);
        bone27.cubeList.add(new ModelBox(bone27, 96, 30, 0.7607F, 1.6314F, -1.0F, 1, 1, 2, 0.0F, false));
        bone27.cubeList.add(new ModelBox(bone27, 96, 30, 0.7607F, 1.6314F, 19.0F, 1, 1, 2, 0.0F, false));

        bone28 = new ModelRenderer(this);
        bone28.setRotationPoint(6.0F, -26.4F, 10.25F);
        setRotationAngle(bone28, 0.0F, 0.0F, -0.4363F);
        ironsights.addChild(bone28);
        bone28.cubeList.add(new ModelBox(bone28, 96, 30, -1.7607F, 1.6314F, -1.0F, 1, 1, 2, 0.0F, true));
        bone28.cubeList.add(new ModelBox(bone28, 96, 30, -1.7607F, 1.6314F, 19.0F, 1, 1, 2, 0.0F, true));

        magazine = new ModelRenderer(this);
        magazine.setRotationPoint(0.0F, 22.1F, 0.0F);
        magazine.cubeList.add(new ModelBox(magazine, 0, 0, -2.5F, -16.0F, -1.0F, 5, 14, 2, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 0, 0, -2.5F, -16.0F, 3.0F, 5, 14, 2, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 0, 0, -2.5F, -16.0F, 7.0F, 5, 14, 2, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 0, 0, -2.5F, -16.0F, 11.0F, 5, 13, 2, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 0, 0, -1.0F, -18.0F, 12.0F, 2, 2, 1, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 0, 0, -1.0F, -18.0F, -1.0F, 2, 2, 1, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 0, 109, -0.9F, -18.328F, 0.3F, 1, 1, 1, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 0, 109, -0.9F, -18.328F, 1.8F, 1, 1, 5, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 0, 109, -0.65F, -18.578F, 6.8F, 1, 1, 3, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 0, 109, -0.9F, -19.128F, 0.3F, 1, 1, 1, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 0, 109, -0.9F, -19.128F, 1.8F, 1, 1, 5, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 0, 109, -0.65F, -18.878F, 6.8F, 1, 1, 3, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 0, 109, -0.1F, -18.328F, 0.3F, 1, 1, 1, 0.0F, true));
        magazine.cubeList.add(new ModelBox(magazine, 0, 109, -0.1F, -18.328F, 1.8F, 1, 1, 5, 0.0F, true));
        magazine.cubeList.add(new ModelBox(magazine, 0, 109, -0.35F, -18.578F, 6.8F, 1, 1, 3, 0.0F, true));
        magazine.cubeList.add(new ModelBox(magazine, 0, 109, -0.1F, -19.128F, 0.3F, 1, 1, 1, 0.0F, true));
        magazine.cubeList.add(new ModelBox(magazine, 0, 109, -0.1F, -19.128F, 1.8F, 1, 1, 5, 0.0F, true));
        magazine.cubeList.add(new ModelBox(magazine, 0, 109, -0.35F, -18.878F, 6.8F, 1, 1, 3, 0.0F, true));
        magazine.cubeList.add(new ModelBox(magazine, 43, 53, -0.5F, -18.728F, 9.8F, 1, 1, 2, 0.0F, true));
        magazine.cubeList.add(new ModelBox(magazine, 0, 109, -0.5F, -18.728F, 1.3F, 1, 1, 1, 0.0F, true));
        magazine.cubeList.add(new ModelBox(magazine, 0, 0, 1.0F, -19.0F, -1.0F, 1, 3, 14, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 0, 0, -2.0F, -19.0F, -1.0F, 1, 3, 14, 0.0F, true));
        magazine.cubeList.add(new ModelBox(magazine, 0, 0, -2.0F, -16.0F, 1.0F, 4, 14, 10, 0.0F, false));

        bone11 = new ModelRenderer(this);
        bone11.setRotationPoint(-2.5F, 0.0F, 0.0F);
        setRotationAngle(bone11, 0.1745F, 0.0F, 0.0F);
        magazine.addChild(bone11);
        bone11.cubeList.add(new ModelBox(bone11, 0, 0, 0.0F, -1.1433F, 11.3625F, 5, 13, 2, 0.0F, false));
        bone11.cubeList.add(new ModelBox(bone11, 0, 0, 0.5F, -2.1433F, 1.3625F, 4, 14, 10, 0.0F, false));
        bone11.cubeList.add(new ModelBox(bone11, 0, 0, 0.0F, -1.1433F, 7.3625F, 5, 13, 2, 0.0F, false));
        bone11.cubeList.add(new ModelBox(bone11, 0, 0, 0.0F, -2.1433F, 3.3625F, 5, 14, 2, 0.0F, false));
        bone11.cubeList.add(new ModelBox(bone11, 0, 0, 0.0F, -2.1433F, -0.6375F, 5, 14, 2, 0.0F, false));

        bone = new ModelRenderer(this);
        bone.setRotationPoint(14.0F, -8.0F, 0.0F);
        setRotationAngle(bone, 0.0F, 0.0F, -0.5236F);
        magazine.addChild(bone);
        bone.cubeList.add(new ModelBox(bone, 0, 0, -6.9593F, -13.6782F, 11.0F, 1, 1, 2, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 0, 0, -6.9593F, -13.6782F, 7.0F, 1, 1, 2, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 0, 0, -6.9593F, -13.6782F, 3.0F, 1, 1, 2, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 0, 0, -6.9593F, -13.6782F, -1.0F, 1, 1, 2, 0.0F, false));

        bone2 = new ModelRenderer(this);
        bone2.setRotationPoint(-14.0F, -8.0F, 0.0F);
        setRotationAngle(bone2, 0.0F, 0.0F, 0.5236F);
        magazine.addChild(bone2);
        bone2.cubeList.add(new ModelBox(bone2, 0, 0, 5.9593F, -13.6782F, 11.0F, 1, 1, 2, 0.0F, true));
        bone2.cubeList.add(new ModelBox(bone2, 0, 0, 5.9593F, -13.6782F, 7.0F, 1, 1, 2, 0.0F, true));
        bone2.cubeList.add(new ModelBox(bone2, 0, 0, 5.9593F, -13.6782F, 3.0F, 1, 1, 2, 0.0F, true));
        bone2.cubeList.add(new ModelBox(bone2, 0, 0, 5.9593F, -13.6782F, -1.0F, 1, 1, 2, 0.0F, true));

        aug = new ModelRenderer(this);
        aug.setRotationPoint(0.0F, 24.0F, 0.0F);
        aug.cubeList.add(new ModelBox(aug, 79, 25, -3.0F, -21.0F, -2.0F, 6, 4, 16, 0.0F, false));
        aug.cubeList.add(new ModelBox(aug, 32, 6, -3.65F, -19.9F, -16.0F, 3, 2, 13, 0.0F, false));
        aug.cubeList.add(new ModelBox(aug, 32, 4, 0.65F, -19.9F, -16.0F, 3, 2, 13, 0.0F, true));
        aug.cubeList.add(new ModelBox(aug, 32, 6, -3.65F, -19.9F, -3.0F, 3, 2, 13, 0.0F, false));
        aug.cubeList.add(new ModelBox(aug, 32, 4, 0.65F, -19.9F, -3.0F, 3, 2, 13, 0.0F, true));
        aug.cubeList.add(new ModelBox(aug, 64, 10, 0.25F, -19.9F, -18.2F, 3, 4, 28, 0.0F, true));
        aug.cubeList.add(new ModelBox(aug, 64, 10, 0.25F, -15.9F, -18.2F, 3, 4, 6, 0.0F, true));
        aug.cubeList.add(new ModelBox(aug, 79, 25, -3.0F, -19.0F, 14.0F, 6, 3, 20, 0.0F, false));
        aug.cubeList.add(new ModelBox(aug, 5, 72, -3.0F, -19.0F, 34.0F, 6, 3, 1, 0.0F, false));
        aug.cubeList.add(new ModelBox(aug, 5, 72, 2.0F, -19.0F, 35.0F, 1, 3, 7, 0.0F, false));
        aug.cubeList.add(new ModelBox(aug, 5, 72, -3.0F, -19.0F, 35.0F, 1, 3, 7, 0.0F, false));
        aug.cubeList.add(new ModelBox(aug, 0, 74, -3.0F, -21.0F, 14.0F, 6, 2, 13, 0.0F, false));
        aug.cubeList.add(new ModelBox(aug, 0, 72, -3.0F, -21.0F, 27.0F, 6, 2, 8, 0.0F, false));
        aug.cubeList.add(new ModelBox(aug, 0, 72, 2.0F, -21.0F, 35.0F, 1, 2, 7, 0.0F, false));
        aug.cubeList.add(new ModelBox(aug, 0, 72, -3.0F, -21.0F, 35.0F, 1, 2, 7, 0.0F, false));
        aug.cubeList.add(new ModelBox(aug, 79, 25, -0.4142F, -16.5858F, 14.0F, 2, 2, 20, 0.0F, false));
        aug.cubeList.add(new ModelBox(aug, 11, 74, -0.4142F, -16.5858F, 34.0F, 2, 2, 1, 0.0F, false));
        aug.cubeList.add(new ModelBox(aug, 11, 74, -0.4142F, -15.5858F, 35.0F, 2, 1, 7, 0.0F, false));
        aug.cubeList.add(new ModelBox(aug, 5, 75, -1.5858F, -16.5858F, 34.0F, 2, 2, 1, 0.0F, false));
        aug.cubeList.add(new ModelBox(aug, 5, 75, -1.5858F, -15.5858F, 35.0F, 2, 1, 7, 0.0F, false));
        aug.cubeList.add(new ModelBox(aug, 33, 10, -0.5F, -19.0358F, 35.0F, 1, 1, 14, 0.0F, false));
        aug.cubeList.add(new ModelBox(aug, 33, 8, -0.5F, -21.7678F, 35.0F, 1, 1, 14, 0.0F, false));
        aug.cubeList.add(new ModelBox(aug, 33, 10, -1.866F, -20.4018F, 35.0F, 1, 1, 14, 0.0F, false));
        aug.cubeList.add(new ModelBox(aug, 33, 8, 0.866F, -20.4018F, 35.0F, 1, 1, 14, 0.0F, true));
        aug.cubeList.add(new ModelBox(aug, 33, 10, -0.5F, -19.0358F, 49.0F, 1, 1, 6, 0.0F, false));
        aug.cubeList.add(new ModelBox(aug, 33, 8, -0.5F, -21.7678F, 49.0F, 1, 1, 6, 0.0F, false));
        aug.cubeList.add(new ModelBox(aug, 33, 10, -1.866F, -20.4018F, 49.0F, 1, 1, 6, 0.0F, false));
        aug.cubeList.add(new ModelBox(aug, 33, 8, 0.866F, -20.4018F, 49.0F, 1, 1, 6, 0.0F, true));
        aug.cubeList.add(new ModelBox(aug, 79, 25, -1.5858F, -16.5858F, 14.0F, 2, 2, 20, 0.0F, false));
        aug.cubeList.add(new ModelBox(aug, 79, 25, -2.0F, -15.0858F, 20.0F, 4, 4, 5, 0.0F, false));
        aug.cubeList.add(new ModelBox(aug, 79, 25, -2.0F, -4.7361F, 17.3848F, 4, 2, 5, 0.0F, false));
        aug.cubeList.add(new ModelBox(aug, 79, 25, -1.5F, -3.4861F, 17.8848F, 3, 1, 8, 0.0F, false));
        aug.cubeList.add(new ModelBox(aug, 79, 25, -1.5F, -14.5858F, 25.0F, 3, 3, 1, 0.0F, false));
        aug.cubeList.add(new ModelBox(aug, 42, 43, -1.0F, -15.2858F, 25.75F, 2, 3, 1, 0.0F, false));
        aug.cubeList.add(new ModelBox(aug, 74, 15, -1.0F, -23.0F, -4.0F, 2, 2, 18, 0.0F, false));
        aug.cubeList.add(new ModelBox(aug, 0, 75, -1.0F, -23.0F, 14.0F, 2, 2, 13, 0.0F, false));
        aug.cubeList.add(new ModelBox(aug, 0, 75, -1.0F, -24.0F, 27.75F, 2, 1, 1, 0.0F, false));
        aug.cubeList.add(new ModelBox(aug, 0, 75, -1.0F, -24.0F, 15.75F, 2, 1, 1, 0.0F, false));
        aug.cubeList.add(new ModelBox(aug, 0, 75, -1.0F, -24.0F, 3.75F, 2, 1, 1, 0.0F, false));
        aug.cubeList.add(new ModelBox(aug, 0, 75, -1.0F, -24.0F, 29.75F, 2, 1, 1, 0.0F, false));
        aug.cubeList.add(new ModelBox(aug, 0, 75, -1.0F, -24.0F, 17.75F, 2, 1, 1, 0.0F, false));
        aug.cubeList.add(new ModelBox(aug, 0, 75, -1.0F, -24.0F, 5.75F, 2, 1, 1, 0.0F, false));
        aug.cubeList.add(new ModelBox(aug, 0, 75, -1.0F, -24.0F, 31.75F, 2, 1, 1, 0.0F, false));
        aug.cubeList.add(new ModelBox(aug, 0, 75, -1.0F, -24.0F, 19.75F, 2, 1, 1, 0.0F, false));
        aug.cubeList.add(new ModelBox(aug, 0, 75, -1.0F, -24.0F, 7.75F, 2, 1, 1, 0.0F, false));
        aug.cubeList.add(new ModelBox(aug, 0, 75, -1.0F, -24.0F, 25.75F, 2, 1, 1, 0.0F, false));
        aug.cubeList.add(new ModelBox(aug, 0, 75, -1.0F, -24.0F, 13.75F, 2, 1, 1, 0.0F, false));
        aug.cubeList.add(new ModelBox(aug, 0, 75, -1.0F, -24.0F, 1.75F, 2, 1, 1, 0.0F, false));
        aug.cubeList.add(new ModelBox(aug, 0, 75, -1.0F, -24.0F, 23.75F, 2, 1, 1, 0.0F, false));
        aug.cubeList.add(new ModelBox(aug, 0, 75, -1.0F, -24.0F, 11.75F, 2, 1, 1, 0.0F, false));
        aug.cubeList.add(new ModelBox(aug, 0, 75, -1.0F, -24.0F, 21.75F, 2, 1, 1, 0.0F, false));
        aug.cubeList.add(new ModelBox(aug, 0, 75, -1.0F, -24.0F, 9.75F, 2, 1, 1, 0.0F, false));
        aug.cubeList.add(new ModelBox(aug, 0, 75, -1.5F, -23.4F, 20.75F, 3, 1, 12, 0.0F, false));
        aug.cubeList.add(new ModelBox(aug, 0, 75, -1.5F, -23.4F, 8.75F, 3, 1, 12, 0.0F, false));
        aug.cubeList.add(new ModelBox(aug, 0, 75, -1.5F, -23.4F, 1.75F, 3, 1, 7, 0.0F, false));
        aug.cubeList.add(new ModelBox(aug, 0, 75, -1.0F, -23.8F, 32.1F, 2, 1, 1, 0.0F, false));
        aug.cubeList.add(new ModelBox(aug, 0, 75, -1.866F, -23.5F, 20.75F, 2, 0, 12, 0.0F, false));
        aug.cubeList.add(new ModelBox(aug, 0, 75, -1.866F, -23.5F, 8.75F, 2, 0, 12, 0.0F, false));
        aug.cubeList.add(new ModelBox(aug, 0, 75, -1.866F, -23.5F, 1.75F, 2, 0, 7, 0.0F, false));
        aug.cubeList.add(new ModelBox(aug, 0, 75, -0.134F, -23.5F, 20.75F, 2, 0, 12, 0.0F, true));
        aug.cubeList.add(new ModelBox(aug, 0, 75, -0.134F, -23.5F, 8.75F, 2, 0, 12, 0.0F, true));
        aug.cubeList.add(new ModelBox(aug, 0, 75, -0.134F, -23.5F, 1.75F, 2, 0, 7, 0.0F, true));
        aug.cubeList.add(new ModelBox(aug, 0, 75, -0.134F, -23.5F, 26.75F, 2, 0, 1, 0.0F, true));
        aug.cubeList.add(new ModelBox(aug, 0, 75, -1.0F, -23.0F, 27.0F, 2, 2, 8, 0.0F, false));
        aug.cubeList.add(new ModelBox(aug, 0, 75, -1.0F, -23.0F, 35.0F, 2, 1, 7, 0.0F, false));
        aug.cubeList.add(new ModelBox(aug, 72, 19, -1.0F, -23.0F, -20.0F, 2, 1, 16, 0.0F, false));
        aug.cubeList.add(new ModelBox(aug, 79, 25, -3.0F, -21.0F, -16.0F, 6, 3, 14, 0.0F, false));
        aug.cubeList.add(new ModelBox(aug, 79, 25, -3.0F, -21.0F, -20.0F, 6, 4, 4, 0.0F, false));
        aug.cubeList.add(new ModelBox(aug, 7, 71, -3.0F, -21.0F, -22.0F, 6, 4, 2, 0.0F, false));
        aug.cubeList.add(new ModelBox(aug, 87, 17, -3.0F, -17.0F, -20.0F, 6, 12, 4, 0.0F, false));
        aug.cubeList.add(new ModelBox(aug, 11, 73, -3.0F, -17.0F, -21.0F, 6, 12, 1, 0.0F, false));
        aug.cubeList.add(new ModelBox(aug, 87, 17, -3.0F, -18.0F, -16.0F, 6, 6, 2, 0.0F, false));
        aug.cubeList.add(new ModelBox(aug, 87, 17, -3.0F, -18.0F, -14.0F, 6, 4, 1, 0.0F, false));
        aug.cubeList.add(new ModelBox(aug, 87, 17, -3.0F, -18.0F, -13.0F, 6, 3, 2, 0.0F, false));
        aug.cubeList.add(new ModelBox(aug, 87, 17, -3.0F, -18.0F, -11.0F, 6, 2, 3, 0.0F, false));
        aug.cubeList.add(new ModelBox(aug, 87, 17, -3.0F, -18.0F, -8.0F, 6, 1, 3, 0.0F, false));
        aug.cubeList.add(new ModelBox(aug, 87, 17, -2.5F, -7.0F, -16.0F, 5, 2, 1, 0.0F, false));
        aug.cubeList.add(new ModelBox(aug, 87, 17, -2.0F, -5.4805F, -20.9766F, 4, 1, 6, 0.0F, false));
        aug.cubeList.add(new ModelBox(aug, 87, 17, -2.0F, -7.0F, -15.2695F, 4, 2, 1, 0.0F, false));
        aug.cubeList.add(new ModelBox(aug, 72, 12, -3.0F, -17.0F, -1.0F, 1, 5, 14, 0.0F, false));
        aug.cubeList.add(new ModelBox(aug, 5, 70, -3.5F, -17.0F, 8.0F, 1, 3, 2, 0.0F, false));
        aug.cubeList.add(new ModelBox(aug, 5, 70, -3.2F, -21.0F, -21.0F, 1, 16, 2, 0.0F, false));
        aug.cubeList.add(new ModelBox(aug, 5, 70, 2.2F, -21.0F, -21.0F, 1, 16, 2, 0.0F, true));
        aug.cubeList.add(new ModelBox(aug, 50, 38, -3.1F, -17.3F, 34.2F, 1, 1, 1, 0.0F, false));
        aug.cubeList.add(new ModelBox(aug, 50, 38, -3.1F, -17.3F, 40.7F, 1, 1, 1, 0.0F, false));
        aug.cubeList.add(new ModelBox(aug, 50, 38, 2.1F, -17.3F, 40.7F, 1, 1, 1, 0.0F, true));
        aug.cubeList.add(new ModelBox(aug, 50, 38, 2.1F, -17.3F, 34.2F, 1, 1, 1, 0.0F, true));
        aug.cubeList.add(new ModelBox(aug, 50, 38, -3.1F, -20.5F, 34.2F, 1, 1, 1, 0.0F, false));
        aug.cubeList.add(new ModelBox(aug, 50, 38, -3.1F, -20.5F, 40.7F, 1, 1, 1, 0.0F, false));
        aug.cubeList.add(new ModelBox(aug, 50, 38, 2.1F, -20.5F, 40.7F, 1, 1, 1, 0.0F, true));
        aug.cubeList.add(new ModelBox(aug, 50, 38, -3.1F, -20.5F, 25.2F, 1, 1, 1, 0.0F, false));
        aug.cubeList.add(new ModelBox(aug, 50, 38, 2.1F, -20.5F, 25.2F, 1, 1, 1, 0.0F, true));
        aug.cubeList.add(new ModelBox(aug, 50, 38, -3.1F, -20.5F, 16.2F, 1, 1, 1, 0.0F, false));
        aug.cubeList.add(new ModelBox(aug, 50, 38, 2.1F, -20.5F, 16.2F, 1, 1, 1, 0.0F, true));
        aug.cubeList.add(new ModelBox(aug, 50, 38, 2.1F, -20.5F, 34.2F, 1, 1, 1, 0.0F, true));
        aug.cubeList.add(new ModelBox(aug, 72, 12, 2.0F, -17.0F, -1.0F, 1, 5, 14, 0.0F, true));
        aug.cubeList.add(new ModelBox(aug, 88, 31, -3.0F, -17.0F, -2.0F, 6, 5, 1, 0.0F, true));
        aug.cubeList.add(new ModelBox(aug, 110, 37, -3.0F, -17.0F, 13.0F, 6, 5, 1, 0.0F, true));

        bone12 = new ModelRenderer(this);
        bone12.setRotationPoint(4.0F, 0.0F, 0.0F);
        setRotationAngle(bone12, 0.0F, 0.0F, -0.7854F);
        aug.addChild(bone12);
        bone12.cubeList.add(new ModelBox(bone12, 64, 24, 8.6066F, -14.0208F, 14.0F, 2, 2, 20, 0.0F, false));
        bone12.cubeList.add(new ModelBox(bone12, 8, 77, 8.6066F, -14.0208F, 34.0F, 2, 2, 1, 0.0F, false));
        bone12.cubeList.add(new ModelBox(bone12, 8, 77, 8.6066F, -13.0208F, 35.0F, 2, 1, 7, 0.0F, false));
        bone12.cubeList.add(new ModelBox(bone12, 7, 75, 6.364F, -16.2635F, 34.0F, 2, 2, 1, 0.0F, false));
        bone12.cubeList.add(new ModelBox(bone12, 7, 75, 6.364F, -16.2635F, 35.0F, 1, 2, 7, 0.0F, false));
        bone12.cubeList.add(new ModelBox(bone12, 79, 25, 6.364F, -16.2635F, 14.0F, 2, 2, 20, 0.0F, false));

        bone25 = new ModelRenderer(this);
        bone25.setRotationPoint(4.0858F, 0.0F, 0.0F);
        setRotationAngle(bone25, 0.0F, 0.0F, -0.5236F);
        aug.addChild(bone25);
        bone25.cubeList.add(new ModelBox(bone25, 33, 8, 5.9125F, -18.4123F, 35.0F, 1, 1, 14, 0.0F, false));
        bone25.cubeList.add(new ModelBox(bone25, 33, 10, 4.5465F, -19.7784F, 35.0F, 1, 1, 14, 0.0F, false));
        bone25.cubeList.add(new ModelBox(bone25, 33, 8, 7.2785F, -19.7784F, 35.0F, 1, 1, 14, 0.0F, false));
        bone25.cubeList.add(new ModelBox(bone25, 33, 10, 5.9125F, -21.1444F, 35.0F, 1, 1, 14, 0.0F, false));
        bone25.cubeList.add(new ModelBox(bone25, 33, 8, 5.9125F, -18.4123F, 49.0F, 1, 1, 6, 0.0F, false));
        bone25.cubeList.add(new ModelBox(bone25, 33, 10, 4.5465F, -19.7784F, 49.0F, 1, 1, 6, 0.0F, false));
        bone25.cubeList.add(new ModelBox(bone25, 33, 8, 7.2785F, -19.7784F, 49.0F, 1, 1, 6, 0.0F, false));
        bone25.cubeList.add(new ModelBox(bone25, 33, 10, 5.9125F, -21.1444F, 49.0F, 1, 1, 6, 0.0F, false));

        bone26 = new ModelRenderer(this);
        bone26.setRotationPoint(-4.0858F, 0.0F, 0.0F);
        setRotationAngle(bone26, 0.0F, 0.0F, 0.5236F);
        aug.addChild(bone26);
        bone26.cubeList.add(new ModelBox(bone26, 33, 10, -6.9125F, -18.4123F, 35.0F, 1, 1, 14, 0.0F, true));
        bone26.cubeList.add(new ModelBox(bone26, 33, 8, -5.5465F, -19.7784F, 35.0F, 1, 1, 14, 0.0F, true));
        bone26.cubeList.add(new ModelBox(bone26, 33, 8, -6.9125F, -21.1444F, 35.0F, 1, 1, 14, 0.0F, true));
        bone26.cubeList.add(new ModelBox(bone26, 33, 10, -8.2785F, -19.7784F, 35.0F, 1, 1, 14, 0.0F, true));
        bone26.cubeList.add(new ModelBox(bone26, 33, 10, -6.9125F, -18.4123F, 49.0F, 1, 1, 6, 0.0F, true));
        bone26.cubeList.add(new ModelBox(bone26, 33, 8, -5.5465F, -19.7784F, 49.0F, 1, 1, 6, 0.0F, true));
        bone26.cubeList.add(new ModelBox(bone26, 33, 8, -6.9125F, -21.1444F, 49.0F, 1, 1, 6, 0.0F, true));
        bone26.cubeList.add(new ModelBox(bone26, 33, 10, -8.2785F, -19.7784F, 49.0F, 1, 1, 6, 0.0F, true));

        bone16 = new ModelRenderer(this);
        bone16.setRotationPoint(0.0F, -2.9861F, 21.8848F);
        setRotationAngle(bone16, 0.5236F, 0.0F, 0.0F);
        aug.addChild(bone16);
        bone16.cubeList.add(new ModelBox(bone16, 79, 25, -1.5F, 1.433F, 3.2141F, 3, 1, 2, 0.0F, false));

        bone17 = new ModelRenderer(this);
        bone17.setRotationPoint(0.0F, -2.9861F, 21.8848F);
        setRotationAngle(bone17, 1.0472F, 0.0F, 0.0F);
        aug.addChild(bone17);
        bone17.cubeList.add(new ModelBox(bone17, 79, 25, -1.5F, 3.7141F, 3.299F, 3, 1, 13, 0.0F, false));

        bone15 = new ModelRenderer(this);
        bone15.setRotationPoint(4.0858F, 0.0F, 0.0F);
        setRotationAngle(bone15, 0.6109F, 0.0F, 0.0F);
        aug.addChild(bone15);
        bone15.cubeList.add(new ModelBox(bone15, 79, 25, -5.5858F, 4.4225F, 24.9433F, 3, 1, 3, 0.0F, false));

        bone14 = new ModelRenderer(this);
        bone14.setRotationPoint(3.5858F, -4.5F, -3.25F);
        setRotationAngle(bone14, -0.2618F, 0.0F, 0.0F);
        aug.addChild(bone14);
        bone14.cubeList.add(new ModelBox(bone14, 79, 25, -5.5858F, -15.5687F, 19.8706F, 4, 10, 5, 0.0F, false));

        bone23 = new ModelRenderer(this);
        bone23.setRotationPoint(0.0F, -23.6F, 34.4F);
        setRotationAngle(bone23, 0.6109F, 0.0F, 0.0F);
        aug.addChild(bone23);

        bone24 = new ModelRenderer(this);
        bone24.setRotationPoint(0.0F, -23.6F, 32.15F);
        setRotationAngle(bone24, 0.6109F, 0.0F, 0.0F);
        aug.addChild(bone24);

        bone21 = new ModelRenderer(this);
        bone21.setRotationPoint(0.0F, -27.5F, 29.25F);
        setRotationAngle(bone21, 0.0F, 0.0F, -0.5236F);
        aug.addChild(bone21);
        bone21.cubeList.add(new ModelBox(bone21, 0, 75, -3.616F, 2.5311F, -1.5F, 1, 1, 1, 0.0F, false));
        bone21.cubeList.add(new ModelBox(bone21, 0, 75, -3.616F, 2.5311F, -13.5F, 1, 1, 1, 0.0F, false));
        bone21.cubeList.add(new ModelBox(bone21, 0, 75, -3.616F, 2.5311F, -25.5F, 1, 1, 1, 0.0F, false));
        bone21.cubeList.add(new ModelBox(bone21, 0, 75, -3.616F, 2.5311F, 0.5F, 1, 1, 1, 0.0F, false));
        bone21.cubeList.add(new ModelBox(bone21, 0, 75, -3.616F, 2.5311F, -11.5F, 1, 1, 1, 0.0F, false));
        bone21.cubeList.add(new ModelBox(bone21, 0, 75, -3.616F, 2.5311F, -23.5F, 1, 1, 1, 0.0F, false));
        bone21.cubeList.add(new ModelBox(bone21, 0, 75, -3.616F, 2.5311F, 2.5F, 1, 1, 1, 0.0F, false));
        bone21.cubeList.add(new ModelBox(bone21, 0, 75, -3.616F, 2.5311F, -9.5F, 1, 1, 1, 0.0F, false));
        bone21.cubeList.add(new ModelBox(bone21, 0, 75, -3.616F, 2.5311F, -21.5F, 1, 1, 1, 0.0F, false));
        bone21.cubeList.add(new ModelBox(bone21, 0, 75, -3.616F, 2.5311F, -3.5F, 1, 1, 1, 0.0F, false));
        bone21.cubeList.add(new ModelBox(bone21, 0, 75, -3.616F, 2.5311F, -15.5F, 1, 1, 1, 0.0F, false));
        bone21.cubeList.add(new ModelBox(bone21, 0, 75, -3.616F, 2.5311F, -27.5F, 1, 1, 1, 0.0F, false));
        bone21.cubeList.add(new ModelBox(bone21, 0, 75, -3.616F, 2.5311F, -5.5F, 1, 1, 1, 0.0F, false));
        bone21.cubeList.add(new ModelBox(bone21, 0, 75, -3.616F, 2.5311F, -17.5F, 1, 1, 1, 0.0F, false));
        bone21.cubeList.add(new ModelBox(bone21, 0, 75, -3.616F, 2.5311F, -7.5F, 1, 1, 1, 0.0F, false));
        bone21.cubeList.add(new ModelBox(bone21, 0, 75, -3.616F, 2.5311F, -19.5F, 1, 1, 1, 0.0F, false));
        bone21.cubeList.add(new ModelBox(bone21, 0, 75, -3.616F, 2.5311F, -0.5F, 0, 1, 1, 0.0F, false));
        bone21.cubeList.add(new ModelBox(bone21, 0, 75, -3.616F, 2.5311F, -8.5F, 0, 1, 12, 0.0F, false));
        bone21.cubeList.add(new ModelBox(bone21, 0, 75, -3.616F, 2.5311F, -20.5F, 0, 1, 12, 0.0F, false));
        bone21.cubeList.add(new ModelBox(bone21, 0, 75, -3.616F, 2.5311F, -27.5F, 0, 1, 7, 0.0F, false));

        bone22 = new ModelRenderer(this);
        bone22.setRotationPoint(0.0F, -27.5F, 29.25F);
        setRotationAngle(bone22, 0.0F, 0.0F, 0.5236F);
        aug.addChild(bone22);
        bone22.cubeList.add(new ModelBox(bone22, 0, 75, 2.616F, 2.5311F, -1.5F, 1, 1, 1, 0.0F, true));
        bone22.cubeList.add(new ModelBox(bone22, 0, 75, 2.616F, 2.5311F, -13.5F, 1, 1, 1, 0.0F, true));
        bone22.cubeList.add(new ModelBox(bone22, 0, 75, 2.616F, 2.5311F, -25.5F, 1, 1, 1, 0.0F, true));
        bone22.cubeList.add(new ModelBox(bone22, 0, 75, 2.616F, 2.5311F, 0.5F, 1, 1, 1, 0.0F, true));
        bone22.cubeList.add(new ModelBox(bone22, 0, 75, 2.616F, 2.5311F, -11.5F, 1, 1, 1, 0.0F, true));
        bone22.cubeList.add(new ModelBox(bone22, 0, 75, 2.616F, 2.5311F, -23.5F, 1, 1, 1, 0.0F, true));
        bone22.cubeList.add(new ModelBox(bone22, 0, 75, 2.616F, 2.5311F, 2.5F, 1, 1, 1, 0.0F, true));
        bone22.cubeList.add(new ModelBox(bone22, 0, 75, 2.616F, 2.5311F, -9.5F, 1, 1, 1, 0.0F, true));
        bone22.cubeList.add(new ModelBox(bone22, 0, 75, 2.616F, 2.5311F, -21.5F, 1, 1, 1, 0.0F, true));
        bone22.cubeList.add(new ModelBox(bone22, 0, 75, 2.616F, 2.5311F, -3.5F, 1, 1, 1, 0.0F, true));
        bone22.cubeList.add(new ModelBox(bone22, 0, 75, 2.616F, 2.5311F, -15.5F, 1, 1, 1, 0.0F, true));
        bone22.cubeList.add(new ModelBox(bone22, 0, 75, 2.616F, 2.5311F, -27.5F, 1, 1, 1, 0.0F, true));
        bone22.cubeList.add(new ModelBox(bone22, 0, 75, 2.616F, 2.5311F, -5.5F, 1, 1, 1, 0.0F, true));
        bone22.cubeList.add(new ModelBox(bone22, 0, 75, 2.616F, 2.5311F, -17.5F, 1, 1, 1, 0.0F, true));
        bone22.cubeList.add(new ModelBox(bone22, 0, 75, 2.616F, 2.5311F, -7.5F, 1, 1, 1, 0.0F, true));
        bone22.cubeList.add(new ModelBox(bone22, 0, 75, 2.616F, 2.5311F, -19.5F, 1, 1, 1, 0.0F, true));
        bone22.cubeList.add(new ModelBox(bone22, 0, 75, 3.616F, 2.5311F, -8.5F, 0, 1, 12, 0.0F, true));
        bone22.cubeList.add(new ModelBox(bone22, 0, 75, 3.616F, 2.5311F, -20.5F, 0, 1, 12, 0.0F, true));
        bone22.cubeList.add(new ModelBox(bone22, 0, 75, 3.616F, 2.5311F, -27.5F, 0, 1, 7, 0.0F, true));

        bone18 = new ModelRenderer(this);
        bone18.setRotationPoint(0.0F, -22.0F, 28.5F);
        setRotationAngle(bone18, 0.0F, 0.0F, 0.7854F);
        aug.addChild(bone18);
        bone18.cubeList.add(new ModelBox(bone18, 0, 73, 0.0F, -1.4142F, -14.5F, 2, 2, 12, 0.0F, false));
        bone18.cubeList.add(new ModelBox(bone18, 0, 73, 0.0F, -1.4142F, -2.5F, 2, 2, 9, 0.0F, false));
        bone18.cubeList.add(new ModelBox(bone18, 0, 73, 0.0F, -1.4142F, 6.5F, 1, 1, 7, 0.0F, false));
        bone18.cubeList.add(new ModelBox(bone18, 0, 73, 1.0F, -1.4142F, 6.5F, 1, 1, 1, 0.0F, false));
        bone18.cubeList.add(new ModelBox(bone18, 0, 73, 1.0F, -1.4142F, 9.5F, 1, 1, 1, 0.0F, false));
        bone18.cubeList.add(new ModelBox(bone18, 0, 73, 1.0F, -1.4142F, 12.5F, 1, 1, 1, 0.0F, false));
        bone18.cubeList.add(new ModelBox(bone18, 0, 73, 1.8284F, -1.4142F, -14.5F, 1, 1, 12, 0.0F, false));
        bone18.cubeList.add(new ModelBox(bone18, 0, 73, 1.8284F, -1.4142F, -2.5F, 1, 1, 9, 0.0F, false));
        bone18.cubeList.add(new ModelBox(bone18, 0, 73, 1.8284F, -1.4142F, 6.5F, 1, 1, 7, 0.0F, false));
        bone18.cubeList.add(new ModelBox(bone18, 0, 68, -1.4142F, 0.0F, -14.5F, 2, 2, 4, 0.0F, false));
        bone18.cubeList.add(new ModelBox(bone18, 0, 68, -1.4142F, 0.0F, -10.5F, 1, 1, 13, 0.0F, false));
        bone18.cubeList.add(new ModelBox(bone18, 34, 11, -1.1642F, 1.0F, -10.5F, 1, 1, 13, 0.0F, false));
        bone18.cubeList.add(new ModelBox(bone18, 34, 11, -3.1642F, 0.9F, -3.5F, 2, 1, 2, 0.0F, false));
        bone18.cubeList.add(new ModelBox(bone18, 34, 11, -4.1642F, 0.9F, -2.5F, 1, 1, 2, 0.0F, false));
        bone18.cubeList.add(new ModelBox(bone18, 0, 68, -1.4142F, 0.0F, 2.5F, 2, 2, 4, 0.0F, false));
        bone18.cubeList.add(new ModelBox(bone18, 0, 68, -1.4142F, 0.0F, 6.5F, 1, 1, 7, 0.0F, false));
        bone18.cubeList.add(new ModelBox(bone18, 0, 68, -1.4142F, 1.0F, 6.5F, 1, 1, 1, 0.0F, false));
        bone18.cubeList.add(new ModelBox(bone18, 0, 68, -1.4142F, 1.0F, 9.5F, 1, 1, 1, 0.0F, false));
        bone18.cubeList.add(new ModelBox(bone18, 0, 68, -1.4142F, 1.0F, 12.5F, 1, 1, 1, 0.0F, false));
        bone18.cubeList.add(new ModelBox(bone18, 4, 68, -1.4142F, 1.8284F, -14.5F, 1, 1, 12, 0.0F, false));
        bone18.cubeList.add(new ModelBox(bone18, 4, 68, -1.4142F, 1.8284F, -2.5F, 1, 1, 9, 0.0F, false));
        bone18.cubeList.add(new ModelBox(bone18, 4, 68, -1.4142F, 1.8284F, 6.5F, 1, 1, 7, 0.0F, false));

        bone20 = new ModelRenderer(this);
        bone20.setRotationPoint(-3.6642F, 1.4F, -2.0F);
        setRotationAngle(bone20, 0.0F, -0.3491F, 0.0F);
        bone18.addChild(bone20);
        bone20.cubeList.add(new ModelBox(bone20, 34, 11, -0.0171F, -0.5F, -0.7615F, 1, 1, 2, 0.0F, false));

        bone19 = new ModelRenderer(this);
        bone19.setRotationPoint(-4.4142F, 1.4F, -2.0F);
        setRotationAngle(bone19, 0.0F, -0.7854F, 0.0F);
        bone18.addChild(bone19);
        bone19.cubeList.add(new ModelBox(bone19, 34, 11, -0.1768F, -0.5F, -1.9445F, 1, 1, 1, 0.0F, false));
        bone19.cubeList.add(new ModelBox(bone19, 34, 11, -0.1768F, -0.5F, -1.5303F, 1, 1, 1, 0.0F, false));

        bone9 = new ModelRenderer(this);
        bone9.setRotationPoint(3.5F, 0.0F, 0.0F);
        setRotationAngle(bone9, 0.7854F, 0.0F, 0.0F);
        aug.addChild(bone9);
        bone9.cubeList.add(new ModelBox(bone9, 2, 71, -4.5F, -30.1581F, -0.9107F, 2, 1, 3, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 2, 71, -5.0F, -30.0255F, -0.4687F, 3, 1, 2, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 2, 71, -5.5F, -29.6333F, -0.7946F, 4, 1, 2, 0.0F, false));

        bone8 = new ModelRenderer(this);
        bone8.setRotationPoint(13.8995F, -19.299F, -13.0F);
        setRotationAngle(bone8, 0.0F, 0.0F, -0.6109F);
        aug.addChild(bone8);
        bone8.cubeList.add(new ModelBox(bone8, 72, 19, -12.8676F, -11.0865F, -7.0F, 2, 1, 17, 0.0F, false));
        bone8.cubeList.add(new ModelBox(bone8, 72, 19, -12.8676F, -11.0865F, 10.0F, 2, 1, 17, 0.0F, false));
        bone8.cubeList.add(new ModelBox(bone8, 72, 19, -9.4438F, -10.4305F, -7.0F, 1, 2, 17, 0.0F, false));
        bone8.cubeList.add(new ModelBox(bone8, 72, 19, -9.4438F, -10.4305F, 10.0F, 1, 2, 17, 0.0F, false));

        bone10 = new ModelRenderer(this);
        bone10.setRotationPoint(-13.8995F, -19.299F, -13.0F);
        setRotationAngle(bone10, 0.0F, 0.0F, 0.6109F);
        aug.addChild(bone10);
        bone10.cubeList.add(new ModelBox(bone10, 72, 19, 8.4438F, -10.4305F, -7.0F, 1, 2, 17, 0.0F, true));
        bone10.cubeList.add(new ModelBox(bone10, 72, 19, 8.4438F, -10.4305F, 10.0F, 1, 2, 17, 0.0F, true));
        bone10.cubeList.add(new ModelBox(bone10, 72, 19, 10.8676F, -11.0865F, -7.0F, 2, 1, 17, 0.0F, true));
        bone10.cubeList.add(new ModelBox(bone10, 72, 19, 10.8676F, -11.0865F, 10.0F, 2, 1, 17, 0.0F, true));

        bone7 = new ModelRenderer(this);
        bone7.setRotationPoint(3.5F, 0.0F, 0.0F);
        setRotationAngle(bone7, 0.0873F, 0.0F, 0.0F);
        aug.addChild(bone7);
        bone7.cubeList.add(new ModelBox(bone7, 6, 72, -6.5F, -18.8527F, -20.4346F, 6, 12, 1, 0.0F, false));

        bone3 = new ModelRenderer(this);
        bone3.setRotationPoint(0.0F, -15.0391F, -10.8008F);
        setRotationAngle(bone3, 0.3491F, 0.0F, 0.0F);
        aug.addChild(bone3);
        bone3.cubeList.add(new ModelBox(bone3, 79, 25, -1.5858F, 2.4774F, -2.7201F, 2, 2, 13, 0.0F, false));
        bone3.cubeList.add(new ModelBox(bone3, 79, 25, -3.0F, -0.9369F, -2.7201F, 6, 4, 13, 0.0F, false));
        bone3.cubeList.add(new ModelBox(bone3, 79, 25, -0.4142F, 2.4774F, -2.7201F, 2, 2, 13, 0.0F, true));

        bone5 = new ModelRenderer(this);
        bone5.setRotationPoint(0.0F, 0.0F, 0.0F);
        setRotationAngle(bone5, 0.0F, 0.0F, -0.7854F);
        bone3.addChild(bone5);
        bone5.cubeList.add(new ModelBox(bone5, 79, 25, -4.2873F, 0.0447F, -2.7201F, 1, 2, 13, 0.0F, false));
        bone5.cubeList.add(new ModelBox(bone5, 79, 25, -2.0447F, 3.2873F, -2.7201F, 2, 1, 13, 0.0F, false));

        bone4 = new ModelRenderer(this);
        bone4.setRotationPoint(0.0F, -11.2188F, -16.7109F);
        setRotationAngle(bone4, 0.6109F, 0.0F, 0.0F);
        aug.addChild(bone4);
        bone4.cubeList.add(new ModelBox(bone4, 79, 25, -1.5858F, 2.4774F, -2.7201F, 2, 2, 13, 0.0F, false));
        bone4.cubeList.add(new ModelBox(bone4, 79, 25, -3.0F, -0.9369F, -2.7201F, 6, 4, 13, 0.0F, false));
        bone4.cubeList.add(new ModelBox(bone4, 79, 25, -0.4142F, 2.4774F, -2.7201F, 2, 2, 13, 0.0F, true));

        bone6 = new ModelRenderer(this);
        bone6.setRotationPoint(0.0F, 0.0F, 0.0F);
        setRotationAngle(bone6, 0.0F, 0.0F, -0.7854F);
        bone4.addChild(bone6);
        bone6.cubeList.add(new ModelBox(bone6, 79, 25, -4.2873F, 0.0447F, -2.7201F, 1, 2, 13, 0.0F, false));
        bone6.cubeList.add(new ModelBox(bone6, 79, 25, -2.0447F, 3.2873F, -2.7201F, 2, 1, 13, 0.0F, false));

        bone13 = new ModelRenderer(this);
        bone13.setRotationPoint(-3.0F, 0.0F, 0.0F);
        setRotationAngle(bone13, -0.5236F, 0.0F, 0.0F);
        aug.addChild(bone13);
        bone13.cubeList.add(new ModelBox(bone13, 110, 37, 0.0F, -22.3923F, 4.1244F, 6, 5, 2, 0.0F, true));
        this.initAnimations();
    }

    @Override
    public String textureName() {
        return "akm";
    }

    @Override
    public void initAnimations() {
        initAimAnimation(-0.56f, 0.2825f, 0.3f, 2f);
        initAimingAnimationStates(0.2825f, 0.25f, 0.22f);
        reloadAnimation = new ReloadAnimation(magazine, ReloadStyle.MAGAZINE).initMovement(DEFAULT_PART_ANIMATION, 180)
                .withSpeed(0.9f);
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }

    @Override
    public void render(ItemStack stack, ItemCameraTransforms.TransformType transformType) {
        EntityPlayerSP player = Minecraft.getMinecraft().player;
        IPlayerData data = PlayerData.get(player);
        if (data != null) {
            GlStateManager.pushMatrix();
            {
                renderAUG(data.isAiming(), stack);
            }
            GlStateManager.popMatrix();
        }
    }

    private void renderAUG(boolean aim, ItemStack stack) {
        GlStateManager.pushMatrix();
        ModelTransformationHelper.defaultARTransform();
        GlStateManager.rotate(180, 0, 1, 0);
        GlStateManager.translate(0.0, 3.85, -6.0);
        aug.render(1f);
        magazine.render(1f);
        if (!hasScopeAtachment(stack)) ironsights.render(1f);
        GlStateManager.popMatrix();
        /*
        renderARSilencer(-0.025, -1.525, 29, 1f, stack);
        renderVerticalGrip(0, -5, 13, 0.6F, stack);
        renderAngledGrip(0, 3, 13, 0.6F, stack);
        renderRedDot(0.05, 11.8, 9, 0.8F, stack);
        renderHolo(-0.1, 8, -1, 0.8F, stack);
        renderScope2X(0, 2, 0, 1f, stack);
        renderScope4X(0, 4, -2, 1f, stack);
        */
    }
}

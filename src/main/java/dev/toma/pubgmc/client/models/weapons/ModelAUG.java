package dev.toma.pubgmc.client.models.weapons;

import dev.toma.pubgmc.client.animation.AnimationElement;
import dev.toma.pubgmc.client.util.ModelTransformationHelper;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.item.ItemStack;

public class ModelAUG extends ModelGun {

    private final ModelRenderer slide;
    private final ModelRenderer bone20;
    private final ModelRenderer bone19;
    private final ModelRenderer ironsights;
    private final ModelRenderer bone29;
    private final ModelRenderer bone30;
    private final ModelRenderer bone31;
    private final ModelRenderer bone32;
    private final ModelRenderer bone27;
    private final ModelRenderer bone28;
    private final ModelRenderer bullet;
    private final ModelRenderer magazine;
    private final ModelRenderer bone11;
    private final ModelRenderer bone33;
    private final ModelRenderer bone34;
    private final ModelRenderer bone;
    private final ModelRenderer bone2;
    private final ModelRenderer bolt;
    private final ModelRenderer aug;
    private final ModelRenderer bone12;
    private final ModelRenderer bone25;
    private final ModelRenderer bone26;
    private final ModelRenderer bone16;
    private final ModelRenderer bone17;
    private final ModelRenderer bone15;
    private final ModelRenderer bone14;
    private final ModelRenderer bone23;
    private final ModelRenderer bone21;
    private final ModelRenderer bone22;
    private final ModelRenderer bone18;
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
        textureWidth = 512;
        textureHeight = 512;

        slide = new ModelRenderer(this);
        slide.setRotationPoint(-1.3477F, 2.1556F, 30.198F);
        setRotationAngle(slide, 0.0F, 0.0F, 0.2618F);
        slide.cubeList.add(new ModelBox(slide, 92, 27, -1.7766F, -0.5076F, -1.302F, 2, 1, 2, 0.0F, false));
        slide.cubeList.add(new ModelBox(slide, 92, 27, -2.7766F, -0.5076F, -0.302F, 1, 1, 2, 0.0F, false));

        bone20 = new ModelRenderer(this);
        bone20.setRotationPoint(12.0046F, -9.2788F, -3.698F);
        slide.addChild(bone20);
        setRotationAngle(bone20, 0.0F, -0.3491F, 0.0F);
        bone20.cubeList.add(new ModelBox(bone20, 92, 27, -12.1046F, 8.7712F, 7.784F, 1, 1, 2, 0.0F, false));

        bone19 = new ModelRenderer(this);
        bone19.setRotationPoint(11.2546F, -9.2788F, -3.698F);
        slide.addChild(bone19);
        setRotationAngle(bone19, 0.0F, -0.7854F, 0.0F);
        bone19.cubeList.add(new ModelBox(bone19, 92, 27, -7.5202F, 8.7712F, 10.9087F, 1, 1, 1, 0.0F, false));
        bone19.cubeList.add(new ModelBox(bone19, 92, 27, -7.5202F, 8.7712F, 11.3229F, 1, 1, 1, 0.0F, false));

        ironsights = new ModelRenderer(this);
        ironsights.setRotationPoint(-4.5F, 24.0F, 0.0F);
        ironsights.cubeList.add(new ModelBox(ironsights, 37, 12, 3.0F, -28.364F, 9.75F, 3, 1, 1, 0.0F, true));
        ironsights.cubeList.add(new ModelBox(ironsights, 37, 12, 2.5F, -29.364F, 9.75F, 1, 1, 1, 0.0F, true));
        ironsights.cubeList.add(new ModelBox(ironsights, 37, 12, 4.0F, -28.864F, 29.75F, 1, 2, 1, 0.0F, true));
        ironsights.cubeList.add(new ModelBox(ironsights, 37, 12, 5.5F, -29.364F, 9.75F, 1, 1, 1, 0.0F, true));
        ironsights.cubeList.add(new ModelBox(ironsights, 37, 12, 3.0F, -27.4063F, 9.25F, 3, 1, 2, 0.0F, true));
        ironsights.cubeList.add(new ModelBox(ironsights, 37, 12, 3.0F, -27.6F, 29.25F, 3, 1, 2, 0.0F, true));
        ironsights.cubeList.add(new ModelBox(ironsights, 37, 12, 2.5774F, -26.6937F, 29.25F, 1, 1, 2, 0.0F, false));
        ironsights.cubeList.add(new ModelBox(ironsights, 37, 12, 5.4226F, -26.6937F, 29.25F, 1, 1, 2, 0.0F, true));

        bone29 = new ModelRenderer(this);
        bone29.setRotationPoint(-12.8F, -5.9F, 0.0F);
        ironsights.addChild(bone29);
        setRotationAngle(bone29, 0.0F, 0.0F, 0.5236F);


        bone30 = new ModelRenderer(this);
        bone30.setRotationPoint(21.8F, -5.9F, 0.0F);
        ironsights.addChild(bone30);
        setRotationAngle(bone30, 0.0F, 0.0F, -0.5236F);


        bone31 = new ModelRenderer(this);
        bone31.setRotationPoint(3.4F, -29.364F, 10.25F);
        ironsights.addChild(bone31);
        setRotationAngle(bone31, 0.0F, 0.0F, 0.5236F);
        bone31.cubeList.add(new ModelBox(bone31, 37, 12, 2.1847F, -0.684F, -0.5F, 1, 1, 1, 0.0F, true));

        bone32 = new ModelRenderer(this);
        bone32.setRotationPoint(5.6F, -29.364F, 10.25F);
        ironsights.addChild(bone32);
        setRotationAngle(bone32, 0.0F, 0.0F, -0.5236F);
        bone32.cubeList.add(new ModelBox(bone32, 37, 12, -3.1847F, -0.684F, -0.5F, 1, 1, 1, 0.0F, false));

        bone27 = new ModelRenderer(this);
        bone27.setRotationPoint(3.0F, -26.4F, 10.25F);
        ironsights.addChild(bone27);
        setRotationAngle(bone27, 0.0F, 0.0F, 0.4363F);
        bone27.cubeList.add(new ModelBox(bone27, 37, 12, -0.4193F, -0.9149F, -1.0F, 1, 1, 2, 0.0F, false));
        bone27.cubeList.add(new ModelBox(bone27, 37, 12, 2.0537F, -1.5342F, -1.0F, 1, 1, 2, 0.0F, false));
        bone27.cubeList.add(new ModelBox(bone27, 37, 12, -0.5071F, -1.0876F, 19.0F, 1, 1, 2, 0.0F, false));

        bone28 = new ModelRenderer(this);
        bone28.setRotationPoint(6.0F, -26.4F, 10.25F);
        ironsights.addChild(bone28);
        setRotationAngle(bone28, 0.0F, 0.0F, -0.4363F);
        bone28.cubeList.add(new ModelBox(bone28, 37, 12, -0.5807F, -0.9149F, -1.0F, 1, 1, 2, 0.0F, true));
        bone28.cubeList.add(new ModelBox(bone28, 37, 12, -3.0537F, -1.5342F, -1.0F, 1, 1, 2, 0.0F, true));
        bone28.cubeList.add(new ModelBox(bone28, 37, 12, -0.4929F, -1.0876F, 19.0F, 1, 1, 2, 0.0F, true));

        bullet = new ModelRenderer(this);
        bullet.setRotationPoint(0.0F, 27.0F, 0.0F);
        bullet.cubeList.add(new ModelBox(bullet, 14, 497, -0.5F, -20.628F, 9.8F, 1, 1, 2, 0.0F, true));
        bullet.cubeList.add(new ModelBox(bullet, 14, 497, -0.65F, -20.778F, 6.8F, 1, 1, 3, 0.0F, false));
        bullet.cubeList.add(new ModelBox(bullet, 14, 497, -0.35F, -20.778F, 6.8F, 1, 1, 3, 0.0F, true));
        bullet.cubeList.add(new ModelBox(bullet, 14, 497, -0.9F, -21.028F, 1.8F, 1, 1, 5, 0.0F, false));
        bullet.cubeList.add(new ModelBox(bullet, 14, 497, -0.1F, -21.028F, 1.8F, 1, 1, 5, 0.0F, true));
        bullet.cubeList.add(new ModelBox(bullet, 14, 497, -0.5F, -20.628F, 1.3F, 1, 1, 1, 0.0F, true));
        bullet.cubeList.add(new ModelBox(bullet, 14, 497, -0.1F, -21.028F, 0.3F, 1, 1, 1, 0.0F, true));
        bullet.cubeList.add(new ModelBox(bullet, 14, 497, -0.9F, -21.028F, 0.3F, 1, 1, 1, 0.0F, false));
        bullet.cubeList.add(new ModelBox(bullet, 14, 497, -0.1F, -20.228F, 0.3F, 1, 1, 1, 0.0F, true));
        bullet.cubeList.add(new ModelBox(bullet, 14, 497, -0.9F, -20.228F, 0.3F, 1, 1, 1, 0.0F, false));
        bullet.cubeList.add(new ModelBox(bullet, 14, 497, -0.9F, -20.228F, 1.8F, 1, 1, 5, 0.0F, false));
        bullet.cubeList.add(new ModelBox(bullet, 14, 497, -0.65F, -20.478F, 6.8F, 1, 1, 3, 0.0F, false));
        bullet.cubeList.add(new ModelBox(bullet, 14, 497, -0.1F, -20.228F, 1.8F, 1, 1, 5, 0.0F, true));
        bullet.cubeList.add(new ModelBox(bullet, 14, 497, -0.35F, -20.478F, 6.8F, 1, 1, 3, 0.0F, true));

        magazine = new ModelRenderer(this);
        magazine.setRotationPoint(0.0F, 25.1F, 0.0F);
        magazine.cubeList.add(new ModelBox(magazine, 76, 29, -2.5F, -16.0F, -1.0F, 5, 7, 2, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 76, 29, -2.5F, -16.0F, 3.0F, 5, 7, 2, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 76, 29, -2.5F, -16.0F, 7.0F, 5, 7, 2, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 76, 29, -2.5F, -16.0F, 11.0F, 5, 7, 2, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 76, 29, -1.0F, -18.0F, 12.0F, 2, 2, 1, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 76, 29, -1.0F, -18.0F, -1.0F, 2, 2, 1, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 76, 29, 1.0F, -19.0F, -1.0F, 1, 3, 14, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 76, 29, -2.0F, -19.0F, -1.0F, 1, 3, 14, 0.0F, true));
        magazine.cubeList.add(new ModelBox(magazine, 76, 29, -2.0F, -16.0F, 1.0F, 4, 7, 10, 0.0F, false));

        bone11 = new ModelRenderer(this);
        bone11.setRotationPoint(-2.5F, 2.0F, 0.0F);
        magazine.addChild(bone11);
        setRotationAngle(bone11, 0.0873F, 0.0F, 0.0F);
        bone11.cubeList.add(new ModelBox(bone11, 76, 29, 0.0F, -11.0453F, 11.9625F, 5, 6, 2, 0.0F, false));
        bone11.cubeList.add(new ModelBox(bone11, 76, 29, 0.5F, -11.0453F, 1.9625F, 4, 6, 10, 0.0F, false));
        bone11.cubeList.add(new ModelBox(bone11, 76, 29, 0.0F, -11.0453F, 7.9625F, 5, 6, 2, 0.0F, false));
        bone11.cubeList.add(new ModelBox(bone11, 76, 29, 0.0F, -11.0453F, 3.9625F, 5, 6, 2, 0.0F, false));
        bone11.cubeList.add(new ModelBox(bone11, 76, 29, 0.0F, -11.0453F, -0.0375F, 5, 6, 2, 0.0F, false));

        bone33 = new ModelRenderer(this);
        bone33.setRotationPoint(0.0F, -6.0453F, 6.9625F);
        magazine.addChild(bone33);
        setRotationAngle(bone33, 0.1745F, 0.0F, 0.0F);
        bone33.cubeList.add(new ModelBox(bone33, 76, 29, -2.5F, 1.6847F, 4.1486F, 5, 6, 2, 0.0F, false));
        bone33.cubeList.add(new ModelBox(bone33, 76, 29, -2.0F, 1.6847F, -5.8514F, 4, 6, 10, 0.0F, false));
        bone33.cubeList.add(new ModelBox(bone33, 76, 29, -2.5F, 1.6847F, 0.1486F, 5, 6, 2, 0.0F, false));
        bone33.cubeList.add(new ModelBox(bone33, 76, 29, -2.5F, 1.6847F, -3.8514F, 5, 6, 2, 0.0F, false));
        bone33.cubeList.add(new ModelBox(bone33, 76, 29, -2.5F, 1.6847F, -7.8514F, 5, 6, 2, 0.0F, false));

        bone34 = new ModelRenderer(this);
        bone34.setRotationPoint(0.0F, -6.0453F, 6.9625F);
        magazine.addChild(bone34);
        setRotationAngle(bone34, 0.2618F, 0.0F, 0.0F);
        bone34.cubeList.add(new ModelBox(bone34, 76, 29, -2.5F, 6.9711F, 3.5087F, 5, 6, 2, 0.0F, false));
        bone34.cubeList.add(new ModelBox(bone34, 76, 29, -2.0F, 6.9711F, -6.4913F, 4, 6, 10, 0.0F, false));
        bone34.cubeList.add(new ModelBox(bone34, 76, 29, -2.5F, 6.9711F, -0.4913F, 5, 6, 2, 0.0F, false));
        bone34.cubeList.add(new ModelBox(bone34, 76, 29, -2.5F, 6.9711F, -4.4913F, 5, 6, 2, 0.0F, false));
        bone34.cubeList.add(new ModelBox(bone34, 76, 29, -2.5F, 6.9711F, -8.4913F, 5, 6, 2, 0.0F, false));

        bone = new ModelRenderer(this);
        bone.setRotationPoint(14.0F, -8.0F, 0.0F);
        magazine.addChild(bone);
        setRotationAngle(bone, 0.0F, 0.0F, -0.5236F);
        bone.cubeList.add(new ModelBox(bone, 76, 29, -6.9593F, -13.6782F, 11.0F, 1, 1, 2, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 76, 29, -6.9593F, -13.6782F, 7.0F, 1, 1, 2, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 76, 29, -6.9593F, -13.6782F, 3.0F, 1, 1, 2, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 76, 29, -6.9593F, -13.6782F, -1.0F, 1, 1, 2, 0.0F, false));

        bone2 = new ModelRenderer(this);
        bone2.setRotationPoint(-14.0F, -8.0F, 0.0F);
        magazine.addChild(bone2);
        setRotationAngle(bone2, 0.0F, 0.0F, 0.5236F);
        bone2.cubeList.add(new ModelBox(bone2, 76, 29, 5.9593F, -13.6782F, 11.0F, 1, 1, 2, 0.0F, true));
        bone2.cubeList.add(new ModelBox(bone2, 76, 29, 5.9593F, -13.6782F, 7.0F, 1, 1, 2, 0.0F, true));
        bone2.cubeList.add(new ModelBox(bone2, 76, 29, 5.9593F, -13.6782F, 3.0F, 1, 1, 2, 0.0F, true));
        bone2.cubeList.add(new ModelBox(bone2, 76, 29, 5.9593F, -13.6782F, -1.0F, 1, 1, 2, 0.0F, true));

        bolt = new ModelRenderer(this);
        bolt.setRotationPoint(0.0F, 24.0F, -9.0F);
        bolt.cubeList.add(new ModelBox(bolt, 0, 24, 1.6F, -20.0F, 1.0F, 1, 3, 10, 0.0F, false));

        aug = new ModelRenderer(this);
        aug.setRotationPoint(0.0F, 24.0F, 0.0F);
        aug.cubeList.add(new ModelBox(aug, 210, 84, -3.0F, -21.0F, -2.0F, 6, 4, 3, 0.0F, false));
        aug.cubeList.add(new ModelBox(aug, 210, 84, -3.0F, -21.0F, 11.0F, 6, 4, 3, 0.0F, false));
        aug.cubeList.add(new ModelBox(aug, 210, 84, -3.0F, -21.0F, 1.0F, 6, 1, 10, 0.0F, false));
        aug.cubeList.add(new ModelBox(aug, 210, 84, -3.0F, -20.0F, 1.0F, 1, 3, 10, 0.0F, false));
        aug.cubeList.add(new ModelBox(aug, 210, 84, -3.65F, -19.9F, -16.0F, 3, 2, 13, 0.0F, false));
        aug.cubeList.add(new ModelBox(aug, 210, 84, 0.65F, -19.9F, -16.0F, 3, 2, 13, 0.0F, true));
        aug.cubeList.add(new ModelBox(aug, 210, 84, -3.65F, -19.9F, -3.0F, 3, 2, 13, 0.0F, false));
        aug.cubeList.add(new ModelBox(aug, 210, 84, 0.25F, -19.9F, -18.2F, 3, 4, 18, 0.0F, true));
        aug.cubeList.add(new ModelBox(aug, 210, 84, 0.25F, -15.9F, -18.2F, 3, 4, 6, 0.0F, true));
        aug.cubeList.add(new ModelBox(aug, 210, 84, -3.0F, -19.0F, 14.0F, 6, 3, 20, 0.0F, false));
        aug.cubeList.add(new ModelBox(aug, 210, 84, -3.0F, -19.0F, 34.0F, 6, 3, 1, 0.0F, false));
        aug.cubeList.add(new ModelBox(aug, 210, 84, 2.0F, -19.0F, 35.0F, 1, 3, 7, 0.0F, false));
        aug.cubeList.add(new ModelBox(aug, 210, 84, -3.0F, -19.0F, 35.0F, 1, 3, 7, 0.0F, false));
        aug.cubeList.add(new ModelBox(aug, 210, 84, -3.0F, -21.0F, 14.0F, 6, 2, 13, 0.0F, false));
        aug.cubeList.add(new ModelBox(aug, 210, 84, -3.0F, -21.0F, 27.0F, 6, 2, 8, 0.0F, false));
        aug.cubeList.add(new ModelBox(aug, 210, 84, 2.0F, -21.0F, 35.0F, 1, 2, 7, 0.0F, false));
        aug.cubeList.add(new ModelBox(aug, 210, 84, -3.0F, -21.0F, 35.0F, 1, 2, 7, 0.0F, false));
        aug.cubeList.add(new ModelBox(aug, 210, 84, -0.4142F, -16.5858F, 14.0F, 2, 2, 20, 0.0F, false));
        aug.cubeList.add(new ModelBox(aug, 210, 84, -0.4142F, -16.5858F, 34.0F, 2, 2, 1, 0.0F, false));
        aug.cubeList.add(new ModelBox(aug, 210, 84, -0.4142F, -15.5858F, 35.0F, 2, 1, 7, 0.0F, false));
        aug.cubeList.add(new ModelBox(aug, 210, 84, -1.5858F, -16.5858F, 34.0F, 2, 2, 1, 0.0F, false));
        aug.cubeList.add(new ModelBox(aug, 210, 84, -1.5858F, -15.5858F, 35.0F, 2, 1, 7, 0.0F, false));
        aug.cubeList.add(new ModelBox(aug, 83, 96, -0.5F, -19.0358F, 35.0F, 1, 1, 14, 0.0F, false));
        aug.cubeList.add(new ModelBox(aug, 83, 96, -0.5F, -21.7678F, 35.0F, 1, 1, 14, 0.0F, false));
        aug.cubeList.add(new ModelBox(aug, 83, 96, -1.866F, -20.4018F, 35.0F, 1, 1, 14, 0.0F, false));
        aug.cubeList.add(new ModelBox(aug, 83, 96, 0.866F, -20.4018F, 35.0F, 1, 1, 14, 0.0F, true));
        aug.cubeList.add(new ModelBox(aug, 83, 96, -0.5F, -19.0358F, 49.0F, 1, 1, 6, 0.0F, false));
        aug.cubeList.add(new ModelBox(aug, 83, 96, -0.5F, -21.7678F, 49.0F, 1, 1, 6, 0.0F, false));
        aug.cubeList.add(new ModelBox(aug, 83, 96, -1.866F, -20.4018F, 49.0F, 1, 1, 6, 0.0F, false));
        aug.cubeList.add(new ModelBox(aug, 83, 96, 0.866F, -20.4018F, 49.0F, 1, 1, 6, 0.0F, true));
        aug.cubeList.add(new ModelBox(aug, 210, 84, -1.5858F, -16.5858F, 14.0F, 2, 2, 20, 0.0F, false));
        aug.cubeList.add(new ModelBox(aug, 210, 84, -2.0F, -15.0858F, 20.0F, 4, 4, 5, 0.0F, false));
        aug.cubeList.add(new ModelBox(aug, 210, 84, -2.0F, -4.7361F, 17.3848F, 4, 2, 5, 0.0F, false));
        aug.cubeList.add(new ModelBox(aug, 17, 92, -1.5F, -3.4861F, 17.8848F, 3, 1, 8, 0.0F, false));
        aug.cubeList.add(new ModelBox(aug, 210, 84, -1.5F, -14.5858F, 25.0F, 3, 3, 1, 0.0F, false));
        aug.cubeList.add(new ModelBox(aug, 91, 94, -1.0F, -15.2858F, 25.75F, 2, 3, 1, 0.0F, false));
        aug.cubeList.add(new ModelBox(aug, 206, 83, -1.0F, -23.0F, -4.0F, 2, 2, 18, 0.0F, false));
        aug.cubeList.add(new ModelBox(aug, 210, 84, -1.0F, -23.0F, 14.0F, 2, 2, 13, 0.0F, false));
        aug.cubeList.add(new ModelBox(aug, 0, 75, -1.0F, -27.0F, 31.75F, 2, 1, 1, 0.0F, false));
        aug.cubeList.add(new ModelBox(aug, 0, 75, -1.0F, -27.0F, 29.75F, 2, 1, 1, 0.0F, false));
        aug.cubeList.add(new ModelBox(aug, 0, 75, -1.0F, -27.0F, 27.75F, 2, 1, 1, 0.0F, false));
        aug.cubeList.add(new ModelBox(aug, 0, 75, -1.0F, -27.0F, 25.75F, 2, 1, 1, 0.0F, false));
        aug.cubeList.add(new ModelBox(aug, 0, 75, -1.0F, -27.0F, 23.75F, 2, 1, 1, 0.0F, false));
        aug.cubeList.add(new ModelBox(aug, 0, 75, -1.0F, -27.0F, 21.75F, 2, 1, 1, 0.0F, false));
        aug.cubeList.add(new ModelBox(aug, 0, 75, -1.0F, -27.0F, 19.75F, 2, 1, 1, 0.0F, false));
        aug.cubeList.add(new ModelBox(aug, 0, 75, -1.0F, -27.0F, 17.75F, 2, 1, 1, 0.0F, false));
        aug.cubeList.add(new ModelBox(aug, 0, 75, -1.0F, -27.0F, 15.75F, 2, 1, 1, 0.0F, false));
        aug.cubeList.add(new ModelBox(aug, 0, 75, -1.0F, -27.0F, 13.75F, 2, 1, 1, 0.0F, false));
        aug.cubeList.add(new ModelBox(aug, 0, 75, -1.0F, -27.0F, 11.75F, 2, 1, 1, 0.0F, false));
        aug.cubeList.add(new ModelBox(aug, 0, 75, -1.0F, -27.0F, 9.75F, 2, 1, 1, 0.0F, false));
        aug.cubeList.add(new ModelBox(aug, 0, 75, -1.0F, -27.0F, 7.75F, 2, 1, 1, 0.0F, false));
        aug.cubeList.add(new ModelBox(aug, 0, 75, -1.5F, -26.4F, 20.75F, 3, 1, 12, 0.0F, false));
        aug.cubeList.add(new ModelBox(aug, 0, 75, -1.5F, -26.4F, 7.75F, 3, 1, 13, 0.0F, false));
        aug.cubeList.add(new ModelBox(aug, 0, 75, -1.0F, -25.4F, 14.75F, 2, 3, 2, 0.0F, false));
        aug.cubeList.add(new ModelBox(aug, 0, 75, -1.0F, -23.8F, 32.1F, 2, 1, 1, 0.0F, false));
        aug.cubeList.add(new ModelBox(aug, 0, 75, -1.866F, -26.5F, 30.75F, 2, 0, 1, 0.0F, false));
        aug.cubeList.add(new ModelBox(aug, 0, 75, -1.866F, -26.5F, 28.75F, 2, 0, 1, 0.0F, false));
        aug.cubeList.add(new ModelBox(aug, 0, 75, -1.866F, -26.5F, 26.75F, 2, 0, 1, 0.0F, false));
        aug.cubeList.add(new ModelBox(aug, 0, 75, -1.866F, -26.5F, 24.75F, 2, 0, 1, 0.0F, false));
        aug.cubeList.add(new ModelBox(aug, 0, 75, -1.866F, -26.5F, 22.75F, 2, 0, 1, 0.0F, false));
        aug.cubeList.add(new ModelBox(aug, 0, 75, -1.866F, -26.5F, 20.75F, 2, 0, 1, 0.0F, false));
        aug.cubeList.add(new ModelBox(aug, 0, 75, -1.866F, -26.5F, 18.75F, 2, 0, 1, 0.0F, false));
        aug.cubeList.add(new ModelBox(aug, 0, 75, -1.866F, -26.5F, 16.75F, 2, 0, 1, 0.0F, false));
        aug.cubeList.add(new ModelBox(aug, 0, 75, -1.866F, -26.5F, 14.75F, 2, 0, 1, 0.0F, false));
        aug.cubeList.add(new ModelBox(aug, 0, 75, -1.866F, -26.5F, 12.75F, 2, 0, 1, 0.0F, false));
        aug.cubeList.add(new ModelBox(aug, 0, 75, -1.866F, -26.5F, 10.75F, 2, 0, 1, 0.0F, false));
        aug.cubeList.add(new ModelBox(aug, 0, 75, -1.866F, -26.5F, 8.75F, 2, 0, 1, 0.0F, false));
        aug.cubeList.add(new ModelBox(aug, 0, 75, -0.134F, -26.5F, 30.75F, 2, 0, 1, 0.0F, true));
        aug.cubeList.add(new ModelBox(aug, 0, 75, -0.134F, -26.5F, 28.75F, 2, 0, 1, 0.0F, true));
        aug.cubeList.add(new ModelBox(aug, 0, 75, -0.134F, -26.5F, 26.75F, 2, 0, 1, 0.0F, true));
        aug.cubeList.add(new ModelBox(aug, 0, 75, -0.134F, -26.5F, 24.75F, 2, 0, 1, 0.0F, true));
        aug.cubeList.add(new ModelBox(aug, 0, 75, -0.134F, -26.5F, 22.75F, 2, 0, 1, 0.0F, true));
        aug.cubeList.add(new ModelBox(aug, 0, 75, -0.134F, -26.5F, 20.75F, 2, 0, 1, 0.0F, true));
        aug.cubeList.add(new ModelBox(aug, 0, 75, -0.134F, -26.5F, 18.75F, 2, 0, 1, 0.0F, true));
        aug.cubeList.add(new ModelBox(aug, 0, 75, -0.134F, -26.5F, 16.75F, 2, 0, 1, 0.0F, true));
        aug.cubeList.add(new ModelBox(aug, 0, 75, -0.134F, -26.5F, 14.75F, 2, 0, 1, 0.0F, true));
        aug.cubeList.add(new ModelBox(aug, 0, 75, -0.134F, -26.5F, 12.75F, 2, 0, 1, 0.0F, true));
        aug.cubeList.add(new ModelBox(aug, 0, 75, -0.134F, -26.5F, 10.75F, 2, 0, 1, 0.0F, true));
        aug.cubeList.add(new ModelBox(aug, 0, 75, -0.134F, -26.5F, 8.75F, 2, 0, 1, 0.0F, true));
        aug.cubeList.add(new ModelBox(aug, 210, 84, -1.0F, -23.0F, 27.0F, 2, 2, 8, 0.0F, false));
        aug.cubeList.add(new ModelBox(aug, 210, 84, -1.0F, -23.0F, 35.0F, 2, 1, 7, 0.0F, false));
        aug.cubeList.add(new ModelBox(aug, 210, 84, -1.0F, -23.0F, -20.0F, 2, 1, 16, 0.0F, false));
        aug.cubeList.add(new ModelBox(aug, 210, 84, -3.0F, -21.0F, -16.0F, 6, 3, 14, 0.0F, false));
        aug.cubeList.add(new ModelBox(aug, 210, 84, -3.0F, -21.0F, -20.0F, 6, 4, 4, 0.0F, false));
        aug.cubeList.add(new ModelBox(aug, 29, 88, -3.0F, -21.001F, -22.0F, 6, 4, 2, 0.0F, false));
        aug.cubeList.add(new ModelBox(aug, 210, 84, -3.0F, -17.0F, -20.0F, 6, 12, 4, 0.0F, false));
        aug.cubeList.add(new ModelBox(aug, 210, 84, -3.0F, -17.0F, -21.0F, 6, 12, 1, 0.0F, false));
        aug.cubeList.add(new ModelBox(aug, 210, 84, -3.0F, -18.0F, -16.0F, 6, 6, 2, 0.0F, false));
        aug.cubeList.add(new ModelBox(aug, 210, 84, -3.0F, -18.0F, -14.0F, 6, 4, 1, 0.0F, false));
        aug.cubeList.add(new ModelBox(aug, 210, 84, -3.0F, -18.0F, -13.0F, 6, 3, 2, 0.0F, false));
        aug.cubeList.add(new ModelBox(aug, 210, 84, -3.0F, -18.0F, -11.0F, 6, 2, 3, 0.0F, false));
        aug.cubeList.add(new ModelBox(aug, 210, 84, -3.0F, -18.0F, -8.0F, 6, 1, 3, 0.0F, false));
        aug.cubeList.add(new ModelBox(aug, 210, 84, -2.5F, -7.0F, -16.0F, 5, 2, 1, 0.0F, false));
        aug.cubeList.add(new ModelBox(aug, 210, 84, -2.0F, -5.4805F, -20.9766F, 4, 1, 6, 0.0F, false));
        aug.cubeList.add(new ModelBox(aug, 210, 84, -2.0F, -7.0F, -15.2695F, 4, 2, 1, 0.0F, false));
        aug.cubeList.add(new ModelBox(aug, 210, 84, -3.0F, -17.0F, -1.0F, 1, 7, 14, 0.0F, false));
        aug.cubeList.add(new ModelBox(aug, 5, 70, -3.5F, -17.0F, 8.0F, 1, 3, 2, 0.0F, false));
        aug.cubeList.add(new ModelBox(aug, 210, 84, -3.2F, -21.0F, -21.0F, 1, 16, 2, 0.0F, false));
        aug.cubeList.add(new ModelBox(aug, 210, 84, 2.2F, -21.0F, -21.0F, 1, 16, 2, 0.0F, true));
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
        aug.cubeList.add(new ModelBox(aug, 210, 84, 2.0F, -17.0F, -1.0F, 1, 7, 14, 0.0F, true));
        aug.cubeList.add(new ModelBox(aug, 210, 84, -3.0F, -17.0F, -2.0F, 6, 7, 1, 0.0F, true));
        aug.cubeList.add(new ModelBox(aug, 210, 84, -3.0F, -17.0F, 13.0F, 6, 7, 1, 0.0F, true));

        bone12 = new ModelRenderer(this);
        bone12.setRotationPoint(4.0F, 0.0F, 0.0F);
        aug.addChild(bone12);
        setRotationAngle(bone12, 0.0F, 0.0F, -0.7854F);
        bone12.cubeList.add(new ModelBox(bone12, 210, 84, 8.6066F, -14.0208F, 14.0F, 2, 2, 20, 0.0F, false));
        bone12.cubeList.add(new ModelBox(bone12, 210, 84, 8.6066F, -14.0208F, 34.0F, 2, 2, 1, 0.0F, false));
        bone12.cubeList.add(new ModelBox(bone12, 210, 84, 8.6066F, -13.0208F, 35.0F, 2, 1, 7, 0.0F, false));
        bone12.cubeList.add(new ModelBox(bone12, 210, 84, 6.364F, -16.2635F, 34.0F, 2, 2, 1, 0.0F, false));
        bone12.cubeList.add(new ModelBox(bone12, 210, 84, 6.364F, -16.2635F, 35.0F, 1, 2, 7, 0.0F, false));
        bone12.cubeList.add(new ModelBox(bone12, 210, 84, 6.364F, -16.2635F, 14.0F, 2, 2, 20, 0.0F, false));

        bone25 = new ModelRenderer(this);
        bone25.setRotationPoint(4.0858F, 0.0F, 0.0F);
        aug.addChild(bone25);
        setRotationAngle(bone25, 0.0F, 0.0F, -0.5236F);
        bone25.cubeList.add(new ModelBox(bone25, 83, 96, 5.9125F, -18.4123F, 35.0F, 1, 1, 14, 0.0F, false));
        bone25.cubeList.add(new ModelBox(bone25, 83, 96, 4.5465F, -19.7784F, 35.0F, 1, 1, 14, 0.0F, false));
        bone25.cubeList.add(new ModelBox(bone25, 83, 96, 7.2785F, -19.7784F, 35.0F, 1, 1, 14, 0.0F, false));
        bone25.cubeList.add(new ModelBox(bone25, 83, 96, 5.9125F, -21.1444F, 35.0F, 1, 1, 14, 0.0F, false));
        bone25.cubeList.add(new ModelBox(bone25, 83, 96, 5.9125F, -18.4123F, 49.0F, 1, 1, 6, 0.0F, false));
        bone25.cubeList.add(new ModelBox(bone25, 83, 96, 4.5465F, -19.7784F, 49.0F, 1, 1, 6, 0.0F, false));
        bone25.cubeList.add(new ModelBox(bone25, 83, 96, 7.2785F, -19.7784F, 49.0F, 1, 1, 6, 0.0F, false));
        bone25.cubeList.add(new ModelBox(bone25, 83, 96, 5.9125F, -21.1444F, 49.0F, 1, 1, 6, 0.0F, false));

        bone26 = new ModelRenderer(this);
        bone26.setRotationPoint(-4.0858F, 0.0F, 0.0F);
        aug.addChild(bone26);
        setRotationAngle(bone26, 0.0F, 0.0F, 0.5236F);
        bone26.cubeList.add(new ModelBox(bone26, 83, 96, -6.9125F, -18.4123F, 35.0F, 1, 1, 14, 0.0F, true));
        bone26.cubeList.add(new ModelBox(bone26, 83, 96, -5.5465F, -19.7784F, 35.0F, 1, 1, 14, 0.0F, true));
        bone26.cubeList.add(new ModelBox(bone26, 83, 96, -6.9125F, -21.1444F, 35.0F, 1, 1, 14, 0.0F, true));
        bone26.cubeList.add(new ModelBox(bone26, 83, 96, -8.2785F, -19.7784F, 35.0F, 1, 1, 14, 0.0F, true));
        bone26.cubeList.add(new ModelBox(bone26, 83, 96, -6.9125F, -18.4123F, 49.0F, 1, 1, 6, 0.0F, true));
        bone26.cubeList.add(new ModelBox(bone26, 83, 96, -5.5465F, -19.7784F, 49.0F, 1, 1, 6, 0.0F, true));
        bone26.cubeList.add(new ModelBox(bone26, 83, 96, -6.9125F, -21.1444F, 49.0F, 1, 1, 6, 0.0F, true));
        bone26.cubeList.add(new ModelBox(bone26, 83, 96, -8.2785F, -19.7784F, 49.0F, 1, 1, 6, 0.0F, true));

        bone16 = new ModelRenderer(this);
        bone16.setRotationPoint(0.0F, -2.9861F, 21.8848F);
        aug.addChild(bone16);
        setRotationAngle(bone16, 0.5236F, 0.0F, 0.0F);
        bone16.cubeList.add(new ModelBox(bone16, 17, 92, -1.5F, 1.433F, 3.2141F, 3, 1, 2, 0.0F, false));

        bone17 = new ModelRenderer(this);
        bone17.setRotationPoint(0.0F, -2.9861F, 21.8848F);
        aug.addChild(bone17);
        setRotationAngle(bone17, 1.0472F, 0.0F, 0.0F);
        bone17.cubeList.add(new ModelBox(bone17, 17, 92, -1.5F, 3.7141F, 3.299F, 3, 1, 13, 0.0F, false));

        bone15 = new ModelRenderer(this);
        bone15.setRotationPoint(4.0858F, 0.0F, 0.0F);
        aug.addChild(bone15);
        setRotationAngle(bone15, 0.6109F, 0.0F, 0.0F);
        bone15.cubeList.add(new ModelBox(bone15, 210, 84, -5.5858F, 4.4225F, 24.9433F, 3, 1, 3, 0.0F, false));

        bone14 = new ModelRenderer(this);
        bone14.setRotationPoint(3.5858F, -4.5F, -3.25F);
        aug.addChild(bone14);
        setRotationAngle(bone14, -0.2618F, 0.0F, 0.0F);
        bone14.cubeList.add(new ModelBox(bone14, 210, 84, -5.5858F, -15.5687F, 19.8706F, 4, 10, 5, 0.0F, false));

        bone23 = new ModelRenderer(this);
        bone23.setRotationPoint(0.0F, -23.6F, 34.4F);
        aug.addChild(bone23);
        setRotationAngle(bone23, 0.6109F, 0.0F, 0.0F);
        bone23.cubeList.add(new ModelBox(bone23, 0, 75, -1.0F, -3.3853F, -1.8362F, 2, 5, 2, 0.0F, false));

        bone21 = new ModelRenderer(this);
        bone21.setRotationPoint(0.0F, -27.5F, 29.25F);
        aug.addChild(bone21);
        setRotationAngle(bone21, 0.0F, 0.0F, -0.5236F);
        bone21.cubeList.add(new ModelBox(bone21, 0, 75, -2.116F, -0.067F, 2.5F, 1, 1, 1, 0.0F, false));
        bone21.cubeList.add(new ModelBox(bone21, 0, 75, -2.116F, -0.067F, 0.5F, 1, 1, 1, 0.0F, false));
        bone21.cubeList.add(new ModelBox(bone21, 0, 75, -2.116F, -0.067F, -1.5F, 1, 1, 1, 0.0F, false));
        bone21.cubeList.add(new ModelBox(bone21, 0, 75, -2.116F, -0.067F, -3.5F, 1, 1, 1, 0.0F, false));
        bone21.cubeList.add(new ModelBox(bone21, 0, 75, -2.116F, -0.067F, -5.5F, 1, 1, 1, 0.0F, false));
        bone21.cubeList.add(new ModelBox(bone21, 0, 75, -2.116F, -0.067F, -7.5F, 1, 1, 1, 0.0F, false));
        bone21.cubeList.add(new ModelBox(bone21, 0, 75, -2.116F, -0.067F, -9.5F, 1, 1, 1, 0.0F, false));
        bone21.cubeList.add(new ModelBox(bone21, 0, 75, -2.116F, -0.067F, -11.5F, 1, 1, 1, 0.0F, false));
        bone21.cubeList.add(new ModelBox(bone21, 0, 75, -2.116F, -0.067F, -13.5F, 1, 1, 1, 0.0F, false));
        bone21.cubeList.add(new ModelBox(bone21, 0, 75, -2.116F, -0.067F, -15.5F, 1, 1, 1, 0.0F, false));
        bone21.cubeList.add(new ModelBox(bone21, 0, 75, -2.116F, -0.067F, -17.5F, 1, 1, 1, 0.0F, false));
        bone21.cubeList.add(new ModelBox(bone21, 0, 75, -2.116F, -0.067F, -19.5F, 1, 1, 1, 0.0F, false));
        bone21.cubeList.add(new ModelBox(bone21, 0, 75, -2.116F, -0.067F, -21.5F, 1, 1, 1, 0.0F, false));
        bone21.cubeList.add(new ModelBox(bone21, 0, 75, -2.116F, -0.067F, 1.5F, 0, 1, 1, 0.0F, false));
        bone21.cubeList.add(new ModelBox(bone21, 0, 75, -2.116F, -0.067F, -0.5F, 0, 1, 1, 0.0F, false));
        bone21.cubeList.add(new ModelBox(bone21, 0, 75, -2.116F, -0.067F, -2.5F, 0, 1, 1, 0.0F, false));
        bone21.cubeList.add(new ModelBox(bone21, 0, 75, -2.116F, -0.067F, -4.5F, 0, 1, 1, 0.0F, false));
        bone21.cubeList.add(new ModelBox(bone21, 0, 75, -2.116F, -0.067F, -6.5F, 0, 1, 1, 0.0F, false));
        bone21.cubeList.add(new ModelBox(bone21, 0, 75, -2.116F, -0.067F, -8.5F, 0, 1, 1, 0.0F, false));
        bone21.cubeList.add(new ModelBox(bone21, 0, 75, -2.116F, -0.067F, -10.5F, 0, 1, 1, 0.0F, false));
        bone21.cubeList.add(new ModelBox(bone21, 0, 75, -2.116F, -0.067F, -12.5F, 0, 1, 1, 0.0F, false));
        bone21.cubeList.add(new ModelBox(bone21, 0, 75, -2.116F, -0.067F, -14.5F, 0, 1, 1, 0.0F, false));
        bone21.cubeList.add(new ModelBox(bone21, 0, 75, -2.116F, -0.067F, -16.5F, 0, 1, 1, 0.0F, false));
        bone21.cubeList.add(new ModelBox(bone21, 0, 75, -2.116F, -0.067F, -18.5F, 0, 1, 1, 0.0F, false));
        bone21.cubeList.add(new ModelBox(bone21, 0, 75, -2.116F, -0.067F, -20.5F, 0, 1, 1, 0.0F, false));

        bone22 = new ModelRenderer(this);
        bone22.setRotationPoint(0.0F, -27.5F, 29.25F);
        aug.addChild(bone22);
        setRotationAngle(bone22, 0.0F, 0.0F, 0.5236F);
        bone22.cubeList.add(new ModelBox(bone22, 0, 75, 1.116F, -0.067F, 2.5F, 1, 1, 1, 0.0F, true));
        bone22.cubeList.add(new ModelBox(bone22, 0, 75, 1.116F, -0.067F, 0.5F, 1, 1, 1, 0.0F, true));
        bone22.cubeList.add(new ModelBox(bone22, 0, 75, 1.116F, -0.067F, -1.5F, 1, 1, 1, 0.0F, true));
        bone22.cubeList.add(new ModelBox(bone22, 0, 75, 1.116F, -0.067F, -3.5F, 1, 1, 1, 0.0F, true));
        bone22.cubeList.add(new ModelBox(bone22, 0, 75, 1.116F, -0.067F, -5.5F, 1, 1, 1, 0.0F, true));
        bone22.cubeList.add(new ModelBox(bone22, 0, 75, 1.116F, -0.067F, -7.5F, 1, 1, 1, 0.0F, true));
        bone22.cubeList.add(new ModelBox(bone22, 0, 75, 1.116F, -0.067F, -9.5F, 1, 1, 1, 0.0F, true));
        bone22.cubeList.add(new ModelBox(bone22, 0, 75, 1.116F, -0.067F, -11.5F, 1, 1, 1, 0.0F, true));
        bone22.cubeList.add(new ModelBox(bone22, 0, 75, 1.116F, -0.067F, -13.5F, 1, 1, 1, 0.0F, true));
        bone22.cubeList.add(new ModelBox(bone22, 0, 75, 1.116F, -0.067F, -15.5F, 1, 1, 1, 0.0F, true));
        bone22.cubeList.add(new ModelBox(bone22, 0, 75, 1.116F, -0.067F, -17.5F, 1, 1, 1, 0.0F, true));
        bone22.cubeList.add(new ModelBox(bone22, 0, 75, 1.116F, -0.067F, -19.5F, 1, 1, 1, 0.0F, true));
        bone22.cubeList.add(new ModelBox(bone22, 0, 75, 1.116F, -0.067F, -21.5F, 1, 1, 1, 0.0F, true));
        bone22.cubeList.add(new ModelBox(bone22, 0, 75, 2.116F, -0.067F, 1.5F, 0, 1, 1, 0.0F, true));
        bone22.cubeList.add(new ModelBox(bone22, 0, 75, 2.116F, -0.067F, -0.5F, 0, 1, 1, 0.0F, true));
        bone22.cubeList.add(new ModelBox(bone22, 0, 75, 2.116F, -0.067F, -2.5F, 0, 1, 1, 0.0F, true));
        bone22.cubeList.add(new ModelBox(bone22, 0, 75, 2.116F, -0.067F, -4.5F, 0, 1, 1, 0.0F, true));
        bone22.cubeList.add(new ModelBox(bone22, 0, 75, 2.116F, -0.067F, -6.5F, 0, 1, 1, 0.0F, true));
        bone22.cubeList.add(new ModelBox(bone22, 0, 75, 2.116F, -0.067F, -8.5F, 0, 1, 1, 0.0F, true));
        bone22.cubeList.add(new ModelBox(bone22, 0, 75, 2.116F, -0.067F, -10.5F, 0, 1, 1, 0.0F, true));
        bone22.cubeList.add(new ModelBox(bone22, 0, 75, 2.116F, -0.067F, -12.5F, 0, 1, 1, 0.0F, true));
        bone22.cubeList.add(new ModelBox(bone22, 0, 75, 2.116F, -0.067F, -14.5F, 0, 1, 1, 0.0F, true));
        bone22.cubeList.add(new ModelBox(bone22, 0, 75, 2.116F, -0.067F, -16.5F, 0, 1, 1, 0.0F, true));
        bone22.cubeList.add(new ModelBox(bone22, 0, 75, 2.116F, -0.067F, -18.5F, 0, 1, 1, 0.0F, true));
        bone22.cubeList.add(new ModelBox(bone22, 0, 75, 2.116F, -0.067F, -20.5F, 0, 1, 1, 0.0F, true));

        bone18 = new ModelRenderer(this);
        bone18.setRotationPoint(0.0F, -22.0F, 28.5F);
        aug.addChild(bone18);
        setRotationAngle(bone18, 0.0F, 0.0F, 0.7854F);
        bone18.cubeList.add(new ModelBox(bone18, 210, 84, 0.0F, -1.4142F, -14.5F, 2, 2, 12, 0.0F, false));
        bone18.cubeList.add(new ModelBox(bone18, 210, 84, 0.0F, -1.4142F, -2.5F, 2, 2, 9, 0.0F, false));
        bone18.cubeList.add(new ModelBox(bone18, 210, 84, 0.0F, -1.4142F, 6.5F, 1, 1, 7, 0.0F, false));
        bone18.cubeList.add(new ModelBox(bone18, 210, 84, 1.0F, -1.4142F, 6.5F, 1, 1, 1, 0.0F, false));
        bone18.cubeList.add(new ModelBox(bone18, 210, 84, 1.0F, -1.4142F, 9.5F, 1, 1, 1, 0.0F, false));
        bone18.cubeList.add(new ModelBox(bone18, 210, 84, 1.0F, -1.4142F, 12.5F, 1, 1, 1, 0.0F, false));
        bone18.cubeList.add(new ModelBox(bone18, 210, 84, 1.8284F, -1.4142F, -14.5F, 1, 1, 12, 0.0F, false));
        bone18.cubeList.add(new ModelBox(bone18, 210, 84, 1.8284F, -1.4142F, -2.5F, 1, 1, 9, 0.0F, false));
        bone18.cubeList.add(new ModelBox(bone18, 210, 84, 1.8284F, -1.4142F, 6.5F, 1, 1, 7, 0.0F, false));
        bone18.cubeList.add(new ModelBox(bone18, 206, 83, -1.4142F, 0.0F, -14.5F, 2, 2, 5, 0.0F, false));
        bone18.cubeList.add(new ModelBox(bone18, 206, 83, -1.4142F, 0.0F, -7.5F, 1, 1, 10, 0.0F, false));
        bone18.cubeList.add(new ModelBox(bone18, 19, 166, -1.1642F, 0.0F, -9.5F, 1, 2, 12, 0.0F, false));
        bone18.cubeList.add(new ModelBox(bone18, 206, 83, -1.4142F, 0.0F, 2.5F, 2, 2, 4, 0.0F, false));
        bone18.cubeList.add(new ModelBox(bone18, 206, 83, -1.4142F, 0.0F, 6.5F, 1, 1, 7, 0.0F, false));
        bone18.cubeList.add(new ModelBox(bone18, 206, 83, -1.4142F, 1.0F, 6.5F, 1, 1, 1, 0.0F, false));
        bone18.cubeList.add(new ModelBox(bone18, 206, 83, -1.4142F, 1.0F, 9.5F, 1, 1, 1, 0.0F, false));
        bone18.cubeList.add(new ModelBox(bone18, 206, 83, -1.4142F, 1.0F, 12.5F, 1, 1, 1, 0.0F, false));
        bone18.cubeList.add(new ModelBox(bone18, 206, 83, -1.4142F, 1.8284F, -14.5F, 1, 1, 12, 0.0F, false));
        bone18.cubeList.add(new ModelBox(bone18, 206, 83, -1.4142F, 1.8284F, -2.5F, 1, 1, 9, 0.0F, false));
        bone18.cubeList.add(new ModelBox(bone18, 206, 83, -1.4142F, 1.8284F, 6.5F, 1, 1, 7, 0.0F, false));

        bone9 = new ModelRenderer(this);
        bone9.setRotationPoint(3.5F, 0.0F, 0.0F);
        aug.addChild(bone9);
        setRotationAngle(bone9, 0.7854F, 0.0F, 0.0F);
        bone9.cubeList.add(new ModelBox(bone9, 29, 88, -4.5F, -30.1581F, -0.9107F, 2, 1, 3, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 29, 88, -5.0F, -30.0255F, -0.4687F, 3, 1, 2, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 29, 88, -5.5F, -29.6333F, -0.7946F, 4, 1, 2, 0.0F, false));

        bone8 = new ModelRenderer(this);
        bone8.setRotationPoint(13.8995F, -19.299F, -13.0F);
        aug.addChild(bone8);
        setRotationAngle(bone8, 0.0F, 0.0F, -0.6109F);
        bone8.cubeList.add(new ModelBox(bone8, 210, 84, -12.8676F, -11.0865F, -7.0F, 2, 1, 17, 0.0F, false));
        bone8.cubeList.add(new ModelBox(bone8, 206, 83, -12.8676F, -11.0865F, 10.0F, 2, 1, 17, 0.0F, false));
        bone8.cubeList.add(new ModelBox(bone8, 210, 84, -9.4438F, -10.4305F, -7.0F, 1, 2, 17, 0.0F, false));
        bone8.cubeList.add(new ModelBox(bone8, 210, 84, -9.4438F, -10.4305F, 10.0F, 1, 2, 17, 0.0F, false));

        bone10 = new ModelRenderer(this);
        bone10.setRotationPoint(-13.8995F, -19.299F, -13.0F);
        aug.addChild(bone10);
        setRotationAngle(bone10, 0.0F, 0.0F, 0.6109F);
        bone10.cubeList.add(new ModelBox(bone10, 210, 84, 8.4438F, -10.4305F, -7.0F, 1, 2, 17, 0.0F, true));
        bone10.cubeList.add(new ModelBox(bone10, 206, 83, 8.4438F, -10.4305F, 10.0F, 1, 2, 17, 0.0F, true));
        bone10.cubeList.add(new ModelBox(bone10, 210, 84, 10.8676F, -11.0865F, -7.0F, 2, 1, 17, 0.0F, true));
        bone10.cubeList.add(new ModelBox(bone10, 210, 84, 10.8676F, -11.0865F, 10.0F, 2, 1, 17, 0.0F, true));

        bone7 = new ModelRenderer(this);
        bone7.setRotationPoint(3.5F, 0.0F, 0.0F);
        aug.addChild(bone7);
        setRotationAngle(bone7, 0.0873F, 0.0F, 0.0F);
        bone7.cubeList.add(new ModelBox(bone7, 29, 88, -6.5F, -18.8527F, -20.4346F, 6, 12, 1, 0.0F, false));

        bone3 = new ModelRenderer(this);
        bone3.setRotationPoint(0.0F, -15.0391F, -10.8008F);
        aug.addChild(bone3);
        setRotationAngle(bone3, 0.3491F, 0.0F, 0.0F);
        bone3.cubeList.add(new ModelBox(bone3, 210, 84, -1.5858F, 2.4774F, -2.7201F, 2, 2, 13, 0.0F, false));
        bone3.cubeList.add(new ModelBox(bone3, 210, 84, -3.0F, -0.9369F, -2.7201F, 6, 4, 13, 0.0F, false));
        bone3.cubeList.add(new ModelBox(bone3, 210, 84, -0.4142F, 2.4774F, -2.7201F, 2, 2, 13, 0.0F, true));

        bone5 = new ModelRenderer(this);
        bone5.setRotationPoint(0.0F, 0.0F, 0.0F);
        bone3.addChild(bone5);
        setRotationAngle(bone5, 0.0F, 0.0F, -0.7854F);
        bone5.cubeList.add(new ModelBox(bone5, 210, 84, -4.2873F, 0.0447F, -2.7201F, 1, 2, 13, 0.0F, false));
        bone5.cubeList.add(new ModelBox(bone5, 210, 84, -2.0447F, 3.2873F, -2.7201F, 2, 1, 13, 0.0F, false));

        bone4 = new ModelRenderer(this);
        bone4.setRotationPoint(0.0F, -11.2188F, -16.7109F);
        aug.addChild(bone4);
        setRotationAngle(bone4, 0.6109F, 0.0F, 0.0F);
        bone4.cubeList.add(new ModelBox(bone4, 210, 84, -1.5858F, 2.4774F, -2.7201F, 2, 2, 13, 0.0F, false));
        bone4.cubeList.add(new ModelBox(bone4, 210, 84, -3.0F, -0.9369F, -2.7201F, 6, 4, 13, 0.0F, false));
        bone4.cubeList.add(new ModelBox(bone4, 210, 84, -0.4142F, 2.4774F, -2.7201F, 2, 2, 13, 0.0F, true));

        bone6 = new ModelRenderer(this);
        bone6.setRotationPoint(0.0F, 0.0F, 0.0F);
        bone4.addChild(bone6);
        setRotationAngle(bone6, 0.0F, 0.0F, -0.7854F);
        bone6.cubeList.add(new ModelBox(bone6, 210, 84, -4.2873F, 0.0447F, -2.7201F, 1, 2, 13, 0.0F, false));
        bone6.cubeList.add(new ModelBox(bone6, 210, 84, -2.0447F, 3.2873F, -2.7201F, 2, 1, 13, 0.0F, false));

        bone13 = new ModelRenderer(this);
        bone13.setRotationPoint(-3.0F, 0.0F, 0.0F);
        aug.addChild(bone13);
        setRotationAngle(bone13, -0.5236F, 0.0F, 0.0F);
        bone13.cubeList.add(new ModelBox(bone13, 210, 84, 0.0F, -22.3923F, 4.1244F, 6, 5, 2, 0.0F, true));

        addEntry(AnimationElement.MAGAZINE, stack -> magazine);
        addEntry(AnimationElement.CHARGING, stack -> slide);
        addEntry(AnimationElement.BOLT, stack -> bolt);
        addEntry(AnimationElement.BULLET, stack -> bullet);
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }

    @Override
    public void transformModel() {
        ModelTransformationHelper.defaultARTransform();
        GlStateManager.rotate(180, 0, 1, 0);
        GlStateManager.translate(0.0, 3.85, -6.0);
    }

    @Override
    public void renderModel(ItemStack stack) {
        aug.render(1f);
        if (!hasScopeAtachment(stack)) ironsights.render(1f);
    }
}

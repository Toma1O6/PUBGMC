package dev.toma.pubgmc.client.models.weapons;

import dev.toma.pubgmc.client.animation.AnimationElement;
import dev.toma.pubgmc.client.util.ModelTransformationHelper;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.item.ItemStack;

public class ModelS12K extends ModelGun {

    private final ModelRenderer bolt;
    private final ModelRenderer bone23;
    private final ModelRenderer bone24_r1;
    private final ModelRenderer bone23_r1;
    private final ModelRenderer bone26;
    private final ModelRenderer bone24;
    private final ModelRenderer bone19;
    private final ModelRenderer gun;
    private final ModelRenderer bone15;
    private final ModelRenderer bone16;
    private final ModelRenderer bone9;
    private final ModelRenderer bone10;
    private final ModelRenderer bone13;
    private final ModelRenderer bone11;
    private final ModelRenderer bone12;
    private final ModelRenderer bone3;
    private final ModelRenderer dust_cover;
    private final ModelRenderer bone29;
    private final ModelRenderer bone28;
    private final ModelRenderer bone17;
    private final ModelRenderer bone22;
    private final ModelRenderer bone21;
    private final ModelRenderer bone18;
    private final ModelRenderer bone20;
    private final ModelRenderer stock;
    private final ModelRenderer bone32;
    private final ModelRenderer bone30;
    private final ModelRenderer bone31;
    private final ModelRenderer bone27;
    private final ModelRenderer bone38;
    private final ModelRenderer bone25;
    private final ModelRenderer bone2;
    private final ModelRenderer handguard;
    private final ModelRenderer cube_r1;
    private final ModelRenderer cube_r2;
    private final ModelRenderer cube_r3;
    private final ModelRenderer cube_r4;
    private final ModelRenderer cube_r5;
    private final ModelRenderer cube_r6;
    private final ModelRenderer bullet3;
    private final ModelRenderer bullet;
    private final ModelRenderer rail;
    private final ModelRenderer grid;
    private final ModelRenderer cube_r9;
    private final ModelRenderer cube_r10;
    private final ModelRenderer cube_r11;
    private final ModelRenderer cube_r12;
    private final ModelRenderer toprail;
    private final ModelRenderer bone;
    private final ModelRenderer bone5;
    private final ModelRenderer bone4;
    private final ModelRenderer bone6;
    private final ModelRenderer magazine;
    private final ModelRenderer cube_r13;
    private final ModelRenderer cube_r14;

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }

    @Override
    public void renderModel(ItemStack stack) {
        gun.render(1f);
    }

    @Override
    public void transformModel() {
        ModelTransformationHelper.defaultShotgunTransform();
        GlStateManager.translate(-0.65000033, -1.0, -9.0);
        GlStateManager.rotate(180, 0, 1, 0);
    }

    public ModelS12K() {
        textureWidth = 512;
        textureHeight = 512;

        bolt = new ModelRenderer(this);
        bolt.setRotationPoint(-5.2266F, -37.9844F, -3.6563F);
        setRotationAngle(bolt, 0.0F, 0.0F, 0.2443F);
        bolt.cubeList.add(new ModelBox(bolt, 13, 148, 1.9339F, 1.7672F, -13.0F, 2, 4, 18, 0.0F, false));

        bone23 = new ModelRenderer(this);
        bone23.setRotationPoint(1.6874F, 3.7672F, -9.191F);
        bolt.addChild(bone23);
        setRotationAngle(bone23, 0.0F, -0.2618F, 0.0F);
        bone23.cubeList.add(new ModelBox(bone23, 13, 148, -0.4688F, -1.0F, -2.5F, 2, 2, 4, 0.0F, false));

        bone24_r1 = new ModelRenderer(this);
        bone24_r1.setRotationPoint(-3.0361F, -0.629F, 0.5F);
        bone23.addChild(bone24_r1);
        setRotationAngle(bone24_r1, 0.0F, 0.0F, 0.3491F);
        bone24_r1.cubeList.add(new ModelBox(bone24_r1, 13, 148, -1.0F, -1.0F, -1.0F, 2, 2, 2, 0.0F, false));

        bone23_r1 = new ModelRenderer(this);
        bone23_r1.setRotationPoint(-1.28F, -0.1585F, 0.5F);
        bone23.addChild(bone23_r1);
        setRotationAngle(bone23_r1, 0.0F, 0.0F, 0.1745F);
        bone23_r1.cubeList.add(new ModelBox(bone23_r1, 13, 148, -1.0F, -1.0F, -1.0F, 2, 2, 2, 0.0F, false));

        bone26 = new ModelRenderer(this);
        bone26.setRotationPoint(1.6874F, 3.7672F, -9.191F);
        bolt.addChild(bone26);
        setRotationAngle(bone26, 0.0F, -0.6981F, 0.0F);


        bone24 = new ModelRenderer(this);
        bone24.setRotationPoint(1.6874F, 3.7672F, -9.191F);
        bolt.addChild(bone24);
        setRotationAngle(bone24, 0.0F, -0.5236F, 0.0F);


        bone19 = new ModelRenderer(this);
        bone19.setRotationPoint(2.8245F, 3.7672F, -9.2656F);
        bolt.addChild(bone19);
        setRotationAngle(bone19, 0.0F, 0.0698F, 0.0F);
        bone19.cubeList.add(new ModelBox(bone19, 13, 148, -1.2093F, -1.0F, 0.9927F, 2, 2, 4, 0.0F, false));

        gun = new ModelRenderer(this);
        gun.setRotationPoint(0.0F, 24.0F, 0.0F);
        gun.cubeList.add(new ModelBox(gun, 71, 23, -5.0F, -50.0F, 10.0F, 10, 2, 14, 0.0F, false));
        gun.cubeList.add(new ModelBox(gun, 71, 23, -0.1094F, -48.5078F, 15.2344F, 4, 2, 8, 0.0F, false));
        gun.cubeList.add(new ModelBox(gun, 71, 23, -3.8906F, -48.5078F, 15.2344F, 4, 2, 8, 0.0F, true));
        gun.cubeList.add(new ModelBox(gun, 71, 23, -5.0F, -54.0F, -12.0F, 2, 4, 23, 0.0F, false));
        gun.cubeList.add(new ModelBox(gun, 71, 23, -5.0F, -54.0F, 11.0F, 2, 4, 13, 0.0F, false));
        gun.cubeList.add(new ModelBox(gun, 71, 23, -2.0F, -48.4525F, 4.9637F, 4, 6, 2, 0.0F, false));
        gun.cubeList.add(new ModelBox(gun, 71, 23, -4.0F, -49.6478F, 1.0731F, 8, 6, 4, 0.0F, false));
        gun.cubeList.add(new ModelBox(gun, 71, 23, -3.2766F, -43.6478F, 1.0731F, 2, 2, 4, 0.0F, false));
        gun.cubeList.add(new ModelBox(gun, 71, 23, 1.2766F, -43.6478F, 1.0731F, 2, 2, 4, 0.0F, true));
        gun.cubeList.add(new ModelBox(gun, 71, 23, -5.0F, -58.0F, 0.0F, 2, 4, 26, 0.0F, false));
        gun.cubeList.add(new ModelBox(gun, 71, 23, 3.0F, -58.0F, 0.0F, 2, 4, 26, 0.0F, true));
        gun.cubeList.add(new ModelBox(gun, 71, 23, -1.2427F, -62.2158F, 3.0F, 1, 2, 19, 0.0F, false));
        gun.cubeList.add(new ModelBox(gun, 71, 23, -1.2427F, -62.2158F, -21.0F, 1, 2, 22, 0.0F, false));
        gun.cubeList.add(new ModelBox(gun, 71, 23, -0.7573F, -62.7158F, 16.0F, 2, 2, 2, 0.0F, true));
        gun.cubeList.add(new ModelBox(gun, 71, 23, -0.7573F, -62.7158F, 1.0F, 2, 2, 2, 0.0F, true));
        gun.cubeList.add(new ModelBox(gun, 71, 23, -0.7573F, -62.7158F, -14.0F, 2, 2, 2, 0.0F, true));
        gun.cubeList.add(new ModelBox(gun, 71, 23, 4.0F, -58.5F, 16.0F, 1, 1, 2, 0.0F, true));
        gun.cubeList.add(new ModelBox(gun, 71, 23, 4.0F, -58.5F, 1.0F, 1, 1, 2, 0.0F, true));
        gun.cubeList.add(new ModelBox(gun, 71, 23, 4.0F, -58.5F, -14.0F, 1, 1, 2, 0.0F, true));
        gun.cubeList.add(new ModelBox(gun, 71, 23, -5.0F, -58.5F, 16.0F, 1, 1, 2, 0.0F, false));
        gun.cubeList.add(new ModelBox(gun, 71, 23, -5.0F, -58.5F, 1.0F, 1, 1, 2, 0.0F, false));
        gun.cubeList.add(new ModelBox(gun, 71, 23, -2.0F, -61.9346F, 20.4453F, 4, 2, 2, 0.0F, true));
        gun.cubeList.add(new ModelBox(gun, 71, 23, -0.7573F, -62.2158F, 3.0F, 2, 2, 19, 0.0F, true));
        gun.cubeList.add(new ModelBox(gun, 71, 23, -0.7573F, -62.2158F, -21.0F, 2, 2, 22, 0.0F, true));
        gun.cubeList.add(new ModelBox(gun, 71, 23, -1.2427F, -62.7158F, 16.0F, 2, 2, 2, 0.0F, false));
        gun.cubeList.add(new ModelBox(gun, 71, 23, -1.2427F, -62.7158F, 1.0F, 2, 2, 2, 0.0F, false));
        gun.cubeList.add(new ModelBox(gun, 71, 23, -1.2427F, -62.7158F, -14.0F, 2, 2, 2, 0.0F, false));
        gun.cubeList.add(new ModelBox(gun, 71, 23, -5.0F, -58.0F, -24.0F, 10, 6, 8, 0.0F, false));
        gun.cubeList.add(new ModelBox(gun, 71, 23, -5.0F, -56.0F, -16.0F, 2, 2, 16, 0.0F, false));
        gun.cubeList.add(new ModelBox(gun, 71, 23, 3.0F, -58.0F, -16.0F, 2, 4, 16, 0.0F, true));
        gun.cubeList.add(new ModelBox(gun, 71, 23, -5.0F, -54.7734F, 24.0F, 10, 2, 2, 0.0F, false));
        gun.cubeList.add(new ModelBox(gun, 71, 23, -5.0F, -54.0F, -14.0F, 2, 2, 2, 0.0F, false));
        gun.cubeList.add(new ModelBox(gun, 71, 23, 3.0F, -54.0F, -14.0F, 2, 2, 2, 0.0F, false));
        gun.cubeList.add(new ModelBox(gun, 79, 23, 3.0F, -54.0F, 6.0F, 2, 4, 18, 0.0F, true));
        gun.cubeList.add(new ModelBox(gun, 69, 18, 3.0F, -54.0F, -12.0F, 2, 4, 18, 0.0F, true));
        gun.cubeList.add(new ModelBox(gun, 71, 23, -5.1758F, -50.1953F, 19.8789F, 1, 1, 1, 0.0F, false));
        gun.cubeList.add(new ModelBox(gun, 71, 23, 4.1758F, -50.1953F, 19.8789F, 1, 1, 1, 0.0F, true));
        gun.cubeList.add(new ModelBox(gun, 71, 23, 4.1758F, -49.7656F, 12.1094F, 1, 1, 1, 0.0F, true));
        gun.cubeList.add(new ModelBox(gun, 86, 95, 4.1758F, -51.8789F, -16.0117F, 1, 1, 1, 0.0F, true));
        gun.cubeList.add(new ModelBox(gun, 86, 95, -5.1758F, -51.8789F, -16.0117F, 1, 1, 1, 0.0F, false));
        gun.cubeList.add(new ModelBox(gun, 71, 23, -5.1758F, -49.7656F, 12.1094F, 1, 1, 1, 0.0F, false));
        gun.cubeList.add(new ModelBox(gun, 71, 23, 2.5313F, -43.0859F, 3.7148F, 1, 1, 1, 0.0F, true));
        gun.cubeList.add(new ModelBox(gun, 71, 23, -3.5313F, -43.0859F, 3.7148F, 1, 1, 1, 0.0F, false));

        bone15 = new ModelRenderer(this);
        bone15.setRotationPoint(0.0F, -41.8125F, 17.0F);
        gun.addChild(bone15);
        setRotationAngle(bone15, 0.6981F, 0.0F, 0.0F);
        bone15.cubeList.add(new ModelBox(bone15, 71, 23, -2.0F, -2.5702F, -3.1521F, 4, 2, 4, 0.0F, false));
        bone15.cubeList.add(new ModelBox(bone15, 71, 23, -2.0F, -8.227F, -8.809F, 4, 2, 2, 0.0F, false));

        bone16 = new ModelRenderer(this);
        bone16.setRotationPoint(2.0F, 41.8125F, -17.0F);
        bone15.addChild(bone16);
        setRotationAngle(bone16, -0.7854F, 0.0F, 0.0F);
        bone16.cubeList.add(new ModelBox(bone16, 71, 23, -4.0F, -41.761F, -28.1772F, 4, 2, 8, 0.0F, false));

        bone9 = new ModelRenderer(this);
        bone9.setRotationPoint(0.0F, -1.7109F, -1.2344F);
        gun.addChild(bone9);
        setRotationAngle(bone9, 0.1222F, 0.0F, 0.0F);
        bone9.cubeList.add(new ModelBox(bone9, 71, 23, -4.0F, -42.5602F, 20.6427F, 4, 2, 10, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 71, 23, 0.0F, -42.5602F, 20.6427F, 4, 2, 10, 0.0F, false));

        bone10 = new ModelRenderer(this);
        bone10.setRotationPoint(-0.2734F, 0.0313F, 0.5078F);
        bone9.addChild(bone10);
        setRotationAngle(bone10, 0.1745F, 0.0F, 0.0F);
        bone10.cubeList.add(new ModelBox(bone10, 71, 23, -3.7266F, -32.8803F, 27.8519F, 8, 13, 8, 0.0F, false));
        bone10.cubeList.add(new ModelBox(bone10, 86, 90, -4.2266F, -34.8803F, 29.8519F, 9, 14, 4, 0.0F, false));
        bone10.cubeList.add(new ModelBox(bone10, 71, 23, -3.7266F, -20.466F, 29.2661F, 8, 2, 4, 0.0F, false));
        bone10.cubeList.add(new ModelBox(bone10, 71, 23, -3.7266F, -20.466F, 32.4377F, 8, 2, 2, 0.0F, false));

        bone13 = new ModelRenderer(this);
        bone13.setRotationPoint(0.2734F, 24.0224F, 18.818F);
        bone10.addChild(bone13);
        setRotationAngle(bone13, -0.7854F, 0.0F, 0.0F);
        bone13.cubeList.add(new ModelBox(bone13, 71, 23, -4.0F, -43.0886F, -19.9991F, 8, 2, 1, 0.0F, false));
        bone13.cubeList.add(new ModelBox(bone13, 71, 23, -4.0F, -38.4317F, -24.656F, 8, 1, 2, 0.0F, false));

        bone11 = new ModelRenderer(this);
        bone11.setRotationPoint(-0.2734F, 0.0313F, 0.5078F);
        bone9.addChild(bone11);
        setRotationAngle(bone11, -0.0873F, 0.0F, 0.0F);
        bone11.cubeList.add(new ModelBox(bone11, 71, 23, -3.7266F, -43.0634F, 18.4824F, 8, 4, 8, 0.0F, false));

        bone12 = new ModelRenderer(this);
        bone12.setRotationPoint(-0.2734F, 7.1875F, -2.9375F);
        bone9.addChild(bone12);
        setRotationAngle(bone12, 0.4363F, 0.0F, 0.0F);
        bone12.cubeList.add(new ModelBox(bone12, 71, 23, -3.7266F, -33.3087F, 41.55F, 8, 4, 2, 0.0F, false));

        bone3 = new ModelRenderer(this);
        bone3.setRotationPoint(0.0F, 0.0F, 0.0F);
        gun.addChild(bone3);
        setRotationAngle(bone3, -0.0873F, 0.0F, 0.0F);
        bone3.cubeList.add(new ModelBox(bone3, 71, 23, -5.001F, -52.6889F, -28.2215F, 10, 4, 7, 0.0F, false));
        bone3.cubeList.add(new ModelBox(bone3, 71, 23, -4.99F, -52.6889F, -21.2215F, 2, 4, 17, 0.0F, false));
        bone3.cubeList.add(new ModelBox(bone3, 71, 23, 2.999F, -52.6889F, -21.2215F, 2, 4, 17, 0.0F, false));
        bone3.cubeList.add(new ModelBox(bone3, 71, 23, -5.001F, -52.6889F, -4.2215F, 10, 4, 10, 0.0F, false));

        dust_cover = new ModelRenderer(this);
        dust_cover.setRotationPoint(20.0F, -31.0F, -4.0F);
        gun.addChild(dust_cover);
        dust_cover.cubeList.add(new ModelBox(dust_cover, 24, 158, -26.0F, -22.0F, 22.0F, 1, 2, 2, 0.0F, false));
        dust_cover.cubeList.add(new ModelBox(dust_cover, 24, 158, -26.1563F, -22.0F, 20.6172F, 1, 2, 2, 0.0F, false));
        dust_cover.cubeList.add(new ModelBox(dust_cover, 24, 158, -26.0F, -23.4142F, 18.5858F, 1, 2, 4, 0.0F, false));
        dust_cover.cubeList.add(new ModelBox(dust_cover, 24, 158, -26.0F, -25.5355F, 6.4645F, 1, 3, 10, 0.0F, false));
        dust_cover.cubeList.add(new ModelBox(dust_cover, 24, 158, -26.3633F, -25.7777F, 5.4645F, 2, 1, 3, 0.0F, false));
        dust_cover.cubeList.add(new ModelBox(dust_cover, 24, 158, -26.0F, -20.5858F, 20.5858F, 1, 2, 2, 0.0F, false));

        bone29 = new ModelRenderer(this);
        bone29.setRotationPoint(0.0F, 0.0F, 0.0F);
        dust_cover.addChild(bone29);
        setRotationAngle(bone29, -0.2618F, 0.0F, 0.0F);
        bone29.cubeList.add(new ModelBox(bone29, 24, 158, -26.0F, -26.2805F, 0.074F, 1, 3, 15, 0.0F, false));

        bone28 = new ModelRenderer(this);
        bone28.setRotationPoint(-25.0F, -21.0F, 23.0F);
        dust_cover.addChild(bone28);
        setRotationAngle(bone28, -0.7854F, 0.0F, 0.0F);
        bone28.cubeList.add(new ModelBox(bone28, 24, 158, -1.0F, -1.4142F, -2.0F, 1, 2, 2, 0.0F, false));
        bone28.cubeList.add(new ModelBox(bone28, 24, 158, -1.0F, 1.4142F, -7.8284F, 1, 3, 5, 0.0F, false));
        bone28.cubeList.add(new ModelBox(bone28, 24, 158, -1.0F, 0.0F, -0.5858F, 1, 2, 2, 0.0F, false));

        bone17 = new ModelRenderer(this);
        bone17.setRotationPoint(0.0F, -40.9837F, 3.0215F);
        gun.addChild(bone17);
        setRotationAngle(bone17, -0.192F, 0.0F, 0.0F);
        bone17.cubeList.add(new ModelBox(bone17, 71, 23, -2.0F, -3.0F, -1.0F, 4, 6, 2, 0.0F, true));

        bone22 = new ModelRenderer(this);
        bone22.setRotationPoint(4.0F, -66.0F, 13.0F);
        gun.addChild(bone22);
        setRotationAngle(bone22, 0.0F, 0.0F, 1.2217F);
        bone22.cubeList.add(new ModelBox(bone22, 71, 23, 1.7628F, 6.2208F, -10.0F, 2, 2, 19, 0.0F, true));
        bone22.cubeList.add(new ModelBox(bone22, 71, 23, 1.7628F, 6.2208F, -34.0F, 2, 2, 22, 0.0F, true));
        bone22.cubeList.add(new ModelBox(bone22, 71, 23, 1.293F, 6.0498F, 3.0F, 2, 2, 2, 0.0F, true));
        bone22.cubeList.add(new ModelBox(bone22, 71, 23, 1.293F, 6.0498F, -12.0F, 2, 2, 2, 0.0F, true));
        bone22.cubeList.add(new ModelBox(bone22, 71, 23, 1.293F, 6.0498F, -27.0F, 2, 2, 2, 0.0F, true));

        bone21 = new ModelRenderer(this);
        bone21.setRotationPoint(-4.0F, -66.0F, 13.0F);
        gun.addChild(bone21);
        setRotationAngle(bone21, 0.0F, 0.0F, -1.2217F);
        bone21.cubeList.add(new ModelBox(bone21, 71, 23, -3.7628F, 6.2208F, -10.0F, 2, 2, 19, 0.0F, false));
        bone21.cubeList.add(new ModelBox(bone21, 71, 23, -3.7628F, 6.2208F, -34.0F, 2, 2, 22, 0.0F, false));
        bone21.cubeList.add(new ModelBox(bone21, 71, 23, -3.293F, 6.0498F, 3.0F, 2, 2, 2, 0.0F, false));
        bone21.cubeList.add(new ModelBox(bone21, 71, 23, -3.293F, 6.0498F, -12.0F, 2, 2, 2, 0.0F, false));
        bone21.cubeList.add(new ModelBox(bone21, 71, 23, -3.293F, 6.0498F, -27.0F, 2, 2, 2, 0.0F, false));

        bone18 = new ModelRenderer(this);
        bone18.setRotationPoint(-4.0F, -64.0F, 13.0F);
        gun.addChild(bone18);
        setRotationAngle(bone18, 0.0F, 0.0F, 0.4887F);
        bone18.cubeList.add(new ModelBox(bone18, 71, 23, 1.9339F, 1.7672F, -13.0F, 2, 4, 22, 0.0F, false));
        bone18.cubeList.add(new ModelBox(bone18, 71, 23, 1.6991F, 1.3257F, 3.0F, 2, 4, 2, 0.0F, false));
        bone18.cubeList.add(new ModelBox(bone18, 71, 23, 1.6991F, 1.3257F, -12.0F, 2, 4, 2, 0.0F, false));
        bone18.cubeList.add(new ModelBox(bone18, 71, 23, 1.6991F, 1.3257F, -27.0F, 2, 2, 2, 0.0F, false));
        bone18.cubeList.add(new ModelBox(bone18, 71, 23, 1.9339F, 3.7672F, 9.0F, 4, 2, 2, 0.0F, false));
        bone18.cubeList.add(new ModelBox(bone18, 71, 23, 1.9339F, 1.7672F, -34.0F, 2, 4, 5, 0.0F, false));
        bone18.cubeList.add(new ModelBox(bone18, 71, 23, 1.9339F, 1.7672F, -29.0F, 2, 2, 16, 0.0F, false));

        bone20 = new ModelRenderer(this);
        bone20.setRotationPoint(4.0F, -64.0F, 13.0F);
        gun.addChild(bone20);
        setRotationAngle(bone20, 0.0F, 0.0F, -0.4887F);
        bone20.cubeList.add(new ModelBox(bone20, 71, 23, -3.9339F, 1.7672F, -13.0F, 2, 4, 22, 0.0F, true));
        bone20.cubeList.add(new ModelBox(bone20, 71, 23, -3.6991F, 1.3257F, 3.0F, 2, 4, 2, 0.0F, true));
        bone20.cubeList.add(new ModelBox(bone20, 71, 23, -3.6991F, 1.3257F, -12.0F, 2, 4, 2, 0.0F, true));
        bone20.cubeList.add(new ModelBox(bone20, 71, 23, -3.6991F, 1.3257F, -27.0F, 2, 4, 2, 0.0F, true));
        bone20.cubeList.add(new ModelBox(bone20, 71, 23, -5.9339F, 3.7672F, 9.0F, 4, 2, 2, 0.0F, true));
        bone20.cubeList.add(new ModelBox(bone20, 71, 23, -3.9339F, 1.7672F, -34.0F, 2, 4, 5, 0.0F, true));
        bone20.cubeList.add(new ModelBox(bone20, 71, 23, -3.9339F, 1.7672F, -29.0F, 2, 4, 16, 0.0F, true));

        stock = new ModelRenderer(this);
        stock.setRotationPoint(1.0F, 0.5F, 2.6719F);
        gun.addChild(stock);
        setRotationAngle(stock, -0.1222F, 0.0F, 0.0F);
        stock.cubeList.add(new ModelBox(stock, 29, 147, -3.0F, -59.7173F, 16.4474F, 4, 1, 10, 0.0F, false));
        stock.cubeList.add(new ModelBox(stock, 18, 87, -1.5F, -59.936F, 20.924F, 1, 1, 1, 0.0F, false));
        stock.cubeList.add(new ModelBox(stock, 18, 87, -1.5F, -59.936F, 17.8732F, 1, 1, 1, 0.0F, false));
        stock.cubeList.add(new ModelBox(stock, 18, 87, 0.6F, -60.1977F, 16.4474F, 1, 1, 9, 0.0F, false));
        stock.cubeList.add(new ModelBox(stock, 18, 87, -3.0F, -59.9455F, 22.6921F, 4, 1, 1, 0.0F, false));
        stock.cubeList.add(new ModelBox(stock, 18, 87, 1.3071F, -59.4906F, 16.4474F, 1, 5, 14, 0.0F, false));
        stock.cubeList.add(new ModelBox(stock, 18, 87, 1.3071F, -59.4906F, 30.4474F, 1, 5, 14, 0.0F, false));
        stock.cubeList.add(new ModelBox(stock, 18, 87, -4.0F, -61.3383F, 58.7052F, 6, 14, 1, 0.0F, false));
        stock.cubeList.add(new ModelBox(stock, 18, 87, -4.3071F, -59.5414F, 44.4474F, 1, 12, 15, 0.0F, true));
        stock.cubeList.add(new ModelBox(stock, 18, 87, 1.3071F, -59.5414F, 44.4474F, 1, 12, 15, 0.0F, false));
        stock.cubeList.add(new ModelBox(stock, 18, 87, -4.3071F, -59.4906F, 16.4474F, 1, 5, 14, 0.0F, true));
        stock.cubeList.add(new ModelBox(stock, 18, 87, -4.3071F, -59.4906F, 30.4474F, 1, 5, 14, 0.0F, true));
        stock.cubeList.add(new ModelBox(stock, 18, 87, -4.3071F, -54.4906F, 26.4474F, 1, 2, 18, 0.0F, true));
        stock.cubeList.add(new ModelBox(stock, 18, 87, 1.3071F, -54.4906F, 26.4474F, 1, 2, 18, 0.0F, false));
        stock.cubeList.add(new ModelBox(stock, 18, 87, -4.3071F, -52.4906F, 34.4474F, 1, 3, 10, 0.0F, true));
        stock.cubeList.add(new ModelBox(stock, 18, 87, 1.3071F, -52.4906F, 34.4474F, 1, 3, 10, 0.0F, false));
        stock.cubeList.add(new ModelBox(stock, 18, 87, -4.3071F, -61.4906F, 38.4474F, 1, 2, 11, 0.0F, true));
        stock.cubeList.add(new ModelBox(stock, 18, 87, -4.3071F, -61.4906F, 49.4474F, 1, 2, 10, 0.0F, true));
        stock.cubeList.add(new ModelBox(stock, 18, 87, -4.3071F, -61.4906F, 27.4474F, 1, 2, 11, 0.0F, true));
        stock.cubeList.add(new ModelBox(stock, 18, 87, 1.3071F, -61.4906F, 38.4474F, 1, 2, 11, 0.0F, false));
        stock.cubeList.add(new ModelBox(stock, 18, 87, 1.3071F, -61.4906F, 49.4474F, 1, 2, 10, 0.0F, false));
        stock.cubeList.add(new ModelBox(stock, 18, 87, -1.4F, -62.1977F, 38.4474F, 3, 1, 11, 0.0F, false));
        stock.cubeList.add(new ModelBox(stock, 18, 87, -1.4F, -62.1977F, 49.4474F, 3, 1, 10, 0.0F, false));
        stock.cubeList.add(new ModelBox(stock, 18, 87, -1.4F, -62.1977F, 27.4474F, 3, 1, 11, 0.0F, false));
        stock.cubeList.add(new ModelBox(stock, 18, 87, -3.6F, -62.1977F, 38.4474F, 3, 1, 11, 0.0F, true));
        stock.cubeList.add(new ModelBox(stock, 18, 87, -3.6F, -62.1977F, 49.4474F, 3, 1, 10, 0.0F, true));
        stock.cubeList.add(new ModelBox(stock, 18, 87, -3.6F, -62.1977F, 27.4474F, 3, 1, 11, 0.0F, true));
        stock.cubeList.add(new ModelBox(stock, 18, 87, 1.3071F, -61.4906F, 27.4474F, 1, 2, 11, 0.0F, false));
        stock.cubeList.add(new ModelBox(stock, 18, 87, -3.6F, -60.1977F, 16.4474F, 1, 1, 9, 0.0F, true));

        bone32 = new ModelRenderer(this);
        bone32.setRotationPoint(-0.6929F, -63.5656F, 32.054F);
        stock.addChild(bone32);
        setRotationAngle(bone32, 0.3142F, 0.0F, 0.0F);
        bone32.cubeList.add(new ModelBox(bone32, 18, 87, 0.0F, 0.5499F, -10.0223F, 3, 2, 5, 0.0F, true));
        bone32.cubeList.add(new ModelBox(bone32, 18, 87, 2.0F, 0.5499F, -12.0223F, 1, 1, 2, 0.0F, true));
        bone32.cubeList.add(new ModelBox(bone32, 18, 87, -3.6142F, 0.5499F, -12.0223F, 1, 1, 2, 0.0F, false));
        bone32.cubeList.add(new ModelBox(bone32, 18, 87, -3.6142F, 0.5499F, -10.0223F, 4, 2, 5, 0.0F, false));

        bone30 = new ModelRenderer(this);
        bone30.setRotationPoint(-3.5F, -53.9906F, 23.4474F);
        stock.addChild(bone30);
        setRotationAngle(bone30, -0.2443F, 0.0F, 0.0F);
        bone30.cubeList.add(new ModelBox(bone30, 18, 87, -0.807F, -1.3699F, -7.3882F, 1, 3, 15, 0.0F, true));
        bone30.cubeList.add(new ModelBox(bone30, 18, 87, 4.807F, -1.3699F, -7.3882F, 1, 3, 15, 0.0F, false));
        bone30.cubeList.add(new ModelBox(bone30, 18, 87, -0.807F, -1.3699F, 7.6118F, 1, 3, 15, 0.0F, true));
        bone30.cubeList.add(new ModelBox(bone30, 18, 87, 4.807F, -1.3699F, 7.6118F, 1, 3, 15, 0.0F, false));
        bone30.cubeList.add(new ModelBox(bone30, 18, 87, -0.807F, -2.8308F, 31.3813F, 1, 2, 5, 0.0F, true));
        bone30.cubeList.add(new ModelBox(bone30, 18, 87, -0.807F, -1.3699F, 22.6118F, 1, 3, 14, 0.0F, true));
        bone30.cubeList.add(new ModelBox(bone30, 18, 87, -0.5F, -2.3387F, 35.7493F, 6, 4, 1, 0.0F, true));
        bone30.cubeList.add(new ModelBox(bone30, 18, 87, 4.807F, -2.8308F, 31.3813F, 1, 2, 5, 0.0F, false));
        bone30.cubeList.add(new ModelBox(bone30, 18, 87, 4.807F, -1.3699F, 22.6118F, 1, 3, 14, 0.0F, false));
        bone30.cubeList.add(new ModelBox(bone30, 18, 87, -0.1F, 1.3372F, 13.6118F, 3, 1, 12, 0.0F, true));
        bone30.cubeList.add(new ModelBox(bone30, 18, 87, -0.1F, 1.3372F, 25.6118F, 3, 1, 11, 0.0F, true));
        bone30.cubeList.add(new ModelBox(bone30, 18, 87, -0.1F, 1.3372F, 1.6118F, 3, 1, 12, 0.0F, true));
        bone30.cubeList.add(new ModelBox(bone30, 18, 87, -0.1F, 1.3372F, -7.3882F, 3, 1, 9, 0.0F, true));
        bone30.cubeList.add(new ModelBox(bone30, 18, 87, 2.1F, 1.3372F, 13.6118F, 3, 1, 12, 0.0F, false));
        bone30.cubeList.add(new ModelBox(bone30, 18, 87, 2.1F, 1.3372F, 25.6118F, 3, 1, 11, 0.0F, false));
        bone30.cubeList.add(new ModelBox(bone30, 18, 87, 2.1F, 1.3372F, 1.6118F, 3, 1, 12, 0.0F, false));
        bone30.cubeList.add(new ModelBox(bone30, 18, 87, 2.1F, 1.3372F, -7.3882F, 3, 1, 9, 0.0F, false));

        bone31 = new ModelRenderer(this);
        bone31.setRotationPoint(-0.3071F, 2.1301F, 22.6118F);
        bone30.addChild(bone31);
        setRotationAngle(bone31, 0.0F, 0.0F, -0.7854F);
        bone31.cubeList.add(new ModelBox(bone31, 18, 87, 0.0F, -0.7071F, -12.0F, 1, 1, 15, 0.0F, true));
        bone31.cubeList.add(new ModelBox(bone31, 18, 87, 0.0F, -0.7071F, 3.0F, 1, 1, 11, 0.0F, true));
        bone31.cubeList.add(new ModelBox(bone31, 18, 87, 3.677F, 2.9698F, -12.0F, 1, 1, 15, 0.0F, true));
        bone31.cubeList.add(new ModelBox(bone31, 18, 87, 3.677F, 2.9698F, 3.0F, 1, 1, 11, 0.0F, true));
        bone31.cubeList.add(new ModelBox(bone31, 18, 87, 0.0F, -0.7071F, -30.0F, 1, 1, 18, 0.0F, true));
        bone31.cubeList.add(new ModelBox(bone31, 18, 87, 3.677F, 2.9698F, -30.0F, 1, 1, 18, 0.0F, true));

        bone27 = new ModelRenderer(this);
        bone27.setRotationPoint(7.0F, -59.6977F, 23.4474F);
        stock.addChild(bone27);
        setRotationAngle(bone27, 0.0F, 0.0F, 0.7854F);
        bone27.cubeList.add(new ModelBox(bone27, 18, 87, -4.1719F, 3.4648F, -7.0F, 1, 1, 9, 0.0F, false));
        bone27.cubeList.add(new ModelBox(bone27, 18, 87, -5.5861F, 2.0506F, 4.0F, 1, 1, 11, 0.0F, false));
        bone27.cubeList.add(new ModelBox(bone27, 18, 87, -9.2631F, 5.7276F, 4.0F, 1, 1, 11, 0.0F, true));
        bone27.cubeList.add(new ModelBox(bone27, 18, 87, -5.5861F, 2.0506F, 15.0F, 1, 1, 11, 0.0F, false));
        bone27.cubeList.add(new ModelBox(bone27, 18, 87, -5.5861F, 2.0506F, 26.0F, 1, 1, 10, 0.0F, false));
        bone27.cubeList.add(new ModelBox(bone27, 18, 87, -9.2631F, 5.7276F, 15.0F, 1, 1, 11, 0.0F, true));
        bone27.cubeList.add(new ModelBox(bone27, 18, 87, -9.2631F, 5.7276F, 26.0F, 1, 1, 10, 0.0F, true));
        bone27.cubeList.add(new ModelBox(bone27, 18, 87, -7.8489F, 7.1418F, -7.0F, 1, 1, 9, 0.0F, false));

        bone38 = new ModelRenderer(this);
        bone38.setRotationPoint(1.0F, 0.0F, 0.0F);
        gun.addChild(bone38);
        setRotationAngle(bone38, -0.1745F, 0.0F, 0.0F);
        bone38.cubeList.add(new ModelBox(bone38, 71, 23, -6.001F, -61.0766F, 14.4474F, 10, 8, 2, 0.0F, false));

        bone25 = new ModelRenderer(this);
        bone25.setRotationPoint(0.0F, -57.0766F, 15.4474F);
        gun.addChild(bone25);
        setRotationAngle(bone25, 0.7854F, 0.0F, 0.0F);
        bone25.cubeList.add(new ModelBox(bone25, 71, 23, -5.001F, 5.8771F, 6.1827F, 10, 2, 2, 0.0F, false));
        bone25.cubeList.add(new ModelBox(bone25, 71, 23, -4.0F, 3.8771F, 6.1827F, 8, 2, 2, 0.0F, false));
        bone25.cubeList.add(new ModelBox(bone25, 71, 23, -2.0F, 1.3693F, 6.4561F, 4, 6, 2, 0.0F, false));
        bone25.cubeList.add(new ModelBox(bone25, 29, 147, -1.0F, 2.6662F, 6.5811F, 2, 3, 2, 0.0F, false));
        bone25.cubeList.add(new ModelBox(bone25, 71, 23, -3.0F, 1.4709F, 5.8155F, 6, 4, 2, 0.0F, false));

        bone2 = new ModelRenderer(this);
        bone2.setRotationPoint(0.0F, -3.0F, 32.0F);
        gun.addChild(bone2);
        setRotationAngle(bone2, 0.7854F, 0.0F, 0.0F);
        bone2.cubeList.add(new ModelBox(bone2, 71, 23, -5.001F, -39.4767F, 26.163F, 10, 2, 2, 0.0F, false));

        handguard = new ModelRenderer(this);
        handguard.setRotationPoint(0.0F, 24.0F, 0.0F);
        handguard.cubeList.add(new ModelBox(handguard, 0, 81, -4.0F, -59.0F, -41.0F, 8, 1, 20, 0.0F, false));
        handguard.cubeList.add(new ModelBox(handguard, 0, 81, -4.0F, -59.0F, -53.0F, 8, 1, 12, 0.0F, false));
        handguard.cubeList.add(new ModelBox(handguard, 0, 81, -4.0F, -60.0F, -25.0F, 8, 1, 4, 0.0F, false));
        handguard.cubeList.add(new ModelBox(handguard, 0, 81, -4.0F, -60.0F, -31.0F, 8, 1, 2, 0.0F, false));
        handguard.cubeList.add(new ModelBox(handguard, 0, 81, -4.0F, -60.0F, -37.0F, 8, 1, 2, 0.0F, false));
        handguard.cubeList.add(new ModelBox(handguard, 0, 81, -4.0F, -60.0F, -49.0F, 8, 1, 2, 0.0F, false));
        handguard.cubeList.add(new ModelBox(handguard, 0, 81, -4.0F, -60.0F, -43.0F, 8, 1, 2, 0.0F, false));
        handguard.cubeList.add(new ModelBox(handguard, 0, 81, -4.0F, -60.0F, -57.0F, 1, 2, 4, 0.0F, false));
        handguard.cubeList.add(new ModelBox(handguard, 0, 81, 3.0F, -60.0F, -57.0F, 1, 2, 4, 0.0F, false));
        handguard.cubeList.add(new ModelBox(handguard, 0, 81, -3.0F, -61.0F, -54.0F, 6, 9, 1, 0.0F, false));
        handguard.cubeList.add(new ModelBox(handguard, 69, 15, -2.0F, -57.5F, -76.0F, 4, 4, 22, 0.0F, false));
        handguard.cubeList.add(new ModelBox(handguard, 0, 81, -4.5F, -58.0F, -32.0F, 9, 6, 8, 0.0F, false));
        handguard.cubeList.add(new ModelBox(handguard, 0, 81, -4.5F, -58.0F, -36.0F, 9, 6, 2, 0.0F, false));
        handguard.cubeList.add(new ModelBox(handguard, 0, 81, -4.5F, -58.0F, -40.0F, 9, 6, 2, 0.0F, false));
        handguard.cubeList.add(new ModelBox(handguard, 0, 81, -4.5F, -58.0F, -44.0F, 9, 6, 2, 0.0F, false));
        handguard.cubeList.add(new ModelBox(handguard, 0, 81, -4.5F, -58.0F, -48.0F, 9, 6, 2, 0.0F, false));
        handguard.cubeList.add(new ModelBox(handguard, 0, 81, -4.5F, -58.0F, -57.0F, 1, 6, 7, 0.0F, false));
        handguard.cubeList.add(new ModelBox(handguard, 0, 81, 3.5F, -58.0F, -57.0F, 1, 6, 7, 0.0F, false));
        handguard.cubeList.add(new ModelBox(handguard, 0, 81, 3.0F, -58.0F, -57.0F, 1, 6, 7, 0.0F, false));
        handguard.cubeList.add(new ModelBox(handguard, 0, 81, -4.0F, -58.0F, -57.0F, 1, 6, 7, 0.0F, true));
        handguard.cubeList.add(new ModelBox(handguard, 0, 81, -4.0F, -58.134F, -34.0F, 8, 7, 2, 0.0F, false));
        handguard.cubeList.add(new ModelBox(handguard, 0, 81, -4.0F, -58.134F, -38.0F, 8, 7, 2, 0.0F, false));
        handguard.cubeList.add(new ModelBox(handguard, 0, 81, -4.0F, -58.134F, -42.0F, 8, 7, 2, 0.0F, false));
        handguard.cubeList.add(new ModelBox(handguard, 0, 81, -4.0F, -58.134F, -46.0F, 8, 7, 2, 0.0F, false));
        handguard.cubeList.add(new ModelBox(handguard, 0, 81, -4.0F, -58.134F, -50.0F, 8, 7, 2, 0.0F, false));
        handguard.cubeList.add(new ModelBox(handguard, 0, 81, -3.5F, -52.2679F, -35.0F, 7, 2, 11, 0.0F, false));
        handguard.cubeList.add(new ModelBox(handguard, 0, 81, -3.5F, -52.2679F, -57.0F, 7, 2, 11, 0.0F, false));
        handguard.cubeList.add(new ModelBox(handguard, 0, 81, -3.5F, -52.2679F, -46.0F, 7, 2, 11, 0.0F, false));
        handguard.cubeList.add(new ModelBox(handguard, 70, 81, -3.0F, -60.7321F, -41.0F, 6, 2, 20, 0.0F, false));
        handguard.cubeList.add(new ModelBox(handguard, 70, 81, -3.0F, -60.7321F, -53.0F, 6, 2, 12, 0.0F, false));
        handguard.cubeList.add(new ModelBox(handguard, 0, 81, -3.0F, -61.7321F, -41.0F, 6, 1, 20, 0.0F, false));
        handguard.cubeList.add(new ModelBox(handguard, 27, 165, -1.0F, -62.7321F, -25.0F, 2, 1, 2, 0.0F, false));
        handguard.cubeList.add(new ModelBox(handguard, 27, 165, -1.0F, -62.7321F, -46.0F, 2, 1, 2, 0.0F, false));
        handguard.cubeList.add(new ModelBox(handguard, 27, 165, -1.0F, -62.7321F, -39.0F, 2, 1, 2, 0.0F, false));
        handguard.cubeList.add(new ModelBox(handguard, 27, 165, -1.0F, -62.7321F, -32.0F, 2, 1, 2, 0.0F, false));
        handguard.cubeList.add(new ModelBox(handguard, 27, 165, -1.0F, -62.7321F, -53.0F, 2, 1, 2, 0.0F, false));
        handguard.cubeList.add(new ModelBox(handguard, 0, 141, -1.5F, -63.7321F, -51.0F, 3, 1, 13, 0.0F, false));
        handguard.cubeList.add(new ModelBox(handguard, 0, 141, -1.5F, -63.7321F, -38.0F, 3, 1, 13, 0.0F, false));
        handguard.cubeList.add(new ModelBox(handguard, 0, 141, -2.0F, -63.7321F, -25.0F, 4, 1, 3, 0.0F, false));
        handguard.cubeList.add(new ModelBox(handguard, 0, 141, -2.0F, -63.7321F, -54.0F, 4, 1, 3, 0.0F, false));
        handguard.cubeList.add(new ModelBox(handguard, 0, 141, 1.4617F, -64.6191F, -25.0F, 1, 1, 3, 0.0F, false));
        handguard.cubeList.add(new ModelBox(handguard, 0, 141, 1.4617F, -64.6191F, -54.0F, 1, 1, 3, 0.0F, false));
        handguard.cubeList.add(new ModelBox(handguard, 102, 29, 1.0F, -65.1191F, -24.5F, 1, 2, 2, 0.0F, false));
        handguard.cubeList.add(new ModelBox(handguard, 0, 141, -2.4617F, -64.6191F, -25.0F, 1, 1, 3, 0.0F, true));
        handguard.cubeList.add(new ModelBox(handguard, 0, 141, -2.4617F, -64.6191F, -54.0F, 1, 1, 3, 0.0F, true));
        handguard.cubeList.add(new ModelBox(handguard, 102, 29, -2.0F, -65.1191F, -24.5F, 1, 2, 2, 0.0F, true));
        handguard.cubeList.add(new ModelBox(handguard, 102, 29, -0.5F, -65.1191F, -53.5F, 1, 1, 2, 0.0F, true));
        handguard.cubeList.add(new ModelBox(handguard, 102, 29, -1.0F, -64.1191F, -24.5F, 2, 1, 2, 0.0F, true));
        handguard.cubeList.add(new ModelBox(handguard, 102, 29, -1.5F, -64.1191F, -53.5F, 3, 1, 2, 0.0F, true));
        handguard.cubeList.add(new ModelBox(handguard, 0, 81, -3.0F, -61.7321F, -53.0F, 6, 1, 12, 0.0F, false));
        handguard.cubeList.add(new ModelBox(handguard, 0, 81, -3.0F, -61.7321F, -57.0F, 6, 1, 4, 0.0F, false));

        cube_r1 = new ModelRenderer(this);
        cube_r1.setRotationPoint(-0.9019F, -59.366F, -24.5F);
        handguard.addChild(cube_r1);
        setRotationAngle(cube_r1, 0.0F, 0.0F, 0.5236F);
        cube_r1.cubeList.add(new ModelBox(cube_r1, 2, 82, -3.0F, -1.0F, -28.5F, 1, 1, 28, 0.0F, true));
        cube_r1.cubeList.add(new ModelBox(cube_r1, 0, 81, -3.0F, -1.0F, -32.5F, 1, 2, 4, 0.0F, true));
        cube_r1.cubeList.add(new ModelBox(cube_r1, 0, 81, -3.0F, 0.0F, -18.5F, 1, 1, 2, 0.0F, true));
        cube_r1.cubeList.add(new ModelBox(cube_r1, 0, 81, -3.0F, 0.0F, -24.5F, 1, 1, 2, 0.0F, true));
        cube_r1.cubeList.add(new ModelBox(cube_r1, 0, 81, -3.0F, 0.0F, -12.5F, 1, 1, 2, 0.0F, true));
        cube_r1.cubeList.add(new ModelBox(cube_r1, 0, 81, -3.0F, 0.0F, -6.5F, 1, 1, 2, 0.0F, true));
        cube_r1.cubeList.add(new ModelBox(cube_r1, 0, 81, -3.0F, -1.0F, -0.5F, 1, 2, 4, 0.0F, true));

        cube_r2 = new ModelRenderer(this);
        cube_r2.setRotationPoint(0.9019F, -59.366F, -22.5F);
        handguard.addChild(cube_r2);
        setRotationAngle(cube_r2, 0.0F, 0.0F, -0.5236F);
        cube_r2.cubeList.add(new ModelBox(cube_r2, 0, 81, 2.0F, 0.0F, -26.5F, 1, 1, 2, 0.0F, false));
        cube_r2.cubeList.add(new ModelBox(cube_r2, 0, 81, 2.0F, 0.0F, -20.5F, 1, 1, 2, 0.0F, false));
        cube_r2.cubeList.add(new ModelBox(cube_r2, 0, 81, 2.0F, -1.0F, -30.5F, 1, 1, 28, 0.0F, false));
        cube_r2.cubeList.add(new ModelBox(cube_r2, 0, 81, 2.0F, 0.0F, -14.5F, 1, 1, 2, 0.0F, false));
        cube_r2.cubeList.add(new ModelBox(cube_r2, 0, 81, 2.0F, 0.0F, -8.5F, 1, 1, 2, 0.0F, false));
        cube_r2.cubeList.add(new ModelBox(cube_r2, 0, 81, 2.0F, -1.0F, -2.5F, 1, 2, 4, 0.0F, false));
        cube_r2.cubeList.add(new ModelBox(cube_r2, 0, 81, 2.0F, -1.0F, -34.5F, 1, 2, 4, 0.0F, false));

        cube_r3 = new ModelRenderer(this);
        cube_r3.setRotationPoint(-0.4569F, -64.0991F, -52.5F);
        handguard.addChild(cube_r3);
        setRotationAngle(cube_r3, 0.0F, 0.0F, -0.48F);
        cube_r3.cubeList.add(new ModelBox(cube_r3, 0, 141, -2.0F, -0.5F, -1.5F, 1, 1, 3, 0.0F, true));
        cube_r3.cubeList.add(new ModelBox(cube_r3, 0, 141, -2.0F, -0.5F, 27.5F, 1, 1, 3, 0.0F, true));

        cube_r4 = new ModelRenderer(this);
        cube_r4.setRotationPoint(0.4569F, -64.0991F, -52.5F);
        handguard.addChild(cube_r4);
        setRotationAngle(cube_r4, 0.0F, 0.0F, 0.48F);
        cube_r4.cubeList.add(new ModelBox(cube_r4, 0, 141, 1.0F, -0.5F, -1.5F, 1, 1, 3, 0.0F, false));
        cube_r4.cubeList.add(new ModelBox(cube_r4, 0, 141, 1.0F, -0.5F, 27.5F, 1, 1, 3, 0.0F, false));

        cube_r5 = new ModelRenderer(this);
        cube_r5.setRotationPoint(0.8971F, -51.6519F, -52.0F);
        handguard.addChild(cube_r5);
        setRotationAngle(cube_r5, 0.0F, 0.0F, -0.5236F);
        cube_r5.cubeList.add(new ModelBox(cube_r5, 0, 81, -4.5F, -2.0F, 2.0F, 1, 1, 2, 0.0F, true));
        cube_r5.cubeList.add(new ModelBox(cube_r5, 0, 81, -4.5F, -2.0F, 6.0F, 1, 1, 2, 0.0F, true));
        cube_r5.cubeList.add(new ModelBox(cube_r5, 0, 81, -4.5F, -2.0F, 10.0F, 1, 1, 2, 0.0F, true));
        cube_r5.cubeList.add(new ModelBox(cube_r5, 0, 81, -4.5F, -2.0F, 14.0F, 1, 1, 2, 0.0F, true));
        cube_r5.cubeList.add(new ModelBox(cube_r5, 0, 81, -4.5F, -2.0F, 18.0F, 1, 1, 2, 0.0F, true));
        cube_r5.cubeList.add(new ModelBox(cube_r5, 0, 81, -4.5F, -3.0F, -5.0F, 1, 2, 7, 0.0F, true));
        cube_r5.cubeList.add(new ModelBox(cube_r5, 0, 81, -4.5F, -3.0F, 4.0F, 1, 2, 2, 0.0F, true));
        cube_r5.cubeList.add(new ModelBox(cube_r5, 0, 81, -4.5F, -3.0F, 8.0F, 1, 2, 2, 0.0F, true));
        cube_r5.cubeList.add(new ModelBox(cube_r5, 0, 81, -4.5F, -3.0F, 12.0F, 1, 2, 2, 0.0F, true));
        cube_r5.cubeList.add(new ModelBox(cube_r5, 0, 81, -4.5F, -3.0F, 16.0F, 1, 2, 2, 0.0F, true));
        cube_r5.cubeList.add(new ModelBox(cube_r5, 0, 81, -4.5F, -3.0F, 20.0F, 1, 2, 8, 0.0F, true));

        cube_r6 = new ModelRenderer(this);
        cube_r6.setRotationPoint(-0.8971F, -51.6519F, -52.0F);
        handguard.addChild(cube_r6);
        setRotationAngle(cube_r6, 0.0F, 0.0F, 0.5236F);
        cube_r6.cubeList.add(new ModelBox(cube_r6, 0, 81, 3.5F, -2.0F, 2.0F, 1, 1, 2, 0.0F, false));
        cube_r6.cubeList.add(new ModelBox(cube_r6, 0, 81, 3.5F, -2.0F, 6.0F, 1, 1, 2, 0.0F, false));
        cube_r6.cubeList.add(new ModelBox(cube_r6, 0, 81, 3.5F, -2.0F, 10.0F, 1, 1, 2, 0.0F, false));
        cube_r6.cubeList.add(new ModelBox(cube_r6, 0, 81, 3.5F, -2.0F, 14.0F, 1, 1, 2, 0.0F, false));
        cube_r6.cubeList.add(new ModelBox(cube_r6, 0, 81, 3.5F, -2.0F, 18.0F, 1, 1, 2, 0.0F, false));
        cube_r6.cubeList.add(new ModelBox(cube_r6, 0, 81, 3.5F, -3.0F, -5.0F, 1, 2, 7, 0.0F, false));
        cube_r6.cubeList.add(new ModelBox(cube_r6, 0, 81, 3.5F, -3.0F, 4.0F, 1, 2, 2, 0.0F, false));
        cube_r6.cubeList.add(new ModelBox(cube_r6, 0, 81, 3.5F, -3.0F, 8.0F, 1, 2, 2, 0.0F, false));
        cube_r6.cubeList.add(new ModelBox(cube_r6, 0, 81, 3.5F, -3.0F, 12.0F, 1, 2, 2, 0.0F, false));
        cube_r6.cubeList.add(new ModelBox(cube_r6, 0, 81, 3.5F, -3.0F, 16.0F, 1, 2, 2, 0.0F, false));
        cube_r6.cubeList.add(new ModelBox(cube_r6, 0, 81, 3.5F, -3.0F, 20.0F, 1, 2, 8, 0.0F, false));

        bullet3 = new ModelRenderer(this);
        bullet3.setRotationPoint(0.0F, -28.5F, -0.5F);
        setRotationAngle(bullet3, -0.0873F, 0.0F, 0.0F);


        bullet = new ModelRenderer(this);
        bullet.setRotationPoint(-0.5F, 55.5676F, 0.8539F);
        bullet3.addChild(bullet);
        bullet.cubeList.add(new ModelBox(bullet, 3, 454, -1.5F, -59.5F, -15.0F, 4, 3, 9, 0.0F, false));
        bullet.cubeList.add(new ModelBox(bullet, 8, 489, -1.5F, -59.5F, -5.0F, 1, 3, 3, 0.0F, false));
        bullet.cubeList.add(new ModelBox(bullet, 8, 489, 1.5F, -59.5F, -5.0F, 1, 3, 3, 0.0F, false));
        bullet.cubeList.add(new ModelBox(bullet, 8, 489, 1.0F, -59.5F, -3.1F, 1, 2, 1, 0.0F, false));
        bullet.cubeList.add(new ModelBox(bullet, 8, 489, -1.0F, -58.5F, -3.1F, 1, 2, 1, 0.0F, false));
        bullet.cubeList.add(new ModelBox(bullet, 26, 94, 0.0F, -58.5F, -3.0F, 1, 1, 1, 0.0F, false));
        bullet.cubeList.add(new ModelBox(bullet, 8, 489, 0.0F, -57.5F, -3.1F, 2, 1, 1, 0.0F, false));
        bullet.cubeList.add(new ModelBox(bullet, 8, 489, -1.0F, -59.5F, -3.1F, 2, 1, 1, 0.0F, false));
        bullet.cubeList.add(new ModelBox(bullet, 16, 459, -1.0F, -59.5F, -6.0F, 3, 3, 1, 0.0F, false));
        bullet.cubeList.add(new ModelBox(bullet, 3, 454, -1.0F, -60.0F, -15.0F, 3, 4, 9, 0.0F, false));
        bullet.cubeList.add(new ModelBox(bullet, 8, 489, -1.0F, -60.0F, -5.0F, 3, 1, 3, 0.0F, false));
        bullet.cubeList.add(new ModelBox(bullet, 8, 489, -1.0F, -57.0F, -5.0F, 3, 1, 3, 0.0F, false));

        rail = new ModelRenderer(this);
        rail.setRotationPoint(0.0F, 24.0F, 0.0F);


        grid = new ModelRenderer(this);
        grid.setRotationPoint(0.2F, -0.5F, -1.5F);
        rail.addChild(grid);
        grid.cubeList.add(new ModelBox(grid, 19, 94, 4.8F, -55.5F, -0.5F, 1, 1, 2, 0.0F, false));
        grid.cubeList.add(new ModelBox(grid, 97, 89, 5.0F, -56.0F, 0.0F, 1, 1, 1, 0.0F, false));
        grid.cubeList.add(new ModelBox(grid, 19, 94, 4.8F, -56.5F, -1.5F, 1, 1, 9, 0.0F, false));
        grid.cubeList.add(new ModelBox(grid, 19, 94, 4.8F, -58.5F, -0.5F, 1, 1, 7, 0.0F, false));
        grid.cubeList.add(new ModelBox(grid, 19, 94, 4.8F, -57.5F, 4.5F, 1, 1, 1, 0.0F, false));
        grid.cubeList.add(new ModelBox(grid, 29, 81, 4.8F, -57.5F, 2.5F, 1, 1, 1, 0.0F, false));
        grid.cubeList.add(new ModelBox(grid, 19, 86, 4.8F, -57.5F, 0.5F, 1, 1, 1, 0.0F, false));
        grid.cubeList.add(new ModelBox(grid, 19, 94, 4.8F, -58.5F, 6.5F, 1, 2, 1, 0.0F, false));
        grid.cubeList.add(new ModelBox(grid, 19, 94, 4.8F, -58.5F, -1.5F, 1, 2, 1, 0.0F, false));
        grid.cubeList.add(new ModelBox(grid, 19, 94, 4.8F, -55.5F, 4.5F, 1, 1, 2, 0.0F, false));
        grid.cubeList.add(new ModelBox(grid, 97, 89, 5.0F, -56.0F, 5.0F, 1, 1, 1, 0.0F, false));

        cube_r9 = new ModelRenderer(this);
        cube_r9.setRotationPoint(0.9399F, -62.1003F, 10.5F);
        grid.addChild(cube_r9);
        setRotationAngle(cube_r9, 0.0F, 0.0F, -1.3963F);
        cube_r9.cubeList.add(new ModelBox(cube_r9, 19, 94, 0.0F, -1.0F, -11.0F, 1, 1, 7, 0.0F, false));
        cube_r9.cubeList.add(new ModelBox(cube_r9, 19, 94, 0.0F, -3.0F, -6.0F, 1, 2, 1, 0.0F, false));
        cube_r9.cubeList.add(new ModelBox(cube_r9, 19, 94, 0.0F, 0.0F, -6.0F, 1, 1, 1, 0.0F, false));
        cube_r9.cubeList.add(new ModelBox(cube_r9, 19, 94, 0.0F, -3.0F, -8.0F, 1, 2, 1, 0.0F, false));
        cube_r9.cubeList.add(new ModelBox(cube_r9, 19, 94, 0.0F, 0.0F, -8.0F, 1, 1, 1, 0.0F, false));
        cube_r9.cubeList.add(new ModelBox(cube_r9, 19, 94, 0.0F, -3.0F, -10.0F, 1, 2, 1, 0.0F, false));
        cube_r9.cubeList.add(new ModelBox(cube_r9, 19, 94, 0.0F, 0.0F, -10.0F, 1, 1, 1, 0.0F, false));
        cube_r9.cubeList.add(new ModelBox(cube_r9, 19, 94, 0.0F, -3.0F, -12.0F, 1, 2, 1, 0.0F, false));
        cube_r9.cubeList.add(new ModelBox(cube_r9, 19, 94, 0.0F, -1.0F, -12.0F, 1, 2, 1, 0.0F, false));
        cube_r9.cubeList.add(new ModelBox(cube_r9, 19, 94, 0.0F, -3.0F, -4.0F, 1, 2, 1, 0.0F, false));
        cube_r9.cubeList.add(new ModelBox(cube_r9, 19, 94, 0.0F, -1.0F, -4.0F, 1, 2, 1, 0.0F, false));

        cube_r10 = new ModelRenderer(this);
        cube_r10.setRotationPoint(2.4644F, -61.5454F, 9.5F);
        grid.addChild(cube_r10);
        setRotationAngle(cube_r10, 0.0F, 0.0F, -1.0472F);
        cube_r10.cubeList.add(new ModelBox(cube_r10, 19, 94, 0.0F, 0.0F, -5.0F, 1, 1, 1, 0.0F, false));
        cube_r10.cubeList.add(new ModelBox(cube_r10, 19, 94, 0.0F, 0.0F, -7.0F, 1, 1, 1, 0.0F, false));
        cube_r10.cubeList.add(new ModelBox(cube_r10, 19, 94, 0.0F, 0.0F, -9.0F, 1, 1, 1, 0.0F, false));
        cube_r10.cubeList.add(new ModelBox(cube_r10, 19, 94, 0.0F, -1.0F, -10.0F, 1, 1, 7, 0.0F, false));
        cube_r10.cubeList.add(new ModelBox(cube_r10, 19, 94, 0.0F, -1.0F, -11.0F, 1, 2, 1, 0.0F, false));
        cube_r10.cubeList.add(new ModelBox(cube_r10, 19, 94, 0.0F, -1.0F, -3.0F, 1, 2, 1, 0.0F, false));

        cube_r11 = new ModelRenderer(this);
        cube_r11.setRotationPoint(3.3857F, -60.8857F, 1.5F);
        grid.addChild(cube_r11);
        setRotationAngle(cube_r11, 0.0F, 0.0F, -0.6981F);
        cube_r11.cubeList.add(new ModelBox(cube_r11, 19, 86, 0.0F, 0.5F, -1.0F, 1, 1, 1, 0.0F, false));
        cube_r11.cubeList.add(new ModelBox(cube_r11, 29, 81, 0.0F, 0.5F, 1.0F, 1, 1, 1, 0.0F, false));
        cube_r11.cubeList.add(new ModelBox(cube_r11, 19, 94, 0.0F, 0.5F, 3.0F, 1, 1, 1, 0.0F, false));
        cube_r11.cubeList.add(new ModelBox(cube_r11, 19, 94, 0.0F, -0.5F, -2.0F, 1, 1, 7, 0.0F, false));
        cube_r11.cubeList.add(new ModelBox(cube_r11, 19, 94, 0.0F, -0.5F, -3.0F, 1, 2, 1, 0.0F, false));
        cube_r11.cubeList.add(new ModelBox(cube_r11, 19, 94, 0.0F, -0.5F, 5.0F, 1, 2, 1, 0.0F, false));

        cube_r12 = new ModelRenderer(this);
        cube_r12.setRotationPoint(4.6893F, -58.6278F, -5.0F);
        grid.addChild(cube_r12);
        setRotationAngle(cube_r12, 0.0F, 0.0F, -0.3491F);
        cube_r12.cubeList.add(new ModelBox(cube_r12, 19, 86, 0.0F, -0.5F, 5.5F, 1, 1, 1, 0.0F, false));
        cube_r12.cubeList.add(new ModelBox(cube_r12, 29, 81, 0.0F, -0.5F, 7.5F, 1, 1, 1, 0.0F, false));
        cube_r12.cubeList.add(new ModelBox(cube_r12, 19, 94, 0.0F, -0.5F, 9.5F, 1, 1, 1, 0.0F, false));
        cube_r12.cubeList.add(new ModelBox(cube_r12, 19, 94, 0.0F, -1.5F, 4.5F, 1, 1, 7, 0.0F, false));
        cube_r12.cubeList.add(new ModelBox(cube_r12, 19, 94, 0.0F, -1.5F, 3.5F, 1, 2, 1, 0.0F, false));
        cube_r12.cubeList.add(new ModelBox(cube_r12, 19, 94, 0.0F, -1.5F, 11.5F, 1, 2, 1, 0.0F, false));

        toprail = new ModelRenderer(this);
        toprail.setRotationPoint(0.0F, 0.0F, 0.0F);
        rail.addChild(toprail);
        toprail.cubeList.add(new ModelBox(toprail, 82, 95, -1.5F, -64.3F, 2.0F, 3, 1, 15, 0.0F, false));
        toprail.cubeList.add(new ModelBox(toprail, 82, 95, -1.0F, -65.3F, 16.0F, 2, 1, 1, 0.0F, false));
        toprail.cubeList.add(new ModelBox(toprail, 82, 95, -0.134F, -64.8F, 15.0F, 2, 0, 1, 0.0F, false));
        toprail.cubeList.add(new ModelBox(toprail, 82, 95, -1.866F, -64.8F, 15.0F, 2, 0, 1, 0.0F, true));
        toprail.cubeList.add(new ModelBox(toprail, 82, 95, -1.0F, -65.3F, 8.0F, 2, 1, 1, 0.0F, false));
        toprail.cubeList.add(new ModelBox(toprail, 82, 95, -0.134F, -64.8F, 7.0F, 2, 0, 1, 0.0F, false));
        toprail.cubeList.add(new ModelBox(toprail, 82, 95, -1.866F, -64.8F, 7.0F, 2, 0, 1, 0.0F, true));
        toprail.cubeList.add(new ModelBox(toprail, 82, 95, -1.0F, -65.3F, 0.0F, 2, 1, 1, 0.0F, false));
        toprail.cubeList.add(new ModelBox(toprail, 82, 95, -0.134F, -64.8F, -1.0F, 2, 0, 1, 0.0F, false));
        toprail.cubeList.add(new ModelBox(toprail, 82, 95, -1.866F, -64.8F, -1.0F, 2, 0, 1, 0.0F, true));
        toprail.cubeList.add(new ModelBox(toprail, 82, 95, -1.0F, -65.3F, 12.0F, 2, 1, 1, 0.0F, false));
        toprail.cubeList.add(new ModelBox(toprail, 82, 95, -0.134F, -64.8F, 11.0F, 2, 0, 1, 0.0F, false));
        toprail.cubeList.add(new ModelBox(toprail, 82, 95, -1.866F, -64.8F, 11.0F, 2, 0, 1, 0.0F, true));
        toprail.cubeList.add(new ModelBox(toprail, 82, 95, -1.0F, -65.3F, 4.0F, 2, 1, 1, 0.0F, false));
        toprail.cubeList.add(new ModelBox(toprail, 82, 95, -0.134F, -64.8F, 3.0F, 2, 0, 1, 0.0F, false));
        toprail.cubeList.add(new ModelBox(toprail, 82, 95, -1.866F, -64.8F, 3.0F, 2, 0, 1, 0.0F, true));
        toprail.cubeList.add(new ModelBox(toprail, 82, 95, -1.0F, -65.3F, -4.0F, 2, 1, 1, 0.0F, false));
        toprail.cubeList.add(new ModelBox(toprail, 82, 95, -0.134F, -64.8F, -5.0F, 2, 0, 1, 0.0F, false));
        toprail.cubeList.add(new ModelBox(toprail, 82, 95, -1.866F, -64.8F, -5.0F, 2, 0, 1, 0.0F, true));
        toprail.cubeList.add(new ModelBox(toprail, 82, 95, -1.0F, -65.3F, -10.0F, 2, 1, 1, 0.0F, false));
        toprail.cubeList.add(new ModelBox(toprail, 82, 95, -0.134F, -64.8F, -11.0F, 2, 0, 1, 0.0F, false));
        toprail.cubeList.add(new ModelBox(toprail, 82, 95, -1.866F, -64.8F, -11.0F, 2, 0, 1, 0.0F, true));
        toprail.cubeList.add(new ModelBox(toprail, 82, 95, -1.0F, -65.3F, 14.0F, 2, 1, 1, 0.0F, false));
        toprail.cubeList.add(new ModelBox(toprail, 82, 95, -0.134F, -64.8F, 13.0F, 2, 0, 1, 0.0F, false));
        toprail.cubeList.add(new ModelBox(toprail, 82, 95, -1.866F, -64.8F, 13.0F, 2, 0, 1, 0.0F, true));
        toprail.cubeList.add(new ModelBox(toprail, 82, 95, -1.0F, -65.3F, 6.0F, 2, 1, 1, 0.0F, false));
        toprail.cubeList.add(new ModelBox(toprail, 82, 95, -0.134F, -64.8F, 5.0F, 2, 0, 1, 0.0F, false));
        toprail.cubeList.add(new ModelBox(toprail, 82, 95, -1.866F, -64.8F, 5.0F, 2, 0, 1, 0.0F, true));
        toprail.cubeList.add(new ModelBox(toprail, 82, 95, -1.0F, -65.3F, -2.0F, 2, 1, 1, 0.0F, false));
        toprail.cubeList.add(new ModelBox(toprail, 82, 95, -0.134F, -64.8F, -3.0F, 2, 0, 1, 0.0F, false));
        toprail.cubeList.add(new ModelBox(toprail, 82, 95, -1.866F, -64.8F, -3.0F, 2, 0, 1, 0.0F, true));
        toprail.cubeList.add(new ModelBox(toprail, 82, 95, -1.0F, -65.3F, -8.0F, 2, 1, 1, 0.0F, false));
        toprail.cubeList.add(new ModelBox(toprail, 82, 95, -0.134F, -64.8F, -9.0F, 2, 0, 1, 0.0F, false));
        toprail.cubeList.add(new ModelBox(toprail, 82, 95, -1.866F, -64.8F, -9.0F, 2, 0, 1, 0.0F, true));
        toprail.cubeList.add(new ModelBox(toprail, 82, 95, -1.0F, -65.3F, 10.0F, 2, 1, 1, 0.0F, false));
        toprail.cubeList.add(new ModelBox(toprail, 82, 95, -0.134F, -64.8F, 9.0F, 2, 0, 1, 0.0F, false));
        toprail.cubeList.add(new ModelBox(toprail, 82, 95, -1.866F, -64.8F, 9.0F, 2, 0, 1, 0.0F, true));
        toprail.cubeList.add(new ModelBox(toprail, 82, 95, -1.0F, -65.3F, 2.0F, 2, 1, 1, 0.0F, false));
        toprail.cubeList.add(new ModelBox(toprail, 82, 95, -0.134F, -64.8F, 1.0F, 2, 0, 1, 0.0F, false));
        toprail.cubeList.add(new ModelBox(toprail, 82, 95, -1.866F, -64.8F, 1.0F, 2, 0, 1, 0.0F, true));
        toprail.cubeList.add(new ModelBox(toprail, 82, 95, -1.0F, -65.3F, -6.0F, 2, 1, 1, 0.0F, false));
        toprail.cubeList.add(new ModelBox(toprail, 82, 95, -0.134F, -64.8F, -7.0F, 2, 0, 1, 0.0F, false));
        toprail.cubeList.add(new ModelBox(toprail, 82, 95, -1.866F, -64.8F, -7.0F, 2, 0, 1, 0.0F, true));
        toprail.cubeList.add(new ModelBox(toprail, 82, 95, -1.0F, -65.3F, -12.0F, 2, 1, 1, 0.0F, false));
        toprail.cubeList.add(new ModelBox(toprail, 82, 95, -0.134F, -64.8F, -13.0F, 2, 0, 1, 0.0F, false));
        toprail.cubeList.add(new ModelBox(toprail, 82, 95, -1.866F, -64.8F, -13.0F, 2, 0, 1, 0.0F, true));
        toprail.cubeList.add(new ModelBox(toprail, 82, 95, -1.0F, -65.3F, -14.0F, 2, 1, 1, 0.0F, false));
        toprail.cubeList.add(new ModelBox(toprail, 82, 95, -1.5F, -64.3F, -14.0F, 3, 1, 16, 0.0F, false));

        bone = new ModelRenderer(this);
        bone.setRotationPoint(0.0F, -64.8F, 1.5F);
        toprail.addChild(bone);
        setRotationAngle(bone, 0.0F, 0.0F, 0.5236F);
        bone.cubeList.add(new ModelBox(bone, 82, 95, 0.616F, -0.933F, 12.5F, 1, 1, 1, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 82, 95, 0.616F, -0.933F, 14.5F, 1, 1, 1, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 82, 95, 0.616F, -0.933F, 10.5F, 1, 1, 1, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 82, 95, 0.616F, -0.933F, 6.5F, 1, 1, 1, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 82, 95, 0.616F, -0.933F, 8.5F, 1, 1, 1, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 82, 95, 0.616F, -0.933F, 4.5F, 1, 1, 1, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 82, 95, 0.616F, -0.933F, 2.5F, 1, 1, 1, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 82, 95, 0.616F, -0.933F, -1.5F, 1, 1, 1, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 82, 95, 0.616F, -0.933F, -7.5F, 1, 1, 1, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 82, 95, 0.616F, -0.933F, -9.5F, 1, 1, 1, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 82, 95, 0.616F, -0.933F, -11.5F, 1, 1, 1, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 82, 95, 0.616F, -0.933F, -13.5F, 1, 1, 1, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 82, 95, 0.616F, -0.933F, -15.5F, 1, 1, 1, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 82, 95, 0.616F, -0.933F, -5.5F, 1, 1, 1, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 82, 95, 0.616F, -0.933F, -3.5F, 1, 1, 1, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 82, 95, 0.616F, -0.933F, 0.5F, 1, 1, 1, 0.0F, false));

        bone5 = new ModelRenderer(this);
        bone5.setRotationPoint(0.0F, -64.8F, 1.5F);
        toprail.addChild(bone5);
        setRotationAngle(bone5, 0.0F, 0.0F, -0.5236F);
        bone5.cubeList.add(new ModelBox(bone5, 82, 95, -1.616F, -0.933F, 12.5F, 1, 1, 1, 0.0F, true));
        bone5.cubeList.add(new ModelBox(bone5, 82, 95, -1.616F, -0.933F, 14.5F, 1, 1, 1, 0.0F, true));
        bone5.cubeList.add(new ModelBox(bone5, 82, 95, -1.616F, -0.933F, 10.5F, 1, 1, 1, 0.0F, true));
        bone5.cubeList.add(new ModelBox(bone5, 82, 95, -1.616F, -0.933F, 6.5F, 1, 1, 1, 0.0F, true));
        bone5.cubeList.add(new ModelBox(bone5, 82, 95, -1.616F, -0.933F, 8.5F, 1, 1, 1, 0.0F, true));
        bone5.cubeList.add(new ModelBox(bone5, 82, 95, -1.616F, -0.933F, 4.5F, 1, 1, 1, 0.0F, true));
        bone5.cubeList.add(new ModelBox(bone5, 82, 95, -1.616F, -0.933F, 2.5F, 1, 1, 1, 0.0F, true));
        bone5.cubeList.add(new ModelBox(bone5, 82, 95, -1.616F, -0.933F, -1.5F, 1, 1, 1, 0.0F, true));
        bone5.cubeList.add(new ModelBox(bone5, 82, 95, -1.616F, -0.933F, -7.5F, 1, 1, 1, 0.0F, true));
        bone5.cubeList.add(new ModelBox(bone5, 82, 95, -1.616F, -0.933F, -9.5F, 1, 1, 1, 0.0F, true));
        bone5.cubeList.add(new ModelBox(bone5, 82, 95, -1.616F, -0.933F, -11.5F, 1, 1, 1, 0.0F, true));
        bone5.cubeList.add(new ModelBox(bone5, 82, 95, -1.616F, -0.933F, -13.5F, 1, 1, 1, 0.0F, true));
        bone5.cubeList.add(new ModelBox(bone5, 82, 95, -1.616F, -0.933F, -15.5F, 1, 1, 1, 0.0F, true));
        bone5.cubeList.add(new ModelBox(bone5, 82, 95, -1.616F, -0.933F, -5.5F, 1, 1, 1, 0.0F, true));
        bone5.cubeList.add(new ModelBox(bone5, 82, 95, -1.616F, -0.933F, -3.5F, 1, 1, 1, 0.0F, true));
        bone5.cubeList.add(new ModelBox(bone5, 82, 95, -1.616F, -0.933F, 0.5F, 1, 1, 1, 0.0F, true));

        bone4 = new ModelRenderer(this);
        bone4.setRotationPoint(0.0F, -64.8F, 0.5F);
        toprail.addChild(bone4);
        setRotationAngle(bone4, 0.0F, 0.0F, 0.5236F);
        bone4.cubeList.add(new ModelBox(bone4, 82, 95, 1.616F, -0.933F, 12.5F, 0, 1, 1, 0.0F, false));
        bone4.cubeList.add(new ModelBox(bone4, 82, 95, 1.616F, -0.933F, 14.5F, 0, 1, 1, 0.0F, false));
        bone4.cubeList.add(new ModelBox(bone4, 82, 95, 1.616F, -0.933F, 10.5F, 0, 1, 1, 0.0F, false));
        bone4.cubeList.add(new ModelBox(bone4, 82, 95, 1.616F, -0.933F, 6.5F, 0, 1, 1, 0.0F, false));
        bone4.cubeList.add(new ModelBox(bone4, 82, 95, 1.616F, -0.933F, 8.5F, 0, 1, 1, 0.0F, false));
        bone4.cubeList.add(new ModelBox(bone4, 82, 95, 1.616F, -0.933F, 4.5F, 0, 1, 1, 0.0F, false));
        bone4.cubeList.add(new ModelBox(bone4, 82, 95, 1.616F, -0.933F, 2.5F, 0, 1, 1, 0.0F, false));
        bone4.cubeList.add(new ModelBox(bone4, 82, 95, 1.616F, -0.933F, -1.5F, 0, 1, 1, 0.0F, false));
        bone4.cubeList.add(new ModelBox(bone4, 82, 95, 1.616F, -0.933F, -7.5F, 0, 1, 1, 0.0F, false));
        bone4.cubeList.add(new ModelBox(bone4, 82, 95, 1.616F, -0.933F, -9.5F, 0, 1, 1, 0.0F, false));
        bone4.cubeList.add(new ModelBox(bone4, 82, 95, 1.616F, -0.933F, -11.5F, 0, 1, 1, 0.0F, false));
        bone4.cubeList.add(new ModelBox(bone4, 82, 95, 1.616F, -0.933F, -13.5F, 0, 1, 1, 0.0F, false));
        bone4.cubeList.add(new ModelBox(bone4, 82, 95, 1.616F, -0.933F, -5.5F, 0, 1, 1, 0.0F, false));
        bone4.cubeList.add(new ModelBox(bone4, 82, 95, 1.616F, -0.933F, -3.5F, 0, 1, 1, 0.0F, false));
        bone4.cubeList.add(new ModelBox(bone4, 82, 95, 1.616F, -0.933F, 0.5F, 0, 1, 1, 0.0F, false));

        bone6 = new ModelRenderer(this);
        bone6.setRotationPoint(0.0F, -64.8F, 0.5F);
        toprail.addChild(bone6);
        setRotationAngle(bone6, 0.0F, 0.0F, -0.5236F);
        bone6.cubeList.add(new ModelBox(bone6, 82, 95, -1.616F, -0.933F, 12.5F, 0, 1, 1, 0.0F, true));
        bone6.cubeList.add(new ModelBox(bone6, 82, 95, -1.616F, -0.933F, 14.5F, 0, 1, 1, 0.0F, true));
        bone6.cubeList.add(new ModelBox(bone6, 82, 95, -1.616F, -0.933F, 10.5F, 0, 1, 1, 0.0F, true));
        bone6.cubeList.add(new ModelBox(bone6, 82, 95, -1.616F, -0.933F, 6.5F, 0, 1, 1, 0.0F, true));
        bone6.cubeList.add(new ModelBox(bone6, 82, 95, -1.616F, -0.933F, 8.5F, 0, 1, 1, 0.0F, true));
        bone6.cubeList.add(new ModelBox(bone6, 82, 95, -1.616F, -0.933F, 4.5F, 0, 1, 1, 0.0F, true));
        bone6.cubeList.add(new ModelBox(bone6, 82, 95, -1.616F, -0.933F, 2.5F, 0, 1, 1, 0.0F, true));
        bone6.cubeList.add(new ModelBox(bone6, 82, 95, -1.616F, -0.933F, -1.5F, 0, 1, 1, 0.0F, true));
        bone6.cubeList.add(new ModelBox(bone6, 82, 95, -1.616F, -0.933F, -7.5F, 0, 1, 1, 0.0F, true));
        bone6.cubeList.add(new ModelBox(bone6, 82, 95, -1.616F, -0.933F, -9.5F, 0, 1, 1, 0.0F, true));
        bone6.cubeList.add(new ModelBox(bone6, 82, 95, -1.616F, -0.933F, -11.5F, 0, 1, 1, 0.0F, true));
        bone6.cubeList.add(new ModelBox(bone6, 82, 95, -1.616F, -0.933F, -13.5F, 0, 1, 1, 0.0F, true));
        bone6.cubeList.add(new ModelBox(bone6, 82, 95, -1.616F, -0.933F, -5.5F, 0, 1, 1, 0.0F, true));
        bone6.cubeList.add(new ModelBox(bone6, 82, 95, -1.616F, -0.933F, -3.5F, 0, 1, 1, 0.0F, true));
        bone6.cubeList.add(new ModelBox(bone6, 82, 95, -1.616F, -0.933F, 0.5F, 0, 1, 1, 0.0F, true));

        magazine = new ModelRenderer(this);
        magazine.setRotationPoint(0.0F, -28.5F, -0.5F);
        setRotationAngle(magazine, -0.0873F, 0.0F, 0.0F);
        magazine.cubeList.add(new ModelBox(magazine, 11, 82, -3.001F, 2.5676F, -15.1461F, 6, 1, 15, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 11, 82, 1.999F, -2.4324F, -15.1461F, 1, 5, 15, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 11, 82, -3.001F, -2.4324F, -15.1461F, 1, 5, 15, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 11, 82, -2.001F, -1.4324F, -1.1461F, 4, 4, 1, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 11, 82, -2.001F, -0.4324F, -15.1461F, 4, 3, 1, 0.0F, false));

        cube_r13 = new ModelRenderer(this);
        cube_r13.setRotationPoint(-0.5F, 2.4325F, -7.5765F);
        magazine.addChild(cube_r13);
        setRotationAngle(cube_r13, -0.2182F, 0.0F, 0.0F);
        cube_r13.cubeList.add(new ModelBox(cube_r13, 11, 82, -2.0F, 2.5F, -3.5F, 5, 20, 3, 0.0F, false));
        cube_r13.cubeList.add(new ModelBox(cube_r13, 11, 82, -2.501F, 0.5F, -3.5F, 6, 2, 3, 0.0F, false));
        cube_r13.cubeList.add(new ModelBox(cube_r13, 11, 82, -2.501F, 0.5F, -7.5F, 6, 21, 4, 0.0F, false));
        cube_r13.cubeList.add(new ModelBox(cube_r13, 11, 82, -2.501F, 0.5F, -0.5F, 6, 22, 8, 0.0F, false));
        cube_r13.cubeList.add(new ModelBox(cube_r13, 11, 82, -2.501F, -0.5F, 2.5F, 6, 1, 5, 0.0F, false));

        cube_r14 = new ModelRenderer(this);
        cube_r14.setRotationPoint(0.5F, 35.3105F, -12.2468F);
        magazine.addChild(cube_r14);
        setRotationAngle(cube_r14, -0.3054F, 0.0F, 0.0F);
        cube_r14.cubeList.add(new ModelBox(cube_r14, 76, 24, -4.0F, -11.0F, -11.0F, 7, 1, 15, 0.0F, false));

        addEntry(AnimationElement.MAGAZINE, stack -> magazine);
        addEntry(AnimationElement.CHARGING, stack -> bolt);
        addEntry(AnimationElement.BULLET, stack -> bullet3);
    }
}

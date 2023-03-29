package dev.toma.pubgmc.client.models.weapons;

import dev.toma.pubgmc.client.animation.AnimationElement;
import dev.toma.pubgmc.client.util.ModelTransformationHelper;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.item.ItemStack;

public class ModelDP28 extends ModelGun {

    private final ModelRenderer dp28;
    private final ModelRenderer bone27;
    private final ModelRenderer bone28;
    private final ModelRenderer bone25;
    private final ModelRenderer bone26;
    private final ModelRenderer bone24;
    private final ModelRenderer bone30;
    private final ModelRenderer bone32;
    private final ModelRenderer bone34;
    private final ModelRenderer bone35;
    private final ModelRenderer bone41;
    private final ModelRenderer bone36;
    private final ModelRenderer bone37;
    private final ModelRenderer bone38;
    private final ModelRenderer bone39;
    private final ModelRenderer bone40;
    private final ModelRenderer bone33;
    private final ModelRenderer ironsights;
    private final ModelRenderer bone31;
    private final ModelRenderer bone29;
    private final ModelRenderer bullet;
    private final ModelRenderer magazine;
    private final ModelRenderer bone;
    private final ModelRenderer bone2;
    private final ModelRenderer bone3;
    private final ModelRenderer bone4;
    private final ModelRenderer bone5;
    private final ModelRenderer bone6;
    private final ModelRenderer bone7;
    private final ModelRenderer bone8;
    private final ModelRenderer bone9;
    private final ModelRenderer bone10;
    private final ModelRenderer bone11;
    private final ModelRenderer bone12;
    private final ModelRenderer bone13;
    private final ModelRenderer bone14;
    private final ModelRenderer bone15;
    private final ModelRenderer bone16;
    private final ModelRenderer bone17;
    private final ModelRenderer bone18;
    private final ModelRenderer bone19;
    private final ModelRenderer bone20;
    private final ModelRenderer bone21;
    private final ModelRenderer bone22;
    private final ModelRenderer bone23;
    private final ModelRenderer charging_handle;

    public ModelDP28() {
        textureWidth = 512;
        textureHeight = 512;

        dp28 = new ModelRenderer(this);
        dp28.setRotationPoint(0.0F, 24.0F, 0.0F);
        dp28.cubeList.add(new ModelBox(dp28, 2, 81, -2.5F, -2.0F, -24.0F, 5, 2, 7, 0.0F, false));
        dp28.cubeList.add(new ModelBox(dp28, 2, 81, -2.5F, -5.0F, -36.0F, 5, 5, 12, 0.0F, false));
        dp28.cubeList.add(new ModelBox(dp28, 2, 81, -3.5F, -4.0F, -37.0F, 7, 3, 1, 0.0F, false));
        dp28.cubeList.add(new ModelBox(dp28, 2, 81, -3.5F, -4.0F, -40.0F, 7, 3, 1, 0.0F, false));
        dp28.cubeList.add(new ModelBox(dp28, 2, 81, -3.5F, -4.0F, -43.0F, 7, 3, 1, 0.0F, false));
        dp28.cubeList.add(new ModelBox(dp28, 2, 81, -3.5F, -4.0F, -46.0F, 7, 3, 1, 0.0F, false));
        dp28.cubeList.add(new ModelBox(dp28, 2, 81, -3.5F, -4.0F, -49.0F, 7, 3, 1, 0.0F, false));
        dp28.cubeList.add(new ModelBox(dp28, 2, 81, -3.5F, -4.0F, -52.0F, 7, 3, 1, 0.0F, false));
        dp28.cubeList.add(new ModelBox(dp28, 2, 81, -3.5F, -4.0F, -55.0F, 7, 3, 1, 0.0F, false));
        dp28.cubeList.add(new ModelBox(dp28, 2, 81, -3.5F, -4.0F, -58.0F, 7, 3, 1, 0.0F, false));
        dp28.cubeList.add(new ModelBox(dp28, 2, 81, -3.5F, -4.0F, -61.0F, 7, 3, 1, 0.0F, false));
        dp28.cubeList.add(new ModelBox(dp28, 2, 81, -3.5F, -4.0F, -64.0F, 7, 3, 1, 0.0F, false));
        dp28.cubeList.add(new ModelBox(dp28, 2, 81, -3.5F, -4.0F, -74.0F, 7, 3, 8, 0.0F, false));
        dp28.cubeList.add(new ModelBox(dp28, 8, 131, 0.866F, -3.0F, -92.0F, 1, 1, 18, 0.0F, false));
        dp28.cubeList.add(new ModelBox(dp28, 8, 131, -1.866F, -3.0F, -92.0F, 1, 1, 18, 0.0F, false));
        dp28.cubeList.add(new ModelBox(dp28, 8, 131, -0.5F, -4.366F, -92.0F, 1, 1, 18, 0.0F, false));
        dp28.cubeList.add(new ModelBox(dp28, 8, 131, -0.5F, -1.634F, -92.0F, 1, 1, 18, 0.0F, false));
        dp28.cubeList.add(new ModelBox(dp28, 2, 81, -3.5F, -3.5F, -39.0F, 7, 2, 2, 0.0F, false));
        dp28.cubeList.add(new ModelBox(dp28, 2, 81, -3.5F, -3.5F, -42.0F, 7, 2, 2, 0.0F, false));
        dp28.cubeList.add(new ModelBox(dp28, 2, 81, -3.5F, -3.5F, -45.0F, 7, 2, 2, 0.0F, false));
        dp28.cubeList.add(new ModelBox(dp28, 2, 81, -3.5F, -3.5F, -48.0F, 7, 2, 2, 0.0F, false));
        dp28.cubeList.add(new ModelBox(dp28, 2, 81, -3.5F, -3.5F, -51.0F, 7, 2, 2, 0.0F, false));
        dp28.cubeList.add(new ModelBox(dp28, 2, 81, -3.5F, -3.5F, -54.0F, 7, 2, 2, 0.0F, false));
        dp28.cubeList.add(new ModelBox(dp28, 2, 81, -3.5F, -3.5F, -57.0F, 7, 2, 2, 0.0F, false));
        dp28.cubeList.add(new ModelBox(dp28, 2, 81, -3.5F, -3.5F, -60.0F, 7, 2, 2, 0.0F, false));
        dp28.cubeList.add(new ModelBox(dp28, 2, 81, -3.5F, -3.5F, -63.0F, 7, 2, 2, 0.0F, false));
        dp28.cubeList.add(new ModelBox(dp28, 2, 81, -3.5F, -3.5F, -66.0F, 7, 2, 2, 0.0F, false));
        dp28.cubeList.add(new ModelBox(dp28, 2, 81, -1.5F, -6.0F, -37.0F, 3, 7, 1, 0.0F, false));
        dp28.cubeList.add(new ModelBox(dp28, 2, 81, -1.5F, -6.0F, -40.0F, 3, 7, 1, 0.0F, false));
        dp28.cubeList.add(new ModelBox(dp28, 2, 81, -1.5F, -6.0F, -43.0F, 3, 7, 1, 0.0F, false));
        dp28.cubeList.add(new ModelBox(dp28, 2, 81, -1.5F, -6.0F, -46.0F, 3, 7, 1, 0.0F, false));
        dp28.cubeList.add(new ModelBox(dp28, 2, 81, -1.5F, -6.0F, -49.0F, 3, 7, 1, 0.0F, false));
        dp28.cubeList.add(new ModelBox(dp28, 2, 81, -1.5F, -6.0F, -52.0F, 3, 7, 1, 0.0F, false));
        dp28.cubeList.add(new ModelBox(dp28, 2, 81, -1.5F, -6.0F, -55.0F, 3, 7, 1, 0.0F, false));
        dp28.cubeList.add(new ModelBox(dp28, 2, 81, -1.5F, -6.0F, -58.0F, 3, 7, 1, 0.0F, false));
        dp28.cubeList.add(new ModelBox(dp28, 2, 81, -1.5F, -6.0F, -61.0F, 3, 7, 1, 0.0F, false));
        dp28.cubeList.add(new ModelBox(dp28, 2, 81, -1.5F, -6.0F, -64.0F, 3, 7, 1, 0.0F, false));
        dp28.cubeList.add(new ModelBox(dp28, 2, 81, -1.5F, -6.0F, -74.0F, 3, 7, 8, 0.0F, false));
        dp28.cubeList.add(new ModelBox(dp28, 2, 81, -1.5F, 1.0F, -74.0F, 3, 2, 22, 0.0F, false));
        dp28.cubeList.add(new ModelBox(dp28, 2, 81, -0.5F, 1.0F, -77.0F, 1, 1, 1, 0.0F, false));
        dp28.cubeList.add(new ModelBox(dp28, 2, 81, -1.0F, 0.5F, -76.5586F, 2, 2, 3, 0.0F, false));
        dp28.cubeList.add(new ModelBox(dp28, 2, 81, -1.5F, 1.0F, -52.0F, 3, 2, 16, 0.0F, false));
        dp28.cubeList.add(new ModelBox(dp28, 2, 81, -0.5F, 2.1289F, -48.5117F, 1, 1, 24, 0.0F, false));
        dp28.cubeList.add(new ModelBox(dp28, 2, 81, -0.5F, 2.1289F, -72.5117F, 1, 1, 24, 0.0F, false));
        dp28.cubeList.add(new ModelBox(dp28, 2, 81, -1.5F, 0.0F, -36.0F, 3, 3, 10, 0.0F, false));
        dp28.cubeList.add(new ModelBox(dp28, 2, 81, -1.0F, -6.0F, -39.0F, 2, 3, 2, 0.0F, false));
        dp28.cubeList.add(new ModelBox(dp28, 2, 81, -1.0F, -6.0F, -42.0F, 2, 3, 2, 0.0F, false));
        dp28.cubeList.add(new ModelBox(dp28, 2, 81, -1.0F, -6.0F, -45.0F, 2, 3, 2, 0.0F, false));
        dp28.cubeList.add(new ModelBox(dp28, 2, 81, -1.0F, -6.0F, -48.0F, 2, 3, 2, 0.0F, false));
        dp28.cubeList.add(new ModelBox(dp28, 2, 81, -1.0F, -6.0F, -51.0F, 2, 3, 2, 0.0F, false));
        dp28.cubeList.add(new ModelBox(dp28, 2, 81, -1.0F, -6.0F, -54.0F, 2, 3, 2, 0.0F, false));
        dp28.cubeList.add(new ModelBox(dp28, 2, 81, -1.0F, -6.0F, -57.0F, 2, 3, 2, 0.0F, false));
        dp28.cubeList.add(new ModelBox(dp28, 2, 81, -1.0F, -6.0F, -60.0F, 2, 3, 2, 0.0F, false));
        dp28.cubeList.add(new ModelBox(dp28, 2, 81, -1.0F, -6.0F, -63.0F, 2, 3, 2, 0.0F, false));
        dp28.cubeList.add(new ModelBox(dp28, 2, 81, -1.0F, -6.0F, -66.0F, 2, 3, 2, 0.0F, false));
        dp28.cubeList.add(new ModelBox(dp28, 2, 81, -1.5F, -2.0F, -39.0F, 3, 3, 2, 0.0F, false));
        dp28.cubeList.add(new ModelBox(dp28, 2, 81, -1.5F, -2.0F, -42.0F, 3, 3, 2, 0.0F, false));
        dp28.cubeList.add(new ModelBox(dp28, 2, 81, -1.5F, -2.0F, -45.0F, 3, 3, 2, 0.0F, false));
        dp28.cubeList.add(new ModelBox(dp28, 2, 81, -1.5F, -2.0F, -48.0F, 3, 3, 2, 0.0F, false));
        dp28.cubeList.add(new ModelBox(dp28, 2, 81, -1.5F, -2.0F, -51.0F, 3, 3, 2, 0.0F, false));
        dp28.cubeList.add(new ModelBox(dp28, 2, 81, -1.5F, -2.0F, -54.0F, 3, 3, 2, 0.0F, false));
        dp28.cubeList.add(new ModelBox(dp28, 2, 81, -1.5F, -2.0F, -57.0F, 3, 3, 2, 0.0F, false));
        dp28.cubeList.add(new ModelBox(dp28, 2, 81, -1.5F, -2.0F, -60.0F, 3, 3, 2, 0.0F, false));
        dp28.cubeList.add(new ModelBox(dp28, 2, 81, -1.5F, -2.0F, -63.0F, 3, 3, 2, 0.0F, false));
        dp28.cubeList.add(new ModelBox(dp28, 2, 81, -1.5F, -2.0F, -66.0F, 3, 3, 2, 0.0F, false));
        dp28.cubeList.add(new ModelBox(dp28, 2, 81, -2.5F, -5.0F, -24.0F, 5, 3, 7, 0.0F, false));
        dp28.cubeList.add(new ModelBox(dp28, 2, 81, -2.5F, -5.0F, -5.0F, 5, 3, 9, 0.0F, false));
        dp28.cubeList.add(new ModelBox(dp28, 2, 81, -2.5F, -5.0F, 4.0F, 5, 5, 12, 0.0F, false));
        dp28.cubeList.add(new ModelBox(dp28, 2, 81, -2.0F, -6.0F, 2.4492F, 4, 1, 13, 0.0F, false));
        dp28.cubeList.add(new ModelBox(dp28, 2, 81, -2.0F, -6.2656F, 3.4492F, 4, 1, 1, 0.0F, false));
        dp28.cubeList.add(new ModelBox(dp28, 2, 81, -2.0F, -6.2656F, 5.4492F, 4, 1, 1, 0.0F, false));
        dp28.cubeList.add(new ModelBox(dp28, 2, 81, -2.0F, -6.2656F, 7.4492F, 4, 1, 1, 0.0F, false));
        dp28.cubeList.add(new ModelBox(dp28, 2, 81, -2.0F, -6.2656F, 9.4492F, 4, 1, 1, 0.0F, false));
        dp28.cubeList.add(new ModelBox(dp28, 2, 81, -2.0F, -6.2656F, 11.4492F, 4, 1, 1, 0.0F, false));
        dp28.cubeList.add(new ModelBox(dp28, 2, 81, -2.0F, -6.2656F, 13.4492F, 4, 1, 1, 0.0F, false));
        dp28.cubeList.add(new ModelBox(dp28, 2, 81, 1.2305F, -6.4414F, 2.4492F, 1, 1, 13, 0.0F, false));
        dp28.cubeList.add(new ModelBox(dp28, 2, 81, -2.2305F, -6.4414F, 2.4492F, 1, 1, 13, 0.0F, true));
        dp28.cubeList.add(new ModelBox(dp28, 2, 81, -2.5F, -2.0F, -5.0F, 5, 2, 9, 0.0F, false));
        dp28.cubeList.add(new ModelBox(dp28, 2, 81, -2.5F, -5.0F, -17.0F, 1, 5, 12, 0.0F, false));
        dp28.cubeList.add(new ModelBox(dp28, 2, 81, 1.5F, -5.0F, -17.0F, 1, 5, 12, 0.0F, false));

        bone27 = new ModelRenderer(this);
        bone27.setRotationPoint(-1.5F, 0.0F, 0.0F);
        dp28.addChild(bone27);
        setRotationAngle(bone27, 0.0F, 0.0F, -0.5236F);
        bone27.cubeList.add(new ModelBox(bone27, 8, 131, 3.4151F, -1.9151F, -92.0F, 1, 1, 18, 0.0F, false));
        bone27.cubeList.add(new ModelBox(bone27, 8, 131, 0.683F, -1.9151F, -92.0F, 1, 1, 18, 0.0F, false));
        bone27.cubeList.add(new ModelBox(bone27, 8, 131, 2.049F, -0.549F, -92.0F, 1, 1, 18, 0.0F, false));
        bone27.cubeList.add(new ModelBox(bone27, 8, 131, 2.049F, -3.2811F, -92.0F, 1, 1, 18, 0.0F, false));

        bone28 = new ModelRenderer(this);
        bone28.setRotationPoint(1.5F, 0.0F, 0.0F);
        dp28.addChild(bone28);
        setRotationAngle(bone28, 0.0F, 0.0F, 0.5236F);
        bone28.cubeList.add(new ModelBox(bone28, 8, 131, -3.049F, -3.2811F, -92.0F, 1, 1, 18, 0.0F, true));
        bone28.cubeList.add(new ModelBox(bone28, 8, 131, -3.049F, -0.549F, -92.0F, 1, 1, 18, 0.0F, true));
        bone28.cubeList.add(new ModelBox(bone28, 8, 131, -4.4151F, -1.9151F, -92.0F, 1, 1, 18, 0.0F, true));
        bone28.cubeList.add(new ModelBox(bone28, 8, 131, -1.683F, -1.9151F, -92.0F, 1, 1, 18, 0.0F, true));

        bone25 = new ModelRenderer(this);
        bone25.setRotationPoint(2.0F, -4.5F, -37.5F);
        dp28.addChild(bone25);
        setRotationAngle(bone25, 0.0F, 0.0F, 0.7854F);
        bone25.cubeList.add(new ModelBox(bone25, 71, 25, -0.5F, -0.6523F, -1.5F, 1, 1, 2, 0.0F, false));
        bone25.cubeList.add(new ModelBox(bone25, 71, 25, -0.5F, -0.6523F, -4.5F, 1, 1, 2, 0.0F, false));
        bone25.cubeList.add(new ModelBox(bone25, 71, 25, -0.5F, -0.6523F, -7.5F, 1, 1, 2, 0.0F, false));
        bone25.cubeList.add(new ModelBox(bone25, 71, 25, -0.5F, -0.6523F, -10.5F, 1, 1, 2, 0.0F, false));
        bone25.cubeList.add(new ModelBox(bone25, 71, 25, -0.5F, -0.6523F, -13.5F, 1, 1, 2, 0.0F, false));
        bone25.cubeList.add(new ModelBox(bone25, 71, 25, -0.5F, -0.6523F, -16.5F, 1, 1, 2, 0.0F, false));
        bone25.cubeList.add(new ModelBox(bone25, 71, 25, -0.5F, -0.6523F, -19.5F, 1, 1, 2, 0.0F, false));
        bone25.cubeList.add(new ModelBox(bone25, 71, 25, -0.5F, -0.6523F, -22.5F, 1, 1, 2, 0.0F, false));
        bone25.cubeList.add(new ModelBox(bone25, 71, 25, -0.5F, -0.6523F, -25.5F, 1, 1, 2, 0.0F, false));
        bone25.cubeList.add(new ModelBox(bone25, 71, 25, -0.5F, -0.6523F, -28.5F, 1, 1, 2, 0.0F, false));

        bone26 = new ModelRenderer(this);
        bone26.setRotationPoint(-2.0F, -4.5F, -37.5F);
        dp28.addChild(bone26);
        setRotationAngle(bone26, 0.0F, 0.0F, -0.7854F);
        bone26.cubeList.add(new ModelBox(bone26, 71, 25, -0.5F, -0.6523F, -1.5F, 1, 1, 2, 0.0F, true));
        bone26.cubeList.add(new ModelBox(bone26, 71, 25, -0.5F, -0.6523F, -4.5F, 1, 1, 2, 0.0F, true));
        bone26.cubeList.add(new ModelBox(bone26, 71, 25, -0.5F, -0.6523F, -7.5F, 1, 1, 2, 0.0F, true));
        bone26.cubeList.add(new ModelBox(bone26, 71, 25, -0.5F, -0.6523F, -10.5F, 1, 1, 2, 0.0F, true));
        bone26.cubeList.add(new ModelBox(bone26, 71, 25, -0.5F, -0.6523F, -13.5F, 1, 1, 2, 0.0F, true));
        bone26.cubeList.add(new ModelBox(bone26, 71, 25, -0.5F, -0.6523F, -16.5F, 1, 1, 2, 0.0F, true));
        bone26.cubeList.add(new ModelBox(bone26, 71, 25, -0.5F, -0.6523F, -19.5F, 1, 1, 2, 0.0F, true));
        bone26.cubeList.add(new ModelBox(bone26, 71, 25, -0.5F, -0.6523F, -22.5F, 1, 1, 2, 0.0F, true));
        bone26.cubeList.add(new ModelBox(bone26, 71, 25, -0.5F, -0.6523F, -25.5F, 1, 1, 2, 0.0F, true));
        bone26.cubeList.add(new ModelBox(bone26, 71, 25, -0.5F, -0.6523F, -28.5F, 1, 1, 2, 0.0F, true));

        bone24 = new ModelRenderer(this);
        bone24.setRotationPoint(0.0F, -7.5F, -36.5F);
        dp28.addChild(bone24);
        setRotationAngle(bone24, 0.0F, 0.0F, 0.7854F);
        bone24.cubeList.add(new ModelBox(bone24, 71, 25, 2.9497F, 0.0F, -0.5F, 2, 2, 1, 0.0F, false));
        bone24.cubeList.add(new ModelBox(bone24, 71, 25, 2.9497F, 0.0F, -3.5F, 2, 2, 1, 0.0F, false));
        bone24.cubeList.add(new ModelBox(bone24, 71, 25, 2.9497F, 0.0F, -6.5F, 2, 2, 1, 0.0F, false));
        bone24.cubeList.add(new ModelBox(bone24, 71, 25, 2.9497F, 0.0F, -9.5F, 2, 2, 1, 0.0F, false));
        bone24.cubeList.add(new ModelBox(bone24, 71, 25, 2.9497F, 0.0F, -12.5F, 2, 2, 1, 0.0F, false));
        bone24.cubeList.add(new ModelBox(bone24, 71, 25, 2.9497F, 0.0F, -15.5F, 2, 2, 1, 0.0F, false));
        bone24.cubeList.add(new ModelBox(bone24, 71, 25, 2.9497F, 0.0F, -18.5F, 2, 2, 1, 0.0F, false));
        bone24.cubeList.add(new ModelBox(bone24, 71, 25, 2.9497F, 0.0F, -21.5F, 2, 2, 1, 0.0F, false));
        bone24.cubeList.add(new ModelBox(bone24, 71, 25, 2.9497F, 0.0F, -24.5F, 2, 2, 1, 0.0F, false));
        bone24.cubeList.add(new ModelBox(bone24, 71, 25, 2.9497F, 0.0F, -27.5F, 2, 2, 1, 0.0F, false));
        bone24.cubeList.add(new ModelBox(bone24, 71, 25, 2.9497F, 0.0F, -37.5F, 2, 2, 8, 0.0F, false));
        bone24.cubeList.add(new ModelBox(bone24, 71, 25, 5.0711F, 2.1213F, -0.5F, 2, 2, 1, 0.0F, false));
        bone24.cubeList.add(new ModelBox(bone24, 71, 25, 5.0711F, 2.1213F, -3.5F, 2, 2, 1, 0.0F, false));
        bone24.cubeList.add(new ModelBox(bone24, 71, 25, 5.0711F, 2.1213F, -6.5F, 2, 2, 1, 0.0F, false));
        bone24.cubeList.add(new ModelBox(bone24, 71, 25, 5.0711F, 2.1213F, -9.5F, 2, 2, 1, 0.0F, false));
        bone24.cubeList.add(new ModelBox(bone24, 71, 25, 5.0711F, 2.1213F, -12.5F, 2, 2, 1, 0.0F, false));
        bone24.cubeList.add(new ModelBox(bone24, 71, 25, 5.0711F, 2.1213F, -15.5F, 2, 2, 1, 0.0F, false));
        bone24.cubeList.add(new ModelBox(bone24, 71, 25, 5.0711F, 2.1213F, -18.5F, 2, 2, 1, 0.0F, false));
        bone24.cubeList.add(new ModelBox(bone24, 71, 25, 5.0711F, 2.1213F, -21.5F, 2, 2, 1, 0.0F, false));
        bone24.cubeList.add(new ModelBox(bone24, 71, 25, 5.0711F, 2.1213F, -24.5F, 2, 2, 1, 0.0F, false));
        bone24.cubeList.add(new ModelBox(bone24, 71, 25, 5.0711F, 2.1213F, -27.5F, 2, 2, 1, 0.0F, false));
        bone24.cubeList.add(new ModelBox(bone24, 71, 25, 5.0711F, 2.1213F, -37.5F, 2, 2, 8, 0.0F, false));
        bone24.cubeList.add(new ModelBox(bone24, 71, 25, 0.0F, 2.9497F, -0.5F, 2, 2, 1, 0.0F, false));
        bone24.cubeList.add(new ModelBox(bone24, 71, 25, 0.0F, 2.9497F, -3.5F, 2, 2, 1, 0.0F, false));
        bone24.cubeList.add(new ModelBox(bone24, 71, 25, 0.0F, 2.9497F, -6.5F, 2, 2, 1, 0.0F, false));
        bone24.cubeList.add(new ModelBox(bone24, 71, 25, 0.0F, 2.9497F, -9.5F, 2, 2, 1, 0.0F, false));
        bone24.cubeList.add(new ModelBox(bone24, 71, 25, 0.0F, 2.9497F, -12.5F, 2, 2, 1, 0.0F, false));
        bone24.cubeList.add(new ModelBox(bone24, 71, 25, 0.0F, 2.9497F, -15.5F, 2, 2, 1, 0.0F, false));
        bone24.cubeList.add(new ModelBox(bone24, 71, 25, 0.0F, 2.9497F, -18.5F, 2, 2, 1, 0.0F, false));
        bone24.cubeList.add(new ModelBox(bone24, 71, 25, 0.0F, 2.9497F, -21.5F, 2, 2, 1, 0.0F, false));
        bone24.cubeList.add(new ModelBox(bone24, 71, 25, 0.0F, 2.9497F, -24.5F, 2, 2, 1, 0.0F, false));
        bone24.cubeList.add(new ModelBox(bone24, 71, 25, 0.0F, 2.9497F, -27.5F, 2, 2, 1, 0.0F, false));
        bone24.cubeList.add(new ModelBox(bone24, 71, 25, 0.0F, 2.9497F, -37.5F, 2, 2, 8, 0.0F, false));
        bone24.cubeList.add(new ModelBox(bone24, 71, 25, 2.1213F, 5.0711F, -0.5F, 2, 2, 1, 0.0F, false));
        bone24.cubeList.add(new ModelBox(bone24, 71, 25, 2.1213F, 5.0711F, -3.5F, 2, 2, 1, 0.0F, false));
        bone24.cubeList.add(new ModelBox(bone24, 71, 25, 2.1213F, 5.0711F, -6.5F, 2, 2, 1, 0.0F, false));
        bone24.cubeList.add(new ModelBox(bone24, 71, 25, 2.1213F, 5.0711F, -9.5F, 2, 2, 1, 0.0F, false));
        bone24.cubeList.add(new ModelBox(bone24, 71, 25, 2.1213F, 5.0711F, -12.5F, 2, 2, 1, 0.0F, false));
        bone24.cubeList.add(new ModelBox(bone24, 71, 25, 2.1213F, 5.0711F, -15.5F, 2, 2, 1, 0.0F, false));
        bone24.cubeList.add(new ModelBox(bone24, 71, 25, 2.1213F, 5.0711F, -18.5F, 2, 2, 1, 0.0F, false));
        bone24.cubeList.add(new ModelBox(bone24, 71, 25, 2.1213F, 5.0711F, -21.5F, 2, 2, 1, 0.0F, false));
        bone24.cubeList.add(new ModelBox(bone24, 71, 25, 2.1213F, 5.0711F, -24.5F, 2, 2, 1, 0.0F, false));
        bone24.cubeList.add(new ModelBox(bone24, 71, 25, 2.1213F, 5.0711F, -27.5F, 2, 2, 1, 0.0F, false));
        bone24.cubeList.add(new ModelBox(bone24, 71, 25, 2.1213F, 5.0711F, -37.5F, 2, 2, 8, 0.0F, false));
        bone24.cubeList.add(new ModelBox(bone24, 71, 25, 2.1213F, 0.0F, -0.5F, 1, 1, 1, 0.0F, false));
        bone24.cubeList.add(new ModelBox(bone24, 71, 25, 2.1213F, 0.0F, -3.5F, 1, 1, 1, 0.0F, false));
        bone24.cubeList.add(new ModelBox(bone24, 71, 25, 2.1213F, 0.0F, -6.5F, 1, 1, 1, 0.0F, false));
        bone24.cubeList.add(new ModelBox(bone24, 71, 25, 2.1213F, 0.0F, -9.5F, 1, 1, 1, 0.0F, false));
        bone24.cubeList.add(new ModelBox(bone24, 71, 25, 2.1213F, 0.0F, -12.5F, 1, 1, 1, 0.0F, false));
        bone24.cubeList.add(new ModelBox(bone24, 71, 25, 2.1213F, 0.0F, -15.5F, 1, 1, 1, 0.0F, false));
        bone24.cubeList.add(new ModelBox(bone24, 71, 25, 2.1213F, 0.0F, -18.5F, 1, 1, 1, 0.0F, false));
        bone24.cubeList.add(new ModelBox(bone24, 71, 25, 2.1213F, 0.0F, -21.5F, 1, 1, 1, 0.0F, false));
        bone24.cubeList.add(new ModelBox(bone24, 71, 25, 2.1213F, 0.0F, -24.5F, 1, 1, 1, 0.0F, false));
        bone24.cubeList.add(new ModelBox(bone24, 71, 25, 2.1213F, 0.0F, -27.5F, 1, 1, 1, 0.0F, false));
        bone24.cubeList.add(new ModelBox(bone24, 71, 25, 2.1213F, 0.0F, -37.5F, 1, 1, 8, 0.0F, false));
        bone24.cubeList.add(new ModelBox(bone24, 71, 25, 0.0F, 2.1213F, -0.5F, 1, 1, 1, 0.0F, false));
        bone24.cubeList.add(new ModelBox(bone24, 71, 25, 0.0F, 2.1213F, -3.5F, 1, 1, 1, 0.0F, false));
        bone24.cubeList.add(new ModelBox(bone24, 71, 25, 0.0F, 2.1213F, -6.5F, 1, 1, 1, 0.0F, false));
        bone24.cubeList.add(new ModelBox(bone24, 71, 25, 0.0F, 2.1213F, -9.5F, 1, 1, 1, 0.0F, false));
        bone24.cubeList.add(new ModelBox(bone24, 71, 25, 0.0F, 2.1213F, -12.5F, 1, 1, 1, 0.0F, false));
        bone24.cubeList.add(new ModelBox(bone24, 71, 25, 0.0F, 2.1213F, -15.5F, 1, 1, 1, 0.0F, false));
        bone24.cubeList.add(new ModelBox(bone24, 71, 25, 0.0F, 2.1213F, -18.5F, 1, 1, 1, 0.0F, false));
        bone24.cubeList.add(new ModelBox(bone24, 71, 25, 0.0F, 2.1213F, -21.5F, 1, 1, 1, 0.0F, false));
        bone24.cubeList.add(new ModelBox(bone24, 71, 25, 0.0F, 2.1213F, -24.5F, 1, 1, 1, 0.0F, false));
        bone24.cubeList.add(new ModelBox(bone24, 71, 25, 0.0F, 2.1213F, -27.5F, 1, 1, 1, 0.0F, false));
        bone24.cubeList.add(new ModelBox(bone24, 71, 25, 0.0F, 2.1213F, -37.5F, 1, 1, 8, 0.0F, false));
        bone24.cubeList.add(new ModelBox(bone24, 71, 25, 3.9497F, 6.0711F, -0.5F, 1, 1, 1, 0.0F, false));
        bone24.cubeList.add(new ModelBox(bone24, 71, 25, 3.9497F, 6.0711F, -3.5F, 1, 1, 1, 0.0F, false));
        bone24.cubeList.add(new ModelBox(bone24, 71, 25, 3.9497F, 6.0711F, -6.5F, 1, 1, 1, 0.0F, false));
        bone24.cubeList.add(new ModelBox(bone24, 71, 25, 3.9497F, 6.0711F, -9.5F, 1, 1, 1, 0.0F, false));
        bone24.cubeList.add(new ModelBox(bone24, 71, 25, 3.9497F, 6.0711F, -12.5F, 1, 1, 1, 0.0F, false));
        bone24.cubeList.add(new ModelBox(bone24, 71, 25, 3.9497F, 6.0711F, -15.5F, 1, 1, 1, 0.0F, false));
        bone24.cubeList.add(new ModelBox(bone24, 71, 25, 3.9497F, 6.0711F, -18.5F, 1, 1, 1, 0.0F, false));
        bone24.cubeList.add(new ModelBox(bone24, 71, 25, 3.9497F, 6.0711F, -21.5F, 1, 1, 1, 0.0F, false));
        bone24.cubeList.add(new ModelBox(bone24, 71, 25, 3.9497F, 6.0711F, -24.5F, 1, 1, 1, 0.0F, false));
        bone24.cubeList.add(new ModelBox(bone24, 71, 25, 3.9497F, 6.0711F, -27.5F, 1, 1, 1, 0.0F, false));
        bone24.cubeList.add(new ModelBox(bone24, 71, 25, 3.9497F, 6.0711F, -37.5F, 1, 1, 8, 0.0F, false));
        bone24.cubeList.add(new ModelBox(bone24, 71, 25, 6.0711F, 3.9497F, -0.5F, 1, 1, 1, 0.0F, false));
        bone24.cubeList.add(new ModelBox(bone24, 71, 25, 6.0711F, 3.9497F, -3.5F, 1, 1, 1, 0.0F, false));
        bone24.cubeList.add(new ModelBox(bone24, 71, 25, 6.0711F, 3.9497F, -6.5F, 1, 1, 1, 0.0F, false));
        bone24.cubeList.add(new ModelBox(bone24, 71, 25, 6.0711F, 3.9497F, -9.5F, 1, 1, 1, 0.0F, false));
        bone24.cubeList.add(new ModelBox(bone24, 71, 25, 6.0711F, 3.9497F, -12.5F, 1, 1, 1, 0.0F, false));
        bone24.cubeList.add(new ModelBox(bone24, 71, 25, 6.0711F, 3.9497F, -15.5F, 1, 1, 1, 0.0F, false));
        bone24.cubeList.add(new ModelBox(bone24, 71, 25, 6.0711F, 3.9497F, -18.5F, 1, 1, 1, 0.0F, false));
        bone24.cubeList.add(new ModelBox(bone24, 71, 25, 6.0711F, 3.9497F, -21.5F, 1, 1, 1, 0.0F, false));
        bone24.cubeList.add(new ModelBox(bone24, 71, 25, 6.0711F, 3.9497F, -24.5F, 1, 1, 1, 0.0F, false));
        bone24.cubeList.add(new ModelBox(bone24, 71, 25, 6.0711F, 3.9497F, -27.5F, 1, 1, 1, 0.0F, false));
        bone24.cubeList.add(new ModelBox(bone24, 71, 25, 6.0711F, 3.9497F, -37.5F, 1, 1, 8, 0.0F, false));

        bone30 = new ModelRenderer(this);
        bone30.setRotationPoint(0.0F, 10.5F, -31.0F);
        dp28.addChild(bone30);
        setRotationAngle(bone30, 0.3491F, 0.0F, 0.0F);
        bone30.cubeList.add(new ModelBox(bone30, 71, 25, -1.5F, -8.3376F, 7.2636F, 3, 3, 9, 0.0F, false));

        bone32 = new ModelRenderer(this);
        bone32.setRotationPoint(-1.5F, -8.3594F, -1.3867F);
        dp28.addChild(bone32);
        setRotationAngle(bone32, -0.5236F, 0.0F, 0.0F);
        bone32.cubeList.add(new ModelBox(bone32, 68, 19, -0.5F, -5.0F, 16.0F, 4, 4, 5, 0.0F, false));
        bone32.cubeList.add(new ModelBox(bone32, 68, 19, 0.7929F, -5.7071F, 16.0F, 2, 1, 5, 0.0F, false));
        bone32.cubeList.add(new ModelBox(bone32, 167, 158, 0.7929F, -5.7071F, 21.0F, 2, 1, 10, 0.0F, false));
        bone32.cubeList.add(new ModelBox(bone32, 68, 19, 0.7929F, -1.2929F, 16.0F, 2, 1, 5, 0.0F, false));
        bone32.cubeList.add(new ModelBox(bone32, 167, 158, 0.7929F, -1.2929F, 21.0F, 2, 1, 10, 0.0F, false));
        bone32.cubeList.add(new ModelBox(bone32, 68, 19, 0.2071F, -5.7071F, 16.0F, 1, 1, 5, 0.0F, true));
        bone32.cubeList.add(new ModelBox(bone32, 167, 158, 0.2071F, -5.7071F, 21.0F, 1, 1, 10, 0.0F, true));
        bone32.cubeList.add(new ModelBox(bone32, 68, 19, 0.2071F, -1.2929F, 16.0F, 1, 1, 5, 0.0F, true));
        bone32.cubeList.add(new ModelBox(bone32, 167, 158, 0.2071F, -1.2929F, 21.0F, 1, 1, 10, 0.0F, true));

        bone34 = new ModelRenderer(this);
        bone34.setRotationPoint(-0.5F, 0.0F, 0.0F);
        bone32.addChild(bone34);
        setRotationAngle(bone34, 0.0F, 0.0F, 0.7854F);
        bone34.cubeList.add(new ModelBox(bone34, 68, 19, -1.7071F, -6.364F, 16.0F, 1, 1, 5, 0.0F, false));
        bone34.cubeList.add(new ModelBox(bone34, 167, 158, -1.7071F, -6.364F, 21.0F, 1, 1, 10, 0.0F, false));
        bone34.cubeList.add(new ModelBox(bone34, 68, 19, 1.1213F, -3.5355F, 16.0F, 1, 1, 5, 0.0F, false));
        bone34.cubeList.add(new ModelBox(bone34, 167, 158, 1.1213F, -3.5355F, 21.0F, 1, 1, 10, 0.0F, false));
        bone34.cubeList.add(new ModelBox(bone34, 68, 19, -0.7071F, -1.7071F, 16.0F, 1, 1, 5, 0.0F, false));
        bone34.cubeList.add(new ModelBox(bone34, 167, 158, -0.7071F, -1.7071F, 21.0F, 1, 1, 10, 0.0F, false));
        bone34.cubeList.add(new ModelBox(bone34, 68, 19, -3.5355F, -4.5355F, 16.0F, 1, 1, 5, 0.0F, false));
        bone34.cubeList.add(new ModelBox(bone34, 167, 158, -3.5355F, -4.5355F, 21.0F, 1, 1, 10, 0.0F, false));

        bone35 = new ModelRenderer(this);
        bone35.setRotationPoint(-1.5F, 5.5352F, 9.6133F);
        dp28.addChild(bone35);
        setRotationAngle(bone35, -0.0873F, 0.0F, 0.0F);
        bone35.cubeList.add(new ModelBox(bone35, 167, 161, -0.5F, -5.0F, 16.0F, 4, 5, 6, 0.0F, false));
        bone35.cubeList.add(new ModelBox(bone35, 167, 158, 0.7929F, -5.7071F, 16.0F, 2, 1, 6, 0.0F, false));
        bone35.cubeList.add(new ModelBox(bone35, 167, 158, 0.2071F, -5.7071F, 16.0F, 1, 1, 6, 0.0F, true));

        bone41 = new ModelRenderer(this);
        bone41.setRotationPoint(-0.5F, 0.0F, 0.0F);
        bone35.addChild(bone41);
        setRotationAngle(bone41, -0.2618F, 0.0F, 0.0F);
        bone41.cubeList.add(new ModelBox(bone41, 167, 158, 0.0F, -9.6719F, 17.543F, 4, 5, 9, 0.0F, false));
        bone41.cubeList.add(new ModelBox(bone41, 167, 157, 0.0F, -9.6719F, 26.543F, 4, 5, 6, 0.0F, false));
        bone41.cubeList.add(new ModelBox(bone41, 167, 160, 0.0F, -13.6719F, 23.875F, 4, 4, 7, 0.0F, false));

        bone36 = new ModelRenderer(this);
        bone36.setRotationPoint(-0.5F, 0.0F, 0.0F);
        bone35.addChild(bone36);
        setRotationAngle(bone36, 0.0F, 0.0F, 0.7854F);
        bone36.cubeList.add(new ModelBox(bone36, 167, 158, -1.7071F, -6.364F, 16.0F, 1, 1, 6, 0.0F, false));
        bone36.cubeList.add(new ModelBox(bone36, 167, 158, -3.5355F, -4.5355F, 16.0F, 1, 1, 6, 0.0F, false));

        bone37 = new ModelRenderer(this);
        bone37.setRotationPoint(-1.5F, 16.0313F, 21.1836F);
        dp28.addChild(bone37);
        setRotationAngle(bone37, 0.6109F, 0.0F, 0.0F);
        bone37.cubeList.add(new ModelBox(bone37, 148, 158, -0.5F, -5.0F, 14.0F, 4, 4, 8, 0.0F, false));
        bone37.cubeList.add(new ModelBox(bone37, 148, 158, 0.7929F, -5.7071F, 16.0F, 2, 1, 6, 0.0F, false));
        bone37.cubeList.add(new ModelBox(bone37, 148, 158, 0.2071F, -5.7071F, 16.0F, 1, 1, 6, 0.0F, true));

        bone38 = new ModelRenderer(this);
        bone38.setRotationPoint(-0.5F, 0.0F, 0.0F);
        bone37.addChild(bone38);
        setRotationAngle(bone38, 0.0F, 0.0F, 0.7854F);
        bone38.cubeList.add(new ModelBox(bone38, 148, 158, -1.7071F, -6.364F, 16.0F, 1, 1, 6, 0.0F, false));
        bone38.cubeList.add(new ModelBox(bone38, 148, 158, -3.5355F, -4.5355F, 16.0F, 1, 1, 6, 0.0F, false));

        bone39 = new ModelRenderer(this);
        bone39.setRotationPoint(-1.5F, 4.1875F, 19.7383F);
        dp28.addChild(bone39);
        bone39.cubeList.add(new ModelBox(bone39, 158, 163, -0.5F, -5.0F, 16.0F, 4, 4, 12, 0.0F, false));
        bone39.cubeList.add(new ModelBox(bone39, 158, 163, -0.5F, -5.0F, 28.0F, 4, 4, 8, 0.0F, false));
        bone39.cubeList.add(new ModelBox(bone39, 158, 161, -0.5F, -1.0F, 22.0F, 4, 9, 6, 0.0F, false));
        bone39.cubeList.add(new ModelBox(bone39, 158, 163, -0.5F, -1.0F, 28.0F, 4, 9, 8, 0.0F, false));
        bone39.cubeList.add(new ModelBox(bone39, 86, 86, -1.0F, -5.9063F, 36.0F, 5, 14, 1, 0.0F, false));
        bone39.cubeList.add(new ModelBox(bone39, 148, 158, 0.7929F, -5.7071F, 16.0F, 2, 1, 12, 0.0F, false));
        bone39.cubeList.add(new ModelBox(bone39, 148, 158, 0.7929F, -5.7071F, 28.0F, 2, 1, 8, 0.0F, false));
        bone39.cubeList.add(new ModelBox(bone39, 148, 158, 0.2071F, -5.7071F, 16.0F, 1, 1, 12, 0.0F, true));
        bone39.cubeList.add(new ModelBox(bone39, 148, 158, 0.2071F, -5.7071F, 28.0F, 1, 1, 8, 0.0F, true));

        bone40 = new ModelRenderer(this);
        bone40.setRotationPoint(-0.5F, 0.0F, 0.0F);
        bone39.addChild(bone40);
        setRotationAngle(bone40, 0.0F, 0.0F, 0.7854F);
        bone40.cubeList.add(new ModelBox(bone40, 148, 158, -1.7071F, -6.364F, 16.0F, 1, 1, 12, 0.0F, false));
        bone40.cubeList.add(new ModelBox(bone40, 148, 158, -1.7071F, -6.364F, 28.0F, 1, 1, 8, 0.0F, false));
        bone40.cubeList.add(new ModelBox(bone40, 148, 158, -3.5355F, -4.5355F, 16.0F, 1, 1, 12, 0.0F, false));
        bone40.cubeList.add(new ModelBox(bone40, 148, 158, -3.5355F, -4.5355F, 28.0F, 1, 1, 8, 0.0F, false));

        bone33 = new ModelRenderer(this);
        bone33.setRotationPoint(-1.5F, -8.3594F, -1.3867F);
        dp28.addChild(bone33);
        setRotationAngle(bone33, -0.5236F, 0.0F, 0.0F);
        bone33.cubeList.add(new ModelBox(bone33, 167, 158, -0.5F, -5.0F, 21.0F, 4, 4, 10, 0.0F, false));
        bone33.cubeList.add(new ModelBox(bone33, 33, 66, 0.0F, -5.5F, 21.0F, 3, 5, 10, 0.0F, false));

        ironsights = new ModelRenderer(this);
        ironsights.setRotationPoint(-0.5F, 24.0F, 0.0F);
        ironsights.cubeList.add(new ModelBox(ironsights, 68, 19, -2.0F, -8.0273F, 2.7227F, 5, 3, 4, 0.0F, false));
        ironsights.cubeList.add(new ModelBox(ironsights, 68, 19, 1.6F, -9.0273F, 2.7227F, 1, 1, 4, 0.0F, false));
        ironsights.cubeList.add(new ModelBox(ironsights, 68, 19, -1.6F, -9.0273F, 2.7227F, 1, 1, 4, 0.0F, true));
        ironsights.cubeList.add(new ModelBox(ironsights, 68, 19, -1.0F, -9.2031F, 3.2227F, 3, 2, 1, 0.0F, true));
        ironsights.cubeList.add(new ModelBox(ironsights, 68, 19, -1.6F, -11.0273F, 2.7227F, 1, 2, 1, 0.0F, true));
        ironsights.cubeList.add(new ModelBox(ironsights, 68, 19, 1.6F, -11.0273F, 2.7227F, 1, 2, 1, 0.0F, false));
        ironsights.cubeList.add(new ModelBox(ironsights, 71, 25, -1.0F, -8.6F, -73.0F, 3, 1, 3, 0.0F, false));
        ironsights.cubeList.add(new ModelBox(ironsights, 71, 25, 1.2305F, -10.2289F, -73.0F, 1, 2, 3, 0.0F, false));
        ironsights.cubeList.add(new ModelBox(ironsights, 71, 25, 1.2305F, -10.936F, -71.7071F, 1, 1, 1, 0.0F, false));
        ironsights.cubeList.add(new ModelBox(ironsights, 71, 25, -1.2305F, -10.936F, -71.7071F, 1, 1, 1, 0.0F, true));
        ironsights.cubeList.add(new ModelBox(ironsights, 71, 25, -1.2305F, -10.2289F, -73.0F, 1, 2, 3, 0.0F, true));
        ironsights.cubeList.add(new ModelBox(ironsights, 71, 25, 1.2305F, -10.936F, -72.2929F, 1, 1, 1, 0.0F, false));
        ironsights.cubeList.add(new ModelBox(ironsights, 71, 25, -1.2305F, -10.936F, -72.2929F, 1, 1, 1, 0.0F, true));
        ironsights.cubeList.add(new ModelBox(ironsights, 71, 25, -0.5F, -9.3852F, -72.5F, 2, 1, 2, 0.0F, true));
        ironsights.cubeList.add(new ModelBox(ironsights, 71, 25, -0.5F, -7.8F, -73.0F, 2, 1, 3, 0.0F, false));
        ironsights.cubeList.add(new ModelBox(ironsights, 71, 25, -1.0F, -7.0F, -73.0F, 3, 1, 3, 0.0F, false));

        bone31 = new ModelRenderer(this);
        bone31.setRotationPoint(2.5F, -8.3125F, 13.207F);
        ironsights.addChild(bone31);
        setRotationAngle(bone31, 0.7854F, 0.0F, 0.0F);
        bone31.cubeList.add(new ModelBox(bone31, 68, 19, -4.1F, -8.2138F, -6.2059F, 1, 2, 1, 0.0F, true));
        bone31.cubeList.add(new ModelBox(bone31, 68, 19, -0.9F, -8.2138F, -6.2059F, 1, 2, 1, 0.0F, false));

        bone29 = new ModelRenderer(this);
        bone29.setRotationPoint(-0.7305F, -10.7289F, -71.5F);
        ironsights.addChild(bone29);
        setRotationAngle(bone29, 0.7854F, 0.0F, 0.0F);
        bone29.cubeList.add(new ModelBox(bone29, 71, 25, -0.5F, -0.7071F, -1.4142F, 1, 1, 1, 0.0F, true));
        bone29.cubeList.add(new ModelBox(bone29, 71, 25, -0.5F, 0.4142F, -0.2929F, 1, 1, 1, 0.0F, true));
        bone29.cubeList.add(new ModelBox(bone29, 71, 25, 1.9609F, -0.7071F, -1.4142F, 1, 1, 1, 0.0F, false));
        bone29.cubeList.add(new ModelBox(bone29, 71, 25, 1.9609F, 0.4142F, -0.2929F, 1, 1, 1, 0.0F, false));

        bullet = new ModelRenderer(this);
        bullet.setRotationPoint(0.0F, 24.0F, 0.0F);
        bullet.cubeList.add(new ModelBox(bullet, 3, 493, -1.0F, -5.0F, -12.5F, 2, 2, 5, 0.0F, true));
        bullet.cubeList.add(new ModelBox(bullet, 3, 493, -0.5F, -4.5F, -13.5F, 1, 1, 1, 0.0F, true));

        magazine = new ModelRenderer(this);
        magazine.setRotationPoint(0.0F, 14.0F, 0.0F);
        magazine.cubeList.add(new ModelBox(magazine, 78, 35, -2.5F, 3.0F, -2.0F, 5, 2, 3, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 78, 35, 0.0F, 3.0F, -5.0F, 10, 2, 3, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 78, 35, -9.0F, 3.0F, -5.0F, 9, 2, 3, 0.0F, true));
        magazine.cubeList.add(new ModelBox(magazine, 78, 35, -12.0F, 3.0F, -8.0F, 12, 2, 3, 0.0F, true));
        magazine.cubeList.add(new ModelBox(magazine, 78, 35, 0.0F, 3.0F, -8.0F, 13, 2, 3, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 78, 35, 5.0F, 3.0F, -14.0F, 10, 2, 6, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 78, 35, 5.0F, 3.0F, -20.0F, 10, 2, 6, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 78, 35, -15.0F, 3.0F, -14.0F, 10, 2, 6, 0.0F, true));
        magazine.cubeList.add(new ModelBox(magazine, 78, 35, -15.0F, 3.0F, -20.0F, 10, 2, 6, 0.0F, true));
        magazine.cubeList.add(new ModelBox(magazine, 78, 35, -15.0F, 3.0F, -25.0F, 10, 2, 5, 0.0F, true));
        magazine.cubeList.add(new ModelBox(magazine, 78, 35, -5.0F, 3.0F, -25.0F, 10, 2, 5, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 78, 35, -13.0F, 3.0F, -28.0F, 13, 2, 3, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 78, 35, -10.5F, 3.0F, -30.0F, 12, 2, 2, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 78, 35, 1.5F, 3.0F, -30.0F, 9, 2, 2, 0.0F, true));
        magazine.cubeList.add(new ModelBox(magazine, 78, 35, 2.5F, 3.0F, -32.0F, 5, 2, 2, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 78, 35, -7.5F, 3.0F, -32.0F, 5, 2, 2, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 78, 35, 0.0F, 3.0F, -28.0F, 13, 2, 3, 0.0F, true));
        magazine.cubeList.add(new ModelBox(magazine, 78, 35, 5.0F, 3.0F, -25.0F, 10, 2, 5, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 78, 35, -5.0F, 3.0F, -14.0F, 10, 2, 6, 0.0F, true));
        magazine.cubeList.add(new ModelBox(magazine, 78, 35, 1.0F, 4.0F, -15.5F, 1, 2, 9, 0.0F, true));
        magazine.cubeList.add(new ModelBox(magazine, 78, 35, -2.0F, 4.0F, -15.5F, 1, 2, 9, 0.0F, true));
        magazine.cubeList.add(new ModelBox(magazine, 78, 35, -2.0F, 4.0F, -6.5F, 4, 2, 1, 0.0F, true));
        magazine.cubeList.add(new ModelBox(magazine, 78, 35, -2.0F, 4.0F, -16.5F, 4, 2, 1, 0.0F, true));
        magazine.cubeList.add(new ModelBox(magazine, 78, 35, -5.0F, 3.0F, -20.0F, 10, 2, 6, 0.0F, true));
        magazine.cubeList.add(new ModelBox(magazine, 78, 35, -2.5F, 3.5F, 1.0F, 5, 1, 1, 0.0F, false));

        bone = new ModelRenderer(this);
        bone.setRotationPoint(0.0F, 0.0F, 0.0F);
        magazine.addChild(bone);
        setRotationAngle(bone, 0.0F, 0.2618F, 0.0F);
        bone.cubeList.add(new ModelBox(bone, 78, 35, 1.8972F, 3.0F, -1.4211F, 5, 2, 3, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 78, 35, 1.8972F, 3.5F, 1.5789F, 5, 1, 1, 0.0F, false));

        bone2 = new ModelRenderer(this);
        bone2.setRotationPoint(0.0F, 0.0F, 0.0F);
        magazine.addChild(bone2);
        setRotationAngle(bone2, 0.0F, 0.5236F, 0.0F);
        bone2.cubeList.add(new ModelBox(bone2, 78, 35, 5.9947F, 3.0F, 0.2761F, 5, 2, 3, 0.0F, false));
        bone2.cubeList.add(new ModelBox(bone2, 78, 35, 5.9947F, 3.5F, 3.2761F, 5, 1, 1, 0.0F, false));

        bone3 = new ModelRenderer(this);
        bone3.setRotationPoint(0.0F, 0.0F, 0.0F);
        magazine.addChild(bone3);
        setRotationAngle(bone3, 0.0F, 0.7854F, 0.0F);
        bone3.cubeList.add(new ModelBox(bone3, 78, 35, 9.5133F, 3.0F, 2.9761F, 5, 2, 3, 0.0F, false));
        bone3.cubeList.add(new ModelBox(bone3, 78, 35, 9.5133F, 3.5F, 5.9761F, 5, 1, 1, 0.0F, false));

        bone4 = new ModelRenderer(this);
        bone4.setRotationPoint(0.0F, 0.0F, 0.0F);
        magazine.addChild(bone4);
        setRotationAngle(bone4, 0.0F, 1.0472F, 0.0F);
        bone4.cubeList.add(new ModelBox(bone4, 78, 35, 12.2132F, 3.0F, 6.4947F, 5, 2, 3, 0.0F, false));
        bone4.cubeList.add(new ModelBox(bone4, 78, 35, 12.2132F, 3.5F, 9.4947F, 5, 1, 1, 0.0F, false));

        bone5 = new ModelRenderer(this);
        bone5.setRotationPoint(0.0F, 0.0F, 0.0F);
        magazine.addChild(bone5);
        setRotationAngle(bone5, 0.0F, 1.309F, 0.0F);
        bone5.cubeList.add(new ModelBox(bone5, 78, 35, 13.9105F, 3.0F, 10.5922F, 5, 2, 3, 0.0F, false));
        bone5.cubeList.add(new ModelBox(bone5, 78, 35, 13.9105F, 3.5F, 13.5922F, 5, 1, 1, 0.0F, false));

        bone6 = new ModelRenderer(this);
        bone6.setRotationPoint(0.0F, 0.0F, 0.0F);
        magazine.addChild(bone6);
        setRotationAngle(bone6, 0.0F, 1.5708F, 0.0F);
        bone6.cubeList.add(new ModelBox(bone6, 78, 35, 14.4894F, 3.0F, 14.9894F, 5, 2, 3, 0.0F, false));
        bone6.cubeList.add(new ModelBox(bone6, 78, 35, 14.4894F, 3.5F, 17.9894F, 5, 1, 1, 0.0F, false));

        bone7 = new ModelRenderer(this);
        bone7.setRotationPoint(0.0F, 0.0F, 0.0F);
        magazine.addChild(bone7);
        setRotationAngle(bone7, 0.0F, 1.8326F, 0.0F);
        bone7.cubeList.add(new ModelBox(bone7, 78, 35, 13.9105F, 3.0F, 19.3866F, 5, 2, 3, 0.0F, false));
        bone7.cubeList.add(new ModelBox(bone7, 78, 35, 13.9105F, 3.5F, 22.3866F, 5, 1, 1, 0.0F, false));

        bone8 = new ModelRenderer(this);
        bone8.setRotationPoint(0.0F, 0.0F, 0.0F);
        magazine.addChild(bone8);
        setRotationAngle(bone8, 0.0F, 2.0944F, 0.0F);
        bone8.cubeList.add(new ModelBox(bone8, 78, 35, 12.2132F, 3.0F, 23.4841F, 5, 2, 3, 0.0F, false));
        bone8.cubeList.add(new ModelBox(bone8, 78, 35, 12.2132F, 3.5F, 26.4841F, 5, 1, 1, 0.0F, false));

        bone9 = new ModelRenderer(this);
        bone9.setRotationPoint(0.0F, 0.0F, 0.0F);
        magazine.addChild(bone9);
        setRotationAngle(bone9, 0.0F, 2.3562F, 0.0F);
        bone9.cubeList.add(new ModelBox(bone9, 78, 35, 9.5133F, 3.0F, 27.0027F, 5, 2, 3, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 78, 35, 9.5133F, 3.5F, 30.0027F, 5, 1, 1, 0.0F, false));

        bone10 = new ModelRenderer(this);
        bone10.setRotationPoint(0.0F, 0.0F, 0.0F);
        magazine.addChild(bone10);
        setRotationAngle(bone10, 0.0F, 2.618F, 0.0F);
        bone10.cubeList.add(new ModelBox(bone10, 78, 35, 5.9947F, 3.0F, 29.7026F, 5, 2, 3, 0.0F, false));
        bone10.cubeList.add(new ModelBox(bone10, 78, 35, 5.9947F, 3.5F, 32.7026F, 5, 1, 1, 0.0F, false));

        bone11 = new ModelRenderer(this);
        bone11.setRotationPoint(0.0F, 0.0F, 0.0F);
        magazine.addChild(bone11);
        setRotationAngle(bone11, 0.0F, 2.8798F, 0.0F);
        bone11.cubeList.add(new ModelBox(bone11, 78, 35, 1.8972F, 3.0F, 31.3999F, 5, 2, 3, 0.0F, false));
        bone11.cubeList.add(new ModelBox(bone11, 78, 35, 1.8972F, 3.5F, 34.3999F, 5, 1, 1, 0.0F, false));

        bone12 = new ModelRenderer(this);
        bone12.setRotationPoint(0.0F, 0.0F, 0.0F);
        magazine.addChild(bone12);
        setRotationAngle(bone12, 0.0F, 3.1416F, 0.0F);
        bone12.cubeList.add(new ModelBox(bone12, 78, 35, -2.5F, 3.0F, 29.9788F, 5, 2, 5, 0.0F, false));
        bone12.cubeList.add(new ModelBox(bone12, 78, 35, -2.5F, 3.5F, 34.9788F, 5, 1, 1, 0.0F, false));

        bone13 = new ModelRenderer(this);
        bone13.setRotationPoint(0.0F, 0.0F, 0.0F);
        magazine.addChild(bone13);
        setRotationAngle(bone13, 0.0F, -2.8798F, 0.0F);
        bone13.cubeList.add(new ModelBox(bone13, 78, 35, -6.8972F, 3.0F, 31.3999F, 5, 2, 3, 0.0F, false));
        bone13.cubeList.add(new ModelBox(bone13, 78, 35, -6.8972F, 3.5F, 34.3999F, 5, 1, 1, 0.0F, false));

        bone14 = new ModelRenderer(this);
        bone14.setRotationPoint(0.0F, 0.0F, 0.0F);
        magazine.addChild(bone14);
        setRotationAngle(bone14, 0.0F, -2.618F, 0.0F);
        bone14.cubeList.add(new ModelBox(bone14, 78, 35, -10.9947F, 3.0F, 29.7026F, 5, 2, 3, 0.0F, false));
        bone14.cubeList.add(new ModelBox(bone14, 78, 35, -10.9947F, 3.5F, 32.7026F, 5, 1, 1, 0.0F, false));

        bone15 = new ModelRenderer(this);
        bone15.setRotationPoint(0.0F, 0.0F, 0.0F);
        magazine.addChild(bone15);
        setRotationAngle(bone15, 0.0F, -2.3562F, 0.0F);
        bone15.cubeList.add(new ModelBox(bone15, 78, 35, -14.5133F, 3.0F, 27.0027F, 5, 2, 3, 0.0F, false));
        bone15.cubeList.add(new ModelBox(bone15, 78, 35, -14.5133F, 3.5F, 30.0027F, 5, 1, 1, 0.0F, false));

        bone16 = new ModelRenderer(this);
        bone16.setRotationPoint(0.0F, 0.0F, 0.0F);
        magazine.addChild(bone16);
        setRotationAngle(bone16, 0.0F, -2.0944F, 0.0F);
        bone16.cubeList.add(new ModelBox(bone16, 78, 35, -17.2132F, 3.0F, 23.4841F, 5, 2, 3, 0.0F, false));
        bone16.cubeList.add(new ModelBox(bone16, 78, 35, -17.2132F, 3.5F, 26.4841F, 5, 1, 1, 0.0F, false));

        bone17 = new ModelRenderer(this);
        bone17.setRotationPoint(0.0F, 0.0F, 0.0F);
        magazine.addChild(bone17);
        setRotationAngle(bone17, 0.0F, -1.8326F, 0.0F);
        bone17.cubeList.add(new ModelBox(bone17, 78, 35, -18.9105F, 3.0F, 19.3866F, 5, 2, 3, 0.0F, false));
        bone17.cubeList.add(new ModelBox(bone17, 78, 35, -18.9105F, 3.5F, 22.3866F, 5, 1, 1, 0.0F, false));

        bone18 = new ModelRenderer(this);
        bone18.setRotationPoint(0.0F, 0.0F, 0.0F);
        magazine.addChild(bone18);
        setRotationAngle(bone18, 0.0F, -1.5708F, 0.0F);
        bone18.cubeList.add(new ModelBox(bone18, 78, 35, -19.4894F, 3.0F, 14.9894F, 5, 2, 3, 0.0F, false));
        bone18.cubeList.add(new ModelBox(bone18, 78, 35, -19.4894F, 3.5F, 17.9894F, 5, 1, 1, 0.0F, false));

        bone19 = new ModelRenderer(this);
        bone19.setRotationPoint(0.0F, 0.0F, 0.0F);
        magazine.addChild(bone19);
        setRotationAngle(bone19, 0.0F, -1.309F, 0.0F);
        bone19.cubeList.add(new ModelBox(bone19, 78, 35, -18.9105F, 3.0F, 10.5922F, 5, 2, 3, 0.0F, false));
        bone19.cubeList.add(new ModelBox(bone19, 78, 35, -18.9105F, 3.5F, 13.5922F, 5, 1, 1, 0.0F, false));

        bone20 = new ModelRenderer(this);
        bone20.setRotationPoint(0.0F, 0.0F, 0.0F);
        magazine.addChild(bone20);
        setRotationAngle(bone20, 0.0F, -1.0472F, 0.0F);
        bone20.cubeList.add(new ModelBox(bone20, 78, 35, -17.2132F, 3.0F, 6.4947F, 5, 2, 3, 0.0F, false));
        bone20.cubeList.add(new ModelBox(bone20, 78, 35, -17.2132F, 3.5F, 9.4947F, 5, 1, 1, 0.0F, false));

        bone21 = new ModelRenderer(this);
        bone21.setRotationPoint(0.0F, 0.0F, 0.0F);
        magazine.addChild(bone21);
        setRotationAngle(bone21, 0.0F, -0.7854F, 0.0F);
        bone21.cubeList.add(new ModelBox(bone21, 78, 35, -14.5133F, 3.0F, 2.9761F, 5, 2, 3, 0.0F, false));
        bone21.cubeList.add(new ModelBox(bone21, 78, 35, -14.5133F, 3.5F, 5.9761F, 5, 1, 1, 0.0F, false));

        bone22 = new ModelRenderer(this);
        bone22.setRotationPoint(0.0F, 0.0F, 0.0F);
        magazine.addChild(bone22);
        setRotationAngle(bone22, 0.0F, -0.5236F, 0.0F);
        bone22.cubeList.add(new ModelBox(bone22, 78, 35, -10.9947F, 3.0F, 0.2761F, 5, 2, 3, 0.0F, false));
        bone22.cubeList.add(new ModelBox(bone22, 78, 35, -10.9947F, 3.5F, 3.2761F, 5, 1, 1, 0.0F, false));

        bone23 = new ModelRenderer(this);
        bone23.setRotationPoint(0.0F, 0.0F, 0.0F);
        magazine.addChild(bone23);
        setRotationAngle(bone23, 0.0F, -0.2618F, 0.0F);
        bone23.cubeList.add(new ModelBox(bone23, 78, 35, -6.8972F, 3.0F, -1.4211F, 5, 2, 3, 0.0F, false));
        bone23.cubeList.add(new ModelBox(bone23, 78, 35, -6.8972F, 3.5F, 1.5789F, 5, 1, 1, 0.0F, false));

        charging_handle = new ModelRenderer(this);
        charging_handle.setRotationPoint(-0.5F, 24.0F, 0.0F);
        charging_handle.cubeList.add(new ModelBox(charging_handle, 33, 155, -3.5F, 0.7188F, -15.6328F, 8, 1, 2, 0.0F, false));
        charging_handle.cubeList.add(new ModelBox(charging_handle, 33, 155, -1.0F, -0.4609F, -16.6328F, 3, 2, 4, 0.0F, false));
        charging_handle.cubeList.add(new ModelBox(charging_handle, 2, 81, -1.0F, -1.4609F, -17.0F, 3, 1, 12, 0.0F, false));

        addEntry(AnimationElement.MAGAZINE, stack -> magazine);
        addEntry(AnimationElement.CHARGING, stack -> charging_handle);
        addEntry(AnimationElement.BULLET, stack -> bullet);
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }

    @Override
    public void renderModel(ItemStack stack) {
        dp28.render(1f);
        if (!hasScopeAtachment(stack)) ironsights.render(1f);
    }

    @Override
    public void transformModel() {
        ModelTransformationHelper.defaultARTransform();
        GlStateManager.translate(-0.075, -10.85, -10.0);
    }
}

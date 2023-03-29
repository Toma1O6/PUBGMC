package dev.toma.pubgmc.client.models.weapons;

import dev.toma.pubgmc.client.animation.AnimationElement;
import dev.toma.pubgmc.client.util.ModelTransformationHelper;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.item.ItemStack;

public class ModelSawedOff extends ModelGun {

    private final ModelRenderer barrels;
    private final ModelRenderer bone4;
    private final ModelRenderer bone2;
    private final ModelRenderer bone3;
    private final ModelRenderer sawedoff;
    private final ModelRenderer bone12;
    private final ModelRenderer bone13;
    private final ModelRenderer bone14;
    private final ModelRenderer bone6;
    private final ModelRenderer bone7;
    private final ModelRenderer bone8;
    private final ModelRenderer bone9;
    private final ModelRenderer bone10;
    private final ModelRenderer bone11;
    private final ModelRenderer bone5;
    private final ModelRenderer bullet1;
    private final ModelRenderer bullet2;

    public ModelSawedOff() {
        textureWidth = 512;
        textureHeight = 512;

        barrels = new ModelRenderer(this);
        barrels.setRotationPoint(0.0F, 28.5F, -3.5F);
        barrels.cubeList.add(new ModelBox(barrels, 64, 13, -2.0F, -1.5F, -2.5F, 4, 3, 4, 0.0F, true));
        barrels.cubeList.add(new ModelBox(barrels, 64, 13, -5.0F, -1.5F, -6.5F, 10, 3, 4, 0.0F, true));
        barrels.cubeList.add(new ModelBox(barrels, 141, 158, -4.5F, -1.5F, -13.5F, 9, 2, 7, 0.0F, true));
        barrels.cubeList.add(new ModelBox(barrels, 141, 158, -4.5F, 0.5F, -7.5F, 9, 2, 1, 0.0F, true));
        barrels.cubeList.add(new ModelBox(barrels, 141, 158, -4.5F, -1.5F, -20.5F, 9, 2, 7, 0.0F, true));
        barrels.cubeList.add(new ModelBox(barrels, 79, 26, 5.4641F, -6.5F, -26.5F, 2, 2, 14, 0.0F, true));
        barrels.cubeList.add(new ModelBox(barrels, 70, 11, 5.4641F, -6.5F, -12.5F, 2, 2, 19, 0.0F, true));
        barrels.cubeList.add(new ModelBox(barrels, 70, 11, 2.7321F, -9.2321F, -12.5F, 2, 2, 19, 0.0F, false));
        barrels.cubeList.add(new ModelBox(barrels, 79, 26, 2.7321F, -9.2321F, -26.5F, 2, 2, 14, 0.0F, false));
        barrels.cubeList.add(new ModelBox(barrels, 79, 26, -4.7321F, -9.2321F, -26.5F, 2, 2, 14, 0.0F, true));
        barrels.cubeList.add(new ModelBox(barrels, 70, 11, -4.7321F, -9.2321F, -12.5F, 2, 2, 19, 0.0F, true));
        barrels.cubeList.add(new ModelBox(barrels, 64, 13, -4.7321F, -9.2321F, 6.5F, 2, 1, 8, 0.0F, true));
        barrels.cubeList.add(new ModelBox(barrels, 64, 13, 2.7321F, -9.2321F, 6.5F, 2, 1, 8, 0.0F, true));
        barrels.cubeList.add(new ModelBox(barrels, 70, 11, -7.4641F, -6.5F, -12.5F, 2, 2, 19, 0.0F, false));
        barrels.cubeList.add(new ModelBox(barrels, 79, 26, -7.4641F, -6.5F, -26.5F, 2, 2, 14, 0.0F, false));
        barrels.cubeList.add(new ModelBox(barrels, 64, 13, -7.4641F, -6.5F, 6.5F, 1, 2, 8, 0.0F, false));
        barrels.cubeList.add(new ModelBox(barrels, 64, 13, -1.0F, -6.5F, 6.5F, 2, 2, 8, 0.0F, false));
        barrels.cubeList.add(new ModelBox(barrels, 64, 13, 6.4641F, -6.5F, 6.5F, 1, 2, 8, 0.0F, false));
        barrels.cubeList.add(new ModelBox(barrels, 79, 26, -4.7321F, -3.7679F, -26.5F, 2, 2, 14, 0.0F, true));
        barrels.cubeList.add(new ModelBox(barrels, 79, 26, 2.7321F, -3.7679F, -26.5F, 2, 2, 14, 0.0F, false));
        barrels.cubeList.add(new ModelBox(barrels, 79, 26, -2.0F, -6.5F, -26.5F, 2, 2, 14, 0.0F, true));
        barrels.cubeList.add(new ModelBox(barrels, 79, 26, 0.0F, -6.5F, -26.5F, 2, 2, 14, 0.0F, false));
        barrels.cubeList.add(new ModelBox(barrels, 70, 11, 0.0F, -6.5F, -12.5F, 2, 2, 19, 0.0F, false));
        barrels.cubeList.add(new ModelBox(barrels, 70, 11, -2.0F, -6.5F, -12.5F, 2, 2, 19, 0.0F, true));
        barrels.cubeList.add(new ModelBox(barrels, 70, 11, -4.7321F, -3.7679F, -12.5F, 2, 2, 19, 0.0F, true));
        barrels.cubeList.add(new ModelBox(barrels, 70, 11, 2.7321F, -3.7679F, -12.5F, 2, 2, 19, 0.0F, false));
        barrels.cubeList.add(new ModelBox(barrels, 64, 13, -4.7321F, -2.7679F, 6.5F, 2, 1, 8, 0.0F, true));
        barrels.cubeList.add(new ModelBox(barrels, 64, 13, 2.7321F, -2.7679F, 6.5F, 2, 1, 8, 0.0F, true));

        bone4 = new ModelRenderer(this);
        bone4.setRotationPoint(0.0F, 1.5F, -10.0F);
        barrels.addChild(bone4);
        setRotationAngle(bone4, -0.1745F, 0.0F, 0.0F);
        bone4.cubeList.add(new ModelBox(bone4, 141, 158, -4.5F, -1.4493F, -3.3643F, 9, 2, 6, 0.0F, true));
        bone4.cubeList.add(new ModelBox(bone4, 141, 158, -4.5F, -0.4493F, -9.3643F, 9, 1, 6, 0.0F, true));

        bone2 = new ModelRenderer(this);
        bone2.setRotationPoint(0.0F, -4.5F, 3.5F);
        barrels.addChild(bone2);
        setRotationAngle(bone2, 0.0F, 0.0F, 0.5236F);
        bone2.cubeList.add(new ModelBox(bone2, 70, 11, -1.0F, -3.7321F, -16.0F, 2, 2, 19, 0.0F, false));
        bone2.cubeList.add(new ModelBox(bone2, 79, 26, -1.0F, -3.7321F, -30.0F, 2, 2, 14, 0.0F, false));
        bone2.cubeList.add(new ModelBox(bone2, 70, 11, -7.4641F, 0.0F, -16.0F, 2, 2, 19, 0.0F, false));
        bone2.cubeList.add(new ModelBox(bone2, 79, 26, -7.4641F, 0.0F, -30.0F, 2, 2, 14, 0.0F, false));
        bone2.cubeList.add(new ModelBox(bone2, 64, 13, -7.4641F, 0.0F, 3.0F, 1, 2, 8, 0.0F, false));
        bone2.cubeList.add(new ModelBox(bone2, 64, 13, -1.0F, -3.7321F, 3.0F, 1, 2, 8, 0.0F, false));
        bone2.cubeList.add(new ModelBox(bone2, 64, 13, -1.0F, 0.0F, 3.0F, 1, 2, 8, 0.0F, false));
        bone2.cubeList.add(new ModelBox(bone2, 64, 13, 5.4641F, -3.7321F, 3.0F, 1, 2, 8, 0.0F, false));
        bone2.cubeList.add(new ModelBox(bone2, 70, 11, 1.7321F, -1.0F, -16.0F, 2, 2, 19, 0.0F, false));
        bone2.cubeList.add(new ModelBox(bone2, 79, 26, 1.7321F, -1.0F, -30.0F, 2, 2, 14, 0.0F, false));
        bone2.cubeList.add(new ModelBox(bone2, 70, 11, -4.7321F, 2.7321F, -16.0F, 2, 2, 19, 0.0F, false));
        bone2.cubeList.add(new ModelBox(bone2, 79, 26, -4.7321F, 2.7321F, -30.0F, 2, 2, 14, 0.0F, false));
        bone2.cubeList.add(new ModelBox(bone2, 64, 13, -4.7321F, 3.7321F, 3.0F, 2, 1, 8, 0.0F, false));
        bone2.cubeList.add(new ModelBox(bone2, 64, 13, 1.7321F, 0.0F, 3.0F, 2, 1, 8, 0.0F, false));
        bone2.cubeList.add(new ModelBox(bone2, 70, 11, -4.7321F, -2.7321F, -16.0F, 2, 2, 19, 0.0F, false));
        bone2.cubeList.add(new ModelBox(bone2, 79, 26, -4.7321F, -2.7321F, -30.0F, 2, 2, 14, 0.0F, false));
        bone2.cubeList.add(new ModelBox(bone2, 64, 13, -4.7321F, -2.7321F, 3.0F, 2, 1, 8, 0.0F, false));
        bone2.cubeList.add(new ModelBox(bone2, 64, 13, 1.7321F, -6.4641F, 3.0F, 2, 1, 8, 0.0F, false));
        bone2.cubeList.add(new ModelBox(bone2, 70, 11, 1.7321F, -6.4641F, -16.0F, 2, 2, 19, 0.0F, false));
        bone2.cubeList.add(new ModelBox(bone2, 79, 26, 1.7321F, -6.4641F, -30.0F, 2, 2, 14, 0.0F, false));
        bone2.cubeList.add(new ModelBox(bone2, 70, 11, -2.0F, 0.0F, -16.0F, 2, 2, 19, 0.0F, false));
        bone2.cubeList.add(new ModelBox(bone2, 79, 26, -2.0F, 0.0F, -30.0F, 2, 2, 14, 0.0F, false));
        bone2.cubeList.add(new ModelBox(bone2, 70, 11, 4.4641F, -3.7321F, -16.0F, 2, 2, 19, 0.0F, false));
        bone2.cubeList.add(new ModelBox(bone2, 79, 26, 4.4641F, -3.7321F, -30.0F, 2, 2, 14, 0.0F, false));

        bone3 = new ModelRenderer(this);
        bone3.setRotationPoint(0.0F, -4.5F, 3.5F);
        barrels.addChild(bone3);
        setRotationAngle(bone3, 0.0F, 0.0F, -0.5236F);
        bone3.cubeList.add(new ModelBox(bone3, 70, 11, -1.0F, -3.7321F, -16.0F, 2, 2, 19, 0.0F, true));
        bone3.cubeList.add(new ModelBox(bone3, 79, 26, -1.0F, -3.7321F, -30.0F, 2, 2, 14, 0.0F, true));
        bone3.cubeList.add(new ModelBox(bone3, 70, 11, 5.4641F, 0.0F, -16.0F, 2, 2, 19, 0.0F, true));
        bone3.cubeList.add(new ModelBox(bone3, 79, 26, 5.4641F, 0.0F, -30.0F, 2, 2, 14, 0.0F, true));
        bone3.cubeList.add(new ModelBox(bone3, 70, 11, 2.7321F, -2.7321F, -16.0F, 2, 2, 19, 0.0F, true));
        bone3.cubeList.add(new ModelBox(bone3, 79, 26, 2.7321F, -2.7321F, -30.0F, 2, 2, 14, 0.0F, true));
        bone3.cubeList.add(new ModelBox(bone3, 70, 11, -3.7321F, -6.4641F, -16.0F, 2, 2, 19, 0.0F, true));
        bone3.cubeList.add(new ModelBox(bone3, 79, 26, -3.7321F, -6.4641F, -30.0F, 2, 2, 14, 0.0F, true));
        bone3.cubeList.add(new ModelBox(bone3, 64, 13, -3.7321F, -6.4641F, 3.0F, 2, 1, 8, 0.0F, true));
        bone3.cubeList.add(new ModelBox(bone3, 64, 13, 2.7321F, -2.7321F, 3.0F, 2, 1, 8, 0.0F, true));
        bone3.cubeList.add(new ModelBox(bone3, 70, 11, -3.7321F, -1.0F, -16.0F, 2, 2, 19, 0.0F, true));
        bone3.cubeList.add(new ModelBox(bone3, 79, 26, -3.7321F, -1.0F, -30.0F, 2, 2, 14, 0.0F, true));
        bone3.cubeList.add(new ModelBox(bone3, 64, 13, -3.7321F, 0.0F, 3.0F, 2, 1, 8, 0.0F, true));
        bone3.cubeList.add(new ModelBox(bone3, 64, 13, 2.7321F, 3.7321F, 3.0F, 2, 1, 8, 0.0F, true));
        bone3.cubeList.add(new ModelBox(bone3, 70, 11, 2.7321F, 2.7321F, -16.0F, 2, 2, 19, 0.0F, true));
        bone3.cubeList.add(new ModelBox(bone3, 79, 26, 2.7321F, 2.7321F, -30.0F, 2, 2, 14, 0.0F, true));
        bone3.cubeList.add(new ModelBox(bone3, 70, 11, 0.0F, 0.0F, -16.0F, 2, 2, 19, 0.0F, true));
        bone3.cubeList.add(new ModelBox(bone3, 79, 26, 0.0F, 0.0F, -30.0F, 2, 2, 14, 0.0F, true));
        bone3.cubeList.add(new ModelBox(bone3, 70, 11, -6.4641F, -3.7321F, -16.0F, 2, 2, 19, 0.0F, true));
        bone3.cubeList.add(new ModelBox(bone3, 79, 26, -6.4641F, -3.7321F, -30.0F, 2, 2, 14, 0.0F, true));
        bone3.cubeList.add(new ModelBox(bone3, 64, 13, -6.4641F, -3.7321F, 3.0F, 1, 2, 8, 0.0F, true));
        bone3.cubeList.add(new ModelBox(bone3, 64, 13, 0.0F, 0.0F, 3.0F, 1, 2, 8, 0.0F, true));
        bone3.cubeList.add(new ModelBox(bone3, 64, 13, 0.0F, -3.7321F, 3.0F, 1, 2, 8, 0.0F, true));
        bone3.cubeList.add(new ModelBox(bone3, 64, 13, 6.4641F, 0.0F, 3.0F, 1, 2, 8, 0.0F, true));

        sawedoff = new ModelRenderer(this);
        sawedoff.setRotationPoint(0.0F, 24.0F, 0.0F);
        sawedoff.cubeList.add(new ModelBox(sawedoff, 64, 13, -5.0F, -4.0F, 11.0F, 10, 7, 3, 0.0F, true));
        sawedoff.cubeList.add(new ModelBox(sawedoff, 64, 13, 4.984F, -3.488F, 11.0F, 1, 5, 2, 0.0F, true));
        sawedoff.cubeList.add(new ModelBox(sawedoff, 64, 13, -5.984F, -3.488F, 11.0F, 1, 5, 2, 0.0F, false));
        sawedoff.cubeList.add(new ModelBox(sawedoff, 64, 13, 5.504F, -3.016F, 11.0F, 1, 4, 1, 0.0F, true));
        sawedoff.cubeList.add(new ModelBox(sawedoff, 64, 13, -6.504F, -3.016F, 11.0F, 1, 4, 1, 0.0F, false));
        sawedoff.cubeList.add(new ModelBox(sawedoff, 64, 13, -5.0F, 3.0F, -1.0F, 10, 3, 15, 0.0F, true));
        sawedoff.cubeList.add(new ModelBox(sawedoff, 64, 13, 2.0F, 3.0F, -5.0F, 3, 3, 4, 0.0F, true));
        sawedoff.cubeList.add(new ModelBox(sawedoff, 64, 13, -5.0F, 3.0F, -5.0F, 3, 3, 4, 0.0F, true));
        sawedoff.cubeList.add(new ModelBox(sawedoff, 64, 13, -2.0F, -4.0F, 14.0F, 4, 1, 6, 0.0F, true));
        sawedoff.cubeList.add(new ModelBox(sawedoff, 64, 13, -2.0F, -5.0F, 11.096F, 4, 1, 2, 0.0F, true));
        sawedoff.cubeList.add(new ModelBox(sawedoff, 64, 13, 1.0F, -6.0F, 11.096F, 1, 1, 2, 0.0F, true));
        sawedoff.cubeList.add(new ModelBox(sawedoff, 64, 13, -2.0F, -6.0F, 11.096F, 1, 1, 2, 0.0F, false));
        sawedoff.cubeList.add(new ModelBox(sawedoff, 64, 13, -5.0F, 2.0F, 14.0F, 10, 4, 7, 0.0F, true));

        bone12 = new ModelRenderer(this);
        bone12.setRotationPoint(-2.0F, -1.0F, 0.0F);
        sawedoff.addChild(bone12);
        setRotationAngle(bone12, -0.0524F, 0.0F, 0.0F);
        bone12.cubeList.add(new ModelBox(bone12, 64, 13, 0.5F, -4.0426F, 19.8156F, 3, 2, 6, 0.0F, true));

        bone13 = new ModelRenderer(this);
        bone13.setRotationPoint(0.0F, -8.0F, 0.0F);
        bone12.addChild(bone13);
        setRotationAngle(bone13, -0.1745F, 0.0F, 0.0F);
        bone13.cubeList.add(new ModelBox(bone13, 64, 13, 1.0F, -0.5856F, 26.1106F, 2, 2, 6, 0.0F, true));

        bone14 = new ModelRenderer(this);
        bone14.setRotationPoint(1.0F, 0.0F, 0.0F);
        bone13.addChild(bone14);
        setRotationAngle(bone14, -0.7854F, 0.0F, 0.0F);
        bone14.cubeList.add(new ModelBox(bone14, 64, 13, 0.0F, -23.9645F, 21.3371F, 2, 2, 2, 0.0F, true));

        bone6 = new ModelRenderer(this);
        bone6.setRotationPoint(-5.0F, 0.0F, 0.0F);
        sawedoff.addChild(bone6);
        setRotationAngle(bone6, -0.1745F, 0.0F, 0.0F);
        bone6.cubeList.add(new ModelBox(bone6, 64, 13, 0.0F, -6.7585F, 17.53F, 10, 9, 5, 0.0F, true));
        bone6.cubeList.add(new ModelBox(bone6, 136, 82, 1.0F, -6.7585F, 22.53F, 8, 9, 7, 0.0F, true));
        bone6.cubeList.add(new ModelBox(bone6, 136, 82, 0.0F, -5.7585F, 22.53F, 10, 7, 7, 0.0F, true));
        bone6.cubeList.add(new ModelBox(bone6, 136, 82, 1.0F, -6.7585F, 29.53F, 8, 9, 6, 0.0F, true));
        bone6.cubeList.add(new ModelBox(bone6, 136, 82, 0.0F, -5.7585F, 29.53F, 10, 7, 6, 0.0F, true));

        bone7 = new ModelRenderer(this);
        bone7.setRotationPoint(5.0F, -11.2585F, 32.53F);
        bone6.addChild(bone7);
        setRotationAngle(bone7, 0.0F, 0.0F, -0.7854F);
        bone7.cubeList.add(new ModelBox(bone7, 136, 82, -1.3536F, 6.4246F, -10.0F, 1, 1, 13, 0.0F, true));
        bone7.cubeList.add(new ModelBox(bone7, 136, 82, -6.3033F, 11.3744F, -10.0F, 1, 1, 13, 0.0F, true));
        bone7.cubeList.add(new ModelBox(bone7, 136, 82, -6.7175F, 11.3744F, -10.0F, 1, 1, 13, 0.0F, true));
        bone7.cubeList.add(new ModelBox(bone7, 136, 82, -1.3536F, 6.0104F, -10.0F, 1, 1, 13, 0.0F, true));
        bone7.cubeList.add(new ModelBox(bone7, 146, 73, -7.0104F, 0.3536F, -10.0F, 1, 1, 13, 0.0F, true));
        bone7.cubeList.add(new ModelBox(bone7, 146, 73, -7.4246F, 0.3536F, -10.0F, 1, 1, 13, 0.0F, true));
        bone7.cubeList.add(new ModelBox(bone7, 136, 82, -12.3744F, 5.3033F, -10.0F, 1, 1, 13, 0.0F, true));
        bone7.cubeList.add(new ModelBox(bone7, 136, 82, -12.3744F, 5.7175F, -10.0F, 1, 1, 13, 0.0F, true));

        bone8 = new ModelRenderer(this);
        bone8.setRotationPoint(-5.0F, 0.0F, 18.0F);
        sawedoff.addChild(bone8);
        setRotationAngle(bone8, -0.6981F, 0.0F, 0.0F);
        bone8.cubeList.add(new ModelBox(bone8, 136, 82, 1.0F, -12.0479F, 13.6018F, 8, 9, 7, 0.0F, true));
        bone8.cubeList.add(new ModelBox(bone8, 136, 82, 0.0F, -11.0479F, 13.6018F, 10, 7, 7, 0.0F, true));
        bone8.cubeList.add(new ModelBox(bone8, 136, 82, 1.0F, -12.0479F, 20.6018F, 8, 9, 3, 0.0F, true));
        bone8.cubeList.add(new ModelBox(bone8, 136, 82, 0.0F, -11.0479F, 20.6018F, 10, 7, 3, 0.0F, true));

        bone9 = new ModelRenderer(this);
        bone9.setRotationPoint(5.0F, -11.2585F, 32.53F);
        bone8.addChild(bone9);
        setRotationAngle(bone9, 0.0F, 0.0F, -0.7854F);
        bone9.cubeList.add(new ModelBox(bone9, 136, 82, 2.3866F, 2.6845F, -18.9282F, 1, 1, 10, 0.0F, true));
        bone9.cubeList.add(new ModelBox(bone9, 136, 82, -2.5632F, 7.6342F, -18.9282F, 1, 1, 10, 0.0F, true));
        bone9.cubeList.add(new ModelBox(bone9, 136, 82, -2.9774F, 7.6342F, -18.9282F, 1, 1, 10, 0.0F, true));
        bone9.cubeList.add(new ModelBox(bone9, 136, 82, 2.3866F, 2.2703F, -18.9282F, 1, 1, 10, 0.0F, true));
        bone9.cubeList.add(new ModelBox(bone9, 146, 73, -3.2703F, -3.3866F, -18.9282F, 1, 1, 10, 0.0F, true));
        bone9.cubeList.add(new ModelBox(bone9, 146, 73, -3.6845F, -3.3866F, -18.9282F, 1, 1, 10, 0.0F, true));
        bone9.cubeList.add(new ModelBox(bone9, 136, 82, -8.6342F, 1.5632F, -18.9282F, 1, 1, 10, 0.0F, true));
        bone9.cubeList.add(new ModelBox(bone9, 136, 82, -8.6342F, 1.9774F, -18.9282F, 1, 1, 10, 0.0F, true));

        bone10 = new ModelRenderer(this);
        bone10.setRotationPoint(-5.0F, 0.0F, 17.0F);
        sawedoff.addChild(bone10);
        setRotationAngle(bone10, -1.2217F, 0.0F, 0.0F);
        bone10.cubeList.add(new ModelBox(bone10, 136, 82, 1.0F, -23.1744F, 14.7579F, 8, 9, 7, 0.0F, true));
        bone10.cubeList.add(new ModelBox(bone10, 136, 82, 0.0F, -22.1744F, 14.7579F, 10, 7, 7, 0.0F, true));
        bone10.cubeList.add(new ModelBox(bone10, 136, 82, 1.0F, -23.1744F, 21.7579F, 8, 9, 3, 0.0F, true));
        bone10.cubeList.add(new ModelBox(bone10, 136, 82, 0.0F, -22.1744F, 21.7579F, 10, 7, 3, 0.0F, true));

        bone11 = new ModelRenderer(this);
        bone11.setRotationPoint(5.0F, -11.2585F, 32.53F);
        bone10.addChild(bone11);
        setRotationAngle(bone11, 0.0F, 0.0F, -0.7854F);
        bone11.cubeList.add(new ModelBox(bone11, 136, 82, 10.2542F, -5.1831F, -17.7721F, 1, 1, 10, 0.0F, true));
        bone11.cubeList.add(new ModelBox(bone11, 136, 82, 5.3045F, -0.2334F, -17.7721F, 1, 1, 10, 0.0F, true));
        bone11.cubeList.add(new ModelBox(bone11, 136, 82, 4.8902F, -0.2334F, -17.7721F, 1, 1, 10, 0.0F, true));
        bone11.cubeList.add(new ModelBox(bone11, 136, 82, 10.2542F, -5.5974F, -17.7721F, 1, 1, 10, 0.0F, true));
        bone11.cubeList.add(new ModelBox(bone11, 136, 82, 4.5974F, -11.2542F, -17.7721F, 1, 1, 10, 0.0F, true));
        bone11.cubeList.add(new ModelBox(bone11, 136, 82, 4.1831F, -11.2542F, -17.7721F, 1, 1, 10, 0.0F, true));
        bone11.cubeList.add(new ModelBox(bone11, 136, 82, -0.7666F, -6.3045F, -17.7721F, 1, 1, 10, 0.0F, true));
        bone11.cubeList.add(new ModelBox(bone11, 136, 82, -0.7666F, -5.8902F, -17.7721F, 1, 1, 10, 0.0F, true));

        bone5 = new ModelRenderer(this);
        bone5.setRotationPoint(0.0F, -7.0F, 17.0F);
        sawedoff.addChild(bone5);
        setRotationAngle(bone5, -0.0873F, 0.0F, 0.0F);
        bone5.cubeList.add(new ModelBox(bone5, 64, 13, -5.0F, 3.2501F, -3.2729F, 10, 6, 5, 0.0F, true));

        bullet1 = new ModelRenderer(this);
        bullet1.setRotationPoint(0.0F, 24.0F, -1.0F);
        bullet1.cubeList.add(new ModelBox(bullet1, 0, 511, 0.732F, -3.0F, 9.73F, 6, 4, 2, 0.0F, false));
        bullet1.cubeList.add(new ModelBox(bullet1, 0, 511, 1.732F, -3.0F, 8.73F, 4, 4, 1, 0.0F, false));
        bullet1.cubeList.add(new ModelBox(bullet1, 0, 462, 0.732F, -3.0F, -1.27F, 6, 4, 10, 0.0F, false));
        bullet1.cubeList.add(new ModelBox(bullet1, 0, 511, 1.732F, -4.0F, 9.73F, 4, 1, 2, 0.0F, false));
        bullet1.cubeList.add(new ModelBox(bullet1, 0, 462, 1.732F, -4.0F, -1.27F, 4, 1, 10, 0.0F, false));
        bullet1.cubeList.add(new ModelBox(bullet1, 0, 511, 1.732F, 1.0F, 9.73F, 4, 1, 2, 0.0F, false));
        bullet1.cubeList.add(new ModelBox(bullet1, 0, 462, 1.732F, 1.0F, -1.27F, 4, 1, 10, 0.0F, false));

        bullet2 = new ModelRenderer(this);
        bullet2.setRotationPoint(0.0F, 24.0F, -1.0F);
        bullet2.cubeList.add(new ModelBox(bullet2, 0, 511, -6.732F, -3.0F, 9.73F, 6, 4, 2, 0.0F, true));
        bullet2.cubeList.add(new ModelBox(bullet2, 0, 511, -5.732F, -3.0F, 8.73F, 4, 4, 1, 0.0F, true));
        bullet2.cubeList.add(new ModelBox(bullet2, 0, 462, -6.732F, -3.0F, -1.27F, 6, 4, 10, 0.0F, true));
        bullet2.cubeList.add(new ModelBox(bullet2, 0, 511, -5.732F, -4.0F, 9.73F, 4, 1, 2, 0.0F, true));
        bullet2.cubeList.add(new ModelBox(bullet2, 0, 462, -5.732F, -4.0F, -1.27F, 4, 1, 10, 0.0F, true));
        bullet2.cubeList.add(new ModelBox(bullet2, 0, 511, -5.732F, 1.0F, 9.73F, 4, 1, 2, 0.0F, true));
        bullet2.cubeList.add(new ModelBox(bullet2, 0, 462, -5.732F, 1.0F, -1.27F, 4, 1, 10, 0.0F, true));

        addEntry(AnimationElement.MAGAZINE, stack -> barrels);
        addEntry(AnimationElement.BULLET, stack -> bullet1);
        addEntry(AnimationElement.BULLET1, stack -> bullet2);
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }

    @Override
    public void transformModel() {
        ModelTransformationHelper.defaultPistolTransform();
        GlStateManager.scale(0.5, 0.5, 0.5);
        GlStateManager.translate(-0.05, -0.6, -12);
    }

    @Override
    public void renderModel(ItemStack stack) {
        sawedoff.render(1f);
    }
}

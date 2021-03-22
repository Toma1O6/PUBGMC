package dev.toma.pubgmc.client.models.weapons;

import dev.toma.pubgmc.client.animation.AnimationElement;
import dev.toma.pubgmc.client.util.ModelTransformationHelper;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.item.ItemStack;

public class ModelR1895 extends ModelGun {

    private final ModelRenderer drum;
    private final ModelRenderer bone6;
    private final ModelRenderer r1895;
    private final ModelRenderer bone15;
    private final ModelRenderer bone17;
    private final ModelRenderer bone16;
    private final ModelRenderer bone14;
    private final ModelRenderer bone18;
    private final ModelRenderer bone24;
    private final ModelRenderer bone23;
    private final ModelRenderer bone21;
    private final ModelRenderer bone22;
    private final ModelRenderer bone13;
    private final ModelRenderer bone9;
    private final ModelRenderer bone10;
    private final ModelRenderer bone11;
    private final ModelRenderer bone12;
    private final ModelRenderer bone8;
    private final ModelRenderer bone19;
    private final ModelRenderer bone20;
    private final ModelRenderer bone7;
    private final ModelRenderer bone5;
    private final ModelRenderer bone4;
    private final ModelRenderer bone2;
    private final ModelRenderer bone3;

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }

    @Override
    public void transformModel() {
        GlStateManager.scale(0.7f, 0.7f, 0.7f);
        ModelTransformationHelper.defaultPistolTransform();
        GlStateManager.translate(-7.1499963, -16.95, 0.0);
    }

    @Override
    public void renderModel(ItemStack stack) {
        r1895.render(1.0F);
    }

    public ModelR1895() {
        textureWidth = 512;
        textureHeight = 512;

        drum = new ModelRenderer(this);
        drum.setRotationPoint(0.928F, 21.416F, -4.016F);
        drum.cubeList.add(new ModelBox(drum, 18, 164, -1.428F, -1.6348F, -2.288F, 1, 1, 1, 0.0F, false));
        drum.cubeList.add(new ModelBox(drum, 18, 164, -1.928F, -3.6348F, -1.76F, 2, 2, 7, 0.0F, false));
        drum.cubeList.add(new ModelBox(drum, 76, 19, -3.428F, -3.6348F, -1.0083F, 5, 2, 7, 0.0F, false));
        drum.cubeList.add(new ModelBox(drum, 76, 19, -1.928F, -5.1347F, -1.0083F, 2, 5, 7, 0.0F, false));

        bone6 = new ModelRenderer(this);
        bone6.setRotationPoint(-0.928F, -2.6348F, 2.0277F);
        drum.addChild(bone6);
        setRotationAngle(bone6, 0.0F, 0.0F, -0.7854F);
        bone6.cubeList.add(new ModelBox(bone6, 76, 19, -2.5F, -1.0F, -3.036F, 5, 2, 7, 0.0F, false));
        bone6.cubeList.add(new ModelBox(bone6, 76, 19, -1.0F, -2.5F, -3.036F, 2, 5, 7, 0.0F, false));

        r1895 = new ModelRenderer(this);
        r1895.setRotationPoint(0.0F, 24.0F, 0.0F);
        r1895.cubeList.add(new ModelBox(r1895, 85, 27, -2.0F, -2.0F, 0.0F, 4, 2, 2, 0.0F, false));
        r1895.cubeList.add(new ModelBox(r1895, 85, 27, -2.0F, -9.0F, 2.0F, 4, 9, 2, 0.0F, false));
        r1895.cubeList.add(new ModelBox(r1895, 85, 27, -2.001F, -5.0F, 4.0F, 4, 5, 3, 0.0F, false));
        r1895.cubeList.add(new ModelBox(r1895, 85, 27, -1.2929F, -5.7071F, 4.0F, 2, 1, 3, 0.0F, false));
        r1895.cubeList.add(new ModelBox(r1895, 41, 44, -0.5F, -6.9493F, 4.1953F, 1, 1, 2, 0.0F, false));
        r1895.cubeList.add(new ModelBox(r1895, 41, 44, -0.5F, -8.3829F, 5.6211F, 1, 1, 1, 0.0F, false));
        r1895.cubeList.add(new ModelBox(r1895, 41, 44, -0.5F, -8.9493F, 5.1953F, 1, 2, 1, 0.0F, false));
        r1895.cubeList.add(new ModelBox(r1895, 85, 27, 0.2929F, -5.7071F, 4.0F, 1, 1, 3, 0.0F, true));
        r1895.cubeList.add(new ModelBox(r1895, 85, 27, -2.0F, -10.0F, 3.0F, 4, 1, 1, 0.0F, false));
        r1895.cubeList.add(new ModelBox(r1895, 85, 27, -1.2773F, -10.668F, 3.2305F, 1, 1, 1, 0.0F, true));
        r1895.cubeList.add(new ModelBox(r1895, 85, 27, -0.5F, -10.2266F, 3.2305F, 1, 1, 1, 0.0F, true));
        r1895.cubeList.add(new ModelBox(r1895, 85, 27, 0.2773F, -10.668F, 3.2305F, 1, 1, 1, 0.0F, false));
        r1895.cubeList.add(new ModelBox(r1895, 85, 27, -1.2929F, -10.0F, 3.7071F, 2, 5, 1, 0.0F, false));
        r1895.cubeList.add(new ModelBox(r1895, 85, 27, 0.2929F, -10.0F, 3.7071F, 1, 5, 1, 0.0F, true));
        r1895.cubeList.add(new ModelBox(r1895, 85, 27, -1.5F, -2.0F, -6.0F, 3, 2, 6, 0.0F, false));
        r1895.cubeList.add(new ModelBox(r1895, 85, 27, -1.0F, 0.0F, -4.2578F, 2, 3, 1, 0.0F, false));
        r1895.cubeList.add(new ModelBox(r1895, 85, 27, -1.0F, 0.0F, 1.4742F, 2, 3, 1, 0.0F, false));
        r1895.cubeList.add(new ModelBox(r1895, 16, 86, -0.5F, -0.6055F, -0.5258F, 1, 2, 1, 0.0F, false));
        r1895.cubeList.add(new ModelBox(r1895, 85, 27, -1.0F, 3.366F, -2.8918F, 2, 1, 4, 0.0F, false));
        r1895.cubeList.add(new ModelBox(r1895, 85, 27, -1.5F, -5.0F, -6.7656F, 3, 4, 1, 0.0F, false));
        r1895.cubeList.add(new ModelBox(r1895, 85, 27, -2.0F, -8.5234F, -6.7656F, 4, 1, 1, 0.0F, false));
        r1895.cubeList.add(new ModelBox(r1895, 85, 27, -2.0F, -9.0544F, -6.0889F, 4, 1, 9, 0.0F, false));
        r1895.cubeList.add(new ModelBox(r1895, 85, 27, -2.0F, -8.0F, -8.7656F, 4, 3, 3, 0.0F, false));
        r1895.cubeList.add(new ModelBox(r1895, 85, 27, -1.0F, -7.7617F, -21.7656F, 2, 2, 14, 0.0F, false));
        r1895.cubeList.add(new ModelBox(r1895, 85, 27, -1.0F, -5.7617F, -10.7656F, 2, 2, 4, 0.0F, false));
        r1895.cubeList.add(new ModelBox(r1895, 85, 27, -0.5F, -5.2617F, -13.7656F, 1, 1, 3, 0.0F, false));
        r1895.cubeList.add(new ModelBox(r1895, 85, 27, -0.5F, -6.2617F, -11.6523F, 1, 1, 1, 0.0F, false));
        r1895.cubeList.add(new ModelBox(r1895, 85, 27, -0.7F, -5.3617F, -15.7656F, 1, 1, 2, 0.0F, false));
        r1895.cubeList.add(new ModelBox(r1895, 85, 27, -0.3F, -5.3617F, -15.7656F, 1, 1, 2, 0.0F, true));
        r1895.cubeList.add(new ModelBox(r1895, 85, 27, -0.7F, -5.1617F, -15.7656F, 1, 1, 2, 0.0F, false));
        r1895.cubeList.add(new ModelBox(r1895, 85, 27, -0.3F, -5.1617F, -15.7656F, 1, 1, 2, 0.0F, true));
        r1895.cubeList.add(new ModelBox(r1895, 85, 27, -1.0F, -8.7617F, -21.2773F, 2, 1, 3, 0.0F, false));
        r1895.cubeList.add(new ModelBox(r1895, 85, 27, -0.5F, -9.5625F, -21.1523F, 1, 1, 2, 0.0F, false));
        r1895.cubeList.add(new ModelBox(r1895, 85, 27, -0.5F, -9.4285F, -19.9203F, 1, 1, 1, 0.0F, false));

        bone15 = new ModelRenderer(this);
        bone15.setRotationPoint(1.3281F, 5.3477F, 3.6133F);
        r1895.addChild(bone15);
        setRotationAngle(bone15, 0.3491F, 0.0F, 0.0F);
        bone15.cubeList.add(new ModelBox(bone15, 162, 169, -2.0F, -3.0334F, 3.0603F, 3, 5, 6, 0.0F, true));
        bone15.cubeList.add(new ModelBox(bone15, 86, 29, -3.3281F, -6.342F, 3.7009F, 4, 5, 5, 0.0F, true));
        bone15.cubeList.add(new ModelBox(bone15, 162, 169, -2.8281F, -3.6347F, 7.9535F, 3, 1, 1, 0.0F, true));
        bone15.cubeList.add(new ModelBox(bone15, 162, 169, -3.6563F, -3.0334F, 3.0603F, 3, 5, 6, 0.0F, false));

        bone17 = new ModelRenderer(this);
        bone17.setRotationPoint(0.0F, 0.0F, 0.0F);
        bone15.addChild(bone17);
        setRotationAngle(bone17, -0.2618F, 0.0F, 0.0F);
        bone17.cubeList.add(new ModelBox(bone17, 162, 169, -3.6563F, -0.4454F, 3.2606F, 3, 5, 6, 0.0F, false));
        bone17.cubeList.add(new ModelBox(bone17, 162, 169, -2.0F, -0.4454F, 3.2606F, 3, 5, 6, 0.0F, true));

        bone16 = new ModelRenderer(this);
        bone16.setRotationPoint(-0.3281F, 1.3242F, 1.6875F);
        bone15.addChild(bone16);
        setRotationAngle(bone16, 0.6981F, 0.0F, 0.0F);
        bone16.cubeList.add(new ModelBox(bone16, 86, 29, -3.0F, -4.9442F, 4.1293F, 4, 3, 2, 0.0F, true));

        bone14 = new ModelRenderer(this);
        bone14.setRotationPoint(-0.5F, 5.5391F, -2.7891F);
        r1895.addChild(bone14);
        setRotationAngle(bone14, 0.4363F, 0.0F, 0.0F);
        bone14.cubeList.add(new ModelBox(bone14, 162, 169, -1.0F, -3.3082F, 13.1196F, 3, 3, 2, 0.0F, false));

        bone18 = new ModelRenderer(this);
        bone18.setRotationPoint(0.0F, 0.0F, 0.0F);
        bone14.addChild(bone18);
        setRotationAngle(bone18, 0.7854F, 0.0F, 0.0F);
        bone18.cubeList.add(new ModelBox(bone18, 162, 169, -1.0F, 5.3519F, 11.0304F, 3, 3, 2, 0.0F, false));
        bone18.cubeList.add(new ModelBox(bone18, 86, 29, -1.5F, 5.5395F, 10.8207F, 4, 3, 2, 0.0F, false));

        bone24 = new ModelRenderer(this);
        bone24.setRotationPoint(0.0F, -7.9493F, 5.6953F);
        r1895.addChild(bone24);
        setRotationAngle(bone24, 0.3491F, 0.0F, 0.0F);
        bone24.cubeList.add(new ModelBox(bone24, 41, 44, -0.5F, -1.7687F, -0.1881F, 1, 1, 1, 0.0F, false));
        bone24.cubeList.add(new ModelBox(bone24, 41, 44, -0.5F, -0.1284F, -0.2614F, 1, 1, 2, 0.0F, false));
        bone24.cubeList.add(new ModelBox(bone24, 41, 44, -0.5F, 1.0504F, -2.2142F, 1, 1, 2, 0.0F, false));

        bone23 = new ModelRenderer(this);
        bone23.setRotationPoint(0.0F, -7.9493F, 5.6953F);
        r1895.addChild(bone23);
        setRotationAngle(bone23, -0.1745F, 0.0F, 0.0F);
        bone23.cubeList.add(new ModelBox(bone23, 41, 44, -0.5F, -0.898F, -0.6661F, 1, 2, 1, 0.0F, false));
        bone23.cubeList.add(new ModelBox(bone23, 41, 44, -0.5F, 0.5825F, -0.6661F, 1, 1, 1, 0.0F, false));

        bone21 = new ModelRenderer(this);
        bone21.setRotationPoint(1.0F, 0.7617F, -0.4805F);
        r1895.addChild(bone21);
        setRotationAngle(bone21, -0.7854F, 0.0F, 0.0F);
        bone21.cubeList.add(new ModelBox(bone21, 85, 27, -2.2929F, -8.9112F, -1.6347F, 2, 1, 2, 0.0F, false));
        bone21.cubeList.add(new ModelBox(bone21, 85, 27, -0.7071F, -8.9112F, -1.6347F, 1, 1, 2, 0.0F, true));
        bone21.cubeList.add(new ModelBox(bone21, 85, 27, 0.0F, -8.2041F, -1.8964F, 1, 1, 2, 0.0F, true));
        bone21.cubeList.add(new ModelBox(bone21, 85, 27, -3.0F, -8.2041F, -1.8964F, 1, 1, 2, 0.0F, false));

        bone22 = new ModelRenderer(this);
        bone22.setRotationPoint(0.0F, 0.0F, 0.0F);
        bone21.addChild(bone22);
        setRotationAngle(bone22, 0.0F, 0.0F, 0.7854F);
        bone22.cubeList.add(new ModelBox(bone22, 85, 27, -6.0941F, -6.5083F, -2.6347F, 1, 1, 3, 0.0F, true));
        bone22.cubeList.add(new ModelBox(bone22, 85, 27, -7.9225F, -4.6798F, -2.6347F, 1, 1, 3, 0.0F, true));

        bone13 = new ModelRenderer(this);
        bone13.setRotationPoint(-1.0F, 0.0F, 0.0F);
        r1895.addChild(bone13);
        setRotationAngle(bone13, 0.0F, 0.0F, -0.7854F);
        bone13.cubeList.add(new ModelBox(bone13, 85, 27, 4.6569F, -2.4142F, 4.0F, 1, 1, 3, 0.0F, false));
        bone13.cubeList.add(new ModelBox(bone13, 85, 27, 2.8284F, -4.2426F, 4.0F, 1, 1, 3, 0.0F, false));

        bone9 = new ModelRenderer(this);
        bone9.setRotationPoint(-1.0F, 0.0F, 0.0F);
        r1895.addChild(bone9);
        setRotationAngle(bone9, 0.0F, 0.7854F, 0.0F);
        bone9.cubeList.add(new ModelBox(bone9, 85, 27, -1.7071F, -10.0F, 3.9497F, 1, 5, 1, 0.0F, false));

        bone10 = new ModelRenderer(this);
        bone10.setRotationPoint(1.0F, 0.0F, 0.0F);
        r1895.addChild(bone10);
        setRotationAngle(bone10, 0.0F, -0.7854F, 0.0F);
        bone10.cubeList.add(new ModelBox(bone10, 85, 27, 0.7071F, -10.0F, 3.9497F, 1, 5, 1, 0.0F, true));

        bone11 = new ModelRenderer(this);
        bone11.setRotationPoint(0.7773F, -11.168F, 3.7305F);
        r1895.addChild(bone11);
        setRotationAngle(bone11, 0.0F, 0.0F, -0.3491F);
        bone11.cubeList.add(new ModelBox(bone11, 85, 27, -0.7012F, 0.6409F, -0.5F, 1, 1, 1, 0.0F, false));

        bone12 = new ModelRenderer(this);
        bone12.setRotationPoint(-0.7773F, -11.168F, 3.7305F);
        r1895.addChild(bone12);
        setRotationAngle(bone12, 0.0F, 0.0F, 0.3491F);
        bone12.cubeList.add(new ModelBox(bone12, 85, 27, -0.2988F, 0.6409F, -0.5F, 1, 1, 1, 0.0F, true));

        bone8 = new ModelRenderer(this);
        bone8.setRotationPoint(-1.0F, 0.0F, 0.0F);
        r1895.addChild(bone8);
        setRotationAngle(bone8, 0.3491F, 0.0F, 0.0F);
        bone8.cubeList.add(new ModelBox(bone8, 85, 27, -1.0F, -8.3709F, 5.2393F, 4, 1, 1, 0.0F, false));

        bone19 = new ModelRenderer(this);
        bone19.setRotationPoint(0.0F, 1.0F, -3.7578F);
        r1895.addChild(bone19);
        setRotationAngle(bone19, 0.5236F, 0.0F, 0.0F);
        bone19.cubeList.add(new ModelBox(bone19, 85, 27, -1.0F, 1.4821F, -1.433F, 2, 1, 1, 0.0F, false));
        bone19.cubeList.add(new ModelBox(bone19, 85, 27, -1.0F, 4.3481F, 2.5311F, 2, 1, 1, 0.0F, false));

        bone20 = new ModelRenderer(this);
        bone20.setRotationPoint(0.0F, 1.0F, -3.7578F);
        r1895.addChild(bone20);
        setRotationAngle(bone20, 1.0472F, 0.0F, 0.0F);
        bone20.cubeList.add(new ModelBox(bone20, 85, 27, -1.0F, 1.433F, -2.4821F, 2, 1, 1, 0.0F, false));
        bone20.cubeList.add(new ModelBox(bone20, 85, 27, -1.0F, 5.3971F, 0.384F, 2, 1, 1, 0.0F, false));
        bone20.cubeList.add(new ModelBox(bone20, 16, 86, -0.5F, 2.8623F, 0.7744F, 1, 1, 1, 0.0F, false));

        bone7 = new ModelRenderer(this);
        bone7.setRotationPoint(0.0F, -10.0625F, -17.6523F);
        r1895.addChild(bone7);
        setRotationAngle(bone7, -0.5236F, 0.0F, 0.0F);
        bone7.cubeList.add(new ModelBox(bone7, 85, 27, -0.5F, 1.183F, -2.7811F, 1, 1, 2, 0.0F, false));

        bone5 = new ModelRenderer(this);
        bone5.setRotationPoint(0.0F, -1.5F, -6.2656F);
        r1895.addChild(bone5);
        setRotationAngle(bone5, 0.1745F, 0.0F, 0.0F);
        bone5.cubeList.add(new ModelBox(bone5, 85, 27, -1.5F, -3.2958F, -0.5725F, 3, 4, 1, 0.0F, false));
        bone5.cubeList.add(new ModelBox(bone5, 85, 27, -2.0F, -7.4089F, -0.5142F, 4, 1, 2, 0.0F, false));

        bone4 = new ModelRenderer(this);
        bone4.setRotationPoint(0.0F, 0.0F, 0.0F);
        r1895.addChild(bone4);
        setRotationAngle(bone4, -0.7854F, 0.0F, 0.0F);
        bone4.cubeList.add(new ModelBox(bone4, 85, 27, -1.5F, 0.5219F, -9.6889F, 3, 2, 2, 0.0F, false));
        bone4.cubeList.add(new ModelBox(bone4, 85, 27, -2.0F, -0.4586F, -11.8551F, 4, 1, 1, 0.0F, false));
        bone4.cubeList.add(new ModelBox(bone4, 85, 27, -2.0F, -8.2861F, -5.3724F, 4, 1, 1, 0.0F, false));
        bone4.cubeList.add(new ModelBox(bone4, 85, 27, -1.5F, 3.2426F, -5.2426F, 3, 1, 1, 0.0F, false));

        bone2 = new ModelRenderer(this);
        bone2.setRotationPoint(0.0F, 0.0F, 0.0F);
        r1895.addChild(bone2);
        setRotationAngle(bone2, 0.0F, 0.1745F, 0.0F);
        bone2.cubeList.add(new ModelBox(bone2, 85, 27, 0.9696F, -2.0F, -2.6527F, 1, 2, 3, 0.0F, false));

        bone3 = new ModelRenderer(this);
        bone3.setRotationPoint(0.0F, 0.0F, 0.0F);
        r1895.addChild(bone3);
        setRotationAngle(bone3, 0.0F, -0.1745F, 0.0F);
        bone3.cubeList.add(new ModelBox(bone3, 85, 27, -1.9696F, -2.0F, -2.6527F, 1, 2, 3, 0.0F, true));

        addEntry(AnimationElement.MAGAZINE, stack -> drum);
    }
}

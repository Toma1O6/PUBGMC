package dev.toma.pubgmc.client.models.weapons;

import dev.toma.pubgmc.client.animation.AnimationElement;
import dev.toma.pubgmc.client.util.ModelTransformationHelper;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.item.ItemStack;

public class ModelR45 extends ModelGun {

    private final ModelRenderer drum;
    private final ModelRenderer bone6;
    private final ModelRenderer hammer;
    private final ModelRenderer bone24;
    private final ModelRenderer r45;
    private final ModelRenderer bone15;
    private final ModelRenderer bone17;
    private final ModelRenderer bone16;
    private final ModelRenderer bone14;
    private final ModelRenderer bone18;
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
    private final ModelRenderer bone27;
    private final ModelRenderer bone26;
    private final ModelRenderer bone25;
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

    private void renderR45(ItemStack stack) {
        GlStateManager.pushMatrix();
        {
            ModelTransformationHelper.defaultPistolTransform();
            GlStateManager.translate(0.15, -9.349995, -7.0);
        }
        GlStateManager.popMatrix();

//        renderRedDot(0.475, -7.25, -8, 0.8f, stack);
    }

    @Override
    public void renderModel(ItemStack stack) {
        r45.render(1f);
    }

    public ModelR45() {
        textureWidth = 512;
        textureHeight = 512;

        drum = new ModelRenderer(this);
        drum.setRotationPoint(1.0F, 21.056F, -5.0F);
        drum.cubeList.add(new ModelBox(drum, 24, 134, -1.5F, -1.2747F, -0.344F, 1, 2, 1, 0.0F, false));
        drum.cubeList.add(new ModelBox(drum, 44, 161, -2.0F, -3.2747F, -0.288F, 2, 2, 7, 0.0F, false));
        drum.cubeList.add(new ModelBox(drum, 76, 19, -3.5F, -3.2747F, 0.9117F, 5, 2, 6, 0.0F, false));
        drum.cubeList.add(new ModelBox(drum, 76, 19, -2.0F, -4.7747F, 0.9117F, 2, 5, 6, 0.0F, false));

        bone6 = new ModelRenderer(this);
        bone6.setRotationPoint(-1.0F, -2.2747F, 3.0117F);
        drum.addChild(bone6);
        setRotationAngle(bone6, 0.0F, 0.0F, -0.7854F);
        bone6.cubeList.add(new ModelBox(bone6, 76, 19, -2.5F, -1.0F, -2.1F, 5, 2, 6, 0.0F, false));
        bone6.cubeList.add(new ModelBox(bone6, 76, 19, -1.0F, -2.5F, -2.1F, 2, 5, 6, 0.0F, false));

        hammer = new ModelRenderer(this);
        hammer.setRotationPoint(0.0F, 16.832F, 5.84F);
        setRotationAngle(hammer, -0.0873F, 0.0F, 0.0F);
        hammer.cubeList.add(new ModelBox(hammer, 8, 0, -0.5F, -1.1147F, 0.1037F, 1, 2, 1, 0.0F, false));
        hammer.cubeList.add(new ModelBox(hammer, 40, 13, -0.5F, -0.7727F, -0.836F, 1, 1, 1, 0.0F, false));

        bone24 = new ModelRenderer(this);
        bone24.setRotationPoint(0.0F, -0.6811F, 0.1779F);
        hammer.addChild(bone24);
        setRotationAngle(bone24, 0.3491F, 0.0F, 0.0F);
        bone24.cubeList.add(new ModelBox(bone24, 8, 0, -0.5F, -0.2513F, 0.4009F, 1, 1, 1, 0.0F, false));
        bone24.cubeList.add(new ModelBox(bone24, 8, 0, -0.5F, -0.4328F, -0.9214F, 1, 1, 1, 0.0F, false));

        r45 = new ModelRenderer(this);
        r45.setRotationPoint(0.0F, 24.0F, 0.0F);
        r45.cubeList.add(new ModelBox(r45, 85, 27, -2.0F, -2.0F, 0.0F, 4, 2, 2, 0.0F, false));
        r45.cubeList.add(new ModelBox(r45, 85, 27, -2.0F, -9.0F, 2.0F, 4, 9, 2, 0.0F, false));
        r45.cubeList.add(new ModelBox(r45, 85, 27, -2.0F, -8.3438F, 4.0F, 4, 3, 1, 0.0F, false));
        r45.cubeList.add(new ModelBox(r45, 85, 27, -2.0F, -6.0F, 4.0F, 4, 6, 3, 0.0F, false));
        r45.cubeList.add(new ModelBox(r45, 85, 27, -0.7071F, -6.285F, 5.9736F, 2, 1, 1, 0.0F, false));
        r45.cubeList.add(new ModelBox(r45, 85, 27, -1.2929F, -6.285F, 5.9736F, 2, 1, 1, 0.0F, true));
        r45.cubeList.add(new ModelBox(r45, 41, 44, -0.5F, -6.9493F, 4.1953F, 1, 1, 2, 0.0F, false));
        r45.cubeList.add(new ModelBox(r45, 85, 27, -2.0F, -2.0F, -6.0F, 4, 2, 6, 0.0F, false));
        r45.cubeList.add(new ModelBox(r45, 16, 86, -0.5F, -0.6055F, 3.0641F, 1, 2, 1, 0.0F, false));
        r45.cubeList.add(new ModelBox(r45, 24, 134, -0.5F, -3.2188F, -5.344F, 1, 2, 1, 0.0F, false));
        r45.cubeList.add(new ModelBox(r45, 85, 27, -2.0F, -5.4142F, -7.4142F, 4, 4, 2, 0.0F, false));
        r45.cubeList.add(new ModelBox(r45, 85, 27, -2.0F, -6.4753F, -7.179F, 4, 1, 2, 0.0F, false));
        r45.cubeList.add(new ModelBox(r45, 85, 27, -1.5F, -9.0544F, -6.0889F, 3, 1, 10, 0.0F, false));
        r45.cubeList.add(new ModelBox(r45, 32, 158, -1.501F, -9.2341F, 1.2627F, 3, 1, 2, 0.0F, false));
        r45.cubeList.add(new ModelBox(r45, 32, 158, -1.4063F, -9.7771F, 1.2627F, 1, 1, 2, 0.0F, true));
        r45.cubeList.add(new ModelBox(r45, 32, 158, 0.4063F, -9.7771F, 1.2627F, 1, 1, 2, 0.0F, false));
        r45.cubeList.add(new ModelBox(r45, 85, 27, -0.5F, -8.3789F, -21.2344F, 1, 1, 16, 0.0F, false));
        r45.cubeList.add(new ModelBox(r45, 26, 162, -0.5F, -9.3789F, -20.6602F, 1, 1, 3, 0.0F, false));
        r45.cubeList.add(new ModelBox(r45, 26, 162, -0.5F, -9.5625F, -20.2852F, 1, 1, 2, 0.0F, false));
        r45.cubeList.add(new ModelBox(r45, 85, 27, -0.5F, -4.2852F, -21.2344F, 1, 1, 15, 0.0F, false));
        r45.cubeList.add(new ModelBox(r45, 85, 27, 0.5176F, -5.576F, -21.2344F, 1, 1, 15, 0.0F, false));
        r45.cubeList.add(new ModelBox(r45, 85, 27, -1.5176F, -5.576F, -21.2344F, 1, 1, 15, 0.0F, true));
        r45.cubeList.add(new ModelBox(r45, 85, 27, -1.0F, -7.5078F, -21.2344F, 2, 1, 15, 0.0F, false));

        bone15 = new ModelRenderer(this);
        bone15.setRotationPoint(1.3281F, 5.3477F, 3.6133F);
        r45.addChild(bone15);
        setRotationAngle(bone15, 0.3491F, 0.0F, 0.0F);
        bone15.cubeList.add(new ModelBox(bone15, 169, 160, -2.0F, -6.0334F, 3.0603F, 3, 6, 6, 0.0F, true));
        bone15.cubeList.add(new ModelBox(bone15, 73, 46, -3.3281F, -6.342F, 3.7009F, 4, 5, 5, 0.0F, true));
        bone15.cubeList.add(new ModelBox(bone15, 73, 46, -1.8281F, -16.836F, -6.1227F, 1, 1, 2, 0.0F, true));
        bone15.cubeList.add(new ModelBox(bone15, 169, 160, -3.6563F, -6.0334F, 3.0603F, 3, 6, 6, 0.0F, false));

        bone17 = new ModelRenderer(this);
        bone17.setRotationPoint(0.0F, 0.0F, 0.0F);
        bone15.addChild(bone17);
        setRotationAngle(bone17, -0.2618F, 0.0F, 0.0F);
        bone17.cubeList.add(new ModelBox(bone17, 169, 160, -3.6563F, -2.3773F, 2.7429F, 3, 7, 6, 0.0F, false));
        bone17.cubeList.add(new ModelBox(bone17, 169, 160, -2.0F, -2.3773F, 2.7429F, 3, 7, 6, 0.0F, true));

        bone16 = new ModelRenderer(this);
        bone16.setRotationPoint(-0.3281F, 1.3242F, 1.6875F);
        bone15.addChild(bone16);
        setRotationAngle(bone16, 0.6981F, 0.0F, 0.0F);


        bone14 = new ModelRenderer(this);
        bone14.setRotationPoint(-0.5F, 5.5391F, -2.7891F);
        r45.addChild(bone14);
        setRotationAngle(bone14, 0.4363F, 0.0F, 0.0F);


        bone18 = new ModelRenderer(this);
        bone18.setRotationPoint(0.0F, 0.0F, 0.0F);
        bone14.addChild(bone18);
        setRotationAngle(bone18, 0.7854F, 0.0F, 0.0F);
        bone18.cubeList.add(new ModelBox(bone18, 89, 40, -1.5F, 5.5395F, 10.8207F, 4, 3, 2, 0.0F, false));

        bone23 = new ModelRenderer(this);
        bone23.setRotationPoint(0.0F, -7.9493F, 5.6953F);
        r45.addChild(bone23);
        setRotationAngle(bone23, -0.1745F, 0.0F, 0.0F);
        bone23.cubeList.add(new ModelBox(bone23, 41, 44, -0.5F, 0.5825F, -0.6661F, 1, 1, 1, 0.0F, false));

        bone21 = new ModelRenderer(this);
        bone21.setRotationPoint(1.0F, 0.7617F, -0.4805F);
        r45.addChild(bone21);
        setRotationAngle(bone21, -0.7854F, 0.0F, 0.0F);
        bone21.cubeList.add(new ModelBox(bone21, 85, 27, -2.2929F, -10.2536F, -2.712F, 2, 1, 3, 0.0F, false));
        bone21.cubeList.add(new ModelBox(bone21, 85, 27, -0.7071F, -10.2536F, -2.712F, 1, 1, 3, 0.0F, true));
        bone21.cubeList.add(new ModelBox(bone21, 85, 27, 0.0F, -9.5465F, -1.9737F, 1, 1, 2, 0.0F, true));
        bone21.cubeList.add(new ModelBox(bone21, 85, 27, -3.0F, -9.5465F, -1.9737F, 1, 1, 2, 0.0F, false));

        bone22 = new ModelRenderer(this);
        bone22.setRotationPoint(0.0F, 0.0F, 0.0F);
        bone21.addChild(bone22);
        setRotationAngle(bone22, 0.0F, 0.0F, 0.7854F);
        bone22.cubeList.add(new ModelBox(bone22, 85, 27, -7.0433F, -7.4575F, -2.712F, 1, 1, 3, 0.0F, true));
        bone22.cubeList.add(new ModelBox(bone22, 85, 27, -8.8717F, -5.6291F, -2.712F, 1, 1, 3, 0.0F, true));

        bone13 = new ModelRenderer(this);
        bone13.setRotationPoint(-1.0F, 0.0F, 0.0F);
        r45.addChild(bone13);
        setRotationAngle(bone13, 0.0F, 0.0F, -0.7854F);


        bone9 = new ModelRenderer(this);
        bone9.setRotationPoint(-1.0F, 0.0F, 0.0F);
        r45.addChild(bone9);
        setRotationAngle(bone9, 0.0F, 0.7854F, 0.0F);


        bone10 = new ModelRenderer(this);
        bone10.setRotationPoint(1.0F, 0.0F, 0.0F);
        r45.addChild(bone10);
        setRotationAngle(bone10, 0.0F, -0.7854F, 0.0F);


        bone11 = new ModelRenderer(this);
        bone11.setRotationPoint(0.7773F, -11.168F, 3.7305F);
        r45.addChild(bone11);
        setRotationAngle(bone11, 0.0F, 0.0F, -0.3491F);


        bone12 = new ModelRenderer(this);
        bone12.setRotationPoint(-0.7773F, -11.168F, 3.7305F);
        r45.addChild(bone12);
        setRotationAngle(bone12, 0.0F, 0.0F, 0.3491F);


        bone8 = new ModelRenderer(this);
        bone8.setRotationPoint(-1.0F, 0.0F, 0.0F);
        r45.addChild(bone8);
        setRotationAngle(bone8, 0.3491F, 0.0F, 0.0F);


        bone19 = new ModelRenderer(this);
        bone19.setRotationPoint(0.0F, 1.0F, -3.7578F);
        r45.addChild(bone19);
        setRotationAngle(bone19, 0.5236F, 0.0F, 0.0F);


        bone20 = new ModelRenderer(this);
        bone20.setRotationPoint(0.0F, 1.0F, -3.7578F);
        r45.addChild(bone20);
        setRotationAngle(bone20, 1.0472F, 0.0F, 0.0F);
        bone20.cubeList.add(new ModelBox(bone20, 16, 86, -0.5F, 5.9712F, 2.5693F, 1, 1, 1, 0.0F, false));

        bone27 = new ModelRenderer(this);
        bone27.setRotationPoint(0.0F, 0.0F, 0.0F);
        r45.addChild(bone27);
        setRotationAngle(bone27, -0.4363F, 0.0F, 0.0F);
        bone27.cubeList.add(new ModelBox(bone27, 85, 27, -2.0F, -2.7736F, -9.0077F, 4, 1, 2, 0.0F, false));
        bone27.cubeList.add(new ModelBox(bone27, 85, 27, -1.5F, -5.7781F, -9.2284F, 3, 3, 2, 0.0F, false));

        bone26 = new ModelRenderer(this);
        bone26.setRotationPoint(1.0F, 0.0F, 0.0F);
        r45.addChild(bone26);
        setRotationAngle(bone26, 0.0F, 0.0F, 0.2618F);
        bone26.cubeList.add(new ModelBox(bone26, 85, 27, -3.6175F, -7.7052F, -21.2344F, 1, 1, 16, 0.0F, true));
        bone26.cubeList.add(new ModelBox(bone26, 85, 27, -3.875F, -6.7344F, -21.2344F, 1, 1, 15, 0.0F, true));
        bone26.cubeList.add(new ModelBox(bone26, 85, 27, -1.6843F, -4.554F, -21.2344F, 1, 1, 15, 0.0F, true));
        bone26.cubeList.add(new ModelBox(bone26, 85, 27, -3.1162F, -3.9024F, -21.2344F, 1, 1, 15, 0.0F, true));
        bone26.cubeList.add(new ModelBox(bone26, 85, 27, -3.875F, -5.7344F, -9.2344F, 1, 1, 3, 0.0F, true));
        bone26.cubeList.add(new ModelBox(bone26, 85, 27, -3.875F, -5.7344F, -18.2344F, 1, 1, 2, 0.0F, true));
        bone26.cubeList.add(new ModelBox(bone26, 85, 27, -3.875F, -5.7344F, -21.2344F, 1, 1, 2, 0.0F, true));
        bone26.cubeList.add(new ModelBox(bone26, 85, 27, -3.875F, -5.7344F, -12.2344F, 1, 1, 2, 0.0F, true));
        bone26.cubeList.add(new ModelBox(bone26, 85, 27, -3.875F, -5.7344F, -15.2344F, 1, 1, 2, 0.0F, true));

        bone25 = new ModelRenderer(this);
        bone25.setRotationPoint(-1.0F, 0.0F, 0.0F);
        r45.addChild(bone25);
        setRotationAngle(bone25, 0.0F, 0.0F, -0.2618F);
        bone25.cubeList.add(new ModelBox(bone25, 85, 27, 2.6175F, -7.7052F, -21.2344F, 1, 1, 16, 0.0F, false));
        bone25.cubeList.add(new ModelBox(bone25, 85, 27, 2.875F, -6.7344F, -21.2344F, 1, 1, 15, 0.0F, false));
        bone25.cubeList.add(new ModelBox(bone25, 85, 27, 0.6843F, -4.554F, -21.2344F, 1, 1, 15, 0.0F, false));
        bone25.cubeList.add(new ModelBox(bone25, 85, 27, 2.1162F, -3.9024F, -21.2344F, 1, 1, 15, 0.0F, false));
        bone25.cubeList.add(new ModelBox(bone25, 85, 27, 2.875F, -5.7344F, -9.2344F, 1, 1, 3, 0.0F, false));
        bone25.cubeList.add(new ModelBox(bone25, 85, 27, 2.875F, -5.7344F, -18.2344F, 1, 1, 2, 0.0F, false));
        bone25.cubeList.add(new ModelBox(bone25, 85, 27, 2.875F, -5.7344F, -21.2344F, 1, 1, 2, 0.0F, false));
        bone25.cubeList.add(new ModelBox(bone25, 85, 27, 2.875F, -5.7344F, -12.2344F, 1, 1, 2, 0.0F, false));
        bone25.cubeList.add(new ModelBox(bone25, 85, 27, 2.875F, -5.7344F, -15.2344F, 1, 1, 2, 0.0F, false));

        bone7 = new ModelRenderer(this);
        bone7.setRotationPoint(0.0F, -10.0625F, -17.6523F);
        r45.addChild(bone7);
        setRotationAngle(bone7, -0.5236F, 0.0F, 0.0F);

        bone5 = new ModelRenderer(this);
        bone5.setRotationPoint(0.0F, -1.5F, -6.2656F);
        r45.addChild(bone5);
        setRotationAngle(bone5, 0.1745F, 0.0F, 0.0F);

        bone4 = new ModelRenderer(this);
        bone4.setRotationPoint(0.0F, 0.0F, 0.0F);
        r45.addChild(bone4);
        setRotationAngle(bone4, -0.7854F, 0.0F, 0.0F);
        bone4.cubeList.add(new ModelBox(bone4, 85, 27, -2.0F, 3.2426F, -6.2426F, 4, 1, 2, 0.0F, false));

        bone2 = new ModelRenderer(this);
        bone2.setRotationPoint(0.0F, 0.0F, 0.0F);
        r45.addChild(bone2);
        setRotationAngle(bone2, 0.0F, 0.1745F, 0.0F);

        bone3 = new ModelRenderer(this);
        bone3.setRotationPoint(0.0F, 0.0F, 0.0F);
        r45.addChild(bone3);
        setRotationAngle(bone3, 0.0F, -0.1745F, 0.0F);

        addEntry(AnimationElement.MAGAZINE, stack -> drum);
        addEntry(AnimationElement.HAMMER, stack -> hammer);
    }
}

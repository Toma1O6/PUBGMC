package dev.toma.pubgmc.client.models.weapons;

import dev.toma.pubgmc.client.animation.AnimationElement;
import dev.toma.pubgmc.client.util.ModelTransformationHelper;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.item.ItemStack;

public class ModelAWM extends ModelGun {

    private final ModelRenderer awm;
    private final ModelRenderer bone10;
    private final ModelRenderer bone33;
    private final ModelRenderer bone28;
    private final ModelRenderer bone30;
    private final ModelRenderer bone31;
    private final ModelRenderer bone32;
    private final ModelRenderer bone11;
    private final ModelRenderer grip;
    private final ModelRenderer bone21;
    private final ModelRenderer bone27;
    private final ModelRenderer bone22;
    private final ModelRenderer bone13;
    private final ModelRenderer bone14;
    private final ModelRenderer bone15;
    private final ModelRenderer bone16;
    private final ModelRenderer bone17;
    private final ModelRenderer bone18;
    private final ModelRenderer bone19;
    private final ModelRenderer bone20;
    private final ModelRenderer bone23;
    private final ModelRenderer bone24;
    private final ModelRenderer bone25;
    private final ModelRenderer bone26;
    private final ModelRenderer magazine;
    private final ModelRenderer bullet;
    private final ModelRenderer bullet2;
    private final ModelRenderer ironsights;
    private final ModelRenderer bone38;
    private final ModelRenderer bone39;
    private final ModelRenderer bone36;
    private final ModelRenderer bone37;
    private final ModelRenderer bolt_case;
    private final ModelRenderer bone;
    private final ModelRenderer bone12;
    private final ModelRenderer bone4;
    private final ModelRenderer bone2;
    private final ModelRenderer bone3;
    private final ModelRenderer bolt;
    private final ModelRenderer catch_;
    private final ModelRenderer bone7;
    private final ModelRenderer bone8;
    private final ModelRenderer bone6;
    private final ModelRenderer rotatingPart3;
    private final ModelRenderer bone5;
    private final ModelRenderer stock;
    private final ModelRenderer bone9;
    private final ModelRenderer bone29;
    private final ModelRenderer bone41;
    private final ModelRenderer bone49;
    private final ModelRenderer bone50;
    private final ModelRenderer bone52;
    private final ModelRenderer bone42;
    private final ModelRenderer bone44;
    private final ModelRenderer bone45;
    private final ModelRenderer bone47;
    private final ModelRenderer bone48;
    private final ModelRenderer bone51;
    private final ModelRenderer bone46;
    private final ModelRenderer bone40;
    private final ModelRenderer bone43;

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }

    @Override
    public void transformModel() {
        ModelTransformationHelper.defaultSRTransform();
        GlStateManager.rotate(-90f, 0f, 1f, 0f);
        GlStateManager.scale(0.9, 0.9, 0.9);
        GlStateManager.translate(-7.0, 8.699999, 0.0);
    }

    @Override
    public void renderModel(ItemStack stack) {
        awm.render(1f);
        stock.render(1.0F);
        if (!hasScopeAtachment(stack))
            ironsights.render(1.0F);
    }

    public ModelAWM() {
        textureWidth = 512;
        textureHeight = 512;

        awm = new ModelRenderer(this);
        awm.setRotationPoint(0.0F, 24.0F, 0.0F);
        awm.cubeList.add(new ModelBox(awm, 196, 86, -6.0F, -29.5F, -3.5F, 8, 5, 7, 0.0F, true));
        awm.cubeList.add(new ModelBox(awm, 196, 86, 4.0F, -24.5F, -3.5F, 8, 4, 7, 0.0F, true));
        awm.cubeList.add(new ModelBox(awm, 196, 86, -4.0F, -24.5F, -3.5F, 8, 4, 7, 0.0F, true));
        awm.cubeList.add(new ModelBox(awm, 196, 86, -12.0F, -24.5F, -3.5F, 8, 4, 7, 0.0F, true));
        awm.cubeList.add(new ModelBox(awm, 196, 86, -20.0F, -24.5F, -3.5F, 8, 4, 7, 0.0F, true));
        awm.cubeList.add(new ModelBox(awm, 196, 86, -25.0F, -24.5F, -3.5F, 5, 4, 7, 0.0F, true));
        awm.cubeList.add(new ModelBox(awm, 196, 86, 12.0F, -24.5F, -3.5F, 12, 4, 7, 0.0F, true));
        awm.cubeList.add(new ModelBox(awm, 196, 86, 4.0F, -20.5F, -3.5F, 6, 3, 7, 0.0F, true));
        awm.cubeList.add(new ModelBox(awm, 196, 86, -1.0F, -20.5F, -3.5F, 5, 3, 7, 0.0F, true));
        awm.cubeList.add(new ModelBox(awm, 196, 86, -10.0F, -20.5F, -3.5F, 9, 3, 1, 0.0F, true));
        awm.cubeList.add(new ModelBox(awm, 196, 86, -10.0F, -20.5F, 2.5F, 9, 3, 1, 0.0F, false));
        awm.cubeList.add(new ModelBox(awm, 196, 86, 11.0F, -20.5F, -3.5F, 8, 1, 7, 0.0F, false));
        awm.cubeList.add(new ModelBox(awm, 196, 86, 12.0F, -19.5F, -3.5F, 6, 1, 7, 0.0F, true));
        awm.cubeList.add(new ModelBox(awm, 196, 86, 12.7F, -18.5F, -3.5F, 4, 1, 7, 0.0F, false));
        awm.cubeList.add(new ModelBox(awm, 196, 86, 13.2F, -17.5F, -3.5F, 2, 1, 7, 0.0F, true));
        awm.cubeList.add(new ModelBox(awm, 196, 86, 32.0F, -26.5F, -3.5F, 8, 6, 7, 0.0F, false));
        awm.cubeList.add(new ModelBox(awm, 196, 86, 24.0F, -26.5F, -3.5F, 8, 6, 7, 0.0F, false));
        awm.cubeList.add(new ModelBox(awm, 196, 86, 31.0F, -20.5F, -3.5F, 9, 14, 7, 0.0F, false));
        awm.cubeList.add(new ModelBox(awm, 196, 86, 40.0F, -26.5F, -3.5F, 9, 20, 7, 0.0F, false));
        awm.cubeList.add(new ModelBox(awm, 0, 65, 49.0F, -27.5F, -3.5F, 2, 23, 7, 0.0F, true));
        awm.cubeList.add(new ModelBox(awm, 196, 86, 25.0F, -8.5F, -3.5F, 6, 4, 7, 0.0F, false));
        awm.cubeList.add(new ModelBox(awm, 196, 86, 19.0F, -8.5F, -3.5F, 6, 4, 7, 0.0F, false));
        awm.cubeList.add(new ModelBox(awm, 196, 86, 12.0F, -25.0F, -3.5F, 12, 1, 7, 0.0F, false));
        awm.cubeList.add(new ModelBox(awm, 196, 86, 5.0F, -25.0F, -3.5F, 7, 1, 7, 0.0F, false));
        awm.cubeList.add(new ModelBox(awm, 196, 86, 2.0F, -25.0F, -3.5F, 3, 1, 7, 0.0F, false));
        awm.cubeList.add(new ModelBox(awm, 196, 86, -20.0F, -29.5F, -3.5F, 5, 5, 7, 0.0F, true));
        awm.cubeList.add(new ModelBox(awm, 196, 86, -25.0F, -29.5F, -3.5F, 5, 5, 7, 0.0F, true));
        awm.cubeList.add(new ModelBox(awm, 196, 86, -34.0F, -26.5F, -3.5F, 9, 4, 7, 0.0F, true));
        awm.cubeList.add(new ModelBox(awm, 196, 86, -43.0F, -26.5F, -3.5F, 9, 4, 7, 0.0F, true));
        awm.cubeList.add(new ModelBox(awm, 196, 86, -47.0F, -26.5F, -3.5F, 4, 4, 7, 0.0F, true));
        awm.cubeList.add(new ModelBox(awm, 196, 86, -54.0F, -26.5F, -3.5F, 7, 3, 7, 0.0F, false));
        awm.cubeList.add(new ModelBox(awm, 64, 44, -56.0F, -25.0F, -1.0F, 31, 1, 2, 0.0F, false));
        awm.cubeList.add(new ModelBox(awm, 64, 44, -87.0F, -25.0F, -1.0F, 31, 1, 2, 0.0F, false));
        awm.cubeList.add(new ModelBox(awm, 64, 44, -105.0F, -25.0F, -1.0F, 18, 1, 2, 0.0F, false));
        awm.cubeList.add(new ModelBox(awm, 196, 86, -77.0F, -27.9F, -1.5F, 1, 3, 3, 0.0F, false));
        awm.cubeList.add(new ModelBox(awm, 64, 44, -56.0F, -28.8284F, -1.0F, 31, 1, 2, 0.0F, false));
        awm.cubeList.add(new ModelBox(awm, 64, 44, -87.0F, -28.8284F, -1.0F, 31, 1, 2, 0.0F, false));
        awm.cubeList.add(new ModelBox(awm, 64, 44, -105.0F, -28.8284F, -1.0F, 18, 1, 2, 0.0F, false));
        awm.cubeList.add(new ModelBox(awm, 64, 44, -56.0F, -27.4142F, 1.4142F, 31, 2, 1, 0.0F, false));
        awm.cubeList.add(new ModelBox(awm, 64, 44, -87.0F, -27.4142F, 1.4142F, 31, 2, 1, 0.0F, false));
        awm.cubeList.add(new ModelBox(awm, 64, 44, -105.0F, -27.4142F, 1.4142F, 18, 2, 1, 0.0F, false));
        awm.cubeList.add(new ModelBox(awm, 64, 44, -56.0F, -27.4142F, -2.4142F, 31, 2, 1, 0.0F, false));
        awm.cubeList.add(new ModelBox(awm, 64, 44, -87.0F, -27.4142F, -2.4142F, 31, 2, 1, 0.0F, false));
        awm.cubeList.add(new ModelBox(awm, 64, 44, -105.0F, -27.4142F, -2.4142F, 18, 2, 1, 0.0F, false));
        awm.cubeList.add(new ModelBox(awm, 196, 86, -15.0F, -29.5F, -3.5F, 9, 5, 1, 0.0F, true));
        awm.cubeList.add(new ModelBox(awm, 196, 86, -11.0F, -30.2071F, -2.7929F, 13, 1, 3, 0.0F, false));
        awm.cubeList.add(new ModelBox(awm, 76, 85, 0.0F, -32.0071F, -1.0F, 1, 1, 2, 0.0F, true));
        awm.cubeList.add(new ModelBox(awm, 76, 85, -2.0F, -32.0071F, -1.0F, 1, 1, 2, 0.0F, true));
        awm.cubeList.add(new ModelBox(awm, 76, 85, -4.0F, -32.0071F, -1.0F, 1, 1, 2, 0.0F, true));
        awm.cubeList.add(new ModelBox(awm, 76, 85, -6.0F, -32.0071F, -1.0F, 1, 1, 2, 0.0F, true));
        awm.cubeList.add(new ModelBox(awm, 76, 85, -8.0F, -32.0071F, -1.0F, 1, 1, 2, 0.0F, true));
        awm.cubeList.add(new ModelBox(awm, 76, 85, -10.0F, -32.0071F, -1.0F, 1, 1, 2, 0.0F, true));
        awm.cubeList.add(new ModelBox(awm, 76, 85, -12.0F, -32.0071F, -1.0F, 1, 1, 2, 0.0F, true));
        awm.cubeList.add(new ModelBox(awm, 76, 85, -14.0F, -32.0071F, -1.0F, 1, 1, 2, 0.0F, true));
        awm.cubeList.add(new ModelBox(awm, 76, 85, -16.0F, -32.0071F, -1.0F, 1, 1, 2, 0.0F, true));
        awm.cubeList.add(new ModelBox(awm, 76, 85, -18.0F, -32.0071F, -1.0F, 1, 1, 2, 0.0F, true));
        awm.cubeList.add(new ModelBox(awm, 76, 85, -20.0F, -32.0071F, -1.0F, 1, 1, 2, 0.0F, true));
        awm.cubeList.add(new ModelBox(awm, 76, 85, -22.0F, -32.0071F, -1.0F, 1, 1, 2, 0.0F, true));
        awm.cubeList.add(new ModelBox(awm, 76, 85, -24.0F, -32.0071F, -1.0F, 1, 1, 2, 0.0F, true));
        awm.cubeList.add(new ModelBox(awm, 69, 93, -23.0F, -31.5071F, -1.866F, 1, 0, 2, 0.0F, true));
        awm.cubeList.add(new ModelBox(awm, 69, 93, -19.0F, -31.5071F, -1.866F, 1, 0, 2, 0.0F, true));
        awm.cubeList.add(new ModelBox(awm, 69, 93, -15.0F, -31.5071F, -1.866F, 1, 0, 2, 0.0F, true));
        awm.cubeList.add(new ModelBox(awm, 69, 93, -11.0F, -31.5071F, -1.866F, 1, 0, 2, 0.0F, true));
        awm.cubeList.add(new ModelBox(awm, 69, 93, -7.0F, -31.5071F, -1.866F, 1, 0, 2, 0.0F, true));
        awm.cubeList.add(new ModelBox(awm, 69, 93, -3.0F, -31.5071F, -1.866F, 1, 0, 2, 0.0F, true));
        awm.cubeList.add(new ModelBox(awm, 69, 93, -21.0F, -31.5071F, -1.866F, 1, 0, 2, 0.0F, true));
        awm.cubeList.add(new ModelBox(awm, 69, 93, -17.0F, -31.5071F, -1.866F, 1, 0, 2, 0.0F, true));
        awm.cubeList.add(new ModelBox(awm, 69, 93, -13.0F, -31.5071F, -1.866F, 1, 0, 2, 0.0F, true));
        awm.cubeList.add(new ModelBox(awm, 69, 93, -9.0F, -31.5071F, -1.866F, 1, 0, 2, 0.0F, true));
        awm.cubeList.add(new ModelBox(awm, 69, 93, -5.0F, -31.5071F, -1.866F, 1, 0, 2, 0.0F, true));
        awm.cubeList.add(new ModelBox(awm, 69, 93, -1.0F, -31.5071F, -1.866F, 1, 0, 2, 0.0F, true));
        awm.cubeList.add(new ModelBox(awm, 69, 93, -23.0F, -31.5071F, -0.134F, 1, 0, 2, 0.0F, false));
        awm.cubeList.add(new ModelBox(awm, 69, 93, -19.0F, -31.5071F, -0.134F, 1, 0, 2, 0.0F, false));
        awm.cubeList.add(new ModelBox(awm, 69, 93, -15.0F, -31.5071F, -0.134F, 1, 0, 2, 0.0F, false));
        awm.cubeList.add(new ModelBox(awm, 69, 93, -11.0F, -31.5071F, -0.134F, 1, 0, 2, 0.0F, false));
        awm.cubeList.add(new ModelBox(awm, 69, 93, -7.0F, -31.5071F, -0.134F, 1, 0, 2, 0.0F, false));
        awm.cubeList.add(new ModelBox(awm, 69, 93, -3.0F, -31.5071F, -0.134F, 1, 0, 2, 0.0F, false));
        awm.cubeList.add(new ModelBox(awm, 69, 93, -21.0F, -31.5071F, -0.134F, 1, 0, 2, 0.0F, false));
        awm.cubeList.add(new ModelBox(awm, 69, 93, -17.0F, -31.5071F, -0.134F, 1, 0, 2, 0.0F, false));
        awm.cubeList.add(new ModelBox(awm, 69, 93, -13.0F, -31.5071F, -0.134F, 1, 0, 2, 0.0F, false));
        awm.cubeList.add(new ModelBox(awm, 69, 93, -9.0F, -31.5071F, -0.134F, 1, 0, 2, 0.0F, false));
        awm.cubeList.add(new ModelBox(awm, 69, 93, -5.0F, -31.5071F, -0.134F, 1, 0, 2, 0.0F, false));
        awm.cubeList.add(new ModelBox(awm, 69, 93, -1.0F, -31.5071F, -0.134F, 1, 0, 2, 0.0F, false));
        awm.cubeList.add(new ModelBox(awm, 69, 88, -8.0F, -31.0071F, -1.5F, 9, 1, 3, 0.0F, true));
        awm.cubeList.add(new ModelBox(awm, 69, 88, -16.0F, -31.0071F, -1.5F, 8, 1, 3, 0.0F, true));
        awm.cubeList.add(new ModelBox(awm, 69, 88, -24.0F, -31.0071F, -1.5F, 8, 1, 3, 0.0F, true));
        awm.cubeList.add(new ModelBox(awm, 196, 86, -25.0F, -30.2071F, -2.7929F, 14, 1, 3, 0.0F, true));
        awm.cubeList.add(new ModelBox(awm, 196, 86, -11.0F, -30.2071F, -0.2071F, 13, 1, 3, 0.0F, false));
        awm.cubeList.add(new ModelBox(awm, 196, 86, -25.0F, -30.2071F, -0.2071F, 14, 1, 3, 0.0F, true));
        awm.cubeList.add(new ModelBox(awm, 196, 86, -15.0F, -29.5F, 0.5F, 9, 1, 3, 0.0F, true));
        awm.cubeList.add(new ModelBox(awm, 196, 86, -15.0F, -25.5F, 0.5F, 9, 1, 3, 0.0F, true));

        bone10 = new ModelRenderer(this);
        bone10.setRotationPoint(-27.5F, -22.5F, 0.0F);
        awm.addChild(bone10);
        setRotationAngle(bone10, 0.0F, 0.0F, 0.1047F);
        bone10.cubeList.add(new ModelBox(bone10, 196, 86, -5.3046F, -0.2723F, -3.5F, 8, 2, 7, 0.0F, true));
        bone10.cubeList.add(new ModelBox(bone10, 196, 86, -13.3046F, -0.2723F, -3.5F, 8, 2, 7, 0.0F, true));
        bone10.cubeList.add(new ModelBox(bone10, 196, 86, -17.3046F, -0.2723F, -3.5F, 4, 2, 7, 0.0F, true));

        bone33 = new ModelRenderer(this);
        bone33.setRotationPoint(0.3F, -13.0F, 0.0F);
        awm.addChild(bone33);
        setRotationAngle(bone33, 0.0F, 0.0F, -0.7854F);


        bone28 = new ModelRenderer(this);
        bone28.setRotationPoint(-15.5F, -19.0F, 0.0F);
        awm.addChild(bone28);
        setRotationAngle(bone28, 0.0F, 0.0F, 0.3491F);
        bone28.cubeList.add(new ModelBox(bone28, 196, 86, -0.3187F, -3.4716F, -3.5F, 6, 3, 1, 0.0F, true));
        bone28.cubeList.add(new ModelBox(bone28, 196, 86, -3.3187F, -3.4716F, -3.5F, 3, 3, 7, 0.0F, true));
        bone28.cubeList.add(new ModelBox(bone28, 196, 86, -33.7974F, 6.4847F, -3.5F, 3, 1, 7, 0.0F, true));
        bone28.cubeList.add(new ModelBox(bone28, 196, 86, -0.3187F, -3.4716F, 2.5F, 6, 3, 1, 0.0F, false));

        bone30 = new ModelRenderer(this);
        bone30.setRotationPoint(-63.0F, -24.5F, 0.0F);
        awm.addChild(bone30);
        setRotationAngle(bone30, -0.7854F, 0.0F, 0.0F);
        bone30.cubeList.add(new ModelBox(bone30, 64, 44, 7.0F, 0.0607F, -2.3536F, 31, 1, 2, 0.0F, false));
        bone30.cubeList.add(new ModelBox(bone30, 64, 44, -24.0F, 0.0607F, -2.3536F, 31, 1, 2, 0.0F, false));
        bone30.cubeList.add(new ModelBox(bone30, 64, 44, -42.0F, 0.0607F, -2.3536F, 18, 1, 2, 0.0F, false));
        bone30.cubeList.add(new ModelBox(bone30, 64, 44, 7.0F, -2.3536F, 0.0607F, 31, 2, 1, 0.0F, false));
        bone30.cubeList.add(new ModelBox(bone30, 64, 44, -24.0F, -2.3536F, 0.0607F, 31, 2, 1, 0.0F, false));
        bone30.cubeList.add(new ModelBox(bone30, 64, 44, -42.0F, -2.3536F, 0.0607F, 18, 2, 1, 0.0F, false));
        bone30.cubeList.add(new ModelBox(bone30, 64, 44, 7.0F, -3.7678F, -2.3536F, 31, 1, 2, 0.0F, false));
        bone30.cubeList.add(new ModelBox(bone30, 64, 44, -24.0F, -3.7678F, -2.3536F, 31, 1, 2, 0.0F, false));
        bone30.cubeList.add(new ModelBox(bone30, 64, 44, -42.0F, -3.7678F, -2.3536F, 18, 1, 2, 0.0F, false));
        bone30.cubeList.add(new ModelBox(bone30, 64, 44, 7.0F, -2.3536F, -3.7678F, 31, 2, 1, 0.0F, false));
        bone30.cubeList.add(new ModelBox(bone30, 64, 44, -24.0F, -2.3536F, -3.7678F, 31, 2, 1, 0.0F, false));
        bone30.cubeList.add(new ModelBox(bone30, 64, 44, -42.0F, -2.3536F, -3.7678F, 18, 2, 1, 0.0F, false));

        bone31 = new ModelRenderer(this);
        bone31.setRotationPoint(1.5F, -32.7071F, -4.0F);
        awm.addChild(bone31);
        setRotationAngle(bone31, -0.5236F, 0.0F, 0.0F);
        bone31.cubeList.add(new ModelBox(bone31, 69, 93, -1.5F, -1.8938F, 4.6801F, 1, 1, 1, 0.0F, true));
        bone31.cubeList.add(new ModelBox(bone31, 69, 93, -3.5F, -1.8938F, 4.6801F, 1, 1, 1, 0.0F, true));
        bone31.cubeList.add(new ModelBox(bone31, 69, 93, -5.5F, -1.8938F, 4.6801F, 1, 1, 1, 0.0F, true));
        bone31.cubeList.add(new ModelBox(bone31, 69, 93, -7.5F, -1.8938F, 4.6801F, 1, 1, 1, 0.0F, true));
        bone31.cubeList.add(new ModelBox(bone31, 69, 93, -9.5F, -1.8938F, 4.6801F, 1, 1, 1, 0.0F, true));
        bone31.cubeList.add(new ModelBox(bone31, 69, 93, -11.5F, -1.8938F, 4.6801F, 1, 1, 1, 0.0F, true));
        bone31.cubeList.add(new ModelBox(bone31, 69, 93, -13.5F, -1.8938F, 4.6801F, 1, 1, 1, 0.0F, true));
        bone31.cubeList.add(new ModelBox(bone31, 69, 93, -15.5F, -1.8938F, 4.6801F, 1, 1, 1, 0.0F, true));
        bone31.cubeList.add(new ModelBox(bone31, 69, 93, -17.5F, -1.8938F, 4.6801F, 1, 1, 1, 0.0F, true));
        bone31.cubeList.add(new ModelBox(bone31, 69, 93, -19.5F, -1.8938F, 4.6801F, 1, 1, 1, 0.0F, true));
        bone31.cubeList.add(new ModelBox(bone31, 69, 93, -21.5F, -1.8938F, 4.6801F, 1, 1, 1, 0.0F, true));
        bone31.cubeList.add(new ModelBox(bone31, 69, 93, -23.5F, -1.8938F, 4.6801F, 1, 1, 1, 0.0F, true));
        bone31.cubeList.add(new ModelBox(bone31, 69, 93, -25.5F, -1.8938F, 4.6801F, 1, 1, 1, 0.0F, true));
        bone31.cubeList.add(new ModelBox(bone31, 69, 93, -24.5F, -1.8938F, 5.6801F, 1, 1, 0, 0.0F, true));
        bone31.cubeList.add(new ModelBox(bone31, 69, 93, -20.5F, -1.8938F, 5.6801F, 1, 1, 0, 0.0F, true));
        bone31.cubeList.add(new ModelBox(bone31, 69, 93, -16.5F, -1.8938F, 5.6801F, 1, 1, 0, 0.0F, true));
        bone31.cubeList.add(new ModelBox(bone31, 69, 93, -12.5F, -1.8938F, 5.6801F, 1, 1, 0, 0.0F, true));
        bone31.cubeList.add(new ModelBox(bone31, 69, 93, -8.5F, -1.8938F, 5.6801F, 1, 1, 0, 0.0F, true));
        bone31.cubeList.add(new ModelBox(bone31, 69, 93, -4.5F, -1.8938F, 5.6801F, 1, 1, 0, 0.0F, true));
        bone31.cubeList.add(new ModelBox(bone31, 69, 93, -22.5F, -1.8938F, 5.6801F, 1, 1, 0, 0.0F, true));
        bone31.cubeList.add(new ModelBox(bone31, 69, 93, -18.5F, -1.8938F, 5.6801F, 1, 1, 0, 0.0F, true));
        bone31.cubeList.add(new ModelBox(bone31, 69, 93, -14.5F, -1.8938F, 5.6801F, 1, 1, 0, 0.0F, true));
        bone31.cubeList.add(new ModelBox(bone31, 69, 93, -10.5F, -1.8938F, 5.6801F, 1, 1, 0, 0.0F, true));
        bone31.cubeList.add(new ModelBox(bone31, 69, 93, -6.5F, -1.8938F, 5.6801F, 1, 1, 0, 0.0F, true));
        bone31.cubeList.add(new ModelBox(bone31, 69, 93, -2.5F, -1.8938F, 5.6801F, 1, 1, 0, 0.0F, true));

        bone32 = new ModelRenderer(this);
        bone32.setRotationPoint(0.5F, -35.3901F, 1.0131F);
        awm.addChild(bone32);
        setRotationAngle(bone32, 0.5236F, 0.0F, 0.0F);
        bone32.cubeList.add(new ModelBox(bone32, 86, 88, -0.5F, 1.9232F, -4.4349F, 1, 1, 1, 0.0F, true));
        bone32.cubeList.add(new ModelBox(bone32, 86, 88, -2.5F, 1.9232F, -4.4349F, 1, 1, 1, 0.0F, true));
        bone32.cubeList.add(new ModelBox(bone32, 86, 88, -4.5F, 1.9232F, -4.4349F, 1, 1, 1, 0.0F, true));
        bone32.cubeList.add(new ModelBox(bone32, 86, 88, -6.5F, 1.9232F, -4.4349F, 1, 1, 1, 0.0F, true));
        bone32.cubeList.add(new ModelBox(bone32, 86, 88, -8.5F, 1.9232F, -4.4349F, 1, 1, 1, 0.0F, true));
        bone32.cubeList.add(new ModelBox(bone32, 86, 88, -10.5F, 1.9232F, -4.4349F, 1, 1, 1, 0.0F, true));
        bone32.cubeList.add(new ModelBox(bone32, 86, 88, -12.5F, 1.9232F, -4.4349F, 1, 1, 1, 0.0F, true));
        bone32.cubeList.add(new ModelBox(bone32, 86, 88, -14.5F, 1.9232F, -4.4349F, 1, 1, 1, 0.0F, true));
        bone32.cubeList.add(new ModelBox(bone32, 86, 88, -16.5F, 1.9232F, -4.4349F, 1, 1, 1, 0.0F, true));
        bone32.cubeList.add(new ModelBox(bone32, 86, 88, -18.5F, 1.9232F, -4.4349F, 1, 1, 1, 0.0F, true));
        bone32.cubeList.add(new ModelBox(bone32, 86, 88, -20.5F, 1.9232F, -4.4349F, 1, 1, 1, 0.0F, true));
        bone32.cubeList.add(new ModelBox(bone32, 86, 88, -22.5F, 1.9232F, -4.4349F, 1, 1, 1, 0.0F, true));
        bone32.cubeList.add(new ModelBox(bone32, 86, 88, -24.5F, 1.9232F, -4.4349F, 1, 1, 1, 0.0F, true));
        bone32.cubeList.add(new ModelBox(bone32, 86, 88, -23.5F, 1.9232F, -4.4349F, 1, 1, 0, 0.0F, true));
        bone32.cubeList.add(new ModelBox(bone32, 86, 88, -19.5F, 1.9232F, -4.4349F, 1, 1, 0, 0.0F, true));
        bone32.cubeList.add(new ModelBox(bone32, 86, 88, -15.5F, 1.9232F, -4.4349F, 1, 1, 0, 0.0F, true));
        bone32.cubeList.add(new ModelBox(bone32, 86, 88, -11.5F, 1.9232F, -4.4349F, 1, 1, 0, 0.0F, true));
        bone32.cubeList.add(new ModelBox(bone32, 86, 88, -7.5F, 1.9232F, -4.4349F, 1, 1, 0, 0.0F, true));
        bone32.cubeList.add(new ModelBox(bone32, 86, 88, -3.5F, 1.9232F, -4.4349F, 1, 1, 0, 0.0F, true));
        bone32.cubeList.add(new ModelBox(bone32, 86, 88, -21.5F, 1.9232F, -4.4349F, 1, 1, 0, 0.0F, true));
        bone32.cubeList.add(new ModelBox(bone32, 86, 88, -17.5F, 1.9232F, -4.4349F, 1, 1, 0, 0.0F, true));
        bone32.cubeList.add(new ModelBox(bone32, 86, 88, -13.5F, 1.9232F, -4.4349F, 1, 1, 0, 0.0F, true));
        bone32.cubeList.add(new ModelBox(bone32, 86, 88, -9.5F, 1.9232F, -4.4349F, 1, 1, 0, 0.0F, true));
        bone32.cubeList.add(new ModelBox(bone32, 86, 88, -5.5F, 1.9232F, -4.4349F, 1, 1, 0, 0.0F, true));
        bone32.cubeList.add(new ModelBox(bone32, 86, 88, -1.5F, 1.9232F, -4.4349F, 1, 1, 0, 0.0F, true));

        bone11 = new ModelRenderer(this);
        bone11.setRotationPoint(-10.5F, -7.0F, 2.0F);
        awm.addChild(bone11);
        setRotationAngle(bone11, -0.7854F, 0.0F, 0.0F);
        bone11.cubeList.add(new ModelBox(bone11, 196, 86, -0.5F, -16.9706F, -15.8492F, 13, 1, 1, 0.0F, true));
        bone11.cubeList.add(new ModelBox(bone11, 196, 86, -0.5F, -13.0208F, -19.799F, 13, 1, 1, 0.0F, true));
        bone11.cubeList.add(new ModelBox(bone11, 196, 86, -14.5F, -16.9706F, -15.8492F, 14, 1, 1, 0.0F, true));
        bone11.cubeList.add(new ModelBox(bone11, 196, 86, -14.5F, -13.0208F, -19.799F, 14, 1, 1, 0.0F, true));

        grip = new ModelRenderer(this);
        grip.setRotationPoint(7.0F, 0.0F, 0.0F);
        awm.addChild(grip);


        bone21 = new ModelRenderer(this);
        bone21.setRotationPoint(4.0F, -11.6964F, 0.0F);
        grip.addChild(bone21);
        setRotationAngle(bone21, 0.0F, 0.0F, -2.0944F);
        bone21.cubeList.add(new ModelBox(bone21, 196, 86, -9.645F, -2.0146F, -3.5F, 9, 7, 7, 0.0F, true));
        bone21.cubeList.add(new ModelBox(bone21, 196, 86, -0.645F, -2.0146F, -3.5F, 9, 7, 7, 0.0F, true));
        bone21.cubeList.add(new ModelBox(bone21, 196, 86, -7.645F, 4.6925F, -0.2071F, 9, 1, 3, 0.0F, false));
        bone21.cubeList.add(new ModelBox(bone21, 196, 86, -9.645F, -2.7217F, -0.2071F, 18, 1, 3, 0.0F, false));
        bone21.cubeList.add(new ModelBox(bone21, 196, 86, -7.645F, 4.6925F, -2.7929F, 9, 1, 3, 0.0F, false));
        bone21.cubeList.add(new ModelBox(bone21, 196, 86, -9.645F, -2.7217F, -2.7929F, 18, 1, 3, 0.0F, false));

        bone27 = new ModelRenderer(this);
        bone27.setRotationPoint(-4.0F, 11.6964F, 0.0F);
        bone21.addChild(bone27);
        setRotationAngle(bone27, 0.0F, 0.0F, 0.1745F);
        bone27.cubeList.add(new ModelBox(bone27, 196, 86, -7.9401F, -12.5225F, -3.5F, 2, 7, 7, 0.0F, false));

        bone22 = new ModelRenderer(this);
        bone22.setRotationPoint(0.0F, -3.3036F, 0.0F);
        bone21.addChild(bone22);
        setRotationAngle(bone22, 0.7854F, 0.0F, 0.0F);
        bone22.cubeList.add(new ModelBox(bone22, 196, 86, -7.645F, 7.3361F, -4.3863F, 14, 1, 1, 0.0F, false));
        bone22.cubeList.add(new ModelBox(bone22, 196, 86, -9.645F, 2.3863F, 0.5634F, 12, 1, 1, 0.0F, false));
        bone22.cubeList.add(new ModelBox(bone22, 196, 86, -9.645F, -1.5634F, -3.3863F, 12, 1, 1, 0.0F, false));
        bone22.cubeList.add(new ModelBox(bone22, 196, 86, 2.355F, 2.3863F, 0.5634F, 6, 1, 1, 0.0F, false));
        bone22.cubeList.add(new ModelBox(bone22, 196, 86, 2.355F, -1.5634F, -3.3863F, 6, 1, 1, 0.0F, false));
        bone22.cubeList.add(new ModelBox(bone22, 196, 86, -7.645F, 3.3863F, -8.3361F, 14, 1, 1, 0.0F, false));

        bone13 = new ModelRenderer(this);
        bone13.setRotationPoint(12.0F, -2.6964F, 0.0F);
        grip.addChild(bone13);
        setRotationAngle(bone13, 0.0F, 0.0F, -2.8798F);
        bone13.cubeList.add(new ModelBox(bone13, 196, 86, -3.4666F, 4.3832F, -3.5F, 7, 2, 7, 0.0F, false));
        bone13.cubeList.add(new ModelBox(bone13, 196, 86, -3.4666F, 6.0903F, -0.2071F, 7, 1, 3, 0.0F, false));
        bone13.cubeList.add(new ModelBox(bone13, 196, 86, -3.4666F, 6.0903F, -2.7929F, 7, 1, 3, 0.0F, false));

        bone14 = new ModelRenderer(this);
        bone14.setRotationPoint(0.0F, -3.3036F, 0.0F);
        bone13.addChild(bone14);
        setRotationAngle(bone14, 0.7854F, 0.0F, 0.0F);
        bone14.cubeList.add(new ModelBox(bone14, 196, 86, -3.4666F, 8.3244F, -5.3747F, 7, 1, 1, 0.0F, false));
        bone14.cubeList.add(new ModelBox(bone14, 196, 86, -3.4666F, 4.3747F, -9.3244F, 7, 1, 1, 0.0F, false));

        bone15 = new ModelRenderer(this);
        bone15.setRotationPoint(20.3F, -3.1964F, 0.0F);
        grip.addChild(bone15);
        setRotationAngle(bone15, 0.0F, 0.0F, 2.618F);
        bone15.cubeList.add(new ModelBox(bone15, 196, 86, -5.7431F, 3.0835F, -3.5F, 7, 3, 7, 0.0F, false));
        bone15.cubeList.add(new ModelBox(bone15, 196, 86, -10.8561F, -0.7211F, -3.5F, 7, 6, 7, 0.0F, false));
        bone15.cubeList.add(new ModelBox(bone15, 196, 86, -5.7431F, 5.7906F, -0.2071F, 7, 1, 3, 0.0F, false));
        bone15.cubeList.add(new ModelBox(bone15, 196, 86, -5.7431F, 5.7906F, -2.7929F, 7, 1, 3, 0.0F, false));

        bone16 = new ModelRenderer(this);
        bone16.setRotationPoint(0.0F, -3.3036F, 0.0F);
        bone15.addChild(bone16);
        setRotationAngle(bone16, 0.7854F, 0.0F, 0.0F);
        bone16.cubeList.add(new ModelBox(bone16, 196, 86, -5.7431F, 8.1125F, -5.1628F, 7, 1, 1, 0.0F, false));
        bone16.cubeList.add(new ModelBox(bone16, 196, 86, -5.7431F, 4.1628F, -9.1125F, 7, 1, 1, 0.0F, false));

        bone17 = new ModelRenderer(this);
        bone17.setRotationPoint(25.8F, -8.9964F, 0.0F);
        grip.addChild(bone17);
        setRotationAngle(bone17, 0.0F, 0.0F, 1.8326F);
        bone17.cubeList.add(new ModelBox(bone17, 196, 86, -7.1409F, 2.2619F, -3.5F, 7, 2, 7, 0.0F, false));
        bone17.cubeList.add(new ModelBox(bone17, 196, 86, -7.1409F, 3.969F, -0.2071F, 7, 1, 3, 0.0F, false));
        bone17.cubeList.add(new ModelBox(bone17, 196, 86, -14.6296F, 12.0304F, -3.5F, 2, 1, 7, 0.0F, true));
        bone17.cubeList.add(new ModelBox(bone17, 196, 86, -7.1409F, 3.969F, -2.7929F, 7, 1, 3, 0.0F, false));

        bone18 = new ModelRenderer(this);
        bone18.setRotationPoint(0.0F, -3.3036F, 0.0F);
        bone17.addChild(bone18);
        setRotationAngle(bone18, 0.7854F, 0.0F, 0.0F);
        bone18.cubeList.add(new ModelBox(bone18, 196, 86, -7.1409F, 6.8244F, -3.8747F, 7, 1, 1, 0.0F, false));
        bone18.cubeList.add(new ModelBox(bone18, 196, 86, -7.1409F, 2.8747F, -7.8244F, 7, 1, 1, 0.0F, false));

        bone19 = new ModelRenderer(this);
        bone19.setRotationPoint(24.3F, -16.9964F, 0.0F);
        grip.addChild(bone19);
        setRotationAngle(bone19, 0.0F, 0.0F, 0.7854F);
        bone19.cubeList.add(new ModelBox(bone19, 196, 86, -4.5644F, -2.6359F, -3.5F, 4, 2, 7, 0.0F, false));
        bone19.cubeList.add(new ModelBox(bone19, 196, 86, -6.3644F, -0.6359F, -3.5F, 7, 2, 7, 0.0F, false));
        bone19.cubeList.add(new ModelBox(bone19, 196, 86, -6.3644F, 1.0712F, -0.2071F, 7, 1, 3, 0.0F, false));
        bone19.cubeList.add(new ModelBox(bone19, 196, 86, -6.3644F, 1.0712F, -2.7929F, 7, 1, 3, 0.0F, false));

        bone20 = new ModelRenderer(this);
        bone20.setRotationPoint(0.0F, -3.3036F, 0.0F);
        bone19.addChild(bone20);
        setRotationAngle(bone20, 0.7854F, 0.0F, 0.0F);
        bone20.cubeList.add(new ModelBox(bone20, 196, 86, -6.3644F, 4.7754F, -1.8257F, 7, 1, 1, 0.0F, false));
        bone20.cubeList.add(new ModelBox(bone20, 196, 86, -6.3644F, 0.8257F, -5.7754F, 7, 1, 1, 0.0F, false));

        bone23 = new ModelRenderer(this);
        bone23.setRotationPoint(16.7F, -20.3964F, 0.0F);
        grip.addChild(bone23);
        setRotationAngle(bone23, 0.0F, 0.0F, -0.0873F);
        bone23.cubeList.add(new ModelBox(bone23, 196, 86, -4.9816F, -0.5032F, -3.5F, 8, 1, 7, 0.0F, false));
        bone23.cubeList.add(new ModelBox(bone23, 196, 86, -4.9816F, 0.2039F, -0.2071F, 8, 1, 3, 0.0F, false));
        bone23.cubeList.add(new ModelBox(bone23, 196, 86, -4.9816F, 0.2039F, -2.7929F, 8, 1, 3, 0.0F, false));

        bone24 = new ModelRenderer(this);
        bone24.setRotationPoint(0.0F, -3.3036F, 0.0F);
        bone23.addChild(bone24);
        setRotationAngle(bone24, 0.7854F, 0.0F, 0.0F);
        bone24.cubeList.add(new ModelBox(bone24, 196, 86, -4.9816F, 4.1622F, -1.2124F, 8, 1, 1, 0.0F, false));
        bone24.cubeList.add(new ModelBox(bone24, 196, 86, -4.9816F, 0.2124F, -5.1622F, 8, 1, 1, 0.0F, false));

        bone25 = new ModelRenderer(this);
        bone25.setRotationPoint(9.1F, -18.1964F, 0.0F);
        grip.addChild(bone25);
        setRotationAngle(bone25, 0.0F, 0.0F, -0.6109F);
        bone25.cubeList.add(new ModelBox(bone25, 196, 86, -2.8223F, 0.028F, -3.5F, 7, 1, 7, 0.0F, true));
        bone25.cubeList.add(new ModelBox(bone25, 196, 86, -3.5223F, 0.7351F, -0.2071F, 8, 1, 3, 0.0F, false));
        bone25.cubeList.add(new ModelBox(bone25, 196, 86, -3.5223F, 0.7351F, -2.7929F, 8, 1, 3, 0.0F, false));

        bone26 = new ModelRenderer(this);
        bone26.setRotationPoint(0.0F, -3.3036F, 0.0F);
        bone25.addChild(bone26);
        setRotationAngle(bone26, 0.7854F, 0.0F, 0.0F);
        bone26.cubeList.add(new ModelBox(bone26, 196, 86, -3.5223F, 4.5377F, -1.588F, 8, 1, 1, 0.0F, false));
        bone26.cubeList.add(new ModelBox(bone26, 196, 86, -3.5223F, 0.588F, -5.5377F, 8, 1, 1, 0.0F, false));

        magazine = new ModelRenderer(this);
        magazine.setRotationPoint(-6.6929F, 8.6071F, 0.0F);
        setRotationAngle(magazine, 0.0F, 0.0F, 0.0873F);
        magazine.cubeList.add(new ModelBox(magazine, 103, 36, 0.0929F, 4.6929F, -2.5F, 5, 1, 5, 0.0F, true));
        magazine.cubeList.add(new ModelBox(magazine, 81, 12, -2.9071F, 3.6929F, -2.5F, 8, 1, 5, 0.0F, true));
        magazine.cubeList.add(new ModelBox(magazine, 74, 40, -5.9071F, 2.6929F, -2.5F, 11, 1, 5, 0.0F, true));
        magazine.cubeList.add(new ModelBox(magazine, 80, 22, -8.9071F, -6.3071F, -2.5F, 14, 9, 5, 0.0F, true));
        magazine.cubeList.add(new ModelBox(magazine, 78, 30, -8.9071F, -10.3071F, -2.5F, 14, 4, 1, 0.0F, true));
        magazine.cubeList.add(new ModelBox(magazine, 79, 26, 4.0929F, -9.3071F, -1.5F, 1, 3, 3, 0.0F, true));
        magazine.cubeList.add(new ModelBox(magazine, 79, 26, -8.9071F, -10.3071F, 1.5F, 14, 4, 1, 0.0F, true));
        magazine.cubeList.add(new ModelBox(magazine, 82, 28, -8.9071F, -8.3071F, -1.5F, 1, 2, 3, 0.0F, true));

        bullet = new ModelRenderer(this);
        bullet.setRotationPoint(-6.6929F, 8.6071F, 0.0F);
        setRotationAngle(bullet, 0.0F, 0.0F, 0.0873F);


        bullet2 = new ModelRenderer(this);
        bullet2.setRotationPoint(6.6929F, 10.3929F, 0.0F);
        bullet.addChild(bullet2);
        bullet2.cubeList.add(new ModelBox(bullet2, 0, 491, -13.6F, -21.9F, -1.0F, 10, 2, 2, 0.0F, true));
        bullet2.cubeList.add(new ModelBox(bullet2, 0, 491, -14.2F, -21.3F, -0.5F, 1, 1, 1, 0.0F, true));
        bullet2.cubeList.add(new ModelBox(bullet2, 0, 491, -11.6F, -22.4F, -1.5F, 7, 3, 3, 0.0F, true));
        bullet2.cubeList.add(new ModelBox(bullet2, 0, 491, -3.6F, -22.4F, -1.5F, 1, 3, 3, 0.0F, true));

        ironsights = new ModelRenderer(this);
        ironsights.setRotationPoint(0.0F, 24.0F, 1.2929F);
        ironsights.cubeList.add(new ModelBox(ironsights, 2, 79, -2.6F, -32.0071F, -3.2929F, 2, 1, 4, 0.0F, true));
        ironsights.cubeList.add(new ModelBox(ironsights, 2, 79, -22.6F, -32.0071F, -3.2929F, 2, 1, 4, 0.0F, true));
        ironsights.cubeList.add(new ModelBox(ironsights, 2, 79, -2.6F, -33.0071F, -0.2929F, 2, 1, 1, 0.0F, true));
        ironsights.cubeList.add(new ModelBox(ironsights, 2, 79, -22.6F, -33.0071F, -0.2929F, 2, 1, 1, 0.0F, true));
        ironsights.cubeList.add(new ModelBox(ironsights, 2, 79, -2.6F, -33.0071F, -3.2929F, 2, 1, 1, 0.0F, true));
        ironsights.cubeList.add(new ModelBox(ironsights, 2, 79, -22.6F, -33.0071F, -3.2929F, 2, 1, 1, 0.0F, true));
        ironsights.cubeList.add(new ModelBox(ironsights, 2, 79, -2.1F, -32.9071F, -2.7929F, 1, 1, 3, 0.0F, true));
        ironsights.cubeList.add(new ModelBox(ironsights, 2, 79, -22.1F, -32.9071F, -2.7929F, 1, 1, 3, 0.0F, true));
        ironsights.cubeList.add(new ModelBox(ironsights, 2, 79, -2.1F, -33.9071F, -1.7929F, 1, 1, 1, 0.0F, true));
        ironsights.cubeList.add(new ModelBox(ironsights, 2, 79, -22.1F, -33.9071F, -1.7929F, 1, 1, 1, 0.0F, true));
        ironsights.cubeList.add(new ModelBox(ironsights, 2, 79, -2.1F, -36.6392F, -1.7929F, 1, 1, 1, 0.0F, true));
        ironsights.cubeList.add(new ModelBox(ironsights, 2, 79, -2.1F, -35.2731F, -3.1589F, 1, 1, 1, 0.0F, true));
        ironsights.cubeList.add(new ModelBox(ironsights, 2, 79, -2.1F, -35.2731F, -0.4269F, 1, 1, 1, 0.0F, true));

        bone38 = new ModelRenderer(this);
        bone38.setRotationPoint(-21.6F, -33.5071F, -2.7929F);
        ironsights.addChild(bone38);
        setRotationAngle(bone38, -0.2618F, 0.0F, 0.0F);
        bone38.cubeList.add(new ModelBox(bone38, 2, 79, -1.0F, -2.1641F, 2.5442F, 2, 2, 1, 0.0F, true));

        bone39 = new ModelRenderer(this);
        bone39.setRotationPoint(-21.6F, -33.5071F, 0.2071F);
        ironsights.addChild(bone39);
        setRotationAngle(bone39, 0.2618F, 0.0F, 0.0F);
        bone39.cubeList.add(new ModelBox(bone39, 2, 79, -1.0F, -2.1641F, -3.5442F, 2, 2, 1, 0.0F, true));

        bone36 = new ModelRenderer(this);
        bone36.setRotationPoint(-1.6F, -33.4071F, -1.2929F);
        ironsights.addChild(bone36);
        setRotationAngle(bone36, -0.5236F, 0.0F, 0.0F);
        bone36.cubeList.add(new ModelBox(bone36, 2, 79, -0.5F, -0.317F, -1.183F, 1, 1, 1, 0.0F, true));
        bone36.cubeList.add(new ModelBox(bone36, 2, 79, -0.5F, -3.049F, -1.183F, 1, 1, 1, 0.0F, true));
        bone36.cubeList.add(new ModelBox(bone36, 2, 79, -0.5F, -1.683F, 0.183F, 1, 1, 1, 0.0F, true));
        bone36.cubeList.add(new ModelBox(bone36, 2, 79, -0.5F, -1.683F, -2.549F, 1, 1, 1, 0.0F, true));

        bone37 = new ModelRenderer(this);
        bone37.setRotationPoint(-1.6F, -33.4071F, -1.2929F);
        ironsights.addChild(bone37);
        setRotationAngle(bone37, 0.5236F, 0.0F, 0.0F);
        bone37.cubeList.add(new ModelBox(bone37, 2, 79, -0.5F, -0.317F, 0.183F, 1, 1, 1, 0.0F, true));
        bone37.cubeList.add(new ModelBox(bone37, 2, 79, -0.5F, -3.049F, 0.183F, 1, 1, 1, 0.0F, true));
        bone37.cubeList.add(new ModelBox(bone37, 2, 79, -0.5F, -1.683F, -1.183F, 1, 1, 1, 0.0F, true));
        bone37.cubeList.add(new ModelBox(bone37, 2, 79, -0.5F, -1.683F, 1.549F, 1, 1, 1, 0.0F, true));

        bolt_case = new ModelRenderer(this);
        bolt_case.setRotationPoint(3.0F, 24.0F, 0.0F);


        bone = new ModelRenderer(this);
        bone.setRotationPoint(0.0F, 0.0F, 0.0F);
        bolt_case.addChild(bone);
        bone.cubeList.add(new ModelBox(bone, 36, 11, -1.0F, -29.0F, -3.0F, 6, 4, 6, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 0, 0, -15.0F, -28.8F, -1.499F, 14, 3, 3, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 100, 157, -18.001F, -28.5F, 2.0F, 9, 3, 1, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 38, 10, 6.0F, -28.0F, 2.0F, 2, 3, 1, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 38, 10, 5.0F, -28.0F, -3.0F, 3, 3, 1, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 46, 5, 5.0F, -29.0F, -3.0F, 3, 1, 6, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 46, 12, 8.0F, -29.0F, -2.0F, 1, 4, 4, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 75, 58, 8.1914F, -28.0F, -1.0F, 1, 2, 2, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 34, 12, -1.0F, -30.0F, -2.0F, 9, 1, 4, 0.0F, false));

        bone12 = new ModelRenderer(this);
        bone12.setRotationPoint(-12.5F, -4.5F, 3.5F);
        bone.addChild(bone12);
        setRotationAngle(bone12, 0.0F, 0.2618F, 0.0F);
        bone12.cubeList.add(new ModelBox(bone12, 45, 111, -4.0394F, -23.5F, -2.2279F, 3, 2, 1, 0.0F, false));

        bone4 = new ModelRenderer(this);
        bone4.setRotationPoint(11.5F, -6.5F, 0.0F);
        bone.addChild(bone4);
        setRotationAngle(bone4, 0.0F, 0.0F, -0.7854F);
        bone4.cubeList.add(new ModelBox(bone4, 42, 15, 13.1422F, -19.0919F, -2.0F, 1, 1, 4, 0.0F, false));
        bone4.cubeList.add(new ModelBox(bone4, 42, 15, 13.1422F, -18.6776F, -2.0F, 1, 1, 4, 0.0F, false));

        bone2 = new ModelRenderer(this);
        bone2.setRotationPoint(11.5F, -4.0F, -2.5F);
        bone.addChild(bone2);
        setRotationAngle(bone2, 0.0F, 0.7854F, 0.0F);
        bone2.cubeList.add(new ModelBox(bone2, 42, 15, -3.1213F, -25.0F, -2.4142F, 1, 4, 1, 0.0F, false));
        bone2.cubeList.add(new ModelBox(bone2, 42, 15, -5.9497F, -25.0F, 0.4142F, 1, 4, 1, 0.0F, false));
        bone2.cubeList.add(new ModelBox(bone2, 42, 15, -3.1213F, -25.0F, -2.8284F, 1, 4, 1, 0.0F, false));
        bone2.cubeList.add(new ModelBox(bone2, 42, 15, -6.3639F, -25.0F, 0.4142F, 1, 4, 1, 0.0F, false));

        bone3 = new ModelRenderer(this);
        bone3.setRotationPoint(6.5F, -6.5F, -2.5F);
        bone.addChild(bone3);
        setRotationAngle(bone3, -0.7854F, 0.0F, 0.0F);
        bone3.cubeList.add(new ModelBox(bone3, 42, 15, -7.5F, -16.9706F, -16.2635F, 9, 1, 1, 0.0F, false));
        bone3.cubeList.add(new ModelBox(bone3, 42, 15, -7.5F, -16.5563F, -16.2635F, 9, 1, 1, 0.0F, false));
        bone3.cubeList.add(new ModelBox(bone3, 42, 15, -7.5F, -19.799F, -13.0208F, 9, 1, 1, 0.0F, false));
        bone3.cubeList.add(new ModelBox(bone3, 42, 15, -7.5F, -19.799F, -13.435F, 9, 1, 1, 0.0F, false));

        bolt = new ModelRenderer(this);
        bolt.setRotationPoint(11.5F, -2.8F, 0.0F);
        setRotationAngle(bolt, -0.2793F, 0.0F, 0.0F);
        bolt.cubeList.add(new ModelBox(bolt, 15, 76, -3.5F, -0.5344F, 0.0F, 1, 1, 4, 0.0F, false));

        catch_ = new ModelRenderer(this);
        catch_.setRotationPoint(0.0F, 21.8743F, 7.1074F);
        bolt.addChild(catch_);
        catch_.cubeList.add(new ModelBox(catch_, 15, 76, -3.5F, -22.4087F, -3.5074F, 1, 1, 1, 0.0F, false));
        catch_.cubeList.add(new ModelBox(catch_, 15, 76, -3.5F, -22.4087F, -2.0932F, 1, 1, 1, 0.0F, false));
        catch_.cubeList.add(new ModelBox(catch_, 15, 76, -2.7929F, -22.4087F, -2.8003F, 1, 1, 1, 0.0F, false));
        catch_.cubeList.add(new ModelBox(catch_, 15, 76, -4.2071F, -22.4087F, -2.8003F, 1, 1, 1, 0.0F, true));
        catch_.cubeList.add(new ModelBox(catch_, 15, 76, -3.5F, -23.1158F, -2.8003F, 1, 1, 1, 0.0F, false));
        catch_.cubeList.add(new ModelBox(catch_, 15, 76, -3.5F, -21.7016F, -2.8003F, 1, 1, 1, 0.0F, false));

        bone7 = new ModelRenderer(this);
        bone7.setRotationPoint(1.5F, 3.6F, 3.6F);
        catch_.addChild(bone7);


        bone8 = new ModelRenderer(this);
        bone8.setRotationPoint(0.0F, 0.0F, 0.0F);
        bone7.addChild(bone8);
        setRotationAngle(bone8, 0.0F, -0.7854F, 0.0F);
        bone8.cubeList.add(new ModelBox(bone8, 15, 76, -7.8541F, -26.0087F, -0.7831F, 1, 1, 1, 0.0F, false));
        bone8.cubeList.add(new ModelBox(bone8, 15, 76, -7.147F, -26.0087F, -1.4902F, 1, 1, 1, 0.0F, false));
        bone8.cubeList.add(new ModelBox(bone8, 15, 76, -8.5612F, -26.0087F, -1.4902F, 1, 1, 1, 0.0F, true));
        bone8.cubeList.add(new ModelBox(bone8, 15, 76, -7.8541F, -26.0087F, -2.1973F, 1, 1, 1, 0.0F, false));

        bone6 = new ModelRenderer(this);
        bone6.setRotationPoint(0.0F, -0.7415F, 4.8071F);
        catch_.addChild(bone6);
        setRotationAngle(bone6, 0.0F, 0.0F, -0.7854F);
        bone6.cubeList.add(new ModelBox(bone6, 15, 76, 13.0533F, -17.5888F, -7.6074F, 1, 1, 1, 0.0F, false));
        bone6.cubeList.add(new ModelBox(bone6, 15, 76, 12.3462F, -16.8817F, -7.6074F, 1, 1, 1, 0.0F, false));
        bone6.cubeList.add(new ModelBox(bone6, 15, 76, 12.3462F, -18.2959F, -7.6074F, 1, 1, 1, 0.0F, false));
        bone6.cubeList.add(new ModelBox(bone6, 15, 76, 11.6391F, -17.5888F, -7.6074F, 1, 1, 1, 0.0F, true));

        rotatingPart3 = new ModelRenderer(this);
        rotatingPart3.setRotationPoint(0.0F, 21.8743F, 11.7074F);
        bolt.addChild(rotatingPart3);


        bone5 = new ModelRenderer(this);
        bone5.setRotationPoint(0.0F, 0.0F, 0.0F);
        rotatingPart3.addChild(bone5);
        setRotationAngle(bone5, -0.7854F, 0.0F, 0.0F);
        bone5.cubeList.add(new ModelBox(bone5, 15, 76, -3.5F, -11.1125F, -21.5781F, 1, 1, 1, 0.0F, false));
        bone5.cubeList.add(new ModelBox(bone5, 15, 76, -3.5F, -11.8196F, -20.871F, 1, 1, 1, 0.0F, false));
        bone5.cubeList.add(new ModelBox(bone5, 15, 76, -3.5F, -10.4054F, -20.871F, 1, 1, 1, 0.0F, false));
        bone5.cubeList.add(new ModelBox(bone5, 15, 76, -3.5F, -11.1125F, -20.1639F, 1, 1, 1, 0.0F, false));

        stock = new ModelRenderer(this);
        stock.setRotationPoint(0.0F, 24.0F, 0.0F);


        bone9 = new ModelRenderer(this);
        bone9.setRotationPoint(43.0F, -15.99F, 1.19F);
        stock.addChild(bone9);
        setRotationAngle(bone9, 0.0F, 0.0F, -0.1309F);
        bone9.cubeList.add(new ModelBox(bone9, 89, 149, -8.0F, -2.0F, -5.19F, 3, 2, 1, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 22, 93, -5.1516F, -2.502F, -5.69F, 4, 3, 1, 0.0F, false));

        bone29 = new ModelRenderer(this);
        bone29.setRotationPoint(43.0F, -15.99F, 1.19F);
        stock.addChild(bone29);
        setRotationAngle(bone29, 0.0436F, 0.0F, -0.1309F);
        bone29.cubeList.add(new ModelBox(bone29, 89, 149, -13.5241F, -1.9374F, -5.1927F, 6, 2, 1, 0.0F, false));

        bone41 = new ModelRenderer(this);
        bone41.setRotationPoint(41.92F, -15.15F, 2.87F);
        stock.addChild(bone41);
        setRotationAngle(bone41, 0.0175F, 0.0F, -0.0873F);
        bone41.cubeList.add(new ModelBox(bone41, 89, 149, -13.5241F, -1.9374F, -5.1927F, 1, 2, 2, 0.0F, false));

        bone49 = new ModelRenderer(this);
        bone49.setRotationPoint(70.92F, -15.15F, 2.87F);
        stock.addChild(bone49);
        setRotationAngle(bone49, 0.0175F, 0.0F, -0.0873F);
        bone49.cubeList.add(new ModelBox(bone49, 89, 149, -20.0554F, -5.8936F, -5.5707F, 1, 2, 6, 0.0F, false));

        bone50 = new ModelRenderer(this);
        bone50.setRotationPoint(68.21F, -14.42F, 1.66F);
        stock.addChild(bone50);
        setRotationAngle(bone50, 0.0175F, 0.0F, -0.0873F);
        bone50.cubeList.add(new ModelBox(bone50, 89, 149, -27.0554F, -5.8936F, -5.5707F, 8, 2, 1, 0.0F, false));

        bone52 = new ModelRenderer(this);
        bone52.setRotationPoint(56.21F, -11.19F, 2.65F);
        stock.addChild(bone52);
        setRotationAngle(bone52, 0.0175F, -0.0698F, 0.0349F);
        bone52.cubeList.add(new ModelBox(bone52, 89, 149, -23.7598F, -5.6986F, -5.3141F, 5, 2, 1, 0.0F, false));

        bone42 = new ModelRenderer(this);
        bone42.setRotationPoint(42.31F, -15.15F, 2.87F);
        stock.addChild(bone42);
        setRotationAngle(bone42, 0.0175F, 0.1047F, -0.0873F);
        bone42.cubeList.add(new ModelBox(bone42, 89, 149, -13.5241F, -1.9374F, -5.1927F, 1, 2, 4, 0.0F, false));

        bone44 = new ModelRenderer(this);
        bone44.setRotationPoint(30.1459F, -14.7774F, 2.1173F);
        stock.addChild(bone44);
        setRotationAngle(bone44, 0.0175F, -0.0524F, -0.0873F);
        bone44.cubeList.add(new ModelBox(bone44, 89, 149, -0.5856F, -0.9234F, 0.9705F, 2, 2, 1, 0.0F, false));

        bone45 = new ModelRenderer(this);
        bone45.setRotationPoint(31.9659F, -15.0474F, 2.2173F);
        stock.addChild(bone45);
        setRotationAngle(bone45, 0.0F, 0.0F, -0.1745F);
        bone45.cubeList.add(new ModelBox(bone45, 89, 149, -0.5856F, -0.9234F, 0.9705F, 6, 2, 1, 0.0F, false));

        bone47 = new ModelRenderer(this);
        bone47.setRotationPoint(43.4359F, -17.1774F, 2.2173F);
        stock.addChild(bone47);
        setRotationAngle(bone47, -0.0349F, 0.0F, -0.2269F);
        bone47.cubeList.add(new ModelBox(bone47, 89, 149, -0.644F, -0.6702F, 0.9793F, 6, 2, 1, 0.0F, false));

        bone48 = new ModelRenderer(this);
        bone48.setRotationPoint(48.5059F, -17.1774F, 2.2173F);
        stock.addChild(bone48);
        setRotationAngle(bone48, -0.0349F, 0.1309F, -0.1571F);
        bone48.cubeList.add(new ModelBox(bone48, 89, 149, -0.009F, -1.7017F, 1.0906F, 3, 2, 1, 0.0F, false));

        bone51 = new ModelRenderer(this);
        bone51.setRotationPoint(48.2959F, -16.9974F, -2.2273F);
        stock.addChild(bone51);
        setRotationAngle(bone51, 0.0349F, -0.2618F, -0.1571F);
        bone51.cubeList.add(new ModelBox(bone51, 89, 149, -0.009F, -1.7017F, -2.0906F, 3, 2, 1, 0.0F, false));

        bone46 = new ModelRenderer(this);
        bone46.setRotationPoint(40.1203F, -16.3408F, 3.6877F);
        stock.addChild(bone46);
        setRotationAngle(bone46, 0.0F, 0.0524F, -0.1396F);
        bone46.cubeList.add(new ModelBox(bone46, 89, 149, -3.0F, -1.0F, -0.5F, 6, 2, 1, 0.0F, false));

        bone40 = new ModelRenderer(this);
        bone40.setRotationPoint(38.52F, -15.29F, 7.29F);
        stock.addChild(bone40);
        setRotationAngle(bone40, 0.0436F, -0.5236F, -0.1309F);
        bone40.cubeList.add(new ModelBox(bone40, 89, 149, -13.5241F, -1.9374F, -5.1927F, 1, 2, 2, 0.0F, false));

        bone43 = new ModelRenderer(this);
        bone43.setRotationPoint(38.52F, -14.36F, -7.29F);
        stock.addChild(bone43);
        setRotationAngle(bone43, -0.0436F, 0.5236F, -0.0436F);
        bone43.cubeList.add(new ModelBox(bone43, 89, 149, -13.5241F, -1.9374F, 3.1927F, 1, 2, 2, 0.0F, false));

        addEntry(AnimationElement.MAGAZINE, stack -> magazine);
        addEntry(AnimationElement.BOLT, stack -> bolt);
        addEntry(AnimationElement.CHARGING, stack -> bolt_case);
        addEntry(AnimationElement.BULLET, stack -> bullet);
    }
}

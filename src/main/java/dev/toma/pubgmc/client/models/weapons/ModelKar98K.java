package dev.toma.pubgmc.client.models.weapons;

import dev.toma.pubgmc.client.animation.AnimationElement;
import dev.toma.pubgmc.client.util.ModelTransformationHelper;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.item.ItemStack;

public class ModelKar98K extends ModelGun {

    private final ModelRenderer kar98k;
    private final ModelRenderer bone13;
    private final ModelRenderer bone16;
    private final ModelRenderer bone20;
    private final ModelRenderer bone17;
    private final ModelRenderer bone19;
    private final ModelRenderer bone18;
    private final ModelRenderer bone14;
    private final ModelRenderer bone15;
    private final ModelRenderer bone12;
    private final ModelRenderer bone10;
    private final ModelRenderer bone11;
    private final ModelRenderer bone9;
    private final ModelRenderer bone7;
    private final ModelRenderer bone8;
    private final ModelRenderer bolt;
    private final ModelRenderer bone3;
    private final ModelRenderer bone6;
    private final ModelRenderer bone5;
    private final ModelRenderer bone4;
    private final ModelRenderer boltcarrier;
    private final ModelRenderer bone2;
    private final ModelRenderer stock;
    private final ModelRenderer bone21;
    private final ModelRenderer bone22;
    private final ModelRenderer bone23;
    private final ModelRenderer bone24;
    private final ModelRenderer bone25;
    private final ModelRenderer bone26;
    private final ModelRenderer bone27;
    private final ModelRenderer bone28;
    private final ModelRenderer bone29;
    private final ModelRenderer bone30;
    private final ModelRenderer bone31;
    private final ModelRenderer bone32;
    private final ModelRenderer bone33;
    private final ModelRenderer bone34;
    private final ModelRenderer bone35;
    private final ModelRenderer bone36;
    private final ModelRenderer bone37;
    private final ModelRenderer bone38;
    private final ModelRenderer bone39;
    private final ModelRenderer bone40;
    private final ModelRenderer bone;
    private final ModelRenderer bone41;
    private final ModelRenderer bone42;
    private final ModelRenderer bullets;
    private final ModelRenderer bullet;

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }

    @Override
    public void renderModel(ItemStack stack) {
        kar98k.render(1f);
        if(hasBulletLoops(stack))
            stock.render(1.0F);
    }

    @Override
    public void transformModel() {
        ModelTransformationHelper.defaultSRTransform();
        GlStateManager.scale(0.6999999, 0.6999999, 0.6999999);
        GlStateManager.translate(0.0, -18, 3.0);
    }

    public ModelKar98K() {
        textureWidth = 512;
        textureHeight = 512;

        kar98k = new ModelRenderer(this);
        kar98k.setRotationPoint(0.0F, 24.0F, 0.0F);
        kar98k.cubeList.add(new ModelBox(kar98k, 142, 156, -2.6188F, -7.2929F, -2.0F, 5, 7, 10, 0.0F, false));
        kar98k.cubeList.add(new ModelBox(kar98k, 142, 156, -2.6188F, -7.2929F, 8.0F, 5, 7, 11, 0.0F, false));
        kar98k.cubeList.add(new ModelBox(kar98k, 142, 156, -2.6188F, -4.6952F, 25.8984F, 5, 3, 5, 0.0F, false));
        kar98k.cubeList.add(new ModelBox(kar98k, 142, 156, -1.6188F, -5.6952F, 25.8984F, 3, 1, 5, 0.0F, false));
        kar98k.cubeList.add(new ModelBox(kar98k, 142, 156, -2.6188F, -6.3814F, 34.3138F, 5, 3, 2, 0.0F, false));
        kar98k.cubeList.add(new ModelBox(kar98k, 142, 156, -1.6188F, -7.3814F, 34.3138F, 3, 1, 2, 0.0F, false));
        kar98k.cubeList.add(new ModelBox(kar98k, 142, 156, -2.6188F, 0.0561F, 28.3138F, 5, 6, 11, 0.0F, false));
        kar98k.cubeList.add(new ModelBox(kar98k, 142, 156, -2.6188F, 5.0561F, 39.3138F, 5, 1, 5, 0.0F, false));
        kar98k.cubeList.add(new ModelBox(kar98k, 142, 156, -2.6188F, 1.6186F, 64.0559F, 5, 18, 8, 0.0F, false));
        kar98k.cubeList.add(new ModelBox(kar98k, 142, 156, -1.6188F, 0.6186F, 64.0559F, 3, 1, 8, 0.0F, false));
        kar98k.cubeList.add(new ModelBox(kar98k, 142, 156, -2.6188F, -7.2929F, -14.0F, 5, 7, 1, 0.0F, false));
        kar98k.cubeList.add(new ModelBox(kar98k, 142, 156, -2.6188F, -7.2929F, -13.0F, 1, 7, 11, 0.0F, false));
        kar98k.cubeList.add(new ModelBox(kar98k, 142, 156, 1.3812F, -7.2929F, -13.0F, 1, 7, 11, 0.0F, true));
        kar98k.cubeList.add(new ModelBox(kar98k, 5, 158, 1.0F, -11.0312F, -14.0F, 1, 4, 1, 0.0F, false));
        kar98k.cubeList.add(new ModelBox(kar98k, 5, 158, -1.0F, -11.0312F, -14.0F, 2, 1, 1, 0.0F, false));
        kar98k.cubeList.add(new ModelBox(kar98k, 5, 158, -1.0F, -8.0312F, -14.0F, 2, 1, 1, 0.0F, false));
        kar98k.cubeList.add(new ModelBox(kar98k, 5, 158, -2.0F, -11.0312F, -14.0F, 1, 4, 1, 0.0F, false));
        kar98k.cubeList.add(new ModelBox(kar98k, 12, 162, -1.6188F, -12.0312F, -23.0F, 3, 1, 8, 0.0F, false));
        kar98k.cubeList.add(new ModelBox(kar98k, 12, 162, -2.1188F, -13.0312F, -17.0F, 4, 1, 1, 0.0F, false));
        kar98k.cubeList.add(new ModelBox(kar98k, 0, 93, 1.0F, -11.0312F, -2.0F, 1, 4, 2, 0.0F, false));
        kar98k.cubeList.add(new ModelBox(kar98k, 0, 93, -2.2376F, -11.0312F, -2.0F, 1, 4, 2, 0.0F, true));
        kar98k.cubeList.add(new ModelBox(kar98k, 0, 93, 1.0F, -11.0312F, 10.0F, 1, 4, 2, 0.0F, false));
        kar98k.cubeList.add(new ModelBox(kar98k, 0, 93, -2.2376F, -11.0312F, 10.0F, 1, 4, 2, 0.0F, true));
        kar98k.cubeList.add(new ModelBox(kar98k, 0, 93, -1.6188F, -12.0312F, -2.0F, 3, 1, 14, 0.0F, false));
        kar98k.cubeList.add(new ModelBox(kar98k, 0, 93, -1.9118F, -12.1952F, -2.0F, 1, 1, 14, 0.0F, false));
        kar98k.cubeList.add(new ModelBox(kar98k, 0, 93, 0.6742F, -12.1952F, -2.0F, 1, 1, 14, 0.0F, true));
        kar98k.cubeList.add(new ModelBox(kar98k, 0, 93, -2.0F, -7.9531F, 14.0F, 1, 1, 5, 0.0F, false));
        kar98k.cubeList.add(new ModelBox(kar98k, 0, 93, 1.0F, -7.9531F, 14.0F, 1, 1, 5, 0.0F, false));
        kar98k.cubeList.add(new ModelBox(kar98k, 142, 156, -2.6188F, -10.2929F, -25.0F, 5, 10, 11, 0.0F, false));
        kar98k.cubeList.add(new ModelBox(kar98k, 142, 156, -2.6188F, -10.2929F, -36.0F, 5, 4, 11, 0.0F, false));
        kar98k.cubeList.add(new ModelBox(kar98k, 142, 156, -2.6188F, -10.2929F, -47.0F, 5, 4, 11, 0.0F, false));
        kar98k.cubeList.add(new ModelBox(kar98k, 142, 156, -2.6188F, -10.2929F, -58.0F, 5, 4, 11, 0.0F, false));
        kar98k.cubeList.add(new ModelBox(kar98k, 142, 156, -2.6188F, -10.2929F, -69.0F, 5, 4, 11, 0.0F, false));
        kar98k.cubeList.add(new ModelBox(kar98k, 142, 156, -2.6188F, -6.2929F, -69.0F, 5, 2, 6, 0.0F, false));
        kar98k.cubeList.add(new ModelBox(kar98k, 142, 156, -2.1188F, -9.7929F, -82.0F, 1, 4, 11, 0.0F, false));
        kar98k.cubeList.add(new ModelBox(kar98k, 142, 156, 0.8812F, -9.7929F, -82.0F, 1, 4, 11, 0.0F, false));
        kar98k.cubeList.add(new ModelBox(kar98k, 142, 156, -2.6188F, -10.2929F, -71.0F, 5, 6, 2, 0.0F, false));
        kar98k.cubeList.add(new ModelBox(kar98k, 142, 156, -1.6188F, -11.2929F, -25.0F, 3, 1, 11, 0.0F, false));
        kar98k.cubeList.add(new ModelBox(kar98k, 142, 156, -1.6188F, -11.2929F, -36.0F, 3, 1, 11, 0.0F, false));
        kar98k.cubeList.add(new ModelBox(kar98k, 142, 156, -1.6188F, -11.2929F, -47.0F, 3, 1, 11, 0.0F, false));
        kar98k.cubeList.add(new ModelBox(kar98k, 142, 156, -1.6188F, -11.2929F, -58.0F, 3, 1, 11, 0.0F, false));
        kar98k.cubeList.add(new ModelBox(kar98k, 142, 156, -1.6188F, -11.2929F, -69.0F, 3, 1, 11, 0.0F, false));
        kar98k.cubeList.add(new ModelBox(kar98k, 142, 156, -1.6188F, -4.2929F, -69.0F, 3, 1, 7, 0.0F, false));
        kar98k.cubeList.add(new ModelBox(kar98k, 142, 156, -1.1188F, -10.7929F, -82.0F, 2, 1, 11, 0.0F, false));
        kar98k.cubeList.add(new ModelBox(kar98k, 0, 12, -1.1188F, -9.9062F, -108.0F, 2, 2, 29, 0.0F, false));
        kar98k.cubeList.add(new ModelBox(kar98k, 12, 162, -1.6188F, -9.9062F, -107.0F, 3, 2, 3, 0.0F, false));
        kar98k.cubeList.add(new ModelBox(kar98k, 12, 162, -1.1188F, -10.4062F, -107.0F, 2, 3, 3, 0.0F, false));
        kar98k.cubeList.add(new ModelBox(kar98k, 12, 162, -0.6188F, -13.4062F, -106.5F, 1, 3, 2, 0.0F, false));
        kar98k.cubeList.add(new ModelBox(kar98k, 0, 12, -1.1188F, -7.7148F, -90.0F, 2, 2, 11, 0.0F, false));
        kar98k.cubeList.add(new ModelBox(kar98k, 142, 156, -1.1188F, -5.7929F, -82.0F, 2, 1, 11, 0.0F, false));
        kar98k.cubeList.add(new ModelBox(kar98k, 142, 156, -1.6188F, -11.2929F, -71.0F, 3, 1, 2, 0.0F, false));
        kar98k.cubeList.add(new ModelBox(kar98k, 142, 156, -1.6188F, -4.2929F, -71.0F, 3, 1, 2, 0.0F, false));
        kar98k.cubeList.add(new ModelBox(kar98k, 142, 156, -1.6188F, -0.2929F, -25.0F, 3, 1, 11, 0.0F, false));
        kar98k.cubeList.add(new ModelBox(kar98k, 142, 156, -1.6188F, -0.2929F, -14.0F, 3, 1, 11, 0.0F, false));
        kar98k.cubeList.add(new ModelBox(kar98k, 142, 156, -1.6188F, -0.2929F, -3.0F, 3, 1, 11, 0.0F, false));
        kar98k.cubeList.add(new ModelBox(kar98k, 142, 156, -1.6188F, -0.2929F, 8.0F, 3, 1, 13, 0.0F, false));
        kar98k.cubeList.add(new ModelBox(kar98k, 17, 154, -1.1188F, -0.0129F, -0.5F, 2, 1, 12, 0.0F, false));
        kar98k.cubeList.add(new ModelBox(kar98k, 142, 156, -1.6188F, -7.2929F, 19.0F, 3, 2, 4, 0.0F, false));

        bone13 = new ModelRenderer(this);
        bone13.setRotationPoint(5.3812F, 0.0F, 0.0F);
        kar98k.addChild(bone13);
        setRotationAngle(bone13, -0.3491F, 0.0F, 0.0F);
        bone13.cubeList.add(new ModelBox(bone13, 142, 156, -8.0F, -13.3514F, 15.3598F, 5, 7, 9, 0.0F, false));
        bone13.cubeList.add(new ModelBox(bone13, 142, 156, -7.0F, -14.3514F, 15.3598F, 3, 1, 9, 0.0F, false));

        bone16 = new ModelRenderer(this);
        bone16.setRotationPoint(5.3812F, 14.832F, 17.1758F);
        kar98k.addChild(bone16);
        setRotationAngle(bone16, 0.4363F, 0.0F, 0.0F);
        bone16.cubeList.add(new ModelBox(bone16, 142, 156, -8.0F, -11.9831F, 18.4975F, 5, 7, 6, 0.0F, false));
        bone16.cubeList.add(new ModelBox(bone16, 142, 156, -7.0F, -12.9831F, 20.4975F, 3, 1, 4, 0.0F, false));

        bone20 = new ModelRenderer(this);
        bone20.setRotationPoint(-5.3812F, -12.2176F, 22.3737F);
        bone16.addChild(bone20);
        setRotationAngle(bone20, 0.0F, 0.0F, 0.7854F);
        bone20.cubeList.add(new ModelBox(bone20, 142, 156, 0.4354F, -1.5179F, -1.8762F, 1, 1, 4, 0.0F, false));
        bone20.cubeList.add(new ModelBox(bone20, 142, 156, -1.6859F, 0.6034F, -1.8762F, 1, 1, 4, 0.0F, false));
        bone20.cubeList.add(new ModelBox(bone20, 142, 156, -1.6859F, 1.0176F, -1.8762F, 1, 1, 4, 0.0F, false));
        bone20.cubeList.add(new ModelBox(bone20, 142, 156, 0.8496F, -1.5179F, -1.8762F, 1, 1, 4, 0.0F, false));

        bone17 = new ModelRenderer(this);
        bone17.setRotationPoint(5.3812F, 14.832F, 26.1758F);
        kar98k.addChild(bone17);
        setRotationAngle(bone17, -0.2618F, 0.0F, 0.0F);
        bone17.cubeList.add(new ModelBox(bone17, 142, 156, -8.0F, -23.1144F, 4.3021F, 5, 7, 11, 0.0F, false));
        bone17.cubeList.add(new ModelBox(bone17, 142, 156, -7.0F, -24.1144F, 4.3021F, 3, 1, 11, 0.0F, false));
        bone17.cubeList.add(new ModelBox(bone17, 142, 156, -8.0F, -23.1144F, 15.3021F, 5, 7, 11, 0.0F, false));
        bone17.cubeList.add(new ModelBox(bone17, 142, 156, -7.0F, -24.1144F, 15.3021F, 3, 1, 11, 0.0F, false));
        bone17.cubeList.add(new ModelBox(bone17, 142, 156, -8.0F, -23.1144F, 26.3021F, 5, 9, 11, 0.0F, false));
        bone17.cubeList.add(new ModelBox(bone17, 142, 156, -7.0F, -24.1144F, 26.3021F, 3, 1, 11, 0.0F, false));

        bone19 = new ModelRenderer(this);
        bone19.setRotationPoint(0.0F, 0.0F, 0.0F);
        bone17.addChild(bone19);
        setRotationAngle(bone19, 0.0F, 0.0F, 0.7854F);
        bone19.cubeList.add(new ModelBox(bone19, 142, 156, -19.8799F, -14.223F, 4.3021F, 1, 1, 11, 0.0F, false));
        bone19.cubeList.add(new ModelBox(bone19, 142, 156, -19.8799F, -14.223F, 15.3021F, 1, 1, 11, 0.0F, false));
        bone19.cubeList.add(new ModelBox(bone19, 142, 156, -19.8799F, -14.223F, 26.3021F, 1, 1, 9, 0.0F, false));
        bone19.cubeList.add(new ModelBox(bone19, 142, 156, -22.0012F, -12.1017F, 26.3021F, 1, 1, 9, 0.0F, false));
        bone19.cubeList.add(new ModelBox(bone19, 142, 156, -22.0012F, -12.1017F, 15.3021F, 1, 1, 11, 0.0F, false));
        bone19.cubeList.add(new ModelBox(bone19, 142, 156, -22.0012F, -12.1017F, 4.3021F, 1, 1, 11, 0.0F, false));
        bone19.cubeList.add(new ModelBox(bone19, 142, 156, -19.4656F, -14.223F, 4.3021F, 1, 1, 11, 0.0F, false));
        bone19.cubeList.add(new ModelBox(bone19, 142, 156, -19.4656F, -14.223F, 15.3021F, 1, 1, 11, 0.0F, false));
        bone19.cubeList.add(new ModelBox(bone19, 142, 156, -19.4656F, -14.223F, 26.3021F, 1, 1, 9, 0.0F, false));
        bone19.cubeList.add(new ModelBox(bone19, 142, 156, -22.0012F, -11.6875F, 26.3021F, 1, 1, 9, 0.0F, false));
        bone19.cubeList.add(new ModelBox(bone19, 142, 156, -22.0012F, -11.6875F, 15.3021F, 1, 1, 11, 0.0F, false));
        bone19.cubeList.add(new ModelBox(bone19, 142, 156, -22.0012F, -11.6875F, 4.3021F, 1, 1, 11, 0.0F, false));

        bone18 = new ModelRenderer(this);
        bone18.setRotationPoint(5.3812F, 14.832F, 26.1758F);
        kar98k.addChild(bone18);
        setRotationAngle(bone18, -0.5236F, 0.0F, 0.0F);
        bone18.cubeList.add(new ModelBox(bone18, 142, 156, -8.0F, -21.7947F, 2.1985F, 5, 7, 11, 0.0F, false));
        bone18.cubeList.add(new ModelBox(bone18, 142, 156, -8.0F, -22.7947F, 13.1985F, 5, 8, 11, 0.0F, false));
        bone18.cubeList.add(new ModelBox(bone18, 142, 156, -8.0F, -22.7947F, 24.1985F, 5, 8, 11, 0.0F, false));

        bone14 = new ModelRenderer(this);
        bone14.setRotationPoint(5.3812F, -7.918F, 0.0F);
        kar98k.addChild(bone14);
        setRotationAngle(bone14, -1.0472F, 0.0F, 0.0F);
        bone14.cubeList.add(new ModelBox(bone14, 142, 156, -8.0F, -23.0157F, 18.1439F, 5, 7, 10, 0.0F, false));
        bone14.cubeList.add(new ModelBox(bone14, 142, 156, -7.0F, -23.0157F, 28.1439F, 3, 7, 1, 0.0F, false));

        bone15 = new ModelRenderer(this);
        bone15.setRotationPoint(0.0F, 0.0F, 0.0F);
        bone14.addChild(bone15);
        setRotationAngle(bone15, 0.0F, 0.7854F, 0.0F);
        bone15.cubeList.add(new ModelBox(bone15, 142, 156, -23.022F, -23.0157F, 16.7795F, 1, 7, 1, 0.0F, false));
        bone15.cubeList.add(new ModelBox(bone15, 142, 156, -23.4362F, -23.0157F, 16.7795F, 1, 7, 1, 0.0F, false));
        bone15.cubeList.add(new ModelBox(bone15, 142, 156, -25.5575F, -23.0157F, 14.6581F, 1, 7, 1, 0.0F, false));
        bone15.cubeList.add(new ModelBox(bone15, 142, 156, -25.5575F, -23.0157F, 14.2439F, 1, 7, 1, 0.0F, false));

        bone12 = new ModelRenderer(this);
        bone12.setRotationPoint(1.0F, -12.2383F, -15.0F);
        kar98k.addChild(bone12);
        setRotationAngle(bone12, 0.1745F, 0.0F, 0.0F);
        bone12.cubeList.add(new ModelBox(bone12, 12, 162, -0.6188F, -1.1851F, -7.9146F, 1, 1, 8, 0.0F, false));
        bone12.cubeList.add(new ModelBox(bone12, 12, 162, -2.6188F, -1.1851F, -7.9146F, 1, 1, 8, 0.0F, true));

        bone10 = new ModelRenderer(this);
        bone10.setRotationPoint(0.0F, -3.0F, -30.5F);
        kar98k.addChild(bone10);
        setRotationAngle(bone10, -0.1047F, 0.0F, 0.0F);
        bone10.cubeList.add(new ModelBox(bone10, 142, 156, -2.6198F, -3.8826F, -5.2471F, 5, 6, 11, 0.0F, false));
        bone10.cubeList.add(new ModelBox(bone10, 142, 156, -2.6198F, -2.8826F, -16.2471F, 5, 5, 11, 0.0F, false));
        bone10.cubeList.add(new ModelBox(bone10, 142, 156, -2.6198F, -1.8826F, -27.2471F, 5, 4, 11, 0.0F, false));
        bone10.cubeList.add(new ModelBox(bone10, 142, 156, -2.6198F, -0.8826F, -33.2471F, 5, 3, 6, 0.0F, false));
        bone10.cubeList.add(new ModelBox(bone10, 142, 156, -1.6188F, 2.1174F, -33.2471F, 3, 1, 13, 0.0F, false));
        bone10.cubeList.add(new ModelBox(bone10, 142, 156, -1.6188F, 2.1174F, -20.2471F, 3, 1, 13, 0.0F, false));
        bone10.cubeList.add(new ModelBox(bone10, 142, 156, -1.6188F, 2.1174F, -7.2471F, 3, 1, 13, 0.0F, false));

        bone11 = new ModelRenderer(this);
        bone11.setRotationPoint(6.501F, 3.0F, 30.5F);
        bone10.addChild(bone11);
        setRotationAngle(bone11, 0.0F, 0.0F, -0.7854F);
        bone11.cubeList.add(new ModelBox(bone11, 142, 156, -3.289F, -4.5373F, -37.7471F, 1, 1, 13, 0.0F, false));
        bone11.cubeList.add(new ModelBox(bone11, 142, 156, -5.8252F, -7.0735F, -37.7471F, 1, 1, 13, 0.0F, false));
        bone11.cubeList.add(new ModelBox(bone11, 142, 156, -3.289F, -4.5373F, -50.7471F, 1, 1, 13, 0.0F, false));
        bone11.cubeList.add(new ModelBox(bone11, 142, 156, -5.8252F, -7.0735F, -50.7471F, 1, 1, 13, 0.0F, false));
        bone11.cubeList.add(new ModelBox(bone11, 142, 156, -3.289F, -4.5373F, -63.7471F, 1, 1, 13, 0.0F, false));
        bone11.cubeList.add(new ModelBox(bone11, 142, 156, -5.8252F, -7.0735F, -63.7471F, 1, 1, 13, 0.0F, false));
        bone11.cubeList.add(new ModelBox(bone11, 142, 156, -3.7032F, -4.5373F, -37.7471F, 1, 1, 13, 0.0F, false));
        bone11.cubeList.add(new ModelBox(bone11, 142, 156, -5.8245F, -6.6586F, -37.7471F, 1, 1, 13, 0.0F, false));
        bone11.cubeList.add(new ModelBox(bone11, 142, 156, -3.7032F, -4.5373F, -50.7471F, 1, 1, 13, 0.0F, false));
        bone11.cubeList.add(new ModelBox(bone11, 142, 156, -5.8245F, -6.6586F, -50.7471F, 1, 1, 13, 0.0F, false));
        bone11.cubeList.add(new ModelBox(bone11, 142, 156, -3.7032F, -4.5373F, -63.7471F, 1, 1, 13, 0.0F, false));
        bone11.cubeList.add(new ModelBox(bone11, 142, 156, -5.8245F, -6.6586F, -63.7471F, 1, 1, 13, 0.0F, false));

        bone9 = new ModelRenderer(this);
        bone9.setRotationPoint(5.3812F, 0.0F, 0.0F);
        kar98k.addChild(bone9);
        setRotationAngle(bone9, 0.0F, 0.0F, 0.7854F);
        bone9.cubeList.add(new ModelBox(bone9, 142, 156, -12.935F, -3.0355F, -25.0F, 1, 1, 11, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 142, 156, -12.935F, -3.0355F, -36.0F, 1, 1, 11, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 142, 156, -12.935F, -3.0355F, -47.0F, 1, 1, 11, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 142, 156, -12.935F, -3.0355F, -58.0F, 1, 1, 11, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 142, 156, -12.935F, -3.0355F, -69.0F, 1, 1, 11, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 142, 156, -12.935F, -3.0355F, -71.0F, 1, 1, 2, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 142, 156, -8.2782F, 1.6213F, -71.0F, 1, 1, 2, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 142, 156, -10.8137F, -5.1569F, -36.0F, 1, 1, 11, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 142, 156, -10.8137F, -5.1569F, -47.0F, 1, 1, 11, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 142, 156, -10.8137F, -5.1569F, -58.0F, 1, 1, 11, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 142, 156, -10.8137F, -5.1569F, -69.0F, 1, 1, 11, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 142, 156, -6.1568F, -0.9142F, -69.0F, 1, 1, 7, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 142, 156, -10.8137F, -5.1569F, -71.0F, 1, 1, 2, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 142, 156, -6.1568F, -0.5F, -71.0F, 1, 1, 2, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 142, 156, -10.3995F, -5.1569F, -36.0F, 1, 1, 11, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 142, 156, -10.3995F, -5.1569F, -47.0F, 1, 1, 11, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 142, 156, -10.3995F, -5.1569F, -58.0F, 1, 1, 11, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 142, 156, -10.3995F, -5.1569F, -69.0F, 1, 1, 11, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 142, 156, -6.1568F, -0.5F, -69.0F, 1, 1, 7, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 142, 156, -8.2782F, 1.6213F, -69.0F, 1, 1, 7, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 142, 156, -8.6924F, 1.6213F, -69.0F, 1, 1, 7, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 142, 156, -10.3995F, -4.4498F, -82.0F, 1, 1, 11, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 142, 156, -12.2279F, -2.6213F, -82.0F, 1, 1, 11, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 142, 156, -12.2279F, -3.0355F, -82.0F, 1, 1, 11, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 142, 156, -10.8137F, -4.4498F, -82.0F, 1, 1, 11, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 142, 156, -7.5711F, -1.2071F, -82.0F, 1, 1, 11, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 142, 156, -8.9853F, 0.2071F, -82.0F, 1, 1, 11, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 142, 156, -7.5711F, -1.6213F, -82.0F, 1, 1, 11, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 142, 156, -9.3995F, 0.2071F, -82.0F, 1, 1, 11, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 142, 156, -10.3995F, -5.1569F, -71.0F, 1, 1, 2, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 142, 156, -6.1568F, -0.9142F, -71.0F, 1, 1, 2, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 142, 156, -10.3995F, -5.1569F, -25.0F, 1, 1, 11, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 142, 156, -3.3284F, 1.9142F, -25.0F, 1, 1, 11, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 142, 156, -3.3284F, 1.9142F, -14.0F, 1, 1, 11, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 142, 156, -3.3284F, 1.9142F, -3.0F, 1, 1, 11, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 142, 156, -2.391F, 3.2659F, 62.0559F, 1, 1, 10, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 142, 156, -8.0478F, -2.391F, 34.3138F, 1, 1, 2, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 142, 156, -6.8556F, -1.1987F, 24.8984F, 1, 1, 6, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 142, 156, -8.9769F, 0.9226F, 24.8984F, 1, 1, 6, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 142, 156, -10.1691F, -0.2697F, 34.3138F, 1, 1, 2, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 142, 156, -10.1691F, 0.1446F, 34.3138F, 1, 1, 2, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 142, 156, -4.5123F, 5.3872F, 62.0559F, 1, 1, 10, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 142, 156, -1.9767F, 3.2659F, 65.0559F, 1, 1, 7, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 142, 156, -7.6336F, -2.391F, 34.3138F, 1, 1, 2, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 142, 156, -6.4413F, -1.1987F, 24.8984F, 1, 1, 6, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 142, 156, -8.9769F, 1.3368F, 24.8984F, 1, 1, 6, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 142, 156, -4.5123F, 5.8014F, 65.0559F, 1, 1, 7, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 142, 156, -3.3284F, 1.9142F, 8.0F, 1, 1, 11, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 142, 156, -3.3284F, 2.3284F, -25.0F, 1, 1, 11, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 142, 156, -3.3284F, 2.3284F, -14.0F, 1, 1, 11, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 142, 156, -3.3284F, 2.3284F, -3.0F, 1, 1, 11, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 142, 156, -3.3284F, 2.3284F, 8.0F, 1, 1, 13, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 142, 156, -5.4497F, 4.4497F, -25.0F, 1, 1, 11, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 142, 156, -5.4497F, 4.4497F, -14.0F, 1, 1, 11, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 142, 156, -5.4497F, 4.4497F, -3.0F, 1, 1, 11, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 142, 156, -5.4497F, 4.4497F, 8.0F, 1, 1, 13, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 142, 156, -5.8639F, 4.4497F, -25.0F, 1, 1, 11, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 142, 156, -5.8639F, 4.4497F, -14.0F, 1, 1, 11, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 142, 156, -5.8639F, 4.4497F, -3.0F, 1, 1, 11, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 142, 156, -5.8639F, 4.4497F, 8.0F, 1, 1, 11, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 142, 156, -10.8137F, -5.1569F, -25.0F, 1, 1, 11, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 142, 156, -12.935F, -2.6213F, -25.0F, 1, 1, 11, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 142, 156, -12.935F, -2.6213F, -36.0F, 1, 1, 11, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 142, 156, -12.935F, -2.6213F, -47.0F, 1, 1, 11, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 142, 156, -12.935F, -2.6213F, -58.0F, 1, 1, 11, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 142, 156, -12.935F, -2.6213F, -69.0F, 1, 1, 11, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 142, 156, -12.935F, -2.6213F, -71.0F, 1, 1, 2, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 142, 156, -8.6924F, 1.6213F, -71.0F, 1, 1, 2, 0.0F, false));

        bone7 = new ModelRenderer(this);
        bone7.setRotationPoint(5.9883F, 0.0F, 0.0F);
        kar98k.addChild(bone7);
        setRotationAngle(bone7, 0.0F, 0.2618F, 0.0F);
        bone7.cubeList.add(new ModelBox(bone7, 142, 156, -13.2314F, -7.2929F, 16.125F, 1, 2, 4, 0.0F, false));

        bone8 = new ModelRenderer(this);
        bone8.setRotationPoint(-6.0F, 0.0F, 0.0F);
        kar98k.addChild(bone8);
        setRotationAngle(bone8, 0.0F, -0.2618F, 0.0F);
        bone8.cubeList.add(new ModelBox(bone8, 142, 156, 12.0131F, -7.2929F, 16.1833F, 1, 2, 4, 0.0F, false));

        bolt = new ModelRenderer(this);
        bolt.setRotationPoint(2.0F, 25.0F, -1.0F);
        setRotationAngle(bolt, 0.0F, 0.0F, -0.3491F);
        bolt.cubeList.add(new ModelBox(bolt, 84, 17, -3.1343F, -11.5369F, 13.0F, 4, 2, 2, 0.0F, false));

        bone3 = new ModelRenderer(this);
        bone3.setRotationPoint(0.0F, 0.0F, 0.0F);
        bolt.addChild(bone3);
        setRotationAngle(bone3, 0.0F, 0.0F, -0.8727F);
        bone3.cubeList.add(new ModelBox(bone3, 84, 17, 3.8231F, -9.8169F, 13.0F, 3, 2, 2, 0.0F, false));
        bone3.cubeList.add(new ModelBox(bone3, 84, 17, 1.4089F, -9.8169F, 13.0F, 1, 2, 2, 0.0F, false));
        bone3.cubeList.add(new ModelBox(bone3, 84, 17, 2.116F, -9.8169F, 14.7071F, 2, 2, 1, 0.0F, false));
        bone3.cubeList.add(new ModelBox(bone3, 84, 17, 2.116F, -9.8169F, 12.2929F, 2, 2, 1, 0.0F, false));
        bone3.cubeList.add(new ModelBox(bone3, 84, 17, 2.116F, -10.524F, 13.0F, 2, 1, 2, 0.0F, false));
        bone3.cubeList.add(new ModelBox(bone3, 84, 17, 2.116F, -8.1098F, 13.0F, 2, 1, 2, 0.0F, false));

        bone6 = new ModelRenderer(this);
        bone6.setRotationPoint(0.0F, 0.0F, 0.0F);
        bone3.addChild(bone6);
        setRotationAngle(bone6, 0.0F, -0.7854F, 0.0F);
        bone6.cubeList.add(new ModelBox(bone6, 84, 17, 11.6028F, -9.8169F, 8.6104F, 1, 2, 1, 0.0F, false));
        bone6.cubeList.add(new ModelBox(bone6, 84, 17, 13.017F, -9.8169F, 7.1962F, 1, 2, 1, 0.0F, false));
        bone6.cubeList.add(new ModelBox(bone6, 84, 17, 10.1886F, -9.8169F, 7.1962F, 1, 2, 1, 0.0F, false));
        bone6.cubeList.add(new ModelBox(bone6, 84, 17, 11.6028F, -9.8169F, 5.782F, 1, 2, 1, 0.0F, false));

        bone5 = new ModelRenderer(this);
        bone5.setRotationPoint(0.0F, 0.0F, 0.0F);
        bone3.addChild(bone5);
        setRotationAngle(bone5, 0.7854F, 0.0F, 0.0F);
        bone5.cubeList.add(new ModelBox(bone5, 84, 17, 2.116F, 4.5792F, 15.634F, 2, 1, 1, 0.0F, false));
        bone5.cubeList.add(new ModelBox(bone5, 84, 17, 2.116F, 3.165F, 14.2198F, 2, 1, 1, 0.0F, false));
        bone5.cubeList.add(new ModelBox(bone5, 84, 17, 2.116F, 3.165F, 17.0482F, 2, 1, 1, 0.0F, false));
        bone5.cubeList.add(new ModelBox(bone5, 84, 17, 2.116F, 1.7508F, 15.634F, 2, 1, 1, 0.0F, false));

        bone4 = new ModelRenderer(this);
        bone4.setRotationPoint(0.0F, 0.0F, 0.0F);
        bone3.addChild(bone4);
        setRotationAngle(bone4, 0.0F, 0.0F, -0.7854F);
        bone4.cubeList.add(new ModelBox(bone4, 84, 17, 9.3521F, -4.5312F, 13.0F, 1, 1, 2, 0.0F, false));
        bone4.cubeList.add(new ModelBox(bone4, 84, 17, 7.9378F, -5.9454F, 13.0F, 1, 1, 2, 0.0F, false));
        bone4.cubeList.add(new ModelBox(bone4, 84, 17, 6.5236F, -4.5312F, 13.0F, 1, 1, 2, 0.0F, false));
        bone4.cubeList.add(new ModelBox(bone4, 84, 17, 7.9378F, -3.117F, 13.0F, 1, 1, 2, 0.0F, false));

        boltcarrier = new ModelRenderer(this);
        boltcarrier.setRotationPoint(0.0F, 15.3F, 21.0F);
        boltcarrier.cubeList.add(new ModelBox(boltcarrier, 84, 17, -1.0F, -1.5929F, -28.0F, 2, 3, 14, 0.0F, false));
        boltcarrier.cubeList.add(new ModelBox(boltcarrier, 84, 17, -1.0F, -0.5929F, -14.0F, 2, 2, 14, 0.0F, false));
        boltcarrier.cubeList.add(new ModelBox(boltcarrier, 84, 17, -0.2929F, -1.3F, -28.0F, 2, 2, 14, 0.0F, false));
        boltcarrier.cubeList.add(new ModelBox(boltcarrier, 84, 17, -0.2929F, -1.3F, -42.0F, 2, 2, 14, 0.0F, false));
        boltcarrier.cubeList.add(new ModelBox(boltcarrier, 84, 17, -0.2929F, -1.3F, -14.0F, 2, 2, 14, 0.0F, false));
        boltcarrier.cubeList.add(new ModelBox(boltcarrier, 84, 17, -1.7071F, -1.3F, -28.0F, 2, 2, 14, 0.0F, false));
        boltcarrier.cubeList.add(new ModelBox(boltcarrier, 84, 17, -1.7071F, -1.3F, -42.0F, 2, 2, 14, 0.0F, false));
        boltcarrier.cubeList.add(new ModelBox(boltcarrier, 84, 17, -1.7071F, -1.3F, -14.0F, 2, 2, 14, 0.0F, false));
        boltcarrier.cubeList.add(new ModelBox(boltcarrier, 84, 17, -1.0F, -2.0071F, -28.0F, 2, 2, 14, 0.0F, false));
        boltcarrier.cubeList.add(new ModelBox(boltcarrier, 84, 17, -1.0F, -2.0071F, -42.0F, 2, 2, 14, 0.0F, false));
        boltcarrier.cubeList.add(new ModelBox(boltcarrier, 84, 17, -1.0F, -0.5929F, -42.0F, 2, 2, 14, 0.0F, false));
        boltcarrier.cubeList.add(new ModelBox(boltcarrier, 84, 17, -1.0F, -2.0071F, -14.0F, 2, 2, 14, 0.0F, false));

        bone2 = new ModelRenderer(this);
        bone2.setRotationPoint(-2.0F, 9.7F, -22.0F);
        boltcarrier.addChild(bone2);
        setRotationAngle(bone2, 0.0F, 0.0F, -0.7854F);
        bone2.cubeList.add(new ModelBox(bone2, 84, 17, 7.9853F, -5.7426F, -6.0F, 1, 2, 14, 0.0F, false));
        bone2.cubeList.add(new ModelBox(bone2, 84, 17, 7.9853F, -5.7426F, -20.0F, 1, 2, 14, 0.0F, false));
        bone2.cubeList.add(new ModelBox(bone2, 84, 17, 7.9853F, -5.7426F, 8.0F, 1, 2, 14, 0.0F, false));
        bone2.cubeList.add(new ModelBox(bone2, 84, 17, 6.5711F, -6.1568F, -6.0F, 2, 1, 14, 0.0F, false));
        bone2.cubeList.add(new ModelBox(bone2, 84, 17, 6.5711F, -6.1568F, -20.0F, 2, 1, 14, 0.0F, false));
        bone2.cubeList.add(new ModelBox(bone2, 84, 17, 6.5711F, -6.1568F, 8.0F, 2, 1, 14, 0.0F, false));
        bone2.cubeList.add(new ModelBox(bone2, 84, 17, 8.3995F, -6.1568F, -6.0F, 2, 1, 14, 0.0F, false));
        bone2.cubeList.add(new ModelBox(bone2, 84, 17, 8.3995F, -6.1568F, -20.0F, 2, 1, 14, 0.0F, false));
        bone2.cubeList.add(new ModelBox(bone2, 84, 17, 8.3995F, -6.1568F, 8.0F, 2, 1, 14, 0.0F, false));
        bone2.cubeList.add(new ModelBox(bone2, 84, 17, 7.9853F, -7.5711F, -6.0F, 1, 2, 14, 0.0F, false));
        bone2.cubeList.add(new ModelBox(bone2, 84, 17, 7.9853F, -7.5711F, -20.0F, 1, 2, 14, 0.0F, false));
        bone2.cubeList.add(new ModelBox(bone2, 84, 17, 7.9853F, -7.5711F, 8.0F, 1, 2, 14, 0.0F, false));

        stock = new ModelRenderer(this);
        stock.setRotationPoint(0.0F, 24.0F, -2.0F);


        bone21 = new ModelRenderer(this);
        bone21.setRotationPoint(-0.5F, -2.5F, 53.5F);
        stock.addChild(bone21);
        setRotationAngle(bone21, -0.2618F, -0.1745F, 0.0698F);
        bone21.cubeList.add(new ModelBox(bone21, 212, 153, -1.0134F, -0.9991F, 5.4306F, 5, 1, 2, 0.0F, false));

        bone22 = new ModelRenderer(this);
        bone22.setRotationPoint(-0.5F, -2.75F, 52.66F);
        stock.addChild(bone22);
        setRotationAngle(bone22, -0.3142F, -0.1047F, 0.0F);
        bone22.cubeList.add(new ModelBox(bone22, 212, 153, -1.5547F, -1.3166F, 4.8841F, 5, 1, 2, 0.0F, false));

        bone23 = new ModelRenderer(this);
        bone23.setRotationPoint(-4.0066F, -2.2342F, 59.8428F);
        stock.addChild(bone23);
        setRotationAngle(bone23, -0.2705F, 0.1134F, 0.6545F);
        bone23.cubeList.add(new ModelBox(bone23, 212, 153, 1.2292F, -0.646F, -1.0725F, 1, 1, 2, 0.0F, false));

        bone24 = new ModelRenderer(this);
        bone24.setRotationPoint(-4.1366F, -2.4342F, 58.1528F);
        stock.addChild(bone24);
        setRotationAngle(bone24, -0.2705F, 0.0262F, 0.5672F);
        bone24.cubeList.add(new ModelBox(bone24, 212, 153, 1.2425F, -0.6747F, -1.0555F, 1, 1, 2, 0.0F, false));

        bone25 = new ModelRenderer(this);
        bone25.setRotationPoint(1.0434F, -2.7542F, 59.0128F);
        stock.addChild(bone25);
        setRotationAngle(bone25, -0.2705F, 0.2705F, 0.829F);
        bone25.cubeList.add(new ModelBox(bone25, 212, 153, 1.2146F, -0.5851F, -1.1058F, 1, 1, 4, 0.0F, false));

        bone26 = new ModelRenderer(this);
        bone26.setRotationPoint(-1.43F, -2.2F, 56.46F);
        stock.addChild(bone26);
        setRotationAngle(bone26, -0.576F, 0.0698F, 0.0F);
        bone26.cubeList.add(new ModelBox(bone26, 212, 153, 3.0015F, -1.1463F, 3.2526F, 1, 5, 2, 0.0F, false));

        bone27 = new ModelRenderer(this);
        bone27.setRotationPoint(-1.02F, -2.22F, 54.54F);
        stock.addChild(bone27);
        setRotationAngle(bone27, -0.4451F, -0.0611F, 0.0F);
        bone27.cubeList.add(new ModelBox(bone27, 212, 153, 3.1127F, -0.9499F, 2.6037F, 1, 5, 2, 0.0F, false));

        bone28 = new ModelRenderer(this);
        bone28.setRotationPoint(-0.96F, 2.29F, 53.7F);
        stock.addChild(bone28);
        setRotationAngle(bone28, -0.4538F, -0.0611F, 0.0524F);
        bone28.cubeList.add(new ModelBox(bone28, 212, 153, 3.0976F, -0.9433F, 2.605F, 1, 5, 2, 0.0F, false));

        bone29 = new ModelRenderer(this);
        bone29.setRotationPoint(2.7713F, 4.9076F, 55.3132F);
        stock.addChild(bone29);
        setRotationAngle(bone29, -0.5847F, -0.0611F, -0.0349F);
        bone29.cubeList.add(new ModelBox(bone29, 212, 153, -0.6083F, -2.7512F, -1.1583F, 1, 5, 2, 0.0F, false));

        bone30 = new ModelRenderer(this);
        bone30.setRotationPoint(2.7713F, 7.5776F, 53.7032F);
        stock.addChild(bone30);
        setRotationAngle(bone30, -0.5847F, 0.096F, -0.0349F);
        bone30.cubeList.add(new ModelBox(bone30, 212, 153, -0.8891F, -1.5516F, -0.9579F, 1, 6, 3, 0.0F, false));

        bone31 = new ModelRenderer(this);
        bone31.setRotationPoint(2.9934F, 11.7658F, 52.8428F);
        stock.addChild(bone31);
        setRotationAngle(bone31, -0.2705F, 0.5498F, 1.3526F);
        bone31.cubeList.add(new ModelBox(bone31, 212, 153, -0.4929F, 0.6502F, -2.4052F, 1, 2, 2, 0.0F, false));

        bone32 = new ModelRenderer(this);
        bone32.setRotationPoint(2.6362F, 12.0719F, 52.1305F);
        stock.addChild(bone32);
        setRotationAngle(bone32, -0.0524F, 0.5498F, 1.3526F);
        bone32.cubeList.add(new ModelBox(bone32, 212, 153, -0.7657F, -0.439F, -1.1599F, 1, 2, 2, 0.0F, false));

        bone33 = new ModelRenderer(this);
        bone33.setRotationPoint(2.8413F, 16.0276F, 55.3132F);
        stock.addChild(bone33);
        setRotationAngle(bone33, -0.5847F, -0.0611F, 0.096F);
        bone33.cubeList.add(new ModelBox(bone33, 212, 153, -6.476F, -2.1328F, -5.7264F, 5, 1, 2, 0.0F, false));

        bone34 = new ModelRenderer(this);
        bone34.setRotationPoint(-0.9886F, 11.3433F, 51.1138F);
        stock.addChild(bone34);
        setRotationAngle(bone34, -0.672F, 0.0262F, 0.096F);
        bone34.cubeList.add(new ModelBox(bone34, 212, 153, -2.6463F, -0.7169F, -1.1775F, 5, 1, 2, 0.0F, false));

        bone35 = new ModelRenderer(this);
        bone35.setRotationPoint(-0.9886F, 10.7833F, 51.1138F);
        stock.addChild(bone35);
        setRotationAngle(bone35, -0.4538F, -0.0611F, 0.096F);
        bone35.cubeList.add(new ModelBox(bone35, 212, 153, -2.459F, -2.7988F, 0.8357F, 1, 3, 2, 0.0F, false));

        bone36 = new ModelRenderer(this);
        bone36.setRotationPoint(-0.9886F, 10.7833F, 51.1138F);
        stock.addChild(bone36);
        setRotationAngle(bone36, -0.5847F, 0.0262F, 0.0087F);
        bone36.cubeList.add(new ModelBox(bone36, 212, 153, -2.6213F, -2.7416F, -1.1638F, 1, 3, 2, 0.0F, false));

        bone37 = new ModelRenderer(this);
        bone37.setRotationPoint(-0.7386F, 8.9533F, 54.0038F);
        stock.addChild(bone37);
        setRotationAngle(bone37, -0.5847F, 0.0262F, 0.0087F);
        bone37.cubeList.add(new ModelBox(bone37, 212, 153, -2.6213F, -5.7416F, -1.1638F, 1, 6, 2, 0.0F, false));

        bone38 = new ModelRenderer(this);
        bone38.setRotationPoint(-2.8486F, 6.4533F, 54.0038F);
        stock.addChild(bone38);
        setRotationAngle(bone38, -0.5847F, -0.1047F, 0.0087F);
        bone38.cubeList.add(new ModelBox(bone38, 212, 153, -0.6207F, -3.2503F, -1.1505F, 1, 6, 2, 0.0F, false));

        bone39 = new ModelRenderer(this);
        bone39.setRotationPoint(-2.8486F, 5.6233F, 53.0238F);
        stock.addChild(bone39);
        setRotationAngle(bone39, -0.4538F, -0.1047F, 0.0087F);
        bone39.cubeList.add(new ModelBox(bone39, 212, 153, 0.1412F, -9.0882F, 1.7885F, 1, 6, 2, 0.0F, false));

        bone40 = new ModelRenderer(this);
        bone40.setRotationPoint(-2.7068F, 1.383F, 57.0389F);
        stock.addChild(bone40);
        setRotationAngle(bone40, -0.4538F, -0.3665F, 0.1833F);
        bone40.cubeList.add(new ModelBox(bone40, 212, 153, -0.5318F, -2.9956F, -0.7827F, 1, 6, 1, 0.0F, false));

        bone = new ModelRenderer(this);
        bone.setRotationPoint(2.8F, 6.5F, 60.5F);
        stock.addChild(bone);
        setRotationAngle(bone, -0.5236F, 0.0F, 0.0F);
        bone.cubeList.add(new ModelBox(bone, 18, 86, -0.5F, -0.5F, -15.5F, 1, 3, 21, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 18, 86, 0.366F, 0.0F, -15.5F, 1, 2, 21, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 105, 181, 0.866F, 0.5F, -14.7F, 1, 1, 1, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 105, 181, 0.866F, 0.5F, -10.7F, 1, 1, 1, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 105, 181, 0.866F, 0.5F, -6.7F, 1, 1, 1, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 105, 181, 0.866F, 0.5F, -2.7F, 1, 1, 1, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 105, 181, 0.866F, 0.5F, 1.3F, 1, 1, 1, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 105, 181, 0.866F, 0.5F, -12.3F, 1, 1, 1, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 105, 181, 0.866F, 0.5F, -8.3F, 1, 1, 1, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 105, 181, 0.866F, 0.5F, -4.3F, 1, 1, 1, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 105, 181, 0.866F, 0.5F, -0.3F, 1, 1, 1, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 105, 181, 0.866F, 0.5F, 3.7F, 1, 1, 1, 0.0F, false));

        bone41 = new ModelRenderer(this);
        bone41.setRotationPoint(0.0F, 1.0F, -5.0F);
        bone.addChild(bone41);
        setRotationAngle(bone41, 0.0F, 0.0F, 0.5236F);
        bone41.cubeList.add(new ModelBox(bone41, 18, 86, -0.317F, -1.549F, -10.5F, 1, 1, 1, 0.0F, false));
        bone41.cubeList.add(new ModelBox(bone41, 18, 86, -0.317F, -1.549F, -6.5F, 1, 1, 1, 0.0F, false));
        bone41.cubeList.add(new ModelBox(bone41, 18, 86, -0.317F, -1.549F, -2.5F, 1, 1, 1, 0.0F, false));
        bone41.cubeList.add(new ModelBox(bone41, 18, 86, -0.317F, -1.549F, 1.5F, 1, 1, 1, 0.0F, false));
        bone41.cubeList.add(new ModelBox(bone41, 18, 86, -0.317F, -1.549F, 5.5F, 1, 1, 1, 0.0F, false));
        bone41.cubeList.add(new ModelBox(bone41, 18, 86, -0.317F, -1.549F, 9.5F, 1, 1, 1, 0.0F, false));

        bone42 = new ModelRenderer(this);
        bone42.setRotationPoint(0.0F, -14.0F, -5.0F);
        bone.addChild(bone42);
        setRotationAngle(bone42, 0.0F, 0.0F, -0.5236F);
        bone42.cubeList.add(new ModelBox(bone42, 18, 86, -7.817F, 13.5394F, -10.5F, 1, 1, 1, 0.0F, false));
        bone42.cubeList.add(new ModelBox(bone42, 18, 86, -7.817F, 13.5394F, -6.5F, 1, 1, 1, 0.0F, false));
        bone42.cubeList.add(new ModelBox(bone42, 18, 86, -7.817F, 13.5394F, -2.5F, 1, 1, 1, 0.0F, false));
        bone42.cubeList.add(new ModelBox(bone42, 18, 86, -7.817F, 13.5394F, 1.5F, 1, 1, 1, 0.0F, false));
        bone42.cubeList.add(new ModelBox(bone42, 18, 86, -7.817F, 13.5394F, 5.5F, 1, 1, 1, 0.0F, false));
        bone42.cubeList.add(new ModelBox(bone42, 18, 86, -7.817F, 13.5394F, 9.5F, 1, 1, 1, 0.0F, false));

        bullets = new ModelRenderer(this);
        bullets.setRotationPoint(2.2F, -5.4F, -14.0F);
        bone.addChild(bullets);
        bullets.cubeList.add(new ModelBox(bullets, 5, 500, -1.6188F, 3.0F, 7.5F, 3, 1, 3, 0.0F, false));
        bullets.cubeList.add(new ModelBox(bullets, 5, 500, -1.6188F, 3.0F, 3.5F, 3, 1, 3, 0.0F, false));
        bullets.cubeList.add(new ModelBox(bullets, 5, 500, -1.6188F, 3.0F, 11.5F, 3, 1, 3, 0.0F, false));
        bullets.cubeList.add(new ModelBox(bullets, 5, 500, -1.6188F, 3.0F, 15.5F, 3, 1, 3, 0.0F, false));
        bullets.cubeList.add(new ModelBox(bullets, 5, 500, -1.6188F, 3.0F, -0.5F, 3, 1, 3, 0.0F, false));
        bullets.cubeList.add(new ModelBox(bullets, 17, 495, -1.6188F, 4.4F, 7.5F, 3, 4, 3, 0.0F, false));
        bullets.cubeList.add(new ModelBox(bullets, 17, 495, -1.6188F, 4.4F, 3.5F, 3, 4, 3, 0.0F, false));
        bullets.cubeList.add(new ModelBox(bullets, 17, 495, -1.6188F, 4.4F, 11.5F, 3, 4, 3, 0.0F, false));
        bullets.cubeList.add(new ModelBox(bullets, 17, 495, -1.6188F, 4.4F, 15.5F, 3, 4, 3, 0.0F, false));
        bullets.cubeList.add(new ModelBox(bullets, 17, 495, -1.6188F, 4.4F, -0.5F, 3, 4, 3, 0.0F, false));
        bullets.cubeList.add(new ModelBox(bullets, 17, 495, -1.1188F, 10.6F, 8.0F, 2, 2, 2, 0.0F, false));
        bullets.cubeList.add(new ModelBox(bullets, 17, 495, -1.1188F, 10.6F, 4.0F, 2, 2, 2, 0.0F, false));
        bullets.cubeList.add(new ModelBox(bullets, 17, 495, -1.1188F, 10.6F, 12.0F, 2, 2, 2, 0.0F, false));
        bullets.cubeList.add(new ModelBox(bullets, 17, 495, -1.1188F, 10.6F, 16.0F, 2, 2, 2, 0.0F, false));
        bullets.cubeList.add(new ModelBox(bullets, 17, 495, -1.1188F, 10.6F, 0.0F, 2, 2, 2, 0.0F, false));
        bullets.cubeList.add(new ModelBox(bullets, 17, 495, -0.8188F, 4.0F, 8.3F, 2, 7, 2, 0.0F, false));
        bullets.cubeList.add(new ModelBox(bullets, 17, 495, -0.8188F, 4.0F, 4.3F, 2, 7, 2, 0.0F, false));
        bullets.cubeList.add(new ModelBox(bullets, 17, 495, -0.8188F, 4.0F, 12.3F, 2, 7, 2, 0.0F, false));
        bullets.cubeList.add(new ModelBox(bullets, 17, 495, -0.8188F, 4.0F, 16.3F, 2, 7, 2, 0.0F, false));
        bullets.cubeList.add(new ModelBox(bullets, 17, 495, -0.8188F, 4.0F, 0.3F, 2, 7, 2, 0.0F, false));
        bullets.cubeList.add(new ModelBox(bullets, 17, 495, -0.8188F, 4.0F, 7.7F, 2, 7, 2, 0.0F, false));
        bullets.cubeList.add(new ModelBox(bullets, 17, 495, -0.8188F, 4.0F, 3.7F, 2, 7, 2, 0.0F, false));
        bullets.cubeList.add(new ModelBox(bullets, 17, 495, -0.8188F, 4.0F, 11.7F, 2, 7, 2, 0.0F, false));
        bullets.cubeList.add(new ModelBox(bullets, 17, 495, -0.8188F, 4.0F, 15.7F, 2, 7, 2, 0.0F, false));
        bullets.cubeList.add(new ModelBox(bullets, 17, 495, -0.8188F, 4.0F, -0.3F, 2, 7, 2, 0.0F, false));
        bullets.cubeList.add(new ModelBox(bullets, 17, 495, -1.4188F, 4.0F, 8.3F, 2, 7, 2, 0.0F, true));
        bullets.cubeList.add(new ModelBox(bullets, 17, 495, -1.4188F, 4.0F, 4.3F, 2, 7, 2, 0.0F, true));
        bullets.cubeList.add(new ModelBox(bullets, 17, 495, -1.4188F, 4.0F, 12.3F, 2, 7, 2, 0.0F, true));
        bullets.cubeList.add(new ModelBox(bullets, 17, 495, -1.4188F, 4.0F, 16.3F, 2, 7, 2, 0.0F, true));
        bullets.cubeList.add(new ModelBox(bullets, 17, 495, -1.4188F, 4.0F, 0.3F, 2, 7, 2, 0.0F, true));
        bullets.cubeList.add(new ModelBox(bullets, 17, 495, -1.4188F, 4.0F, 7.7F, 2, 7, 2, 0.0F, true));
        bullets.cubeList.add(new ModelBox(bullets, 17, 495, -1.4188F, 4.0F, 3.7F, 2, 7, 2, 0.0F, true));
        bullets.cubeList.add(new ModelBox(bullets, 17, 495, -1.4188F, 4.0F, 11.7F, 2, 7, 2, 0.0F, true));
        bullets.cubeList.add(new ModelBox(bullets, 17, 495, -1.4188F, 4.0F, 15.7F, 2, 7, 2, 0.0F, true));
        bullets.cubeList.add(new ModelBox(bullets, 17, 495, -1.4188F, 4.0F, -0.3F, 2, 7, 2, 0.0F, true));

        bullet = new ModelRenderer(this);
        bullet.setRotationPoint(0.0F, 32.0F, 0.0F);
        bullet.cubeList.add(new ModelBox(bullet, 5, 506, -1.6188F, -10.5F, -4.0F, 3, 3, 1, 0.0F, false));
        bullet.cubeList.add(new ModelBox(bullet, 9, 497, -1.6188F, -10.5F, -8.4F, 3, 3, 4, 0.0F, false));
        bullet.cubeList.add(new ModelBox(bullet, 7, 503, -1.1188F, -10.0F, -12.6F, 2, 2, 2, 0.0F, false));
        bullet.cubeList.add(new ModelBox(bullet, 5, 499, -0.8188F, -10.3F, -11.0F, 2, 2, 7, 0.0F, false));
        bullet.cubeList.add(new ModelBox(bullet, 5, 499, -0.8188F, -9.7F, -11.0F, 2, 2, 7, 0.0F, false));
        bullet.cubeList.add(new ModelBox(bullet, 5, 499, -1.4188F, -10.3F, -11.0F, 2, 2, 7, 0.0F, true));
        bullet.cubeList.add(new ModelBox(bullet, 5, 499, -1.4188F, -9.7F, -11.0F, 2, 2, 7, 0.0F, true));

        addEntry(AnimationElement.BOLT, stack -> bolt);
        addEntry(AnimationElement.CHARGING, stack -> boltcarrier);
        addEntry(AnimationElement.BULLET, stack -> bullet);

    }
}

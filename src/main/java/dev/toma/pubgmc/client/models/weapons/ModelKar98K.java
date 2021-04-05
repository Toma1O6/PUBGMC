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
    private final ModelRenderer bone;
    private final ModelRenderer bone3;
    private final ModelRenderer bone6;
    private final ModelRenderer bone5;
    private final ModelRenderer bone4;
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
    private final ModelRenderer bullet;

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }

    @Override
    public void renderModel(ItemStack stack) {
        kar98k.render(1f);
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
        kar98k.cubeList.add(new ModelBox(kar98k, 142, 156, -2.6188F, 1.7071F, -2.0F, 5, 7, 10, 0.0F, false));
        kar98k.cubeList.add(new ModelBox(kar98k, 142, 156, -2.6188F, 1.7071F, 8.0F, 5, 7, 11, 0.0F, false));
        kar98k.cubeList.add(new ModelBox(kar98k, 142, 156, -2.6188F, 4.3048F, 25.8984F, 5, 3, 5, 0.0F, false));
        kar98k.cubeList.add(new ModelBox(kar98k, 142, 156, -1.6188F, 3.3048F, 25.8984F, 3, 1, 5, 0.0F, false));
        kar98k.cubeList.add(new ModelBox(kar98k, 142, 156, -2.6188F, 2.6186F, 34.3138F, 5, 3, 2, 0.0F, false));
        kar98k.cubeList.add(new ModelBox(kar98k, 142, 156, -1.6188F, 1.6186F, 34.3138F, 3, 1, 2, 0.0F, false));
        kar98k.cubeList.add(new ModelBox(kar98k, 142, 156, -2.6188F, 9.0561F, 28.3138F, 5, 6, 11, 0.0F, false));
        kar98k.cubeList.add(new ModelBox(kar98k, 142, 156, -2.6188F, 14.0561F, 39.3138F, 5, 1, 5, 0.0F, false));
        kar98k.cubeList.add(new ModelBox(kar98k, 142, 156, -2.6188F, 10.6186F, 64.0559F, 5, 18, 8, 0.0F, false));
        kar98k.cubeList.add(new ModelBox(kar98k, 142, 156, -1.6188F, 9.6186F, 64.0559F, 3, 1, 8, 0.0F, false));
        kar98k.cubeList.add(new ModelBox(kar98k, 142, 156, -2.6188F, 1.7071F, -14.0F, 5, 7, 1, 0.0F, false));
        kar98k.cubeList.add(new ModelBox(kar98k, 142, 156, -2.6188F, 1.7071F, -13.0F, 1, 7, 11, 0.0F, false));
        kar98k.cubeList.add(new ModelBox(kar98k, 142, 156, 1.3812F, 1.7071F, -13.0F, 1, 7, 11, 0.0F, true));
        kar98k.cubeList.add(new ModelBox(kar98k, 5, 158, 1.0F, -2.0312F, -14.0F, 1, 4, 1, 0.0F, false));
        kar98k.cubeList.add(new ModelBox(kar98k, 5, 158, -1.0F, -2.0312F, -14.0F, 2, 1, 1, 0.0F, false));
        kar98k.cubeList.add(new ModelBox(kar98k, 5, 158, -1.0F, 0.9688F, -14.0F, 2, 1, 1, 0.0F, false));
        kar98k.cubeList.add(new ModelBox(kar98k, 5, 158, -2.0F, -2.0312F, -14.0F, 1, 4, 1, 0.0F, false));
        kar98k.cubeList.add(new ModelBox(kar98k, 12, 162, -1.6188F, -3.0312F, -23.0F, 3, 1, 8, 0.0F, false));
        kar98k.cubeList.add(new ModelBox(kar98k, 12, 162, -2.1188F, -4.0312F, -17.0F, 4, 1, 1, 0.0F, false));
        kar98k.cubeList.add(new ModelBox(kar98k, 0, 93, 1.0F, -2.0312F, -2.0F, 1, 4, 2, 0.0F, false));
        kar98k.cubeList.add(new ModelBox(kar98k, 0, 93, -2.2376F, -2.0312F, -2.0F, 1, 4, 2, 0.0F, true));
        kar98k.cubeList.add(new ModelBox(kar98k, 0, 93, 1.0F, -2.0312F, 10.0F, 1, 4, 2, 0.0F, false));
        kar98k.cubeList.add(new ModelBox(kar98k, 0, 93, -2.2376F, -2.0312F, 10.0F, 1, 4, 2, 0.0F, true));
        kar98k.cubeList.add(new ModelBox(kar98k, 0, 93, -1.6188F, -3.0312F, -2.0F, 3, 1, 14, 0.0F, false));
        kar98k.cubeList.add(new ModelBox(kar98k, 0, 93, -1.9118F, -3.1952F, -2.0F, 1, 1, 14, 0.0F, false));
        kar98k.cubeList.add(new ModelBox(kar98k, 0, 93, 0.6742F, -3.1952F, -2.0F, 1, 1, 14, 0.0F, true));
        kar98k.cubeList.add(new ModelBox(kar98k, 0, 93, -2.0F, 1.0469F, 14.0F, 1, 1, 5, 0.0F, false));
        kar98k.cubeList.add(new ModelBox(kar98k, 0, 93, 1.0F, 1.0469F, 14.0F, 1, 1, 5, 0.0F, false));
        kar98k.cubeList.add(new ModelBox(kar98k, 142, 156, -2.6188F, -1.2929F, -25.0F, 5, 10, 11, 0.0F, false));
        kar98k.cubeList.add(new ModelBox(kar98k, 142, 156, -2.6188F, -1.2929F, -36.0F, 5, 4, 11, 0.0F, false));
        kar98k.cubeList.add(new ModelBox(kar98k, 142, 156, -2.6188F, -1.2929F, -47.0F, 5, 4, 11, 0.0F, false));
        kar98k.cubeList.add(new ModelBox(kar98k, 142, 156, -2.6188F, -1.2929F, -58.0F, 5, 4, 11, 0.0F, false));
        kar98k.cubeList.add(new ModelBox(kar98k, 142, 156, -2.6188F, -1.2929F, -69.0F, 5, 4, 11, 0.0F, false));
        kar98k.cubeList.add(new ModelBox(kar98k, 142, 156, -2.6188F, 2.7071F, -69.0F, 5, 2, 6, 0.0F, false));
        kar98k.cubeList.add(new ModelBox(kar98k, 142, 156, -2.1188F, -0.7929F, -82.0F, 1, 4, 11, 0.0F, false));
        kar98k.cubeList.add(new ModelBox(kar98k, 142, 156, 0.8812F, -0.7929F, -82.0F, 1, 4, 11, 0.0F, false));
        kar98k.cubeList.add(new ModelBox(kar98k, 142, 156, -2.6188F, -1.2929F, -71.0F, 5, 6, 2, 0.0F, false));
        kar98k.cubeList.add(new ModelBox(kar98k, 142, 156, -1.6188F, -2.2929F, -25.0F, 3, 1, 11, 0.0F, false));
        kar98k.cubeList.add(new ModelBox(kar98k, 142, 156, -1.6188F, -2.2929F, -36.0F, 3, 1, 11, 0.0F, false));
        kar98k.cubeList.add(new ModelBox(kar98k, 142, 156, -1.6188F, -2.2929F, -47.0F, 3, 1, 11, 0.0F, false));
        kar98k.cubeList.add(new ModelBox(kar98k, 142, 156, -1.6188F, -2.2929F, -58.0F, 3, 1, 11, 0.0F, false));
        kar98k.cubeList.add(new ModelBox(kar98k, 142, 156, -1.6188F, -2.2929F, -69.0F, 3, 1, 11, 0.0F, false));
        kar98k.cubeList.add(new ModelBox(kar98k, 142, 156, -1.6188F, 4.7071F, -69.0F, 3, 1, 7, 0.0F, false));
        kar98k.cubeList.add(new ModelBox(kar98k, 142, 156, -1.1188F, -1.7929F, -82.0F, 2, 1, 11, 0.0F, false));
        kar98k.cubeList.add(new ModelBox(kar98k, 0, 12, -1.1188F, -0.9062F, -108.0F, 2, 2, 29, 0.0F, false));
        kar98k.cubeList.add(new ModelBox(kar98k, 12, 162, -1.6188F, -0.9062F, -107.0F, 3, 2, 3, 0.0F, false));
        kar98k.cubeList.add(new ModelBox(kar98k, 12, 162, -1.1188F, -1.4062F, -107.0F, 2, 3, 3, 0.0F, false));
        kar98k.cubeList.add(new ModelBox(kar98k, 12, 162, -0.6188F, -4.4062F, -106.5F, 1, 3, 2, 0.0F, false));
        kar98k.cubeList.add(new ModelBox(kar98k, 0, 12, -1.1188F, 1.2852F, -90.0F, 2, 2, 11, 0.0F, false));
        kar98k.cubeList.add(new ModelBox(kar98k, 142, 156, -1.1188F, 3.2071F, -82.0F, 2, 1, 11, 0.0F, false));
        kar98k.cubeList.add(new ModelBox(kar98k, 142, 156, -1.6188F, -2.2929F, -71.0F, 3, 1, 2, 0.0F, false));
        kar98k.cubeList.add(new ModelBox(kar98k, 142, 156, -1.6188F, 4.7071F, -71.0F, 3, 1, 2, 0.0F, false));
        kar98k.cubeList.add(new ModelBox(kar98k, 142, 156, -1.6188F, 8.7071F, -25.0F, 3, 1, 11, 0.0F, false));
        kar98k.cubeList.add(new ModelBox(kar98k, 142, 156, -1.6188F, 8.7071F, -14.0F, 3, 1, 11, 0.0F, false));
        kar98k.cubeList.add(new ModelBox(kar98k, 142, 156, -1.6188F, 8.7071F, -3.0F, 3, 1, 11, 0.0F, false));
        kar98k.cubeList.add(new ModelBox(kar98k, 142, 156, -1.6188F, 8.7071F, 8.0F, 3, 1, 13, 0.0F, false));
        kar98k.cubeList.add(new ModelBox(kar98k, 17, 154, -1.1188F, 8.9871F, -0.5F, 2, 1, 12, 0.0F, false));
        kar98k.cubeList.add(new ModelBox(kar98k, 142, 156, -1.6188F, 1.7071F, 19.0F, 3, 2, 4, 0.0F, false));

        bone13 = new ModelRenderer(this);
        bone13.setRotationPoint(5.3812F, 0.0F, 0.0F);
        kar98k.addChild(bone13);
        setRotationAngle(bone13, -0.3491F, 0.0F, 0.0F);
        bone13.cubeList.add(new ModelBox(bone13, 142, 156, -8.0F, -4.8943F, 18.4383F, 5, 7, 9, 0.0F, false));
        bone13.cubeList.add(new ModelBox(bone13, 142, 156, -7.0F, -5.8943F, 18.4383F, 3, 1, 9, 0.0F, false));

        bone16 = new ModelRenderer(this);
        bone16.setRotationPoint(5.3812F, 14.832F, 17.1758F);
        kar98k.addChild(bone16);
        setRotationAngle(bone16, 0.4363F, 0.0F, 0.0F);
        bone16.cubeList.add(new ModelBox(bone16, 142, 156, -8.0F, -3.8262F, 14.6942F, 5, 7, 6, 0.0F, false));
        bone16.cubeList.add(new ModelBox(bone16, 142, 156, -7.0F, -4.8262F, 16.6942F, 3, 1, 4, 0.0F, false));

        bone20 = new ModelRenderer(this);
        bone20.setRotationPoint(-5.3812F, -12.2176F, 22.3737F);
        bone16.addChild(bone20);
        setRotationAngle(bone20, 0.0F, 0.0F, 0.7854F);
        bone20.cubeList.add(new ModelBox(bone20, 142, 156, 6.2032F, 4.2499F, -5.6795F, 1, 1, 4, 0.0F, false));
        bone20.cubeList.add(new ModelBox(bone20, 142, 156, 4.0819F, 6.3712F, -5.6795F, 1, 1, 4, 0.0F, false));
        bone20.cubeList.add(new ModelBox(bone20, 142, 156, 4.0819F, 6.7854F, -5.6795F, 1, 1, 4, 0.0F, false));
        bone20.cubeList.add(new ModelBox(bone20, 142, 156, 6.6174F, 4.2499F, -5.6795F, 1, 1, 4, 0.0F, false));

        bone17 = new ModelRenderer(this);
        bone17.setRotationPoint(5.3812F, 14.832F, 26.1758F);
        kar98k.addChild(bone17);
        setRotationAngle(bone17, -0.2618F, 0.0F, 0.0F);
        bone17.cubeList.add(new ModelBox(bone17, 142, 156, -8.0F, -14.4211F, 6.6315F, 5, 7, 11, 0.0F, false));
        bone17.cubeList.add(new ModelBox(bone17, 142, 156, -7.0F, -15.4211F, 6.6315F, 3, 1, 11, 0.0F, false));
        bone17.cubeList.add(new ModelBox(bone17, 142, 156, -8.0F, -14.4211F, 17.6315F, 5, 7, 11, 0.0F, false));
        bone17.cubeList.add(new ModelBox(bone17, 142, 156, -7.0F, -15.4211F, 17.6315F, 3, 1, 11, 0.0F, false));
        bone17.cubeList.add(new ModelBox(bone17, 142, 156, -8.0F, -14.4211F, 28.6315F, 5, 9, 11, 0.0F, false));
        bone17.cubeList.add(new ModelBox(bone17, 142, 156, -7.0F, -15.4211F, 28.6315F, 3, 1, 11, 0.0F, false));

        bone19 = new ModelRenderer(this);
        bone19.setRotationPoint(0.0F, 0.0F, 0.0F);
        bone17.addChild(bone19);
        setRotationAngle(bone19, 0.0F, 0.0F, 0.7854F);
        bone19.cubeList.add(new ModelBox(bone19, 142, 156, -13.7328F, -8.0759F, 6.6315F, 1, 1, 11, 0.0F, false));
        bone19.cubeList.add(new ModelBox(bone19, 142, 156, -13.7328F, -8.0759F, 17.6315F, 1, 1, 11, 0.0F, false));
        bone19.cubeList.add(new ModelBox(bone19, 142, 156, -13.7328F, -8.0759F, 28.6315F, 1, 1, 9, 0.0F, false));
        bone19.cubeList.add(new ModelBox(bone19, 142, 156, -15.8541F, -5.9546F, 28.6315F, 1, 1, 9, 0.0F, false));
        bone19.cubeList.add(new ModelBox(bone19, 142, 156, -15.8541F, -5.9546F, 17.6315F, 1, 1, 11, 0.0F, false));
        bone19.cubeList.add(new ModelBox(bone19, 142, 156, -15.8541F, -5.9546F, 6.6315F, 1, 1, 11, 0.0F, false));
        bone19.cubeList.add(new ModelBox(bone19, 142, 156, -13.3185F, -8.0759F, 6.6315F, 1, 1, 11, 0.0F, false));
        bone19.cubeList.add(new ModelBox(bone19, 142, 156, -13.3185F, -8.0759F, 17.6315F, 1, 1, 11, 0.0F, false));
        bone19.cubeList.add(new ModelBox(bone19, 142, 156, -13.3185F, -8.0759F, 28.6315F, 1, 1, 9, 0.0F, false));
        bone19.cubeList.add(new ModelBox(bone19, 142, 156, -15.8541F, -5.5404F, 28.6315F, 1, 1, 9, 0.0F, false));
        bone19.cubeList.add(new ModelBox(bone19, 142, 156, -15.8541F, -5.5404F, 17.6315F, 1, 1, 11, 0.0F, false));
        bone19.cubeList.add(new ModelBox(bone19, 142, 156, -15.8541F, -5.5404F, 6.6315F, 1, 1, 11, 0.0F, false));

        bone18 = new ModelRenderer(this);
        bone18.setRotationPoint(5.3812F, 14.832F, 26.1758F);
        kar98k.addChild(bone18);
        setRotationAngle(bone18, -0.5236F, 0.0F, 0.0F);
        bone18.cubeList.add(new ModelBox(bone18, 142, 156, -8.0F, -14.0005F, 6.6985F, 5, 7, 11, 0.0F, false));
        bone18.cubeList.add(new ModelBox(bone18, 142, 156, -8.0F, -15.0005F, 17.6985F, 5, 8, 11, 0.0F, false));
        bone18.cubeList.add(new ModelBox(bone18, 142, 156, -8.0F, -15.0005F, 28.6985F, 5, 8, 11, 0.0F, false));

        bone14 = new ModelRenderer(this);
        bone14.setRotationPoint(5.3812F, -7.918F, 0.0F);
        kar98k.addChild(bone14);
        setRotationAngle(bone14, -1.0472F, 0.0F, 0.0F);
        bone14.cubeList.add(new ModelBox(bone14, 142, 156, -8.0F, -18.5157F, 25.9381F, 5, 7, 10, 0.0F, false));
        bone14.cubeList.add(new ModelBox(bone14, 142, 156, -7.0F, -18.5157F, 35.9381F, 3, 7, 1, 0.0F, false));

        bone15 = new ModelRenderer(this);
        bone15.setRotationPoint(0.0F, 0.0F, 0.0F);
        bone14.addChild(bone15);
        setRotationAngle(bone15, 0.0F, 0.7854F, 0.0F);
        bone15.cubeList.add(new ModelBox(bone15, 142, 156, -28.5334F, -18.5157F, 22.2908F, 1, 7, 1, 0.0F, false));
        bone15.cubeList.add(new ModelBox(bone15, 142, 156, -28.9476F, -18.5157F, 22.2908F, 1, 7, 1, 0.0F, false));
        bone15.cubeList.add(new ModelBox(bone15, 142, 156, -31.0689F, -18.5157F, 20.1694F, 1, 7, 1, 0.0F, false));
        bone15.cubeList.add(new ModelBox(bone15, 142, 156, -31.0689F, -18.5157F, 19.7552F, 1, 7, 1, 0.0F, false));

        bone12 = new ModelRenderer(this);
        bone12.setRotationPoint(1.0F, -12.2383F, -15.0F);
        kar98k.addChild(bone12);
        setRotationAngle(bone12, 0.1745F, 0.0F, 0.0F);
        bone12.cubeList.add(new ModelBox(bone12, 12, 162, -0.6188F, 7.6782F, -9.4771F, 1, 1, 8, 0.0F, false));
        bone12.cubeList.add(new ModelBox(bone12, 12, 162, -2.6188F, 7.6782F, -9.4771F, 1, 1, 8, 0.0F, true));

        bone10 = new ModelRenderer(this);
        bone10.setRotationPoint(0.0F, -3.0F, -30.5F);
        kar98k.addChild(bone10);
        setRotationAngle(bone10, -0.1047F, 0.0F, 0.0F);
        bone10.cubeList.add(new ModelBox(bone10, 142, 156, -2.6198F, 5.0681F, -4.3065F, 5, 6, 11, 0.0F, false));
        bone10.cubeList.add(new ModelBox(bone10, 142, 156, -2.6198F, 6.0681F, -15.3065F, 5, 5, 11, 0.0F, false));
        bone10.cubeList.add(new ModelBox(bone10, 142, 156, -2.6198F, 7.0681F, -26.3065F, 5, 4, 11, 0.0F, false));
        bone10.cubeList.add(new ModelBox(bone10, 142, 156, -2.6198F, 8.0681F, -32.3065F, 5, 3, 6, 0.0F, false));
        bone10.cubeList.add(new ModelBox(bone10, 142, 156, -1.6188F, 11.0681F, -32.3065F, 3, 1, 13, 0.0F, false));
        bone10.cubeList.add(new ModelBox(bone10, 142, 156, -1.6188F, 11.0681F, -19.3065F, 3, 1, 13, 0.0F, false));
        bone10.cubeList.add(new ModelBox(bone10, 142, 156, -1.6188F, 11.0681F, -6.3065F, 3, 1, 13, 0.0F, false));

        bone11 = new ModelRenderer(this);
        bone11.setRotationPoint(6.501F, 3.0F, 30.5F);
        bone10.addChild(bone11);
        setRotationAngle(bone11, 0.0F, 0.0F, -0.7854F);
        bone11.cubeList.add(new ModelBox(bone11, 142, 156, -9.6181F, 1.7918F, -36.8065F, 1, 1, 13, 0.0F, false));
        bone11.cubeList.add(new ModelBox(bone11, 142, 156, -12.1543F, -0.7444F, -36.8065F, 1, 1, 13, 0.0F, false));
        bone11.cubeList.add(new ModelBox(bone11, 142, 156, -9.6181F, 1.7918F, -49.8065F, 1, 1, 13, 0.0F, false));
        bone11.cubeList.add(new ModelBox(bone11, 142, 156, -12.1543F, -0.7444F, -49.8065F, 1, 1, 13, 0.0F, false));
        bone11.cubeList.add(new ModelBox(bone11, 142, 156, -9.6181F, 1.7918F, -62.8065F, 1, 1, 13, 0.0F, false));
        bone11.cubeList.add(new ModelBox(bone11, 142, 156, -12.1543F, -0.7444F, -62.8065F, 1, 1, 13, 0.0F, false));
        bone11.cubeList.add(new ModelBox(bone11, 142, 156, -10.0323F, 1.7918F, -36.8065F, 1, 1, 13, 0.0F, false));
        bone11.cubeList.add(new ModelBox(bone11, 142, 156, -12.1536F, -0.3295F, -36.8065F, 1, 1, 13, 0.0F, false));
        bone11.cubeList.add(new ModelBox(bone11, 142, 156, -10.0323F, 1.7918F, -49.8065F, 1, 1, 13, 0.0F, false));
        bone11.cubeList.add(new ModelBox(bone11, 142, 156, -12.1536F, -0.3295F, -49.8065F, 1, 1, 13, 0.0F, false));
        bone11.cubeList.add(new ModelBox(bone11, 142, 156, -10.0323F, 1.7918F, -62.8065F, 1, 1, 13, 0.0F, false));
        bone11.cubeList.add(new ModelBox(bone11, 142, 156, -12.1536F, -0.3295F, -62.8065F, 1, 1, 13, 0.0F, false));

        bone9 = new ModelRenderer(this);
        bone9.setRotationPoint(5.3812F, 0.0F, 0.0F);
        kar98k.addChild(bone9);
        setRotationAngle(bone9, 0.0F, 0.0F, 0.7854F);
        bone9.cubeList.add(new ModelBox(bone9, 142, 156, -6.571F, 3.3284F, -25.0F, 1, 1, 11, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 142, 156, -6.571F, 3.3284F, -36.0F, 1, 1, 11, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 142, 156, -6.571F, 3.3284F, -47.0F, 1, 1, 11, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 142, 156, -6.571F, 3.3284F, -58.0F, 1, 1, 11, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 142, 156, -6.571F, 3.3284F, -69.0F, 1, 1, 11, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 142, 156, -6.571F, 3.3284F, -71.0F, 1, 1, 2, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 142, 156, -1.9142F, 7.9852F, -71.0F, 1, 1, 2, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 142, 156, -4.4497F, 1.207F, -36.0F, 1, 1, 11, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 142, 156, -4.4497F, 1.207F, -47.0F, 1, 1, 11, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 142, 156, -4.4497F, 1.207F, -58.0F, 1, 1, 11, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 142, 156, -4.4497F, 1.207F, -69.0F, 1, 1, 11, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 142, 156, 0.2072F, 5.4497F, -69.0F, 1, 1, 7, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 142, 156, -4.4497F, 1.207F, -71.0F, 1, 1, 2, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 142, 156, 0.2072F, 5.8639F, -71.0F, 1, 1, 2, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 142, 156, -4.0355F, 1.207F, -36.0F, 1, 1, 11, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 142, 156, -4.0355F, 1.207F, -47.0F, 1, 1, 11, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 142, 156, -4.0355F, 1.207F, -58.0F, 1, 1, 11, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 142, 156, -4.0355F, 1.207F, -69.0F, 1, 1, 11, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 142, 156, 0.2072F, 5.8639F, -69.0F, 1, 1, 7, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 142, 156, -1.9142F, 7.9852F, -69.0F, 1, 1, 7, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 142, 156, -2.3284F, 7.9852F, -69.0F, 1, 1, 7, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 142, 156, -4.0355F, 1.9141F, -82.0F, 1, 1, 11, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 142, 156, -5.8639F, 3.7426F, -82.0F, 1, 1, 11, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 142, 156, -5.8639F, 3.3284F, -82.0F, 1, 1, 11, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 142, 156, -4.4497F, 1.9141F, -82.0F, 1, 1, 11, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 142, 156, -1.2071F, 5.1568F, -82.0F, 1, 1, 11, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 142, 156, -2.6213F, 6.571F, -82.0F, 1, 1, 11, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 142, 156, -1.2071F, 4.7426F, -82.0F, 1, 1, 11, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 142, 156, -3.0355F, 6.571F, -82.0F, 1, 1, 11, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 142, 156, -4.0355F, 1.207F, -71.0F, 1, 1, 2, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 142, 156, 0.2072F, 5.4497F, -71.0F, 1, 1, 2, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 142, 156, -4.0355F, 1.207F, -25.0F, 1, 1, 11, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 142, 156, 3.0356F, 8.2781F, -25.0F, 1, 1, 11, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 142, 156, 3.0356F, 8.2781F, -14.0F, 1, 1, 11, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 142, 156, 3.0356F, 8.2781F, -3.0F, 1, 1, 11, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 142, 156, 3.973F, 9.6298F, 62.0559F, 1, 1, 10, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 142, 156, -1.6838F, 3.9729F, 34.3138F, 1, 1, 2, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 142, 156, -0.4916F, 5.1652F, 24.8984F, 1, 1, 6, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 142, 156, -2.6129F, 7.2865F, 24.8984F, 1, 1, 6, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 142, 156, -3.8051F, 6.0942F, 34.3138F, 1, 1, 2, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 142, 156, -3.8051F, 6.5085F, 34.3138F, 1, 1, 2, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 142, 156, 1.8517F, 11.7511F, 62.0559F, 1, 1, 10, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 142, 156, 4.3873F, 9.6298F, 65.0559F, 1, 1, 7, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 142, 156, -1.2696F, 3.9729F, 34.3138F, 1, 1, 2, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 142, 156, -0.0773F, 5.1652F, 24.8984F, 1, 1, 6, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 142, 156, -2.6129F, 7.7007F, 24.8984F, 1, 1, 6, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 142, 156, 1.8517F, 12.1653F, 65.0559F, 1, 1, 7, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 142, 156, 3.0356F, 8.2781F, 8.0F, 1, 1, 11, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 142, 156, 3.0356F, 8.6923F, -25.0F, 1, 1, 11, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 142, 156, 3.0356F, 8.6923F, -14.0F, 1, 1, 11, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 142, 156, 3.0356F, 8.6923F, -3.0F, 1, 1, 11, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 142, 156, 3.0356F, 8.6923F, 8.0F, 1, 1, 13, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 142, 156, 0.9143F, 10.8136F, -25.0F, 1, 1, 11, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 142, 156, 0.9143F, 10.8136F, -14.0F, 1, 1, 11, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 142, 156, 0.9143F, 10.8136F, -3.0F, 1, 1, 11, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 142, 156, 0.9143F, 10.8136F, 8.0F, 1, 1, 13, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 142, 156, 0.5001F, 10.8136F, -25.0F, 1, 1, 11, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 142, 156, 0.5001F, 10.8136F, -14.0F, 1, 1, 11, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 142, 156, 0.5001F, 10.8136F, -3.0F, 1, 1, 11, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 142, 156, 0.5001F, 10.8136F, 8.0F, 1, 1, 11, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 142, 156, -4.4497F, 1.207F, -25.0F, 1, 1, 11, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 142, 156, -6.571F, 3.7426F, -25.0F, 1, 1, 11, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 142, 156, -6.571F, 3.7426F, -36.0F, 1, 1, 11, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 142, 156, -6.571F, 3.7426F, -47.0F, 1, 1, 11, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 142, 156, -6.571F, 3.7426F, -58.0F, 1, 1, 11, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 142, 156, -6.571F, 3.7426F, -69.0F, 1, 1, 11, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 142, 156, -6.571F, 3.7426F, -71.0F, 1, 1, 2, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 142, 156, -2.3284F, 7.9852F, -71.0F, 1, 1, 2, 0.0F, false));

        bone7 = new ModelRenderer(this);
        bone7.setRotationPoint(5.9883F, 0.0F, 0.0F);
        kar98k.addChild(bone7);
        setRotationAngle(bone7, 0.0F, 0.2618F, 0.0F);
        bone7.cubeList.add(new ModelBox(bone7, 142, 156, -13.2314F, 1.7071F, 16.125F, 1, 2, 4, 0.0F, false));

        bone8 = new ModelRenderer(this);
        bone8.setRotationPoint(-6.0F, 0.0F, 0.0F);
        kar98k.addChild(bone8);
        setRotationAngle(bone8, 0.0F, -0.2618F, 0.0F);
        bone8.cubeList.add(new ModelBox(bone8, 142, 156, 12.0131F, 1.7071F, 16.1833F, 1, 2, 4, 0.0F, false));

        bolt = new ModelRenderer(this);
        bolt.setRotationPoint(0.0F, 15.3F, 21.0F);
        bolt.cubeList.add(new ModelBox(bolt, 84, 17, -1.0F, 7.4071F, -28.0F, 2, 3, 14, 0.0F, false));
        bolt.cubeList.add(new ModelBox(bolt, 84, 17, -1.0F, 8.4071F, -14.0F, 2, 2, 14, 0.0F, false));
        bolt.cubeList.add(new ModelBox(bolt, 84, 17, -0.2929F, 7.7F, -28.0F, 2, 2, 14, 0.0F, false));
        bolt.cubeList.add(new ModelBox(bolt, 84, 17, -0.2929F, 7.7F, -42.0F, 2, 2, 14, 0.0F, false));
        bolt.cubeList.add(new ModelBox(bolt, 84, 17, -0.2929F, 7.7F, -14.0F, 2, 2, 14, 0.0F, false));
        bolt.cubeList.add(new ModelBox(bolt, 84, 17, -1.7071F, 7.7F, -28.0F, 2, 2, 14, 0.0F, false));
        bolt.cubeList.add(new ModelBox(bolt, 84, 17, -1.7071F, 7.7F, -42.0F, 2, 2, 14, 0.0F, false));
        bolt.cubeList.add(new ModelBox(bolt, 84, 17, -1.7071F, 7.7F, -14.0F, 2, 2, 14, 0.0F, false));
        bolt.cubeList.add(new ModelBox(bolt, 84, 17, -1.0F, 6.9929F, -28.0F, 2, 2, 14, 0.0F, false));
        bolt.cubeList.add(new ModelBox(bolt, 84, 17, -1.0F, 6.9929F, -42.0F, 2, 2, 14, 0.0F, false));
        bolt.cubeList.add(new ModelBox(bolt, 84, 17, -1.0F, 8.4071F, -42.0F, 2, 2, 14, 0.0F, false));
        bolt.cubeList.add(new ModelBox(bolt, 84, 17, -1.0F, 6.9929F, -14.0F, 2, 2, 14, 0.0F, false));

        bone = new ModelRenderer(this);
        bone.setRotationPoint(2.0F, 9.7F, -22.0F);
        bolt.addChild(bone);
        setRotationAngle(bone, 0.0F, 0.0F, -0.3491F);
        bone.cubeList.add(new ModelBox(bone, 84, 17, -6.2128F, -3.0798F, 13.0F, 4, 2, 2, 0.0F, false));

        bone3 = new ModelRenderer(this);
        bone3.setRotationPoint(0.0F, 0.0F, 0.0F);
        bone.addChild(bone3);
        setRotationAngle(bone3, 0.0F, 0.0F, -0.8727F);
        bone3.cubeList.add(new ModelBox(bone3, 84, 17, -4.6343F, -6.7393F, 13.0F, 3, 2, 2, 0.0F, false));
        bone3.cubeList.add(new ModelBox(bone3, 84, 17, -7.0485F, -6.7393F, 13.0F, 1, 2, 2, 0.0F, false));
        bone3.cubeList.add(new ModelBox(bone3, 84, 17, -6.3414F, -6.7393F, 14.7071F, 2, 2, 1, 0.0F, false));
        bone3.cubeList.add(new ModelBox(bone3, 84, 17, -6.3414F, -6.7393F, 12.2929F, 2, 2, 1, 0.0F, false));
        bone3.cubeList.add(new ModelBox(bone3, 84, 17, -6.3414F, -7.4464F, 13.0F, 2, 1, 2, 0.0F, false));
        bone3.cubeList.add(new ModelBox(bone3, 84, 17, -6.3414F, -5.0322F, 13.0F, 2, 1, 2, 0.0F, false));

        bone6 = new ModelRenderer(this);
        bone6.setRotationPoint(0.0F, 0.0F, 0.0F);
        bone3.addChild(bone6);
        setRotationAngle(bone6, 0.0F, -0.7854F, 0.0F);
        bone6.cubeList.add(new ModelBox(bone6, 84, 17, 5.6225F, -6.7393F, 14.5907F, 1, 2, 1, 0.0F, false));
        bone6.cubeList.add(new ModelBox(bone6, 84, 17, 7.0367F, -6.7393F, 13.1765F, 1, 2, 1, 0.0F, false));
        bone6.cubeList.add(new ModelBox(bone6, 84, 17, 4.2083F, -6.7393F, 13.1765F, 1, 2, 1, 0.0F, false));
        bone6.cubeList.add(new ModelBox(bone6, 84, 17, 5.6225F, -6.7393F, 11.7623F, 1, 2, 1, 0.0F, false));

        bone5 = new ModelRenderer(this);
        bone5.setRotationPoint(0.0F, 0.0F, 0.0F);
        bone3.addChild(bone5);
        setRotationAngle(bone5, 0.7854F, 0.0F, 0.0F);
        bone5.cubeList.add(new ModelBox(bone5, 84, 17, -6.3414F, 6.7554F, 13.4578F, 2, 1, 1, 0.0F, false));
        bone5.cubeList.add(new ModelBox(bone5, 84, 17, -6.3414F, 5.3412F, 12.0436F, 2, 1, 1, 0.0F, false));
        bone5.cubeList.add(new ModelBox(bone5, 84, 17, -6.3414F, 5.3412F, 14.872F, 2, 1, 1, 0.0F, false));
        bone5.cubeList.add(new ModelBox(bone5, 84, 17, -6.3414F, 3.927F, 13.4578F, 2, 1, 1, 0.0F, false));

        bone4 = new ModelRenderer(this);
        bone4.setRotationPoint(0.0F, 0.0F, 0.0F);
        bone3.addChild(bone4);
        setRotationAngle(bone4, 0.0F, 0.0F, -0.7854F);
        bone4.cubeList.add(new ModelBox(bone4, 84, 17, 1.1956F, -8.3353F, 13.0F, 1, 1, 2, 0.0F, false));
        bone4.cubeList.add(new ModelBox(bone4, 84, 17, -0.2187F, -9.7495F, 13.0F, 1, 1, 2, 0.0F, false));
        bone4.cubeList.add(new ModelBox(bone4, 84, 17, -1.6329F, -8.3353F, 13.0F, 1, 1, 2, 0.0F, false));
        bone4.cubeList.add(new ModelBox(bone4, 84, 17, -0.2187F, -6.9211F, 13.0F, 1, 1, 2, 0.0F, false));

        bone2 = new ModelRenderer(this);
        bone2.setRotationPoint(-2.0F, 9.7F, -22.0F);
        bolt.addChild(bone2);
        setRotationAngle(bone2, 0.0F, 0.0F, -0.7854F);
        bone2.cubeList.add(new ModelBox(bone2, 84, 17, 1.6213F, 0.6213F, -6.0F, 1, 2, 14, 0.0F, false));
        bone2.cubeList.add(new ModelBox(bone2, 84, 17, 1.6213F, 0.6213F, -20.0F, 1, 2, 14, 0.0F, false));
        bone2.cubeList.add(new ModelBox(bone2, 84, 17, 1.6213F, 0.6213F, 8.0F, 1, 2, 14, 0.0F, false));
        bone2.cubeList.add(new ModelBox(bone2, 84, 17, 0.2071F, 0.2071F, -6.0F, 2, 1, 14, 0.0F, false));
        bone2.cubeList.add(new ModelBox(bone2, 84, 17, 0.2071F, 0.2071F, -20.0F, 2, 1, 14, 0.0F, false));
        bone2.cubeList.add(new ModelBox(bone2, 84, 17, 0.2071F, 0.2071F, 8.0F, 2, 1, 14, 0.0F, false));
        bone2.cubeList.add(new ModelBox(bone2, 84, 17, 2.0355F, 0.2071F, -6.0F, 2, 1, 14, 0.0F, false));
        bone2.cubeList.add(new ModelBox(bone2, 84, 17, 2.0355F, 0.2071F, -20.0F, 2, 1, 14, 0.0F, false));
        bone2.cubeList.add(new ModelBox(bone2, 84, 17, 2.0355F, 0.2071F, 8.0F, 2, 1, 14, 0.0F, false));
        bone2.cubeList.add(new ModelBox(bone2, 84, 17, 1.6213F, -1.2072F, -6.0F, 1, 2, 14, 0.0F, false));
        bone2.cubeList.add(new ModelBox(bone2, 84, 17, 1.6213F, -1.2072F, -20.0F, 1, 2, 14, 0.0F, false));
        bone2.cubeList.add(new ModelBox(bone2, 84, 17, 1.6213F, -1.2072F, 8.0F, 1, 2, 14, 0.0F, false));

        stock = new ModelRenderer(this);
        stock.setRotationPoint(0.0F, 24.0F, -2.0F);


        bone21 = new ModelRenderer(this);
        bone21.setRotationPoint(-0.5F, -2.5F, 53.5F);
        stock.addChild(bone21);
        setRotationAngle(bone21, -0.2618F, -0.1745F, 0.0698F);
        bone21.cubeList.add(new ModelBox(bone21, 212, 153, -0.3952F, 7.7013F, 7.649F, 5, 1, 2, 0.0F, false));

        bone22 = new ModelRenderer(this);
        bone22.setRotationPoint(-0.5F, -2.75F, 52.66F);
        stock.addChild(bone22);
        setRotationAngle(bone22, -0.3142F, -0.1047F, 0.0F);
        bone22.cubeList.add(new ModelBox(bone22, 212, 153, -1.5547F, 7.2428F, 7.6656F, 5, 1, 2, 0.0F, false));

        bone23 = new ModelRenderer(this);
        bone23.setRotationPoint(-4.0066F, -2.2342F, 59.8428F);
        stock.addChild(bone23);
        setRotationAngle(bone23, -0.2705F, 0.1134F, 0.6545F);
        bone23.cubeList.add(new ModelBox(bone23, 212, 153, 6.6729F, 6.0689F, 1.4329F, 1, 1, 2, 0.0F, false));

        bone24 = new ModelRenderer(this);
        bone24.setRotationPoint(-4.1366F, -2.4342F, 58.1528F);
        stock.addChild(bone24);
        setRotationAngle(bone24, -0.2705F, 0.0262F, 0.5672F);
        bone24.cubeList.add(new ModelBox(bone24, 212, 153, 6.0763F, 6.6061F, 1.0949F, 1, 1, 2, 0.0F, false));

        bone25 = new ModelRenderer(this);
        bone25.setRotationPoint(1.0434F, -2.7542F, 59.0128F);
        stock.addChild(bone25);
        setRotationAngle(bone25, -0.2705F, 0.2705F, 0.829F);
        bone25.cubeList.add(new ModelBox(bone25, 212, 153, 7.6086F, 4.8005F, 2.2276F, 1, 1, 4, 0.0F, false));

        bone26 = new ModelRenderer(this);
        bone26.setRotationPoint(-1.43F, -2.2F, 56.46F);
        stock.addChild(bone26);
        setRotationAngle(bone26, -0.576F, 0.0698F, 0.0F);
        bone26.cubeList.add(new ModelBox(bone26, 212, 153, 3.0015F, 6.4015F, 8.1547F, 1, 5, 2, 0.0F, false));

        bone27 = new ModelRenderer(this);
        bone27.setRotationPoint(-1.02F, -2.22F, 54.54F);
        stock.addChild(bone27);
        setRotationAngle(bone27, -0.4451F, -0.0611F, 0.0F);
        bone27.cubeList.add(new ModelBox(bone27, 212, 153, 3.1127F, 7.1732F, 6.4786F, 1, 5, 2, 0.0F, false));

        bone28 = new ModelRenderer(this);
        bone28.setRotationPoint(-0.96F, 2.29F, 53.7F);
        stock.addChild(bone28);
        setRotationAngle(bone28, -0.4538F, -0.0611F, 0.0524F);
        bone28.cubeList.add(new ModelBox(bone28, 212, 153, 3.5681F, 7.1473F, 6.5192F, 1, 5, 2, 0.0F, false));

        bone29 = new ModelRenderer(this);
        bone29.setRotationPoint(2.7713F, 4.9076F, 55.3132F);
        stock.addChild(bone29);
        setRotationAngle(bone29, -0.5847F, -0.0611F, -0.0349F);
        bone29.cubeList.add(new ModelBox(bone29, 212, 153, -0.9218F, 4.7385F, 3.8222F, 1, 5, 2, 0.0F, false));

        bone30 = new ModelRenderer(this);
        bone30.setRotationPoint(2.7713F, 7.5776F, 53.7032F);
        stock.addChild(bone30);
        setRotationAngle(bone30, -0.5847F, 0.096F, -0.0349F);
        bone30.cubeList.add(new ModelBox(bone30, 212, 153, -1.2017F, 5.9653F, 3.9815F, 1, 6, 3, 0.0F, false));

        bone31 = new ModelRenderer(this);
        bone31.setRotationPoint(2.9934F, 11.7658F, 52.8428F);
        stock.addChild(bone31);
        setRotationAngle(bone31, -0.2705F, 0.5498F, 1.3526F);
        bone31.cubeList.add(new ModelBox(bone31, 212, 153, 6.9988F, 1.3008F, 2.5396F, 1, 2, 2, 0.0F, false));

        bone32 = new ModelRenderer(this);
        bone32.setRotationPoint(2.6362F, 12.0719F, 52.1305F);
        stock.addChild(bone32);
        setRotationAngle(bone32, -0.0524F, 0.5498F, 1.3526F);
        bone32.cubeList.add(new ModelBox(bone32, 212, 153, 6.726F, 1.2661F, 3.527F, 1, 2, 2, 0.0F, false));

        bone33 = new ModelRenderer(this);
        bone33.setRotationPoint(2.8413F, 16.0276F, 55.3132F);
        stock.addChild(bone33);
        setRotationAngle(bone33, -0.5847F, -0.0611F, 0.096F);
        bone33.cubeList.add(new ModelBox(bone33, 212, 153, -5.6149F, 5.3666F, -0.8257F, 5, 1, 2, 0.0F, false));

        bone34 = new ModelRenderer(this);
        bone34.setRotationPoint(-0.9886F, 11.3433F, 51.1138F);
        stock.addChild(bone34);
        setRotationAngle(bone34, -0.672F, 0.0262F, 0.096F);
        bone34.cubeList.add(new ModelBox(bone34, 212, 153, -1.7839F, 6.2798F, 4.4174F, 5, 1, 2, 0.0F, false));

        bone35 = new ModelRenderer(this);
        bone35.setRotationPoint(-0.9886F, 10.7833F, 51.1138F);
        stock.addChild(bone35);
        setRotationAngle(bone35, -0.4538F, -0.0611F, 0.096F);
        bone35.cubeList.add(new ModelBox(bone35, 212, 153, -1.5979F, 5.2761F, 4.7156F, 1, 3, 2, 0.0F, false));

        bone36 = new ModelRenderer(this);
        bone36.setRotationPoint(-0.9886F, 10.7833F, 51.1138F);
        stock.addChild(bone36);
        setRotationAngle(bone36, -0.5847F, 0.0262F, 0.0087F);
        bone36.cubeList.add(new ModelBox(bone36, 212, 153, -2.543F, 4.7619F, 3.8053F, 1, 3, 2, 0.0F, false));

        bone37 = new ModelRenderer(this);
        bone37.setRotationPoint(-0.7386F, 8.9533F, 54.0038F);
        stock.addChild(bone37);
        setRotationAngle(bone37, -0.5847F, 0.0262F, 0.0087F);
        bone37.cubeList.add(new ModelBox(bone37, 212, 153, -2.543F, 1.7619F, 3.8053F, 1, 6, 2, 0.0F, false));

        bone38 = new ModelRenderer(this);
        bone38.setRotationPoint(-2.8486F, 6.4533F, 54.0038F);
        stock.addChild(bone38);
        setRotationAngle(bone38, -0.5847F, -0.1047F, 0.0087F);
        bone38.cubeList.add(new ModelBox(bone38, 212, 153, -0.5428F, 4.2588F, 3.81F, 1, 6, 2, 0.0F, false));

        bone39 = new ModelRenderer(this);
        bone39.setRotationPoint(-2.8486F, 5.6233F, 53.0238F);
        stock.addChild(bone39);
        setRotationAngle(bone39, -0.4538F, -0.1047F, 0.0087F);
        bone39.cubeList.add(new ModelBox(bone39, 212, 153, 0.2191F, -0.9958F, 5.7265F, 1, 6, 2, 0.0F, false));

        bone40 = new ModelRenderer(this);
        bone40.setRotationPoint(-2.7068F, 1.383F, 57.0389F);
        stock.addChild(bone40);
        setRotationAngle(bone40, -0.4538F, -0.3665F, 0.1833F);
        bone40.cubeList.add(new ModelBox(bone40, 212, 153, 0.9997F, 5.2157F, 2.5683F, 1, 6, 1, 0.0F, false));

        bullet = new ModelRenderer(this);
        bullet.setRotationPoint(0.0F, 32.0F, 0.0F);
        bullet.cubeList.add(new ModelBox(bullet, 6, 511, -1.6188F, -1.5F, -4.0F, 3, 3, 1, 0.0F, false));
        bullet.cubeList.add(new ModelBox(bullet, 6, 511, -1.6188F, -1.5F, -8.4F, 3, 3, 4, 0.0F, false));
        bullet.cubeList.add(new ModelBox(bullet, 6, 511, -1.1188F, -1.0F, -12.6F, 2, 2, 2, 0.0F, false));
        bullet.cubeList.add(new ModelBox(bullet, 6, 511, -0.8188F, -1.3F, -11.0F, 2, 2, 7, 0.0F, false));
        bullet.cubeList.add(new ModelBox(bullet, 6, 511, -0.8188F, -0.7F, -11.0F, 2, 2, 7, 0.0F, false));
        bullet.cubeList.add(new ModelBox(bullet, 6, 511, -1.4188F, -1.3F, -11.0F, 2, 2, 7, 0.0F, true));
        bullet.cubeList.add(new ModelBox(bullet, 6, 511, -1.4188F, -0.7F, -11.0F, 2, 2, 7, 0.0F, true));

        addEntry(AnimationElement.CHARGING, stack -> bolt);
        addEntry(AnimationElement.BULLET, stack -> bullet);
    }
}

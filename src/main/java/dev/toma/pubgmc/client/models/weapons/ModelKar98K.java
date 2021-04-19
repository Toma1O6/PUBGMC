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
        kar98k.cubeList.add(new ModelBox(kar98k, 142, 156, -2.6188F, 0.7071F, -2.0F, 5, 7, 10, 0.0F, false));
        kar98k.cubeList.add(new ModelBox(kar98k, 142, 156, -2.6188F, 0.7071F, 8.0F, 5, 7, 11, 0.0F, false));
        kar98k.cubeList.add(new ModelBox(kar98k, 142, 156, -2.6188F, 3.3048F, 25.8984F, 5, 3, 5, 0.0F, false));
        kar98k.cubeList.add(new ModelBox(kar98k, 142, 156, -1.6188F, 2.3048F, 25.8984F, 3, 1, 5, 0.0F, false));
        kar98k.cubeList.add(new ModelBox(kar98k, 142, 156, -2.6188F, 1.6186F, 34.3138F, 5, 3, 2, 0.0F, false));
        kar98k.cubeList.add(new ModelBox(kar98k, 142, 156, -1.6188F, 0.6186F, 34.3138F, 3, 1, 2, 0.0F, false));
        kar98k.cubeList.add(new ModelBox(kar98k, 142, 156, -2.6188F, 8.0561F, 28.3138F, 5, 6, 11, 0.0F, false));
        kar98k.cubeList.add(new ModelBox(kar98k, 142, 156, -2.6188F, 13.0561F, 39.3138F, 5, 1, 5, 0.0F, false));
        kar98k.cubeList.add(new ModelBox(kar98k, 142, 156, -2.6188F, 9.6186F, 64.0559F, 5, 18, 8, 0.0F, false));
        kar98k.cubeList.add(new ModelBox(kar98k, 142, 156, -1.6188F, 8.6186F, 64.0559F, 3, 1, 8, 0.0F, false));
        kar98k.cubeList.add(new ModelBox(kar98k, 142, 156, -2.6188F, 0.7071F, -14.0F, 5, 7, 1, 0.0F, false));
        kar98k.cubeList.add(new ModelBox(kar98k, 142, 156, -2.6188F, 0.7071F, -13.0F, 1, 7, 11, 0.0F, false));
        kar98k.cubeList.add(new ModelBox(kar98k, 142, 156, 1.3812F, 0.7071F, -13.0F, 1, 7, 11, 0.0F, true));
        kar98k.cubeList.add(new ModelBox(kar98k, 5, 158, 1.0F, -3.0312F, -14.0F, 1, 4, 1, 0.0F, false));
        kar98k.cubeList.add(new ModelBox(kar98k, 5, 158, -1.0F, -3.0312F, -14.0F, 2, 1, 1, 0.0F, false));
        kar98k.cubeList.add(new ModelBox(kar98k, 5, 158, -1.0F, -0.0312F, -14.0F, 2, 1, 1, 0.0F, false));
        kar98k.cubeList.add(new ModelBox(kar98k, 5, 158, -2.0F, -3.0312F, -14.0F, 1, 4, 1, 0.0F, false));
        kar98k.cubeList.add(new ModelBox(kar98k, 12, 162, -1.6188F, -4.0312F, -23.0F, 3, 1, 8, 0.0F, false));
        kar98k.cubeList.add(new ModelBox(kar98k, 12, 162, -2.1188F, -5.0312F, -17.0F, 4, 1, 1, 0.0F, false));
        kar98k.cubeList.add(new ModelBox(kar98k, 0, 93, 1.0F, -3.0312F, -2.0F, 1, 4, 2, 0.0F, false));
        kar98k.cubeList.add(new ModelBox(kar98k, 0, 93, -2.2376F, -3.0312F, -2.0F, 1, 4, 2, 0.0F, true));
        kar98k.cubeList.add(new ModelBox(kar98k, 0, 93, 1.0F, -3.0312F, 10.0F, 1, 4, 2, 0.0F, false));
        kar98k.cubeList.add(new ModelBox(kar98k, 0, 93, -2.2376F, -3.0312F, 10.0F, 1, 4, 2, 0.0F, true));
        kar98k.cubeList.add(new ModelBox(kar98k, 0, 93, -1.6188F, -4.0312F, -2.0F, 3, 1, 14, 0.0F, false));
        kar98k.cubeList.add(new ModelBox(kar98k, 0, 93, -1.9118F, -4.1952F, -2.0F, 1, 1, 14, 0.0F, false));
        kar98k.cubeList.add(new ModelBox(kar98k, 0, 93, 0.6742F, -4.1952F, -2.0F, 1, 1, 14, 0.0F, true));
        kar98k.cubeList.add(new ModelBox(kar98k, 0, 93, -2.0F, 0.0469F, 14.0F, 1, 1, 5, 0.0F, false));
        kar98k.cubeList.add(new ModelBox(kar98k, 0, 93, 1.0F, 0.0469F, 14.0F, 1, 1, 5, 0.0F, false));
        kar98k.cubeList.add(new ModelBox(kar98k, 142, 156, -2.6188F, -2.2929F, -25.0F, 5, 10, 11, 0.0F, false));
        kar98k.cubeList.add(new ModelBox(kar98k, 142, 156, -2.6188F, -2.2929F, -36.0F, 5, 4, 11, 0.0F, false));
        kar98k.cubeList.add(new ModelBox(kar98k, 142, 156, -2.6188F, -2.2929F, -47.0F, 5, 4, 11, 0.0F, false));
        kar98k.cubeList.add(new ModelBox(kar98k, 142, 156, -2.6188F, -2.2929F, -58.0F, 5, 4, 11, 0.0F, false));
        kar98k.cubeList.add(new ModelBox(kar98k, 142, 156, -2.6188F, -2.2929F, -69.0F, 5, 4, 11, 0.0F, false));
        kar98k.cubeList.add(new ModelBox(kar98k, 142, 156, -2.6188F, 1.7071F, -69.0F, 5, 2, 6, 0.0F, false));
        kar98k.cubeList.add(new ModelBox(kar98k, 142, 156, -2.1188F, -1.7929F, -82.0F, 1, 4, 11, 0.0F, false));
        kar98k.cubeList.add(new ModelBox(kar98k, 142, 156, 0.8812F, -1.7929F, -82.0F, 1, 4, 11, 0.0F, false));
        kar98k.cubeList.add(new ModelBox(kar98k, 142, 156, -2.6188F, -2.2929F, -71.0F, 5, 6, 2, 0.0F, false));
        kar98k.cubeList.add(new ModelBox(kar98k, 142, 156, -1.6188F, -3.2929F, -25.0F, 3, 1, 11, 0.0F, false));
        kar98k.cubeList.add(new ModelBox(kar98k, 142, 156, -1.6188F, -3.2929F, -36.0F, 3, 1, 11, 0.0F, false));
        kar98k.cubeList.add(new ModelBox(kar98k, 142, 156, -1.6188F, -3.2929F, -47.0F, 3, 1, 11, 0.0F, false));
        kar98k.cubeList.add(new ModelBox(kar98k, 142, 156, -1.6188F, -3.2929F, -58.0F, 3, 1, 11, 0.0F, false));
        kar98k.cubeList.add(new ModelBox(kar98k, 142, 156, -1.6188F, -3.2929F, -69.0F, 3, 1, 11, 0.0F, false));
        kar98k.cubeList.add(new ModelBox(kar98k, 142, 156, -1.6188F, 3.7071F, -69.0F, 3, 1, 7, 0.0F, false));
        kar98k.cubeList.add(new ModelBox(kar98k, 142, 156, -1.1188F, -2.7929F, -82.0F, 2, 1, 11, 0.0F, false));
        kar98k.cubeList.add(new ModelBox(kar98k, 0, 12, -1.1188F, -1.9062F, -108.0F, 2, 2, 29, 0.0F, false));
        kar98k.cubeList.add(new ModelBox(kar98k, 12, 162, -1.6188F, -1.9062F, -107.0F, 3, 2, 3, 0.0F, false));
        kar98k.cubeList.add(new ModelBox(kar98k, 12, 162, -1.1188F, -2.4062F, -107.0F, 2, 3, 3, 0.0F, false));
        kar98k.cubeList.add(new ModelBox(kar98k, 12, 162, -0.6188F, -5.4062F, -106.5F, 1, 3, 2, 0.0F, false));
        kar98k.cubeList.add(new ModelBox(kar98k, 0, 12, -1.1188F, 0.2852F, -90.0F, 2, 2, 11, 0.0F, false));
        kar98k.cubeList.add(new ModelBox(kar98k, 142, 156, -1.1188F, 2.2071F, -82.0F, 2, 1, 11, 0.0F, false));
        kar98k.cubeList.add(new ModelBox(kar98k, 142, 156, -1.6188F, -3.2929F, -71.0F, 3, 1, 2, 0.0F, false));
        kar98k.cubeList.add(new ModelBox(kar98k, 142, 156, -1.6188F, 3.7071F, -71.0F, 3, 1, 2, 0.0F, false));
        kar98k.cubeList.add(new ModelBox(kar98k, 142, 156, -1.6188F, 7.7071F, -25.0F, 3, 1, 11, 0.0F, false));
        kar98k.cubeList.add(new ModelBox(kar98k, 142, 156, -1.6188F, 7.7071F, -14.0F, 3, 1, 11, 0.0F, false));
        kar98k.cubeList.add(new ModelBox(kar98k, 142, 156, -1.6188F, 7.7071F, -3.0F, 3, 1, 11, 0.0F, false));
        kar98k.cubeList.add(new ModelBox(kar98k, 142, 156, -1.6188F, 7.7071F, 8.0F, 3, 1, 13, 0.0F, false));
        kar98k.cubeList.add(new ModelBox(kar98k, 17, 154, -1.1188F, 7.9871F, -0.5F, 2, 1, 12, 0.0F, false));
        kar98k.cubeList.add(new ModelBox(kar98k, 142, 156, -1.6188F, 0.7071F, 19.0F, 3, 2, 4, 0.0F, false));

        bone13 = new ModelRenderer(this);
        bone13.setRotationPoint(5.3812F, 0.0F, 0.0F);
        kar98k.addChild(bone13);
        setRotationAngle(bone13, -0.3491F, 0.0F, 0.0F);
        bone13.cubeList.add(new ModelBox(bone13, 142, 156, -8.0F, -5.834F, 18.0962F, 5, 7, 9, 0.0F, false));
        bone13.cubeList.add(new ModelBox(bone13, 142, 156, -7.0F, -6.834F, 18.0962F, 3, 1, 9, 0.0F, false));

        bone16 = new ModelRenderer(this);
        bone16.setRotationPoint(5.3812F, 14.832F, 17.1758F);
        kar98k.addChild(bone16);
        setRotationAngle(bone16, 0.4363F, 0.0F, 0.0F);
        bone16.cubeList.add(new ModelBox(bone16, 142, 156, -8.0F, -4.7325F, 15.1168F, 5, 7, 6, 0.0F, false));
        bone16.cubeList.add(new ModelBox(bone16, 142, 156, -7.0F, -5.7325F, 17.1168F, 3, 1, 4, 0.0F, false));

        bone20 = new ModelRenderer(this);
        bone20.setRotationPoint(-5.3812F, -12.2176F, 22.3737F);
        bone16.addChild(bone20);
        setRotationAngle(bone20, 0.0F, 0.0F, 0.7854F);
        bone20.cubeList.add(new ModelBox(bone20, 142, 156, 5.5623F, 3.609F, -5.2569F, 1, 1, 4, 0.0F, false));
        bone20.cubeList.add(new ModelBox(bone20, 142, 156, 3.441F, 5.7303F, -5.2569F, 1, 1, 4, 0.0F, false));
        bone20.cubeList.add(new ModelBox(bone20, 142, 156, 3.441F, 6.1445F, -5.2569F, 1, 1, 4, 0.0F, false));
        bone20.cubeList.add(new ModelBox(bone20, 142, 156, 5.9765F, 3.609F, -5.2569F, 1, 1, 4, 0.0F, false));

        bone17 = new ModelRenderer(this);
        bone17.setRotationPoint(5.3812F, 14.832F, 26.1758F);
        kar98k.addChild(bone17);
        setRotationAngle(bone17, -0.2618F, 0.0F, 0.0F);
        bone17.cubeList.add(new ModelBox(bone17, 142, 156, -8.0F, -15.387F, 6.3727F, 5, 7, 11, 0.0F, false));
        bone17.cubeList.add(new ModelBox(bone17, 142, 156, -7.0F, -16.387F, 6.3727F, 3, 1, 11, 0.0F, false));
        bone17.cubeList.add(new ModelBox(bone17, 142, 156, -8.0F, -15.387F, 17.3727F, 5, 7, 11, 0.0F, false));
        bone17.cubeList.add(new ModelBox(bone17, 142, 156, -7.0F, -16.387F, 17.3727F, 3, 1, 11, 0.0F, false));
        bone17.cubeList.add(new ModelBox(bone17, 142, 156, -8.0F, -15.387F, 28.3727F, 5, 9, 11, 0.0F, false));
        bone17.cubeList.add(new ModelBox(bone17, 142, 156, -7.0F, -16.387F, 28.3727F, 3, 1, 11, 0.0F, false));

        bone19 = new ModelRenderer(this);
        bone19.setRotationPoint(0.0F, 0.0F, 0.0F);
        bone17.addChild(bone19);
        setRotationAngle(bone19, 0.0F, 0.0F, 0.7854F);
        bone19.cubeList.add(new ModelBox(bone19, 142, 156, -14.4158F, -8.7589F, 6.3727F, 1, 1, 11, 0.0F, false));
        bone19.cubeList.add(new ModelBox(bone19, 142, 156, -14.4158F, -8.7589F, 17.3727F, 1, 1, 11, 0.0F, false));
        bone19.cubeList.add(new ModelBox(bone19, 142, 156, -14.4158F, -8.7589F, 28.3727F, 1, 1, 9, 0.0F, false));
        bone19.cubeList.add(new ModelBox(bone19, 142, 156, -16.5371F, -6.6376F, 28.3727F, 1, 1, 9, 0.0F, false));
        bone19.cubeList.add(new ModelBox(bone19, 142, 156, -16.5371F, -6.6376F, 17.3727F, 1, 1, 11, 0.0F, false));
        bone19.cubeList.add(new ModelBox(bone19, 142, 156, -16.5371F, -6.6376F, 6.3727F, 1, 1, 11, 0.0F, false));
        bone19.cubeList.add(new ModelBox(bone19, 142, 156, -14.0015F, -8.7589F, 6.3727F, 1, 1, 11, 0.0F, false));
        bone19.cubeList.add(new ModelBox(bone19, 142, 156, -14.0015F, -8.7589F, 17.3727F, 1, 1, 11, 0.0F, false));
        bone19.cubeList.add(new ModelBox(bone19, 142, 156, -14.0015F, -8.7589F, 28.3727F, 1, 1, 9, 0.0F, false));
        bone19.cubeList.add(new ModelBox(bone19, 142, 156, -16.5371F, -6.2234F, 28.3727F, 1, 1, 9, 0.0F, false));
        bone19.cubeList.add(new ModelBox(bone19, 142, 156, -16.5371F, -6.2234F, 17.3727F, 1, 1, 11, 0.0F, false));
        bone19.cubeList.add(new ModelBox(bone19, 142, 156, -16.5371F, -6.2234F, 6.3727F, 1, 1, 11, 0.0F, false));

        bone18 = new ModelRenderer(this);
        bone18.setRotationPoint(5.3812F, 14.832F, 26.1758F);
        kar98k.addChild(bone18);
        setRotationAngle(bone18, -0.5236F, 0.0F, 0.0F);
        bone18.cubeList.add(new ModelBox(bone18, 142, 156, -8.0F, -14.8665F, 6.1985F, 5, 7, 11, 0.0F, false));
        bone18.cubeList.add(new ModelBox(bone18, 142, 156, -8.0F, -15.8665F, 17.1985F, 5, 8, 11, 0.0F, false));
        bone18.cubeList.add(new ModelBox(bone18, 142, 156, -8.0F, -15.8665F, 28.1985F, 5, 8, 11, 0.0F, false));

        bone14 = new ModelRenderer(this);
        bone14.setRotationPoint(5.3812F, -7.918F, 0.0F);
        kar98k.addChild(bone14);
        setRotationAngle(bone14, -1.0472F, 0.0F, 0.0F);
        bone14.cubeList.add(new ModelBox(bone14, 142, 156, -8.0F, -19.0157F, 25.0721F, 5, 7, 10, 0.0F, false));
        bone14.cubeList.add(new ModelBox(bone14, 142, 156, -7.0F, -19.0157F, 35.0721F, 3, 7, 1, 0.0F, false));

        bone15 = new ModelRenderer(this);
        bone15.setRotationPoint(0.0F, 0.0F, 0.0F);
        bone14.addChild(bone15);
        setRotationAngle(bone15, 0.0F, 0.7854F, 0.0F);
        bone15.cubeList.add(new ModelBox(bone15, 142, 156, -27.921F, -19.0157F, 21.6785F, 1, 7, 1, 0.0F, false));
        bone15.cubeList.add(new ModelBox(bone15, 142, 156, -28.3352F, -19.0157F, 21.6785F, 1, 7, 1, 0.0F, false));
        bone15.cubeList.add(new ModelBox(bone15, 142, 156, -30.4565F, -19.0157F, 19.5571F, 1, 7, 1, 0.0F, false));
        bone15.cubeList.add(new ModelBox(bone15, 142, 156, -30.4565F, -19.0157F, 19.1429F, 1, 7, 1, 0.0F, false));

        bone12 = new ModelRenderer(this);
        bone12.setRotationPoint(1.0F, -12.2383F, -15.0F);
        kar98k.addChild(bone12);
        setRotationAngle(bone12, 0.1745F, 0.0F, 0.0F);
        bone12.cubeList.add(new ModelBox(bone12, 12, 162, -0.6188F, 6.6934F, -9.3035F, 1, 1, 8, 0.0F, false));
        bone12.cubeList.add(new ModelBox(bone12, 12, 162, -2.6188F, 6.6934F, -9.3035F, 1, 1, 8, 0.0F, true));

        bone10 = new ModelRenderer(this);
        bone10.setRotationPoint(0.0F, -3.0F, -30.5F);
        kar98k.addChild(bone10);
        setRotationAngle(bone10, -0.1047F, 0.0F, 0.0F);
        bone10.cubeList.add(new ModelBox(bone10, 142, 156, -2.6198F, 4.0736F, -4.411F, 5, 6, 11, 0.0F, false));
        bone10.cubeList.add(new ModelBox(bone10, 142, 156, -2.6198F, 5.0736F, -15.411F, 5, 5, 11, 0.0F, false));
        bone10.cubeList.add(new ModelBox(bone10, 142, 156, -2.6198F, 6.0736F, -26.411F, 5, 4, 11, 0.0F, false));
        bone10.cubeList.add(new ModelBox(bone10, 142, 156, -2.6198F, 7.0736F, -32.411F, 5, 3, 6, 0.0F, false));
        bone10.cubeList.add(new ModelBox(bone10, 142, 156, -1.6188F, 10.0736F, -32.411F, 3, 1, 13, 0.0F, false));
        bone10.cubeList.add(new ModelBox(bone10, 142, 156, -1.6188F, 10.0736F, -19.411F, 3, 1, 13, 0.0F, false));
        bone10.cubeList.add(new ModelBox(bone10, 142, 156, -1.6188F, 10.0736F, -6.411F, 3, 1, 13, 0.0F, false));

        bone11 = new ModelRenderer(this);
        bone11.setRotationPoint(6.501F, 3.0F, 30.5F);
        bone10.addChild(bone11);
        setRotationAngle(bone11, 0.0F, 0.0F, -0.7854F);
        bone11.cubeList.add(new ModelBox(bone11, 142, 156, -8.9149F, 1.0886F, -36.911F, 1, 1, 13, 0.0F, false));
        bone11.cubeList.add(new ModelBox(bone11, 142, 156, -11.4511F, -1.4476F, -36.911F, 1, 1, 13, 0.0F, false));
        bone11.cubeList.add(new ModelBox(bone11, 142, 156, -8.9149F, 1.0886F, -49.911F, 1, 1, 13, 0.0F, false));
        bone11.cubeList.add(new ModelBox(bone11, 142, 156, -11.4511F, -1.4476F, -49.911F, 1, 1, 13, 0.0F, false));
        bone11.cubeList.add(new ModelBox(bone11, 142, 156, -8.9149F, 1.0886F, -62.911F, 1, 1, 13, 0.0F, false));
        bone11.cubeList.add(new ModelBox(bone11, 142, 156, -11.4511F, -1.4476F, -62.911F, 1, 1, 13, 0.0F, false));
        bone11.cubeList.add(new ModelBox(bone11, 142, 156, -9.3291F, 1.0886F, -36.911F, 1, 1, 13, 0.0F, false));
        bone11.cubeList.add(new ModelBox(bone11, 142, 156, -11.4504F, -1.0327F, -36.911F, 1, 1, 13, 0.0F, false));
        bone11.cubeList.add(new ModelBox(bone11, 142, 156, -9.3291F, 1.0886F, -49.911F, 1, 1, 13, 0.0F, false));
        bone11.cubeList.add(new ModelBox(bone11, 142, 156, -11.4504F, -1.0327F, -49.911F, 1, 1, 13, 0.0F, false));
        bone11.cubeList.add(new ModelBox(bone11, 142, 156, -9.3291F, 1.0886F, -62.911F, 1, 1, 13, 0.0F, false));
        bone11.cubeList.add(new ModelBox(bone11, 142, 156, -11.4504F, -1.0327F, -62.911F, 1, 1, 13, 0.0F, false));

        bone9 = new ModelRenderer(this);
        bone9.setRotationPoint(5.3812F, 0.0F, 0.0F);
        kar98k.addChild(bone9);
        setRotationAngle(bone9, 0.0F, 0.0F, 0.7854F);
        bone9.cubeList.add(new ModelBox(bone9, 142, 156, -7.2781F, 2.6213F, -25.0F, 1, 1, 11, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 142, 156, -7.2781F, 2.6213F, -36.0F, 1, 1, 11, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 142, 156, -7.2781F, 2.6213F, -47.0F, 1, 1, 11, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 142, 156, -7.2781F, 2.6213F, -58.0F, 1, 1, 11, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 142, 156, -7.2781F, 2.6213F, -69.0F, 1, 1, 11, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 142, 156, -7.2781F, 2.6213F, -71.0F, 1, 1, 2, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 142, 156, -2.6213F, 7.2781F, -71.0F, 1, 1, 2, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 142, 156, -5.1568F, 0.4999F, -36.0F, 1, 1, 11, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 142, 156, -5.1568F, 0.4999F, -47.0F, 1, 1, 11, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 142, 156, -5.1568F, 0.4999F, -58.0F, 1, 1, 11, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 142, 156, -5.1568F, 0.4999F, -69.0F, 1, 1, 11, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 142, 156, -0.4999F, 4.7426F, -69.0F, 1, 1, 7, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 142, 156, -5.1568F, 0.4999F, -71.0F, 1, 1, 2, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 142, 156, -0.4999F, 5.1568F, -71.0F, 1, 1, 2, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 142, 156, -4.7426F, 0.4999F, -36.0F, 1, 1, 11, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 142, 156, -4.7426F, 0.4999F, -47.0F, 1, 1, 11, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 142, 156, -4.7426F, 0.4999F, -58.0F, 1, 1, 11, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 142, 156, -4.7426F, 0.4999F, -69.0F, 1, 1, 11, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 142, 156, -0.4999F, 5.1568F, -69.0F, 1, 1, 7, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 142, 156, -2.6213F, 7.2781F, -69.0F, 1, 1, 7, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 142, 156, -3.0355F, 7.2781F, -69.0F, 1, 1, 7, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 142, 156, -4.7426F, 1.207F, -82.0F, 1, 1, 11, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 142, 156, -6.571F, 3.0355F, -82.0F, 1, 1, 11, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 142, 156, -6.571F, 2.6213F, -82.0F, 1, 1, 11, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 142, 156, -5.1568F, 1.207F, -82.0F, 1, 1, 11, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 142, 156, -1.9142F, 4.4497F, -82.0F, 1, 1, 11, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 142, 156, -3.3284F, 5.8639F, -82.0F, 1, 1, 11, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 142, 156, -1.9142F, 4.0355F, -82.0F, 1, 1, 11, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 142, 156, -3.7426F, 5.8639F, -82.0F, 1, 1, 11, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 142, 156, -4.7426F, 0.4999F, -71.0F, 1, 1, 2, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 142, 156, -0.4999F, 4.7426F, -71.0F, 1, 1, 2, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 142, 156, -4.7426F, 0.4999F, -25.0F, 1, 1, 11, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 142, 156, 2.3285F, 7.571F, -25.0F, 1, 1, 11, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 142, 156, 2.3285F, 7.571F, -14.0F, 1, 1, 11, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 142, 156, 2.3285F, 7.571F, -3.0F, 1, 1, 11, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 142, 156, 3.2659F, 8.9227F, 62.0559F, 1, 1, 10, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 142, 156, -2.3909F, 3.2658F, 34.3138F, 1, 1, 2, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 142, 156, -1.1987F, 4.4581F, 24.8984F, 1, 1, 6, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 142, 156, -3.32F, 6.5794F, 24.8984F, 1, 1, 6, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 142, 156, -4.5122F, 5.3871F, 34.3138F, 1, 1, 2, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 142, 156, -4.5122F, 5.8014F, 34.3138F, 1, 1, 2, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 142, 156, 1.1446F, 11.044F, 62.0559F, 1, 1, 10, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 142, 156, 3.6802F, 8.9227F, 65.0559F, 1, 1, 7, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 142, 156, -1.9767F, 3.2658F, 34.3138F, 1, 1, 2, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 142, 156, -0.7844F, 4.4581F, 24.8984F, 1, 1, 6, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 142, 156, -3.32F, 6.9936F, 24.8984F, 1, 1, 6, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 142, 156, 1.1446F, 11.4582F, 65.0559F, 1, 1, 7, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 142, 156, 2.3285F, 7.571F, 8.0F, 1, 1, 11, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 142, 156, 2.3285F, 7.9852F, -25.0F, 1, 1, 11, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 142, 156, 2.3285F, 7.9852F, -14.0F, 1, 1, 11, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 142, 156, 2.3285F, 7.9852F, -3.0F, 1, 1, 11, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 142, 156, 2.3285F, 7.9852F, 8.0F, 1, 1, 13, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 142, 156, 0.2072F, 10.1065F, -25.0F, 1, 1, 11, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 142, 156, 0.2072F, 10.1065F, -14.0F, 1, 1, 11, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 142, 156, 0.2072F, 10.1065F, -3.0F, 1, 1, 11, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 142, 156, 0.2072F, 10.1065F, 8.0F, 1, 1, 13, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 142, 156, -0.207F, 10.1065F, -25.0F, 1, 1, 11, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 142, 156, -0.207F, 10.1065F, -14.0F, 1, 1, 11, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 142, 156, -0.207F, 10.1065F, -3.0F, 1, 1, 11, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 142, 156, -0.207F, 10.1065F, 8.0F, 1, 1, 11, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 142, 156, -5.1568F, 0.4999F, -25.0F, 1, 1, 11, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 142, 156, -7.2781F, 3.0355F, -25.0F, 1, 1, 11, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 142, 156, -7.2781F, 3.0355F, -36.0F, 1, 1, 11, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 142, 156, -7.2781F, 3.0355F, -47.0F, 1, 1, 11, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 142, 156, -7.2781F, 3.0355F, -58.0F, 1, 1, 11, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 142, 156, -7.2781F, 3.0355F, -69.0F, 1, 1, 11, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 142, 156, -7.2781F, 3.0355F, -71.0F, 1, 1, 2, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 142, 156, -3.0355F, 7.2781F, -71.0F, 1, 1, 2, 0.0F, false));

        bone7 = new ModelRenderer(this);
        bone7.setRotationPoint(5.9883F, 0.0F, 0.0F);
        kar98k.addChild(bone7);
        setRotationAngle(bone7, 0.0F, 0.2618F, 0.0F);
        bone7.cubeList.add(new ModelBox(bone7, 142, 156, -13.2314F, 0.7071F, 16.125F, 1, 2, 4, 0.0F, false));

        bone8 = new ModelRenderer(this);
        bone8.setRotationPoint(-6.0F, 0.0F, 0.0F);
        kar98k.addChild(bone8);
        setRotationAngle(bone8, 0.0F, -0.2618F, 0.0F);
        bone8.cubeList.add(new ModelBox(bone8, 142, 156, 12.0131F, 0.7071F, 16.1833F, 1, 2, 4, 0.0F, false));

        bolt = new ModelRenderer(this);
        bolt.setRotationPoint(2.0F, 25.0F, -1.0F);
        setRotationAngle(bolt, 0.0F, 0.0F, -0.3491F);
        bolt.cubeList.add(new ModelBox(bolt, 84, 17, -5.8707F, -4.0195F, 13.0F, 4, 2, 2, 0.0F, false));

        bone3 = new ModelRenderer(this);
        bone3.setRotationPoint(0.0F, 0.0F, 0.0F);
        bolt.addChild(bone3);
        setRotationAngle(bone3, 0.0F, 0.0F, -0.8727F);
        bone3.cubeList.add(new ModelBox(bone3, 84, 17, -3.6946F, -7.0813F, 13.0F, 3, 2, 2, 0.0F, false));
        bone3.cubeList.add(new ModelBox(bone3, 84, 17, -6.1088F, -7.0813F, 13.0F, 1, 2, 2, 0.0F, false));
        bone3.cubeList.add(new ModelBox(bone3, 84, 17, -5.4017F, -7.0813F, 14.7071F, 2, 2, 1, 0.0F, false));
        bone3.cubeList.add(new ModelBox(bone3, 84, 17, -5.4017F, -7.0813F, 12.2929F, 2, 2, 1, 0.0F, false));
        bone3.cubeList.add(new ModelBox(bone3, 84, 17, -5.4017F, -7.7884F, 13.0F, 2, 1, 2, 0.0F, false));
        bone3.cubeList.add(new ModelBox(bone3, 84, 17, -5.4017F, -5.3742F, 13.0F, 2, 1, 2, 0.0F, false));

        bone6 = new ModelRenderer(this);
        bone6.setRotationPoint(0.0F, 0.0F, 0.0F);
        bone3.addChild(bone6);
        setRotationAngle(bone6, 0.0F, -0.7854F, 0.0F);
        bone6.cubeList.add(new ModelBox(bone6, 84, 17, 6.287F, -7.0813F, 13.9262F, 1, 2, 1, 0.0F, false));
        bone6.cubeList.add(new ModelBox(bone6, 84, 17, 7.7012F, -7.0813F, 12.512F, 1, 2, 1, 0.0F, false));
        bone6.cubeList.add(new ModelBox(bone6, 84, 17, 4.8728F, -7.0813F, 12.512F, 1, 2, 1, 0.0F, false));
        bone6.cubeList.add(new ModelBox(bone6, 84, 17, 6.287F, -7.0813F, 11.0978F, 1, 2, 1, 0.0F, false));

        bone5 = new ModelRenderer(this);
        bone5.setRotationPoint(0.0F, 0.0F, 0.0F);
        bone3.addChild(bone5);
        setRotationAngle(bone5, 0.7854F, 0.0F, 0.0F);
        bone5.cubeList.add(new ModelBox(bone5, 84, 17, -5.4017F, 6.5136F, 13.6996F, 2, 1, 1, 0.0F, false));
        bone5.cubeList.add(new ModelBox(bone5, 84, 17, -5.4017F, 5.0994F, 12.2854F, 2, 1, 1, 0.0F, false));
        bone5.cubeList.add(new ModelBox(bone5, 84, 17, -5.4017F, 5.0994F, 15.1138F, 2, 1, 1, 0.0F, false));
        bone5.cubeList.add(new ModelBox(bone5, 84, 17, -5.4017F, 3.6852F, 13.6996F, 2, 1, 1, 0.0F, false));

        bone4 = new ModelRenderer(this);
        bone4.setRotationPoint(0.0F, 0.0F, 0.0F);
        bone3.addChild(bone4);
        setRotationAngle(bone4, 0.0F, 0.0F, -0.7854F);
        bone4.cubeList.add(new ModelBox(bone4, 84, 17, 2.1019F, -7.9127F, 13.0F, 1, 1, 2, 0.0F, false));
        bone4.cubeList.add(new ModelBox(bone4, 84, 17, 0.6876F, -9.3269F, 13.0F, 1, 1, 2, 0.0F, false));
        bone4.cubeList.add(new ModelBox(bone4, 84, 17, -0.7266F, -7.9127F, 13.0F, 1, 1, 2, 0.0F, false));
        bone4.cubeList.add(new ModelBox(bone4, 84, 17, 0.6876F, -6.4985F, 13.0F, 1, 1, 2, 0.0F, false));

        boltcarrier = new ModelRenderer(this);
        boltcarrier.setRotationPoint(0.0F, 15.3F, 21.0F);
        boltcarrier.cubeList.add(new ModelBox(boltcarrier, 84, 17, -1.0F, 6.4071F, -28.0F, 2, 3, 14, 0.0F, false));
        boltcarrier.cubeList.add(new ModelBox(boltcarrier, 84, 17, -1.0F, 7.4071F, -14.0F, 2, 2, 14, 0.0F, false));
        boltcarrier.cubeList.add(new ModelBox(boltcarrier, 84, 17, -0.2929F, 6.7F, -28.0F, 2, 2, 14, 0.0F, false));
        boltcarrier.cubeList.add(new ModelBox(boltcarrier, 84, 17, -0.2929F, 6.7F, -42.0F, 2, 2, 14, 0.0F, false));
        boltcarrier.cubeList.add(new ModelBox(boltcarrier, 84, 17, -0.2929F, 6.7F, -14.0F, 2, 2, 14, 0.0F, false));
        boltcarrier.cubeList.add(new ModelBox(boltcarrier, 84, 17, -1.7071F, 6.7F, -28.0F, 2, 2, 14, 0.0F, false));
        boltcarrier.cubeList.add(new ModelBox(boltcarrier, 84, 17, -1.7071F, 6.7F, -42.0F, 2, 2, 14, 0.0F, false));
        boltcarrier.cubeList.add(new ModelBox(boltcarrier, 84, 17, -1.7071F, 6.7F, -14.0F, 2, 2, 14, 0.0F, false));
        boltcarrier.cubeList.add(new ModelBox(boltcarrier, 84, 17, -1.0F, 5.9929F, -28.0F, 2, 2, 14, 0.0F, false));
        boltcarrier.cubeList.add(new ModelBox(boltcarrier, 84, 17, -1.0F, 5.9929F, -42.0F, 2, 2, 14, 0.0F, false));
        boltcarrier.cubeList.add(new ModelBox(boltcarrier, 84, 17, -1.0F, 7.4071F, -42.0F, 2, 2, 14, 0.0F, false));
        boltcarrier.cubeList.add(new ModelBox(boltcarrier, 84, 17, -1.0F, 5.9929F, -14.0F, 2, 2, 14, 0.0F, false));

        bone2 = new ModelRenderer(this);
        bone2.setRotationPoint(-2.0F, 9.7F, -22.0F);
        boltcarrier.addChild(bone2);
        setRotationAngle(bone2, 0.0F, 0.0F, -0.7854F);
        bone2.cubeList.add(new ModelBox(bone2, 84, 17, 2.3284F, -0.0858F, -6.0F, 1, 2, 14, 0.0F, false));
        bone2.cubeList.add(new ModelBox(bone2, 84, 17, 2.3284F, -0.0858F, -20.0F, 1, 2, 14, 0.0F, false));
        bone2.cubeList.add(new ModelBox(bone2, 84, 17, 2.3284F, -0.0858F, 8.0F, 1, 2, 14, 0.0F, false));
        bone2.cubeList.add(new ModelBox(bone2, 84, 17, 0.9142F, -0.5F, -6.0F, 2, 1, 14, 0.0F, false));
        bone2.cubeList.add(new ModelBox(bone2, 84, 17, 0.9142F, -0.5F, -20.0F, 2, 1, 14, 0.0F, false));
        bone2.cubeList.add(new ModelBox(bone2, 84, 17, 0.9142F, -0.5F, 8.0F, 2, 1, 14, 0.0F, false));
        bone2.cubeList.add(new ModelBox(bone2, 84, 17, 2.7426F, -0.5F, -6.0F, 2, 1, 14, 0.0F, false));
        bone2.cubeList.add(new ModelBox(bone2, 84, 17, 2.7426F, -0.5F, -20.0F, 2, 1, 14, 0.0F, false));
        bone2.cubeList.add(new ModelBox(bone2, 84, 17, 2.7426F, -0.5F, 8.0F, 2, 1, 14, 0.0F, false));
        bone2.cubeList.add(new ModelBox(bone2, 84, 17, 2.3284F, -1.9143F, -6.0F, 1, 2, 14, 0.0F, false));
        bone2.cubeList.add(new ModelBox(bone2, 84, 17, 2.3284F, -1.9143F, -20.0F, 1, 2, 14, 0.0F, false));
        bone2.cubeList.add(new ModelBox(bone2, 84, 17, 2.3284F, -1.9143F, 8.0F, 1, 2, 14, 0.0F, false));

        stock = new ModelRenderer(this);
        stock.setRotationPoint(0.0F, 24.0F, -2.0F);


        bone21 = new ModelRenderer(this);
        bone21.setRotationPoint(-0.5F, -2.5F, 53.5F);
        stock.addChild(bone21);
        setRotationAngle(bone21, -0.2618F, -0.1745F, 0.0698F);
        bone21.cubeList.add(new ModelBox(bone21, 212, 153, -0.4639F, 6.7346F, 7.4025F, 5, 1, 2, 0.0F, false));

        bone22 = new ModelRenderer(this);
        bone22.setRotationPoint(-0.5F, -2.75F, 52.66F);
        stock.addChild(bone22);
        setRotationAngle(bone22, -0.3142F, -0.1047F, 0.0F);
        bone22.cubeList.add(new ModelBox(bone22, 212, 153, -1.5547F, 6.2918F, 7.3565F, 5, 1, 2, 0.0F, false));

        bone23 = new ModelRenderer(this);
        bone23.setRotationPoint(-4.0066F, -2.2342F, 59.8428F);
        stock.addChild(bone23);
        setRotationAngle(bone23, -0.2705F, 0.1134F, 0.6545F);
        bone23.cubeList.add(new ModelBox(bone23, 212, 153, 6.068F, 5.3228F, 1.1545F, 1, 1, 2, 0.0F, false));

        bone24 = new ModelRenderer(this);
        bone24.setRotationPoint(-4.1366F, -2.4342F, 58.1528F);
        stock.addChild(bone24);
        setRotationAngle(bone24, -0.2705F, 0.0262F, 0.5672F);
        bone24.cubeList.add(new ModelBox(bone24, 212, 153, 5.5392F, 5.7971F, 0.856F, 1, 1, 2, 0.0F, false));

        bone25 = new ModelRenderer(this);
        bone25.setRotationPoint(1.0434F, -2.7542F, 59.0128F);
        stock.addChild(bone25);
        setRotationAngle(bone25, -0.2705F, 0.2705F, 0.829F);
        bone25.cubeList.add(new ModelBox(bone25, 212, 153, 6.8982F, 4.2021F, 1.8572F, 1, 1, 4, 0.0F, false));

        bone26 = new ModelRenderer(this);
        bone26.setRotationPoint(-1.43F, -2.2F, 56.46F);
        stock.addChild(bone26);
        setRotationAngle(bone26, -0.576F, 0.0698F, 0.0F);
        bone26.cubeList.add(new ModelBox(bone26, 212, 153, 3.0015F, 5.5629F, 7.61F, 1, 5, 2, 0.0F, false));

        bone27 = new ModelRenderer(this);
        bone27.setRotationPoint(-1.02F, -2.22F, 54.54F);
        stock.addChild(bone27);
        setRotationAngle(bone27, -0.4451F, -0.0611F, 0.0F);
        bone27.cubeList.add(new ModelBox(bone27, 212, 153, 3.1127F, 6.2706F, 6.0481F, 1, 5, 2, 0.0F, false));

        bone28 = new ModelRenderer(this);
        bone28.setRotationPoint(-0.96F, 2.29F, 53.7F);
        stock.addChild(bone28);
        setRotationAngle(bone28, -0.4538F, -0.0611F, 0.0524F);
        bone28.cubeList.add(new ModelBox(bone28, 212, 153, 3.5158F, 6.2483F, 6.0843F, 1, 5, 2, 0.0F, false));

        bone29 = new ModelRenderer(this);
        bone29.setRotationPoint(2.7713F, 4.9076F, 55.3132F);
        stock.addChild(bone29);
        setRotationAngle(bone29, -0.5847F, -0.0611F, -0.0349F);
        bone29.cubeList.add(new ModelBox(bone29, 212, 153, -0.8869F, 3.9064F, 3.2688F, 1, 5, 2, 0.0F, false));

        bone30 = new ModelRenderer(this);
        bone30.setRotationPoint(2.7713F, 7.5776F, 53.7032F);
        stock.addChild(bone30);
        setRotationAngle(bone30, -0.5847F, 0.096F, -0.0349F);
        bone30.cubeList.add(new ModelBox(bone30, 212, 153, -1.167F, 5.1301F, 3.4327F, 1, 6, 3, 0.0F, false));

        bone31 = new ModelRenderer(this);
        bone31.setRotationPoint(2.9934F, 11.7658F, 52.8428F);
        stock.addChild(bone31);
        setRotationAngle(bone31, -0.2705F, 0.5498F, 1.3526F);
        bone31.cubeList.add(new ModelBox(bone31, 212, 153, 6.1664F, 1.2285F, 1.9902F, 1, 2, 2, 0.0F, false));

        bone32 = new ModelRenderer(this);
        bone32.setRotationPoint(2.6362F, 12.0719F, 52.1305F);
        stock.addChild(bone32);
        setRotationAngle(bone32, -0.0524F, 0.5498F, 1.3526F);
        bone32.cubeList.add(new ModelBox(bone32, 212, 153, 5.8936F, 1.0766F, 3.0062F, 1, 2, 2, 0.0F, false));

        bone33 = new ModelRenderer(this);
        bone33.setRotationPoint(2.8413F, 16.0276F, 55.3132F);
        stock.addChild(bone33);
        setRotationAngle(bone33, -0.5847F, -0.0611F, 0.096F);
        bone33.cubeList.add(new ModelBox(bone33, 212, 153, -5.7106F, 4.5333F, -1.3702F, 5, 1, 2, 0.0F, false));

        bone34 = new ModelRenderer(this);
        bone34.setRotationPoint(-0.9886F, 11.3433F, 51.1138F);
        stock.addChild(bone34);
        setRotationAngle(bone34, -0.672F, 0.0262F, 0.096F);
        bone34.cubeList.add(new ModelBox(bone34, 212, 153, -1.8797F, 5.5024F, 3.7957F, 5, 1, 2, 0.0F, false));

        bone35 = new ModelRenderer(this);
        bone35.setRotationPoint(-0.9886F, 10.7833F, 51.1138F);
        stock.addChild(bone35);
        setRotationAngle(bone35, -0.4538F, -0.0611F, 0.096F);
        bone35.cubeList.add(new ModelBox(bone35, 212, 153, -1.6936F, 4.3789F, 4.2845F, 1, 3, 2, 0.0F, false));

        bone36 = new ModelRenderer(this);
        bone36.setRotationPoint(-0.9886F, 10.7833F, 51.1138F);
        stock.addChild(bone36);
        setRotationAngle(bone36, -0.5847F, 0.0262F, 0.0087F);
        bone36.cubeList.add(new ModelBox(bone36, 212, 153, -2.5517F, 3.9282F, 3.2531F, 1, 3, 2, 0.0F, false));

        bone37 = new ModelRenderer(this);
        bone37.setRotationPoint(-0.7386F, 8.9533F, 54.0038F);
        stock.addChild(bone37);
        setRotationAngle(bone37, -0.5847F, 0.0262F, 0.0087F);
        bone37.cubeList.add(new ModelBox(bone37, 212, 153, -2.5517F, 0.9282F, 3.2531F, 1, 6, 2, 0.0F, false));

        bone38 = new ModelRenderer(this);
        bone38.setRotationPoint(-2.8486F, 6.4533F, 54.0038F);
        stock.addChild(bone38);
        setRotationAngle(bone38, -0.5847F, -0.1047F, 0.0087F);
        bone38.cubeList.add(new ModelBox(bone38, 212, 153, -0.5515F, 3.4245F, 3.2589F, 1, 6, 2, 0.0F, false));

        bone39 = new ModelRenderer(this);
        bone39.setRotationPoint(-2.8486F, 5.6233F, 53.0238F);
        stock.addChild(bone39);
        setRotationAngle(bone39, -0.4538F, -0.1047F, 0.0087F);
        bone39.cubeList.add(new ModelBox(bone39, 212, 153, 0.2104F, -1.895F, 5.2889F, 1, 6, 2, 0.0F, false));

        bone40 = new ModelRenderer(this);
        bone40.setRotationPoint(-2.7068F, 1.383F, 57.0389F);
        stock.addChild(bone40);
        setRotationAngle(bone40, -0.4538F, -0.3665F, 0.1833F);
        bone40.cubeList.add(new ModelBox(bone40, 212, 153, 0.8296F, 4.3033F, 2.196F, 1, 6, 1, 0.0F, false));

        bone = new ModelRenderer(this);
        bone.setRotationPoint(2.8F, 6.5F, 60.5F);
        stock.addChild(bone);
        setRotationAngle(bone, -0.5236F, 0.0F, 0.0F);
        bone.cubeList.add(new ModelBox(bone, 18, 86, -0.5F, 6.4282F, -11.5F, 1, 3, 21, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 18, 86, 0.366F, 6.9282F, -11.5F, 1, 2, 21, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 105, 181, 0.866F, 7.4282F, -10.7F, 1, 1, 1, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 105, 181, 0.866F, 7.4282F, -6.7F, 1, 1, 1, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 105, 181, 0.866F, 7.4282F, -2.7F, 1, 1, 1, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 105, 181, 0.866F, 7.4282F, 1.3F, 1, 1, 1, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 105, 181, 0.866F, 7.4282F, 5.3F, 1, 1, 1, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 105, 181, 0.866F, 7.4282F, -8.3F, 1, 1, 1, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 105, 181, 0.866F, 7.4282F, -4.3F, 1, 1, 1, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 105, 181, 0.866F, 7.4282F, -0.3F, 1, 1, 1, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 105, 181, 0.866F, 7.4282F, 3.7F, 1, 1, 1, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 105, 181, 0.866F, 7.4282F, 7.7F, 1, 1, 1, 0.0F, false));

        bone41 = new ModelRenderer(this);
        bone41.setRotationPoint(0.0F, 1.0F, -5.0F);
        bone.addChild(bone41);
        setRotationAngle(bone41, 0.0F, 0.0F, 0.5236F);
        bone41.cubeList.add(new ModelBox(bone41, 18, 86, 3.1471F, 4.451F, -6.5F, 1, 1, 1, 0.0F, false));
        bone41.cubeList.add(new ModelBox(bone41, 18, 86, 3.1471F, 4.451F, -2.5F, 1, 1, 1, 0.0F, false));
        bone41.cubeList.add(new ModelBox(bone41, 18, 86, 3.1471F, 4.451F, 1.5F, 1, 1, 1, 0.0F, false));
        bone41.cubeList.add(new ModelBox(bone41, 18, 86, 3.1471F, 4.451F, 5.5F, 1, 1, 1, 0.0F, false));
        bone41.cubeList.add(new ModelBox(bone41, 18, 86, 3.1471F, 4.451F, 9.5F, 1, 1, 1, 0.0F, false));
        bone41.cubeList.add(new ModelBox(bone41, 18, 86, 3.1471F, 4.451F, 13.5F, 1, 1, 1, 0.0F, false));

        bone42 = new ModelRenderer(this);
        bone42.setRotationPoint(0.0F, -14.0F, -5.0F);
        bone.addChild(bone42);
        setRotationAngle(bone42, 0.0F, 0.0F, -0.5236F);
        bone42.cubeList.add(new ModelBox(bone42, 18, 86, -11.2811F, 19.5394F, -6.5F, 1, 1, 1, 0.0F, false));
        bone42.cubeList.add(new ModelBox(bone42, 18, 86, -11.2811F, 19.5394F, -2.5F, 1, 1, 1, 0.0F, false));
        bone42.cubeList.add(new ModelBox(bone42, 18, 86, -11.2811F, 19.5394F, 1.5F, 1, 1, 1, 0.0F, false));
        bone42.cubeList.add(new ModelBox(bone42, 18, 86, -11.2811F, 19.5394F, 5.5F, 1, 1, 1, 0.0F, false));
        bone42.cubeList.add(new ModelBox(bone42, 18, 86, -11.2811F, 19.5394F, 9.5F, 1, 1, 1, 0.0F, false));
        bone42.cubeList.add(new ModelBox(bone42, 18, 86, -11.2811F, 19.5394F, 13.5F, 1, 1, 1, 0.0F, false));

        bullets = new ModelRenderer(this);
        bullets.setRotationPoint(2.2F, -5.4F, -14.0F);
        bone.addChild(bullets);
        bullets.cubeList.add(new ModelBox(bullets, 9, 500, -1.6188F, 9.9282F, 11.5F, 3, 1, 3, 0.0F, false));
        bullets.cubeList.add(new ModelBox(bullets, 9, 500, -1.6188F, 9.9282F, 7.5F, 3, 1, 3, 0.0F, false));
        bullets.cubeList.add(new ModelBox(bullets, 9, 500, -1.6188F, 9.9282F, 15.5F, 3, 1, 3, 0.0F, false));
        bullets.cubeList.add(new ModelBox(bullets, 9, 500, -1.6188F, 9.9282F, 19.5F, 3, 1, 3, 0.0F, false));
        bullets.cubeList.add(new ModelBox(bullets, 9, 500, -1.6188F, 9.9282F, 3.5F, 3, 1, 3, 0.0F, false));
        bullets.cubeList.add(new ModelBox(bullets, 9, 500, -1.6188F, 11.3282F, 11.5F, 3, 4, 3, 0.0F, false));
        bullets.cubeList.add(new ModelBox(bullets, 9, 500, -1.6188F, 11.3282F, 7.5F, 3, 4, 3, 0.0F, false));
        bullets.cubeList.add(new ModelBox(bullets, 9, 500, -1.6188F, 11.3282F, 15.5F, 3, 4, 3, 0.0F, false));
        bullets.cubeList.add(new ModelBox(bullets, 9, 500, -1.6188F, 11.3282F, 19.5F, 3, 4, 3, 0.0F, false));
        bullets.cubeList.add(new ModelBox(bullets, 9, 500, -1.6188F, 11.3282F, 3.5F, 3, 4, 3, 0.0F, false));
        bullets.cubeList.add(new ModelBox(bullets, 9, 500, -1.1188F, 17.5282F, 12.0F, 2, 2, 2, 0.0F, false));
        bullets.cubeList.add(new ModelBox(bullets, 9, 500, -1.1188F, 17.5282F, 8.0F, 2, 2, 2, 0.0F, false));
        bullets.cubeList.add(new ModelBox(bullets, 9, 500, -1.1188F, 17.5282F, 16.0F, 2, 2, 2, 0.0F, false));
        bullets.cubeList.add(new ModelBox(bullets, 9, 500, -1.1188F, 17.5282F, 20.0F, 2, 2, 2, 0.0F, false));
        bullets.cubeList.add(new ModelBox(bullets, 9, 500, -1.1188F, 17.5282F, 4.0F, 2, 2, 2, 0.0F, false));
        bullets.cubeList.add(new ModelBox(bullets, 9, 500, -0.8188F, 10.9282F, 12.3F, 2, 7, 2, 0.0F, false));
        bullets.cubeList.add(new ModelBox(bullets, 9, 500, -0.8188F, 10.9282F, 8.3F, 2, 7, 2, 0.0F, false));
        bullets.cubeList.add(new ModelBox(bullets, 9, 500, -0.8188F, 10.9282F, 16.3F, 2, 7, 2, 0.0F, false));
        bullets.cubeList.add(new ModelBox(bullets, 9, 500, -0.8188F, 10.9282F, 20.3F, 2, 7, 2, 0.0F, false));
        bullets.cubeList.add(new ModelBox(bullets, 9, 500, -0.8188F, 10.9282F, 4.3F, 2, 7, 2, 0.0F, false));
        bullets.cubeList.add(new ModelBox(bullets, 9, 500, -0.8188F, 10.9282F, 11.7F, 2, 7, 2, 0.0F, false));
        bullets.cubeList.add(new ModelBox(bullets, 9, 500, -0.8188F, 10.9282F, 7.7F, 2, 7, 2, 0.0F, false));
        bullets.cubeList.add(new ModelBox(bullets, 9, 500, -0.8188F, 10.9282F, 15.7F, 2, 7, 2, 0.0F, false));
        bullets.cubeList.add(new ModelBox(bullets, 9, 500, -0.8188F, 10.9282F, 19.7F, 2, 7, 2, 0.0F, false));
        bullets.cubeList.add(new ModelBox(bullets, 9, 500, -0.8188F, 10.9282F, 3.7F, 2, 7, 2, 0.0F, false));
        bullets.cubeList.add(new ModelBox(bullets, 9, 500, -1.4188F, 10.9282F, 12.3F, 2, 7, 2, 0.0F, true));
        bullets.cubeList.add(new ModelBox(bullets, 9, 500, -1.4188F, 10.9282F, 8.3F, 2, 7, 2, 0.0F, true));
        bullets.cubeList.add(new ModelBox(bullets, 9, 500, -1.4188F, 10.9282F, 16.3F, 2, 7, 2, 0.0F, true));
        bullets.cubeList.add(new ModelBox(bullets, 9, 500, -1.4188F, 10.9282F, 20.3F, 2, 7, 2, 0.0F, true));
        bullets.cubeList.add(new ModelBox(bullets, 9, 500, -1.4188F, 10.9282F, 4.3F, 2, 7, 2, 0.0F, true));
        bullets.cubeList.add(new ModelBox(bullets, 9, 500, -1.4188F, 10.9282F, 11.7F, 2, 7, 2, 0.0F, true));
        bullets.cubeList.add(new ModelBox(bullets, 9, 500, -1.4188F, 10.9282F, 7.7F, 2, 7, 2, 0.0F, true));
        bullets.cubeList.add(new ModelBox(bullets, 9, 500, -1.4188F, 10.9282F, 15.7F, 2, 7, 2, 0.0F, true));
        bullets.cubeList.add(new ModelBox(bullets, 9, 500, -1.4188F, 10.9282F, 19.7F, 2, 7, 2, 0.0F, true));
        bullets.cubeList.add(new ModelBox(bullets, 9, 500, -1.4188F, 10.9282F, 3.7F, 2, 7, 2, 0.0F, true));

        bullet = new ModelRenderer(this);
        bullet.setRotationPoint(0.0F, 32.0F, 0.0F);
        bullet.cubeList.add(new ModelBox(bullet, 8, 496, -1.6188F, -2.5F, -4.0F, 3, 3, 1, 0.0F, false));
        bullet.cubeList.add(new ModelBox(bullet, 8, 496, -1.6188F, -2.5F, -8.4F, 3, 3, 4, 0.0F, false));
        bullet.cubeList.add(new ModelBox(bullet, 8, 496, -1.1188F, -2.0F, -12.6F, 2, 2, 2, 0.0F, false));
        bullet.cubeList.add(new ModelBox(bullet, 8, 496, -0.8188F, -2.3F, -11.0F, 2, 2, 7, 0.0F, false));
        bullet.cubeList.add(new ModelBox(bullet, 8, 496, -0.8188F, -1.7F, -11.0F, 2, 2, 7, 0.0F, false));
        bullet.cubeList.add(new ModelBox(bullet, 8, 496, -1.4188F, -2.3F, -11.0F, 2, 2, 7, 0.0F, true));
        bullet.cubeList.add(new ModelBox(bullet, 8, 496, -1.4188F, -1.7F, -11.0F, 2, 2, 7, 0.0F, true));

        addEntry(AnimationElement.BOLT, stack -> bolt);
        addEntry(AnimationElement.CHARGING, stack -> boltcarrier);
        addEntry(AnimationElement.BULLET, stack -> bullet);

    }
}

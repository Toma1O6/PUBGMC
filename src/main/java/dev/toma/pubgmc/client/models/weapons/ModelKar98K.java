package dev.toma.pubgmc.client.models.weapons;

import dev.toma.pubgmc.animation.ReloadAnimation;
import dev.toma.pubgmc.animation.ReloadAnimation.ReloadStyle;
import dev.toma.pubgmc.client.util.ModelTransformationHelper;
import dev.toma.pubgmc.common.capability.player.PlayerDataProvider;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
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

    @Override
    public void initAnimations() {
        initAimAnimation(-0.56f, 0.265f, 0.245f);
        initAimingAnimationStates(0.265f, 0.193f, 0.19f);
        reloadAnimation = new ReloadAnimation(null, ReloadStyle.SINGLE);
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }

    @Override
    public void render(ItemStack stack, ItemCameraTransforms.TransformType transformType) {
        EntityPlayerSP player = Minecraft.getMinecraft().player;
        if (player != null && player.hasCapability(PlayerDataProvider.PLAYER_DATA, null)) {
            GlStateManager.pushMatrix();
            {
                renderKar98K(stack);
            }
            GlStateManager.popMatrix();
        }
    }

    private void renderKar98K(ItemStack stack) {
        GlStateManager.pushMatrix();
        ModelTransformationHelper.defaultSRTransform();
        GlStateManager.scale(0.6999999, 0.6999999, 0.6999999);
        GlStateManager.translate(0.0, -9.900002, 3.0);

        kar98k.render(1f);
        bolt.render(1.0F);
        stock.render(1.0F);
        GlStateManager.popMatrix();

        /*renderSniperSilencer(0.225, 1.725, 16.825, 1f, stack);
        renderRedDot(-0.1, 6.45, -35, 0.8f, stack);
        renderHolo(-0.1, 4, -18, 0.87f, stack);
        renderScope2X(-0.175, 4, -23, 0.9f, stack);
        renderScope4X(0, 9, -40, 0.8f, stack);
        renderScope8X(0, 10, -15, 0.7f, stack);
        renderScope15X(0.15, 17.075, -9, 0.64f, stack);*/
    }

    public ModelKar98K() {
        textureWidth = 512;
        textureHeight = 512;

        kar98k = new ModelRenderer(this);
        kar98k.setRotationPoint(0.0F, 24.0F, 0.0F);
        kar98k.cubeList.add(new ModelBox(kar98k, 142, 156, -2.5F, -7.0F, -2.0F, 5, 7, 10, 0.0F, false));
        kar98k.cubeList.add(new ModelBox(kar98k, 142, 156, -2.5F, -7.0F, 8.0F, 5, 7, 11, 0.0F, false));
        kar98k.cubeList.add(new ModelBox(kar98k, 142, 156, -2.5F, -4.4023F, 25.8984F, 5, 3, 5, 0.0F, false));
        kar98k.cubeList.add(new ModelBox(kar98k, 142, 156, -1.5F, -5.4023F, 25.8984F, 3, 1, 5, 0.0F, false));
        kar98k.cubeList.add(new ModelBox(kar98k, 142, 156, -2.5F, -6.0885F, 34.3138F, 5, 3, 2, 0.0F, false));
        kar98k.cubeList.add(new ModelBox(kar98k, 142, 156, -1.5F, -7.0885F, 34.3138F, 3, 1, 2, 0.0F, false));
        kar98k.cubeList.add(new ModelBox(kar98k, 142, 156, -2.5F, 0.349F, 28.3138F, 5, 6, 11, 0.0F, false));
        kar98k.cubeList.add(new ModelBox(kar98k, 142, 156, -2.5F, 5.349F, 39.3138F, 5, 1, 5, 0.0F, false));
        kar98k.cubeList.add(new ModelBox(kar98k, 142, 156, -2.5F, 1.9115F, 64.0559F, 5, 18, 8, 0.0F, false));
        kar98k.cubeList.add(new ModelBox(kar98k, 142, 156, -1.5F, 0.9115F, 64.0559F, 3, 1, 8, 0.0F, false));
        kar98k.cubeList.add(new ModelBox(kar98k, 142, 156, -2.5F, -7.0F, -14.0F, 5, 7, 1, 0.0F, false));
        kar98k.cubeList.add(new ModelBox(kar98k, 142, 156, -2.5F, -7.0F, -13.0F, 1, 7, 11, 0.0F, false));
        kar98k.cubeList.add(new ModelBox(kar98k, 142, 156, 1.5F, -7.0F, -13.0F, 1, 7, 11, 0.0F, true));
        kar98k.cubeList.add(new ModelBox(kar98k, 142, 156, -1.8812F, -10.7383F, -15.0F, 4, 4, 2, 0.0F, false));
        kar98k.cubeList.add(new ModelBox(kar98k, 12, 162, -1.5F, -11.7383F, -19.0F, 3, 1, 8, 0.0F, false));
        kar98k.cubeList.add(new ModelBox(kar98k, 12, 162, -2.0F, -12.7383F, -13.0F, 4, 1, 1, 0.0F, false));
        kar98k.cubeList.add(new ModelBox(kar98k, 0, 93, -1.8812F, -10.7383F, -2.0F, 4, 4, 2, 0.0F, false));
        kar98k.cubeList.add(new ModelBox(kar98k, 0, 93, -1.8812F, -10.7383F, 10.0F, 4, 4, 2, 0.0F, false));
        kar98k.cubeList.add(new ModelBox(kar98k, 0, 93, -1.5F, -11.7383F, -2.0F, 3, 1, 14, 0.0F, false));
        kar98k.cubeList.add(new ModelBox(kar98k, 0, 93, -1.793F, -11.9023F, -2.0F, 1, 1, 14, 0.0F, false));
        kar98k.cubeList.add(new ModelBox(kar98k, 0, 93, 0.793F, -11.9023F, -2.0F, 1, 1, 14, 0.0F, true));
        kar98k.cubeList.add(new ModelBox(kar98k, 0, 93, -1.8812F, -7.6602F, 14.0F, 1, 1, 5, 0.0F, false));
        kar98k.cubeList.add(new ModelBox(kar98k, 0, 93, 1.1188F, -7.6602F, 14.0F, 1, 1, 5, 0.0F, false));
        kar98k.cubeList.add(new ModelBox(kar98k, 142, 156, -2.5F, -10.0F, -25.0F, 5, 10, 11, 0.0F, false));
        kar98k.cubeList.add(new ModelBox(kar98k, 142, 156, -2.5F, -10.0F, -36.0F, 5, 4, 11, 0.0F, false));
        kar98k.cubeList.add(new ModelBox(kar98k, 142, 156, -2.5F, -10.0F, -47.0F, 5, 4, 11, 0.0F, false));
        kar98k.cubeList.add(new ModelBox(kar98k, 142, 156, -2.5F, -10.0F, -58.0F, 5, 4, 11, 0.0F, false));
        kar98k.cubeList.add(new ModelBox(kar98k, 142, 156, -2.5F, -10.0F, -69.0F, 5, 4, 11, 0.0F, false));
        kar98k.cubeList.add(new ModelBox(kar98k, 142, 156, -2.5F, -6.0F, -69.0F, 5, 2, 6, 0.0F, false));
        kar98k.cubeList.add(new ModelBox(kar98k, 142, 156, -2.0F, -9.5F, -82.0F, 1, 4, 11, 0.0F, false));
        kar98k.cubeList.add(new ModelBox(kar98k, 142, 156, 1.0F, -9.5F, -82.0F, 1, 4, 11, 0.0F, false));
        kar98k.cubeList.add(new ModelBox(kar98k, 142, 156, -2.5F, -10.0F, -71.0F, 5, 6, 2, 0.0F, false));
        kar98k.cubeList.add(new ModelBox(kar98k, 142, 156, -1.5F, -11.0F, -25.0F, 3, 1, 11, 0.0F, false));
        kar98k.cubeList.add(new ModelBox(kar98k, 142, 156, -1.5F, -11.0F, -36.0F, 3, 1, 11, 0.0F, false));
        kar98k.cubeList.add(new ModelBox(kar98k, 142, 156, -1.5F, -11.0F, -47.0F, 3, 1, 11, 0.0F, false));
        kar98k.cubeList.add(new ModelBox(kar98k, 142, 156, -1.5F, -11.0F, -58.0F, 3, 1, 11, 0.0F, false));
        kar98k.cubeList.add(new ModelBox(kar98k, 142, 156, -1.5F, -11.0F, -69.0F, 3, 1, 11, 0.0F, false));
        kar98k.cubeList.add(new ModelBox(kar98k, 142, 156, -1.5F, -4.0F, -69.0F, 3, 1, 7, 0.0F, false));
        kar98k.cubeList.add(new ModelBox(kar98k, 142, 156, -1.0F, -10.5F, -82.0F, 2, 1, 11, 0.0F, false));
        kar98k.cubeList.add(new ModelBox(kar98k, 0, 12, -1.0F, -9.6133F, -108.0F, 2, 2, 29, 0.0F, false));
        kar98k.cubeList.add(new ModelBox(kar98k, 12, 162, -1.5F, -9.6133F, -107.0F, 3, 2, 3, 0.0F, false));
        kar98k.cubeList.add(new ModelBox(kar98k, 12, 162, -1.0F, -10.1133F, -107.0F, 2, 3, 3, 0.0F, false));
        kar98k.cubeList.add(new ModelBox(kar98k, 12, 162, -0.5F, -13.1133F, -106.5F, 1, 3, 2, 0.0F, false));
        kar98k.cubeList.add(new ModelBox(kar98k, 0, 12, -1.0F, -7.4219F, -90.0F, 2, 2, 11, 0.0F, false));
        kar98k.cubeList.add(new ModelBox(kar98k, 142, 156, -1.0F, -5.5F, -82.0F, 2, 1, 11, 0.0F, false));
        kar98k.cubeList.add(new ModelBox(kar98k, 142, 156, -1.5F, -11.0F, -71.0F, 3, 1, 2, 0.0F, false));
        kar98k.cubeList.add(new ModelBox(kar98k, 142, 156, -1.5F, -4.0F, -71.0F, 3, 1, 2, 0.0F, false));
        kar98k.cubeList.add(new ModelBox(kar98k, 142, 156, -1.5F, 0.0F, -25.0F, 3, 1, 11, 0.0F, false));
        kar98k.cubeList.add(new ModelBox(kar98k, 142, 156, -1.5F, 0.0F, -14.0F, 3, 1, 11, 0.0F, false));
        kar98k.cubeList.add(new ModelBox(kar98k, 142, 156, -1.5F, 0.0F, -3.0F, 3, 1, 11, 0.0F, false));
        kar98k.cubeList.add(new ModelBox(kar98k, 142, 156, -1.5F, 0.0F, 8.0F, 3, 1, 13, 0.0F, false));
        kar98k.cubeList.add(new ModelBox(kar98k, 17, 154, -1.0F, 0.28F, -0.5F, 2, 1, 12, 0.0F, false));
        kar98k.cubeList.add(new ModelBox(kar98k, 142, 156, -1.5F, -7.0F, 19.0F, 3, 2, 4, 0.0F, false));

        bone13 = new ModelRenderer(this);
        bone13.setRotationPoint(5.3812F, 0.0F, 0.0F);
        kar98k.addChild(bone13);
        setRotationAngle(bone13, -0.3491F, 0.0F, 0.0F);
        bone13.cubeList.add(new ModelBox(bone13, 142, 156, -7.8812F, -13.0762F, 15.46F, 5, 7, 9, 0.0F, false));
        bone13.cubeList.add(new ModelBox(bone13, 142, 156, -6.8812F, -14.0762F, 15.46F, 3, 1, 9, 0.0F, false));

        bone16 = new ModelRenderer(this);
        bone16.setRotationPoint(5.3812F, 14.832F, 17.1758F);
        kar98k.addChild(bone16);
        setRotationAngle(bone16, 0.4363F, 0.0F, 0.0F);
        bone16.cubeList.add(new ModelBox(bone16, 142, 156, -7.8812F, -11.7176F, 18.3737F, 5, 7, 6, 0.0F, false));
        bone16.cubeList.add(new ModelBox(bone16, 142, 156, -6.8812F, -12.7176F, 20.3737F, 3, 1, 4, 0.0F, false));

        bone20 = new ModelRenderer(this);
        bone20.setRotationPoint(-5.3812F, -12.2176F, 22.3737F);
        bone16.addChild(bone20);
        setRotationAngle(bone20, 0.0F, 0.0F, 0.7854F);
        bone20.cubeList.add(new ModelBox(bone20, 142, 156, 0.7071F, -1.4142F, -2.0F, 1, 1, 4, 0.0F, false));
        bone20.cubeList.add(new ModelBox(bone20, 142, 156, -1.4142F, 0.7071F, -2.0F, 1, 1, 4, 0.0F, false));
        bone20.cubeList.add(new ModelBox(bone20, 142, 156, -1.4142F, 1.1213F, -2.0F, 1, 1, 4, 0.0F, false));
        bone20.cubeList.add(new ModelBox(bone20, 142, 156, 1.1213F, -1.4142F, -2.0F, 1, 1, 4, 0.0F, false));

        bone17 = new ModelRenderer(this);
        bone17.setRotationPoint(5.3812F, 14.832F, 26.1758F);
        kar98k.addChild(bone17);
        setRotationAngle(bone17, -0.2618F, 0.0F, 0.0F);
        bone17.cubeList.add(new ModelBox(bone17, 142, 156, -7.8812F, -22.8315F, 4.3779F, 5, 7, 11, 0.0F, false));
        bone17.cubeList.add(new ModelBox(bone17, 142, 156, -6.8812F, -23.8315F, 4.3779F, 3, 1, 11, 0.0F, false));
        bone17.cubeList.add(new ModelBox(bone17, 142, 156, -7.8812F, -22.8315F, 15.3779F, 5, 7, 11, 0.0F, false));
        bone17.cubeList.add(new ModelBox(bone17, 142, 156, -6.8812F, -23.8315F, 15.3779F, 3, 1, 11, 0.0F, false));
        bone17.cubeList.add(new ModelBox(bone17, 142, 156, -7.8812F, -22.8315F, 26.3779F, 5, 9, 11, 0.0F, false));
        bone17.cubeList.add(new ModelBox(bone17, 142, 156, -6.8812F, -23.8315F, 26.3779F, 3, 1, 11, 0.0F, false));

        bone19 = new ModelRenderer(this);
        bone19.setRotationPoint(0.0F, 0.0F, 0.0F);
        bone17.addChild(bone19);
        setRotationAngle(bone19, 0.0F, 0.0F, 0.7854F);
        bone19.cubeList.add(new ModelBox(bone19, 142, 156, -19.5959F, -14.107F, 4.3779F, 1, 1, 11, 0.0F, false));
        bone19.cubeList.add(new ModelBox(bone19, 142, 156, -19.5959F, -14.107F, 15.3779F, 1, 1, 11, 0.0F, false));
        bone19.cubeList.add(new ModelBox(bone19, 142, 156, -19.5959F, -14.107F, 26.3779F, 1, 1, 9, 0.0F, false));
        bone19.cubeList.add(new ModelBox(bone19, 142, 156, -21.7172F, -11.9857F, 26.3779F, 1, 1, 9, 0.0F, false));
        bone19.cubeList.add(new ModelBox(bone19, 142, 156, -21.7172F, -11.9857F, 15.3779F, 1, 1, 11, 0.0F, false));
        bone19.cubeList.add(new ModelBox(bone19, 142, 156, -21.7172F, -11.9857F, 4.3779F, 1, 1, 11, 0.0F, false));
        bone19.cubeList.add(new ModelBox(bone19, 142, 156, -19.1816F, -14.107F, 4.3779F, 1, 1, 11, 0.0F, false));
        bone19.cubeList.add(new ModelBox(bone19, 142, 156, -19.1816F, -14.107F, 15.3779F, 1, 1, 11, 0.0F, false));
        bone19.cubeList.add(new ModelBox(bone19, 142, 156, -19.1816F, -14.107F, 26.3779F, 1, 1, 9, 0.0F, false));
        bone19.cubeList.add(new ModelBox(bone19, 142, 156, -21.7172F, -11.5715F, 26.3779F, 1, 1, 9, 0.0F, false));
        bone19.cubeList.add(new ModelBox(bone19, 142, 156, -21.7172F, -11.5715F, 15.3779F, 1, 1, 11, 0.0F, false));
        bone19.cubeList.add(new ModelBox(bone19, 142, 156, -21.7172F, -11.5715F, 4.3779F, 1, 1, 11, 0.0F, false));

        bone18 = new ModelRenderer(this);
        bone18.setRotationPoint(5.3812F, 14.832F, 26.1758F);
        kar98k.addChild(bone18);
        setRotationAngle(bone18, -0.5236F, 0.0F, 0.0F);
        bone18.cubeList.add(new ModelBox(bone18, 142, 156, -7.8812F, -21.5411F, 2.3449F, 5, 7, 11, 0.0F, false));
        bone18.cubeList.add(new ModelBox(bone18, 142, 156, -7.8812F, -22.5411F, 13.3449F, 5, 8, 11, 0.0F, false));
        bone18.cubeList.add(new ModelBox(bone18, 142, 156, -7.8812F, -22.5411F, 24.3449F, 5, 8, 11, 0.0F, false));

        bone14 = new ModelRenderer(this);
        bone14.setRotationPoint(5.3812F, -7.918F, 0.0F);
        kar98k.addChild(bone14);
        setRotationAngle(bone14, -1.0472F, 0.0F, 0.0F);
        bone14.cubeList.add(new ModelBox(bone14, 142, 156, -7.8812F, -22.8692F, 18.3975F, 5, 7, 10, 0.0F, false));
        bone14.cubeList.add(new ModelBox(bone14, 142, 156, -6.8812F, -22.8692F, 28.3975F, 3, 7, 1, 0.0F, false));

        bone15 = new ModelRenderer(this);
        bone15.setRotationPoint(0.0F, 0.0F, 0.0F);
        bone14.addChild(bone15);
        setRotationAngle(bone15, 0.0F, 0.7854F, 0.0F);
        bone15.cubeList.add(new ModelBox(bone15, 142, 156, -23.1174F, -22.8692F, 17.0428F, 1, 7, 1, 0.0F, false));
        bone15.cubeList.add(new ModelBox(bone15, 142, 156, -23.5316F, -22.8692F, 17.0428F, 1, 7, 1, 0.0F, false));
        bone15.cubeList.add(new ModelBox(bone15, 142, 156, -25.6529F, -22.8692F, 14.9214F, 1, 7, 1, 0.0F, false));
        bone15.cubeList.add(new ModelBox(bone15, 142, 156, -25.6529F, -22.8692F, 14.5072F, 1, 7, 1, 0.0F, false));

        bone12 = new ModelRenderer(this);
        bone12.setRotationPoint(1.0F, -12.2383F, -15.0F);
        kar98k.addChild(bone12);
        setRotationAngle(bone12, 0.1745F, 0.0F, 0.0F);
        bone12.cubeList.add(new ModelBox(bone12, 12, 162, -0.5F, -0.2022F, -4.0261F, 1, 1, 8, 0.0F, false));
        bone12.cubeList.add(new ModelBox(bone12, 12, 162, -2.5F, -0.2022F, -4.0261F, 1, 1, 8, 0.0F, true));

        bone10 = new ModelRenderer(this);
        bone10.setRotationPoint(0.0F, -3.0F, -30.5F);
        kar98k.addChild(bone10);
        setRotationAngle(bone10, -0.1047F, 0.0F, 0.0F);
        bone10.cubeList.add(new ModelBox(bone10, 142, 156, -2.501F, -3.5913F, -5.2165F, 5, 6, 11, 0.0F, false));
        bone10.cubeList.add(new ModelBox(bone10, 142, 156, -2.501F, -2.5913F, -16.2165F, 5, 5, 11, 0.0F, false));
        bone10.cubeList.add(new ModelBox(bone10, 142, 156, -2.501F, -1.5913F, -27.2165F, 5, 4, 11, 0.0F, false));
        bone10.cubeList.add(new ModelBox(bone10, 142, 156, -2.501F, -0.5913F, -33.2165F, 5, 3, 6, 0.0F, false));
        bone10.cubeList.add(new ModelBox(bone10, 142, 156, -1.5F, 2.4087F, -33.2165F, 3, 1, 13, 0.0F, false));
        bone10.cubeList.add(new ModelBox(bone10, 142, 156, -1.5F, 2.4087F, -20.2165F, 3, 1, 13, 0.0F, false));
        bone10.cubeList.add(new ModelBox(bone10, 142, 156, -1.5F, 2.4087F, -7.2165F, 3, 1, 13, 0.0F, false));

        bone11 = new ModelRenderer(this);
        bone11.setRotationPoint(6.501F, 3.0F, 30.5F);
        bone10.addChild(bone11);
        setRotationAngle(bone11, 0.0F, 0.0F, -0.7854F);
        bone11.cubeList.add(new ModelBox(bone11, 142, 156, -3.411F, -4.2473F, -37.7165F, 1, 1, 13, 0.0F, false));
        bone11.cubeList.add(new ModelBox(bone11, 142, 156, -5.9472F, -6.7835F, -37.7165F, 1, 1, 13, 0.0F, false));
        bone11.cubeList.add(new ModelBox(bone11, 142, 156, -3.411F, -4.2473F, -50.7165F, 1, 1, 13, 0.0F, false));
        bone11.cubeList.add(new ModelBox(bone11, 142, 156, -5.9472F, -6.7835F, -50.7165F, 1, 1, 13, 0.0F, false));
        bone11.cubeList.add(new ModelBox(bone11, 142, 156, -3.411F, -4.2473F, -63.7165F, 1, 1, 13, 0.0F, false));
        bone11.cubeList.add(new ModelBox(bone11, 142, 156, -5.9472F, -6.7835F, -63.7165F, 1, 1, 13, 0.0F, false));
        bone11.cubeList.add(new ModelBox(bone11, 142, 156, -3.8252F, -4.2473F, -37.7165F, 1, 1, 13, 0.0F, false));
        bone11.cubeList.add(new ModelBox(bone11, 142, 156, -5.9465F, -6.3686F, -37.7165F, 1, 1, 13, 0.0F, false));
        bone11.cubeList.add(new ModelBox(bone11, 142, 156, -3.8252F, -4.2473F, -50.7165F, 1, 1, 13, 0.0F, false));
        bone11.cubeList.add(new ModelBox(bone11, 142, 156, -5.9465F, -6.3686F, -50.7165F, 1, 1, 13, 0.0F, false));
        bone11.cubeList.add(new ModelBox(bone11, 142, 156, -3.8252F, -4.2473F, -63.7165F, 1, 1, 13, 0.0F, false));
        bone11.cubeList.add(new ModelBox(bone11, 142, 156, -5.9465F, -6.3686F, -63.7165F, 1, 1, 13, 0.0F, false));

        bone9 = new ModelRenderer(this);
        bone9.setRotationPoint(5.3812F, 0.0F, 0.0F);
        kar98k.addChild(bone9);
        setRotationAngle(bone9, 0.0F, 0.0F, 0.7854F);
        bone9.cubeList.add(new ModelBox(bone9, 142, 156, -12.6439F, -2.9124F, -25.0F, 1, 1, 11, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 142, 156, -12.6439F, -2.9124F, -36.0F, 1, 1, 11, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 142, 156, -12.6439F, -2.9124F, -47.0F, 1, 1, 11, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 142, 156, -12.6439F, -2.9124F, -58.0F, 1, 1, 11, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 142, 156, -12.6439F, -2.9124F, -69.0F, 1, 1, 11, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 142, 156, -12.6439F, -2.9124F, -71.0F, 1, 1, 2, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 142, 156, -7.9871F, 1.7444F, -71.0F, 1, 1, 2, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 142, 156, -10.5226F, -5.0338F, -36.0F, 1, 1, 11, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 142, 156, -10.5226F, -5.0338F, -47.0F, 1, 1, 11, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 142, 156, -10.5226F, -5.0338F, -58.0F, 1, 1, 11, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 142, 156, -10.5226F, -5.0338F, -69.0F, 1, 1, 11, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 142, 156, -5.8657F, -0.7911F, -69.0F, 1, 1, 7, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 142, 156, -10.5226F, -5.0338F, -71.0F, 1, 1, 2, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 142, 156, -5.8657F, -0.3769F, -71.0F, 1, 1, 2, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 142, 156, -10.1084F, -5.0338F, -36.0F, 1, 1, 11, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 142, 156, -10.1084F, -5.0338F, -47.0F, 1, 1, 11, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 142, 156, -10.1084F, -5.0338F, -58.0F, 1, 1, 11, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 142, 156, -10.1084F, -5.0338F, -69.0F, 1, 1, 11, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 142, 156, -5.8657F, -0.3769F, -69.0F, 1, 1, 7, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 142, 156, -7.9871F, 1.7444F, -69.0F, 1, 1, 7, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 142, 156, -8.4013F, 1.7444F, -69.0F, 1, 1, 7, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 142, 156, -10.1084F, -4.3267F, -82.0F, 1, 1, 11, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 142, 156, -11.9368F, -2.4982F, -82.0F, 1, 1, 11, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 142, 156, -11.9368F, -2.9124F, -82.0F, 1, 1, 11, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 142, 156, -10.5226F, -4.3267F, -82.0F, 1, 1, 11, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 142, 156, -7.28F, -1.084F, -82.0F, 1, 1, 11, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 142, 156, -8.6942F, 0.3302F, -82.0F, 1, 1, 11, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 142, 156, -7.28F, -1.4982F, -82.0F, 1, 1, 11, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 142, 156, -9.1084F, 0.3302F, -82.0F, 1, 1, 11, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 142, 156, -10.1084F, -5.0338F, -71.0F, 1, 1, 2, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 142, 156, -5.8657F, -0.7911F, -71.0F, 1, 1, 2, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 142, 156, -10.1084F, -5.0338F, -25.0F, 1, 1, 11, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 142, 156, -3.0373F, 2.0373F, -25.0F, 1, 1, 11, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 142, 156, -3.0373F, 2.0373F, -14.0F, 1, 1, 11, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 142, 156, -3.0373F, 2.0373F, -3.0F, 1, 1, 11, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 142, 156, -2.0999F, 3.389F, 62.0559F, 1, 1, 10, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 142, 156, -7.7567F, -2.2679F, 34.3138F, 1, 1, 2, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 142, 156, -6.5645F, -1.0756F, 24.8984F, 1, 1, 6, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 142, 156, -8.6858F, 1.0457F, 24.8984F, 1, 1, 6, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 142, 156, -9.878F, -0.1466F, 34.3138F, 1, 1, 2, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 142, 156, -9.878F, 0.2677F, 34.3138F, 1, 1, 2, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 142, 156, -4.2212F, 5.5103F, 62.0559F, 1, 1, 10, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 142, 156, -1.6856F, 3.389F, 65.0559F, 1, 1, 7, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 142, 156, -7.3425F, -2.2679F, 34.3138F, 1, 1, 2, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 142, 156, -6.1502F, -1.0756F, 24.8984F, 1, 1, 6, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 142, 156, -8.6858F, 1.4599F, 24.8984F, 1, 1, 6, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 142, 156, -4.2212F, 5.9245F, 65.0559F, 1, 1, 7, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 142, 156, -3.0373F, 2.0373F, 8.0F, 1, 1, 11, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 142, 156, -3.0373F, 2.4515F, -25.0F, 1, 1, 11, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 142, 156, -3.0373F, 2.4515F, -14.0F, 1, 1, 11, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 142, 156, -3.0373F, 2.4515F, -3.0F, 1, 1, 11, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 142, 156, -3.0373F, 2.4515F, 8.0F, 1, 1, 13, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 142, 156, -5.1586F, 4.5728F, -25.0F, 1, 1, 11, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 142, 156, -5.1586F, 4.5728F, -14.0F, 1, 1, 11, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 142, 156, -5.1586F, 4.5728F, -3.0F, 1, 1, 11, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 142, 156, -5.1586F, 4.5728F, 8.0F, 1, 1, 13, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 142, 156, -5.5728F, 4.5728F, -25.0F, 1, 1, 11, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 142, 156, -5.5728F, 4.5728F, -14.0F, 1, 1, 11, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 142, 156, -5.5728F, 4.5728F, -3.0F, 1, 1, 11, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 142, 156, -5.5728F, 4.5728F, 8.0F, 1, 1, 11, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 142, 156, -10.5226F, -5.0338F, -25.0F, 1, 1, 11, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 142, 156, -12.6439F, -2.4982F, -25.0F, 1, 1, 11, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 142, 156, -12.6439F, -2.4982F, -36.0F, 1, 1, 11, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 142, 156, -12.6439F, -2.4982F, -47.0F, 1, 1, 11, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 142, 156, -12.6439F, -2.4982F, -58.0F, 1, 1, 11, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 142, 156, -12.6439F, -2.4982F, -69.0F, 1, 1, 11, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 142, 156, -12.6439F, -2.4982F, -71.0F, 1, 1, 2, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 142, 156, -8.4013F, 1.7444F, -71.0F, 1, 1, 2, 0.0F, false));

        bone7 = new ModelRenderer(this);
        bone7.setRotationPoint(5.9883F, 0.0F, 0.0F);
        kar98k.addChild(bone7);
        setRotationAngle(bone7, 0.0F, 0.2618F, 0.0F);
        bone7.cubeList.add(new ModelBox(bone7, 142, 156, -13.1166F, -7.0F, 16.1557F, 1, 2, 4, 0.0F, false));

        bone8 = new ModelRenderer(this);
        bone8.setRotationPoint(-6.0F, 0.0F, 0.0F);
        kar98k.addChild(bone8);
        setRotationAngle(bone8, 0.0F, -0.2618F, 0.0F);
        bone8.cubeList.add(new ModelBox(bone8, 142, 156, 12.1279F, -7.0F, 16.1526F, 1, 2, 4, 0.0F, false));

        bolt = new ModelRenderer(this);
        bolt.setRotationPoint(0.0F, 15.3F, 21.0F);
        bolt.cubeList.add(new ModelBox(bolt, 84, 17, -0.8812F, -1.3F, -28.0F, 2, 3, 14, 0.0F, false));
        bolt.cubeList.add(new ModelBox(bolt, 84, 17, -0.8812F, -0.3F, -14.0F, 2, 2, 14, 0.0F, false));
        bolt.cubeList.add(new ModelBox(bolt, 84, 17, -0.1741F, -1.0071F, -28.0F, 2, 2, 14, 0.0F, false));
        bolt.cubeList.add(new ModelBox(bolt, 84, 17, -0.1741F, -1.0071F, -42.0F, 2, 2, 14, 0.0F, false));
        bolt.cubeList.add(new ModelBox(bolt, 84, 17, -0.1741F, -1.0071F, -14.0F, 2, 2, 14, 0.0F, false));
        bolt.cubeList.add(new ModelBox(bolt, 84, 17, -1.5883F, -1.0071F, -28.0F, 2, 2, 14, 0.0F, false));
        bolt.cubeList.add(new ModelBox(bolt, 84, 17, -1.5883F, -1.0071F, -42.0F, 2, 2, 14, 0.0F, false));
        bolt.cubeList.add(new ModelBox(bolt, 84, 17, -1.5883F, -1.0071F, -14.0F, 2, 2, 14, 0.0F, false));
        bolt.cubeList.add(new ModelBox(bolt, 84, 17, -0.8812F, -1.7142F, -28.0F, 2, 2, 14, 0.0F, false));
        bolt.cubeList.add(new ModelBox(bolt, 84, 17, -0.8812F, -1.7142F, -42.0F, 2, 2, 14, 0.0F, false));
        bolt.cubeList.add(new ModelBox(bolt, 84, 17, -0.8812F, -0.3F, -42.0F, 2, 2, 14, 0.0F, false));
        bolt.cubeList.add(new ModelBox(bolt, 84, 17, -0.8812F, -1.7142F, -14.0F, 2, 2, 14, 0.0F, false));

        bone = new ModelRenderer(this);
        bone.setRotationPoint(2.0F, 9.7F, -22.0F);
        bolt.addChild(bone);
        setRotationAngle(bone, 0.0F, 0.0F, -0.3491F);
        bone.cubeList.add(new ModelBox(bone, 84, 17, -3.1229F, -11.2211F, 13.0F, 4, 2, 2, 0.0F, false));

        bone3 = new ModelRenderer(this);
        bone3.setRotationPoint(0.0F, 0.0F, 0.0F);
        bone.addChild(bone3);
        setRotationAngle(bone3, 0.0F, 0.0F, -0.8727F);
        bone3.cubeList.add(new ModelBox(bone3, 84, 17, 3.5885F, -9.6051F, 13.0F, 3, 2, 2, 0.0F, false));
        bone3.cubeList.add(new ModelBox(bone3, 84, 17, 1.1743F, -9.6051F, 13.0F, 1, 2, 2, 0.0F, false));
        bone3.cubeList.add(new ModelBox(bone3, 84, 17, 1.8814F, -9.6051F, 14.7071F, 2, 2, 1, 0.0F, false));
        bone3.cubeList.add(new ModelBox(bone3, 84, 17, 1.8814F, -9.6051F, 12.2929F, 2, 2, 1, 0.0F, false));
        bone3.cubeList.add(new ModelBox(bone3, 84, 17, 1.8814F, -10.3122F, 13.0F, 2, 1, 2, 0.0F, false));
        bone3.cubeList.add(new ModelBox(bone3, 84, 17, 1.8814F, -7.898F, 13.0F, 2, 1, 2, 0.0F, false));

        bone6 = new ModelRenderer(this);
        bone6.setRotationPoint(0.0F, 0.0F, 0.0F);
        bone3.addChild(bone6);
        setRotationAngle(bone6, 0.0F, -0.7854F, 0.0F);
        bone6.cubeList.add(new ModelBox(bone6, 84, 17, 11.4369F, -9.6051F, 8.7763F, 1, 2, 1, 0.0F, false));
        bone6.cubeList.add(new ModelBox(bone6, 84, 17, 12.8511F, -9.6051F, 7.3621F, 1, 2, 1, 0.0F, false));
        bone6.cubeList.add(new ModelBox(bone6, 84, 17, 10.0227F, -9.6051F, 7.3621F, 1, 2, 1, 0.0F, false));
        bone6.cubeList.add(new ModelBox(bone6, 84, 17, 11.4369F, -9.6051F, 5.9479F, 1, 2, 1, 0.0F, false));

        bone5 = new ModelRenderer(this);
        bone5.setRotationPoint(0.0F, 0.0F, 0.0F);
        bone3.addChild(bone5);
        setRotationAngle(bone5, 0.7854F, 0.0F, 0.0F);
        bone5.cubeList.add(new ModelBox(bone5, 84, 17, 1.8814F, 4.729F, 15.4842F, 2, 1, 1, 0.0F, false));
        bone5.cubeList.add(new ModelBox(bone5, 84, 17, 1.8814F, 3.3148F, 14.07F, 2, 1, 1, 0.0F, false));
        bone5.cubeList.add(new ModelBox(bone5, 84, 17, 1.8814F, 3.3148F, 16.8984F, 2, 1, 1, 0.0F, false));
        bone5.cubeList.add(new ModelBox(bone5, 84, 17, 1.8814F, 1.9006F, 15.4842F, 2, 1, 1, 0.0F, false));

        bone4 = new ModelRenderer(this);
        bone4.setRotationPoint(0.0F, 0.0F, 0.0F);
        bone3.addChild(bone4);
        setRotationAngle(bone4, 0.0F, 0.0F, -0.7854F);
        bone4.cubeList.add(new ModelBox(bone4, 84, 17, 9.0364F, -4.5473F, 13.0F, 1, 1, 2, 0.0F, false));
        bone4.cubeList.add(new ModelBox(bone4, 84, 17, 7.6221F, -5.9615F, 13.0F, 1, 1, 2, 0.0F, false));
        bone4.cubeList.add(new ModelBox(bone4, 84, 17, 6.2079F, -4.5473F, 13.0F, 1, 1, 2, 0.0F, false));
        bone4.cubeList.add(new ModelBox(bone4, 84, 17, 7.6221F, -3.1331F, 13.0F, 1, 1, 2, 0.0F, false));

        bone2 = new ModelRenderer(this);
        bone2.setRotationPoint(-2.0F, 9.7F, -22.0F);
        bolt.addChild(bone2);
        setRotationAngle(bone2, 0.0F, 0.0F, -0.7854F);
        bone2.cubeList.add(new ModelBox(bone2, 84, 17, 7.8622F, -5.4515F, -6.0F, 1, 2, 14, 0.0F, false));
        bone2.cubeList.add(new ModelBox(bone2, 84, 17, 7.8622F, -5.4515F, -20.0F, 1, 2, 14, 0.0F, false));
        bone2.cubeList.add(new ModelBox(bone2, 84, 17, 7.8622F, -5.4515F, 8.0F, 1, 2, 14, 0.0F, false));
        bone2.cubeList.add(new ModelBox(bone2, 84, 17, 6.448F, -5.8657F, -6.0F, 2, 1, 14, 0.0F, false));
        bone2.cubeList.add(new ModelBox(bone2, 84, 17, 6.448F, -5.8657F, -20.0F, 2, 1, 14, 0.0F, false));
        bone2.cubeList.add(new ModelBox(bone2, 84, 17, 6.448F, -5.8657F, 8.0F, 2, 1, 14, 0.0F, false));
        bone2.cubeList.add(new ModelBox(bone2, 84, 17, 8.2764F, -5.8657F, -6.0F, 2, 1, 14, 0.0F, false));
        bone2.cubeList.add(new ModelBox(bone2, 84, 17, 8.2764F, -5.8657F, -20.0F, 2, 1, 14, 0.0F, false));
        bone2.cubeList.add(new ModelBox(bone2, 84, 17, 8.2764F, -5.8657F, 8.0F, 2, 1, 14, 0.0F, false));
        bone2.cubeList.add(new ModelBox(bone2, 84, 17, 7.8622F, -7.28F, -6.0F, 1, 2, 14, 0.0F, false));
        bone2.cubeList.add(new ModelBox(bone2, 84, 17, 7.8622F, -7.28F, -20.0F, 1, 2, 14, 0.0F, false));
        bone2.cubeList.add(new ModelBox(bone2, 84, 17, 7.8622F, -7.28F, 8.0F, 1, 2, 14, 0.0F, false));

        stock = new ModelRenderer(this);
        stock.setRotationPoint(0.0F, 24.0F, -2.0F);


        bone21 = new ModelRenderer(this);
        bone21.setRotationPoint(-0.5F, -2.5F, 53.5F);
        stock.addChild(bone21);
        setRotationAngle(bone21, -0.2618F, -0.1745F, 0.0698F);
        bone21.cubeList.add(new ModelBox(bone21, 212, 153, -0.8765F, -0.7186F, 5.4807F, 5, 1, 2, 0.0F, false));

        bone22 = new ModelRenderer(this);
        bone22.setRotationPoint(-0.5F, -2.75F, 52.66F);
        stock.addChild(bone22);
        setRotationAngle(bone22, -0.3142F, -0.1047F, 0.0F);
        bone22.cubeList.add(new ModelBox(bone22, 212, 153, -1.4366F, -1.0342F, 4.9628F, 5, 1, 2, 0.0F, false));

        bone23 = new ModelRenderer(this);
        bone23.setRotationPoint(-4.0066F, -2.2342F, 59.8428F);
        stock.addChild(bone23);
        setRotationAngle(bone23, -0.2705F, 0.1134F, 0.6545F);
        bone23.cubeList.add(new ModelBox(bone23, 212, 153, 1.5F, -0.5F, -1.0F, 1, 1, 2, 0.0F, false));

        bone24 = new ModelRenderer(this);
        bone24.setRotationPoint(-4.1366F, -2.4342F, 58.1528F);
        stock.addChild(bone24);
        setRotationAngle(bone24, -0.2705F, 0.0262F, 0.5672F);
        bone24.cubeList.add(new ModelBox(bone24, 212, 153, 1.5F, -0.5F, -1.0F, 1, 1, 2, 0.0F, false));

        bone25 = new ModelRenderer(this);
        bone25.setRotationPoint(1.0434F, -2.7542F, 59.0128F);
        stock.addChild(bone25);
        setRotationAngle(bone25, -0.2705F, 0.2705F, 0.829F);
        bone25.cubeList.add(new ModelBox(bone25, 212, 153, 1.5F, -0.5F, -1.0F, 1, 1, 4, 0.0F, false));

        bone26 = new ModelRenderer(this);
        bone26.setRotationPoint(-1.43F, -2.2F, 56.46F);
        stock.addChild(bone26);
        setRotationAngle(bone26, -0.576F, 0.0698F, 0.0F);
        bone26.cubeList.add(new ModelBox(bone26, 212, 153, 3.12F, -0.9052F, 3.4191F, 1, 5, 2, 0.0F, false));

        bone27 = new ModelRenderer(this);
        bone27.setRotationPoint(-1.02F, -2.22F, 54.54F);
        stock.addChild(bone27);
        setRotationAngle(bone27, -0.4451F, -0.0611F, 0.0F);
        bone27.cubeList.add(new ModelBox(bone27, 212, 153, 3.2313F, -0.6824F, 2.7232F, 1, 5, 2, 0.0F, false));

        bone28 = new ModelRenderer(this);
        bone28.setRotationPoint(-0.96F, 2.29F, 53.7F);
        stock.addChild(bone28);
        setRotationAngle(bone28, -0.4538F, -0.0611F, 0.0524F);
        bone28.cubeList.add(new ModelBox(bone28, 212, 153, 3.2313F, -0.6824F, 2.7232F, 1, 5, 2, 0.0F, false));

        bone29 = new ModelRenderer(this);
        bone29.setRotationPoint(2.7713F, 4.9076F, 55.3132F);
        stock.addChild(bone29);
        setRotationAngle(bone29, -0.5847F, -0.0611F, -0.0349F);
        bone29.cubeList.add(new ModelBox(bone29, 212, 153, -0.5F, -2.5F, -1.0F, 1, 5, 2, 0.0F, false));

        bone30 = new ModelRenderer(this);
        bone30.setRotationPoint(2.7713F, 7.5776F, 53.7032F);
        stock.addChild(bone30);
        setRotationAngle(bone30, -0.5847F, 0.096F, -0.0349F);
        bone30.cubeList.add(new ModelBox(bone30, 212, 153, -0.7811F, -1.3098F, -0.7854F, 1, 6, 3, 0.0F, false));

        bone31 = new ModelRenderer(this);
        bone31.setRotationPoint(2.9934F, 11.7658F, 52.8428F);
        stock.addChild(bone31);
        setRotationAngle(bone31, -0.2705F, 0.5498F, 1.3526F);
        bone31.cubeList.add(new ModelBox(bone31, 212, 153, -0.2272F, 0.5561F, -2.2623F, 1, 2, 2, 0.0F, false));

        bone32 = new ModelRenderer(this);
        bone32.setRotationPoint(2.6362F, 12.0719F, 52.1305F);
        stock.addChild(bone32);
        setRotationAngle(bone32, -0.0524F, 0.5498F, 1.3526F);
        bone32.cubeList.add(new ModelBox(bone32, 212, 153, -0.5F, -0.5F, -1.0F, 1, 2, 2, 0.0F, false));

        bone33 = new ModelRenderer(this);
        bone33.setRotationPoint(2.8413F, 16.0276F, 55.3132F);
        stock.addChild(bone33);
        setRotationAngle(bone33, -0.5847F, -0.0611F, 0.096F);
        bone33.cubeList.add(new ModelBox(bone33, 212, 153, -6.3299F, -1.8943F, -5.5793F, 5, 1, 2, 0.0F, false));

        bone34 = new ModelRenderer(this);
        bone34.setRotationPoint(-0.9886F, 11.3433F, 51.1138F);
        stock.addChild(bone34);
        setRotationAngle(bone34, -0.672F, 0.0262F, 0.096F);
        bone34.cubeList.add(new ModelBox(bone34, 212, 153, -2.5F, -0.5F, -1.0F, 5, 1, 2, 0.0F, false));

        bone35 = new ModelRenderer(this);
        bone35.setRotationPoint(-0.9886F, 10.7833F, 51.1138F);
        stock.addChild(bone35);
        setRotationAngle(bone35, -0.4538F, -0.0611F, 0.096F);
        bone35.cubeList.add(new ModelBox(bone35, 212, 153, -2.3129F, -2.5431F, 0.9504F, 1, 3, 2, 0.0F, false));

        bone36 = new ModelRenderer(this);
        bone36.setRotationPoint(-0.9886F, 10.7833F, 51.1138F);
        stock.addChild(bone36);
        setRotationAngle(bone36, -0.5847F, 0.0262F, 0.0087F);
        bone36.cubeList.add(new ModelBox(bone36, 212, 153, -2.5F, -2.5F, -1.0F, 1, 3, 2, 0.0F, false));

        bone37 = new ModelRenderer(this);
        bone37.setRotationPoint(-0.7386F, 8.9533F, 54.0038F);
        stock.addChild(bone37);
        setRotationAngle(bone37, -0.5847F, 0.0262F, 0.0087F);
        bone37.cubeList.add(new ModelBox(bone37, 212, 153, -2.5F, -5.5F, -1.0F, 1, 6, 2, 0.0F, false));

        bone38 = new ModelRenderer(this);
        bone38.setRotationPoint(-2.8486F, 6.4533F, 54.0038F);
        stock.addChild(bone38);
        setRotationAngle(bone38, -0.5847F, -0.1047F, 0.0087F);
        bone38.cubeList.add(new ModelBox(bone38, 212, 153, -0.5F, -3.0F, -1.0F, 1, 6, 2, 0.0F, false));

        bone39 = new ModelRenderer(this);
        bone39.setRotationPoint(-2.8486F, 5.6233F, 53.0238F);
        stock.addChild(bone39);
        setRotationAngle(bone39, -0.4538F, -0.1047F, 0.0087F);
        bone39.cubeList.add(new ModelBox(bone39, 212, 153, 0.2619F, -8.8203F, 1.9051F, 1, 6, 2, 0.0F, false));

        bone40 = new ModelRenderer(this);
        bone40.setRotationPoint(-2.7068F, 1.383F, 57.0389F);
        stock.addChild(bone40);
        setRotationAngle(bone40, -0.4538F, -0.3665F, 0.1833F);
        bone40.cubeList.add(new ModelBox(bone40, 212, 153, -0.3729F, -2.7295F, -0.7208F, 1, 6, 1, 0.0F, false));
        this.initAnimations();
    }
}

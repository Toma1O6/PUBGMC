package dev.toma.pubgmc.client.models.weapons;

import dev.toma.pubgmc.client.animation.AnimationElement;
import dev.toma.pubgmc.client.util.ModelTransformationHelper;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.item.ItemStack;

public class ModelG36C extends ModelGun {

    private final ModelRenderer g36;
    private final ModelRenderer bone49;
    private final ModelRenderer bone50;
    private final ModelRenderer bone100;
    private final ModelRenderer bone98;
    private final ModelRenderer bone99;
    private final ModelRenderer bone97;
    private final ModelRenderer bone96;
    private final ModelRenderer bone95;
    private final ModelRenderer bone82;
    private final ModelRenderer bone83;
    private final ModelRenderer bone79;
    private final ModelRenderer bone80;
    private final ModelRenderer bone78;
    private final ModelRenderer bone67;
    private final ModelRenderer bone68;
    private final ModelRenderer bone69;
    private final ModelRenderer bone70;
    private final ModelRenderer bone71;
    private final ModelRenderer bone72;
    private final ModelRenderer bone73;
    private final ModelRenderer bone74;
    private final ModelRenderer bone75;
    private final ModelRenderer bone76;
    private final ModelRenderer bone77;
    private final ModelRenderer bone81;
    private final ModelRenderer bone53;
    private final ModelRenderer bone54;
    private final ModelRenderer bone25;
    private final ModelRenderer bone26;
    private final ModelRenderer bone48;
    private final ModelRenderer bone93;
    private final ModelRenderer bone94;
    private final ModelRenderer bone92;
    private final ModelRenderer bone3;
    private final ModelRenderer bone4;
    private final ModelRenderer bone55;
    private final ModelRenderer bone51;
    private final ModelRenderer bone52;
    private final ModelRenderer bone41;
    private final ModelRenderer bone56;
    private final ModelRenderer bone2;
    private final ModelRenderer bone6;
    private final ModelRenderer bone5;
    private final ModelRenderer bone57;
    private final ModelRenderer bone58;
    private final ModelRenderer bone59;
    private final ModelRenderer bone60;
    private final ModelRenderer bone61;
    private final ModelRenderer bone62;
    private final ModelRenderer bone63;
    private final ModelRenderer bone64;
    private final ModelRenderer bone65;
    private final ModelRenderer bone66;
    private final ModelRenderer bone9;
    private final ModelRenderer bone12;
    private final ModelRenderer bone13;
    private final ModelRenderer bone14;
    private final ModelRenderer bone15;
    private final ModelRenderer bone16;
    private final ModelRenderer bone17;
    private final ModelRenderer bone20;
    private final ModelRenderer bone21;
    private final ModelRenderer bone22;
    private final ModelRenderer bone23;
    private final ModelRenderer bone24;
    private final ModelRenderer bone18;
    private final ModelRenderer bone19;
    private final ModelRenderer bone10;
    private final ModelRenderer bone11;
    private final ModelRenderer bone7;
    private final ModelRenderer bone84;
    private final ModelRenderer bone85;
    private final ModelRenderer bone86;
    private final ModelRenderer bone87;
    private final ModelRenderer bone88;
    private final ModelRenderer bone89;
    private final ModelRenderer bone90;
    private final ModelRenderer bone91;
    private final ModelRenderer bone8;
    private final ModelRenderer magazine;
    private final ModelRenderer bone103;
    private final ModelRenderer bone101;
    private final ModelRenderer bone102;
    private final ModelRenderer charging_handle;
    private final ModelRenderer bone46;
    private final ModelRenderer bone45;
    private final ModelRenderer bone43;
    private final ModelRenderer bone29;
    private final ModelRenderer bone47;
    private final ModelRenderer bone42;
    private final ModelRenderer bone44;
    private final ModelRenderer bone36;
    private final ModelRenderer bone35;
    private final ModelRenderer bone34;
    private final ModelRenderer bone31;
    private final ModelRenderer bone37;
    private final ModelRenderer bone39;
    private final ModelRenderer bone40;
    private final ModelRenderer bone104;
    private final ModelRenderer bone32;
    private final ModelRenderer bone30;
    private final ModelRenderer bone28;
    private final ModelRenderer bone38;
    private final ModelRenderer bone;
    private final ModelRenderer bone33;
    private final ModelRenderer bone27;

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }

    @Override
    public void renderModel(ItemStack stack) {
        g36.render(1f);
    }

    private void renderG36C(boolean aim, ItemStack stack) {
        GlStateManager.pushMatrix();
        {
            ModelTransformationHelper.defaultARTransform();
            GlStateManager.scale(0.5, 0.5, 0.5);
            GlStateManager.translate(-1.975, 37.125, -20.0);
        }
        GlStateManager.popMatrix();
    }

    public ModelG36C() {
        textureWidth = 512;
        textureHeight = 512;

        g36 = new ModelRenderer(this);
        g36.setRotationPoint(0.0F, 24.0F, 0.0F);
        g36.cubeList.add(new ModelBox(g36, 64, 24, -4.0F, -62.0F, 2.0F, 8, 5, 17, 0.0F, false));
        g36.cubeList.add(new ModelBox(g36, 82, 81, -3.0F, -64.0F, 16.0F, 6, 2, 10, 0.0F, false));
        g36.cubeList.add(new ModelBox(g36, 82, 81, -3.0F, -64.0F, 6.0F, 6, 2, 10, 0.0F, false));
        g36.cubeList.add(new ModelBox(g36, 82, 81, -3.0F, -64.0F, -4.0F, 6, 2, 10, 0.0F, false));
        g36.cubeList.add(new ModelBox(g36, 82, 81, -3.0F, -64.0F, -22.0F, 6, 2, 8, 0.0F, false));
        g36.cubeList.add(new ModelBox(g36, 87, 89, -3.0F, -40.6523F, -53.0F, 6, 2, 10, 0.0F, false));
        g36.cubeList.add(new ModelBox(g36, 87, 89, -3.0F, -40.6523F, -43.0F, 6, 2, 10, 0.0F, false));
        g36.cubeList.add(new ModelBox(g36, 87, 89, -3.0F, -40.6523F, -33.0F, 6, 2, 10, 0.0F, false));
        g36.cubeList.add(new ModelBox(g36, 82, 81, -3.0F, -64.0F, -14.0F, 6, 2, 10, 0.0F, false));
        g36.cubeList.add(new ModelBox(g36, 82, 81, -3.0F, -64.0F, -26.0F, 6, 2, 4, 0.0F, false));
        g36.cubeList.add(new ModelBox(g36, 81, 81, -2.0F, -66.0F, 22.0F, 4, 2, 2, 0.0F, false));
        g36.cubeList.add(new ModelBox(g36, 81, 81, -2.0F, -66.0F, 18.0F, 4, 2, 2, 0.0F, false));
        g36.cubeList.add(new ModelBox(g36, 81, 81, -2.0F, -66.0F, 14.0F, 4, 2, 2, 0.0F, false));
        g36.cubeList.add(new ModelBox(g36, 81, 81, -2.0F, -66.0F, 10.0F, 4, 2, 2, 0.0F, false));
        g36.cubeList.add(new ModelBox(g36, 81, 81, -2.0F, -66.0F, 6.0F, 4, 2, 2, 0.0F, false));
        g36.cubeList.add(new ModelBox(g36, 81, 81, -2.0F, -66.0F, 2.0F, 4, 2, 2, 0.0F, false));
        g36.cubeList.add(new ModelBox(g36, 81, 81, -2.0F, -66.0F, -2.0F, 4, 2, 2, 0.0F, false));
        g36.cubeList.add(new ModelBox(g36, 81, 81, -2.0F, -66.0F, -6.0F, 4, 2, 2, 0.0F, false));
        g36.cubeList.add(new ModelBox(g36, 81, 81, -2.0F, -66.0F, -10.0F, 4, 2, 2, 0.0F, false));
        g36.cubeList.add(new ModelBox(g36, 81, 81, -2.0F, -66.0F, -14.0F, 4, 2, 2, 0.0F, false));
        g36.cubeList.add(new ModelBox(g36, 81, 81, -2.0F, -66.0F, -18.0F, 4, 2, 2, 0.0F, false));
        g36.cubeList.add(new ModelBox(g36, 81, 81, -2.0F, -66.0F, -22.0F, 4, 2, 2, 0.0F, false));
        g36.cubeList.add(new ModelBox(g36, 81, 81, -2.0F, -66.0F, -26.0F, 4, 2, 2, 0.0F, false));
        g36.cubeList.add(new ModelBox(g36, 87, 89, -2.0F, -39.0F, -53.0F, 4, 2, 2, 0.0F, false));
        g36.cubeList.add(new ModelBox(g36, 87, 89, -2.0F, -39.0F, -45.0F, 4, 2, 2, 0.0F, false));
        g36.cubeList.add(new ModelBox(g36, 87, 89, -2.0F, -39.0F, -37.0F, 4, 2, 2, 0.0F, false));
        g36.cubeList.add(new ModelBox(g36, 87, 89, -2.0F, -39.0F, -29.0F, 4, 2, 2, 0.0F, false));
        g36.cubeList.add(new ModelBox(g36, 87, 89, -2.0F, -39.0F, -49.0F, 4, 2, 2, 0.0F, false));
        g36.cubeList.add(new ModelBox(g36, 87, 89, -2.0F, -39.0F, -41.0F, 4, 2, 2, 0.0F, false));
        g36.cubeList.add(new ModelBox(g36, 87, 89, -2.0F, -39.0F, -33.0F, 4, 2, 2, 0.0F, false));
        g36.cubeList.add(new ModelBox(g36, 87, 89, -2.0F, -39.0F, -25.0F, 4, 2, 2, 0.0F, false));
        g36.cubeList.add(new ModelBox(g36, 81, 81, -0.2688F, -65.0005F, 20.0F, 4, 0, 2, 0.0F, false));
        g36.cubeList.add(new ModelBox(g36, 81, 81, -0.2688F, -65.0005F, 16.0F, 4, 0, 2, 0.0F, false));
        g36.cubeList.add(new ModelBox(g36, 81, 81, -0.2688F, -65.0005F, 12.0F, 4, 0, 2, 0.0F, false));
        g36.cubeList.add(new ModelBox(g36, 81, 81, -0.2688F, -65.0005F, 8.0F, 4, 0, 2, 0.0F, false));
        g36.cubeList.add(new ModelBox(g36, 81, 81, -0.2688F, -65.0005F, 4.0F, 4, 0, 2, 0.0F, false));
        g36.cubeList.add(new ModelBox(g36, 81, 81, -0.2688F, -65.0005F, 0.0F, 4, 0, 2, 0.0F, false));
        g36.cubeList.add(new ModelBox(g36, 81, 81, -0.2688F, -65.0005F, -4.0F, 4, 0, 2, 0.0F, false));
        g36.cubeList.add(new ModelBox(g36, 81, 81, -0.2688F, -65.0005F, -8.0F, 4, 0, 2, 0.0F, false));
        g36.cubeList.add(new ModelBox(g36, 81, 81, -0.2688F, -65.0005F, -12.0F, 4, 0, 2, 0.0F, false));
        g36.cubeList.add(new ModelBox(g36, 81, 81, -0.2688F, -65.0005F, -16.0F, 4, 0, 2, 0.0F, false));
        g36.cubeList.add(new ModelBox(g36, 81, 81, -0.2688F, -65.0005F, -20.0F, 4, 0, 2, 0.0F, false));
        g36.cubeList.add(new ModelBox(g36, 81, 81, -0.2688F, -65.0005F, -24.0F, 4, 0, 2, 0.0F, false));
        g36.cubeList.add(new ModelBox(g36, 87, 89, -0.2679F, -38.0F, -51.0F, 4, 0, 2, 0.0F, false));
        g36.cubeList.add(new ModelBox(g36, 87, 89, -0.2679F, -38.0F, -43.0F, 4, 0, 2, 0.0F, false));
        g36.cubeList.add(new ModelBox(g36, 87, 89, -0.2679F, -38.0F, -35.0F, 4, 0, 2, 0.0F, false));
        g36.cubeList.add(new ModelBox(g36, 87, 89, -0.2679F, -38.0F, -27.0F, 4, 0, 2, 0.0F, false));
        g36.cubeList.add(new ModelBox(g36, 87, 89, -0.2679F, -38.0F, -47.0F, 4, 0, 2, 0.0F, false));
        g36.cubeList.add(new ModelBox(g36, 87, 89, -0.2679F, -38.0F, -39.0F, 4, 0, 2, 0.0F, false));
        g36.cubeList.add(new ModelBox(g36, 87, 89, -0.2679F, -38.0F, -31.0F, 4, 0, 2, 0.0F, false));
        g36.cubeList.add(new ModelBox(g36, 81, 81, -0.2688F, -65.0005F, 24.0F, 4, 0, 2, 0.0F, false));
        g36.cubeList.add(new ModelBox(g36, 81, 81, -3.7312F, -65.0005F, 20.0F, 4, 0, 2, 0.0F, true));
        g36.cubeList.add(new ModelBox(g36, 81, 81, -3.7312F, -65.0005F, 16.0F, 4, 0, 2, 0.0F, true));
        g36.cubeList.add(new ModelBox(g36, 81, 81, -3.7312F, -65.0005F, 12.0F, 4, 0, 2, 0.0F, true));
        g36.cubeList.add(new ModelBox(g36, 81, 81, -3.7312F, -65.0005F, 8.0F, 4, 0, 2, 0.0F, true));
        g36.cubeList.add(new ModelBox(g36, 81, 81, -3.7312F, -65.0005F, 4.0F, 4, 0, 2, 0.0F, true));
        g36.cubeList.add(new ModelBox(g36, 81, 81, -3.7312F, -65.0005F, 0.0F, 4, 0, 2, 0.0F, true));
        g36.cubeList.add(new ModelBox(g36, 81, 81, -3.7312F, -65.0005F, -4.0F, 4, 0, 2, 0.0F, true));
        g36.cubeList.add(new ModelBox(g36, 81, 81, -3.7312F, -65.0005F, -8.0F, 4, 0, 2, 0.0F, true));
        g36.cubeList.add(new ModelBox(g36, 81, 81, -3.7312F, -65.0005F, -12.0F, 4, 0, 2, 0.0F, true));
        g36.cubeList.add(new ModelBox(g36, 81, 81, -3.7312F, -65.0005F, -16.0F, 4, 0, 2, 0.0F, true));
        g36.cubeList.add(new ModelBox(g36, 81, 81, -3.7312F, -65.0005F, -20.0F, 4, 0, 2, 0.0F, true));
        g36.cubeList.add(new ModelBox(g36, 81, 81, -3.7312F, -65.0005F, -24.0F, 4, 0, 2, 0.0F, true));
        g36.cubeList.add(new ModelBox(g36, 87, 89, -3.7321F, -37.999F, -51.0F, 4, 0, 2, 0.0F, true));
        g36.cubeList.add(new ModelBox(g36, 87, 89, -3.7321F, -37.999F, -43.0F, 4, 0, 2, 0.0F, true));
        g36.cubeList.add(new ModelBox(g36, 87, 89, -3.7321F, -37.999F, -35.0F, 4, 0, 2, 0.0F, true));
        g36.cubeList.add(new ModelBox(g36, 87, 89, -3.7321F, -37.999F, -27.0F, 4, 0, 2, 0.0F, true));
        g36.cubeList.add(new ModelBox(g36, 87, 89, -3.7321F, -37.999F, -47.0F, 4, 0, 2, 0.0F, true));
        g36.cubeList.add(new ModelBox(g36, 87, 89, -3.7321F, -37.999F, -39.0F, 4, 0, 2, 0.0F, true));
        g36.cubeList.add(new ModelBox(g36, 87, 89, -3.7321F, -37.999F, -31.0F, 4, 0, 2, 0.0F, true));
        g36.cubeList.add(new ModelBox(g36, 81, 81, -3.7312F, -65.0005F, 24.0F, 4, 0, 2, 0.0F, true));
        g36.cubeList.add(new ModelBox(g36, 72, 10, -3.0F, -57.0F, 11.0F, 6, 3, 13, 0.0F, false));
        g36.cubeList.add(new ModelBox(g36, 72, 10, -4.0F, -57.0F, 6.0F, 8, 3, 5, 0.0F, false));
        g36.cubeList.add(new ModelBox(g36, 72, 10, -3.5F, -54.0F, 11.0F, 7, 1, 13, 0.0F, false));
        g36.cubeList.add(new ModelBox(g36, 72, 10, -4.5F, -54.0F, 6.0F, 9, 1, 5, 0.0F, false));
        g36.cubeList.add(new ModelBox(g36, 66, 17, -4.5F, -53.0F, 15.0F, 9, 3, 22, 0.0F, false));
        g36.cubeList.add(new ModelBox(g36, 64, 21, -4.5F, -53.0F, -7.0F, 9, 3, 22, 0.0F, false));
        g36.cubeList.add(new ModelBox(g36, 70, 26, -5.5F, -50.0F, 19.0F, 11, 2, 18, 0.0F, false));
        g36.cubeList.add(new ModelBox(g36, 64, 26, -5.5F, -44.0F, 16.0F, 11, 2, 21, 0.0F, false));
        g36.cubeList.add(new ModelBox(g36, 64, 26, -5.5F, -44.0F, -5.0F, 11, 2, 21, 0.0F, false));
        g36.cubeList.add(new ModelBox(g36, 64, 26, -5.5F, -41.0F, -7.9141F, 11, 3, 10, 0.0F, false));
        g36.cubeList.add(new ModelBox(g36, 64, 26, -5.5F, -41.0F, 2.0859F, 1, 7, 14, 0.0F, false));
        g36.cubeList.add(new ModelBox(g36, 64, 26, 4.5F, -41.0F, 2.0859F, 1, 7, 14, 0.0F, true));
        g36.cubeList.add(new ModelBox(g36, 64, 26, -3.5F, -42.0F, 16.0859F, 7, 3, 17, 0.0F, false));
        g36.cubeList.add(new ModelBox(g36, 64, 26, -1.5F, -33.2773F, 16.0859F, 3, 1, 6, 0.0F, false));
        g36.cubeList.add(new ModelBox(g36, 64, 26, -4.0F, -41.0F, 26.0859F, 8, 7, 8, 0.0F, false));
        g36.cubeList.add(new ModelBox(g36, 64, 26, -5.5F, -41.0F, 13.0859F, 11, 11, 3, 0.0F, false));
        g36.cubeList.add(new ModelBox(g36, 64, 26, -4.5F, -41.0F, 2.0859F, 9, 3, 11, 0.0F, false));
        g36.cubeList.add(new ModelBox(g36, 67, 16, -5.0F, -42.0F, -13.9141F, 10, 1, 16, 0.0F, false));
        g36.cubeList.add(new ModelBox(g36, 67, 16, -5.0F, -42.0F, 2.0859F, 10, 1, 12, 0.0F, false));
        g36.cubeList.add(new ModelBox(g36, 68, 17, -5.5F, -42.0F, 14.0859F, 11, 1, 2, 0.0F, false));
        g36.cubeList.add(new ModelBox(g36, 64, 26, -5.5F, -44.0F, -14.0F, 11, 2, 9, 0.0F, false));
        g36.cubeList.add(new ModelBox(g36, 66, 31, -7.0F, -47.0F, -28.0F, 14, 5, 14, 0.0F, false));
        g36.cubeList.add(new ModelBox(g36, 66, 31, -7.0F, -47.0F, -39.0F, 14, 5, 11, 0.0F, false));
        g36.cubeList.add(new ModelBox(g36, 66, 31, -7.0F, -47.0F, -54.0F, 2, 5, 15, 0.0F, false));
        g36.cubeList.add(new ModelBox(g36, 67, 30, -5.5858F, -42.5858F, -54.0F, 6, 2, 20, 0.0F, false));
        g36.cubeList.add(new ModelBox(g36, 76, 16, -5.5858F, -42.5858F, -34.0F, 6, 2, 20, 0.0F, false));
        g36.cubeList.add(new ModelBox(g36, 76, 17, -0.4142F, -42.5858F, -54.0F, 6, 2, 20, 0.0F, true));
        g36.cubeList.add(new ModelBox(g36, 64, 29, -0.4142F, -42.5858F, -34.0F, 6, 2, 20, 0.0F, true));
        g36.cubeList.add(new ModelBox(g36, 66, 31, 5.0F, -47.0F, -54.0F, 2, 5, 15, 0.0F, true));
        g36.cubeList.add(new ModelBox(g36, 32, 39, -0.5F, -47.5F, -54.0F, 1, 1, 15, 0.0F, false));
        g36.cubeList.add(new ModelBox(g36, 32, 39, -0.5F, -47.5F, -69.0F, 1, 1, 15, 0.0F, false));
        g36.cubeList.add(new ModelBox(g36, 32, 39, -1.866F, -48.866F, -54.0F, 1, 1, 15, 0.0F, false));
        g36.cubeList.add(new ModelBox(g36, 32, 39, -1.866F, -48.866F, -69.0F, 1, 1, 15, 0.0F, false));
        g36.cubeList.add(new ModelBox(g36, 32, 39, -0.5F, -50.2321F, -54.0F, 1, 1, 15, 0.0F, true));
        g36.cubeList.add(new ModelBox(g36, 32, 39, -0.5F, -50.2321F, -69.0F, 1, 1, 15, 0.0F, true));
        g36.cubeList.add(new ModelBox(g36, 32, 39, 0.866F, -48.866F, -54.0F, 1, 1, 15, 0.0F, true));
        g36.cubeList.add(new ModelBox(g36, 32, 39, 0.866F, -48.866F, -69.0F, 1, 1, 15, 0.0F, true));
        g36.cubeList.add(new ModelBox(g36, 70, 26, -5.5F, -48.0F, 29.0F, 2, 5, 5, 0.0F, false));
        g36.cubeList.add(new ModelBox(g36, 70, 26, 3.3164F, -48.0F, 29.0F, 2, 4, 5, 0.0F, false));
        g36.cubeList.add(new ModelBox(g36, 24, 161, 3.9367F, -45.6992F, 29.4F, 2, 1, 2, 0.0F, false));
        g36.cubeList.add(new ModelBox(g36, 70, 26, -5.5F, -48.0F, 34.0F, 11, 4, 3, 0.0F, false));
        g36.cubeList.add(new ModelBox(g36, 70, 26, -5.5F, -48.0F, 19.0F, 11, 4, 10, 0.0F, false));
        g36.cubeList.add(new ModelBox(g36, 67, 14, -5.5F, -50.0F, 1.0F, 11, 6, 18, 0.0F, false));
        g36.cubeList.add(new ModelBox(g36, 83, 93, 4.9141F, -39.543F, -7.1836F, 1, 2, 5, 0.0F, false));
        g36.cubeList.add(new ModelBox(g36, 84, 31, 4.9141F, -49.4297F, -14.1367F, 1, 2, 3, 0.0F, false));
        g36.cubeList.add(new ModelBox(g36, 84, 31, 5.457F, -47.4297F, -14.1367F, 1, 5, 3, 0.0F, false));
        g36.cubeList.add(new ModelBox(g36, 84, 31, -6.457F, -47.4297F, -14.1367F, 1, 5, 3, 0.0F, true));
        g36.cubeList.add(new ModelBox(g36, 84, 31, -5.9141F, -49.4297F, -14.1367F, 1, 2, 3, 0.0F, true));
        g36.cubeList.add(new ModelBox(g36, 84, 31, -5.9141F, -39.543F, -7.1836F, 1, 2, 5, 0.0F, true));
        g36.cubeList.add(new ModelBox(g36, 64, 14, -5.5F, -50.0F, -17.0F, 11, 6, 18, 0.0F, false));
        g36.cubeList.add(new ModelBox(g36, 64, 14, -5.5F, -50.0F, -39.0F, 11, 6, 22, 0.0F, false));
        g36.cubeList.add(new ModelBox(g36, 64, 21, -4.5F, -53.0F, -29.0F, 9, 3, 22, 0.0F, false));
        g36.cubeList.add(new ModelBox(g36, 64, 21, -4.5F, -53.0F, -39.0F, 9, 3, 10, 0.0F, false));
        g36.cubeList.add(new ModelBox(g36, 64, 21, -3.5F, -54.0F, -54.0F, 7, 2, 18, 0.0F, false));
        g36.cubeList.add(new ModelBox(g36, 64, 21, -4.5F, -53.0F, -54.0F, 2, 2, 15, 0.0F, false));
        g36.cubeList.add(new ModelBox(g36, 64, 21, 2.5F, -53.0F, -54.0F, 2, 2, 15, 0.0F, true));
        g36.cubeList.add(new ModelBox(g36, 72, 10, -3.0F, -56.0F, 0.0F, 6, 3, 6, 0.0F, false));
        g36.cubeList.add(new ModelBox(g36, 73, 18, -3.0F, -57.0F, 24.0F, 6, 3, 13, 0.0F, false));
        g36.cubeList.add(new ModelBox(g36, 73, 18, -3.5F, -54.0F, 24.0F, 7, 1, 13, 0.0F, false));
        g36.cubeList.add(new ModelBox(g36, 64, 24, -4.9172F, -57.4609F, 37.3906F, 2, 3, 2, 0.0F, false));
        g36.cubeList.add(new ModelBox(g36, 64, 24, -4.4133F, -54.4609F, 37.3906F, 2, 1, 1, 0.0F, false));
        g36.cubeList.add(new ModelBox(g36, 64, 24, -4.9172F, -54.5212F, 38.0486F, 2, 1, 1, 0.0F, false));
        g36.cubeList.add(new ModelBox(g36, 64, 24, -4.1172F, -58.4609F, 37.5906F, 1, 1, 1, 0.0F, false));
        g36.cubeList.add(new ModelBox(g36, 64, 24, -4.1172F, -58.4609F, 38.1906F, 1, 1, 1, 0.0F, false));
        g36.cubeList.add(new ModelBox(g36, 64, 24, -4.7172F, -58.4609F, 37.5906F, 1, 1, 1, 0.0F, false));
        g36.cubeList.add(new ModelBox(g36, 64, 24, -4.7172F, -58.4609F, 38.1906F, 1, 1, 1, 0.0F, false));
        g36.cubeList.add(new ModelBox(g36, 64, 24, -4.5F, -58.0F, 37.0F, 9, 4, 2, 0.0F, false));
        g36.cubeList.add(new ModelBox(g36, 64, 23, -3.5F, -55.0F, 39.0F, 7, 4, 23, 0.0F, false));
        g36.cubeList.add(new ModelBox(g36, 64, 23, -3.5F, -47.0F, 39.0F, 7, 4, 19, 0.0F, false));
        g36.cubeList.add(new ModelBox(g36, 64, 23, -3.5F, -46.0F, 58.0F, 7, 3, 2, 0.0F, false));
        g36.cubeList.add(new ModelBox(g36, 64, 23, -3.5F, -55.0F, 62.0F, 7, 4, 19, 0.0F, false));
        g36.cubeList.add(new ModelBox(g36, 64, 23, -3.5F, -51.0F, 39.0F, 7, 4, 6, 0.0F, false));
        g36.cubeList.add(new ModelBox(g36, 64, 23, -3.5F, -51.0F, 58.0F, 7, 5, 4, 0.0F, false));
        g36.cubeList.add(new ModelBox(g36, 64, 23, -2.5F, -43.1797F, 70.0F, 5, 4, 11, 0.0F, false));
        g36.cubeList.add(new ModelBox(g36, 64, 23, -3.5F, -56.0F, 81.0F, 7, 1, 3, 0.0F, false));
        g36.cubeList.add(new ModelBox(g36, 64, 23, -4.5F, -55.0F, 81.0F, 9, 25, 3, 0.0F, false));
        g36.cubeList.add(new ModelBox(g36, 64, 24, -5.0F, -54.0F, 37.0F, 10, 4, 2, 0.0F, false));
        g36.cubeList.add(new ModelBox(g36, 64, 24, -6.0F, -50.0F, 37.0F, 12, 4, 2, 0.0F, false));
        g36.cubeList.add(new ModelBox(g36, 64, 24, -1.5F, -58.7539F, 37.0F, 3, 1, 2, 0.0F, false));
        g36.cubeList.add(new ModelBox(g36, 64, 24, -4.0F, -62.0F, -31.0F, 8, 5, 2, 0.0F, false));
        g36.cubeList.add(new ModelBox(g36, 64, 24, -4.0F, -57.0F, -33.0F, 8, 3, 4, 0.0F, false));
        g36.cubeList.add(new ModelBox(g36, 64, 24, -5.0F, -54.0F, -33.0F, 10, 4, 4, 0.0F, false));
        g36.cubeList.add(new ModelBox(g36, 64, 24, 2.0F, -62.0F, -17.0F, 2, 4, 7, 0.0F, false));
        g36.cubeList.add(new ModelBox(g36, 64, 24, -4.0F, -62.0F, -17.0F, 2, 4, 7, 0.0F, true));
        g36.cubeList.add(new ModelBox(g36, 64, 24, -3.0F, -62.0F, -17.0F, 7, 2, 19, 0.0F, false));
        g36.cubeList.add(new ModelBox(g36, 64, 24, -4.0F, -62.0F, -29.0F, 8, 2, 12, 0.0F, false));
        g36.cubeList.add(new ModelBox(g36, 64, 24, -3.5F, -64.0F, -31.0F, 7, 2, 5, 0.0F, false));
        g36.cubeList.add(new ModelBox(g36, 64, 24, -4.0F, -62.0F, 19.0F, 8, 5, 18, 0.0F, false));
        g36.cubeList.add(new ModelBox(g36, 64, 24, -4.0F, -62.5508F, 31.5586F, 8, 1, 4, 0.0F, false));
        g36.cubeList.add(new ModelBox(g36, 112, 30, -3.5F, -63.2891F, 31.8867F, 7, 1, 1, 0.0F, false));
        g36.cubeList.add(new ModelBox(g36, 64, 24, -3.5F, -63.7461F, 29.9297F, 7, 1, 4, 0.0F, false));
        g36.cubeList.add(new ModelBox(g36, 64, 24, -3.5F, -64.4532F, 33.6368F, 7, 1, 1, 0.0F, false));
        g36.cubeList.add(new ModelBox(g36, 42, 48, 3.6836F, -64.5313F, 33.3595F, 1, 1, 1, 0.0F, false));
        g36.cubeList.add(new ModelBox(g36, 42, 48, -4.6836F, -64.5313F, 33.3595F, 1, 1, 1, 0.0F, true));
        g36.cubeList.add(new ModelBox(g36, 64, 24, 3.5F, -64.5508F, 25.5586F, 1, 3, 10, 0.0F, false));
        g36.cubeList.add(new ModelBox(g36, 64, 24, 3.5F, -68.5508F, 24.6304F, 1, 7, 1, 0.0F, false));
        g36.cubeList.add(new ModelBox(g36, 64, 24, -4.5F, -68.5508F, 24.6304F, 1, 7, 1, 0.0F, true));
        g36.cubeList.add(new ModelBox(g36, 64, 24, 3.5F, -68.5508F, 25.6304F, 1, 4, 3, 0.0F, false));
        g36.cubeList.add(new ModelBox(g36, 64, 24, -4.5F, -68.5508F, 25.6304F, 1, 4, 3, 0.0F, true));
        g36.cubeList.add(new ModelBox(g36, 64, 24, 3.5F, -65.5508F, 28.6304F, 1, 1, 1, 0.0F, false));
        g36.cubeList.add(new ModelBox(g36, 64, 24, -4.5F, -65.5508F, 28.6304F, 1, 1, 1, 0.0F, true));
        g36.cubeList.add(new ModelBox(g36, 64, 24, 1.5F, -65.9368F, 25.0319F, 2, 1, 6, 0.0F, false));
        g36.cubeList.add(new ModelBox(g36, 64, 24, -3.5F, -65.9368F, 25.0319F, 2, 1, 6, 0.0F, true));
        g36.cubeList.add(new ModelBox(g36, 64, 24, -1.5F, -65.9368F, 25.0319F, 3, 1, 3, 0.0F, true));
        g36.cubeList.add(new ModelBox(g36, 64, 24, -3.5F, -64.9368F, 25.0319F, 7, 3, 1, 0.0F, true));
        g36.cubeList.add(new ModelBox(g36, 64, 24, -2.5F, -66.2141F, 26.0319F, 5, 1, 1, 0.0F, true));
        g36.cubeList.add(new ModelBox(g36, 64, 24, -0.5F, -65.7141F, -29.9681F, 1, 1, 3, 0.0F, true));
        g36.cubeList.add(new ModelBox(g36, 64, 24, -0.5F, -70.3854F, -29.9681F, 1, 1, 3, 0.0F, true));
        g36.cubeList.add(new ModelBox(g36, 64, 24, -1.5F, -65.4368F, -29.4681F, 3, 2, 2, 0.0F, true));
        g36.cubeList.add(new ModelBox(g36, 0, 9, -0.5F, -67.4368F, -29.4681F, 1, 2, 2, 0.0F, true));
        g36.cubeList.add(new ModelBox(g36, 44, 497, -0.5F, -67.4368F, -27.4564F, 1, 1, 0, 0.0F, true));
        g36.cubeList.add(new ModelBox(g36, 64, 24, -2.5F, -70.2141F, 26.0319F, 5, 1, 1, 0.0F, true));
        g36.cubeList.add(new ModelBox(g36, 64, 24, -2.0F, -70.3141F, 26.0319F, 4, 1, 1, 0.0F, true));
        g36.cubeList.add(new ModelBox(g36, 64, 24, -2.5F, -69.2141F, 26.0319F, 1, 3, 1, 0.0F, true));
        g36.cubeList.add(new ModelBox(g36, 44, 497, -2.5531F, -68.2141F, 26.0944F, 1, 1, 1, 0.0F, true));
        g36.cubeList.add(new ModelBox(g36, 44, 497, 1.5531F, -68.2141F, 26.0944F, 1, 1, 1, 0.0F, false));
        g36.cubeList.add(new ModelBox(g36, 64, 24, -2.6F, -69.7141F, 26.0319F, 1, 4, 1, 0.0F, true));
        g36.cubeList.add(new ModelBox(g36, 64, 24, 1.6F, -69.7141F, 26.0319F, 1, 4, 1, 0.0F, false));
        g36.cubeList.add(new ModelBox(g36, 64, 24, 1.5F, -69.2141F, 26.0319F, 1, 3, 1, 0.0F, true));
        g36.cubeList.add(new ModelBox(g36, 64, 24, -1.5F, -65.6779F, 27.9978F, 3, 1, 4, 0.0F, true));
        g36.cubeList.add(new ModelBox(g36, 64, 24, -4.5F, -64.5508F, 25.5586F, 1, 3, 10, 0.0F, true));
        g36.cubeList.add(new ModelBox(g36, 45, 44, 3.043F, -59.0195F, 32.0F, 1, 2, 2, 0.0F, false));
        g36.cubeList.add(new ModelBox(g36, 45, 44, 3.043F, -59.0195F, 8.0F, 1, 2, 2, 0.0F, false));
        g36.cubeList.add(new ModelBox(g36, 45, 44, 3.3945F, -61.0195F, 32.0F, 1, 2, 2, 0.0F, false));
        g36.cubeList.add(new ModelBox(g36, 45, 44, 3.3945F, -61.0195F, 8.0F, 1, 2, 2, 0.0F, false));
        g36.cubeList.add(new ModelBox(g36, 45, 44, 3.5945F, -60.5195F, 32.5F, 1, 1, 1, 0.0F, false));
        g36.cubeList.add(new ModelBox(g36, 45, 44, 3.5945F, -60.5195F, 8.5F, 1, 1, 1, 0.0F, false));

        bone49 = new ModelRenderer(this);
        bone49.setRotationPoint(-6.0F, -38.0F, -17.0F);
        g36.addChild(bone49);
        setRotationAngle(bone49, 0.0F, 0.0F, -0.5236F);
        bone49.cubeList.add(new ModelBox(bone49, 83, 84, 15.4641F, -22.2487F, 39.0F, 2, 2, 2, 0.0F, false));
        bone49.cubeList.add(new ModelBox(bone49, 83, 84, 15.4641F, -22.2487F, 35.0F, 2, 2, 2, 0.0F, false));
        bone49.cubeList.add(new ModelBox(bone49, 83, 84, 15.4641F, -22.2487F, 31.0F, 2, 2, 2, 0.0F, false));
        bone49.cubeList.add(new ModelBox(bone49, 83, 84, 15.4641F, -22.2487F, 27.0F, 2, 2, 2, 0.0F, false));
        bone49.cubeList.add(new ModelBox(bone49, 83, 84, 15.4641F, -22.2487F, 23.0F, 2, 2, 2, 0.0F, false));
        bone49.cubeList.add(new ModelBox(bone49, 83, 84, 15.4641F, -22.2487F, 19.0F, 2, 2, 2, 0.0F, false));
        bone49.cubeList.add(new ModelBox(bone49, 83, 84, 15.4641F, -22.2487F, 15.0F, 2, 2, 2, 0.0F, false));
        bone49.cubeList.add(new ModelBox(bone49, 83, 84, 15.4641F, -22.2487F, 11.0F, 2, 2, 2, 0.0F, false));
        bone49.cubeList.add(new ModelBox(bone49, 83, 84, 15.4641F, -22.2487F, 7.0F, 2, 2, 2, 0.0F, false));
        bone49.cubeList.add(new ModelBox(bone49, 83, 84, 15.4641F, -22.2487F, 3.0F, 2, 2, 2, 0.0F, false));
        bone49.cubeList.add(new ModelBox(bone49, 83, 84, 15.4641F, -22.2487F, -1.0F, 2, 2, 2, 0.0F, false));
        bone49.cubeList.add(new ModelBox(bone49, 83, 84, 15.4641F, -22.2487F, -5.0F, 2, 2, 2, 0.0F, false));
        bone49.cubeList.add(new ModelBox(bone49, 83, 84, 15.4641F, -22.2487F, -9.0F, 2, 2, 2, 0.0F, false));
        bone49.cubeList.add(new ModelBox(bone49, 83, 84, 6.4282F, 2.866F, -36.0F, 2, 2, 2, 0.0F, false));
        bone49.cubeList.add(new ModelBox(bone49, 83, 84, 6.4282F, 2.866F, -28.0F, 2, 2, 2, 0.0F, false));
        bone49.cubeList.add(new ModelBox(bone49, 83, 84, 6.4282F, 2.866F, -20.0F, 2, 2, 2, 0.0F, false));
        bone49.cubeList.add(new ModelBox(bone49, 83, 84, 6.4282F, 2.866F, -12.0F, 2, 2, 2, 0.0F, false));
        bone49.cubeList.add(new ModelBox(bone49, 83, 84, 6.4282F, 2.866F, -32.0F, 2, 2, 2, 0.0F, false));
        bone49.cubeList.add(new ModelBox(bone49, 83, 84, 6.4282F, 2.866F, -24.0F, 2, 2, 2, 0.0F, false));
        bone49.cubeList.add(new ModelBox(bone49, 83, 84, 6.4282F, 2.866F, -16.0F, 2, 2, 2, 0.0F, false));
        bone49.cubeList.add(new ModelBox(bone49, 83, 84, 6.4282F, 2.866F, -8.0F, 2, 2, 2, 0.0F, false));
        bone49.cubeList.add(new ModelBox(bone49, 83, 84, 15.4641F, -22.2487F, 37.0F, 0, 2, 2, 0.0F, false));
        bone49.cubeList.add(new ModelBox(bone49, 83, 84, 15.4641F, -22.2487F, 33.0F, 0, 2, 2, 0.0F, false));
        bone49.cubeList.add(new ModelBox(bone49, 83, 84, 15.4641F, -22.2487F, 29.0F, 0, 2, 2, 0.0F, false));
        bone49.cubeList.add(new ModelBox(bone49, 83, 84, 15.4641F, -22.2487F, 25.0F, 0, 2, 2, 0.0F, false));
        bone49.cubeList.add(new ModelBox(bone49, 83, 84, 15.4641F, -22.2487F, 21.0F, 0, 2, 2, 0.0F, false));
        bone49.cubeList.add(new ModelBox(bone49, 83, 84, 15.4641F, -22.2487F, 17.0F, 0, 2, 2, 0.0F, false));
        bone49.cubeList.add(new ModelBox(bone49, 83, 84, 15.4641F, -22.2487F, 13.0F, 0, 2, 2, 0.0F, false));
        bone49.cubeList.add(new ModelBox(bone49, 83, 84, 15.4641F, -22.2487F, 9.0F, 0, 2, 2, 0.0F, false));
        bone49.cubeList.add(new ModelBox(bone49, 83, 84, 15.4641F, -22.2487F, 5.0F, 0, 2, 2, 0.0F, false));
        bone49.cubeList.add(new ModelBox(bone49, 83, 84, 15.4641F, -22.2487F, 1.0F, 0, 2, 2, 0.0F, false));
        bone49.cubeList.add(new ModelBox(bone49, 83, 84, 15.4641F, -22.2487F, -3.0F, 0, 2, 2, 0.0F, false));
        bone49.cubeList.add(new ModelBox(bone49, 83, 84, 15.4641F, -22.2487F, -7.0F, 0, 2, 2, 0.0F, false));
        bone49.cubeList.add(new ModelBox(bone49, 83, 84, 8.4282F, 2.866F, -34.0F, 0, 2, 2, 0.0F, false));
        bone49.cubeList.add(new ModelBox(bone49, 83, 84, 8.4282F, 2.866F, -26.0F, 0, 2, 2, 0.0F, false));
        bone49.cubeList.add(new ModelBox(bone49, 83, 84, 8.4282F, 2.866F, -18.0F, 0, 2, 2, 0.0F, false));
        bone49.cubeList.add(new ModelBox(bone49, 83, 84, 8.4282F, 2.866F, -10.0F, 0, 2, 2, 0.0F, false));
        bone49.cubeList.add(new ModelBox(bone49, 83, 84, 8.4282F, 2.866F, -30.0F, 0, 2, 2, 0.0F, false));
        bone49.cubeList.add(new ModelBox(bone49, 83, 84, 8.4282F, 2.866F, -22.0F, 0, 2, 2, 0.0F, false));
        bone49.cubeList.add(new ModelBox(bone49, 83, 84, 8.4282F, 2.866F, -14.0F, 0, 2, 2, 0.0F, false));
        bone49.cubeList.add(new ModelBox(bone49, 83, 84, 15.4641F, -22.2487F, 41.0F, 0, 2, 2, 0.0F, false));

        bone50 = new ModelRenderer(this);
        bone50.setRotationPoint(6.0F, -38.0F, -17.0F);
        g36.addChild(bone50);
        setRotationAngle(bone50, 0.0F, 0.0F, 0.5236F);
        bone50.cubeList.add(new ModelBox(bone50, 83, 84, -17.4641F, -22.2487F, 39.0F, 2, 2, 2, 0.0F, true));
        bone50.cubeList.add(new ModelBox(bone50, 83, 84, -17.4641F, -22.2487F, 35.0F, 2, 2, 2, 0.0F, true));
        bone50.cubeList.add(new ModelBox(bone50, 83, 84, -17.4641F, -22.2487F, 31.0F, 2, 2, 2, 0.0F, true));
        bone50.cubeList.add(new ModelBox(bone50, 83, 84, -17.4641F, -22.2487F, 27.0F, 2, 2, 2, 0.0F, true));
        bone50.cubeList.add(new ModelBox(bone50, 83, 84, -17.4641F, -22.2487F, 23.0F, 2, 2, 2, 0.0F, true));
        bone50.cubeList.add(new ModelBox(bone50, 83, 84, -17.4641F, -22.2487F, 19.0F, 2, 2, 2, 0.0F, true));
        bone50.cubeList.add(new ModelBox(bone50, 83, 84, -17.4641F, -22.2487F, 15.0F, 2, 2, 2, 0.0F, true));
        bone50.cubeList.add(new ModelBox(bone50, 83, 84, -17.4641F, -22.2487F, 11.0F, 2, 2, 2, 0.0F, true));
        bone50.cubeList.add(new ModelBox(bone50, 83, 84, -17.4641F, -22.2487F, 7.0F, 2, 2, 2, 0.0F, true));
        bone50.cubeList.add(new ModelBox(bone50, 83, 84, -17.4641F, -22.2487F, 3.0F, 2, 2, 2, 0.0F, true));
        bone50.cubeList.add(new ModelBox(bone50, 83, 84, -17.4641F, -22.2487F, -1.0F, 2, 2, 2, 0.0F, true));
        bone50.cubeList.add(new ModelBox(bone50, 83, 84, -17.4641F, -22.2487F, -5.0F, 2, 2, 2, 0.0F, true));
        bone50.cubeList.add(new ModelBox(bone50, 83, 84, -17.4641F, -22.2487F, -9.0F, 2, 2, 2, 0.0F, true));
        bone50.cubeList.add(new ModelBox(bone50, 83, 84, -8.4282F, 2.866F, -36.0F, 2, 2, 2, 0.0F, true));
        bone50.cubeList.add(new ModelBox(bone50, 83, 84, -8.4282F, 2.866F, -28.0F, 2, 2, 2, 0.0F, true));
        bone50.cubeList.add(new ModelBox(bone50, 83, 84, -8.4282F, 2.866F, -20.0F, 2, 2, 2, 0.0F, true));
        bone50.cubeList.add(new ModelBox(bone50, 83, 84, -8.4282F, 2.866F, -12.0F, 2, 2, 2, 0.0F, true));
        bone50.cubeList.add(new ModelBox(bone50, 83, 84, -8.4282F, 2.866F, -32.0F, 2, 2, 2, 0.0F, true));
        bone50.cubeList.add(new ModelBox(bone50, 83, 84, -8.4282F, 2.866F, -24.0F, 2, 2, 2, 0.0F, true));
        bone50.cubeList.add(new ModelBox(bone50, 83, 84, -8.4282F, 2.866F, -16.0F, 2, 2, 2, 0.0F, true));
        bone50.cubeList.add(new ModelBox(bone50, 83, 84, -8.4282F, 2.866F, -8.0F, 2, 2, 2, 0.0F, true));
        bone50.cubeList.add(new ModelBox(bone50, 83, 84, -15.4641F, -22.2487F, 37.0F, 0, 2, 2, 0.0F, true));
        bone50.cubeList.add(new ModelBox(bone50, 83, 84, -15.4641F, -22.2487F, 33.0F, 0, 2, 2, 0.0F, true));
        bone50.cubeList.add(new ModelBox(bone50, 83, 84, -15.4641F, -22.2487F, 29.0F, 0, 2, 2, 0.0F, true));
        bone50.cubeList.add(new ModelBox(bone50, 83, 84, -15.4641F, -22.2487F, 25.0F, 0, 2, 2, 0.0F, true));
        bone50.cubeList.add(new ModelBox(bone50, 83, 84, -15.4641F, -22.2487F, 21.0F, 0, 2, 2, 0.0F, true));
        bone50.cubeList.add(new ModelBox(bone50, 83, 84, -15.4641F, -22.2487F, 17.0F, 0, 2, 2, 0.0F, true));
        bone50.cubeList.add(new ModelBox(bone50, 83, 84, -15.4641F, -22.2487F, 13.0F, 0, 2, 2, 0.0F, true));
        bone50.cubeList.add(new ModelBox(bone50, 83, 84, -15.4641F, -22.2487F, 9.0F, 0, 2, 2, 0.0F, true));
        bone50.cubeList.add(new ModelBox(bone50, 83, 84, -15.4641F, -22.2487F, 5.0F, 0, 2, 2, 0.0F, true));
        bone50.cubeList.add(new ModelBox(bone50, 83, 84, -15.4641F, -22.2487F, 1.0F, 0, 2, 2, 0.0F, true));
        bone50.cubeList.add(new ModelBox(bone50, 83, 84, -15.4641F, -22.2487F, -3.0F, 0, 2, 2, 0.0F, true));
        bone50.cubeList.add(new ModelBox(bone50, 83, 84, -15.4641F, -22.2487F, -7.0F, 0, 2, 2, 0.0F, true));
        bone50.cubeList.add(new ModelBox(bone50, 83, 84, -8.4272F, 2.866F, -34.0F, 0, 2, 2, 0.0F, true));
        bone50.cubeList.add(new ModelBox(bone50, 83, 84, -8.4272F, 2.866F, -26.0F, 0, 2, 2, 0.0F, true));
        bone50.cubeList.add(new ModelBox(bone50, 83, 84, -8.4272F, 2.866F, -18.0F, 0, 2, 2, 0.0F, true));
        bone50.cubeList.add(new ModelBox(bone50, 83, 84, -8.4272F, 2.866F, -10.0F, 0, 2, 2, 0.0F, true));
        bone50.cubeList.add(new ModelBox(bone50, 83, 84, -8.4272F, 2.866F, -30.0F, 0, 2, 2, 0.0F, true));
        bone50.cubeList.add(new ModelBox(bone50, 83, 84, -8.4272F, 2.866F, -22.0F, 0, 2, 2, 0.0F, true));
        bone50.cubeList.add(new ModelBox(bone50, 83, 84, -8.4272F, 2.866F, -14.0F, 0, 2, 2, 0.0F, true));
        bone50.cubeList.add(new ModelBox(bone50, 83, 84, -15.4641F, -22.2487F, 41.0F, 0, 2, 2, 0.0F, true));

        bone100 = new ModelRenderer(this);
        bone100.setRotationPoint(-2.5F, 0.0F, 0.0F);
        g36.addChild(bone100);
        setRotationAngle(bone100, 0.3491F, 0.0F, 0.0F);
        bone100.cubeList.add(new ModelBox(bone100, 64, 26, -3.0F, -37.7602F, 6.3552F, 11, 1, 3, 0.0F, false));
        bone100.cubeList.add(new ModelBox(bone100, 64, 26, -3.0F, -37.7602F, 5.3552F, 1, 2, 1, 0.0F, false));
        bone100.cubeList.add(new ModelBox(bone100, 64, 26, 7.0F, -37.7602F, 5.3552F, 1, 2, 1, 0.0F, true));
        bone100.cubeList.add(new ModelBox(bone100, 64, 26, -3.0F, -42.7602F, 5.3552F, 11, 5, 4, 0.0F, false));

        bone98 = new ModelRenderer(this);
        bone98.setRotationPoint(0.0F, -11.1172F, -16.3789F);
        g36.addChild(bone98);
        setRotationAngle(bone98, -0.0873F, 0.0F, 0.0F);
        bone98.cubeList.add(new ModelBox(bone98, 47, 9, -1.0F, -31.3835F, 36.9946F, 2, 2, 1, 0.0F, false));

        bone99 = new ModelRenderer(this);
        bone99.setRotationPoint(0.0F, -9.1172F, -16.3789F);
        g36.addChild(bone99);
        setRotationAngle(bone99, -0.4363F, 0.0F, 0.0F);
        bone99.cubeList.add(new ModelBox(bone99, 47, 9, -1.0F, -42.419F, 23.8082F, 2, 2, 1, 0.0F, false));

        bone97 = new ModelRenderer(this);
        bone97.setRotationPoint(-1.5F, 0.0F, -11.0F);
        g36.addChild(bone97);
        setRotationAngle(bone97, -0.3491F, 0.0F, 0.0F);


        bone96 = new ModelRenderer(this);
        bone96.setRotationPoint(-3.0F, 0.0F, 0.0F);
        g36.addChild(bone96);
        setRotationAngle(bone96, 0.3491F, 0.0F, 0.0F);
        bone96.cubeList.add(new ModelBox(bone96, 64, 26, -1.0F, -23.7117F, 36.2621F, 8, 14, 8, 0.0F, false));
        bone96.cubeList.add(new ModelBox(bone96, 64, 26, 1.5F, -23.7769F, 31.7935F, 3, 1, 5, 0.0F, false));

        bone95 = new ModelRenderer(this);
        bone95.setRotationPoint(-2.5F, 0.0F, 0.0F);
        g36.addChild(bone95);
        setRotationAngle(bone95, -0.2618F, 0.0F, 0.0F);
        bone95.cubeList.add(new ModelBox(bone95, 64, 26, -3.0F, -39.3647F, -16.1245F, 1, 7, 8, 0.0F, false));
        bone95.cubeList.add(new ModelBox(bone95, 64, 26, 7.0F, -39.3647F, -16.1245F, 1, 7, 8, 0.0F, true));
        bone95.cubeList.add(new ModelBox(bone95, 64, 26, -3.0F, -39.3647F, -8.1245F, 1, 7, 13, 0.0F, false));
        bone95.cubeList.add(new ModelBox(bone95, 64, 26, -2.0F, -35.5717F, -15.6909F, 9, 1, 20, 0.0F, false));
        bone95.cubeList.add(new ModelBox(bone95, 64, 26, 7.0F, -39.3647F, -8.1245F, 1, 7, 13, 0.0F, true));

        bone82 = new ModelRenderer(this);
        bone82.setRotationPoint(0.0F, -20.0F, -92.0F);
        g36.addChild(bone82);
        setRotationAngle(bone82, 0.0F, 0.0F, -0.5236F);
        bone82.cubeList.add(new ModelBox(bone82, 32, 39, 13.683F, -23.6997F, 38.0F, 1, 1, 15, 0.0F, false));
        bone82.cubeList.add(new ModelBox(bone82, 32, 39, 13.683F, -23.6997F, 23.0F, 1, 1, 15, 0.0F, false));
        bone82.cubeList.add(new ModelBox(bone82, 32, 39, 12.317F, -25.0657F, 38.0F, 1, 1, 15, 0.0F, false));
        bone82.cubeList.add(new ModelBox(bone82, 32, 39, 12.317F, -25.0657F, 23.0F, 1, 1, 15, 0.0F, false));
        bone82.cubeList.add(new ModelBox(bone82, 32, 39, 15.049F, -25.0657F, 38.0F, 1, 1, 15, 0.0F, false));
        bone82.cubeList.add(new ModelBox(bone82, 32, 39, 15.049F, -25.0657F, 23.0F, 1, 1, 15, 0.0F, false));
        bone82.cubeList.add(new ModelBox(bone82, 32, 39, 13.683F, -26.4317F, 38.0F, 1, 1, 15, 0.0F, false));
        bone82.cubeList.add(new ModelBox(bone82, 32, 39, 13.683F, -26.4317F, 23.0F, 1, 1, 15, 0.0F, false));

        bone83 = new ModelRenderer(this);
        bone83.setRotationPoint(0.0F, -20.0F, -92.0F);
        g36.addChild(bone83);
        setRotationAngle(bone83, 0.0F, 0.0F, 0.5236F);
        bone83.cubeList.add(new ModelBox(bone83, 32, 39, -14.683F, -23.6997F, 38.0F, 1, 1, 15, 0.0F, true));
        bone83.cubeList.add(new ModelBox(bone83, 32, 39, -14.683F, -23.6997F, 23.0F, 1, 1, 15, 0.0F, true));
        bone83.cubeList.add(new ModelBox(bone83, 32, 39, -13.317F, -25.0657F, 38.0F, 1, 1, 15, 0.0F, true));
        bone83.cubeList.add(new ModelBox(bone83, 32, 39, -13.317F, -25.0657F, 23.0F, 1, 1, 15, 0.0F, true));
        bone83.cubeList.add(new ModelBox(bone83, 32, 39, -16.049F, -25.0657F, 38.0F, 1, 1, 15, 0.0F, true));
        bone83.cubeList.add(new ModelBox(bone83, 32, 39, -16.049F, -25.0657F, 23.0F, 1, 1, 15, 0.0F, true));
        bone83.cubeList.add(new ModelBox(bone83, 32, 39, -14.683F, -26.4317F, 38.0F, 1, 1, 15, 0.0F, true));
        bone83.cubeList.add(new ModelBox(bone83, 32, 39, -14.683F, -26.4317F, 23.0F, 1, 1, 15, 0.0F, true));

        bone79 = new ModelRenderer(this);
        bone79.setRotationPoint(14.0F, -19.0F, -67.0F);
        g36.addChild(bone79);
        setRotationAngle(bone79, 0.0F, 0.0F, -0.2618F);
        bone79.cubeList.add(new ModelBox(bone79, 64, 26, -1.5145F, -33.8577F, 28.0F, 2, 5, 25, 0.0F, false));
        bone79.cubeList.add(new ModelBox(bone79, 64, 26, -1.5145F, -33.8577F, 23.0F, 2, 1, 5, 0.0F, false));
        bone79.cubeList.add(new ModelBox(bone79, 64, 26, -1.5145F, -33.8577F, 20.0F, 2, 5, 3, 0.0F, false));
        bone79.cubeList.add(new ModelBox(bone79, 64, 26, -1.5145F, -33.8577F, 13.0F, 2, 5, 2, 0.0F, false));
        bone79.cubeList.add(new ModelBox(bone79, 64, 26, -1.5145F, -33.8577F, 15.0F, 2, 1, 5, 0.0F, false));
        bone79.cubeList.add(new ModelBox(bone79, 64, 26, -1.5145F, -29.8577F, 23.0F, 2, 1, 5, 0.0F, false));
        bone79.cubeList.add(new ModelBox(bone79, 64, 26, -1.5145F, -29.8577F, 15.0F, 2, 1, 5, 0.0F, false));

        bone80 = new ModelRenderer(this);
        bone80.setRotationPoint(-14.0F, -19.0F, -67.0F);
        g36.addChild(bone80);
        setRotationAngle(bone80, 0.0F, 0.0F, 0.2618F);
        bone80.cubeList.add(new ModelBox(bone80, 64, 26, -0.4855F, -33.8577F, 28.0F, 2, 5, 25, 0.0F, true));
        bone80.cubeList.add(new ModelBox(bone80, 64, 26, -0.4855F, -33.8577F, 23.0F, 2, 1, 5, 0.0F, true));
        bone80.cubeList.add(new ModelBox(bone80, 64, 26, -0.4855F, -33.8577F, 20.0F, 2, 5, 3, 0.0F, true));
        bone80.cubeList.add(new ModelBox(bone80, 64, 26, -0.4855F, -33.8577F, 13.0F, 2, 5, 2, 0.0F, true));
        bone80.cubeList.add(new ModelBox(bone80, 64, 26, -0.4855F, -33.8577F, 15.0F, 2, 1, 5, 0.0F, true));
        bone80.cubeList.add(new ModelBox(bone80, 64, 26, -0.4855F, -29.8577F, 23.0F, 2, 1, 5, 0.0F, true));
        bone80.cubeList.add(new ModelBox(bone80, 64, 26, -0.4855F, -29.8577F, 15.0F, 2, 1, 5, 0.0F, true));

        bone78 = new ModelRenderer(this);
        bone78.setRotationPoint(11.0F, -18.5F, -7.5F);
        g36.addChild(bone78);
        setRotationAngle(bone78, 0.0F, 0.7854F, -0.5236F);
        bone78.cubeList.add(new ModelBox(bone78, 11, 466, -22.9819F, -26.1684F, 32.5425F, 1, 1, 1, 0.0F, false));
        bone78.cubeList.add(new ModelBox(bone78, 11, 466, -22.0726F, -27.8747F, 33.0265F, 1, 1, 1, 0.0F, false));
        bone78.cubeList.add(new ModelBox(bone78, 11, 466, -22.7698F, -27.0344F, 33.0375F, 1, 1, 1, 0.0F, false));
        bone78.cubeList.add(new ModelBox(bone78, 11, 466, -23.3355F, -26.1684F, 32.8961F, 1, 1, 1, 0.0F, false));
        bone78.cubeList.add(new ModelBox(bone78, 11, 466, -22.4262F, -27.8747F, 33.38F, 1, 1, 1, 0.0F, false));
        bone78.cubeList.add(new ModelBox(bone78, 11, 466, -24.0426F, -26.1684F, 33.6032F, 1, 1, 1, 0.0F, false));
        bone78.cubeList.add(new ModelBox(bone78, 11, 466, -23.689F, -26.1684F, 33.2496F, 1, 1, 1, 0.0F, false));
        bone78.cubeList.add(new ModelBox(bone78, 11, 466, -22.7797F, -27.8747F, 33.7336F, 1, 1, 1, 0.0F, false));

        bone67 = new ModelRenderer(this);
        bone67.setRotationPoint(8.8F, -18.1992F, -9.6F);
        g36.addChild(bone67);
        setRotationAngle(bone67, 0.2618F, 0.0F, 0.0F);
        bone67.cubeList.add(new ModelBox(bone67, 24, 161, -4.8633F, -16.2272F, 44.6251F, 2, 1, 2, 0.0F, false));

        bone68 = new ModelRenderer(this);
        bone68.setRotationPoint(8.8F, -18.1992F, -9.6F);
        g36.addChild(bone68);
        setRotationAngle(bone68, 0.5236F, 0.0F, 0.0F);
        bone68.cubeList.add(new ModelBox(bone68, 24, 161, -4.8633F, -3.8827F, 47.141F, 2, 1, 2, 0.0F, false));

        bone69 = new ModelRenderer(this);
        bone69.setRotationPoint(8.8F, -18.1992F, -9.6F);
        g36.addChild(bone69);
        setRotationAngle(bone69, 0.7854F, 0.0F, 0.0F);
        bone69.cubeList.add(new ModelBox(bone69, 24, 161, -4.8633F, 8.6924F, 46.3762F, 2, 1, 2, 0.0F, false));

        bone70 = new ModelRenderer(this);
        bone70.setRotationPoint(8.8F, -18.1992F, -9.6F);
        g36.addChild(bone70);
        setRotationAngle(bone70, 1.0472F, 0.0F, 0.0F);
        bone70.cubeList.add(new ModelBox(bone70, 24, 161, -4.8633F, 20.641F, 42.3827F, 2, 1, 2, 0.0F, false));

        bone71 = new ModelRenderer(this);
        bone71.setRotationPoint(8.8F, -18.1992F, -9.6F);
        g36.addChild(bone71);
        setRotationAngle(bone71, 1.309F, 0.0F, 0.0F);
        bone71.cubeList.add(new ModelBox(bone71, 24, 161, -4.8633F, 31.1489F, 35.4328F, 2, 1, 2, 0.0F, false));

        bone72 = new ModelRenderer(this);
        bone72.setRotationPoint(8.8F, -18.1992F, -9.6F);
        g36.addChild(bone72);
        setRotationAngle(bone72, 1.5708F, 0.0F, 0.0F);
        bone72.cubeList.add(new ModelBox(bone72, 24, 161, -4.8633F, 39.5F, 26.0F, 2, 1, 2, 0.0F, false));

        bone73 = new ModelRenderer(this);
        bone73.setRotationPoint(8.8F, -18.1992F, -9.6F);
        g36.addChild(bone73);
        setRotationAngle(bone73, 1.8326F, 0.0F, 0.0F);
        bone73.cubeList.add(new ModelBox(bone73, 24, 161, -4.8633F, 45.1251F, 14.7272F, 2, 1, 2, 0.0F, false));

        bone74 = new ModelRenderer(this);
        bone74.setRotationPoint(8.8F, -18.1992F, -9.6F);
        g36.addChild(bone74);
        setRotationAngle(bone74, 2.0944F, 0.0F, 0.0F);
        bone74.cubeList.add(new ModelBox(bone74, 24, 161, -4.8633F, 47.641F, 2.3827F, 2, 1, 2, 0.0F, false));

        bone75 = new ModelRenderer(this);
        bone75.setRotationPoint(8.8F, -18.1992F, -9.6F);
        g36.addChild(bone75);
        setRotationAngle(bone75, 2.3562F, 0.0F, 0.0F);
        bone75.cubeList.add(new ModelBox(bone75, 24, 161, -4.8633F, 46.8762F, -10.1924F, 2, 1, 2, 0.0F, false));

        bone76 = new ModelRenderer(this);
        bone76.setRotationPoint(8.8F, -18.1992F, -9.6F);
        g36.addChild(bone76);
        setRotationAngle(bone76, 2.618F, 0.0F, 0.0F);
        bone76.cubeList.add(new ModelBox(bone76, 24, 161, -4.8633F, 42.8827F, -22.141F, 2, 1, 2, 0.0F, false));

        bone77 = new ModelRenderer(this);
        bone77.setRotationPoint(8.8F, -18.1992F, -9.6F);
        g36.addChild(bone77);
        setRotationAngle(bone77, 2.8798F, 0.0F, 0.0F);
        bone77.cubeList.add(new ModelBox(bone77, 24, 161, -4.8633F, 35.9328F, -32.6489F, 2, 1, 2, 0.0F, false));

        bone81 = new ModelRenderer(this);
        bone81.setRotationPoint(-3.0F, -32.0F, -86.5F);
        g36.addChild(bone81);
        setRotationAngle(bone81, 0.0F, 0.0F, 0.7854F);
        bone81.cubeList.add(new ModelBox(bone81, 64, 21, -15.9099F, -14.7886F, 32.5F, 1, 1, 21, 0.0F, false));
        bone81.cubeList.add(new ModelBox(bone81, 64, 21, -10.5459F, -20.1525F, 32.5F, 1, 1, 21, 0.0F, false));
        bone81.cubeList.add(new ModelBox(bone81, 64, 21, -10.9602F, -20.1525F, 32.5F, 1, 1, 18, 0.0F, false));
        bone81.cubeList.add(new ModelBox(bone81, 70, 21, -2.0F, -14.1421F, 45.5F, 2, 2, 27, 0.0F, false));
        bone81.cubeList.add(new ModelBox(bone81, 70, 21, -2.0F, -14.1421F, 32.5F, 2, 2, 13, 0.0F, false));
        bone81.cubeList.add(new ModelBox(bone81, 64, 21, -12.3744F, -21.5668F, 167.5F, 1, 1, 3, 0.0F, false));
        bone81.cubeList.add(new ModelBox(bone81, 64, 21, -17.3241F, -16.617F, 167.5F, 1, 1, 3, 0.0F, false));
        bone81.cubeList.add(new ModelBox(bone81, 64, 21, -17.3241F, -16.2028F, 167.5F, 1, 1, 3, 0.0F, false));
        bone81.cubeList.add(new ModelBox(bone81, 64, 21, -11.9602F, -21.5668F, 167.5F, 1, 1, 3, 0.0F, false));
        bone81.cubeList.add(new ModelBox(bone81, 70, 21, -9.8995F, -6.2426F, 45.5F, 2, 2, 27, 0.0F, false));
        bone81.cubeList.add(new ModelBox(bone81, 70, 21, -9.8995F, -6.2426F, 32.5F, 2, 2, 13, 0.0F, false));
        bone81.cubeList.add(new ModelBox(bone81, 64, 21, -15.9099F, -15.2028F, 32.5F, 1, 1, 18, 0.0F, false));

        bone53 = new ModelRenderer(this);
        bone53.setRotationPoint(17.5F, -25.8867F, -16.0F);
        g36.addChild(bone53);
        setRotationAngle(bone53, 0.0F, 0.0F, 0.7854F);
        bone53.cubeList.add(new ModelBox(bone53, 72, 10, -30.3644F, -9.9796F, 27.0F, 2, 1, 26, 0.0F, false));

        bone54 = new ModelRenderer(this);
        bone54.setRotationPoint(-17.5F, -25.8867F, -16.0F);
        g36.addChild(bone54);
        setRotationAngle(bone54, 0.0F, 0.0F, -0.7854F);
        bone54.cubeList.add(new ModelBox(bone54, 72, 10, 28.3644F, -9.9796F, 27.0F, 2, 1, 26, 0.0F, true));

        bone25 = new ModelRenderer(this);
        bone25.setRotationPoint(4.0F, -25.5F, -18.25F);
        g36.addChild(bone25);
        setRotationAngle(bone25, 0.0F, 0.0F, -0.5236F);
        bone25.cubeList.add(new ModelBox(bone25, 73, 18, 12.817F, -25.9317F, 42.25F, 1, 1, 13, 0.0F, false));
        bone25.cubeList.add(new ModelBox(bone25, 64, 16, 12.549F, -22.4676F, 24.25F, 1, 2, 31, 0.0F, false));
        bone25.cubeList.add(new ModelBox(bone25, 64, 18, 12.549F, -22.4676F, -6.75F, 1, 2, 31, 0.0F, false));
        bone25.cubeList.add(new ModelBox(bone25, 64, 18, 12.549F, -22.4676F, -18.75F, 1, 2, 12, 0.0F, false));
        bone25.cubeList.add(new ModelBox(bone25, 73, 18, 14.116F, -25.1817F, 55.25F, 1, 1, 2, 0.0F, false));
        bone25.cubeList.add(new ModelBox(bone25, 73, 18, 12.9821F, -22.2176F, 55.25F, 1, 2, 2, 0.0F, false));
        bone25.cubeList.add(new ModelBox(bone25, 72, 10, 12.817F, -25.9317F, 29.25F, 1, 1, 13, 0.0F, false));
        bone25.cubeList.add(new ModelBox(bone25, 72, 10, 13.683F, -25.4317F, 24.25F, 1, 1, 5, 0.0F, false));

        bone26 = new ModelRenderer(this);
        bone26.setRotationPoint(-4.0F, -25.5F, -18.25F);
        g36.addChild(bone26);
        setRotationAngle(bone26, 0.0F, 0.0F, 0.5236F);
        bone26.cubeList.add(new ModelBox(bone26, 73, 18, -13.817F, -25.9317F, 42.25F, 1, 1, 13, 0.0F, true));
        bone26.cubeList.add(new ModelBox(bone26, 64, 16, -13.549F, -22.4676F, 24.25F, 1, 2, 31, 0.0F, true));
        bone26.cubeList.add(new ModelBox(bone26, 64, 16, -13.549F, -22.4676F, -6.75F, 1, 2, 31, 0.0F, true));
        bone26.cubeList.add(new ModelBox(bone26, 64, 16, -13.549F, -22.4676F, -18.75F, 1, 2, 12, 0.0F, true));
        bone26.cubeList.add(new ModelBox(bone26, 73, 18, -15.116F, -25.1817F, 55.25F, 1, 1, 2, 0.0F, true));
        bone26.cubeList.add(new ModelBox(bone26, 73, 18, -13.9821F, -22.2176F, 55.25F, 1, 2, 2, 0.0F, true));
        bone26.cubeList.add(new ModelBox(bone26, 72, 10, -13.817F, -25.9317F, 29.25F, 1, 1, 13, 0.0F, true));
        bone26.cubeList.add(new ModelBox(bone26, 72, 10, -14.683F, -25.4317F, 24.25F, 1, 1, 5, 0.0F, true));

        bone48 = new ModelRenderer(this);
        bone48.setRotationPoint(-3.9133F, -26.9609F, 0.8906F);
        g36.addChild(bone48);
        setRotationAngle(bone48, -0.3491F, 0.0F, 0.0F);
        bone48.cubeList.add(new ModelBox(bone48, 64, 24, -1.0039F, -39.0093F, 25.7726F, 2, 1, 1, 0.0F, false));

        bone93 = new ModelRenderer(this);
        bone93.setRotationPoint(-0.5F, 0.0F, 0.0F);
        g36.addChild(bone93);
        setRotationAngle(bone93, -0.3491F, 0.0F, 0.0F);
        bone93.cubeList.add(new ModelBox(bone93, 64, 23, -3.0F, -64.6868F, 40.3066F, 7, 4, 24, 0.0F, false));

        bone94 = new ModelRenderer(this);
        bone94.setRotationPoint(-0.5F, -14.0F, 0.1719F);
        g36.addChild(bone94);
        setRotationAngle(bone94, -0.7854F, 0.0F, 0.0F);
        bone94.cubeList.add(new ModelBox(bone94, 64, 23, -3.0F, -73.0104F, 35.4896F, 7, 4, 10, 0.0F, false));

        bone92 = new ModelRenderer(this);
        bone92.setRotationPoint(-0.5F, 0.0F, 0.0F);
        g36.addChild(bone92);
        setRotationAngle(bone92, -0.3491F, 0.0F, 0.0F);
        bone92.cubeList.add(new ModelBox(bone92, 64, 24, -3.0F, -67.841F, 16.8108F, 7, 4, 9, 0.0F, false));

        bone3 = new ModelRenderer(this);
        bone3.setRotationPoint(-0.5F, 0.0F, 0.0F);
        g36.addChild(bone3);
        setRotationAngle(bone3, 0.0F, 0.0F, -0.2618F);
        bone3.cubeList.add(new ModelBox(bone3, 64, 24, 16.1385F, -56.2343F, 37.0F, 1, 1, 2, 0.0F, false));

        bone4 = new ModelRenderer(this);
        bone4.setRotationPoint(0.5F, 0.0F, 0.0F);
        g36.addChild(bone4);
        setRotationAngle(bone4, 0.0F, 0.0F, 0.2618F);
        bone4.cubeList.add(new ModelBox(bone4, 64, 24, -17.1385F, -56.2343F, 37.0F, 1, 1, 2, 0.0F, true));

        bone55 = new ModelRenderer(this);
        bone55.setRotationPoint(0.0F, -28.5F, -80.0F);
        g36.addChild(bone55);
        setRotationAngle(bone55, -0.3491F, 0.0F, 0.0F);
        bone55.cubeList.add(new ModelBox(bone55, 64, 24, -4.0F, -43.7072F, 32.4742F, 8, 3, 3, 0.0F, false));
        bone55.cubeList.add(new ModelBox(bone55, 64, 24, -4.0F, -40.7072F, 32.4742F, 8, 3, 4, 0.0F, false));

        bone51 = new ModelRenderer(this);
        bone51.setRotationPoint(13.5F, -26.0F, -71.0F);
        g36.addChild(bone51);
        setRotationAngle(bone51, 0.0F, 0.0F, -0.2618F);
        bone51.cubeList.add(new ModelBox(bone51, 64, 24, -1.9634F, -33.2459F, 38.0F, 1, 4, 4, 0.0F, false));

        bone52 = new ModelRenderer(this);
        bone52.setRotationPoint(-13.5F, -26.0F, -71.0F);
        g36.addChild(bone52);
        setRotationAngle(bone52, 0.0F, 0.0F, 0.2618F);
        bone52.cubeList.add(new ModelBox(bone52, 64, 24, 0.9634F, -33.2459F, 38.0F, 1, 4, 4, 0.0F, true));

        bone41 = new ModelRenderer(this);
        bone41.setRotationPoint(0.0F, -26.0F, -52.0F);
        g36.addChild(bone41);
        setRotationAngle(bone41, 0.0F, -2.7925F, 0.0F);


        bone56 = new ModelRenderer(this);
        bone56.setRotationPoint(0.0F, -36.0F, -80.5F);
        g36.addChild(bone56);
        setRotationAngle(bone56, -0.4363F, 0.0F, 0.0F);
        bone56.cubeList.add(new ModelBox(bone56, 64, 24, -3.5F, -46.2962F, 33.0289F, 7, 5, 1, 0.0F, false));

        bone2 = new ModelRenderer(this);
        bone2.setRotationPoint(0.0F, -10.5F, -44.0F);
        g36.addChild(bone2);
        setRotationAngle(bone2, -0.7854F, 0.0F, 0.0F);
        bone2.cubeList.add(new ModelBox(bone2, 76, 27, 3.0F, -68.4074F, -3.3536F, 1, 3, 3, 0.0F, false));
        bone2.cubeList.add(new ModelBox(bone2, 76, 27, -4.0F, -68.4074F, -3.3536F, 1, 3, 3, 0.0F, true));
        bone2.cubeList.add(new ModelBox(bone2, 76, 27, -3.0F, -67.5896F, -3.5357F, 6, 1, 2, 0.0F, false));
        bone2.cubeList.add(new ModelBox(bone2, 64, 24, 3.0F, -67.4074F, -5.3536F, 1, 2, 2, 0.0F, false));
        bone2.cubeList.add(new ModelBox(bone2, 64, 24, -4.0F, -67.4074F, -5.3536F, 1, 2, 2, 0.0F, true));
        bone2.cubeList.add(new ModelBox(bone2, 64, 24, 2.0F, -60.6292F, -11.5459F, 2, 3, 2, 0.0F, false));
        bone2.cubeList.add(new ModelBox(bone2, 64, 24, -4.0F, -60.6292F, -11.5459F, 2, 3, 2, 0.0F, true));
        bone2.cubeList.add(new ModelBox(bone2, 64, 24, 2.0F, -54.6795F, -17.4957F, 2, 2, 3, 0.0F, false));
        bone2.cubeList.add(new ModelBox(bone2, 64, 24, -4.0F, -54.6795F, -17.4957F, 2, 2, 3, 0.0F, true));
        bone2.cubeList.add(new ModelBox(bone2, 64, 24, 3.0F, -46.4871F, -25.2739F, 1, 3, 3, 0.0F, false));
        bone2.cubeList.add(new ModelBox(bone2, 64, 24, -4.0F, -46.4871F, -25.2739F, 1, 3, 3, 0.0F, true));
        bone2.cubeList.add(new ModelBox(bone2, 64, 24, -3.0F, -46.6251F, -24.4119F, 6, 2, 1, 0.0F, false));
        bone2.cubeList.add(new ModelBox(bone2, 64, 24, -4.0F, -43.6084F, -27.2236F, 8, 3, 4, 0.0F, false));
        bone2.cubeList.add(new ModelBox(bone2, 64, 24, -4.0F, -45.6084F, -27.2236F, 8, 2, 2, 0.0F, false));
        bone2.cubeList.add(new ModelBox(bone2, 64, 24, 3.0F, -48.4871F, -24.2739F, 1, 2, 2, 0.0F, false));
        bone2.cubeList.add(new ModelBox(bone2, 64, 24, -4.0F, -48.4871F, -24.2739F, 1, 2, 2, 0.0F, true));

        bone6 = new ModelRenderer(this);
        bone6.setRotationPoint(0.0F, -39.9532F, -5.8632F);
        g36.addChild(bone6);
        setRotationAngle(bone6, -0.2618F, 0.0F, 0.0F);
        bone6.cubeList.add(new ModelBox(bone6, 64, 24, -3.5F, -34.6474F, 29.9129F, 7, 1, 2, 0.0F, false));
        bone6.cubeList.add(new ModelBox(bone6, 64, 24, -3.5F, -34.6474F, 28.9129F, 2, 1, 1, 0.0F, false));
        bone6.cubeList.add(new ModelBox(bone6, 64, 24, 1.5F, -34.6474F, 28.9129F, 2, 1, 1, 0.0F, false));

        bone5 = new ModelRenderer(this);
        bone5.setRotationPoint(0.0F, -36.2461F, -3.5703F);
        g36.addChild(bone5);
        setRotationAngle(bone5, 0.7854F, 0.0F, 0.0F);
        bone5.cubeList.add(new ModelBox(bone5, 64, 24, -3.5F, 6.7782F, 45.2548F, 7, 1, 1, 0.0F, false));
        bone5.cubeList.add(new ModelBox(bone5, 64, 24, -3.5F, 6.0711F, 45.9619F, 7, 1, 1, 0.0F, false));

        bone57 = new ModelRenderer(this);
        bone57.setRotationPoint(0.0F, -38.2141F, -68.4681F);
        g36.addChild(bone57);
        setRotationAngle(bone57, 0.0F, 0.0F, -0.3491F);
        bone57.cubeList.add(new ModelBox(bone57, 64, 24, 9.5334F, -25.7308F, 38.5F, 1, 1, 3, 0.0F, true));
        bone57.cubeList.add(new ModelBox(bone57, 64, 24, 9.5334F, -30.4021F, 38.5F, 1, 1, 3, 0.0F, true));

        bone58 = new ModelRenderer(this);
        bone58.setRotationPoint(0.0F, -38.2141F, -68.4681F);
        g36.addChild(bone58);
        setRotationAngle(bone58, 0.0F, 0.0F, -0.6981F);
        bone58.cubeList.add(new ModelBox(bone58, 64, 24, 18.3566F, -20.6368F, 38.5F, 1, 1, 3, 0.0F, true));
        bone58.cubeList.add(new ModelBox(bone58, 64, 24, 18.3566F, -25.308F, 38.5F, 1, 1, 3, 0.0F, true));

        bone59 = new ModelRenderer(this);
        bone59.setRotationPoint(0.0F, -38.2141F, -68.4681F);
        g36.addChild(bone59);
        setRotationAngle(bone59, 0.0F, 0.0F, -1.0472F);
        bone59.cubeList.add(new ModelBox(bone59, 64, 24, 24.9054F, -12.8322F, 38.5F, 1, 1, 3, 0.0F, true));
        bone59.cubeList.add(new ModelBox(bone59, 64, 24, 24.9054F, -17.5035F, 38.5F, 1, 1, 3, 0.0F, true));

        bone60 = new ModelRenderer(this);
        bone60.setRotationPoint(0.0F, -38.2141F, -68.4681F);
        g36.addChild(bone60);
        setRotationAngle(bone60, 0.0F, 0.0F, -1.3963F);
        bone60.cubeList.add(new ModelBox(bone60, 64, 24, 28.39F, -3.2584F, 38.5F, 1, 1, 3, 0.0F, true));
        bone60.cubeList.add(new ModelBox(bone60, 64, 24, 28.39F, -7.9297F, 38.5F, 1, 1, 3, 0.0F, true));

        bone61 = new ModelRenderer(this);
        bone61.setRotationPoint(0.0F, -38.2141F, -68.4681F);
        g36.addChild(bone61);
        setRotationAngle(bone61, 0.0F, 0.0F, -1.7453F);
        bone61.cubeList.add(new ModelBox(bone61, 64, 24, 28.39F, 6.9297F, 38.5F, 1, 1, 3, 0.0F, true));
        bone61.cubeList.add(new ModelBox(bone61, 64, 24, 28.39F, 2.2584F, 38.5F, 1, 1, 3, 0.0F, true));

        bone62 = new ModelRenderer(this);
        bone62.setRotationPoint(0.0F, -38.2141F, -68.4681F);
        g36.addChild(bone62);
        setRotationAngle(bone62, 0.0F, 0.0F, -2.0944F);
        bone62.cubeList.add(new ModelBox(bone62, 64, 24, 24.9054F, 16.5035F, 38.5F, 1, 1, 3, 0.0F, true));
        bone62.cubeList.add(new ModelBox(bone62, 64, 24, 24.9054F, 11.8322F, 38.5F, 1, 1, 3, 0.0F, true));

        bone63 = new ModelRenderer(this);
        bone63.setRotationPoint(0.0F, -38.2141F, -68.4681F);
        g36.addChild(bone63);
        setRotationAngle(bone63, 0.0F, 0.0F, -2.4435F);
        bone63.cubeList.add(new ModelBox(bone63, 64, 24, 18.3566F, 24.308F, 38.5F, 1, 1, 3, 0.0F, true));
        bone63.cubeList.add(new ModelBox(bone63, 64, 24, 18.3566F, 19.6368F, 38.5F, 1, 1, 3, 0.0F, true));

        bone64 = new ModelRenderer(this);
        bone64.setRotationPoint(0.0F, -38.2141F, -68.4681F);
        g36.addChild(bone64);
        setRotationAngle(bone64, 0.0F, 0.0F, -2.7925F);
        bone64.cubeList.add(new ModelBox(bone64, 64, 24, 9.5334F, 29.4021F, 38.5F, 1, 1, 3, 0.0F, true));
        bone64.cubeList.add(new ModelBox(bone64, 64, 24, 9.5334F, 24.7308F, 38.5F, 1, 1, 3, 0.0F, true));

        bone65 = new ModelRenderer(this);
        bone65.setRotationPoint(0.0F, -38.9368F, -75.9681F);
        g36.addChild(bone65);
        setRotationAngle(bone65, -0.3491F, 0.0F, 0.0F);
        bone65.cubeList.add(new ModelBox(bone65, 64, 24, -1.5F, -40.8058F, 34.6322F, 3, 3, 1, 0.0F, true));

        bone66 = new ModelRenderer(this);
        bone66.setRotationPoint(0.0F, -38.9368F, -75.9681F);
        g36.addChild(bone66);
        setRotationAngle(bone66, 0.3491F, 0.0F, 0.0F);
        bone66.cubeList.add(new ModelBox(bone66, 64, 24, -1.5F, -8.3139F, 53.6386F, 3, 3, 1, 0.0F, true));

        bone9 = new ModelRenderer(this);
        bone9.setRotationPoint(0.0F, -41.4368F, -13.4681F);
        g36.addChild(bone9);
        setRotationAngle(bone9, 0.0F, 0.0F, -0.5236F);
        bone9.cubeList.add(new ModelBox(bone9, 64, 24, 10.6387F, -23.2568F, 39.5F, 1, 1, 1, 0.0F, true));
        bone9.cubeList.add(new ModelBox(bone9, 64, 24, 14.6387F, -23.2568F, 39.5F, 1, 1, 1, 0.0F, true));

        bone12 = new ModelRenderer(this);
        bone12.setRotationPoint(0.0F, -41.4368F, -13.4681F);
        g36.addChild(bone12);
        setRotationAngle(bone12, 0.0F, 0.0F, -0.6981F);
        bone12.cubeList.add(new ModelBox(bone12, 64, 24, 14.3908F, -20.6296F, 39.5F, 1, 1, 1, 0.0F, true));
        bone12.cubeList.add(new ModelBox(bone12, 64, 24, 18.3908F, -20.6296F, 39.5F, 1, 1, 1, 0.0F, true));

        bone13 = new ModelRenderer(this);
        bone13.setRotationPoint(0.0F, -41.4368F, -13.4681F);
        g36.addChild(bone13);
        setRotationAngle(bone13, 0.0F, 0.0F, -0.8727F);
        bone13.cubeList.add(new ModelBox(bone13, 64, 24, 17.6296F, -17.3908F, 39.5F, 1, 1, 1, 0.0F, true));
        bone13.cubeList.add(new ModelBox(bone13, 64, 24, 21.6296F, -17.3908F, 39.5F, 1, 1, 1, 0.0F, true));

        bone14 = new ModelRenderer(this);
        bone14.setRotationPoint(0.0F, -41.4368F, -13.4681F);
        g36.addChild(bone14);
        setRotationAngle(bone14, 0.0F, 0.0F, -1.0472F);
        bone14.cubeList.add(new ModelBox(bone14, 64, 24, 20.2568F, -13.6387F, 39.5F, 1, 1, 1, 0.0F, true));
        bone14.cubeList.add(new ModelBox(bone14, 64, 24, 24.2568F, -13.6387F, 39.5F, 1, 1, 1, 0.0F, true));

        bone15 = new ModelRenderer(this);
        bone15.setRotationPoint(0.0F, -41.4368F, -13.4681F);
        g36.addChild(bone15);
        setRotationAngle(bone15, 0.0F, 0.0F, -1.2217F);
        bone15.cubeList.add(new ModelBox(bone15, 64, 24, 22.1926F, -9.4874F, 39.5F, 1, 1, 1, 0.0F, true));
        bone15.cubeList.add(new ModelBox(bone15, 64, 24, 26.1926F, -9.4874F, 39.5F, 1, 1, 1, 0.0F, true));

        bone16 = new ModelRenderer(this);
        bone16.setRotationPoint(0.0F, -41.4368F, -13.4681F);
        g36.addChild(bone16);
        setRotationAngle(bone16, 0.0F, 0.0F, -1.3963F);
        bone16.cubeList.add(new ModelBox(bone16, 64, 24, 23.3781F, -5.063F, 39.5F, 1, 1, 1, 0.0F, true));
        bone16.cubeList.add(new ModelBox(bone16, 64, 24, 27.3781F, -5.063F, 39.5F, 1, 1, 1, 0.0F, true));

        bone17 = new ModelRenderer(this);
        bone17.setRotationPoint(0.0F, -41.4368F, -13.4681F);
        g36.addChild(bone17);
        setRotationAngle(bone17, 0.0F, 0.0F, -2.0944F);
        bone17.cubeList.add(new ModelBox(bone17, 64, 24, 20.2568F, 12.6387F, 39.5F, 1, 1, 1, 0.0F, true));
        bone17.cubeList.add(new ModelBox(bone17, 64, 24, 24.2568F, 12.6387F, 39.5F, 1, 1, 1, 0.0F, true));

        bone20 = new ModelRenderer(this);
        bone20.setRotationPoint(0.0F, -41.4368F, -13.4681F);
        g36.addChild(bone20);
        setRotationAngle(bone20, 0.0F, 0.0F, -2.2689F);
        bone20.cubeList.add(new ModelBox(bone20, 64, 24, 17.6296F, 16.3908F, 39.5F, 1, 1, 1, 0.0F, true));
        bone20.cubeList.add(new ModelBox(bone20, 64, 24, 21.6296F, 16.3908F, 39.5F, 1, 1, 1, 0.0F, true));

        bone21 = new ModelRenderer(this);
        bone21.setRotationPoint(0.0F, -41.4368F, -13.4681F);
        g36.addChild(bone21);
        setRotationAngle(bone21, 0.0F, 0.0F, -2.4435F);
        bone21.cubeList.add(new ModelBox(bone21, 64, 24, 14.3908F, 19.6296F, 39.5F, 1, 1, 1, 0.0F, true));
        bone21.cubeList.add(new ModelBox(bone21, 64, 24, 18.3908F, 19.6296F, 39.5F, 1, 1, 1, 0.0F, true));

        bone22 = new ModelRenderer(this);
        bone22.setRotationPoint(0.0F, -41.4368F, -13.4681F);
        g36.addChild(bone22);
        setRotationAngle(bone22, 0.0F, 0.0F, -2.618F);
        bone22.cubeList.add(new ModelBox(bone22, 64, 24, 10.6387F, 22.2568F, 39.5F, 1, 1, 1, 0.0F, true));
        bone22.cubeList.add(new ModelBox(bone22, 64, 24, 14.6387F, 22.2568F, 39.5F, 1, 1, 1, 0.0F, true));

        bone23 = new ModelRenderer(this);
        bone23.setRotationPoint(0.0F, -41.4368F, -13.4681F);
        g36.addChild(bone23);
        setRotationAngle(bone23, 0.0F, 0.0F, -2.7925F);
        bone23.cubeList.add(new ModelBox(bone23, 64, 24, 6.4874F, 24.1926F, 39.5F, 1, 1, 1, 0.0F, true));
        bone23.cubeList.add(new ModelBox(bone23, 64, 24, 10.4874F, 24.1926F, 39.5F, 1, 1, 1, 0.0F, true));

        bone24 = new ModelRenderer(this);
        bone24.setRotationPoint(0.0F, -41.4368F, -13.4681F);
        g36.addChild(bone24);
        setRotationAngle(bone24, 0.0F, 0.0F, -2.9671F);
        bone24.cubeList.add(new ModelBox(bone24, 64, 24, 2.063F, 25.3781F, 39.5F, 1, 1, 1, 0.0F, true));
        bone24.cubeList.add(new ModelBox(bone24, 64, 24, 6.063F, 25.3781F, 39.5F, 1, 1, 1, 0.0F, true));

        bone18 = new ModelRenderer(this);
        bone18.setRotationPoint(0.0F, -41.4368F, -13.4681F);
        g36.addChild(bone18);
        setRotationAngle(bone18, 0.0F, 0.0F, -1.7453F);
        bone18.cubeList.add(new ModelBox(bone18, 64, 24, 23.3781F, 4.063F, 39.5F, 1, 1, 1, 0.0F, true));
        bone18.cubeList.add(new ModelBox(bone18, 64, 24, 27.3781F, 4.063F, 39.5F, 1, 1, 1, 0.0F, true));

        bone19 = new ModelRenderer(this);
        bone19.setRotationPoint(0.0F, -41.4368F, -13.4681F);
        g36.addChild(bone19);
        setRotationAngle(bone19, 0.0F, 0.0F, -1.9199F);
        bone19.cubeList.add(new ModelBox(bone19, 64, 24, 22.1926F, 8.4874F, 39.5F, 1, 1, 1, 0.0F, true));
        bone19.cubeList.add(new ModelBox(bone19, 64, 24, 26.1926F, 8.4874F, 39.5F, 1, 1, 1, 0.0F, true));

        bone10 = new ModelRenderer(this);
        bone10.setRotationPoint(0.0F, -41.4368F, -13.4681F);
        g36.addChild(bone10);
        setRotationAngle(bone10, 0.0F, 0.0F, -0.1745F);
        bone10.cubeList.add(new ModelBox(bone10, 64, 24, 2.063F, -26.3781F, 39.5F, 1, 1, 1, 0.0F, true));
        bone10.cubeList.add(new ModelBox(bone10, 64, 24, 6.063F, -26.3781F, 39.5F, 1, 1, 1, 0.0F, true));

        bone11 = new ModelRenderer(this);
        bone11.setRotationPoint(0.0F, -41.4368F, -13.4681F);
        g36.addChild(bone11);
        setRotationAngle(bone11, 0.0F, 0.0F, -0.3491F);
        bone11.cubeList.add(new ModelBox(bone11, 64, 24, 6.4874F, -25.1926F, 39.5F, 1, 1, 1, 0.0F, true));
        bone11.cubeList.add(new ModelBox(bone11, 64, 24, 10.4874F, -25.1926F, 39.5F, 1, 1, 1, 0.0F, true));

        bone7 = new ModelRenderer(this);
        bone7.setRotationPoint(0.0F, -39.1779F, -9.6647F);
        g36.addChild(bone7);
        setRotationAngle(bone7, 0.0F, -0.7854F, 0.0F);
        bone7.cubeList.add(new ModelBox(bone7, 91, 35, 27.2843F, -26.9F, 27.2843F, 2, 1, 2, 0.0F, true));

        bone84 = new ModelRenderer(this);
        bone84.setRotationPoint(0.0F, -39.1779F, -9.6647F);
        g36.addChild(bone84);
        setRotationAngle(bone84, 0.0F, -1.1345F, 0.0F);
        bone84.cubeList.add(new ModelBox(bone84, 91, 35, 35.2523F, -26.9F, 15.9047F, 2, 1, 2, 0.0F, true));

        bone85 = new ModelRenderer(this);
        bone85.setRotationPoint(0.0F, -39.1779F, -9.6647F);
        g36.addChild(bone85);
        setRotationAngle(bone85, 0.0F, -1.4835F, 0.0F);
        bone85.cubeList.add(new ModelBox(bone85, 91, 35, 38.8478F, -26.9F, 2.4862F, 2, 1, 2, 0.0F, true));

        bone86 = new ModelRenderer(this);
        bone86.setRotationPoint(0.0F, -39.1779F, -9.6647F);
        g36.addChild(bone86);
        setRotationAngle(bone86, 0.0F, -1.8326F, 0.0F);
        bone86.cubeList.add(new ModelBox(bone86, 91, 35, 37.637F, -26.9F, -11.3528F, 2, 1, 2, 0.0F, true));

        bone87 = new ModelRenderer(this);
        bone87.setRotationPoint(0.0F, -39.1779F, -9.6647F);
        g36.addChild(bone87);
        setRotationAngle(bone87, 0.0F, -2.1817F, 0.0F);
        bone87.cubeList.add(new ModelBox(bone87, 91, 35, 31.7661F, -26.9F, -23.9431F, 2, 1, 2, 0.0F, true));

        bone88 = new ModelRenderer(this);
        bone88.setRotationPoint(0.0F, -39.1779F, -9.6647F);
        g36.addChild(bone88);
        setRotationAngle(bone88, 0.0F, -2.5307F, 0.0F);
        bone88.cubeList.add(new ModelBox(bone88, 91, 35, 21.9431F, -26.9F, -33.7661F, 2, 1, 2, 0.0F, true));

        bone89 = new ModelRenderer(this);
        bone89.setRotationPoint(0.0F, -39.1779F, -9.6647F);
        g36.addChild(bone89);
        setRotationAngle(bone89, 0.0F, -2.8798F, 0.0F);
        bone89.cubeList.add(new ModelBox(bone89, 91, 35, 9.3528F, -26.9F, -39.637F, 2, 1, 2, 0.0F, true));

        bone90 = new ModelRenderer(this);
        bone90.setRotationPoint(0.0F, -39.1779F, -9.6647F);
        g36.addChild(bone90);
        setRotationAngle(bone90, 0.0F, 3.0543F, 0.0F);
        bone90.cubeList.add(new ModelBox(bone90, 91, 35, -4.4862F, -26.9F, -40.8478F, 2, 1, 2, 0.0F, true));

        bone91 = new ModelRenderer(this);
        bone91.setRotationPoint(0.0F, -39.1779F, -9.6647F);
        g36.addChild(bone91);
        setRotationAngle(bone91, 0.0F, 2.7053F, 0.0F);
        bone91.cubeList.add(new ModelBox(bone91, 91, 35, -17.9047F, -26.9F, -37.2523F, 2, 1, 2, 0.0F, true));

        bone8 = new ModelRenderer(this);
        bone8.setRotationPoint(-4.0F, -39.0508F, -9.9414F);
        g36.addChild(bone8);
        setRotationAngle(bone8, -0.5236F, 0.0F, 0.0F);
        bone8.cubeList.add(new ModelBox(bone8, 77, 20, 7.5F, -44.8336F, 18.6542F, 1, 3, 8, 0.0F, false));
        bone8.cubeList.add(new ModelBox(bone8, 77, 20, -0.5F, -44.8336F, 18.6542F, 1, 3, 8, 0.0F, true));

        magazine = new ModelRenderer(this);
        magazine.setRotationPoint(-30.0F, 21.5F, 0.5F);
        setRotationAngle(magazine, -0.1745F, 0.0F, 0.0F);
        magazine.cubeList.add(new ModelBox(magazine, 0, 10, 27.5F, -40.0898F, -12.1885F, 5, 13, 1, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 8, 3, 32.5F, -42.0898F, -7.1885F, 2, 15, 5, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 8, 3, 25.5F, -42.0898F, -7.1885F, 2, 15, 5, 0.0F, true));
        magazine.cubeList.add(new ModelBox(magazine, 8, 3, 32.5F, -42.0898F, -12.1885F, 1, 15, 5, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 8, 3, 26.5F, -42.0898F, -12.1885F, 1, 15, 5, 0.0F, true));
        magazine.cubeList.add(new ModelBox(magazine, 8, 3, 33.5F, -42.0898F, -12.1885F, 1, 15, 3, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 8, 3, 25.5F, -42.0898F, -12.1885F, 1, 15, 3, 0.0F, true));
        magazine.cubeList.add(new ModelBox(magazine, 0, 2, 26.5F, -24.0898F, -12.1885F, 4, 3, 10, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 0, 2, 26.5F, -27.0898F, -12.1885F, 4, 3, 10, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 0, 2, 25.5F, -27.0898F, -7.1885F, 1, 5, 5, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 39, 42, 25.2461F, -28.6211F, -5.1885F, 1, 3, 3, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 39, 42, 33.7539F, -28.6211F, -5.1885F, 1, 3, 3, 0.0F, true));
        magazine.cubeList.add(new ModelBox(magazine, 0, 2, 33.5F, -27.0898F, -7.1885F, 1, 5, 5, 0.0F, true));
        magazine.cubeList.add(new ModelBox(magazine, 0, 2, 25.5F, -27.0898F, -12.1885F, 1, 4, 3, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 0, 2, 33.5F, -27.0898F, -12.1885F, 1, 4, 3, 0.0F, true));
        magazine.cubeList.add(new ModelBox(magazine, 0, 2, 30.5F, -24.0898F, -12.1885F, 3, 3, 9, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 0, 2, 30.5F, -27.0898F, -12.1885F, 3, 3, 9, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 0, 2, 25.5F, -24.0898F, -2.1885F, 5, 3, 9, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 0, 2, 25.5F, -27.0898F, -2.1885F, 5, 3, 9, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 0, 2, 30.5F, -27.0898F, -3.1885F, 4, 3, 10, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 0, 2, 30.5F, -24.0898F, -3.1885F, 4, 3, 10, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 8, 3, 32.5F, -42.0898F, -2.1885F, 2, 15, 9, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 8, 4, 25.5F, -42.0898F, -2.1885F, 2, 15, 9, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 0, 10, 27.5F, -41.0898F, 4.8115F, 5, 14, 2, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 6, 489, 27.5F, -43.0898F, 3.8115F, 5, 5, 2, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 0, 486, 27.5F, -43.0898F, -6.1885F, 5, 5, 9, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 6, 489, 28.5F, -42.0898F, -11.1885F, 3, 3, 2, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 6, 489, 29.0F, -43.0898F, -9.1885F, 2, 5, 3, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 6, 489, 27.5F, -41.5898F, -9.1885F, 5, 2, 3, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 6, 489, 28.0F, -42.5898F, 2.8115F, 4, 4, 1, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 6, 489, 28.0F, -42.5898F, -9.1885F, 4, 4, 3, 0.0F, false));

        bone103 = new ModelRenderer(this);
        bone103.setRotationPoint(4.5F, 0.5F, -0.5F);
        magazine.addChild(bone103);
        setRotationAngle(bone103, -0.2618F, 0.0F, 0.0F);
        bone103.cubeList.add(new ModelBox(bone103, 0, 2, 26.0F, -22.7465F, -17.5255F, 3, 3, 9, 0.0F, false));
        bone103.cubeList.add(new ModelBox(bone103, 0, 2, 26.0F, -19.7465F, -17.5255F, 3, 3, 9, 0.0F, false));
        bone103.cubeList.add(new ModelBox(bone103, 0, 2, 26.0F, -16.7465F, -17.5255F, 3, 3, 9, 0.0F, false));
        bone103.cubeList.add(new ModelBox(bone103, 0, 2, 26.0F, -13.7465F, -17.5255F, 3, 3, 9, 0.0F, false));
        bone103.cubeList.add(new ModelBox(bone103, 0, 2, 26.0F, -10.7465F, -17.5255F, 3, 5, 9, 0.0F, false));
        bone103.cubeList.add(new ModelBox(bone103, 0, 2, 20.5F, -5.7465F, -4.0255F, 10, 1, 6, 0.0F, false));
        bone103.cubeList.add(new ModelBox(bone103, 0, 2, 20.5F, -5.7465F, -10.0255F, 10, 1, 6, 0.0F, false));
        bone103.cubeList.add(new ModelBox(bone103, 0, 2, 20.5F, -5.7465F, -16.0255F, 10, 1, 6, 0.0F, false));
        bone103.cubeList.add(new ModelBox(bone103, 0, 2, 20.5F, -5.7465F, -18.0255F, 10, 1, 2, 0.0F, false));
        bone103.cubeList.add(new ModelBox(bone103, 0, 2, 26.0F, -22.7465F, -8.5255F, 4, 3, 10, 0.0F, false));
        bone103.cubeList.add(new ModelBox(bone103, 0, 2, 26.0F, -19.7465F, -8.5255F, 4, 3, 10, 0.0F, false));
        bone103.cubeList.add(new ModelBox(bone103, 0, 2, 26.0F, -16.7465F, -8.5255F, 4, 3, 10, 0.0F, false));
        bone103.cubeList.add(new ModelBox(bone103, 0, 2, 26.0F, -13.7465F, -8.5255F, 4, 3, 10, 0.0F, false));
        bone103.cubeList.add(new ModelBox(bone103, 0, 2, 26.0F, -10.7465F, -8.5255F, 4, 5, 10, 0.0F, false));
        bone103.cubeList.add(new ModelBox(bone103, 0, 2, 21.0F, -22.7465F, -7.5255F, 5, 3, 9, 0.0F, false));
        bone103.cubeList.add(new ModelBox(bone103, 0, 2, 21.0F, -19.7465F, -7.5255F, 5, 3, 9, 0.0F, false));
        bone103.cubeList.add(new ModelBox(bone103, 0, 2, 21.0F, -16.7465F, -7.5255F, 5, 3, 9, 0.0F, false));
        bone103.cubeList.add(new ModelBox(bone103, 0, 2, 21.0F, -13.7465F, -7.5255F, 5, 3, 9, 0.0F, false));
        bone103.cubeList.add(new ModelBox(bone103, 0, 2, 21.0F, -10.7465F, -7.5255F, 5, 5, 9, 0.0F, false));
        bone103.cubeList.add(new ModelBox(bone103, 0, 2, 22.0F, -22.7465F, -17.5255F, 4, 3, 10, 0.0F, false));
        bone103.cubeList.add(new ModelBox(bone103, 0, 2, 22.0F, -19.7465F, -17.5255F, 4, 3, 10, 0.0F, false));
        bone103.cubeList.add(new ModelBox(bone103, 0, 2, 22.0F, -16.7465F, -17.5255F, 4, 3, 10, 0.0F, false));
        bone103.cubeList.add(new ModelBox(bone103, 0, 2, 22.0F, -13.7465F, -17.5255F, 4, 3, 10, 0.0F, false));
        bone103.cubeList.add(new ModelBox(bone103, 0, 2, 21.0F, -20.7465F, -17.5255F, 1, 15, 3, 0.0F, false));
        bone103.cubeList.add(new ModelBox(bone103, 0, 2, 29.0F, -20.7465F, -17.5255F, 1, 15, 3, 0.0F, true));
        bone103.cubeList.add(new ModelBox(bone103, 0, 2, 21.0F, -21.7465F, -12.5255F, 1, 16, 5, 0.0F, false));
        bone103.cubeList.add(new ModelBox(bone103, 0, 2, 29.0F, -21.7465F, -12.5255F, 1, 16, 5, 0.0F, true));
        bone103.cubeList.add(new ModelBox(bone103, 44, 45, 29.2539F, -14.5239F, -10.5255F, 1, 3, 3, 0.0F, true));
        bone103.cubeList.add(new ModelBox(bone103, 44, 45, 20.7461F, -14.5239F, -10.5255F, 1, 3, 3, 0.0F, false));
        bone103.cubeList.add(new ModelBox(bone103, 0, 2, 22.0F, -10.7465F, -17.5255F, 4, 5, 10, 0.0F, false));

        bone101 = new ModelRenderer(this);
        bone101.setRotationPoint(4.5F, 0.5F, -0.5F);
        magazine.addChild(bone101);
        setRotationAngle(bone101, 0.0F, 0.0F, 0.1745F);
        bone101.cubeList.add(new ModelBox(bone101, 0, 10, 13.2855F, -48.5884F, -1.6885F, 2, 3, 9, 0.0F, false));
        bone101.cubeList.add(new ModelBox(bone101, 0, 10, 13.2855F, -48.5884F, -11.6885F, 2, 3, 10, 0.0F, false));

        bone102 = new ModelRenderer(this);
        bone102.setRotationPoint(-4.5F, 0.5F, -0.5F);
        magazine.addChild(bone102);
        setRotationAngle(bone102, 0.0F, 0.0F, -0.1745F);
        bone102.cubeList.add(new ModelBox(bone102, 0, 10, 43.8033F, -38.1714F, -1.6885F, 2, 3, 9, 0.0F, true));
        bone102.cubeList.add(new ModelBox(bone102, 0, 10, 43.8033F, -38.1714F, -11.6885F, 2, 3, 10, 0.0F, true));

        charging_handle = new ModelRenderer(this);
        charging_handle.setRotationPoint(-1.0F, 24.0F, 0.0F);
        charging_handle.cubeList.add(new ModelBox(charging_handle, 33, 8, -1.0F, -54.0F, -11.0F, 4, 1, 2, 0.0F, false));
        charging_handle.cubeList.add(new ModelBox(charging_handle, 33, 8, 4.0F, -55.0F, -13.5F, 1, 2, 2, 0.0F, false));
        charging_handle.cubeList.add(new ModelBox(charging_handle, 33, 8, 2.0F, -55.0F, -13.5F, 2, 1, 2, 0.0F, false));
        charging_handle.cubeList.add(new ModelBox(charging_handle, 33, 8, 7.0F, -55.0F, -13.5F, 1, 2, 1, 0.0F, false));
        charging_handle.cubeList.add(new ModelBox(charging_handle, 33, 8, 5.0F, -55.0F, -12.5F, 2, 2, 1, 0.0F, false));
        charging_handle.cubeList.add(new ModelBox(charging_handle, 33, 8, -1.0F, -55.0F, -9.0F, 4, 2, 9, 0.0F, false));
        charging_handle.cubeList.add(new ModelBox(charging_handle, 33, 8, 5.5F, -55.0F, -13.1047F, 1, 2, 1, 0.0F, false));
        charging_handle.cubeList.add(new ModelBox(charging_handle, 42, 14, -1.0F, -55.0F, -13.0F, 4, 2, 2, 0.0F, false));

        bone46 = new ModelRenderer(this);
        bone46.setRotationPoint(7.5F, -26.0F, -51.0F);
        charging_handle.addChild(bone46);
        setRotationAngle(bone46, 0.0F, -0.7854F, 0.0F);
        bone46.cubeList.add(new ModelBox(bone46, 33, 8, 26.5772F, -29.0F, 27.2843F, 1, 2, 1, 0.0F, false));
        bone46.cubeList.add(new ModelBox(bone46, 33, 8, 26.5772F, -29.0F, 26.8701F, 1, 2, 1, 0.0F, false));

        bone45 = new ModelRenderer(this);
        bone45.setRotationPoint(-4.0F, -26.0F, -52.3F);
        charging_handle.addChild(bone45);
        setRotationAngle(bone45, 0.0F, -0.4363F, 0.0F);
        bone45.cubeList.add(new ModelBox(bone45, 33, 8, 24.5544F, -29.0F, 31.3612F, 1, 2, 1, 0.0F, true));

        bone43 = new ModelRenderer(this);
        bone43.setRotationPoint(1.0F, -27.0F, -50.5F);
        charging_handle.addChild(bone43);
        setRotationAngle(bone43, -0.4363F, 0.0F, 0.0F);
        bone43.cubeList.add(new ModelBox(bone43, 33, 8, -2.0F, -42.8246F, 25.8207F, 4, 1, 1, 0.0F, false));

        bone29 = new ModelRenderer(this);
        bone29.setRotationPoint(1.0F, -26.0F, -52.0F);
        charging_handle.addChild(bone29);
        setRotationAngle(bone29, 0.0F, -0.6981F, 0.0F);
        bone29.cubeList.add(new ModelBox(bone29, 42, 14, 23.7115F, -29.0F, 29.6418F, 4, 2, 2, 0.0F, false));

        bone47 = new ModelRenderer(this);
        bone47.setRotationPoint(4.5F, -26.0F, -52.5F);
        charging_handle.addChild(bone47);
        setRotationAngle(bone47, 0.0F, 0.0F, -0.4363F);
        bone47.cubeList.add(new ModelBox(bone47, 33, 8, 10.9998F, -25.7723F, 39.0F, 1, 1, 2, 0.0F, false));

        bone42 = new ModelRenderer(this);
        bone42.setRotationPoint(1.0F, -26.0F, -52.0F);
        charging_handle.addChild(bone42);
        setRotationAngle(bone42, 0.0F, -2.9671F, 0.0F);
        bone42.cubeList.add(new ModelBox(bone42, 42, 14, 4.9459F, -29.0F, -40.3923F, 4, 2, 2, 0.0F, false));

        bone44 = new ModelRenderer(this);
        bone44.setRotationPoint(6.0F, -26.0F, -52.3F);
        charging_handle.addChild(bone44);
        setRotationAngle(bone44, 0.0F, 0.4363F, 0.0F);
        bone44.cubeList.add(new ModelBox(bone44, 33, 8, -16.4913F, -29.0F, 35.5874F, 1, 2, 1, 0.0F, false));

        bone36 = new ModelRenderer(this);
        bone36.setRotationPoint(1.0F, -26.0F, -52.0F);
        charging_handle.addChild(bone36);
        setRotationAngle(bone36, 0.0F, -1.9199F, 0.0F);
        bone36.cubeList.add(new ModelBox(bone36, 42, 14, 35.5877F, -29.0F, -14.6808F, 4, 2, 2, 0.0F, false));

        bone35 = new ModelRenderer(this);
        bone35.setRotationPoint(1.0F, -26.0F, -52.0F);
        charging_handle.addChild(bone35);
        setRotationAngle(bone35, 0.0F, -1.7453F, 0.0F);
        bone35.cubeList.add(new ModelBox(bone35, 42, 14, 37.3923F, -29.0F, -7.9459F, 4, 2, 2, 0.0F, false));

        bone34 = new ModelRenderer(this);
        bone34.setRotationPoint(1.0F, -26.0F, -52.0F);
        charging_handle.addChild(bone34);
        setRotationAngle(bone34, 0.0F, -1.5708F, 0.0F);
        bone34.cubeList.add(new ModelBox(bone34, 42, 14, 38.0F, -29.0F, -1.0F, 4, 2, 2, 0.0F, false));

        bone31 = new ModelRenderer(this);
        bone31.setRotationPoint(1.0F, -26.0F, -52.0F);
        charging_handle.addChild(bone31);
        setRotationAngle(bone31, 0.0F, -1.0472F, 0.0F);
        bone31.cubeList.add(new ModelBox(bone31, 42, 14, 32.641F, -29.0F, 19.0F, 4, 2, 2, 0.0F, false));

        bone37 = new ModelRenderer(this);
        bone37.setRotationPoint(1.0F, -26.0F, -52.0F);
        charging_handle.addChild(bone37);
        setRotationAngle(bone37, 0.0F, -2.0944F, 0.0F);
        bone37.cubeList.add(new ModelBox(bone37, 42, 14, 32.641F, -29.0F, -21.0F, 4, 2, 2, 0.0F, false));

        bone39 = new ModelRenderer(this);
        bone39.setRotationPoint(1.0F, -26.0F, -52.0F);
        charging_handle.addChild(bone39);
        setRotationAngle(bone39, 0.0F, -2.4435F, 0.0F);
        bone39.cubeList.add(new ModelBox(bone39, 42, 14, 23.7115F, -29.0F, -31.6418F, 4, 2, 2, 0.0F, false));

        bone40 = new ModelRenderer(this);
        bone40.setRotationPoint(1.0F, -26.0F, -52.0F);
        charging_handle.addChild(bone40);
        setRotationAngle(bone40, 0.0F, -2.618F, 0.0F);
        bone40.cubeList.add(new ModelBox(bone40, 42, 14, 18.0F, -29.0F, -35.641F, 4, 2, 2, 0.0F, false));

        bone104 = new ModelRenderer(this);
        bone104.setRotationPoint(1.0F, -26.0F, -52.0F);
        charging_handle.addChild(bone104);
        setRotationAngle(bone104, 0.0F, -2.618F, 0.0F);
        bone104.cubeList.add(new ModelBox(bone104, 42, 14, 18.0F, -29.0F, -35.641F, 4, 2, 2, 0.0F, false));

        bone32 = new ModelRenderer(this);
        bone32.setRotationPoint(1.0F, -26.0F, -52.0F);
        charging_handle.addChild(bone32);
        setRotationAngle(bone32, 0.0F, -1.2217F, 0.0F);
        bone32.cubeList.add(new ModelBox(bone32, 42, 14, 35.5877F, -29.0F, 12.6808F, 4, 2, 2, 0.0F, false));

        bone30 = new ModelRenderer(this);
        bone30.setRotationPoint(1.0F, -26.0F, -52.0F);
        charging_handle.addChild(bone30);
        setRotationAngle(bone30, 0.0F, -0.8727F, 0.0F);
        bone30.cubeList.add(new ModelBox(bone30, 42, 14, 28.6418F, -29.0F, 24.7115F, 4, 2, 2, 0.0F, false));

        bone28 = new ModelRenderer(this);
        bone28.setRotationPoint(1.0F, -26.0F, -52.0F);
        charging_handle.addChild(bone28);
        setRotationAngle(bone28, 0.0F, -0.5236F, 0.0F);
        bone28.cubeList.add(new ModelBox(bone28, 42, 14, 18.0F, -29.0F, 33.641F, 4, 2, 2, 0.0F, false));

        bone38 = new ModelRenderer(this);
        bone38.setRotationPoint(1.0F, -26.0F, -52.0F);
        charging_handle.addChild(bone38);
        setRotationAngle(bone38, 0.0F, -2.2689F, 0.0F);
        bone38.cubeList.add(new ModelBox(bone38, 42, 14, 28.6418F, -29.0F, -26.7115F, 4, 2, 2, 0.0F, false));

        bone = new ModelRenderer(this);
        bone.setRotationPoint(1.0F, -26.0F, -52.0F);
        charging_handle.addChild(bone);
        setRotationAngle(bone, 0.0F, -0.1745F, 0.0F);
        bone.cubeList.add(new ModelBox(bone, 42, 14, 4.9459F, -29.0F, 38.3923F, 4, 2, 2, 0.0F, false));

        bone33 = new ModelRenderer(this);
        bone33.setRotationPoint(1.0F, -26.0F, -52.0F);
        charging_handle.addChild(bone33);
        setRotationAngle(bone33, 0.0F, -1.3963F, 0.0F);
        bone33.cubeList.add(new ModelBox(bone33, 42, 14, 37.3923F, -29.0F, 5.9459F, 4, 2, 2, 0.0F, false));

        bone27 = new ModelRenderer(this);
        bone27.setRotationPoint(1.0F, -26.0F, -52.0F);
        charging_handle.addChild(bone27);
        setRotationAngle(bone27, 0.0F, -0.3491F, 0.0F);
        bone27.cubeList.add(new ModelBox(bone27, 42, 14, 11.6808F, -29.0F, 36.5877F, 4, 2, 2, 0.0F, false));

        addEntry(AnimationElement.MAGAZINE, stack -> magazine);
        addEntry(AnimationElement.CHARGING, stack -> charging_handle);
    }
}

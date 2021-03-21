package dev.toma.pubgmc.client.models.weapons;

import dev.toma.pubgmc.client.util.ModelTransformationHelper;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.item.ItemStack;

public class ModelS12K extends ModelGun {

    private final ModelRenderer charging_handle;
    private final ModelRenderer bone24;
    private final ModelRenderer magazine;
    private final ModelRenderer bone2;
    private final ModelRenderer bone4;
    private final ModelRenderer bone5;
    private final ModelRenderer bone3;
    private final ModelRenderer s12k;
    private final ModelRenderer bone38;
    private final ModelRenderer bone25;
    private final ModelRenderer bone26;
    private final ModelRenderer bone32;
    private final ModelRenderer bone33;
    private final ModelRenderer bone;
    private final ModelRenderer bone39;
    private final ModelRenderer bone29;
    private final ModelRenderer bone30;
    private final ModelRenderer bone31;
    private final ModelRenderer bone19;
    private final ModelRenderer bone20;
    private final ModelRenderer bone18;
    private final ModelRenderer bone21;
    private final ModelRenderer bone22;
    private final ModelRenderer bone15;
    private final ModelRenderer bone14;
    private final ModelRenderer bone16;
    private final ModelRenderer bone17;
    private final ModelRenderer bone12;
    private final ModelRenderer bone13;
    private final ModelRenderer bone10;
    private final ModelRenderer bone27;
    private final ModelRenderer bone11;
    private final ModelRenderer bone28;
    private final ModelRenderer bone6;
    private final ModelRenderer bone8;
    private final ModelRenderer bone9;
    private final ModelRenderer bone7;
    private final ModelRenderer ironsights;
    private final ModelRenderer bone37;
    private final ModelRenderer bone36;
    private final ModelRenderer bone34;
    private final ModelRenderer bone35;
    private final ModelRenderer bone41;
    private final ModelRenderer bone42;
    private final ModelRenderer bone40;

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }

    @Override
    public void renderModel(ItemStack stack) {
        s12k.render(1f);
        magazine.render(1f);
        charging_handle.render(1.0F);
        if(hasScopeAtachment(stack))
            return;
        ironsights.render(1f);
    }

    private void renderWeapon(ItemStack stack) {
        GlStateManager.pushMatrix();
        {
            ModelTransformationHelper.defaultShotgunTransform();
            GlStateManager.translate(-0.65000033, -1.0, -9.0);
            GlStateManager.rotate(180, 0, 1, 0);
        }
        GlStateManager.popMatrix();

        /*renderRedDot(3.525, 13.4, 14, 0.7F, stack);
        renderHolo(1, 7.075,  1, 0.8F, stack);
        renderScope2X(1.15, 4, 5, 0.9F, stack);
        renderScope4X(2, 6, 1, 0.9F, stack);*/
    }

    public ModelS12K() {
        textureWidth = 512;
        textureHeight = 512;

        charging_handle = new ModelRenderer(this);
        charging_handle.setRotationPoint(-3.4F, 17.0F, 6.6F);
        charging_handle.cubeList.add(new ModelBox(charging_handle, 102, 146, 5.6F, -1.0F, -9.2F, 1, 2, 10, 0.0F, true));

        bone24 = new ModelRenderer(this);
        bone24.setRotationPoint(6.8F, 0.0F, 0.0F);
        charging_handle.addChild(bone24);
        setRotationAngle(bone24, 0.0F, 0.3491F, 0.0F);
        bone24.cubeList.add(new ModelBox(bone24, 102, 146, -0.5F, -0.8F, -0.5F, 2, 1, 1, 0.0F, true));

        magazine = new ModelRenderer(this);
        magazine.setRotationPoint(0.0F, 21.0F, 0.0F);
        magazine.cubeList.add(new ModelBox(magazine, 1, 502, -1.5F, -4.0F, -3.1F, 3, 4, 2, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 0, 461, -1.5F, -4.0F, -0.5F, 3, 4, 8, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 1, 502, -2.0F, -3.5F, -3.1F, 4, 3, 2, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 0, 0, -3.0F, -4.5F, -4.3F, 1, 4, 13, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 0, 0, -3.0F, -0.5F, -4.3F, 6, 3, 3, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 0, 0, -2.5F, 7.4802F, -3.1876F, 5, 1, 2, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 0, 0, -3.0F, -0.5F, -1.3F, 6, 5, 10, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 0, 0, 2.0F, -4.5F, -4.3F, 1, 4, 13, 0.0F, true));
        magazine.cubeList.add(new ModelBox(magazine, 0, 0, -2.0F, -3.5F, 7.7F, 4, 3, 1, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 0, 0, -2.0F, -3.5F, -4.3F, 4, 3, 1, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 0, 461, -2.0F, -3.5F, -0.5F, 4, 3, 8, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 1, 509, -1.5F, -3.5F, -1.4F, 3, 3, 1, 0.0F, false));

        bone2 = new ModelRenderer(this);
        bone2.setRotationPoint(5.0F, 0.0F, 3.0F);
        magazine.addChild(bone2);
        setRotationAngle(bone2, 0.3491F, 0.0F, 0.0F);
        bone2.cubeList.add(new ModelBox(bone2, 0, 0, -8.0F, -0.1475F, -7.7148F, 6, 7, 7, 0.0F, false));
        bone2.cubeList.add(new ModelBox(bone2, 0, 0, -7.5F, 3.8525F, -8.7148F, 5, 2, 1, 0.0F, false));
        bone2.cubeList.add(new ModelBox(bone2, 0, 0, -8.0F, 1.8525F, -0.7148F, 6, 5, 6, 0.0F, false));
        bone2.cubeList.add(new ModelBox(bone2, 0, 0, -8.0F, 6.8525F, 3.2852F, 6, 12, 2, 0.0F, false));
        bone2.cubeList.add(new ModelBox(bone2, 0, 0, -7.0F, -1.1475F, 5.2852F, 4, 20, 1, 0.0F, false));
        bone2.cubeList.add(new ModelBox(bone2, 0, 0, -8.0F, 11.8525F, -5.7148F, 6, 1, 9, 0.0F, false));
        bone2.cubeList.add(new ModelBox(bone2, 0, 0, -8.0F, 17.8525F, -5.7148F, 6, 1, 9, 0.0F, false));
        bone2.cubeList.add(new ModelBox(bone2, 0, 0, -8.0F, 6.8525F, 0.2852F, 6, 5, 1, 0.0F, false));
        bone2.cubeList.add(new ModelBox(bone2, 0, 0, -8.0F, 12.8525F, 0.2852F, 6, 5, 1, 0.0F, false));
        bone2.cubeList.add(new ModelBox(bone2, 0, 0, -8.0F, 6.8525F, -7.7148F, 6, 12, 2, 0.0F, false));
        bone2.cubeList.add(new ModelBox(bone2, 0, 0, -8.0F, 6.8525F, -3.7148F, 6, 5, 1, 0.0F, false));
        bone2.cubeList.add(new ModelBox(bone2, 0, 0, -8.0F, 12.8525F, -3.7148F, 6, 5, 1, 0.0F, false));
        bone2.cubeList.add(new ModelBox(bone2, 0, 0, -7.5F, 6.8525F, -5.7148F, 5, 5, 9, 0.0F, false));
        bone2.cubeList.add(new ModelBox(bone2, 0, 0, -7.5F, 12.8525F, -5.7148F, 5, 5, 9, 0.0F, false));

        bone4 = new ModelRenderer(this);
        bone4.setRotationPoint(0.0F, 0.0F, 0.0F);
        bone2.addChild(bone4);
        setRotationAngle(bone4, 0.0F, -0.3491F, 0.0F);
        bone4.cubeList.add(new ModelBox(bone4, 0, 0, -1.6694F, -1.1475F, 4.9322F, 1, 20, 2, 0.0F, false));

        bone5 = new ModelRenderer(this);
        bone5.setRotationPoint(-10.0F, 0.0F, 0.0F);
        bone2.addChild(bone5);
        setRotationAngle(bone5, 0.0F, 0.3491F, 0.0F);
        bone5.cubeList.add(new ModelBox(bone5, 0, 0, 0.6694F, -1.1475F, 4.9322F, 1, 20, 2, 0.0F, true));

        bone3 = new ModelRenderer(this);
        bone3.setRotationPoint(5.0F, -3.6F, 12.5F);
        magazine.addChild(bone3);
        setRotationAngle(bone3, 0.6109F, 0.0F, 0.0F);


        s12k = new ModelRenderer(this);
        s12k.setRotationPoint(0.0F, 24.0F, 0.0F);
        s12k.cubeList.add(new ModelBox(s12k, 96, 40, -3.5F, -5.0F, -4.6F, 1, 1, 13, 0.0F, false));
        s12k.cubeList.add(new ModelBox(s12k, 74, 14, -3.5F, -5.0F, 8.4F, 7, 1, 5, 0.0F, false));
        s12k.cubeList.add(new ModelBox(s12k, 74, 14, -3.0F, -4.2F, 11.4F, 6, 1, 2, 0.0F, false));
        s12k.cubeList.add(new ModelBox(s12k, 72, 18, -3.5F, -5.0F, -8.6F, 7, 1, 4, 0.0F, false));
        s12k.cubeList.add(new ModelBox(s12k, 72, 18, -3.5F, -9.0F, -8.6F, 1, 4, 6, 0.0F, false));
        s12k.cubeList.add(new ModelBox(s12k, 72, 18, -3.5261F, -9.1809F, -8.6F, 1, 1, 22, 0.0F, false));
        s12k.cubeList.add(new ModelBox(s12k, 72, 18, 2.5261F, -9.1809F, -8.6F, 1, 1, 22, 0.0F, true));
        s12k.cubeList.add(new ModelBox(s12k, 72, 18, 2.5F, -9.0F, -8.6F, 1, 4, 6, 0.0F, true));
        s12k.cubeList.add(new ModelBox(s12k, 72, 18, -3.5F, -9.0F, 7.4F, 1, 4, 6, 0.0F, false));
        s12k.cubeList.add(new ModelBox(s12k, 72, 18, 2.5F, -9.0F, 7.4F, 1, 4, 6, 0.0F, true));
        s12k.cubeList.add(new ModelBox(s12k, 109, 21, 3.2F, -6.6F, 7.384F, 1, 1, 1, 0.0F, true));
        s12k.cubeList.add(new ModelBox(s12k, 109, 21, 3.2F, -6.6F, -3.592F, 1, 1, 1, 0.0F, true));
        s12k.cubeList.add(new ModelBox(s12k, 85, 26, -3.5F, -6.0F, -2.6F, 1, 1, 10, 0.0F, false));
        s12k.cubeList.add(new ModelBox(s12k, 85, 26, 2.5F, -6.0F, -2.6F, 1, 1, 10, 0.0F, true));
        s12k.cubeList.add(new ModelBox(s12k, 85, 26, 2.8F, -6.2F, -2.6F, 1, 1, 10, 0.0F, true));
        s12k.cubeList.add(new ModelBox(s12k, 49, 157, -3.8F, -6.2F, -2.6F, 1, 1, 10, 0.0F, false));
        s12k.cubeList.add(new ModelBox(s12k, 86, 154, -3.2F, -8.0F, -2.6F, 1, 2, 10, 0.0F, false));
        s12k.cubeList.add(new ModelBox(s12k, 72, 18, -3.5F, -9.0F, -2.6F, 1, 1, 10, 0.0F, false));
        s12k.cubeList.add(new ModelBox(s12k, 72, 18, 2.5F, -9.0F, -2.6F, 1, 1, 10, 0.0F, true));
        s12k.cubeList.add(new ModelBox(s12k, 72, 18, -3.0F, -9.0F, -19.6F, 6, 5, 11, 0.0F, false));
        s12k.cubeList.add(new ModelBox(s12k, 72, 18, -3.0261F, -9.1809F, -16.6F, 6, 1, 8, 0.0F, false));
        s12k.cubeList.add(new ModelBox(s12k, 72, 18, -2.9739F, -9.1809F, -16.6F, 6, 1, 8, 0.0F, true));
        s12k.cubeList.add(new ModelBox(s12k, 90, 93, -1.5F, -12.688F, -15.892F, 3, 1, 9, 0.0F, false));
        s12k.cubeList.add(new ModelBox(s12k, 90, 93, -1.5F, -12.688F, 2.108F, 3, 1, 10, 0.0F, false));
        s12k.cubeList.add(new ModelBox(s12k, 90, 93, -1.5F, -12.688F, -6.892F, 3, 1, 9, 0.0F, false));
        s12k.cubeList.add(new ModelBox(s12k, 90, 93, -1.0F, -13.688F, -16.392F, 2, 1, 1, 0.0F, false));
        s12k.cubeList.add(new ModelBox(s12k, 90, 93, -1.0F, -13.688F, -12.392F, 2, 1, 1, 0.0F, false));
        s12k.cubeList.add(new ModelBox(s12k, 90, 93, -1.0F, -13.688F, -8.392F, 2, 1, 1, 0.0F, false));
        s12k.cubeList.add(new ModelBox(s12k, 90, 93, -1.0F, -13.688F, -4.392F, 2, 1, 1, 0.0F, false));
        s12k.cubeList.add(new ModelBox(s12k, 90, 93, -1.0F, -13.688F, -0.392F, 2, 1, 1, 0.0F, false));
        s12k.cubeList.add(new ModelBox(s12k, 90, 93, -1.0F, -13.688F, 3.608F, 2, 1, 1, 0.0F, false));
        s12k.cubeList.add(new ModelBox(s12k, 90, 93, -1.0F, -13.688F, 7.608F, 2, 1, 1, 0.0F, false));
        s12k.cubeList.add(new ModelBox(s12k, 90, 93, -1.0F, -13.688F, -14.392F, 2, 1, 1, 0.0F, false));
        s12k.cubeList.add(new ModelBox(s12k, 90, 93, -1.0F, -13.688F, -10.392F, 2, 1, 1, 0.0F, false));
        s12k.cubeList.add(new ModelBox(s12k, 90, 93, -1.0F, -13.688F, -6.392F, 2, 1, 1, 0.0F, false));
        s12k.cubeList.add(new ModelBox(s12k, 90, 93, -1.0F, -13.688F, -2.392F, 2, 1, 1, 0.0F, false));
        s12k.cubeList.add(new ModelBox(s12k, 90, 93, -1.0F, -13.688F, 1.608F, 2, 1, 1, 0.0F, false));
        s12k.cubeList.add(new ModelBox(s12k, 90, 93, -1.0F, -13.688F, 5.608F, 2, 1, 1, 0.0F, false));
        s12k.cubeList.add(new ModelBox(s12k, 90, 93, -1.0F, -13.688F, 9.608F, 2, 1, 1, 0.0F, false));
        s12k.cubeList.add(new ModelBox(s12k, 90, 93, -1.0F, -13.688F, 11.608F, 2, 1, 1, 0.0F, false));
        s12k.cubeList.add(new ModelBox(s12k, 90, 93, -1.866F, -13.188F, -15.392F, 2, 0, 1, 0.0F, false));
        s12k.cubeList.add(new ModelBox(s12k, 90, 93, -1.866F, -13.188F, -11.392F, 2, 0, 1, 0.0F, false));
        s12k.cubeList.add(new ModelBox(s12k, 90, 93, -1.866F, -13.188F, -7.392F, 2, 0, 1, 0.0F, false));
        s12k.cubeList.add(new ModelBox(s12k, 90, 93, -1.866F, -13.188F, -3.392F, 2, 0, 1, 0.0F, false));
        s12k.cubeList.add(new ModelBox(s12k, 90, 93, -1.866F, -13.188F, 0.608F, 2, 0, 1, 0.0F, false));
        s12k.cubeList.add(new ModelBox(s12k, 90, 93, -1.866F, -13.188F, 4.608F, 2, 0, 1, 0.0F, false));
        s12k.cubeList.add(new ModelBox(s12k, 90, 93, -1.866F, -13.188F, 8.608F, 2, 0, 1, 0.0F, false));
        s12k.cubeList.add(new ModelBox(s12k, 90, 93, -1.866F, -13.188F, -13.392F, 2, 0, 1, 0.0F, false));
        s12k.cubeList.add(new ModelBox(s12k, 90, 93, -1.866F, -13.188F, -9.392F, 2, 0, 1, 0.0F, false));
        s12k.cubeList.add(new ModelBox(s12k, 90, 93, -1.866F, -13.188F, -5.392F, 2, 0, 1, 0.0F, false));
        s12k.cubeList.add(new ModelBox(s12k, 90, 93, -1.866F, -13.188F, -1.392F, 2, 0, 1, 0.0F, false));
        s12k.cubeList.add(new ModelBox(s12k, 90, 93, -1.866F, -13.188F, 2.608F, 2, 0, 1, 0.0F, false));
        s12k.cubeList.add(new ModelBox(s12k, 90, 93, -1.866F, -13.188F, 6.608F, 2, 0, 1, 0.0F, false));
        s12k.cubeList.add(new ModelBox(s12k, 90, 93, -1.866F, -13.188F, 10.608F, 2, 0, 1, 0.0F, false));
        s12k.cubeList.add(new ModelBox(s12k, 90, 93, -0.134F, -13.188F, -15.392F, 2, 0, 1, 0.0F, true));
        s12k.cubeList.add(new ModelBox(s12k, 90, 93, -0.134F, -13.188F, -11.392F, 2, 0, 1, 0.0F, true));
        s12k.cubeList.add(new ModelBox(s12k, 90, 93, -0.134F, -13.188F, -7.392F, 2, 0, 1, 0.0F, true));
        s12k.cubeList.add(new ModelBox(s12k, 90, 93, -0.134F, -13.188F, -3.392F, 2, 0, 1, 0.0F, true));
        s12k.cubeList.add(new ModelBox(s12k, 90, 93, -0.134F, -13.188F, 0.608F, 2, 0, 1, 0.0F, true));
        s12k.cubeList.add(new ModelBox(s12k, 90, 93, -0.134F, -13.188F, 4.608F, 2, 0, 1, 0.0F, true));
        s12k.cubeList.add(new ModelBox(s12k, 90, 93, -0.134F, -13.188F, 8.608F, 2, 0, 1, 0.0F, true));
        s12k.cubeList.add(new ModelBox(s12k, 90, 93, -0.134F, -13.188F, -13.392F, 2, 0, 1, 0.0F, true));
        s12k.cubeList.add(new ModelBox(s12k, 90, 93, -0.134F, -13.188F, -9.392F, 2, 0, 1, 0.0F, true));
        s12k.cubeList.add(new ModelBox(s12k, 90, 93, -0.134F, -13.188F, -5.392F, 2, 0, 1, 0.0F, true));
        s12k.cubeList.add(new ModelBox(s12k, 90, 93, -0.134F, -13.188F, -1.392F, 2, 0, 1, 0.0F, true));
        s12k.cubeList.add(new ModelBox(s12k, 90, 93, -0.134F, -13.188F, 2.608F, 2, 0, 1, 0.0F, true));
        s12k.cubeList.add(new ModelBox(s12k, 90, 93, -0.134F, -13.188F, 6.608F, 2, 0, 1, 0.0F, true));
        s12k.cubeList.add(new ModelBox(s12k, 90, 93, -0.134F, -13.188F, 10.608F, 2, 0, 1, 0.0F, true));
        s12k.cubeList.add(new ModelBox(s12k, 70, 17, -2.5F, -12.0F, -8.6F, 5, 3, 22, 0.0F, false));
        s12k.cubeList.add(new ModelBox(s12k, 4, 98, -2.5F, -12.3F, 13.4F, 5, 1, 13, 0.0F, false));
        s12k.cubeList.add(new ModelBox(s12k, 4, 98, -2.5F, -12.3F, 26.4F, 5, 1, 10, 0.0F, false));
        s12k.cubeList.add(new ModelBox(s12k, 4, 98, -2.5F, -3.5679F, 13.4F, 5, 1, 13, 0.0F, false));
        s12k.cubeList.add(new ModelBox(s12k, 4, 98, -2.5F, -3.5679F, 26.4F, 5, 1, 10, 0.0F, false));
        s12k.cubeList.add(new ModelBox(s12k, 0, 148, -1.0F, -4.7679F, 33.4F, 2, 1, 22, 0.0F, false));
        s12k.cubeList.add(new ModelBox(s12k, 0, 148, -1.0F, -8.5964F, 33.4F, 2, 1, 16, 0.0F, false));
        s12k.cubeList.add(new ModelBox(s12k, 0, 148, -1.0F, -8.5964F, 53.4F, 2, 1, 2, 0.0F, false));
        s12k.cubeList.add(new ModelBox(s12k, 14, 138, -1.5F, -8.5964F, 47.4F, 3, 1, 6, 0.0F, false));
        s12k.cubeList.add(new ModelBox(s12k, 67, 6, -1.0F, -13.5964F, 50.4F, 2, 4, 2, 0.0F, false));
        s12k.cubeList.add(new ModelBox(s12k, 67, 6, -1.0F, -9.5964F, 49.4F, 2, 1, 3, 0.0F, false));
        s12k.cubeList.add(new ModelBox(s12k, 0, 148, -2.4142F, -7.1822F, 33.4F, 1, 2, 22, 0.0F, false));
        s12k.cubeList.add(new ModelBox(s12k, 0, 148, 1.4142F, -7.1822F, 33.4F, 1, 2, 22, 0.0F, true));
        s12k.cubeList.add(new ModelBox(s12k, 14, 69, -3.0F, -11.4679F, 32.7F, 6, 8, 1, 0.0F, false));
        s12k.cubeList.add(new ModelBox(s12k, 4, 98, -3.866F, -10.934F, 13.4F, 1, 7, 13, 0.0F, false));
        s12k.cubeList.add(new ModelBox(s12k, 4, 98, -3.866F, -10.934F, 26.4F, 1, 7, 10, 0.0F, false));
        s12k.cubeList.add(new ModelBox(s12k, 4, 98, 2.866F, -10.934F, 13.4F, 1, 7, 13, 0.0F, true));
        s12k.cubeList.add(new ModelBox(s12k, 4, 98, 2.866F, -10.934F, 26.4F, 1, 7, 10, 0.0F, true));
        s12k.cubeList.add(new ModelBox(s12k, 0, 74, -2.0F, -8.0F, -24.6F, 4, 4, 5, 0.0F, false));
        s12k.cubeList.add(new ModelBox(s12k, 0, 74, -2.0F, -8.0F, -31.3F, 4, 4, 2, 0.0F, false));
        s12k.cubeList.add(new ModelBox(s12k, 0, 74, -0.7071F, -7.9751F, -43.3F, 2, 1, 12, 0.0F, false));
        s12k.cubeList.add(new ModelBox(s12k, 0, 74, -1.2929F, -7.9751F, -43.3F, 1, 1, 12, 0.0F, true));
        s12k.cubeList.add(new ModelBox(s12k, 0, 73, -2.0F, -7.268F, -43.3F, 4, 3, 12, 0.0F, false));
        s12k.cubeList.add(new ModelBox(s12k, 3, 75, -2.0F, -4.568F, -43.3F, 4, 7, 6, 0.0F, false));
        s12k.cubeList.add(new ModelBox(s12k, 0, 74, -2.0F, -7.7F, -29.4F, 4, 3, 5, 0.0F, false));
        s12k.cubeList.add(new ModelBox(s12k, 72, 18, -2.5F, -4.0F, -19.2F, 5, 1, 8, 0.0F, false));
        s12k.cubeList.add(new ModelBox(s12k, 72, 18, -2.5F, -3.0F, -18.2F, 5, 3, 7, 0.0F, false));
        s12k.cubeList.add(new ModelBox(s12k, 72, 18, -1.5F, 0.0F, -11.2F, 3, 1, 5, 0.0F, false));
        s12k.cubeList.add(new ModelBox(s12k, 72, 18, -1.5F, -4.366F, -5.834F, 3, 4, 1, 0.0F, false));
        s12k.cubeList.add(new ModelBox(s12k, 96, 40, 2.5F, -5.0F, -4.6F, 1, 1, 13, 0.0F, false));

        bone38 = new ModelRenderer(this);
        bone38.setRotationPoint(0.0F, 0.0F, 0.0F);
        s12k.addChild(bone38);
        setRotationAngle(bone38, -0.4363F, 0.0F, 0.0F);
        bone38.cubeList.add(new ModelBox(bone38, 74, 14, -3.0F, -8.718F, 6.9795F, 6, 1, 2, 0.0F, false));

        bone25 = new ModelRenderer(this);
        bone25.setRotationPoint(3.5F, 0.0F, 0.0F);
        s12k.addChild(bone25);
        setRotationAngle(bone25, 0.0F, 0.0F, -0.2618F);
        bone25.cubeList.add(new ModelBox(bone25, 18, 149, 0.8945F, -5.9111F, -2.6F, 1, 2, 10, 0.0F, true));
        bone25.cubeList.add(new ModelBox(bone25, 18, 149, 0.9945F, -5.2111F, -2.1F, 1, 1, 9, 0.0F, true));

        bone26 = new ModelRenderer(this);
        bone26.setRotationPoint(0.5F, 0.0F, 0.0F);
        s12k.addChild(bone26);
        setRotationAngle(bone26, 0.0F, 0.0F, -0.5236F);
        bone26.cubeList.add(new ModelBox(bone26, 49, 157, -1.1239F, -6.6533F, -2.6F, 1, 1, 10, 0.0F, false));

        bone32 = new ModelRenderer(this);
        bone32.setRotationPoint(-1.0F, 0.0F, 0.0F);
        s12k.addChild(bone32);
        setRotationAngle(bone32, 0.0F, 0.0F, 0.5236F);
        bone32.cubeList.add(new ModelBox(bone32, 100, 90, -5.1119F, -12.8542F, -16.392F, 1, 1, 1, 0.0F, false));
        bone32.cubeList.add(new ModelBox(bone32, 100, 90, -5.1119F, -12.8542F, -12.392F, 1, 1, 1, 0.0F, false));
        bone32.cubeList.add(new ModelBox(bone32, 100, 90, -5.1119F, -12.8542F, -8.392F, 1, 1, 1, 0.0F, false));
        bone32.cubeList.add(new ModelBox(bone32, 100, 90, -5.1119F, -12.8542F, -4.392F, 1, 1, 1, 0.0F, false));
        bone32.cubeList.add(new ModelBox(bone32, 100, 90, -5.1119F, -12.8542F, -0.392F, 1, 1, 1, 0.0F, false));
        bone32.cubeList.add(new ModelBox(bone32, 100, 90, -5.1119F, -12.8542F, 3.608F, 1, 1, 1, 0.0F, false));
        bone32.cubeList.add(new ModelBox(bone32, 100, 90, -5.1119F, -12.8542F, 7.608F, 1, 1, 1, 0.0F, false));
        bone32.cubeList.add(new ModelBox(bone32, 100, 90, -5.1119F, -12.8542F, -14.392F, 1, 1, 1, 0.0F, false));
        bone32.cubeList.add(new ModelBox(bone32, 100, 90, -5.1119F, -12.8542F, -10.392F, 1, 1, 1, 0.0F, false));
        bone32.cubeList.add(new ModelBox(bone32, 100, 90, -5.1119F, -12.8542F, -6.392F, 1, 1, 1, 0.0F, false));
        bone32.cubeList.add(new ModelBox(bone32, 100, 90, -5.1119F, -12.8542F, -2.392F, 1, 1, 1, 0.0F, false));
        bone32.cubeList.add(new ModelBox(bone32, 100, 90, -5.1119F, -12.8542F, 1.608F, 1, 1, 1, 0.0F, false));
        bone32.cubeList.add(new ModelBox(bone32, 100, 90, -5.1119F, -12.8542F, 5.608F, 1, 1, 1, 0.0F, false));
        bone32.cubeList.add(new ModelBox(bone32, 100, 90, -5.1119F, -12.8542F, 9.608F, 1, 1, 1, 0.0F, false));
        bone32.cubeList.add(new ModelBox(bone32, 100, 90, -5.1119F, -12.8542F, 11.608F, 1, 1, 1, 0.0F, false));
        bone32.cubeList.add(new ModelBox(bone32, 100, 90, -4.1119F, -12.8542F, -15.392F, 0, 1, 1, 0.0F, false));
        bone32.cubeList.add(new ModelBox(bone32, 100, 90, -4.1119F, -12.8542F, -11.392F, 0, 1, 1, 0.0F, false));
        bone32.cubeList.add(new ModelBox(bone32, 100, 90, -4.1119F, -12.8542F, -7.392F, 0, 1, 1, 0.0F, false));
        bone32.cubeList.add(new ModelBox(bone32, 100, 90, -4.1119F, -12.8542F, -3.392F, 0, 1, 1, 0.0F, false));
        bone32.cubeList.add(new ModelBox(bone32, 100, 90, -4.1119F, -12.8542F, 0.608F, 0, 1, 1, 0.0F, false));
        bone32.cubeList.add(new ModelBox(bone32, 100, 90, -4.1119F, -12.8542F, 4.608F, 0, 1, 1, 0.0F, false));
        bone32.cubeList.add(new ModelBox(bone32, 100, 90, -4.1119F, -12.8542F, 8.608F, 0, 1, 1, 0.0F, false));
        bone32.cubeList.add(new ModelBox(bone32, 100, 90, -4.1119F, -12.8542F, -13.392F, 0, 1, 1, 0.0F, false));
        bone32.cubeList.add(new ModelBox(bone32, 100, 90, -4.1119F, -12.8542F, -9.392F, 0, 1, 1, 0.0F, false));
        bone32.cubeList.add(new ModelBox(bone32, 100, 90, -4.1119F, -12.8542F, -5.392F, 0, 1, 1, 0.0F, false));
        bone32.cubeList.add(new ModelBox(bone32, 100, 90, -4.1119F, -12.8542F, -1.392F, 0, 1, 1, 0.0F, false));
        bone32.cubeList.add(new ModelBox(bone32, 100, 90, -4.1119F, -12.8542F, 2.608F, 0, 1, 1, 0.0F, false));
        bone32.cubeList.add(new ModelBox(bone32, 100, 90, -4.1119F, -12.8542F, 6.608F, 0, 1, 1, 0.0F, false));
        bone32.cubeList.add(new ModelBox(bone32, 100, 90, -4.1119F, -12.8542F, 10.608F, 0, 1, 1, 0.0F, false));

        bone33 = new ModelRenderer(this);
        bone33.setRotationPoint(1.0F, 0.0F, 0.0F);
        s12k.addChild(bone33);
        setRotationAngle(bone33, 0.0F, 0.0F, -0.5236F);
        bone33.cubeList.add(new ModelBox(bone33, 76, 85, 4.1119F, -12.8542F, -16.392F, 1, 1, 1, 0.0F, true));
        bone33.cubeList.add(new ModelBox(bone33, 76, 85, 4.1119F, -12.8542F, -12.392F, 1, 1, 1, 0.0F, true));
        bone33.cubeList.add(new ModelBox(bone33, 76, 85, 4.1119F, -12.8542F, -8.392F, 1, 1, 1, 0.0F, true));
        bone33.cubeList.add(new ModelBox(bone33, 76, 85, 4.1119F, -12.8542F, -4.392F, 1, 1, 1, 0.0F, true));
        bone33.cubeList.add(new ModelBox(bone33, 76, 85, 4.1119F, -12.8542F, -0.392F, 1, 1, 1, 0.0F, true));
        bone33.cubeList.add(new ModelBox(bone33, 76, 85, 4.1119F, -12.8542F, 3.608F, 1, 1, 1, 0.0F, true));
        bone33.cubeList.add(new ModelBox(bone33, 76, 85, 4.1119F, -12.8542F, 7.608F, 1, 1, 1, 0.0F, true));
        bone33.cubeList.add(new ModelBox(bone33, 76, 85, 4.1119F, -12.8542F, -14.392F, 1, 1, 1, 0.0F, true));
        bone33.cubeList.add(new ModelBox(bone33, 76, 85, 4.1119F, -12.8542F, -10.392F, 1, 1, 1, 0.0F, true));
        bone33.cubeList.add(new ModelBox(bone33, 76, 85, 4.1119F, -12.8542F, -6.392F, 1, 1, 1, 0.0F, true));
        bone33.cubeList.add(new ModelBox(bone33, 76, 85, 4.1119F, -12.8542F, -2.392F, 1, 1, 1, 0.0F, true));
        bone33.cubeList.add(new ModelBox(bone33, 76, 85, 4.1119F, -12.8542F, 1.608F, 1, 1, 1, 0.0F, true));
        bone33.cubeList.add(new ModelBox(bone33, 76, 85, 4.1119F, -12.8542F, 5.608F, 1, 1, 1, 0.0F, true));
        bone33.cubeList.add(new ModelBox(bone33, 76, 85, 4.1119F, -12.8542F, 9.608F, 1, 1, 1, 0.0F, true));
        bone33.cubeList.add(new ModelBox(bone33, 76, 85, 4.1119F, -12.8542F, 11.608F, 1, 1, 1, 0.0F, true));
        bone33.cubeList.add(new ModelBox(bone33, 76, 85, 4.1119F, -12.8542F, -15.392F, 0, 1, 1, 0.0F, true));
        bone33.cubeList.add(new ModelBox(bone33, 76, 85, 4.1119F, -12.8542F, -11.392F, 0, 1, 1, 0.0F, true));
        bone33.cubeList.add(new ModelBox(bone33, 76, 85, 4.1119F, -12.8542F, -7.392F, 0, 1, 1, 0.0F, true));
        bone33.cubeList.add(new ModelBox(bone33, 76, 85, 4.1119F, -12.8542F, -3.392F, 0, 1, 1, 0.0F, true));
        bone33.cubeList.add(new ModelBox(bone33, 76, 85, 4.1119F, -12.8542F, 0.608F, 0, 1, 1, 0.0F, true));
        bone33.cubeList.add(new ModelBox(bone33, 76, 85, 4.1119F, -12.8542F, 4.608F, 0, 1, 1, 0.0F, true));
        bone33.cubeList.add(new ModelBox(bone33, 76, 85, 4.1119F, -12.8542F, 8.608F, 0, 1, 1, 0.0F, true));
        bone33.cubeList.add(new ModelBox(bone33, 76, 85, 4.1119F, -12.8542F, -13.392F, 0, 1, 1, 0.0F, true));
        bone33.cubeList.add(new ModelBox(bone33, 76, 85, 4.1119F, -12.8542F, -9.392F, 0, 1, 1, 0.0F, true));
        bone33.cubeList.add(new ModelBox(bone33, 76, 85, 4.1119F, -12.8542F, -5.392F, 0, 1, 1, 0.0F, true));
        bone33.cubeList.add(new ModelBox(bone33, 76, 85, 4.1119F, -12.8542F, -1.392F, 0, 1, 1, 0.0F, true));
        bone33.cubeList.add(new ModelBox(bone33, 76, 85, 4.1119F, -12.8542F, 2.608F, 0, 1, 1, 0.0F, true));
        bone33.cubeList.add(new ModelBox(bone33, 76, 85, 4.1119F, -12.8542F, 6.608F, 0, 1, 1, 0.0F, true));
        bone33.cubeList.add(new ModelBox(bone33, 76, 85, 4.1119F, -12.8542F, 10.608F, 0, 1, 1, 0.0F, true));

        bone = new ModelRenderer(this);
        bone.setRotationPoint(-3.0F, 0.0F, 0.0F);
        s12k.addChild(bone);
        setRotationAngle(bone, -0.4363F, 0.0F, 0.0F);
        bone.cubeList.add(new ModelBox(bone, 67, 6, 2.0F, -33.6225F, 39.9318F, 2, 6, 2, 0.0F, false));

        bone39 = new ModelRenderer(this);
        bone39.setRotationPoint(-3.0F, 0.0F, 0.0F);
        s12k.addChild(bone39);
        setRotationAngle(bone39, 0.0F, 0.0F, -0.7854F);
        bone39.cubeList.add(new ModelBox(bone39, 0, 148, 5.4928F, -0.8359F, 33.4F, 2, 1, 22, 0.0F, false));
        bone39.cubeList.add(new ModelBox(bone39, 0, 148, 7.907F, -3.2501F, 33.4F, 1, 2, 22, 0.0F, false));
        bone39.cubeList.add(new ModelBox(bone39, 0, 148, 4.0786F, -3.2501F, 33.4F, 1, 2, 22, 0.0F, false));
        bone39.cubeList.add(new ModelBox(bone39, 0, 148, 5.4928F, -4.6643F, 33.4F, 2, 1, 22, 0.0F, false));

        bone29 = new ModelRenderer(this);
        bone29.setRotationPoint(-1.5F, 0.0F, 0.0F);
        s12k.addChild(bone29);
        setRotationAngle(bone29, 0.0F, 0.0F, -0.5236F);
        bone29.cubeList.add(new ModelBox(bone29, 4, 98, 4.284F, -11.1521F, 13.4F, 1, 1, 13, 0.0F, false));
        bone29.cubeList.add(new ModelBox(bone29, 4, 98, 4.284F, -11.1521F, 26.4F, 1, 1, 10, 0.0F, false));
        bone29.cubeList.add(new ModelBox(bone29, 4, 98, 9.1141F, -7.7861F, 13.4F, 1, 1, 13, 0.0F, false));
        bone29.cubeList.add(new ModelBox(bone29, 4, 98, 9.1141F, -7.7861F, 26.4F, 1, 1, 10, 0.0F, false));
        bone29.cubeList.add(new ModelBox(bone29, 4, 98, -0.0821F, -4.5899F, 13.4F, 1, 1, 13, 0.0F, false));
        bone29.cubeList.add(new ModelBox(bone29, 4, 98, -0.0821F, -4.5899F, 26.4F, 1, 1, 10, 0.0F, false));
        bone29.cubeList.add(new ModelBox(bone29, 4, 98, 4.7481F, -1.2239F, 13.4F, 1, 1, 13, 0.0F, false));
        bone29.cubeList.add(new ModelBox(bone29, 4, 98, 4.7481F, -1.2239F, 26.4F, 1, 1, 10, 0.0F, false));

        bone30 = new ModelRenderer(this);
        bone30.setRotationPoint(1.5F, 0.0F, 0.0F);
        s12k.addChild(bone30);
        setRotationAngle(bone30, 0.0F, 0.0F, 0.5236F);
        bone30.cubeList.add(new ModelBox(bone30, 4, 98, -5.284F, -11.1521F, 13.4F, 1, 1, 13, 0.0F, true));
        bone30.cubeList.add(new ModelBox(bone30, 4, 98, -5.284F, -11.1521F, 26.4F, 1, 1, 10, 0.0F, true));

        bone31 = new ModelRenderer(this);
        bone31.setRotationPoint(1.5F, 0.0F, 0.0F);
        s12k.addChild(bone31);
        setRotationAngle(bone31, 0.0F, 0.0F, 0.5236F);
        bone31.cubeList.add(new ModelBox(bone31, 4, 98, -10.1141F, -7.7861F, 13.4F, 1, 1, 13, 0.0F, true));
        bone31.cubeList.add(new ModelBox(bone31, 4, 98, -10.1141F, -7.7861F, 26.4F, 1, 1, 10, 0.0F, true));
        bone31.cubeList.add(new ModelBox(bone31, 4, 98, -0.9179F, -4.5899F, 13.4F, 1, 1, 13, 0.0F, true));
        bone31.cubeList.add(new ModelBox(bone31, 4, 98, -0.9179F, -4.5899F, 26.4F, 1, 1, 10, 0.0F, true));
        bone31.cubeList.add(new ModelBox(bone31, 4, 98, -5.7481F, -1.2239F, 13.4F, 1, 1, 13, 0.0F, true));
        bone31.cubeList.add(new ModelBox(bone31, 4, 98, -5.7481F, -1.2239F, 26.4F, 1, 1, 10, 0.0F, true));

        bone19 = new ModelRenderer(this);
        bone19.setRotationPoint(-5.0F, 0.0F, 0.0F);
        s12k.addChild(bone19);
        setRotationAngle(bone19, 0.0F, 0.0F, 0.3491F);
        bone19.cubeList.add(new ModelBox(bone19, 72, 18, -1.2852F, -12.3024F, -16.6F, 1, 3, 8, 0.0F, false));
        bone19.cubeList.add(new ModelBox(bone19, 72, 18, -1.755F, -12.1314F, -8.6F, 1, 3, 22, 0.0F, false));

        bone20 = new ModelRenderer(this);
        bone20.setRotationPoint(5.0F, 0.0F, 0.0F);
        s12k.addChild(bone20);
        setRotationAngle(bone20, 0.0F, 0.0F, -0.3491F);
        bone20.cubeList.add(new ModelBox(bone20, 72, 18, 0.2852F, -12.3024F, -16.6F, 1, 3, 8, 0.0F, true));
        bone20.cubeList.add(new ModelBox(bone20, 72, 18, 0.755F, -12.1314F, -8.6F, 1, 3, 22, 0.0F, true));

        bone18 = new ModelRenderer(this);
        bone18.setRotationPoint(-1.0F, 0.0F, 0.0F);
        s12k.addChild(bone18);
        setRotationAngle(bone18, -0.7854F, 0.0F, 0.0F);
        bone18.cubeList.add(new ModelBox(bone18, 72, 18, -1.0F, 3.2527F, -20.2233F, 4, 2, 2, 0.0F, false));
        bone18.cubeList.add(new ModelBox(bone18, 72, 18, -2.0F, 5.4953F, -20.2233F, 6, 2, 2, 0.0F, false));
        bone18.cubeList.add(new ModelBox(bone18, 72, 18, -1.5F, 4.4953F, -20.2233F, 5, 1, 3, 0.0F, false));
        bone18.cubeList.add(new ModelBox(bone18, 42, 42, 0.4F, 3.3777F, -20.3647F, 1, 1, 3, 0.0F, false));
        bone18.cubeList.add(new ModelBox(bone18, 42, 42, 0.4F, 6.2414F, -20.3647F, 1, 1, 3, 0.0F, false));

        bone21 = new ModelRenderer(this);
        bone21.setRotationPoint(-0.5F, 0.0F, 0.0F);
        bone18.addChild(bone21);
        setRotationAngle(bone21, 0.0F, 0.0F, -0.2618F);
        bone21.cubeList.add(new ModelBox(bone21, 42, 42, -0.039F, 3.7544F, -20.3647F, 1, 2, 3, 0.0F, false));
        bone21.cubeList.add(new ModelBox(bone21, 42, 42, -1.0049F, 5.2276F, -20.3647F, 1, 2, 3, 0.0F, false));

        bone22 = new ModelRenderer(this);
        bone22.setRotationPoint(2.5F, 0.0F, 0.0F);
        bone18.addChild(bone22);
        setRotationAngle(bone22, 0.0F, 0.0F, 0.2618F);
        bone22.cubeList.add(new ModelBox(bone22, 42, 42, -0.1883F, 5.2794F, -20.3647F, 1, 2, 3, 0.0F, true));
        bone22.cubeList.add(new ModelBox(bone22, 42, 42, -1.1542F, 3.8061F, -20.3647F, 1, 2, 3, 0.0F, true));

        bone15 = new ModelRenderer(this);
        bone15.setRotationPoint(-0.5F, 0.0F, 0.0F);
        s12k.addChild(bone15);
        setRotationAngle(bone15, 0.0F, 0.0F, 0.7854F);
        bone15.cubeList.add(new ModelBox(bone15, 0, 74, -4.3715F, -6.907F, -43.3F, 1, 1, 12, 0.0F, false));
        bone15.cubeList.add(new ModelBox(bone15, 0, 74, -6.1999F, -5.0786F, -43.3F, 1, 1, 12, 0.0F, false));

        bone14 = new ModelRenderer(this);
        bone14.setRotationPoint(0.0F, -27.2893F, -16.9756F);
        s12k.addChild(bone14);
        setRotationAngle(bone14, 0.5236F, 0.0F, 0.0F);
        bone14.cubeList.add(new ModelBox(bone14, 0, 74, -2.0F, 9.5428F, -24.05F, 4, 1, 2, 0.0F, false));

        bone16 = new ModelRenderer(this);
        bone16.setRotationPoint(0.0F, -1.068F, -34.8F);
        s12k.addChild(bone16);
        setRotationAngle(bone16, 0.3491F, 0.0F, 0.0F);
        bone16.cubeList.add(new ModelBox(bone16, 0, 74, -2.0F, -4.5661F, -3.5463F, 4, 7, 4, 0.0F, false));
        bone16.cubeList.add(new ModelBox(bone16, 0, 74, -2.0F, -3.5661F, 0.4537F, 4, 6, 4, 0.0F, false));
        bone16.cubeList.add(new ModelBox(bone16, 0, 74, -2.0F, -1.5661F, 4.4537F, 4, 4, 5, 0.0F, false));

        bone17 = new ModelRenderer(this);
        bone17.setRotationPoint(0.0F, -1.068F, -34.8F);
        s12k.addChild(bone17);
        setRotationAngle(bone17, 0.6109F, 0.0F, 0.0F);
        bone17.cubeList.add(new ModelBox(bone17, 0, 74, -2.0F, 1.7977F, 8.5016F, 4, 3, 4, 0.0F, false));

        bone12 = new ModelRenderer(this);
        bone12.setRotationPoint(0.0F, -6.0F, -27.1F);
        s12k.addChild(bone12);
        setRotationAngle(bone12, 0.1745F, 0.0F, 0.0F);
        bone12.cubeList.add(new ModelBox(bone12, 0, 74, -2.0F, -1.5355F, 0.8093F, 4, 1, 2, 0.0F, false));

        bone13 = new ModelRenderer(this);
        bone13.setRotationPoint(0.0F, -6.0F, -26.8F);
        s12k.addChild(bone13);
        setRotationAngle(bone13, -0.1745F, 0.0F, 0.0F);
        bone13.cubeList.add(new ModelBox(bone13, 0, 74, -2.0F, -1.5355F, -2.8093F, 4, 1, 2, 0.0F, false));

        bone10 = new ModelRenderer(this);
        bone10.setRotationPoint(0.0F, 0.0F, 0.0F);
        s12k.addChild(bone10);
        setRotationAngle(bone10, 0.0F, -0.5236F, 0.0F);
        bone10.cubeList.add(new ModelBox(bone10, 72, 18, -7.3311F, -9.0F, -6.6978F, 1, 5, 1, 0.0F, false));

        bone27 = new ModelRenderer(this);
        bone27.setRotationPoint(-5.0F, 0.0F, 0.0F);
        bone10.addChild(bone27);
        setRotationAngle(bone27, 0.0F, 0.0F, 0.6109F);
        bone27.cubeList.add(new ModelBox(bone27, 72, 18, -7.0717F, -8.0353F, -6.6978F, 1, 2, 1, 0.0F, false));

        bone11 = new ModelRenderer(this);
        bone11.setRotationPoint(0.0F, 0.0F, 0.0F);
        s12k.addChild(bone11);
        setRotationAngle(bone11, 0.0F, 0.5236F, 0.0F);
        bone11.cubeList.add(new ModelBox(bone11, 72, 18, 6.3311F, -9.0F, -6.6978F, 1, 5, 1, 0.0F, true));

        bone28 = new ModelRenderer(this);
        bone28.setRotationPoint(5.0F, 0.0F, 0.0F);
        bone11.addChild(bone28);
        setRotationAngle(bone28, 0.0F, 0.0F, -0.6109F);
        bone28.cubeList.add(new ModelBox(bone28, 72, 18, 6.0717F, -8.0353F, -6.6978F, 1, 2, 1, 0.0F, true));

        bone6 = new ModelRenderer(this);
        bone6.setRotationPoint(0.0F, -2.5F, -14.2F);
        s12k.addChild(bone6);
        setRotationAngle(bone6, -1.0472F, 0.0F, 0.0F);
        bone6.cubeList.add(new ModelBox(bone6, 72, 18, -2.5F, 3.0801F, -2.933F, 5, 1, 2, 0.0F, false));

        bone8 = new ModelRenderer(this);
        bone8.setRotationPoint(0.5F, 0.0F, 0.0F);
        s12k.addChild(bone8);
        setRotationAngle(bone8, -0.5236F, 0.0F, 0.0F);
        bone8.cubeList.add(new ModelBox(bone8, 72, 18, -2.0F, 5.466F, -11.1995F, 3, 1, 2, 0.0F, false));
        bone8.cubeList.add(new ModelBox(bone8, 72, 18, -2.0F, 2.1F, -5.3694F, 3, 1, 1, 0.0F, false));

        bone9 = new ModelRenderer(this);
        bone9.setRotationPoint(0.5F, 0.0F, -21.5F);
        s12k.addChild(bone9);
        setRotationAngle(bone9, 0.5236F, 0.0F, 0.0F);
        bone9.cubeList.add(new ModelBox(bone9, 72, 18, -2.0F, 7.516F, 12.7502F, 3, 1, 1, 0.0F, false));

        bone7 = new ModelRenderer(this);
        bone7.setRotationPoint(0.5F, 8.3F, -2.0F);
        s12k.addChild(bone7);
        setRotationAngle(bone7, -0.3491F, 0.0F, 0.0F);
        bone7.cubeList.add(new ModelBox(bone7, 72, 18, -3.0F, -3.0F, -18.2F, 5, 8, 6, 0.0F, false));
        bone7.cubeList.add(new ModelBox(bone7, 72, 18, -3.5F, 5.0F, -18.2F, 6, 1, 6, 0.0F, false));
        bone7.cubeList.add(new ModelBox(bone7, 72, 18, -3.0F, 5.0F, -18.5F, 5, 1, 7, 0.0F, false));
        bone7.cubeList.add(new ModelBox(bone7, 72, 18, -3.0F, -4.0F, -16.2F, 5, 1, 4, 0.0F, false));
        bone7.cubeList.add(new ModelBox(bone7, 72, 18, -3.0F, -5.0F, -14.2F, 5, 1, 2, 0.0F, false));

        ironsights = new ModelRenderer(this);
        ironsights.setRotationPoint(0.0F, 24.0F, 0.0F);
        ironsights.cubeList.add(new ModelBox(ironsights, 0, 0, -2.0F, -14.1F, -12.9F, 4, 1, 2, 0.0F, false));
        ironsights.cubeList.add(new ModelBox(ironsights, 0, 0, -1.9F, -13.4F, -12.884F, 1, 1, 1, 0.0F, false));
        ironsights.cubeList.add(new ModelBox(ironsights, 0, 0, 0.9F, -13.4F, -12.884F, 1, 1, 1, 0.0F, true));
        ironsights.cubeList.add(new ModelBox(ironsights, 0, 0, 1.7F, -17.3124F, -12.9F, 1, 2, 2, 0.0F, true));
        ironsights.cubeList.add(new ModelBox(ironsights, 0, 0, -0.5F, -15.6F, -12.4F, 1, 2, 1, 0.0F, false));
        ironsights.cubeList.add(new ModelBox(ironsights, 0, 0, 0.866F, -16.966F, -12.4F, 1, 1, 1, 0.0F, false));
        ironsights.cubeList.add(new ModelBox(ironsights, 0, 0, -0.5F, -18.3321F, -12.4F, 1, 1, 1, 0.0F, true));
        ironsights.cubeList.add(new ModelBox(ironsights, 0, 0, -1.866F, -16.966F, -12.4F, 1, 1, 1, 0.0F, true));
        ironsights.cubeList.add(new ModelBox(ironsights, 0, 0, -2.7F, -17.3124F, -12.9F, 1, 2, 2, 0.0F, false));
        ironsights.cubeList.add(new ModelBox(ironsights, 0, 0, -1.5F, -14.5964F, 49.6679F, 1, 1, 3, 0.0F, false));
        ironsights.cubeList.add(new ModelBox(ironsights, 0, 0, -1.5F, -14.5964F, 52.4F, 1, 1, 1, 0.0F, false));
        ironsights.cubeList.add(new ModelBox(ironsights, 0, 0, -1.9F, -15.1964F, 50.5679F, 1, 2, 2, 0.0F, false));
        ironsights.cubeList.add(new ModelBox(ironsights, 0, 0, -1.5F, -15.9624F, 51.034F, 1, 1, 1, 0.0F, false));
        ironsights.cubeList.add(new ModelBox(ironsights, 0, 0, 0.5F, -15.9624F, 51.034F, 1, 1, 1, 0.0F, true));
        ironsights.cubeList.add(new ModelBox(ironsights, 0, 0, 0.5F, -14.5964F, 52.4F, 1, 1, 1, 0.0F, true));
        ironsights.cubeList.add(new ModelBox(ironsights, 0, 0, -0.5F, -14.0964F, 50.9679F, 1, 1, 1, 0.0F, false));
        ironsights.cubeList.add(new ModelBox(ironsights, 0, 0, 0.9F, -15.1964F, 50.5679F, 1, 2, 2, 0.0F, true));
        ironsights.cubeList.add(new ModelBox(ironsights, 0, 0, 0.5F, -14.5964F, 49.6679F, 1, 1, 3, 0.0F, true));
        ironsights.cubeList.add(new ModelBox(ironsights, 0, 0, -1.5F, -11.1323F, 47.6679F, 3, 3, 5, 0.0F, false));
        ironsights.cubeList.add(new ModelBox(ironsights, 0, 0, -1.5F, -13.5964F, 52.4F, 3, 5, 1, 0.0F, false));
        ironsights.cubeList.add(new ModelBox(ironsights, 0, 0, -1.5F, -13.5964F, 49.6679F, 3, 3, 3, 0.0F, false));
        ironsights.cubeList.add(new ModelBox(ironsights, 82, 33, -2.0F, -12.0F, -16.6F, 4, 3, 8, 0.0F, false));
        ironsights.cubeList.add(new ModelBox(ironsights, 0, 0, -1.9F, -13.4F, -11.916F, 1, 1, 1, 0.0F, false));
        ironsights.cubeList.add(new ModelBox(ironsights, 0, 0, 0.9F, -13.4F, -11.916F, 1, 1, 1, 0.0F, true));

        bone37 = new ModelRenderer(this);
        bone37.setRotationPoint(0.0F, -13.6F, -11.9F);
        ironsights.addChild(bone37);
        setRotationAngle(bone37, 0.0F, 0.0F, 0.5236F);
        bone37.cubeList.add(new ModelBox(bone37, 0, 0, 0.4821F, -2.833F, -1.0F, 1, 2, 2, 0.0F, true));
        bone37.cubeList.add(new ModelBox(bone37, 0, 0, -4.1945F, -3.8651F, -1.0F, 1, 2, 2, 0.0F, true));

        bone36 = new ModelRenderer(this);
        bone36.setRotationPoint(0.0F, -13.6F, -11.9F);
        ironsights.addChild(bone36);
        setRotationAngle(bone36, 0.0F, 0.0F, -0.5236F);
        bone36.cubeList.add(new ModelBox(bone36, 0, 0, -1.4821F, -2.833F, -1.0F, 1, 2, 2, 0.0F, false));
        bone36.cubeList.add(new ModelBox(bone36, 0, 0, 3.1945F, -3.8651F, -1.0F, 1, 2, 2, 0.0F, false));

        bone34 = new ModelRenderer(this);
        bone34.setRotationPoint(0.0F, -15.1F, -11.9F);
        ironsights.addChild(bone34);
        setRotationAngle(bone34, 0.0F, 0.0F, 0.5236F);
        bone34.cubeList.add(new ModelBox(bone34, 0, 0, -1.183F, -0.317F, -0.5F, 1, 1, 1, 0.0F, false));
        bone34.cubeList.add(new ModelBox(bone34, 0, 0, 0.183F, -1.683F, -0.5F, 1, 1, 1, 0.0F, false));
        bone34.cubeList.add(new ModelBox(bone34, 0, 0, -2.549F, -1.683F, -0.5F, 1, 1, 1, 0.0F, false));
        bone34.cubeList.add(new ModelBox(bone34, 0, 0, -1.183F, -3.049F, -0.5F, 1, 1, 1, 0.0F, false));

        bone35 = new ModelRenderer(this);
        bone35.setRotationPoint(0.0F, -15.1F, -11.9F);
        ironsights.addChild(bone35);
        setRotationAngle(bone35, 0.0F, 0.0F, -0.5236F);
        bone35.cubeList.add(new ModelBox(bone35, 0, 0, 0.183F, -0.317F, -0.5F, 1, 1, 1, 0.0F, true));
        bone35.cubeList.add(new ModelBox(bone35, 0, 0, -1.183F, -1.683F, -0.5F, 1, 1, 1, 0.0F, true));
        bone35.cubeList.add(new ModelBox(bone35, 0, 0, 1.549F, -1.683F, -0.5F, 1, 1, 1, 0.0F, true));
        bone35.cubeList.add(new ModelBox(bone35, 0, 0, 0.183F, -3.049F, -0.5F, 1, 1, 1, 0.0F, true));

        bone41 = new ModelRenderer(this);
        bone41.setRotationPoint(-1.0F, -14.0964F, 59.9F);
        ironsights.addChild(bone41);
        setRotationAngle(bone41, 1.0472F, 0.0F, 0.0F);
        bone41.cubeList.add(new ModelBox(bone41, 0, 0, -0.5F, -7.7452F, -4.317F, 1, 1, 2, 0.0F, false));
        bone41.cubeList.add(new ModelBox(bone41, 0, 0, 1.5F, -7.7452F, -4.317F, 1, 1, 2, 0.0F, true));
        bone41.cubeList.add(new ModelBox(bone41, 0, 0, -0.5F, -9.1112F, -5.683F, 1, 1, 2, 0.0F, false));
        bone41.cubeList.add(new ModelBox(bone41, 0, 0, 1.5F, -9.1112F, -5.683F, 1, 1, 2, 0.0F, true));
        bone41.cubeList.add(new ModelBox(bone41, 0, 0, -0.5F, -9.1112F, -8.683F, 3, 2, 3, 0.0F, true));

        bone42 = new ModelRenderer(this);
        bone42.setRotationPoint(0.0F, -14.1964F, 58.4679F);
        ironsights.addChild(bone42);
        setRotationAngle(bone42, 0.0F, 0.0F, -0.7854F);
        bone42.cubeList.add(new ModelBox(bone42, 0, 0, -0.5F, -0.5F, -7.5F, 1, 1, 1, 0.0F, false));

        bone40 = new ModelRenderer(this);
        bone40.setRotationPoint(-1.0F, -14.0964F, 59.9F);
        ironsights.addChild(bone40);
        setRotationAngle(bone40, 0.5236F, 0.0F, 0.0F);
        bone40.cubeList.add(new ModelBox(bone40, 0, 0, -0.5F, -4.683F, -6.3792F, 1, 1, 1, 0.0F, false));
        bone40.cubeList.add(new ModelBox(bone40, 0, 0, 1.5F, -4.683F, -6.3792F, 1, 1, 1, 0.0F, true));
        bone40.cubeList.add(new ModelBox(bone40, 0, 0, -0.5F, -6.049F, -7.7452F, 1, 2, 1, 0.0F, false));
        bone40.cubeList.add(new ModelBox(bone40, 0, 0, 1.5F, -6.049F, -7.7452F, 1, 2, 1, 0.0F, true));
    }
}

package dev.toma.pubgmc.client.models.weapons;

import dev.toma.pubgmc.animation.HeldAnimation;
import dev.toma.pubgmc.animation.HeldAnimation.HeldStyle;
import dev.toma.pubgmc.animation.ReloadAnimation;
import dev.toma.pubgmc.animation.ReloadAnimation.ReloadStyle;
import dev.toma.pubgmc.client.util.ModelTransformationHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.item.ItemStack;

/**
 * @author OfficialMajonaise
 */
public class ModelDeagle extends ModelGun {

    private final ModelRenderer slide;
    private final ModelRenderer bone2;
    private final ModelRenderer bone21;
    private final ModelRenderer bone22;
    private final ModelRenderer bone4;
    private final ModelRenderer bone3;
    private final ModelRenderer bone6;
    private final ModelRenderer bone30;
    private final ModelRenderer bone17;
    private final ModelRenderer bone18;
    private final ModelRenderer bone23;
    private final ModelRenderer bone9;
    private final ModelRenderer bone;
    private final ModelRenderer hammer;
    private final ModelRenderer bone12;
    private final ModelRenderer bone11;
    private final ModelRenderer bone29;
    private final ModelRenderer deagle;
    private final ModelRenderer bone27;
    private final ModelRenderer bone5;
    private final ModelRenderer bone28;
    private final ModelRenderer bone8;
    private final ModelRenderer bone7;
    private final ModelRenderer bone10;
    private final ModelRenderer bone15;
    private final ModelRenderer rail;
    private final ModelRenderer bone25;
    private final ModelRenderer bone24;
    private final ModelRenderer bone26;
    private final ModelRenderer bone19;
    private final ModelRenderer bone20;
    private final ModelRenderer magazine;

    @Override
    public void initAnimations() {
        initAimAnimation(-0.561f, 0.268f, -0.1f, 2.0F);
        initAimingAnimationStates(0.268f, 0.165f, 0.268f);
        heldAnimation = new HeldAnimation(HeldStyle.SMALL);
        reloadAnimation = new ReloadAnimation(magazine, ReloadStyle.MAGAZINE).withSpeed(1.7F);
    }

    @Override
    public void render(ItemStack stack, ItemCameraTransforms.TransformType transformType) {
        EntityPlayerSP player = Minecraft.getMinecraft().player;
        if (player != null) {
            GlStateManager.pushMatrix();
            this.renderDeagle(stack);
            GlStateManager.popMatrix();
        }
    }

    private void renderDeagle(ItemStack stack) {
        GlStateManager.pushMatrix();
        ModelTransformationHelper.defaultPistolTransform();
        GlStateManager.scale(0.5, 0.5, 0.5);
        GlStateManager.translate(-0.15, 20.0, 10.0);
        deagle.render(1f);
        magazine.render(1f);
        slide.render(1.0F);
        hammer.render(1.0F);
        if(hasScopeAtachment(stack)) rail.render(1f);
        GlStateManager.popMatrix();
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }

    public ModelDeagle() {
        textureWidth = 512;
        textureHeight = 512;

        slide = new ModelRenderer(this);
        slide.setRotationPoint(0.5F, 24.0F, -9.0F);
        slide.cubeList.add(new ModelBox(slide, 2, 18, -3.5F, -20.0F, -26.0F, 6, 4, 10, 0.0F, false));
        slide.cubeList.add(new ModelBox(slide, 0, 0, -0.9142F, -16.5858F, -26.0F, 2, 2, 10, 0.0F, true));
        slide.cubeList.add(new ModelBox(slide, 0, 0, -2.0858F, -16.5858F, -26.0F, 2, 2, 10, 0.0F, true));
        slide.cubeList.add(new ModelBox(slide, 0, 0, -4.5F, -20.0F, -26.0F, 8, 2, 19, 0.0F, false));
        slide.cubeList.add(new ModelBox(slide, 0, 0, -4.5F, -20.0F, -7.0F, 8, 2, 11, 0.0F, false));
        slide.cubeList.add(new ModelBox(slide, 0, 0, -4.5F, -23.44F, -2.0F, 8, 4, 14, 0.0F, false));
        slide.cubeList.add(new ModelBox(slide, 0, 0, -4.5F, -19.44F, 10.0F, 8, 1, 3, 0.0F, false));
        slide.cubeList.add(new ModelBox(slide, 0, 0, -3.0F, -19.44F, 12.616F, 5, 1, 1, 0.0F, false));
        slide.cubeList.add(new ModelBox(slide, 0, 0, -0.9495F, -25.8895F, -2.0F, 2, 2, 14, 0.0F, false));
        slide.cubeList.add(new ModelBox(slide, 0, 0, -2.0505F, -25.8895F, -2.0F, 2, 2, 14, 0.0F, true));
        slide.cubeList.add(new ModelBox(slide, 0, 0, -1.5F, -24.976F, -26.0F, 2, 2, 24, 0.0F, false));
        slide.cubeList.add(new ModelBox(slide, 8, 83, -2.0F, -25.592F, -25.0F, 3, 1, 5, 0.0F, false));
        slide.cubeList.add(new ModelBox(slide, 12, 75, -1.5F, -26.592F, -24.68F, 2, 1, 3, 0.0F, false));
        slide.cubeList.add(new ModelBox(slide, 23, 77, -1.5F, -27.592F, -24.28F, 2, 1, 2, 0.0F, false));
        slide.cubeList.add(new ModelBox(slide, 23, 77, -1.0F, -28.16F, -23.68F, 1, 1, 1, 0.0F, false));
        slide.cubeList.add(new ModelBox(slide, 23, 77, -1.0F, -28.08F, -24.6F, 1, 2, 1, 0.0F, false));
        slide.cubeList.add(new ModelBox(slide, 23, 77, -1.0F, -26.8714F, -22.2737F, 1, 2, 1, 0.0F, false));
        slide.cubeList.add(new ModelBox(slide, 17, 82, -1.5F, -26.416F, 9.6F, 2, 1, 1, 0.0F, false));
        slide.cubeList.add(new ModelBox(slide, 10, 80, -3.5F, -26.304F, 7.8679F, 6, 1, 2, 0.0F, true));
        slide.cubeList.add(new ModelBox(slide, 17, 82, 0.5F, -27.304F, 9.6F, 2, 2, 1, 0.0F, false));
        slide.cubeList.add(new ModelBox(slide, 17, 82, 0.952F, -26.3594F, 10.1823F, 1, 1, 1, 0.0F, false));
        slide.cubeList.add(new ModelBox(slide, 17, 82, -3.048F, -26.3594F, 10.1823F, 1, 1, 1, 0.0F, false));
        slide.cubeList.add(new ModelBox(slide, 17, 82, -3.5F, -27.304F, 9.6F, 2, 2, 1, 0.0F, true));

        bone2 = new ModelRenderer(this);
        bone2.setRotationPoint(-0.5F, 0.0F, 0.0F);
        slide.addChild(bone2);
        setRotationAngle(bone2, 0.0F, 0.0F, 0.7854F);
        bone2.cubeList.add(new ModelBox(bone2, 0, 0, -13.435F, -11.1924F, -26.0F, 2, 2, 10, 0.0F, false));
        bone2.cubeList.add(new ModelBox(bone2, 0, 0, -15.5563F, -11.8995F, -26.0F, 2, 2, 8, 0.0F, false));
        bone2.cubeList.add(new ModelBox(bone2, 0, 0, -11.8995F, -15.5563F, -26.0F, 2, 2, 8, 0.0F, false));
        bone2.cubeList.add(new ModelBox(bone2, 0, 0, -11.1924F, -13.435F, -26.0F, 2, 2, 10, 0.0F, false));

        bone21 = new ModelRenderer(this);
        bone21.setRotationPoint(-14.5563F, -10.8995F, -22.0F);
        bone2.addChild(bone21);
        setRotationAngle(bone21, 0.4363F, 0.0F, 0.0F);
        bone21.cubeList.add(new ModelBox(bone21, 0, 0, -1.0F, 1.5968F, 3.2026F, 2, 1, 3, 0.0F, false));

        bone22 = new ModelRenderer(this);
        bone22.setRotationPoint(-10.8995F, -14.5563F, -22.0F);
        bone2.addChild(bone22);
        setRotationAngle(bone22, 0.0F, -0.4363F, 0.0F);
        bone22.cubeList.add(new ModelBox(bone22, 0, 0, 1.5968F, -1.0F, 3.2026F, 1, 2, 2, 0.0F, false));

        bone4 = new ModelRenderer(this);
        bone4.setRotationPoint(-0.5F, -23.0F, -20.0F);
        slide.addChild(bone4);
        setRotationAngle(bone4, 0.0F, 0.0F, 0.2618F);
        bone4.cubeList.add(new ModelBox(bone4, 0, 0, -2.8916F, -0.2356F, 6.0F, 2, 4, 12, 0.0F, true));
        bone4.cubeList.add(new ModelBox(bone4, 0, 0, -2.8916F, -0.2356F, -6.0F, 2, 4, 12, 0.0F, true));
        bone4.cubeList.add(new ModelBox(bone4, 30, 100, -3.0621F, 1.2407F, 6.544F, 2, 2, 1, 0.0F, true));
        bone4.cubeList.add(new ModelBox(bone4, 30, 100, -3.0621F, 1.2407F, -3.056F, 2, 2, 1, 0.0F, true));
        bone4.cubeList.add(new ModelBox(bone4, 30, 100, -3.0621F, 1.2407F, 4.144F, 2, 2, 1, 0.0F, true));
        bone4.cubeList.add(new ModelBox(bone4, 30, 100, -3.0621F, 1.2407F, -5.456F, 2, 2, 1, 0.0F, true));
        bone4.cubeList.add(new ModelBox(bone4, 30, 100, -3.0621F, 1.2407F, 8.944F, 2, 2, 1, 0.0F, true));
        bone4.cubeList.add(new ModelBox(bone4, 30, 100, -3.0621F, 1.2407F, -0.656F, 2, 2, 1, 0.0F, true));
        bone4.cubeList.add(new ModelBox(bone4, 30, 100, -3.0621F, 1.2407F, 13.744F, 2, 2, 1, 0.0F, true));
        bone4.cubeList.add(new ModelBox(bone4, 30, 100, -3.0621F, 1.2407F, 11.344F, 2, 2, 1, 0.0F, true));
        bone4.cubeList.add(new ModelBox(bone4, 30, 100, -3.0621F, 1.2407F, 1.744F, 2, 2, 1, 0.0F, true));
        bone4.cubeList.add(new ModelBox(bone4, 30, 100, -3.0621F, 1.2407F, 16.144F, 2, 2, 1, 0.0F, true));
        bone4.cubeList.add(new ModelBox(bone4, 0, 0, 0.7498F, -3.1923F, 18.0F, 2, 2, 14, 0.0F, true));
        bone4.cubeList.add(new ModelBox(bone4, 0, 0, -3.9776F, -1.3897F, 18.0F, 2, 2, 14, 0.0F, true));

        bone3 = new ModelRenderer(this);
        bone3.setRotationPoint(-0.5F, -23.0F, -20.0F);
        slide.addChild(bone3);
        setRotationAngle(bone3, 0.0F, 0.0F, -0.2618F);
        bone3.cubeList.add(new ModelBox(bone3, 0, 0, 0.8916F, -0.2356F, 6.0F, 2, 4, 12, 0.0F, false));
        bone3.cubeList.add(new ModelBox(bone3, 0, 0, 0.8916F, -0.2356F, -6.0F, 2, 4, 12, 0.0F, false));
        bone3.cubeList.add(new ModelBox(bone3, 30, 100, 1.0621F, 1.2407F, 6.544F, 2, 2, 1, 0.0F, false));
        bone3.cubeList.add(new ModelBox(bone3, 30, 100, 1.0621F, 1.2407F, -3.056F, 2, 2, 1, 0.0F, false));
        bone3.cubeList.add(new ModelBox(bone3, 30, 100, 1.0621F, 1.2407F, 4.144F, 2, 2, 1, 0.0F, false));
        bone3.cubeList.add(new ModelBox(bone3, 30, 100, 1.0621F, 1.2407F, -5.456F, 2, 2, 1, 0.0F, false));
        bone3.cubeList.add(new ModelBox(bone3, 30, 100, 1.0621F, 1.2407F, 8.944F, 2, 2, 1, 0.0F, false));
        bone3.cubeList.add(new ModelBox(bone3, 30, 100, 1.0621F, 1.2407F, -0.656F, 2, 2, 1, 0.0F, false));
        bone3.cubeList.add(new ModelBox(bone3, 30, 100, 1.0621F, 1.2407F, 13.744F, 2, 2, 1, 0.0F, false));
        bone3.cubeList.add(new ModelBox(bone3, 30, 100, 1.0621F, 1.2407F, 11.344F, 2, 2, 1, 0.0F, false));
        bone3.cubeList.add(new ModelBox(bone3, 30, 100, 1.0621F, 1.2407F, 1.744F, 2, 2, 1, 0.0F, false));
        bone3.cubeList.add(new ModelBox(bone3, 30, 100, 1.0621F, 1.2407F, 16.144F, 2, 2, 1, 0.0F, false));
        bone3.cubeList.add(new ModelBox(bone3, 7, 22, -2.7498F, -3.1923F, 18.0F, 2, 2, 14, 0.0F, false));
        bone3.cubeList.add(new ModelBox(bone3, 0, 0, 1.9776F, -1.3897F, 18.0F, 2, 2, 14, 0.0F, false));

        bone6 = new ModelRenderer(this);
        bone6.setRotationPoint(-0.34F, -25.4F, -20.0F);
        slide.addChild(bone6);
        setRotationAngle(bone6, 0.0F, 0.0F, 0.5236F);
        bone6.cubeList.add(new ModelBox(bone6, 0, 0, 0.9395F, -0.0528F, -6.0F, 2, 2, 24, 0.0F, false));
        bone6.cubeList.add(new ModelBox(bone6, 0, 0, 4.0255F, 2.7565F, -6.0F, 2, 2, 24, 0.0F, false));

        bone30 = new ModelRenderer(this);
        bone30.setRotationPoint(-0.66F, -25.4F, -20.0F);
        slide.addChild(bone30);
        setRotationAngle(bone30, 0.0F, 0.0F, -0.5236F);
        bone30.cubeList.add(new ModelBox(bone30, 0, 0, -2.9395F, -0.0528F, -6.0F, 2, 2, 24, 0.0F, true));
        bone30.cubeList.add(new ModelBox(bone30, 0, 0, -6.0255F, 2.7565F, -6.0F, 2, 2, 24, 0.0F, true));

        bone17 = new ModelRenderer(this);
        bone17.setRotationPoint(-0.5F, -27.16F, -19.68F);
        slide.addChild(bone17);
        setRotationAngle(bone17, -0.4363F, 0.0F, 0.0F);
        bone17.cubeList.add(new ModelBox(bone17, 23, 77, -0.5F, 0.3615F, -3.1415F, 1, 1, 1, 0.0F, false));

        bone18 = new ModelRenderer(this);
        bone18.setRotationPoint(-0.5F, -27.16F, -19.68F);
        slide.addChild(bone18);
        setRotationAngle(bone18, -1.0472F, 0.0F, 0.0F);
        bone18.cubeList.add(new ModelBox(bone18, 23, 77, -0.5F, 1.5245F, -1.5469F, 1, 1, 1, 0.0F, false));
        bone18.cubeList.add(new ModelBox(bone18, 41, 489, -0.5F, 1.5072F, -1.5369F, 1, 0, 1, 0.0F, false));
        bone18.cubeList.add(new ModelBox(bone18, 17, 82, 1.452F, -26.3272F, 15.1245F, 1, 1, 1, 0.0F, false));
        bone18.cubeList.add(new ModelBox(bone18, 41, 489, 1.452F, -26.3411F, 15.1325F, 1, 0, 1, 0.0F, false));
        bone18.cubeList.add(new ModelBox(bone18, 17, 82, -2.548F, -26.3272F, 15.1245F, 1, 1, 1, 0.0F, false));
        bone18.cubeList.add(new ModelBox(bone18, 41, 489, -2.548F, -26.3411F, 15.1325F, 1, 0, 1, 0.0F, false));
        bone18.cubeList.add(new ModelBox(bone18, 10, 80, 2.0F, -25.4292F, 14.5153F, 1, 2, 2, 0.0F, false));
        bone18.cubeList.add(new ModelBox(bone18, 17, 82, -3.0F, -25.4292F, 14.5153F, 1, 2, 2, 0.0F, false));

        bone23 = new ModelRenderer(this);
        bone23.setRotationPoint(-0.5F, -18.94F, 11.5F);
        slide.addChild(bone23);
        setRotationAngle(bone23, -0.2618F, 0.0F, 0.0F);
        bone23.cubeList.add(new ModelBox(bone23, 0, 0, -4.0F, -0.1288F, -4.3195F, 8, 1, 3, 0.0F, false));

        bone9 = new ModelRenderer(this);
        bone9.setRotationPoint(1.5F, 0.896F, 3.088F);
        slide.addChild(bone9);
        setRotationAngle(bone9, 0.1745F, 0.0F, 0.0F);
        bone9.cubeList.add(new ModelBox(bone9, 0, 0, -6.0F, -22.4039F, 12.2048F, 8, 4, 1, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 0, 0, -1.5F, -24.2927F, 12.8351F, 2, 6, 1, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 0, 0, -4.5F, -24.2927F, 12.8351F, 2, 6, 1, 0.0F, true));
        bone9.cubeList.add(new ModelBox(bone9, 0, 0, -2.5F, -20.2927F, 12.8351F, 1, 2, 1, 0.0F, true));
        bone9.cubeList.add(new ModelBox(bone9, 96, 39, -2.5F, -24.167F, 13.1608F, 1, 1, 1, 0.0F, true));

        bone = new ModelRenderer(this);
        bone.setRotationPoint(1.5F, 0.0F, 0.0F);
        slide.addChild(bone);
        setRotationAngle(bone, -0.2618F, 0.0F, 0.0F);
        bone.cubeList.add(new ModelBox(bone, 0, 0, -6.0F, -20.4219F, -1.795F, 8, 2, 1, 0.0F, false));

        hammer = new ModelRenderer(this);
        hammer.setRotationPoint(0.0F, 1.972F, 3.604F);


        bone12 = new ModelRenderer(this);
        bone12.setRotationPoint(0.0F, -2.16F, 1.92F);
        hammer.addChild(bone12);
        setRotationAngle(bone12, -0.6109F, 0.0F, 0.0F);
        bone12.cubeList.add(new ModelBox(bone12, 94, 41, -0.5F, 0.5718F, -0.9747F, 1, 2, 1, 0.0F, false));
        bone12.cubeList.add(new ModelBox(bone12, 94, 41, -0.5F, -0.4727F, -0.6447F, 1, 1, 1, 0.0F, false));

        bone11 = new ModelRenderer(this);
        bone11.setRotationPoint(0.0F, 0.0F, 0.0F);
        hammer.addChild(bone11);
        setRotationAngle(bone11, -0.2618F, 0.0F, 0.0F);
        bone11.cubeList.add(new ModelBox(bone11, 94, 41, -0.5F, -0.5F, -0.5F, 1, 1, 1, 0.0F, false));
        bone11.cubeList.add(new ModelBox(bone11, 108, 38, -0.5F, -2.9259F, 0.4668F, 1, 1, 1, 0.0F, false));
        bone11.cubeList.add(new ModelBox(bone11, 94, 41, -0.5F, -3.248F, -0.1486F, 1, 1, 1, 0.0F, false));
        bone11.cubeList.add(new ModelBox(bone11, 94, 41, -0.5F, -3.1446F, 0.8048F, 1, 1, 1, 0.0F, false));

        bone29 = new ModelRenderer(this);
        bone29.setRotationPoint(2.0F, 22.924F, -9.516F);
        hammer.addChild(bone29);
        setRotationAngle(bone29, 0.1745F, 0.0F, 0.0F);
        bone29.cubeList.add(new ModelBox(bone29, 94, 41, -2.5F, -23.0545F, 15.4886F, 1, 1, 1, 0.0F, true));

        deagle = new ModelRenderer(this);
        deagle.setRotationPoint(0.0F, 24.0F, -9.0F);
        deagle.cubeList.add(new ModelBox(deagle, 76, 26, -3.0F, -20.0F, -6.0F, 6, 4, 17, 0.0F, false));
        deagle.cubeList.add(new ModelBox(deagle, 69, 16, -3.0F, -20.0F, -16.0F, 6, 4, 10, 0.0F, false));
        deagle.cubeList.add(new ModelBox(deagle, 84, 36, -1.5F, -18.744F, -26.472F, 3, 3, 11, 0.0F, false));
        deagle.cubeList.add(new ModelBox(deagle, 84, 36, -0.5F, -17.744F, -26.864F, 1, 1, 1, 0.0F, false));
        deagle.cubeList.add(new ModelBox(deagle, 101, 30, -2.0F, -19.776F, 10.36F, 4, 2, 3, 0.0F, false));
        deagle.cubeList.add(new ModelBox(deagle, 101, 30, -3.0F, -20.032F, 10.36F, 6, 2, 2, 0.0F, false));
        deagle.cubeList.add(new ModelBox(deagle, 84, 25, -1.0F, -24.0924F, -28.264F, 2, 1, 16, 0.0F, true));
        deagle.cubeList.add(new ModelBox(deagle, 84, 25, 1.4142F, -22.6782F, -28.264F, 1, 2, 16, 0.0F, true));
        deagle.cubeList.add(new ModelBox(deagle, 84, 25, -2.4142F, -22.6782F, -28.264F, 1, 2, 16, 0.0F, false));
        deagle.cubeList.add(new ModelBox(deagle, 84, 25, -1.0F, -20.264F, -28.264F, 2, 1, 16, 0.0F, false));
        deagle.cubeList.add(new ModelBox(deagle, 20, 79, -1.5F, -23.264F, -26.096F, 3, 3, 1, 0.0F, false));
        deagle.cubeList.add(new ModelBox(deagle, 73, 28, -3.7321F, -15.6F, 4.0F, 4, 1, 6, 0.0F, true));
        deagle.cubeList.add(new ModelBox(deagle, 99, 38, -3.7321F, -17.6F, 10.0F, 4, 3, 2, 0.0F, true));
        deagle.cubeList.add(new ModelBox(deagle, 93, 25, -3.7321F, -17.6F, -4.0F, 4, 3, 8, 0.0F, true));
        deagle.cubeList.add(new ModelBox(deagle, 80, 24, -3.7321F, -17.6F, -16.0F, 2, 1, 12, 0.0F, true));
        deagle.cubeList.add(new ModelBox(deagle, 73, 28, -3.1881F, -16.08F, 4.0F, 2, 1, 6, 0.0F, true));
        deagle.cubeList.add(new ModelBox(deagle, 90, 27, 1.1881F, -16.08F, 4.0F, 2, 1, 6, 0.0F, false));
        deagle.cubeList.add(new ModelBox(deagle, 99, 38, -0.2679F, -15.6F, 4.0F, 4, 1, 6, 0.0F, false));
        deagle.cubeList.add(new ModelBox(deagle, 99, 38, -0.2679F, -17.6F, 10.0F, 4, 3, 2, 0.0F, false));
        deagle.cubeList.add(new ModelBox(deagle, 81, 24, -0.2679F, -17.6F, -4.0F, 4, 3, 8, 0.0F, false));
        deagle.cubeList.add(new ModelBox(deagle, 44, 174, 2.8321F, -17.5F, -5.1F, 1, 1, 9, 0.0F, false));
        deagle.cubeList.add(new ModelBox(deagle, 44, 174, -3.8321F, -17.5F, -5.1F, 1, 1, 9, 0.0F, true));
        deagle.cubeList.add(new ModelBox(deagle, 44, 174, 2.8001F, -16.6F, 0.08F, 1, 1, 1, 0.0F, false));
        deagle.cubeList.add(new ModelBox(deagle, 44, 174, -3.8001F, -16.6F, 0.08F, 1, 1, 1, 0.0F, true));
        deagle.cubeList.add(new ModelBox(deagle, 44, 174, 2.8001F, -16.552F, 2.848F, 1, 2, 1, 0.0F, false));
        deagle.cubeList.add(new ModelBox(deagle, 44, 174, -3.8001F, -16.552F, 2.848F, 1, 2, 1, 0.0F, true));
        deagle.cubeList.add(new ModelBox(deagle, 44, 174, 2.8001F, -17.624F, -3.88F, 1, 2, 1, 0.0F, false));
        deagle.cubeList.add(new ModelBox(deagle, 44, 174, -3.8001F, -17.624F, -3.88F, 1, 2, 1, 0.0F, true));
        deagle.cubeList.add(new ModelBox(deagle, 44, 174, 2.8001F, -16.6F, -1.084F, 1, 1, 1, 0.0F, false));
        deagle.cubeList.add(new ModelBox(deagle, 44, 174, -3.8001F, -16.6F, -1.084F, 1, 1, 1, 0.0F, true));
        deagle.cubeList.add(new ModelBox(deagle, 44, 174, 2.8321F, -15.7F, -3.9F, 1, 1, 7, 0.0F, false));
        deagle.cubeList.add(new ModelBox(deagle, 44, 174, -3.8321F, -15.7F, -3.9F, 1, 1, 7, 0.0F, true));
        deagle.cubeList.add(new ModelBox(deagle, 81, 24, -2.5F, -15.8679F, -7.0F, 5, 1, 3, 0.0F, false));
        deagle.cubeList.add(new ModelBox(deagle, 81, 24, -1.5F, -14.8679F, -7.0F, 3, 4, 1, 0.0F, false));
        deagle.cubeList.add(new ModelBox(deagle, 81, 24, -1.0F, -10.8419F, -4.1809F, 2, 1, 6, 0.0F, false));
        deagle.cubeList.add(new ModelBox(deagle, 80, 25, 1.7321F, -17.6F, -16.0F, 2, 1, 12, 0.0F, false));
        deagle.cubeList.add(new ModelBox(deagle, 81, 24, -0.768F, -20.0F, 2.0F, 4, 2, 9, 0.0F, false));
        deagle.cubeList.add(new ModelBox(deagle, 81, 27, -3.232F, -20.0F, 2.0F, 4, 2, 9, 0.0F, true));
        deagle.cubeList.add(new ModelBox(deagle, 105, 25, -1.0F, -15.8679F, -16.0F, 2, 2, 9, 0.0F, true));

        bone27 = new ModelRenderer(this);
        bone27.setRotationPoint(0.16F, -25.4F, -20.0F);
        deagle.addChild(bone27);
        setRotationAngle(bone27, 0.0F, 0.0F, 0.5236F);
        bone27.cubeList.add(new ModelBox(bone27, 81, 22, 5.4935F, 5.835F, 4.0F, 2, 2, 12, 0.0F, false));
        bone27.cubeList.add(new ModelBox(bone27, 100, 23, 2.7614F, 8.567F, 4.0F, 2, 2, 9, 0.0F, false));

        bone5 = new ModelRenderer(this);
        bone5.setRotationPoint(-0.16F, -25.4F, -20.0F);
        deagle.addChild(bone5);
        setRotationAngle(bone5, 0.0F, 0.0F, -0.5236F);
        bone5.cubeList.add(new ModelBox(bone5, 80, 24, -7.4935F, 5.835F, 4.0F, 2, 2, 12, 0.0F, true));
        bone5.cubeList.add(new ModelBox(bone5, 105, 25, -4.7614F, 8.567F, 4.0F, 2, 2, 9, 0.0F, true));

        bone28 = new ModelRenderer(this);
        bone28.setRotationPoint(0.0F, -22.028F, 12.604F);
        deagle.addChild(bone28);
        setRotationAngle(bone28, -0.2618F, 0.0F, 0.0F);
        bone28.cubeList.add(new ModelBox(bone28, 99, 38, -2.0F, 7.4998F, -1.0525F, 4, 3, 2, 0.0F, false));

        bone8 = new ModelRenderer(this);
        bone8.setRotationPoint(0.0F, -21.264F, -22.264F);
        deagle.addChild(bone8);
        setRotationAngle(bone8, 0.0F, 0.0F, -0.7854F);
        bone8.cubeList.add(new ModelBox(bone8, 84, 25, -2.1213F, -1.2929F, -6.0F, 1, 2, 16, 0.0F, false));
        bone8.cubeList.add(new ModelBox(bone8, 84, 25, -0.7071F, -2.7071F, -6.0F, 2, 1, 16, 0.0F, false));
        bone8.cubeList.add(new ModelBox(bone8, 84, 25, -0.7071F, 1.1213F, -6.0F, 2, 1, 16, 0.0F, false));
        bone8.cubeList.add(new ModelBox(bone8, 84, 25, 1.7071F, -1.2929F, -6.0F, 1, 2, 16, 0.0F, false));

        bone7 = new ModelRenderer(this);
        bone7.setRotationPoint(-1.2679F, 0.0F, 0.0F);
        deagle.addChild(bone7);
        setRotationAngle(bone7, 0.2618F, 0.0F, 0.0F);
        bone7.cubeList.add(new ModelBox(bone7, 93, 32, -1.7321F, -14.0732F, 4.4856F, 6, 14, 9, 0.0F, true));

        bone10 = new ModelRenderer(this);
        bone10.setRotationPoint(0.0F, -17.408F, 8.168F);
        deagle.addChild(bone10);
        setRotationAngle(bone10, -0.0524F, 0.0F, 0.0F);
        bone10.cubeList.add(new ModelBox(bone10, 106, 97, -4.2F, -0.336F, -0.56F, 2, 2, 2, 0.0F, false));
        bone10.cubeList.add(new ModelBox(bone10, 13, 85, 2.2F, -0.336F, -0.56F, 2, 2, 2, 0.0F, true));
        bone10.cubeList.add(new ModelBox(bone10, 106, 97, -4.3F, 0.164F, -0.06F, 1, 1, 1, 0.0F, false));
        bone10.cubeList.add(new ModelBox(bone10, 111, 41, 3.3F, 0.164F, -0.06F, 1, 1, 1, 0.0F, true));
        bone10.cubeList.add(new ModelBox(bone10, 106, 97, -4.2F, -0.336F, -3.46F, 1, 1, 3, 0.0F, false));
        bone10.cubeList.add(new ModelBox(bone10, 13, 85, 3.2F, -0.336F, -3.46F, 1, 1, 3, 0.0F, true));

        bone15 = new ModelRenderer(this);
        bone15.setRotationPoint(0.0F, -13.3679F, -6.5F);
        deagle.addChild(bone15);
        setRotationAngle(bone15, 1.2217F, 0.0F, 0.0F);
        bone15.cubeList.add(new ModelBox(bone15, 81, 24, -1.5F, 0.3852F, -2.5202F, 3, 2, 1, 0.0F, false));
        bone15.cubeList.add(new ModelBox(bone15, 81, 24, -1.0F, 2.3852F, -2.5202F, 2, 1, 1, 0.0F, false));
        bone15.cubeList.add(new ModelBox(bone15, 81, 24, -1.0F, 8.0234F, -0.4681F, 2, 1, 2, 0.0F, false));

        rail = new ModelRenderer(this);
        rail.setRotationPoint(-1.0F, 23.872F, 6.0F);
        rail.cubeList.add(new ModelBox(rail, 0, 68, 0.0F, -28.416F, -10.4F, 2, 1, 1, 0.0F, false));
        rail.cubeList.add(new ModelBox(rail, 0, 68, 0.0F, -28.416F, -18.4F, 2, 1, 1, 0.0F, false));
        rail.cubeList.add(new ModelBox(rail, 0, 68, 0.0F, -28.416F, -14.4F, 2, 1, 1, 0.0F, false));
        rail.cubeList.add(new ModelBox(rail, 0, 68, 0.0F, -28.416F, -26.4F, 2, 1, 1, 0.0F, false));
        rail.cubeList.add(new ModelBox(rail, 0, 68, 0.0F, -28.416F, -22.4F, 2, 1, 1, 0.0F, false));
        rail.cubeList.add(new ModelBox(rail, 0, 68, 0.0F, -28.416F, -12.4F, 2, 1, 1, 0.0F, false));
        rail.cubeList.add(new ModelBox(rail, 0, 68, 0.0F, -28.416F, -20.4F, 2, 1, 1, 0.0F, false));
        rail.cubeList.add(new ModelBox(rail, 0, 68, 0.0F, -28.416F, -16.4F, 2, 1, 1, 0.0F, false));
        rail.cubeList.add(new ModelBox(rail, 0, 68, 0.0F, -28.416F, -28.4F, 2, 1, 1, 0.0F, false));
        rail.cubeList.add(new ModelBox(rail, 0, 68, 0.0F, -28.416F, -24.4F, 2, 1, 1, 0.0F, false));
        rail.cubeList.add(new ModelBox(rail, 0, 68, -0.8669F, -27.9155F, -11.4F, 2, 0, 1, 0.0F, false));
        rail.cubeList.add(new ModelBox(rail, 0, 68, -0.8669F, -27.9155F, -19.4F, 2, 0, 1, 0.0F, false));
        rail.cubeList.add(new ModelBox(rail, 0, 68, -0.8669F, -27.9155F, -15.4F, 2, 0, 1, 0.0F, false));
        rail.cubeList.add(new ModelBox(rail, 0, 68, -0.8669F, -27.9155F, -27.4F, 2, 0, 1, 0.0F, false));
        rail.cubeList.add(new ModelBox(rail, 0, 68, -0.8669F, -27.9155F, -23.4F, 2, 0, 1, 0.0F, false));
        rail.cubeList.add(new ModelBox(rail, 0, 68, -0.8669F, -27.9155F, -13.4F, 2, 0, 1, 0.0F, false));
        rail.cubeList.add(new ModelBox(rail, 0, 68, -0.8669F, -27.9155F, -25.4F, 2, 0, 1, 0.0F, false));
        rail.cubeList.add(new ModelBox(rail, 0, 68, -0.8669F, -27.9155F, -21.4F, 2, 0, 1, 0.0F, false));
        rail.cubeList.add(new ModelBox(rail, 0, 68, -0.8669F, -27.9155F, -17.4F, 2, 0, 1, 0.0F, false));
        rail.cubeList.add(new ModelBox(rail, 0, 68, 0.8669F, -27.9155F, -11.4F, 2, 0, 1, 0.0F, true));
        rail.cubeList.add(new ModelBox(rail, 0, 68, 0.8669F, -27.9155F, -19.4F, 2, 0, 1, 0.0F, true));
        rail.cubeList.add(new ModelBox(rail, 0, 68, 0.8669F, -27.9155F, -15.4F, 2, 0, 1, 0.0F, true));
        rail.cubeList.add(new ModelBox(rail, 0, 68, 0.8669F, -27.9155F, -27.4F, 2, 0, 1, 0.0F, true));
        rail.cubeList.add(new ModelBox(rail, 0, 68, 0.8669F, -27.9155F, -23.4F, 2, 0, 1, 0.0F, true));
        rail.cubeList.add(new ModelBox(rail, 0, 68, 0.8669F, -27.9155F, -13.4F, 2, 0, 1, 0.0F, true));
        rail.cubeList.add(new ModelBox(rail, 0, 68, 0.8669F, -27.9155F, -25.4F, 2, 0, 1, 0.0F, true));
        rail.cubeList.add(new ModelBox(rail, 0, 68, 0.8669F, -27.9155F, -21.4F, 2, 0, 1, 0.0F, true));
        rail.cubeList.add(new ModelBox(rail, 0, 68, 0.8669F, -27.9155F, -17.4F, 2, 0, 1, 0.0F, true));
        rail.cubeList.add(new ModelBox(rail, 0, 68, -0.5F, -27.704F, -28.4F, 3, 1, 10, 0.0F, false));
        rail.cubeList.add(new ModelBox(rail, 0, 68, -0.5F, -27.704F, -18.4F, 3, 1, 9, 0.0F, false));
        rail.cubeList.add(new ModelBox(rail, 67, 105, -0.5F, -26.704F, -23.2F, 3, 1, 5, 0.0F, false));
        rail.cubeList.add(new ModelBox(rail, 67, 105, -1.0F, -26.428F, -24.2F, 4, 1, 7, 0.0F, false));
        rail.cubeList.add(new ModelBox(rail, 68, 108, -1.0F, -26.028F, -24.2F, 4, 1, 7, 0.0F, false));
        rail.cubeList.add(new ModelBox(rail, 0, 68, -2.0F, -26.228F, -24.0F, 6, 1, 1, 0.0F, false));
        rail.cubeList.add(new ModelBox(rail, 0, 68, -2.0F, -26.228F, -18.4F, 6, 1, 1, 0.0F, false));
        rail.cubeList.add(new ModelBox(rail, 0, 68, 5.1213F, -24.1067F, -18.4F, 1, 2, 1, 0.0F, true));
        rail.cubeList.add(new ModelBox(rail, 0, 68, -4.1213F, -19.4727F, -12.8378F, 1, 2, 1, 0.0F, false));
        rail.cubeList.add(new ModelBox(rail, 0, 68, -2.8213F, -16.7047F, -12.8378F, 1, 1, 1, 0.0F, false));
        rail.cubeList.add(new ModelBox(rail, 0, 68, 3.8213F, -16.7047F, -12.8378F, 1, 1, 1, 0.0F, true));
        rail.cubeList.add(new ModelBox(rail, 0, 68, 5.1213F, -19.4727F, -12.8378F, 1, 2, 1, 0.0F, true));
        rail.cubeList.add(new ModelBox(rail, 0, 68, 5.2493F, -18.8247F, -18.0218F, 1, 1, 6, 0.0F, true));
        rail.cubeList.add(new ModelBox(rail, 0, 68, -4.2493F, -18.8247F, -18.0218F, 1, 1, 6, 0.0F, false));
        rail.cubeList.add(new ModelBox(rail, 0, 68, -4.2493F, -23.8647F, -23.8618F, 1, 1, 6, 0.0F, false));
        rail.cubeList.add(new ModelBox(rail, 0, 68, 5.2493F, -23.8647F, -23.8618F, 1, 1, 6, 0.0F, true));
        rail.cubeList.add(new ModelBox(rail, 0, 68, 3.0253F, -26.3447F, -23.8618F, 1, 1, 6, 0.0F, true));
        rail.cubeList.add(new ModelBox(rail, 0, 68, -2.0253F, -26.3447F, -23.8618F, 1, 1, 6, 0.0F, false));
        rail.cubeList.add(new ModelBox(rail, 0, 68, -4.1213F, -19.4727F, -18.4378F, 1, 2, 1, 0.0F, false));
        rail.cubeList.add(new ModelBox(rail, 0, 68, -2.8213F, -16.7047F, -18.4378F, 1, 1, 1, 0.0F, false));
        rail.cubeList.add(new ModelBox(rail, 0, 68, 3.8213F, -16.7047F, -18.4378F, 1, 1, 1, 0.0F, true));
        rail.cubeList.add(new ModelBox(rail, 0, 68, 5.1213F, -19.4727F, -18.4378F, 1, 2, 1, 0.0F, true));
        rail.cubeList.add(new ModelBox(rail, 0, 68, -4.1213F, -24.1067F, -18.4F, 1, 2, 1, 0.0F, false));
        rail.cubeList.add(new ModelBox(rail, 0, 68, 5.1213F, -24.1067F, -24.0F, 1, 2, 1, 0.0F, true));
        rail.cubeList.add(new ModelBox(rail, 0, 68, -4.1213F, -24.1067F, -24.0F, 1, 2, 1, 0.0F, false));

        bone25 = new ModelRenderer(this);
        bone25.setRotationPoint(1.0F, -25.728F, -26.7F);
        rail.addChild(bone25);
        setRotationAngle(bone25, 0.0F, 0.0F, 0.7854F);
        bone25.cubeList.add(new ModelBox(bone25, 0, 68, 1.7678F, -2.4749F, 2.7F, 3, 1, 1, 0.0F, true));
        bone25.cubeList.add(new ModelBox(bone25, 0, 68, 8.4587F, 2.2161F, 8.2622F, 1, 2, 1, 0.0F, true));
        bone25.cubeList.add(new ModelBox(bone25, 0, 68, 1.7678F, -2.4749F, 8.3F, 3, 1, 1, 0.0F, true));
        bone25.cubeList.add(new ModelBox(bone25, 0, 68, 8.4587F, 2.2161F, 13.8622F, 1, 2, 1, 0.0F, true));

        bone24 = new ModelRenderer(this);
        bone24.setRotationPoint(1.0F, -25.728F, -26.7F);
        rail.addChild(bone24);
        setRotationAngle(bone24, 0.0F, 0.0F, -0.7854F);
        bone24.cubeList.add(new ModelBox(bone24, 0, 68, -4.7678F, -2.4749F, 2.7F, 3, 1, 1, 0.0F, false));
        bone24.cubeList.add(new ModelBox(bone24, 0, 68, -9.4587F, 2.2161F, 8.2622F, 1, 2, 1, 0.0F, false));
        bone24.cubeList.add(new ModelBox(bone24, 0, 68, -4.7678F, -2.4749F, 8.3F, 3, 1, 1, 0.0F, false));
        bone24.cubeList.add(new ModelBox(bone24, 0, 68, -9.4587F, 2.2161F, 13.8622F, 1, 2, 1, 0.0F, false));

        bone26 = new ModelRenderer(this);
        bone26.setRotationPoint(5.6213F, -23.6067F, -21.1F);
        rail.addChild(bone26);
        setRotationAngle(bone26, -0.5236F, 0.0F, 0.0F);
        bone26.cubeList.add(new ModelBox(bone26, 0, 68, -0.5F, -1.051F, 3.0883F, 1, 1, 7, 0.0F, true));
        bone26.cubeList.add(new ModelBox(bone26, 0, 68, -9.7426F, -1.051F, 3.0883F, 1, 1, 7, 0.0F, false));
        bone26.cubeList.add(new ModelBox(bone26, 0, 68, -0.5F, 1.749F, -1.7615F, 1, 1, 7, 0.0F, true));
        bone26.cubeList.add(new ModelBox(bone26, 0, 68, -9.7426F, 1.749F, -1.7615F, 1, 1, 7, 0.0F, false));

        bone19 = new ModelRenderer(this);
        bone19.setRotationPoint(1.0F, -27.916F, -9.9F);
        rail.addChild(bone19);
        setRotationAngle(bone19, 0.0F, 0.0F, -0.5236F);
        bone19.cubeList.add(new ModelBox(bone19, 0, 68, -1.616F, -0.933F, -0.5F, 1, 1, 1, 0.0F, false));
        bone19.cubeList.add(new ModelBox(bone19, 0, 68, -1.616F, -0.933F, -8.5F, 1, 1, 1, 0.0F, false));
        bone19.cubeList.add(new ModelBox(bone19, 0, 68, -1.616F, -0.933F, -4.5F, 1, 1, 1, 0.0F, false));
        bone19.cubeList.add(new ModelBox(bone19, 0, 68, -1.616F, -0.933F, -16.5F, 1, 1, 1, 0.0F, false));
        bone19.cubeList.add(new ModelBox(bone19, 0, 68, -1.616F, -0.933F, -12.5F, 1, 1, 1, 0.0F, false));
        bone19.cubeList.add(new ModelBox(bone19, 0, 68, -1.616F, -0.933F, -2.5F, 1, 1, 1, 0.0F, false));
        bone19.cubeList.add(new ModelBox(bone19, 0, 68, -1.616F, -0.933F, -10.5F, 1, 1, 1, 0.0F, false));
        bone19.cubeList.add(new ModelBox(bone19, 0, 68, -1.616F, -0.933F, -6.5F, 1, 1, 1, 0.0F, false));
        bone19.cubeList.add(new ModelBox(bone19, 0, 68, -1.616F, -0.933F, -18.5F, 1, 1, 1, 0.0F, false));
        bone19.cubeList.add(new ModelBox(bone19, 0, 68, -1.616F, -0.933F, -14.5F, 1, 1, 1, 0.0F, false));
        bone19.cubeList.add(new ModelBox(bone19, 0, 68, -1.616F, -0.933F, -1.5F, 0, 1, 1, 0.0F, false));
        bone19.cubeList.add(new ModelBox(bone19, 0, 68, -1.616F, -0.933F, -9.5F, 0, 1, 1, 0.0F, false));
        bone19.cubeList.add(new ModelBox(bone19, 0, 68, -1.616F, -0.933F, -5.5F, 0, 1, 1, 0.0F, false));
        bone19.cubeList.add(new ModelBox(bone19, 0, 68, -1.616F, -0.933F, -17.5F, 0, 1, 1, 0.0F, false));
        bone19.cubeList.add(new ModelBox(bone19, 0, 68, -1.616F, -0.933F, -13.5F, 0, 1, 1, 0.0F, false));
        bone19.cubeList.add(new ModelBox(bone19, 0, 68, -1.616F, -0.933F, -3.5F, 0, 1, 1, 0.0F, false));
        bone19.cubeList.add(new ModelBox(bone19, 0, 68, -1.616F, -0.933F, -15.5F, 0, 1, 1, 0.0F, false));
        bone19.cubeList.add(new ModelBox(bone19, 0, 68, -1.616F, -0.933F, -11.5F, 0, 1, 1, 0.0F, false));
        bone19.cubeList.add(new ModelBox(bone19, 0, 68, -1.616F, -0.933F, -7.5F, 0, 1, 1, 0.0F, false));

        bone20 = new ModelRenderer(this);
        bone20.setRotationPoint(1.0F, -27.916F, -9.9F);
        rail.addChild(bone20);
        setRotationAngle(bone20, 0.0F, 0.0F, 0.5236F);
        bone20.cubeList.add(new ModelBox(bone20, 0, 68, 0.616F, -0.933F, -0.5F, 1, 1, 1, 0.0F, true));
        bone20.cubeList.add(new ModelBox(bone20, 0, 68, 0.616F, -0.933F, -8.5F, 1, 1, 1, 0.0F, true));
        bone20.cubeList.add(new ModelBox(bone20, 0, 68, 0.616F, -0.933F, -4.5F, 1, 1, 1, 0.0F, true));
        bone20.cubeList.add(new ModelBox(bone20, 0, 68, 0.616F, -0.933F, -16.5F, 1, 1, 1, 0.0F, true));
        bone20.cubeList.add(new ModelBox(bone20, 0, 68, 0.616F, -0.933F, -12.5F, 1, 1, 1, 0.0F, true));
        bone20.cubeList.add(new ModelBox(bone20, 0, 68, 0.616F, -0.933F, -2.5F, 1, 1, 1, 0.0F, true));
        bone20.cubeList.add(new ModelBox(bone20, 0, 68, 0.616F, -0.933F, -10.5F, 1, 1, 1, 0.0F, true));
        bone20.cubeList.add(new ModelBox(bone20, 0, 68, 0.616F, -0.933F, -6.5F, 1, 1, 1, 0.0F, true));
        bone20.cubeList.add(new ModelBox(bone20, 0, 68, 0.616F, -0.933F, -18.5F, 1, 1, 1, 0.0F, true));
        bone20.cubeList.add(new ModelBox(bone20, 0, 68, 0.616F, -0.933F, -14.5F, 1, 1, 1, 0.0F, true));
        bone20.cubeList.add(new ModelBox(bone20, 0, 68, 1.616F, -0.933F, -1.5F, 0, 1, 1, 0.0F, true));
        bone20.cubeList.add(new ModelBox(bone20, 0, 68, 1.616F, -0.933F, -9.5F, 0, 1, 1, 0.0F, true));
        bone20.cubeList.add(new ModelBox(bone20, 0, 68, 1.616F, -0.933F, -5.5F, 0, 1, 1, 0.0F, true));
        bone20.cubeList.add(new ModelBox(bone20, 0, 68, 1.616F, -0.933F, -17.5F, 0, 1, 1, 0.0F, true));
        bone20.cubeList.add(new ModelBox(bone20, 0, 68, 1.616F, -0.933F, -13.5F, 0, 1, 1, 0.0F, true));
        bone20.cubeList.add(new ModelBox(bone20, 0, 68, 1.616F, -0.933F, -3.5F, 0, 1, 1, 0.0F, true));
        bone20.cubeList.add(new ModelBox(bone20, 0, 68, 1.616F, -0.933F, -15.5F, 0, 1, 1, 0.0F, true));
        bone20.cubeList.add(new ModelBox(bone20, 0, 68, 1.616F, -0.933F, -11.5F, 0, 1, 1, 0.0F, true));
        bone20.cubeList.add(new ModelBox(bone20, 0, 68, 1.616F, -0.933F, -7.5F, 0, 1, 1, 0.0F, true));

        magazine = new ModelRenderer(this);
        magazine.setRotationPoint(-1.2679F, 24.0F, -9.0F);
        setRotationAngle(magazine, 0.2618F, 0.0F, 0.0F);
        magazine.cubeList.add(new ModelBox(magazine, 80, 90, -1.2321F, -1.4732F, 3.8696F, 5, 2, 9, 0.0F, true));
        magazine.cubeList.add(new ModelBox(magazine, 80, 90, -1.2321F, -15.4732F, 4.8696F, 5, 14, 8, 0.0F, true));
        magazine.cubeList.add(new ModelBox(magazine, 80, 90, -1.2321F, -18.4732F, 4.8696F, 1, 3, 8, 0.0F, true));
        magazine.cubeList.add(new ModelBox(magazine, 80, 90, 2.7679F, -18.4732F, 4.8696F, 1, 3, 8, 0.0F, true));
        magazine.cubeList.add(new ModelBox(magazine, 80, 90, -0.2321F, -16.4732F, 4.8696F, 3, 1, 1, 0.0F, true));
        magazine.cubeList.add(new ModelBox(magazine, 80, 90, -0.2321F, -17.4732F, 11.8696F, 3, 2, 1, 0.0F, true));
        magazine.cubeList.add(new ModelBox(magazine, 8, 497, -0.2321F, -19.0852F, 11.2656F, 3, 3, 1, 0.0F, true));
        magazine.cubeList.add(new ModelBox(magazine, 8, 497, -0.2321F, -19.0852F, 6.6976F, 3, 3, 4, 0.0F, true));
        magazine.cubeList.add(new ModelBox(magazine, 8, 497, 0.2679F, -18.5852F, 5.4576F, 2, 2, 6, 0.0F, true));
        this.initAnimations();
    }
}

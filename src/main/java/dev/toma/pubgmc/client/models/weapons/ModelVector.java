package dev.toma.pubgmc.client.models.weapons;

import dev.toma.pubgmc.animation.HeldAnimation;
import dev.toma.pubgmc.animation.HeldAnimation.HeldStyle;
import dev.toma.pubgmc.animation.ReloadAnimation;
import dev.toma.pubgmc.client.util.ModelTransformationHelper;
import dev.toma.pubgmc.common.capability.player.PlayerDataProvider;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.item.ItemStack;

public class ModelVector extends ModelGun {

    private final ModelRenderer vector;
    private final ModelRenderer bone9;
    private final ModelRenderer bone10;
    private final ModelRenderer bone11;
    private final ModelRenderer bone6;
    private final ModelRenderer bone7;
    private final ModelRenderer bone8;
    private final ModelRenderer bone4;
    private final ModelRenderer rail;
    private final ModelRenderer bone;
    private final ModelRenderer bone3;
    private final ModelRenderer rail2;
    private final ModelRenderer bone13;
    private final ModelRenderer bone14;
    private final ModelRenderer bone2;
    private final ModelRenderer bone18;
    private final ModelRenderer bone12;
    private final ModelRenderer bone16;
    private final ModelRenderer bone17;
    private final ModelRenderer bone15;
    private final ModelRenderer bone5;
    private final ModelRenderer magazine;
    private final ModelRenderer ironsights;
    private final ModelRenderer bone19;
    private final ModelRenderer bone22;
    private final ModelRenderer bone23;
    private final ModelRenderer bone20;
    private final ModelRenderer bone21;

    @Override
    public String textureName() {
        return "g36c";
    }

    @Override
    public void initAnimations() {
        initAimAnimation(-0.56f, 0.315f, 0.225f);
        initAimingAnimationStates(0.315f, 0.275f, 0.2575f);
        heldAnimation = new HeldAnimation(HeldStyle.SMALL);
        this.reloadAnimation = new ReloadAnimation(magazine, ReloadAnimation.ReloadStyle.MAGAZINE).withSpeed(1.8F);
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
            renderVector(stack);
            GlStateManager.popMatrix();
        }
    }

    private void renderVector(ItemStack stack) {
        GlStateManager.pushMatrix();
        ModelTransformationHelper.defaultSMGTransform();
        GlStateManager.translate(0.0, -3.025, -16.0);
        vector.render(1f);
        magazine.render(1f);
        if(!hasScopeAtachment(stack)) ironsights.render(1f);
        GlStateManager.popMatrix();

        /*renderSMGSilencer(0, -10, -10, 1.0F, stack);
        renderRedDot(0, 6, 6, 1.2F, stack);
        renderHolo(-0.1, 4, -3, 1.0F, stack);
        renderScope2X(0, 1, -2, 1.2F, stack);
        renderScope4X(0, 5, -6, 1.1F, stack);
        renderVerticalGrip(0, 1, 4, 1.0F, stack);*/
    }

    public ModelVector() {
        textureWidth = 128;
        textureHeight = 128;

        vector = new ModelRenderer(this);
        vector.setRotationPoint(0.0F, 24.0F, 0.0F);
        vector.cubeList.add(new ModelBox(vector, 67, 19, -1.5F, -7.0F, -15.0F, 3, 1, 21, 0.0F, false));
        vector.cubeList.add(new ModelBox(vector, 67, 19, -2.0F, 2.7662F, 0.5206F, 4, 2, 6, 0.0F, false));
        vector.cubeList.add(new ModelBox(vector, 67, 19, -2.0F, 2.7662F, 6.5206F, 4, 3, 6, 0.0F, false));
        vector.cubeList.add(new ModelBox(vector, 67, 19, 0.932F, 0.7662F, 4.5206F, 1, 2, 7, 0.0F, false));
        vector.cubeList.add(new ModelBox(vector, 67, 19, -1.932F, 0.7662F, 4.5206F, 1, 2, 7, 0.0F, true));
        vector.cubeList.add(new ModelBox(vector, 67, 19, 0.932F, -1.2338F, 3.5206F, 1, 2, 7, 0.0F, false));
        vector.cubeList.add(new ModelBox(vector, 67, 19, -1.932F, -1.2338F, 3.5206F, 1, 2, 7, 0.0F, true));
        vector.cubeList.add(new ModelBox(vector, 67, 19, 0.932F, -3.2338F, 2.6006F, 1, 2, 7, 0.0F, false));
        vector.cubeList.add(new ModelBox(vector, 67, 19, -1.932F, -3.2338F, 2.6006F, 1, 2, 7, 0.0F, true));
        vector.cubeList.add(new ModelBox(vector, 67, 19, 0.932F, -5.2338F, 2.0246F, 1, 2, 7, 0.0F, false));
        vector.cubeList.add(new ModelBox(vector, 67, 19, -1.932F, -5.2338F, 2.0246F, 1, 2, 7, 0.0F, true));
        vector.cubeList.add(new ModelBox(vector, 67, 19, 0.932F, -7.2338F, 1.1926F, 1, 2, 7, 0.0F, false));
        vector.cubeList.add(new ModelBox(vector, 67, 19, -1.932F, -7.2338F, 1.1926F, 1, 2, 7, 0.0F, true));
        vector.cubeList.add(new ModelBox(vector, 67, 19, -2.0F, 3.7662F, 12.5206F, 4, 2, 1, 0.0F, false));
        vector.cubeList.add(new ModelBox(vector, 67, 19, -1.5F, 2.03F, 22.0381F, 3, 1, 1, 0.0F, false));
        vector.cubeList.add(new ModelBox(vector, 67, 19, -2.0F, 4.7662F, 13.5206F, 4, 1, 2, 0.0F, false));
        vector.cubeList.add(new ModelBox(vector, 67, 19, 1.2F, 4.4182F, 11.9206F, 1, 1, 1, 0.0F, false));
        vector.cubeList.add(new ModelBox(vector, 67, 19, -2.2F, 4.4182F, 11.9206F, 1, 1, 1, 0.0F, true));
        vector.cubeList.add(new ModelBox(vector, 67, 19, 1.2F, 4.4182F, 6.9046F, 1, 1, 1, 0.0F, false));
        vector.cubeList.add(new ModelBox(vector, 67, 19, 1.2F, 3.7142F, 0.5846F, 1, 1, 1, 0.0F, false));
        vector.cubeList.add(new ModelBox(vector, 67, 19, 1.2F, -1.1658F, -1.1434F, 1, 1, 1, 0.0F, false));
        vector.cubeList.add(new ModelBox(vector, 67, 19, 1.36F, -7.0538F, -15.9274F, 1, 1, 1, 0.0F, false));
        vector.cubeList.add(new ModelBox(vector, 67, 19, -2.36F, -7.0538F, -15.9274F, 1, 1, 1, 0.0F, true));
        vector.cubeList.add(new ModelBox(vector, 67, 19, 1.2F, -6.6058F, -3.1434F, 1, 1, 1, 0.0F, false));
        vector.cubeList.add(new ModelBox(vector, 67, 19, -2.2F, 3.7142F, 0.5846F, 1, 1, 1, 0.0F, true));
        vector.cubeList.add(new ModelBox(vector, 67, 19, -2.2F, -1.1658F, -1.1434F, 1, 1, 1, 0.0F, true));
        vector.cubeList.add(new ModelBox(vector, 67, 19, -2.2F, -6.6058F, -3.1434F, 1, 1, 1, 0.0F, true));
        vector.cubeList.add(new ModelBox(vector, 67, 19, -2.2F, 4.4182F, 6.9046F, 1, 1, 1, 0.0F, true));
        vector.cubeList.add(new ModelBox(vector, 67, 19, 1.0F, 4.7662F, 2.5206F, 1, 1, 4, 0.0F, false));
        vector.cubeList.add(new ModelBox(vector, 67, 19, -2.0F, 4.7662F, 2.5206F, 1, 1, 4, 0.0F, false));
        vector.cubeList.add(new ModelBox(vector, 67, 19, -2.0F, -6.5F, -16.0F, 4, 1, 1, 0.0F, false));
        vector.cubeList.add(new ModelBox(vector, 67, 19, -1.0F, -7.0F, -16.0F, 2, 2, 3, 0.0F, false));
        vector.cubeList.add(new ModelBox(vector, 67, 19, -1.0F, -6.6F, -4.512F, 2, 2, 2, 0.0F, false));
        vector.cubeList.add(new ModelBox(vector, 67, 19, -2.5F, -8.0F, -14.0F, 5, 1, 20, 0.0F, false));
        vector.cubeList.add(new ModelBox(vector, 67, 19, -0.2071F, -11.7071F, -16.0F, 2, 2, 17, 0.0F, false));
        vector.cubeList.add(new ModelBox(vector, 67, 19, -0.2071F, -11.7071F, 1.0F, 2, 1, 21, 0.0F, false));
        vector.cubeList.add(new ModelBox(vector, 67, 19, -1.7929F, -11.7071F, -16.0F, 2, 2, 17, 0.0F, true));
        vector.cubeList.add(new ModelBox(vector, 67, 19, -1.7929F, -11.7071F, 1.0F, 2, 1, 21, 0.0F, true));
        vector.cubeList.add(new ModelBox(vector, 67, 19, -2.5F, -8.0F, 6.0F, 5, 1, 16, 0.0F, false));
        vector.cubeList.add(new ModelBox(vector, 67, 19, 1.5F, -11.0F, 3.0F, 1, 3, 19, 0.0F, false));
        vector.cubeList.add(new ModelBox(vector, 0, 4, -2.0F, -3.0797F, 38.39F, 4, 4, 3, 0.0F, false));
        vector.cubeList.add(new ModelBox(vector, 44, 48, -1.5F, 3.2504F, 38.1579F, 3, 1, 2, 0.0F, false));
        vector.cubeList.add(new ModelBox(vector, 44, 48, -1.0F, 3.2504F, 39.622F, 2, 1, 2, 0.0F, false));
        vector.cubeList.add(new ModelBox(vector, 44, 48, -1.5F, 2.2504F, 39.622F, 3, 1, 2, 0.0F, false));
        vector.cubeList.add(new ModelBox(vector, 0, 7, -2.0F, -10.0797F, 34.39F, 4, 4, 7, 0.0F, false));
        vector.cubeList.add(new ModelBox(vector, 16, 73, -1.5F, -9.0797F, 41.39F, 3, 2, 1, 0.0F, false));
        vector.cubeList.add(new ModelBox(vector, 16, 73, -1.5F, -2.0797F, 41.39F, 3, 2, 1, 0.0F, false));
        vector.cubeList.add(new ModelBox(vector, 42, 10, -2.0F, -11.0797F, 42.39F, 4, 13, 3, 0.0F, false));
        vector.cubeList.add(new ModelBox(vector, 42, 10, -2.7071F, -10.3726F, 43.39F, 1, 6, 2, 0.0F, false));
        vector.cubeList.add(new ModelBox(vector, 42, 10, 1.7071F, -10.3726F, 43.39F, 1, 6, 2, 0.0F, true));
        vector.cubeList.add(new ModelBox(vector, 42, 10, -2.7071F, -4.7869F, 43.39F, 1, 6, 2, 0.0F, false));
        vector.cubeList.add(new ModelBox(vector, 42, 10, 1.7071F, -4.7869F, 43.39F, 1, 6, 2, 0.0F, true));
        vector.cubeList.add(new ModelBox(vector, 67, 19, 1.5F, -9.0F, -16.0F, 1, 1, 19, 0.0F, false));
        vector.cubeList.add(new ModelBox(vector, 67, 19, 1.5F, -8.0F, -16.0F, 1, 1, 2, 0.0F, false));
        vector.cubeList.add(new ModelBox(vector, 67, 19, 1.5F, -11.0F, -12.0F, 1, 1, 9, 0.0F, false));
        vector.cubeList.add(new ModelBox(vector, 0, 72, 1.196F, -10.0F, -12.0F, 1, 1, 9, 0.0F, false));
        vector.cubeList.add(new ModelBox(vector, 40, 54, 1.764F, -10.0F, -11.568F, 2, 1, 1, 0.0F, false));
        vector.cubeList.add(new ModelBox(vector, 67, 19, 1.5F, -11.0F, -3.0F, 1, 2, 6, 0.0F, false));
        vector.cubeList.add(new ModelBox(vector, 67, 19, 1.5F, -11.0F, -16.0F, 1, 2, 4, 0.0F, false));
        vector.cubeList.add(new ModelBox(vector, 67, 19, -2.5F, -11.0F, -16.0F, 1, 3, 13, 0.0F, false));
        vector.cubeList.add(new ModelBox(vector, 67, 19, -2.5F, -8.0F, -16.0F, 1, 1, 2, 0.0F, false));
        vector.cubeList.add(new ModelBox(vector, 67, 19, -1.5F, -11.0F, -14.0F, 3, 3, 1, 0.0F, false));
        vector.cubeList.add(new ModelBox(vector, 8, 80, -1.0F, -9.292F, -20.88F, 2, 2, 7, 0.0F, false));
        vector.cubeList.add(new ModelBox(vector, 8, 80, -0.5F, -9.556F, -20.624F, 1, 1, 2, 0.0F, false));
        vector.cubeList.add(new ModelBox(vector, 67, 19, -2.5F, -11.0F, -3.0F, 1, 1, 7, 0.0F, false));
        vector.cubeList.add(new ModelBox(vector, 0, 9, -2.1F, -10.0F, -3.0F, 1, 2, 7, 0.0F, false));
        vector.cubeList.add(new ModelBox(vector, 0, 10, -2.636F, -10.0F, -2.968F, 1, 2, 1, 0.0F, false));
        vector.cubeList.add(new ModelBox(vector, 67, 19, -2.5F, -11.0F, 4.0F, 1, 3, 18, 0.0F, false));
        vector.cubeList.add(new ModelBox(vector, 67, 19, -2.628F, -10.5F, 3.776F, 1, 3, 1, 0.0F, false));
        vector.cubeList.add(new ModelBox(vector, 0, 7, -2.0F, -11.24F, 21.256F, 4, 4, 12, 0.0F, false));
        vector.cubeList.add(new ModelBox(vector, 67, 19, -0.2071F, -7.2929F, 9.0F, 2, 1, 13, 0.0F, false));
        vector.cubeList.add(new ModelBox(vector, 67, 19, -1.7929F, -7.2929F, 9.0F, 2, 1, 13, 0.0F, true));
        vector.cubeList.add(new ModelBox(vector, 67, 19, -1.0F, -2.5899F, 10.2713F, 2, 1, 4, 0.0F, true));
        vector.cubeList.add(new ModelBox(vector, 18, 83, -0.5F, -6.5899F, 14.9593F, 1, 1, 1, 0.0F, true));
        vector.cubeList.add(new ModelBox(vector, 18, 83, -0.5F, -5.2139F, 14.9593F, 1, 1, 1, 0.0F, true));
        vector.cubeList.add(new ModelBox(vector, 2, 82, -1.0F, -5.2139F, 13.9593F, 2, 1, 1, 0.0F, true));
        vector.cubeList.add(new ModelBox(vector, 18, 83, -0.5F, -6.2139F, 15.9593F, 1, 2, 1, 0.0F, true));
        vector.cubeList.add(new ModelBox(vector, 18, 83, -1.0F, -6.5899F, 13.9593F, 2, 2, 1, 0.0F, true));

        bone9 = new ModelRenderer(this);
        bone9.setRotationPoint(0.0F, 2.722F, 15.6821F);
        setRotationAngle(bone9, -0.6981F, 0.0F, 0.0F);
        vector.addChild(bone9);
        bone9.cubeList.add(new ModelBox(bone9, 67, 19, -0.342F, 1.2496F, -2.6087F, 2, 1, 4, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 67, 19, -1.658F, 1.2496F, -2.6087F, 2, 1, 4, 0.0F, true));
        bone9.cubeList.add(new ModelBox(bone9, 67, 19, 1.0F, 2.1893F, -1.3047F, 1, 1, 2, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 67, 19, -2.0F, 2.1893F, -1.3047F, 1, 1, 2, 0.0F, true));

        bone10 = new ModelRenderer(this);
        bone10.setRotationPoint(0.0F, 2.5F, -1.0F);
        setRotationAngle(bone10, 0.0F, 0.0F, -0.3491F);
        bone9.addChild(bone10);
        bone10.cubeList.add(new ModelBox(bone10, 67, 19, 0.9857F, -0.6079F, -1.6087F, 1, 1, 4, 0.0F, false));

        bone11 = new ModelRenderer(this);
        bone11.setRotationPoint(0.0F, 2.5F, -1.0F);
        setRotationAngle(bone11, 0.0F, 0.0F, 0.3491F);
        bone9.addChild(bone11);
        bone11.cubeList.add(new ModelBox(bone11, 67, 19, -1.9857F, -0.6079F, -1.6087F, 1, 1, 4, 0.0F, true));

        bone6 = new ModelRenderer(this);
        bone6.setRotationPoint(-0.5F, 0.0F, -2.416F);
        setRotationAngle(bone6, 0.3491F, 0.0F, 0.0F);
        vector.addChild(bone6);
        bone6.cubeList.add(new ModelBox(bone6, 67, 19, -1.5F, 10.5531F, 14.8827F, 4, 1, 1, 0.0F, false));
        bone6.cubeList.add(new ModelBox(bone6, 67, 19, -1.0F, 10.5531F, 15.8827F, 3, 1, 7, 0.0F, false));
        bone6.cubeList.add(new ModelBox(bone6, 67, 19, -1.0F, -0.3866F, 19.2247F, 3, 11, 4, 0.0F, false));
        bone6.cubeList.add(new ModelBox(bone6, 67, 19, -0.5F, 3.2134F, 16.2247F, 2, 1, 3, 0.0F, false));

        bone7 = new ModelRenderer(this);
        bone7.setRotationPoint(0.5F, 11.0531F, 18.3827F);
        setRotationAngle(bone7, 0.0F, -0.2618F, 0.0F);
        bone6.addChild(bone7);
        bone7.cubeList.add(new ModelBox(bone7, 67, 19, 0.2848F, -0.5F, -2.9325F, 1, 1, 2, 0.0F, false));

        bone8 = new ModelRenderer(this);
        bone8.setRotationPoint(0.5F, 11.0531F, 18.3827F);
        setRotationAngle(bone8, 0.0F, 0.2618F, 0.0F);
        bone6.addChild(bone8);
        bone8.cubeList.add(new ModelBox(bone8, 67, 19, -1.2848F, -0.5F, -2.9325F, 1, 1, 2, 0.0F, true));

        bone4 = new ModelRenderer(this);
        bone4.setRotationPoint(0.0F, -6.0F, -15.5F);
        setRotationAngle(bone4, -0.4363F, 0.0F, 0.0F);
        vector.addChild(bone4);
        bone4.cubeList.add(new ModelBox(bone4, 67, 19, -2.0F, -1.7582F, -0.3355F, 4, 2, 1, 0.0F, false));

        rail = new ModelRenderer(this);
        rail.setRotationPoint(-0.5F, 0.0F, 0.0F);
        vector.addChild(rail);
        rail.cubeList.add(new ModelBox(rail, 0, 72, -0.5F, -5.0F, -7.0F, 2, 1, 1, 0.0F, false));
        rail.cubeList.add(new ModelBox(rail, 0, 72, -0.5F, -5.0F, -9.0F, 2, 1, 1, 0.0F, false));
        rail.cubeList.add(new ModelBox(rail, 0, 72, -0.5F, -5.0F, -11.0F, 2, 1, 1, 0.0F, false));
        rail.cubeList.add(new ModelBox(rail, 0, 72, -0.5F, -5.0F, -5.0F, 2, 1, 1, 0.0F, false));
        rail.cubeList.add(new ModelBox(rail, 0, 72, -0.5F, -5.0F, -13.0F, 2, 1, 1, 0.0F, false));
        rail.cubeList.add(new ModelBox(rail, 0, 72, -1.3669F, -4.5005F, -8.0F, 2, 0, 1, 0.0F, false));
        rail.cubeList.add(new ModelBox(rail, 0, 72, -1.3669F, -4.5005F, -10.0F, 2, 0, 1, 0.0F, false));
        rail.cubeList.add(new ModelBox(rail, 0, 72, -1.3669F, -4.5005F, -12.0F, 2, 0, 1, 0.0F, false));
        rail.cubeList.add(new ModelBox(rail, 0, 72, -1.3669F, -4.5005F, -6.0F, 2, 0, 1, 0.0F, false));
        rail.cubeList.add(new ModelBox(rail, 0, 72, 0.3669F, -4.5005F, -8.0F, 2, 0, 1, 0.0F, true));
        rail.cubeList.add(new ModelBox(rail, 0, 72, 0.3669F, -4.5005F, -10.0F, 2, 0, 1, 0.0F, true));
        rail.cubeList.add(new ModelBox(rail, 0, 72, 0.3669F, -4.5005F, -12.0F, 2, 0, 1, 0.0F, true));
        rail.cubeList.add(new ModelBox(rail, 0, 72, 0.3669F, -4.5005F, -6.0F, 2, 0, 1, 0.0F, true));
        rail.cubeList.add(new ModelBox(rail, 0, 72, -1.0F, -6.0F, -13.0F, 3, 1, 9, 0.0F, false));

        bone = new ModelRenderer(this);
        bone.setRotationPoint(0.5F, -4.5F, -6.5F);
        setRotationAngle(bone, 0.0F, 0.0F, 0.5236F);
        rail.addChild(bone);
        bone.cubeList.add(new ModelBox(bone, 0, 72, -1.616F, -0.067F, -0.5F, 1, 1, 1, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 0, 72, -1.616F, -0.067F, -2.5F, 1, 1, 1, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 0, 72, -1.616F, -0.067F, -4.5F, 1, 1, 1, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 0, 72, -1.616F, -0.067F, 1.5F, 1, 1, 1, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 0, 72, -1.616F, -0.067F, -6.5F, 1, 1, 1, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 0, 72, -1.616F, -0.067F, -1.5F, 0, 1, 1, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 0, 72, -1.616F, -0.067F, -3.5F, 0, 1, 1, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 0, 72, -1.616F, -0.067F, -5.5F, 0, 1, 1, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 0, 72, -1.616F, -0.067F, 0.5F, 0, 1, 1, 0.0F, false));

        bone3 = new ModelRenderer(this);
        bone3.setRotationPoint(0.5F, -4.5F, -6.5F);
        setRotationAngle(bone3, 0.0F, 0.0F, -0.5236F);
        rail.addChild(bone3);
        bone3.cubeList.add(new ModelBox(bone3, 0, 72, 0.616F, -0.067F, -0.5F, 1, 1, 1, 0.0F, true));
        bone3.cubeList.add(new ModelBox(bone3, 0, 72, 0.616F, -0.067F, -2.5F, 1, 1, 1, 0.0F, true));
        bone3.cubeList.add(new ModelBox(bone3, 0, 72, 0.616F, -0.067F, -4.5F, 1, 1, 1, 0.0F, true));
        bone3.cubeList.add(new ModelBox(bone3, 0, 72, 0.616F, -0.067F, 1.5F, 1, 1, 1, 0.0F, true));
        bone3.cubeList.add(new ModelBox(bone3, 0, 72, 0.616F, -0.067F, -6.5F, 1, 1, 1, 0.0F, true));
        bone3.cubeList.add(new ModelBox(bone3, 0, 72, 1.616F, -0.067F, -1.5F, 0, 1, 1, 0.0F, true));
        bone3.cubeList.add(new ModelBox(bone3, 0, 72, 1.616F, -0.067F, -3.5F, 0, 1, 1, 0.0F, true));
        bone3.cubeList.add(new ModelBox(bone3, 0, 72, 1.616F, -0.067F, -5.5F, 0, 1, 1, 0.0F, true));
        bone3.cubeList.add(new ModelBox(bone3, 0, 72, 1.616F, -0.067F, 0.5F, 0, 1, 1, 0.0F, true));

        rail2 = new ModelRenderer(this);
        rail2.setRotationPoint(-0.5F, -17.616F, -2.496F);
        vector.addChild(rail2);
        rail2.cubeList.add(new ModelBox(rail2, 0, 71, -0.5F, 4.0F, -7.0F, 2, 1, 1, 0.0F, false));
        rail2.cubeList.add(new ModelBox(rail2, 0, 71, -0.5F, 4.0F, 1.0F, 2, 1, 1, 0.0F, false));
        rail2.cubeList.add(new ModelBox(rail2, 0, 71, -0.5F, 4.0F, 9.0F, 2, 1, 1, 0.0F, false));
        rail2.cubeList.add(new ModelBox(rail2, 0, 71, -0.5F, 4.0F, 17.0F, 2, 1, 1, 0.0F, false));
        rail2.cubeList.add(new ModelBox(rail2, 0, 71, -0.5F, 4.0F, -9.0F, 2, 1, 1, 0.0F, false));
        rail2.cubeList.add(new ModelBox(rail2, 0, 71, -0.5F, 4.0F, -1.0F, 2, 1, 1, 0.0F, false));
        rail2.cubeList.add(new ModelBox(rail2, 0, 71, -0.5F, 4.0F, 7.0F, 2, 1, 1, 0.0F, false));
        rail2.cubeList.add(new ModelBox(rail2, 0, 71, -0.5F, 4.0F, 15.0F, 2, 1, 1, 0.0F, false));
        rail2.cubeList.add(new ModelBox(rail2, 0, 71, -0.5F, 4.0F, 23.0F, 2, 1, 1, 0.0F, false));
        rail2.cubeList.add(new ModelBox(rail2, 0, 71, -0.5F, 4.0F, -11.0F, 2, 1, 1, 0.0F, false));
        rail2.cubeList.add(new ModelBox(rail2, 0, 71, -0.5F, 4.0F, -3.0F, 2, 1, 1, 0.0F, false));
        rail2.cubeList.add(new ModelBox(rail2, 0, 71, -0.5F, 4.0F, 5.0F, 2, 1, 1, 0.0F, false));
        rail2.cubeList.add(new ModelBox(rail2, 0, 71, -0.5F, 4.0F, 13.0F, 2, 1, 1, 0.0F, false));
        rail2.cubeList.add(new ModelBox(rail2, 0, 71, -0.5F, 4.0F, 21.0F, 2, 1, 1, 0.0F, false));
        rail2.cubeList.add(new ModelBox(rail2, 0, 71, -0.5F, 4.0F, -5.0F, 2, 1, 1, 0.0F, false));
        rail2.cubeList.add(new ModelBox(rail2, 0, 71, -0.5F, 4.0F, 3.0F, 2, 1, 1, 0.0F, false));
        rail2.cubeList.add(new ModelBox(rail2, 0, 71, -0.5F, 4.0F, 11.0F, 2, 1, 1, 0.0F, false));
        rail2.cubeList.add(new ModelBox(rail2, 0, 71, -0.5F, 4.0F, 19.0F, 2, 1, 1, 0.0F, false));
        rail2.cubeList.add(new ModelBox(rail2, 0, 71, -0.5F, 4.0F, -13.0F, 2, 1, 1, 0.0F, false));
        rail2.cubeList.add(new ModelBox(rail2, 0, 71, -1.3669F, 4.5005F, -8.0F, 2, 0, 1, 0.0F, false));
        rail2.cubeList.add(new ModelBox(rail2, 0, 71, -1.3669F, 4.5005F, 0.0F, 2, 0, 1, 0.0F, false));
        rail2.cubeList.add(new ModelBox(rail2, 0, 71, -1.3669F, 4.5005F, 8.0F, 2, 0, 1, 0.0F, false));
        rail2.cubeList.add(new ModelBox(rail2, 0, 71, -1.3669F, 4.5005F, 16.0F, 2, 0, 1, 0.0F, false));
        rail2.cubeList.add(new ModelBox(rail2, 0, 71, -1.3669F, 4.5005F, -10.0F, 2, 0, 1, 0.0F, false));
        rail2.cubeList.add(new ModelBox(rail2, 0, 71, -1.3669F, 4.5005F, -2.0F, 2, 0, 1, 0.0F, false));
        rail2.cubeList.add(new ModelBox(rail2, 0, 71, -1.3669F, 4.5005F, 6.0F, 2, 0, 1, 0.0F, false));
        rail2.cubeList.add(new ModelBox(rail2, 0, 71, -1.3669F, 4.5005F, 14.0F, 2, 0, 1, 0.0F, false));
        rail2.cubeList.add(new ModelBox(rail2, 0, 71, -1.3669F, 4.5005F, 22.0F, 2, 0, 1, 0.0F, false));
        rail2.cubeList.add(new ModelBox(rail2, 0, 71, -1.3669F, 4.5005F, -12.0F, 2, 0, 1, 0.0F, false));
        rail2.cubeList.add(new ModelBox(rail2, 0, 71, -1.3669F, 4.5005F, -4.0F, 2, 0, 1, 0.0F, false));
        rail2.cubeList.add(new ModelBox(rail2, 0, 71, -1.3669F, 4.5005F, 4.0F, 2, 0, 1, 0.0F, false));
        rail2.cubeList.add(new ModelBox(rail2, 0, 71, -1.3669F, 4.5005F, 12.0F, 2, 0, 1, 0.0F, false));
        rail2.cubeList.add(new ModelBox(rail2, 0, 71, -1.3669F, 4.5005F, 20.0F, 2, 0, 1, 0.0F, false));
        rail2.cubeList.add(new ModelBox(rail2, 0, 71, -1.3669F, 4.5005F, -6.0F, 2, 0, 1, 0.0F, false));
        rail2.cubeList.add(new ModelBox(rail2, 0, 71, -1.3669F, 4.5005F, 2.0F, 2, 0, 1, 0.0F, false));
        rail2.cubeList.add(new ModelBox(rail2, 0, 71, -1.3669F, 4.5005F, 10.0F, 2, 0, 1, 0.0F, false));
        rail2.cubeList.add(new ModelBox(rail2, 0, 71, -1.3669F, 4.5005F, 18.0F, 2, 0, 1, 0.0F, false));
        rail2.cubeList.add(new ModelBox(rail2, 0, 71, 0.3669F, 4.5005F, -8.0F, 2, 0, 1, 0.0F, true));
        rail2.cubeList.add(new ModelBox(rail2, 0, 71, 0.3669F, 4.5005F, 0.0F, 2, 0, 1, 0.0F, true));
        rail2.cubeList.add(new ModelBox(rail2, 0, 71, 0.3669F, 4.5005F, 8.0F, 2, 0, 1, 0.0F, true));
        rail2.cubeList.add(new ModelBox(rail2, 0, 71, 0.3669F, 4.5005F, 16.0F, 2, 0, 1, 0.0F, true));
        rail2.cubeList.add(new ModelBox(rail2, 0, 71, 0.3669F, 4.5005F, -10.0F, 2, 0, 1, 0.0F, true));
        rail2.cubeList.add(new ModelBox(rail2, 0, 71, 0.3669F, 4.5005F, -2.0F, 2, 0, 1, 0.0F, true));
        rail2.cubeList.add(new ModelBox(rail2, 0, 71, 0.3669F, 4.5005F, 6.0F, 2, 0, 1, 0.0F, true));
        rail2.cubeList.add(new ModelBox(rail2, 0, 71, 0.3669F, 4.5005F, 14.0F, 2, 0, 1, 0.0F, true));
        rail2.cubeList.add(new ModelBox(rail2, 0, 71, 0.3669F, 4.5005F, 22.0F, 2, 0, 1, 0.0F, true));
        rail2.cubeList.add(new ModelBox(rail2, 0, 71, 0.3669F, 4.5005F, -12.0F, 2, 0, 1, 0.0F, true));
        rail2.cubeList.add(new ModelBox(rail2, 0, 71, 0.3669F, 4.5005F, -4.0F, 2, 0, 1, 0.0F, true));
        rail2.cubeList.add(new ModelBox(rail2, 0, 71, 0.3669F, 4.5005F, 4.0F, 2, 0, 1, 0.0F, true));
        rail2.cubeList.add(new ModelBox(rail2, 0, 71, 0.3669F, 4.5005F, 12.0F, 2, 0, 1, 0.0F, true));
        rail2.cubeList.add(new ModelBox(rail2, 0, 71, 0.3669F, 4.5005F, 20.0F, 2, 0, 1, 0.0F, true));
        rail2.cubeList.add(new ModelBox(rail2, 0, 71, 0.3669F, 4.5005F, -6.0F, 2, 0, 1, 0.0F, true));
        rail2.cubeList.add(new ModelBox(rail2, 0, 71, 0.3669F, 4.5005F, 2.0F, 2, 0, 1, 0.0F, true));
        rail2.cubeList.add(new ModelBox(rail2, 0, 71, 0.3669F, 4.5005F, 10.0F, 2, 0, 1, 0.0F, true));
        rail2.cubeList.add(new ModelBox(rail2, 0, 71, 0.3669F, 4.5005F, 18.0F, 2, 0, 1, 0.0F, true));
        rail2.cubeList.add(new ModelBox(rail2, 0, 71, -1.0F, 5.0F, -13.0F, 3, 1, 13, 0.0F, false));
        rail2.cubeList.add(new ModelBox(rail2, 0, 71, -1.0F, 5.0F, 11.0F, 3, 1, 13, 0.0F, false));
        rail2.cubeList.add(new ModelBox(rail2, 0, 71, -1.0F, 5.0F, -0.0F, 3, 1, 11, 0.0F, false));

        bone13 = new ModelRenderer(this);
        bone13.setRotationPoint(0.5F, 4.5F, -6.5F);
        setRotationAngle(bone13, 0.0F, 0.0F, -0.5236F);
        rail2.addChild(bone13);
        bone13.cubeList.add(new ModelBox(bone13, 0, 71, -1.616F, -0.933F, -0.5F, 1, 1, 1, 0.0F, false));
        bone13.cubeList.add(new ModelBox(bone13, 0, 71, -1.616F, -0.933F, 7.5F, 1, 1, 1, 0.0F, false));
        bone13.cubeList.add(new ModelBox(bone13, 0, 71, -1.616F, -0.933F, 15.5F, 1, 1, 1, 0.0F, false));
        bone13.cubeList.add(new ModelBox(bone13, 0, 71, -1.616F, -0.933F, 23.5F, 1, 1, 1, 0.0F, false));
        bone13.cubeList.add(new ModelBox(bone13, 0, 71, -1.616F, -0.933F, -2.5F, 1, 1, 1, 0.0F, false));
        bone13.cubeList.add(new ModelBox(bone13, 0, 71, -1.616F, -0.933F, 5.5F, 1, 1, 1, 0.0F, false));
        bone13.cubeList.add(new ModelBox(bone13, 0, 71, -1.616F, -0.933F, 13.5F, 1, 1, 1, 0.0F, false));
        bone13.cubeList.add(new ModelBox(bone13, 0, 71, -1.616F, -0.933F, 21.5F, 1, 1, 1, 0.0F, false));
        bone13.cubeList.add(new ModelBox(bone13, 0, 71, -1.616F, -0.933F, 29.5F, 1, 1, 1, 0.0F, false));
        bone13.cubeList.add(new ModelBox(bone13, 0, 71, -1.616F, -0.933F, -4.5F, 1, 1, 1, 0.0F, false));
        bone13.cubeList.add(new ModelBox(bone13, 0, 71, -1.616F, -0.933F, 3.5F, 1, 1, 1, 0.0F, false));
        bone13.cubeList.add(new ModelBox(bone13, 0, 71, -1.616F, -0.933F, 11.5F, 1, 1, 1, 0.0F, false));
        bone13.cubeList.add(new ModelBox(bone13, 0, 71, -1.616F, -0.933F, 19.5F, 1, 1, 1, 0.0F, false));
        bone13.cubeList.add(new ModelBox(bone13, 0, 71, -1.616F, -0.933F, 27.5F, 1, 1, 1, 0.0F, false));
        bone13.cubeList.add(new ModelBox(bone13, 0, 71, -1.616F, -0.933F, 1.5F, 1, 1, 1, 0.0F, false));
        bone13.cubeList.add(new ModelBox(bone13, 0, 71, -1.616F, -0.933F, 9.5F, 1, 1, 1, 0.0F, false));
        bone13.cubeList.add(new ModelBox(bone13, 0, 71, -1.616F, -0.933F, 17.5F, 1, 1, 1, 0.0F, false));
        bone13.cubeList.add(new ModelBox(bone13, 0, 71, -1.616F, -0.933F, 25.5F, 1, 1, 1, 0.0F, false));
        bone13.cubeList.add(new ModelBox(bone13, 0, 71, -1.616F, -0.933F, -6.5F, 1, 1, 1, 0.0F, false));
        bone13.cubeList.add(new ModelBox(bone13, 0, 71, -1.616F, -0.933F, -1.5F, 0, 1, 1, 0.0F, false));
        bone13.cubeList.add(new ModelBox(bone13, 0, 71, -1.616F, -0.933F, 6.5F, 0, 1, 1, 0.0F, false));
        bone13.cubeList.add(new ModelBox(bone13, 0, 71, -1.616F, -0.933F, 14.5F, 0, 1, 1, 0.0F, false));
        bone13.cubeList.add(new ModelBox(bone13, 0, 71, -1.616F, -0.933F, 22.5F, 0, 1, 1, 0.0F, false));
        bone13.cubeList.add(new ModelBox(bone13, 0, 71, -1.616F, -0.933F, -3.5F, 0, 1, 1, 0.0F, false));
        bone13.cubeList.add(new ModelBox(bone13, 0, 71, -1.616F, -0.933F, 4.5F, 0, 1, 1, 0.0F, false));
        bone13.cubeList.add(new ModelBox(bone13, 0, 71, -1.616F, -0.933F, 12.5F, 0, 1, 1, 0.0F, false));
        bone13.cubeList.add(new ModelBox(bone13, 0, 71, -1.616F, -0.933F, 20.5F, 0, 1, 1, 0.0F, false));
        bone13.cubeList.add(new ModelBox(bone13, 0, 71, -1.616F, -0.933F, 28.5F, 0, 1, 1, 0.0F, false));
        bone13.cubeList.add(new ModelBox(bone13, 0, 71, -1.616F, -0.933F, -5.5F, 0, 1, 1, 0.0F, false));
        bone13.cubeList.add(new ModelBox(bone13, 0, 71, -1.616F, -0.933F, 2.5F, 0, 1, 1, 0.0F, false));
        bone13.cubeList.add(new ModelBox(bone13, 0, 71, -1.616F, -0.933F, 10.5F, 0, 1, 1, 0.0F, false));
        bone13.cubeList.add(new ModelBox(bone13, 0, 71, -1.616F, -0.933F, 18.5F, 0, 1, 1, 0.0F, false));
        bone13.cubeList.add(new ModelBox(bone13, 0, 71, -1.616F, -0.933F, 26.5F, 0, 1, 1, 0.0F, false));
        bone13.cubeList.add(new ModelBox(bone13, 0, 71, -1.616F, -0.933F, 0.5F, 0, 1, 1, 0.0F, false));
        bone13.cubeList.add(new ModelBox(bone13, 0, 71, -1.616F, -0.933F, 8.5F, 0, 1, 1, 0.0F, false));
        bone13.cubeList.add(new ModelBox(bone13, 0, 71, -1.616F, -0.933F, 16.5F, 0, 1, 1, 0.0F, false));
        bone13.cubeList.add(new ModelBox(bone13, 0, 71, -1.616F, -0.933F, 24.5F, 0, 1, 1, 0.0F, false));

        bone14 = new ModelRenderer(this);
        bone14.setRotationPoint(0.5F, 4.5F, -6.5F);
        setRotationAngle(bone14, 0.0F, 0.0F, 0.5236F);
        rail2.addChild(bone14);
        bone14.cubeList.add(new ModelBox(bone14, 0, 71, 0.616F, -0.933F, -0.5F, 1, 1, 1, 0.0F, true));
        bone14.cubeList.add(new ModelBox(bone14, 0, 71, 0.616F, -0.933F, 7.5F, 1, 1, 1, 0.0F, true));
        bone14.cubeList.add(new ModelBox(bone14, 0, 71, 0.616F, -0.933F, 15.5F, 1, 1, 1, 0.0F, true));
        bone14.cubeList.add(new ModelBox(bone14, 0, 71, 0.616F, -0.933F, 23.5F, 1, 1, 1, 0.0F, true));
        bone14.cubeList.add(new ModelBox(bone14, 0, 71, 0.616F, -0.933F, -2.5F, 1, 1, 1, 0.0F, true));
        bone14.cubeList.add(new ModelBox(bone14, 0, 71, 0.616F, -0.933F, 5.5F, 1, 1, 1, 0.0F, true));
        bone14.cubeList.add(new ModelBox(bone14, 0, 71, 0.616F, -0.933F, 13.5F, 1, 1, 1, 0.0F, true));
        bone14.cubeList.add(new ModelBox(bone14, 0, 71, 0.616F, -0.933F, 21.5F, 1, 1, 1, 0.0F, true));
        bone14.cubeList.add(new ModelBox(bone14, 0, 71, 0.616F, -0.933F, 29.5F, 1, 1, 1, 0.0F, true));
        bone14.cubeList.add(new ModelBox(bone14, 0, 71, 0.616F, -0.933F, -4.5F, 1, 1, 1, 0.0F, true));
        bone14.cubeList.add(new ModelBox(bone14, 0, 71, 0.616F, -0.933F, 3.5F, 1, 1, 1, 0.0F, true));
        bone14.cubeList.add(new ModelBox(bone14, 0, 71, 0.616F, -0.933F, 11.5F, 1, 1, 1, 0.0F, true));
        bone14.cubeList.add(new ModelBox(bone14, 0, 71, 0.616F, -0.933F, 19.5F, 1, 1, 1, 0.0F, true));
        bone14.cubeList.add(new ModelBox(bone14, 0, 71, 0.616F, -0.933F, 27.5F, 1, 1, 1, 0.0F, true));
        bone14.cubeList.add(new ModelBox(bone14, 0, 71, 0.616F, -0.933F, 1.5F, 1, 1, 1, 0.0F, true));
        bone14.cubeList.add(new ModelBox(bone14, 0, 71, 0.616F, -0.933F, 9.5F, 1, 1, 1, 0.0F, true));
        bone14.cubeList.add(new ModelBox(bone14, 0, 71, 0.616F, -0.933F, 17.5F, 1, 1, 1, 0.0F, true));
        bone14.cubeList.add(new ModelBox(bone14, 0, 71, 0.616F, -0.933F, 25.5F, 1, 1, 1, 0.0F, true));
        bone14.cubeList.add(new ModelBox(bone14, 0, 71, 0.616F, -0.933F, -6.5F, 1, 1, 1, 0.0F, true));
        bone14.cubeList.add(new ModelBox(bone14, 0, 71, 1.616F, -0.933F, -1.5F, 0, 1, 1, 0.0F, true));
        bone14.cubeList.add(new ModelBox(bone14, 0, 71, 1.616F, -0.933F, 6.5F, 0, 1, 1, 0.0F, true));
        bone14.cubeList.add(new ModelBox(bone14, 0, 71, 1.616F, -0.933F, 14.5F, 0, 1, 1, 0.0F, true));
        bone14.cubeList.add(new ModelBox(bone14, 0, 71, 1.616F, -0.933F, 22.5F, 0, 1, 1, 0.0F, true));
        bone14.cubeList.add(new ModelBox(bone14, 0, 71, 1.616F, -0.933F, -3.5F, 0, 1, 1, 0.0F, true));
        bone14.cubeList.add(new ModelBox(bone14, 0, 71, 1.616F, -0.933F, 4.5F, 0, 1, 1, 0.0F, true));
        bone14.cubeList.add(new ModelBox(bone14, 0, 71, 1.616F, -0.933F, 12.5F, 0, 1, 1, 0.0F, true));
        bone14.cubeList.add(new ModelBox(bone14, 0, 71, 1.616F, -0.933F, 20.5F, 0, 1, 1, 0.0F, true));
        bone14.cubeList.add(new ModelBox(bone14, 0, 71, 1.616F, -0.933F, 28.5F, 0, 1, 1, 0.0F, true));
        bone14.cubeList.add(new ModelBox(bone14, 0, 71, 1.616F, -0.933F, -5.5F, 0, 1, 1, 0.0F, true));
        bone14.cubeList.add(new ModelBox(bone14, 0, 71, 1.616F, -0.933F, 2.5F, 0, 1, 1, 0.0F, true));
        bone14.cubeList.add(new ModelBox(bone14, 0, 71, 1.616F, -0.933F, 10.5F, 0, 1, 1, 0.0F, true));
        bone14.cubeList.add(new ModelBox(bone14, 0, 71, 1.616F, -0.933F, 18.5F, 0, 1, 1, 0.0F, true));
        bone14.cubeList.add(new ModelBox(bone14, 0, 71, 1.616F, -0.933F, 26.5F, 0, 1, 1, 0.0F, true));
        bone14.cubeList.add(new ModelBox(bone14, 0, 71, 1.616F, -0.933F, 0.5F, 0, 1, 1, 0.0F, true));
        bone14.cubeList.add(new ModelBox(bone14, 0, 71, 1.616F, -0.933F, 8.5F, 0, 1, 1, 0.0F, true));
        bone14.cubeList.add(new ModelBox(bone14, 0, 71, 1.616F, -0.933F, 16.5F, 0, 1, 1, 0.0F, true));
        bone14.cubeList.add(new ModelBox(bone14, 0, 71, 1.616F, -0.933F, 24.5F, 0, 1, 1, 0.0F, true));

        bone2 = new ModelRenderer(this);
        bone2.setRotationPoint(-0.5F, 0.0F, 0.0F);
        setRotationAngle(bone2, 0.0F, 0.0F, 0.7854F);
        vector.addChild(bone2);
        bone2.cubeList.add(new ModelBox(bone2, 67, 19, -5.9497F, -4.5355F, -16.0F, 1, 1, 22, 0.0F, false));
        bone2.cubeList.add(new ModelBox(bone2, 67, 19, -6.364F, -4.5355F, -16.0F, 1, 1, 22, 0.0F, false));
        bone2.cubeList.add(new ModelBox(bone2, 67, 19, -6.364F, -4.5355F, 6.0F, 1, 1, 16, 0.0F, false));
        bone2.cubeList.add(new ModelBox(bone2, 67, 19, -9.1924F, -7.364F, 3.0F, 1, 1, 19, 0.0F, false));
        bone2.cubeList.add(new ModelBox(bone2, 67, 19, -9.1924F, -7.364F, -16.0F, 1, 1, 19, 0.0F, false));
        bone2.cubeList.add(new ModelBox(bone2, 67, 19, -3.8284F, -6.6569F, -16.0F, 1, 1, 22, 0.0F, false));
        bone2.cubeList.add(new ModelBox(bone2, 67, 19, -3.8284F, -7.0711F, -16.0F, 1, 1, 22, 0.0F, false));
        bone2.cubeList.add(new ModelBox(bone2, 67, 19, -3.8284F, -7.0711F, 6.0F, 1, 1, 16, 0.0F, false));
        bone2.cubeList.add(new ModelBox(bone2, 67, 19, -6.6569F, -9.8995F, 3.0F, 1, 1, 19, 0.0F, false));
        bone2.cubeList.add(new ModelBox(bone2, 67, 19, -6.6569F, -9.8995F, -16.0F, 1, 1, 19, 0.0F, false));

        bone18 = new ModelRenderer(this);
        bone18.setRotationPoint(-2.5F, 0.0F, 0.0F);
        setRotationAngle(bone18, 0.0F, 0.0F, 0.7854F);
        vector.addChild(bone18);
        bone18.cubeList.add(new ModelBox(bone18, 42, 10, -4.6526F, -11.0165F, 43.39F, 1, 1, 2, 0.0F, false));
        bone18.cubeList.add(new ModelBox(bone18, 42, 10, 3.5398F, -2.8242F, 43.39F, 1, 1, 2, 0.0F, false));
        bone18.cubeList.add(new ModelBox(bone18, 42, 10, -7.481F, -8.1881F, 43.39F, 1, 1, 2, 0.0F, false));
        bone18.cubeList.add(new ModelBox(bone18, 42, 10, 0.7114F, 0.0043F, 43.39F, 1, 1, 2, 0.0F, false));

        bone12 = new ModelRenderer(this);
        bone12.setRotationPoint(-2.636F, -9.0F, -2.0F);
        setRotationAngle(bone12, 0.0F, 0.576F, 0.0F);
        vector.addChild(bone12);
        bone12.cubeList.add(new ModelBox(bone12, 0, 10, -0.0174F, -0.5F, 0.0268F, 1, 1, 1, 0.0F, false));

        bone16 = new ModelRenderer(this);
        bone16.setRotationPoint(0.0F, -9.24F, 27.256F);
        setRotationAngle(bone16, -0.5236F, 0.0F, 0.0F);
        vector.addChild(bone16);
        bone16.cubeList.add(new ModelBox(bone16, 0, 7, -2.0F, -4.7321F, 4.1962F, 4, 4, 1, 0.0F, false));

        bone17 = new ModelRenderer(this);
        bone17.setRotationPoint(0.0F, -9.24F, 27.256F);
        setRotationAngle(bone17, -1.0472F, 0.0F, 0.0F);
        vector.addChild(bone17);
        bone17.cubeList.add(new ModelBox(bone17, 0, 7, -2.0F, -6.6962F, 2.134F, 4, 4, 8, 0.0F, false));
        bone17.cubeList.add(new ModelBox(bone17, 0, 7, -2.0F, -6.6962F, 10.134F, 4, 4, 7, 0.0F, false));

        bone15 = new ModelRenderer(this);
        bone15.setRotationPoint(0.0F, -4.4019F, 14.2993F);
        setRotationAngle(bone15, -0.3491F, 0.0F, 0.0F);
        vector.addChild(bone15);
        bone15.cubeList.add(new ModelBox(bone15, 18, 83, -1.0F, -0.0491F, -0.3155F, 2, 1, 1, 0.0F, true));

        bone5 = new ModelRenderer(this);
        bone5.setRotationPoint(0.5F, -5.7F, 1.5F);
        setRotationAngle(bone5, 0.3491F, 0.0F, 0.0F);
        vector.addChild(bone5);
        bone5.cubeList.add(new ModelBox(bone5, 67, 19, -2.5F, -2.5F, -4.5F, 4, 12, 2, 0.0F, false));
        bone5.cubeList.add(new ModelBox(bone5, 67, 19, 0.5F, 9.1237F, -2.9626F, 1, 2, 1, 0.0F, false));
        bone5.cubeList.add(new ModelBox(bone5, 67, 19, -2.5F, 9.1237F, -2.9626F, 1, 2, 1, 0.0F, false));
        bone5.cubeList.add(new ModelBox(bone5, 67, 19, -2.5F, -1.1319F, -0.7412F, 4, 12, 2, 0.0F, false));
        bone5.cubeList.add(new ModelBox(bone5, 67, 19, -2.5F, 1.0066F, 6.058F, 4, 12, 2, 0.0F, false));
        bone5.cubeList.add(new ModelBox(bone5, 67, 19, -2.216F, -1.816F, -2.6206F, 1, 11, 2, 0.0F, false));
        bone5.cubeList.add(new ModelBox(bone5, 67, 19, 0.216F, -1.816F, -2.6206F, 1, 11, 2, 0.0F, true));
        bone5.cubeList.add(new ModelBox(bone5, 67, 19, -1.5F, 10.4918F, 0.7961F, 2, 2, 1, 0.0F, false));

        magazine = new ModelRenderer(this);
        magazine.setRotationPoint(7.5F, 18.3F, 1.5F);
        setRotationAngle(magazine, 0.3491F, 0.0F, 0.0F);
        magazine.cubeList.add(new ModelBox(magazine, 4, 66, -9.0F, -2.3732F, -4.2066F, 3, 19, 5, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 4, 66, -9.0F, 17.0668F, -4.2066F, 3, 1, 5, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 4, 66, -8.5F, 16.6268F, -3.7066F, 2, 1, 4, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 4, 66, -6.9F, -1.5319F, -2.1427F, 1, 18, 1, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 4, 66, -9.1F, -1.5319F, -2.1427F, 1, 18, 1, 0.0F, true));
        magazine.cubeList.add(new ModelBox(magazine, 4, 66, -6.9F, -1.5319F, -3.6427F, 1, 18, 1, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 4, 66, -9.1F, -1.5319F, -3.6427F, 1, 18, 1, 0.0F, true));
        magazine.cubeList.add(new ModelBox(magazine, 4, 66, -6.9F, -1.5319F, -0.6427F, 1, 18, 1, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 4, 66, -9.1F, -1.5319F, -0.6427F, 1, 18, 1, 0.0F, true));
        magazine.cubeList.add(new ModelBox(magazine, 4, 66, -9.0F, -3.3732F, -4.2066F, 1, 1, 5, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 4, 66, -7.0F, -3.3732F, -4.2066F, 1, 1, 5, 0.0F, true));
        magazine.cubeList.add(new ModelBox(magazine, 4, 66, -8.0F, -2.7732F, -4.2066F, 1, 1, 1, 0.0F, true));
        magazine.cubeList.add(new ModelBox(magazine, 4, 66, -8.0F, -3.0732F, -0.2066F, 1, 1, 1, 0.0F, true));
        magazine.cubeList.add(new ModelBox(magazine, 12, 105, -8.0F, -3.7572F, -3.2066F, 1, 1, 3, 0.0F, true));

        ironsights = new ModelRenderer(this);
        ironsights.setRotationPoint(0.0F, 24.0F, -1.488F);

        bone19 = new ModelRenderer(this);
        bone19.setRotationPoint(0.0F, -14.5F, 18.5F);
        ironsights.addChild(bone19);
        bone19.cubeList.add(new ModelBox(bone19, 40, 50, -0.5F, -0.5F, -0.5F, 1, 1, 1, 0.0F, false));
        bone19.cubeList.add(new ModelBox(bone19, 40, 50, -1.0F, -0.5F, -28.5F, 2, 1, 1, 0.0F, false));
        bone19.cubeList.add(new ModelBox(bone19, 40, 50, -0.5F, -1.5F, -28.5F, 1, 1, 1, 0.0F, false));
        bone19.cubeList.add(new ModelBox(bone19, 40, 50, 0.866F, -1.866F, -0.5F, 1, 1, 1, 0.0F, false));
        bone19.cubeList.add(new ModelBox(bone19, 40, 13, 1.366F, -2.946F, -1.0F, 1, 3, 2, 0.0F, false));
        bone19.cubeList.add(new ModelBox(bone19, 40, 13, 1.366F, -3.446F, -0.5F, 1, 1, 1, 0.0F, false));
        bone19.cubeList.add(new ModelBox(bone19, 40, 13, -2.5F, 0.766F, -1.0F, 5, 1, 2, 0.0F, false));
        bone19.cubeList.add(new ModelBox(bone19, 42, 13, -2.5F, 0.766F, -29.0F, 5, 1, 2, 0.0F, false));
        bone19.cubeList.add(new ModelBox(bone19, 40, 13, 1.0774F, 1.6723F, -1.0F, 1, 1, 2, 0.0F, false));
        bone19.cubeList.add(new ModelBox(bone19, 40, 13, -2.0774F, 1.6723F, -1.0F, 1, 1, 2, 0.0F, true));
        bone19.cubeList.add(new ModelBox(bone19, 40, 13, 1.0774F, 1.6723F, -29.0F, 1, 1, 2, 0.0F, false));
        bone19.cubeList.add(new ModelBox(bone19, 40, 13, -2.0774F, 1.6723F, -29.0F, 1, 1, 2, 0.0F, true));
        bone19.cubeList.add(new ModelBox(bone19, 42, 13, -2.0F, 1.574F, -29.0F, 4, 1, 2, 0.0F, false));
        bone19.cubeList.add(new ModelBox(bone19, 40, 13, 1.254F, -0.05F, -0.5F, 1, 1, 1, 0.0F, false));
        bone19.cubeList.add(new ModelBox(bone19, 42, 13, 1.254F, -1.05F, -29.0F, 1, 2, 2, 0.0F, false));
        bone19.cubeList.add(new ModelBox(bone19, 40, 13, -2.366F, -2.946F, -1.0F, 1, 3, 2, 0.0F, true));
        bone19.cubeList.add(new ModelBox(bone19, 40, 13, -2.366F, -3.446F, -0.5F, 1, 1, 1, 0.0F, true));
        bone19.cubeList.add(new ModelBox(bone19, 40, 13, -2.254F, -0.05F, -0.5F, 1, 1, 1, 0.0F, true));
        bone19.cubeList.add(new ModelBox(bone19, 42, 13, -2.254F, -1.05F, -29.0F, 1, 2, 2, 0.0F, true));
        bone19.cubeList.add(new ModelBox(bone19, 40, 50, -1.866F, -1.866F, -0.5F, 1, 1, 1, 0.0F, true));
        bone19.cubeList.add(new ModelBox(bone19, 40, 50, -0.5F, -3.2321F, -0.5F, 1, 1, 1, 0.0F, true));

        bone22 = new ModelRenderer(this);
        bone22.setRotationPoint(0.0F, 1.266F, 0.0F);
        setRotationAngle(bone22, 0.0F, 0.0F, 0.4363F);
        bone19.addChild(bone22);
        bone22.cubeList.add(new ModelBox(bone22, 40, 13, 1.4771F, -0.6034F, -1.0F, 1, 1, 2, 0.0F, false));
        bone22.cubeList.add(new ModelBox(bone22, 40, 13, 1.4771F, -0.6034F, -29.0F, 1, 1, 2, 0.0F, false));

        bone23 = new ModelRenderer(this);
        bone23.setRotationPoint(0.0F, 1.266F, 0.0F);
        setRotationAngle(bone23, 0.0F, 0.0F, -0.4363F);
        bone19.addChild(bone23);
        bone23.cubeList.add(new ModelBox(bone23, 40, 13, -2.4771F, -0.6034F, -1.0F, 1, 1, 2, 0.0F, true));
        bone23.cubeList.add(new ModelBox(bone23, 40, 13, -2.4771F, -0.6034F, -29.0F, 1, 1, 2, 0.0F, true));

        bone20 = new ModelRenderer(this);
        bone20.setRotationPoint(0.0F, -14.5F, 18.5F);
        setRotationAngle(bone20, 0.0F, 0.0F, 0.5236F);
        ironsights.addChild(bone20);
        bone20.cubeList.add(new ModelBox(bone20, 40, 50, 0.183F, -1.683F, -0.5F, 1, 1, 1, 0.0F, false));
        bone20.cubeList.add(new ModelBox(bone20, 40, 50, -2.549F, -1.683F, -0.5F, 1, 1, 1, 0.0F, false));
        bone20.cubeList.add(new ModelBox(bone20, 40, 50, -1.183F, -3.049F, -0.5F, 1, 1, 1, 0.0F, false));
        bone20.cubeList.add(new ModelBox(bone20, 40, 50, -1.183F, -0.317F, -0.5F, 1, 1, 1, 0.0F, false));
        bone20.cubeList.add(new ModelBox(bone20, 40, 50, -1.616F, -0.067F, -28.5F, 1, 1, 1, 0.0F, false));

        bone21 = new ModelRenderer(this);
        bone21.setRotationPoint(0.0F, -14.5F, 18.5F);
        setRotationAngle(bone21, 0.0F, 0.0F, 1.0472F);
        ironsights.addChild(bone21);
        bone21.cubeList.add(new ModelBox(bone21, 40, 50, -0.317F, -1.183F, -0.5F, 1, 1, 1, 0.0F, false));
        bone21.cubeList.add(new ModelBox(bone21, 40, 50, -0.067F, -1.616F, -28.5F, 1, 1, 1, 0.0F, false));
        bone21.cubeList.add(new ModelBox(bone21, 40, 50, -1.683F, 0.183F, -0.5F, 1, 1, 1, 0.0F, false));
        bone21.cubeList.add(new ModelBox(bone21, 40, 50, -1.683F, -2.549F, -0.5F, 1, 1, 1, 0.0F, false));
        bone21.cubeList.add(new ModelBox(bone21, 40, 50, -3.049F, -1.183F, -0.5F, 1, 1, 1, 0.0F, false));
        this.initAnimations();
    }
}

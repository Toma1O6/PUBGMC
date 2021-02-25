package dev.toma.pubgmc.client.models.weapons;

import dev.toma.pubgmc.animation.HeldAnimation;
import dev.toma.pubgmc.animation.HeldAnimation.HeldStyle;
import dev.toma.pubgmc.animation.ReloadAnimation;
import dev.toma.pubgmc.animation.ReloadAnimation.ReloadStyle;
import dev.toma.pubgmc.client.models.ModelGun;
import dev.toma.pubgmc.client.util.ModelTransformationHelper;
import dev.toma.pubgmc.common.capability.player.PlayerDataProvider;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.item.ItemStack;

public class ModelR45 extends ModelGun {

    private final ModelRenderer bone;
    private final ModelRenderer bone15;
    private final ModelRenderer bone17;
    private final ModelRenderer bone16;
    private final ModelRenderer bone14;
    private final ModelRenderer bone18;
    private final ModelRenderer bone24;
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
    private final ModelRenderer bone6;
    private final ModelRenderer bone27;
    private final ModelRenderer bone26;
    private final ModelRenderer bone25;
    private final ModelRenderer bone7;
    private final ModelRenderer bone5;
    private final ModelRenderer bone4;
    private final ModelRenderer bone2;
    private final ModelRenderer bone3;

    @Override
    public String textureName() {
        return "r45";
    }

    @Override
    public void initAnimations() {
        initAimAnimation(-0.555f, 0.175f, -0.01f, 2.5f);
        initAimingAnimationStates(0.175f, 0.095f, 0f);
        heldAnimation = new HeldAnimation(HeldStyle.SMALL);
        reloadAnimation = new ReloadAnimation(null, ReloadStyle.REVOLVER);
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }

    @Override
    public void render(ItemStack stack) {
        EntityPlayerSP player = Minecraft.getMinecraft().player;
        if (player != null && player.hasCapability(PlayerDataProvider.PLAYER_DATA, null)) {
            GlStateManager.pushMatrix();
            {
                renderR45(stack);
            }
            GlStateManager.popMatrix();
        }
    }

    private void renderR45(ItemStack stack) {
        GlStateManager.pushMatrix();
        {
            ModelTransformationHelper.defaultPistolTransform();
            GlStateManager.translate(0.15, -9.349995, -7.0);

            bone.render(1f);
        }
        GlStateManager.popMatrix();

        renderRedDot(0.475, -7.25, -8, 0.8f, stack);
    }

    public ModelR45() {
        textureWidth = 128;
        textureHeight = 128;

        bone = new ModelRenderer(this);
        bone.setRotationPoint(0.0F, 24.0F, 0.0F);
        bone.cubeList.add(new ModelBox(bone, 85, 27, -2.0F, -2.0F, 0.0F, 4, 2, 2, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 85, 27, -2.0F, -9.0F, 2.0F, 4, 9, 2, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 85, 27, -2.0F, -8.3438F, 4.0F, 4, 3, 1, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 85, 27, -2.0F, -6.0F, 4.0F, 4, 6, 3, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 41, 44, -0.5F, -6.9493F, 4.1953F, 1, 1, 2, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 40, 13, -0.5F, -8.3829F, 5.6211F, 1, 2, 1, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 85, 27, -2.0F, -2.0F, -6.0F, 4, 2, 6, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 16, 86, -0.5F, -0.6055F, 3.0641F, 1, 2, 1, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 6, 66, -1.0F, -6.2188F, -6.0F, 2, 2, 8, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 76, 19, -2.5F, -6.2188F, -4.4883F, 5, 2, 6, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 76, 19, -1.0F, -7.7187F, -4.4883F, 2, 5, 6, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 85, 27, -2.0F, -5.4142F, -7.4142F, 4, 4, 2, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 85, 27, -2.0F, -6.4753F, -7.179F, 4, 1, 2, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 85, 27, -1.5F, -9.0544F, -6.0889F, 3, 1, 10, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 85, 27, -1.5F, -9.2341F, 1.2627F, 3, 1, 2, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 85, 27, -1.4063F, -9.7771F, 1.2627F, 1, 1, 2, 0.0F, true));
        bone.cubeList.add(new ModelBox(bone, 85, 27, 0.4063F, -9.7771F, 1.2627F, 1, 1, 2, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 85, 27, -0.5F, -8.3789F, -21.2344F, 1, 1, 16, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 85, 27, -0.5F, -9.3789F, -20.6602F, 1, 1, 3, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 85, 27, -0.5F, -9.5625F, -20.2852F, 1, 1, 2, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 85, 27, -0.5F, -4.2852F, -21.2344F, 1, 1, 15, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 85, 27, 0.5176F, -5.576F, -21.2344F, 1, 1, 15, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 85, 27, -1.5176F, -5.576F, -21.2344F, 1, 1, 15, 0.0F, true));
        bone.cubeList.add(new ModelBox(bone, 85, 27, -1.0F, -7.5078F, -21.2344F, 2, 1, 15, 0.0F, false));

        bone15 = new ModelRenderer(this);
        bone15.setRotationPoint(1.3281F, 5.3477F, 3.6133F);
        setRotationAngle(bone15, 0.3491F, 0.0F, 0.0F);
        bone.addChild(bone15);
        bone15.cubeList.add(new ModelBox(bone15, 39, 68, -2.0F, -6.0334F, 3.0603F, 3, 6, 6, 0.0F, true));
        bone15.cubeList.add(new ModelBox(bone15, 73, 46, -3.3281F, -6.342F, 3.7009F, 4, 5, 5, 0.0F, true));
        bone15.cubeList.add(new ModelBox(bone15, 73, 46, -1.8281F, -16.836F, -6.1227F, 1, 1, 2, 0.0F, true));
        bone15.cubeList.add(new ModelBox(bone15, 39, 68, -3.6563F, -6.0334F, 3.0603F, 3, 6, 6, 0.0F, false));

        bone17 = new ModelRenderer(this);
        bone17.setRotationPoint(0.0F, 0.0F, 0.0F);
        setRotationAngle(bone17, -0.2618F, 0.0F, 0.0F);
        bone15.addChild(bone17);
        bone17.cubeList.add(new ModelBox(bone17, 39, 68, -3.6563F, -2.3773F, 2.7429F, 3, 7, 6, 0.0F, false));
        bone17.cubeList.add(new ModelBox(bone17, 39, 68, -2.0F, -2.3773F, 2.7429F, 3, 7, 6, 0.0F, true));

        bone16 = new ModelRenderer(this);
        bone16.setRotationPoint(-0.3281F, 1.3242F, 1.6875F);
        setRotationAngle(bone16, 0.6981F, 0.0F, 0.0F);
        bone15.addChild(bone16);

        bone14 = new ModelRenderer(this);
        bone14.setRotationPoint(-0.5F, 5.5391F, -2.7891F);
        setRotationAngle(bone14, 0.4363F, 0.0F, 0.0F);
        bone.addChild(bone14);

        bone18 = new ModelRenderer(this);
        bone18.setRotationPoint(0.0F, 0.0F, 0.0F);
        setRotationAngle(bone18, 0.7854F, 0.0F, 0.0F);
        bone14.addChild(bone18);
        bone18.cubeList.add(new ModelBox(bone18, 89, 40, -1.5F, 5.5395F, 10.8207F, 4, 3, 2, 0.0F, false));

        bone24 = new ModelRenderer(this);
        bone24.setRotationPoint(0.0F, -7.9493F, 5.6953F);
        setRotationAngle(bone24, 0.3491F, 0.0F, 0.0F);
        bone.addChild(bone24);
        bone24.cubeList.add(new ModelBox(bone24, 40, 13, -0.5F, -0.2513F, 0.4009F, 1, 1, 1, 0.0F, false));
        bone24.cubeList.add(new ModelBox(bone24, 40, 13, -0.5F, -0.4328F, -0.9214F, 1, 1, 1, 0.0F, false));
        bone24.cubeList.add(new ModelBox(bone24, 41, 44, -0.5F, 1.0504F, -2.2142F, 1, 1, 2, 0.0F, false));

        bone23 = new ModelRenderer(this);
        bone23.setRotationPoint(0.0F, -7.9493F, 5.6953F);
        setRotationAngle(bone23, -0.1745F, 0.0F, 0.0F);
        bone.addChild(bone23);
        bone23.cubeList.add(new ModelBox(bone23, 41, 44, -0.5F, 0.5825F, -0.6661F, 1, 1, 1, 0.0F, false));

        bone21 = new ModelRenderer(this);
        bone21.setRotationPoint(1.0F, 0.7617F, -0.4805F);
        setRotationAngle(bone21, -0.7854F, 0.0F, 0.0F);
        bone.addChild(bone21);
        bone21.cubeList.add(new ModelBox(bone21, 85, 27, -2.2929F, -10.2536F, -2.712F, 2, 1, 3, 0.0F, false));
        bone21.cubeList.add(new ModelBox(bone21, 85, 27, -0.7071F, -10.2536F, -2.712F, 1, 1, 3, 0.0F, true));
        bone21.cubeList.add(new ModelBox(bone21, 85, 27, 0.0F, -9.5465F, -1.9737F, 1, 1, 2, 0.0F, true));
        bone21.cubeList.add(new ModelBox(bone21, 85, 27, -3.0F, -9.5465F, -1.9737F, 1, 1, 2, 0.0F, false));

        bone22 = new ModelRenderer(this);
        bone22.setRotationPoint(0.0F, 0.0F, 0.0F);
        setRotationAngle(bone22, 0.0F, 0.0F, 0.7854F);
        bone21.addChild(bone22);
        bone22.cubeList.add(new ModelBox(bone22, 85, 27, -7.0433F, -7.4575F, -2.712F, 1, 1, 3, 0.0F, true));
        bone22.cubeList.add(new ModelBox(bone22, 85, 27, -8.8717F, -5.6291F, -2.712F, 1, 1, 3, 0.0F, true));

        bone13 = new ModelRenderer(this);
        bone13.setRotationPoint(-1.0F, 0.0F, 0.0F);
        setRotationAngle(bone13, 0.0F, 0.0F, -0.7854F);
        bone.addChild(bone13);

        bone9 = new ModelRenderer(this);
        bone9.setRotationPoint(-1.0F, 0.0F, 0.0F);
        setRotationAngle(bone9, 0.0F, 0.7854F, 0.0F);
        bone.addChild(bone9);

        bone10 = new ModelRenderer(this);
        bone10.setRotationPoint(1.0F, 0.0F, 0.0F);
        setRotationAngle(bone10, 0.0F, -0.7854F, 0.0F);
        bone.addChild(bone10);

        bone11 = new ModelRenderer(this);
        bone11.setRotationPoint(0.7773F, -11.168F, 3.7305F);
        setRotationAngle(bone11, 0.0F, 0.0F, -0.3491F);
        bone.addChild(bone11);

        bone12 = new ModelRenderer(this);
        bone12.setRotationPoint(-0.7773F, -11.168F, 3.7305F);
        setRotationAngle(bone12, 0.0F, 0.0F, 0.3491F);
        bone.addChild(bone12);

        bone8 = new ModelRenderer(this);
        bone8.setRotationPoint(-1.0F, 0.0F, 0.0F);
        setRotationAngle(bone8, 0.3491F, 0.0F, 0.0F);
        bone.addChild(bone8);

        bone19 = new ModelRenderer(this);
        bone19.setRotationPoint(0.0F, 1.0F, -3.7578F);
        setRotationAngle(bone19, 0.5236F, 0.0F, 0.0F);
        bone.addChild(bone19);

        bone20 = new ModelRenderer(this);
        bone20.setRotationPoint(0.0F, 1.0F, -3.7578F);
        setRotationAngle(bone20, 1.0472F, 0.0F, 0.0F);
        bone.addChild(bone20);
        bone20.cubeList.add(new ModelBox(bone20, 16, 86, -0.5F, 5.9712F, 2.5693F, 1, 1, 1, 0.0F, false));

        bone6 = new ModelRenderer(this);
        bone6.setRotationPoint(0.0F, -5.2188F, -1.9883F);
        setRotationAngle(bone6, 0.0F, 0.0F, -0.7854F);
        bone.addChild(bone6);
        bone6.cubeList.add(new ModelBox(bone6, 76, 19, -2.5F, -1.0F, -2.5F, 5, 2, 6, 0.0F, false));
        bone6.cubeList.add(new ModelBox(bone6, 76, 19, -1.0F, -2.5F, -2.5F, 2, 5, 6, 0.0F, false));

        bone27 = new ModelRenderer(this);
        bone27.setRotationPoint(0.0F, 0.0F, 0.0F);
        setRotationAngle(bone27, -0.4363F, 0.0F, 0.0F);
        bone.addChild(bone27);
        bone27.cubeList.add(new ModelBox(bone27, 85, 27, -2.0F, -2.7736F, -9.0077F, 4, 1, 2, 0.0F, false));
        bone27.cubeList.add(new ModelBox(bone27, 85, 27, -1.5F, -5.7781F, -9.2284F, 3, 3, 2, 0.0F, false));

        bone26 = new ModelRenderer(this);
        bone26.setRotationPoint(1.0F, 0.0F, 0.0F);
        setRotationAngle(bone26, 0.0F, 0.0F, 0.2618F);
        bone.addChild(bone26);
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
        setRotationAngle(bone25, 0.0F, 0.0F, -0.2618F);
        bone.addChild(bone25);
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
        setRotationAngle(bone7, -0.5236F, 0.0F, 0.0F);
        bone.addChild(bone7);

        bone5 = new ModelRenderer(this);
        bone5.setRotationPoint(0.0F, -1.5F, -6.2656F);
        setRotationAngle(bone5, 0.1745F, 0.0F, 0.0F);
        bone.addChild(bone5);

        bone4 = new ModelRenderer(this);
        bone4.setRotationPoint(0.0F, 0.0F, 0.0F);
        setRotationAngle(bone4, -0.7854F, 0.0F, 0.0F);
        bone.addChild(bone4);
        bone4.cubeList.add(new ModelBox(bone4, 85, 27, -2.0F, 3.2426F, -6.2426F, 4, 1, 2, 0.0F, false));

        bone2 = new ModelRenderer(this);
        bone2.setRotationPoint(0.0F, 0.0F, 0.0F);
        setRotationAngle(bone2, 0.0F, 0.1745F, 0.0F);
        bone.addChild(bone2);

        bone3 = new ModelRenderer(this);
        bone3.setRotationPoint(0.0F, 0.0F, 0.0F);
        setRotationAngle(bone3, 0.0F, -0.1745F, 0.0F);
        bone.addChild(bone3);
        this.initAnimations();
    }
}

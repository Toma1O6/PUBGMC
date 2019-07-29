package com.toma.pubgmc.client.models.weapons;

import com.toma.pubgmc.animation.HeldAnimation;
import com.toma.pubgmc.animation.HeldAnimation.HeldStyle;
import com.toma.pubgmc.client.models.ModelGun;
import com.toma.pubgmc.client.util.ModelTransformationHelper;
import com.toma.pubgmc.common.capability.IPlayerData;
import com.toma.pubgmc.common.capability.IPlayerData.PlayerDataProvider;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.item.ItemStack;

public class ModelP1911 extends ModelGun {
    private final ModelRenderer base;
    private final ModelRenderer handle;
    private final ModelRenderer magazine;
    private final ModelRenderer trigger;

    public ModelP1911() {
        super();

        textureWidth = 128;
        textureHeight = 128;

        base = new ModelRenderer(this);
        base.setRotationPoint(0.0F, 24.0F, 0.0F);
        base.cubeList.add(new ModelBox(base, 0, 0, -3.0F, -15.0F, -18.5F, 6, 4, 21, 0.0F, false));
        base.cubeList.add(new ModelBox(base, 0, 0, -2.5F, -15.5F, -18.0F, 5, 1, 20, 0.0F, false));
        base.cubeList.add(new ModelBox(base, 0, 0, -1.0F, -14.5F, -19.0F, 2, 2, 1, 0.0F, false));
        base.cubeList.add(new ModelBox(base, 0, 0, -2.0F, -9.0F, -5.0F, 4, 1, 3, 0.0F, false));
        base.cubeList.add(new ModelBox(base, 0, 0, -2.0F, -10.0F, -6.0F, 4, 1, 1, 0.0F, false));
        base.cubeList.add(new ModelBox(base, 0, 0, -2.0F, -11.0F, -7.0F, 4, 1, 1, 0.0F, false));
        base.cubeList.add(new ModelBox(base, 0, 0, -0.5F, -15.8F, -17.5F, 1, 1, 1, 0.0F, false));
        base.cubeList.add(new ModelBox(base, 17, 64, -1.5F, -16.0F, -1.5F, 1, 1, 2, 0.0F, false));
        base.cubeList.add(new ModelBox(base, 17, 64, 0.5F, -16.0F, -1.5F, 1, 1, 2, 0.0F, false));

        handle = new ModelRenderer(this);
        handle.setRotationPoint(0.0F, 24.0F, 0.0F);
        setRotationAngle(handle, 0.1745F, 0.0F, 0.0F);
        handle.cubeList.add(new ModelBox(handle, 0, 0, -2.5F, -12.0F, -1.0F, 5, 11, 5, 0.0F, false));
        handle.cubeList.add(new ModelBox(handle, 0, 76, -2.7F, -10.5F, -0.5F, 1, 9, 4, 0.0F, false));
        handle.cubeList.add(new ModelBox(handle, 0, 76, 1.7F, -10.5F, -0.5F, 1, 9, 4, 0.0F, false));

        magazine = new ModelRenderer(this);
        magazine.setRotationPoint(0.0F, 24.0F, 0.0F);
        setRotationAngle(magazine, 0.1745F, 0.0F, 0.0F);
        magazine.cubeList.add(new ModelBox(magazine, 0, 64, -2.0F, -11.5F, -0.5F, 4, 11, 4, 0.0F, false));

        trigger = new ModelRenderer(this);
        trigger.setRotationPoint(0.0F, 24.0F, 0.0F);
        setRotationAngle(trigger, -0.0873F, 0.0F, 0.0F);
        trigger.cubeList.add(new ModelBox(trigger, 0, 64, -1.0F, -11.5F, -5.0F, 2, 2, 1, 0.0F, false));
    }

    @Override
    public String textureName() {
        return "p1911";
    }

    @Override
    public void initAnimations() {
        initAimAnimation(-0.56f, 0.275f, 0.2f);
        initAimingAnimationStates(0.275f, 0.185f, 0f);
        heldAnimation = new HeldAnimation(HeldStyle.SMALL);
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
            IPlayerData data = player.getCapability(PlayerDataProvider.PLAYER_DATA, null);

            GlStateManager.pushMatrix();
            {
                renderP1911(data.isAiming(), stack);
            }
            GlStateManager.popMatrix();
        }
    }

    private void renderP1911(boolean aim, ItemStack stack) {
        GlStateManager.pushMatrix();
        {
            ModelTransformationHelper.defaultPistolTransform();
            GlStateManager.translate(0.0, 0.3, 0.9);
            if (aim) rotateModelForADSRendering();
            renderAll();
        }
        GlStateManager.popMatrix();

        renderRedDot(-0.43, -8.3, -0.7, 1.4f, stack);
        renderPistolSilencer(0.165, -3.6, -1.6, 1.2f, stack);
    }

    private void renderAll() {
        this.base.render(1f);
        this.handle.render(1f);
        this.magazine.render(1f);
        this.trigger.render(1f);
    }
}

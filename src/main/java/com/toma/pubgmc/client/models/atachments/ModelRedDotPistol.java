package com.toma.pubgmc.client.models.atachments;

import com.toma.pubgmc.client.models.ModelAtachmentBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;

public class ModelRedDotPistol extends ModelAtachmentBase {
    public ModelRenderer base;
    public double aimOffset = 0;

    public ModelRedDotPistol() {
        textureWidth = 128;
        textureHeight = 128;

        base = new ModelRenderer(this);
        base.setRotationPoint(0.0F, 24.0F, 0.0F);
        base.cubeList.add(new ModelBox(base, 64, 82, -3.0F, -1.0F, -5.0F, 6, 1, 11, 0.0F, false));
        base.cubeList.add(new ModelBox(base, 64, 82, -2.5F, -2.0F, 0.5F, 5, 1, 5, 0.0F, false));
        base.cubeList.add(new ModelBox(base, 64, 82, -2.5F, -3.0F, -4.0F, 5, 2, 2, 0.0F, false));
        base.cubeList.add(new ModelBox(base, 64, 82, -3.0F, -7.0F, -4.0F, 1, 4, 2, 0.0F, false));
        base.cubeList.add(new ModelBox(base, 64, 82, 2.0F, -7.0F, -4.0F, 1, 4, 2, 0.0F, false));
        base.cubeList.add(new ModelBox(base, 64, 82, -2.5F, -8.0F, -4.0F, 5, 1, 2, 0.0F, false));
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }

    @Override
    public void render() {
        this.base.render(1f);
    }

    public void setWeaponOffset() {
        GlStateManager.translate(0, aimOffset, 0);
    }
}

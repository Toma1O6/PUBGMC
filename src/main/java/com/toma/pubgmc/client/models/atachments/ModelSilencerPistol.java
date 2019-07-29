package com.toma.pubgmc.client.models.atachments;

import com.toma.pubgmc.client.models.ModelAtachmentBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;

public class ModelSilencerPistol extends ModelAtachmentBase {
    public ModelRenderer silencer;

    public ModelSilencerPistol() {
        textureWidth = 128;
        textureHeight = 128;

        silencer = new ModelRenderer(this);
        silencer.setRotationPoint(0.0F, 24.0F, 0.0F);
        silencer.cubeList.add(new ModelBox(silencer, 64, 64, -1.0F, -1.0F, -3.0F, 1, 1, 4, 0.0F, false));
    }

    @Override
    public void render() {
        GlStateManager.pushMatrix();
        //GlStateManager.scale(0.1, 0.1, 0.1);
        GlStateManager.translate(0, 0, 0);
        silencer.render(1f);
        GlStateManager.popMatrix();
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}

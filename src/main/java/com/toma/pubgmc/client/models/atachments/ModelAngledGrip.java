package com.toma.pubgmc.client.models.atachments;

import com.toma.pubgmc.client.models.ModelAtachmentBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;

public class ModelAngledGrip extends ModelAtachmentBase {
    private final ModelRenderer grip;
    private final ModelRenderer base;
    private final ModelRenderer angle1;
    private final ModelRenderer angle2;

    public ModelAngledGrip() {
        textureWidth = 128;
        textureHeight = 128;

        grip = new ModelRenderer(this);
        grip.setRotationPoint(0.0F, 24.0F, 0.0F);

        base = new ModelRenderer(this);
        base.setRotationPoint(0.0F, 24.0F, 0.0F);
        base.cubeList.add(new ModelBox(base, 64, 80, -2.0F, -8.0F, -6.0F, 4, 1, 11, 0.0F, false));
        base.cubeList.add(new ModelBox(base, 64, 80, -2.0F, -7.5F, 4.0F, 4, 1, 1, 0.0F, false));

        angle1 = new ModelRenderer(this);
        angle1.setRotationPoint(0.0F, 24.0F, 0.0F);
        setRotationAngle(angle1, 0.0873F, 0.0F, 0.0F);
        angle1.cubeList.add(new ModelBox(angle1, 64, 80, -2.0F, -7.5F, -5.3F, 4, 4, 1, 0.0F, false));

        angle2 = new ModelRenderer(this);
        angle2.setRotationPoint(0.0F, 24.0F, 0.0F);
        setRotationAngle(angle2, -1.0472F, 0.0F, 0.0F);
        angle2.cubeList.add(new ModelBox(angle2, 64, 80, -2.0F, -5.0F, -6.1F, 4, 8, 1, 0.0F, false));
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }

    @Override
    public void render() {
        grip.render(1f);
        base.render(1f);
        angle1.render(1f);
        angle2.render(1f);
    }
}

package com.toma.pubgmc.client.models.atachments;

import com.toma.pubgmc.client.models.ModelAtachmentBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;

public class ModelScope8X extends ModelAtachmentBase {
    private final ModelRenderer base;
    private final ModelRenderer shape1;
    private final ModelRenderer shape2;

    public ModelScope8X() {
        textureWidth = 128;
        textureHeight = 128;

        base = new ModelRenderer(this);
        base.setRotationPoint(0.0F, 24.0F, 0.0F);
        base.cubeList.add(new ModelBox(base, 64, 80, -3.0F, -1.0F, 2.0F, 6, 1, 6, 0.0F, false));
        base.cubeList.add(new ModelBox(base, 64, 80, -3.0F, -1.0F, -8.0F, 6, 1, 6, 0.0F, false));
        base.cubeList.add(new ModelBox(base, 64, 80, -5.0F, -3.0F, 3.0F, 10, 2, 4, 0.0F, false));
        base.cubeList.add(new ModelBox(base, 64, 80, -5.0F, -3.0F, -7.0F, 10, 2, 4, 0.0F, false));
        base.cubeList.add(new ModelBox(base, 64, 80, -3.0F, -4.0F, 0.0F, 6, 1, 15, 0.0F, false));
        base.cubeList.add(new ModelBox(base, 64, 80, -3.0F, -4.0F, -15.0F, 6, 1, 15, 0.0F, false));
        base.cubeList.add(new ModelBox(base, 64, 80, -4.0F, -5.0F, -15.0F, 1, 1, 30, 0.0F, false));
        base.cubeList.add(new ModelBox(base, 64, 80, 3.0F, -5.0F, -15.0F, 1, 1, 30, 0.0F, false));
        base.cubeList.add(new ModelBox(base, 64, 80, 4.0F, -11.0F, -15.0F, 1, 6, 30, 0.0F, false));
        base.cubeList.add(new ModelBox(base, 64, 80, -5.0F, -11.0F, -15.0F, 1, 6, 30, 0.0F, false));
        base.cubeList.add(new ModelBox(base, 64, 80, -4.0F, -12.0F, -15.0F, 1, 1, 30, 0.0F, false));
        base.cubeList.add(new ModelBox(base, 64, 80, 3.0F, -12.0F, -15.0F, 1, 1, 30, 0.0F, false));
        base.cubeList.add(new ModelBox(base, 64, 80, -3.0F, -13.0F, -15.0F, 6, 1, 15, 0.0F, false));
        base.cubeList.add(new ModelBox(base, 64, 80, -6.3F, -10.5F, 3.0F, 2, 8, 4, 0.0F, false));
        base.cubeList.add(new ModelBox(base, 64, 80, -3.0F, -13.0F, 0.0F, 6, 1, 15, 0.0F, false));
        base.cubeList.add(new ModelBox(base, 64, 80, -3.0F, -14.13F, 3.0F, 6, 2, 4, 0.0F, false));
        base.cubeList.add(new ModelBox(base, 64, 80, 4.3F, -10.5F, 3.0F, 2, 8, 4, 0.0F, false));
        base.cubeList.add(new ModelBox(base, 64, 80, -3.0F, -4.0F, 15.0F, 6, 1, 7, 0.0F, false));
        base.cubeList.add(new ModelBox(base, 64, 80, -3.0F, -13.0F, 15.0F, 6, 1, 7, 0.0F, false));
        base.cubeList.add(new ModelBox(base, 64, 80, -4.0F, -12.0F, 15.0F, 1, 1, 7, 0.0F, false));
        base.cubeList.add(new ModelBox(base, 64, 80, -5.0F, -11.0F, 15.0F, 1, 6, 7, 0.0F, false));
        base.cubeList.add(new ModelBox(base, 64, 80, -4.0F, -5.0F, 15.0F, 1, 1, 7, 0.0F, false));
        base.cubeList.add(new ModelBox(base, 64, 80, 3.0F, -5.0F, 15.0F, 1, 1, 7, 0.0F, false));
        base.cubeList.add(new ModelBox(base, 64, 80, 3.0F, -12.0F, 15.0F, 1, 1, 7, 0.0F, false));
        base.cubeList.add(new ModelBox(base, 64, 80, 4.0F, -11.0F, 15.0F, 1, 6, 7, 0.0F, false));
        base.cubeList.add(new ModelBox(base, 64, 80, -4.0F, -5.0F, -22.0F, 1, 1, 7, 0.0F, false));
        base.cubeList.add(new ModelBox(base, 64, 80, -3.0F, -4.0F, -22.0F, 6, 1, 7, 0.0F, false));
        base.cubeList.add(new ModelBox(base, 64, 80, 3.0F, -5.0F, -22.0F, 1, 1, 7, 0.0F, false));
        base.cubeList.add(new ModelBox(base, 64, 80, 4.0F, -11.0F, -22.0F, 1, 6, 7, 0.0F, false));
        base.cubeList.add(new ModelBox(base, 64, 80, 3.0F, -12.0F, -22.0F, 1, 1, 7, 0.0F, false));
        base.cubeList.add(new ModelBox(base, 64, 80, -3.0F, -13.0F, -22.0F, 6, 1, 7, 0.0F, false));
        base.cubeList.add(new ModelBox(base, 64, 80, -4.0F, -12.0F, -22.0F, 1, 1, 7, 0.0F, false));
        base.cubeList.add(new ModelBox(base, 64, 80, -5.0F, -11.0F, -22.0F, 1, 6, 7, 0.0F, false));
        base.cubeList.add(new ModelBox(base, 64, 80, 4.3F, -10.5F, -7.0F, 2, 8, 4, 0.0F, false));
        base.cubeList.add(new ModelBox(base, 64, 80, -6.3F, -10.5F, -7.0F, 2, 8, 4, 0.0F, false));
        base.cubeList.add(new ModelBox(base, 64, 80, -3.0F, -14.13F, -7.0F, 6, 2, 4, 0.0F, false));
        base.cubeList.add(new ModelBox(base, 64, 80, -1.0F, -16.5F, -1.0F, 2, 4, 2, 0.0F, false));
        base.cubeList.add(new ModelBox(base, 64, 80, -1.5F, -16.0F, -1.5F, 3, 2, 3, 0.0F, false));
        base.cubeList.add(new ModelBox(base, 64, 80, -9.0F, -9.0F, -1.5F, 4, 3, 3, 0.0F, false));
        base.cubeList.add(new ModelBox(base, 64, 80, -8.5F, -10.0F, -2.5F, 3, 5, 5, 0.0F, false));
        base.cubeList.add(new ModelBox(base, 64, 80, -4.0F, -12.0F, 19.0F, 8, 8, 1, 0.0F, false));
        base.cubeList.add(new ModelBox(base, 64, 80, -4.0F, -12.0F, -20.0F, 8, 8, 1, 0.0F, false));

        shape1 = new ModelRenderer(this);
        shape1.setRotationPoint(0.0F, 24.0F, 0.0F);
        setRotationAngle(shape1, 0.0F, 0.0F, 0.7854F);
        shape1.cubeList.add(new ModelBox(shape1, 64, 80, -12.0F, -8.0F, 3.0F, 2, 5, 4, 0.0F, false));
        shape1.cubeList.add(new ModelBox(shape1, 64, 80, -12.0F, -8.0F, -7.0F, 2, 5, 4, 0.0F, false));

        shape2 = new ModelRenderer(this);
        shape2.setRotationPoint(0.0F, 24.0F, 0.0F);
        setRotationAngle(shape2, 0.0F, 0.0F, -0.7854F);
        shape2.cubeList.add(new ModelBox(shape2, 64, 80, 10.0F, -8.0F, 3.0F, 2, 5, 4, 0.0F, false));
        shape2.cubeList.add(new ModelBox(shape2, 64, 80, 10.0F, -8.0F, -7.0F, 2, 5, 4, 0.0F, false));
    }

    @Override
    public void render() {
        base.render(1f);
        shape1.render(1f);
        shape2.render(1f);
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}

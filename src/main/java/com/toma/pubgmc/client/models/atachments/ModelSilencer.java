package com.toma.pubgmc.client.models.atachments;

import com.toma.pubgmc.client.models.ModelAtachmentBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;

public class ModelSilencer extends ModelAtachmentBase {
    private final ModelRenderer base;

    public ModelSilencer() {
        textureWidth = 128;
        textureHeight = 128;

        base = new ModelRenderer(this);
        base.setRotationPoint(0.0F, 24.0F, 0.0F);
        base.cubeList.add(new ModelBox(base, 64, 64, -2.0F, -5.0F, -8.0F, 4, 4, 16, 0.0F, false));
    }

    @Override
    public void render() {
        base.render(1f);
    }
}

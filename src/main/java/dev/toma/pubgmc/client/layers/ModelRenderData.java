package dev.toma.pubgmc.client.layers;

import net.minecraft.client.model.ModelBase;
import net.minecraft.util.ResourceLocation;

class ModelRenderData<M extends ModelBase> {

    private final M model;
    private final ResourceLocation texture;

    public ModelRenderData(M model, ResourceLocation texture) {
        this.model = model;
        this.texture = texture;
    }

    public M getModel() {
        return model;
    }

    public ResourceLocation getTexture() {
        return texture;
    }
}

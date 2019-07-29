package com.toma.pubgmc.client.models.vehicles;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

public abstract class ModelVehicle extends ModelBase {
    public static void setupRotatingParts(ModelRenderer steeringWheel, ModelRenderer wheel0, ModelRenderer wheel1, float modifier) {
        setRendererRotation(steeringWheel, 0f, 0f, -modifier / 4.5F);
        setRendererRotation(wheel0, 0f, modifier / 5F, 0f);
        setRendererRotation(wheel1, 0f, modifier / 5F, 0f);
    }

    private static void setRendererRotation(ModelRenderer renderer, float x, float y, float z) {
        renderer.rotateAngleX = x;
        renderer.rotateAngleY = y;
        renderer.rotateAngleZ = z;
    }

    public abstract void render(float turnModifier);

    public void renderSteeringWheel(ModelRenderer steeringWheel, float turnModifier) {
        setRendererRotation(steeringWheel, 0f, 0f, -turnModifier / 4.5f);
        steeringWheel.render(1f);
    }

    public void renderFrontWheel(ModelRenderer wheelRenderer, float turnModifier) {
        setRendererRotation(wheelRenderer, 0f, turnModifier / 5f, 0f);
        wheelRenderer.render(1f);
    }
}

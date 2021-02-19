package dev.toma.pubgmc.client.renderer.entity;

import dev.toma.pubgmc.client.models.vehicles.ModelVehicle;
import dev.toma.pubgmc.common.entity.controllable.EntityVehicle;
import dev.toma.pubgmc.common.entity.vehicles.Variants;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public abstract class RenderVariableVehicle<V extends EntityVehicle & Variants, M extends ModelVehicle<V>> extends RenderVehicle<V, M> {

    public RenderVariableVehicle(RenderManager manager, M vehicleModel) {
        super(manager, vehicleModel);
    }

    @Override
    protected ResourceLocation getEntityTexture(V entity) {
        return entity.getTextures()[entity.getActualTexture()];
    }
}

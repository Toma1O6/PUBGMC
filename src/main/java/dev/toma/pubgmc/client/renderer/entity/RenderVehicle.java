package dev.toma.pubgmc.client.renderer.entity;

import dev.toma.pubgmc.client.models.vehicles.ModelVehicle;
import dev.toma.pubgmc.common.entity.controllable.EntityVehicle;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;

public abstract class RenderVehicle<V extends EntityVehicle, M extends ModelVehicle<V>> extends Render<V> {

    private final M model;

    public RenderVehicle(RenderManager manager, M vehicleModel) {
        super(manager);
        this.model = vehicleModel;
    }

    public void preRenderCallback(V entity) {

    }

    @Override
    public void doRender(V entity, double x, double y, double z, float entityYaw, float partialTicks) {
        GlStateManager.pushMatrix();
        {
            bindEntityTexture(entity);
            GlStateManager.translate(x, y, z);
            GlStateManager.rotate(180, 1, 0, 0);
            preRenderCallback(entity);
            GlStateManager.rotate(entityYaw, 0f, 1f, 0f);
            model.render(entity);
        }
        GlStateManager.popMatrix();
        super.doRender(entity, x, y, z, entityYaw, partialTicks);
    }
}

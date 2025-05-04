package dev.toma.pubgmc.client.renderer.entity;

import dev.toma.pubgmc.client.models.vehicles.ModelVehicle;
import dev.toma.pubgmc.common.entity.vehicles.EntityVehicle;
import dev.toma.pubgmc.common.entity.vehicles.VehicleUAZ;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;

public abstract class RenderVehicle<V extends EntityVehicle, M extends ModelVehicle<V>> extends Render<V> {

    private final M model;

    public RenderVehicle(RenderManager manager, M vehicleModel) {
        super(manager);
        this.model = vehicleModel;
    }

    @Nullable
    @Override
    protected final ResourceLocation getEntityTexture(V entity) {
        return entity.isExploded() ? this.getBurnedTexture(entity) : this.getTexture(entity);
    }

    protected abstract ResourceLocation getBurnedTexture(V vehicle);

    protected abstract ResourceLocation getTexture(V vehicle);


    @Override
    public void doRender(V entity, double x, double y, double z, float entityYaw, float partialTicks) {
        GlStateManager.pushMatrix();
        this.translateModel(entity, x, y, z);
        this.rotateModel(entity, entityYaw);
        this.bindEntityTexture(entity);
        this.model.render(entity);
        GlStateManager.popMatrix();
        super.doRender(entity, x, y, z, entityYaw, partialTicks);
    }

    protected void setupTranslations(V entity) {
    }

    protected void setupRotationsAndScale(V entity, float entityYaw) {
    }

    private void translateModel(V entity, double x, double y, double z) {
        GlStateManager.translate(x, y, z);
        this.setupTranslations(entity);
    }

    private void rotateModel(V entity, float entityYaw) {
        GlStateManager.rotate(180.0F - entityYaw, 0.0F, 1.0F, 0.0F);
        GlStateManager.scale(VehicleUAZ.modelRotation.x, VehicleUAZ.modelRotation.y, VehicleUAZ.modelRotation.z);
        this.setupRotationsAndScale(entity, entityYaw);
    }
}

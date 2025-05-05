package dev.toma.pubgmc.client.renderer.entity;

import dev.toma.pubgmc.Pubgmc;
import dev.toma.pubgmc.client.models.vehicles.ModelDacia;
import dev.toma.pubgmc.common.entity.vehicles.VehicleDacia;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.Vec3d;

public class RenderDacia extends RenderVehicle<VehicleDacia, ModelDacia> {

    private static final ResourceLocation TEXTURE_BURNED = Pubgmc.getResource("textures/vehicle/dacia_burned.png");

    public RenderDacia(RenderManager manager) {
        super(manager, new ModelDacia());
    }

    @Override
    protected ResourceLocation getTexture(VehicleDacia vehicle) {
        return vehicle.getTextures()[vehicle.getActualTexture()];
    }

    @Override
    protected ResourceLocation getBurnedTexture(VehicleDacia vehicle) {
        return TEXTURE_BURNED;
    }

    @Override
    protected void setupTranslations(VehicleDacia entity) {
        GlStateManager.translate(0, entity.getModelOffset().y, 0);
    }

    @Override
    protected void setupRotationsAndScale(VehicleDacia entity, float entityYaw) {
        double scale = 0.8;
        GlStateManager.scale(scale, scale, scale);
        float yawRadian = (float) Math.toRadians(entityYaw);
        Vec3d modelScale = entity.getModelScale();
        Vec3d modelOffset = entity.getModelOffset();
        Vec3d offsetVec = new Vec3d(modelOffset.x * modelScale.x, 0.0D, modelOffset.z * modelScale.z);
        offsetVec.rotateYaw(-yawRadian);
        GlStateManager.translate(offsetVec.x, 0.0D, offsetVec.z);
    }
}

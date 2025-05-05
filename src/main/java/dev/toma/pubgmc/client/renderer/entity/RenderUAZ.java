package dev.toma.pubgmc.client.renderer.entity;

import dev.toma.pubgmc.Pubgmc;
import dev.toma.pubgmc.client.models.vehicles.ModelUAZ;
import dev.toma.pubgmc.common.entity.vehicles.VehicleUAZ;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.Vec3d;

public class RenderUAZ extends RenderVehicle<VehicleUAZ, ModelUAZ> {

    private static final ResourceLocation TEXTURE = Pubgmc.getResource("textures/vehicle/uaz.png");
    private static final ResourceLocation TEXTURE_BURNED = Pubgmc.getResource("textures/vehicle/uaz_burned.png");

    public RenderUAZ(RenderManager manager) {
        super(manager, new ModelUAZ());
    }

    @Override
    protected ResourceLocation getTexture(VehicleUAZ vehicle) {
        return TEXTURE;
    }

    @Override
    protected ResourceLocation getBurnedTexture(VehicleUAZ vehicle) {
        return TEXTURE_BURNED;
    }

    @Override
    protected void setupTranslations(VehicleUAZ entity) {
        GlStateManager.translate(0, VehicleUAZ.modelOffset.y, 0);
    }

    @Override
    protected void setupRotationsAndScale(VehicleUAZ entity, float entityYaw) {
        double scale = 0.8;
        GlStateManager.scale(scale, scale, scale);
        float yawRadian = (float) Math.toRadians(entityYaw);
        Vec3d offsetVec = new Vec3d(VehicleUAZ.modelOffset.x * VehicleUAZ.modelScale.x, 0.0D, VehicleUAZ.modelOffset.z * VehicleUAZ.modelScale.z);
        offsetVec.rotateYaw(-yawRadian);
        GlStateManager.translate(offsetVec.x, 0.0D, offsetVec.z);
    }
}

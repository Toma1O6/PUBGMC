package dev.toma.pubgmc.client.renderer.entity;

import dev.toma.pubgmc.Pubgmc;
import dev.toma.pubgmc.client.models.vehicles.ModelUAZ;
import dev.toma.pubgmc.common.entity.vehicles.EntityVehicleUAZ;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderUAZ extends RenderVehicle<EntityVehicleUAZ, ModelUAZ> {

    private static final ResourceLocation TEXTURE = Pubgmc.getResource("textures/vehicle/uaz.png");
    private static final ResourceLocation TEXTURE_BURNED = Pubgmc.getResource("textures/vehicle/uaz_burned.png");

    public RenderUAZ(RenderManager manager) {
        super(manager, new ModelUAZ());
    }

    @Override
    protected ResourceLocation getTexture(EntityVehicleUAZ vehicle) {
        return TEXTURE;
    }

    @Override
    protected ResourceLocation getBurnedTexture(EntityVehicleUAZ vehicle) {
        return TEXTURE_BURNED;
    }

    @Override
    protected void setupTranslations(EntityVehicleUAZ entity) {
        GlStateManager.translate(0.0, 0.7, 0.0);
    }

    @Override
    protected void setupRotationsAndScale(EntityVehicleUAZ entity) {
        double scale = 0.8;
        GlStateManager.scale(scale, scale, scale);
    }
}

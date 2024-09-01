package dev.toma.pubgmc.client.renderer.entity;

import dev.toma.pubgmc.Pubgmc;
import dev.toma.pubgmc.client.models.vehicles.ModelUAZ;
import dev.toma.pubgmc.common.entity.vehicles.EntityVehicleUAZ;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderUAZ extends RenderVehicle<EntityVehicleUAZ, ModelUAZ> {

    private static final ResourceLocation TEXTURE = Pubgmc.getResource("textures/vehicle/uaz.png");

    public RenderUAZ(RenderManager manager) {
        super(manager, new ModelUAZ());
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityVehicleUAZ entity) {
        return TEXTURE;
    }

    @Override
    public void preRenderCallback(EntityVehicleUAZ entity) {
        GlStateManager.scale(0.05F, 0.05F, 0.05F);
        GlStateManager.translate(0, -29, 0);
    }
}

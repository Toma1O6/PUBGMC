package dev.toma.pubgmc.client.renderer.entity;

import dev.toma.pubgmc.Pubgmc;
import dev.toma.pubgmc.client.models.vehicles.ModelUAZ;
import dev.toma.pubgmc.client.models.vehicles.ModelVehicle;
import dev.toma.pubgmc.common.entity.vehicles.EntityVehicleUAZ;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderUAZ extends RenderVehicle<EntityVehicleUAZ> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(Pubgmc.MOD_ID + ":textures/vehicle/uaz.png");
    private final ModelUAZ uaz = new ModelUAZ();

    public RenderUAZ(RenderManager manager) {
        super(manager);
        scaleModel(0.05f);
        translateModel(0, -29, 0);
    }

    @Override
    public ModelVehicle getVehicleModel() {
        return uaz;
    }

    @Override
    protected ResourceLocation defaultTexture() {
        return TEXTURE;
    }
}

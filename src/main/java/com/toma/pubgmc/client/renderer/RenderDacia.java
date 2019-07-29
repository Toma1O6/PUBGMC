package com.toma.pubgmc.client.renderer;

import com.toma.pubgmc.Pubgmc;
import com.toma.pubgmc.client.models.vehicles.ModelDacia;
import com.toma.pubgmc.client.models.vehicles.ModelVehicle;
import com.toma.pubgmc.common.entity.vehicles.EntityVehicleDacia;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderDacia extends RenderVehicle<EntityVehicleDacia> {
    private final ModelDacia dacia = new ModelDacia();

    public RenderDacia(RenderManager manager) {
        super(manager);
    }

    @Override
    public ModelVehicle getVehicleModel() {
        return dacia;
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityVehicleDacia entity) {
        if (textures.isEmpty()) initTextures(entity);
        return textures.get(entity.getColorVariantIndex());
    }

    @Override
    protected void initTextures(EntityVehicleDacia entity) {
        textures.clear();
        for (String s : entity.getTextureVariants()) {
            ResourceLocation rl = new ResourceLocation(Pubgmc.MOD_ID + ":textures/vehicle/dacia_" + s + ".png");
            textures.add(rl);
        }
    }

    @Override
    public void doRender(EntityVehicleDacia entity, double x, double y, double z, float entityYaw, float partialTicks) {
        GlStateManager.pushMatrix();
        bindEntityTexture(entity);
        GlStateManager.translate(x, y, z);
        GlStateManager.rotate(180f, 1f, 0f, 0f);
        GlStateManager.scale(0.1f, 0.1f, 0.1f);
        GlStateManager.translate(0f, -17f, 0f);
        GlStateManager.rotate(entity.rotationYaw, 0f, 1f, 0f);
        dacia.render(entity.turnModifier);
        GlStateManager.popMatrix();
        //super.doRender(entity, x, y, z, entityYaw, partialTicks);
    }
}

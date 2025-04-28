package dev.toma.pubgmc.client.renderer.entity;

import dev.toma.pubgmc.Pubgmc;
import dev.toma.pubgmc.client.models.ModelFuelCan;
import dev.toma.pubgmc.common.entity.EntityFuelCan;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;

public class RenderFuelCan extends Render<EntityFuelCan> {

    private static final ResourceLocation TEXTURE = Pubgmc.getResource("textures/entity/fuelcan.png");
    private final ModelFuelCan model;

    public RenderFuelCan(RenderManager manager) {
        super(manager);
        this.model = new ModelFuelCan();
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(EntityFuelCan entityFuelCan) {
        return TEXTURE;
    }

    @Override
    public void doRender(EntityFuelCan entity, double x, double y, double z, float entityYaw, float partialTicks) {
        GlStateManager.pushMatrix();
        GlStateManager.translate(x, y, z);
        GlStateManager.rotate(180.0F - entityYaw, 0.0F, 1.0F, 0.0F); // may not need
        bindEntityTexture(entity);
        model.render();
        GlStateManager.popMatrix();
    }
}

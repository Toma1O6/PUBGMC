package com.toma.pubgmc.client.renderer.entity;

import com.toma.pubgmc.Pubgmc;
import com.toma.pubgmc.client.models.vehicles.ModelPlane;
import com.toma.pubgmc.common.entity.EntityPlane;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderPlane extends Render<EntityPlane> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(Pubgmc.MOD_ID + ":textures/vehicle/plane.png");
    private final ModelPlane plane = new ModelPlane();

    public RenderPlane(RenderManager manager) {
        super(manager);
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityPlane entity) {
        return TEXTURE;
    }

    @Override
    public void doRender(EntityPlane entity, double x, double y, double z, float entityYaw, float partialTicks) {
        GlStateManager.pushMatrix();
        {
            bindEntityTexture(entity);
            GlStateManager.translate(x, y, z);
            GlStateManager.rotate(180f, 1f, 0f, 0f);
            GlStateManager.translate(0, -7, 0);
            GlStateManager.scale(0.5f, 0.5f, 0.5f);
            GlStateManager.rotate(entityYaw, 0f, 1f, 0f);
            plane.renderPlane();
        }
        GlStateManager.popMatrix();

        super.doRender(entity, x, y, z, entityYaw, partialTicks);
    }
}

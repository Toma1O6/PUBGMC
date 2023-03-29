package dev.toma.pubgmc.client.renderer.entity;

import dev.toma.pubgmc.DevUtil;
import dev.toma.pubgmc.Pubgmc;
import dev.toma.pubgmc.client.models.ModelParachute;
import dev.toma.pubgmc.common.entity.EntityParachute;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;

public class RenderParachute extends Render<EntityParachute> {

    private static final ResourceLocation TEXTURE = Pubgmc.getResource("textures/entity/parachute.png");
    private final ModelParachute model;

    public RenderParachute(RenderManager manager) {
        super(manager);
        this.model = new ModelParachute();
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(EntityParachute entity) {
        return TEXTURE;
    }

    @Override
    public void doRender(EntityParachute entity, double x, double y, double z, float entityYaw, float partialTicks) {
        GlStateManager.pushMatrix();
        bindEntityTexture(entity);
        int deployTime = Math.min(entity.ticksExisted, 6);
        double mod = deployTime / 6.0D;
        double prevMod = mod - 1 / 6D;
        double smooth = mod == 1 ? 1 : prevMod + (mod - prevMod) * partialTicks;
        GlStateManager.translate(x, y, z);
        GlStateManager.translate(0, 2.4, 0);
        GlStateManager.rotate(180.0F, 1.0F, 0.0F, 0.0F);
        GlStateManager.scale(0.07 * smooth, 0.07 * smooth, 0.07 * smooth);
        GlStateManager.rotate(entityYaw, 0.0F, 1.0F, 0.0F);
        float p = DevUtil.lerp(entity.rotationPitch, entity.prevRotationPitch, partialTicks);
        GlStateManager.rotate(p, 1.0F, 0.0F, 0.0F);
        if (entity.getEmptyTicks() > 0) {
            double land = entity.getEmptyTicks() / 100.0D;
            double prevLand = land - 0.01D;
            double interpolatedLand = prevLand + (land - prevLand) * partialTicks;
            GlStateManager.translate(0, 30 * interpolatedLand, 0);
            GlStateManager.rotate(90.0F * (float) interpolatedLand, 1, 0, 0);
        }
        GlStateManager.disableLighting();
        model.renderChute();
        GlStateManager.enableLighting();
        GlStateManager.popMatrix();
    }
}

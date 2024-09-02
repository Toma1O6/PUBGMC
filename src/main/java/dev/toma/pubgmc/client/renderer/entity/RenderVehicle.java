package dev.toma.pubgmc.client.renderer.entity;

import dev.toma.pubgmc.client.models.vehicles.ModelVehicle;
import dev.toma.pubgmc.common.entity.vehicles.EntityVehicle;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.Vec3d;

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
        return entity.isBurned() ? this.getBurnedTexture(entity) : this.getTexture(entity);
    }

    protected abstract ResourceLocation getBurnedTexture(V vehicle);

    protected abstract ResourceLocation getTexture(V vehicle);

    public void preRenderCallback(V entity) {
    }

    @Override
    public void doRender(V entity, double x, double y, double z, float entityYaw, float partialTicks) {
        GlStateManager.pushMatrix();
        {
            bindEntityTexture(entity);
            GlStateManager.translate(x, y, z);
            preRenderCallback(entity);
            GlStateManager.rotate(180, 1, 0, 0);
            GlStateManager.rotate(entityYaw, 0f, 1f, 0f);
            applyLight();
            model.render(entity);
            GlStateManager.disableLighting();
        }
        GlStateManager.popMatrix();
        RenderHelper.enableStandardItemLighting();
        super.doRender(entity, x, y, z, entityYaw, partialTicks);
    }

    static void applyLight() {
        Vec3d LIGHT0_POS = new Vec3d(0.2D, -10.0D, -0.7D);
        Vec3d LIGHT1_POS = new Vec3d(-0.2D, -10.0D, 0.7D);
        GlStateManager.enableLighting();
        GlStateManager.enableLight(0);
        GlStateManager.enableLight(1);
        GlStateManager.enableColorMaterial();
        GlStateManager.colorMaterial(1032, 5634);
        GlStateManager.glLight(16384, 4611, RenderHelper.setColorBuffer((float) LIGHT0_POS.x, (float) LIGHT0_POS.y, (float) LIGHT0_POS.z, 0.0F));
        float f = 0.6F;
        GlStateManager.glLight(16384, 4609, RenderHelper.setColorBuffer(f, f, f, 1.0F));
        GlStateManager.glLight(16384, 4608, RenderHelper.setColorBuffer(0.0F, 0.0F, 0.0F, 1.0F));
        GlStateManager.glLight(16384, 4610, RenderHelper.setColorBuffer(0.0F, 0.0F, 0.0F, 1.0F));
        GlStateManager.glLight(16385, 4611, RenderHelper.setColorBuffer((float) LIGHT1_POS.x, (float) LIGHT1_POS.y, (float) LIGHT1_POS.z, (float) 0.0D));
        GlStateManager.glLight(16385, 4609, RenderHelper.setColorBuffer(f, f, f, 1.0F));
        GlStateManager.glLight(16385, 4608, RenderHelper.setColorBuffer(0.0F, 0.0F, 0.0F, 1.0F));
        GlStateManager.glLight(16385, 4610, RenderHelper.setColorBuffer(0.0F, 0.0F, 0.0F, 1.0F));
        GlStateManager.shadeModel(7424);
        float f1 = 0.4F;
        GlStateManager.glLightModel(2899, RenderHelper.setColorBuffer(f1, f1, f1, 1.0F));
    }
}

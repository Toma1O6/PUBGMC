//package dev.toma.pubgmc.client.renderer.entity;
//
//import dev.toma.pubgmc.client.models.vehicles.ModelDacia;
//import dev.toma.pubgmc.common.entity.vehicles.EntityVehicleDacia;
//import net.minecraft.client.renderer.GlStateManager;
//import net.minecraft.client.renderer.entity.RenderManager;
//
//public class RenderDacia extends RenderVariableVehicle<EntityVehicleDacia, ModelDacia> {
//
//    public RenderDacia(RenderManager manager) {
//        super(manager, new ModelDacia());
//    }
//
//    @Override
//    public void preRenderCallback(EntityVehicleDacia entity) {
//        GlStateManager.scale(0.1f, 0.1f, 0.1f);
//        GlStateManager.translate(0f, -17f, 0f);
//    }
//}

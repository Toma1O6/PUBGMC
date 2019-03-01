package com.toma.pubgmc.client.renderer;

import com.toma.pubgmc.Pubgmc;
import com.toma.pubgmc.client.models.vehicles.ModelTestVehicle;
import com.toma.pubgmc.common.entity.vehicles.EntityTestVehicle;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderTestVehicle extends Render<EntityTestVehicle>
{
	private final ModelTestVehicle vehicle = new ModelTestVehicle();
	private static final ResourceLocation TEXTURE = new ResourceLocation(Pubgmc.MOD_ID + ":textures/vehicle/testcar.png");
	
	public RenderTestVehicle(RenderManager manager)
	{
		super(manager);
	}
	
	@Override
	protected ResourceLocation getEntityTexture(EntityTestVehicle entity)
	{
		return TEXTURE;
	}
	
	@Override
	public void doRender(EntityTestVehicle entity, double x, double y, double z, float entityYaw, float partialTicks) 
	{
		float scale = 0.15f;
		GlStateManager.pushMatrix();
		{
			GlStateManager.translate(x, y, z);
			GlStateManager.rotate(180, 1, 0, 0);
			GlStateManager.scale(scale, scale, scale);
			GlStateManager.translate(0, -24.5, 0);
			GlStateManager.rotate(entity.rotationYaw, 0f, 1f, 0f);
			bindTexture(TEXTURE);
			vehicle.renderVehicle(entity.turnModifier * 5);
		}
		GlStateManager.popMatrix();
		super.doRender(entity, x, y, z, entityYaw, partialTicks);
	}
}

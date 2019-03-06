package com.toma.pubgmc.client.renderer;

import com.toma.pubgmc.Pubgmc;
import com.toma.pubgmc.client.models.vehicles.ModelUAZ;
import com.toma.pubgmc.common.entity.vehicles.EntityVehicleUAZ;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderUAZ extends Render<EntityVehicleUAZ>
{
	private static final ResourceLocation TEXTURE = new ResourceLocation(Pubgmc.MOD_ID + ":textures/vehicle/uaz.png");
	private final ModelUAZ uaz = new ModelUAZ();
	
	public RenderUAZ(RenderManager manager)
	{
		super(manager);
	}
	
	@Override
	protected ResourceLocation getEntityTexture(EntityVehicleUAZ entity)
	{
		return TEXTURE;
	}
	
	@Override
	public void doRender(EntityVehicleUAZ entity, double x, double y, double z, float entityYaw, float partialTicks)
	{
		float scale = 0.05f;
		GlStateManager.pushMatrix();
		{
			bindEntityTexture(entity);
			GlStateManager.translate(x, y, z);
			GlStateManager.rotate(180, 1, 0, 0);
			GlStateManager.scale(scale, scale, scale);
			GlStateManager.translate(0, -29, 0);
			GlStateManager.rotate(entity.rotationYaw, 0f, 1f, 0f);
			uaz.renderUAZ();
		}
		GlStateManager.popMatrix();
		super.doRender(entity, x, y, z, entityYaw, partialTicks);
	}
}

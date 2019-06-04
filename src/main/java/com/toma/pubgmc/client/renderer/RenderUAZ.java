package com.toma.pubgmc.client.renderer;

import com.toma.pubgmc.Pubgmc;
import com.toma.pubgmc.client.models.vehicles.ModelUAZ;
import com.toma.pubgmc.client.models.vehicles.ModelVehicle;
import com.toma.pubgmc.common.entity.vehicles.EntityVehicleUAZ;

import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderUAZ extends RenderVehicle<EntityVehicleUAZ>
{
	private static final ResourceLocation TEXTURE = new ResourceLocation(Pubgmc.MOD_ID + ":textures/vehicle/uaz.png");
	private final ModelUAZ uaz = new ModelUAZ();
	
	public RenderUAZ(RenderManager manager)
	{
		super(manager);
		scaleModel(0.05f);
		translateModel(0, -29, 0);
	}
	
	@Override
	public ModelVehicle getVehicleModel()
	{
		return uaz;
	}
	
	@Override
	protected ResourceLocation defaultTexture() 
	{
		return TEXTURE;
	}
}

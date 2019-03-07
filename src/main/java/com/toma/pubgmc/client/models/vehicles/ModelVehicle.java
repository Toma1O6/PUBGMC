package com.toma.pubgmc.client.models.vehicles;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;

public abstract class ModelVehicle extends ModelBase
{
	public abstract void render(float turnModifier);
	
	public void renderSteeringWheel(ModelRenderer steeringWheel, float turnModifier)
	{
		GlStateManager.rotate(turnModifier*10, 0f, 0f, 1f);
		steeringWheel.render(1f);
	}
	
	public void renderFrontWheel(ModelRenderer wheelRenderer)
	{
		wheelRenderer.render(1f);
	}
}

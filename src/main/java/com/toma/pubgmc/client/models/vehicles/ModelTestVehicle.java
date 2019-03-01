package com.toma.pubgmc.client.models.vehicles;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;

public class ModelTestVehicle extends ModelBase
{
	private final ModelRenderer car;
	private final ModelRenderer wheel_front_right;
	private final ModelRenderer wheel_front_left;
	private final ModelRenderer wheel;

	public ModelTestVehicle()
	{
		textureWidth = 160;
		textureHeight = 160;

		car = new ModelRenderer(this);
		car.setRotationPoint(0.0F, 24.0F, 0.0F);
		car.cubeList.add(new ModelBox(car, 84, 0, -4.5F, -5.0F, -15.0F, 9, 3, 28, 0.0F, false));
		car.cubeList.add(new ModelBox(car, 0, 65, -8.5F, -5.0F, -7.5F, 17, 3, 15, 0.0F, false));
		car.cubeList.add(new ModelBox(car, 64, 65, -8.5F, -11.0F, -16.0F, 17, 9, 4, 0.0F, false));
		car.cubeList.add(new ModelBox(car, 0, 0, -8.5F, -6.0F, -12.0F, 17, 1, 25, 0.0F, false));
		car.cubeList.add(new ModelBox(car, 0, 31, -8.0F, -15.0F, -12.0F, 1, 9, 25, 0.0F, false));
		car.cubeList.add(new ModelBox(car, 52, 31, 7.0F, -15.0F, -12.0F, 1, 9, 25, 0.0F, false));
		car.cubeList.add(new ModelBox(car, 106, 65, -7.0F, -15.0F, 12.0F, 14, 9, 1, 0.0F, false));

		wheel_front_right = new ModelRenderer(this);
		wheel_front_right.setRotationPoint(0.0F, 24.0F, 0.0F);
		wheel_front_right.cubeList.add(new ModelBox(wheel_front_right, 14, 83, 5.0F, -4.0F, -12.0F, 3, 4, 4, 0.0F, false));
		
		wheel_front_left = new ModelRenderer(this);
		wheel_front_left.setRotationPoint(0.0F, 24.0F, 0.0F);
		wheel_front_left.cubeList.add(new ModelBox(wheel_front_left, 0, 83, -8.0F, -4.0F, -12.0F, 3, 4, 4, 0.0F, false));

		wheel = new ModelRenderer(this);
		wheel.setRotationPoint(0.0F, 24.0F, 0.0F);
		wheel.cubeList.add(new ModelBox(wheel, 42, 83, -8.0F, -4.0F, 8.0F, 3, 4, 4, 0.0F, false));
		wheel.cubeList.add(new ModelBox(wheel, 28, 83, 5.0F, -4.0F, 8.0F, 3, 4, 4, 0.0F, false));
	}
	
	public void renderVehicle(float turnModifier)
	{
		car.render(1f);
		wheel.render(1f);
		
		//GlStateManager.rotate(turnModifier, 0f, 1f, 0f);
		wheel_front_right.render(1f);
		wheel_front_left.render(1f);
	}
}

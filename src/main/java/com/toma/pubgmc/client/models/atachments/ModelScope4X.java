package com.toma.pubgmc.client.models.atachments;

import com.toma.pubgmc.client.models.ModelAtachmentBase;

import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;

public class ModelScope4X extends ModelAtachmentBase
{
	private final ModelRenderer scope;
	private final ModelRenderer base;
	private final ModelRenderer red_stripe;

	public ModelScope4X()
	{
		textureWidth = 128;
		textureHeight = 128;

		scope = new ModelRenderer(this);
		scope.setRotationPoint(0.0F, 24.0F, 0.0F);
		setRotationAngle(scope, -0.1745F, 0.0F, 0.0F);
		scope.cubeList.add(new ModelBox(scope, 64, 80, -3.0F, -9.5F, -13.0F, 6, 1, 9, 0.0F, false));
		scope.cubeList.add(new ModelBox(scope, 64, 80, 1.0F, -10.0F, -14.0F, 2, 1, 10, 0.0F, false));
		scope.cubeList.add(new ModelBox(scope, 64, 80, -3.0F, -10.0F, -14.0F, 2, 1, 10, 0.0F, false));

		base = new ModelRenderer(this);
		base.setRotationPoint(0.0F, 24.0F, 0.0F);
		base.cubeList.add(new ModelBox(base, 64, 80, -2.0F, -1.0F, -4.0F, 4, 1, 7, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 64, 80, -2.0F, -2.0F, -2.0F, 4, 1, 5, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 64, 80, -1.5F, -13.0F, -0.5F, 3, 4, 3, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 64, 80, -2.0F, -3.0F, -12.0F, 4, 1, 21, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 64, 80, 2.0F, -4.0F, -12.0F, 1, 1, 21, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 64, 80, -3.0F, -4.0F, -12.0F, 1, 1, 21, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 64, 80, -4.0F, -8.0F, -12.0F, 1, 4, 21, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 64, 80, 3.0F, -8.0F, -12.0F, 1, 4, 21, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 64, 80, 2.0F, -9.0F, -12.0F, 1, 1, 21, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 64, 80, -3.0F, -9.0F, -12.0F, 1, 1, 21, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 64, 80, -2.0F, -10.0F, -5.0F, 4, 1, 14, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 64, 80, -2.0F, -12.5F, -1.0F, 4, 2, 4, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 64, 80, 2.0F, -10.0F, -12.0F, 1, 1, 10, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 64, 80, -3.0F, -10.0F, -12.0F, 1, 1, 10, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 64, 80, -3.0F, -11.0F, -12.0F, 6, 1, 5, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 64, 80, -3.0F, -11.6F, -12.0F, 6, 1, 1, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 64, 80, 3.0F, -10.0F, -12.0F, 1, 2, 7, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 64, 80, -4.0F, -9.0F, -5.0F, 8, 1, 3, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 64, 80, -4.0F, -11.0F, -12.0F, 8, 1, 3, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 64, 80, -4.0F, -10.0F, -12.0F, 1, 2, 7, 0.0F, false));

		red_stripe = new ModelRenderer(this);
		red_stripe.setRotationPoint(0.0F, 24.0F, 0.0F);
		setRotationAngle(red_stripe, -0.1745F, 0.0F, 0.0F);
		red_stripe.cubeList.add(new ModelBox(red_stripe, 106, 116, -0.5F, -9.7F, -13.0F, 1, 1, 10, 0.0F, false));
	}
	
	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z)
	{
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
	
	@Override
	public void render()
	{
		base.render(1f);
		scope.render(1f);
		red_stripe.render(1f);
	}
}

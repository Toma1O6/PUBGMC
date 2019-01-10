package com.toma.pubgmc.client.models.atachments;

import com.toma.pubgmc.client.models.ModelAtachmentBase;

import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;

public class ModelVerticalGrip extends ModelAtachmentBase
{
	private final ModelRenderer base;

	public ModelVerticalGrip()
	{
		textureWidth = 128;
		textureHeight = 128;

		base = new ModelRenderer(this);
		base.setRotationPoint(0.0F, 24.0F, 0.0F);
		base.cubeList.add(new ModelBox(base, 64, 80, -1.5F, -12.0F, -1.5F, 3, 12, 3, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 64, 80, -3.0F, -14.0F, -5.0F, 6, 2, 10, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 64, 80, -2.0F, -12.0F, -3.0F, 4, 1, 6, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 64, 80, -2.0F, -9.0F, -2.0F, 4, 1, 4, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 64, 80, -2.0F, -6.0F, -2.0F, 4, 1, 4, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 64, 80, -2.0F, -3.0F, -2.0F, 4, 1, 4, 0.0F, false));
	}
	
	@Override
	public void render()
	{
		base.render(1f);
	}
}

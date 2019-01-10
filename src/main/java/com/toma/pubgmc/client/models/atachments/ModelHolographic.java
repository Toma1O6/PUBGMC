package com.toma.pubgmc.client.models.atachments;

import com.toma.pubgmc.client.models.ModelAtachmentBase;

import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;

public class ModelHolographic extends ModelAtachmentBase
{
	private final ModelRenderer base;

	public ModelHolographic()
	{
		textureWidth = 128;
		textureHeight = 128;

		base = new ModelRenderer(this);
		base.setRotationPoint(0.0F, 24.0F, 0.0F);
		base.cubeList.add(new ModelBox(base, 73, 80, -3.0F, -4.0F, -5.0F, 6, 4, 10, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 73, 80, -3.0F, -8.0F, -1.0F, 1, 4, 5, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 73, 80, 2.0F, -8.0F, -1.0F, 1, 4, 5, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 73, 80, -2.0F, -9.0F, -1.0F, 4, 1, 5, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 73, 80, 2.0F, -7.0F, -2.0F, 1, 3, 1, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 73, 80, 2.0F, -6.0F, -3.0F, 1, 2, 1, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 73, 80, 2.0F, -5.0F, -4.0F, 1, 1, 1, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 73, 80, -3.0F, -7.0F, -2.0F, 1, 3, 1, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 73, 80, -3.0F, -6.0F, -3.0F, 1, 2, 1, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 73, 80, -3.0F, -5.0F, -4.0F, 1, 1, 1, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 73, 80, -2.5F, -4.0F, -6.0F, 5, 4, 1, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 73, 80, -3.0F, -7.0F, 4.0F, 1, 3, 1, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 73, 80, 2.0F, -7.0F, 4.0F, 1, 3, 1, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 73, 80, -2.0F, -2.0F, 4.3F, 1, 1, 1, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 73, 80, 1.0F, -2.0F, 4.3F, 1, 1, 1, 0.0F, false));
	}
	
	@Override
	public void render()
	{
		base.render(1f);
	}
}

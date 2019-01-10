package com.toma.pubgmc.client.models.atachments;

import com.toma.pubgmc.client.models.ModelAtachmentBase;

import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;

public class ModelScope15X extends ModelAtachmentBase
{
	private final ModelRenderer base;

	public ModelScope15X()
	{
		textureWidth = 128;
		textureHeight = 128;

		base = new ModelRenderer(this);
		base.setRotationPoint(0.0F, 24.0F, 0.0F);
		base.cubeList.add(new ModelBox(base, 64, 80, -1.0F, -1.0F, -6.0F, 2, 1, 12, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 64, 80, -4.0F, -10.0F, -30.0F, 8, 8, 13, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 64, 80, -3.0F, -9.0F, -17.0F, 6, 6, 10, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 64, 80, -2.0F, -4.0F, -7.0F, 4, 1, 14, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 64, 80, -3.0F, -3.0F, 7.0F, 6, 1, 9, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 64, 80, -4.0F, -9.0F, 7.0F, 1, 6, 9, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 64, 80, 3.0F, -9.0F, 7.0F, 1, 6, 9, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 64, 80, 2.0F, -8.0F, -7.0F, 1, 4, 14, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 64, 80, -3.0F, -8.0F, -7.0F, 1, 4, 14, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 64, 80, -2.0F, -9.0F, -7.0F, 4, 1, 14, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 64, 80, -3.0F, -10.0F, 7.0F, 6, 1, 9, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 64, 80, -3.0F, -9.0F, 7.0F, 6, 6, 6, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 64, 80, -3.0F, -10.0F, -17.0F, 6, 1, 10, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 64, 80, 3.0F, -9.0F, -17.0F, 1, 6, 10, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 64, 80, -4.0F, -9.0F, -17.0F, 1, 6, 10, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 64, 80, -3.0F, -3.0F, -17.0F, 6, 1, 10, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 64, 80, -3.0F, -11.0F, -33.0F, 6, 1, 16, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 64, 80, 4.0F, -9.0F, -33.0F, 1, 6, 15, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 64, 80, -5.0F, -9.0F, -33.0F, 1, 6, 16, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 64, 80, -3.0F, -2.0F, -33.0F, 6, 1, 16, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 64, 80, 3.0F, -10.0F, -33.0F, 1, 1, 3, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 64, 80, -4.0F, -10.0F, -33.0F, 1, 1, 3, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 64, 80, -4.0F, -3.0F, -33.0F, 1, 1, 3, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 64, 80, 3.0F, -3.0F, -33.0F, 1, 1, 3, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 64, 80, -1.0F, -3.0F, -5.0F, 2, 2, 10, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 64, 80, -1.5F, -11.5F, -1.5F, 3, 3, 3, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 64, 80, 4.0F, -8.0F, -2.0F, 1, 4, 4, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 64, 80, 2.5F, -7.5F, -1.5F, 3, 3, 3, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 64, 80, -2.0F, -11.0F, -2.0F, 4, 1, 4, 0.0F, false));
	}
	
	@Override
	public void render()
	{
		base.render(1f);
	}
}

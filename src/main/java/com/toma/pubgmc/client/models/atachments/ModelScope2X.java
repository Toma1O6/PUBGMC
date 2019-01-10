package com.toma.pubgmc.client.models.atachments;

import com.toma.pubgmc.client.models.ModelAtachmentBase;

import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;

public class ModelScope2X extends ModelAtachmentBase
{
	private final ModelRenderer scope;

	public ModelScope2X()
	{
		textureWidth = 128;
		textureHeight = 128;

		scope = new ModelRenderer(this);
		scope.setRotationPoint(0.0F, 24.0F, 0.0F);
		scope.cubeList.add(new ModelBox(scope, 64, 80, -3.0F, -1.0F, 1.0F, 6, 1, 2, 0.0F, false));
		scope.cubeList.add(new ModelBox(scope, 64, 80, -2.0F, -2.0F, -9.0F, 4, 1, 17, 0.0F, false));
		scope.cubeList.add(new ModelBox(scope, 64, 80, 2.0F, -3.0F, -9.0F, 1, 1, 17, 0.0F, false));
		scope.cubeList.add(new ModelBox(scope, 64, 80, 3.0F, -4.0F, -9.0F, 1, 1, 17, 0.0F, false));
		scope.cubeList.add(new ModelBox(scope, 64, 80, 4.0F, -8.0F, -9.0F, 1, 4, 17, 0.0F, false));
		scope.cubeList.add(new ModelBox(scope, 64, 80, 3.0F, -9.0F, -9.0F, 1, 1, 17, 0.0F, false));
		scope.cubeList.add(new ModelBox(scope, 64, 80, 2.0F, -10.0F, -9.0F, 1, 1, 17, 0.0F, false));
		scope.cubeList.add(new ModelBox(scope, 64, 80, -2.0F, -11.0F, -9.0F, 4, 1, 17, 0.0F, false));
		scope.cubeList.add(new ModelBox(scope, 64, 80, -4.0F, -9.0F, -9.0F, 1, 1, 17, 0.0F, false));
		scope.cubeList.add(new ModelBox(scope, 64, 80, -3.0F, -10.0F, -9.0F, 1, 1, 17, 0.0F, false));
		scope.cubeList.add(new ModelBox(scope, 64, 80, -5.0F, -8.0F, -9.0F, 1, 4, 17, 0.0F, false));
		scope.cubeList.add(new ModelBox(scope, 64, 80, -4.0F, -4.0F, -9.0F, 1, 1, 17, 0.0F, false));
		scope.cubeList.add(new ModelBox(scope, 64, 80, -3.0F, -3.0F, -9.0F, 1, 1, 17, 0.0F, false));
		scope.cubeList.add(new ModelBox(scope, 64, 80, -3.0F, -1.0F, -4.0F, 6, 1, 2, 0.0F, false));
		scope.cubeList.add(new ModelBox(scope, 64, 80, -7.0F, -7.0F, -2.0F, 2, 3, 3, 0.0F, false));
		scope.cubeList.add(new ModelBox(scope, 64, 80, -1.0F, -13.0F, -2.0F, 2, 2, 3, 0.0F, false));
		scope.cubeList.add(new ModelBox(scope, 64, 80, -4.0F, -12.0F, -2.0F, 1, 4, 3, 0.0F, false));
		scope.cubeList.add(new ModelBox(scope, 64, 80, -4.0F, -13.0F, -1.0F, 3, 1, 1, 0.0F, false));
		scope.cubeList.add(new ModelBox(scope, 64, 80, -7.0F, -11.0F, -1.0F, 1, 4, 1, 0.0F, false));
		scope.cubeList.add(new ModelBox(scope, 64, 80, -6.0F, -12.0F, -1.0F, 1, 1, 1, 0.0F, false));
		scope.cubeList.add(new ModelBox(scope, 64, 80, -5.0F, -13.0F, -1.0F, 1, 1, 1, 0.0F, false));
	}
	
	@Override
	public void render()
	{
		scope.render(1f);
	}
}

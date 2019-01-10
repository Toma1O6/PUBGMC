package com.toma.pubgmc.client.models.weapons;

import com.toma.pubgmc.client.models.ModelGun;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.item.ItemStack;

public class ModelVSS extends ModelGun
{
	private final ModelRenderer base;
	private final ModelRenderer shape1;
	private final ModelRenderer mag;
	private final ModelRenderer trigger;
	private final ModelRenderer scope;

	public ModelVSS()
	{
		textureWidth = 128;
		textureHeight = 128;

		base = new ModelRenderer(this);
		base.setRotationPoint(0.0F, 24.0F, 0.0F);
		base.cubeList.add(new ModelBox(base, 0, 0, -3.0F, -22.0F, -20.0F, 6, 5, 31, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -2.5F, -21.5F, -45.0F, 5, 4, 25, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -2.5F, -23.0F, -20.0F, 5, 1, 31, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -3.0F, -23.0F, 9.0F, 6, 11, 2, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -3.0F, -13.0F, 13.0F, 6, 1, 8, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -3.0F, -23.0F, 11.0F, 6, 6, 15, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 64, -2.0F, -17.0F, 23.0F, 4, 13, 4, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 64, -2.0F, -17.0F, 26.0F, 4, 2, 25, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 64, -2.0F, -6.0F, 26.0F, 4, 2, 25, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -3.0F, -17.0F, 21.0F, 6, 5, 2, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 64, -2.0F, -17.0F, 51.0F, 4, 13, 4, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -2.0F, -17.0F, 55.0F, 4, 14, 1, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 64, -2.0F, -17.0F, 37.0F, 4, 13, 4, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 64, -2.0F, -7.0F, 27.0F, 4, 1, 2, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 64, -2.0F, -7.0F, 35.0F, 4, 1, 2, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 64, -2.0F, -15.0F, 35.0F, 4, 1, 2, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 64, -2.0F, -15.0F, 27.0F, 4, 1, 2, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 64, -2.0F, -7.0F, 49.0F, 4, 1, 2, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 64, -2.0F, -7.0F, 41.0F, 4, 1, 2, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 64, -2.0F, -15.0F, 41.0F, 4, 1, 2, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 64, -2.0F, -15.0F, 49.0F, 4, 1, 2, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -3.0F, -14.0F, 11.0F, 6, 2, 2, 0.0F, false));

		shape1 = new ModelRenderer(this);
		shape1.setRotationPoint(0.0F, 24.0F, 0.0F);
		setRotationAngle(shape1, -0.1745F, 0.0F, 0.0F);
		shape1.cubeList.add(new ModelBox(shape1, 0, 0, -3.0F, -19.0F, -19.0F, 6, 5, 26, 0.0F, false));

		mag = new ModelRenderer(this);
		mag.setRotationPoint(0.0F, 24.0F, 0.0F);
		setRotationAngle(mag, -0.1745F, 0.0F, 0.0F);
		mag.cubeList.add(new ModelBox(mag, 0, 0, -2.0F, -14.0F, -8.0F, 4, 14, 9, 0.0F, false));

		trigger = new ModelRenderer(this);
		trigger.setRotationPoint(0.0F, 24.0F, 0.0F);
		setRotationAngle(trigger, -0.1745F, 0.0F, 0.0F);
		trigger.cubeList.add(new ModelBox(trigger, 0, 0, -1.0F, -21.0F, 13.0F, 2, 4, 1, 0.0F, false));

		scope = new ModelRenderer(this);
		scope.setRotationPoint(0.0F, 24.0F, 0.0F);
		scope.cubeList.add(new ModelBox(scope, 0, 0, -1.0F, -27.0F, -5.0F, 2, 4, 17, 0.0F, false));
		scope.cubeList.add(new ModelBox(scope, 0, 0, -3.0F, -28.0F, -9.0F, 6, 1, 17, 0.0F, false));
		scope.cubeList.add(new ModelBox(scope, 0, 0, -5.0F, -35.0F, -9.0F, 10, 6, 17, 0.0F, false));
		scope.cubeList.add(new ModelBox(scope, 0, 0, -4.0F, -29.0F, -9.0F, 8, 1, 17, 0.0F, false));
		scope.cubeList.add(new ModelBox(scope, 0, 0, -4.0F, -36.0F, -9.0F, 8, 1, 17, 0.0F, false));
		scope.cubeList.add(new ModelBox(scope, 0, 0, -3.0F, -37.0F, -9.0F, 6, 1, 17, 0.0F, false));
		scope.cubeList.add(new ModelBox(scope, 0, 0, -3.0F, -28.0F, -10.0F, 6, 1, 1, 0.0F, false));
		scope.cubeList.add(new ModelBox(scope, 0, 0, -3.0F, -37.0F, -10.0F, 6, 1, 1, 0.0F, false));
		scope.cubeList.add(new ModelBox(scope, 0, 0, 3.0F, -36.0F, -10.0F, 1, 1, 1, 0.0F, false));
		scope.cubeList.add(new ModelBox(scope, 0, 0, 4.0F, -35.0F, -10.0F, 1, 6, 1, 0.0F, false));
		scope.cubeList.add(new ModelBox(scope, 0, 0, 3.0F, -29.0F, -10.0F, 1, 1, 1, 0.0F, false));
		scope.cubeList.add(new ModelBox(scope, 0, 0, -4.0F, -29.0F, -10.0F, 1, 1, 1, 0.0F, false));
		scope.cubeList.add(new ModelBox(scope, 0, 0, -5.0F, -35.0F, -10.0F, 1, 6, 1, 0.0F, false));
		scope.cubeList.add(new ModelBox(scope, 0, 0, -4.0F, -36.0F, -10.0F, 1, 1, 1, 0.0F, false));
		scope.cubeList.add(new ModelBox(scope, 0, 0, -1.0F, -40.5F, 5.0F, 2, 4, 2, 0.0F, false));
		scope.cubeList.add(new ModelBox(scope, 0, 0, -1.5F, -40.0F, 4.5F, 3, 2, 3, 0.0F, false));
		scope.cubeList.add(new ModelBox(scope, 0, 0, -3.0F, -38.0F, 8.0F, 6, 1, 5, 0.0F, false));
		scope.cubeList.add(new ModelBox(scope, 0, 0, -3.0F, -39.0F, 13.0F, 6, 1, 18, 0.0F, false));
		scope.cubeList.add(new ModelBox(scope, 0, 0, -4.0F, -37.0F, 8.0F, 1, 2, 5, 0.0F, false));
		scope.cubeList.add(new ModelBox(scope, 0, 0, -5.0F, -36.0F, 8.0F, 1, 2, 5, 0.0F, false));
		scope.cubeList.add(new ModelBox(scope, 0, 0, -6.0F, -35.0F, 8.0F, 1, 6, 5, 0.0F, false));
		scope.cubeList.add(new ModelBox(scope, 0, 0, 3.0F, -37.0F, 8.0F, 1, 2, 5, 0.0F, false));
		scope.cubeList.add(new ModelBox(scope, 0, 0, 4.0F, -36.0F, 8.0F, 1, 2, 5, 0.0F, false));
		scope.cubeList.add(new ModelBox(scope, 0, 0, 5.0F, -35.0F, 8.0F, 1, 6, 5, 0.0F, false));
		scope.cubeList.add(new ModelBox(scope, 0, 0, 4.0F, -30.0F, 8.0F, 1, 2, 5, 0.0F, false));
		scope.cubeList.add(new ModelBox(scope, 0, 0, 3.0F, -29.0F, 8.0F, 1, 2, 5, 0.0F, false));
		scope.cubeList.add(new ModelBox(scope, 0, 0, -4.0F, -29.0F, 8.0F, 1, 2, 5, 0.0F, false));
		scope.cubeList.add(new ModelBox(scope, 0, 0, -5.0F, -30.0F, 8.0F, 1, 2, 5, 0.0F, false));
		scope.cubeList.add(new ModelBox(scope, 0, 0, -3.0F, -27.0F, 8.0F, 6, 1, 5, 0.0F, false));
		scope.cubeList.add(new ModelBox(scope, 0, 0, -4.0F, -38.0F, 13.0F, 1, 2, 18, 0.0F, false));
		scope.cubeList.add(new ModelBox(scope, 0, 0, -5.0F, -37.0F, 13.0F, 1, 2, 18, 0.0F, false));
		scope.cubeList.add(new ModelBox(scope, 0, 0, -6.0F, -36.0F, 13.0F, 1, 2, 18, 0.0F, false));
		scope.cubeList.add(new ModelBox(scope, 0, 0, -7.0F, -35.0F, 13.0F, 1, 6, 18, 0.0F, false));
		scope.cubeList.add(new ModelBox(scope, 0, 0, -6.0F, -30.0F, 13.0F, 1, 2, 18, 0.0F, false));
		scope.cubeList.add(new ModelBox(scope, 0, 0, -5.0F, -29.0F, 13.0F, 1, 2, 18, 0.0F, false));
		scope.cubeList.add(new ModelBox(scope, 0, 0, -4.0F, -28.0F, 13.0F, 1, 2, 18, 0.0F, false));
		scope.cubeList.add(new ModelBox(scope, 0, 0, -3.0F, -26.0F, 13.0F, 6, 1, 18, 0.0F, false));
		scope.cubeList.add(new ModelBox(scope, 0, 0, 3.0F, -28.0F, 13.0F, 1, 2, 18, 0.0F, false));
		scope.cubeList.add(new ModelBox(scope, 0, 0, 4.0F, -29.0F, 13.0F, 1, 2, 18, 0.0F, false));
		scope.cubeList.add(new ModelBox(scope, 0, 0, 5.0F, -30.0F, 13.0F, 1, 2, 18, 0.0F, false));
		scope.cubeList.add(new ModelBox(scope, 0, 0, 5.0F, -36.0F, 13.0F, 1, 2, 18, 0.0F, false));
		scope.cubeList.add(new ModelBox(scope, 0, 0, 4.0F, -37.0F, 13.0F, 1, 2, 18, 0.0F, false));
		scope.cubeList.add(new ModelBox(scope, 0, 0, 3.0F, -38.0F, 13.0F, 1, 2, 18, 0.0F, false));
		scope.cubeList.add(new ModelBox(scope, 0, 0, 6.0F, -35.0F, 13.0F, 1, 6, 18, 0.0F, false));
		scope.cubeList.add(new ModelBox(scope, 0, 0, -3.0F, -38.0F, 13.0F, 6, 12, 15, 0.0F, false));
		scope.cubeList.add(new ModelBox(scope, 0, 0, -6.0F, -36.0F, 13.0F, 12, 7, 15, 0.0F, false));
		scope.cubeList.add(new ModelBox(scope, 0, 0, -4.0F, -36.0F, 13.0F, 8, 8, 15, 0.0F, false));
		scope.cubeList.add(new ModelBox(scope, 0, 0, -3.0F, -27.0F, 31.0F, 6, 3, 1, 0.0F, false));
		scope.cubeList.add(new ModelBox(scope, 0, 0, -3.0F, -40.0F, 31.0F, 6, 3, 1, 0.0F, false));
		scope.cubeList.add(new ModelBox(scope, 0, 0, -8.0F, -35.0F, 31.0F, 3, 6, 1, 0.0F, false));
		scope.cubeList.add(new ModelBox(scope, 0, 0, 5.0F, -35.0F, 31.0F, 3, 6, 1, 0.0F, false));
		scope.cubeList.add(new ModelBox(scope, 0, 0, 4.0F, -37.0F, 31.0F, 3, 3, 1, 0.0F, false));
		scope.cubeList.add(new ModelBox(scope, 0, 0, 3.0F, -38.0F, 31.0F, 3, 3, 1, 0.0F, false));
		scope.cubeList.add(new ModelBox(scope, 0, 0, 2.0F, -39.0F, 31.0F, 3, 3, 1, 0.0F, false));
		scope.cubeList.add(new ModelBox(scope, 0, 0, -7.0F, -37.0F, 31.0F, 3, 3, 1, 0.0F, false));
		scope.cubeList.add(new ModelBox(scope, 0, 0, -6.0F, -38.0F, 31.0F, 3, 3, 1, 0.0F, false));
		scope.cubeList.add(new ModelBox(scope, 0, 0, -5.0F, -39.0F, 31.0F, 3, 3, 1, 0.0F, false));
		scope.cubeList.add(new ModelBox(scope, 0, 0, 4.0F, -30.0F, 31.0F, 3, 3, 1, 0.0F, false));
		scope.cubeList.add(new ModelBox(scope, 0, 0, 3.0F, -29.0F, 31.0F, 3, 3, 1, 0.0F, false));
		scope.cubeList.add(new ModelBox(scope, 0, 0, 2.0F, -28.0F, 31.0F, 3, 3, 1, 0.0F, false));
		scope.cubeList.add(new ModelBox(scope, 0, 0, -5.0F, -28.0F, 31.0F, 3, 3, 1, 0.0F, false));
		scope.cubeList.add(new ModelBox(scope, 0, 0, -6.0F, -29.0F, 31.0F, 3, 3, 1, 0.0F, false));
		scope.cubeList.add(new ModelBox(scope, 0, 0, -7.0F, -30.0F, 31.0F, 3, 3, 1, 0.0F, false));
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z)
	{
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
	
	@Override
	public void render(ItemStack stack)
	{
		EntityPlayerSP player = Minecraft.getMinecraft().player;
		
		if(player != null)
		{
			renderVSS();
		}
	}
	
	private void renderVSS()
	{
		GlStateManager.pushMatrix();
		transform.defaultARTransform();
		GlStateManager.translate(0.0, 8.0, -14.0);
		renderParts();
		GlStateManager.popMatrix();
	}
	
	private void renderParts()
	{
		base.render(1f);
		shape1.render(1f);
		mag.render(1f);
		trigger.render(1f);
		scope.render(1f);
	}
}

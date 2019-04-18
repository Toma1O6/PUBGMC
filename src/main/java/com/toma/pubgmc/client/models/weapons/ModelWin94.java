package com.toma.pubgmc.client.models.weapons;

import com.toma.pubgmc.client.models.ModelGun;
import com.toma.pubgmc.common.capability.IPlayerData;
import com.toma.pubgmc.common.capability.IPlayerData.PlayerDataProvider;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.item.ItemStack;

public class ModelWin94 extends ModelGun
{
	private final ModelRenderer base;

	public ModelWin94()
	{
		super();
		
		textureWidth = 128;
		textureHeight = 128;

		base = new ModelRenderer(this);
		base.setRotationPoint(0.0F, 24.0F, 0.0F);
		base.cubeList.add(new ModelBox(base, 0, 32, -2.5F, -15.5F, 15.0F, 5, 4, 13, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 32, -2.5F, -13.5F, 0.0F, 5, 2, 15, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 32, -2.5F, -16.5F, 0.0F, 5, 3, 15, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -2.0F, -16.0F, -21.0F, 4, 2, 24, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -2.0F, -16.0F, -45.0F, 4, 2, 24, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -1.5F, -17.0F, -44.0F, 3, 1, 16, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -2.0F, -19.0F, -45.0F, 4, 2, 24, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 32, -2.5F, -18.5F, -28.0F, 5, 4, 23, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -2.0F, -19.0F, -21.0F, 4, 3, 24, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 32, -2.5F, -12.5F, 20.0F, 5, 3, 13, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 32, -2.5F, -9.5F, 26.0F, 5, 2, 2, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 32, -2.5F, -7.5F, 29.0F, 5, 2, 13, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 32, -2.5F, -13.5F, 28.0F, 5, 6, 14, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 32, -2.5F, -18.5F, 0.0F, 5, 2, 9, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -0.5F, -19.7F, -44.0F, 1, 1, 2, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -1.25F, -19.5F, 0.0F, 1, 1, 2, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, 0.5F, -19.5F, 0.0F, 1, 1, 2, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -1.0F, -12.0F, 1.0F, 2, 2, 1, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -1.0F, -10.0F, 2.0F, 2, 1, 1, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -1.0F, -9.0F, 3.0F, 2, 1, 4, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -1.0F, -12.0F, 7.0F, 2, 3, 1, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -1.0F, -12.0F, 5.0F, 2, 2, 1, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 32, -2.5F, -18.5F, -5.0F, 5, 4, 5, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 32, -2.5F, -14.5F, -3.0F, 5, 1, 3, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 32, -2.5F, -13.5F, -2.0F, 5, 1, 2, 0.0F, false));
	}
	
	@Override
	public void initAnimations()
	{
		initAimAnimation(-0.5625f, 0.365f, 0.29f);
		initAimingAnimationStates(0.365f);
	}
	
	@Override
	public void render(ItemStack stack)
	{
		EntityPlayerSP player = Minecraft.getMinecraft().player;
		
		if(player != null && player.hasCapability(PlayerDataProvider.PLAYER_DATA, null))
		{
			IPlayerData data = player.getCapability(PlayerDataProvider.PLAYER_DATA, null);
			
			GlStateManager.pushMatrix();
			{
				renderWin(data.isAiming());
			}
			GlStateManager.popMatrix();
		}
	}
	
	private void renderWin(boolean aim)
	{
		GlStateManager.pushMatrix();
		transform.defaultPistolTransform();
		GlStateManager.scale(0.59, 0.59, 0.59);
		GlStateManager.translate(-0.2, 15, 4);
		if(aim) rotateModelForADSRendering();
		base.render(1f);
		GlStateManager.popMatrix();
	}
	
	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}

package com.toma.pubgmc.client.models.weapons;

import com.toma.pubgmc.common.capability.IPlayerData;
import com.toma.pubgmc.common.capability.IPlayerData.PlayerDataProvider;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.item.ItemStack;

public class ModelFlareGun extends ModelPistolBase
{
	private final ModelRenderer base;

	public ModelFlareGun()
	{
		textureWidth = 128;
		textureHeight = 128;

		base = new ModelRenderer(this);
		base.setRotationPoint(0.0F, 24.0F, 0.0F);
		base.cubeList.add(new ModelBox(base, 0, 0, -2.0F, -8.0F, -2.0F, 4, 8, 4, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -2.5F, -10.0F, -4.0F, 5, 2, 5, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -2.5F, -12.0F, -16.0F, 5, 2, 16, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -2.0F, -8.0F, -3.0F, 4, 1, 1, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -2.5F, -10.0F, -5.0F, 5, 1, 1, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -2.5F, -14.0F, -16.0F, 5, 2, 14, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 32, -2.5F, -7.0F, -1.5F, 5, 6, 3, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -1.0F, -6.0F, -5.0F, 2, 1, 3, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -1.0F, -9.0F, -6.0F, 2, 3, 1, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 33, -1.0F, -8.7F, -3.9F, 2, 2, 1, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -0.5F, -14.5F, -15.0F, 1, 1, 1, 0.0F, false));
	}

	@Override
	public void render(ItemStack stack)
	{
		EntityPlayerSP player = Minecraft.getMinecraft().player;
		
		if(player != null && player.hasCapability(PlayerDataProvider.PLAYER_DATA, null))
		{
			IPlayerData data = player.getCapability(PlayerDataProvider.PLAYER_DATA, null);
			
			if(data.isAiming())
			{
				GlStateManager.pushMatrix();
				{
					transform.defaultPistolTransform();
					GlStateManager.translate(18.69, -9.9, 0.0);
					base.render(1f);
				}
				GlStateManager.popMatrix();
			}
			else
			{
				GlStateManager.pushMatrix();
				{
					transform.defaultPistolTransform();
					GlStateManager.translate(-2.0, 0.0, 0.0);
					base.render(1f);
				}
				GlStateManager.popMatrix();
			}
		}
	}
	
	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z)
	{
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
	
	@Override
	public boolean hasScope()
	{
		return false;
	}
	
	@Override
	public boolean hasSilencer()
	{
		return false;
	}
}

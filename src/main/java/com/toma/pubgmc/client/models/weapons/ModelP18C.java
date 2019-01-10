package com.toma.pubgmc.client.models.weapons;

import com.toma.pubgmc.common.capability.IPlayerData;
import com.toma.pubgmc.common.capability.IPlayerData.PlayerDataProvider;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;

public class ModelP18C extends ModelPistolBase
{
	private final ModelRenderer barrel;
	private final ModelRenderer handle;

	public ModelP18C() 
	{
		textureWidth = 128;
		textureHeight = 128;

		barrel = new ModelRenderer(this);
		barrel.setRotationPoint(0.0F, 24.0F, 0.0F);
		barrel.cubeList.add(new ModelBox(barrel, 0, 0, -2.0F, -9.0F, -14.0F, 4, 1, 16, 0.0F, false));
		barrel.cubeList.add(new ModelBox(barrel, 0, 32, -2.0F, -12.0F, -14.0F, 4, 3, 16, 0.0F, false));
		barrel.cubeList.add(new ModelBox(barrel, 17, 64, -1.5F, -12.5F, -1.0F, 1, 1, 2, 0.0F, false));
		barrel.cubeList.add(new ModelBox(barrel, 17, 64, 0.5F, -12.5F, -1.0F, 1, 1, 2, 0.0F, false));
		barrel.cubeList.add(new ModelBox(barrel, 17, 64, -0.5F, -12.5F, -13.0F, 1, 1, 1, 0.0F, false));
		barrel.cubeList.add(new ModelBox(barrel, 0, 0, -1.0F, -6.0F, -4.0F, 2, 1, 3, 0.0F, false));
		barrel.cubeList.add(new ModelBox(barrel, 0, 0, -1.0F, -8.0F, -6.0F, 2, 1, 1, 0.0F, false));
		barrel.cubeList.add(new ModelBox(barrel, 0, 0, -1.0F, -7.0F, -5.0F, 2, 1, 1, 0.0F, false));

		handle = new ModelRenderer(this);
		handle.setRotationPoint(0.0F, 24.0F, 0.0F);
		handle.cubeList.add(new ModelBox(handle, 0, 0, -2.0F, -9.0F, -2.0F, 4, 9, 4, 0.0F, false));
		handle.cubeList.add(new ModelBox(handle, 0, 0, -1.0F, -9.0F, -3.5F, 2, 2, 1, 0.0F, false));
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
					GlStateManager.translate(18.68, -12.2, 10);
					
					if(hasRedDot(stack))
					{
						GlStateManager.translate(0, 2, 0);
					}
					
					renderAll();
				}
				GlStateManager.popMatrix();
				
				GlStateManager.pushMatrix();
				{
					transform.defaultPistolSilencerTransform();
					GlStateManager.translate(-5.5, 3.2, 2.35);
					
					if(hasRedDot(stack))
					{
						GlStateManager.translate(0, -0.65, 0);
					}
					
					renderBarrelAttachments(stack);
				}
				GlStateManager.popMatrix();
				
				GlStateManager.pushMatrix();
				{
					transform.defaultPistolRedDotTransform();
					GlStateManager.translate(36.85, -16.5, 15);
					renderRedDot(stack);
				}
				GlStateManager.popMatrix();
			}
			
			else
			{
				GlStateManager.pushMatrix();
				{
					transform.defaultPistolTransform();
					renderAll();
				}
				GlStateManager.popMatrix();
				
				GlStateManager.pushMatrix();
				{
					transform.defaultPistolSilencerTransform();
					GlStateManager.translate(0.1, -0.5, -0.6);
					renderBarrelAttachments(stack);
				}
				GlStateManager.popMatrix();
				
				GlStateManager.pushMatrix();
				{
					transform.defaultPistolRedDotTransform();
					GlStateManager.translate(-0.4, 4, -1);
					renderRedDot(stack);
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
		return true;
	}
	
	@Override
	public boolean hasSilencer()
	{
		return true;
	}
	
	private void renderAll()
	{
		this.barrel.render(1f);
		this.handle.render(1f);
	}
}

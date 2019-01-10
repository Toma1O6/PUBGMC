package com.toma.pubgmc.client.models.weapons;

import com.toma.pubgmc.common.capability.IPlayerData;
import com.toma.pubgmc.common.capability.IPlayerData.PlayerDataProvider;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.item.ItemStack;

public class ModelP1911 extends ModelPistolBase
{
	public ModelRenderer barrel;
	public ModelRenderer handle;
	public ModelRenderer other;
	public ModelRenderer ir;

	public ModelP1911()
	{
		textureWidth = 128;
		textureHeight = 128;

		barrel = new ModelRenderer(this);
		barrel.setRotationPoint(0.0F, 24.0F, 0.0F);
		barrel.cubeList.add(new ModelBox(barrel, 0, 0, -3.0F, -12.0F, -16.0F, 6, 2, 20, 0.0F, false));
		barrel.cubeList.add(new ModelBox(barrel, 0, 0, -2.5F, -13.0F, -16.0F, 5, 1, 19, 0.0F, false));
		barrel.cubeList.add(new ModelBox(barrel, 0, 0, -2.0F, -14.0F, -16.0F, 4, 1, 18, 0.0F, false));
		barrel.cubeList.add(new ModelBox(barrel, 0, 0, -2.5F, -10.0F, -16.0F, 5, 1, 20, 0.0F, false));
		barrel.cubeList.add(new ModelBox(barrel, 0, 66, -1.0F, -12.5F, -16.5F, 2, 2, 1, 0.0F, false));

		handle = new ModelRenderer(this);
		handle.setRotationPoint(0.0F, 24.0F, 0.0F);
		handle.cubeList.add(new ModelBox(handle, 0, 64, -3.0F, -8.0F, 1.0F, 6, 6, 2, 0.0F, false));
		handle.cubeList.add(new ModelBox(handle, 0, 0, -2.5F, -9.0F, 0.0F, 5, 8, 4, 0.0F, false));

		other = new ModelRenderer(this);
		other.setRotationPoint(0.0F, 24.0F, 0.0F);
		other.cubeList.add(new ModelBox(other, 0, 0, -1.0F, -7.0F, -3.0F, 2, 1, 4, 0.0F, false));
		other.cubeList.add(new ModelBox(other, 0, 0, -1.0F, -8.0F, -4.0F, 2, 1, 1, 0.0F, false));
		other.cubeList.add(new ModelBox(other, 0, 0, -1.0F, -9.0F, -5.0F, 2, 1, 1, 0.0F, false));
		other.cubeList.add(new ModelBox(other, 0, 66, -1.0F, -9.5F, -2.0F, 2, 2, 1, 0.0F, false));

		ir = new ModelRenderer(this);
		ir.setRotationPoint(0.0F, 24.0F, 0.0F);
		ir.cubeList.add(new ModelBox(ir, 21, 68, -1.5F, -14.5F, 0.0F, 1, 1, 1, 0.0F, false));
		ir.cubeList.add(new ModelBox(ir, 21, 68, 0.5F, -14.5F, 0.0F, 1, 1, 1, 0.0F, false));
		ir.cubeList.add(new ModelBox(ir, 21, 68, -0.5F, -14.5F, -15.0F, 1, 1, 1, 0.0F, false));
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
	
	@Override
	public void render(ItemStack stack)
	{
		EntityPlayerSP player = Minecraft.getMinecraft().player;
		
		if(player != null)
		{
			if(player.hasCapability(PlayerDataProvider.PLAYER_DATA, null))
			{
				IPlayerData data = player.getCapability(PlayerDataProvider.PLAYER_DATA, null);
				
				if(data.isAiming())
				{
					//weapon
					GlStateManager.pushMatrix();
					{
						transform.defaultPistolTransform();
						
						GlStateManager.translate(0, 1, 0);
						GlStateManager.translate(18.7, -11, 8);
						
						if(hasRedDot(stack))
						{
							GlStateManager.translate(0, 2, 0);
						}
						renderAll();
					}
					GlStateManager.popMatrix();
					
					//suppressor
					GlStateManager.pushMatrix();
					{
						GlStateManager.scale(0.09, 0.09, 0.09);
						GlStateManager.translate(6.05, -16, 1.4);
						GlStateManager.translate(-6.22, 2.8, 1.6);
						
						if(hasRedDot(stack))
						{
							GlStateManager.translate(0, -0.7, 0);
						}
						
						renderBarrelAttachments(stack);
					}
					GlStateManager.popMatrix();
					
					GlStateManager.pushMatrix();
					{
						GlStateManager.scale(0.1, 0.1, 0.1);
						GlStateManager.scale(0.15, 0.15, 0.15);
						GlStateManager.rotate(180, 0, 1, 0);
						GlStateManager.rotate(180, 1, 0, 0);
						GlStateManager.translate(-33.5, -73, 32);
						GlStateManager.translate(37.5, -14, 15);
						renderRedDot(stack);
					}
					GlStateManager.popMatrix();
				}
				
				else
				{
					//weapon
					GlStateManager.pushMatrix();
					{
						transform.defaultPistolTransform();
						
						GlStateManager.translate(0, -2, 1);
						renderAll();
					}
					GlStateManager.popMatrix();
					
					//suppressor
					GlStateManager.pushMatrix();
					{
						GlStateManager.scale(0.09, 0.09, 0.09);
						GlStateManager.translate(6.05, -16, 1.2);
						renderBarrelAttachments(stack);
					}
					GlStateManager.popMatrix();
					
					GlStateManager.pushMatrix();
					{
						GlStateManager.scale(0.1, 0.1, 0.1);
						GlStateManager.scale(0.15, 0.15, 0.15);
						GlStateManager.rotate(180, 0, 1, 0);
						GlStateManager.rotate(180, 1, 0, 0);
						GlStateManager.translate(-33.5, -75, 32);
						renderRedDot(stack);
					}
					GlStateManager.popMatrix();
				}
			}
		}
	}
	
	private void renderAll()
	{
		this.barrel.render(1f);
		this.handle.render(1f);
		this.other.render(1f);
		this.ir.render(1f);
	}
}

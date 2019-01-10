package com.toma.pubgmc.client.models.weapons;

import com.toma.pubgmc.client.models.ModelGun;
import com.toma.pubgmc.client.util.ModelDebugger;
import com.toma.pubgmc.common.capability.IPlayerData;
import com.toma.pubgmc.common.capability.IPlayerData.PlayerDataProvider;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.item.ItemStack;

public class ModelS12K extends ModelGun
{
	private final ModelRenderer base;
	private final ModelRenderer mag1;
	private final ModelRenderer handle;
	private final ModelRenderer rail;

	public ModelS12K()
	{
		textureWidth = 128;
		textureHeight = 128;

		base = new ModelRenderer(this);
		base.setRotationPoint(0.0F, 24.0F, 0.0F);
		base.cubeList.add(new ModelBox(base, 0, 0, -1.0F, -16.0F, -28.0F, 2, 2, 3, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -2.0F, -18.0F, -25.0F, 4, 5, 24, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -2.5F, -17.0F, -25.0F, 5, 5, 23, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -1.5F, -18.5F, -25.0F, 3, 1, 27, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -1.5F, -18.5F, 2.0F, 3, 1, 9, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -2.0F, -18.0F, -1.0F, 4, 5, 12, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -2.5F, -17.0F, -2.0F, 5, 5, 13, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -1.0F, -12.0F, -2.0F, 2, 2, 1, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -1.0F, -10.0F, -1.0F, 2, 1, 1, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -1.0F, -9.0F, 0.0F, 2, 1, 3, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -1.0F, -10.0F, 3.0F, 2, 1, 2, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -2.0F, -16.0F, 11.0F, 4, 5, 6, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -2.0F, -15.0F, 15.0F, 4, 6, 6, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -2.0F, -14.0F, 19.0F, 4, 7, 6, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -2.0F, -17.0F, 11.0F, 4, 1, 2, 0.0F, false));

		mag1 = new ModelRenderer(this);
		mag1.setRotationPoint(0f, 24.0F, 0.0F);
		mag1.cubeList.add(new ModelBox(mag1, 0, 0, -1.0F, -14.0F, 1.0F, 2, 4, 1, 0.0F, false));
		mag1.cubeList.add(new ModelBox(mag1, 0, 0, -1.5F, -14.0F, -10.0F, 3, 12, 6, 0.0F, false));

		handle = new ModelRenderer(this);
		handle.setRotationPoint(0f, 24.0F, 0.0F);
		handle.cubeList.add(new ModelBox(handle, 0, 0, -1.5F, -12.0F, 4.0F, 3, 7, 4, 0.0F, false));

		rail = new ModelRenderer(this);
		rail.setRotationPoint(0.0F, 24.0F, 0.0F);
		rail.cubeList.add(new ModelBox(rail, 0, 0, -1.5F, -19.0F, 4.0F, 3, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 0, -1.5F, -19.0F, 2.0F, 3, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 0, -1.5F, -19.0F, 0.0F, 3, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 0, -1.5F, -19.0F, -10.0F, 3, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 0, -1.5F, -19.0F, -8.0F, 3, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 0, -1.5F, -19.0F, -6.0F, 3, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 0, -1.5F, -19.0F, -4.0F, 3, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 0, -1.5F, -19.0F, -2.0F, 3, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 0, -1.5F, -19.0F, -20.0F, 3, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 0, -1.5F, -19.0F, -18.0F, 3, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 0, -1.5F, -19.0F, -16.0F, 3, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 0, -1.5F, -19.0F, -14.0F, 3, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 0, -1.5F, -19.0F, -12.0F, 3, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 0, -1.5F, -19.0F, 6.0F, 3, 1, 4, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 0, 0.3F, -19.3F, 7.0F, 1, 1, 2, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 0, -1.3F, -19.3F, 7.0F, 1, 1, 2, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 0, -0.5F, -19.5F, -24.0F, 1, 2, 2, 0.0F, false));
	}
	
	@Override
	public void render(ItemStack stack)
	{
		EntityPlayerSP player = Minecraft.getMinecraft().player;
		if(player != null && player.hasCapability(PlayerDataProvider.PLAYER_DATA, null))
		{
			IPlayerData data = player.getCapability(PlayerDataProvider.PLAYER_DATA, null);
			
			if(data.isAiming() && stack.hasTagCompound())
			{
				renderWeapon(true, stack);
				
				if(hasRedDot(stack))
				{
					renderRedDotScope(true, stack);
				}
				
				else if(hasHoloSight(stack))
				{
					renderHolographic(true, stack);
				}
			}
			
			else
			{
				renderWeapon(false, stack);
				
				if(hasRedDot(stack))
				{
					renderRedDotScope(false, stack);
				}
				
				else if(hasHoloSight(stack))
				{
					renderHolographic(false, stack);
				}
				
				else if(has2X(stack))
				{
					render2X(stack);
				}
				
				else if(has4X(stack))
				{
					render4X(stack);
				}
			}
		}
	}
	
	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z)
	{
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
	
	private void renderGun()
	{
		base.render(1f);
		
		//rotate magazine
		GlStateManager.pushMatrix();
		{
			GlStateManager.rotate(-15, 1, 0, 0);
			GlStateManager.translate(0.0, -1.3, 4.0);
			mag1.render(1f);
		}
		GlStateManager.popMatrix();
		
		//rotate handle
		GlStateManager.pushMatrix();
		{
			GlStateManager.rotate(15, 1, 0, 0);
			GlStateManager.translate(0.0, 1.0, -3.0);
			handle.render(1f);
		}
		GlStateManager.popMatrix();
		rail.render(1f);
	}
	
	private void renderWeapon(boolean aim, ItemStack stack)
	{
		GlStateManager.pushMatrix();
		{
			transform.defaultShotgunTransform();
			
			if(aim)
			{
				GlStateManager.rotate(1, 1, 0, 0);

				if(enableADS(stack))
				{
					if(hasRedDot(stack))
					{
						GlStateManager.translate(0.0, 3.1, 0.0);
					}
					
					else if(hasHoloSight(stack))
					{
						GlStateManager.translate(0, 4.9, 0);
					}
					
					GlStateManager.translate(29.1, -17.4, 17.0);
				}
			}
			
			GlStateManager.translate(0.0, 5.0, 0.0);
			renderGun();
		}
		GlStateManager.popMatrix();
	}
	
	private void renderRedDotScope(boolean aim, ItemStack stack)
	{
		GlStateManager.pushMatrix();
		
		if(aim)
		{
			renderRedDot(36.8, -16.6, 24, 1f, stack);
		}
		
		else renderRedDot(1.1, 1.1, 7.0, 1f, stack);
		
		GlStateManager.popMatrix();
	}
	
	private void renderHolographic(boolean aim, ItemStack stack)
	{
		GlStateManager.pushMatrix();
		if(aim)
		{
			renderHolo(29.1, -12.6, 17, 1f, stack);
		}
		
		else renderHolo(0, 0, 0, 1f, stack);
		
		GlStateManager.popMatrix();
	}
	
	private void render2X(ItemStack stack)
	{
		GlStateManager.pushMatrix();
		renderScope2X(12.2, 21.7, -3, 0.8f, stack);
		GlStateManager.popMatrix();
	}
	
	private void render4X(ItemStack stack)
	{
		GlStateManager.pushMatrix();
		renderScope4X(12.1, 22, 0, 0.8f, stack);
		GlStateManager.popMatrix();
	}
}

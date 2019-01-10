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

public class ModelUmp9 extends ModelGun
{
	private final ModelRenderer ump;
	private final ModelRenderer mag;
	private final ModelRenderer handle;
	private final ModelRenderer stock;

	public ModelUmp9()
	{
		textureWidth = 128;
		textureHeight = 128;

		ump = new ModelRenderer(this);
		ump.setRotationPoint(0.0F, 24.0F, 0.0F);
		ump.cubeList.add(new ModelBox(ump, 0, 0, -0.5F, -25.0F, -25.0F, 1, 2, 2, 0.0F, false));
		ump.cubeList.add(new ModelBox(ump, 0, 0, -3.0F, -23.0F, -26.0F, 6, 5, 26, 0.0F, false));
		ump.cubeList.add(new ModelBox(ump, 0, 0, -1.0F, -21.0F, -28.0F, 2, 2, 2, 0.0F, false));
		ump.cubeList.add(new ModelBox(ump, 0, 0, -2.5F, -23.5F, -25.5F, 5, 1, 25, 0.0F, false));
		ump.cubeList.add(new ModelBox(ump, 0, 0, -2.0F, -23.7F, -15.0F, 4, 1, 1, 0.0F, false));
		ump.cubeList.add(new ModelBox(ump, 0, 0, -2.0F, -23.7F, -13.0F, 4, 1, 1, 0.0F, false));
		ump.cubeList.add(new ModelBox(ump, 0, 0, -2.0F, -23.7F, -11.0F, 4, 1, 1, 0.0F, false));
		ump.cubeList.add(new ModelBox(ump, 0, 0, -2.0F, -23.7F, -9.0F, 4, 1, 1, 0.0F, false));
		ump.cubeList.add(new ModelBox(ump, 0, 0, -2.0F, -23.7F, -7.0F, 4, 1, 1, 0.0F, false));
		ump.cubeList.add(new ModelBox(ump, 0, 0, -2.0F, -23.7F, -5.0F, 4, 1, 1, 0.0F, false));
		ump.cubeList.add(new ModelBox(ump, 0, 0, -2.0F, -23.7F, -3.0F, 4, 1, 1, 0.0F, false));
		ump.cubeList.add(new ModelBox(ump, 0, 0, -2.0F, -23.7F, -17.0F, 4, 1, 1, 0.0F, false));
		ump.cubeList.add(new ModelBox(ump, 0, 0, -3.0F, -23.0F, 0.0F, 6, 5, 5, 0.0F, false));
		ump.cubeList.add(new ModelBox(ump, 0, 0, -2.5F, -23.5F, -0.5F, 5, 1, 2, 0.0F, false));
		ump.cubeList.add(new ModelBox(ump, 0, 0, -2.0F, -24.0F, -1.0F, 4, 1, 1, 0.0F, false));
		ump.cubeList.add(new ModelBox(ump, 0, 0, -1.0F, -24.0F, 2.0F, 2, 1, 1, 0.0F, false));
		ump.cubeList.add(new ModelBox(ump, 0, 0, -1.7F, -26.0F, 2.0F, 1, 2, 1, 0.0F, false));
		ump.cubeList.add(new ModelBox(ump, 0, 0, 0.7F, -26.0F, 2.0F, 1, 2, 1, 0.0F, false));
		ump.cubeList.add(new ModelBox(ump, 0, 0, -1.0F, -27.0F, 2.0F, 2, 1, 1, 0.0F, false));
		ump.cubeList.add(new ModelBox(ump, 0, 0, -3.0F, -25.0F, 0.0F, 1, 2, 4, 0.0F, false));
		ump.cubeList.add(new ModelBox(ump, 0, 0, 2.0F, -25.0F, 0.0F, 1, 2, 4, 0.0F, false));
		ump.cubeList.add(new ModelBox(ump, 0, 0, -3.0F, -22.0F, 5.0F, 6, 4, 2, 0.0F, false));
		ump.cubeList.add(new ModelBox(ump, 0, 0, -2.5F, -18.0F, -13.0F, 5, 2, 20, 0.0F, false));
		ump.cubeList.add(new ModelBox(ump, 0, 0, -2.5F, -16.0F, -13.0F, 5, 2, 7, 0.0F, false));
		ump.cubeList.add(new ModelBox(ump, 0, 0, -1.5F, -16.0F, -6.0F, 3, 2, 1, 0.0F, false));
		ump.cubeList.add(new ModelBox(ump, 0, 0, -1.5F, -14.0F, -5.0F, 3, 1, 1, 0.0F, false));
		ump.cubeList.add(new ModelBox(ump, 0, 0, -1.5F, -13.0F, -4.0F, 3, 1, 3, 0.0F, false));
		ump.cubeList.add(new ModelBox(ump, 0, 0, -1.5F, -14.0F, -1.0F, 3, 1, 2, 0.0F, false));
		ump.cubeList.add(new ModelBox(ump, 0, 0, -1.5F, -21.0F, 7.0F, 3, 1, 14, 0.0F, false));
		ump.cubeList.add(new ModelBox(ump, 0, 0, -1.5F, -21.0F, 21.0F, 3, 11, 2, 0.0F, false));
		ump.cubeList.add(new ModelBox(ump, 0, 0, -1.5F, -20.5F, 12.0F, 3, 6, 1, 0.0F, false));
		ump.cubeList.add(new ModelBox(ump, 0, 0, -2.0F, -23.7F, -19.0F, 4, 1, 1, 0.0F, false));
		ump.cubeList.add(new ModelBox(ump, 0, 0, -3.5F, -21.5F, -24.0F, 7, 4, 1, 0.0F, false));
		ump.cubeList.add(new ModelBox(ump, 0, 0, -3.5F, -21.5F, -22.0F, 7, 4, 1, 0.0F, false));
		ump.cubeList.add(new ModelBox(ump, 0, 0, -3.5F, -21.5F, -20.0F, 7, 4, 1, 0.0F, false));
		ump.cubeList.add(new ModelBox(ump, 0, 0, -3.5F, -21.5F, -18.0F, 7, 4, 1, 0.0F, false));
		ump.cubeList.add(new ModelBox(ump, 0, 0, -3.5F, -21.5F, -16.0F, 7, 4, 1, 0.0F, false));

		mag = new ModelRenderer(this);
		mag.setRotationPoint(0.0F, 24.0F, 0.0F);
		setRotationAngle(mag, -0.1745F, 0.0F, 0.0F);
		mag.cubeList.add(new ModelBox(mag, 0, 0, -1.5F, -13.0F, -14.4F, 3, 14, 5, 0.0F, false));
		mag.cubeList.add(new ModelBox(mag, 0, 0, -1.0F, -16.0F, -5.0F, 3, 2, 1, 0.0F, false));

		handle = new ModelRenderer(this);
		handle.setRotationPoint(0.0F, 24.0F, 0.0F);
		setRotationAngle(handle, 0.1745F, 0.0F, 0.0F);
		handle.cubeList.add(new ModelBox(handle, 0, 0, -2.0F, -16.0F, 3.0F, 4, 10, 4, 0.0F, false));

		stock = new ModelRenderer(this);
		stock.setRotationPoint(0.0F, 24.0F, 0.0F);
		setRotationAngle(stock, -0.4363F, 0.0F, 0.0F);
		stock.cubeList.add(new ModelBox(stock, 0, 0, -1.5F, -19.0F, -2.0F, 3, 1, 17, 0.0F, false));
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
		
		if(player != null && player.hasCapability(PlayerDataProvider.PLAYER_DATA, null))
		{
			IPlayerData data = player.getCapability(PlayerDataProvider.PLAYER_DATA, null);
			
			if(data.isAiming())
			{
				renderUmp(true, stack);
				
				if(hasSilencer(stack))
				{
					renderSilencer(true, stack);
				}
				
				if(hasVerticalGrip(stack))
				{
					renderGripVertical(true, stack);
				}
				
				else if(hasAngledGrip(stack))
				{
					renderGripAngled(true, stack);
				}
				
				if(hasRedDot(stack))
				{
					renderRedDotSight(true, stack);
				}
				
				else if(hasHoloSight(stack))
				{
					renderHolographic(true, stack);
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
			
			else
			{
				renderUmp(false, stack);
				
				if(hasSilencer(stack))
				{
					renderSilencer(false, stack);
				}
				
				if(hasVerticalGrip(stack))
				{
					renderGripVertical(false, stack);
				}
				
				else if(hasAngledGrip(stack))
				{
					renderGripAngled(false, stack);
				}
				
				if(hasRedDot(stack))
				{
					renderRedDotSight(false, stack);
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
	
	private void renderUmp(boolean aim, ItemStack stack)
	{
		GlStateManager.pushMatrix();
		transform.defaultSMGTransform();
		
		if(aim && enableADS(stack))
		{
			GlStateManager.translate(18.65, -4.4, 8.0);
			
			if(hasRedDot(stack))
			{
				GlStateManager.translate(0.0, 2.6, 0.0);
			}
			
			else if(hasHoloSight(stack))
			{
				GlStateManager.translate(0.0, 2.6, 0.0);
			}
		}
		
		renderAll();
		GlStateManager.popMatrix();
	}
	
	private void renderSilencer(boolean aim, ItemStack stack)
	{
		if(aim && enableADS(stack))
		{
			if(hasRedDot(stack))
			{
				renderSMGSilencer(-18.6, 4.3, -6.7, 1f, stack);
			}
			
			else if(hasHoloSight(stack))
			{
				renderSMGSilencer(-18.6, 4.2, -6.7, 1f, stack);
			}
			
			else renderSMGSilencer(-18.6, 7, -6.7, 1f, stack);
		}
		
		else renderSMGSilencer(0, 2.3, -15, 1f, stack);
	}
	
	private void renderRedDotSight(boolean aim, ItemStack stack)
	{
		if(aim)
		{
			renderRedDot(23.0, -17.3, 9, 1.6f, stack);
		}
		
		else renderRedDot(-0.3, -14.9, -2.7, 1.6f, stack);
	}
	
	private void renderHolographic(boolean aim, ItemStack stack)
	{
		if(aim)
		{
			renderHolo(20.69, -14.0, 1.0, 1.4f, stack);
		}
		
		else renderHolo(-1.5, -13.1, -7, 1.4f, stack);
	}
	
	private void render2X(ItemStack stack)
	{
		renderScope2X(8, 4, -10, 1f, stack);
	}
	
	private void render4X(ItemStack stack)
	{
		renderScope4X(8, 5, -11, 1f, stack);
	}
	
	private void renderGripVertical(boolean aim, ItemStack stack)
	{
		if(aim && enableADS(stack))
		{
			if(hasRedDot(stack))
			{
				renderVerticalGrip(27.4, -3, 10.0, 1f, stack);
			}
			
			else if(hasHoloSight(stack))
			{
				renderVerticalGrip(27.4, -2, 10.0, 1f, stack);
			}
			
			else renderVerticalGrip(0, 0, 0, 1f, stack);
		}
		
		else renderVerticalGrip(0, 0, 0, 1f, stack);
	}
	
	private void renderGripAngled(boolean aim, ItemStack stack)
	{
		if(aim && enableADS(stack))
		{
			if(hasRedDot(stack))
			{
				renderAngledGrip(-17.9, -1.4, -9, 1f, stack);
			}
			
			else if(hasHoloSight(stack))
			{
				renderAngledGrip(-17.9, -1.4, -9, 1f, stack);
			}
			
			else renderAngledGrip(-17.9, -4, -9, 1f, stack);
		}
		
		else renderAngledGrip(0, 0, 0, 1f, stack);
	}
	
	private void renderAll()
	{
		ump.render(1f);
		mag.render(1f);
		handle.render(1f);
		stock.render(1f);
	}
}

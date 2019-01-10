package com.toma.pubgmc.client.models.weapons;

import com.toma.pubgmc.client.util.ModelDebugger;
import com.toma.pubgmc.common.capability.IPlayerData;
import com.toma.pubgmc.common.capability.IPlayerData.PlayerDataProvider;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.item.ItemStack;

public class ModelR1895 extends ModelPistolBase
{
	private final ModelRenderer mag;
	private final ModelRenderer base;

	public ModelR1895() 
	{
		textureWidth = 128;
		textureHeight = 128;

		mag = new ModelRenderer(this);
		mag.setRotationPoint(0.0F, 24.0F, 0.0F);
		mag.cubeList.add(new ModelBox(mag, 0, 0, -3.0F, -15.0F, -7.0F, 6, 3, 3, 0.0F, false));

		base = new ModelRenderer(this);
		base.setRotationPoint(0.0F, 24.0F, 0.0F);
		base.cubeList.add(new ModelBox(base, 0, 32, -2.0F, -6.0F, -2.0F, 4, 6, 4, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 32, -2.0F, -8.0F, -3.0F, 4, 2, 4, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 32, -2.0F, -10.0F, -4.0F, 4, 2, 4, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -2.5F, -12.0F, -14.0F, 5, 3, 13, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -1.0F, -9.0F, -4.7F, 2, 2, 1, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 38, -1.0F, -6.0F, -6.0F, 2, 1, 3, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 32, -1.0F, -7.0F, -7.0F, 2, 1, 1, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 32, -1.0F, -9.0F, -8.0F, 2, 2, 1, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -2.5F, -15.0F, -4.0F, 5, 3, 2, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -2.5F, -15.0F, -22.0F, 5, 3, 15, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -2.5F, -14.0F, -10.0F, 5, 1, 3, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -0.5F, -11.0F, -19.0F, 1, 1, 5, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -1.0F, -11.5F, -20.0F, 2, 2, 1, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -2.0F, -16.0F, -22.0F, 4, 1, 19, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -0.5F, -16.5F, -21.0F, 1, 1, 2, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -0.5F, -17.0F, -21.0F, 1, 1, 1, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, 0.5F, -16.5F, -6.0F, 1, 1, 2, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -1.5F, -16.5F, -6.0F, 1, 1, 2, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -2.5F, -12.0F, -15.0F, 5, 1, 1, 0.0F, false));
	}
	
	@Override
	public boolean hasScope()
	{
		return false;
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
		
		if(player != null && player.hasCapability(PlayerDataProvider.PLAYER_DATA, null))
		{
			IPlayerData data = player.getCapability(PlayerDataProvider.PLAYER_DATA, null);
			
			if(data.isReloading())
			{
				GlStateManager.pushMatrix();
				{
					transform.defaultPistolTransform();
					GlStateManager.translate(0.0, 2.0, 0.0);
					base.render(1f);
				}
				GlStateManager.popMatrix();
				GlStateManager.pushMatrix();
				{
					transform.defaultPistolTransform();
					GlStateManager.translate(0.0, 2.0, 0.0);
					GlStateManager.translate(2.5, 0.0, 0.0);
					mag.render(1f);
				}
				GlStateManager.popMatrix();
				GlStateManager.pushMatrix();
				{
					transform.defaultPistolSilencerTransform();
					GlStateManager.scale(0.79999995, 0.79999995, 0.79999995);
					GlStateManager.translate(8.326672684688674E-17, 6.000000000000002, -3.5);
					renderBarrelAttachments(stack);
				}
				GlStateManager.popMatrix();
			}
			
			else
			{
				if(data.isAiming())
				{
					GlStateManager.pushMatrix();
					{
						transform.defaultPistolTransform();
						GlStateManager.translate(0.0, 2.0, 0.0);
						GlStateManager.translate(18.599999999999994, -10.0, 11.0);
						renderAll();
					}
					GlStateManager.popMatrix();
					
					GlStateManager.pushMatrix();
					{
						transform.defaultPistolSilencerTransform();
						GlStateManager.translate(-5.499999999999998, 2.9, 0.30000000000000004);
						renderBarrelAttachments(stack);
					}
					GlStateManager.popMatrix();
				}
				
				else
				{
					GlStateManager.pushMatrix();
					{
						transform.defaultPistolTransform();
						GlStateManager.translate(0.0, 2.0, 0.0);
						renderAll();
					}
					GlStateManager.popMatrix();
					
					GlStateManager.pushMatrix();
					{
						transform.defaultPistolSilencerTransform();
						GlStateManager.scale(0.79999995, 0.79999995, 0.79999995);
						GlStateManager.translate(8.326672684688674E-17, 6.000000000000002, -3.5);
						renderBarrelAttachments(stack);
					}
					GlStateManager.popMatrix();
				}
			}
		}
	}
	
	private void renderAll()
	{
		base.render(1f);
		mag.render(1f);
	}
}

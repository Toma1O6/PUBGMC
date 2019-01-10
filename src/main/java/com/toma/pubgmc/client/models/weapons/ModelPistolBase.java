package com.toma.pubgmc.client.models.weapons;

import com.toma.pubgmc.client.models.ModelGun;
import com.toma.pubgmc.client.models.atachments.ModelRedDotPistol;
import com.toma.pubgmc.client.models.atachments.ModelSilencerPistol;
import com.toma.pubgmc.common.items.guns.GunBase;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.item.ItemStack;

public abstract class ModelPistolBase extends ModelGun
{
	public final ModelSilencerPistol silencer = new ModelSilencerPistol();
	public final ModelRedDotPistol red_dot = new ModelRedDotPistol();
	
	public abstract boolean hasSilencer();
	
	public abstract boolean hasScope();
	
	public void renderSilencer()
	{
		if(hasSilencer())
		{
			EntityPlayerSP player = Minecraft.getMinecraft().player;
			
			if(player != null)
			{
				ItemStack gun = player.getHeldItemMainhand();
				
				if(gun.hasTagCompound())
				{
					if(gun.getTagCompound().getInteger("barrel") == 1)
					{
						silencer.render();
					}
					
					else return;
				}
				
				else return;
			}
		}
	}
	
	public void renderBarrelAttachments(ItemStack stack)
	{
		if(stack.hasTagCompound())
		{
			if(stack.getTagCompound().getInteger("barrel") == 1)
			{
				this.silencer.render();
			}
			
			else return;
		}
		
		else return;
	}
	
	public void renderRedDot(ItemStack stack)
	{
		if(stack.hasTagCompound())
		{
			if(stack.getTagCompound().getInteger("scope") == 1)
			{
				this.red_dot.render();
			}
			
			else return;
		}
		
		else return;
	}
}

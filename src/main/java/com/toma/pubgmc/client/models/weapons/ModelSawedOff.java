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

public class ModelSawedOff extends ModelGun
{
	private final ModelRenderer base;

	public ModelSawedOff()
	{
		textureWidth = 128;
		textureHeight = 128;

		base = new ModelRenderer(this);
		base.setRotationPoint(0.0F, 24.0F, 0.0F);
		base.cubeList.add(new ModelBox(base, 0, 0, -3.0F, -9.0F, -23.0F, 3, 3, 20, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, 1.0F, -9.0F, -23.0F, 3, 3, 20, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, 0.0F, -8.1F, -23.0F, 1, 2, 20, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 32, -3.5F, -9.0F, -3.0F, 8, 4, 5, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 32, -3.5F, -8.0F, -19.0F, 8, 3, 16, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 32, -1.5F, -8.0F, 2.0F, 4, 3, 6, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 32, -1.5F, -7.0F, 5.0F, 4, 3, 6, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 32, -1.5F, -6.0F, 8.0F, 4, 3, 6, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 32, -1.5F, -5.0F, 10.0F, 4, 4, 5, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 32, -2.5F, -9.0F, 2.0F, 6, 4, 3, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -1.0F, -5.0F, 0.0F, 2, 2, 1, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -1.0F, -3.0F, 1.0F, 2, 1, 3, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -1.0F, -4.0F, 4.0F, 2, 1, 1, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -1.0F, -6.0F, 2.5F, 2, 2, 1, 0.0F, false));
	}
	
	@Override
	public void render(ItemStack stack)
	{
		EntityPlayerSP player = Minecraft.getMinecraft().player;
		
		if(player != null && player.hasCapability(PlayerDataProvider.PLAYER_DATA, null))
		{
			IPlayerData data = player.getCapability(PlayerDataProvider.PLAYER_DATA, null);
			
			if(data != null && data.isAiming())
			{
				GlStateManager.pushMatrix();
				transform.defaultPistolTransform();
				GlStateManager.translate(18.2, -15.2, 10.0);
				base.render(1f);
				GlStateManager.popMatrix();
			}
			
			else
			{
				GlStateManager.pushMatrix();
				transform.defaultPistolTransform();
				base.render(1f);
				GlStateManager.popMatrix();
			}
		}
	}
}

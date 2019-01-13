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

public class ModelS686 extends ModelGun
{
	private final ModelRenderer base;

	public ModelS686()
	{
		textureWidth = 128;
		textureHeight = 128;

		base = new ModelRenderer(this);
		base.setRotationPoint(0.0F, 24.0F, 0.0F);
		base.cubeList.add(new ModelBox(base, 0, 0, -2.0F, -19.0F, -35.0F, 4, 4, 28, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -2.0F, -19.0F, -7.0F, 4, 4, 6, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -2.0F, -19.0F, -1.0F, 4, 9, 11, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -0.5F, -19.5F, -34.0F, 1, 1, 1, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 32, -2.0F, -18.0F, 10.0F, 4, 8, 4, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 32, -2.0F, -16.0F, 13.0F, 4, 7, 14, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 32, -2.0F, -9.0F, 18.0F, 4, 3, 15, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 32, -2.0F, -14.0F, 27.0F, 4, 5, 6, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -1.0F, -11.0F, 7.0F, 2, 2, 1, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -1.0F, -9.0F, 8.0F, 2, 1, 1, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -1.0F, -8.0F, 9.0F, 2, 1, 3, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -1.0F, -9.0F, 12.0F, 2, 1, 1, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -1.0F, -11.0F, 10.0F, 2, 2, 1, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -2.0F, -14.5F, -35.0F, 4, 4, 28, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -2.0F, -14.5F, -7.0F, 4, 4, 6, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -1.5F, -15.0F, -30.0F, 3, 1, 29, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -2.0F, -15.0F, -35.0F, 4, 1, 2, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -1.5F, -15.0F, -33.0F, 3, 1, 3, 0.0F, false));
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
				transform.defaultShotgunTransform();
				GlStateManager.translate(2.0, 3.0, 0.0);
				GlStateManager.translate(27.1, -15.2, 17.0);
				base.render(1f);
				GlStateManager.popMatrix();
			}
			else
			{
				GlStateManager.pushMatrix();
				transform.defaultShotgunTransform();
				GlStateManager.translate(2.0, 3.0, 0.0);
				base.render(1f);
				GlStateManager.popMatrix();
			}
		}
	}
}

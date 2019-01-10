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
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;

public class ModelS1897 extends ModelGun
{
	private final ModelRenderer base;

	public ModelS1897()
	{
		textureWidth = 128;
		textureHeight = 128;

		base = new ModelRenderer(this);
		base.setRotationPoint(0.0F, 24.0F, 0.0F);
		base.cubeList.add(new ModelBox(base, 120, 91, -1.0F, -12.0F, -35.5F, 2, 2, 2, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -2.0F, -13.0F, -35.0F, 4, 4, 28, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -1.0F, -9.0F, -28.0F, 2, 2, 19, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -2.0F, -13.0F, -7.0F, 4, 4, 6, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -1.0F, -9.0F, -9.0F, 2, 2, 8, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 34, 49, -1.5F, -9.0F, -21.0F, 3, 3, 12, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -2.0F, -13.0F, -1.0F, 4, 6, 11, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -0.5F, -13.5F, -34.0F, 1, 1, 1, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 32, -2.0F, -12.0F, 10.0F, 4, 5, 5, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 32, -2.0F, -10.0F, 13.0F, 4, 5, 14, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 32, -2.0F, -5.0F, 18.0F, 4, 3, 13, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 32, -2.0F, -8.0F, 27.0F, 4, 3, 4, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -1.0F, -7.0F, 7.0F, 2, 2, 1, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -1.0F, -5.0F, 8.0F, 2, 1, 1, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -1.0F, -4.0F, 9.0F, 2, 1, 3, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -1.0F, -5.0F, 12.0F, 2, 1, 1, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -1.0F, -8.0F, 10.0F, 2, 2, 1, 0.0F, false));
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
				GlStateManager.translate(29.1, -18.300000000000004, 16.599999999999994);
				base.render(1f);
				GlStateManager.popMatrix();
			}
			else
			{
				GlStateManager.pushMatrix();
				transform.defaultShotgunTransform();
				base.render(1f);
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
}

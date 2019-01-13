package com.toma.pubgmc.client.models.weapons;

import com.toma.pubgmc.animation.AimAnimation;
import com.toma.pubgmc.client.models.ModelGun;
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
		animation_aim = new AimAnimation(-0.545d, 0.455d, 0.285d, 1f);
		animation_aim.setInvertedCoords(true, false, false);
		animation_aim.setMovementMultiplier(1.2f, 1.85f, 1.2f);
		animation_held.setWeaponType(true);
		
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

			GlStateManager.pushMatrix();
			handleAnimations(data.isAiming(), player.isSprinting(), stack);
			renderSawedOff(data.isAiming(), stack);
			GlStateManager.popMatrix();
		}
	}
	
	private void handleAnimations(boolean aim, boolean sprint, ItemStack stack)
	{
		animation_aim.run(aim);
		animation_held.run(sprint);
	}
	
	private void renderSawedOff(boolean aim, ItemStack stack)
	{
		GlStateManager.pushMatrix();
		{
			transform.defaultPistolTransform();
			if(aim) rotateModelForADSRendering();
			base.render(1f);
		}
		GlStateManager.popMatrix();
	}
}

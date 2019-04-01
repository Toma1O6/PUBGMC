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

public class ModelP92 extends ModelGun
{	
	private final ModelRenderer base;
	private final ModelRenderer handle;
	private final ModelRenderer mag;
	private final ModelRenderer trigger;

	public ModelP92()
	{
		animation_aim = new AimAnimation(-0.65d, 0.31d, 0.24d).setInvertedCoords(true, false, false);
		animation_held.setWeaponType(true);
		
		textureWidth = 128;
		textureHeight = 128;

		base = new ModelRenderer(this);
		base.setRotationPoint(0.0F, 24.0F, 0.0F);
		base.cubeList.add(new ModelBox(base, 0, 0, -3.0F, -18.0F, -23.0F, 6, 3, 25, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -2.5F, -19.0F, -23.0F, 5, 1, 25, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 117, 55, 0.5F, -20.5F, -2.0F, 1, 1, 2, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 117, 55, -1.5F, -20.5F, -2.0F, 1, 1, 2, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 117, 36, -0.5F, -20.5F, -22.0F, 1, 1, 1, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -1.0F, -18.5F, -23.5F, 2, 2, 1, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -2.0F, -20.0F, -23.0F, 4, 1, 24, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -2.0F, -15.0F, -7.0F, 4, 2, 1, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -2.0F, -13.0F, -6.0F, 4, 1, 1, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -2.0F, -12.0F, -5.0F, 4, 1, 3, 0.0F, false));

		handle = new ModelRenderer(this);
		handle.setRotationPoint(0.0F, 24.0F, 0.0F);
		setRotationAngle(handle, 0.1745F, 0.0F, 0.0F);
		handle.cubeList.add(new ModelBox(handle, 0, 0, -2.5F, -16.0F, 0.0F, 5, 11, 5, 0.0F, false));
		handle.cubeList.add(new ModelBox(handle, 116, 72, -0.5F, -19.0F, 0.4F, 1, 2, 5, 0.0F, false));
		handle.cubeList.add(new ModelBox(handle, 0, 115, 1.7F, -13.5F, 0.5F, 1, 8, 4, 0.0F, false));
		handle.cubeList.add(new ModelBox(handle, 0, 115, -2.7F, -13.5F, 0.5F, 1, 8, 4, 0.0F, false));

		mag = new ModelRenderer(this);
		mag.setRotationPoint(0.0F, 24.0F, 0.0F);
		setRotationAngle(mag, 0.1745F, 0.0F, 0.0F);
		mag.cubeList.add(new ModelBox(mag, 64, 64, -2.0F, -13.8F, 1.0F, 4, 9, 3, 0.0F, false));

		trigger = new ModelRenderer(this);
		trigger.setRotationPoint(0.0F, 24.0F, 0.0F);
		setRotationAngle(trigger, -0.0873F, 0.0F, 0.0F);
		trigger.cubeList.add(new ModelBox(trigger, 122, 76, -1.0F, -15.0F, -5.5F, 2, 2, 1, 0.0F, false));
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
			handleAnimations(data.isAiming(), player.isSprinting(), data.isReloading(), stack);
			renderP92(data.isAiming(), stack);
		}
	}
	
	private void handleAnimations(boolean aim, boolean sprint, boolean reload, ItemStack stack)
	{
		if(enableADS(stack))
		{
			if(!hasScopeAtachment(stack) && animation_aim.getFinalY() != 0.31d)
				animation_aim.setYModifier(0.31d);
			else if(hasRedDot(stack) && animation_aim.getFinalY() != 0.23d)
				animation_aim.setYModifier(0.23d);
			
			animation_aim.run(aim);
		}
		animation_reload.run(reload);
		animation_held.run(sprint);
	}
	
	private void renderP92(boolean aim, ItemStack stack)
	{
		GlStateManager.pushMatrix();
		transform.defaultPistolTransform();
		GlStateManager.translate(-3.0, 6.0, 0.0);
		
		if(aim && enableADS(stack))
		{
			rotateModelForADSRendering();
		}
		
		renderParts();
		GlStateManager.popMatrix();
		
		if(hasSilencer(stack)) renderSilencer(stack);
		if(hasRedDot(stack)) renderRedDot(stack);
	}
	
	private void renderRedDot(ItemStack stack)
	{
		renderRedDot(-5.05, -5.3, -4, 1.3f, stack);
	}
	
	private void renderSilencer(ItemStack stack)
	{
		renderPistolSilencer(0.9, -4, -2.8, 1.2f, stack);
	}
	
	private void renderParts()
	{
		base.render(1f);
		handle.render(1f);
		mag.render(1f);
		trigger.render(1f);
	}
}

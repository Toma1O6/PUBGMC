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

public class ModelR45 extends ModelGun
{
	private final ModelRenderer base;
	private final ModelRenderer mag;
	private final ModelRenderer sh1;
	private final ModelRenderer trigger;
	private final ModelRenderer handle;
	private final ModelRenderer irns;

	public ModelR45()
	{
		animation_aim = new AimAnimation(-0.555d, 0.175d, 0.255d).setInvertedCoords(true, false, false);
		animation_held.setWeaponType(true);
		
		textureWidth = 128;
		textureHeight = 128;

		base = new ModelRenderer(this);
		base.setRotationPoint(0.0F, 24.0F, 0.0F);
		base.cubeList.add(new ModelBox(base, 0, 0, -2.0F, -15.0F, -30.0F, 4, 1, 20, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -2.0F, -13.0F, -31.0F, 4, 3, 21, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -2.5F, -12.3F, -31.0F, 5, 2, 21, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -2.0F, -14.5F, -31.0F, 4, 2, 1, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -2.0F, -14.0F, -30.0F, 4, 1, 2, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -2.0F, -14.0F, -26.0F, 4, 1, 2, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -2.0F, -14.0F, -22.0F, 4, 1, 2, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -2.0F, -14.0F, -18.0F, 4, 1, 2, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -2.5F, -14.0F, -16.0F, 5, 4, 6, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -3.0F, -15.5F, -10.0F, 6, 1, 6, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -3.0F, -9.0F, -10.0F, 6, 1, 6, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -3.0F, -15.5F, -4.0F, 6, 8, 2, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -2.5F, -12.0F, -13.0F, 5, 4, 3, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -3.0F, -11.2F, -2.05F, 6, 4, 3, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -2.0F, -8.0F, -12.0F, 4, 2, 1, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -3.0F, -8.0F, -6.0F, 6, 2, 7, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -2.0F, -6.0F, -11.0F, 4, 1, 5, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -2.0F, -15.5F, -14.0F, 4, 1, 4, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -0.5F, -16.1F, -29.0F, 1, 2, 2, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -1.5F, -12.0F, -31.3F, 3, 1, 1, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -1.5F, -14.0F, -31.3F, 3, 1, 1, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, 0.5F, -13.0F, -31.3F, 1, 1, 1, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -1.5F, -13.0F, -31.3F, 1, 1, 1, 0.0F, false));

		mag = new ModelRenderer(this);
		mag.setRotationPoint(0.0F, 24.0F, 0.0F);
		mag.cubeList.add(new ModelBox(mag, 0, 0, -3.0F, -14.0F, -10.0F, 6, 4, 6, 0.0F, false));
		mag.cubeList.add(new ModelBox(mag, 0, 0, -2.5F, -14.5F, -10.0F, 5, 5, 6, 0.0F, false));

		sh1 = new ModelRenderer(this);
		sh1.setRotationPoint(0.0F, 24.0F, 0.0F);
		setRotationAngle(sh1, -1.0472F, 0.0F, 0.0F);
		sh1.cubeList.add(new ModelBox(sh1, 0, 0, -3.0F, -6.2F, -14.3F, 6, 2, 5, 0.0F, false));
		sh1.cubeList.add(new ModelBox(sh1, 0, 0, -3.0F, -5.0F, -11.8F, 6, 2, 3, 0.0F, false));
		sh1.cubeList.add(new ModelBox(sh1, 0, 0, -0.5F, -6.8F, -13.5F, 1, 2, 3, 0.0F, false));

		trigger = new ModelRenderer(this);
		trigger.setRotationPoint(0.0F, 24.0F, 0.0F);
		setRotationAngle(trigger, -0.1745F, 0.0F, 0.0F);
		trigger.cubeList.add(new ModelBox(trigger, 0, 0, -1.0F, -7.0F, -9.0F, 2, 2, 1, 0.0F, false));

		handle = new ModelRenderer(this);
		handle.setRotationPoint(0.0F, 24.0F, 0.0F);
		setRotationAngle(handle, 0.1745F, 0.0F, 0.0F);
		handle.cubeList.add(new ModelBox(handle, 0, 64, -2.0F, -6.0F, -4.0F, 4, 11, 6, 0.0F, false));
		handle.cubeList.add(new ModelBox(handle, 0, 0, -3.0F, -7.0F, -4.0F, 6, 1, 6, 0.0F, false));

		irns = new ModelRenderer(this);
		irns.setRotationPoint(0.0F, 24.0F, 0.0F);
		irns.cubeList.add(new ModelBox(irns, 0, 0, -1.5F, -16.0F, -5.0F, 1, 1, 2, 0.0F, false));
		irns.cubeList.add(new ModelBox(irns, 0, 0, 0.5F, -16.0F, -5.0F, 1, 1, 2, 0.0F, false));
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
			GlStateManager.pushMatrix();
			{
				handleAnimations(data, player.isSprinting(), stack);
				renderR45(data.isAiming(), stack);
			}
			GlStateManager.popMatrix();
		}
	}
	
	private void handleAnimations(IPlayerData data, boolean sprint, ItemStack stack)
	{
		if(enableADS(stack))
		{
			if(!hasScopeAtachment(stack) && animation_aim.getFinalY() != 0.175d) 
			{
				animation_aim.setYModifier(0.175d);
			}
			
			else if(hasRedDot(stack) && animation_aim.getFinalY() != 0.095d)
			{
				animation_aim.setYModifier(0.095d);
			}
			
			animation_aim.run(data.isAiming());
		}
		
		animation_held.run(sprint);
		animation_reload.run(data.isReloading());
	}
	
	private void renderR45(boolean aim, ItemStack stack)
	{
		GlStateManager.pushMatrix();
		{
			transform.defaultPistolTransform();
			GlStateManager.translate(0.15, -3.0, 2.0);
			if(aim && enableADS(stack))
				rotateModelForADSRendering();
			
			renderParts(hasRedDot(stack));
		}
		GlStateManager.popMatrix();
		
		renderRedDot(-0.2, -12.3, -1.1, 1.3f, stack);
	}
	
	private void renderParts(boolean redDotAttached)
	{
		base.render(1f);
		mag.render(1f);
		sh1.render(1f);
		trigger.render(1f);
		handle.render(1f);
		if(!redDotAttached) irns.render(1f);
	}
}

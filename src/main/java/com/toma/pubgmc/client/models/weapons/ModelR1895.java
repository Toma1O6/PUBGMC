package com.toma.pubgmc.client.models.weapons;

import com.toma.pubgmc.animation.HeldAnimation;
import com.toma.pubgmc.animation.HeldAnimation.HeldStyle;
import com.toma.pubgmc.animation.ReloadAnimation;
import com.toma.pubgmc.animation.ReloadAnimation.ReloadStyle;
import com.toma.pubgmc.client.models.ModelGun;
import com.toma.pubgmc.common.capability.IPlayerData;
import com.toma.pubgmc.common.capability.IPlayerData.PlayerDataProvider;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.item.ItemStack;

public class ModelR1895 extends ModelGun
{
	private final ModelRenderer base;
	private final ModelRenderer mag;
	private final ModelRenderer trigger;
	private final ModelRenderer handle;

	public ModelR1895()
	{
		super();
		
		textureWidth = 128;
		textureHeight = 128;

		base = new ModelRenderer(this);
		base.setRotationPoint(0.0F, 24.0F, 0.0F);
		base.cubeList.add(new ModelBox(base, 0, 0, -2.0F, -25.0F, -37.0F, 4, 4, 28, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -1.0F, -20.0F, -21.0F, 2, 2, 12, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -1.5F, -20.5F, -23.0F, 3, 3, 2, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -0.5F, -26.0F, -36.0F, 1, 2, 3, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -0.5F, -26.5F, -35.5F, 1, 1, 2, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -2.0F, -25.0F, -9.0F, 4, 8, 5, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -2.0F, -17.0F, -6.0F, 4, 2, 2, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -2.0F, -26.0F, -4.0F, 4, 2, 7, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -2.0F, -17.0F, -4.0F, 4, 2, 7, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -2.0F, -26.0F, 3.0F, 4, 13, 5, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -2.0F, -13.0F, 3.0F, 4, 5, 13, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -1.5F, -24.0F, 9.0F, 3, 11, 2, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -1.5F, -25.0F, 8.0F, 3, 4, 2, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -1.5F, -16.0F, 8.0F, 3, 2, 2, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -2.0F, -15.0F, 1.0F, 4, 2, 2, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -1.5F, -15.0F, -5.0F, 3, 4, 1, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -1.5F, -11.0F, -4.0F, 3, 1, 7, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 64, -2.0F, -8.0F, 7.0F, 4, 2, 9, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -1.25F, -26.5F, 5.0F, 1, 2, 2, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, 0.25F, -26.5F, 5.0F, 1, 2, 2, 0.0F, false));

		mag = new ModelRenderer(this);
		mag.setRotationPoint(0.0F, 24.0F, 0.0F);
		mag.cubeList.add(new ModelBox(mag, 0, 0, -3.0F, -22.5F, -3.0F, 6, 4, 6, 0.0F, false));
		mag.cubeList.add(new ModelBox(mag, 0, 0, -2.0F, -23.5F, -3.0F, 4, 6, 6, 0.0F, false));

		trigger = new ModelRenderer(this);
		trigger.setRotationPoint(0.0F, 24.0F, 0.0F);
		setRotationAngle(trigger, -0.1745F, 0.0F, 0.0F);
		trigger.cubeList.add(new ModelBox(trigger, 0, 0, 0.0F, -15.0F, -4.0F, 1, 3, 1, 0.0F, false));

		handle = new ModelRenderer(this);
		handle.setRotationPoint(0.0F, 24.0F, 0.0F);
		setRotationAngle(handle, 0.1745F, 0.0F, 0.0F);
		handle.cubeList.add(new ModelBox(handle, 0, 64, -2.0F, -5.0F, 9.0F, 4, 17, 8, 0.0F, false));
	}
	
	@Override
	public void initAnimations()
	{
		initAimAnimation(-0.598f, 0.25f, 0.4f);
		initAimingAnimationStates(0.25f, 0f, 0f);
		heldAnimation = new HeldAnimation(HeldStyle.SMALL);
		reloadAnimation = new ReloadAnimation(null, ReloadStyle.REVOLVER);
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
				renderR1895(data.isAiming(), stack);
			}
			GlStateManager.popMatrix();
		}
	}
	
	private void renderR1895(boolean aim, ItemStack stack)
	{
		GlStateManager.pushMatrix();
		{
			GlStateManager.scale(0.7f, 0.7f, 0.7f);
			transform.defaultPistolTransform();
			GlStateManager.translate(-9.0, -1.0, 0.0);
			
			if(aim && enableADS(stack))
				rotateModelForADSRendering();
			
			renderParts();
		}
		GlStateManager.popMatrix();
		
		renderPistolSilencer(0.5, -1.5, -5.5, 1.1f, stack);
	}
	
	private void renderParts()
	{
		base.render(1f);
		mag.render(1f);
		trigger.render(1f);
		handle.render(1f);
	}
}

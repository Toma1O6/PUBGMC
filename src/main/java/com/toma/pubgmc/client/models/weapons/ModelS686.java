package com.toma.pubgmc.client.models.weapons;

import com.toma.pubgmc.animation.AimAnimation;
import com.toma.pubgmc.animation.SimpleReloadAnimation;
import com.toma.pubgmc.animation.SimpleReloadAnimation.ReloadStyle;
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
	private final ModelRenderer barrel;
	private final ModelRenderer base;
	private final ModelRenderer stock1;
	private final ModelRenderer stock2;
	private final ModelRenderer trigger;

	public ModelS686()
	{
		animation_aim = new AimAnimation(-0.525d, 0.23d, 0.43d).setInvertedCoords(true, false, false);
		animation_reload = new SimpleReloadAnimation(ReloadStyle.SHOTGUN);
		
		
		textureWidth = 128;
		textureHeight = 128;

		barrel = new ModelRenderer(this);
		barrel.setRotationPoint(0.0F, 24.0F, 0.0F);
		barrel.cubeList.add(new ModelBox(barrel, 0, 0, -2.0F, -16.0F, -59.0F, 4, 4, 54, 0.0F, false));
		barrel.cubeList.add(new ModelBox(barrel, 0, 0, -2.0F, -20.5F, -59.0F, 4, 4, 54, 0.0F, false));
		barrel.cubeList.add(new ModelBox(barrel, 0, 64, -2.5F, -20.0F, -24.0F, 5, 9, 15, 0.0F, false));
		barrel.cubeList.add(new ModelBox(barrel, 0, 0, -0.5F, -21.0F, -57.0F, 1, 1, 1, 0.0F, false));

		base = new ModelRenderer(this);
		base.setRotationPoint(0.0F, 24.0F, 0.0F);
		base.cubeList.add(new ModelBox(base, 0, 0, -2.5F, -20.0F, -9.0F, 5, 8, 8, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -1.5F, -12.0F, -8.0F, 3, 2, 1, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -1.5F, -10.0F, -7.0F, 3, 1, 1, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -1.5F, -9.0F, -6.0F, 3, 1, 3, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -1.5F, -12.0F, -3.0F, 3, 3, 1, 0.0F, false));

		stock1 = new ModelRenderer(this);
		stock1.setRotationPoint(0.0F, 24.0F, 0.0F);
		setRotationAngle(stock1, -0.1745F, 0.0F, 0.0F);
		stock1.cubeList.add(new ModelBox(stock1, 0, 64, -2.0F, -18.5F, -6.0F, 4, 6, 23, 0.0F, false));
		stock1.cubeList.add(new ModelBox(stock1, 0, 64, -2.0F, -18.5F, 17.0F, 4, 6, 19, 0.0F, false));
		stock1.cubeList.add(new ModelBox(stock1, 0, 64, -2.0F, -12.5F, 17.0F, 4, 10, 19, 0.0F, false));

		stock2 = new ModelRenderer(this);
		stock2.setRotationPoint(0.0F, 24.0F, 0.0F);
		setRotationAngle(stock2, -0.6981F, 0.0F, 0.0F);
		stock2.cubeList.add(new ModelBox(stock2, 0, 65, -2.0F, -16.65F, -7.6F, 4, 6, 21, 0.0F, false));
		stock2.cubeList.add(new ModelBox(stock2, 0, 65, -2.0F, -19.65F, 0.4F, 4, 6, 10, 0.0F, false));

		trigger = new ModelRenderer(this);
		trigger.setRotationPoint(0.0F, 24.0F, 0.0F);
		setRotationAngle(trigger, -0.1745F, 0.0F, 0.0F);
		trigger.cubeList.add(new ModelBox(trigger, 0, 0, -1.0F, -11.5F, -7.0F, 2, 2, 1, 0.0F, false));
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
				handleAnimations(data.isAiming(), player.isSprinting(), data.isReloading(), stack);
				renderS686(data.isAiming(), stack);
			}
			GlStateManager.popMatrix();
		}
	}
	
	private void handleAnimations(boolean aim, boolean sprint, boolean reload, ItemStack stack)
	{
		if(enableADS(stack))
		{
			animation_aim.run(aim);
		}
		animation_held.run(sprint);
		animation_reload.run(reload);
	}
	
	private void renderS686(boolean aim, ItemStack stack)
	{
		GlStateManager.pushMatrix();
		{
			transform.defaultShotgunTransform();
			GlStateManager.translate(0.0, 1.7, 3.3);
			if(aim && enableADS(stack)) rotateModelForADSRendering();
			renderParts();
		}
		GlStateManager.popMatrix();
	}
	
	private void renderParts()
	{
		barrel.render(1f);
		base.render(1f);
		stock1.render(1f);
		stock2.render(1f);
		trigger.render(1f);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z)
	{
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}

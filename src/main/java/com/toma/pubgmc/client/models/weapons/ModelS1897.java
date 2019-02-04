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

public class ModelS1897 extends ModelGun
{
	private final ModelRenderer barrel;
	private final ModelRenderer stock1;
	private final ModelRenderer stock2;
	private final ModelRenderer trigger;

	public ModelS1897()
	{
		animation_aim = new AimAnimation(-0.525d, 0.31d, 0.315d, 1.2f);
		animation_aim.setInvertedCoords(true, false, false);
		animation_aim.setMovementMultiplier(1f, 1.2f, 1.3f);
		animation_reload = new SimpleReloadAnimation(ReloadStyle.SHOTGUN);
		
		textureWidth = 128;
		textureHeight = 128;

		barrel = new ModelRenderer(this);
		barrel.setRotationPoint(0.0F, 24.0F, 0.0F);
		barrel.cubeList.add(new ModelBox(barrel, 0, 0, -2.0F, -18.0F, -61.0F, 4, 4, 54, 0.0F, false));
		barrel.cubeList.add(new ModelBox(barrel, 0, 0, -1.0F, -13.5F, -49.0F, 2, 2, 35, 0.0F, false));
		barrel.cubeList.add(new ModelBox(barrel, 0, 0, -1.5F, -14.0F, -51.0F, 3, 3, 2, 0.0F, false));
		barrel.cubeList.add(new ModelBox(barrel, 0, 0, -1.5F, -17.5F, -61.5F, 3, 3, 1, 0.0F, false));
		barrel.cubeList.add(new ModelBox(barrel, 0, 64, -1.5F, -14.5F, -41.0F, 3, 4, 17, 0.0F, false));
		barrel.cubeList.add(new ModelBox(barrel, 0, 0, -2.5F, -18.2F, -14.0F, 5, 8, 12, 0.0F, false));
		barrel.cubeList.add(new ModelBox(barrel, 0, 0, -1.0F, -10.0F, -1.0F, 2, 2, 1, 0.0F, false));
		barrel.cubeList.add(new ModelBox(barrel, 0, 0, -1.0F, -8.0F, -5.0F, 2, 1, 4, 0.0F, false));
		barrel.cubeList.add(new ModelBox(barrel, 0, 0, -2.0F, -11.0F, -2.0F, 4, 1, 1, 0.0F, false));
		barrel.cubeList.add(new ModelBox(barrel, 0, 0, -1.0F, -11.0F, -6.0F, 2, 3, 1, 0.0F, false));
		barrel.cubeList.add(new ModelBox(barrel, 0, 0, -0.5F, -18.5F, -60.0F, 1, 1, 1, 0.0F, false));

		stock1 = new ModelRenderer(this);
		stock1.setRotationPoint(0.0F, 24.0F, 0.0F);
		setRotationAngle(stock1, -0.3491F, 0.0F, 0.0F);
		stock1.cubeList.add(new ModelBox(stock1, 0, 0, -2.0F, -16.0F, -8.0F, 4, 7, 7, 0.0F, false));
		stock1.cubeList.add(new ModelBox(stock1, 0, 64, -2.0F, -16.0F, -1.0F, 4, 7, 23, 0.0F, false));

		stock2 = new ModelRenderer(this);
		stock2.setRotationPoint(0.0F, 24.0F, 0.0F);
		setRotationAngle(stock2, -0.0873F, 0.0F, 0.0F);
		stock2.cubeList.add(new ModelBox(stock2, 0, 64, -2.0F, -15.0F, 7.0F, 4, 7, 28, 0.0F, false));
		stock2.cubeList.add(new ModelBox(stock2, 0, 64, -2.0F, -8.0F, 23.0F, 4, 5, 12, 0.0F, false));

		trigger = new ModelRenderer(this);
		trigger.setRotationPoint(0.0F, 24.0F, 0.0F);
		setRotationAngle(trigger, -0.1745F, 0.0F, 0.0F);
		trigger.cubeList.add(new ModelBox(trigger, 0, 0, -1.0F, -11.3F, -5.0F, 2, 3, 1, 0.0F, false));
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
				renderS1897(data.isAiming(),stack);
			}
			GlStateManager.popMatrix();
		}
	}
	
	private void handleAnimations(boolean aim, boolean sprint, boolean reload, ItemStack stack)
	{
		if(enableADS(stack))
		{
			if(animation_aim.getFinalY() != 0.31d) animation_aim.setYModifier(0.31d);
			animation_aim.run(aim);
		}
		animation_held.run(sprint);
		animation_reload.run(reload);
	}
	
	private void renderS1897(boolean aim, ItemStack stack)
	{
		GlStateManager.pushMatrix();
		{
			transform.defaultShotgunTransform();
			GlStateManager.translate(0.0, 3.6, 7.3);
			if(aim && enableADS(stack)) rotateModelForADSRendering();
			renderParts();
		}
		GlStateManager.popMatrix();
	}
	
	private void renderParts()
	{
		barrel.render(1f);
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

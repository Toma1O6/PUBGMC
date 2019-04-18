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

public class ModelSawedOff extends ModelGun
{
	private final ModelRenderer barrel;
	private final ModelRenderer base;
	private final ModelRenderer stock_shape;
	private final ModelRenderer stock_shape2;
	private final ModelRenderer stock;
	private final ModelRenderer stock2;
	private final ModelRenderer trigger;

	public ModelSawedOff()
	{
		super();
		
		textureWidth = 128;
		textureHeight = 128;

		barrel = new ModelRenderer(this);
		barrel.setRotationPoint(0.0F, 24.0F, 0.0F);
		barrel.cubeList.add(new ModelBox(barrel, 0, 0, -3.2F, -14.0F, -25.0F, 3, 3, 19, 0.0F, false));
		barrel.cubeList.add(new ModelBox(barrel, 0, 0, 0.2F, -14.0F, -25.0F, 3, 3, 19, 0.0F, false));
		barrel.cubeList.add(new ModelBox(barrel, 0, 0, -0.5F, -13.0F, -22.01F, 1, 2, 18, 0.0F, false));
		barrel.cubeList.add(new ModelBox(barrel, 0, 0, -3.2F, -14.0F, -6.0F, 3, 1, 2, 0.0F, false));
		barrel.cubeList.add(new ModelBox(barrel, 0, 0, -3.2F, -12.0F, -6.01F, 3, 1, 2, 0.0F, false));
		barrel.cubeList.add(new ModelBox(barrel, 0, 0, 0.2F, -14.0F, -6.0F, 3, 1, 2, 0.0F, false));
		barrel.cubeList.add(new ModelBox(barrel, 0, 0, 0.2F, -12.0F, -6.01F, 3, 1, 2, 0.0F, false));
		barrel.cubeList.add(new ModelBox(barrel, 0, 0, 2.2F, -13.0F, -6.0F, 1, 1, 2, 0.0F, false));
		barrel.cubeList.add(new ModelBox(barrel, 0, 0, 0.2F, -13.0F, -6.0F, 1, 1, 2, 0.0F, false));
		barrel.cubeList.add(new ModelBox(barrel, 0, 0, -3.2F, -13.0F, -6.0F, 1, 1, 2, 0.0F, false));
		barrel.cubeList.add(new ModelBox(barrel, 0, 0, -1.2F, -13.0F, -6.0F, 1, 1, 2, 0.0F, false));
		barrel.cubeList.add(new ModelBox(barrel, 0, 64, -3.5F, -11.95F, -21.0F, 7, 2, 17, 0.0F, false));

		base = new ModelRenderer(this);
		base.setRotationPoint(0.0F, 24.0F, 0.0F);
		base.cubeList.add(new ModelBox(base, 0, 0, -3.5F, -14.0F, -4.0F, 7, 4, 3, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -2.5F, -13.5F, 1.5F, 5, 3, 2, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -1.0F, -10.5F, 0.0F, 2, 2, 1, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -1.0F, -8.5F, 1.0F, 2, 1, 3, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -1.0F, -11.5F, 4.0F, 2, 3, 1, 0.0F, false));

		stock_shape = new ModelRenderer(this);
		stock_shape.setRotationPoint(0.0F, 24.0F, 0.0F);
		setRotationAngle(stock_shape, 0.0F, 0.2618F, 0.0F);
		stock_shape.cubeList.add(new ModelBox(stock_shape, 0, 0, -3.08F, -13.5F, -2.0F, 3, 3, 4, 0.0F, false));

		stock_shape2 = new ModelRenderer(this);
		stock_shape2.setRotationPoint(0.0F, 24.0F, 0.0F);
		setRotationAngle(stock_shape2, 0.0F, -0.2618F, 0.0F);
		stock_shape2.cubeList.add(new ModelBox(stock_shape2, 0, 0, -0.9F, -13.5F, -2.0F, 4, 3, 4, 0.0F, false));

		stock = new ModelRenderer(this);
		stock.setRotationPoint(0.0F, 24.0F, 0.0F);
		setRotationAngle(stock, -0.1745F, 0.0F, 0.0F);
		stock.cubeList.add(new ModelBox(stock, 0, 64, -2.5F, -13.7F, 1.0F, 5, 3, 7, 0.0F, false));

		stock2 = new ModelRenderer(this);
		stock2.setRotationPoint(0.0F, 24.0F, 0.0F);
		setRotationAngle(stock2, -0.6109F, 0.0F, 0.0F);
		stock2.cubeList.add(new ModelBox(stock2, 0, 64, -2.5F, -15.78F, 1.4F, 5, 3, 7, 0.0F, false));

		trigger = new ModelRenderer(this);
		trigger.setRotationPoint(0.0F, 24.0F, 0.0F);
		setRotationAngle(trigger, -0.1745F, 0.0F, 0.0F);
		trigger.cubeList.add(new ModelBox(trigger, 0, 0, -1.0F, -11.5F, 0.5F, 2, 2, 1, 0.0F, false));
	}
	
	@Override
	public void initAnimations() 
	{
		initAimAnimation(-0.56f, 0.295f, 0.265f);
		initAimingAnimationStates(0.295f);
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
			renderSawedOff(data.isAiming(), stack);
			GlStateManager.popMatrix();
		}
	}
	
	private void renderSawedOff(boolean aim, ItemStack stack)
	{
		GlStateManager.pushMatrix();
		{
			transform.defaultPistolTransform();
			if(aim) rotateModelForADSRendering();
			renderParts();
		}
		GlStateManager.popMatrix();
	}
	
	private void renderParts()
	{
		barrel.render(1f);
		base.render(1f);
		stock_shape.render(1f);
		stock_shape2.render(1f);
		stock.render(1f);
		stock2.render(1f);
	}
}

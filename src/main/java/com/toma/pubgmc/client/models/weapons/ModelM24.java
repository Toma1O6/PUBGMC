package com.toma.pubgmc.client.models.weapons;

import com.toma.pubgmc.client.models.ModelGun;
import com.toma.pubgmc.common.capability.IPlayerData.PlayerDataProvider;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.item.ItemStack;

public class ModelM24 extends ModelGun
{
	private final ModelRenderer base;
	private final ModelRenderer stock;
	private final ModelRenderer stock2;
	private final ModelRenderer trigger;
	private final ModelRenderer rail;

	public ModelM24()
	{
		textureWidth = 128;
		textureHeight = 128;

		base = new ModelRenderer(this);
		base.setRotationPoint(0.0F, 24.0F, 0.0F);
		base.cubeList.add(new ModelBox(base, 0, 0, -3.0F, -23.0F, -59.0F, 6, 8, 56, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 66, -2.0F, -24.0F, -74.0F, 4, 4, 37, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 66, -2.0F, -24.0F, -37.0F, 4, 1, 40, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 66, -2.0F, -24.0F, -97.0F, 4, 4, 23, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -3.0F, -23.0F, -3.0F, 6, 8, 8, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 64, -2.0F, -13.0F, 60.0F, 4, 19, 2, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -3.0F, -19.0F, -5.0F, 6, 6, 17, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -2.0F, -13.0F, 57.0F, 4, 19, 3, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 64, -2.0F, 3.0F, 62.0F, 4, 2, 5, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 64, -2.0F, -12.0F, 62.0F, 4, 2, 5, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 64, -2.0F, -4.0F, 62.0F, 4, 2, 5, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 64, -2.0F, -13.0F, 66.0F, 4, 20, 3, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 66, -0.5F, -25.5F, -93.0F, 1, 2, 2, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -2.0F, -13.0F, -6.0F, 4, 1, 1, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -2.0F, -12.0F, -10.0F, 4, 1, 4, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -2.0F, -13.0F, -11.0F, 4, 1, 1, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -2.0F, -15.0F, -12.0F, 4, 2, 1, 0.0F, false));

		stock = new ModelRenderer(this);
		stock.setRotationPoint(0.0F, 24.0F, 0.0F);
		setRotationAngle(stock, -0.0873F, 0.0F, 0.0F);
		stock.cubeList.add(new ModelBox(stock, 0, 0, -2.0F, -18.0F, 10.0F, 4, 4, 48, 0.0F, false));
		stock.cubeList.add(new ModelBox(stock, 0, 0, -2.0F, -14.0F, 18.0F, 4, 2, 41, 0.0F, false));
		stock.cubeList.add(new ModelBox(stock, 0, 0, -2.0F, -12.1F, 28.0F, 4, 3, 31, 0.0F, false));
		stock.cubeList.add(new ModelBox(stock, 0, 0, -2.0F, -9.1F, 35.0F, 4, 2, 24, 0.0F, false));
		stock.cubeList.add(new ModelBox(stock, 0, 0, -2.0F, -13.0F, 22.0F, 4, 2, 8, 0.0F, false));
		stock.cubeList.add(new ModelBox(stock, 0, 0, -2.0F, -15.0F, 16.0F, 4, 2, 8, 0.0F, false));

		stock2 = new ModelRenderer(this);
		stock2.setRotationPoint(0.0F, 24.0F, 0.0F);
		setRotationAngle(stock2, -0.3491F, 0.0F, 0.0F);
		stock2.cubeList.add(new ModelBox(stock2, 0, 0, -3.0F, -23.0F, -3.3F, 6, 4, 8, 0.0F, false));
		stock2.cubeList.add(new ModelBox(stock2, 0, 0, -2.0F, -18.0F, 3.0F, 4, 3, 54, 0.0F, false));
		stock2.cubeList.add(new ModelBox(stock2, 0, 0, -2.0F, -23.0F, 39.0F, 4, 5, 16, 0.0F, false));

		trigger = new ModelRenderer(this);
		trigger.setRotationPoint(0.0F, 24.0F, 0.0F);
		setRotationAngle(trigger, -0.1745F, 0.0F, 0.0F);
		trigger.cubeList.add(new ModelBox(trigger, 0, 64, -1.0F, -14.0F, -10.0F, 2, 2, 1, 0.0F, false));

		rail = new ModelRenderer(this);
		rail.setRotationPoint(0.0F, 24.0F, 0.0F);
		rail.cubeList.add(new ModelBox(rail, 0, 64, -2.0F, -24.5F, -25.0F, 4, 1, 27, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 64, -2.0F, -25.0F, 1.0F, 4, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 64, -2.0F, -25.0F, -1.0F, 4, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 64, -2.0F, -25.0F, -3.0F, 4, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 64, -2.0F, -25.0F, -5.0F, 4, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 64, -2.0F, -25.0F, -7.0F, 4, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 64, -2.0F, -25.0F, -9.0F, 4, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 64, -2.0F, -25.0F, -11.0F, 4, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 64, -2.0F, -25.0F, -13.0F, 4, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 64, -2.0F, -25.0F, -15.0F, 4, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 64, -2.0F, -25.0F, -17.0F, 4, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 64, -2.0F, -25.0F, -19.0F, 4, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 64, -2.0F, -25.0F, -21.0F, 4, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 64, -2.0F, -25.0F, -23.0F, 4, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 64, -2.0F, -25.0F, -25.0F, 4, 1, 1, 0.0F, false));
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
			boolean aim = player.getCapability(PlayerDataProvider.PLAYER_DATA, null).isAiming();
			renderM24(aim, stack);
		}
	}
	
	private void renderM24(boolean aim, ItemStack stack)
	{
		GlStateManager.pushMatrix();
		{
			transform.defaultSRTransform();
			GlStateManager.translate(0.0, 2.0, 3.0);
			
			if(aim && enableADS(stack))
			{
				rotateModelForADSRendering();
				GlStateManager.translate(26.7, -12.5, 13.0);
				
				if(hasRedDot(stack)) GlStateManager.translate(0, 3, 0);
				if(hasHoloSight(stack)) GlStateManager.translate(0, 4.75, 0);
			}
			
			renderParts();
		}
		GlStateManager.popMatrix();
		
		if(hasSilencer(stack)) renderSilencer(aim, stack);
		if(hasRedDot(stack)) renderRedDot(aim, stack);
		else if(hasHoloSight(stack)) renderHolo(aim, stack);
		else if(has2X(stack)) render2x(stack);
		else if(has4X(stack)) render4x(stack);
		else if(has8X(stack)) render8x(stack);
		else if(has15X(stack)) render15x(stack);
	}
	
	private void renderRedDot(boolean aim, ItemStack stack)
	{
		if(aim) {
			renderRedDot(36.8, -16.4, 20, 1f, stack);
		} else renderRedDot(-0.5, -2.8, 4, 1f, stack);
	}
	
	private void renderHolo(boolean aim, ItemStack stack)
	{
		if(aim) {
			renderHolo(29.15, -12.5, 14, 1f, stack);
		} else renderHolo(-2.1, -3.2, -1, 1f, stack);
	}
	
	private void render2x(ItemStack stack)
	{
		renderScope2X(7.7, 10, -7, 1f, stack);
	}
	
	private void render4x(ItemStack stack)
	{
		renderScope4X(7.7, 10, -7, 1f, stack);
	}
	
	private void render8x(ItemStack stack)
	{
		renderScope8X(0, -2, -2, 1f, stack);
	}
	
	private void render15x(ItemStack stack)
	{
		renderScope15X(0, -1, -2, 1f, stack);
	}
	
	private void renderSilencer(boolean aim, ItemStack stack)
	{
		if(aim && enableADS(stack)) 
		{
			if(hasRedDot(stack))
			{
				renderSniperSilencer(-16, 6.3, -14, 1f, stack);
			}
			
			else if(hasHoloSight(stack))
			{
				renderSniperSilencer(-16, 5.2, -14, 1f, stack);
			}
			
			else renderSniperSilencer(-16, 7.8, -14, 1f, stack);
		}
		else renderSniperSilencer(0, 1.1, -21, 1f, stack);
	}
	
	private void renderParts()
	{
		base.render(1f);
		stock.render(1f);
		stock2.render(1f);
		trigger.render(1f);
		rail.render(1f);
	}
}

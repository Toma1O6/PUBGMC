package com.toma.pubgmc.client.models.weapons;

import com.toma.pubgmc.client.models.ModelGun;
import com.toma.pubgmc.common.capability.IPlayerData.PlayerDataProvider;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.item.ItemStack;

public class ModelAWM extends ModelGun
{
	private final ModelRenderer base;
	private final ModelRenderer mag;
	private final ModelRenderer trigger;

	public ModelAWM()
	{
		textureWidth = 128;
		textureHeight = 128;

		base = new ModelRenderer(this);
		base.setRotationPoint(0.0F, 24.0F, 0.0F);
		base.cubeList.add(new ModelBox(base, 0, 0, -3.0F, -23.0F, -45.0F, 6, 6, 48, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 65, -2.0F, -24.0F, -34.0F, 4, 1, 36, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 65, -2.0F, -24.0F, -71.0F, 4, 4, 37, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 65, -2.5F, -24.5F, -81.0F, 5, 5, 10, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -3.0F, -17.0F, -22.0F, 6, 2, 16, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -3.0F, -15.0F, -6.0F, 6, 1, 1, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -3.0F, -14.0F, -5.0F, 6, 1, 5, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -3.0F, -17.0F, 0.0F, 6, 5, 3, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -3.0F, -17.0F, 3.0F, 6, 6, 4, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -3.0F, -21.0F, 3.0F, 6, 4, 4, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -3.0F, -21.0F, 7.0F, 6, 11, 19, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 64, -3.0F, -21.5F, 26.0F, 6, 12, 2, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 64, -2.5F, -21.5F, 6.0F, 5, 1, 19, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 65, -2.0F, -25.0F, 1.0F, 4, 1, 1, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 65, -2.0F, -25.0F, -1.0F, 4, 1, 1, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 65, -2.0F, -25.0F, -3.0F, 4, 1, 1, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 65, -2.5F, -24.5F, -41.0F, 5, 2, 43, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 65, -2.0F, -25.0F, -5.0F, 4, 1, 1, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 65, -2.0F, -25.0F, -7.0F, 4, 1, 1, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 65, -2.0F, -25.0F, -9.0F, 4, 1, 1, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 65, -2.0F, -25.0F, -11.0F, 4, 1, 1, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 65, -2.0F, -25.0F, -13.0F, 4, 1, 1, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 65, -2.0F, -25.0F, -15.0F, 4, 1, 1, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 65, -2.0F, -25.0F, -17.0F, 4, 1, 1, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 65, -2.0F, -25.0F, -19.0F, 4, 1, 1, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 65, -2.0F, -25.0F, -21.0F, 4, 1, 1, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 65, -2.0F, -25.0F, -23.0F, 4, 1, 1, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 65, -2.0F, -25.0F, -25.0F, 4, 1, 1, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 65, -2.0F, -25.0F, -27.0F, 4, 1, 1, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 65, -2.0F, -25.0F, -29.0F, 4, 1, 1, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 65, -2.0F, -25.0F, -31.0F, 4, 1, 1, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 65, -2.0F, -25.0F, -33.0F, 4, 1, 1, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 65, -2.0F, -25.0F, -35.0F, 4, 1, 1, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 65, -2.0F, -25.0F, -37.0F, 4, 1, 1, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 65, -2.0F, -25.0F, -39.0F, 4, 1, 1, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 65, -2.0F, -25.0F, -41.0F, 4, 1, 1, 0.0F, false));

		mag = new ModelRenderer(this);
		mag.setRotationPoint(0.0F, 24.0F, 0.0F);
		mag.cubeList.add(new ModelBox(mag, 0, 64, -2.0F, -15.0F, -21.0F, 4, 6, 14, 0.0F, false));
		mag.cubeList.add(new ModelBox(mag, 0, 64, -2.5F, -16.0F, -10.0F, 5, 7, 3, 0.0F, false));
		mag.cubeList.add(new ModelBox(mag, 0, 64, -2.5F, -16.0F, -21.0F, 5, 7, 3, 0.0F, false));
		mag.cubeList.add(new ModelBox(mag, 0, 64, -2.5F, -16.0F, -17.0F, 5, 7, 6, 0.0F, false));

		trigger = new ModelRenderer(this);
		trigger.setRotationPoint(0.0F, 24.0F, 0.0F);
		setRotationAngle(trigger, -0.1745F, 0.0F, 0.0F);
		trigger.cubeList.add(new ModelBox(trigger, 0, 64, -1.0F, -17.5F, -5.5F, 2, 3, 1, 0.0F, false));
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
			
			renderAWM(aim, stack);
		}
	}
	
	private void renderAWM(boolean aim, ItemStack stack)
	{
		GlStateManager.pushMatrix();
		transform.defaultSRTransform();
		GlStateManager.translate(0.0, 2.0, 6.0);
		
		if(aim && enableADS(stack))
		{
			rotateModelForADSRendering();
			GlStateManager.translate(26.65, -12.6, 12.0);
			
			if(hasRedDot(stack)) GlStateManager.translate(0, 3, 0);
			else if(hasHoloSight(stack)) GlStateManager.translate(0, 4.75, 0);
		}
		
		renderParts();
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
				renderSniperSilencer(-16, 6.3, 1, 1f, stack);
			}
			else if(hasHoloSight(stack))
			{
				renderSniperSilencer(-16, 5.5, 1, 1f, stack);
			}
			else renderSniperSilencer(-16, 8.4, 1, 1f, stack);
		}
		else renderSniperSilencer(0, 1.2, -5, 1f, stack);
	}
	
	private void renderParts()
	{
		base.render(1f);
		mag.render(1f);
		trigger.render(1f);
	}
}

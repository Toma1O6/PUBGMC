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

public class ModelAUG extends ModelGun
{
	private final ModelRenderer base;
	private final ModelRenderer rail;
	private final ModelRenderer handle;
	private final ModelRenderer trigger;
	private final ModelRenderer mag;
	private final ModelRenderer stock;
	private final ModelRenderer irns;

	public ModelAUG()
	{
		animation_aim = new AimAnimation(-0.56d, 0.2825d, 0.215d, 1f).setInvertedCoords(true, false, false);
		
		textureWidth = 128;
		textureHeight = 128;

		base = new ModelRenderer(this);
		base.setRotationPoint(0.0F, 24.0F, 0.0F);
		base.cubeList.add(new ModelBox(base, 0, 0, -3.0F, -25.0F, -43.0F, 6, 4, 44, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 96, -3.0F, -24.0F, -57.0F, 6, 3, 14, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 96, -1.0F, -24.0F, -71.0F, 2, 2, 14, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 96, -1.5F, -24.5F, -79.0F, 3, 3, 8, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -3.0F, -21.5F, -27.0F, 6, 4, 2, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -3.0F, -27.0F, -38.0F, 6, 2, 30, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -3.0F, -26.0F, -49.0F, 6, 2, 11, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -3.0F, -12.0F, -33.0F, 6, 2, 12, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -3.0F, -21.5F, -24.0F, 6, 2, 25, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 64, -2.0F, -19.0F, -14.0F, 4, 6, 8, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -3.0F, -19.5F, -15.0F, 6, 1, 11, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -3.0F, -25.0F, 1.0F, 6, 4, 24, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 96, -3.0F, -25.0F, 26.0F, 6, 15, 1, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -3.0F, -25.0F, 23.0F, 6, 15, 3, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -3.0F, -22.0F, 6.0F, 6, 6, 19, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 96, -0.5F, -28.0F, -55.0F, 1, 4, 1, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 96, -1.5F, -29.0F, -55.0F, 1, 2, 1, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 96, 0.5F, -29.0F, -55.0F, 1, 2, 1, 0.0F, false));

		rail = new ModelRenderer(this);
		rail.setRotationPoint(0.0F, 24.0F, 0.0F);
		rail.cubeList.add(new ModelBox(rail, 0, 97, -2.5F, -27.5F, -37.5F, 5, 1, 29, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 97, -2.5F, -28.0F, -36.5F, 5, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 97, -2.5F, -28.0F, -34.5F, 5, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 97, -2.5F, -28.0F, -32.5F, 5, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 97, -2.5F, -28.0F, -30.5F, 5, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 97, -2.5F, -28.0F, -28.5F, 5, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 97, -2.5F, -28.0F, -26.5F, 5, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 97, -2.5F, -28.0F, -24.5F, 5, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 97, -2.5F, -28.0F, -22.5F, 5, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 97, -2.5F, -28.0F, -20.5F, 5, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 97, -2.5F, -28.0F, -18.5F, 5, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 97, -2.5F, -28.0F, -16.5F, 5, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 97, -2.5F, -28.0F, -14.5F, 5, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 97, -2.5F, -28.0F, -12.5F, 5, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 97, -2.5F, -28.0F, -10.5F, 5, 1, 1, 0.0F, false));

		handle = new ModelRenderer(this);
		handle.setRotationPoint(0.0F, 24.0F, 0.0F);
		setRotationAngle(handle, 0.1745F, 0.0F, 0.0F);
		handle.cubeList.add(new ModelBox(handle, 0, 0, -3.0F, -27.0F, -31.0F, 6, 11, 2, 0.0F, false));
		handle.cubeList.add(new ModelBox(handle, 0, 0, -3.0F, -26.5F, -22.0F, 6, 12, 3, 0.0F, false));

		trigger = new ModelRenderer(this);
		trigger.setRotationPoint(0.0F, 24.0F, 0.0F);
		setRotationAngle(trigger, -0.0873F, 0.0F, 0.0F);
		trigger.cubeList.add(new ModelBox(trigger, 0, 96, -1.0F, -20.0F, -31.0F, 2, 4, 1, 0.0F, false));

		mag = new ModelRenderer(this);
		mag.setRotationPoint(0.0F, 24.0F, 0.0F);
		setRotationAngle(mag, -0.1745F, 0.0F, 0.0F);
		mag.cubeList.add(new ModelBox(mag, 0, 64, -2.0F, -12.0F, -16.2F, 4, 10, 8, 0.0F, false));

		stock = new ModelRenderer(this);
		stock.setRotationPoint(0.0F, 24.0F, 0.0F);
		setRotationAngle(stock, -0.2618F, 0.0F, 0.0F);
		stock.cubeList.add(new ModelBox(stock, 0, 0, -3.0F, -22.0F, -14.0F, 6, 6, 34, 0.0F, false));

		irns = new ModelRenderer(this);
		irns.setRotationPoint(0.0F, 24.0F, 0.0F);
		irns.cubeList.add(new ModelBox(irns, 0, 96, -2.0F, -28.0F, -14.0F, 4, 1, 4, 0.0F, false));
		irns.cubeList.add(new ModelBox(irns, 0, 96, -2.0F, -29.5F, -14.0F, 1, 2, 4, 0.0F, false));
		irns.cubeList.add(new ModelBox(irns, 0, 96, 1.0F, -29.5F, -14.0F, 1, 2, 4, 0.0F, false));
		irns.cubeList.add(new ModelBox(irns, 0, 96, -0.5F, -28.5F, -11.5F, 1, 1, 1, 0.0F, false));
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
				handleAnimations(data.isAiming(), player.isSprinting(), data.isReloading(), stack);
				renderAUG(data.isAiming(), stack);
			}
			GlStateManager.popMatrix();
		}
	}
	
	private void handleAnimations(boolean aim, boolean sprint, boolean reload, ItemStack stack)
	{
		if(enableADS(stack))
		{
			if(!hasScopeAtachment(stack) && animation_aim.getFinalY() != 0.2825d)
				animation_aim.setYModifier(0.2825d);
			else if(hasRedDot(stack) && animation_aim.getFinalY() != 0.205d)
				animation_aim.setYModifier(0.205d);
			else if(hasHoloSight(stack) && animation_aim.getFinalY() != 0.16d)
				animation_aim.setYModifier(0.16d);
			animation_aim.run(aim);
		}
		animation_held.run(sprint);
		animation_reload.run(reload);
	}
	
	private void renderAUG(boolean aim, ItemStack stack)
	{
		GlStateManager.pushMatrix();
		transform.defaultARTransform();
		GlStateManager.translate(0.0, 6.0, 19.0);
		
		if(aim && enableADS(stack))
		{
			rotateModelForADSRendering();
		}
		
		renderParts(hasScopeAtachment(stack));
		GlStateManager.popMatrix();
		
		renderSilencer(stack);
		renderVerticalGrip(stack);
		renderAngledGrip(stack);
		renderRedDot(stack);
		renderHolo(stack);
		render2X(stack);
		render4X(stack);
	}
	
	private void renderSilencer(ItemStack stack)
	{
		renderARSilencer(0, -1.1, 4, 1f, stack);
	}
	
	private void renderVerticalGrip(ItemStack stack)
	{
		renderVerticalGrip(-1, 4, -2, 1f, stack);
	}
	
	private void renderAngledGrip(ItemStack stack)
	{
		renderAngledGrip(0.6, 2.9, -1, 1f, stack);
	}
	
	private void renderRedDot(ItemStack stack)
	{
		renderRedDot(-0.45, -6.5, 5, 1.3f, stack);
	}
	
	private void renderHolo(ItemStack stack)
	{
		renderHolo(-1.55, -7, 0, 1.3f, stack);
	}
	
	private void render2X(ItemStack stack)
	{
		renderScope2X(7.8, 10.3, -5, 1f, stack);
	}
	
	private void render4X(ItemStack stack)
	{
		renderScope4X(7.9, 10.4, -3, 1f, stack);
	}
	
	private void renderParts(boolean ir)
	{
		base.render(1f);
		rail.render(1f);
		handle.render(1f);
		trigger.render(1f);
		mag.render(1f);
		stock.render(1f);
		
		if(!ir)
		{
			irns.render(1f);
		}
	}
}

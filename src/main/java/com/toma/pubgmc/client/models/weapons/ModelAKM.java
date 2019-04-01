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

public class ModelAKM extends ModelGun
{
	private final ModelRenderer base;
	private final ModelRenderer mag;
	private final ModelRenderer trigger;
	private final ModelRenderer handle;
	private final ModelRenderer stock;
	private final ModelRenderer rail;
	private final ModelRenderer irns;
	
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
				renderAKM(data.isAiming(), stack);
			}
			GlStateManager.popMatrix();
		}
	}
	
	private void handleAnimations(boolean aim, boolean sprint, boolean reload, ItemStack stack)
	{
		if(enableADS(stack))
		{
			if(!hasScopeAtachment(stack) && animation_aim.getFinalY() != 0.28d)
				animation_aim.setYModifier(0.28d);
			else if(hasRedDot(stack) && animation_aim.getFinalY() != 0.205d)
				animation_aim.setYModifier(0.205d);
			else if(hasHoloSight(stack) && animation_aim.getFinalY() != 0.155d)
				animation_aim.setYModifier(0.155d);
			animation_aim.run(aim);
		}
		animation_reload.run(reload);
		animation_held.run(sprint);
	}
	
	private void renderAKM(boolean aim, ItemStack stack)
	{
		GlStateManager.pushMatrix();
		transform.defaultARTransform();
		GlStateManager.translate(0.0, 6.0, 5.0);
		
		if(aim && enableADS(stack))
		{
			rotateModelForADSRendering();
		}
		
		renderParts(hasScopeAtachment(stack));
		GlStateManager.popMatrix();
		
		renderSilencer(stack);
		renderRedDot(stack);
		renderHolo(stack);
		render2X(stack);
		render4X(stack);
	}
	
	private void renderSilencer(ItemStack stack)
	{
		renderARSilencer(0, -2.5, -1, 1f, stack);
	}
	
	private void renderRedDot(ItemStack stack)
	{
		renderRedDot(-0.45, -6.4, 2, 1.3f, stack);
	}
	
	private void renderHolo(ItemStack stack)
	{
		renderHolo(-1.5, -7, -2, 1.3f, stack);
	}
	
	private void render2X(ItemStack stack)
	{
		renderScope2X(8, 11, -8, 1f, stack);
	}
	
	private void render4X(ItemStack stack)
	{
		renderScope4X(8, 11, -10, 1f, stack);
	}
	
	public ModelAKM()
	{
		animation_aim = new AimAnimation(-0.56d, 0.28d, 0.31d).setInvertedCoords(true, false, false);
		
		textureWidth = 128;
		textureHeight = 128;

		base = new ModelRenderer(this);
		base.setRotationPoint(0.0F, 24.0F, 0.0F);
		base.cubeList.add(new ModelBox(base, 0, 0, -3.0F, -25.0F, -23.0F, 6, 6, 24, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 64, -3.0F, -22.0F, -38.0F, 6, 3, 15, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -3.0F, -25.0F, -29.0F, 6, 3, 6, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 64, -3.0F, -25.0F, -38.0F, 6, 3, 9, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -1.0F, -24.0F, -47.0F, 2, 1, 8, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -1.0F, -22.0F, -56.0F, 2, 2, 17, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -1.0F, -24.0F, -48.0F, 2, 3, 2, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -1.5F, -22.5F, -61.0F, 3, 3, 5, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 64, -3.0F, -26.0F, -36.0F, 6, 1, 7, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -3.0F, -26.0F, -29.0F, 6, 1, 30, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -3.0F, -25.0F, -39.0F, 6, 6, 1, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -2.5F, -18.5F, -21.5F, 5, 8, 7, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -3.0F, -19.5F, -22.0F, 6, 1, 8, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -3.0F, -19.0F, -14.0F, 6, 1, 1, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -3.0F, -18.0F, -13.0F, 6, 2, 1, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -3.0F, -16.0F, -12.0F, 6, 1, 5, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -3.0F, -19.0F, -7.0F, 6, 3, 1, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 64, -2.5F, -25.0F, 1.0F, 5, 6, 26, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 64, -3.0F, -19.0F, -6.0F, 6, 1, 6, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 64, -2.5F, -19.0F, 25.0F, 5, 6, 2, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 64, -2.5F, -19.0F, 18.0F, 5, 2, 7, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -0.5F, -28.0F, -53.0F, 1, 7, 1, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -0.5F, -26.0F, -52.0F, 1, 5, 1, 0.0F, false));

		mag = new ModelRenderer(this);
		mag.setRotationPoint(0.0F, 24.0F, 0.0F);
		setRotationAngle(mag, -0.1745F, 0.0F, 0.0F);
		mag.cubeList.add(new ModelBox(mag, 0, 0, -2.5F, -8.5F, -23.2F, 5, 9, 7, 0.0F, false));

		trigger = new ModelRenderer(this);
		trigger.setRotationPoint(0.0F, 24.0F, 0.0F);
		setRotationAngle(trigger, -0.1745F, 0.0F, 0.0F);
		trigger.cubeList.add(new ModelBox(trigger, 0, 0, -1.0F, -18.0F, -12.0F, 2, 3, 1, 0.0F, false));

		handle = new ModelRenderer(this);
		handle.setRotationPoint(0.0F, 24.0F, 0.0F);
		setRotationAngle(handle, 0.1745F, 0.0F, 0.0F);
		handle.cubeList.add(new ModelBox(handle, 0, 64, -2.5F, -19.5F, -2.0F, 5, 10, 5, 0.0F, false));

		stock = new ModelRenderer(this);
		stock.setRotationPoint(0.0F, 24.0F, 0.0F);
		setRotationAngle(stock, -0.2618F, 0.0F, 0.0F);
		stock.cubeList.add(new ModelBox(stock, 0, 64, -2.5F, -23.0F, -5.0F, 5, 4, 26, 0.0F, false));

		rail = new ModelRenderer(this);
		rail.setRotationPoint(0.0F, 24.0F, 0.0F);
		rail.cubeList.add(new ModelBox(rail, 0, 0, -2.5F, -27.0F, -28.5F, 5, 1, 29, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 0, -2.5F, -27.5F, -27.5F, 5, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 0, -2.5F, -27.5F, -25.5F, 5, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 0, -2.5F, -27.5F, -23.5F, 5, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 0, -2.5F, -27.5F, -21.5F, 5, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 0, -2.5F, -27.5F, -19.5F, 5, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 0, -2.5F, -27.5F, -17.5F, 5, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 0, -2.5F, -27.5F, -15.5F, 5, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 0, -2.5F, -27.5F, -13.5F, 5, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 0, -2.5F, -27.5F, -11.5F, 5, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 0, -2.5F, -27.5F, -9.5F, 5, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 0, -2.5F, -27.5F, -7.5F, 5, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 0, -2.5F, -27.5F, -5.5F, 5, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 0, -2.5F, -27.5F, -3.5F, 5, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 0, -2.5F, -27.5F, -1.5F, 5, 1, 1, 0.0F, false));

		irns = new ModelRenderer(this);
		irns.setRotationPoint(0.0F, 24.0F, 0.0F);
		irns.cubeList.add(new ModelBox(irns, 0, 0, -2.0F, -28.0F, -6.0F, 4, 1, 5, 0.0F, false));
		irns.cubeList.add(new ModelBox(irns, 0, 0, -2.0F, -29.0F, -6.0F, 1, 1, 5, 0.0F, false));
		irns.cubeList.add(new ModelBox(irns, 0, 0, 1.0F, -29.0F, -6.0F, 1, 1, 5, 0.0F, false));
		irns.cubeList.add(new ModelBox(irns, 0, 0, -0.5F, -28.5F, -2.0F, 1, 1, 0, 0.0F, false));
	}
	
	private void renderParts(boolean hasScope)
	{
		base.render(1f);
		mag.render(1f);
		trigger.render(1f);
		handle.render(1f);
		stock.render(1f);
		rail.render(1f);
		
		if(!hasScope) irns.render(1f);
	}
}

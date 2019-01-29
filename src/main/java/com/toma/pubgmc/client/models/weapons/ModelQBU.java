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

public class ModelQBU extends ModelGun
{
	private final ModelRenderer base;
	private final ModelRenderer deco;
	private final ModelRenderer handle;
	private final ModelRenderer trigger;
	private final ModelRenderer mag;
	private final ModelRenderer rail;
	private final ModelRenderer irns;

	public ModelQBU()
	{
		animation_aim = new AimAnimation(-0.56d, 0.305d, 0.22d, 1f);
		animation_aim.setInvertedCoords(true, false, false);
		animation_aim.setMovementMultiplier(1f, 1f, 1f);
		
		textureWidth = 128;
		textureHeight = 128;

		base = new ModelRenderer(this);
		base.setRotationPoint(0.0F, 24.0F, 0.0F);
		base.cubeList.add(new ModelBox(base, 0, 0, -3.0F, -26.0F, -44.0F, 6, 7, 52, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -1.0F, -24.0F, -65.0F, 2, 2, 21, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -1.5F, -24.5F, -75.0F, 3, 3, 10, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, 2.0F, -18.0F, -77.0F, 2, 2, 32, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -4.0F, -18.0F, -77.0F, 2, 2, 32, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, 1.5F, -18.5F, -78.0F, 3, 3, 1, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -4.5F, -18.5F, -78.0F, 3, 3, 1, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -2.5F, -24.5F, -47.0F, 5, 8, 2, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -2.0F, -19.0F, -15.0F, 3, 2, 1, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -2.0F, -17.0F, -14.0F, 4, 1, 1, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -2.0F, -16.0F, -13.0F, 4, 1, 4, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -2.0F, -19.0F, -3.0F, 4, 2, 34, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -2.0F, -26.0F, 8.0F, 4, 7, 23, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -3.0F, -26.0F, 31.0F, 6, 16, 3, 0.0F, false));

		deco = new ModelRenderer(this);
		deco.setRotationPoint(0.0F, 24.0F, 0.0F);
		deco.cubeList.add(new ModelBox(deco, 0, 0, 2.3F, -25.0F, -43.0F, 1, 5, 51, 0.0F, false));
		deco.cubeList.add(new ModelBox(deco, 0, 0, -3.3F, -25.0F, -43.0F, 1, 5, 51, 0.0F, false));

		handle = new ModelRenderer(this);
		handle.setRotationPoint(0.0F, 24.0F, 0.0F);
		setRotationAngle(handle, 0.1745F, 0.0F, 0.0F);
		handle.cubeList.add(new ModelBox(handle, 0, 0, -2.0F, -21.0F, -7.0F, 4, 15, 6, 0.0F, false));

		trigger = new ModelRenderer(this);
		trigger.setRotationPoint(0.0F, 24.0F, 0.0F);
		setRotationAngle(trigger, -0.1745F, 0.0F, 0.0F);
		trigger.cubeList.add(new ModelBox(trigger, 0, 0, -2.0F, -17.0F, -15.0F, 3, 2, 1, 0.0F, false));

		mag = new ModelRenderer(this);
		mag.setRotationPoint(0.0F, 24.0F, 0.0F);
		mag.cubeList.add(new ModelBox(mag, 0, 0, -1.5F, -17.0F, 2.0F, 3, 10, 11, 0.0F, false));

		rail = new ModelRenderer(this);
		rail.setRotationPoint(0.0F, 24.0F, 0.0F);
		rail.cubeList.add(new ModelBox(rail, 0, 0, -2.5F, -26.4F, -43.0F, 5, 1, 51, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 0, -2.5F, -27.0F, -43.0F, 5, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 0, -2.5F, -27.0F, -41.0F, 5, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 0, -2.5F, -27.0F, -39.0F, 5, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 0, -2.5F, -27.0F, -37.0F, 5, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 0, -2.5F, -27.0F, -35.0F, 5, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 0, -2.5F, -27.0F, -27.0F, 5, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 0, -2.5F, -27.0F, -29.0F, 5, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 0, -2.5F, -27.0F, -31.0F, 5, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 0, -2.5F, -27.0F, -33.0F, 5, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 0, -2.5F, -27.0F, -25.0F, 5, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 0, -2.5F, -27.0F, -17.0F, 5, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 0, -2.5F, -27.0F, -19.0F, 5, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 0, -2.5F, -27.0F, -21.0F, 5, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 0, -2.5F, -27.0F, -23.0F, 5, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 0, -2.5F, -27.0F, -15.0F, 5, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 0, -2.5F, -27.0F, -7.0F, 5, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 0, -2.5F, -27.0F, -9.0F, 5, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 0, -2.5F, -27.0F, -11.0F, 5, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 0, -2.5F, -27.0F, -13.0F, 5, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 0, -2.5F, -27.0F, -5.0F, 5, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 0, -2.5F, -27.0F, 3.0F, 5, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 0, -2.5F, -27.0F, 1.0F, 5, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 0, -2.5F, -27.0F, -1.0F, 5, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 0, -2.5F, -27.0F, -3.0F, 5, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 0, -2.5F, -27.0F, 5.0F, 5, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 0, -2.5F, -27.0F, 7.0F, 5, 1, 1, 0.0F, false));

		irns = new ModelRenderer(this);
		irns.setRotationPoint(0.0F, 24.0F, 0.0F);
		irns.cubeList.add(new ModelBox(irns, 0, 0, -0.5F, -28.5F, -41.0F, 1, 3, 1, 0.0F, false));
		irns.cubeList.add(new ModelBox(irns, 0, 0, -0.5F, -28.0F, 6.0F, 1, 2, 1, 0.0F, false));
		irns.cubeList.add(new ModelBox(irns, 0, 0, -1.5F, -29.5F, 6.0F, 1, 2, 1, 0.0F, false));
		irns.cubeList.add(new ModelBox(irns, 0, 0, 0.5F, -29.5F, 6.0F, 1, 2, 1, 0.0F, false));
		irns.cubeList.add(new ModelBox(irns, 0, 0, -0.5F, -30.0F, 6.0F, 1, 1, 1, 0.0F, false));
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
				renderQBU(data.isAiming(), stack);
			}
			GlStateManager.popMatrix();
		}
	}
	
	private void handleAnimations(boolean aim, boolean sprint, boolean reload, ItemStack stack)
	{
		if(enableADS(stack))
		{
			if(!hasScopeAtachment(stack) && animation_aim.getFinalY() != 0.305d)
				animation_aim.setYModifier(0.305d);
			else if(hasRedDot(stack) && animation_aim.getFinalY() != 0.2375d)
				animation_aim.setYModifier(0.2375d);
			else if(hasHoloSight(stack) && animation_aim.getFinalY() != 0.195d)
				animation_aim.setYModifier(0.195d);

			animation_aim.run(aim);
		}
		animation_reload.run(reload);
		animation_held.run(sprint);
	}
	
	private void renderQBU(boolean aim, ItemStack stack)
	{
		GlStateManager.pushMatrix();
		transform.defaultSRTransform();
		GlStateManager.translate(0.0, 7.0, 1.0);
		
		if(aim && enableADS(stack))
		{
			rotateModelForADSRendering();
		}
		
		renderParts(hasScopeAtachment(stack));
		GlStateManager.popMatrix();
		//attachments
		if(hasSilencer(stack))
			renderSilencer(stack);
		
		if(hasRedDot(stack))
			renderRedDot(stack);
		
		else if(hasHoloSight(stack))
			renderHolo(stack);
		
		else if(has2X(stack))
			render2x(stack);
		
		else if(has4X(stack))
			render4x(stack);
		
		else if(has8X(stack))
			render8x(stack);
		
		else if(has15X(stack))
			render15x(stack);
	}
	
	private void renderRedDot(ItemStack stack)
	{
		renderRedDot(-0.4, -4.8, 2, 1.3f, stack);
	}
	
	private void renderHolo(ItemStack stack)
	{
		renderHolo(-1.5, -5.4, -1, 1.3f, stack);
	}
	
	private void render2x(ItemStack stack)
	{
		renderScope2X(7.9, 13, -6, 1f, stack);
	}
	
	private void render4x(ItemStack stack)
	{
		renderScope4X(7.9, 13, -6, 1f, stack);
	}
	
	private void render8x(ItemStack stack)
	{
		renderScope8X(0, 1.4, -3, 1f, stack);
	}
	
	private void render15x(ItemStack stack)
	{
		renderScope15X(0, 2, -2, 1f, stack);
	}
	
	private void renderSilencer(ItemStack stack)
	{
		renderSniperSilencer(0, -1.1, -5, 1f, stack);
	}
	
	private void renderParts(boolean hasScope)
	{
		base.render(1f);
		deco.render(1f);
		handle.render(1f);
		trigger.render(1f);
		mag.render(1f);
		rail.render(1f);
		if(!hasScope) irns.render(1f);
	}
}

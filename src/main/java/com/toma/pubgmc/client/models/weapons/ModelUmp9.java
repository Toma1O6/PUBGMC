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

public class ModelUmp9 extends ModelGun
{
	private final ModelRenderer ump;
	private final ModelRenderer mag;
	private final ModelRenderer handle;
	private final ModelRenderer stock;

	public ModelUmp9()
	{
		animation_aim = new AimAnimation(-0.56d, 0.14d, 0.14d, 1f);
		animation_aim.setInvertedCoords(true, false, false);
		animation_aim.setMovementMultiplier(1.4f, 1f, 1f);
		animation_held.setWeaponType(true);
		
		textureWidth = 128;
		textureHeight = 128;

		ump = new ModelRenderer(this);
		ump.setRotationPoint(0.0F, 24.0F, 0.0F);
		ump.cubeList.add(new ModelBox(ump, 0, 0, -0.5F, -25.0F, -25.0F, 1, 2, 2, 0.0F, false));
		ump.cubeList.add(new ModelBox(ump, 0, 0, -3.0F, -23.0F, -26.0F, 6, 5, 26, 0.0F, false));
		ump.cubeList.add(new ModelBox(ump, 0, 0, -1.0F, -21.0F, -28.0F, 2, 2, 2, 0.0F, false));
		ump.cubeList.add(new ModelBox(ump, 0, 0, -2.5F, -23.5F, -25.5F, 5, 1, 25, 0.0F, false));
		ump.cubeList.add(new ModelBox(ump, 0, 0, -2.0F, -23.7F, -15.0F, 4, 1, 1, 0.0F, false));
		ump.cubeList.add(new ModelBox(ump, 0, 0, -2.0F, -23.7F, -13.0F, 4, 1, 1, 0.0F, false));
		ump.cubeList.add(new ModelBox(ump, 0, 0, -2.0F, -23.7F, -11.0F, 4, 1, 1, 0.0F, false));
		ump.cubeList.add(new ModelBox(ump, 0, 0, -2.0F, -23.7F, -9.0F, 4, 1, 1, 0.0F, false));
		ump.cubeList.add(new ModelBox(ump, 0, 0, -2.0F, -23.7F, -7.0F, 4, 1, 1, 0.0F, false));
		ump.cubeList.add(new ModelBox(ump, 0, 0, -2.0F, -23.7F, -5.0F, 4, 1, 1, 0.0F, false));
		ump.cubeList.add(new ModelBox(ump, 0, 0, -2.0F, -23.7F, -3.0F, 4, 1, 1, 0.0F, false));
		ump.cubeList.add(new ModelBox(ump, 0, 0, -2.0F, -23.7F, -17.0F, 4, 1, 1, 0.0F, false));
		ump.cubeList.add(new ModelBox(ump, 0, 0, -3.0F, -23.0F, 0.0F, 6, 5, 5, 0.0F, false));
		ump.cubeList.add(new ModelBox(ump, 0, 0, -2.5F, -23.5F, -0.5F, 5, 1, 2, 0.0F, false));
		ump.cubeList.add(new ModelBox(ump, 0, 0, -2.0F, -24.0F, -1.0F, 4, 1, 1, 0.0F, false));
		ump.cubeList.add(new ModelBox(ump, 0, 0, -1.0F, -24.0F, 2.0F, 2, 1, 1, 0.0F, false));
		ump.cubeList.add(new ModelBox(ump, 0, 0, -1.7F, -26.0F, 2.0F, 1, 2, 1, 0.0F, false));
		ump.cubeList.add(new ModelBox(ump, 0, 0, 0.7F, -26.0F, 2.0F, 1, 2, 1, 0.0F, false));
		ump.cubeList.add(new ModelBox(ump, 0, 0, -1.0F, -27.0F, 2.0F, 2, 1, 1, 0.0F, false));
		ump.cubeList.add(new ModelBox(ump, 0, 0, -3.0F, -25.0F, 0.0F, 1, 2, 4, 0.0F, false));
		ump.cubeList.add(new ModelBox(ump, 0, 0, 2.0F, -25.0F, 0.0F, 1, 2, 4, 0.0F, false));
		ump.cubeList.add(new ModelBox(ump, 0, 0, -3.0F, -22.0F, 5.0F, 6, 4, 2, 0.0F, false));
		ump.cubeList.add(new ModelBox(ump, 0, 0, -2.5F, -18.0F, -13.0F, 5, 2, 20, 0.0F, false));
		ump.cubeList.add(new ModelBox(ump, 0, 0, -2.5F, -16.0F, -13.0F, 5, 2, 7, 0.0F, false));
		ump.cubeList.add(new ModelBox(ump, 0, 0, -1.5F, -16.0F, -6.0F, 3, 2, 1, 0.0F, false));
		ump.cubeList.add(new ModelBox(ump, 0, 0, -1.5F, -14.0F, -5.0F, 3, 1, 1, 0.0F, false));
		ump.cubeList.add(new ModelBox(ump, 0, 0, -1.5F, -13.0F, -4.0F, 3, 1, 3, 0.0F, false));
		ump.cubeList.add(new ModelBox(ump, 0, 0, -1.5F, -14.0F, -1.0F, 3, 1, 2, 0.0F, false));
		ump.cubeList.add(new ModelBox(ump, 0, 0, -1.5F, -21.0F, 7.0F, 3, 1, 14, 0.0F, false));
		ump.cubeList.add(new ModelBox(ump, 0, 0, -1.5F, -21.0F, 21.0F, 3, 11, 2, 0.0F, false));
		ump.cubeList.add(new ModelBox(ump, 0, 0, -1.5F, -20.5F, 12.0F, 3, 6, 1, 0.0F, false));
		ump.cubeList.add(new ModelBox(ump, 0, 0, -2.0F, -23.7F, -19.0F, 4, 1, 1, 0.0F, false));
		ump.cubeList.add(new ModelBox(ump, 0, 0, -3.5F, -21.5F, -24.0F, 7, 4, 1, 0.0F, false));
		ump.cubeList.add(new ModelBox(ump, 0, 0, -3.5F, -21.5F, -22.0F, 7, 4, 1, 0.0F, false));
		ump.cubeList.add(new ModelBox(ump, 0, 0, -3.5F, -21.5F, -20.0F, 7, 4, 1, 0.0F, false));
		ump.cubeList.add(new ModelBox(ump, 0, 0, -3.5F, -21.5F, -18.0F, 7, 4, 1, 0.0F, false));
		ump.cubeList.add(new ModelBox(ump, 0, 0, -3.5F, -21.5F, -16.0F, 7, 4, 1, 0.0F, false));

		mag = new ModelRenderer(this);
		mag.setRotationPoint(0.0F, 24.0F, 0.0F);
		setRotationAngle(mag, -0.1745F, 0.0F, 0.0F);
		mag.cubeList.add(new ModelBox(mag, 0, 0, -1.5F, -13.0F, -14.4F, 3, 14, 5, 0.0F, false));
		mag.cubeList.add(new ModelBox(mag, 0, 0, -1.0F, -16.0F, -5.0F, 3, 2, 1, 0.0F, false));

		handle = new ModelRenderer(this);
		handle.setRotationPoint(0.0F, 24.0F, 0.0F);
		setRotationAngle(handle, 0.1745F, 0.0F, 0.0F);
		handle.cubeList.add(new ModelBox(handle, 0, 0, -2.0F, -16.0F, 3.0F, 4, 10, 4, 0.0F, false));

		stock = new ModelRenderer(this);
		stock.setRotationPoint(0.0F, 24.0F, 0.0F);
		setRotationAngle(stock, -0.4363F, 0.0F, 0.0F);
		stock.cubeList.add(new ModelBox(stock, 0, 0, -1.5F, -19.0F, -2.0F, 3, 1, 17, 0.0F, false));
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
			handleAnimations(data.isAiming(), player.isSprinting(), stack);
			renderUmp(data.isAiming(), stack);
			GlStateManager.popMatrix();
		}
	}
	
	private void handleAnimations(boolean aim, boolean sprint, ItemStack stack)
	{
		if(enableADS(stack))
		{
			if(!hasScopeAtachment(stack) && animation_aim.getFinalY() != 0.14d)
				animation_aim.setYModifier(0.14d);
			else if(hasRedDot(stack) && animation_aim.getFinalY() != 0.06d)
				animation_aim.setYModifier(0.06d);
			else if(hasHoloSight(stack) && animation_aim.getFinalY() != 0.03d)
				animation_aim.setYModifier(0.03d);
			
			animation_aim.run(aim);
		}
		
		animation_held.run(sprint);
	}
	
	private void renderUmp(boolean aim, ItemStack stack)
	{
		GlStateManager.pushMatrix();
		transform.defaultSMGTransform();
		
		if(aim && enableADS(stack))
		{
			rotateModelForADSRendering();
		}
		
		renderAll();
		GlStateManager.popMatrix();
		
		renderSilencer(stack);
		renderGripVertical(stack);
		renderGripAngled(stack);
		renderRedDotSight(stack);
		renderHolographic(stack);
		render2X(stack);
		render4X(stack);
	}
	
	private void renderSilencer(ItemStack stack)
	{
		renderSMGSilencer(0, 2.3, -15, 1f, stack);
	}
	
	private void renderRedDotSight(ItemStack stack)
	{
		renderRedDot(-0.3, -14.9, -2.7, 1.6f, stack);
	}
	
	private void renderHolographic(ItemStack stack)
	{
		renderHolo(-1.5, -13.1, -7, 1.4f, stack);
	}
	
	private void render2X(ItemStack stack)
	{
		renderScope2X(8, 4, -10, 1f, stack);
	}
	
	private void render4X(ItemStack stack)
	{
		renderScope4X(8, 5, -11, 1f, stack);
	}
	
	private void renderGripVertical(ItemStack stack)
	{
		renderVerticalGrip(0, 0, 0, 1f, stack);
	}
	
	private void renderGripAngled(ItemStack stack)
	{
		renderAngledGrip(0, 0, 0, 1f, stack);
	}
	
	private void renderAll()
	{
		ump.render(1f);
		mag.render(1f);
		handle.render(1f);
		stock.render(1f);
	}
}

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

public class ModelMini14 extends ModelGun
{
	private final ModelRenderer base;
	private final ModelRenderer mag;
	private final ModelRenderer magSH;
	private final ModelRenderer trigger;
	private final ModelRenderer handle;
	private final ModelRenderer irns;
	private final ModelRenderer rail;

	public ModelMini14()
	{
		textureWidth = 128;
		textureHeight = 128;

		base = new ModelRenderer(this);
		base.setRotationPoint(0.0F, 24.0F, 0.0F);
		base.cubeList.add(new ModelBox(base, 0, 0, -3.0F, -21.0F, -37.0F, 6, 2, 48, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 64, -3.5F, -19.0F, -37.0F, 7, 4, 22, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 64, -3.5F, -19.0F, -15.0F, 7, 5, 25, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -3.0F, -21.0F, -38.0F, 6, 2, 1, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -3.5F, -19.0F, -38.0F, 7, 4, 1, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -1.0F, -19.5F, -60.0F, 2, 2, 22, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -1.5F, -20.0F, -65.0F, 3, 3, 5, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -3.5F, -19.0F, 10.0F, 7, 5, 1, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -1.0F, -16.5F, -45.0F, 2, 1, 7, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -2.5F, -11.0F, 0.0F, 5, 1, 4, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -2.5F, -14.0F, 3.0F, 5, 3, 1, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -1.5F, -20.0F, -48.0F, 3, 5, 3, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -2.5F, -14.0F, -2.0F, 5, 2, 1, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -2.5F, -12.0F, -1.0F, 5, 1, 1, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -1.5F, -19.0F, 11.0F, 3, 2, 25, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -3.0F, -20.0F, 36.0F, 5, 13, 3, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -1.5F, -17.0F, 34.0F, 3, 3, 2, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -0.5F, -23.5F, -47.5F, 1, 4, 1, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -0.5F, -22.5F, -46.5F, 1, 3, 1, 0.0F, false));

		mag = new ModelRenderer(this);
		mag.setRotationPoint(0.0F, 24.0F, 0.0F);
		mag.cubeList.add(new ModelBox(mag, 0, 0, -2.5F, -14.0F, -13.0F, 5, 6, 8, 0.0F, false));

		magSH = new ModelRenderer(this);
		magSH.setRotationPoint(0.0F, 24.0F, 0.0F);
		setRotationAngle(magSH, -0.1745F, 0.0F, 0.0F);
		magSH.cubeList.add(new ModelBox(magSH, 0, 0, -2.5F, -7.0F, -14.3F, 5, 8, 8, 0.0F, false));

		trigger = new ModelRenderer(this);
		trigger.setRotationPoint(0.0F, 24.0F, 0.0F);
		setRotationAngle(trigger, -0.1745F, 0.0F, 0.0F);
		trigger.cubeList.add(new ModelBox(trigger, 0, 0, -1.0F, -15.0F, -1.0F, 2, 3, 1, 0.0F, false));

		handle = new ModelRenderer(this);
		handle.setRotationPoint(0.0F, 24.0F, 0.0F);
		setRotationAngle(handle, 0.1745F, 0.0F, 0.0F);
		handle.cubeList.add(new ModelBox(handle, 0, 0, -2.5F, -13.0F, 7.0F, 5, 10, 5, 0.0F, false));

		irns = new ModelRenderer(this);
		irns.setRotationPoint(0.0F, 24.0F, 0.0F);
		irns.cubeList.add(new ModelBox(irns, 0, 0, -2.0F, -22.0F, 5.0F, 4, 1, 4, 0.0F, false));
		irns.cubeList.add(new ModelBox(irns, 0, 0, 0.75F, -23.0F, 5.5F, 1, 1, 3, 0.0F, false));
		irns.cubeList.add(new ModelBox(irns, 0, 0, -1.75F, -23.0F, 5.5F, 1, 1, 3, 0.0F, false));

		rail = new ModelRenderer(this);
		rail.setRotationPoint(0.0F, 24.0F, 0.0F);
		rail.cubeList.add(new ModelBox(rail, 0, 0, -2.5F, -21.5F, -37.0F, 5, 1, 47, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 0, -2.5F, -22.0F, -37.0F, 5, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 0, -2.5F, -22.0F, -35.0F, 5, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 0, -2.5F, -22.0F, -33.0F, 5, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 0, -2.5F, -22.0F, -31.0F, 5, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 0, -2.5F, -22.0F, -29.0F, 5, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 0, -2.5F, -22.0F, -27.0F, 5, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 0, -2.5F, -22.0F, -25.0F, 5, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 0, -2.5F, -22.0F, -23.0F, 5, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 0, -2.5F, -22.0F, -21.0F, 5, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 0, -2.5F, -22.0F, -19.0F, 5, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 0, -2.5F, -22.0F, -17.0F, 5, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 0, -2.5F, -22.0F, -15.0F, 5, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 0, -2.5F, -22.0F, -13.0F, 5, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 0, -2.5F, -22.0F, -11.0F, 5, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 0, -2.5F, -22.0F, -9.0F, 5, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 0, -2.5F, -22.0F, -7.0F, 5, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 0, -2.5F, -22.0F, -5.0F, 5, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 0, -2.5F, -22.0F, -3.0F, 5, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 0, -2.5F, -22.0F, -1.0F, 5, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 0, -2.5F, -22.0F, 1.0F, 5, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 0, -2.5F, -22.0F, 3.0F, 5, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 0, -2.5F, -22.0F, 5.0F, 5, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 0, -2.5F, -22.0F, 7.0F, 5, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 0, -2.5F, -22.0F, 9.0F, 5, 1, 1, 0.0F, false));
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
				renderMini14(data.isAiming(), stack);
			}
			GlStateManager.popMatrix();
		}
	}
	
	private void renderMini14(boolean aim, ItemStack stack)
	{
		GlStateManager.pushMatrix();
		transform.defaultSRTransform();
		
		if(aim && enableADS(stack))
		{
			rotateModelForADSRendering();
		}
		
		renderParts(hasScopeAtachment(stack));
		GlStateManager.popMatrix();
		
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
		
		if(hasSilencer(stack))
			renderSilencer(stack);
	}
	
	private void renderRedDot(ItemStack stack)
	{
		renderRedDot(-0.4, -6.7, 0, 1.3f, stack);
	}
	
	private void renderHolo(ItemStack stack)
	{
		renderHolo(-1.5, -7, -1, 1.3f, stack);
	}
	
	private void render2x(ItemStack stack)
	{
		renderScope2X(7.9, 11, -6, 1f, stack);
	}
	
	private void render4x(ItemStack stack)
	{
		renderScope4X(7.9, 11, -6, 1f, stack);
	}
	
	private void render8x(ItemStack stack)
	{
		renderScope8X(0, 0, 0, 1f, stack);
	}
	
	private void render15x(ItemStack stack)
	{
		renderScope15X(0, 0, 0, 1f, stack);
	}
	
	private void renderSilencer(ItemStack stack)
	{
		renderSniperSilencer(0, 0, 0, 1f, stack);
	}
	
	private void renderParts(boolean hasScope)
	{
		base.render(1f);
		mag.render(1f);
		magSH.render(1f);
		trigger.render(1f);
		handle.render(1f);
		rail.render(1f);
		if(!hasScope) irns.render(1f);
	}
}

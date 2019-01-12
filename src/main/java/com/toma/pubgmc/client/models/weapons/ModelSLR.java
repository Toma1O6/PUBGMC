package com.toma.pubgmc.client.models.weapons;

import com.toma.pubgmc.client.models.ModelGun;
import com.toma.pubgmc.common.capability.IPlayerData.PlayerDataProvider;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.item.ItemStack;

public class ModelSLR extends ModelGun
{
	private final ModelRenderer base;
	private final ModelRenderer rail;
	private final ModelRenderer mag;
	private final ModelRenderer magsh;
	private final ModelRenderer trigger;
	private final ModelRenderer handle;
	private final ModelRenderer stock;
	private final ModelRenderer irns;
	private final ModelRenderer stock2;

	public ModelSLR()
	{
		textureWidth = 128;
		textureHeight = 128;

		base = new ModelRenderer(this);
		base.setRotationPoint(0.0F, 24.0F, 0.0F);
		base.cubeList.add(new ModelBox(base, 0, 0, -3.0F, -23.0F, -50.0F, 6, 6, 51, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -1.0F, -21.0F, -70.0F, 2, 2, 20, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -1.5F, -21.5F, -79.0F, 3, 3, 9, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -4.0F, -24.0F, -47.0F, 8, 8, 20, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -3.0F, -17.0F, -26.0F, 6, 3, 27, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -2.0F, -14.0F, -14.0F, 4, 2, 1, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -2.0F, -12.0F, -13.0F, 4, 1, 1, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -2.0F, -11.0F, -12.0F, 4, 1, 6, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -2.0F, -20.0F, 28.0F, 4, 15, 2, 0.0F, false));

		rail = new ModelRenderer(this);
		rail.setRotationPoint(0.0F, 24.0F, 0.0F);
		rail.cubeList.add(new ModelBox(rail, 0, 0, -2.5F, -23.5F, -26.0F, 5, 1, 27, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 0, -2.5F, -24.0F, -26.0F, 5, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 0, -2.5F, -24.0F, -24.0F, 5, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 0, -2.5F, -24.0F, -22.0F, 5, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 0, -2.5F, -24.0F, -20.0F, 5, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 0, -2.5F, -24.0F, -18.0F, 5, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 0, -2.5F, -24.0F, -16.0F, 5, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 0, -2.5F, -24.0F, -14.0F, 5, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 0, -2.5F, -24.0F, -12.0F, 5, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 0, -2.5F, -24.0F, -10.0F, 5, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 0, -2.5F, -24.0F, -8.0F, 5, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 0, -2.5F, -24.0F, -6.0F, 5, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 0, -2.5F, -24.0F, -4.0F, 5, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 0, -2.5F, -24.0F, -2.0F, 5, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 0, -2.5F, -24.0F, 0.0F, 5, 1, 1, 0.0F, false));

		mag = new ModelRenderer(this);
		mag.setRotationPoint(0.0F, 24.0F, 0.0F);
		mag.cubeList.add(new ModelBox(mag, 0, 0, -2.0F, -14.0F, -25.0F, 4, 11, 10, 0.0F, false));
		mag.cubeList.add(new ModelBox(mag, 0, 0, -2.0F, -3.69F, -16.0F, 4, 3, 1, 0.0F, false));
		mag.cubeList.add(new ModelBox(mag, 0, 0, -2.0F, -3.1F, -23.0F, 4, 1, 7, 0.0F, false));
		mag.cubeList.add(new ModelBox(mag, 0, 0, -2.0F, -2.69F, -18.0F, 4, 1, 2, 0.0F, false));

		magsh = new ModelRenderer(this);
		magsh.setRotationPoint(0.0F, 24.0F, 0.0F);
		setRotationAngle(magsh, -0.1745F, 0.0F, 0.0F);
		magsh.cubeList.add(new ModelBox(magsh, 0, 0, -2.0F, 0.974F, -25.1318F, 4, 1, 10, 0.0F, false));

		trigger = new ModelRenderer(this);
		trigger.setRotationPoint(0.0F, 24.0F, 0.0F);
		setRotationAngle(trigger, -0.1745F, 0.0F, 0.0F);
		trigger.cubeList.add(new ModelBox(trigger, 0, 0, -2.0F, -13.0F, -13.0F, 4, 3, 1, 0.0F, false));

		handle = new ModelRenderer(this);
		handle.setRotationPoint(0.0F, 24.0F, 0.0F);
		setRotationAngle(handle, 0.3491F, 0.0F, 0.0F);
		handle.cubeList.add(new ModelBox(handle, 0, 0, -2.0F, -17.0F, -2.0F, 4, 15, 5, 0.0F, false));

		stock = new ModelRenderer(this);
		stock.setRotationPoint(0.0F, 24.0F, 0.0F);
		setRotationAngle(stock, -0.0873F, 0.0F, 0.0F);
		stock.cubeList.add(new ModelBox(stock, 0, 0, -2.0F, -22.0F, -2.0F, 4, 6, 29, 0.0F, false));
		stock.cubeList.add(new ModelBox(stock, 0, 0, -2.0F, -18.0F, 14.0F, 4, 6, 14, 0.0F, false));

		irns = new ModelRenderer(this);
		irns.setRotationPoint(0.0F, 24.0F, 0.0F);
		irns.cubeList.add(new ModelBox(irns, 0, 0, -1.0F, -26.5F, -49.0F, 2, 1, 1, 0.0F, false));
		irns.cubeList.add(new ModelBox(irns, 0, 0, 0.5F, -25.5F, -49.0F, 1, 2, 1, 0.0F, false));
		irns.cubeList.add(new ModelBox(irns, 0, 0, -1.5F, -25.5F, -49.0F, 1, 2, 1, 0.0F, false));
		irns.cubeList.add(new ModelBox(irns, 0, 0, -0.5F, -24.5F, -49.0F, 1, 3, 1, 0.0F, false));
		irns.cubeList.add(new ModelBox(irns, 0, 0, -1.5F, -24.0F, -3.0F, 3, 3, 3, 0.0F, false));
		irns.cubeList.add(new ModelBox(irns, 0, 0, -0.5F, -24.5F, -2.0F, 1, 1, 1, 0.0F, false));
		irns.cubeList.add(new ModelBox(irns, 0, 0, -2.5F, -25.0F, -3.0F, 1, 1, 3, 0.0F, false));
		irns.cubeList.add(new ModelBox(irns, 0, 0, 1.5F, -25.0F, -3.0F, 1, 1, 3, 0.0F, false));

		stock2 = new ModelRenderer(this);
		stock2.setRotationPoint(0.0F, 24.0F, 0.0F);
		setRotationAngle(stock2, -0.3491F, 0.0F, 0.0F);
		stock2.cubeList.add(new ModelBox(stock2, 0, 0, -2.0F, -19.0F, -5.0F, 4, 4, 30, 0.0F, false));
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
			
			GlStateManager.pushMatrix();
			{
				animation_held.runAnimation(player.isSprinting());
				renderSLR(aim, stack);
			}
			GlStateManager.popMatrix();
		}
	}
	
	private void renderSLR(boolean aim, ItemStack stack)
	{
		GlStateManager.pushMatrix();
		transform.defaultSRTransform();
		GlStateManager.translate(0.0, 2.0, 5.0);
		
		if(aim && enableADS(stack))
		{
			GlStateManager.rotate(0.2f, 0, 1f, 0);
			GlStateManager.rotate(0.5f, 1f, 0, 0);
			GlStateManager.translate(26.6, -13.2, 16.0);
			
			if(hasRedDot(stack))
				GlStateManager.translate(0, 3.8, 0);
			else if(hasHoloSight(stack))
				GlStateManager.translate(0, 6, 0);
		}
		
		renderParts(hasScopeAtachment(stack));
		GlStateManager.popMatrix();
		
		if(hasSilencer(stack))
			renderSilencer(aim, stack);
		if(hasRedDot(stack))
			renderRedDot(aim, stack);
		else if(hasHoloSight(stack))
			renderHolo(aim, stack);
		else if(has2X(stack))
			render2x(stack);
		else if(has4X(stack))
			render4x(stack);
		else if(has8X(stack))
			render8x(stack);
		else if(has15X(stack))
			render15x(stack);
	}
	
	private void renderRedDot(boolean aim, ItemStack stack)
	{
		if(aim)
		{
			renderRedDot(28.25, -17, 18, 1.3f, stack);
		}
		else renderRedDot(-0.3, -7, 1, 1.3f, stack);
	}
	
	private void renderHolo(boolean aim, ItemStack stack)
	{
		if(aim)
		{
			renderHolo(22.4, -13.8, 11, 1.3f, stack);	
		}
		else renderHolo(-1.5, -7.1, -3, 1.3f, stack);
	}
	
	private void render2x(ItemStack stack)
	{
		renderScope2X(8, 10.6, -10, 1f, stack);
	}
	
	private void render4x(ItemStack stack)
	{
		renderScope4X(8, 10.6, -10, 1f, stack);
	}
	
	private void render8x(ItemStack stack)
	{
		renderScope8X(0, 0, 0, 1f, stack);
	}
	
	private void render15x(ItemStack stack)
	{
		renderScope15X(0, 0, 0, 1f, stack);
	}
	
	private void renderSilencer(boolean aim, ItemStack stack)
	{
		if(aim && enableADS(stack))
		{
			if(hasRedDot(stack))
			{
				renderSniperSilencer(-16, 5.3, 9, 1f, stack);
			}
			
			else if(hasHoloSight(stack))
			{
				renderSniperSilencer(-16, 4, 9, 1f, stack);
			}
			
			else renderSniperSilencer(-16, 8, 9, 1f, stack);
		}
		else renderSniperSilencer(0, 0, 0, 1f, stack);
	}
	
	private void renderParts(boolean hasScope)
	{
		base.render(1f);
		rail.render(1f);
		mag.render(1f);
		magsh.render(1f);
		trigger.render(1f);
		handle.render(1f);
		stock.render(1f);
		stock2.render(1f);
		if(!hasScope) irns.render(1f);
	}
}

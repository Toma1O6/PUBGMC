package com.toma.pubgmc.client.models.weapons;

import com.toma.pubgmc.client.models.ModelGun;
import com.toma.pubgmc.common.capability.IPlayerData.PlayerDataProvider;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.item.ItemStack;

public class ModelMK14 extends ModelGun
{
	private final ModelRenderer bone;
	private final ModelRenderer rail;
	private final ModelRenderer irns;
	private final ModelRenderer mag;
	private final ModelRenderer trigger;
	private final ModelRenderer handle;
	private final ModelRenderer stock;

	public ModelMK14()
	{
		textureWidth = 128;
		textureHeight = 128;

		bone = new ModelRenderer(this);
		bone.setRotationPoint(0.0F, 24.0F, 0.0F);
		bone.cubeList.add(new ModelBox(bone, 0, 0, -3.0F, -23.0F, -51.0F, 6, 6, 55, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 0, 0, -1.0F, -22.0F, -76.0F, 2, 2, 25, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 0, 0, -1.0F, -19.0F, -60.0F, 2, 1, 9, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 0, 0, -2.0F, -23.0F, -60.0F, 4, 6, 2, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 0, 0, -0.5F, -25.0F, -55.0F, 1, 3, 1, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 0, 0, -3.5F, -22.0F, -51.0F, 7, 7, 4, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 0, 0, 3.0F, -16.0F, -65.0F, 1, 1, 14, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 0, 0, -4.0F, -16.0F, -65.0F, 1, 1, 14, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 0, 0, -1.5F, -22.5F, -82.0F, 3, 3, 6, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 0, 0, 2.5F, -16.5F, -66.0F, 2, 2, 1, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 0, 0, -4.5F, -16.5F, -66.0F, 2, 2, 1, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 0, 0, -3.5F, -22.0F, -47.0F, 7, 6, 24, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 0, 0, -3.5F, -22.0F, -23.0F, 7, 8, 29, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 0, 0, -2.0F, -14.0F, -8.0F, 4, 2, 1, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 0, 0, 1.2F, -14.0F, -3.0F, 0, 2, 1, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 0, 0, 1.2F, -12.0F, -7.0F, 0, 1, 4, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 0, 0, 1.0F, -21.0F, 6.0F, 2, 2, 29, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 0, 0, -3.0F, -21.0F, 6.0F, 2, 2, 29, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 0, 0, -4.0F, -21.5F, 34.0F, 8, 14, 2, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 0, 0, -4.0F, -21.5F, 26.0F, 8, 3, 8, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 0, 0, -3.0F, -19.0F, 31.0F, 6, 5, 3, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 0, 0, -3.0F, -14.0F, 33.0F, 6, 4, 1, 0.0F, false));

		rail = new ModelRenderer(this);
		rail.setRotationPoint(0.0F, 24.0F, 0.0F);
		rail.cubeList.add(new ModelBox(rail, 0, 0, -2.5F, -23.5F, -51.0F, 5, 1, 44, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 0, -2.5F, -24.0F, -51.0F, 5, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 0, -2.5F, -24.0F, -49.0F, 5, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 0, -2.5F, -24.0F, -47.0F, 5, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 0, -2.5F, -24.0F, -45.0F, 5, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 0, -2.5F, -24.0F, -43.0F, 5, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 0, -2.5F, -24.0F, -41.0F, 5, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 0, -2.5F, -24.0F, -39.0F, 5, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 0, -2.5F, -24.0F, -37.0F, 5, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 0, -2.5F, -24.0F, -35.0F, 5, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 0, -2.5F, -24.0F, -33.0F, 5, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 0, -2.5F, -24.0F, -31.0F, 5, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 0, -2.5F, -24.0F, -29.0F, 5, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 0, -2.5F, -24.0F, -27.0F, 5, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 0, -2.5F, -24.0F, -25.0F, 5, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 0, -2.5F, -24.0F, -23.0F, 5, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 0, -2.5F, -24.0F, -21.0F, 5, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 0, -2.5F, -24.0F, -19.0F, 5, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 0, -2.5F, -24.0F, -17.0F, 5, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 0, -2.5F, -24.0F, -15.0F, 5, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 0, -2.5F, -24.0F, -13.0F, 5, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 0, -2.5F, -24.0F, -11.0F, 5, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 0, -2.5F, -23.5F, -6.0F, 5, 1, 9, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 0, -2.5F, -24.0F, -9.0F, 5, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 0, -2.5F, -24.0F, -7.0F, 5, 1, 1, 0.0F, false));

		irns = new ModelRenderer(this);
		irns.setRotationPoint(0.0F, 24.0F, 0.0F);
		irns.cubeList.add(new ModelBox(irns, 0, 0, -2.5F, -24.5F, -4.0F, 1, 1, 5, 0.0F, false));
		irns.cubeList.add(new ModelBox(irns, 0, 0, 1.5F, -24.5F, -4.0F, 1, 1, 5, 0.0F, false));
		irns.cubeList.add(new ModelBox(irns, 0, 0, 1.5F, -25.5F, -3.0F, 1, 1, 3, 0.0F, false));
		irns.cubeList.add(new ModelBox(irns, 0, 0, -2.5F, -25.5F, -3.0F, 1, 1, 3, 0.0F, false));
		irns.cubeList.add(new ModelBox(irns, 0, 0, -0.5F, -24.0F, -2.0F, 1, 1, 1, 0.0F, false));
		irns.cubeList.add(new ModelBox(irns, 0, 0, -1.5F, -25.0F, -2.0F, 1, 2, 1, 0.0F, false));
		irns.cubeList.add(new ModelBox(irns, 0, 0, 0.5F, -25.0F, -2.0F, 1, 2, 1, 0.0F, false));
		irns.cubeList.add(new ModelBox(irns, 0, 0, -1.0F, -26.0F, -2.0F, 2, 1, 1, 0.0F, false));

		mag = new ModelRenderer(this);
		mag.setRotationPoint(0.0F, 24.0F, 0.0F);
		mag.cubeList.add(new ModelBox(mag, 0, 0, -2.0F, -14.0F, -21.0F, 4, 12, 9, 0.0F, false));

		trigger = new ModelRenderer(this);
		trigger.setRotationPoint(0.0F, 24.0F, 0.0F);
		setRotationAngle(trigger, -0.1745F, 0.0F, 0.0F);
		trigger.cubeList.add(new ModelBox(trigger, 0, 0, -1.0F, -13.5209F, -7.0456F, 2, 2, 1, 0.0F, false));

		handle = new ModelRenderer(this);
		handle.setRotationPoint(0.0F, 24.0F, 0.0F);
		setRotationAngle(handle, 0.2618F, 0.0F, 0.0F);
		handle.cubeList.add(new ModelBox(handle, 0, 0, -3.0F, -14.0F, 3.0F, 6, 14, 6, 0.0F, false));
		handle.cubeList.add(new ModelBox(handle, 0, 0, -3.5F, 0.0F, 2.5F, 7, 1, 7, 0.0F, false));

		stock = new ModelRenderer(this);
		stock.setRotationPoint(0.0F, 24.0F, 0.0F);
		setRotationAngle(stock, -1.1345F, 0.0F, 0.0F);
		stock.cubeList.add(new ModelBox(stock, 0, 0, -3.0F, -36.0F, -6.0F, 6, 2, 13, 0.0F, false));
		stock.cubeList.add(new ModelBox(stock, 0, 0, -4.0F, -39.0F, -4.0F, 8, 2, 5, 0.0F, false));
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
			
			renderMK14(aim, stack);
		}
	}
	
	private void renderMK14(boolean aim, ItemStack stack)
	{
		//WEAPON
		GlStateManager.pushMatrix();
		{
			transform.defaultSRTransform();
			GlStateManager.translate(-1.0, 1.0, 0.0);
			
			if(aim && enableADS(stack))
			{
				rotateModelForADSRendering();
				GlStateManager.translate(27.6, -12.2, 21.0);
				
				if(hasRedDot(stack))
					GlStateManager.translate(0.0, 3.0, 0.0);
				else if(hasHoloSight(stack))
					GlStateManager.translate(0, 5.7, 0);
			}
			
			renderParts(hasScopeAtachment(stack));
		}
		GlStateManager.popMatrix();
		
		//ATTACHMENTS
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
			renderRedDot(28.3, -17, 20, 1.3f, stack);
		}
		else renderRedDot(-1.5, -7.4, 3, 1.3f, stack);
	}
	
	private void renderHolo(boolean aim, ItemStack stack)
	{
		if(aim)
		{
			renderHolo(22.45, -13.6, 11, 1.3f, stack);
		}
		else renderHolo(-2.4, -7.8, -4, 1.3f, stack);
	}
	
	private void render2x(ItemStack stack)
	{
		renderScope2X(6.9, 10, -11, 1f, stack);
	}
	
	private void render4x(ItemStack stack)
	{
		renderScope4X(6.9, 10, -11, 1f, stack);
	}
	
	private void render8x(ItemStack stack)
	{
		renderScope8X(-1, -1, -8, 1f, stack);
	}
	
	private void render15x(ItemStack stack)
	{
		renderScope15X(-1, -1, -8, 1f, stack);
	}
	
	private void renderSilencer(boolean aim, ItemStack stack)
	{
		if(aim && enableADS(stack))
		{
			if(hasRedDot(stack)) {
				renderSniperSilencer(-16, 6.3, 5, 1f, stack);
			}
			
			else if(hasHoloSight(stack)) {
				renderSniperSilencer(-16, 4.2, 5, 1f, stack);
			}
			
			else renderSniperSilencer(-16, 8, 5, 1f, stack);
		}
		
		else renderSniperSilencer(0.5, 1, -6, 1f, stack);
	}
	
	private void renderParts(boolean hasScope)
	{
		bone.render(1f);
		rail.render(1f);
		mag.render(1f);
		trigger.render(1f);
		handle.render(1f);
		stock.render(1f);
		if(!hasScope) irns.render(1f);
	}
}

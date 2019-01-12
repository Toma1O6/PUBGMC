package com.toma.pubgmc.client.models.weapons;

import com.toma.pubgmc.client.models.ModelGun;
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
			boolean aim = player.getCapability(PlayerDataProvider.PLAYER_DATA, null).isAiming();
			
			GlStateManager.pushMatrix();
			{
				animation_held.runAnimation(player.isSprinting());
				renderQBU(aim, stack);
			}
			GlStateManager.popMatrix();
		}
	}
	
	private void renderQBU(boolean aim, ItemStack stack)
	{
		GlStateManager.pushMatrix();
		transform.defaultSRTransform();
		GlStateManager.translate(0.0, 7.0, 1.0);
		
		if(aim && enableADS(stack))
		{
			GlStateManager.rotate(0.2f, 0, 1f, 0);
			GlStateManager.rotate(0.5f, 1f, 0, 0);
			GlStateManager.translate(26.6, -14.4, 15.0);
			
			if(hasRedDot(stack)) {
				GlStateManager.translate(0.0, 2.8, 0.0);
			}
			else if(hasHoloSight(stack)) {
				GlStateManager.translate(0.0, 5.0, 0.0);
			}
		}
		
		renderParts(hasScopeAtachment(stack));
		GlStateManager.popMatrix();
		//attachments
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
		if(aim) {
			renderRedDot(28.25, -17.1, 13, 1.3f, stack);
		}
		
		else renderRedDot(-0.4, -4.8, 2, 1.3f, stack);
	}
	
	private void renderHolo(boolean aim, ItemStack stack)
	{
		if(aim)
		{
			renderHolo(22.375, -13.9, 8, 1.3f, stack);
		}
		else renderHolo(-1.6, -5.4, -1, 1.3f, stack);
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
	
	private void renderSilencer(boolean aim, ItemStack stack)
	{
		if(aim && enableADS(stack))
		{
			if(hasRedDot(stack)) {
				renderSniperSilencer(-16, 5.6, 5, 1f, stack);
			}
			
			else if(hasHoloSight(stack)) {
				renderSniperSilencer(-16, 4.4, 5, 1f, stack);
			}
			
			else renderSniperSilencer(-16, 7.2, 5, 1f, stack);
		}
		else renderSniperSilencer(0, -1.1, -5, 1f, stack);
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

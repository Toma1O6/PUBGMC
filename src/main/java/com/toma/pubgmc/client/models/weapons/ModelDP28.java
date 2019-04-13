package com.toma.pubgmc.client.models.weapons;

import com.toma.pubgmc.client.models.ModelGun;
import com.toma.pubgmc.common.capability.IPlayerData;
import com.toma.pubgmc.common.capability.IPlayerData.PlayerDataProvider;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.item.ItemStack;

public class ModelDP28 extends ModelGun
{
	private final ModelRenderer base;
	private final ModelRenderer mag1;
	private final ModelRenderer mag2;
	private final ModelRenderer rail;
	private final ModelRenderer stock;
	private final ModelRenderer trigger;
	private final ModelRenderer ir;

	public ModelDP28()
	{
		textureWidth = 128;
		textureHeight = 128;

		base = new ModelRenderer(this);
		base.setRotationPoint(0.0F, 24.0F, 0.0F);
		base.cubeList.add(new ModelBox(base, 0, 0, -3.0F, -21.0F, -39.0F, 6, 3, 40, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -3.0F, -24.0F, -9.0F, 6, 3, 21, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -1.0F, -22.0F, -64.0F, 2, 2, 25, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -1.5F, -22.5F, -70.0F, 3, 3, 6, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -3.0F, -23.0F, -39.0F, 6, 2, 6, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -3.0F, -21.0F, 1.0F, 6, 3, 11, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -4.0F, -17.0F, -45.0F, 1, 1, 18, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, 3.0F, -17.0F, -45.0F, 1, 1, 18, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -3.0F, -18.0F, -29.0F, 6, 2, 25, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, 2.5F, -17.5F, -47.0F, 2, 2, 2, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -4.5F, -17.5F, -47.0F, 2, 2, 2, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -0.5F, -25.5F, -38.0F, 1, 3, 2, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -4.0F, -23.5F, -33.0F, 8, 3, 24, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -12.0F, -23.5F, -25.0F, 24, 3, 8, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -1.0F, -24.0F, -22.0F, 2, 1, 2, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -11.0F, -24.0F, -22.0F, 2, 1, 2, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, 9.0F, -24.0F, -22.0F, 2, 1, 2, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -1.0F, -24.0F, -12.0F, 2, 1, 2, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -1.0F, -24.0F, -32.0F, 2, 1, 2, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -3.0F, -23.0F, 12.0F, 6, 5, 2, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 64, -2.5F, -23.0F, 14.0F, 5, 5, 24, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 64, -2.5F, -23.0F, 38.0F, 5, 14, 17, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 64, -2.5F, -18.1F, 34.0F, 5, 7, 4, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 64, -2.5F, -18.1F, 31.0F, 5, 6, 3, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 64, -2.5F, -18.1F, 28.0F, 5, 5, 3, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 64, -2.5F, -18.1F, 25.0F, 5, 4, 3, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 64, -2.5F, -18.1F, 23.0F, 5, 3, 3, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 64, -2.5F, -18.1F, 19.0F, 5, 2, 4, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -3.0F, -18.0F, 11.0F, 6, 3, 1, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -3.0F, -16.0F, 5.0F, 6, 1, 1, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -3.0F, -15.0F, 6.0F, 6, 1, 5, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -3.0F, -18.0F, 4.0F, 6, 2, 1, 0.0F, false));

		mag1 = new ModelRenderer(this);
		mag1.setRotationPoint(0.0F, 24.0F, 0.0F);
		setRotationAngle(mag1, 0.0F, -0.7854F, 0.0F);
		mag1.cubeList.add(new ModelBox(mag1, 0, 0, -26.0F, -23.5F, -20.3F, 7, 3, 11, 0.0F, false));
		mag1.cubeList.add(new ModelBox(mag1, 0, 0, -10.6F, -23.5F, -20.3F, 7, 3, 11, 0.0F, false));

		mag2 = new ModelRenderer(this);
		mag2.setRotationPoint(0.0F, 24.0F, 0.0F);
		setRotationAngle(mag2, 0.0F, 0.7854F, 0.0F);
		mag2.cubeList.add(new ModelBox(mag2, 0, 0, 3.4F, -23.5F, -20.3F, 7, 3, 11, 0.0F, false));
		mag2.cubeList.add(new ModelBox(mag2, 0, 0, 19.0F, -23.5F, -20.3F, 7, 3, 11, 0.0F, false));

		rail = new ModelRenderer(this);
		rail.setRotationPoint(0.0F, 24.0F, 0.0F);
		rail.cubeList.add(new ModelBox(rail, 0, 0, -2.5F, -24.5F, -9.0F, 5, 1, 21, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 0, -2.5F, -25.0F, -8.0F, 5, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 0, -2.5F, -25.0F, -6.0F, 5, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 0, -2.5F, -25.0F, -4.0F, 5, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 0, -2.5F, -25.0F, -2.0F, 5, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 0, -2.5F, -25.0F, 0.0F, 5, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 0, -2.5F, -25.0F, 2.0F, 5, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 0, -2.5F, -25.0F, 4.0F, 5, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 0, -2.5F, -25.0F, 6.0F, 5, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 0, -2.5F, -25.0F, 8.0F, 5, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 0, -2.5F, -25.0F, 10.0F, 5, 1, 1, 0.0F, false));

		stock = new ModelRenderer(this);
		stock.setRotationPoint(0.0F, 24.0F, 0.0F);
		setRotationAngle(stock, -0.3491F, 0.0F, 0.0F);
		stock.cubeList.add(new ModelBox(stock, 0, 64, -2.5F, -23.6F, 6.0F, 5, 2, 27, 0.0F, false));

		trigger = new ModelRenderer(this);
		trigger.setRotationPoint(0.0F, 24.0F, 0.0F);
		setRotationAngle(trigger, -0.0873F, 0.0F, 0.0F);
		trigger.cubeList.add(new ModelBox(trigger, 0, 0, -1.0F, -19.0F, 7.0F, 2, 2, 1, 0.0F, false));

		ir = new ModelRenderer(this);
		ir.setRotationPoint(0.0F, 24.0F, 0.0F);
		ir.cubeList.add(new ModelBox(ir, 0, 0, -2.0F, -25.0F, 7.0F, 4, 1, 4, 0.0F, false));
		ir.cubeList.add(new ModelBox(ir, 0, 0, -1.75F, -26.0F, 8.0F, 1, 1, 2, 0.0F, false));
		ir.cubeList.add(new ModelBox(ir, 0, 0, 0.75F, -26.0F, 8.0F, 1, 1, 2, 0.0F, false));
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
				renderDP28(data.isAiming(), stack);
			}
			GlStateManager.popMatrix();
		}
	}
	
	private void renderDP28(boolean aim, ItemStack stack)
	{
		GlStateManager.pushMatrix();
		transform.defaultARTransform();
		GlStateManager.translate(0.0, 5.0, -5.0);
		
		if(aim && enableADS(stack))
		{
			rotateModelForADSRendering();
		}
		
		renderParts(hasScopeAtachment(stack));
		GlStateManager.popMatrix();
		
		renderRedDot(stack);
		renderHolo(stack);
		render2x(stack);
		render4x(stack);
	}
	
	private void renderRedDot(ItemStack stack)
	{
		renderRedDot(-0.43, -4.1, 5, 1.3f, stack);
	}
	
	private void renderHolo(ItemStack stack)
	{
		renderHolo(-1.5, -5.3, -1, 1.3f, stack);
	}
	
	private void render2x(ItemStack stack)
	{
		renderScope2X(8, 12.8, -5, 1f, stack);
	}
	
	private void render4x(ItemStack stack)
	{
		renderScope4X(8, 12.8, -4, 1f, stack);
	}
	
	private void renderParts(boolean hasScope)
	{
		base.render(1f);
		mag1.render(1f);
		mag2.render(1f);
		rail.render(1f);
		stock.render(1f);
		trigger.render(1f);
		if(!hasScope) ir.render(1f);
	}
}

package com.toma.pubgmc.client.models.weapons;

import com.toma.pubgmc.client.models.ModelGun;
import com.toma.pubgmc.common.capability.IPlayerData.PlayerDataProvider;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.item.ItemStack;

public class ModelM249 extends ModelGun
{
	private final ModelRenderer base;
	private final ModelRenderer mag;
	private final ModelRenderer mag2;
	private final ModelRenderer mag3;
	private final ModelRenderer trigger;
	private final ModelRenderer handle;
	private final ModelRenderer stock;
	private final ModelRenderer handle2;
	private final ModelRenderer rail;
	private final ModelRenderer ir;

	public ModelM249()
	{
		textureWidth = 128;
		textureHeight = 128;

		base = new ModelRenderer(this);
		base.setRotationPoint(0.0F, 24.0F, 0.0F);
		base.cubeList.add(new ModelBox(base, 0, 0, -4.0F, -24.0F, -49.0F, 8, 10, 28, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -1.0F, -22.0F, -68.0F, 2, 2, 19, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -1.5F, -22.5F, -73.0F, 3, 3, 5, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -3.0F, -25.0F, -48.0F, 6, 1, 44, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, 2.0F, -16.0F, -55.0F, 2, 2, 6, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -4.0F, -16.0F, -55.0F, 2, 2, 6, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, 1.5F, -16.5F, -57.0F, 3, 3, 2, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -4.5F, -16.5F, -57.0F, 3, 3, 2, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -3.0F, -24.0F, -21.0F, 6, 5, 17, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -3.0F, -25.0F, -4.0F, 6, 9, 24, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -3.0F, -16.0F, -4.0F, 6, 3, 1, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -3.0F, -13.0F, -3.0F, 6, 1, 1, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -3.0F, -12.0F, -2.0F, 6, 1, 8, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -3.0F, -16.0F, 6.0F, 6, 4, 1, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -2.0F, -22.0F, 20.0F, 4, 8, 34, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -3.0F, -16.0F, 14.0F, 6, 3, 6, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -2.0F, -23.0F, 52.0F, 4, 20, 3, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -2.0F, -14.0F, 41.0F, 4, 5, 13, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -2.0F, -14.0F, 36.0F, 4, 2, 5, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -2.5F, -26.0F, -43.0F, 5, 1, 62, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -2.5F, -26.0F, -48.0F, 5, 1, 5, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -8.5F, -33.0F, -49.0F, 2, 2, 10, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -0.5F, -28.0F, -52.0F, 1, 6, 1, 0.0F, false));

		mag = new ModelRenderer(this);
		mag.setRotationPoint(0.0F, 24.0F, 0.0F);
		mag.cubeList.add(new ModelBox(mag, 0, 64, -6.0F, -19.0F, -21.0F, 12, 18, 17, 0.0F, false));
		mag.cubeList.add(new ModelBox(mag, 0, 64, 6.0F, -10.0F, -21.0F, 4, 9, 17, 0.0F, false));
		mag.cubeList.add(new ModelBox(mag, 0, 64, -10.0F, -10.0F, -21.0F, 4, 9, 17, 0.0F, false));

		mag2 = new ModelRenderer(this);
		mag2.setRotationPoint(0.0F, 24.0F, 0.0F);
		setRotationAngle(mag2, 0.0F, 0.0F, -0.4363F);
		mag2.cubeList.add(new ModelBox(mag2, 0, 64, 7.3F, -14.8F, -21.0F, 6, 10, 17, 0.0F, false));

		mag3 = new ModelRenderer(this);
		mag3.setRotationPoint(0.0F, 24.0F, 0.0F);
		setRotationAngle(mag3, 0.0F, 0.0F, 0.4363F);
		mag3.cubeList.add(new ModelBox(mag3, 0, 64, -13.3F, -14.8F, -21.0F, 6, 10, 17, 0.0F, false));

		trigger = new ModelRenderer(this);
		trigger.setRotationPoint(0.0F, 24.0F, 0.0F);
		setRotationAngle(trigger, -0.1745F, 0.0F, 0.0F);
		trigger.cubeList.add(new ModelBox(trigger, 0, 0, -1.0F, -18.0F, 0.0F, 2, 4, 1, 0.0F, false));

		handle = new ModelRenderer(this);
		handle.setRotationPoint(0.0F, 24.0F, 0.0F);
		setRotationAngle(handle, 0.1745F, 0.0F, 0.0F);
		handle.cubeList.add(new ModelBox(handle, 0, 0, -3.0F, -16.0F, 10.0F, 6, 16, 7, 0.0F, false));

		stock = new ModelRenderer(this);
		stock.setRotationPoint(0.0F, 24.0F, 0.0F);
		setRotationAngle(stock, -0.2618F, 0.0F, 0.0F);
		stock.cubeList.add(new ModelBox(stock, 0, 0, -2.0F, -23.0F, 14.0F, 4, 5, 36, 0.0F, false));

		handle2 = new ModelRenderer(this);
		handle2.setRotationPoint(0.0F, 24.0F, 0.0F);
		setRotationAngle(handle2, 0.3491F, 0.0F, -0.3491F);
		handle2.cubeList.add(new ModelBox(handle2, 0, 0, 4.0F, -47.0F, -35.0F, 1, 11, 1, 0.0F, false));

		rail = new ModelRenderer(this);
		rail.setRotationPoint(0.0F, 24.0F, 0.0F);
		rail.cubeList.add(new ModelBox(rail, 0, 0, -2.0F, -26.4F, 17.0F, 4, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 0, -2.0F, -26.4F, 15.0F, 4, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 0, -2.0F, -26.4F, 13.0F, 4, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 0, -2.0F, -26.4F, 11.0F, 4, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 0, -2.0F, -26.4F, 9.0F, 4, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 0, -2.0F, -26.4F, 7.0F, 4, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 0, -2.0F, -26.4F, 5.0F, 4, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 0, -2.0F, -26.4F, 3.0F, 4, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 0, -2.0F, -26.4F, 1.0F, 4, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 0, -2.0F, -26.4F, -1.0F, 4, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 0, -2.0F, -26.4F, -3.0F, 4, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 0, -2.0F, -26.4F, -5.0F, 4, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 0, -2.0F, -26.4F, -7.0F, 4, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 0, -2.0F, -26.4F, -31.0F, 4, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 0, -2.0F, -26.4F, -29.0F, 4, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 0, -2.0F, -26.4F, -25.0F, 4, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 0, -2.0F, -26.4F, -27.0F, 4, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 0, -2.0F, -26.4F, -23.0F, 4, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 0, -2.0F, -26.4F, -21.0F, 4, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 0, -2.0F, -26.4F, -19.0F, 4, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 0, -2.0F, -26.4F, -17.0F, 4, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 0, -2.0F, -26.4F, -15.0F, 4, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 0, -2.0F, -26.4F, -13.0F, 4, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 0, -2.0F, -26.4F, -11.0F, 4, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 0, -2.0F, -26.4F, -9.0F, 4, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 0, -2.0F, -26.4F, -33.0F, 4, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 0, -2.0F, -26.4F, -47.0F, 4, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 0, -2.0F, -26.4F, -45.0F, 4, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 0, -2.0F, -26.4F, -43.0F, 4, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 0, -2.0F, -26.4F, -41.0F, 4, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 0, -2.0F, -26.4F, -39.0F, 4, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 0, -2.0F, -26.4F, -37.0F, 4, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 0, -2.0F, -26.4F, -35.0F, 4, 1, 1, 0.0F, false));

		ir = new ModelRenderer(this);
		ir.setRotationPoint(0.0F, 24.0F, 0.0F);
		ir.cubeList.add(new ModelBox(ir, 0, 0, -2.0F, -26.5F, 11.0F, 4, 1, 7, 0.0F, false));
		ir.cubeList.add(new ModelBox(ir, 0, 0, -2.0F, -28.5F, 13.0F, 1, 2, 3, 0.0F, false));
		ir.cubeList.add(new ModelBox(ir, 0, 0, 1.0F, -28.5F, 13.0F, 1, 2, 3, 0.0F, false));
		ir.cubeList.add(new ModelBox(ir, 0, 0, 1.0F, -27.5F, 12.0F, 1, 1, 5, 0.0F, false));
		ir.cubeList.add(new ModelBox(ir, 0, 0, -2.0F, -27.5F, 12.0F, 1, 1, 5, 0.0F, false));
		ir.cubeList.add(new ModelBox(ir, 0, 0, -0.5F, -27.0F, 14.0F, 1, 1, 1, 0.0F, false));
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
			
			renderM249(aim, stack);
			renderRedDot(aim, stack);
			renderHolo(aim, stack);
			render2x(stack);
			render4x(stack);
		}
	}
	
	private void renderM249(boolean aim, ItemStack stack)
	{
		GlStateManager.pushMatrix();
		transform.defaultARTransform();
		GlStateManager.translate(0.0, 4.0, -2.0);
		
		if(aim && enableADS(stack))
		{
			GlStateManager.translate(26.6, -12.8, 0.0);
			if(hasRedDot(stack))
			{
				GlStateManager.translate(0.0, 3.4, 0.0);
			}
			
			else if(hasHoloSight(stack))
			{
				GlStateManager.translate(0, 5.4, 0);
			}
		}
		
		renderParts(hasScopeAtachment(stack));
		GlStateManager.popMatrix();
	}
	
	private void renderRedDot(boolean aim, ItemStack stack)
	{
		if(aim)
		{
			renderRedDot(28.3, -17, 13, 1.3f, stack);
		}
		
		else renderRedDot(-0.4, -7, 5, 1.3f, stack);
	}
	
	private void renderHolo(boolean aim, ItemStack stack)
	{
		if(aim)
		{
			renderHolo(22.4, -14, 8, 1.3f, stack);
		}
		else renderHolo(-1.5, -7.3, 0, 1.3f, stack);
	}
	
	private void render2x(ItemStack stack)
	{
		renderScope2X(8, 10.2, -3, 1f, stack);
	}
	
	private void render4x(ItemStack stack)
	{
		renderScope4X(8, 10.2, -3, 1f, stack);
	}
	
	private void renderParts(boolean hasScope)
	{
		base.render(1f);
		mag.render(1f);
		mag2.render(1f);
		mag3.render(1f);
		trigger.render(1f);
		handle.render(1f);
		stock.render(1f);
		handle2.render(1f);
		rail.render(1f);
		
		if(!hasScope) ir.render(1f);
	}
}

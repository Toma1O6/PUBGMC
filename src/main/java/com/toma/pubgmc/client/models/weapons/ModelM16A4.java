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

public class ModelM16A4 extends ModelGun
{
	private final ModelRenderer base;
	private final ModelRenderer ir_base;
	private final ModelRenderer ir_base1;
	private final ModelRenderer deco;
	private final ModelRenderer rail;
	private final ModelRenderer mag;
	private final ModelRenderer trigger;
	private final ModelRenderer handle;
	private final ModelRenderer stock;
	private final ModelRenderer iron_sight;

	public ModelM16A4()
	{
		initAimAnimation(-0.56f, 0.195f, 0.215f);
		initAimingAnimationStates(0.195f, 0.18f, 0.145f);
		
		textureWidth = 128;
		textureHeight = 128;

		base = new ModelRenderer(this);
		base.setRotationPoint(0.0F, 24.0F, 0.0F);
		base.cubeList.add(new ModelBox(base, 0, 0, -3.0F, -22.0F, -15.0F, 6, 6, 25, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -3.5F, -22.5F, -37.0F, 7, 7, 23, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -1.5F, -20.5F, -53.0F, 3, 3, 16, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -2.0F, -21.0F, -50.0F, 4, 4, 2, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -3.0F, -16.0F, -13.0F, 6, 4, 23, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -3.0F, -20.0F, 10.0F, 6, 6, 26, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -3.0F, -12.0F, -13.0F, 6, 2, 10, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -3.0F, -10.0F, -3.0F, 6, 1, 1, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -3.0F, -9.0F, -2.0F, 6, 1, 5, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -3.0F, -12.0F, 3.0F, 6, 3, 1, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -3.0F, -15.0F, 14.0F, 6, 2, 22, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -3.0F, -14.0F, 29.0F, 6, 7, 7, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -3.0F, -11.0F, 33.0F, 6, 6, 3, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -3.0F, -5.0F, 34.0F, 6, 1, 2, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -3.0F, -14.0F, 25.0F, 6, 6, 4, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -3.0F, -13.0F, 21.0F, 6, 3, 4, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -3.0F, -14.0F, 17.0F, 6, 3, 4, 0.0F, false));

		ir_base = new ModelRenderer(this);
		ir_base.setRotationPoint(0.0F, 24.0F, 0.0F);
		ir_base.cubeList.add(new ModelBox(ir_base, 0, 0, -0.5F, -27.0F, -47.0F, 1, 7, 1, 0.0F, false));

		ir_base1 = new ModelRenderer(this);
		ir_base1.setRotationPoint(0.0F, 24.0F, 0.0F);
		setRotationAngle(ir_base1, 0.6981F, 0.0F, 0.0F);
		ir_base1.cubeList.add(new ModelBox(ir_base1, 0, 0, -0.5F, -50.8F, -18.9F, 1, 9, 1, 0.0F, false));

		deco = new ModelRenderer(this);
		deco.setRotationPoint(0.0F, 24.0F, 0.0F);
		deco.cubeList.add(new ModelBox(deco, 0, 0, -4.0F, -23.0F, -16.0F, 8, 8, 1, 0.0F, false));
		deco.cubeList.add(new ModelBox(deco, 0, 0, -4.0F, -23.0F, -18.0F, 8, 8, 1, 0.0F, false));
		deco.cubeList.add(new ModelBox(deco, 0, 0, -4.0F, -23.0F, -20.0F, 8, 8, 1, 0.0F, false));
		deco.cubeList.add(new ModelBox(deco, 0, 0, -4.0F, -23.0F, -22.0F, 8, 8, 1, 0.0F, false));
		deco.cubeList.add(new ModelBox(deco, 0, 0, -4.0F, -23.0F, -24.0F, 8, 8, 1, 0.0F, false));
		deco.cubeList.add(new ModelBox(deco, 0, 0, -4.0F, -23.0F, -26.0F, 8, 8, 1, 0.0F, false));
		deco.cubeList.add(new ModelBox(deco, 0, 0, -4.0F, -23.0F, -28.0F, 8, 8, 1, 0.0F, false));
		deco.cubeList.add(new ModelBox(deco, 0, 0, -4.0F, -23.0F, -30.0F, 8, 8, 1, 0.0F, false));
		deco.cubeList.add(new ModelBox(deco, 0, 0, -4.0F, -23.0F, -32.0F, 8, 8, 1, 0.0F, false));
		deco.cubeList.add(new ModelBox(deco, 0, 0, -4.0F, -23.0F, -34.0F, 8, 8, 1, 0.0F, false));
		deco.cubeList.add(new ModelBox(deco, 0, 0, -4.0F, -23.0F, -36.0F, 8, 8, 1, 0.0F, false));

		rail = new ModelRenderer(this);
		rail.setRotationPoint(0.0F, 24.0F, 0.0F);
		rail.cubeList.add(new ModelBox(rail, 0, 0, -2.5F, -23.0F, -12.0F, 5, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 0, -2.5F, -23.0F, -10.0F, 5, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 0, -2.5F, -23.0F, -8.0F, 5, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 0, -2.5F, -23.0F, -6.0F, 5, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 0, -2.5F, -23.0F, -4.0F, 5, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 0, -2.5F, -23.0F, -2.0F, 5, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 0, -2.5F, -23.0F, 0.0F, 5, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 0, -2.5F, -23.0F, 2.0F, 5, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 0, -2.5F, -22.4F, -13.0F, 5, 1, 21, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 0, -2.5F, -23.0F, 4.0F, 5, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 0, -2.5F, -23.0F, 6.0F, 5, 1, 1, 0.0F, false));

		mag = new ModelRenderer(this);
		mag.setRotationPoint(0.0F, 24.0F, 0.0F);
		setRotationAngle(mag, -0.2618F, 0.0F, 0.0F);
		mag.cubeList.add(new ModelBox(mag, 0, 0, -2.0F, -12.0F, -14.5F, 4, 19, 8, 0.0F, false));

		trigger = new ModelRenderer(this);
		trigger.setRotationPoint(0.0F, 24.0F, 0.0F);
		setRotationAngle(trigger, -0.2618F, 0.0F, 0.0F);
		trigger.cubeList.add(new ModelBox(trigger, 0, 0, 0.0F, -13.8F, -3.0F, 2, 4, 1, 0.0F, false));

		handle = new ModelRenderer(this);
		handle.setRotationPoint(0.0F, 24.0F, 0.0F);
		setRotationAngle(handle, 0.2618F, 0.0F, 0.0F);
		handle.cubeList.add(new ModelBox(handle, 0, 0, -3.0F, -11.0F, 6.0F, 6, 12, 5, 0.0F, false));

		stock = new ModelRenderer(this);
		stock.setRotationPoint(0.0F, 24.0F, 0.0F);
		setRotationAngle(stock, -0.3491F, 0.0F, 0.0F);
		stock.cubeList.add(new ModelBox(stock, 0, 0, -3.0F, -18.0F, 3.0F, 6, 2, 28, 0.0F, false));

		iron_sight = new ModelRenderer(this);
		iron_sight.setRotationPoint(0.0F, 24.0F, 0.0F);
		iron_sight.cubeList.add(new ModelBox(iron_sight, 0, 0, -2.5F, -25.0F, 5.0F, 5, 3, 3, 0.0F, false));
		iron_sight.cubeList.add(new ModelBox(iron_sight, 0, 0, -2.5F, -25.0F, -11.0F, 5, 3, 2, 0.0F, false));
		iron_sight.cubeList.add(new ModelBox(iron_sight, 0, 0, -2.5F, -26.0F, -10.0F, 5, 1, 18, 0.0F, false));
		iron_sight.cubeList.add(new ModelBox(iron_sight, 0, 0, -2.5F, -27.0F, -9.0F, 1, 1, 17, 0.0F, false));
		iron_sight.cubeList.add(new ModelBox(iron_sight, 0, 0, 1.5F, -27.0F, -9.0F, 1, 1, 17, 0.0F, false));
		iron_sight.cubeList.add(new ModelBox(iron_sight, 0, 0, -0.5F, -26.5F, 4.0F, 1, 1, 3, 0.0F, false));
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
			super.preRender(stack);
			IPlayerData data = player.getCapability(PlayerDataProvider.PLAYER_DATA, null);
			boolean aim = data.isAiming();
			
			GlStateManager.pushMatrix();
			{
				renderM16A4(aim, stack);
			}
			GlStateManager.popMatrix();
		}
	}
	
	private void renderM16A4(boolean aim, ItemStack stack)
	{
		GlStateManager.pushMatrix();
		transform.defaultARTransform();
		
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
		renderARSilencer(0, 0, 0, 1f, stack);
	}
	
	private void renderRedDot(ItemStack stack)
	{
		renderRedDot(-0.4, -7.9, 6, 1.3f, stack);
	}
	
	private void renderHolo(ItemStack stack)
	{
		renderHolo(-1.6, -6.8, 0, 1.2f, stack);
	}
	
	private void render2X(ItemStack stack)
	{
		renderScope2X(7.8, 9.7, -3, 1f, stack);
	}
	
	private void render4X(ItemStack stack)
	{
		renderScope4X(8, 10, -1, 1f, stack);
	}
	
	private void renderParts(boolean ir)
	{
		base.render(1f);
		ir_base.render(1f);
		ir_base1.render(1f);
		deco.render(1f);
		rail.render(1f);
		mag.render(1f);
		trigger.render(1f);
		handle.render(1f);
		stock.render(1f);
		
		if(!ir)
		{
			iron_sight.render(1f);
		}
	}
}

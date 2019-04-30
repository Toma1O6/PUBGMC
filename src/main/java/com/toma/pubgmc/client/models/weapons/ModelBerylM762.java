package com.toma.pubgmc.client.models.weapons;

import com.toma.pubgmc.animation.IPartAnimated.MagazineMovementStyle;
import com.toma.pubgmc.animation.ReloadAnimation;
import com.toma.pubgmc.animation.ReloadAnimation.ReloadStyle;
import com.toma.pubgmc.client.models.ModelGun;
import com.toma.pubgmc.common.capability.IPlayerData;
import com.toma.pubgmc.common.capability.IPlayerData.PlayerDataProvider;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.item.ItemStack;

public class ModelBerylM762 extends ModelGun
{
	private final ModelRenderer base;
	private final ModelRenderer rails;
	private final ModelRenderer mag;
	private final ModelRenderer trigger;
	private final ModelRenderer handle;
	private final ModelRenderer stock;
	private final ModelRenderer ir;

	public ModelBerylM762()
	{
		super();
		
		textureWidth = 128;
		textureHeight = 128;

		base = new ModelRenderer(this);
		base.setRotationPoint(0.0F, 24.0F, 0.0F);
		base.cubeList.add(new ModelBox(base, 0, 0, -3.0F, -27.0F, -47.0F, 6, 7, 47, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -1.0F, -23.0F, -71.0F, 2, 2, 24, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -1.0F, -26.0F, -60.0F, 2, 2, 13, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -1.0F, -24.0F, -60.0F, 2, 1, 2, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -1.5F, -23.5F, -77.0F, 3, 3, 6, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -3.0F, -20.0F, -26.0F, 6, 2, 26, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 44, -2.5F, -18.0F, -25.5F, 5, 10, 9, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -3.0F, -18.0F, -17.0F, 6, 3, 1, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -3.0F, -14.0F, -15.0F, 6, 1, 8, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -3.0F, -15.0F, -16.0F, 6, 1, 1, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -3.0F, -25.0F, 0.0F, 6, 7, 4, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -2.0F, -23.0F, 4.0F, 4, 3, 24, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -3.0F, -24.0F, 27.0F, 6, 15, 2, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -2.0F, -20.0F, 24.0F, 4, 7, 3, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -0.5F, -29.0F, -67.0F, 1, 6, 1, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -0.5F, -27.0F, -66.0F, 1, 4, 1, 0.0F, false));

		rails = new ModelRenderer(this);
		rails.setRotationPoint(0.0F, 24.0F, 0.0F);
		rails.cubeList.add(new ModelBox(rails, 0, 0, -3.5F, -26.5F, -46.0F, 7, 7, 1, 0.0F, false));
		rails.cubeList.add(new ModelBox(rails, 0, 0, -3.5F, -26.5F, -44.0F, 7, 7, 1, 0.0F, false));
		rails.cubeList.add(new ModelBox(rails, 0, 0, -3.5F, -26.5F, -42.0F, 7, 7, 1, 0.0F, false));
		rails.cubeList.add(new ModelBox(rails, 0, 0, -3.5F, -26.5F, -40.0F, 7, 7, 1, 0.0F, false));
		rails.cubeList.add(new ModelBox(rails, 0, 0, -3.5F, -26.5F, -38.0F, 7, 7, 1, 0.0F, false));
		rails.cubeList.add(new ModelBox(rails, 0, 0, -3.5F, -26.5F, -36.0F, 7, 7, 1, 0.0F, false));
		rails.cubeList.add(new ModelBox(rails, 0, 0, -3.5F, -26.5F, -34.0F, 7, 7, 1, 0.0F, false));
		rails.cubeList.add(new ModelBox(rails, 0, 0, -3.5F, -26.5F, -32.0F, 7, 7, 1, 0.0F, false));
		rails.cubeList.add(new ModelBox(rails, 0, 0, -3.5F, -26.5F, -30.0F, 7, 7, 1, 0.0F, false));
		rails.cubeList.add(new ModelBox(rails, 0, 0, -3.5F, -26.5F, -28.0F, 7, 7, 1, 0.0F, false));
		rails.cubeList.add(new ModelBox(rails, 0, 0, -2.5F, -27.5F, -47.0F, 5, 1, 46, 0.0F, false));
		rails.cubeList.add(new ModelBox(rails, 0, 0, -2.5F, -28.0F, -46.0F, 5, 1, 1, 0.0F, false));
		rails.cubeList.add(new ModelBox(rails, 0, 0, -2.5F, -28.0F, -44.0F, 5, 1, 1, 0.0F, false));
		rails.cubeList.add(new ModelBox(rails, 0, 0, -2.5F, -28.0F, -42.0F, 5, 1, 1, 0.0F, false));
		rails.cubeList.add(new ModelBox(rails, 0, 0, -2.5F, -28.0F, -40.0F, 5, 1, 1, 0.0F, false));
		rails.cubeList.add(new ModelBox(rails, 0, 0, -2.5F, -28.0F, -34.0F, 5, 1, 1, 0.0F, false));
		rails.cubeList.add(new ModelBox(rails, 0, 0, -2.5F, -28.0F, -36.0F, 5, 1, 1, 0.0F, false));
		rails.cubeList.add(new ModelBox(rails, 0, 0, -2.5F, -28.0F, -38.0F, 5, 1, 1, 0.0F, false));
		rails.cubeList.add(new ModelBox(rails, 0, 0, -2.5F, -28.0F, -32.0F, 5, 1, 1, 0.0F, false));
		rails.cubeList.add(new ModelBox(rails, 0, 0, -2.5F, -28.0F, -26.0F, 5, 1, 1, 0.0F, false));
		rails.cubeList.add(new ModelBox(rails, 0, 0, -2.5F, -28.0F, -28.0F, 5, 1, 1, 0.0F, false));
		rails.cubeList.add(new ModelBox(rails, 0, 0, -2.5F, -28.0F, -30.0F, 5, 1, 1, 0.0F, false));
		rails.cubeList.add(new ModelBox(rails, 0, 0, -2.5F, -28.0F, -24.0F, 5, 1, 1, 0.0F, false));
		rails.cubeList.add(new ModelBox(rails, 0, 0, -2.5F, -28.0F, -18.0F, 5, 1, 1, 0.0F, false));
		rails.cubeList.add(new ModelBox(rails, 0, 0, -2.5F, -28.0F, -20.0F, 5, 1, 1, 0.0F, false));
		rails.cubeList.add(new ModelBox(rails, 0, 0, -2.5F, -28.0F, -22.0F, 5, 1, 1, 0.0F, false));
		rails.cubeList.add(new ModelBox(rails, 0, 0, -2.5F, -28.0F, -16.0F, 5, 1, 1, 0.0F, false));
		rails.cubeList.add(new ModelBox(rails, 0, 0, -2.5F, -28.0F, -10.0F, 5, 1, 1, 0.0F, false));
		rails.cubeList.add(new ModelBox(rails, 0, 0, -2.5F, -28.0F, -12.0F, 5, 1, 1, 0.0F, false));
		rails.cubeList.add(new ModelBox(rails, 0, 0, -2.5F, -28.0F, -14.0F, 5, 1, 1, 0.0F, false));
		rails.cubeList.add(new ModelBox(rails, 0, 0, -2.5F, -28.0F, -8.0F, 5, 1, 1, 0.0F, false));
		rails.cubeList.add(new ModelBox(rails, 0, 0, -2.5F, -28.0F, -2.0F, 5, 1, 1, 0.0F, false));
		rails.cubeList.add(new ModelBox(rails, 0, 0, -2.5F, -28.0F, -4.0F, 5, 1, 1, 0.0F, false));
		rails.cubeList.add(new ModelBox(rails, 0, 0, -2.5F, -28.0F, -6.0F, 5, 1, 1, 0.0F, false));

		mag = new ModelRenderer(this);
		mag.setRotationPoint(0.0F, 24.0F, 0.0F);
		setRotationAngle(mag, -0.1745F, 0.0F, 0.0F);
		mag.cubeList.add(new ModelBox(mag, 0, 41, -2.5F, -6.0F, -26.8F, 5, 14, 9, 0.0F, false));

		trigger = new ModelRenderer(this);
		trigger.setRotationPoint(0.0F, 24.0F, 0.0F);
		setRotationAngle(trigger, -0.1745F, 0.0F, 0.0F);
		trigger.cubeList.add(new ModelBox(trigger, 0, 0, -1.0F, -17.0F, -14.0F, 2, 3, 1, 0.0F, false));

		handle = new ModelRenderer(this);
		handle.setRotationPoint(0.0F, 24.0F, 0.0F);
		setRotationAngle(handle, 0.1745F, 0.0F, 0.0F);
		handle.cubeList.add(new ModelBox(handle, 0, 0, -3.0F, -20.0F, -5.0F, 6, 15, 7, 0.0F, false));

		stock = new ModelRenderer(this);
		stock.setRotationPoint(0.0F, 24.0F, 0.0F);
		setRotationAngle(stock, -0.3491F, 0.0F, 0.0F);
		stock.cubeList.add(new ModelBox(stock, 0, 0, -2.0F, -22.0F, -3.5F, 4, 3, 26, 0.0F, false));
		stock.cubeList.add(new ModelBox(stock, 0, 0, -3.0F, -25.0F, -9.7F, 6, 3, 5, 0.0F, false));

		ir = new ModelRenderer(this);
		ir.setRotationPoint(0.0F, 24.0F, 0.0F);
		ir.cubeList.add(new ModelBox(ir, 0, 0, -1.5F, -28.0F, -5.0F, 3, 1, 4, 0.0F, false));
		ir.cubeList.add(new ModelBox(ir, 0, 0, -0.5F, -28.5F, -5.0F, 1, 1, 1, 0.0F, false));
		ir.cubeList.add(new ModelBox(ir, 0, 0, -2.0F, -29.0F, -5.0F, 1, 1, 4, 0.0F, false));
		ir.cubeList.add(new ModelBox(ir, 0, 0, 1.0F, -29.0F, -5.0F, 1, 1, 4, 0.0F, false));
		this.initAnimations();
	}
	
	@Override
	public void initAnimations() 
	{
		initAimAnimation(-0.56f, 0.26f, 0.18f);
		initAimingAnimationStates(0.26f, 0.175f, 0.135f);
		//reloadAnimation = new ReloadAnimation(mag, MagazineMovementStyle.DEFAULT, ReloadStyle.MAGAZINE);
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
			
			GlStateManager.pushMatrix();
			{
				renderM762(data.isAiming(), stack);
			}
			GlStateManager.popMatrix();
		}
	}
	
	private void renderM762(boolean aim, ItemStack stack)
	{
		GlStateManager.pushMatrix();
		transform.defaultARTransform();
		GlStateManager.translate(0.0, 5.0, 10.0);
		
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
		renderSilencer(stack);
		renderVerticalGrip(stack);
		renderAngledGrip(stack);
	}
	
	private void renderRedDot(ItemStack stack)
	{
		renderRedDot(-0.4, -8, 5, 1.3f, stack);
	}
	
	private void renderHolo(ItemStack stack)
	{
		renderHolo(-1.5, -8, 0, 1.3f, stack);
	}
	
	private void render2x(ItemStack stack)
	{
		renderScope2X(7.7, 10, -4, 1f, stack);
	}
	
	private void render4x(ItemStack stack)
	{
		renderScope4X(7.8, 9.8, -6, 1f, stack);
	}
	
	private void renderSilencer(ItemStack stack)
	{
		renderARSilencer(0, -1, -7, 1f, stack);	
	}
	
	private void renderVerticalGrip(ItemStack stack)
	{
		renderVerticalGrip(-1, 4, 0, 1f, stack);
	}
	
	private void renderAngledGrip(ItemStack stack)
	{
		renderAngledGrip(0.7, 3, -2, 1f, stack);	
	}
	
	private void renderParts(boolean hasScope)
	{
		base.render(1f);
		rails.render(1f);
		mag.render(1f);
		trigger.render(1f);
		handle.render(1f);
		stock.render(1f);
		if(!hasScope) ir.render(1f);
	}
}

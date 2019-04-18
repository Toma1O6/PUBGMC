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

public class ModelTommyGun extends ModelGun
{
	private final ModelRenderer base;
	private final ModelRenderer mag;
	private final ModelRenderer grip;
	private final ModelRenderer handle;
	private final ModelRenderer handle1;
	private final ModelRenderer stock;
	private final ModelRenderer stock1;

	public ModelTommyGun() 
	{
		super();
		
		textureWidth = 128;
		textureHeight = 128;

		base = new ModelRenderer(this);
		base.setRotationPoint(0.0F, 24.0F, 0.0F);
		base.cubeList.add(new ModelBox(base, 0, 0, -3.0F, -16.0F, -25.0F, 6, 5, 25, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -2.0F, -15.0F, -49.0F, 4, 4, 24, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -2.5F, -15.5F, -48.0F, 5, 5, 3, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -1.5F, -11.5F, -22.5F, 3, 3, 5, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 64, -2.5F, -11.5F, -37.0F, 5, 1, 8, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -1.5F, -11.5F, -17.5F, 3, 2, 5, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 64, -1.5F, -11.5F, -12.5F, 3, 2, 5, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -1.0F, -7.0F, -15.0F, 2, 1, 5, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -1.0F, -8.0F, -16.0F, 2, 1, 1, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -1.0F, -10.0F, -17.0F, 2, 2, 1, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 64, -2.0F, -11.0F, 16.0F, 4, 11, 5, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 62, -2.0F, -10.0F, 11.0F, 4, 6, 5, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 62, -2.0F, -12.0F, 6.0F, 4, 6, 5, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -0.5F, -17.0F, -44.0F, 1, 3, 1, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, 2.0F, -17.5F, -8.0F, 1, 2, 6, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -3.0F, -17.5F, -8.0F, 1, 2, 6, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -1.5F, -17.0F, -5.0F, 1, 2, 2, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, 0.5F, -17.0F, -5.0F, 1, 2, 2, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, 2.0F, -16.5F, -21.0F, 1, 1, 2, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -3.0F, -16.5F, -21.0F, 1, 1, 2, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -1.0F, -14.0F, -51.0F, 2, 2, 2, 0.0F, false));

		mag = new ModelRenderer(this);
		mag.setRotationPoint(0.0F, 24.0F, 0.0F);
		mag.cubeList.add(new ModelBox(mag, 0, 0, -1.0F, -8.5F, -22.0F, 2, 13, 4, 0.0F, false));

		grip = new ModelRenderer(this);
		grip.setRotationPoint(0.0F, 24.0F, 0.0F);
		setRotationAngle(grip, 0.3491F, 0.0F, 0.0F);
		grip.cubeList.add(new ModelBox(grip, 0, 64, -1.0F, -23.0F, -30.0F, 2, 9, 4, 0.0F, false));

		handle = new ModelRenderer(this);
		handle.setRotationPoint(0.0F, 24.0F, 0.0F);
		setRotationAngle(handle, -0.1745F, 0.0F, 0.0F);
		handle.cubeList.add(new ModelBox(handle, 0, 0, -1.0F, -10.5F, -15.0F, 2, 5, 1, 0.0F, false));
		
		handle1 = new ModelRenderer(this);
		handle1.setRotationPoint(0.0F, 24.0F, 0.0F);
		setRotationAngle(handle1, 0.3491F, 0.0F, 0.0F);
		handle1.cubeList.add(new ModelBox(handle1, 0, 64, -1.5F, -13.0F, -8.0F, 3, 8, 4, 0.0F, false));

		stock = new ModelRenderer(this);
		stock.setRotationPoint(0.0F, 24.0F, 0.0F);
		setRotationAngle(stock, -0.1745F, 0.0F, 0.0F);
		stock.cubeList.add(new ModelBox(stock, 0, 64, -2.0F, -14.0F, -3.0F, 4, 3, 19, 0.0F, false));

		stock1 = new ModelRenderer(this);
		stock1.setRotationPoint(0.0F, 24.0F, 0.0F);
		setRotationAngle(stock1, -0.5236F, 0.0F, 0.0F);
		stock1.cubeList.add(new ModelBox(stock1, 0, 64, -2.0F, -12.0F, -8.0F, 4, 4, 22, 0.0F, false));
	}
	
	@Override
	public void initAnimations()
	{
		initAimAnimation(-0.56f, 0.39f, 0.35f);
		initAimingAnimationStates(0.39f);
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
			boolean aiming = data.isAiming();
			
			GlStateManager.pushMatrix();
			renderTommyGun(aiming, stack);
			GlStateManager.popMatrix();
		}
	}
	
	private void renderTommyGun(boolean aim, ItemStack stack)
	{
		boolean grip = stack.hasTagCompound() && stack.getTagCompound().getInteger("grip") == 1;
		GlStateManager.pushMatrix();
		transform.defaultSMGTransform();
		
		if(aim && enableADS(stack))
		{
			rotateModelForADSRendering();
		}
		
		renderParts(grip);
		GlStateManager.popMatrix();
		
		renderSilencer(stack);
	}
	
	private void renderSilencer(ItemStack stack)
	{
		renderSMGSilencer(0, -6, -31, 1.1f, stack);
	}
	
	private void renderParts(boolean hasGrip)
	{
		base.render(1f);
		mag.render(1f);
		handle.render(1f);
		handle1.render(1f);
		stock.render(1f);
		stock1.render(1f);
		
		if(hasGrip)
		{
			grip.render(1f);
		}
	}
}

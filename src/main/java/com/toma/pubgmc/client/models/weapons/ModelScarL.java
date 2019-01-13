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

public class ModelScarL extends ModelGun
{
	private final ModelRenderer base;
	private final ModelRenderer rail;
	private final ModelRenderer mag;
	private final ModelRenderer handle;
	private final ModelRenderer trigger;
	private final ModelRenderer stock;
	private final ModelRenderer stock1;
	private final ModelRenderer irns;

	public ModelScarL() 
	{
		animation_aim = new AimAnimation(-0.56d, 0.245d, 0.225d, 1f);
		animation_aim.setInvertedCoords(true, false, false);
		animation_aim.setMovementMultiplier(1.3f, 1f, 1.25f);
		
		textureWidth = 128;
		textureHeight = 128;

		base = new ModelRenderer(this);
		base.setRotationPoint(0.0F, 24.0F, 0.0F);
		base.cubeList.add(new ModelBox(base, 0, 0, -3.0F, -29.0F, -16.0F, 6, 6, 26, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -3.0F, -29.0F, -43.0F, 6, 5, 27, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 64, -3.0F, -24.0F, -43.0F, 6, 1, 19, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 64, -3.0F, -28.0F, -46.0F, 6, 5, 3, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 64, -1.5F, -27.0F, -57.0F, 3, 3, 11, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 64, -2.0F, -27.5F, -62.0F, 4, 4, 5, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -3.0F, -23.0F, -15.0F, 6, 4, 25, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -2.5F, -25.0F, 8.0F, 5, 6, 24, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -3.0F, -24.0F, -24.0F, 6, 1, 8, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 64, -0.5F, -32.0F, -45.5F, 1, 4, 2, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -3.0F, -19.0F, -15.0F, 6, 3, 9, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -3.0F, -16.0F, -6.0F, 6, 1, 1, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -3.0F, -15.0F, -5.0F, 6, 1, 9, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -2.5F, -25.0F, 30.0F, 5, 15, 7, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 64, -2.5F, -25.0F, 37.0F, 5, 15, 2, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, 2.5F, -28.0F, -22.0F, 1, 4, 31, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -3.5F, -28.0F, -22.0F, 1, 4, 31, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -3.6F, -19.0F, -15.5F, 7, 3, 10, 0.0F, false));

		rail = new ModelRenderer(this);
		rail.setRotationPoint(0.0F, 24.0F, 0.0F);
		rail.cubeList.add(new ModelBox(rail, 0, 64, -2.5F, -29.5F, -18.0F, 5, 1, 26, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 64, -2.5F, -29.5F, -41.0F, 5, 1, 23, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 64, -2.5F, -29.8F, -40.0F, 5, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 64, -2.5F, -29.8F, -38.0F, 5, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 64, -2.5F, -29.8F, -36.0F, 5, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 64, -2.5F, -29.8F, -34.0F, 5, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 64, -2.5F, -29.8F, -28.0F, 5, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 64, -2.5F, -29.8F, -30.0F, 5, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 64, -2.5F, -29.8F, -32.0F, 5, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 64, -2.5F, -29.8F, -26.0F, 5, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 64, -2.5F, -29.8F, -20.0F, 5, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 64, -2.5F, -29.8F, -22.0F, 5, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 64, -2.5F, -29.8F, -24.0F, 5, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 64, -2.5F, -29.8F, -18.0F, 5, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 64, -2.5F, -29.8F, -12.0F, 5, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 64, -2.5F, -29.8F, -14.0F, 5, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 64, -2.5F, -29.8F, -16.0F, 5, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 64, -2.5F, -29.8F, -10.0F, 5, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 64, -2.5F, -29.8F, -4.0F, 5, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 64, -2.5F, -29.8F, -6.0F, 5, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 64, -2.5F, -29.8F, -8.0F, 5, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 64, -2.5F, -29.8F, -2.0F, 5, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 64, -2.5F, -29.8F, 4.0F, 5, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 64, -2.5F, -29.8F, 2.0F, 5, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 64, -2.5F, -29.8F, 0.0F, 5, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 64, -2.5F, -29.8F, 6.0F, 5, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 64, -2.5F, -23.5F, -42.0F, 5, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 64, -2.5F, -23.5F, -40.0F, 5, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 64, -2.5F, -23.5F, -38.0F, 5, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 64, -2.5F, -23.5F, -36.0F, 5, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 64, -2.5F, -23.5F, -34.0F, 5, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 64, -2.5F, -23.5F, -32.0F, 5, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 64, -2.5F, -23.5F, -30.0F, 5, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 64, -2.5F, -23.5F, -28.0F, 5, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 64, -2.5F, -23.5F, -26.0F, 5, 1, 1, 0.0F, false));

		mag = new ModelRenderer(this);
		mag.setRotationPoint(0.0F, 24.0F, 0.0F);
		setRotationAngle(mag, -0.1745F, 0.0F, 0.0F);
		mag.cubeList.add(new ModelBox(mag, 0, 64, -2.5F, -15.0F, -17.0F, 5, 16, 8, 0.0F, false));

		handle = new ModelRenderer(this);
		handle.setRotationPoint(0.0F, 24.0F, 0.0F);
		setRotationAngle(handle, 0.0873F, 0.0F, 0.0F);
		handle.cubeList.add(new ModelBox(handle, 0, 0, -3.0F, -19.0F, 5.0F, 6, 14, 6, 0.0F, false));

		trigger = new ModelRenderer(this);
		trigger.setRotationPoint(0.0F, 24.0F, 0.0F);
		setRotationAngle(trigger, -0.1745F, 0.0F, 0.0F);
		trigger.cubeList.add(new ModelBox(trigger, 0, 64, -2.0F, -20.0F, -4.0F, 2, 4, 2, 0.0F, false));

		stock = new ModelRenderer(this);
		stock.setRotationPoint(0.0F, 24.0F, 0.0F);
		setRotationAngle(stock, -0.0873F, 0.0F, 0.0F);
		stock.cubeList.add(new ModelBox(stock, 0, 0, -2.5F, -28.0F, 6.75F, 5, 3, 23, 0.0F, false));

		stock1 = new ModelRenderer(this);
		stock1.setRotationPoint(0.0F, 24.0F, 0.0F);
		setRotationAngle(stock1, 0.1745F, 0.0F, 0.0F);
		stock1.cubeList.add(new ModelBox(stock1, 0, 0, -2.5F, -14.6F, 31.0F, 5, 10, 5, 0.0F, false));

		irns = new ModelRenderer(this);
		irns.setRotationPoint(0.0F, 24.0F, 0.0F);
		irns.cubeList.add(new ModelBox(irns, 0, 64, -2.0F, -30.0F, 1.0F, 4, 1, 6, 0.0F, false));
		irns.cubeList.add(new ModelBox(irns, 0, 64, -2.0F, -30.5F, 1.0F, 1, 1, 6, 0.0F, false));
		irns.cubeList.add(new ModelBox(irns, 0, 64, 1.0F, -30.5F, 1.0F, 1, 1, 6, 0.0F, false));
		irns.cubeList.add(new ModelBox(irns, 0, 64, -0.5F, -34.0F, 3.0F, 1, 1, 1, 0.0F, false));
		irns.cubeList.add(new ModelBox(irns, 0, 64, -1.5F, -33.25F, 3.0F, 1, 2, 1, 0.0F, false));
		irns.cubeList.add(new ModelBox(irns, 0, 64, 0.5F, -33.25F, 3.0F, 1, 2, 1, 0.0F, false));
		irns.cubeList.add(new ModelBox(irns, 0, 64, -0.5F, -31.5F, 3.0F, 1, 2, 1, 0.0F, false));
	}
	
	@Override
	public void render(ItemStack stack)
	{
		EntityPlayerSP player = Minecraft.getMinecraft().player;
		
		if(player != null && player.hasCapability(PlayerDataProvider.PLAYER_DATA, null))
		{
			IPlayerData data = player.getCapability(PlayerDataProvider.PLAYER_DATA, null);
			boolean aim = data.isAiming();
			
			GlStateManager.pushMatrix();
			{
				animation_held.run(player.isSprinting());
				handleAnimation(aim, stack);
				renderScarL(aim, stack);
			}
			GlStateManager.popMatrix();
		}
	}
	
	private void renderScarL(boolean aim, ItemStack stack)
	{
		GlStateManager.pushMatrix();
		transform.defaultARTransform();
		GlStateManager.translate(0.0, 8.0, 0.0);
		
		if(aim && enableADS(stack))
		{
			rotateModelForADSRendering();
		}
		
		renderParts(hasScopeAtachment(stack));
		GlStateManager.popMatrix();
		
		renderSilencer(stack);
		renderAngledGrip(stack);
		renderVerticalGrip(stack);
		renderRedDot(stack);
		renderHolo(stack);
		render2X(stack);
		render4X(stack);
	}
	
	private void handleAnimation(boolean aim, ItemStack stack)
	{
		if(enableADS(stack))
		{
			if(!hasScopeAtachment(stack) && animation_aim.getFinalY() != 0.245d)
			{
				animation_aim.setYModifier(0.245d);
			}
			
			else if(hasRedDot(stack) && animation_aim.getFinalY() != 0.185d)
			{
				animation_aim.setYModifier(0.185d);
			}
			
			else if(hasHoloSight(stack) && animation_aim.getFinalY() != 0.14d)
			{
				animation_aim.setYModifier(0.14d);
			}
			
			animation_aim.run(aim);
		}
	}
	
	private void renderSilencer(ItemStack stack)
	{
		renderARSilencer(0, -0.9, -2, 1f, stack);
	}
	
	private void renderAngledGrip(ItemStack stack)
	{
		renderAngledGrip(0.6, 3.1, 3, 1f, stack);
	}
	
	private void renderVerticalGrip(ItemStack stack)
	{
		renderVerticalGrip(-0.8, 4.6, -6, 1f, stack);
	}
	
	private void renderRedDot(ItemStack stack)
	{
		renderRedDot(-0.3, -9.1, 3, 1.5f, stack);
	}
	
	private void renderHolo(ItemStack stack)
	{
		renderHolo(-1.4, -8.4, 0, 1.4f, stack);
	}
	
	private void render2X(ItemStack stack)
	{
		if(has2X(stack))
		{
			renderScope2X(6.3, 5, -4, 1.2f, stack);
		}
	}
	
	private void render4X(ItemStack stack)
	{
		if(has4X(stack))
		{
			renderScope4X(6.6, 5.2, -5, 1.2f, stack);
		}
	}
	
	private void renderParts(boolean ir)
	{
		base.render(1f);
		rail.render(1f);
		mag.render(1f);
		handle.render(1f);
		trigger.render(1f);
		stock.render(1f);
		stock1.render(1f);
		
		if(!ir) irns.render(1f);
	}
	
	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z)
	{
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}

package com.toma.pubgmc.client.models.weapons;

import com.toma.pubgmc.client.models.ModelGun;
import com.toma.pubgmc.common.capability.IPlayerData.PlayerDataProvider;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.item.ItemStack;

public class ModelMK47Mutant extends ModelGun
{
	private final ModelRenderer base;
	private final ModelRenderer rails;
	private final ModelRenderer mag;
	private final ModelRenderer trigger;
	private final ModelRenderer handle;
	private final ModelRenderer stock;
	private final ModelRenderer ironsight;

	public ModelMK47Mutant()
	{
		textureWidth = 128;
		textureHeight = 128;

		base = new ModelRenderer(this);
		base.setRotationPoint(0.0F, 24.0F, 0.0F);
		base.cubeList.add(new ModelBox(base, 0, 0, -3.0F, -23.0F, -60.0F, 6, 6, 42, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -1.5F, -21.5F, -63.0F, 3, 3, 3, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -3.0F, -23.0F, -18.0F, 6, 8, 18, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -3.0F, -17.5F, -27.0F, 6, 1, 10, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -2.5F, -16.5F, -26.5F, 5, 9, 9, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -3.0F, -15.0F, -18.0F, 6, 2, 3, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -3.0F, -13.0F, -17.0F, 6, 1, 2, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -3.0F, -12.0F, -16.0F, 6, 1, 8, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -3.0F, -15.0F, -8.0F, 6, 3, 1, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -3.0F, -21.0F, 0.0F, 6, 6, 3, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -2.0F, -21.0F, 3.0F, 4, 3, 23, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -2.0F, -21.0F, 26.0F, 4, 15, 2, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -2.0F, -18.0F, 22.0F, 4, 10, 4, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -2.0F, -18.0F, 3.0F, 4, 3, 5, 0.0F, false));

		rails = new ModelRenderer(this);
		rails.setRotationPoint(0.0F, 24.0F, 0.0F);
		rails.cubeList.add(new ModelBox(rails, 0, 0, -3.5F, -21.5F, -59.0F, 7, 5, 1, 0.0F, false));
		rails.cubeList.add(new ModelBox(rails, 0, 0, -3.5F, -21.5F, -57.0F, 7, 5, 1, 0.0F, false));
		rails.cubeList.add(new ModelBox(rails, 0, 0, -3.5F, -21.5F, -55.0F, 7, 5, 1, 0.0F, false));
		rails.cubeList.add(new ModelBox(rails, 0, 0, -3.5F, -21.5F, -53.0F, 7, 5, 1, 0.0F, false));
		rails.cubeList.add(new ModelBox(rails, 0, 0, -3.5F, -21.5F, -51.0F, 7, 5, 1, 0.0F, false));
		rails.cubeList.add(new ModelBox(rails, 0, 0, -3.5F, -21.5F, -49.0F, 7, 5, 1, 0.0F, false));
		rails.cubeList.add(new ModelBox(rails, 0, 0, -3.5F, -21.5F, -47.0F, 7, 5, 1, 0.0F, false));
		rails.cubeList.add(new ModelBox(rails, 0, 0, -3.5F, -21.5F, -45.0F, 7, 5, 1, 0.0F, false));
		rails.cubeList.add(new ModelBox(rails, 0, 0, -3.5F, -21.5F, -43.0F, 7, 5, 1, 0.0F, false));
		rails.cubeList.add(new ModelBox(rails, 0, 0, -3.5F, -21.5F, -41.0F, 7, 5, 1, 0.0F, false));
		rails.cubeList.add(new ModelBox(rails, 0, 0, -2.5F, -23.5F, -60.0F, 5, 1, 42, 0.0F, false));
		rails.cubeList.add(new ModelBox(rails, 0, 0, -2.5F, -24.0F, -59.0F, 5, 1, 1, 0.0F, false));
		rails.cubeList.add(new ModelBox(rails, 0, 0, -2.5F, -24.0F, -57.0F, 5, 1, 1, 0.0F, false));
		rails.cubeList.add(new ModelBox(rails, 0, 0, -2.5F, -24.0F, -55.0F, 5, 1, 1, 0.0F, false));
		rails.cubeList.add(new ModelBox(rails, 0, 0, -2.5F, -24.0F, -53.0F, 5, 1, 1, 0.0F, false));
		rails.cubeList.add(new ModelBox(rails, 0, 0, -2.5F, -24.0F, -51.0F, 5, 1, 1, 0.0F, false));
		rails.cubeList.add(new ModelBox(rails, 0, 0, -2.5F, -24.0F, -49.0F, 5, 1, 1, 0.0F, false));
		rails.cubeList.add(new ModelBox(rails, 0, 0, -2.5F, -24.0F, -47.0F, 5, 1, 1, 0.0F, false));
		rails.cubeList.add(new ModelBox(rails, 0, 0, -2.5F, -24.0F, -45.0F, 5, 1, 1, 0.0F, false));
		rails.cubeList.add(new ModelBox(rails, 0, 0, -2.5F, -24.0F, -43.0F, 5, 1, 1, 0.0F, false));
		rails.cubeList.add(new ModelBox(rails, 0, 0, -2.5F, -24.0F, -41.0F, 5, 1, 1, 0.0F, false));
		rails.cubeList.add(new ModelBox(rails, 0, 0, -2.5F, -24.0F, -39.0F, 5, 1, 1, 0.0F, false));
		rails.cubeList.add(new ModelBox(rails, 0, 0, -2.5F, -24.0F, -37.0F, 5, 1, 1, 0.0F, false));
		rails.cubeList.add(new ModelBox(rails, 0, 0, -2.5F, -24.0F, -35.0F, 5, 1, 1, 0.0F, false));
		rails.cubeList.add(new ModelBox(rails, 0, 0, -2.5F, -24.0F, -33.0F, 5, 1, 1, 0.0F, false));
		rails.cubeList.add(new ModelBox(rails, 0, 0, -2.5F, -24.0F, -31.0F, 5, 1, 1, 0.0F, false));
		rails.cubeList.add(new ModelBox(rails, 0, 0, -2.5F, -24.0F, -29.0F, 5, 1, 1, 0.0F, false));
		rails.cubeList.add(new ModelBox(rails, 0, 0, -2.5F, -24.0F, -27.0F, 5, 1, 1, 0.0F, false));
		rails.cubeList.add(new ModelBox(rails, 0, 0, -2.5F, -24.0F, -25.0F, 5, 1, 1, 0.0F, false));
		rails.cubeList.add(new ModelBox(rails, 0, 0, -2.5F, -24.0F, -23.0F, 5, 1, 1, 0.0F, false));
		rails.cubeList.add(new ModelBox(rails, 0, 0, -2.5F, -24.0F, -21.0F, 5, 1, 1, 0.0F, false));
		rails.cubeList.add(new ModelBox(rails, 0, 0, -2.5F, -24.0F, -19.0F, 5, 1, 1, 0.0F, false));
		rails.cubeList.add(new ModelBox(rails, 0, 0, -3.5F, -21.5F, -39.0F, 7, 5, 1, 0.0F, false));
		rails.cubeList.add(new ModelBox(rails, 0, 0, -3.5F, -21.5F, -37.0F, 7, 5, 1, 0.0F, false));
		rails.cubeList.add(new ModelBox(rails, 0, 0, -3.5F, -21.5F, -35.0F, 7, 5, 1, 0.0F, false));
		rails.cubeList.add(new ModelBox(rails, 0, 0, -3.5F, -21.5F, -33.0F, 7, 5, 1, 0.0F, false));
		rails.cubeList.add(new ModelBox(rails, 0, 0, -2.5F, -24.0F, -3.0F, 5, 1, 1, 0.0F, false));
		rails.cubeList.add(new ModelBox(rails, 0, 0, -2.5F, -24.0F, -5.0F, 5, 1, 1, 0.0F, false));
		rails.cubeList.add(new ModelBox(rails, 0, 0, -2.5F, -24.0F, -7.0F, 5, 1, 1, 0.0F, false));
		rails.cubeList.add(new ModelBox(rails, 0, 0, -2.5F, -24.0F, -9.0F, 5, 1, 1, 0.0F, false));
		rails.cubeList.add(new ModelBox(rails, 0, 0, -2.5F, -24.0F, -11.0F, 5, 1, 1, 0.0F, false));
		rails.cubeList.add(new ModelBox(rails, 0, 0, -2.5F, -24.0F, -13.0F, 5, 1, 1, 0.0F, false));
		rails.cubeList.add(new ModelBox(rails, 0, 0, -2.5F, -24.0F, -15.0F, 5, 1, 1, 0.0F, false));
		rails.cubeList.add(new ModelBox(rails, 0, 0, -2.5F, -24.0F, -17.0F, 5, 1, 1, 0.0F, false));
		rails.cubeList.add(new ModelBox(rails, 0, 0, -2.5F, -23.5F, -18.0F, 5, 1, 17, 0.0F, false));

		mag = new ModelRenderer(this);
		mag.setRotationPoint(0.0F, 24.0F, 0.0F);
		setRotationAngle(mag, -0.1745F, 0.0F, 0.0F);
		mag.cubeList.add(new ModelBox(mag, 0, 0, -2.5F, -4.5F, -27.5F, 5, 10, 9, 0.0F, false));

		trigger = new ModelRenderer(this);
		trigger.setRotationPoint(0.0F, 24.0F, 0.0F);
		setRotationAngle(trigger, -0.1745F, 0.0F, 0.0F);
		trigger.cubeList.add(new ModelBox(trigger, 0, 0, -1.0F, -14.0F, -13.0F, 2, 3, 1, 0.0F, false));

		handle = new ModelRenderer(this);
		handle.setRotationPoint(0.0F, 24.0F, 0.0F);
		setRotationAngle(handle, 0.1745F, 0.0F, 0.0F);
		handle.cubeList.add(new ModelBox(handle, 0, 0, -3.0F, -17.0F, -4.0F, 6, 14, 6, 0.0F, false));

		stock = new ModelRenderer(this);
		stock.setRotationPoint(0.0F, 24.0F, 0.0F);
		setRotationAngle(stock, -0.3491F, 0.0F, 0.0F);
		stock.cubeList.add(new ModelBox(stock, 0, 0, -2.0F, -17.0F, -4.0F, 4, 2, 27, 0.0F, false));
		stock.cubeList.add(new ModelBox(stock, 0, 0, -3.0F, -21.0F, -8.0F, 6, 2, 4, 0.0F, false));

		ironsight = new ModelRenderer(this);
		ironsight.setRotationPoint(0.0F, 24.0F, 0.0F);
		ironsight.cubeList.add(new ModelBox(ironsight, 0, 0, -2.0F, -24.0F, -5.0F, 4, 1, 2, 0.0F, false));
		ironsight.cubeList.add(new ModelBox(ironsight, 0, 0, -1.0F, -24.5F, -4.0F, 2, 1, 1, 0.0F, false));
		ironsight.cubeList.add(new ModelBox(ironsight, 0, 0, 1.0F, -26.5F, -4.0F, 1, 2, 1, 0.0F, false));
		ironsight.cubeList.add(new ModelBox(ironsight, 0, 0, -2.0F, -26.5F, -4.0F, 1, 2, 1, 0.0F, false));
		ironsight.cubeList.add(new ModelBox(ironsight, 0, 0, -1.0F, -27.5F, -4.0F, 2, 1, 1, 0.0F, false));
		ironsight.cubeList.add(new ModelBox(ironsight, 0, 0, -2.0F, -24.0F, -59.0F, 4, 1, 3, 0.0F, false));
		ironsight.cubeList.add(new ModelBox(ironsight, 0, 0, -0.5F, -25.5F, -58.0F, 1, 2, 1, 0.0F, false));
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
				renderMK47(aim, stack);
			}
			GlStateManager.popMatrix();
		}
	}
	
	private void renderMK47(boolean aim, ItemStack stack)
	{
		GlStateManager.pushMatrix();
		transform.defaultARTransform();
		GlStateManager.translate(0.0, 2.0, 7.0);
		
		if(aim && enableADS(stack))
		{
			rotateModelForADSRendering();
			GlStateManager.translate(26.5, -12.2, 14.0);
			
			if(hasRedDot(stack))
			{
				GlStateManager.translate(0, 2.6, 0);
			}
			
			else if(hasHoloSight(stack))
			{
				GlStateManager.translate(0, 4.8, 0);
			}
		}
		
		renderParts(hasScopeAtachment(stack));
		GlStateManager.popMatrix();
		
		renderRedDot(aim, stack);
		renderHolo(aim, stack);
		render2x(stack);
		render4x(stack);
		renderSilencer(aim, stack);
		renderVerticalGrip(aim, stack);
		renderAngledGrip(aim, stack);
	}
	
	private void renderRedDot(boolean aim, ItemStack stack)
	{
		if(aim)
		{
			renderRedDot(28.2, -17, 15, 1.3f, stack);
		}
		
		else renderRedDot(-0.4, -6.3, 0, 1.3f, stack);
	}
	
	private void renderHolo(boolean aim, ItemStack stack)
	{
		if(aim)
		{
			renderHolo(22.3, -13.8, 10, 1.3f, stack);
		}
		
		else renderHolo(-1.5, -7.2, -2, 1.3f, stack);
	}
	
	private void render2x(ItemStack stack)
	{
		renderScope2X(7.9, 10.8, -9, 1f, stack);
	}
	
	private void render4x(ItemStack stack)
	{
		renderScope4X(7.9, 10.8, -11, 1f, stack);
	}
	
	private void renderSilencer(boolean aim, ItemStack stack)
	{
		if(aim && enableADS(stack))
		{
			if(hasRedDot(stack))
			{
				renderARSilencer(-18.6, 6.2, 7, 1f, stack);	
			}
			
			else if(hasHoloSight(stack))
			{
				renderARSilencer(-18.6, 4.8, 7, 1f, stack);
			}
			
			else renderARSilencer(-18.6, 7.8, 7, 1f, stack);
		}
		
		else renderARSilencer(0, -0.6, -3, 1f, stack);
	}
	
	private void renderVerticalGrip(boolean aim, ItemStack stack)
	{
		if(aim && enableADS(stack))
		{
			if(hasRedDot(stack))
			{
				renderVerticalGrip(27, -6, 1, 1f, stack);
			}
			
			else if(hasHoloSight(stack))
			{
				renderVerticalGrip(27, -3, 1, 1f, stack);
			}
			
			else renderVerticalGrip(27, -8, 1, 1f, stack);
		}
		
		else renderVerticalGrip(0, 4, -12, 1f, stack);
	}
	
	private void renderAngledGrip(boolean aim, ItemStack stack)
	{
		if(aim && enableADS(stack))
		{
			if(hasRedDot(stack))
			{
				renderAngledGrip(-18, -4, -3, 1f, stack);
			}
			
			else if(hasHoloSight(stack))
			{
				renderAngledGrip(-18, -2, -3, 1f, stack);
			}
			
			else renderAngledGrip(-18, -5.3, -3, 1f, stack);
		}
		
		else renderAngledGrip(0, 3, 7, 1f, stack);
	}
	
	private void renderParts(boolean hasScope)
	{
		base.render(1f);
		rails.render(1f);
		mag.render(1f);
		trigger.render(1f);
		handle.render(1f);
		stock.render(1f);
		if(!hasScope) ironsight.render(1f);
	}
}

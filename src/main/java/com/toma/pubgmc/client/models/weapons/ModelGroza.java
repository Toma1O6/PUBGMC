package com.toma.pubgmc.client.models.weapons;

import com.toma.pubgmc.client.models.ModelGun;
import com.toma.pubgmc.common.capability.IPlayerData.PlayerDataProvider;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.item.ItemStack;

public class ModelGroza extends ModelGun
{
	private final ModelRenderer base;
	private final ModelRenderer handle;
	private final ModelRenderer mag;
	private final ModelRenderer trigger;
	private final ModelRenderer rail;
	private final ModelRenderer irns;

	public ModelGroza()
	{
		textureWidth = 128;
		textureHeight = 128;

		base = new ModelRenderer(this);
		base.setRotationPoint(0.0F, 24.0F, 0.0F);
		base.cubeList.add(new ModelBox(base, 0, 0, -3.0F, -21.0F, -27.0F, 6, 4, 27, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -2.0F, -21.0F, -50.0F, 4, 3, 23, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -3.0F, -32.0F, -27.0F, 6, 11, 2, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 64, -3.0F, -25.0F, -25.0F, 6, 4, 23, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -3.0F, -32.0F, -2.0F, 6, 11, 2, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -3.0F, -32.0F, -25.0F, 6, 2, 23, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -3.0F, -25.0F, 0.0F, 6, 8, 23, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -2.0F, -18.0F, -35.0F, 4, 11, 4, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -2.0F, -19.0F, -49.0F, 4, 2, 2, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -2.0F, -17.0F, -26.0F, 4, 3, 1, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -2.0F, -22.0F, 1.0F, 4, 13, 7, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -2.0F, -14.0F, -25.0F, 4, 1, 7, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -3.0F, -25.0F, 23.0F, 6, 14, 3, 0.0F, false));

		handle = new ModelRenderer(this);
		handle.setRotationPoint(0.0F, 24.0F, 0.0F);
		setRotationAngle(handle, 0.1745F, 0.0F, 0.0F);
		handle.cubeList.add(new ModelBox(handle, 0, 0, -2.0F, -22.0F, -16.0F, 4, 18, 6, 0.0F, false));

		mag = new ModelRenderer(this);
		mag.setRotationPoint(0.0F, 24.0F, 0.0F);
		setRotationAngle(mag, -0.1745F, 0.0F, 0.0F);
		mag.cubeList.add(new ModelBox(mag, 0, 0, -2.0F, -10.2F, -0.7F, 4, 12, 7, 0.0F, false));

		trigger = new ModelRenderer(this);
		trigger.setRotationPoint(0.0F, 24.0F, 0.0F);
		setRotationAngle(trigger, -0.0873F, 0.0F, 0.0F);
		trigger.cubeList.add(new ModelBox(trigger, 0, 0, -1.0F, -16.0F, -23.0F, 2, 3, 1, 0.0F, false));

		rail = new ModelRenderer(this);
		rail.setRotationPoint(0.0F, 24.0F, 0.0F);
		rail.cubeList.add(new ModelBox(rail, 0, 0, -3.0F, -32.5F, -26.0F, 6, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 0, -3.0F, -32.5F, -24.0F, 6, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 0, -3.0F, -32.5F, -22.0F, 6, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 0, -3.0F, -32.5F, -20.0F, 6, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 0, -3.0F, -32.5F, -18.0F, 6, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 0, -3.0F, -32.5F, -16.0F, 6, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 0, -3.0F, -32.5F, -14.0F, 6, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 0, -3.0F, -32.5F, -12.0F, 6, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 0, -3.0F, -32.5F, -10.0F, 6, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 0, -3.0F, -32.5F, -8.0F, 6, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 0, -3.0F, -32.5F, -6.0F, 6, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 0, -3.0F, -32.5F, -4.0F, 6, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 0, -3.0F, -32.5F, -2.0F, 6, 1, 1, 0.0F, false));

		irns = new ModelRenderer(this);
		irns.setRotationPoint(0.0F, 24.0F, 0.0F);
		irns.cubeList.add(new ModelBox(irns, 0, 0, -2.0F, -33.0F, -9.0F, 4, 1, 7, 0.0F, false));
		irns.cubeList.add(new ModelBox(irns, 0, 0, -2.0F, -34.0F, -6.0F, 1, 1, 3, 0.0F, false));
		irns.cubeList.add(new ModelBox(irns, 0, 0, 1.0F, -34.0F, -6.0F, 1, 1, 3, 0.0F, false));
		irns.cubeList.add(new ModelBox(irns, 0, 0, -0.5F, -33.5F, -5.0F, 1, 1, 1, 0.0F, false));
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
				renderGroza(aim, stack);
			}
			GlStateManager.popMatrix();
		}
	}
	
	private void renderGroza(boolean aim, ItemStack stack)
	{
		GlStateManager.pushMatrix();
		transform.defaultARTransform();
		GlStateManager.translate(0.0, 4.0, 3.0);
		
		if(aim && enableADS(stack))
		{
			GlStateManager.translate(26.65, -6.3, 15);
			
			if(hasRedDot(stack))
			{
				GlStateManager.translate(0, 3.5, 0);
			}
			
			else if(hasHoloSight(stack))
			{
				GlStateManager.translate(0, 5, 0);
			}
		}
		
		renderParts(hasScopeAtachment(stack));
		GlStateManager.popMatrix();
		
		renderSilencer(aim, stack);
		renderRedDot(aim, stack);
		renderHolo(aim, stack);
		render2x(stack);
		render4x(stack);
	}
	
	private void renderSilencer(boolean aim, ItemStack stack)
	{
		if(aim && enableADS(stack))
		{
			if(hasRedDot(stack))
			{
				renderARSilencer(-18.6, -0.3, 19, 1f, stack);
			}
			
			else if(hasHoloSight(stack))
			{
				renderARSilencer(-18.6, -1.5, 19, 1f, stack);
			}
			
			else renderARSilencer(-18.6, 2.3, 19, 1f, stack);
		}
		
		else renderARSilencer(0, -2.3, 9, 1f, stack);
	}
	
	private void renderRedDot(boolean aim, ItemStack stack)
	{
		if(aim)
		{
			renderRedDot(28.3, -17, 17, 1.3f, stack);
		}
		
		else renderRedDot(-0.3, -14, 0, 1.3f, stack);
	}
	
	private void renderHolo(boolean aim, ItemStack stack)
	{
		if(aim)
		{
			renderHolo(22.4, -13.9, 10, 1.3f, stack);
		}
		
		else renderHolo(-1.6, -13.2, -4, 1.3f, stack);
	}
	
	private void render2x(ItemStack stack)
	{
		renderScope2X(8.2, 3.5, -10, 1f, stack);
	}
	
	private void render4x(ItemStack stack)
	{
		renderScope4X(8.2, 3.5, -12, 1f, stack);
	}
	
	private void renderParts(boolean ir)
	{
		base.render(1f);
		handle.render(1f);
		mag.render(1f);
		trigger.render(1f);
		rail.render(1f);
		if(!ir) irns.render(1f);
	}
}

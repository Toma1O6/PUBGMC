package com.toma.pubgmc.client.models.weapons;

import com.toma.pubgmc.client.models.ModelGun;
import com.toma.pubgmc.common.capability.IPlayerData.PlayerDataProvider;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.item.ItemStack;

public class ModelQBZ extends ModelGun
{
	private final ModelRenderer base;
	private final ModelRenderer deco;
	private final ModelRenderer rail;
	private final ModelRenderer angle;
	private final ModelRenderer handle;
	private final ModelRenderer trigger;
	private final ModelRenderer mag;
	private final ModelRenderer irns;

	public ModelQBZ()
	{
		textureWidth = 128;
		textureHeight = 128;

		base = new ModelRenderer(this);
		base.setRotationPoint(0.0F, 24.0F, 0.0F);
		base.cubeList.add(new ModelBox(base, 0, 0, -3.0F, -27.0F, -25.0F, 6, 6, 26, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -3.0F, -27.0F, -45.0F, 6, 6, 20, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -1.0F, -25.0F, -60.0F, 2, 2, 15, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -1.5F, -25.5F, -67.0F, 3, 3, 7, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -2.0F, -27.5F, -44.0F, 4, 1, 27, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -3.5F, -26.5F, -24.0F, 7, 5, 25, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -1.0F, -31.0F, -44.0F, 2, 4, 1, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -2.0F, -32.2F, -36.5F, 4, 1, 22, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -2.0F, -32.2F, -14.5F, 4, 6, 3, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -2.0F, -27.5F, -17.0F, 4, 1, 3, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, 1.0F, -34.0F, -44.0F, 1, 4, 1, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -2.0F, -34.0F, -44.0F, 1, 4, 1, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -0.5F, -33.0F, -44.0F, 1, 4, 1, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -2.0F, -21.0F, -24.0F, 4, 2, 1, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -2.0F, -19.0F, -23.0F, 4, 1, 1, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -2.0F, -18.0F, -22.0F, 4, 1, 4, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -3.0F, -27.0F, -10.0F, 6, 6, 20, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -3.0F, -27.0F, 10.0F, 6, 12, 7, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -3.5F, -26.5F, 1.0F, 7, 5, 15, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -3.5F, -21.5F, 11.0F, 7, 6, 5, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -2.5F, -27.5F, -11.0F, 5, 1, 27, 0.0F, false));

		deco = new ModelRenderer(this);
		deco.setRotationPoint(0.0F, 24.0F, 0.0F);
		deco.cubeList.add(new ModelBox(deco, 0, 0, -3.5F, -26.5F, -43.0F, 7, 6, 1, 0.0F, false));
		deco.cubeList.add(new ModelBox(deco, 0, 0, -3.5F, -26.5F, -29.0F, 7, 6, 1, 0.0F, false));
		deco.cubeList.add(new ModelBox(deco, 0, 0, -3.5F, -26.5F, -39.0F, 7, 6, 1, 0.0F, false));
		deco.cubeList.add(new ModelBox(deco, 0, 0, -3.5F, -26.5F, -33.0F, 7, 6, 1, 0.0F, false));
		deco.cubeList.add(new ModelBox(deco, 0, 0, -3.5F, -26.5F, -31.0F, 7, 6, 1, 0.0F, false));
		deco.cubeList.add(new ModelBox(deco, 0, 0, -3.5F, -26.5F, -35.0F, 7, 6, 1, 0.0F, false));
		deco.cubeList.add(new ModelBox(deco, 0, 0, -3.5F, -26.5F, -37.0F, 7, 6, 1, 0.0F, false));
		deco.cubeList.add(new ModelBox(deco, 0, 0, -3.5F, -26.5F, -41.0F, 7, 6, 1, 0.0F, false));

		rail = new ModelRenderer(this);
		rail.setRotationPoint(0.0F, 24.0F, 0.0F);
		rail.cubeList.add(new ModelBox(rail, 0, 0, -1.5F, -32.4F, -17.0F, 3, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 0, -1.5F, -32.4F, -15.0F, 3, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 0, -1.5F, -32.4F, -19.0F, 3, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 0, -1.5F, -32.4F, -21.0F, 3, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 0, -1.5F, -32.4F, -23.0F, 3, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 0, -1.5F, -32.4F, -25.0F, 3, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 0, -1.5F, -32.4F, -27.0F, 3, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 0, -1.5F, -32.4F, -29.0F, 3, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 0, -1.5F, -32.4F, -31.0F, 3, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 0, -1.5F, -32.4F, -33.0F, 3, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 0, -1.5F, -32.4F, -35.0F, 3, 1, 1, 0.0F, false));

		angle = new ModelRenderer(this);
		angle.setRotationPoint(0.0F, 24.0F, 0.0F);
		setRotationAngle(angle, 0.7854F, 0.0F, 0.0F);
		angle.cubeList.add(new ModelBox(angle, 0, 0, -2.0F, -48.5F, -10.0F, 4, 1, 7, 0.0F, false));

		handle = new ModelRenderer(this);
		handle.setRotationPoint(0.0F, 24.0F, 0.0F);
		setRotationAngle(handle, 0.1745F, 0.0F, 0.0F);
		handle.cubeList.add(new ModelBox(handle, 0, 0, -2.5F, -24.0F, -15.0F, 5, 12, 5, 0.0F, false));

		trigger = new ModelRenderer(this);
		trigger.setRotationPoint(0.0F, 24.0F, 0.0F);
		setRotationAngle(trigger, -0.0873F, 0.0F, 0.0F);
		trigger.cubeList.add(new ModelBox(trigger, 0, 0, -1.0F, -21.5F, -22.5F, 2, 4, 1, 0.0F, false));

		mag = new ModelRenderer(this);
		mag.setRotationPoint(0.0F, 24.0F, 0.0F);
		setRotationAngle(mag, -0.1745F, 0.0F, 0.0F);
		mag.cubeList.add(new ModelBox(mag, 0, 0, -2.5F, -22.0F, -7.0F, 5, 16, 7, 0.0F, false));

		irns = new ModelRenderer(this);
		irns.setRotationPoint(0.0F, 24.0F, 0.0F);
		irns.cubeList.add(new ModelBox(irns, 0, 0, -2.0F, -34.0F, -14.0F, 1, 2, 2, 0.0F, false));
		irns.cubeList.add(new ModelBox(irns, 0, 0, 1.0F, -34.0F, -14.0F, 1, 2, 2, 0.0F, false));
		irns.cubeList.add(new ModelBox(irns, 0, 0, -0.5F, -33.0F, -14.0F, 1, 1, 1, 0.0F, false));
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
				animation_held.run(player.isSprinting());
				renderQBZ(aim, stack);
			}
			GlStateManager.popMatrix();
		}
	}
	
	private void renderQBZ(boolean aim, ItemStack stack)
	{
		GlStateManager.pushMatrix();
		transform.defaultARTransform();
		GlStateManager.translate(0.0, 9.0, 14.0);
		
		if(aim && enableADS(stack))
		{
			rotateModelForADSRendering();
			GlStateManager.translate(26.59, -11.8, 16.0);
			
			if(hasRedDot(stack))
			{
				GlStateManager.translate(0.0, 3.3, 0.0);
			}
			
			else if(hasHoloSight(stack))
			{
				GlStateManager.translate(0.0, 5.2, 0.0);
			}
		}
		
		renderParts(hasScopeAtachment(stack));
		GlStateManager.popMatrix();
		
		renderRedDot(aim, stack);
		renderHolo(aim, stack);
		render2X(stack);
		render4X(stack);
		renderSilencer(aim, stack);
		renderVerticalGrip(aim, stack);
		renderAngledGrip(aim, stack);
	}
	
	private void renderRedDot(boolean aim, ItemStack stack)
	{
		if(aim)
		{
			renderRedDot(30.6, -16.9, 16, 1.2f, stack);
		}
		
		else renderRedDot(-0.6, -7.1, -2, 1.2f, stack);
	}
	
	private void renderHolo(boolean aim, ItemStack stack)
	{
		if(aim)
		{
			renderHolo(24.2, -13.6, 11, 1.2f, stack);
		}
		else renderHolo(-1.6, -7.1, -5, 1.2f, stack);
	}
	
	private void render2X(ItemStack stack)
	{
		renderScope2X(7.7, 9, -10, 1f, stack);
	}
	
	private void render4X(ItemStack stack)
	{
		renderScope4X(7.7, 9, -10, 1f, stack);
	}
	
	private void renderSilencer(boolean aim, ItemStack stack)
	{
		if(aim && enableADS(stack))
		{
			if(hasRedDot(stack)) renderARSilencer(-18.8, 3.4, 21, 1f, stack);
			else if(hasHoloSight(stack)) renderARSilencer(-18.8, 2, 21, 1f, stack);
			else renderARSilencer(-18.8, 5.7, 21, 1f, stack);
		}
		
		else renderARSilencer(-0.1, -2.6, 10, 1f, stack);
	}
	
	private void renderVerticalGrip(boolean aim, ItemStack stack)
	{
		if(aim && enableADS(stack))
		{
			if(hasRedDot(stack)) renderVerticalGrip(27, -1, 23, 1f, stack);
			else if(hasHoloSight(stack)) renderVerticalGrip(27, 1, 23, 1f, stack);
			else renderVerticalGrip(27, -5, 23, 1f, stack);
		}
		
		else renderVerticalGrip(-1, 8, 6, 1f, stack);
	}
	
	private void renderAngledGrip(boolean aim, ItemStack stack)
	{
		if(aim && enableADS(stack))
		{
			if(hasRedDot(stack)) renderAngledGrip(-18, -1, -17, 1f, stack);
			else if(hasHoloSight(stack)) renderAngledGrip(-18, 0.7, -17, 1f, stack);
			else renderAngledGrip(-18, -3, -17, 1f, stack);
		}
		
		else renderAngledGrip(0.6, 5, -5, 1f, stack);
	}
	
	private void renderParts(boolean ir)
	{
		base.render(1f);
		deco.render(1f);
		rail.render(1f);
		angle.render(1f);
		handle.render(1f);
		trigger.render(1f);
		mag.render(1f);
		if(!ir) irns.render(1f);
	}
	
	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z)
	{
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}

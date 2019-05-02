package com.toma.pubgmc.client.models.weapons;

import com.toma.pubgmc.animation.ReloadAnimation;
import com.toma.pubgmc.animation.IPartAnimated.MagazineMovementStyle;
import com.toma.pubgmc.animation.ReloadAnimation.ReloadStyle;
import com.toma.pubgmc.client.models.ModelGun;
import com.toma.pubgmc.client.util.ModelDebugger;
import com.toma.pubgmc.common.capability.IPlayerData;
import com.toma.pubgmc.common.capability.IPlayerData.PlayerDataProvider;
import com.toma.pubgmc.util.PUBGMCUtil;

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
	private final ModelRenderer magazine;
	private final ModelRenderer trigger;
	private final ModelRenderer rail;
	private final ModelRenderer irns;

	public ModelGroza()
	{
		super();
		
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
		//base.cubeList.add(new ModelBox(base, 0, 0, -2.0F, -22.0F, 1.0F, 4, 13, 7, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -2.0F, -14.0F, -25.0F, 4, 1, 7, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -3.0F, -25.0F, 23.0F, 6, 14, 3, 0.0F, false));

		handle = new ModelRenderer(this);
		handle.setRotationPoint(0.0F, 24.0F, 0.0F);
		setRotationAngle(handle, 0.1745F, 0.0F, 0.0F);
		handle.cubeList.add(new ModelBox(handle, 0, 0, -2.0F, -22.0F, -16.0F, 4, 18, 6, 0.0F, false));
		
		magazine = new ModelRenderer(this);
		magazine.setRotationPoint(0.0F, 24.0F, 0.0F);
		magazine.cubeList.add(new ModelBox(base, 0, 0, -2.0F, -22.0F, 1.0F, 4, 13, 7, 0.0F, false));
		
		mag = new ModelRenderer(this);
		mag.setRotationPoint(0.0F, 24.0F, 0.0F);
		magazine.addChild(mag);
		setRotationAngle(mag, -0.1745F, 0.0F, 0.0F);
		mag.cubeList.add(new ModelBox(mag, 0, 0, -2.0F, -34.4F, -4.8F, 4, 12, 7, 0.0F, false));

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
		this.initAnimations();
	}
	
	@Override
	public void initAnimations() 
	{
		initAimAnimation(-0.56f, 0.135f, 0.28f);
		initAimingAnimationStates(0.135f, 0.0575f, 0.01f);
		reloadAnimation = new ReloadAnimation(magazine, MagazineMovementStyle.DEFAULT, ReloadStyle.MAGAZINE);
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
				renderGroza(data.isAiming(), stack);
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
			rotateModelForADSRendering();
		}
		
		renderParts(hasScopeAtachment(stack));
		GlStateManager.popMatrix();
		
		renderSilencer(stack);
		renderRedDot(stack);
		renderHolo(stack);
		render2x(stack);
		render4x(stack);
	}
	
	private void renderSilencer(ItemStack stack)
	{
		renderARSilencer(0, -2.3, 9, 1f, stack);
	}
	
	private void renderRedDot(ItemStack stack)
	{
		renderRedDot(-0.42, -14, 0, 1.3f, stack);
	}
	
	private void renderHolo(ItemStack stack)
	{
		renderHolo(-1.5, -13.2, -4, 1.3f, stack);
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
		magazine.render(1f);
		trigger.render(1f);
		rail.render(1f);
		if(!ir) irns.render(1f);
	}
}

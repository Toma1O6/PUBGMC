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

public class ModelScorpion extends ModelGun
{
	private final ModelRenderer base;
	private final ModelRenderer rail;
	private final ModelRenderer mag_n;
	private final ModelRenderer sh;
	private final ModelRenderer handle;
	private final ModelRenderer ironsight;
	private final ModelRenderer mag_round;
	private final ModelRenderer lup;
	private final ModelRenderer ldown;
	private final ModelRenderer trigger;

	public ModelScorpion()
	{
		animation_aim = new AimAnimation(-0.56d, 0.2d, 0.2d, 1f).setInvertedCoords(true, false, false);
		animation_held.setWeaponType(true);
		
		textureWidth = 128;
		textureHeight = 128;

		base = new ModelRenderer(this);
		base.setRotationPoint(0.0F, 24.0F, 0.0F);
		base.cubeList.add(new ModelBox(base, 0, 0, -3.0F, -22.0F, -31.0F, 6, 4, 32, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -1.0F, -22.0F, -37.0F, 2, 2, 5, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -2.0F, -23.0F, -32.0F, 4, 4, 1, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -2.5F, -24.0F, -31.0F, 5, 2, 32, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -2.0F, -18.0F, -13.5F, 4, 3, 1, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -2.0F, -15.0F, -12.5F, 4, 1, 5, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -2.0F, -18.0F, -7.5F, 4, 3, 1, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -2.5F, -18.0F, -6.0F, 5, 1, 8, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -2.5F, -17.0F, -6.0F, 5, 3, 7, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -2.0F, -23.0F, 0.5F, 4, 5, 1, 0.0F, false));

		rail = new ModelRenderer(this);
		rail.setRotationPoint(0.0F, 24.0F, 0.0F);
		rail.cubeList.add(new ModelBox(rail, 0, 0, -2.5F, -18.6F, -30.0F, 5, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 0, -2.5F, -18.6F, -28.0F, 5, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 0, -2.5F, -18.6F, -26.0F, 5, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 0, -2.5F, -18.6F, -24.0F, 5, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 0, -2.5F, -18.6F, -22.0F, 5, 1, 1, 0.0F, false));

		mag_n = new ModelRenderer(this);
		mag_n.setRotationPoint(0.0F, 24.0F, 0.0F);
		mag_n.cubeList.add(new ModelBox(mag_n, 0, 0, -2.5F, -18.0F, -19.0F, 5, 8, 5, 0.0F, false));
		mag_n.cubeList.add(new ModelBox(mag_n, 0, 0, 1.7F, -18.0F, -17.0F, 1, 8, 1, 0.0F, false));
		mag_n.cubeList.add(new ModelBox(mag_n, 0, 0, 1.7F, -17.0F, -19.0F, 1, 1, 5, 0.0F, false));
		mag_n.cubeList.add(new ModelBox(mag_n, 0, 0, 1.7F, -14.0F, -19.0F, 1, 1, 5, 0.0F, false));
		mag_n.cubeList.add(new ModelBox(mag_n, 0, 0, 1.7F, -11.0F, -19.0F, 1, 1, 5, 0.0F, false));
		mag_n.cubeList.add(new ModelBox(mag_n, 0, 0, -2.7F, -18.0F, -17.0F, 1, 8, 1, 0.0F, false));
		mag_n.cubeList.add(new ModelBox(mag_n, 0, 0, -2.7F, -17.0F, -19.0F, 1, 1, 5, 0.0F, false));
		mag_n.cubeList.add(new ModelBox(mag_n, 0, 0, -2.7F, -14.0F, -19.0F, 1, 1, 5, 0.0F, false));
		mag_n.cubeList.add(new ModelBox(mag_n, 0, 0, -2.7F, -11.0F, -19.0F, 1, 1, 5, 0.0F, false));

		sh = new ModelRenderer(this);
		sh.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(sh, -0.1745F, 0.0F, 0.0F);
		mag_n.addChild(sh);
		sh.cubeList.add(new ModelBox(sh, 0, 0, -2.5F, -7.5F, -20.55F, 5, 10, 5, 0.0F, false));
		sh.cubeList.add(new ModelBox(sh, 0, 0, 1.7F, -7.5F, -18.55F, 1, 10, 1, 0.0F, false));
		sh.cubeList.add(new ModelBox(sh, 0, 0, 1.7F, 1.5F, -20.55F, 1, 1, 5, 0.0F, false));
		sh.cubeList.add(new ModelBox(sh, 0, 0, 1.7F, -1.5F, -20.55F, 1, 1, 5, 0.0F, false));
		sh.cubeList.add(new ModelBox(sh, 0, 0, 1.7F, -4.5F, -20.55F, 1, 1, 5, 0.0F, false));
		sh.cubeList.add(new ModelBox(sh, 0, 0, -2.7F, -7.5F, -18.55F, 1, 10, 1, 0.0F, false));
		sh.cubeList.add(new ModelBox(sh, 0, 0, -2.7F, 1.5F, -20.55F, 1, 1, 5, 0.0F, false));
		sh.cubeList.add(new ModelBox(sh, 0, 0, -2.7F, -1.5F, -20.55F, 1, 1, 5, 0.0F, false));
		sh.cubeList.add(new ModelBox(sh, 0, 0, -2.7F, -4.5F, -20.55F, 1, 1, 5, 0.0F, false));

		handle = new ModelRenderer(this);
		handle.setRotationPoint(0.0F, 24.0F, 0.0F);
		setRotationAngle(handle, 0.0873F, 0.0F, 0.0F);
		handle.cubeList.add(new ModelBox(handle, 0, 0, -2.5F, -15.0F, -4.65F, 5, 9, 7, 0.0F, false));

		ironsight = new ModelRenderer(this);
		ironsight.setRotationPoint(0.0F, 24.0F, 0.0F);
		ironsight.cubeList.add(new ModelBox(ironsight, 0, 0, -2.0F, -24.5F, -5.0F, 1, 1, 3, 0.0F, false));
		ironsight.cubeList.add(new ModelBox(ironsight, 0, 0, 1.0F, -24.5F, -5.0F, 1, 1, 3, 0.0F, false));
		ironsight.cubeList.add(new ModelBox(ironsight, 0, 0, -0.5F, -24.65F, -29.0F, 1, 1, 1, 0.0F, false));
		ironsight.cubeList.add(new ModelBox(ironsight, 0, 0, 1.0F, -25.0F, -4.5F, 1, 1, 2, 0.0F, false));
		ironsight.cubeList.add(new ModelBox(ironsight, 0, 0, -2.0F, -25.0F, -4.5F, 1, 1, 2, 0.0F, false));

		mag_round = new ModelRenderer(this);
		mag_round.setRotationPoint(0.0F, 24.0F, 0.0F);
		mag_round.cubeList.add(new ModelBox(mag_round, 0, 0, -4.0F, -18.0F, -20.0F, 8, 1, 6, 0.0F, false));
		mag_round.cubeList.add(new ModelBox(mag_round, 0, 0, 3.7F, -15.0F, -20.0F, 3, 4, 6, 0.0F, false));
		mag_round.cubeList.add(new ModelBox(mag_round, 0, 0, -4.0F, -17.1F, -20.0F, 8, 9, 6, 0.0F, false));
		mag_round.cubeList.add(new ModelBox(mag_round, 0, 0, -6.8F, -15.0F, -20.0F, 3, 4, 6, 0.0F, false));

		lup = new ModelRenderer(this);
		lup.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(lup, 0.0F, 0.0F, 0.7854F);
		mag_round.addChild(lup);
		lup.cubeList.add(new ModelBox(lup, 0, 0, -10.0F, -15.5F, -20.0F, 4, 4, 6, 0.0F, false));
		lup.cubeList.add(new ModelBox(lup, 0, 0, -12.6F, -6.95F, -20.0F, 4, 4, 6, 0.0F, false));

		ldown = new ModelRenderer(this);
		ldown.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(ldown, 0.0F, 0.0F, -0.7854F);
		mag_round.addChild(ldown);
		ldown.cubeList.add(new ModelBox(ldown, 0, 0, 8.4F, -7.0F, -20.0F, 4, 4, 6, 0.0F, false));
		ldown.cubeList.add(new ModelBox(ldown, 0, 0, 6.0F, -15.48F, -20.0F, 4, 4, 6, 0.0F, false));

		trigger = new ModelRenderer(this);
		trigger.setRotationPoint(0.0F, 24.0F, 0.0F);
		setRotationAngle(trigger, -0.1745F, 0.0F, 0.0F);
		trigger.cubeList.add(new ModelBox(trigger, 0, 0, -1.0F, -17.0F, -12.0F, 2, 3, 1, 0.0F, false));
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
				handleAnimations(data, player.isSprinting(), stack);
				renderScorpion(data.isAiming(), stack);
			}
			GlStateManager.popMatrix();
		}
	}
	
	private void handleAnimations(IPlayerData data, boolean sprint, ItemStack stack)
	{
		if(enableADS(stack))
		{
			if(!hasScopeAtachment(stack) && animation_aim.getFinalY() != 0.2d)
				animation_aim.setYModifier(0.2d);
			
			else if(hasRedDot(stack) && animation_aim.getFinalY() != 0.117d)
				animation_aim.setYModifier(0.117d);
			
			animation_aim.run(data.isAiming());
		}
		animation_aim.setMovementMultiplier(1.3f, 1f, 1.2f);
		animation_held.run(sprint);
		animation_reload.run(data.isReloading());
	}
	
	private void renderScorpion(boolean aim, ItemStack stack)
	{
		GlStateManager.pushMatrix();
		{
			transform.defaultPistolTransform();
			GlStateManager.scale(0.8, 0.8, 0.8);
			GlStateManager.translate(0.0, 8.0, 4.0);
			
			if(aim && enableADS(stack)) rotateModelForADSRendering();
			
			renderParts(hasExtendedMagazine(stack));
		}
		GlStateManager.popMatrix();
		
		renderRedDot(-0.45, -11, -3, 1.3f, stack);
		renderPistolSilencer(0.1, 1.1, -3.3, 1f, stack);
		renderVerticalGrip(-1, -0.2, 1, 1f, stack);
	}

	private void renderParts(boolean mag) 
	{
		base.render(1f);
		rail.render(1f);
		handle.render(1f);
		ironsight.render(1f);
		trigger.render(1f);
		
		if(mag) {
			mag_round.render(1f);
		} else {
			mag_n.render(1f);
		}
	}
}

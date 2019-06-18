package com.toma.pubgmc.client.models.weapons;

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

public class ModelM24 extends ModelGun
{
	private final ModelRenderer m24;
	private final ModelRenderer bone2;
	private final ModelRenderer bone3;
	private final ModelRenderer bone4;
	private final ModelRenderer bone5;
	private final ModelRenderer bone8;
	private final ModelRenderer bone9;
	private final ModelRenderer bone10;
	private final ModelRenderer bone11;
	private final ModelRenderer magazine;
	private final ModelRenderer bone;
	private final ModelRenderer bone6;
	private final ModelRenderer bullet;
	private final ModelRenderer bullet2;
	private final ModelRenderer bone7;
	private final ModelRenderer bolt;
	private final ModelRenderer bone12;

	public ModelM24() {
		textureWidth = 128;
		textureHeight = 128;

		m24 = new ModelRenderer(this);
		m24.setRotationPoint(0.0F, 24.0F, -21.0F);
		m24.cubeList.add(new ModelBox(m24, 74, 34, -3.0F, 0.0F, 1.0F, 6, 2, 20, 0.0F, false));
		m24.cubeList.add(new ModelBox(m24, 66, 4, -4.0F, -4.0F, -2.0F, 8, 4, 23, 0.0F, false));
		m24.cubeList.add(new ModelBox(m24, 33, 97, -2.0F, -6.0F, 13.0F, 4, 2, 13, 0.0F, false));
		m24.cubeList.add(new ModelBox(m24, 75, 32, -3.0F, 2.0F, 21.0F, 6, 2, 16, 0.0F, false));
		m24.cubeList.add(new ModelBox(m24, 64, 10, -4.0F, -4.0F, 21.0F, 8, 6, 24, 0.0F, false));
		m24.cubeList.add(new ModelBox(m24, 75, 32, -3.0F, -7.0F, 26.0F, 6, 4, 19, 0.0F, false));
		m24.cubeList.add(new ModelBox(m24, 75, 32, 0.0304F, -7.3473F, 26.0F, 1, 2, 19, 0.0F, false));
		m24.cubeList.add(new ModelBox(m24, 75, 32, -1.0304F, -7.3473F, 26.0F, 2, 2, 19, 0.0F, true));
		m24.cubeList.add(new ModelBox(m24, 32, 106, -2.0F, -6.0F, -50.0F, 4, 4, 12, 0.0F, false));
		m24.cubeList.add(new ModelBox(m24, 38, 101, -2.5F, -6.5F, -47.0F, 5, 5, 6, 0.0F, false));
		m24.cubeList.add(new ModelBox(m24, 83, 64, -3.5F, -3.5F, 44.5F, 7, 1, 1, 0.0F, false));
		m24.cubeList.add(new ModelBox(m24, 75, 32, -3.0F, 2.8224F, 53.7654F, 6, 9, 8, 0.0F, false));
		m24.cubeList.add(new ModelBox(m24, 0, 33, -3.5F, 0.8224F, 60.7654F, 7, 13, 9, 0.0F, false));
		m24.cubeList.add(new ModelBox(m24, 0, 45, -2.5F, -0.1776F, 60.7654F, 5, 1, 11, 0.0F, false));
		m24.cubeList.add(new ModelBox(m24, 13, 73, -3.5F, -0.0776F, 77.7654F, 7, 13, 2, 0.0F, false));
		m24.cubeList.add(new ModelBox(m24, 40, 68, -1.5F, 1.4224F, 76.7654F, 3, 10, 1, 0.0F, false));
		m24.cubeList.add(new ModelBox(m24, 0, 45, -2.5F, -0.1776F, 71.7654F, 5, 1, 5, 0.0F, false));
		m24.cubeList.add(new ModelBox(m24, 0, 33, -3.5F, 0.8224F, 69.7654F, 7, 13, 7, 0.0F, false));
		m24.cubeList.add(new ModelBox(m24, 73, 18, -4.0F, -4.0F, -15.0F, 8, 4, 14, 0.0F, false));
		m24.cubeList.add(new ModelBox(m24, 74, 34, -3.0F, 0.0F, -15.0F, 6, 2, 16, 0.0F, false));
		m24.cubeList.add(new ModelBox(m24, 33, 97, -2.0F, -6.0F, 0.0F, 4, 2, 13, 0.0F, false));
		m24.cubeList.add(new ModelBox(m24, 33, 97, -2.0F, -6.0F, -13.0F, 4, 2, 13, 0.0F, false));
		m24.cubeList.add(new ModelBox(m24, 33, 97, -2.0F, -6.0F, -26.0F, 4, 4, 13, 0.0F, false));
		m24.cubeList.add(new ModelBox(m24, 33, 97, -2.0F, -6.0F, -38.0F, 4, 4, 12, 0.0F, false));

		bone2 = new ModelRenderer(this);
		bone2.setRotationPoint(0.0F, 3.0F, 0.0F);
		setRotationAngle(bone2, 0.0F, 0.0F, -0.7854F);
		m24.addChild(bone2);
		bone2.cubeList.add(new ModelBox(bone2, 74, 23, 2.9497F, -1.2929F, -2.0F, 2, 2, 23, 0.0F, false));
		bone2.cubeList.add(new ModelBox(bone2, 72, 20, -0.7071F, -4.9497F, 1.0F, 2, 2, 20, 0.0F, false));
		bone2.cubeList.add(new ModelBox(bone2, 75, 32, 1.5355F, 0.1213F, 21.0F, 2, 2, 16, 0.0F, false));
		bone2.cubeList.add(new ModelBox(bone2, 75, 32, -2.1213F, -3.5355F, 21.0F, 2, 2, 16, 0.0F, false));
		bone2.cubeList.add(new ModelBox(bone2, 80, 33, 2.9497F, -1.2929F, -15.0F, 2, 2, 13, 0.0F, false));
		bone2.cubeList.add(new ModelBox(bone2, 79, 29, -0.7071F, -4.9497F, -15.0F, 2, 2, 16, 0.0F, false));

		bone3 = new ModelRenderer(this);
		bone3.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(bone3, 0.0F, 0.0F, -0.1745F);
		m24.addChild(bone3);
		bone3.cubeList.add(new ModelBox(bone3, 65, 4, 2.17F, -6.3727F, 26.0F, 2, 4, 19, 0.0F, false));
		bone3.cubeList.add(new ModelBox(bone3, 75, 32, -1.7389F, -7.4146F, 26.0F, 2, 2, 19, 0.0F, false));

		bone4 = new ModelRenderer(this);
		bone4.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(bone4, 0.0F, 0.0F, 0.1745F);
		m24.addChild(bone4);
		bone4.cubeList.add(new ModelBox(bone4, 75, 31, -4.17F, -6.3727F, 26.0F, 2, 4, 19, 0.0F, true));
		bone4.cubeList.add(new ModelBox(bone4, 75, 32, -0.2611F, -7.4146F, 26.0F, 2, 2, 19, 0.0F, true));

		bone5 = new ModelRenderer(this);
		bone5.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(bone5, 0.5236F, 0.0F, 0.0F);
		m24.addChild(bone5);
		bone5.cubeList.add(new ModelBox(bone5, 78, 48, -3.0F, 19.9641F, 30.0429F, 6, 2, 5, 0.0F, false));

		bone8 = new ModelRenderer(this);
		bone8.setRotationPoint(0.0F, -1.0F, 33.0F);
		setRotationAngle(bone8, -0.4363F, 0.0F, 0.0F);
		m24.addChild(bone8);
		bone8.cubeList.add(new ModelBox(bone8, 93, 47, -3.001F, -7.7903F, 9.6078F, 6, 3, 8, 0.0F, false));

		bone9 = new ModelRenderer(this);
		bone9.setRotationPoint(0.0F, -1.0F, 33.0F);
		setRotationAngle(bone9, -0.6109F, 0.0F, 0.0F);
		m24.addChild(bone9);
		bone9.cubeList.add(new ModelBox(bone9, 96, 44, -3.0F, -9.4255F, 9.5506F, 6, 5, 10, 0.0F, false));
		bone9.cubeList.add(new ModelBox(bone9, 101, 47, -2.999F, -10.7296F, 15.9876F, 6, 3, 6, 0.0F, false));

		bone10 = new ModelRenderer(this);
		bone10.setRotationPoint(4.0F, -1.0F, 44.0F);
		setRotationAngle(bone10, 0.0F, 0.7854F, 0.0F);
		m24.addChild(bone10);
		bone10.cubeList.add(new ModelBox(bone10, 84, 17, -3.7071F, -2.0F, -1.2929F, 3, 5, 2, 0.0F, false));
		bone10.cubeList.add(new ModelBox(bone10, 84, 17, -6.364F, -2.0F, -4.9498F, 2, 5, 3, 0.0F, false));

		bone11 = new ModelRenderer(this);
		bone11.setRotationPoint(0.0F, -1.0F, 33.0F);
		setRotationAngle(bone11, 0.2618F, 0.0F, 0.0F);
		m24.addChild(bone11);
		bone11.cubeList.add(new ModelBox(bone11, 107, 49, -2.998F, 10.2704F, 18.9876F, 6, 8, 1, 0.0F, false));
		bone11.cubeList.add(new ModelBox(bone11, 75, 32, -2.998F, 9.2704F, 13.9876F, 6, 9, 5, 0.0F, false));

		magazine = new ModelRenderer(this);
		magazine.setRotationPoint(0.0F, 12.2F, 0.0F);
		magazine.cubeList.add(new ModelBox(magazine, 32, 7, -2.5F, 15.5F, 1.5F, 5, 5, 10, 0.0F, false));
		magazine.cubeList.add(new ModelBox(magazine, 40, 10, -1.5F, 14.5F, 1.5F, 3, 1, 1, 0.0F, false));
		magazine.cubeList.add(new ModelBox(magazine, 40, 10, -1.5F, 14.5F, 10.5F, 3, 1, 1, 0.0F, false));
		magazine.cubeList.add(new ModelBox(magazine, 40, 10, 1.5F, 14.1F, 1.5F, 1, 2, 10, 0.0F, false));
		magazine.cubeList.add(new ModelBox(magazine, 40, 10, -2.5F, 14.1F, 1.5F, 1, 2, 10, 0.0F, false));

		bone = new ModelRenderer(this);
		bone.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(bone, 0.0F, 0.0F, 0.3491F);
		magazine.addChild(bone);
		bone.cubeList.add(new ModelBox(bone, 40, 10, 2.4733F, 13.1047F, 1.5F, 1, 1, 10, 0.0F, false));

		bone6 = new ModelRenderer(this);
		bone6.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(bone6, 0.0F, 0.0F, -0.3491F);
		magazine.addChild(bone6);
		bone6.cubeList.add(new ModelBox(bone6, 40, 10, -3.4733F, 13.1047F, 1.5F, 1, 1, 10, 0.0F, true));

		bullet = new ModelRenderer(this);
		bullet.setRotationPoint(0.0F, -0.3F, 0.0F);
		magazine.addChild(bullet);
		bullet.cubeList.add(new ModelBox(bullet, 9, 107, -0.8F, 13.8F, 8.75F, 1, 1, 1, 0.0F, false));
		bullet.cubeList.add(new ModelBox(bullet, 9, 107, -1.0F, 13.0F, 9.5F, 2, 2, 1, 0.0F, false));
		bullet.cubeList.add(new ModelBox(bullet, 9, 107, -1.0F, 13.0F, 4.0F, 2, 2, 5, 0.0F, false));
		bullet.cubeList.add(new ModelBox(bullet, 9, 107, -0.8F, 13.2F, 8.75F, 1, 1, 1, 0.0F, false));
		bullet.cubeList.add(new ModelBox(bullet, 9, 107, -0.2F, 13.2F, 8.75F, 1, 1, 1, 0.0F, false));
		bullet.cubeList.add(new ModelBox(bullet, 9, 107, -0.2F, 13.8F, 8.75F, 1, 1, 1, 0.0F, false));
		bullet.cubeList.add(new ModelBox(bullet, 9, 107, -0.5F, 13.0F, 3.4F, 1, 2, 1, 0.0F, false));
		bullet.cubeList.add(new ModelBox(bullet, 9, 107, -1.0F, 13.5F, 3.4F, 2, 1, 1, 0.0F, false));
		bullet.cubeList.add(new ModelBox(bullet, 9, 107, -0.15F, 13.15F, 3.4F, 1, 1, 1, 0.0F, false));
		bullet.cubeList.add(new ModelBox(bullet, 9, 107, -0.85F, 13.15F, 3.4F, 1, 1, 1, 0.0F, false));
		bullet.cubeList.add(new ModelBox(bullet, 9, 107, -0.15F, 13.85F, 3.4F, 1, 1, 1, 0.0F, false));
		bullet.cubeList.add(new ModelBox(bullet, 9, 107, -0.85F, 13.85F, 3.4F, 1, 1, 1, 0.0F, false));
		bullet.cubeList.add(new ModelBox(bullet, 9, 107, -0.65F, 13.35F, 2.9F, 1, 1, 1, 0.0F, false));
		bullet.cubeList.add(new ModelBox(bullet, 9, 107, -0.35F, 13.35F, 2.9F, 1, 1, 1, 0.0F, true));
		bullet.cubeList.add(new ModelBox(bullet, 9, 107, -0.35F, 13.65F, 2.9F, 1, 1, 1, 0.0F, true));
		bullet.cubeList.add(new ModelBox(bullet, 9, 107, -0.65F, 13.65F, 2.9F, 1, 1, 1, 0.0F, false));
		bullet.cubeList.add(new ModelBox(bullet, 9, 107, -0.5F, 13.5F, 2.2F, 1, 1, 2, 0.0F, false));

		bullet2 = new ModelRenderer(this);
		bullet2.setRotationPoint(-0.5F, 1.8F, 0.0F);
		magazine.addChild(bullet2);
		bullet2.cubeList.add(new ModelBox(bullet2, 9, 107, -0.8F, 13.8F, 8.75F, 1, 1, 1, 0.0F, false));
		bullet2.cubeList.add(new ModelBox(bullet2, 9, 107, -1.0F, 13.0F, 9.5F, 2, 2, 1, 0.0F, false));
		bullet2.cubeList.add(new ModelBox(bullet2, 9, 107, -1.0F, 13.0F, 4.0F, 2, 2, 5, 0.0F, false));
		bullet2.cubeList.add(new ModelBox(bullet2, 9, 107, -0.8F, 13.2F, 8.75F, 1, 1, 1, 0.0F, false));
		bullet2.cubeList.add(new ModelBox(bullet2, 9, 107, -0.2F, 13.2F, 8.75F, 1, 1, 1, 0.0F, false));
		bullet2.cubeList.add(new ModelBox(bullet2, 9, 107, -0.2F, 13.8F, 8.75F, 1, 1, 1, 0.0F, false));
		bullet2.cubeList.add(new ModelBox(bullet2, 9, 107, -0.5F, 13.0F, 3.4F, 1, 2, 1, 0.0F, false));
		bullet2.cubeList.add(new ModelBox(bullet2, 9, 107, -1.0F, 13.5F, 3.4F, 2, 1, 1, 0.0F, false));
		bullet2.cubeList.add(new ModelBox(bullet2, 9, 107, -0.15F, 13.15F, 3.4F, 1, 1, 1, 0.0F, false));
		bullet2.cubeList.add(new ModelBox(bullet2, 9, 107, -0.85F, 13.15F, 3.4F, 1, 1, 1, 0.0F, false));
		bullet2.cubeList.add(new ModelBox(bullet2, 9, 107, -0.15F, 13.85F, 3.4F, 1, 1, 1, 0.0F, false));
		bullet2.cubeList.add(new ModelBox(bullet2, 9, 107, -0.85F, 13.85F, 3.4F, 1, 1, 1, 0.0F, false));
		bullet2.cubeList.add(new ModelBox(bullet2, 9, 107, -0.65F, 13.35F, 2.9F, 1, 1, 1, 0.0F, false));
		bullet2.cubeList.add(new ModelBox(bullet2, 9, 107, -0.35F, 13.35F, 2.9F, 1, 1, 1, 0.0F, true));
		bullet2.cubeList.add(new ModelBox(bullet2, 9, 107, -0.35F, 13.65F, 2.9F, 1, 1, 1, 0.0F, true));
		bullet2.cubeList.add(new ModelBox(bullet2, 9, 107, -0.65F, 13.65F, 2.9F, 1, 1, 1, 0.0F, false));
		bullet2.cubeList.add(new ModelBox(bullet2, 9, 107, -0.5F, 13.5F, 2.2F, 1, 1, 2, 0.0F, false));

		bone7 = new ModelRenderer(this);
		bone7.setRotationPoint(0.0F, 18.5F, 6.5F);
		setRotationAngle(bone7, -0.1745F, 0.0F, 0.0F);
		magazine.addChild(bone7);
		bone7.cubeList.add(new ModelBox(bone7, 32, 0, -2.4994F, 1.1014F, -4.7287F, 5, 6, 10, 0.0F, false));

		bolt = new ModelRenderer(this);
		bolt.setRotationPoint(0.0F, 18.9F, 26.0F);
		bolt.cubeList.add(new ModelBox(bolt, 32, 34, 0.5F, -1.0F, -14.0F, 1, 2, 15, 0.0F, false));
		bolt.cubeList.add(new ModelBox(bolt, 32, 40, 0.0F, -1.5F, -14.0F, 1, 3, 15, 0.0F, false));
		bolt.cubeList.add(new ModelBox(bolt, 39, 47, -1.0F, -1.0F, 0.5F, 2, 2, 1, 0.0F, false));
		bolt.cubeList.add(new ModelBox(bolt, 32, 34, -1.5F, -1.0F, -14.0F, 1, 2, 15, 0.0F, false));
		bolt.cubeList.add(new ModelBox(bolt, 32, 40, -1.0F, -1.5F, -14.0F, 1, 3, 15, 0.0F, false));

		bone12 = new ModelRenderer(this);
		bone12.setRotationPoint(0.0F, 0.0F, -0.1F);
		setRotationAngle(bone12, 0.0F, 0.0F, -0.3491F);
		bolt.addChild(bone12);
		bone12.cubeList.add(new ModelBox(bone12, 39, 47, -5.1658F, -1.094F, -1.0F, 6, 2, 2, 0.0F, false));
		bone12.cubeList.add(new ModelBox(bone12, 39, 47, -7.4158F, -1.594F, -1.5F, 3, 3, 3, 0.0F, false));
	}
	
	@Override
	public String textureName() {
		return "m24";
	}
	
	@Override
	public void initAnimations()
	{
		initAimAnimation(-0.56f, 0.265f, 0.335f);
		initAimingAnimationStates(0.265f, 0.205f, 0.16f);
		reloadAnimation = new ReloadAnimation(magazine, ReloadStyle.MAGAZINE).initMovement(DEFAULT_PART_ANIMATION);
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
				renderM24(data.isAiming(), stack);
			}
			GlStateManager.popMatrix();
		}
	}
	
	private void renderM24(boolean aim, ItemStack stack)
	{
		GlStateManager.pushMatrix();
		{
			transform.defaultSRTransform();
			GlStateManager.scale(0.79999995, 0.79999995, 0.79999995);
			GlStateManager.translate(0.0, -15.300001, -18.0);
			
			if(aim && enableADS(stack))
			{
				rotateModelForADSRendering();
			}
			
			renderParts();
		}
		GlStateManager.popMatrix();
		
		if(hasSilencer(stack)) renderSilencer(stack);
		if(hasRedDot(stack)) renderRedDot(stack);
		else if(hasHoloSight(stack)) renderHolo(stack);
		else if(has2X(stack)) render2x(stack);
		else if(has4X(stack)) render4x(stack);
		else if(has8X(stack)) render8x(stack);
		else if(has15X(stack)) render15x(stack);
	}
	
	private void renderRedDot(ItemStack stack)
	{
		renderRedDot(-0.5, -2.8, 4, 1f, stack);
	}
	
	private void renderHolo(ItemStack stack)
	{
		renderHolo(-1.95, -3.4, -1, 1f, stack);
	}
	
	private void render2x(ItemStack stack)
	{
		renderScope2X(7.7, 10, -7, 1f, stack);
	}
	
	private void render4x(ItemStack stack)
	{
		renderScope4X(7.7, 10, -7, 1f, stack);
	}
	
	private void render8x(ItemStack stack)
	{
		renderScope8X(0, -2, 8, 1f, stack);
	}
	
	private void render15x(ItemStack stack)
	{
		renderScope15X(0, -1, 3, 1f, stack);
	}
	
	private void renderSilencer(ItemStack stack)
	{
		renderSniperSilencer(0, 1.3, -8.5, 1f, stack);
	}
	
	private void renderParts()
	{
		m24.render(1f);
		magazine.render(1f);
		bolt.render(1f);
	}
}

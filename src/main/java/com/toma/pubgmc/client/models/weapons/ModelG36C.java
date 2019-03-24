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

public class ModelG36C extends ModelGun
{
	private final ModelRenderer base;
	private final ModelRenderer base2;
	private final ModelRenderer mag;
	private final ModelRenderer mag1;
	private final ModelRenderer sh;
	private final ModelRenderer handle;
	private final ModelRenderer stock;
	private final ModelRenderer s1;
	private final ModelRenderer bone;
	private final ModelRenderer bone2;
	private final ModelRenderer trigger;
	private final ModelRenderer base_ironsight;
	private final ModelRenderer sh2;
	private final ModelRenderer ironsight;
	
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
				renderG36C(data.isAiming(), stack);
			}
			GlStateManager.popMatrix();
		}
	}
	
	private void handleAnimations(IPlayerData data, boolean sprint, ItemStack stack)
	{
		if(enableADS(stack))
		{
			if(!hasScopeAtachment(stack) && animation_aim.getFinalY() != 0.188d)
				animation_aim.setYModifier(0.188d);
			else if(hasRedDot(stack) && animation_aim.getFinalY() != 0.106d)
				animation_aim.setYModifier(0.106d);
			else if(hasHoloSight(stack) && animation_aim.getFinalY() != 0.057d)
				animation_aim.setYModifier(0.057d);
			animation_aim.run(data.isAiming());
		}
		
		animation_held.run(sprint);
		animation_reload.run(data.isReloading());
	}
	
	private void renderG36C(boolean aim, ItemStack stack)
	{
		GlStateManager.pushMatrix();
		{
			transform.defaultARTransform();
			GlStateManager.translate(-1.0, 3.0, 17.0);
			if(aim && enableADS(stack)) rotateModelForADSRendering();
			
			renderParts(hasScopeAtachment(stack));
		}
		GlStateManager.popMatrix();
		
		renderRedDot(-1.51, -11.4, 0, 1.3f, stack);
		renderHolo(-2.4, -11.2, -3, 1.3f, stack);
		renderScope2X(6.8, 5.8, -8, 1f, stack);
		renderScope4X(6.8, 5.8, -8, 1f, stack);
		renderVerticalGrip(-2, 3, -5, 1f, stack);
		renderAngledGrip(1.4, 2.4, 2, 1f, stack);
		renderARSilencer(0.6, 0.1, 3.0, 1f, stack);
	}
	
	private void renderParts(boolean hasScope)
	{
		base.render(1f);
		base2.render(1f);
		mag.render(1f);
		sh.render(1f);
		handle.render(1f);
		stock.render(1f);
		trigger.render(1f);
		base_ironsight.render(1f);
		if(!hasScope) ironsight.render(1f);
	}
	
	public ModelG36C()
	{
		animation_aim = new AimAnimation(-0.581d, 0.188d, 0.3d, 1f).setInvertedCoords(true, false, false).setMovementMultiplier(1f, 0.75f, 1.2f);;
		
		textureWidth = 128;
		textureHeight = 128;

		base = new ModelRenderer(this);
		base.setRotationPoint(0.0F, 24.0F, 0.0F);
		base.cubeList.add(new ModelBox(base, 0, 0, -3.0F, -20.0F, -58.0F, 6, 1, 49, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -3.0F, -25.0F, -57.0F, 6, 5, 48, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -1.0F, -23.0F, -75.0F, 2, 2, 18, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -1.5F, -23.5F, -81.0F, 3, 3, 6, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -0.6F, -24.5F, -56.0F, 4, 6, 17, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -3.4F, -24.5F, -56.0F, 3, 6, 17, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -3.0F, -19.0F, -38.0F, 6, 2, 11, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -2.0F, -17.0F, -28.0F, 4, 2, 1, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -3.0F, -19.0F, -27.0F, 6, 1, 17, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -2.0F, -15.0F, -22.0F, 4, 1, 4, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -2.0F, -18.0F, -18.0F, 4, 4, 1, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -2.5F, -25.5F, -56.0F, 5, 5, 45, 0.0F, false));

		base2 = new ModelRenderer(this);
		base2.setRotationPoint(0.0F, 24.0F, 0.0F);
		setRotationAngle(base2, -0.1745F, 0.0F, 0.0F);
		base2.cubeList.add(new ModelBox(base2, 0, 0, -3.0F, -14.5F, -60.6F, 6, 5, 1, 0.0F, false));

		mag = new ModelRenderer(this);
		mag.setRotationPoint(0.0F, 24.0F, 0.0F);
		setRotationAngle(mag, -0.0873F, 0.0F, 0.0F);
		mag.cubeList.add(new ModelBox(mag, 0, 0, -2.5F, -13.0F, -37.5F, 5, 10, 8, 0.0F, false));
		mag.cubeList.add(new ModelBox(mag, 0, 0, 1.8F, -13.0F, -37.5F, 1, 10, 2, 0.0F, false));
		mag.cubeList.add(new ModelBox(mag, 0, 0, 1.8F, -13.0F, -31.5F, 1, 10, 2, 0.0F, false));
		mag.cubeList.add(new ModelBox(mag, 0, 0, 1.8F, -13.0F, -34.5F, 1, 10, 2, 0.0F, false));
		mag.cubeList.add(new ModelBox(mag, 0, 0, -2.8F, -13.0F, -31.5F, 1, 10, 2, 0.0F, false));
		mag.cubeList.add(new ModelBox(mag, 0, 0, -2.8F, -13.0F, -34.5F, 1, 10, 2, 0.0F, false));
		mag.cubeList.add(new ModelBox(mag, 0, 0, -2.8F, -13.0F, -37.5F, 1, 10, 2, 0.0F, false));

		mag1 = new ModelRenderer(this);
		mag1.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(mag1, -0.1745F, 0.0F, 0.0F);
		mag.addChild(mag1);
		mag1.cubeList.add(new ModelBox(mag1, 0, 0, -2.5F, 2.0F, -37.6F, 5, 8, 8, 0.0F, false));
		mag1.cubeList.add(new ModelBox(mag1, 0, 0, 1.8F, 2.0F, -31.6F, 1, 8, 2, 0.0F, false));
		mag1.cubeList.add(new ModelBox(mag1, 0, 0, 1.8F, 2.0F, -34.6F, 1, 8, 2, 0.0F, false));
		mag1.cubeList.add(new ModelBox(mag1, 0, 0, 1.8F, 2.0F, -37.6F, 1, 8, 2, 0.0F, false));
		mag1.cubeList.add(new ModelBox(mag1, 0, 0, 1.8F, 8.0F, -35.6F, 1, 2, 4, 0.0F, false));
		mag1.cubeList.add(new ModelBox(mag1, 0, 0, -2.8F, 8.0F, -37.6F, 1, 2, 8, 0.0F, false));
		mag1.cubeList.add(new ModelBox(mag1, 0, 0, -2.8F, 3.0F, -37.6F, 1, 5, 2, 0.0F, false));
		mag1.cubeList.add(new ModelBox(mag1, 0, 0, -2.8F, 2.0F, -34.6F, 1, 6, 2, 0.0F, false));
		mag1.cubeList.add(new ModelBox(mag1, 0, 0, -2.8F, 2.0F, -31.6F, 1, 6, 2, 0.0F, false));

		sh = new ModelRenderer(this);
		sh.setRotationPoint(0.0F, 24.0F, 0.0F);
		setRotationAngle(sh, -0.0873F, 0.0F, 0.0F);
		sh.cubeList.add(new ModelBox(sh, 0, 0, -3.0F, -16.0F, -38.0F, 6, 4, 9, 0.0F, false));
		sh.cubeList.add(new ModelBox(sh, 0, 0, -2.0F, -13.0F, -29.0F, 4, 1, 6, 0.0F, false));

		handle = new ModelRenderer(this);
		handle.setRotationPoint(0.0F, 24.0F, 0.0F);
		setRotationAngle(handle, 0.1745F, 0.0F, 0.0F);
		handle.cubeList.add(new ModelBox(handle, 0, 0, -2.5F, -21.0F, -14.0F, 5, 14, 7, 0.0F, false));
		handle.cubeList.add(new ModelBox(handle, 0, 0, -2.5F, -8.0F, -14.7F, 5, 1, 7, 0.0F, false));

		stock = new ModelRenderer(this);
		stock.setRotationPoint(0.0F, 24.0F, 0.0F);
		stock.cubeList.add(new ModelBox(stock, 0, 0, -2.5F, -23.75F, -2.0F, 5, 1, 19, 0.0F, false));
		stock.cubeList.add(new ModelBox(stock, 0, 0, -2.5F, -22.75F, -3.0F, 5, 2, 1, 0.0F, false));
		stock.cubeList.add(new ModelBox(stock, 0, 0, -2.5F, -22.75F, 16.0F, 5, 11, 1, 0.0F, false));
		stock.cubeList.add(new ModelBox(stock, 0, 0, -2.5F, -20.75F, -8.0F, 5, 1, 6, 0.0F, false));
		stock.cubeList.add(new ModelBox(stock, 0, 0, -2.5F, -23.75F, -8.0F, 5, 3, 1, 0.0F, false));
		stock.cubeList.add(new ModelBox(stock, 0, 0, -2.5F, -23.75F, -8.0F, 5, 1, 6, 0.0F, false));
		stock.cubeList.add(new ModelBox(stock, 0, 0, -2.5F, -24.75F, -9.0F, 5, 5, 1, 0.0F, false));
		stock.cubeList.add(new ModelBox(stock, 0, 0, -2.5F, -20.75F, -2.0F, 5, 1, 6, 0.0F, false));
		stock.cubeList.add(new ModelBox(stock, 0, 0, -3.0F, -24.0F, 17.0F, 6, 13, 2, 0.0F, false));

		s1 = new ModelRenderer(this);
		s1.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(s1, -0.1745F, 0.0F, 0.0F);
		stock.addChild(s1);
		s1.cubeList.add(new ModelBox(s1, 0, 0, -2.5F, -23.0F, -13.0F, 5, 1, 7, 0.0F, false));

		bone = new ModelRenderer(this);
		bone.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(bone, 0.3491F, 0.0F, 0.0F);
		stock.addChild(bone);
		bone.cubeList.add(new ModelBox(bone, 0, 0, -2.5F, -11.7F, 18.5977F, 5, 6, 1, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 0, 0, -2.5F, -10.92F, 19.5977F, 5, 4, 1, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 0, 0, -2.5F, -10.92F, 20.5977F, 5, 1, 1, 0.0F, false));

		bone2 = new ModelRenderer(this);
		bone2.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(bone2, 0.9599F, 0.0F, 0.0F);
		bone.addChild(bone2);
		bone2.cubeList.add(new ModelBox(bone2, 0, 0, -2.5F, -1.7F, 20.0F, 5, 13, 1, 0.0F, false));

		trigger = new ModelRenderer(this);
		trigger.setRotationPoint(0.0F, 24.0F, 0.0F);
		setRotationAngle(trigger, -0.0873F, 0.0F, 0.0F);
		trigger.cubeList.add(new ModelBox(trigger, 0, 0, -1.0F, -17.0F, -23.0F, 2, 3, 1, 0.0F, false));

		base_ironsight = new ModelRenderer(this);
		base_ironsight.setRotationPoint(0.0F, 24.0F, 0.0F);
		base_ironsight.cubeList.add(new ModelBox(base_ironsight, 0, 0, -2.5F, -29.0F, -37.4F, 5, 1, 25, 0.0F, false));
		base_ironsight.cubeList.add(new ModelBox(base_ironsight, 0, 0, -2.5F, -28.5F, -37.4F, 5, 1, 25, 0.0F, false));
		base_ironsight.cubeList.add(new ModelBox(base_ironsight, 0, 0, -2.5F, -27.5F, -20.4F, 5, 2, 8, 0.0F, false));
		base_ironsight.cubeList.add(new ModelBox(base_ironsight, 0, 0, -2.5F, -28.5F, -25.4F, 5, 2, 8, 0.0F, false));
		base_ironsight.cubeList.add(new ModelBox(base_ironsight, 0, 0, -2.5F, -29.3F, -14.4F, 5, 1, 1, 0.0F, false));
		base_ironsight.cubeList.add(new ModelBox(base_ironsight, 0, 0, -2.0F, -29.65F, -14.4F, 4, 1, 1, 0.0F, false));
		base_ironsight.cubeList.add(new ModelBox(base_ironsight, 0, 0, -2.0F, -29.65F, -16.4F, 4, 1, 1, 0.0F, false));
		base_ironsight.cubeList.add(new ModelBox(base_ironsight, 0, 0, -2.5F, -29.3F, -16.4F, 5, 1, 1, 0.0F, false));
		base_ironsight.cubeList.add(new ModelBox(base_ironsight, 0, 0, -2.0F, -29.65F, -18.4F, 4, 1, 1, 0.0F, false));
		base_ironsight.cubeList.add(new ModelBox(base_ironsight, 0, 0, -2.5F, -29.3F, -18.4F, 5, 1, 1, 0.0F, false));
		base_ironsight.cubeList.add(new ModelBox(base_ironsight, 0, 0, -2.0F, -29.65F, -20.4F, 4, 1, 1, 0.0F, false));
		base_ironsight.cubeList.add(new ModelBox(base_ironsight, 0, 0, -2.5F, -29.3F, -20.4F, 5, 1, 1, 0.0F, false));
		base_ironsight.cubeList.add(new ModelBox(base_ironsight, 0, 0, -2.0F, -29.65F, -22.4F, 4, 1, 1, 0.0F, false));
		base_ironsight.cubeList.add(new ModelBox(base_ironsight, 0, 0, -2.5F, -29.3F, -22.4F, 5, 1, 1, 0.0F, false));
		base_ironsight.cubeList.add(new ModelBox(base_ironsight, 0, 0, -2.0F, -29.65F, -24.4F, 4, 1, 1, 0.0F, false));
		base_ironsight.cubeList.add(new ModelBox(base_ironsight, 0, 0, -2.5F, -29.3F, -24.4F, 5, 1, 1, 0.0F, false));
		base_ironsight.cubeList.add(new ModelBox(base_ironsight, 0, 0, -2.0F, -29.65F, -26.4F, 4, 1, 1, 0.0F, false));
		base_ironsight.cubeList.add(new ModelBox(base_ironsight, 0, 0, -2.5F, -29.3F, -26.4F, 5, 1, 1, 0.0F, false));
		base_ironsight.cubeList.add(new ModelBox(base_ironsight, 0, 0, -2.0F, -29.65F, -28.4F, 4, 1, 1, 0.0F, false));
		base_ironsight.cubeList.add(new ModelBox(base_ironsight, 0, 0, -2.5F, -29.3F, -28.4F, 5, 1, 1, 0.0F, false));
		base_ironsight.cubeList.add(new ModelBox(base_ironsight, 0, 0, -2.0F, -29.65F, -30.4F, 4, 1, 1, 0.0F, false));
		base_ironsight.cubeList.add(new ModelBox(base_ironsight, 0, 0, -2.5F, -29.3F, -30.4F, 5, 1, 1, 0.0F, false));
		base_ironsight.cubeList.add(new ModelBox(base_ironsight, 0, 0, -2.0F, -29.65F, -32.4F, 4, 1, 1, 0.0F, false));
		base_ironsight.cubeList.add(new ModelBox(base_ironsight, 0, 0, -2.5F, -29.3F, -32.4F, 5, 1, 1, 0.0F, false));
		base_ironsight.cubeList.add(new ModelBox(base_ironsight, 0, 0, -2.0F, -29.65F, -34.4F, 4, 1, 1, 0.0F, false));
		base_ironsight.cubeList.add(new ModelBox(base_ironsight, 0, 0, -2.5F, -29.3F, -34.4F, 5, 1, 1, 0.0F, false));
		base_ironsight.cubeList.add(new ModelBox(base_ironsight, 0, 0, -2.0F, -29.65F, -36.4F, 4, 1, 1, 0.0F, false));
		base_ironsight.cubeList.add(new ModelBox(base_ironsight, 0, 0, -2.5F, -29.3F, -36.4F, 5, 1, 1, 0.0F, false));

		sh2 = new ModelRenderer(this);
		sh2.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(sh2, 0.7854F, 0.0F, 0.0F);
		base_ironsight.addChild(sh2);
		sh2.cubeList.add(new ModelBox(sh2, 0, 0, -2.5F, -47.0F, -12.0F, 5, 2, 6, 0.0F, false));

		ironsight = new ModelRenderer(this);
		ironsight.setRotationPoint(0.0F, 24.0F, 0.0F);
		ironsight.cubeList.add(new ModelBox(ironsight, 0, 0, -2.5F, -29.7F, -19.4F, 5, 1, 7, 0.0F, false));
		ironsight.cubeList.add(new ModelBox(ironsight, 0, 0, -2.5F, -30.0F, -19.4F, 1, 1, 7, 0.0F, false));
		ironsight.cubeList.add(new ModelBox(ironsight, 0, 0, 1.5F, -30.0F, -19.4F, 1, 1, 7, 0.0F, false));
		ironsight.cubeList.add(new ModelBox(ironsight, 0, 0, 1.5F, -30.5F, -19.4F, 1, 1, 6, 0.0F, false));
		ironsight.cubeList.add(new ModelBox(ironsight, 0, 0, 1.5F, -31.0F, -18.4F, 1, 1, 4, 0.0F, false));
		ironsight.cubeList.add(new ModelBox(ironsight, 0, 0, -2.5F, -31.0F, -18.4F, 1, 1, 4, 0.0F, false));
		ironsight.cubeList.add(new ModelBox(ironsight, 0, 0, -2.5F, -30.5F, -19.4F, 1, 1, 6, 0.0F, false));
		ironsight.cubeList.add(new ModelBox(ironsight, 0, 0, -2.5F, -29.7F, -36.4F, 5, 1, 5, 0.0F, false));
		ironsight.cubeList.add(new ModelBox(ironsight, 0, 0, -2.5F, -31.0F, -36.0F, 1, 2, 4, 0.0F, false));
		ironsight.cubeList.add(new ModelBox(ironsight, 0, 0, 1.5F, -31.0F, -36.0F, 1, 2, 4, 0.0F, false));
		ironsight.cubeList.add(new ModelBox(ironsight, 0, 0, 1.5F, -31.5F, -35.5F, 1, 1, 3, 0.0F, false));
		ironsight.cubeList.add(new ModelBox(ironsight, 0, 0, -2.5F, -31.5F, -35.5F, 1, 1, 3, 0.0F, false));
		ironsight.cubeList.add(new ModelBox(ironsight, 0, 0, -0.5F, -31.4F, -35.0F, 1, 2, 1, 0.0F, false));
		ironsight.cubeList.add(new ModelBox(ironsight, 0, 0, -1.35F, -31.0F, -16.0F, 1, 2, 1, 0.0F, false));
		ironsight.cubeList.add(new ModelBox(ironsight, 0, 0, 0.35F, -31.0F, -16.0F, 1, 2, 1, 0.0F, false));
		ironsight.cubeList.add(new ModelBox(ironsight, 0, 0, -0.5F, -30.0F, -16.0F, 1, 1, 1, 0.0F, false));
	}
}

package com.toma.pubgmc.client.models.weapons;

import com.toma.pubgmc.animation.HeldAnimation;
import com.toma.pubgmc.animation.HeldAnimation.HeldStyle;
import com.toma.pubgmc.client.models.ModelGun;
import com.toma.pubgmc.client.util.ModelTransformationHelper;
import com.toma.pubgmc.common.capability.IPlayerData;
import com.toma.pubgmc.common.capability.IPlayerData.PlayerDataProvider;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.item.ItemStack;

public class ModelFlareGun extends ModelGun
{
	private final ModelRenderer core;
	private final ModelRenderer barrel;
	private final ModelRenderer base;
	private final ModelRenderer basesh;
	private final ModelRenderer handle;
	private final ModelRenderer handle2;
	private final ModelRenderer trigger;

	public ModelFlareGun() 
	{
		super();
		
		textureWidth = 128;
		textureHeight = 128;
		
		core = new ModelRenderer(this);
		core.setRotationPoint(0, 0f, 0);

		barrel = new ModelRenderer(this);
		barrel.setRotationPoint(0.0F, 24.0F, 0.0F);
		barrel.cubeList.add(new ModelBox(barrel, 0, 0, -3.0F, -20.0F, -6.0F, 6, 6, 2, 0.0F, false));
		barrel.cubeList.add(new ModelBox(barrel, 0, 0, -3.0F, -15.0F, -29.0F, 6, 1, 23, 0.0F, false));
		barrel.cubeList.add(new ModelBox(barrel, 0, 0, -3.0F, -20.0F, -29.0F, 6, 1, 23, 0.0F, false));
		barrel.cubeList.add(new ModelBox(barrel, 0, 0, 2.0F, -19.0F, -29.0F, 1, 4, 23, 0.0F, false));
		barrel.cubeList.add(new ModelBox(barrel, 0, 0, -3.0F, -19.0F, -29.0F, 1, 4, 23, 0.0F, false));
		barrel.cubeList.add(new ModelBox(barrel, 0, 0, -0.5F, -20.7F, -27.0F, 1, 1, 2, 0.0F, false));
		core.addChild(barrel);

		base = new ModelRenderer(this);
		base.setRotationPoint(0.0F, 24.0F, 0.0F);
		base.cubeList.add(new ModelBox(base, 0, 0, -2.0F, -14.0F, -17.0F, 4, 5, 19, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -2.0F, -19.0F, -4.0F, 4, 5, 1, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -1.0F, -9.0F, -1.0F, 2, 2, 1, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -1.0F, -9.0F, -6.0F, 2, 2, 1, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -1.0F, -7.0F, -5.0F, 2, 1, 4, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 32, -2.2F, -13.5F, -16.5F, 1, 1, 1, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 32, -2.2F, -13.5F, -6.5F, 1, 1, 1, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 32, -2.2F, -17.5F, -3.5F, 1, 1, 1, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 32, 1.2F, -13.5F, -16.5F, 1, 1, 1, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 32, 1.2F, -13.5F, -6.5F, 1, 1, 1, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 32, 1.2F, -17.5F, -3.5F, 1, 1, 1, 0.0F, false));
		core.addChild(base);

		basesh = new ModelRenderer(this);
		basesh.setRotationPoint(0.0F, 24.0F, 0.0F);
		setRotationAngle(basesh, 0.7854F, 0.0F, 0.0F);
		basesh.cubeList.add(new ModelBox(basesh, 0, 0, -2.0F, -15.6F, 7.3F, 4, 7, 4, 0.0F, false));
		basesh.cubeList.add(new ModelBox(basesh, 0, 32, -0.5F, -14.6F, 10.8F, 1, 2, 2, 0.0F, false));
		core.addChild(basesh);

		handle = new ModelRenderer(this);
		handle.setRotationPoint(0.0F, 24.0F, 0.0F);
		setRotationAngle(handle, -0.3491F, 0.0F, 0.0F);
		handle.cubeList.add(new ModelBox(handle, 0, 0, -2.0F, -13.77F, -3.0F, 4, 5, 6, 0.0F, false));
		handle.cubeList.add(new ModelBox(handle, 0, 32, 1.2F, -13.1F, -4.0F, 1, 4, 7, 0.0F, false));
		handle.cubeList.add(new ModelBox(handle, 0, 32, -2.2F, -13.1F, -4.0F, 1, 4, 7, 0.0F, false));
		core.addChild(handle);

		handle2 = new ModelRenderer(this);
		handle2.setRotationPoint(0.0F, 24.0F, 0.0F);
		setRotationAngle(handle2, -1.1345F, 0.0F, 0.0F);
		handle2.cubeList.add(new ModelBox(handle2, 0, 0, -2.0F, -11.8F, -7.7F, 4, 5, 11, 0.0F, false));
		handle2.cubeList.add(new ModelBox(handle2, 0, 32, 1.2F, -11.3F, -7.2F, 1, 4, 10, 0.0F, false));
		handle2.cubeList.add(new ModelBox(handle2, 0, 32, -2.2F, -11.3F, -7.2F, 1, 4, 10, 0.0F, false));
		core.addChild(handle2);

		trigger = new ModelRenderer(this);
		trigger.setRotationPoint(0.0F, 24.0F, 0.0F);
		setRotationAngle(trigger, -0.1745F, 0.0F, 0.0F);
		trigger.cubeList.add(new ModelBox(trigger, 0, 32, -1.0F, -9.0F, -4.0F, 2, 2, 1, 0.0F, false));
		core.addChild(trigger);
	}
	
	@Override
	public String textureName() {
		return "flare_gun";
	}
	
	@Override
	public void initAnimations()
	{
		initAimAnimation(-0.57f, 0.215f, 0.225f);
		initAimingAnimationStates(0.215f);
		heldAnimation = new HeldAnimation(HeldStyle.SMALL);
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
				renderFlareGun(data.isAiming());
			}
			GlStateManager.popMatrix();
		}
	}
	
	private void renderFlareGun(boolean aim)
	{
		GlStateManager.pushMatrix();
		ModelTransformationHelper.defaultPistolTransform();
		GlStateManager.translate(-0.4, 2.9, 1.3);
		if(aim) rotateModelForADSRendering();
		renderParts();
		GlStateManager.popMatrix();
	}
	
	private void renderParts()
	{
		core.render(1f);
	}
}

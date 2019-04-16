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

public class ModelP18C extends ModelGun
{
	private final ModelRenderer base;
	private final ModelRenderer handle;
	private final ModelRenderer magazine;
	private final ModelRenderer trigger;

	public ModelP18C()
	{
		initAimAnimation(-0.585f, 0.22f, 0.235f);
		initAimingAnimationStates(0.22f, 0.14f, 0f);
		
		textureWidth = 128;
		textureHeight = 128;

		base = new ModelRenderer(this);
		base.setRotationPoint(0.0F, 24.0F, 0.0F);
		base.cubeList.add(new ModelBox(base, 0, 0, -3.0F, -15.0F, -23.0F, 6, 1, 26, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 33, -3.0F, -18.0F, -23.0F, 6, 3, 26, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -2.0F, -11.0F, -6.0F, 4, 1, 4, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -2.0F, -12.0F, -7.0F, 4, 1, 1, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -2.0F, -14.0F, -8.0F, 4, 2, 1, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 33, -2.5F, -18.4F, 0.0F, 5, 1, 2, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 33, -2.5F, -19.0F, 0.5F, 2, 1, 1, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 33, 0.5F, -19.0F, 0.5F, 2, 1, 1, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 33, -0.5F, -18.8F, -22.5F, 1, 1, 2, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 33, -1.0F, -17.4F, -23.3F, 2, 2, 1, 0.0F, false));

		handle = new ModelRenderer(this);
		handle.setRotationPoint(0.0F, 24.0F, 0.0F);
		setRotationAngle(handle, 0.1745F, 0.0F, 0.0F);
		handle.cubeList.add(new ModelBox(handle, 0, 0, -3.0F, -14.4F, -0.23F, 6, 13, 5, 0.0F, false));

		magazine = new ModelRenderer(this);
		magazine.setRotationPoint(0.0F, 24.0F, 0.0F);
		setRotationAngle(magazine, 0.1745F, 0.0F, 0.0F);
		magazine.cubeList.add(new ModelBox(magazine, 0, 33, -2.5F, -2.0F, 0.35F, 5, 1, 4, 0.0F, false));
		magazine.cubeList.add(new ModelBox(magazine, 0, 33, -2.5F, -14.0F, 1.35F, 5, 12, 3, 0.0F, false));

		trigger = new ModelRenderer(this);
		trigger.setRotationPoint(0.0F, 24.0F, 0.0F);
		setRotationAngle(trigger, -0.1745F, 0.0F, 0.0F);
		trigger.cubeList.add(new ModelBox(trigger, 0, 50, -1.0F, -13.5F, -7.0F, 2, 2, 1, 0.0F, false));
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
				renderP18C(data.isAiming(), stack);
			}
			GlStateManager.popMatrix();
		}
	}
	
	private void renderP18C(boolean aim, ItemStack stack)
	{
		GlStateManager.pushMatrix();
		{
			transform.defaultPistolTransform();
			GlStateManager.translate(-0.9, 1.4, 0.0);
			if(aim) rotateModelForADSRendering();
			renderParts();
		}
		GlStateManager.popMatrix();
		
		renderRedDot(-1.6, -10.4, -1.1, 1.4f, stack);
		renderPistolSilencer(0.385, -2.3, -3, 1.15f, stack);
	}
	
	private void renderParts()
	{
		base.render(1f);
		handle.render(1f);
		magazine.render(1f);
		trigger.render(1f);
	}
	
	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) 
	{
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}

package com.toma.pubgmc.client.models.weapons;

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

public class ModelS12K extends ModelGun
{
	private final ModelRenderer base;
	private final ModelRenderer mag;
	private final ModelRenderer magsh;
	private final ModelRenderer trigger;
	private final ModelRenderer handle;
	private final ModelRenderer stock;
	private final ModelRenderer rail;
	private final ModelRenderer irns;

	public ModelS12K()
	{
		super();
		
		textureWidth = 128;
		textureHeight = 128;

		base = new ModelRenderer(this);
		base.setRotationPoint(0.0F, 24.0F, 0.0F);
		base.cubeList.add(new ModelBox(base, 0, 0, -3.0F, -25.0F, -30.0F, 6, 7, 48, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -1.0F, -22.0F, -41.0F, 2, 2, 11, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -1.5F, -22.5F, -46.0F, 3, 3, 5, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -2.0F, -18.0F, 1.0F, 4, 3, 1, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -2.0F, -15.0F, 2.0F, 4, 1, 7, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -2.0F, -18.0F, 9.0F, 4, 3, 1, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -3.0F, -25.0F, 18.0F, 6, 7, 1, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -3.0F, -25.5F, -19.0F, 6, 1, 37, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -2.5F, -24.5F, 19.0F, 5, 4, 25, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -2.5F, -21.5F, 40.0F, 5, 14, 4, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -2.5F, -20.5F, 23.0F, 5, 4, 18, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -2.5F, -17.5F, 32.0F, 5, 5, 9, 0.0F, false));

		mag = new ModelRenderer(this);
		mag.setRotationPoint(0.0F, 24.0F, 0.0F);
		mag.cubeList.add(new ModelBox(mag, 0, 0, -2.0F, -18.0F, -9.0F, 4, 7, 10, 0.0F, false));

		magsh = new ModelRenderer(this);
		magsh.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(magsh, -0.1745F, 0.0F, 0.0F);
		mag.addChild(magsh);
		magsh.cubeList.add(new ModelBox(magsh, 0, 0, -2.0F, -11.0F, -11.0F, 4, 8, 10, 0.0F, false));

		trigger = new ModelRenderer(this);
		trigger.setRotationPoint(0.0F, 24.0F, 0.0F);
		setRotationAngle(trigger, -0.1745F, 0.0F, 0.0F);
		trigger.cubeList.add(new ModelBox(trigger, 0, 0, -1.0F, -19.0F, 3.0F, 2, 2, 1, 0.0F, false));

		handle = new ModelRenderer(this);
		handle.setRotationPoint(0.0F, 24.0F, 0.0F);
		setRotationAngle(handle, 0.1745F, 0.0F, 0.0F);
		handle.cubeList.add(new ModelBox(handle, 0, 0, -2.0F, -16.0F, 13.0F, 4, 13, 7, 0.0F, false));

		stock = new ModelRenderer(this);
		stock.setRotationPoint(0.0F, 24.0F, 0.0F);
		setRotationAngle(stock, -0.4363F, 0.0F, 0.0F);
		stock.cubeList.add(new ModelBox(stock, 0, 0, -2.5F, -28.5F, 7.0F, 5, 4, 27, 0.0F, false));

		rail = new ModelRenderer(this);
		rail.setRotationPoint(0.0F, 24.0F, 0.0F);
		rail.cubeList.add(new ModelBox(rail, 0, 0, -2.5F, -26.0F, -18.0F, 5, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 0, -2.5F, -26.0F, -16.0F, 5, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 0, -2.5F, -26.0F, -14.0F, 5, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 0, -2.5F, -26.0F, -12.0F, 5, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 0, -2.5F, -26.0F, -10.0F, 5, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 0, -2.5F, -26.0F, -8.0F, 5, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 0, -2.5F, -26.0F, -6.0F, 5, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 0, -2.5F, -26.0F, -4.0F, 5, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 0, -2.5F, -26.0F, -2.0F, 5, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 0, -2.5F, -26.0F, 0.0F, 5, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 0, -2.5F, -26.0F, 2.0F, 5, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 0, -2.5F, -26.0F, 4.0F, 5, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 0, -2.5F, -26.0F, 6.0F, 5, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 0, -2.5F, -26.0F, 8.0F, 5, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 0, -2.5F, -26.0F, 10.0F, 5, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 0, -2.5F, -26.0F, 12.0F, 5, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 0, -2.5F, -26.0F, 14.0F, 5, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 0, -2.5F, -26.0F, 16.0F, 5, 1, 1, 0.0F, false));

		irns = new ModelRenderer(this);
		irns.setRotationPoint(0.0F, 24.0F, 0.0F);
		irns.cubeList.add(new ModelBox(irns, 0, 0, -0.5F, -26.5F, -28.0F, 1, 2, 2, 0.0F, false));
	}
	
	@Override
	public String textureName() {
		return "s12k";
	}
	
	@Override
	public void initAnimations()
	{
		initAimAnimation(-0.535f, 0.27f, 0.235f);
		initAimingAnimationStates(0.27f, 0.195f, 0.15f);
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
				renderWeapon(data.isAiming(), stack);
			}
			GlStateManager.popMatrix();
		}
	}
	
	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z)
	{
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
	
	private void renderParts()
	{
		base.render(1f);
		mag.render(1f);
		trigger.render(1f);
		handle.render(1f);
		stock.render(1f);
		rail.render(1f);
		irns.render(1f);
	}
	
	private void renderWeapon(boolean aim, ItemStack stack)
	{
		GlStateManager.pushMatrix();
		{
			ModelTransformationHelper.defaultShotgunTransform();
			GlStateManager.translate(-0.8, 9.9, -7.5);
			if(aim && enableADS(stack)) rotateModelForADSRendering();
			renderParts();
		}
		GlStateManager.popMatrix();
		
		renderRedDot(0.78, -7, 5, 1.3f, stack);
		renderHolo(-0.4, -7.4, 0, 1.3f, stack);
		renderScope2X(9, 10, -3, 1f, stack);
		renderScope4X(9, 10, -3, 1f, stack);
	}
}

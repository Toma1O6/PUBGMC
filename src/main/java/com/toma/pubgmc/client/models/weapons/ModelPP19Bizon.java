package com.toma.pubgmc.client.models.weapons;

import com.toma.pubgmc.animation.HeldAnimation;
import com.toma.pubgmc.animation.HeldAnimation.HeldStyle;
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

public class ModelPP19Bizon extends ModelGun
{
	private final ModelRenderer base;
	private final ModelRenderer mag;
	private final ModelRenderer trigger;
	private final ModelRenderer handle;
	private final ModelRenderer base2;
	private final ModelRenderer stock;
	private final ModelRenderer stock2;
	private final ModelRenderer rail;
	private final ModelRenderer ironsight;
	private final ModelRenderer r;
	private final ModelRenderer l;

	public ModelPP19Bizon()
	{
		super();
		
		textureWidth = 128;
		textureHeight = 128;

		base = new ModelRenderer(this);
		base.setRotationPoint(0.0F, 24.0F, 0.0F);
		base.cubeList.add(new ModelBox(base, 0, 0, -3.0F, -17.0F, -29.0F, 6, 4, 30, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -2.0F, -18.5F, -44.0F, 4, 1, 39, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -2.5F, -18.0F, -29.0F, 5, 1, 24, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -1.0F, -16.5F, -50.0F, 2, 2, 6, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -1.5F, -17.0F, -54.0F, 3, 3, 4, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -2.0F, -16.0F, -44.0F, 4, 3, 15, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -3.0F, -13.0F, -19.0F, 6, 5, 5, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -2.5F, -8.0F, -19.0F, 5, 1, 14, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -3.0F, -13.0F, -8.0F, 6, 5, 3, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -2.5F, -9.0F, -14.0F, 5, 1, 6, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -2.5F, -11.0F, -14.0F, 5, 2, 1, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -2.5F, -10.0F, -13.0F, 5, 1, 1, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -2.0F, -18.0F, -44.0F, 4, 2, 3, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -2.0F, -18.0F, -42.0F, 4, 1, 13, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -2.0F, -17.0F, -31.0F, 4, 1, 2, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -2.0F, -17.0F, -35.0F, 4, 1, 2, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -2.0F, -17.0F, -39.0F, 4, 1, 2, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -3.0F, -15.5F, -42.0F, 6, 2, 13, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -2.5F, -18.0F, -43.0F, 5, 1, 13, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -2.5F, -16.0F, -43.0F, 5, 3, 13, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -2.5F, -17.0F, -43.0F, 5, 1, 2, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -2.5F, -18.0F, -31.0F, 5, 6, 2, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -1.5F, -17.0F, -45.0F, 3, 3, 2, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -3.0F, -13.0F, -5.0F, 6, 6, 6, 0.0F, false));

		mag = new ModelRenderer(this);
		mag.setRotationPoint(0.0F, 24.0F, 0.0F);
		mag.cubeList.add(new ModelBox(mag, 0, 0, -3.5F, -12.0F, -43.0F, 7, 5, 24, 0.0F, false));
		mag.cubeList.add(new ModelBox(mag, 0, 0, -3.0F, -13.0F, -43.0F, 6, 1, 24, 0.0F, false));
		mag.cubeList.add(new ModelBox(mag, 0, 0, -3.0F, -7.0F, -43.0F, 6, 1, 24, 0.0F, false));
		mag.cubeList.add(new ModelBox(mag, 0, 0, 3.0F, -12.0F, -42.0F, 1, 5, 2, 0.0F, false));
		mag.cubeList.add(new ModelBox(mag, 0, 0, -4.0F, -12.0F, -42.0F, 1, 5, 2, 0.0F, false));
		mag.cubeList.add(new ModelBox(mag, 0, 0, -3.5F, -13.0F, -42.0F, 7, 1, 2, 0.0F, false));
		mag.cubeList.add(new ModelBox(mag, 0, 0, -3.5F, -7.0F, -42.0F, 7, 1, 2, 0.0F, false));
		mag.cubeList.add(new ModelBox(mag, 0, 0, -3.0F, -6.5F, -42.0F, 6, 1, 2, 0.0F, false));
		mag.cubeList.add(new ModelBox(mag, 0, 0, -4.0F, -12.0F, -22.0F, 1, 5, 1, 0.0F, false));
		mag.cubeList.add(new ModelBox(mag, 0, 0, -3.5F, -7.0F, -22.0F, 7, 1, 1, 0.0F, false));
		mag.cubeList.add(new ModelBox(mag, 0, 0, -3.0F, -6.5F, -22.0F, 6, 1, 1, 0.0F, false));
		mag.cubeList.add(new ModelBox(mag, 0, 0, -3.5F, -13.0F, -22.0F, 7, 1, 1, 0.0F, false));
		mag.cubeList.add(new ModelBox(mag, 0, 0, 3.0F, -12.0F, -22.0F, 1, 5, 1, 0.0F, false));

		trigger = new ModelRenderer(this);
		trigger.setRotationPoint(0.0F, 24.0F, 0.0F);
		setRotationAngle(trigger, -0.1745F, 0.0F, 0.0F);
		trigger.cubeList.add(new ModelBox(trigger, 0, 0, -1.0F, -12.0F, -12.0F, 2, 3, 1, 0.0F, false));

		handle = new ModelRenderer(this);
		handle.setRotationPoint(0.0F, 24.0F, 0.0F);
		setRotationAngle(handle, 0.2618F, 0.0F, 0.0F);
		handle.cubeList.add(new ModelBox(handle, 0, 0, -3.0F, -9.0F, -3.0F, 6, 14, 6, 0.0F, false));

		base2 = new ModelRenderer(this);
		base2.setRotationPoint(0.0F, 24.0F, 0.0F);
		setRotationAngle(base2, -0.1745F, 0.0F, 0.0F);
		base2.cubeList.add(new ModelBox(base2, 0, 0, -2.5F, -17.0F, -9.0F, 5, 2, 7, 0.0F, false));
		base2.cubeList.add(new ModelBox(base2, 0, 0, -1.0F, -17.5F, -7.0F, 2, 2, 3, 0.0F, false));

		stock = new ModelRenderer(this);
		stock.setRotationPoint(0.0F, 24.0F, 0.0F);
		stock.cubeList.add(new ModelBox(stock, 0, 0, -2.5F, -15.0F, 1.0F, 5, 2, 30, 0.0F, false));
		stock.cubeList.add(new ModelBox(stock, 0, 0, -2.5F, -14.0F, 1.0F, 5, 3, 2, 0.0F, false));
		stock.cubeList.add(new ModelBox(stock, 0, 0, -2.5F, -13.3F, 29.0F, 5, 10, 2, 0.0F, false));

		stock2 = new ModelRenderer(this);
		stock2.setRotationPoint(0.0F, 24.0F, 0.0F);
		setRotationAngle(stock2, -0.2618F, 0.0F, 0.0F);
		stock2.cubeList.add(new ModelBox(stock2, 0, 0, -2.5F, -13.0F, -4.0F, 5, 2, 32, 0.0F, false));

		rail = new ModelRenderer(this);
		rail.setRotationPoint(0.0F, 24.0F, 0.0F);
		rail.cubeList.add(new ModelBox(rail, 0, 0, -1.5F, -18.8F, -8.0F, 3, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 0, -1.5F, -18.8F, -10.0F, 3, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 0, -1.5F, -18.8F, -12.0F, 3, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 0, -1.5F, -18.8F, -14.0F, 3, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 0, -1.5F, -18.8F, -16.0F, 3, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 0, -1.5F, -18.8F, -18.0F, 3, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 0, -1.5F, -18.8F, -20.0F, 3, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 0, -1.5F, -18.8F, -22.0F, 3, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 0, -1.5F, -18.8F, -24.0F, 3, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 0, -1.5F, -18.8F, -26.0F, 3, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 0, -1.5F, -18.8F, -28.0F, 3, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 0, -1.5F, -18.8F, -30.0F, 3, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 0, -1.5F, -18.8F, -32.0F, 3, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 0, -1.5F, -18.8F, -34.0F, 3, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 0, -1.5F, -18.8F, -36.0F, 3, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 0, -1.5F, -18.8F, -38.0F, 3, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 0, -1.5F, -18.8F, -40.0F, 3, 1, 1, 0.0F, false));
		rail.cubeList.add(new ModelBox(rail, 0, 0, -1.5F, -18.8F, -42.0F, 3, 1, 1, 0.0F, false));

		ironsight = new ModelRenderer(this);
		ironsight.setRotationPoint(0.0F, 24.0F, 0.0F);
		ironsight.cubeList.add(new ModelBox(ironsight, 0, 0, -0.5F, -21.0F, -43.5F, 1, 3, 1, 0.0F, false));
		ironsight.cubeList.add(new ModelBox(ironsight, 0, 0, -1.5F, -19.5F, -11.5F, 3, 2, 5, 0.0F, false));

		r = new ModelRenderer(this);
		r.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(r, 0.0F, 0.0F, -0.3491F);
		ironsight.addChild(r);
		r.cubeList.add(new ModelBox(r, 0, 0, 5.0F, -20.0F, -10.5F, 1, 2, 3, 0.0F, false));

		l = new ModelRenderer(this);
		l.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(l, 0.0F, 0.0F, 0.3491F);
		ironsight.addChild(l);
		l.cubeList.add(new ModelBox(l, 0, 0, -6.0F, -20.0F, -10.5F, 1, 2, 3, 0.0F, false));
		this.initAnimations();
	}
	
	@Override
	public void initAnimations()
	{
		initAimAnimation(-0.68f, 0.235f, 0.15f);
		initAimingAnimationStates(0.235f, 0.165f, 0.105f);
		heldAnimation = new HeldAnimation(HeldStyle.SMALL);
		reloadAnimation = new ReloadAnimation(mag, ReloadStyle.MAGAZINE).initMovement(DEFAULT_PART_ANIMATION);
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
				renderBizon(data.isAiming(), stack);
			}
			GlStateManager.popMatrix();
		}
	}
	
	private void renderBizon(boolean aim, ItemStack stack)
	{
		GlStateManager.pushMatrix();
		{
			transform.defaultSMGTransform();
			GlStateManager.translate(-4.0, -2.0, 7.0);
			if(aim && enableADS(stack))
			{
				rotateModelForADSRendering();
			}
			
			renderParts(hasScopeAtachment(stack));
		}
		GlStateManager.popMatrix();
		
		renderRedDot(-6.1, -9.3, -2, 1.4f, stack);
		renderHolo(-5.35, -10.9, -5, 1.6f, stack);
		renderScope2X(1.5, 6.4, -10, 1.1f, stack);
		renderScope4X(1.5, 6.4, -10, 1.1f, stack);
		renderSMGSilencer(3.4, -3.6, -23, 1.2f, stack);
	}
	
	private void renderParts(boolean hasScope)
	{
		base.render(1f);
		mag.render(1f);
		trigger.render(1f);
		handle.render(1f);
		base2.render(1f);
		stock.render(1f);
		stock2.render(1f);
		rail.render(1f);
		if(!hasScope) ironsight.render(1f);
	}
}

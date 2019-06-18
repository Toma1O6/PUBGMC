package com.toma.pubgmc.client.models.weapons;

import com.toma.pubgmc.animation.HeldAnimation;
import com.toma.pubgmc.animation.ReloadAnimation;
import com.toma.pubgmc.animation.HeldAnimation.HeldStyle;
import com.toma.pubgmc.animation.ReloadAnimation.ReloadStyle;
import com.toma.pubgmc.client.models.ModelGun;
import com.toma.pubgmc.common.capability.IPlayerData;
import com.toma.pubgmc.common.capability.IPlayerData.PlayerDataProvider;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class ModelMP5K extends ModelGun {
	
	private final ModelRenderer mp5k;
	private final ModelRenderer core;
	private final ModelRenderer bone;
	private final ModelRenderer bone2;
	private final ModelRenderer bone5;
	private final ModelRenderer bone6;
	private final ModelRenderer bone7;
	private final ModelRenderer bone11;
	private final ModelRenderer magazine;
	private final ModelRenderer bone3;
	private final ModelRenderer bone4;
	private final ModelRenderer stock;
	private final ModelRenderer bone8;
	private final ModelRenderer sight;
	private final ModelRenderer bone9;
	private final ModelRenderer bone10;

	public ModelMP5K() {
		super();
		textureWidth = 128;
		textureHeight = 128;

		mp5k = new ModelRenderer(this);
		mp5k.setRotationPoint(0.0F, 24.0F, 0.0F);

		core = new ModelRenderer(this);
		core.setRotationPoint(0.0F, 0.0F, 0.0F);
		mp5k.addChild(core);
		core.cubeList.add(new ModelBox(core, 0, 0, -4.0F, -23.05F, -22.0F, 6, 7, 34, 0.0F, false));
		core.cubeList.add(new ModelBox(core, 0, 0, 1.13F, -20.9F, -22.0F, 3, 2, 34, 0.0F, false));
		core.cubeList.add(new ModelBox(core, 0, 0, -6.12F, -20.9F, -22.0F, 3, 2, 34, 0.0F, false));
		core.cubeList.add(new ModelBox(core, 0, 0, -3.5F, -16.05F, -11.0F, 5, 2, 23, 0.0F, false));
		core.cubeList.add(new ModelBox(core, 0, 0, -3.5F, -14.05F, -2.0F, 5, 2, 14, 0.0F, false));
		core.cubeList.add(new ModelBox(core, 0, 0, 0.6F, -14.85F, -11.0F, 1, 3, 9, 0.0F, false));
		core.cubeList.add(new ModelBox(core, 0, 0, -3.6F, -14.85F, -11.0F, 1, 3, 9, 0.0F, false));
		core.cubeList.add(new ModelBox(core, 0, 0, -3.5F, -14.85F, -11.1F, 5, 3, 1, 0.0F, false));
		core.cubeList.add(new ModelBox(core, 0, 0, -3.5F, -14.85F, -2.9F, 5, 3, 1, 0.0F, false));
		core.cubeList.add(new ModelBox(core, 0, 0, -2.5F, -8.65F, 1.5F, 3, 1, 5, 0.0F, false));
		core.cubeList.add(new ModelBox(core, 0, 0, 2.53F, -21.9F, -22.0F, 2, 4, 10, 0.0F, false));
		core.cubeList.add(new ModelBox(core, 0, 0, -4.6F, -16.75F, -22.0F, 7, 1, 10, 0.0F, false));
		core.cubeList.add(new ModelBox(core, 0, 0, -6.67F, -21.9F, -22.0F, 2, 4, 10, 0.0F, false));
		core.cubeList.add(new ModelBox(core, 0, 0, -2.5F, -20.8F, -26.0F, 3, 3, 4, 0.0F, false));
		core.cubeList.add(new ModelBox(core, 0, 0, -3.5F, -23.55F, -7.0F, 5, 1, 14, 0.0F, false));

		bone = new ModelRenderer(this);
		bone.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(bone, 0.0F, 0.0F, 0.7854F);
		core.addChild(bone);
		bone.cubeList.add(new ModelBox(bone, 0, 0, -14.8355F, -17.6855F, -22.0F, 3, 2, 34, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 0, 0, -17.6855F, -11.0355F, -22.0F, 3, 2, 34, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 0, 0, -17.3855F, -9.9355F, -22.0F, 3, 2, 10, 0.0F, false));

		bone2 = new ModelRenderer(this);
		bone2.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(bone2, 0.0F, 0.0F, -0.7854F);
		core.addChild(bone2);
		bone2.cubeList.add(new ModelBox(bone2, 0, 0, 13.2355F, -12.4355F, -22.0F, 3, 2, 34, 0.0F, false));
		bone2.cubeList.add(new ModelBox(bone2, 0, 0, 10.4355F, -19.0855F, -22.0F, 3, 2, 34, 0.0F, false));
		bone2.cubeList.add(new ModelBox(bone2, 0, 0, 12.8355F, -10.4355F, -22.0F, 3, 1, 10, 0.0F, false));

		bone5 = new ModelRenderer(this);
		bone5.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(bone5, 0.3491F, 0.0F, 0.0F);
		core.addChild(bone5);
		bone5.cubeList.add(new ModelBox(bone5, 0, 0, -2.5F, -11.7F, 4.0F, 3, 5, 1, 0.0F, false));

		bone6 = new ModelRenderer(this);
		bone6.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(bone6, 0.1745F, 0.0F, 0.0F);
		core.addChild(bone6);
		bone6.cubeList.add(new ModelBox(bone6, 0, 0, -3.5F, -11.05F, 9.0F, 5, 12, 5, 0.0F, false));
		bone6.cubeList.add(new ModelBox(bone6, 0, 0, -3.5F, -0.05F, 8.6F, 5, 1, 5, 0.0F, false));

		bone7 = new ModelRenderer(this);
		bone7.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(bone7, 0.5236F, 0.0F, 0.0F);
		core.addChild(bone7);
		bone7.cubeList.add(new ModelBox(bone7, 0, 0, -2.5F, -4.4F, 9.4F, 3, 1, 2, 0.0F, false));

		bone11 = new ModelRenderer(this);
		bone11.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(bone11, -0.2618F, 0.0F, 0.0F);
		core.addChild(bone11);
		bone11.cubeList.add(new ModelBox(bone11, 0, 0, -2.0F, -13.8F, 1.6F, 2, 3, 1, 0.0F, false));

		magazine = new ModelRenderer(this);
		magazine.setRotationPoint(0.0F, 0.0F, 0.0F);
		mp5k.addChild(magazine);
		magazine.cubeList.add(new ModelBox(magazine, 0, 0, -2.5F, -13.0F, -10.0F, 3, 4, 7, 0.0F, false));
		magazine.cubeList.add(new ModelBox(magazine, 0, 0, -0.3F, -13.0F, -8.0F, 1, 4, 5, 0.0F, false));
		magazine.cubeList.add(new ModelBox(magazine, 0, 0, -0.3F, -13.0F, -10.0F, 1, 4, 1, 0.0F, false));
		magazine.cubeList.add(new ModelBox(magazine, 0, 0, -2.7F, -13.0F, -8.0F, 1, 4, 5, 0.0F, false));
		magazine.cubeList.add(new ModelBox(magazine, 0, 0, -2.7F, -13.0F, -10.0F, 1, 4, 1, 0.0F, false));

		bone3 = new ModelRenderer(this);
		bone3.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(bone3, -0.0873F, 0.0F, 0.0F);
		magazine.addChild(bone3);
		bone3.cubeList.add(new ModelBox(bone3, 0, 0, -2.5F, -8.8F, -10.8F, 3, 4, 7, 0.0F, false));
		bone3.cubeList.add(new ModelBox(bone3, 0, 0, -0.3F, -8.8F, -8.8F, 1, 4, 5, 0.0F, false));
		bone3.cubeList.add(new ModelBox(bone3, 0, 0, -0.3F, -8.8F, -10.8F, 1, 4, 1, 0.0F, false));
		bone3.cubeList.add(new ModelBox(bone3, 0, 0, -2.7F, -8.8F, -8.8F, 1, 4, 5, 0.0F, false));
		bone3.cubeList.add(new ModelBox(bone3, 0, 0, -2.7F, -8.8F, -10.8F, 1, 4, 1, 0.0F, false));

		bone4 = new ModelRenderer(this);
		bone4.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(bone4, -0.0873F, 0.0F, 0.0F);
		bone3.addChild(bone4);
		bone4.cubeList.add(new ModelBox(bone4, 0, 0, -2.5F, -4.8F, -11.2F, 3, 10, 7, 0.0F, false));
		bone4.cubeList.add(new ModelBox(bone4, 0, 0, -0.3F, -4.8F, -9.2F, 1, 8, 5, 0.0F, false));
		bone4.cubeList.add(new ModelBox(bone4, 0, 0, -0.3F, -4.8F, -11.2F, 1, 9, 1, 0.0F, false));
		bone4.cubeList.add(new ModelBox(bone4, 0, 0, -3.0F, 4.2F, -11.2F, 4, 1, 7, 0.0F, false));
		bone4.cubeList.add(new ModelBox(bone4, 0, 0, -2.7F, -4.8F, -9.2F, 1, 8, 5, 0.0F, false));
		bone4.cubeList.add(new ModelBox(bone4, 0, 0, -2.7F, -4.8F, -11.2F, 1, 9, 1, 0.0F, false));

		stock = new ModelRenderer(this);
		stock.setRotationPoint(0.0F, 0.0F, 0.0F);
		mp5k.addChild(stock);
		stock.cubeList.add(new ModelBox(stock, 0, 0, -3.5F, -21.0F, 19.2F, 5, 1, 21, 0.0F, false));
		stock.cubeList.add(new ModelBox(stock, 0, 0, -3.5F, -20.9F, 39.2F, 5, 12, 1, 0.0F, false));
		stock.cubeList.add(new ModelBox(stock, 0, 0, -3.5F, -21.0F, 16.0F, 5, 3, 4, 0.0F, false));
		stock.cubeList.add(new ModelBox(stock, 0, 0, -3.5F, -20.9F, 25.2F, 5, 5, 1, 0.0F, false));
		stock.cubeList.add(new ModelBox(stock, 0, 0, -4.5F, -23.0F, 12.0F, 7, 6, 2, 0.0F, false));
		stock.cubeList.add(new ModelBox(stock, 0, 0, -5.0F, -22.5F, 14.0F, 8, 5, 2, 0.0F, false));
		stock.cubeList.add(new ModelBox(stock, 0, 0, -5.5F, -22.0F, 12.0F, 9, 4, 2, 0.0F, false));

		bone8 = new ModelRenderer(this);
		bone8.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(bone8, -0.4363F, 0.0F, 0.0F);
		stock.addChild(bone8);
		bone8.cubeList.add(new ModelBox(bone8, 0, 0, -3.5F, -25.6F, 9.9F, 5, 1, 22, 0.0F, false));

		sight = new ModelRenderer(this);
		sight.setRotationPoint(0.0F, 0.0F, 0.0F);
		mp5k.addChild(sight);
		sight.cubeList.add(new ModelBox(sight, 0, 0, -1.5F, -25.25F, -20.0F, 1, 3, 1, 0.0F, false));
		sight.cubeList.add(new ModelBox(sight, 0, 0, 0.0F, -24.75F, 7.8F, 1, 3, 2, 0.0F, false));
		sight.cubeList.add(new ModelBox(sight, 0, 0, -3.0F, -24.75F, 7.8F, 1, 3, 2, 0.0F, false));
		sight.cubeList.add(new ModelBox(sight, 0, 0, -2.0F, -23.75F, 8.3F, 2, 2, 1, 0.0F, false));
		sight.cubeList.add(new ModelBox(sight, 0, 0, -2.5F, -24.35F, 8.3F, 1, 1, 1, 0.0F, false));
		sight.cubeList.add(new ModelBox(sight, 0, 0, -0.5F, -24.35F, 8.3F, 1, 1, 1, 0.0F, false));

		bone9 = new ModelRenderer(this);
		bone9.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(bone9, 0.6981F, 0.0F, 0.0F);
		sight.addChild(bone9);
		bone9.cubeList.add(new ModelBox(bone9, 0, 0, -1.5F, -31.55F, 0.7F, 1, 3, 1, 0.0F, false));

		bone10 = new ModelRenderer(this);
		bone10.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(bone10, -0.9599F, 0.0F, 0.0F);
		sight.addChild(bone10);
		bone10.cubeList.add(new ModelBox(bone10, 0, 0, -3.0F, -22.2266F, -14.6515F, 1, 1, 3, 0.0F, false));
		bone10.cubeList.add(new ModelBox(bone10, 0, 0, 0.0F, -22.2266F, -14.6515F, 1, 1, 3, 0.0F, false));
		this.initAnimations();
	}
	
	@Override
	public String textureName() {
		return "mp5k";
	}
	
	@Override
	public void initAnimations() {
		initAimAnimation(-0.62f, 0.23f, 0.1f);
		initAimingAnimationStates(0.23f, 0.15f, 0.1f);
		heldAnimation = new HeldAnimation(HeldStyle.SMALL);
		reloadAnimation = new ReloadAnimation(magazine, ReloadStyle.MAGAZINE).initMovement(DEFAULT_PART_ANIMATION);
	}
	
	@Override
	public void render(ItemStack stack) {
		EntityPlayer player = Minecraft.getMinecraft().player;
		if(player != null && player.hasCapability(PlayerDataProvider.PLAYER_DATA, null)) {
			IPlayerData data = player.getCapability(PlayerDataProvider.PLAYER_DATA, null);
			GlStateManager.pushMatrix();
			renderMP5K(stack, data.isAiming());
			GlStateManager.popMatrix();
		}
	}
	
	private void renderMP5K(ItemStack stack, boolean aim) {
		GlStateManager.pushMatrix();
		transform.defaultSMGTransform();
		GlStateManager.translate(-1.1000001, 2.0, -9.0);
		if(aim && enableADS(stack)) {
			rotateModelForADSRendering();
		}
		mp5k.render(1f);
		GlStateManager.popMatrix();
		renderRedDot(-3.375f, -10, -5, 1.4f, stack);
		renderHolo(-3.875f, -10, -7, 1.4f, stack);
		renderScope2X(5, 8, -15, 1f, stack);
		renderScope4X(5, 8, -15, 1f, stack);
		renderVerticalGrip(-3.4, 5, -10, 1f, stack);
		renderAngledGrip(2.5, 4, 5, 1f, stack);
		renderSMGSilencer(debug.X, debug.Y, debug.Z, debug.scale, stack);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}

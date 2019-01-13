package com.toma.pubgmc.client.models;

import com.toma.pubgmc.animation.AimAnimation;
import com.toma.pubgmc.animation.HeldAnimations;
import com.toma.pubgmc.client.models.atachments.ModelAngledGrip;
import com.toma.pubgmc.client.models.atachments.ModelHolographic;
import com.toma.pubgmc.client.models.atachments.ModelRedDotPistol;
import com.toma.pubgmc.client.models.atachments.ModelScope15X;
import com.toma.pubgmc.client.models.atachments.ModelScope2X;
import com.toma.pubgmc.client.models.atachments.ModelScope4X;
import com.toma.pubgmc.client.models.atachments.ModelScope8X;
import com.toma.pubgmc.client.models.atachments.ModelSilencer;
import com.toma.pubgmc.client.models.atachments.ModelSilencerPistol;
import com.toma.pubgmc.client.models.atachments.ModelVerticalGrip;
import com.toma.pubgmc.client.util.ModelDebugger;
import com.toma.pubgmc.client.util.ModelTransformationHelper;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.item.ItemStack;

public abstract class ModelGun extends ModelBase
{	
	public ModelTransformationHelper transform = ModelTransformationHelper.instance;
	public ModelDebugger debug = ModelDebugger.instance;
	public HeldAnimations animation_held;
	public AimAnimation animation_aim;
	
	private final ModelSilencerPistol silencer_pistol = new ModelSilencerPistol();
	private final ModelSilencer silencer = new ModelSilencer();
	private final ModelVerticalGrip grip_vertical = new ModelVerticalGrip();
	private final ModelAngledGrip grip_angled = new ModelAngledGrip();
	private final ModelRedDotPistol red_dot = new ModelRedDotPistol();
	private final ModelHolographic holo = new ModelHolographic();
	private final ModelScope2X scope2x = new ModelScope2X();
	private final ModelScope4X scope4x = new ModelScope4X();
	private final ModelScope8X scope8x = new ModelScope8X();
	private final ModelScope15X scope15x = new ModelScope15X();
	
	public ModelGun()
	{
		HeldAnimations anim = new HeldAnimations();
		anim.setWeaponType(false);
		animation_held = anim;
	}
	
	public abstract void render(ItemStack stack);
	
	public static void rotateModelForADSRendering()
	{
		GlStateManager.rotate(0.2f, 0, 1f, 0);
		GlStateManager.rotate(0.5f, 1f, 0, 0);
	}
	
	public boolean hasRedDot(ItemStack stack)
	{
		return stack.hasTagCompound() && stack.getTagCompound().getInteger("scope") == 1;
	}
	
	public boolean hasHoloSight(ItemStack stack)
	{
		return stack.hasTagCompound() && stack.getTagCompound().getInteger("scope") == 2;
	}
	
	public boolean has2X(ItemStack stack)
	{
		return stack.hasTagCompound() && stack.getTagCompound().getInteger("scope") == 3;
	}
	
	public boolean has4X(ItemStack stack)
	{
		return stack.hasTagCompound() && stack.getTagCompound().getInteger("scope") == 4;
	}
	
	public boolean has8X(ItemStack stack)
	{
		return stack.hasTagCompound() && stack.getTagCompound().getInteger("scope") == 5;
	}
	
	public boolean has15X(ItemStack stack)
	{
		return stack.hasTagCompound() && stack.getTagCompound().getInteger("scope") == 6;
	}
	
	public boolean hasSilencer(ItemStack stack)
	{
		return stack.hasTagCompound() && stack.getTagCompound().getInteger("barrel") == 1;
	}
	
	public boolean hasVerticalGrip(ItemStack stack)
	{
		return stack.hasTagCompound() && stack.getTagCompound().getInteger("grip") == 1;
	}
	
	public boolean hasAngledGrip(ItemStack stack)
	{
		return stack.hasTagCompound() && stack.getTagCompound().getInteger("grip") == 2;
	}
	
	public boolean enableADS(ItemStack stack)
	{
		return stack.hasTagCompound() && stack.getTagCompound().getInteger("scope") < 3;
	}
	
	public boolean hasScopeAtachment(ItemStack stack)
	{
		return stack.hasTagCompound() && stack.getTagCompound().getInteger("scope") > 0;
	}
	
	public void renderRedDot(double x, double y, double z, float scale, ItemStack stack)
	{
		if(hasRedDot(stack))
		{
			GlStateManager.pushMatrix();
			transform.defaultPistolRedDotTransform();
			GlStateManager.scale(scale, scale, scale);
			GlStateManager.translate(x, y, z);
			red_dot.render();
			GlStateManager.popMatrix();
		}
	}
	
	public void renderHolo(double x, double y, double z, float scale, ItemStack stack)
	{
		if(hasHoloSight(stack))
		{
			GlStateManager.pushMatrix();
			transform.defaultHoloTransform();
			GlStateManager.scale(scale, scale, scale);
			GlStateManager.translate(x, y, z);
			holo.render();
			GlStateManager.popMatrix();
		}
	}
	
	public void renderScope2X(double x, double y, double z, float scale, ItemStack stack)
	{
		if(has2X(stack))
		{
			GlStateManager.pushMatrix();
			transform.default2XTransform();
			GlStateManager.scale(scale, scale, scale);
			GlStateManager.translate(x, y, z);
			scope2x.render();
			GlStateManager.popMatrix();
		}
	}
	
	public void renderScope4X(double x, double y, double z, float scale, ItemStack stack)
	{
		if(has4X(stack))
		{
			GlStateManager.pushMatrix();
			transform.default4XTransform();
			GlStateManager.scale(scale, scale, scale);
			GlStateManager.translate(x, y, z);
			scope4x.render();
			GlStateManager.popMatrix();
		}
	}
	
	public void renderScope8X(double x, double y, double z, float scale, ItemStack stack)
	{
		if(has8X(stack))
		{
			GlStateManager.pushMatrix();
			transform.default8XTransform();
			GlStateManager.scale(scale, scale, scale);
			GlStateManager.translate(x, y, z);
			scope8x.render();
			GlStateManager.popMatrix();
		}
	}
	
	public void renderScope15X(double x, double y, double z, float scale, ItemStack stack)
	{
		if(has15X(stack))
		{
			GlStateManager.pushMatrix();
			transform.default15XTransform();
			GlStateManager.scale(scale, scale, scale);
			GlStateManager.translate(x, y, z);
			scope15x.render();
			GlStateManager.popMatrix();
		}
	}
	
	public void renderPistolSilencer(double x, double y, double z, float scale, ItemStack stack)
	{
		if(hasSilencer(stack))
		{
			GlStateManager.pushMatrix();
			transform.defaultPistolSilencerTransform();
			GlStateManager.scale(scale, scale, scale);
			GlStateManager.translate(x, y, z);
			silencer_pistol.render();
			GlStateManager.popMatrix();
		}
	}
	
	public void renderSMGSilencer(double x, double y, double z, float scale, ItemStack stack)
	{
		if(hasSilencer(stack))
		{
			GlStateManager.pushMatrix();
			transform.silencerSMGTransform();
			GlStateManager.scale(scale, scale, scale);
			GlStateManager.translate(x, y, z);
			silencer.render();
			GlStateManager.popMatrix();
		}
	}
	
	public void renderARSilencer(double x, double y, double z, float scale, ItemStack stack)
	{
		if(hasSilencer(stack))
		{
			GlStateManager.pushMatrix();
			transform.silencerARTransform();
			GlStateManager.scale(scale, scale, scale);
			GlStateManager.translate(x, y, z);
			silencer.render();
			GlStateManager.popMatrix();
		}
	}
	
	public void renderSniperSilencer(double x, double y, double z, float scale, ItemStack stack)
	{
		if(hasSilencer(stack))
		{
			GlStateManager.pushMatrix();
			transform.silencerSRTransform();
			GlStateManager.scale(scale, scale, scale);
			GlStateManager.translate(x, y, z);
			silencer.render();
			GlStateManager.popMatrix();
		}
	}
	
	public void renderVerticalGrip(double x, double y, double z, float scale, ItemStack stack)
	{
		if(hasVerticalGrip(stack))
		{
			GlStateManager.pushMatrix();
			transform.verticalGripTransform();
			GlStateManager.scale(scale, scale, scale);
			GlStateManager.translate(x, y, z);
			grip_vertical.render();
			GlStateManager.popMatrix();	
		}
	}
	
	public void renderAngledGrip(double x, double y, double z, float scale, ItemStack stack)
	{
		if(hasAngledGrip(stack))
		{
			GlStateManager.pushMatrix();
			transform.angledGripTransform();
			GlStateManager.scale(scale, scale, scale);
			GlStateManager.translate(x, y, z);
			grip_angled.render();
			GlStateManager.popMatrix();
		}
	}
}

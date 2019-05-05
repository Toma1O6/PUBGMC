package com.toma.pubgmc.animation;

import javax.vecmath.Vector3f;

import org.apache.commons.lang3.tuple.MutablePair;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;

public class ReloadAnimation extends Animation implements IPartAnimated<ReloadAnimation>
{
	final ModelRenderer magazine;
	final ReloadStyle style;
	private MutablePair<Vector3f, Vector3f>[] steps;
	private float x, y, z;
	private float rx, ry, rz;
	private float px, py, pz, prx, pry, prz;
	private int step;
	private float speedMultiplier;
	
	public ReloadAnimation(final ModelRenderer partToAnimate, final ReloadStyle styleOfReload)
	{
		this.magazine = partToAnimate;
		this.style = styleOfReload;
		this.speedMultiplier = 1.0F;
	}
	
	public ReloadAnimation withSpeed(float speed)
	{
		speed = speed < 0f ? Math.abs(speed) : speed;
		this.speedMultiplier = speed;
		return this;
	}
	
	public final void processAnimation(boolean reloading)
	{
		if(!Minecraft.getMinecraft().isGamePaused())
		{
			this.getReloadStyle().process(reloading, this);
		}
	}
	
	@Override
	public void setCurrentStep(int step)
	{
		this.step = step;
	}
	
	@Override
	public int currentStep()
	{
		return step;
	}
	
	@Override
	public void setMovement(float x, float y, float z)
	{
		this.px = x;
		this.py = y;
		this.pz = z;
	}
	
	@Override
	public void setRotation(float x, float y, float z) 
	{
		this.prx = x;
		this.pry = y;
		this.prz = z;
	}
	
	@Override
	public ReloadAnimation initMovement(MutablePair<Vector3f, Vector3f>[] steps)
	{
		this.steps = steps;
		return this;
	}
	
	@Override
	public MutablePair<Vector3f, Vector3f>[] animationSteps()
	{
		return steps;
	}
	
	@Override
	public float[] getDefaultRotationAngles()
	{
		ModelRenderer part = this.getPart();
		return new float[] {part.rotateAngleX, part.rotateAngleY, part.rotateAngleZ};
	}
	
	@Override
	public float getSpeed()
	{
		return speedMultiplier;
	}
	
	@Override
	public Vector3f getMovementVec()
	{
		return new Vector3f(x, y, z);
	}
	
	@Override
	public Vector3f getRotationVector() 
	{
		return new Vector3f(rx, ry, rz);
	}
	
	@Override
	public ModelRenderer getPart()
	{
		return magazine;
	}
	
	@Override
	public Vector3f getPartMovement()
	{
		return new Vector3f(px, py, pz);
	}
	
	@Override
	public Vector3f getPartRotation()
	{
		return new Vector3f(prx, pry, prz);
	}
	
	//Crashes the game during model initialization
	@Deprecated
	public MutablePair<Vector3f, Vector3f>[] getDefaultAnimation()
	{
		return new MutablePair[] {
				new MutablePair(new Vector3f(getPart().rotateAngleX, getPart().rotateAngleY, getPart().rotateAngleZ), new Vector3f(0f, 0.5f, 0f)),
				new MutablePair(new Vector3f(getPart().rotateAngleX, getPart().rotateAngleY, getPart().rotateAngleZ), new Vector3f(0f, 11.5f, 0f)),
				new MutablePair(new Vector3f(getPart().rotateAngleX, getPart().rotateAngleY, getPart().rotateAngleZ), new Vector3f(0f, 0f, 0f))
		};
	}
	
	public ReloadStyle getReloadStyle()
	{
		return style;
	}
	
	private boolean isMagFinished()
	{
		return rx == style.rotation.x && rz == style.rotation.z && z == style.translation.z;
	}
	
	private boolean isMagReturned()
	{
		return rx == 0f && rz == 0f && z == 0f;
	}
	
	private boolean isSingleFinished()
	{
		return ry == style.rotation.y && rz == style.rotation.z && x == style.translation.x && z == style.translation.z;
	}
	
	private boolean isSingleReturned()
	{
		return ry == 0f && rz == 0f && x == 0f && z == 0f;
	}
	
	private boolean isRevolverFinished()
	{
		return rx == style.rotation.x && rz == style.rotation.z;
	}
	
	private boolean isRevolverReturned()
	{
		return rx == 0f && rz == 0f;
	}
	
	public enum ReloadStyle
	{
		MAGAZINE(new Vector3f(60f, 0f, -20f), new Vector3f(0f, 0f, -0.5f)),
		REVOLVER(new Vector3f(-20f, 0f, -15f), EMPTYVEC),
		SINGLE(new Vector3f(0f, 10f, 15f), new Vector3f(-0.05f, 0f, 0.05f));
		
		private final Vector3f rotation, translation;
		
		private ReloadStyle(final Vector3f rotation, final Vector3f translation)
		{
			this.rotation = rotation;
			this.translation = translation;
		}
		
		public final void process(boolean reload, ReloadAnimation animation)
		{
			if(this.equals(MAGAZINE)) {
				handleMagazineStyle(reload, animation);
			}
			else if(this.equals(SINGLE)) {
				handleSingleStyle(reload, animation);
			}
			else if(this.equals(REVOLVER)) {
				handleRevolverStyle(reload, animation);
			}
			else throw new IllegalStateException("Uknown reload style!");
		}
		
		private void handleMagazineStyle(boolean reload, ReloadAnimation a)
		{
			a.process(reload);
			if(reload && !a.isMagFinished())
			{
				a.z = decreasePartialMovement(a.z, translation.z, 0.04f);
				a.rx = increasePartialMovement(a.rx, rotation.x, 3.5f);
				a.rz = decreasePartialMovement(a.rz, rotation.z, 2.7f);
			}
			
			else if(!reload && !a.isMagReturned())
			{
				a.z = increasePartialMovement(a.z, 0f, 0.04f);
				a.rx = decreasePartialMovement(a.rx, 0f, 3.5f);
				a.rz = increasePartialMovement(a.rz, 0f, 2.7f);
			}
			
			GlStateManager.rotate(a.rx, 1f, 0f, 0f);
			GlStateManager.rotate(a.rz, 0f, 0f, 1f);
			GlStateManager.translate(0f, 0f, a.z);
		}
		
		private void handleSingleStyle(boolean reload, ReloadAnimation a)
		{
			a.process(reload);
			if(reload && !a.isSingleFinished())
			{
				a.ry = increasePartialMovement(a.ry, rotation.y, 2.5f);
				a.rz = increasePartialMovement(a.rz, rotation.z, 2.5f);
				a.x = decreasePartialMovement(a.x, translation.x, 0.02f);
				a.z = increasePartialMovement(a.z, translation.z, 0.02f);
			}
			
			else if(!reload && !a.isSingleReturned())
			{
				a.ry = decreasePartialMovement(a.ry, 0f, 2.5f);
				a.rz = decreasePartialMovement(a.rz, 0f, 2.5f);
				a.x = increasePartialMovement(a.x, 0f, 0.02f);
				a.z = decreasePartialMovement(a.z, 0f, 0.02f);
			}
			
			GlStateManager.rotate(a.ry, 0f, 1f, 0f);
			GlStateManager.rotate(a.rz, 0f, 0f, 1f);
			GlStateManager.translate(a.x, 0f, a.z);
		}
		
		private void handleRevolverStyle(boolean reload, ReloadAnimation a)
		{
			a.process(reload);
			if(reload && !a.isRevolverFinished())
			{
				a.rx = decreasePartialMovement(a.rx, rotation.x, 2.5f);
				a.rz = decreasePartialMovement(a.rz, rotation.z, 2.5f);
			}
			
			else if(!reload && !a.isRevolverReturned())
			{
				a.rx = increasePartialMovement(a.rx, 0f, 2.5f);
				a.rz = increasePartialMovement(a.rz, 0f, 2.5f);
			}
			
			GlStateManager.rotate(a.rx, 1f, 0f, 0f);
			GlStateManager.rotate(a.rz, 0f, 0f, 1f);
		}
	}
}

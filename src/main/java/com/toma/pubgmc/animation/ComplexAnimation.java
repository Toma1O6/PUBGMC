package com.toma.pubgmc.animation;

import net.minecraft.client.model.ModelRenderer;
import net.minecraft.util.math.Vec3d;

public abstract class ComplexAnimation extends Animation implements IMultiStepAnimation
{
	protected double mx, my, mz;
	protected float mrx, mry, mrz;
	
	/** Has to be initialized in {@code IMultiStepAnimation.initPoints()}**/
	public AnimationPoint[] points;
	
	public void onPointFinished(AnimationPoint point)
	{
		final Vec3d pos = point.getPosition();
		final Vec3d rot = point.getRotation();
		
		mx += pos.x;
		my += pos.y;
		mz += pos.z;
		
		mrx += rot.x;
		mry += rot.y;
		mrz += rot.z;
	}
	
	protected void moveModel(AnimationPoint point, ModelRenderer modelToAnimate)
	{
	}
}

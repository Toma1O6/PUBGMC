package com.toma.pubgmc.animation;

import net.minecraft.client.model.ModelRenderer;
import net.minecraft.util.math.Vec3d;

public class ReloadAnimation extends ComplexAnimation
{
	private final ModelRenderer mag;
	private short id;
	
	public ReloadAnimation(ModelRenderer magazineRenderer)
	{
		mag = magazineRenderer;
		id = 0;
		
		AnimationPoint p0 = new AnimationPoint(new Vec3d(0.0, 0.0, 0.0), new Vec3d(0, 30, 15));
		this.initPoints(p0);
	}
	
	@Override
	public void initPoints(AnimationPoint... animationPoints)
	{
		this.points = animationPoints;
	}
	
	@Override
	public AnimationPoint[] getPoints()
	{
		return points;
	}
	
	@Override
	public void execute() throws IllegalArgumentException
	{
		if(points.length < 1)
			throw new IllegalArgumentException("Point array cannot be empty!");
		
		final AnimationPoint point = points[id];
		
		// Run when the movement isn't complete
		if(!point.isFinished())
		{
			this.moveModel(point, mag);
		}
		
		else
		{
			this.onPointFinished(point);
			
			if(!nextPoint())
			{
				this.stopAnimation();
			}
		}
	}
	
	private void stopAnimation()
	{
		// TODO
	}
	
	public boolean nextPoint()
	{
		if(points.length > id + 1)
		{ 
			id++;
			return true;
		}
		
		return false;
	}
	
	@Override
	public void run(boolean boolParameter)
	{
		this.execute();
	}
}

package com.toma.pubgmc.animation;

public interface IMultiStepAnimation 
{
	public void execute();
	
	public void initPoints(AnimationPoint... animationPoints);
	
	public AnimationPoint[] getPoints();
}

package com.toma.pubgmc.animation;

public interface IMultiStepAnimation 
{
	public void execute() throws IllegalArgumentException;
	
	public void initPoints(AnimationPoint... animationPoints);
	
	public AnimationPoint[] getPoints();
}

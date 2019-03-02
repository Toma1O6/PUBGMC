package com.toma.pubgmc.animation;

import net.minecraft.client.model.ModelRenderer;

//Work in progress, lot of stuff will have to be tweaked
public final class ReloadAnimationBuilder
{
	private ModelRenderer model;
	private double offsetX, offsetY, offsetZ;
	private float rotationX, rotationY, rotationZ;
	private int id = Integer.MIN_VALUE;
	
	public static ReloadAnimationBuilder create()
	{
		return new ReloadAnimationBuilder();
	}
	
	public ReloadAnimationBuilder id(int id)
	{
		this.id = id;
		return this;
	}
	
	public ReloadAnimationBuilder model(ModelRenderer modelToAnimate)
	{
		this.model = modelToAnimate;
		return this;
	}
	
	public ReloadAnimationBuilder withOffset(double x, double y, double z)
	{
		this.offsetX = x;
		this.offsetY = y;
		this.offsetZ = z;
		return this;
	}
	
	public ReloadAnimationBuilder withRotation(float x, float y, float z)
	{
		this.rotationX = x;
		this.rotationY = y;
		this.rotationZ = z;
		return this;
	}
	
	public ReloadAnimationPoint setup()
	{
		checkID();
		checkModel();
		
		ReloadAnimationPoint point = new ReloadAnimationPoint(id, model, new double[] {offsetX,  offsetY, offsetZ}, new float[] {rotationX,rotationY,rotationZ});
		ReloadAnimationPoint.POINTS.add(point);
		return point;
	}
	
	private void checkID() throws IllegalArgumentException
	{
		if(id < 0) throw new IllegalArgumentException("ID cannot be smaller than 0!");
	}
	
	private void checkModel() throws IllegalArgumentException
	{
		if(model == null) throw new IllegalArgumentException("Model cannot be null!");
	}
}

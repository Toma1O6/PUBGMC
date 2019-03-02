package com.toma.pubgmc.animation;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.model.ModelRenderer;

//Work in progress..
public class ReloadAnimationPoint 
{
	private final ModelRenderer model;
	private final int id;
	private final double x,y,z;
	private final float rotX, rotY, rotZ;
	//Bad idea, I guess. Let's store this inside each modelGun instance
	protected static final List<ReloadAnimationPoint> POINTS = new ArrayList<ReloadAnimationPoint>();
	
	public ReloadAnimationPoint(int id, ModelRenderer model, double[] offset, float[] rotation)
	{
		this.id = id;
		this.model = model;
		this.x = offset[0];
		this.y = offset[1];
		this.z = offset[2];
		this.rotX = rotation[0];
		this.rotY = rotation[1];
		this.rotZ = rotation[2];
	}
}

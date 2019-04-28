package com.toma.pubgmc.animation;

import javax.vecmath.Vector3f;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import net.minecraft.client.model.ModelRenderer;

public interface IPartAnimated
{
	ModelRenderer getPart();
	
	Vector3f getPartMovement();
	
	Vector3f getPartRotation();
	
	MagazineMovementStyle getMagazineMovementPattern();
	
	public enum MagazineMovementStyle
	{
		DEFAULT(new ImmutablePair(new Vector3f(-15f,0f,0f), new Vector3f(0f, 0.5f, 0f)),
				new ImmutablePair(new Vector3f(-15f,0f,0f), new Vector3f(0f, 5.5f, 0f))),
		
		REVOLVER,
		DP;
		
		private Pair<Vector3f, Vector3f>[] steps;
		private float x,y,z,rx,ry,rz;
		
		/**
		 * L - rotation
		 * R - translation
		 */
		private MagazineMovementStyle(Pair<Vector3f, Vector3f>... steps)
		{
			this.steps = steps;
		}
		
		public Vector3f getMovement()
		{
			return new Vector3f(x, y, z);
		}
		
		public Vector3f getRotation()
		{
			return new Vector3f(rx, ry, rz);
		}
		
		public final void process()
		{
			switch(this)
			{
				case DEFAULT: {
					handleDefault();
					break;
				}
				
				case REVOLVER: {
					handleRevolver();
					break;
				}
				
				case DP: {
					handleDP();
					break;
				}
			}
		}
		
		private void handleDefault()
		{
			
		}
		
		private void handleRevolver()
		{
			
		}
		
		private void handleDP()
		{
			
		}
		
		private boolean shouldContinue(int step, Pair<Vector3f, Vector3f>[] group)
		{
			return step+1 < group.length;
		}
	}
}

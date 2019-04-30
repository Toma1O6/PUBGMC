package com.toma.pubgmc.animation;

import javax.vecmath.Vector3f;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import com.toma.pubgmc.util.PUBGMCUtil;

import net.minecraft.client.model.ModelRenderer;

public interface IPartAnimated
{
	ModelRenderer getPart();
	
	Vector3f getPartMovement();
	
	Vector3f getPartRotation();
	
	MagazineMovementStyle getMagazineMovementPattern();
	
	public enum MagazineMovementStyle
	{
		// positions are relative to 0
		DEFAULT(new ImmutablePair(new Vector3f(0f, 0f, 0f), new Vector3f(0f, 0.5f, 0f)),
				new ImmutablePair(new Vector3f(0f, 0f, 0f), new Vector3f(0f, 11.5f, 0f)),
				new ImmutablePair(new Vector3f(0f, 0f, 0f), new Vector3f(0f, 0f, 0f))),
		
		REVOLVER(new ImmutablePair(Animation.EMPTYVEC, Animation.EMPTYVEC),
				 new ImmutablePair(Animation.EMPTYVEC, Animation.EMPTYVEC)),
		
		DP;
		
		private Pair<Vector3f, Vector3f>[] steps;
		private float x,y,z,rx,ry,rz;
		private short currentStep = 0;
		
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
		
		public final boolean isPartFinished(IPartAnimated part)
		{
			Vector3f transl = steps[currentStep].getRight();
			Vector3f rot = steps[currentStep].getLeft();
			Vector3f magTransl = this.getMovement();
			Vector3f magRot = this.getRotation();
			return magTransl.x == transl.x && magTransl.y == transl.y && magTransl.z == transl.z &&
					magRot.x == rot.x && magRot.y == rot.y && magRot.z == rot.z;
		}
		
		public final boolean isPartReturned(IPartAnimated part)
		{
			Vector3f translation = this.getMovement();
			Vector3f rotation = this.getRotation();
			return translation.x == 0f && translation.y == 0f && translation.z == 0f && rotation.x == 0f && rotation.y == 0f && rotation.z == 0f;
		}
		
		public final void process(IPartAnimated animatedPart, boolean reload)
		{
			if(animatedPart.getPart() == null) {
				return;
			}

			switch(this)
			{
				case DEFAULT: {
					handleDefault(animatedPart, reload);
					break;
				}
				
				case REVOLVER: {
					handleRevolver(animatedPart, reload);
					break;
				}
				
				case DP: {
					handleDP(animatedPart, reload);
					break;
				}
			}
			
			ModelRenderer model = animatedPart.getPart();
			PUBGMCUtil.setModelPosition(model, x, y, z);
			PUBGMCUtil.setModelRotation(model, rx, ry, rz);
		}
		
		private void handleDefault(IPartAnimated a, boolean reload)
		{
			Pair<Vector3f, Vector3f> step = steps[currentStep];
			if(reload)
			{
				if(!isPartFinished(a))
				{
					//rz = rz < step.getLeft().z ? rz + Animation.calculateMovement(0.01f) : step.getLeft().z;
					//y = y < step.getRight().y ? y + Animation.calculateMovement(0.3f) : step.getRight().y;
					y = Math.abs(step.getRight().y - y) < 0.25f ? step.getRight().y : y;
					y = y < step.getRight().y ? y + Animation.calculateMovement(0.2f) : y > step.getRight().y ? y - Animation.calculateMovement(0.2f) : y;
				}
				else if(shouldContinue(currentStep, steps))
				{
					++currentStep;
				}
			}
			
			else if(!reload)
			{
				currentStep = 0;
				if(!isPartReturned(a))
				{
					doReturning(a);
				}
			}
		}
		
		private void handleRevolver(IPartAnimated a, boolean reload)
		{
			
		}
		
		private void handleDP(IPartAnimated a, boolean reload)
		{
			
		}
		
		private void doReturning(IPartAnimated a)
		{
			x = Math.abs(x) < 0.25f ? 0f : x;
			y = Math.abs(y) < 0.25f ? 0f : y;
			z = Math.abs(z) < 0.25f ? 0f : z;
			x = x > 0f ? x - Animation.calculateMovement(0.2f) : x < 0f ? x + Animation.calculateMovement(0.2f) : 0f;
			y = y > 0f ? y - Animation.calculateMovement(0.2f) : y < 0f ? y + Animation.calculateMovement(0.2f) : 0f;
			z = z > 0f ? z - Animation.calculateMovement(0.2f) : z < 0f ? z + Animation.calculateMovement(0.2f) : 0f;
		}
		
		private boolean shouldContinue(int step, Pair<Vector3f, Vector3f>[] group)
		{
			return step+1 < group.length;
		}
	}
}

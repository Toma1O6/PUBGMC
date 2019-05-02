package com.toma.pubgmc.animation;

import javax.vecmath.Vector3f;

import org.apache.commons.lang3.tuple.MutablePair;
import org.apache.commons.lang3.tuple.Pair;

import com.toma.pubgmc.util.PUBGMCUtil;

import net.minecraft.client.model.ModelRenderer;

public interface IPartAnimated
{
	ModelRenderer getPart();
	
	Vector3f getPartMovement();
	
	Vector3f getPartRotation();
	
	float getSpeed();
	
	MagazineMovementStyle getMagazineMovementPattern();
	
	public enum MagazineMovementStyle
	{
		// positions are relative to 0
		DEFAULT(new MutablePair(new Vector3f(0f, 0f, 0f), new Vector3f(0f, 0.5f, 0f)),
				new MutablePair(new Vector3f(0f, 0f, 0f), new Vector3f(0f, 11.5f, 0f)),
				new MutablePair(new Vector3f(0f, 0f, 0f), new Vector3f(0f, 0f, 0f))),
		
		REVOLVER(new MutablePair(Animation.EMPTYVEC, Animation.EMPTYVEC),
				 new MutablePair(Animation.EMPTYVEC, Animation.EMPTYVEC)),
		
		DP;
		
		private MutablePair<Vector3f, Vector3f>[] steps;
		private float x,y,z,rx,ry,rz;
		private short currentStep = 0;
		private float[] rotationAngles = new float[] {Float.MIN_VALUE};
		
		/**
		 * L - rotation
		 * R - translation
		 */
		private MagazineMovementStyle(MutablePair<Vector3f, Vector3f>... steps)
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
			Vector3f magRot = new Vector3f(rx, ry, rz);
			//System.out.println(magRot.x + "    " + rot.x);
			return magTransl.x == transl.x && magTransl.y == transl.y && magTransl.z == transl.z &&
					magRot.x == rot.x && magRot.y == rot.y && magRot.z == rot.z;
		}
		
		public final boolean isPartReturned(IPartAnimated part)
		{
			Vector3f translation = this.getMovement();
			Vector3f rotation = this.getRotation();
			return translation.x == 0f && translation.y == 0f && translation.z == 0f 
					&& rotation.x == rotationAngles[0] && rotation.y == rotationAngles[1] && rotation.z == rotationAngles[2];
		}
		
		public final void process(IPartAnimated animatedPart, boolean reload)
		{
			if(animatedPart.getPart() == null) {
				return;
			}
			
			if(rotationAngles.length == 1) {
				initRotationAngles(animatedPart);
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
					//System.out.println(rx);
					//rx = Math.abs(rx - rotationAngles[0]) < 0.1f ? rotationAngles[0] : rx > rotationAngles[0] ? rx - Animation.calculateMovement(0.05f) : rx < rotationAngles[0] ? rx + Animation.calculateMovement(0.05f) : rx;
					//y = Math.abs(step.getRight().y - y) < 0.25f ? step.getRight().y : y;
					//y = y < step.getRight().y ? y + Animation.calculateMovement(0.2f * a.getSpeed()) : y > step.getRight().y ? y - Animation.calculateMovement(0.2f * a.getSpeed()) : y;
					y = Animation.getPartialMovement(y, step.getRight().y, 0.2f * a.getSpeed());
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
			x = Animation.getPartialMovement(x, 0f, 0.2f * a.getSpeed());
			y = Animation.getPartialMovement(y, 0f, 0.2f * a.getSpeed());
			z = Animation.getPartialMovement(z, 0f, 0.2f * a.getSpeed());
		}
		
		private boolean shouldContinue(int step, Pair<Vector3f, Vector3f>[] group)
		{
			return step+1 < group.length;
		}
		
		private void initRotationAngles(IPartAnimated part)
		{
			rotationAngles = new float[] {part.getPart().rotateAngleX, part.getPart().rotateAngleY, part.getPart().rotateAngleZ};
			rx = rotationAngles[0];
			ry = rotationAngles[1];
			rz = rotationAngles[2];
			this.initStepRotations(rx, ry, rz);
		}
		
		private void initStepRotations(float rx, float ry, float rz)
		{
			for(int i = 0; i < steps.length; i++)
			{
				MutablePair<Vector3f, Vector3f> pair = steps[i];
				if(rx != 0f || ry != 0f || rz != 0f)
				{
					Vector3f vec = pair.getLeft();
					if(vec.x == 0f) {
						vec.x = rx;
					}
					if(vec.y == 0f) {
						vec.y = ry;
					}
					if(vec.z == 0f) {
						vec.z = rz;
					}
					pair.setLeft(vec);
				}
			}
		}
	}
}

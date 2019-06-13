package com.toma.pubgmc.animation;

import javax.vecmath.Vector3f;

import org.apache.commons.lang3.tuple.MutablePair;

import com.toma.pubgmc.util.PUBGMCUtil;

import net.minecraft.client.model.ModelRenderer;

public interface IPartAnimated<T extends Animation>
{
	ModelRenderer getPart();
	
	Vector3f getPartMovement();
	
	Vector3f getPartRotation();
	
	float getSpeed();
	
	float[] getDefaultRotationAngles();
	
	T initMovement(MutablePair<Vector3f, Vector3f>[] mutablePairs);
	
	int currentStep();
	
	void setCurrentStep(int step);
	
	MutablePair<Vector3f, Vector3f> animationSteps()[];
	
	void setMovement(float x, float y, float z);
	
	void setRotation(float x, float y, float z);
	
	default void process(boolean bool)
	{
		if(this.getPart() == null || animationSteps() == null) {
			return;
		}
		
		MutablePair<Vector3f, Vector3f> step = animationSteps()[currentStep()];
		Vector3f movement = getPartMovement();
		if(bool) {
			if(!Animation.isPartMovementFinished(this)) {
				setMovement(movement.x, Animation.getPartialMovement(movement.y, step.getRight().y, 0.2f * getSpeed()), movement.z);
			}
			else if(Animation.canExecuteNextStep(currentStep(), animationSteps()))
			{
				setCurrentStep(currentStep() + 1);
			}
		}
		else if(!bool) {
			setCurrentStep(0);
			if(!Animation.isPartReturned(this)) {
				setMovement(
						Animation.getPartialMovement(movement.x, 0f, 0.2f * getSpeed()),
						Animation.getPartialMovement(movement.y, 0f, 0.2f * getSpeed()),
						Animation.getPartialMovement(movement.z, 0f, 0.2f * getSpeed()));
			}
		}
		
		PUBGMCUtil.setModelPosition(getPart(), movement.x, movement.y, movement.z);
		//TODO: PUBGMCUtil.setModelRotation(getPart(), x, y, z);
	}
}

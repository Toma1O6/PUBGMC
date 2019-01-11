package com.toma.pubgmc.animation;

public interface IGunAnimation
{
	/**
	 * This will move the weapon into it's cords.
	 * Has to run every tick for smooth movement
	 */
	public void processAnimation(boolean scopeIn);
}

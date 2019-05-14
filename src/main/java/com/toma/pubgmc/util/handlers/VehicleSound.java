package com.toma.pubgmc.util.handlers;

import com.toma.pubgmc.common.entity.EntityVehicle;

import net.minecraft.client.audio.ITickableSound;
import net.minecraft.client.audio.PositionedSound;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.MathHelper;

public class VehicleSound extends PositionedSound implements ITickableSound
{
	private final EntityVehicle vehicle;
	
	public VehicleSound(SoundEvent event, EntityVehicle vehicle)
	{
		super(event, SoundCategory.MASTER);
		this.vehicle = vehicle;
		
		xPosF = (float)vehicle.posX;
		yPosF = (float)vehicle.posY;
		zPosF = (float)vehicle.posZ;
	}
	
	@Override
	public boolean isDonePlaying() 
	{
		return vehicle.isDead;
	}
	
	@Override
	public void update() 
	{
		if(!isDonePlaying())
		{
            this.xPosF = (float)this.vehicle.posX;
            this.yPosF = (float)this.vehicle.posY;
            this.zPosF = (float)this.vehicle.posZ;
            double d = Math.sqrt(this.vehicle.motionX * this.vehicle.motionX + this.vehicle.motionZ * this.vehicle.motionZ);
            if (d >= 0.01D) {
                this.volume = 0.0F + MathHelper.clamp((float)d, 0.0F, 0.5F) * 0.7F;
            }
            else
                this.volume = 0.0F;
		}
	}
}

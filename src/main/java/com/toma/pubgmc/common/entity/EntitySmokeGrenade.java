package com.toma.pubgmc.common.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.MoverType;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class EntitySmokeGrenade extends Entity
{
	public EntityLivingBase thrower;
	private int id;
	protected int fuse;
	protected float velocity = 0.5f;
	private double bounce = 0.15d;
	private double bounce_small = 0.8d;
	

	public EntitySmokeGrenade(World worldIn) 
	{
        super(worldIn);
        this.fuse = 480;
        this.preventEntitySpawning = true;
        this.isImmuneToFire = true;
        this.setSize(0.15f, 0.15f);
	}
	
	public EntitySmokeGrenade(World world, EntityLivingBase thrower, boolean isRightClick)
	{
		this(world);
		this.setPosition(thrower.posX, thrower.posY + thrower.getEyeHeight(), thrower.posZ);
		
		Vec3d vec = thrower.getLookVec();
		double modifier = 1;
		float strenght = 0.5f;
		if(isRightClick)
		{
			strenght = 1f;
		}
		
		if(thrower.isSprinting())
		{
			modifier = 1.25;
		}
		
        this.motionX = ((vec.x * 1.5) * modifier) * strenght;
        this.motionY = ((vec.y * 1.5) * modifier) * strenght;
        this.motionZ = ((vec.z * 1.5) * modifier) * strenght;
        this.thrower = thrower;
	}
	
	@Override
	public boolean isInRangeToRenderDist(double distance)
	{
		return true;
	}
	
    public void onUpdate()
    {
        this.prevPosX = this.posX;
        this.prevPosY = this.posY;
        this.prevPosZ = this.posZ;

        if (!this.hasNoGravity())
        {
            this.motionY -= 0.03999999910593033D;
        }

        this.move(MoverType.SELF, this.motionX, this.motionY, this.motionZ);
        this.motionX *= 0.9800000190734863D;
        this.motionY *= 0.9800000190734863D;
        this.motionZ *= 0.9800000190734863D;

        if (this.onGround)
        {
            this.motionX *= 0.699999988079071D;
            this.motionZ *= 0.699999988079071D;
            this.motionY *= -0.5D;
        }

        --this.fuse;
        
        if (this.fuse <= 400)
        {
        	if(world.isRemote)
        	{
            	if(!this.isInWater())
            	{
                    this.world.spawnParticle(EnumParticleTypes.EXPLOSION_HUGE, true, this.posX, this.posY + 0.5, this.posZ, 0, 0, 0, 0);
            	}
            	
            	else
            	{
                    this.world.spawnParticle(EnumParticleTypes.WATER_BUBBLE, true, this.posX, this.posY, this.posZ, 0, 0.15, 0.2, 0);
                    this.world.spawnParticle(EnumParticleTypes.WATER_BUBBLE, true, this.posX, this.posY, this.posZ, 0, 0.15, -0.2, 0);
                    this.world.spawnParticle(EnumParticleTypes.WATER_BUBBLE, true, this.posX, this.posY, this.posZ, 0.2, 0.15, 0, 0);
                    this.world.spawnParticle(EnumParticleTypes.WATER_BUBBLE, true, this.posX, this.posY, this.posZ, -0.2, 0.15, 0, 0);
                    this.world.spawnParticle(EnumParticleTypes.WATER_BUBBLE, true, this.posX, this.posY, this.posZ, 0.2, 0.15, 0.2, 0);
                    this.world.spawnParticle(EnumParticleTypes.WATER_BUBBLE, true, this.posX, this.posY, this.posZ, 0.2, 0.15, -0.2, 0);
                    this.world.spawnParticle(EnumParticleTypes.WATER_BUBBLE, true, this.posX, this.posY, this.posZ, -0.2, 0.15, 0.2, 0);
                    this.world.spawnParticle(EnumParticleTypes.WATER_BUBBLE, true, this.posX, this.posY, this.posZ, -0.2, 0.15, -0.2, 0);
            	}
        	}
        }
        
        if(this.fuse <= 0)
        {
        	this.setDead();
        }
        else
        {
            this.handleWaterMovement();
            if(!this.isInWater())
            {
                this.world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, this.posX, this.posY + 0.1D, this.posZ, 0.0D, 0.0D, 0.0D);
            }
            
            else
            {
            	this.world.spawnParticle(EnumParticleTypes.WATER_BUBBLE, this.posX, this.posY + 0.1D, this.posZ, 0.0D, 0.1D, 0.0D);
            }
        }
    }
	
	@Override
	public boolean canBeCollidedWith()
	{
		return true;
	}
	
	@Override
	public boolean canBePushed()
	{
		return false;
	}
	
	@Override
	public boolean attackEntityFrom(DamageSource source, float amount)
	{
		return false;
	}
	
	@Override
	protected void entityInit()
	{
	}
	
	@Override
	protected void writeEntityToNBT(NBTTagCompound compound)
	{
		compound.setDouble("posX", this.posX);
		compound.setDouble("posY", this.posY);
		compound.setDouble("posZ", this.posZ);
		compound.setDouble("motionX", this.motionX);
		compound.setDouble("motionY", this.motionY);
		compound.setDouble("motionZ", this.motionZ);
		compound.setInteger("fuse", this.fuse);
	}
	
	@Override
	protected void readEntityFromNBT(NBTTagCompound compound) 
	{
		posX = compound.getDouble("posX");
		posY = compound.getDouble("posY");
		posZ = compound.getDouble("posZ");
		motionX = compound.getDouble("motionX");
		motionY = compound.getDouble("motionY");
		motionZ = compound.getDouble("motionZ");
		fuse = compound.getInteger("fuse");
	}
}

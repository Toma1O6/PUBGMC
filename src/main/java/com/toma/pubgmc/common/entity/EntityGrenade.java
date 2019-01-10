package com.toma.pubgmc.common.entity;

import com.toma.pubgmc.common.capability.IPlayerData;
import com.toma.pubgmc.common.capability.IPlayerData.PlayerDataProvider;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.MoverType;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class EntityGrenade extends Entity
{
	public EntityLivingBase thrower;
	private int id;
	private int fuse;
	protected float velocity = 0.5f;
	private double bounce = 0.15d;
	private double bounce_small = 0.8d;
	

	public EntityGrenade(World worldIn) 
	{
        super(worldIn);
        this.fuse = 80;
        this.preventEntitySpawning = true;
        this.isImmuneToFire = true;
        this.setSize(0.15f, 0.15f);
	}
	
	public EntityGrenade(World world, EntityLivingBase thrower, boolean isRightClick)
	{
		this(world);
		IPlayerData data = thrower.getCapability(PlayerDataProvider.PLAYER_DATA, null);
		this.fuse = 80 - data.getCookingTime();
		this.setPosition(thrower.posX, thrower.posY + thrower.getEyeHeight(), thrower.posZ);
		float strenght = 1f;
		
		if(!isRightClick)
		{
			strenght = 0.5f;
		}
		
		Vec3d vec = thrower.getLookVec();
		double modifier = 1;
		if(thrower.isSprinting())
		{
			modifier = 1.25;
		}
		
        this.motionX = ((vec.x * 1.5) * modifier) * strenght;
        this.motionY = ((vec.y * 1.5) * modifier) * strenght;
        this.motionZ = ((vec.z * 1.5) * modifier) * strenght;
      
        this.thrower = thrower;
	}
	
	public EntityGrenade(World world, EntityLivingBase thrower, int fuse) 
	{
		this(world, thrower, false);
		IPlayerData data = thrower.getCapability(PlayerDataProvider.PLAYER_DATA, null);
		
		this.fuse = fuse;
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

        if (this.fuse <= 0)
        {
            if (!this.world.isRemote)
            {
            	world.createExplosion(this, posX, posY, posZ, 5.0f, false);
            }
            
            this.setDead();
        }
        else
        {
            this.handleWaterMovement();
            if(!this.isInWater())
            {
                this.world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, this.posX, this.posY + 0.2D, this.posZ, 0.0D, 0.0D, 0.0D);
            }
            
            else
            {
                this.world.spawnParticle(EnumParticleTypes.WATER_BUBBLE, this.posX, this.posY + 0.2D, this.posZ, 0.0D, 0.1D, 0.0D);
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
		compound.setInteger("fuse", this.fuse);
		compound.setInteger("id", this.id);
	}
	
	@Override
	protected void readEntityFromNBT(NBTTagCompound compound) 
	{
		compound.getInteger("fuse");
		compound.getInteger("id");
	}
	
	public void setFuse(int fuse)
	{
		this.fuse = fuse;
	}
	
	public int getFuse() 
	{
		return fuse;
	}
	
}

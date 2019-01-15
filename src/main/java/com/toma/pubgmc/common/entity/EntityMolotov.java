package com.toma.pubgmc.common.entity;

import java.util.List;
import java.util.Random;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class EntityMolotov extends Entity
{
	public EntityLivingBase thrower;
	private int id;
	private int damageDelay = 0;
	protected int fuse;
	protected float velocity = 0.5f;
	private double bounce = 0.15d;
	private double bounce_small = 0.8d;
	
	private Random rand = new Random();

	public EntityMolotov(World worldIn) 
	{
        super(worldIn);
        this.fuse = 200;
        this.preventEntitySpawning = true;
        this.isImmuneToFire = true;
        this.setSize(0.15f, 0.15f);
	}
	
	public EntityMolotov(World world, EntityLivingBase thrower, boolean isRightClick)
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
			modifier = 1.3;
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
            this.motionX = 0;
            this.motionZ = 0;
            this.motionY = 0;
            --this.fuse;
            
            createParticles(world);
            
            List<Entity> list = world.getEntitiesWithinAABBExcludingEntity(this, new AxisAlignedBB(this.posX - 4, this.posY, this.posZ - 4, this.posX + 4, this.posY + 1.5, this.posZ + 4));
            for(Entity entity : list)
            {
            	if(entity instanceof EntityLiving)
            	{
            		EntityLiving entityliv = (EntityLiving)entity;
            		
            		entity.setFire(10);
            	}
            	
            	if(entity instanceof EntityPlayer)
            	{
            		EntityPlayer player = (EntityPlayer)entity;
            		
            		
            		player.setFire(10);
            		
            		if(damageDelay <= 0 && !player.isDead)
            		{
            			damageDelay = 15;
            			//Use ON_FIRE instead of IN_FIRE otherwise it will damage the armor 
            			player.attackEntityFrom(DamageSource.ON_FIRE, 5);
            		}
            		
            		damageDelay--;
            	}
            }
        }
        
        if(this.fuse <= 0)
        {
        	this.setDead();
        }
        
        if(this.isInWater())
        {
        	this.setDead();
        }
        else
        {
            this.handleWaterMovement();
            this.world.spawnParticle(EnumParticleTypes.FLAME, this.posX, this.posY + 0.5D, this.posZ, 0.0D, 0.0D, 0.0D);
        }
    }
    
    private void createParticles(World world)
    {	
    	this.world.spawnParticle(EnumParticleTypes.FLAME, this.posX, this.posY + 0.6D, this.posZ, rand.nextDouble() - 0.55, 0.0D, rand.nextDouble() - 0.55);
    	this.world.spawnParticle(EnumParticleTypes.FLAME, this.posX, this.posY + 0.6D, this.posZ, -(rand.nextDouble() - 0.55), 0.0D, -(rand.nextDouble() - 0.55));
    	this.world.spawnParticle(EnumParticleTypes.FLAME, this.posX, this.posY + 0.6D, this.posZ, rand.nextDouble() - 0.55, 0.0D, rand.nextDouble() - 0.55);
    	this.world.spawnParticle(EnumParticleTypes.FLAME, this.posX, this.posY + 0.6D, this.posZ, -(rand.nextDouble() - 0.55), 0.0D, -(rand.nextDouble() - 0.55));
    	this.world.spawnParticle(EnumParticleTypes.FLAME, this.posX, this.posY + 0.6D, this.posZ, rand.nextDouble() - 0.55, 0.0D, rand.nextDouble() - 0.55);
    	this.world.spawnParticle(EnumParticleTypes.FLAME, this.posX, this.posY + 0.6D, this.posZ, -(rand.nextDouble() - 0.55), 0.0D, -(rand.nextDouble() - 0.55));
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
		compound.setInteger("delay", this.damageDelay);
	}
	
	@Override
	protected void readEntityFromNBT(NBTTagCompound compound) 
	{
		compound.getInteger("fuse");
		compound.getInteger("id");
		compound.getInteger("delay");
	}
	
	
	
}

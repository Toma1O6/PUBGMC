package com.toma.pubgmc.common.entity;

import com.toma.pubgmc.common.entity.vehicles.EntityTestVehicle;
import com.toma.pubgmc.init.PMCDamageSources;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.RayTraceResult.Type;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class EntityVehicle extends Entity
{
	private static final AxisAlignedBB BOX = new AxisAlignedBB(-0.5d, 0d, -0.5d, 1.5d, 1d, 1.5d);
	private static final float MAX_TURNING_MODIFIER = 2.0F;
	
	public int vehicleID;
	
	private boolean isWaterVehicle;
	
	/** The max damage vehicle can take before it explodes **/
	private float maxHealth;
	
	private float health;
	
	/** Acceleration speed **/
	private float acceleration;
	
	/** The max speed vehicle will be able to do **/
	private float maxSpeed;
	
	private float currentSpeed;
	
	/** How well will the vehicle drive on different surfaces **/
	private float turnModifier;
	
	/** The amount of fuel vehicle can take **/
	private float maxFuel;
	
	private float fuel;
	
	private boolean randomFuelLevel = true;
	
	/** If the vehicle is broken or not **/
	private boolean isBroken = false;
	
	private int timeInInvalidState;
	
	/** Handles inputs from player who is driving the vehicle **/
	private boolean inputForward, inputBack, inputRight, inputLeft, inputBoost;
	
	private SoundEvent driving_sound, braking_sound;
	
	public EntityVehicle(World world)
	{
		super(world);
		setSize(1f, 1f);
		preventEntitySpawning = true;
	}
	
	public EntityVehicle(World world, int x, int y, int z, boolean waterVehicle)
	{
		this(world);
		setPosition(x, y, z);
		setHealth(maxHealth);
		if(randomFuelLevel) setFuelLevel(rand.nextFloat() * maxFuel);
		else setFuelLevel(maxFuel);
	}
	
	protected void handleVehicle()
	{
		Vec3d vec = this.getLookVec();
		
		handleAcceleration();
		handleBraking();
		handleTurning();
		
		if(isVehicleMoving() && !inputForward && !inputBack)
		{
			if(isVehicleMovingForward())
			{
				if(currentSpeed > 0.02f)
				{
					currentSpeed -= 0.01f;
				}
				
				else currentSpeed = 0f;
			}
			
			else if(isVehicleMovingBackward())
			{
				if(currentSpeed < -0.02f)
				{
					currentSpeed += 0.01f;
				}
				
				else currentSpeed = 0;
			}
		}
		
		if(!inputRight && !inputLeft)
		{
			if(turnModifier != 0f)
			{
				if(turnModifier > 0f)
				{
					if(turnModifier > 0.2f)
					{
						turnModifier -= 0.2f;
					}
					
					else turnModifier = 0f;
				}
				
				else if(turnModifier < 0f)
				{
					if(turnModifier < -0.2f)
					{
						turnModifier += 0.2f;
					}
					
					else turnModifier = 0f;
				}
			}
		}
		
		motionX = (vec.x * currentSpeed) / 10;
		motionZ = (vec.z * currentSpeed) / 10;
		
		move(MoverType.SELF, motionX, motionY, motionZ);
	}
	
	@Override
	public void onUpdate() 
	{
		if(!this.isBeingRidden())
		{
			if(inputForward || inputBack || inputRight || inputLeft || inputBoost)
			{
				inputForward = false;
				inputBack = false;
				inputRight = false;
				inputLeft = false;
				inputBoost = false;
			}
		}
		
		handleVehicleBreaking();

		if(!isBroken)
		{
			checkSteps();
			handleVehicle();
		}
		
		if(!onGround)
		{
			motionY = -0.3d;
		}
		
		//Handling vehicle collisions with blocks to apply collision damage to passengers
		if(currentSpeed > 0 && (motionX == 0 || motionZ == 0))
		{
			if(currentSpeed > 1f)
			{
				for(Entity e : this.getPassengers())
				{
					e.attackEntityFrom(PMCDamageSources.VEHICLE, currentSpeed);
				}
				
				this.health -= currentSpeed;
			}

			currentSpeed = 0f;
		}
		
		move(MoverType.SELF, motionX, motionY, motionZ);
		super.onUpdate();
	}
	
	@Override
	public boolean attackEntityFrom(DamageSource source, float amount) 
	{
		health -= amount;

		return true;
	}
	
	private void checkSteps()
	{
		Vec3d vec1 = new Vec3d(posX, posY, posZ);
		Vec3d vec2 = new Vec3d(posX + motionX, posY + motionY, posZ + motionZ);
		RayTraceResult trace = world.rayTraceBlocks(vec1, vec2, false, true, false);
		
		if(trace != null)
		{
			if(trace.typeOfHit == Type.BLOCK)
			{
				BlockPos potentialCollision = new BlockPos(trace.hitVec.x, trace.hitVec.y, trace.hitVec.z);
				Block block = world.getBlockState(potentialCollision).getBlock();
				if(!block.isReplaceable(world, potentialCollision))
				{
					if(world.isAirBlock(new BlockPos(potentialCollision.getX(), potentialCollision.getY()+1, potentialCollision.getZ())))
					{
						setPosition(posX, posY + 1, posZ);
					}
				}
			}
		}
	}
	
	private void handleAcceleration()
	{
		if(inputForward && !inputBack)
		{
			if(currentSpeed < maxSpeed)
			{
				currentSpeed += 0.1f;
			}
		}
		
		else
		{
			if(currentSpeed > 0)
			{
				currentSpeed -= 0.01f;
			}
		}
	}
	
	private void handleTurning()
	{
		if(isVehicleMovingBackward())
		{
			if(inputRight && !inputLeft && turnModifier < MAX_TURNING_MODIFIER)
			{
				turnModifier -= 0.2f;
			}
			
			if(inputLeft && !inputRight && turnModifier > -MAX_TURNING_MODIFIER)
			{
				turnModifier += 0.2f;
			}
		}
		
		else if(isVehicleMoving())
		{
			if(inputRight && !inputLeft && turnModifier < MAX_TURNING_MODIFIER)
			{
				turnModifier += 0.2f;
			}
			
			if(inputLeft && !inputRight && turnModifier > -MAX_TURNING_MODIFIER)
			{
				turnModifier -= 0.2f;
			}
		}
		
		if(!inputLeft && !inputRight && turnModifier != 0f)
		{
			if(turnModifier > 0 && turnModifier > -MAX_TURNING_MODIFIER)
			{
				turnModifier -= 0.1f;
			}
			
			else turnModifier += 0.1f;
		}
		
		if(this.isVehicleMoving())
		{
			this.rotationYaw += turnModifier;
		}
	}
	
	private void handleBraking()
	{
		if(inputBack && !inputForward)
		{
			if(isVehicleMovingForward())
			{
				currentSpeed -= 0.1f;
			}
			
			if(currentSpeed <= 0 && currentSpeed > (-maxSpeed / 4))
			{
				//reverse mode
				currentSpeed -= 0.05f;
			}
		}
	}
	
	private void handleVehicleBreaking()
	{
		if(timeInInvalidState >= 100)
		{
			isBroken = true;
		}
		
		if(health <= 0 && isAddedToWorld()) 
		{
			explode();
		}
		
		if(!isWaterVehicle)
		{
			if(isInWater() || isInLava())
			{
				timeInInvalidState++;
			}
			
			else if(timeInInvalidState > 0)
			{
				timeInInvalidState--;
			}
		}
		
		else
		{
			if(!isInWater() || isInLava())
			{
				timeInInvalidState++;
			}
			
			else if(timeInInvalidState > 0)
			{
				timeInInvalidState--;
			}
		}
		
		if(isInLava())
		{
			explode();
		}
		
		if(isBroken)
		{
			if(motionX != 0)
			{
				motionX *= 0.9d;
			}
			
			if(motionZ != 0)
			{
				motionZ *= 0.9d;
			}
		}
	}
	
	@Override
	public boolean processInitialInteract(EntityPlayer player, EnumHand hand)
	{
		if(!world.isRemote)
		{
			if(this.canBeRidden(player))
			{
				player.startRiding(this);
			}
		}
		
		return true;
	}
	
	private void explode()
	{
		if(!world.isRemote)
		{
			this.world.createExplosion(this, posX, posY, posZ, 3f, false);
			this.setDead();
		}
	}
	
	@Override
	public void applyEntityCollision(Entity entityIn) 
	{
		if(currentSpeed >= 3f)
		{
			entityIn.attackEntityFrom(PMCDamageSources.VEHICLE, currentSpeed * 2.5f);
			
			entityIn.motionX = this.motionX;
			entityIn.motionY = this.motionY * 1.5d;
			entityIn.motionZ = this.motionZ;
		}
	}
	
	public void handleInputs(boolean forward, boolean back, boolean right, boolean left, boolean boost, EntityPlayer player)
	{
		if(isPlayerDriver(player))
		{
			this.inputForward = forward;
			this.inputBack = back;
			this.inputLeft = left;
			this.inputRight = right;
			this.inputBoost = boost;
		}
	}
	
	@Override
	protected void doBlockCollisions()
	{
		super.doBlockCollisions();
	}
	
	@Override
	public void onCollideWithPlayer(EntityPlayer entityIn)
	{
		if(this.isVehicleMoving() && entityIn.getRidingEntity() != this)
		{
			entityIn.attackEntityFrom(PMCDamageSources.VEHICLE, currentSpeed * 2.5f);

			entityIn.motionY = currentSpeed / 5;
			entityIn.motionX = this.motionX * currentSpeed * 20;
			entityIn.motionZ = this.motionZ * currentSpeed * 20;
		}
	}
	
	@Override
	public boolean isInRangeToRenderDist(double distance) 
	{
		return true;
	}
	
	public boolean isPlayerDriver(EntityPlayer player)
	{
		return player.isRiding() && player.getRidingEntity() instanceof EntityVehicle && player.getRidingEntity().getPassengers().get(0) == player;
	}
	
	private boolean isVehicleMoving()
	{
		return currentSpeed != 0;
	}
	
	private boolean isVehicleMovingForward()
	{
		return currentSpeed > 0;
	}
	
	private boolean isVehicleMovingBackward()
	{
		return currentSpeed < 0;
	}
	
	@Override
	protected void entityInit() 
	{
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
		health = compound.getFloat("health");
		fuel = compound.getFloat("fuel");
		currentSpeed = compound.getFloat("speed");
		isBroken = compound.getBoolean("isBroken");
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
		compound.setFloat("health", this.health);
		compound.setFloat("fuel", this.fuel);
		compound.setFloat("speed", this.currentSpeed);
		compound.setBoolean("isBroken", this.isBroken);
	}
	
	@Override
	public boolean canBeCollidedWith()
	{
		return true;
	}
	
	public boolean isBroken()
	{
		return isBroken;
	}
	
	public boolean hasFuel()
	{
		return fuel > 0f;
	}
	
	public void setHealth(float health)
	{
		this.health = health;
	}
	
	public void setFuelLevel(float fuel)
	{
		this.fuel = fuel;
	}
	
	public void setMaxHealth(float maxHealth)
	{
		this.maxHealth = maxHealth;
	}
	
	public void setMaxFuel(float fuel)
	{
		this.maxFuel = fuel;
	}
	
	public void setHasRandomFuelLevel(boolean fuelLevel)
	{
		this.randomFuelLevel = fuelLevel;
	}
	
	public boolean getHasRandomFuelLevel()
	{
		return randomFuelLevel;
	}
	
	public void setMaxSpeed(float maxSpeed)
	{
		this.maxSpeed = maxSpeed;
	}
	
	public void setWaterVehicle(boolean waterVehicle)
	{
		this.isWaterVehicle = waterVehicle;
	}
	
	public void setVehicleID(int id)
	{
		this.vehicleID = id;
	}
	
	public int getVehicleID()
	{
		return vehicleID;
	}
	
	public boolean isWaterVehicle()
	{
		return isWaterVehicle;
	}
	
	public float getTurnModifier()
	{
		return turnModifier;
	}
	
	public static EntityVehicle getVehicleByID(int id, World world, BlockPos pos)
	{
		switch(id)
		{
			case 0: return new EntityTestVehicle(world, pos.getX(), pos.getY() + 1.5, pos.getZ());
			default: return null;
		}
	}
}

package com.toma.pubgmc.common.entity;

import java.text.DecimalFormat;
import java.util.List;

import javax.annotation.Nullable;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.toma.pubgmc.common.network.PacketHandler;
import com.toma.pubgmc.common.network.sp.PacketSpawnVehicle;
import com.toma.pubgmc.common.network.sp.PacketVehicleData;
import com.toma.pubgmc.init.PMCDamageSources;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.Entity;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntitySelectors;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public abstract class EntityVehicle extends Entity implements IEntityAdditionalSpawnData
{
	private static final Predicate<Entity> TARGET = Predicates.and(EntitySelectors.NOT_SPECTATING, EntitySelectors.IS_ALIVE, Entity::canBeCollidedWith);
	private static final AxisAlignedBB BOX = new AxisAlignedBB(-0.5d, 0d, -0.5d, 1.5d, 1d, 1.5d);
	private static final float MAX_TURNING_MODIFIER = 3F;
	
	private boolean isWaterVehicle;
	
	/** The max damage vehicle can take before it explodes **/
	public float maxHealth = 100f;
	
	/** Current health state **/
	public float health = 100f;
	
	/** Acceleration speed **/
	public float acceleration = 0.08f;
	
	/** Speed at which will vehicle turn **/
	public float turnSpeed = 0.45f;
	
	/** The max speed vehicle will be able to do **/
	public float maxSpeed = 1.2f;
	
	public float currentSpeed = 0;
	
	/** How well will the vehicle drive on different surfaces **/
	public float turnModifier;
	
	/** Decides if vehicle can drive **/
	public float fuel = 100f;
	
	/** If the vehicle is broken or not **/
	private boolean isBroken = false;
	
	/** Ticks underWater **/
	private short timeInInvalidState;
	
	/** Handles inputs from player who is driving the vehicle **/
	private boolean inputForward, inputBack, inputRight, inputLeft, inputBoost;
	
	public EntityVehicle(World world)
	{
		super(world);
		setSize(1f, 1f);
		stepHeight = 1f;
		preventEntitySpawning = true;
		maxSpeed = 1.0f;
	}
	
	public EntityVehicle(World world, int x, int y, int z)
	{
		this(world);
		setPosition(x, y, z);
	}
	
	public abstract int getMaximumCapacity();
	
	@Override
	public void onUpdate()
	{
		super.onUpdate();

		if(!world.isRemote)
		{
			if(!this.isBeingRidden() && (!noAccerationInput() || !noTurningInput() || !hasFuel()))
			{
				inputForward = false;
				inputBack = false;
				inputRight = false;
				inputLeft = false;
			}
			
			updateMotion();
			handleEntityCollisions();
			checkState();
			
			if(collidedHorizontally)
			{
				currentSpeed *= 0.6;
			}
			
			PacketHandler.sendToClientsAround(new PacketVehicleData(this).health(health).fuel(fuel), dimension, posX, posY, posZ, 256);
		}
		
		move(MoverType.SELF, motionX, motionY, motionZ);
	}
	
	public void handleEntityCollisions()
	{
		Vec3d vec1 = new Vec3d(posX, posY, posZ);
		Vec3d vec2 = new Vec3d(vec1.x + motionX, vec1.y + motionY, vec1.z + motionZ);
		Entity e = findEntityInPath(vec1, vec2);
		
		if(e != null)
		{
			e.motionX += motionX * currentSpeed * 3;
			e.motionY += currentSpeed;
			e.motionZ += motionZ * currentSpeed * 3;
			e.attackEntityFrom(PMCDamageSources.VEHICLE, Math.abs(currentSpeed) * 25f);
		}
	}
	
	public void updateMotion()
	{
		Vec3d lookVec = getLookVec();
		
		if(!isBroken && hasFuel())
		{
			if(inputForward && !inputBack)
			{
				burnFuel();
				currentSpeed = currentSpeed < maxSpeed ? currentSpeed + acceleration : maxSpeed;
			}
			
			if(inputBack && !inputForward)
			{
				burnFuel();
				currentSpeed = currentSpeed > 0 ? currentSpeed - acceleration : currentSpeed > (-maxSpeed * 0.3f) ? currentSpeed - 0.02f : -maxSpeed * 0.3f;
			}
		}
		
		if(inputRight && !inputLeft)
		{
			turnModifier = turnModifier < MAX_TURNING_MODIFIER ? turnModifier + turnSpeed : MAX_TURNING_MODIFIER;
		}
		
		if(inputLeft && !inputRight)
		{
			turnModifier = turnModifier > -MAX_TURNING_MODIFIER ? turnModifier - turnSpeed : -MAX_TURNING_MODIFIER;
		}
		
		if(noAccerationInput() || isBroken)
		{
			if(Math.abs(currentSpeed) < 0.009f) currentSpeed = 0f;
			
			if(currentSpeed != 0)
			{
				currentSpeed = currentSpeed > 0 ? currentSpeed - 0.008f : currentSpeed + 0.008f;
			}
		}
		
		if(noTurningInput())
		{
			if(Math.abs(turnModifier) < 0.1f) turnModifier = 0f;
			
			if(turnModifier != 0)
			{
				turnModifier = turnModifier > 0 ? turnModifier - 0.3f : turnModifier + 0.3f;
			}
		}
		
		motionX = lookVec.x * currentSpeed;
		motionZ = lookVec.z * currentSpeed;
		if(currentSpeed != 0)
		{
			rotationYaw += currentSpeed > 0 ? turnModifier : -turnModifier;
		}
		
		if(!onGround) motionY -= 0.1d;
	}
	
	@Nullable
	protected Entity findEntityInPath(Vec3d start, Vec3d end)
	{
		Entity e = null;
		List<Entity> entityList = world.getEntitiesInAABBexcluding(this, this.getEntityBoundingBox().expand(motionX, motionY + 1, motionZ).grow(1d), TARGET);
		double d0 = 0;
		
		for(int i = 0; i < entityList.size(); i++)
		{
			Entity entity = entityList.get(i);
			
			if(entity != this)
			{
                AxisAlignedBB axisalignedbb = entity.getEntityBoundingBox().grow(0.30000001192092896D);
                RayTraceResult raytraceresult = axisalignedbb.calculateIntercept(start, end);
                
                if (raytraceresult != null)
                {
                    double d1 = start.squareDistanceTo(raytraceresult.hitVec);
                    
                    if (d1 < d0 || d0 == 0.0D)
                    {
                        e = entity;
                        d0 = d1;
                    }
                }
			}
		}
		
		return e;
	}
	
	@Override
	public boolean processInitialInteract(EntityPlayer player, EnumHand hand)
	{
		if(!world.isRemote)
		{
			if(this.canBeRidden(player) && canFitPassenger(player))
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
	
	// Should be running only on server side in case some client doesn't receive packet
	// containing new health value of this vehicle
	protected void checkState()
	{
		// if whole vehicle is under water -> can drive in shallow water
		if(this.isInWater() && world.getBlockState(getPosition().up()).getMaterial().isLiquid())
		{
			timeInInvalidState++;
			motionX *= 0.4d;
			motionZ *= 0.4d;
			motionY = -0.15d;
		}
		
		if(timeInInvalidState > 30)
		{
			isBroken = true;
		}
		
		if(isInLava() || health <= 0f) explode();
	}
	
	@Override
	public boolean attackEntityFrom(DamageSource source, float amount)
	{
		if(!getPassengers().contains(source.getTrueSource()))
		{
			this.health -= amount;
		}
		
		return true;
	}
	
	@Override
    public void updatePassenger(Entity passenger)
    {
        if(this.isPassenger(passenger))
        {
            float x = 0F;
            float z = -0.55F;
            float f1 = (float)((this.isDead ? 0.009999999776482582D : this.getMountedYOffset()) + passenger.getYOffset());

            if(this.getPassengers().size() > 0)
            {
                int i = this.getPassengers().indexOf(passenger);
                x = getPassengerXOffset(i);
                z = getPassengerZOffset(i);
            }

            Vec3d vec3d = (new Vec3d((double)x, 0.0D, (double)z)).rotateYaw(-this.rotationYaw * 0.017453292F - ((float)Math.PI / 2F));
            passenger.setPosition(this.posX + vec3d.x, this.posY + (double)f1, this.posZ + vec3d.z);

            if (passenger instanceof EntityAnimal && this.getPassengers().size() > 1)
            {
                int j = passenger.getEntityId() % 2 == 0 ? 90 : 270;
                passenger.setRenderYawOffset(((EntityAnimal)passenger).renderYawOffset + (float)j);
                passenger.setRotationYawHead(passenger.getRotationYawHead() + (float)j);
            }
        }
    }
	
	@Override
	protected boolean canFitPassenger(Entity passenger)
	{
		return this.getPassengers().size() < getMaximumCapacity();
	}
	
	public void handleInputs(boolean forward, boolean back, boolean right, boolean left, EntityPlayer player)
	{
		if(isPlayerDriver(player))
		{
			if(hasFuel())
			{
				this.inputForward = forward;
				this.inputBack = back;
			}
			
			this.inputLeft = left;
			this.inputRight = right;
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
		maxHealth = compound.getFloat("maxHealth");
		health = compound.getFloat("health");
		fuel = compound.getFloat("fuel");
		currentSpeed = compound.getFloat("speed");
		acceleration = compound.getFloat("acceleration");
		turnSpeed = compound.getFloat("turnSpeed");
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
		compound.setFloat("maxHealth", this.maxHealth);
		compound.setFloat("health", this.health);
		compound.setFloat("fuel", this.fuel);
		compound.setFloat("speed", this.currentSpeed);
		compound.setFloat("acceleration", this.acceleration);
		compound.setFloat("turnSpeed", this.turnSpeed);
		compound.setBoolean("isBroken", this.isBroken);
	}
	
	@Override
	public boolean canBeCollidedWith()
	{
		return true;
	}
	
	protected float getPassengerXOffset(int passengerIndex)
	{
		return passengerIndex % 2 == 0 ? 0.5f : -0.5f;
	}
	
	protected float getPassengerZOffset(int passengerIndex)
	{
		return passengerIndex < 2 ? -0.55f : 0.55f;
	}
	
    protected void applyYawToEntity(Entity entityToUpdate)
    {
        entityToUpdate.setRenderYawOffset(this.rotationYaw);
        float f = MathHelper.wrapDegrees(entityToUpdate.rotationYaw - this.rotationYaw);
        float f1 = MathHelper.clamp(f, -105.0F, 105.0F);
        entityToUpdate.prevRotationYaw += f1 - f;
        entityToUpdate.rotationYaw += f1 - f;
        entityToUpdate.setRotationYawHead(entityToUpdate.rotationYaw);
    }
	
	public boolean noAccerationInput()
	{
		return !inputForward && !inputBack;
	}
	
	public boolean noTurningInput()
	{
		return !inputRight && !inputLeft;
	}
	
	public void setAllRequiredValues(float maxHealth, float health, float maxSpeed, float acceleration, float turnSpeed)
	{
		this.maxHealth = maxHealth;
		this.health = health;
		this.maxSpeed = maxSpeed;
		this.acceleration = acceleration;
		this.turnSpeed = turnSpeed;
	}
	
	public boolean hasFuel()
	{
		return fuel > 0; 
	}
	
	/** Call with Fuel can **/
	public void refill()
	{
		fuel = fuel + 30f < 100f ? fuel + 30f : 100f;
	}
	
	/** Decrement each tick **/
	public void burnFuel()
	{
		fuel = hasFuel() ? fuel - 0.01f : 0f;
	}
	
	@Override
	public void writeSpawnData(ByteBuf buf)
	{
		buf.writeFloat(maxHealth);
		buf.writeFloat(health);
		buf.writeFloat(maxSpeed);
		buf.writeFloat(acceleration);
		buf.writeFloat(turnSpeed);
		buf.writeFloat(fuel);
	}
	
	@Override
	public void readSpawnData(ByteBuf buf)
	{
		maxHealth = buf.readFloat();
		health = buf.readFloat();
		maxSpeed = buf.readFloat();
		acceleration = buf.readFloat();
		turnSpeed = buf.readFloat();
		fuel = buf.readFloat();
	}
}

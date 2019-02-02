package com.toma.pubgmc.common.entity;

import com.jcraft.jorbis.Block;

import net.minecraft.entity.Entity;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;

public class EntityParachute extends Entity
{
	public static final float MAX_TURN_MODIFIER = 2.5f;
	private Entity user;
	private int color = -1;
	private float turnModifier = 0f;
	private boolean inputDown,inputUp,inputRight,inputLeft = false;
	
	public EntityParachute(World world)
	{
		super(world);
		preventEntitySpawning = true;
	}
	
	public EntityParachute(World world, Entity user)
	{
		this(world);
		setPosition(user.posX, user.posY, user.posZ);
		setSilent(true);
		setSize(1f, 1f);
		this.user = user;
		rotationYaw = user.rotationYaw;
		
		prevPosX = user.posX;
		prevPosY = user.posY;
		prevPosZ = user.posZ;
	}
	
	@Override
	public void onUpdate()
	{	
		if(!this.isBeingRidden() && ticksExisted > 2)
		{
			setDead();
		}
		
		else
		{
			Entity entity = this.getControllingPassenger();
			BlockPos pos = entity.getPosition();
			motionY = -0.2d;
			
			if(onGround || isInWater() || isInLava())
			{
				setDead();
			}
			
			if(entity instanceof EntityPlayer)
			{
				EntityPlayer player = (EntityPlayer)entity;
				handleMovement(player);
			}
			
			if(!world.isAirBlock(new BlockPos(pos.getX()-1, pos.getY(), pos.getZ())) ||
					!world.isAirBlock(new BlockPos(pos.getX()+1, pos.getY(), pos.getZ())) ||
					!world.isAirBlock(new BlockPos(pos.getX(), pos.getY(), pos.getZ()+1)) ||
					!world.isAirBlock(new BlockPos(pos.getX(), pos.getY(), pos.getZ()-1)))
				setDead();
		}
		
		move(MoverType.SELF, motionX, motionY, motionZ);
		super.onUpdate();
	}
	
	protected void handleMovement(EntityPlayer user)
	{	
		if(inputUp && !inputDown)
		{
			if(this.rotationPitch < 35f)
			{
				rotationPitch += 2f;
			}
		}
		
		if(inputDown && !inputUp)
		{
			if(this.rotationPitch > 0)
			{
				rotationPitch -= 2f;
				if(rotationPitch < 0) rotationPitch = 0f;
			}
		}
		
		if(inputRight && !inputLeft)
		{
			if(turnModifier < MAX_TURN_MODIFIER)
			{
				turnModifier += 0.4f;
			}
		}
		
		if(inputLeft && !inputRight)
		{
			if(turnModifier > -MAX_TURN_MODIFIER)
			{
				turnModifier -= 0.4f;
			}
		}
		
		this.rotationYaw += turnModifier;
		
		if(noHorizontalMovementInput() && turnModifier != 0)
		{
			if(turnModifier > 0 && turnModifier - 0.25f < 0) turnModifier = 0f;
			if(turnModifier < 0 && turnModifier + 0.25f > 0) turnModifier = 0f;
			if(turnModifier > 0) turnModifier -= 0.25f;
			if(turnModifier < 0) turnModifier += 0.25f;
		}
		
		if(noVerticalMovementInput() && rotationPitch != 0)
		{
			if(rotationPitch > 0) rotationPitch -= 0.5f;
			if(rotationPitch < 0) rotationPitch = 0f;
		}
		
		float speedModifier = 1f;
		Vec3d lookVec = this.getLookVec();
		if(rotationPitch > 0) speedModifier = 1 - (rotationPitch/100)*1.75f;

		motionX = lookVec.x * speedModifier * 0.7d;
		motionZ = lookVec.z * speedModifier * 0.7d;
	}
	
	@Override
	public void onAddedToWorld()
	{
		handleColorState();
		super.onAddedToWorld();
	}
	
	@Override
	public void applyOrientationToEntity(Entity entityToUpdate) 
	{
		entityToUpdate.rotationYaw = this.rotationYaw;
		entityToUpdate.rotationPitch = this.rotationPitch;
	}
	
	@Override
	public Entity getControllingPassenger()
	{
		return this.isBeingRidden() ? this.getPassengers().get(0) : null;
	}
	
	private void handleColorState()
	{
		color = rand.nextInt(EnumDyeColor.values().length);
	}
	
	@Override
	public void fall(float distance, float damageMultiplier)
	{
	}
	
	@Override
	public boolean isInRangeToRenderDist(double distance)
	{
		return true;
	}
	
	@Override
	public boolean attackEntityFrom(DamageSource source, float f)
	{
		setDead();
		return true;
	}
	
	@Override
	public boolean shouldRiderSit()
	{
		return false;
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
	}
	
	@Override
	protected void writeEntityToNBT(NBTTagCompound compound)
	{
		compound.setDouble("posX", posX);
		compound.setDouble("posY", posY);
		compound.setDouble("posZ", posZ);
		compound.setDouble("motionX", motionX);
		compound.setDouble("motionY", motionY);
		compound.setDouble("motionZ", motionZ);
	}

	@Override
	protected void entityInit() 
	{
	}
	
	private boolean noInput()
	{
		return !this.inputDown && !this.inputUp && !this.inputRight && !this.inputRight;
	}
	
	private boolean noHorizontalMovementInput()
	{
		return !this.inputRight && !this.inputLeft;
	}
	
	private boolean noVerticalMovementInput()
	{
		return !this.inputDown && !this.inputUp;
	}
	
	public void handleInputs(boolean down, boolean up, boolean right, boolean left)
	{
		this.inputDown = up;
		this.inputUp = down;
		this.inputRight = right;
		this.inputLeft = left;
	}
	
	public void setColor(int colorID)
	{
		this.color = colorID;
	}
	
	public int getColor()
	{
		return color;
	}
	
	public Entity getUser()
	{
		return user;
	}
}

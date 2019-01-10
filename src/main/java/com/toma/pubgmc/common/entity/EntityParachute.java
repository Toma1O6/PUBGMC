package com.toma.pubgmc.common.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.MoverType;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.Vec3d;
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
	}
	
	public EntityParachute(World world, Entity user)
	{
		this(world);
		setPosition(user.posX, user.posY, user.posZ);
		setSilent(true);
		setSize(1f, 1f);
		setColor(rand.nextInt(EnumDyeColor.values().length));
		this.user = user;
		rotationYaw = user.rotationYaw;
		
		prevPosX = user.posX;
		prevPosY = user.posY;
		prevPosZ = user.posZ;
	}
	
	@Override
	public void onUpdate()
	{	
		if(!this.isBeingRidden())
		{
			setDead();
		}
		
		else
		{	
			if(color == -1)
			{
				color = handleColorState();
			}
			
			if(!world.isRemote)
			{	
				motionY = -0.2d;
				handleParachuteDirectionState();
				Vec3d vec = this.getLookVec();
				float speedModifier = 1f;
				
				if(rotationPitch > 0f)
				{
					speedModifier = rotationPitch / 100;
					speedModifier *= 1.75f;
					speedModifier = 1 - speedModifier;
				}
				
				motionX = vec.x * 0.7d * speedModifier;
				motionZ = vec.z * 0.7d * speedModifier;
				
				if(vec.y < 0)
				{
					motionY -= this.rotationPitch / 60f;
				}
			}
			
			if(this.onGround || this.isInLava() || this.isInWater())
			{
				if(this.isBeingRidden())
				{
					if(motionY < -0.55f && this.onGround)
					{
						Entity entity = getPassengers().get(0);
						
						if(!entity.getIsInvulnerable() && !entity.isDead)
						{
							entity.attackEntityFrom(DamageSource.FALL, (float)(-motionY * 8));
						}
					}
				}
				setDead();
			}
		}
		
		move(MoverType.SELF, motionX, motionY, motionZ);
		super.onUpdate();
	}
	
	private int handleColorState()
	{
		int color = rand.nextInt(EnumDyeColor.values().length);
		return color;
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
		//color = color.values()[compound.getInteger("chute")];
	}
	
	@Override
	protected void writeEntityToNBT(NBTTagCompound compound)
	{
		//compound.setInteger("chute", color.ordinal());
	}

	@Override
	protected void entityInit() 
	{
	}
	
	private void handleParachuteDirectionState()
	{	
		if(inputRight && !inputLeft && turnModifier < MAX_TURN_MODIFIER)
		{
			this.turnModifier += 0.5f;
		}
		
		if(inputLeft && !inputRight && turnModifier > -MAX_TURN_MODIFIER)
		{
			this.turnModifier -= 0.5f;
		}
		
		if(inputDown && !inputUp)
		{
			if(this.rotationPitch < 30f)
			{
				this.rotationPitch += 2f;
			}
		}
		
		if(inputUp && !inputDown)
		{
			if(this.rotationPitch > 0f)
			{
				this.rotationPitch -= 2f;
			}
		}
		
		if(noVerticalMovementInput() && rotationPitch > 0f)
		{
			this.rotationPitch -= 0.5f;
		}
		
		if(noHorizontalMovementInput())
		{
			if(turnModifier > 0f)
			{
				turnModifier -= 0.2f;
			}
			
			else if(turnModifier < 0f)
			{
				turnModifier += 0.2f;
			}
		}
		
		this.rotationYaw += turnModifier;
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
	
	public void handlePlayerInput(boolean down, boolean up, boolean right, boolean left)
	{
		if(user != null)
		{
			this.inputDown = up;
			this.inputUp = down;
			this.inputRight = right;
			this.inputLeft = left;
		}
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

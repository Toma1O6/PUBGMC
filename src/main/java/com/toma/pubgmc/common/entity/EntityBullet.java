package com.toma.pubgmc.common.entity;

import java.util.List;

import javax.annotation.Nullable;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.toma.pubgmc.common.capability.IPlayerData;
import com.toma.pubgmc.common.capability.IPlayerData.PlayerDataProvider;
import com.toma.pubgmc.common.items.guns.GunBase;
import com.toma.pubgmc.common.items.guns.GunBase.GunType;
import com.toma.pubgmc.init.DamageSourceGun;
import com.toma.pubgmc.init.PMCDamageSources;
import com.toma.pubgmc.init.PMCSounds;
import com.toma.pubgmc.util.handlers.ConfigHandler;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.dispenser.IBlockSource;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntitySelectors;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.event.ForgeEventFactory;

public class EntityBullet extends Entity
{
	 private static final Predicate<Entity> ARROW_TARGETS = Predicates.and(EntitySelectors.NOT_SPECTATING, EntitySelectors.IS_ALIVE, Entity::canBeCollidedWith);
	 private int shooterId;
	 private EntityLivingBase shooter;
	 private int gravitystart;
	 private double velocity;
	 private double gravity;
	 private float damage;
	 private GunType type;
	 private int survivalTime;
	 private ItemStack stack;
	
	public EntityBullet(World worldIn) 
	{
		super(worldIn);
		this.setSize(0.1f, 0.1f);
	}
	
    public EntityBullet(World worldIn, EntityLivingBase shooter, GunBase gun)
    {
        this(worldIn);
		this.setSize(0.1f, 0.1f);
		this.noClip = true;
        this.shooterId = shooter.getEntityId();
        this.shooter = shooter;
        gravitystart = gun.getGravityStartTime();
        gravity = gun.getGravityModifier();
        velocity = gun.getVelocity();
        damage = gun.getDamage();
        type = gun.getGunType();
        survivalTime = (int)velocity + 3;
        stack = new ItemStack(gun);
        
        Vec3d direct = getVectorForRotation(shooter.rotationPitch, shooter.getRotationYawHead());
        
        IPlayerData data = shooter.getCapability(PlayerDataProvider.PLAYER_DATA, null);
        
        if(data.isAiming() && type != GunType.SHOTGUN)
        {
            this.motionX = direct.x * velocity;
            this.motionY = direct.y * velocity;
            this.motionZ = direct.z * velocity;
        }
        
        else
        {
            this.motionX = direct.x * velocity + (rand.nextDouble() - 0.5);
            this.motionY = direct.y * velocity + (rand.nextDouble() - 0.5);
            this.motionZ = direct.z * velocity + (rand.nextDouble() - 0.5);
        }

        this.setPosition(shooter.posX, shooter.posY + shooter.getEyeHeight(), shooter.posZ);
        
        updateHeading();
    }
    
    private void updateHeading()
    {
    	//Basic triangle math
        float f = MathHelper.sqrt(this.motionX * this.motionX + this.motionZ * this.motionZ);
        
        //We set the direction based on player rotation
        this.rotationYaw = (float) (MathHelper.atan2(this.motionX, this.motionZ) * (180D / Math.PI));
        this.rotationPitch = (float) (MathHelper.atan2(this.motionY, (double) f) * (180D / Math.PI));
        this.prevRotationYaw = this.rotationYaw;
        this.prevRotationPitch = this.rotationPitch;
    }
    
    @Override
    public void onUpdate() 
    {
        super.onUpdate();
        updateHeading();

        //Gravity
        if(this.ticksExisted > gravitystart && !world.isRemote)
        {
        	this.motionY -= gravity;
        }
        
        if(this.ticksExisted >= this.velocity * 3)
        {
        	this.setDead();
        }
        
        if(this.ticksExisted > 2 && this.ticksExisted % 2 == 0)
        {
        	world.playSound(null, posX, posY, posZ, PMCSounds.bullet_whizz, SoundCategory.PLAYERS, 0.1f, 1f);
        }
        
        if(type == GunType.SHOTGUN && !world.isRemote)
        {
        	if(this.ticksExisted % 2 == 0 && damage > 1)
        	{
        		damage -= 1;
        		
        		if(damage <= 0)
        		{
        			damage = 1;
        		}
        	}
        }
        
        Vec3d vec3d1 = new Vec3d(this.posX, this.posY, this.posZ);
        Vec3d vec3d = new Vec3d(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);
        RayTraceResult raytraceresult = this.world.rayTraceBlocks(vec3d1, vec3d, false, true, false);
        vec3d1 = new Vec3d(this.posX, this.posY, this.posZ);
        vec3d = new Vec3d(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);
        
        if (raytraceresult != null)
        {
            vec3d = new Vec3d(raytraceresult.hitVec.x, raytraceresult.hitVec.y, raytraceresult.hitVec.z);
        }

        Entity entity = this.findEntityOnPath(vec3d1, vec3d);

        if (entity != null)
        {
            raytraceresult = new RayTraceResult(entity);
        }

        if (raytraceresult != null && raytraceresult.entityHit instanceof EntityPlayer)
        {
            EntityPlayer entityplayer = (EntityPlayer)raytraceresult.entityHit;

            if (this.shooter instanceof EntityPlayer && !((EntityPlayer)this.shooter).canAttackPlayer(entityplayer))
            {
                raytraceresult = null;
            }
        }

        if (raytraceresult != null && !ForgeEventFactory.onProjectileImpact(this, raytraceresult))
        {
            this.onHit(raytraceresult);
        }

        this.posX += this.motionX;
        this.posY += this.motionY;
        this.posZ += this.motionZ;

        this.setPosition(this.posX, this.posY, this.posZ);
    }  
    
    @Nullable
    protected Entity findEntityOnPath(Vec3d start, Vec3d end)
    {
        Entity entity = null;
        List<Entity> list = this.world.getEntitiesInAABBexcluding(this, this.getEntityBoundingBox().expand(this.motionX, this.motionY, this.motionZ).grow(1.0D), ARROW_TARGETS);
        double d0 = 0.0D;

        for (int i = 0; i < list.size(); ++i)
        {
            Entity entity1 = list.get(i);

            if (entity1 != this.shooter)
            {
                AxisAlignedBB axisalignedbb = entity1.getEntityBoundingBox().grow(0.30000001192092896D);
                RayTraceResult raytraceresult = axisalignedbb.calculateIntercept(start, end);

                if (raytraceresult != null)
                {
                    double d1 = start.squareDistanceTo(raytraceresult.hitVec);

                    if (d1 < d0 || d0 == 0.0D)
                    {
                        entity = entity1;
                        d0 = d1;
                    }
                }
            }
        }

        return entity;
    }
    
    protected void onHit(RayTraceResult raytraceResultIn)
    {
        Entity entity = raytraceResultIn.entityHit;

        if(entity != null && !world.isRemote)
        {
            DamageSourceGun gunsource = new DamageSourceGun("generic", shooter, entity, stack);

            if(world.getGameRules().getBoolean("weaponKnockback"))
            {
                entity.attackEntityFrom(gunsource, damage);
            }
            else
            {
            	entity.attackEntityFrom(PMCDamageSources.WEAPON_GENERIC, damage);
            }
            
            
            entity.hurtResistantTime = 0;
            this.setDead();
        }
        
        if(raytraceResultIn.getBlockPos() != null)
        {
        	BlockPos pos = raytraceResultIn.getBlockPos();
        	IBlockState state = world.getBlockState(pos);
        	Block block = state.getBlock();
        	
        	if(state.getMaterial() == Material.GLASS)
        	{
        		if(world.getGameRules().getBoolean("weaponGriefing"))
        		{
            		world.setBlockToAir(pos);
            		world.playSound(null, posX, posY, posZ, SoundEvents.BLOCK_GLASS_BREAK, SoundCategory.BLOCKS, 3.0f, 1.0f);
        		}
        		
        		else return;
        	}
        	
        	else if(!block.isFullCube(state))
        	{
        		return;
        	}
        	
        	else if(!block.isReplaceable(world, pos))
        	{
        		this.setDead();
        	}
        }
    }   
    
    @Override
    public boolean isInRangeToRenderDist(double distance)
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
    	compound.setDouble("x", this.posX);
    	compound.setDouble("y", this.posY);
    	compound.setDouble("z", this.posZ);
    	compound.setDouble("movx", this.motionX);
    	compound.setDouble("movy", this.motionY);
    	compound.setDouble("movz", this.motionZ);
    	compound.setInteger("id", this.shooterId);
    	compound.setString("shooter", this.shooter.getName());
    }
    
    @Override
    protected void readEntityFromNBT(NBTTagCompound compound)
    {
    	compound.getDouble("x");
    	compound.getDouble("y");
    	compound.getDouble("z");
    	compound.getDouble("movx");
    	compound.getDouble("movy");
    	compound.getDouble("movz");
    	compound.getInteger("id");
    	compound.getString("shooter");
    }
    
    public EntityLivingBase getShooter()
    {
        return shooter;
    }
}

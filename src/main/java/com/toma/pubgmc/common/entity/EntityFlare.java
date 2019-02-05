package com.toma.pubgmc.common.entity;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.toma.pubgmc.init.PMCBlocks;
import com.toma.pubgmc.init.PMCSounds;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityFallingBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EntitySelectors;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class EntityFlare extends Entity //implements IEntityAdditionalSpawnData
{

	 private static final Predicate<Entity> ARROW_TARGETS = Predicates.and(EntitySelectors.NOT_SPECTATING, EntitySelectors.IS_ALIVE, Entity::canBeCollidedWith);
	 private int shooterId;
	 private int timer;
	 private double height;
	 private EntityLivingBase shooter;
	 private ItemStack item = ItemStack.EMPTY;
	
	public EntityFlare(World worldIn) 
	{
		super(worldIn);
		this.setSize(0.1f, 0.1f);
		this.setInvisible(false);
		preventEntitySpawning = true;
	}
	
    public EntityFlare(World worldIn, EntityLivingBase shooter)
    {
        this(worldIn);
        this.shooterId = shooter.getEntityId();
        this.shooter = shooter;
        this.timer = 0;
        this.height = shooter.posY;
        
        Vec3d direct = getVectorForRotation(shooter.rotationPitch, shooter.getRotationYawHead());
        this.motionX = direct.x * 2;
        this.motionY = direct.y * 2;
        this.motionZ = direct.z * 2;

        this.setPosition(shooter.posX, shooter.posY + shooter.getEyeHeight(), shooter.posZ);
        
        updateHeading();
    }
    
    private void updateHeading()
    {
        float f = MathHelper.sqrt(this.motionX * this.motionX + this.motionZ * this.motionZ);
        this.rotationYaw = (float) (MathHelper.atan2(this.motionX, this.motionZ) * (180D / Math.PI));
        this.rotationPitch = (float) (MathHelper.atan2(this.motionY, (double) f) * (180D / Math.PI));
        this.prevRotationYaw = this.rotationYaw;
        this.prevRotationPitch = this.rotationPitch;
    }
    
    @Override
    public void onEntityUpdate()
    {	
    	if(this.posY <= 0)
    	{
    		this.setDead();
    	}
    	
    	if(this.ticksExisted >= 500)
    	{
    		this.setDead();
    	}
    }
    
    @Override
    public void onUpdate() 
    {
        super.onUpdate();
        updateHeading();
        
        world.spawnParticle(EnumParticleTypes.REDSTONE, posX, posY, posZ, 0.5, 0, 0.5, 0);
        world.spawnParticle(EnumParticleTypes.REDSTONE, posX, posY, posZ, 0.5, 0, -0.5, 0);
        world.spawnParticle(EnumParticleTypes.REDSTONE, posX, posY, posZ, -0.5, 0, 0.5, 0);
        world.spawnParticle(EnumParticleTypes.REDSTONE, posX, posY, posZ, -0.5, 0, -0.5, 0);
        
        if(this.posY >= height + 100)
        {
        	timer++;
        	this.motionX = 0;
        	this.motionY = 0;
        	this.motionZ = 0;
        	world.spawnParticle(EnumParticleTypes.EXPLOSION_HUGE, posX, posY, posZ, 0, 0, 0, 0);
        	
        	if(timer >= 400 && !world.isRemote)
        	{
        		world.playSound(null, posX, posY, posZ, PMCSounds.airdrop_plane_fly_by, SoundCategory.MASTER, 15f, 1f);
        		EntityFallingBlock block = new EntityFallingBlock(world, (int)posX + 0.5, posY, (int)posZ + 0.5, PMCBlocks.BIG_AIRDROP.getDefaultState());
        		world.createExplosion(this, posX, posY, posZ, 3.0f, false);
        		world.spawnEntity(block);
        		block.fallTime = 1;
        		block.motionY = -0.1;
        		this.setDead();
        	}
        }
        
        world.spawnParticle(EnumParticleTypes.REDSTONE, posX, posY, posZ, 0, 0, 0, 0);

        this.posX += this.motionX;
        this.posY += this.motionY;
        this.posZ += this.motionZ;

        this.setPosition(this.posX, this.posY, this.posZ);
    }    
    
    @Override
    public boolean isInRangeToRenderDist(double distance)
    {
    	return true;
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
    	compound.setInteger("flare_timer", this.timer);
    }
    
    @Override
    protected void readEntityFromNBT(NBTTagCompound compound)
    {
    	posX = compound.getDouble("x");
    	posY = compound.getDouble("y");
    	posZ = compound.getDouble("z");
    	motionX = compound.getDouble("movx");
    	motionY = compound.getDouble("movy");
    	motionZ = compound.getDouble("movz");
    	timer = compound.getInteger("flare_timer");
    }
    
    @Override
    public boolean canBeCollidedWith()
    {
    	return true;
    }
}

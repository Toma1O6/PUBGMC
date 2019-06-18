package com.toma.pubgmc.common.entity;

import java.util.List;

import javax.annotation.Nullable;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.toma.pubgmc.ConfigPMC.WeaponCFG;
import com.toma.pubgmc.Pubgmc;
import com.toma.pubgmc.common.blocks.BlockLandMine;
import com.toma.pubgmc.common.capability.IPlayerData;
import com.toma.pubgmc.common.capability.IPlayerData.PlayerDataProvider;
import com.toma.pubgmc.common.items.armor.ArmorBase;
import com.toma.pubgmc.common.items.guns.GunBase;
import com.toma.pubgmc.common.items.guns.GunBase.GunType;
import com.toma.pubgmc.common.network.PacketHandler;
import com.toma.pubgmc.common.network.sp.PacketParticle;
import com.toma.pubgmc.common.tileentity.TileEntityLandMine;
import com.toma.pubgmc.init.DamageSourceGun;
import com.toma.pubgmc.init.PMCDamageSources;
import com.toma.pubgmc.init.PMCRegistry;
import com.toma.pubgmc.init.PMCRegistry.PMCItems;
import com.toma.pubgmc.init.PMCSounds;
import com.toma.pubgmc.util.PUBGMCUtil;

import io.netty.buffer.ByteBuf;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntityEvoker;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntitySnowman;
import net.minecraft.entity.monster.EntityStray;
import net.minecraft.entity.monster.EntityVindicator;
import net.minecraft.entity.monster.EntityWitch;
import net.minecraft.entity.monster.EntityWitherSkeleton;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
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
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;

public class EntityBullet extends Entity implements IEntityAdditionalSpawnData
{
	 private static final Predicate<Entity> ARROW_TARGETS = Predicates.and(EntitySelectors.NOT_SPECTATING, EntitySelectors.IS_ALIVE, Entity::canBeCollidedWith);
	 private int shooterId;
	 private EntityLivingBase shooter;
	 private WeaponCFG stats;
	 private GunType type;
	 private int survivalTime;
	 private ItemStack stack;
	 private RayTraceResult entityRaytrace;
	 private float finalDamage;
	
	public EntityBullet(World worldIn) 
	{
		super(worldIn);
		this.setSize(0.1f, 0.1f);
		preventEntitySpawning = true;
	}
	
    public EntityBullet(World worldIn, EntityLivingBase shooter, GunBase gun)
    {
        this(worldIn);
		this.setSize(0.1f, 0.1f);
        this.shooterId = shooter.getEntityId();
        this.shooter = shooter;
        
        stats = gun.getConfigurableStats();
        type = gun.getGunType();
        survivalTime = (int)stats.velocity + 3;
        stack = new ItemStack(gun);
        finalDamage = stats.damage;
        
        Vec3d direct = getVectorForRotation(shooter.rotationPitch + getPitchRotationInaccuracy(shooter), shooter.getRotationYawHead() + getYawRotationInaccuracy(shooter));
        
        IPlayerData data = shooter.getCapability(PlayerDataProvider.PLAYER_DATA, null);
        
        calculateBulletHeading(direct, shooter, data.isAiming());

        this.setPosition(shooter.posX, shooter.posY + shooter.getEyeHeight(), shooter.posZ);
        
        updateHeading();
    }
    
    @Override
    public void writeSpawnData(ByteBuf buf) {
    	buf.writeInt(shooterId);
    	WeaponCFG.writeToBuf(buf, stats);
    	buf.writeInt(type.ordinal());
    	buf.writeInt(survivalTime);
    	buf.writeFloat(finalDamage);
    }
    
    @Override
    public void readSpawnData(ByteBuf buf) {
    	shooterId = buf.readInt();
    	shooter = (EntityLivingBase)world.getEntityByID(shooterId);
    	stats = WeaponCFG.readFromBuf(buf);
    	type = GunType.values()[buf.readInt()];
    	survivalTime = buf.readInt();
    	finalDamage = buf.readFloat();
    }
    
    private float getPitchRotationInaccuracy(EntityLivingBase shooter)
    {
    	if(!shooter.isSprinting()) {
    		return 0f;
    	}
    	float f = (type.equals(GunType.PISTOL) || type.equals(GunType.SMG) || shooter.getHeldItemMainhand().getItem() == PMCItems.SAWED_OFF)
    			&& shooter.getHeldItemMainhand().getItem() != PMCItems.WIN94 ? -35f : 0f;
    	return f;
    }
    
    private float getYawRotationInaccuracy(EntityLivingBase shooter)
    {
    	if(!shooter.isSprinting()) {
    		return 0f;
    	}
    	float f = getPitchRotationInaccuracy(shooter) == -35f ? 0f : -60f;
    	return f;
    }
    
    private void calculateBulletHeading(Vec3d rotVec, EntityLivingBase shooter, boolean aim)
    {
		if(aim && type != GunType.SHOTGUN)
		{
            this.motionX = rotVec.x * stats.velocity;
            this.motionY = rotVec.y * stats.velocity;
            this.motionZ = rotVec.z * stats.velocity;
		}
		
		else
		{
			this.motionX = rotVec.x * stats.velocity + (rand.nextDouble() - 0.5);
            this.motionY = rotVec.y * stats.velocity + (rand.nextDouble() - 0.5);
            this.motionZ = rotVec.z * stats.velocity + (rand.nextDouble() - 0.5);
		}
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
    public void onUpdate() 
    {
        updateHeading();
        
        Vec3d vec3d1 = new Vec3d(this.posX, this.posY, this.posZ);
        Vec3d vec3d = new Vec3d(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);
        RayTraceResult raytraceresult = this.world.rayTraceBlocks(vec3d1, vec3d, false, true, false);
        if(stats == null) {
        	this.setDead();
        	Pubgmc.logger.error("Error occured while getting weapon stats for bullet entity with ID {}, removing the entity.", this.getEntityId());
        	return;
        }
        //Gravity
        if(this.ticksExisted > stats.gravityEffectStart && !world.isRemote)
        {
        	this.motionY -= stats.gravityModifier;
        }
        
        if(this.ticksExisted > 2 && this.ticksExisted % 2 == 0)
        {
        	world.playSound(null, posX, posY, posZ, PMCSounds.bullet_whizz, SoundCategory.PLAYERS, 0.1f, 1f);
        }
        
        if(type == GunType.SHOTGUN && !world.isRemote)
        {
        	if(this.ticksExisted % 2 == 0 && finalDamage > 1)
        	{
        		finalDamage -= 1;
        		
        		if(finalDamage <= 0)
        		{
        			finalDamage = 1;
        		}
        	}
        }
        Entity entity = this.findEntityOnPath(vec3d1, vec3d, raytraceresult);
        if(entity != null)
        {
            raytraceresult = new RayTraceResult(entity);
            this.onEntityHit(entity);
        }
        
        if(raytraceresult != null && raytraceresult.entityHit instanceof EntityPlayer)
        {
            EntityPlayer entityplayer = (EntityPlayer)raytraceresult.entityHit;

            if(this.shooter instanceof EntityPlayer && !((EntityPlayer)this.shooter).canAttackPlayer(entityplayer))
            {
                raytraceresult = null;
            }
        }
        
        if(raytraceresult != null && !ForgeEventFactory.onProjectileImpact(this, raytraceresult))
        {
            this.onHit(raytraceresult);
        }
        
        move(MoverType.SELF, motionX, motionY, motionZ);
        super.onUpdate();
    }
    
    public void onEntityHit(Entity entity) {
    	if(entity != null) {
        	if(!world.isRemote) {
            	boolean headshot = canEntityGetHeadshot(entity) && entityRaytrace.hitVec.y >= entity.getPosition().getY() + entity.getEyeHeight() - 0.15f;
            	double offset = 0f;
            	Vec3d vec = entityRaytrace.hitVec;
            	Block particleBlock = entity instanceof EntityVehicle ? Blocks.GOLD_BLOCK : Blocks.REDSTONE_BLOCK;
            	
                if(headshot) {
                	finalDamage *= 2.5f;
                	offset = entity.posY + entity.getEyeHeight();
                }
                else offset = vec.y;
                
                if(entity instanceof EntityLivingBase || entity instanceof EntityVehicle)
                	PacketHandler.sendToDimension(new PacketParticle(EnumParticleTypes.BLOCK_CRACK, 2*Math.round(finalDamage), vec.x, entityRaytrace.hitVec.y, vec.z, particleBlock), this.dimension);
                
                onEntityHit(headshot, entity);
                entity.hurtResistantTime = 0;
                
                this.setDead();
        	}
    	}
    }
    
    @Override
    public boolean canBeCollidedWith() {
    	return true;
    }
    
    @Nullable
    protected Entity findEntityOnPath(Vec3d start, Vec3d end, RayTraceResult trace)
    {
        Entity entity = null;
        List<Entity> list = this.world.getEntitiesInAABBexcluding(this, this.getEntityBoundingBox().expand(this.motionX, this.motionY, this.motionZ).grow(1.0D), ARROW_TARGETS);
        double d0 = 0.0D;

        for (int i = 0; i < list.size(); ++i)
        {
            Entity entity1 = list.get(i);

            if(entity1 != this.shooter)
            {
                AxisAlignedBB axisalignedbb = entity1.getEntityBoundingBox().grow(0.30000001192092896D);
                RayTraceResult raytraceresult = axisalignedbb.calculateIntercept(start, end);
                if(raytraceresult != null)
                {
                	if(trace != null) {
                		if(getDistanceTo(trace.hitVec) < getDistanceTo(raytraceresult.hitVec)) {
                			return entity;
                		}
                	}
                    double d1 = start.squareDistanceTo(raytraceresult.hitVec);
                    entityRaytrace = raytraceresult;
                    
                    if(d1 < d0 || d0 == 0.0D)
                    {
                        entity = entity1;
                        d0 = d1;
                    }
                }
            }
        }

        return entity;
    }
    
    private double getDistanceTo(Vec3d vec) {
    	Vec3d start = PUBGMCUtil.getPositionVec(this);
    	return PUBGMCUtil.getDistanceToBlockPos3D(new BlockPos(start), new BlockPos(vec));
    }
    
    protected void onHit(RayTraceResult raytraceResultIn)
    {
        if(raytraceResultIn.getBlockPos() != null && !world.isRemote)
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
        		Vec3d hitvec = raytraceResultIn.hitVec;
        		PacketHandler.sendToDimension(new PacketParticle(EnumParticleTypes.BLOCK_CRACK, 10, hitvec, block), this.dimension);
        		world.playSound(null, posX, posY, posZ, block.getSoundType().getBreakSound(), SoundCategory.BLOCKS, 0.5f, block.getSoundType().getPitch() * 0.8f);
        		if(block instanceof BlockLandMine)
        		{
        			((TileEntityLandMine)world.getTileEntity(pos)).explode(world, pos);
        		}
        		
        		else if(block == PMCRegistry.PMCBlocks.TARGET)
        		{
        			this.onTargetHit(pos, hitvec, shooter);
        		}
        		this.setDead();
        	}
        }
    }
    
    protected void onEntityHit(boolean isHeadshot, Entity entity)
    {
    	if(world.getGameRules().getBoolean("weaponKnockback"))
    	{
            DamageSource gunsource = new DamageSourceGun("generic", shooter, entity, stack, isHeadshot).setDamageBypassesArmor();
            
    		if(entity instanceof EntityLivingBase)
    		{
    			getCalculatedDamage((EntityLivingBase)entity, isHeadshot);
    		}
    		
    		entity.attackEntityFrom(gunsource, finalDamage);
    	}
    	
    	else
    	{
    		if(entity instanceof EntityLivingBase)
    		{
    			getCalculatedDamage((EntityLivingBase)entity, isHeadshot);
    		}
    		
    		entity.attackEntityFrom(PMCDamageSources.WEAPON_GENERIC, finalDamage);
    	}
    }
    
    /**
     * Calculates damage based on player armor and applies damage to the right part of the armor
     * Damage reduction:
     * <ul>
     * <li> 30% For level 1 armor
     * <li> 40% For level 2 armor
     * <li> 60% For level 3 armor
     * </ul>
     * @param player - the player who got hit
     * @param baseDamage - base weapon damage
     * @param isHeadShot
     */
    private void getCalculatedDamage(EntityLivingBase entity, boolean isHeadShot)
    {
    	float baseDamage = finalDamage;
    	
    	if(isHeadShot)
    	{
        	ItemStack head = entity.getItemStackFromSlot(EntityEquipmentSlot.HEAD);
        	
    		if(head.getItem() == PMCRegistry.PMCItems.ARMOR1HELMET)
    		{
    			finalDamage *= 0.7f;
    		}
    		
    		else if(head.getItem() == PMCRegistry.PMCItems.ARMOR2HELMET)
    		{
    			finalDamage *= 0.6f;
    		}
    		
    		else if(head.getItem() == PMCRegistry.PMCItems.ARMOR3HELMET)
    		{
    			finalDamage *= 0.4f;
    		}
    		
        	if(entity.getItemStackFromSlot(EntityEquipmentSlot.HEAD).getItem() instanceof ArmorBase)
        	{
        		head.damageItem(Math.round((baseDamage - (baseDamage - finalDamage)) * 0.55f), entity);
        	}
    	}
    	
    	else
    	{
        	ItemStack body = entity.getItemStackFromSlot(EntityEquipmentSlot.CHEST);
        	
    		if(body.getItem() == PMCRegistry.PMCItems.ARMOR1BODY)
    		{
    			finalDamage *= 0.7f;
    		}
    		
    		else if(body.getItem() == PMCRegistry.PMCItems.ARMOR2BODY)
    		{
    			finalDamage *= 0.6f;
    		}
    		
    		else if(body.getItem() == PMCRegistry.PMCItems.ARMOR3BODY)
    		{
    			finalDamage *= 0.5f;
    		}
    		
        	if(entity.getItemStackFromSlot(EntityEquipmentSlot.CHEST).getItem() instanceof ArmorBase)
        	{
        		body.damageItem(Math.round((baseDamage - (baseDamage - finalDamage)) * 0.8f), entity);
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
    	compound.setInteger("lifespan", this.ticksExisted);
    	compound.setFloat("bullet_damage", this.finalDamage);
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
    	ticksExisted = compound.getInteger("lifespan");
    	finalDamage = compound.getFloat("bullet_damage");
    }
    
    private static boolean canEntityGetHeadshot(Entity e)
    {
    	return e instanceof EntityZombie || e instanceof EntitySkeleton || e instanceof EntityCreeper || e instanceof EntityWitch || e instanceof EntityPigZombie || e instanceof EntityEnderman || e instanceof EntityWitherSkeleton || e instanceof EntityPlayer || e instanceof EntityVillager || e instanceof EntityEvoker || e instanceof EntityStray || e instanceof EntityVindicator || e instanceof EntityIronGolem || e instanceof EntitySnowman;
    }
    
    public EntityLivingBase getShooter()
    {
        return shooter;
    }
    
    public static void onTargetHit(BlockPos pos, Vec3d hit, EntityLivingBase shooter)
    {
    	if(shooter.world.getGameRules().getBoolean("notifyTargetHits"))
    	{
    		Vec3d vec = new Vec3d(Math.abs(hit.x - (int)hit.x), Math.abs(hit.y - (int)hit.y), Math.abs(hit.z - (int)hit.z));
    		
    		// Hitting the center
    		if(((vec.x > 0.4 && vec.x < 0.6) || (vec.z > 0.4 && vec.z < 0.6)) && (vec.y > 0.4 && vec.y < 0.6))
    		{
    			shooter.sendMessage(new TextComponentString(TextFormatting.YELLOW + "HIT!"));
    		}
    	}
    }
}

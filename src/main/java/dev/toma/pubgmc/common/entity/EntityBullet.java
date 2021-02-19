package dev.toma.pubgmc.common.entity;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import dev.toma.pubgmc.common.blocks.BlockLandMine;
import dev.toma.pubgmc.common.blocks.BlockWindow;
import dev.toma.pubgmc.common.blocks.IBulletReaction;
import dev.toma.pubgmc.common.capability.IPlayerData;
import dev.toma.pubgmc.common.entity.controllable.EntityVehicle;
import dev.toma.pubgmc.common.items.armor.ArmorBase;
import dev.toma.pubgmc.common.items.guns.GunBase;
import dev.toma.pubgmc.common.tileentity.TileEntityLandMine;
import dev.toma.pubgmc.config.common.CFGWeapon;
import dev.toma.pubgmc.init.DamageSourceGun;
import dev.toma.pubgmc.init.PMCRegistry;
import dev.toma.pubgmc.init.PMCRegistry.PMCItems;
import dev.toma.pubgmc.init.PMCSounds;
import dev.toma.pubgmc.network.PacketHandler;
import dev.toma.pubgmc.network.sp.PacketParticle;
import dev.toma.pubgmc.util.PUBGMCUtil;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.MoverType;
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
import net.minecraft.util.math.*;
import net.minecraft.world.World;
import net.minecraftforge.event.ForgeEventFactory;

import javax.annotation.Nullable;
import java.util.List;

public class EntityBullet extends Entity {
    private static final Predicate<Entity> ARROW_TARGETS = Predicates.and(EntitySelectors.NOT_SPECTATING, EntitySelectors.IS_ALIVE, Entity::canBeCollidedWith);
    private int shooterId;
    private EntityLivingBase shooter;
    private int gravitystart;
    private double velocity;
    private double gravity;
    private float damage;
    private GunBase.GunType type;
    private int survivalTime;
    private ItemStack stack;
    private RayTraceResult entityRaytrace;

    public EntityBullet(World worldIn) {
        super(worldIn);
        this.setSize(0.1f, 0.1f);
        preventEntitySpawning = true;
    }

    public EntityBullet(World worldIn, EntityLivingBase shooter, GunBase gun) {
        this(worldIn);
        this.setSize(0.1f, 0.1f);
        this.noClip = true;
        this.shooterId = shooter.getEntityId();
        this.shooter = shooter;
        CFGWeapon cfg = gun.getConfigurableStats();
        gravitystart = cfg.gravityEffectStart.get();
        gravity = cfg.gravityModifier.getAsFloat();
        velocity = cfg.velocity.getAsFloat();
        damage = cfg.damage.getAsFloat();
        type = gun.getGunType();
        survivalTime = (int) velocity + 3;
        stack = new ItemStack(gun);

        Vec3d direct = getVectorForRotation(shooter.rotationPitch + getPitchRotationInaccuracy(shooter), shooter.getRotationYawHead() + getYawRotationInaccuracy(shooter));
        if(shooter instanceof EntityPlayer) {
            IPlayerData data = shooter.getCapability(IPlayerData.PlayerDataProvider.PLAYER_DATA, null);
            calculateBulletHeading(direct, (EntityPlayer) shooter, data.isAiming());
            this.setPosition(shooter.posX, data.isProning() ? shooter.posY + 0.5f : shooter.posY + shooter.getEyeHeight(), shooter.posZ);
        } else {
            this.calculateBulletHeading(direct, shooter, 2 + this.world.getDifficulty().ordinal());
            this.setPosition(shooter.posX, shooter.posY + shooter.getEyeHeight(), shooter.posZ);
        }

        updateHeading();
    }

    public EntityLivingBase getShooter() {
        return shooter;
    }

    public void onBulletCollided(RayTraceResult rayTraceResult) {
        if(rayTraceResult == null) {
            return;
        }
        Entity entity = rayTraceResult.entityHit;
        if(entity != null && !world.isRemote) {
            boolean isHeadshot = this.canEntityGetHeadshot(entity) && entityRaytrace.hitVec.y >= entity.getPosition().getY() + entity.getEyeHeight() - 0.15f;
            Vec3d vec = rayTraceResult.hitVec;
            Block block = entity instanceof EntityVehicle ? Blocks.GOLD_BLOCK : Blocks.REDSTONE_BLOCK;
            if(isHeadshot) {
                damage *= 2.5;
            }
            if(entity instanceof EntityLivingBase || entity instanceof EntityVehicle) {
                PacketHandler.sendToDimension(new PacketParticle(EnumParticleTypes.BLOCK_CRACK, 2*Math.round(damage), vec.x, entityRaytrace.hitVec.y, vec.z, block, PacketParticle.ParticleAction.HIT_EFFECT, 0), this.dimension);
            }
            this.onEntityHit(isHeadshot, entity);
            entity.hurtResistantTime = 0;
            this.setDead();
        } else if(rayTraceResult.getBlockPos() != null && !world.isRemote) {
            BlockPos pos = rayTraceResult.getBlockPos();
            IBlockState state = world.getBlockState(pos);
            Block block = state.getBlock();
            if(block instanceof IBulletReaction && ((IBulletReaction) block).canReceiveFeedBack(world, rayTraceResult.getBlockPos(), state)) {
                ((IBulletReaction) block).onHit(this, rayTraceResult.hitVec);
            }
            boolean griefingFlag = world.getGameRules().getBoolean("weaponGriefing");
            boolean canBePenetrated = false;
            if(block instanceof BlockWindow) {
                canBePenetrated = true;
                ((BlockWindow)block).breakWindow(state, pos, world);
            } else if(state.getMaterial() == Material.GLASS) {
                if(griefingFlag) {
                    world.setBlockToAir(pos);
                    world.playSound(null, posX, posY, posZ, SoundEvents.BLOCK_GLASS_BREAK, SoundCategory.BLOCKS, 3.0F, 1.0F);
                }
                canBePenetrated = griefingFlag;
            } else if(!block.isReplaceable(world, pos)) {
                Vec3d vec = rayTraceResult.hitVec;
                PacketHandler.sendToDimension(new PacketParticle(EnumParticleTypes.BLOCK_CRACK, 10, vec, pos, PacketParticle.ParticleAction.SPREAD_RANDOMLY, 0), this.dimension);
                world.playSound(null, posX, posY, posZ, block.getSoundType().getBreakSound(), SoundCategory.BLOCKS, 0.5F, block.getSoundType().getPitch() * 0.8F);
                if(block instanceof BlockLandMine) {
                    ((TileEntityLandMine) world.getTileEntity(pos)).explode(world, pos);
                }
                this.setDead();
            }

            if(canBePenetrated && damage > 0) {
                Vec3d startVec = PUBGMCUtil.getPositionVec(this);
                Vec3d nextPos = PUBGMCUtil.getMotionVec(this);
                RayTraceResult trace = world.rayTraceBlocks(nextPos, startVec, false, true, false);
                Entity e = this.findEntityOnPath(nextPos, startVec, trace);
                if(e != null) {
                    trace = new RayTraceResult(e);
                }
                if(trace != null && rayTraceResult.entityHit instanceof EntityPlayer) {
                    EntityPlayer player = (EntityPlayer) rayTraceResult.entityHit;
                    if(shooter instanceof EntityPlayer && !((EntityPlayer)shooter).canAttackPlayer(player)) {
                        trace = null;
                    }
                }
                if(trace != null) {
                    // allows shooting through multiple objects
                    this.onBulletCollided(trace);
                }
            }
        }
    }

    @Override
    public void onUpdate() {
        super.onUpdate();
        updateHeading();

        Vec3d vec3d1 = new Vec3d(this.posX, this.posY, this.posZ);
        Vec3d vec3d = new Vec3d(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);
        RayTraceResult raytraceresult = this.world.rayTraceBlocks(vec3d1, vec3d, false, true, false);
        //Gravity
        if (this.ticksExisted > gravitystart && !world.isRemote) {
            this.motionY -= gravity;
        }

        if (this.ticksExisted >= this.velocity * 3) {
            this.setDead();
        }

        if (this.ticksExisted > 2 && this.ticksExisted % 2 == 0) {
            world.playSound(null, posX, posY, posZ, PMCSounds.bullet_whizz, SoundCategory.PLAYERS, 0.1f, 1f);
        }

        if (type == GunBase.GunType.SHOTGUN && !world.isRemote) {
            if (this.ticksExisted % 2 == 0 && damage > 1) {
                damage -= 1;

                if (damage <= 0) {
                    damage = 1;
                }
            }
        }
        Entity entity = this.findEntityOnPath(vec3d1, vec3d, raytraceresult);
        if (entity != null) {
            raytraceresult = new RayTraceResult(entity);
        }
        if (raytraceresult != null && raytraceresult.entityHit instanceof EntityPlayer) {
            EntityPlayer entityplayer = (EntityPlayer) raytraceresult.entityHit;
            if (this.shooter instanceof EntityPlayer && !((EntityPlayer) this.shooter).canAttackPlayer(entityplayer)) {
                raytraceresult = null;
            }
        }
        if (raytraceresult != null && !ForgeEventFactory.onProjectileImpact(this, raytraceresult)) {
            this.onBulletCollided(raytraceresult);
        }

        move(MoverType.SELF, motionX, motionY, motionZ);
    }

    @Override
    public boolean isInRangeToRenderDist(double distance) {
        return false;
    }

    @Nullable
    protected Entity findEntityOnPath(Vec3d start, Vec3d end, RayTraceResult trace) {
        Entity entity = null;
        List<Entity> list = this.world.getEntitiesInAABBexcluding(this, this.getEntityBoundingBox().expand(this.motionX, this.motionY, this.motionZ).grow(1.0D), ARROW_TARGETS);
        double d0 = 0.0D;
        for (int i = 0; i < list.size(); ++i) {
            Entity entity1 = list.get(i);
            if (entity1 != this.shooter) {
                AxisAlignedBB axisalignedbb = entity1.getEntityBoundingBox();
                RayTraceResult raytraceresult = axisalignedbb.calculateIntercept(start, end);
                if (raytraceresult != null) {
                    if (trace != null) {
                        if (this.getDistanceTo(trace.hitVec) < this.getDistanceTo(raytraceresult.hitVec)) {
                            return entity;
                        }
                    }
                    double d1 = start.squareDistanceTo(raytraceresult.hitVec);
                    entityRaytrace = raytraceresult;

                    if (d1 < d0 || d0 == 0.0D) {
                        entity = entity1;
                        d0 = d1;
                    }
                }
            }
        }
        return entity;
    }

    protected void onEntityHit(boolean isHeadshot, Entity entity) {
        DamageSource gunsource = new DamageSourceGun("generic", shooter, entity, stack, isHeadshot).setDamageBypassesArmor();

        if (entity instanceof EntityLivingBase) {
            getCalculatedDamage((EntityLivingBase) entity, isHeadshot);
        }

        entity.attackEntityFrom(gunsource, damage);
    }

    @Override
    protected void entityInit() {
    }

    @Override
    protected void writeEntityToNBT(NBTTagCompound compound) {
        compound.setDouble("x", this.posX);
        compound.setDouble("y", this.posY);
        compound.setDouble("z", this.posZ);
        compound.setDouble("movx", this.motionX);
        compound.setDouble("movy", this.motionY);
        compound.setDouble("movz", this.motionZ);
        compound.setInteger("lifespan", this.ticksExisted);
        compound.setFloat("bullet_damage", this.damage);
        compound.setDouble("bullet_velocity", this.velocity);
    }

    @Override
    protected void readEntityFromNBT(NBTTagCompound compound) {
        posX = compound.getDouble("x");
        posY = compound.getDouble("y");
        posZ = compound.getDouble("z");
        motionX = compound.getDouble("movx");
        motionY = compound.getDouble("movy");
        motionZ = compound.getDouble("movz");
        ticksExisted = compound.getInteger("lifespan");
        damage = compound.getFloat("bullet_damage");
        velocity = compound.getDouble("bullet_velocity");
    }

    private boolean canEntityGetHeadshot(Entity e) {
        double ratio = e.height / e.width;
        return ratio > 1.0F;
    }

    private float getPitchRotationInaccuracy(EntityLivingBase shooter) {
        if (!shooter.isSprinting()) {
            return 0f;
        }
        return (type.equals(GunBase.GunType.PISTOL) || type.equals(GunBase.GunType.SMG) || shooter.getHeldItemMainhand().getItem() == PMCItems.SAWED_OFF)
                && shooter.getHeldItemMainhand().getItem() != PMCItems.WIN94 ? -35f : 0f;
    }

    private float getYawRotationInaccuracy(EntityLivingBase shooter) {
        if (!shooter.isSprinting()) {
            return 0f;
        }
        return getPitchRotationInaccuracy(shooter) == -35f ? 0f : -60f;
    }

    private void calculateBulletHeading(Vec3d rotVec, EntityPlayer shooter, boolean aim) {
        if (aim && type != GunBase.GunType.SHOTGUN) {
            this.motionX = rotVec.x * velocity;
            this.motionY = rotVec.y * velocity;
            this.motionZ = rotVec.z * velocity;
        } else {
            this.motionX = rotVec.x * velocity + (rand.nextDouble() - 0.5);
            this.motionY = rotVec.y * velocity + (rand.nextDouble() - 0.5);
            this.motionZ = rotVec.z * velocity + (rand.nextDouble() - 0.5);
        }
    }

    private void calculateBulletHeading(Vec3d rotVec, EntityLivingBase shooter, int accuracy) {
        if(accuracy == 0) accuracy = 1;
        this.motionX = rotVec.x * velocity + rand.nextDouble() / accuracy - rand.nextDouble() / accuracy;
        this.motionY = rotVec.y * velocity + rand.nextDouble() / accuracy - rand.nextDouble() / accuracy;
        this.motionZ = rotVec.z * velocity + rand.nextDouble() / accuracy - rand.nextDouble() / accuracy;
    }

    private void updateHeading() {
        float f = MathHelper.sqrt(this.motionX * this.motionX + this.motionZ * this.motionZ);
        this.rotationYaw = (float) (MathHelper.atan2(this.motionX, this.motionZ) * (180D / Math.PI));
        this.rotationPitch = (float) (MathHelper.atan2(this.motionY, (double) f) * (180D / Math.PI));
        this.prevRotationYaw = this.rotationYaw;
        this.prevRotationPitch = this.rotationPitch;
    }

    private double getDistanceTo(Vec3d vec) {
        Vec3d start = PUBGMCUtil.getPositionVec(this);
        return PUBGMCUtil.getDistanceToBlockPos3D(new BlockPos(start), new BlockPos(vec));
    }

    /**
     * Calculates damage based on player armor and applies damage to the right part of the armor
     * Damage reduction:
     * <ul>
     * <li> 30% For level 1 armor
     * <li> 40% For level 2 armor
     * <li> 60% For level 3 armor
     * </ul>
     */
    private void getCalculatedDamage(EntityLivingBase entity, boolean isHeadShot) {
        float baseDamage = damage;

        if (isHeadShot) {
            ItemStack head = entity.getItemStackFromSlot(EntityEquipmentSlot.HEAD);

            if (head.getItem() == PMCRegistry.PMCItems.ARMOR1HELMET) {
                damage *= 0.7f;
            } else if (head.getItem() == PMCRegistry.PMCItems.ARMOR2HELMET) {
                damage *= 0.6f;
            } else if (head.getItem() == PMCRegistry.PMCItems.ARMOR3HELMET) {
                damage *= 0.4f;
            }

            if (entity.getItemStackFromSlot(EntityEquipmentSlot.HEAD).getItem() instanceof ArmorBase) {
                head.damageItem(Math.round((baseDamage - (baseDamage - damage)) * 0.55f), entity);
            }
        } else {
            ItemStack body = entity.getItemStackFromSlot(EntityEquipmentSlot.CHEST);

            if (body.getItem() == PMCRegistry.PMCItems.ARMOR1BODY) {
                damage *= 0.7f;
            } else if (body.getItem() == PMCRegistry.PMCItems.ARMOR2BODY) {
                damage *= 0.6f;
            } else if (body.getItem() == PMCRegistry.PMCItems.ARMOR3BODY) {
                damage *= 0.5f;
            }

            if (entity.getItemStackFromSlot(EntityEquipmentSlot.CHEST).getItem() instanceof ArmorBase) {
                body.damageItem(Math.round((baseDamage - (baseDamage - damage)) * 0.8f), entity);
            }
        }
    }

    public float getDamage() {
        return damage;
    }
}
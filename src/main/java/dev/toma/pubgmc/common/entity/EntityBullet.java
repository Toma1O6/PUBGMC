package dev.toma.pubgmc.common.entity;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import dev.toma.pubgmc.api.block.IBulletReaction;
import dev.toma.pubgmc.api.capability.IPlayerData;
import dev.toma.pubgmc.api.capability.PlayerDataProvider;
import dev.toma.pubgmc.api.entity.CustomProjectileBoundingBoxProvider;
import dev.toma.pubgmc.api.game.mutator.ArmorMutator;
import dev.toma.pubgmc.api.game.mutator.GameMutatorHelper;
import dev.toma.pubgmc.api.game.mutator.GameMutators;
import dev.toma.pubgmc.api.item.BulletproofArmor;
import dev.toma.pubgmc.common.blocks.BlockWindow;
import dev.toma.pubgmc.common.items.guns.GunBase;
import dev.toma.pubgmc.common.items.guns.WeaponStats;
import dev.toma.pubgmc.config.ConfigPMC;
import dev.toma.pubgmc.init.DamageSourceGun;
import dev.toma.pubgmc.init.PMCItems;
import dev.toma.pubgmc.init.PMCSounds;
import dev.toma.pubgmc.network.PacketHandler;
import dev.toma.pubgmc.network.s2c.S2C_PacketMakeParticles;
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

    public static final Function<Entity, AxisAlignedBB> BOUNDING_BOX_PROVIDER = entity -> entity instanceof
            CustomProjectileBoundingBoxProvider ?
            ((CustomProjectileBoundingBoxProvider) entity).getBoundingBoxForProjectiles()
            : entity.getEntityBoundingBox();
    private static final Predicate<Entity> TARGET_FILTER = Predicates.and(EntitySelectors.NOT_SPECTATING, EntitySelectors.IS_ALIVE, Entity::canBeCollidedWith);
    private EntityLivingBase shooter;
    private int gravitystart;
    private double velocity;
    private double gravity;
    private float damage;
    private int damagedropstart;
    private float damagedrop;
    private float mindamage;
    private GunBase.GunType type;
    private ItemStack stack;
    private RayTraceResult entityRaytrace;

    public EntityBullet(World worldIn) {
        super(worldIn);
        this.setSize(0.1f, 0.1f);
        preventEntitySpawning = true;
    }

    public EntityBullet(World worldIn, EntityLivingBase shooter, GunBase gun) {
        this(worldIn);
        this.setSize(0.01f, 0.01f);
        this.noClip = true;
        this.shooter = shooter;
        WeaponStats stats = gun.getWeaponStats();
        gravitystart = stats.getGravityEffectStart();
        gravity = stats.getGravityModifier();
        velocity = stats.getVelocity();
        damage = stats.getDamage();
        damagedropstart = stats.getDamagedropEffectStart();
        damagedrop = stats.getDamagedropModifier();
        mindamage = stats.getMinDamage();
        type = gun.getGunType();
        stack = new ItemStack(gun);

        Vec3d direct = getVectorForRotation(shooter.rotationPitch + getPitchRotationInaccuracy(shooter), shooter.getRotationYawHead() + getYawRotationInaccuracy(shooter));
        if (shooter instanceof EntityPlayer) {
            IPlayerData data = shooter.getCapability(PlayerDataProvider.PLAYER_DATA, null);
            calculateBulletHeading(direct, shooter.onGround && data.getAimInfo().isFullyAds());
            this.setPosition(shooter.posX, data.isProne() ? shooter.posY + 0.5f : shooter.posY + shooter.getEyeHeight(), shooter.posZ);
        } else {
            this.calculateBulletHeading(direct, 2.5F - this.world.getDifficulty().ordinal() * 0.5F);
            this.setPosition(shooter.posX, shooter.posY + shooter.getEyeHeight(), shooter.posZ);
        }

        updateHeading();
    }

    public EntityLivingBase getShooter() {
        return shooter;
    }

    public void onBulletCollided(RayTraceResult rayTraceResult) {
        if (rayTraceResult == null) {
            return;
        }
        Entity entity = rayTraceResult.entityHit;
        if (world.isRemote) {
            return;
        }
        // server only
        if (entity != null) { // entity hit
            this.onEntityHit(entity, rayTraceResult.hitVec);
            entity.hurtResistantTime = 0;
            handleBulletReaction(rayTraceResult);
            this.setDead();
        } else { // block hit
            BlockPos pos = rayTraceResult.getBlockPos();
            IBlockState state = world.getBlockState(pos);
            Block block = state.getBlock();
            handleBulletReaction(rayTraceResult);

            boolean griefingFlag = ConfigPMC.world().weaponGriefing.get();
            boolean canBePenetrated = false;
            if (block instanceof BlockWindow) {
                canBePenetrated = true;
                ((BlockWindow) block).breakWindow(state, pos, world);
            } else if (state.getMaterial() == Material.GLASS) {
                if (griefingFlag) {
                    world.setBlockToAir(pos);
                    world.playSound(null, posX, posY, posZ, SoundEvents.BLOCK_GLASS_BREAK, SoundCategory.BLOCKS, 3.0F, 1.0F);
                }
                canBePenetrated = griefingFlag;
            } else if (!block.isReplaceable(world, pos)) {
                Vec3d vec = rayTraceResult.hitVec;
                int hitParticles = (int) damage;
                PacketHandler.sendToDimension(new S2C_PacketMakeParticles(EnumParticleTypes.BLOCK_CRACK, hitParticles, vec, pos, S2C_PacketMakeParticles.ParticleAction.SPREAD_RANDOMLY, 0), this.dimension);
                world.playSound(null, posX, posY, posZ, block.getSoundType().getBreakSound(), SoundCategory.BLOCKS, 0.5F, block.getSoundType().getPitch() * 0.8F);
                this.setDead();
            }

            if (canBePenetrated && damage > 0) {
                Vec3d startVec = PUBGMCUtil.getPositionVec(this);
                Vec3d nextPos = PUBGMCUtil.getMotionVec(this);
                RayTraceResult trace = world.rayTraceBlocks(nextPos, startVec, false, true, false);
                Entity e = this.findEntityOnPath(nextPos, startVec, trace);
                if (e != null) {
                    trace = new RayTraceResult(e);
                }
                if (trace != null && rayTraceResult.entityHit instanceof EntityPlayer) {
                    EntityPlayer player = (EntityPlayer) rayTraceResult.entityHit;
                    if (shooter instanceof EntityPlayer && !((EntityPlayer) shooter).canAttackPlayer(player)) {
                        trace = null;
                    }
                }
                if (trace != null) {
                    // allows shooting through multiple objects
                    this.onBulletCollided(trace);
                }
            }
        }
    }

    public void handleBulletReaction(RayTraceResult rayTraceResult) {
        if (world.isRemote) {
            return;
        }
        Entity entity = rayTraceResult.entityHit;
        if (entity != null) { // entity hit
            if (entity instanceof IBulletReaction) {
                IBulletReaction reaction = (IBulletReaction) entity;
                if (reaction.allowBulletInteraction(world, null, entity)) {
                    reaction.onHit(this, rayTraceResult.hitVec, null, entity);
                }
            }
        } else { // block hit
            BlockPos pos = rayTraceResult.getBlockPos();
            IBlockState state = world.getBlockState(pos);
            Block block = state.getBlock();
            if (block instanceof IBulletReaction) {
                IBulletReaction reaction = (IBulletReaction) block;
                if (reaction.allowBulletInteraction(world, state, null)) {
                    reaction.onHit(this, rayTraceResult.hitVec, state, null);
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
        //Max fly time
        if (this.ticksExisted >= ConfigPMC.world().bulletTime.get()) {
            this.setDead();
        }
        //Gravity
        if (this.ticksExisted > gravitystart && !world.isRemote) {
            this.motionY -= gravity;
        }
        //Sound
        if (this.ticksExisted > 2 && this.ticksExisted % 2 == 0) {
            float v = this.getVelocity();
            if (this.ticksExisted * v > 100) {
                float volume = this.getDamage() / 40f;
                world.playSound(null, posX, posY, posZ, PMCSounds.bullet_whizz, SoundCategory.PLAYERS, volume, 1f);
            }
        }
        //Damagedrop
        if (this.ticksExisted > damagedropstart && !world.isRemote) {
            damage -= damagedrop;
            if (damage <= mindamage) {
                damage = mindamage;
            }
            if (damage <= 0) {
                this.setDead();
            }
        }

        Entity entity = this.findEntityOnPath(vec3d1, vec3d, raytraceresult);
        if (entity != null) {
            raytraceresult = new RayTraceResult(entity);
        }
        if (raytraceresult != null) {
            if (raytraceresult.entityHit instanceof EntityPlayer) {
                EntityPlayer entityplayer = (EntityPlayer) raytraceresult.entityHit;
                if (this.shooter instanceof EntityPlayer && !((EntityPlayer) this.shooter).canAttackPlayer(entityplayer)) {
                    raytraceresult = null;
                }
            }
            if (!ForgeEventFactory.onProjectileImpact(this, raytraceresult)) {
                this.onBulletCollided(raytraceresult);
            }
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
        List<Entity> list = this.world.getEntitiesInAABBexcluding(this, this.getEntityBoundingBox().expand(this.motionX, this.motionY, this.motionZ).grow(1.0D), TARGET_FILTER);
        double d0 = 0.0D;
        for (int i = 0; i < list.size(); ++i) {
            Entity entity1 = list.get(i);
            if (entity1 == this.shooter) {
                continue;
            }
            AxisAlignedBB axisalignedbb = BOUNDING_BOX_PROVIDER.apply(entity1);
            if (axisalignedbb == null) // this check is needed
                continue;
            RayTraceResult raytraceresult = axisalignedbb.calculateIntercept(start, end);
            if (raytraceresult == null) {
                continue;
            }
            if (trace != null && this.getDistanceTo(trace.hitVec) < this.getDistanceTo(raytraceresult.hitVec)) {
                return entity;
            }
            double d1 = start.squareDistanceTo(raytraceresult.hitVec);
            entityRaytrace = raytraceresult;

            if (d1 < d0 || d0 == 0.0D) {
                entity = entity1;
                d0 = d1;
            }
        }
        return entity;
    }

    protected void onEntityHit(Entity entity, Vec3d vec) {
        boolean isHeadshot = this.canEntityGetHeadshot(entity) && isEntityGetHeadshot(entity);
        if (isHeadshot) {
            this.damage *= getHeadshotMultipler();
        }
        float unProtectedDamage = this.damage;
        DamageSource gunsource = new DamageSourceGun(shooter, this, stack, isHeadshot);
        if (ConfigPMC.common.world.damages.bulletPenetration.get()) {
            gunsource.setDamageBypassesArmor();
        }
        boolean isLivingEntity = entity instanceof EntityLivingBase;

        if (isLivingEntity) {
            float protectedDamage = getProtectedDamage((EntityLivingBase) entity, isHeadshot);
            if (protectedDamage != -1) {
                this.damage = protectedDamage;
            }
        }
        if (entity.attackEntityFrom(gunsource, this.damage) && isLivingEntity) {
            damageArmor(isHeadshot, unProtectedDamage, (EntityLivingBase) entity);
        }

        Block block = isLivingEntity ? Blocks.REDSTONE_BLOCK : Blocks.IRON_BLOCK;
        PacketHandler.sendToDimension(new S2C_PacketMakeParticles(EnumParticleTypes.BLOCK_CRACK, 2 * Math.round(damage),
                vec.x, entityRaytrace.hitVec.y, vec.z,
                block, S2C_PacketMakeParticles.ParticleAction.HIT_EFFECT, 0), this.dimension);
    }

    @Override
    protected void entityInit() {
    }

    @Override
    protected void writeEntityToNBT(NBTTagCompound compound) {
        compound.setFloat("bullet_damage", this.damage);
        compound.setDouble("bullet_velocity", this.velocity);
    }

    @Override
    protected void readEntityFromNBT(NBTTagCompound compound) {
        damage = compound.getFloat("bullet_damage");
        velocity = compound.getDouble("bullet_velocity");
    }

    private boolean canEntityGetHeadshot(Entity e) {
        if (!(e instanceof EntityLivingBase)) { // non-living entity can't get headshot
            return false;
        }
        double ratio = e.height / e.width;
        return ratio > 1.0F;
    }

    private boolean isEntityGetHeadshot(Entity entity) {
        return entityRaytrace.hitVec.y >= entity.getPosition().getY() + entity.getEyeHeight() - 0.15f;
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

    private void calculateBulletHeading(Vec3d rotVec, boolean aim) {
        float v = this.getVelocity();
        if (aim && type != GunBase.GunType.SHOTGUN) {
            this.motionX = rotVec.x * v;
            this.motionY = rotVec.y * v;
            this.motionZ = rotVec.z * v;
        } else {
            this.motionX = rotVec.x * v + (rand.nextDouble() - 0.5);
            this.motionY = rotVec.y * v + (rand.nextDouble() - 0.5);
            this.motionZ = rotVec.z * v + (rand.nextDouble() - 0.5);
        }
    }

    private void calculateBulletHeading(Vec3d rotVec, float inaccuracy) {
        if (inaccuracy == 0)
            inaccuracy = 1;
        float v = this.getVelocity();
        this.motionX = rotVec.x * v + (rand.nextDouble() - rand.nextDouble()) * inaccuracy;
        this.motionY = rotVec.y * v + (rand.nextDouble() - rand.nextDouble()) * inaccuracy;
        this.motionZ = rotVec.z * v + (rand.nextDouble() - rand.nextDouble()) * inaccuracy;
    }

    private void updateHeading() {
        float f = MathHelper.sqrt(this.motionX * this.motionX + this.motionZ * this.motionZ);
        this.rotationYaw = (float) (MathHelper.atan2(this.motionX, this.motionZ) * (180D / Math.PI));
        this.rotationPitch = (float) (MathHelper.atan2(this.motionY, f) * (180D / Math.PI));
        this.prevRotationYaw = this.rotationYaw;
        this.prevRotationPitch = this.rotationPitch;
    }

    private double getDistanceTo(Vec3d vec) {
        Vec3d start = PUBGMCUtil.getPositionVec(this);
        return PUBGMCUtil.getDistanceToBlockPos3D(new BlockPos(start), new BlockPos(vec));
    }

    private float getProtectedDamage(EntityLivingBase entity, boolean isHeadShot) {
        ItemStack stack;
        BulletproofArmor.DamageArea area;
        if (isHeadShot) {
            stack = entity.getItemStackFromSlot(EntityEquipmentSlot.HEAD);
            area = BulletproofArmor.DamageArea.HEAD;
        } else {
            stack = entity.getItemStackFromSlot(EntityEquipmentSlot.CHEST);
            area = BulletproofArmor.DamageArea.OTHER;
        }

        if (stack.isEmpty())
            return -1;
        if (!(stack.getItem() instanceof BulletproofArmor)) {
            return -1;
        }

        if (!isHeadShot && stack.getItemDamage() == stack.getMaxDamage()) {
            return damage * 0.8f;
        } else {
            BulletproofArmor armor = (BulletproofArmor) stack.getItem();
            ArmorMutator mutator = GameMutatorHelper.getMutator(world, GameMutators.ARMOR).orElse(ArmorMutator.DEFAULT);
            return damage * mutator.getDamageMultiplier(armor, area, stack, entity);
        }
    }

    private void damageArmor(boolean headshot, float baseDamage, EntityLivingBase target) {
        ItemStack stack = target.getItemStackFromSlot(headshot ? EntityEquipmentSlot.HEAD : EntityEquipmentSlot.CHEST);
        if (stack.isEmpty())
            return;
        if (!(stack.getItem() instanceof BulletproofArmor)) {
            return;
        }

        BulletproofArmor armor = (BulletproofArmor) stack.getItem();
        BulletproofArmor.DamageArea area = headshot ? BulletproofArmor.DamageArea.HEAD : BulletproofArmor.DamageArea.OTHER;
        ArmorMutator mutator = GameMutatorHelper.getMutator(world, GameMutators.ARMOR).orElse(ArmorMutator.DEFAULT);
        float protectionMultiplier = 1.0f - mutator.getDamageMultiplier(armor, area, stack, target);
        int damageAmount;
        if (headshot) {
            damageAmount = Math.round(baseDamage * 5 * protectionMultiplier);
        } else {
            damageAmount = Math.min(Math.round(baseDamage * 5 * protectionMultiplier), stack.getMaxDamage() - stack.getItemDamage());
        }
        stack.damageItem(damageAmount, target);
    }

    public float getDamage() {
        return damage;
    }

    public float getVelocity() {
        return (float)velocity;
    }

    public float getHeadshotMultipler() {
        return type.getHeadshotDamageMultiplier();
    }
}
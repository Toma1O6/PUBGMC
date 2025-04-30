package dev.toma.pubgmc.common.entity.throwables;

import com.google.common.base.Predicates;
import dev.toma.pubgmc.DevUtil;
import dev.toma.pubgmc.Pubgmc;
import dev.toma.pubgmc.api.entity.IBombReaction;
import dev.toma.pubgmc.config.ConfigPMC;
import dev.toma.pubgmc.network.PacketHandler;
import dev.toma.pubgmc.network.s2c.S2C_PacketMakeParticles;
import dev.toma.pubgmc.util.PUBGMCUtil;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.*;
import net.minecraft.util.math.*;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class EntityC4 extends EntityThrowableExplodeable {
    @Nullable
    private BlockPos stickedBlockPos;
    @Nullable
    private UUID stickedEntityUUID;
    private Vec3d stickOffset = Vec3d.ZERO;
    public static final int initFuse = 320; // 16 seconds
    private static final List<Integer> SOUND_TICKS = calculateSoundTicks(33);
    private int soundIndex = 0;
    private static final List<BlockPos> sphereOffsets;
    private static final double[] blockBreakStrengths;
    private static final int BOMB_RADIUS = 25;
    private static final double DAMAGE_RADIUS = 25.0;
    private static final double MAX_DAMAGE = 1024.0;
    private static final double KEY_RADIUS = 15.0;
    private static final double DAMAGE_AT_KEY_RADIUS = 20.0; // a control point, not equals to real damage
    private static final EnumFacing FRONT = EnumFacing.DOWN;
    private static final float size = 0.2F; // large size will reduce the probability to stick
    private static final float explodeInteractRadius = 5F;

    static {
        sphereOffsets = calculateSphereOffsets(BOMB_RADIUS);
        blockBreakStrengths = calculateDamageValues(DAMAGE_RADIUS, KEY_RADIUS, MAX_DAMAGE, DAMAGE_AT_KEY_RADIUS, sphereOffsets);
    }

    private static List<BlockPos> calculateSphereOffsets(int radius) {
        List<BlockPos> offsets = new ArrayList<>();
        for (int x = -radius; x <= radius; x++) {
            for (int y = -radius; y <= radius; y++) {
                for (int z = -radius; z <= radius; z++) {
                    if (x * x + y * y + z * z <= radius * radius) {
                        offsets.add(new BlockPos(x, y, z));
                    }
                }
            }
        }
        return offsets;
    }

    private static double[] calculateDamageValues(double damageRadius, double damageKeyRadius,double maxDamage, double damageAtKeyRadius, List<BlockPos> offsets) {
        double[] damages = new double[offsets.size()];
        for (int i = 0; i < offsets.size(); i++) {
            double distance = MathHelper.sqrt(offsets.get(i).getX() * offsets.get(i).getX() +
                    offsets.get(i).getY() * offsets.get(i).getY() +
                    offsets.get(i).getZ() * offsets.get(i).getZ());
            damages[i] = calculateBombDamage(distance, damageRadius, damageKeyRadius, maxDamage, damageAtKeyRadius);
        }
        return damages;
    }

    private static double calculateBombDamage(double distance, double damageRadius, double damageKeyRadius,double maxDamage, double damageAtKeyRadius) {
        if (distance > damageRadius) {
            return 0;
        }
        double maxDamageRadius = 3F;
        if (distance < maxDamageRadius) {
            return maxDamage;
        }
        double x1 = -4;
        double x2 = 4;
        // y = 2^x
        if (distance > damageKeyRadius) { // x -> [-4, 0), y -> [0.0625, 1)
            double x = ((distance - damageKeyRadius) / (damageRadius - damageKeyRadius)) * (x1);
            return damageAtKeyRadius * Math.pow(2, x); // no need to smooth to 0 damage
        } else { // x -> [0, 4], y -> [1, 16]
            double x = (1 - (distance - maxDamageRadius) / (damageKeyRadius - maxDamageRadius)) * x2;
            return damageAtKeyRadius + (maxDamage - damageAtKeyRadius) * ((Math.pow(2, x) - 1) / (Math.pow(2,x2) - 1));
        }
    }

    @Override
    public void onExplode() {
        handleExplodeEffect();
        if (!world.isRemote) {
            boolean canBreakBlocks = ConfigPMC.world().bombs.grenadeGriefing.get();
            BlockPos centerPos = new BlockPos(MathHelper.floor(this.posX), MathHelper.floor(this.posY + 1), MathHelper.floor(this.posZ));
            Explosion explosion = new Explosion(world, this, this.posX, this.posY + 1, this.posZ, 0.0F, false, canBreakBlocks);

            if (canBreakBlocks) {
                for (int i = 0; i < sphereOffsets.size(); i++) {
                    BlockPos offset = sphereOffsets.get(i);
                    BlockPos currentPos = centerPos.add(offset);
                    double strength = blockBreakStrengths[i];

                    if (strength > 0) {
                        IBlockState state = world.getBlockState(currentPos);
                        float resistance = state.getBlock().getExplosionResistance(world, currentPos, this, explosion);
                        if (strength > resistance) {
                            world.setBlockToAir(currentPos);
                        }
                    }
                }
            }

            AxisAlignedBB aabb = new AxisAlignedBB(
                    this.posX - BOMB_RADIUS, this.posY - BOMB_RADIUS, this.posZ - BOMB_RADIUS,
                    this.posX + BOMB_RADIUS, this.posY + BOMB_RADIUS, this.posZ + BOMB_RADIUS
            );
            List<Entity> affectedEntities = world.getEntitiesWithinAABB(Entity.class, aabb);

            for (Entity entity : affectedEntities) {
                double distSq = entity.getDistanceSq(this.posX, this.posY, this.posZ);
                if (distSq <= DAMAGE_RADIUS * DAMAGE_RADIUS) {
                    double distance = MathHelper.sqrt(distSq);
                    double damageToEntity = calculateBombDamage(distance, DAMAGE_RADIUS, KEY_RADIUS, MAX_DAMAGE, DAMAGE_AT_KEY_RADIUS);
                    EntityLivingBase throwerEntity = (EntityLivingBase) getThrower();
                    DamageSource damageSource = throwerEntity != null ? DamageSource.causeExplosionDamage(throwerEntity) : DamageSource.causeExplosionDamage(explosion);
                    entity.attackEntityFrom(damageSource, (float) damageToEntity);
                }
            }
            handleExplodeInteraction();
        }
        this.setDead();
    }

    public void handleExplodeInteraction() {
        AxisAlignedBB aabb = new AxisAlignedBB(this.posX - explodeInteractRadius, this.posY - explodeInteractRadius, this.posZ - explodeInteractRadius,
                this.posX + explodeInteractRadius, this.posY + explodeInteractRadius, this.posZ + explodeInteractRadius);
        List<Entity> entities = this.world.getEntitiesWithinAABB(Entity.class, aabb, Predicates.and(EntitySelectors.IS_ALIVE, EntitySelectors.NOT_SPECTATING));
        for (Entity e : entities) {
            if (e instanceof IBombReaction) {
                IBombReaction reaction = (IBombReaction) e;
                if (reaction.allowBombInteraction(world, null, e)) {
                    double vecX = e.posX - this.posX;
                    double vecY = e.posY - this.posY;
                    double vecZ = e.posZ - this.posZ;
                    double distance = Math.sqrt(vecX*vecX + vecY*vecY + vecZ*vecZ);
                    if (distance < explodeInteractRadius) {
                        // Provides moderate vertical thrust, high horizontal thrust
                        double bombStrength = Math.min(1.2 - distance / explodeInteractRadius, 1);
                        double xz = Math.sqrt(vecX * vecX + vecZ * vecZ);
                        // Expected: xz=2.8, y=1.5, sqrt(2.8)=1.67
                        vecX = (2.8 * vecX / xz) * bombStrength;
                        vecY = 1.5 * bombStrength * ((1.5 + vecY) > 0 ? 1 : -1);
                        vecZ = (2.8 * vecZ / xz) * bombStrength;
                        double multiplier = 1.3F; // already checked the maximum effect
                        reaction.onBomb(this, new Vec3d(vecX, vecY, vecZ).scale(multiplier), null, e);
                    }
                }
            }
        }
    }

    private static List<Integer> calculateSoundTicks(int soundCount) {
        List<Integer> fuseTicks = new ArrayList<>();
        if (soundCount <= 0) return fuseTicks;
        double x1 = -2;
        double y1 = 0.25;
        double x2 = 2;
        double y2 = 4;
        double diffX = x2 - x1;
        double diffY = y2 - y1;
        int preTick = -1;

        for (int i = soundCount; i > 0; i--) {
            double x = x1 + diffX * ((double)i/soundCount);
            double y = Math.pow(2, x);
            int curTick = (int) Math.round(initFuse * (y/y2));
            if (curTick != preTick) {
                fuseTicks.add(curTick);
                preTick = curTick;
            }
        }
        return fuseTicks;
    }

    public EntityC4(World world) {
        super(world);
        this.setSize(size, size);
    }

    public EntityC4(World world, EntityLivingBase thrower, EnumEntityThrowState state) {
        super(world, thrower, state);
        this.setSize(size, size);
    }

    public EntityC4(World world, EntityLivingBase thrower, EnumEntityThrowState state, int time) {
        super(world, thrower, state, initFuse); // ensure force refresh
        this.setSize(size, size);
    }

    // TODO improve
    public void adjustStickRotation() {
        // Stick to block
        if (this.stickedBlockPos != null) {
            EnumFacing facing = null;
            Vec3d hitVec = this.getPositionVector().subtract(new Vec3d(this.stickedBlockPos).addVector(0.5, 0.5, 0.5));
            double minDistanceSq = Double.MAX_VALUE;
            for (EnumFacing face : EnumFacing.values()) {
                Vec3d faceVec = new Vec3d(face.getDirectionVec());
                Vec3d diff = hitVec.subtract(faceVec.scale(hitVec.dotProduct(faceVec)));
                double distanceSq = diff.lengthSquared();
                if (distanceSq < minDistanceSq) {
                    minDistanceSq = distanceSq;
                    facing = face;
                }
            }

            if (facing != null) {
                EnumFacing targetFront = facing.getOpposite();
                DevUtil.calculateRotationToBlockFace(this.stickedBlockPos, this, targetFront);
            }
            return;
        }
        // Stick to entity
        if (this.stickedEntityUUID != null) {
            Entity stickedEntity = null;
            for (Entity entity : this.world.loadedEntityList) {
                if (entity.getUniqueID().equals(this.stickedEntityUUID)) {
                    stickedEntity = entity;
                    break;
                }
            }

            if (stickedEntity != null) {
                DevUtil.calculateRotationToEntity(this, stickedEntity, this, FRONT);
            }
        }
    }

    public void handleStickEffect(@Nullable RayTraceResult blockRayTrace) {
        world.playSound(null, this.posX, this.posY, this.posZ, SoundEvents.BLOCK_SLIME_HIT, SoundCategory.BLOCKS, 1.0F, 1.0F);
        if (blockRayTrace != null && blockRayTrace.hitVec != null && blockRayTrace.getBlockPos() != null) {
            PacketHandler.sendToDimension(new S2C_PacketMakeParticles(EnumParticleTypes.SLIME, 20, blockRayTrace.hitVec, blockRayTrace.getBlockPos(), S2C_PacketMakeParticles.ParticleAction.SPREAD_RANDOMLY, 0), this.dimension);
        } else {
            Pubgmc.logger.warn("handleStickEffect received a null or incomplete RayTraceResult.");
            PacketHandler.sendToDimension(new S2C_PacketMakeParticles(EnumParticleTypes.SLIME, 20, new Vec3d(this.posX, this.posY, this.posZ), new BlockPos(this.posX, Math.round(this.posY), this.posZ), S2C_PacketMakeParticles.ParticleAction.SPREAD_RANDOMLY, 0), this.dimension);
        }
        adjustStickRotation();
    }

    public void handleSoundTick() {
        if (soundIndex < SOUND_TICKS.size() && fuse <= SOUND_TICKS.get(soundIndex)) {
            if (soundIndex % 2 == 0) {
                this.handleIntervalEffect1();
            } else {
                this.handleIntervalEffect2();
            }
            this.soundIndex++;
        } else if (soundIndex >= SOUND_TICKS.size()) {
            if (world.isRemote) { // clinet only
                world.spawnParticle(EnumParticleTypes.EXPLOSION_LARGE,true, this.posX, this.posY + 1, this.posZ,
                        (rand.nextDouble() - 0.5) * 100, (rand.nextDouble() - 0.5) * 100, (rand.nextDouble() - 0.5) * 100);
            }
            soundIndex++;
        }
    }

    public boolean stickCheckTick() {
        if (this.stickedBlockPos != null) { // Already has sticked block
            IBlockState state = this.world.getBlockState(this.stickedBlockPos);
            if (state.getBlock().isAir(state, this.world, this.stickedBlockPos)) {
                this.stickedBlockPos = null;
                this.setNoGravity(false);
                this.motionY -= 0.039D;
            } else {
                this.setPosition(this.stickedBlockPos.getX() + 0.5 + this.stickOffset.x + size/2,
                        this.stickedBlockPos.getY() + 0.5 + this.stickOffset.y + size/2,
                        this.stickedBlockPos.getZ() + 0.5 + this.stickOffset.z + size/2);
                this.setPosition(this.stickedBlockPos.getX() + 0.5 + this.stickOffset.x,
                        this.stickedBlockPos.getY() + 0.5 + this.stickOffset.y,
                        this.stickedBlockPos.getZ() + 0.5 + this.stickOffset.z);
                this.motionX = 0;
                this.motionY = 0;
                this.motionZ = 0;
                return true;
            }
        }
        if (this.stickedEntityUUID != null) { // Already has sticked entity
            Entity stickedEntity = null;
            for (Entity entity : this.world.loadedEntityList) {
                if (entity.getUniqueID().equals(this.stickedEntityUUID)) {
                    stickedEntity = entity;
                    break;
                }
            }
            if (stickedEntity != null && !stickedEntity.isDead) {
                Vec3d entityPos = stickedEntity.getPositionVector();
                this.setPosition(entityPos.x + this.stickOffset.x,
                        entityPos.y + this.stickOffset.y,
                        entityPos.z + this.stickOffset.z);
                this.setPosition(entityPos.x + this.stickOffset.x, entityPos.y + this.stickOffset.y, entityPos.z + this.stickOffset.z);
                this.motionX = 0;
                this.motionY = 0;
                this.motionZ = 0;
                return true;
            } else {
                this.stickedEntityUUID = null;
                this.stickOffset = Vec3d.ZERO;
                this.setNoGravity(false);
                this.motionY -= 0.039D;
            }
        }
        return false;
    }

    public boolean attemptStick(@Nullable RayTraceResult blockRayTrace) {
        // Stick to block
        if (blockRayTrace != null && this.stickedBlockPos == null && blockRayTrace.typeOfHit == RayTraceResult.Type.BLOCK) {
            BlockPos hitPos = blockRayTrace.getBlockPos();
            boolean isSolidBlock = world.getBlockState(hitPos).isFullCube();
            if (isSolidBlock) {
                this.stickedBlockPos = hitPos;
                this.stickedEntityUUID = null;
                this.motionX = 0;
                this.motionY = 0;
                this.motionZ = 0;
                this.setNoGravity(true);

                EnumFacing facing = blockRayTrace.sideHit;
                switch (facing) {
                    case DOWN:
                        this.stickOffset = new Vec3d(0, -0.5, 0);
                        break;
                    case UP:
                        this.stickOffset = new Vec3d(0, 0.5, 0);
                        break;
                    case NORTH:
                        this.stickOffset = new Vec3d(0, 0, -0.5);
                        break;
                    case SOUTH:
                        this.stickOffset = new Vec3d(0, 0, 0.5);
                        break;
                    case WEST:
                        this.stickOffset = new Vec3d(-0.5, 0, 0);
                        break;
                    case EAST:
                        this.stickOffset = new Vec3d(0.5, 0, 0);
                        break;
                }

                return true;
            }
        }
        // Stick to entity
        if (this.stickedBlockPos == null && this.stickedEntityUUID == null) {
            List<Entity> collidingEntities = this.world.getEntitiesInAABBexcluding(this, this.getEntityBoundingBox().expand(0.1, 0.1, 0.1), entity -> entity != this && !(entity instanceof EntityPlayer));
            if (!collidingEntities.isEmpty()) {
                Entity hitEntity = collidingEntities.get(0);
                this.stickedEntityUUID = hitEntity.getUniqueID();
                this.stickedBlockPos = null;
                this.stickOffset = this.getPositionVector().subtract(hitEntity.getPositionVector());
                this.motionX = 0;
                this.motionY = 0;
                this.motionZ = 0;
                this.setNoGravity(true);
                return true;
            }
        }
        return false;
    }

    public void handleNormalMove(Vec3d from, Vec3d to, @Nullable RayTraceResult blockRayTrace) {
        double prevMotionX = motionX;
        double prevMotionY = motionY;
        double prevMotionZ = motionZ;
        if (blockRayTrace != null && blockRayTrace.typeOfHit == RayTraceResult.Type.BLOCK) {
            this.onCollide(from, to, blockRayTrace);
        }
        this.move(MoverType.SELF, motionX, motionY, motionZ);
        if (motionX != prevMotionX) {
            this.motionX = -BOUNCE_MODIFIER * prevMotionX;
            this.onGrenadeBounce(BounceAxis.X);
        }
        if (motionY != prevMotionY) {
            this.motionY = -BOUNCE_MODIFIER * prevMotionY;
            this.onGrenadeBounce(BounceAxis.Y);
        }
        if (motionZ != prevMotionZ) {
            this.motionZ = -BOUNCE_MODIFIER * prevMotionZ;
            this.onGrenadeBounce(BounceAxis.Z);
        }
        if (!this.hasNoGravity()) {
            this.motionY -= 0.039D;
        }
        this.motionX *= AIR_DRAG_MODIFIER;
        this.motionY *= AIR_DRAG_MODIFIER;
        this.motionZ *= AIR_DRAG_MODIFIER;
    }

    public void handleRotation() {
        this.lastRotation = this.rotation;
        if (world.isRemote && !this.onGround // client only
                && this.motionX != 0 && this.motionY != 0 && this.motionZ != 0) {
            this.rotation += 45F;
        }
    }

    public void handleExplodeEffect() {
        world.playSound(null, this.posX, this.posY, this.posZ, SoundEvents.ENTITY_GENERIC_EXPLODE, SoundCategory.BLOCKS, 3.0F, 1.0F);
        if (world.isRemote) { // client only
            for (double xOff : new double[]{5, -5}) {
                for (double zOff: new double[]{5, -5}) {
                    world.spawnParticle(EnumParticleTypes.EXPLOSION_HUGE,true, this.posX + xOff, this.posY + 1, this.posZ + zOff, xOff * 2, 0.0D, zOff * 2);
                }
            }
            for (double yOff : new double[]{5, 0, -5}) {
                world.spawnParticle(EnumParticleTypes.EXPLOSION_HUGE,true, this.posX, this.posY + 1 + yOff, this.posZ, 0, 0, 0);
            }
        }
    }

    public void handleIntervalEffect1() {
        world.playSound(null, this.posX, this.posY, this.posZ, SoundEvents.BLOCK_LEVER_CLICK, SoundCategory.BLOCKS, 3.0F, 1.0F);
        world.playSound(null, this.posX, this.posY, this.posZ, SoundEvents.BLOCK_PISTON_EXTEND, SoundCategory.BLOCKS, 1F, 1.0F);
        if (world.isRemote) { // client only
            for (double xOff : new double[]{0.1, -0.1}) {
                for (double zOff : new double[]{0.1, -0.1}) {
                    world.spawnParticle(EnumParticleTypes.REDSTONE, true, this.posX + xOff, this.posY + 0.3, this.posZ + zOff, xOff * 100, 20, zOff * 100, 0);
                }
            }
        }
    }

    public void handleIntervalEffect2() {
        world.playSound(null, this.posX, this.posY, this.posZ, SoundEvents.BLOCK_LEVER_CLICK, SoundCategory.BLOCKS, 3.0F, 1.0F);
        world.playSound(null, this.posX, this.posY, this.posZ, SoundEvents.BLOCK_PISTON_CONTRACT, SoundCategory.BLOCKS, 1F, 1.0F);
        if (world.isRemote) { // client only
            for (double xOff : new double[]{0.1, -0.1}) {
                for (double zOff : new double[]{0.1, -0.1}) {
                    world.spawnParticle(EnumParticleTypes.REDSTONE, true, this.posX + xOff, this.posY + 0.3, this.posZ + zOff, xOff * 100, 20, zOff * 100, 0);
                }
            }
        }
    }

    @Override
    public void onUpdate() {
        --this.fuse;
        if (fuse < 0) {
            this.onExplode();
        }
        this.handleSoundTick();

        this.prevPosX = this.posX;
        this.prevPosY = this.posY;
        this.prevPosZ = this.posZ;

        if (!world.isRemote) {
            boolean stuck = stickCheckTick();
            if (stuck) return;

            Vec3d from = PUBGMCUtil.getPositionVec(this);
            Vec3d to = PUBGMCUtil.getMotionVec(this);
            RayTraceResult blockRayTrace = this.world.rayTraceBlocks(from, to, false, true, false);

            if (attemptStick(blockRayTrace)) {
                handleStickEffect(blockRayTrace);
                return;
            }
            handleNormalMove(from, to, blockRayTrace);
        }
        handleRotation();
        // this.onThrowableTick(); // not for C4
    }

    @Override
    protected void setInitialMotion(EnumEntityThrowState state, EntityLivingBase thrower) {
        if (thrower == null) {
            return;
        }
        int i = state.ordinal();
        float sprintModifier = 1.2F;
        float jumpModifier = 1.25F;
        float modifier = (i == EnumEntityThrowState.LONG.ordinal()) ? 0.72F : (i == EnumEntityThrowState.SHORT.ordinal() ? 0.28F : 0);
        if (thrower.isSprinting()) modifier *= sprintModifier;
        if (!thrower.onGround) modifier *= jumpModifier;
        Vec3d viewVec = thrower.getLookVec();
        this.motionX = viewVec.x * modifier;
        this.motionY = viewVec.y * modifier / sprintModifier;
        this.motionZ = viewVec.z * modifier;
    }
}
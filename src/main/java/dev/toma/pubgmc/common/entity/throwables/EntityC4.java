package dev.toma.pubgmc.common.entity.throwables;

import dev.toma.pubgmc.DevUtil;
import dev.toma.pubgmc.config.ConfigPMC;
import dev.toma.pubgmc.util.PUBGMCUtil;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.function.Predicate;

public class EntityC4 extends EntityThrowableExplodeable {
    protected UUID thrower;
    @Nullable
    private BlockPos stickedBlockPos;
    @Nullable
    private Entity stickedEntity;
    @Nullable
    private UUID stickedEntityUUID;
    private Vec3d stickOffset = Vec3d.ZERO;
    private static final int initFuse = 320; // 16 seconds
    private static final List<Integer> SOUND_TICKS = calculateSoundTicks(33);
    private int soundIndex = 0;

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
    }

    public EntityC4(World world, EntityLivingBase thrower, EnumEntityThrowState state) {
        super(world, thrower, state);
    }

    public EntityC4(World world, EntityLivingBase thrower, EnumEntityThrowState state, int time) {
        super(world, thrower, state, initFuse); // force refresh to ensure 16s
    }

    public void playStickSound() {
        world.playSound(null, this.posX, this.posY, this.posZ, SoundEvents.BLOCK_SLIME_HIT, SoundCategory.BLOCKS, 1.0F, 1.0F);
    }

    public void playIntervalSound() {
        world.playSound(null, this.posX, this.posY, this.posZ, SoundEvents.BLOCK_LEVER_CLICK, SoundCategory.BLOCKS, 3.0F, 1.0F);
        world.playSound(null, this.posX, this.posY, this.posZ, SoundEvents.BLOCK_PISTON_EXTEND, SoundCategory.BLOCKS, 0.5F, 1.0F);
    }

    public void playIntervalSound2() {
        world.playSound(null, this.posX, this.posY, this.posZ, SoundEvents.BLOCK_LEVER_CLICK, SoundCategory.BLOCKS, 3.0F, 1.0F);
        world.playSound(null, this.posX, this.posY, this.posZ, SoundEvents.BLOCK_PISTON_CONTRACT, SoundCategory.BLOCKS, 0.5F, 1.0F);
    }

    public void handleSoundTick() {
        if (!world.isRemote && soundIndex < SOUND_TICKS.size() && fuse <= SOUND_TICKS.get(soundIndex)) {
            if (soundIndex % 2 == 0) {
                this.playIntervalSound();
            } else {
                this.playIntervalSound2();
            }
            this.soundIndex++;
        }
    }

    @Override
    public void onExplode() {
        if (!world.isRemote) {
            boolean canBreakBlocks = ConfigPMC.world().grenadeGriefing.get();
            this.setPosition(this.posX, this.posY + 1, this.posZ);
            world.createExplosion(getThrower(), this.posX, this.posY, this.posZ, 50.0F, canBreakBlocks);
        }
        this.setDead();
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

        if (this.stickedBlockPos != null) { // Already has sticked block
            IBlockState state = this.world.getBlockState(this.stickedBlockPos);
            if (state.getBlock().isAir(state, this.world, this.stickedBlockPos)) {
                this.stickedBlockPos = null;
                this.setNoGravity(false);
                this.motionY -= 0.039D;
            } else {
                this.setPosition(this.stickedBlockPos.getX() + 0.5 + this.stickOffset.x,
                        this.stickedBlockPos.getY() + 0.5 + this.stickOffset.y,
                        this.stickedBlockPos.getZ() + 0.5 + this.stickOffset.z);
                this.motionX = 0;
                this.motionY = 0;
                this.motionZ = 0;
                return;
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
                this.setPosition(entityPos.x + this.stickOffset.x, entityPos.y + this.stickOffset.y, entityPos.z + this.stickOffset.z);
                this.motionX = 0;
                this.motionY = 0;
                this.motionZ = 0;
                return;
            } else {
                this.stickedEntityUUID = null;
                this.stickOffset = Vec3d.ZERO;
                this.setNoGravity(false);
                this.motionY -= 0.039D;
            }
        }
        if (!world.isRemote) {
            Vec3d from = PUBGMCUtil.getPositionVec(this);
            Vec3d to = PUBGMCUtil.getMotionVec(this);
            RayTraceResult blockRayTrace = this.world.rayTraceBlocks(from, to, false, true, false);

            // Stick to block
            if (blockRayTrace != null && this.stickedBlockPos == null && blockRayTrace.typeOfHit == RayTraceResult.Type.BLOCK) {
                IBlockState hitState = world.getBlockState(blockRayTrace.getBlockPos());
                boolean isSolidBlock = hitState.isFullCube();
                if (isSolidBlock) {
                    this.stickedBlockPos = blockRayTrace.getBlockPos();
                    this.stickedEntityUUID = null;
                    this.stickOffset = blockRayTrace.hitVec.subtract(new Vec3d(this.stickedBlockPos.getX() + 0.5, this.stickedBlockPos.getY() + 0.5, this.stickedBlockPos.getZ() + 0.5));
                    this.motionX = 0;
                    this.motionY = 0;
                    this.motionZ = 0;
                    this.setNoGravity(true);
                    playStickSound();
                    return;
                }
            }
            // Stick to entity
            if (this.stickedBlockPos == null && this.stickedEntityUUID == null) {
                List<Entity> collidingEntities = this.world.getEntitiesInAABBexcluding(this, this.getEntityBoundingBox().expand(0.8, 0.8, 0.8), entity -> entity != this && !(entity instanceof EntityPlayer));
                if (!collidingEntities.isEmpty()) {
                    Entity hitEntity = collidingEntities.get(0);
                    this.stickedEntityUUID = hitEntity.getUniqueID();
                    this.stickedBlockPos = null;
                    this.stickOffset = this.getPositionVector().subtract(hitEntity.getPositionVector());
                    this.motionX = 0;
                    this.motionY = 0;
                    this.motionZ = 0;
                    this.setNoGravity(true);
                    playStickSound();
                    return;
                }
            }

            // Normal move
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

        this.lastRotation = this.rotation;
        if (world.isRemote && !this.onGround
                && this.motionX != 0 && this.motionY != 0 && this.motionZ != 0) {
            this.rotation += 45F;
        }

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
package dev.toma.pubgmc.common.entity.throwables;

import dev.toma.pubgmc.common.blocks.BlockWindow;
import dev.toma.pubgmc.config.ConfigPMC;
import dev.toma.pubgmc.network.PacketHandler;
import dev.toma.pubgmc.network.client.PacketSyncEntity;
import dev.toma.pubgmc.util.PUBGMCUtil;
import io.netty.buffer.ByteBuf;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.MoverType;
import net.minecraft.init.SoundEvents;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;

public abstract class EntityThrowableExplodeable extends Entity implements IEntityAdditionalSpawnData {

    public static final float AIR_DRAG_MODIFIER = 0.98F;
    public static final float GROUND_DRAG_MODIFIER = 0.7F;
    public static final float BOUNCE_MODIFIER = 0.8F;

    public int fuse;

    public float rotation;
    public float lastRotation;

    public int timesBounced = 0;
    public boolean isFrozen = false;

    public EntityThrowableExplodeable(World world) {
        this(world, null, EnumEntityThrowState.FORCED);
    }

    public EntityThrowableExplodeable(World world, EntityLivingBase thrower, EnumEntityThrowState state) {
        this(world, thrower, state, state == EnumEntityThrowState.FORCED ? 1 : 100);
    }

    public EntityThrowableExplodeable(World world, EntityLivingBase thrower, EnumEntityThrowState state, int time) {
        super(world);
        this.setSize(0.2F, 0.2F);
        if(thrower != null) this.setPosition(thrower.posX, thrower.posY + thrower.getEyeHeight(), thrower.posZ);
        this.fuse = time;

        this.setInitialMotion(state, thrower);
    }

    public abstract void onExplode();

    @Override
    public void onUpdate() {
        --this.fuse;
        if(fuse < 0) {
            this.onExplode();
        }
        this.prevPosX = this.posX;
        this.prevPosY = this.posY;
        this.prevPosZ = this.posZ;
        double prevMotionX = motionX;
        double prevMotionY = motionY;
        double prevMotionZ = motionZ;
        if(!world.isRemote) {
            Vec3d from = PUBGMCUtil.getPositionVec(this);
            Vec3d to = PUBGMCUtil.getMotionVec(this);
            RayTraceResult rayTraceResult = this.world.rayTraceBlocks(from, to, false, true, false);
            if(rayTraceResult != null && rayTraceResult.typeOfHit == RayTraceResult.Type.BLOCK) {
                this.onCollide(from, to, rayTraceResult);
            }
        }
        this.move(MoverType.SELF, motionX, motionY, motionZ);
        if(motionX != prevMotionX) {
            this.motionX = -BOUNCE_MODIFIER * prevMotionX;
            this.onGrenadeBounce(BounceAxis.X);
        }
        if(motionY != prevMotionY) {
            this.motionY = -BOUNCE_MODIFIER * prevMotionY;
            this.onGrenadeBounce(BounceAxis.Y);
        }
        if(motionZ != prevMotionZ) {
            this.motionZ = -BOUNCE_MODIFIER * prevMotionZ;
            this.onGrenadeBounce(BounceAxis.Z);
        }
        if(!this.hasNoGravity()) {
            this.motionY -= 0.039D;
        }
        this.motionX *= AIR_DRAG_MODIFIER;
        this.motionY *= AIR_DRAG_MODIFIER;
        this.motionZ *= AIR_DRAG_MODIFIER;
        if(this.onGround) {
            this.motionX *= GROUND_DRAG_MODIFIER;
            this.motionY *= GROUND_DRAG_MODIFIER;
            this.motionZ *= GROUND_DRAG_MODIFIER;
        }
        this.lastRotation = this.rotation;
        if(world.isRemote && !this.onGround) {
            if(this.motionX != 0 && this.motionY != 0 && this.motionZ != 0) {
                this.rotation += 45F;
            }
        }

        this.onThrowableTick();
    }

    public final void onCollide(Vec3d from, Vec3d to, RayTraceResult result) {
        BlockPos pos = result.getBlockPos();
        IBlockState state = this.world.getBlockState(pos);
        boolean flag = ConfigPMC.world().weaponGriefing.get();
        if(flag) {
            boolean hasBrokenGlass = false;
            if(state.getBlock() instanceof BlockWindow) {
                BlockWindow window = (BlockWindow) state.getBlock();
                boolean isBroken = state.getValue(BlockWindow.BROKEN);
                if(!isBroken) {
                    window.breakWindow(state, pos, this.world);
                    hasBrokenGlass = true;
                }
            } else if(state.getMaterial() == Material.GLASS) {
                world.destroyBlock(pos, false);
                hasBrokenGlass = true;
            }
            if(hasBrokenGlass) {
                RayTraceResult rayTraceResult = this.world.rayTraceBlocks(from, to, false, true, false);
                if(rayTraceResult != null && rayTraceResult.typeOfHit == RayTraceResult.Type.BLOCK) {
                    this.onCollide(from, to, rayTraceResult);
                }
            }
        }
    }

    @Override
    public boolean isInRangeToRenderDist(double distance) {
        return true;
    }

    public void onThrowableTick() {

    }

    public void bounce() {

    }

    public boolean canBounce() {
        return true;
    }

    public final boolean isFirstBounce() {
        return timesBounced == 1;
    }

    @Override
    public void writeSpawnData(ByteBuf buf) {
        ByteBufUtils.writeTag(buf, this.writeToNBT(new NBTTagCompound()));
    }

    @Override
    public void readSpawnData(ByteBuf buf) {
        this.readFromNBT(ByteBufUtils.readTag(buf));
    }

    @Override
    protected void entityInit() {
    }

    @Override
    protected void writeEntityToNBT(NBTTagCompound compound) {
        compound.setInteger("fuse", this.fuse);
        compound.setBoolean("frozen", this.isFrozen);
        compound.setInteger("timesBounced", this.timesBounced);
    }

    @Override
    protected void readEntityFromNBT(NBTTagCompound compound) {
        this.fuse = compound.getInteger("fuse");
        this.isFrozen = compound.getBoolean("frozen");
        this.timesBounced = compound.getInteger("timesBounced");
    }

    protected void onEntityFrozen() {
        PacketHandler.sendToAllTracking(new PacketSyncEntity(this), this);
    }

    private void setInitialMotion(EnumEntityThrowState state, EntityLivingBase thrower) {
        if (thrower == null) {
            return;
        }
        int i = state.ordinal();
        float sprintModifier = 1.25F;
        float modifier = i == 2 ? 0 : i == 1 ? 0.6F : 1.4F;
        if(thrower.isSprinting()) modifier *= sprintModifier;
        Vec3d viewVec = thrower.getLookVec();
        this.motionX = viewVec.x * modifier;
        this.motionY = viewVec.y * modifier / sprintModifier;
        this.motionZ = viewVec.z * modifier;
    }

    private void freezeEntity() {
        this.motionX = 0;
        this.motionY = 0;
        this.motionZ = 0;
        // to make sure it won't get accidentally called twice
        if(isFrozen) return;
        this.isFrozen = true;
        this.onEntityFrozen();
    }

    private void onGrenadeBounce(BounceAxis axis) {
        if(Math.sqrt(motionX*motionX+motionY*motionY+motionZ*motionZ) >= 0.2) {
            this.world.playSound(null, this.posX, this.posY, this.posZ, SoundEvents.BLOCK_ANVIL_BREAK, SoundCategory.MASTER, 1.0F, 1.8F);
        }
        this.timesBounced++;
        if(!canBounce()) {
            this.freezeEntity();
        }
        this.bounce();
    }

    public enum EnumEntityThrowState {
        LONG,
        SHORT,
        FORCED
    }

    public enum BounceAxis {
        X, Y, Z
    }
}

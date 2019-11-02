package com.toma.pubgmc.common.entity.throwables;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.MoverType;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;

public abstract class EntityThrowableExplodeable extends Entity implements IEntityAdditionalSpawnData {

    public static final float AIR_DRAG_MODIFIER = 0.98F;
    public static final float GROUND_DRAG_MODIFIER = 0.7F;
    public static final float BOUNCE_MODIFIER = 0.8F;

    public EnumEntityThrowState state;
    public Entity thrower;
    public int fuse;

    public float rotation;
    public float lastRotation;

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
        this.state = state;
        this.thrower = thrower;

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
        if(this.onGround || this.isInWater() || this.isInLava()) {
            this.motionX *= GROUND_DRAG_MODIFIER;
            this.motionY *= GROUND_DRAG_MODIFIER;
            this.motionZ *= GROUND_DRAG_MODIFIER;
        }
        if(world.isRemote && !this.onGround) {
            if(this.motionX != 0 && this.motionY != 0 && this.motionZ != 0) {
                int rotationAmount = 90;
                for(int i = 0; i < rotationAmount; i++) {
                    this.lastRotation = this.rotation;
                    this.rotation += 0.5F;
                }
            }
        }
    }

    public void onGrenadeBounce(BounceAxis axis) {

    }

    @Override
    public void writeSpawnData(ByteBuf buf) {
        buf.writeInt(fuse);
        buf.writeInt(thrower.getEntityId());
    }

    @Override
    public void readSpawnData(ByteBuf buf) {
        this.fuse = buf.readInt();
        this.thrower = this.world.getEntityByID(buf.readInt());
    }

    @Override
    protected void entityInit() {
    }

    @Override
    protected void writeEntityToNBT(NBTTagCompound compound) {
    }

    @Override
    protected void readEntityFromNBT(NBTTagCompound compound) {
    }

    private void setInitialMotion(EnumEntityThrowState state, EntityLivingBase thrower) {
        // TODO reduce sprint bonus
        if (thrower == null) {
            return;
        }
        int i = state.ordinal();
        float modifier = i == 2 ? 0 : i == 1 ? 0.25F : 1.0F;
        if(thrower.isSprinting()) modifier *= 2F;
        Vec3d viewVec = thrower.getLookVec();
        this.motionX = viewVec.x * modifier;
        this.motionY = viewVec.y * modifier / (thrower.isSprinting() ? 2 : 1);
        this.motionZ = viewVec.z * modifier;
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

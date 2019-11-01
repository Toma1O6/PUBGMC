package com.toma.pubgmc.common.entity.throwables;

import com.toma.pubgmc.util.PUBGMCUtil;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.MoverType;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;

public abstract class EntityThrowableExplodeable extends Entity implements IEntityAdditionalSpawnData {

    public static final float DRAG_MODIFIER = 0.984F;
    public static final float BOUNCE_MODIFIER = 0.25F;

    public EnumEntityThrowState state;
    public Entity thrower;
    public int fuse;

    private float bounceAmount = 1.0F;
    private int timesBounced = 0;

    public EntityThrowableExplodeable(World world) {
        this(world, null, EnumEntityThrowState.FORCED);
    }

    public EntityThrowableExplodeable(World world, EntityLivingBase thrower, EnumEntityThrowState state) {
        this(world, thrower, state, state == EnumEntityThrowState.FORCED ? 1 : 100);
    }

    public EntityThrowableExplodeable(World world, EntityLivingBase thrower, EnumEntityThrowState state, int time) {
        super(world);
        this.setSize(0.2F, 0.2F);
        this.setPosition(thrower.posX, thrower.posY, thrower.posZ);
        this.fuse = time;
        this.state = state;
        this.thrower = thrower;
    }

    public abstract void onExplode();

    @Override
    public void onUpdate() {
        --this.fuse;
        if(fuse < 0) {
            this.onExplode();
        }

        Vec3d positionVec = PUBGMCUtil.getPositionVec(this);
        Vec3d motionVec = PUBGMCUtil.getMotionVec(this);
        RayTraceResult rayTraceResult = this.world.rayTraceBlocks(positionVec, motionVec, false, true, false);

        if(rayTraceResult != null && rayTraceResult.typeOfHit == RayTraceResult.Type.BLOCK) {
            this.onGrenadeBounce(rayTraceResult);
        }

        this.move(MoverType.SELF, this.motionX, this.motionY, this.motionZ);
        super.onUpdate();
    }

    public void onGrenadeBounce(RayTraceResult rayTraceResult) {
        ++this.timesBounced;
        Vec3d collidedAt = rayTraceResult.hitVec;
        double horizontalAngle = Math.toDegrees(Math.atan2(collidedAt.z - this.posZ, collidedAt.x - this.posX));
        double verticalAngle = Math.toDegrees(Math.atan(collidedAt.y - this.posY));
        double xNew = Math.sin(horizontalAngle);
        double yNew = Math.sin(verticalAngle);
        double zNew = Math.cos(horizontalAngle);
        this.motionX *= bounceAmount;
        this.motionY *= bounceAmount;
        this.motionZ *= bounceAmount;
        this.bounceAmount = bounceAmount <= BOUNCE_MODIFIER ? 0 : bounceAmount - BOUNCE_MODIFIER;
        this.setMotionAndUpdate(this.motionX * xNew, this.motionY * yNew, this.motionZ * zNew);
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

    protected void setMotionAndUpdate(double x, double y, double z) {
        this.motionX = x * DRAG_MODIFIER;
        this.motionY = motionY < -0.4905D && motionY < -0.52D ? y * -DRAG_MODIFIER : y * DRAG_MODIFIER;
        this.motionZ = z * DRAG_MODIFIER;
    }

    public enum EnumEntityThrowState {

        LONG,
        SHORT,
        FORCED
    }
}

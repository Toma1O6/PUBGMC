package dev.toma.pubgmc.common.entity.controllable;

import io.netty.buffer.ByteBuf;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.event.ForgeEventFactory;

public abstract class EntityVehicle extends EntityDriveable {

    private int timeOnFire;

    public EntityVehicle(World world) {
        super(world);
        this.stepHeight = this.getStepHeight();
    }

    @Override
    public void runVehicleTick() {
        this.handleDestroyedTick();
    }

    @Override
    protected void writeEntityToNBT(NBTTagCompound compound) {
        super.writeEntityToNBT(compound);
        compound.setInteger("timeOnFire", this.timeOnFire);
    }

    @Override
    protected void readEntityFromNBT(NBTTagCompound compound) {
        super.readEntityFromNBT(compound);
        this.timeOnFire = compound.getInteger("timeOnFire");
    }

    @Override
    public void writeSpawnData(ByteBuf buffer) {
    }

    @Override
    public void readSpawnData(ByteBuf additionalData) {
    }

    @Override
    public NBTTagCompound encodeNetworkData() {
        return new NBTTagCompound();
    }

    @Override
    public void decodeNetworkData(NBTTagCompound nbt) {
    }

    protected float getStepHeight() {
        return 1.25F;
    }

    protected int getTicksToExplode() {
        return 100;
    }

    protected void explode() {
        if (this.world.isRemote)
            return;
        boolean doMobGriefing = ForgeEventFactory.getMobGriefingEvent(this.world, this);
        this.world.createExplosion(this, this.posX, this.posY, this.posZ, this.getExplosionPower(), doMobGriefing);
        this.setDead();
    }

    protected float getExplosionPower() {
        return 5.0F;
    }

    private void handleDestroyedTick() {
        if (!this.isDestroyed())
            return;
        if (++this.timeOnFire > this.getTicksToExplode()) {
            this.explode();
        }
    }
}

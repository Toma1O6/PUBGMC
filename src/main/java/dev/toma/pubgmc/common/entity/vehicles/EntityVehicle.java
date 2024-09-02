package dev.toma.pubgmc.common.entity.vehicles;

import io.netty.buffer.ByteBuf;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.event.ForgeEventFactory;

public abstract class EntityVehicle extends EntityDriveable {

    private int timeOnFire;

    public EntityVehicle(World world) {
        super(world);
        this.updateStepHeight();
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

    protected final void updateStepHeight() {
        this.stepHeight = this.getStepHeight();
    }

    private void handleDestroyedTick() {
        if (!this.isDestroyed())
            return;
        if (++this.timeOnFire > this.getTicksToExplode()) {
            this.explode();
        }
    }
}

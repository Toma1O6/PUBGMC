package dev.toma.pubgmc.common.entity.vehicles;

import dev.toma.pubgmc.common.entity.vehicles.util.SeatPart;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraftforge.event.ForgeEventFactory;

public abstract class EntityVehicle extends EntityDriveable {

    protected static final DataParameter<Boolean> BURNED = EntityDataManager.createKey(EntityVehicle.class, DataSerializers.BOOLEAN);

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
        compound.setBoolean("burned", this.isBurned());
    }

    @Override
    protected void readEntityFromNBT(NBTTagCompound compound) {
        super.readEntityFromNBT(compound);
        this.timeOnFire = compound.getInteger("timeOnFire");
        this.setBurned(compound.getBoolean("burned"));
    }

    @Override
    public boolean canEntityBoardVehicle(SeatPart seat, EntityLivingBase entity) {
        return super.canEntityBoardVehicle(seat, entity) && !this.isBurned();
    }

    @Override
    protected void entityInit() {
        super.entityInit();
        this.dataManager.register(BURNED, Boolean.FALSE);
    }

    @Override
    protected boolean handleEntityAttack(DamageSource source, float amount) {
        if (this.isBurned() && !this.world.isRemote && source.isCreativePlayer()) {
            this.setDead();
            return true;
        }
        if (!this.isBurned() && source.isExplosion() && amount >= this.getHealth()) {
            this.timeOnFire = Math.max(this.getTicksToExplode() - 5, this.timeOnFire);
        }
        return super.handleEntityAttack(source, amount);
    }

    public final boolean isFunctional() {
        return !this.isDestroyed() && !this.isBurned();
    }

    public final void setBurned(boolean burned) {
        this.dataManager.set(BURNED, burned);
    }

    public final boolean isBurned() {
        return this.dataManager.get(BURNED);
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
        this.multiplyMotion(1.2F);
        this.motionY += 0.8F;
        this.removePassengers();
        this.setBurned(true);
        for (EntityVehiclePart part : this.getParts()) {
            part.hurt(DamageSource.GENERIC, Integer.MAX_VALUE);
        }
        this.world.createExplosion(this, this.posX, this.posY, this.posZ, this.getExplosionPower(), doMobGriefing);
    }

    protected float getExplosionPower() {
        return 4.0F;
    }

    protected final void updateStepHeight() {
        this.stepHeight = this.getStepHeight();
    }

    private void handleDestroyedTick() {
        if (!this.isDestroyed() || this.isBurned())
            return;
        this.killEngine();
        if (++this.timeOnFire > this.getTicksToExplode()) {
            this.explode();
        }
    }
}

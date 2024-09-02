package dev.toma.pubgmc.common.entity.vehicles;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;

public abstract class EntityVehicleDamageablePart extends EntityVehiclePart {

    public static final DataParameter<Float> HEALTH = EntityDataManager.createKey(EntityVehicleDamageablePart.class, DataSerializers.FLOAT);

    protected final float maxHealth;

    public EntityVehicleDamageablePart(EntityDriveable parent, String name, float width, float height, Vec3d relativePosition, float maxHealth) {
        super(parent, name, width, height, relativePosition);
        this.maxHealth = maxHealth;
    }

    @Override
    protected void entityInit() {
        this.dataManager.register(HEALTH, this.maxHealth);
    }

    @Override
    protected void hurt(DamageSource source, float damage) {
        this.hurt(damage);
    }

    @Override
    protected boolean isDestroyed() {
        return this.getHealth() <= 0;
    }

    @Override
    protected void writeEntityToNBT(NBTTagCompound compound) {
        compound.setFloat("health", this.getHealth());
    }

    @Override
    protected void readEntityFromNBT(NBTTagCompound compound) {
        this.setHealth(compound.getFloat("health"));
    }

    public final void setHealth(float health) {
        this.dataManager.set(HEALTH, MathHelper.clamp(health, 0.0F, this.maxHealth));
    }

    public final void hurt(float amount) {
        this.setHealth(this.getHealth() - amount);
    }

    public final float getHealth() {
        return this.dataManager.get(HEALTH);
    }

    public final float getMaxHealth() {
        return maxHealth;
    }
}

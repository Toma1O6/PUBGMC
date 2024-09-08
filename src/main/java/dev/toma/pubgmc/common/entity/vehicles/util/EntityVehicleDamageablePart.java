package dev.toma.pubgmc.common.entity.vehicles.util;

import dev.toma.pubgmc.common.entity.vehicles.EntityDriveable;
import dev.toma.pubgmc.common.entity.vehicles.EntityVehiclePart;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;

public abstract class EntityVehicleDamageablePart extends EntityVehiclePart {

    protected final float maxHealth;

    private float health;

    public EntityVehicleDamageablePart(EntityDriveable parent, String name, float width, float height, Vec3d relativePosition, float maxHealth) {
        super(parent, name, width, height, relativePosition);
        this.maxHealth = maxHealth;

        this.health = maxHealth;
    }

    @Override
    protected void hurt(DamageSource source, float damage) {
        boolean destroyed = this.isDestroyed();
        this.hurt(damage);
        if (!destroyed && this.isDestroyed()) {
            this.onDestroyed();
        }
        this.access.synchronizeClientData();
    }

    @Override
    protected boolean isDestroyed() {
        return this.getHealth() <= 0;
    }

    @Override
    public boolean hasCustomSaveData() {
        return true;
    }

    @Override
    public NBTTagCompound savePartData() {
        NBTTagCompound compound = new NBTTagCompound();
        compound.setFloat("health", this.getHealth());
        return compound;
    }

    @Override
    public void loadPartData(NBTTagCompound compound) {
        this.setHealth(compound.getFloat("health"));
    }

    public final void setHealth(float health) {
        this.health = MathHelper.clamp(health, 0.0F, this.maxHealth);
    }

    public final void hurt(float amount) {
        this.setHealth(this.getHealth() - amount);
    }

    public final float getHealth() {
        return this.health;
    }

    public final float getMaxHealth() {
        return maxHealth;
    }

    protected void onDestroyed() {}
}

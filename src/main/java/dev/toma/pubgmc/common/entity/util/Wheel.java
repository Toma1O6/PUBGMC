package dev.toma.pubgmc.common.entity.util;

import dev.toma.pubgmc.common.entity.controllable.EntityLandVehicle;
import net.minecraft.entity.MultiPartEntityPart;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;

public final class Wheel extends MultiPartEntityPart {

    private static final DataParameter<Float> HEALTH = EntityDataManager.createKey(Wheel.class, DataSerializers.FLOAT);

    private final Vec3d position;
    private final float size;
    private final float maxHealth;
    private boolean isTurnWheel;
    private boolean isAccelerationWheel;

    public Wheel(EntityLandVehicle owner, String name, Vec3d position, float size, float maxHealth) {
        super(owner, name, size, size);
        this.position = position;
        this.size = size;
        this.maxHealth = maxHealth;
    }

    public void updatePosition(EntityLandVehicle vehicle) {
        Vec3d pos = this.getWorldPosition(vehicle);
        this.setPositionAndUpdate(pos.x, pos.y, pos.z);
    }

    public boolean isDestroyed() {
        return this.getHealth() <= 0.0F;
    }

    public void applyDamage(float amount) {
        this.setHealth(this.getHealth() - amount);
    }

    public float getHealth() {
        return this.dataManager.get(HEALTH);
    }

    public void setHealth(float amount) {
        this.dataManager.set(HEALTH, MathHelper.clamp(amount, 0.0F, this.maxHealth));
    }

    @Override
    protected void entityInit() {
        this.dataManager.register(HEALTH, this.maxHealth);
    }

    public Vec3d getWorldPosition(EntityLandVehicle owner) {
        Vec3d offset = owner.getPositionVector();
        return this.position.rotateYaw(-owner.rotationYaw * (float) (Math.PI / 180.0F)).add(offset);
    }

    public AxisAlignedBB getBoundingBox(EntityLandVehicle owner) {
        Vec3d pos = this.getWorldPosition(owner);
        return new AxisAlignedBB(pos.x - this.size, pos.y, pos.z - this.size, pos.x + this.size, pos.y + 2 * this.size, pos.z + this.size);
    }

    public Vec3d getRelativePosition() {
        return position;
    }

    public float getSize() {
        return size;
    }

    public float getMaxHealth() {
        return maxHealth;
    }

    public void setTurnWheel(boolean turnWheel) {
        isTurnWheel = turnWheel;
    }

    public void setAccelerationWheel(boolean accelerationWheel) {
        isAccelerationWheel = accelerationWheel;
    }

    public boolean isTurnWheel() {
        return isTurnWheel;
    }

    public boolean isAccelerationWheel() {
        return isAccelerationWheel;
    }
}

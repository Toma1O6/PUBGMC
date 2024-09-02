package dev.toma.pubgmc.common.entity.vehicles;

import net.minecraft.util.math.Vec3d;

public final class WheelPart extends EntityVehicleDamageablePart {

    private boolean isTurnWheel;
    private boolean isAccelerationWheel;

    public WheelPart(EntityLandVehicle owner, String name, Vec3d position, float size, float maxHealth) {
        super(owner, name, size, size, position, maxHealth);
        this.setDamageMultiplier(EntityLandVehicle.WHEEL_DAMAGE_MULTIPLIER);
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

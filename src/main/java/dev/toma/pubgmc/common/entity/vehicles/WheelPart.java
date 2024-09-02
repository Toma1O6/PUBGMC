package dev.toma.pubgmc.common.entity.vehicles;

import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.WorldServer;

import static dev.toma.pubgmc.DevUtil.randomRange;

public final class WheelPart extends EntityVehicleDamageablePart {

    public static final float DEFAULT_WHEEL_HEALTH = 3.0F;
    public static final float DEFAULT_WHEEL_SIZE = 0.75F;

    private boolean isTurnWheel;
    private boolean isAccelerationWheel;

    public WheelPart(EntityLandVehicle owner, String name, Vec3d position) {
        this(owner, name, position, DEFAULT_WHEEL_SIZE);
    }

    public WheelPart(EntityLandVehicle owner, String name, Vec3d position, float size) {
        this(owner, name, position, size, DEFAULT_WHEEL_HEALTH);
    }

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

    @Override
    protected void onDestroyed() {
        if (this.world.isRemote)
            return;
        WorldServer server = (WorldServer) this.world;
        float halfWidth = this.width / 2.0F;
        Vec3d pos = this.getWorldPosition().addVector(halfWidth, this.height / 2.0F, halfWidth);
        server.spawnParticle(EnumParticleTypes.CLOUD, true, pos.x, pos.y, pos.z, 25, randomRange(this.rand, 0.25), randomRange(this.rand, 0.05), randomRange(this.rand, 0.25), 0.05F);
    }
}

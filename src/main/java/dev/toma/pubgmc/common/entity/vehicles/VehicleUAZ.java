package dev.toma.pubgmc.common.entity.vehicles;

import dev.toma.pubgmc.common.entity.vehicles.util.SeatPart;
import dev.toma.pubgmc.common.entity.vehicles.util.WheelPart;
import dev.toma.pubgmc.config.ConfigPMC;
import dev.toma.pubgmc.config.common.CFGVehicle;
import dev.toma.pubgmc.init.PMCSounds;
import dev.toma.pubgmc.util.math.Mth;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.function.Consumer;

public class VehicleUAZ extends EntityLandVehicle {

    private static final float FUEL_TANK_CAPACITY = 120F;
    private static final Vec3d ENGINE_POSITION = new Vec3d(0.0, 1.5, 2.0);
    private static final Vec3d EXHAUST_POSITION = new Vec3d(0.6, 0.3, -1.9);
    private static final int TOTAL_TURN_WHEEL = 2;
    private static final int TOTAL_ACCELERATION_WHEEL = 2;
    private static final int TOTAL_WHEELS = 4;
    private EntityVehiclePart body;

    public VehicleUAZ(World world) {
        super(world);
        this.setSize(4.5F, 2.4F);
    }

    @Override
    public CFGVehicle getVehicleConfiguration() {
        return ConfigPMC.vehicles().uaz;
    }

    @Override
    public LandVehicleSoundPack createSoundPack() {
        return new LandVehicleSoundPack(
                PMCSounds.uazStarting,
                PMCSounds.uazStarted
        );
    }

    @Override
    public EntityVehiclePart getMainBodyPart() {
        return this.body;
    }

    @Override
    public void registerVehicleParts(PartRegistration registration) {
        this.body = registration.register(new EntityVehiclePart(this, "bodyMain", 2.4F, 1.2F, new Vec3d(0.0, 0.3, -0.3F)));
        this.body.setDamageMultiplier(BODY_DAMAGE_MULTIPLIER);

        EntityVehiclePart engine = registration.register(new EntityVehiclePart(this, "bodyEngine", 2.0F, 1.2F, new Vec3d(0.0, 0.3, 1.7)));
        engine.setDamageMultiplier(ENGINE_DAMAGE_MULTIPLIER);

        EntityVehiclePart roof = registration.register(new EntityVehiclePart(this, "bodyRoof", 2.4F, 0.2F, new Vec3d(0.0, 2.2F, -0.3F)));
        roof.setDamageMultiplier(ROOF_DAMAGE_MULTIPLIER);

        WheelPart frontRight = registration.register(new WheelPart(this, "wheelFrontRight", new Vec3d(-1.0, 0.0, 1.8)));
        frontRight.setTurnWheel(true);
        frontRight.setAccelerationWheel(false);

        WheelPart frontLeft = registration.register(new WheelPart(this, "wheelFrontLeft", new Vec3d(1.0, 0.0, 1.8)));
        frontLeft.setTurnWheel(true);
        frontLeft.setAccelerationWheel(false);

        WheelPart rearRight = registration.register(new WheelPart(this, "wheelRearRight", new Vec3d(-1.0, 0.0, -0.8)));
        rearRight.setTurnWheel(false);
        rearRight.setAccelerationWheel(true);

        WheelPart rearLeft = registration.register(new WheelPart(this, "wheelRearLeft", new Vec3d(1.0, 0.0, -0.8)));
        rearLeft.setTurnWheel(false);
        rearLeft.setAccelerationWheel(true);

        registration.register(new SeatPart(this, "seatDriver", new Vec3d(0.6, 0.3, 0.2), 1.2F, true));
        registration.register(new SeatPart(this, "seatPassengerFront", new Vec3d(-0.6, 0.3, 0.2), -1.2F));
        registration.register(new SeatPart(this, "seatPassengerBackLeft", new Vec3d(0.7, 0.35, -0.8), 1.1F));
        registration.register(new SeatPart(this, "seatPassengerBackCenter", new Vec3d(0.0, 0.35, -0.8), 1.8F));
        registration.register(new SeatPart(this, "seatPassengerBackRight", new Vec3d(-0.7, 0.35, -0.8), -1.1F));
    }

    @Override
    public float getTurnSpeed() {
        float base = getVehicleConfiguration().turningSpeed.getAsFloat();
        EntityVehiclePart[] parts = this.getParts();
        if (parts == null) {
            return 0F;
        }
        int good = 0;
        for (EntityVehiclePart part : this.getParts()) {
            if (part instanceof WheelPart && ((WheelPart) part).isTurnWheel() && !part.isDead && !part.isDestroyed()) {
                good++;
            }
        }
        return Mth.exponentialDecay(base, 0.6F + 0.4F * ((float) good / TOTAL_TURN_WHEEL));
    }

    @Override
    public float getAcceleration() {
        float base = getVehicleConfiguration().acceleration.getAsFloat();
        EntityVehiclePart[] parts = this.getParts();
        if (parts == null) {
            return 0F;
        }
        int good = 0;
        for (EntityVehiclePart part : this.getParts()) {
            if (part instanceof WheelPart && ((WheelPart) part).isAccelerationWheel() && !part.isDead && !part.isDestroyed()) {
                good++;
            }
        }
        return Mth.exponentialDecay(base, 0.4F + 0.6F * ((float) good / TOTAL_ACCELERATION_WHEEL));
    }

    @Override
    public float getMaxSpeed() {
        float base = getVehicleConfiguration().maxSpeed.getAsFloat();
        EntityVehiclePart[] parts = this.getParts();
        if (parts == null) {
            return 0F;
        }
        int turn = TOTAL_TURN_WHEEL;
        int acc = TOTAL_ACCELERATION_WHEEL;
        for (EntityVehiclePart part : this.getParts()) {
            if (part instanceof WheelPart && !part.isDead && part.isDestroyed()) {
                if (((WheelPart) part).isTurnWheel())
                    turn--;
                if (((WheelPart) part).isAccelerationWheel())
                    acc--;
            }
        }
        return Mth.exponentialDecay(base, Math.min(0.2F + 0.2F * turn + 0.6F * acc, 1));
    }

    @Override
    public float getReverseMaxSpeed() {
        return -0.3F * this.getMaxSpeed();
    }

    @Override
    public float getMaxHealth() {
        CFGVehicle cfg = getVehicleConfiguration();
        return cfg.maxHealth.getAsFloat();
    }

    @Override
    public float getFuelTankCapacity() {
        return FUEL_TANK_CAPACITY;
    }

    @Override
    public void processEngineParticles(Consumer<Vec3d> particleOriginRegistration) {
        particleOriginRegistration.accept(ENGINE_POSITION);
    }

    @Override
    public void processExhaustParticles(Consumer<Vec3d> particleOriginRegistration) {
        particleOriginRegistration.accept(EXHAUST_POSITION);
    }
}
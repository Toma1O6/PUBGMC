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
    private static final Vec3d EXHAUST_POSITION = new Vec3d(-0.6, 0.3, -1.9);
    private static final int TOTAL_TURN_WHEEL = 2;
    private static final int TOTAL_ACCELERATION_WHEEL = 2;
    private static final int TOTAL_WHEELS = 4;
    private EntityVehiclePart body;
    protected static final Vec3d modelScale = new Vec3d(-1, -1, 1);
    protected static final Vec3d modelOffset = new Vec3d(0, 1.4F, 0.6F); // at 0° yaw

    public VehicleUAZ(World world) {
        super(world);
        this.setSize(3.35F, 2.4F);
    }

    @Override
    public final Vec3d getModelScale() {
        return modelScale;
    }

    @Override
    public final Vec3d getModelOffset() {
        return modelOffset;
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
        // Register wheel first, used in ModelUAZ render
        WheelPart wheelFL = registration.register(new WheelPart(this, "wheelFrontLeft", new Vec3d(1.0, 0.0, 1.3F), 0.75F));
        wheelFL.setTurnWheel(true);
        wheelFL.setAccelerationWheel(false);
        WheelPart wheelFR = registration.register(new WheelPart(this, "wheelFrontRight", new Vec3d(-1.0, 0.0, 1.3F), 0.75F));
        wheelFR.setTurnWheel(true);
        wheelFR.setAccelerationWheel(false);
        WheelPart wheelRL = registration.register(new WheelPart(this, "wheelRearLeft", new Vec3d(1.1, 0.0, -1.3F), 0.75F));
        wheelRL.setTurnWheel(false);
        wheelRL.setAccelerationWheel(true);
        wheelRL.setBlockCollisionMode(EntityVehiclePart.BoundingBoxMode.NONE);
        WheelPart wheelRR = registration.register(new WheelPart(this, "wheelRearRight", new Vec3d(-1.1, 0.0, -1.3F), 0.75F));
        wheelRR.setTurnWheel(false);
        wheelRR.setAccelerationWheel(true);
        wheelRR.setBlockCollisionMode(EntityVehiclePart.BoundingBoxMode.NONE);

        EntityVehiclePart engine = registration.register(new EntityVehiclePart(this, "bodyEngine", 2.0F, 1.2F, new Vec3d(0.0, 0.3, 1.4F)));
        engine.setDamageMultiplier(ENGINE_DAMAGE_MULTIPLIER);

        this.body = registration.register(new EntityVehiclePart(this, "bodyMain", 2.7F, 1.2F, new Vec3d(0.0, 0.3, -0.95F)));
        this.body.setDamageMultiplier(BODY_DAMAGE_MULTIPLIER);
        EntityVehiclePart aPillarL = registration.register(new EntityVehiclePart(this, "aPillarLeft", 0.1F, 0.7F, new Vec3d(1.3F, 1.5F, 0.6F)));
        aPillarL.setDamageMultiplier(ROOF_DAMAGE_MULTIPLIER);
        EntityVehiclePart aPillarR = registration.register(new EntityVehiclePart(this, "aPillarRight", 0.1F, 0.7F, new Vec3d(-1.3F, 1.5F, 0.6F)));
        aPillarR.setDamageMultiplier(ROOF_DAMAGE_MULTIPLIER);
        registration.register(new SeatPart(this, "seatDriver", new Vec3d(0.6, 0.3, -0.3F), 0.9F, 1.35F,
                new Vec3d(2.3F, 0.25F, -1), true));
        registration.register(new SeatPart(this, "seatPassengerFront", new Vec3d(-0.6, 0.3, -0.3F), 0.9F, 1.35F,
                new Vec3d(-2.3F, 0.25F, 0)));
        EntityVehiclePart bPillarL = registration.register(new EntityVehiclePart(this, "bPillarLeft", 0.1F, 0.7F, new Vec3d(1.3F, 1.5F, -0.28F)));
        bPillarL.setDamageMultiplier(ROOF_DAMAGE_MULTIPLIER);
        EntityVehiclePart bPillarR = registration.register(new EntityVehiclePart(this, "bPillarRight", 0.1F, 0.7F, new Vec3d(-1.3F, 1.5F, -0.28F)));
        bPillarR.setDamageMultiplier(ROOF_DAMAGE_MULTIPLIER);
        registration.register(new SeatPart(this, "seatPassengerBackLeft", new Vec3d(0.6, 0.3, -1.3F), 1.0F, 1.45F,
                new Vec3d(2.3F, 0.25F, 0)));
        registration.register(new SeatPart(this, "seatPassengerBackRight", new Vec3d(-0.6, 0.3, -1.3F), 1.0F, 1.45F,
                new Vec3d(-2.3F, 0.25F, 0)));
        EntityVehiclePart cPillarL = registration.register(new EntityVehiclePart(this, "cPillarLeft", 0.1F, 0.7F, new Vec3d(1.3F, 1.5F, -1.2F)));
        cPillarL.setDamageMultiplier(ROOF_DAMAGE_MULTIPLIER);
        EntityVehiclePart cPillarR = registration.register(new EntityVehiclePart(this, "cPillarRight", 0.1F, 0.7F, new Vec3d(-1.3F, 1.5F, -1.2F)));
        cPillarR.setDamageMultiplier(ROOF_DAMAGE_MULTIPLIER);
        EntityVehiclePart dPillarL = registration.register(new EntityVehiclePart(this, "dPillarLeft", 0.32F, 0.7F, new Vec3d(1.19F, 1.5F, -2F)));
        dPillarL.setDamageMultiplier(ROOF_DAMAGE_MULTIPLIER);
        EntityVehiclePart dPillarR = registration.register(new EntityVehiclePart(this, "dPillarRight", 0.32F, 0.7F, new Vec3d(-1.19F, 1.5F, -2F)));
        dPillarR.setDamageMultiplier(ROOF_DAMAGE_MULTIPLIER);
        EntityVehiclePart roof = registration.register(new EntityVehiclePart(this, "bodyRoof", 2.7F, 0.2F, new Vec3d(0.0, 2.2F, -0.85F)));
        roof.setDamageMultiplier(ROOF_DAMAGE_MULTIPLIER);
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
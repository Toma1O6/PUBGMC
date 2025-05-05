package dev.toma.pubgmc.common.entity.vehicles;

import dev.toma.pubgmc.Pubgmc;
import dev.toma.pubgmc.common.entity.vehicles.util.SeatPart;
import dev.toma.pubgmc.common.entity.vehicles.util.WheelPart;
import dev.toma.pubgmc.config.ConfigPMC;
import dev.toma.pubgmc.config.common.CFGVehicle;
import dev.toma.pubgmc.init.PMCSounds;
import dev.toma.pubgmc.util.math.Mth;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.function.Consumer;

public class VehicleDacia extends EntityLandVehicle implements Variants{

    private static final float FUEL_TANK_CAPACITY = 100F;
    private static final Vec3d ENGINE_POSITION = new Vec3d(0,1.21F,2.04F);
    private static final Vec3d EXHAUST_POSITION = new Vec3d(0.7F,0.35,-3.35F);
    private static final int TOTAL_TURN_WHEEL = 2;
    private static final int TOTAL_ACCELERATION_WHEEL = 2;
    private static final int TOTAL_WHEELS = 4;
    private EntityVehiclePart body;
    protected static final Vec3d modelScale = new Vec3d(-1, -1, 1);
    protected static final Vec3d modelOffset = new Vec3d(0, 1.9F, -1F);
    private static final ResourceLocation[] VARIANTS = {
            Pubgmc.getResource("textures/vehicle/dacia_blue.png"),
            Pubgmc.getResource("textures/vehicle/dacia_orange.png"),
            Pubgmc.getResource("textures/vehicle/dacia_white.png"),
            Pubgmc.getResource("textures/vehicle/dacia_yellow.png")
    };
    private int textureIndex;

    public VehicleDacia(World world) {
        super(world);
        this.setSize(4.75F, 2.19F);
        this.textureIndex = world.rand.nextInt(VARIANTS.length);
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
    public int getActualTexture() {
        return textureIndex;
    }

    @Override
    public void setTexture(int texture) {
        this.textureIndex = texture;
    }

    @Override
    public ResourceLocation[] getTextures() {
        return VARIANTS;
    }

    @Override
    public CFGVehicle getVehicleConfiguration() {
        return ConfigPMC.vehicles().dacia;
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
        // Register wheel first, used in ModelDacia render
        WheelPart wheelFL = registration.register(new WheelPart(this, "wheelFrontLeft", new Vec3d(0.75F, 0.0, 2.02F), 0.7F));
        wheelFL.setTurnWheel(true);
        wheelFL.setAccelerationWheel(false);
        WheelPart wheelFR = registration.register(new WheelPart(this, "wheelFrontRight", new Vec3d(-0.75F, 0.0, 2.02F), 0.7F));
        wheelFR.setTurnWheel(true);
        wheelFR.setAccelerationWheel(false);
        WheelPart wheelRL = registration.register(new WheelPart(this, "wheelRearLeft", new Vec3d(0.85F, 0.0, -2.02F), 0.7F));
        wheelRL.setTurnWheel(false);
        wheelRL.setAccelerationWheel(true);
        WheelPart wheelRR = registration.register(new WheelPart(this, "wheelRearRight", new Vec3d(-0.85F, 0.0, -2.02F), 0.7F));
        wheelRR.setTurnWheel(false);
        wheelRR.setAccelerationWheel(true);

        EntityVehiclePart engine = registration.register(new EntityVehiclePart(this, "bodyEngine", 1.62F, 0.86F, new Vec3d(0.0, 0.35F, 2.04F)));
        engine.setDamageMultiplier(ENGINE_DAMAGE_MULTIPLIER);

        this.body = registration.register(new EntityVehiclePart(this, "bodyMain", 2.19F, 0.96F, new Vec3d(0.0, 0.35F, 0.14)));
        this.body.setDamageMultiplier(BODY_DAMAGE_MULTIPLIER);
        EntityVehiclePart aPillarL = registration.register(new EntityVehiclePart(this, "aPillarLeft", 0.1F, 0.79F, new Vec3d(1.045F, 1.31F, 0.95F)));
        aPillarL.setDamageMultiplier(ROOF_DAMAGE_MULTIPLIER);
        EntityVehiclePart aPillarR = registration.register(new EntityVehiclePart(this, "aPillarRight", 0.1F, 0.79F, new Vec3d(-1.045F, 1.31F, 0.95F)));
        aPillarR.setDamageMultiplier(ROOF_DAMAGE_MULTIPLIER);
        SeatPart seatFL = new SeatPart(this, "seatDriver", new Vec3d(0.5F, 0.15, 0.25F), 0.8F, 1.4F,
                new Vec3d(2.3F, 0.35F, -1), true);
        seatFL.disableBulletHit();
        registration.register(seatFL);
        SeatPart seatFR = new SeatPart(this, "seatPassengerFront", new Vec3d(-0.5F, 0.15, 0.25F), 0.8F, 1.4F,
                new Vec3d(-2.3F, 0.35F, 0));
        seatFR.disableBulletHit();
        registration.register(seatFR);
        EntityVehiclePart roof = registration.register(new EntityVehiclePart(this, "bodyRoof", 2.1F, 0.1F, new Vec3d(0.0, 2.0F, -0.25F)));
        roof.setDamageMultiplier(ROOF_DAMAGE_MULTIPLIER);
        EntityVehiclePart bPillarL = registration.register(new EntityVehiclePart(this, "bPillarLeft", 0.12F, 0.79F, new Vec3d(1.035F, 1.31F, -0.28F)));
        bPillarL.setDamageMultiplier(ROOF_DAMAGE_MULTIPLIER);
        EntityVehiclePart bPillarR = registration.register(new EntityVehiclePart(this, "bPillarRight", 0.12F, 0.79F, new Vec3d(-1.035F, 1.31F, -0.28F)));
        bPillarR.setDamageMultiplier(ROOF_DAMAGE_MULTIPLIER);
        SeatPart seatRL = new SeatPart(this, "seatPassengerBackLeft", new Vec3d(0.5F, 0.15, -0.84F), 0.9F, 1.5F,
                new Vec3d(2.3F, 0.35F, 0));
        seatRL.disableBulletHit();
        registration.register(seatRL);
        SeatPart seatRR = new SeatPart(this, "seatPassengerBackRight", new Vec3d(-0.5F, 0.15, -0.84F), 0.9F, 1.5F,
                new Vec3d(-2.3F, 0.35F, 0));
        seatRR.disableBulletHit();
        registration.register(seatRR);
        EntityVehiclePart cPillarL = registration.register(new EntityVehiclePart(this, "cPillarLeft", 0.1F, 0.79F, new Vec3d(1.045F, 1.31F, -1.3F)));
        cPillarL.setDamageMultiplier(ROOF_DAMAGE_MULTIPLIER);
        EntityVehiclePart cPillarR = registration.register(new EntityVehiclePart(this, "cPillarRight", 0.1F, 0.79F, new Vec3d(-1.045F, 1.31F, -1.3F)));
        cPillarR.setDamageMultiplier(ROOF_DAMAGE_MULTIPLIER);
        EntityVehiclePart bodyRear = registration.register(new EntityVehiclePart(this, "bodyRear", 2.19F, 0.96F, new Vec3d(0, 0.35F, -2.05F)));
        bodyRear.setDamageMultiplier(BODY_DAMAGE_MULTIPLIER);
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

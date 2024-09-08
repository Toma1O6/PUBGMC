package dev.toma.pubgmc.common.entity.vehicles;

import dev.toma.pubgmc.common.entity.vehicles.util.SeatPart;
import dev.toma.pubgmc.common.entity.vehicles.util.WheelPart;
import dev.toma.pubgmc.init.PMCSounds;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.function.Consumer;

public class VehicleUAZ extends EntityLandVehicle {

    private static final Vec3d ENGINE_POSITION = new Vec3d(0.0, 1.5, 2.0);
    private static final Vec3d EXHAUST_POSITION = new Vec3d(0.6, 0.3, -1.9);
    private EntityVehiclePart body;

    public VehicleUAZ(World world) {
        super(world);
        this.setSize(5.5F, 3.0F);
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

        EntityVehiclePart engine = registration.register(new EntityVehiclePart(this, "bodyEngine", 2.0F, 1.2F, new Vec3d(0.0, 0.3, 1.7)));
        engine.setDamageMultiplier(ENGINE_DAMAGE_MULTIPLIER);

        EntityVehiclePart roof = registration.register(new EntityVehiclePart(this, "bodyRoof", 2.4F, 0.2F, new Vec3d(0.0, 2.2F, -0.3F)));
        roof.setDamageMultiplier(0.6F);

        WheelPart frontRight = registration.register(new WheelPart(this, "wheelFrontRight", new Vec3d(-1.0, 0.0, 1.8)));
        frontRight.setAccelerationWheel(true);
        frontRight.setTurnWheel(true);

        WheelPart frontLeft = registration.register(new WheelPart(this, "wheelFrontLeft", new Vec3d(1.0, 0.0, 1.8)));
        frontLeft.setAccelerationWheel(true);
        frontLeft.setTurnWheel(true);

        WheelPart rearRight = registration.register(new WheelPart(this, "wheelRearRight", new Vec3d(-1.0, 0.0, -0.8)));
        rearRight.setAccelerationWheel(true);

        WheelPart rearLeft = registration.register(new WheelPart(this, "wheelRearLeft", new Vec3d(1.0, 0.0, -0.8)));
        rearLeft.setAccelerationWheel(true);

        registration.register(new SeatPart(this, "seatDriver", new Vec3d(0.6, 0.3, 0.2), 1.2F, true));
        registration.register(new SeatPart(this, "seatPassengerFront", new Vec3d(-0.6, 0.3, 0.2), -1.2F));
        registration.register(new SeatPart(this, "seatPassengerBackLeft", new Vec3d(0.7, 0.35, -0.8), 1.1F));
        registration.register(new SeatPart(this, "seatPassengerBackCenter", new Vec3d(0.0, 0.35, -0.8), 1.8F));
        registration.register(new SeatPart(this, "seatPassengerBackRight", new Vec3d(-0.7, 0.35, -0.8), -1.1F));
    }

    @Override
    public float getMaxHealth() {
        return 20.0F; // TODO config
    }

    @Override
    public float getFuelTankCapacity() {
        return 120.0F;
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
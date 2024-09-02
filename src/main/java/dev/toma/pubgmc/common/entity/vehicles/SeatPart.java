package dev.toma.pubgmc.common.entity.vehicles;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.Vec3d;

import javax.annotation.Nullable;

public final class SeatPart extends EntityVehiclePart {

    private final boolean driver;

    public SeatPart(EntityDriveable vehicle, String seatName, Vec3d position) {
        this(vehicle, seatName, position, false);
    }

    public SeatPart(EntityDriveable vehicle, String seatName, Vec3d position, boolean driver) {
        super(vehicle, seatName, 1.0F, 1.5F, position);
        this.driver = driver;
    }

    public boolean isDriver() {
        return driver;
    }

    @Override
    public boolean processInitialInteract(EntityPlayer player, EnumHand hand) {
        EntityDriveable driveable = (EntityDriveable) this.parent;
        return driveable.boardVehicle(this, player, hand);
    }

    @Nullable
    @Override
    public AxisAlignedBB getCollisionBoundingBox() {
        return null;
    }
}

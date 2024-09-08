package dev.toma.pubgmc.common.entity.vehicles.util;

import dev.toma.pubgmc.common.entity.vehicles.EntityDriveable;
import dev.toma.pubgmc.common.entity.vehicles.EntityVehiclePart;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.Vec3d;

import javax.annotation.Nullable;

public final class SeatPart extends EntityVehiclePart {

    private final float dismountOffset;
    private final boolean driver;

    public SeatPart(EntityDriveable vehicle, String seatName, Vec3d position, float dismountOffset) {
        this(vehicle, seatName, position, dismountOffset, false);
    }

    public SeatPart(EntityDriveable vehicle, String seatName, Vec3d position, float dismountOffset, boolean driver) {
        super(vehicle, seatName, 1.0F, 1.5F, position);
        this.dismountOffset = dismountOffset;
        this.driver = driver;
        this.setBlockCollisionMode(BoundingBoxMode.NONE);
    }

    public Vec3d getDismountPosition() {
        EntityDriveable parent = this.access.getParentEntity();
        Vec3d seatPosition = this.getRelativePosition().addVector(this.dismountOffset, -0.2, 0.0);
        return seatPosition.rotateYaw(-parent.rotationYaw * (float) (Math.PI / 180.0F)).add(parent.getPositionVector());
    }

    public boolean isDriver() {
        return driver;
    }

    @Override
    public boolean processInitialInteract(EntityPlayer player, EnumHand hand) {
        EntityDriveable driveable = this.access.getParentEntity();
        return driveable.boardVehicle(this, player, hand);
    }

    @Nullable
    @Override
    public AxisAlignedBB getCollisionBoundingBox() {
        return null;
    }
}

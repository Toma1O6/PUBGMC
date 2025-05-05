package dev.toma.pubgmc.common.entity.vehicles.util;

import dev.toma.pubgmc.common.entity.vehicles.EntityDriveable;
import dev.toma.pubgmc.common.entity.vehicles.EntityVehiclePart;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.Vec3d;


public final class SeatPart extends EntityVehiclePart {

    private final Vec3d dismountOffset;
    private final boolean driver;

    public SeatPart(EntityDriveable vehicle, String seatName, Vec3d position, Vec3d dismountOffset) {
        this(vehicle, seatName, position, dismountOffset, false);
    }

    public SeatPart(EntityDriveable vehicle, String seatName, Vec3d position, Vec3d dismountOffset, boolean driver) {
        this(vehicle, seatName, position, 1.0F, 1.5F, dismountOffset, driver);
    }

    public SeatPart(EntityDriveable vehicle, String seatName, Vec3d position, float width, float height, Vec3d dismountOffset) {
        this(vehicle, seatName, position, width, height, dismountOffset, false);
    }

    public SeatPart(EntityDriveable vehicle, String seatName, Vec3d position, float width, float height, Vec3d dismountOffset, boolean driver) {
        super(vehicle, seatName, width, height, position);
        this.dismountOffset = dismountOffset;
        this.driver = driver;
        this.setBlockCollisionMode(BoundingBoxMode.COLLIDER);
    }

    public Vec3d getDismountPosition() {
        EntityDriveable parent = this.access.getParentEntity();
        Vec3d seatPosition = this.getRelativePosition().add(this.dismountOffset);
        return seatPosition.rotateYaw(-parent.rotationYaw * (float) (Math.PI / 180.0F)).add(parent.getPositionVector());
    }

    public boolean isDriver() {
        return driver;
    }

    @Override
    public boolean processInitialInteract(EntityPlayer player, EnumHand hand) {
        // TODO cancel aiming when interact
        EntityDriveable driveable = this.access.getParentEntity();
        return driveable.boardVehicle(this, player, hand);
    }
}

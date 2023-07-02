package dev.toma.pubgmc.api.game;

public interface PlayzoneDeliveryVehicle extends GameObject {

    default boolean disablePlayzoneDamageForPassengers() {
        return true;
    }
}

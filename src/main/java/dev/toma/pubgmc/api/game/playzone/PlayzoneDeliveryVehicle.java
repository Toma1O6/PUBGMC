package dev.toma.pubgmc.api.game.playzone;

import dev.toma.pubgmc.api.game.GameObject;

public interface PlayzoneDeliveryVehicle extends GameObject {

    default boolean disablePlayzoneDamageForPassengers() {
        return true;
    }
}

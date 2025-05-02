package dev.toma.pubgmc.common.entity.vehicles.util;

public abstract class VehicleSoundController {

    public abstract void play(int eventId);

    public abstract void stop(int eventId);

    public abstract void update();

    public abstract void playStartingSound();

    public abstract void playStartedSound();
}

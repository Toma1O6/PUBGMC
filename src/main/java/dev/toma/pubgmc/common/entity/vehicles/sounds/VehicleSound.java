package dev.toma.pubgmc.common.entity.vehicles.sounds;

import dev.toma.pubgmc.common.entity.vehicles.EntityDriveable;
import net.minecraft.client.audio.MovingSound;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;

import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class VehicleSound<D extends EntityDriveable> extends MovingSound {

    private final int soundId;
    private final D vehicle;
    private Predicate<D> playCondition;

    private Consumer<VehicleSound<D>> onSoundStopped;

    public VehicleSound(int soundId, D vehicle, SoundEvent sound) {
        super(sound, SoundCategory.MASTER);
        this.soundId = soundId;
        this.vehicle = vehicle;
    }

    public final void onSoundStopped(Consumer<VehicleSound<D>> onSoundStopped) {
        this.onSoundStopped = onSoundStopped;
    }

    public final void setPlayCondition(Predicate<D> playCondition) {
        this.playCondition = playCondition;
    }

    public final void setRepeating(boolean repeating) {
        this.repeat = repeating;
    }

    @Override
    public void update() {
        if (this.vehicle == null || !this.vehicle.isEntityAlive() || !this.playCondition.test(this.vehicle)) {
            this.donePlaying = true;
            if (this.onSoundStopped != null) {
                this.onSoundStopped.accept(this);
            }
            return;
        }
        this.xPosF = (float) vehicle.posX;
        this.yPosF = (float) vehicle.posY;
        this.zPosF = (float) vehicle.posZ;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof VehicleSound)) return false;
        VehicleSound<?> sound = (VehicleSound<?>) o;
        return Objects.equals(soundId, sound.soundId) && Objects.equals(vehicle.getEntityId(), sound.vehicle.getEntityId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.soundId, this.vehicle.getEntityId());
    }
}

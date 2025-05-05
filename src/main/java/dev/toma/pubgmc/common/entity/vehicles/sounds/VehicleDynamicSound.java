package dev.toma.pubgmc.common.entity.vehicles.sounds;

import dev.toma.pubgmc.common.entity.vehicles.EntityDriveable;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.MathHelper;

import java.util.function.Supplier;

public class VehicleDynamicSound<D extends EntityDriveable> extends VehicleSound<D> {

    private float pitchFrom = 0.5F;
    private float pitchTo = 2.0F;
    private Supplier<Float> pitchAmountUpdater = () -> 0.0F;

    public VehicleDynamicSound(int soundId, D vehicle, SoundEvent sound) {
        super(soundId, vehicle, sound);
    }

    public void setPitchAmountUpdater(Supplier<Float> pitchAmountUpdater) {
        this.pitchAmountUpdater = pitchAmountUpdater;
    }

    public void setSoundPitchRange(float from, float to) {
        if (from > to) {
            throw new IllegalArgumentException("Min pitch value must be less than or equal to max pitch value");
        }
        this.pitchFrom = MathHelper.clamp(from, 0.5F, 2.0F);
        this.pitchTo = MathHelper.clamp(to, 0.5F, 2.0F);
    }

    @Override
    public void update() {
        this.updatePitch(this.pitchAmountUpdater.get());
        super.update();
    }

    public void updatePitch(float percentage) {
        float diff = this.pitchTo - this.pitchFrom;
        this.pitch = this.pitchFrom + percentage * diff;
    }
}

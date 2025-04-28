package dev.toma.pubgmc.common.games.playzone;

import dev.toma.pubgmc.api.game.playzone.Playzone;
import dev.toma.pubgmc.api.game.playzone.PlayzoneDeliveryVehicle;
import dev.toma.pubgmc.config.ConfigPMC;
import dev.toma.pubgmc.init.PMCDamageSources;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.world.WorldServer;

import java.util.List;
import java.util.Objects;
import java.util.function.Supplier;

public abstract class AbstractDamagingPlayzone implements Playzone {

    private DamageOptions damageOptions;
    private boolean visible;

    public AbstractDamagingPlayzone(DamageOptions damageOptions) {
        this.damageOptions = Objects.requireNonNull(damageOptions);
    }

    public DamageOptions getDamageOptions() {
        return damageOptions;
    }

    public void setDamageOptions(DamageOptions damageOptions) {
        this.damageOptions = Objects.requireNonNull(damageOptions);
    }

    @Override
    public boolean isVisible() {
        return visible;
    }

    @Override
    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public void hurtEntity(Entity entity) {
        Entity vehicle = entity.getRidingEntity();
        if (vehicle instanceof PlayzoneDeliveryVehicle) {
            PlayzoneDeliveryVehicle playzoneDeliveryVehicle = (PlayzoneDeliveryVehicle) vehicle;
            if (playzoneDeliveryVehicle.disablePlayzoneDamageForPassengers()) {
                return;
            }
        }
        DamageSource source = PMCDamageSources.ZONE;
        if (ConfigPMC.common.world.damages.zonePenetration.get()) {
            source.setDamageBypassesArmor();
        }
        entity.attackEntityFrom(source, damageOptions.getDamageAmount());
    }

    public void hurtAllOutside(WorldServer world, List<Entity> entities) {
        hurtAllOutside(world, () -> entities);
    }

    public void hurtAllOutside(WorldServer world, Supplier<List<Entity>> entitiesProvider) {
        int interval = damageOptions.getDamageInterval();
        if (interval >= 0 && (interval == 0 || world.getTotalWorldTime() % interval == 0)) {
            List<Entity> entities = entitiesProvider.get();
            entities.stream()
                    .filter(entity -> !isWithin(entity))
                    .forEach(this::hurtEntity);
        }
    }

    public static final class DamageOptions {

        public static final DamageOptions NONE = new DamageOptions(0.0F, -1);
        public static final DamageOptions BOUNDS = new DamageOptions(5.0F, 20);
        private final float damageAmount;
        private final int damageInterval;

        public DamageOptions(float damageAmount, int damageInterval) {
            this.damageAmount = damageAmount;
            this.damageInterval = damageInterval;
        }

        public boolean isDisabled() {
            return damageInterval < 0;
        }

        public float getDamageAmount() {
            return damageAmount;
        }

        public int getDamageInterval() {
            return damageInterval;
        }

        public NBTTagCompound toNbt() {
            NBTTagCompound nbt = new NBTTagCompound();
            nbt.setFloat("amount", damageAmount);
            nbt.setInteger("interval", damageInterval);
            return nbt;
        }

        public static DamageOptions fromNbt(NBTTagCompound nbt) {
            float amount = nbt.getFloat("amount");
            int interval = nbt.getInteger("interval");
            if (interval < 0) {
                return NONE;
            }
            return new DamageOptions(amount, interval);
        }

        @Override
        public String toString() {
            return "[Amount: " + damageAmount + ", Interval: " + damageInterval + "]";
        }
    }
}

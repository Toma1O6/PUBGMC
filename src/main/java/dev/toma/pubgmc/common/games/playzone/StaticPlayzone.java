package dev.toma.pubgmc.common.games.playzone;

import dev.toma.pubgmc.api.game.playzone.PlayzoneSerializer;
import dev.toma.pubgmc.api.game.playzone.PlayzoneType;
import dev.toma.pubgmc.api.util.Position2;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class StaticPlayzone extends AbstractDamagingPlayzone {

    private final Position2 min, max;

    public StaticPlayzone(DamageOptions damageOptions, Position2 min, Position2 max) {
        super(damageOptions);
        this.min = min;
        this.max = max;
    }

    public StaticPlayzone(AbstractDamagingPlayzone playzone) {
        this(playzone.getDamageOptions(), playzone.getPositionMin(1.0F), playzone.getPositionMax(1.0F));
    }

    @Override
    public PlayzoneType<?> getPlayzoneType() {
        return PlayzoneTypes.STATIC_PLAYZONE;
    }

    @Override
    public void tickPlayzone(World world) {
    }

    @Override
    public Position2 getPositionMin(float partialTicks) {
        return min;
    }

    @Override
    public Position2 getPositionMax(float partialTicks) {
        return max;
    }

    @Override
    public String toString() {
        return String.format("StaticPlayzone [%s] -> [%s], DamageSettings: %s", min, max, getDamageOptions());
    }

    public static final class Serializer implements PlayzoneSerializer<StaticPlayzone> {

        @Override
        public NBTTagCompound serializePlayzone(StaticPlayzone playzone) {
            NBTTagCompound nbt = new NBTTagCompound();
            nbt.setTag("min", playzone.min.toNbt());
            nbt.setTag("max", playzone.max.toNbt());
            nbt.setTag("opt", playzone.getDamageOptions().toNbt());
            return nbt;
        }

        @Override
        public StaticPlayzone deserializePlayzone(NBTTagCompound nbt) {
            Position2 min = new Position2(nbt.getCompoundTag("min"));
            Position2 max = new Position2(nbt.getCompoundTag("max"));
            DamageOptions options = DamageOptions.fromNbt(nbt.getCompoundTag("opt"));
            return new StaticPlayzone(options, min, max);
        }
    }
}

package dev.toma.pubgmc.common.games.area;

import dev.toma.pubgmc.api.game.area.GameAreaSerializer;
import dev.toma.pubgmc.api.game.area.GameAreaType;
import dev.toma.pubgmc.api.util.Position2;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class StaticGameArea extends AbstractDamagingArea {

    private final Position2 min, max;

    public StaticGameArea(DamageOptions damageOptions, Position2 min, Position2 max) {
        super(damageOptions);
        this.min = min;
        this.max = max;
    }

    @Override
    public GameAreaType<?> getAreaType() {
        return GameAreaTypes.STATIC_AREA;
    }

    @Override
    public void tickGameArea(World world) {
    }

    @Override
    public Position2 getPositionMin(float partialTicks) {
        return min;
    }

    @Override
    public Position2 getPositionMax(float partialTicks) {
        return max;
    }

    public static final class Serializer implements GameAreaSerializer<StaticGameArea> {

        @Override
        public NBTTagCompound serializeArea(StaticGameArea area) {
            NBTTagCompound nbt = new NBTTagCompound();
            nbt.setTag("min", area.min.toNbt());
            nbt.setTag("max", area.max.toNbt());
            nbt.setTag("opt", area.getDamageOptions().toNbt());
            return nbt;
        }

        @Override
        public StaticGameArea deserializeArea(NBTTagCompound nbt) {
            Position2 min = new Position2(nbt.getCompoundTag("min"));
            Position2 max = new Position2(nbt.getCompoundTag("max"));
            DamageOptions options = DamageOptions.fromNbt(nbt.getCompoundTag("opt"));
            return new StaticGameArea(options, min, max);
        }
    }
}

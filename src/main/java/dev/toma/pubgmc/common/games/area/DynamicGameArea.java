package dev.toma.pubgmc.common.games.area;

import dev.toma.pubgmc.api.game.area.GameArea;
import dev.toma.pubgmc.api.game.area.GameAreaSerializer;
import dev.toma.pubgmc.api.game.area.GameAreaType;
import dev.toma.pubgmc.api.util.Position2;
import dev.toma.pubgmc.util.PUBGMCUtil;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.util.Constants;

import javax.annotation.Nullable;

public class DynamicGameArea extends AbstractDamagingArea {

    private Position2 min, max;
    @Nullable
    private AreaTarget target;
    @Nullable
    private ResizeCompletedCallback resizeCompletedCallback;

    public DynamicGameArea(DamageOptions damageOptions, Position2 min, Position2 max) {
        super(damageOptions);
        this.min = min;
        this.max = max;
    }

    public DynamicGameArea(AbstractDamagingArea area) {
        this(area.getDamageOptions(), area.getPositionMin(1.0F), area.getPositionMax(1.0F));
    }

    public void onResizeCompleted(ResizeCompletedCallback callback) {
        this.resizeCompletedCallback = callback;
    }

    @Override
    public GameAreaType<?> getAreaType() {
        return GameAreaTypes.DYNAMIC_AREA;
    }

    @Override
    public void tickGameArea(World world) {
        if (target != null) {
            target.tick();
            if (target.isCompleted()) {
                min = target.nextMin;
                max = target.nextMax;
                if (target.newDamageOptions != null) {
                    setDamageOptions(target.newDamageOptions);
                }
                target = null;
                if (resizeCompletedCallback != null) {
                    resizeCompletedCallback.onResizeCompleted(this, world);
                }
            }
        }
    }

    @Override
    public Position2 getPositionMin(float partialTicks) {
        return target != null ? target.getAdjustedMinPosition(min, partialTicks) : min;
    }

    @Override
    public Position2 getPositionMax(float partialTicks) {
        return target != null ? target.getAdjustedMaxPosition(max, partialTicks) : max;
    }

    public void setTarget(@Nullable AreaTarget target) {
        this.target = target;
    }

    public boolean isResizing() {
        return target != null && target.getResizeProgress(1.0F) > 0.0F;
    }

    public int getRemainingStationaryTime() {
        return target == null ? -1 : target.initiationDelay - target.startTimer;
    }

    public GameArea getResultingGameArea() {
        return target != null ? new StaticGameArea(target.newDamageOptions, target.nextMin, target.nextMax) : this;
    }

    public static final class AreaTarget {

        private final Position2 nextMin, nextMax;
        private final DamageOptions newDamageOptions;
        private final int resizeTimeTotal;
        private final int initiationDelay;
        private int resizeTimer;
        private int startTimer;

        public AreaTarget(Position2 nextMin, Position2 nextMax, DamageOptions newDamageOptions, int resizeTimeTotal, int initiationDelay) {
            this.nextMin = nextMin;
            this.nextMax = nextMax;
            this.newDamageOptions = newDamageOptions;
            this.resizeTimeTotal = resizeTimeTotal;
            this.initiationDelay = initiationDelay;
        }

        public void tick() {
            if (startTimer < initiationDelay) {
                ++startTimer;
                return;
            }
            if (resizeTimer < resizeTimeTotal) {
                ++resizeTimer;
            }
        }

        public boolean isCompleted() {
            return resizeTimer >= resizeTimeTotal;
        }

        public Position2 getAdjustedMinPosition(Position2 current, float partialTicks) {
            return getAdjustedPosition(current, nextMin, partialTicks);
        }

        public Position2 getAdjustedMaxPosition(Position2 current, float partialTicks) {
            return getAdjustedPosition(current, nextMax, partialTicks);
        }

        private Position2 getAdjustedPosition(Position2 current, Position2 target, float partialTicks) {
            float f = getResizeProgress(partialTicks);
            if (f == 0.0F) {
                return current;
            }
            if (f == 1.0F) {
                return target;
            }
            double xDiff = target.getX() - current.getX();
            double zDiff = target.getZ() - current.getZ();
            return new Position2(current.getX() + xDiff * f, current.getZ() + zDiff * f);
        }

        private float getResizeProgress(float partialTicks) {
            if (startTimer < initiationDelay) {
                return 0.0F;
            }
            if (resizeTimer >= resizeTimeTotal) {
                return 1.0F;
            }
            int prev = Math.max(0, resizeTimer - 1);
            float f0 = prev / (float) resizeTimeTotal;
            float f1 = resizeTimer / (float) resizeTimeTotal;
            return PUBGMCUtil.interpolate(f0, f1, partialTicks);
        }

        private NBTTagCompound serialize() {
            NBTTagCompound nbt = new NBTTagCompound();
            nbt.setTag("nextMin", nextMin.toNbt());
            nbt.setTag("nextMax", nextMax.toNbt());
            if (newDamageOptions != null) {
                nbt.setTag("damageOpt", newDamageOptions.toNbt());
            }
            nbt.setInteger("resizeTimeTotal", resizeTimeTotal);
            nbt.setInteger("delayTimeTotal", initiationDelay);
            nbt.setInteger("resizeTime", resizeTimer);
            nbt.setInteger("delayTime", startTimer);
            return nbt;
        }

        private static AreaTarget deserialize(NBTTagCompound nbt) {
            Position2 nextMin = new Position2(nbt.getCompoundTag("nextMin"));
            Position2 nextMax = new Position2(nbt.getCompoundTag("nextMax"));
            DamageOptions options = null;
            if (nbt.hasKey("damageOpt", Constants.NBT.TAG_COMPOUND)) {
                options = DamageOptions.fromNbt(nbt.getCompoundTag("damageOpt"));
            }
            int resizeTimeTotal = nbt.getInteger("resizeTimeTotal");
            int delayTimeTotal = nbt.getInteger("delayTimeTotal");
            int resizeTime = nbt.getInteger("resizeTime");
            int delayTime = nbt.getInteger("delayTime");
            AreaTarget target = new AreaTarget(nextMin, nextMax, options, resizeTimeTotal, delayTimeTotal);
            target.resizeTimer = resizeTime;
            target.startTimer = delayTime;
            return target;
        }
    }

    @FunctionalInterface
    public interface ResizeCompletedCallback {
        void onResizeCompleted(DynamicGameArea area, World world);
    }

    public static final class Serializer implements GameAreaSerializer<DynamicGameArea> {

        @Override
        public NBTTagCompound serializeArea(DynamicGameArea area) {
            NBTTagCompound nbt = new NBTTagCompound();
            nbt.setTag("min", area.min.toNbt());
            nbt.setTag("max", area.max.toNbt());
            nbt.setTag("opt", area.getDamageOptions().toNbt());
            nbt.setBoolean("visible", area.isVisible());
            if (area.target != null) {
                nbt.setTag("target", area.target.serialize());
            }
            return nbt;
        }

        @Override
        public DynamicGameArea deserializeArea(NBTTagCompound nbt) {
            Position2 min = new Position2(nbt.getCompoundTag("min"));
            Position2 max = new Position2(nbt.getCompoundTag("max"));
            DamageOptions options = DamageOptions.fromNbt(nbt.getCompoundTag("opt"));
            boolean visible = nbt.getBoolean("visible");
            DynamicGameArea gameArea = new DynamicGameArea(options, min, max);
            gameArea.setVisible(visible);
            if (nbt.hasKey("target", Constants.NBT.TAG_COMPOUND)) {
                AreaTarget target = AreaTarget.deserialize(nbt.getCompoundTag("target"));
                gameArea.setTarget(target);
            }
            return gameArea;
        }
    }
}

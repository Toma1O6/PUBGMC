package dev.toma.pubgmc.common.games.playzone;

import dev.toma.pubgmc.api.game.playzone.Playzone;
import dev.toma.pubgmc.api.game.playzone.PlayzoneSerializer;
import dev.toma.pubgmc.api.game.playzone.PlayzoneType;
import dev.toma.pubgmc.api.util.Position2;
import dev.toma.pubgmc.util.PUBGMCUtil;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.util.Constants;

import javax.annotation.Nullable;

public class DynamicPlayzone extends AbstractDamagingPlayzone {

    private Position2 min, max;
    @Nullable
    private ResizeTarget target;
    @Nullable
    private ResizeCallback onPlayzoneResizeStart;
    @Nullable
    private ResizeCallback onPlayzoneResized;

    public DynamicPlayzone(DamageOptions damageOptions, Position2 min, Position2 max) {
        super(damageOptions);
        this.min = min;
        this.max = max;
    }

    public DynamicPlayzone(AbstractDamagingPlayzone playzone) {
        this(playzone.getDamageOptions(), playzone.getPositionMin(1.0F), playzone.getPositionMax(1.0F));
    }

    public void onResizeStarted(ResizeCallback callback) {
        this.onPlayzoneResizeStart = callback;
    }

    public void onResizeCompleted(ResizeCallback callback) {
        this.onPlayzoneResized = callback;
    }

    @Override
    public PlayzoneType<?> getPlayzoneType() {
        return PlayzoneTypes.DYNAMIC_PLAYZONE;
    }

    @Override
    public void tickPlayzone(World world) {
        if (target != null) {
            boolean completeDelayBefore = target.completeResizeDelay();
            target.tick();
            boolean completeDelayNow = target.completeResizeDelay();
            if (!completeDelayBefore && completeDelayNow && this.onPlayzoneResizeStart != null) {
                this.onPlayzoneResizeStart.onResizeChange(this, world);
            }
            if (target.isCompleted()) {
                min = target.nextMin;
                max = target.nextMax;
                if (target.newDamageOptions != null) {
                    setDamageOptions(target.newDamageOptions);
                }
                target = null;
                if (onPlayzoneResized != null) {
                    onPlayzoneResized.onResizeChange(this, world);
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

    public void setTarget(@Nullable ResizeTarget target) {
        this.target = target;
    }

    public boolean isResizing() {
        return target != null && target.getResizeProgress(1.0F) > 0.0F;
    }

    public int getResizeDelayTotal() {
        return target == null ? -1 : target.resizeDelayTotal();
    }

    public int getResizeDelayRemain() {
        return target == null ? -1 : target.resizeDelayRemain();
    }

    public int getResizingTimeTotal() {
        return target == null ? -1 : target.resizeTimeTotal();
    }

    public int getResizingTimeRemain() {
        return target == null ? -1 : target.resizeTimeRemain();
    }

    public Playzone getResultingPlayzone() {
        return target != null ? new StaticPlayzone(target.newDamageOptions, target.nextMin, target.nextMax) : this;
    }

    @Nullable
    public ResizeTarget getTarget() {
        return target;
    }

    @Override
    public String toString() {
        return String.format("DynamicPlayzone: [%s] -> [%s], DamageSettings: %s, ResizeTarget: %s", min, max, getDamageOptions(), target);
    }

    public static final class ResizeTarget {
        private final Position2 nextMin, nextMax;
        private final DamageOptions newDamageOptions;
        private final int resizeTime;
        private final int resizeDelay;
        private int resizeTimer;
        private int startTimer;

        public ResizeTarget(Position2 nextMin, Position2 nextMax, DamageOptions newDamageOptions, int resizeTime, int resizeDelay) {
            this.nextMin = nextMin;
            this.nextMax = nextMax;
            this.newDamageOptions = newDamageOptions;
            this.resizeTime = resizeTime;
            this.resizeDelay = resizeDelay;
        }

        public int resizeTimeTotal() {
            return resizeTime;
        }

        public int resizeTimeRemain() {
            return resizeTime - resizeTimer;
        }

        public int resizeDelayTotal() {
            return resizeDelay;
        }

        public int resizeDelayRemain() {
            return resizeDelay - startTimer;
        }

        public boolean completeResizeDelay() {
            return startTimer >= resizeDelay;
        }

        public void tick() {
            if (startTimer < resizeDelay) {
                ++startTimer;
                return;
            }
            if (resizeTimer < resizeTime) {
                ++resizeTimer;
            }
        }

        public boolean isCompleted() {
            return resizeTimer >= resizeTime;
        }

        public Position2 getAdjustedMinPosition(Position2 current, float partialTicks) {
            return getAdjustedPosition(current, nextMin, this.getResizeProgress(partialTicks));
        }

        public Position2 getAdjustedMaxPosition(Position2 current, float partialTicks) {
            return getAdjustedPosition(current, nextMax, this.getResizeProgress(partialTicks));
        }

        public boolean isWithinTargetArea(EntityPlayer player) {
            double x = player.posX;
            double z = player.posZ;
            Position2 currentPos = new Position2(x, z);
            Position2 min = this.getAdjustedPosition(currentPos, nextMin, 1.0F);
            Position2 max = this.getAdjustedPosition(currentPos, nextMax, 1.0F);
            return x > min.getX() && x < max.getX() && z > min.getZ() && z < max.getZ();
        }

        private Position2 getAdjustedPosition(Position2 current, Position2 target, float resizeProgress) {
            if (resizeProgress <= 0.0F) {
                return current;
            }
            if (resizeProgress >= 1.0F) {
                return target;
            }
            double xDiff = target.getX() - current.getX();
            double zDiff = target.getZ() - current.getZ();
            return new Position2(current.getX() + xDiff * resizeProgress, current.getZ() + zDiff * resizeProgress);
        }

        private float getResizeProgress(float partialTicks) {
            if (startTimer < resizeDelay) {
                return 0.0F;
            }
            if (resizeTimer >= resizeTime) {
                return 1.0F;
            }
            int prev = Math.max(0, resizeTimer - 1);
            float f0 = prev / (float) resizeTime;
            float f1 = resizeTimer / (float) resizeTime;
            return PUBGMCUtil.interpolate(f0, f1, partialTicks);
        }

        private NBTTagCompound serialize() {
            NBTTagCompound nbt = new NBTTagCompound();
            nbt.setTag("nextMin", nextMin.toNbt());
            nbt.setTag("nextMax", nextMax.toNbt());
            if (newDamageOptions != null) {
                nbt.setTag("damageOpt", newDamageOptions.toNbt());
            }
            nbt.setInteger("resizeTime", resizeTime);
            nbt.setInteger("delayTime", resizeDelay);
            nbt.setInteger("resizeTimer", resizeTimer);
            nbt.setInteger("delayTimer", startTimer);
            return nbt;
        }

        private static ResizeTarget deserialize(NBTTagCompound nbt) {
            Position2 nextMin = new Position2(nbt.getCompoundTag("nextMin"));
            Position2 nextMax = new Position2(nbt.getCompoundTag("nextMax"));
            DamageOptions options = null;
            if (nbt.hasKey("damageOpt", Constants.NBT.TAG_COMPOUND)) {
                options = DamageOptions.fromNbt(nbt.getCompoundTag("damageOpt"));
            }
            int resizeTime = nbt.getInteger("resizeTime");
            int delayTime = nbt.getInteger("delayTime");
            int resizeTimer = nbt.getInteger("resizeTimer");
            int delayTimer = nbt.getInteger("delayTimer");
            ResizeTarget target = new ResizeTarget(nextMin, nextMax, options, resizeTime, delayTime);
            target.resizeTimer = resizeTimer;
            target.startTimer = delayTimer;
            return target;
        }

        @Override
        public String toString() {
            return String.format("[Bounds: [%s] -> [%s], DamageSettings: %s]", nextMin, nextMax, newDamageOptions);
        }
    }

    @FunctionalInterface
    public interface ResizeCallback {
        void onResizeChange(DynamicPlayzone playzone, World world);
    }

    public static final class Serializer implements PlayzoneSerializer<DynamicPlayzone> {

        @Override
        public NBTTagCompound serializePlayzone(DynamicPlayzone playzone) {
            NBTTagCompound nbt = new NBTTagCompound();
            nbt.setTag("min", playzone.min.toNbt());
            nbt.setTag("max", playzone.max.toNbt());
            nbt.setTag("opt", playzone.getDamageOptions().toNbt());
            nbt.setBoolean("visible", playzone.isVisible());
            if (playzone.target != null) {
                nbt.setTag("target", playzone.target.serialize());
            }
            return nbt;
        }

        @Override
        public DynamicPlayzone deserializePlayzone(NBTTagCompound nbt) {
            Position2 min = new Position2(nbt.getCompoundTag("min"));
            Position2 max = new Position2(nbt.getCompoundTag("max"));
            DamageOptions options = DamageOptions.fromNbt(nbt.getCompoundTag("opt"));
            boolean visible = nbt.getBoolean("visible");
            DynamicPlayzone playzone = new DynamicPlayzone(options, min, max);
            playzone.setVisible(visible);
            if (nbt.hasKey("target", Constants.NBT.TAG_COMPOUND)) {
                ResizeTarget target = ResizeTarget.deserialize(nbt.getCompoundTag("target"));
                playzone.setTarget(target);
            }
            return playzone;
        }
    }
}

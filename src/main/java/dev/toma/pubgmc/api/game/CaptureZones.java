package dev.toma.pubgmc.api.game;

import dev.toma.pubgmc.api.game.util.CaptureStatus;
import dev.toma.pubgmc.util.helper.SerializationHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;

import javax.annotation.Nullable;

public interface CaptureZones {

    @Nullable
    CaptureData getCapturePointData(BlockPos pos);

    @Nullable
    @Deprecated
    CaptureData getEntityCaptureData(Entity entity);

    boolean shouldCaptureOrDefend(BlockPos pos, EntityLivingBase entity);

    class CaptureData {

        private CaptureStatus status;
        private int background;
        private int foreground;
        private float captureProgress;

        public CaptureData(CaptureStatus status, int background, int foreground, float captureProgress) {
            this.status = status;
            this.background = background;
            this.foreground = foreground;
            this.captureProgress = captureProgress;
        }

        public CaptureData(NBTTagCompound nbt) {
            this.status = SerializationHelper.enumByIndex(nbt.getInteger("status"), CaptureStatus.class);
            this.background = nbt.getInteger("bg");
            this.foreground = nbt.getInteger("fg");
            this.captureProgress = nbt.getFloat("progress");
        }

        public void setStatus(CaptureStatus status) {
            this.status = status;
        }

        public CaptureStatus getStatus() {
            return status;
        }

        public void setBackground(int background) {
            this.background = background;
        }

        public int getBackground() {
            return background;
        }

        public void setForeground(int foreground) {
            this.foreground = foreground;
        }

        public int getForeground() {
            return foreground;
        }

        public void setCaptureProgress(float captureProgress) {
            this.captureProgress = MathHelper.clamp(captureProgress, 0.0F, 1.0F);
        }

        public float getCaptureProgress() {
            return captureProgress;
        }

        public NBTTagCompound serialize() {
            NBTTagCompound nbt = new NBTTagCompound();
            nbt.setInteger("status", status.ordinal());
            nbt.setInteger("bg", background);
            nbt.setInteger("fg", foreground);
            nbt.setFloat("progress", captureProgress);
            return nbt;
        }
    }
}

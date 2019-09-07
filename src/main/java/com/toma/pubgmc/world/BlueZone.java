package com.toma.pubgmc.world;

import com.toma.pubgmc.Pubgmc;
import com.toma.pubgmc.api.Game;
import com.toma.pubgmc.util.game.ZoneSettings;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.util.INBTSerializable;
import org.apache.commons.lang3.tuple.MutablePair;
import org.apache.commons.lang3.tuple.Pair;

public final class BlueZone implements INBTSerializable<NBTTagCompound> {

    private Game game;
    private ZoneSettings settings;
    public MutablePair<BlockPos, BlockPos> currentCorners;
    public Pair<BlockPos, BlockPos> nextCorners;
    public int currentStage;
    public int diameter;

    private boolean shrinking;
    private float shrinkX, shrinkZ, shrinkXn, shrinkZn;

    public BlueZone(ZoneSettings settings, Game game) {
        this.settings = settings;
        BlockPos center = game.gameData.getMapCenter();
        int offset = game.gameData.getMapSize()/2;
        this.diameter = offset;
        this.currentCorners = new MutablePair<>(new BlockPos(center.getX()-offset, 256, center.getZ()-offset), new BlockPos(center.getX()+offset, 256, center.getZ()+offset));
    }

    public void notifyFirstZoneCreation() {
        if(nextCorners == null) {
            ++currentStage;
            calculateNextZone();
        }
    }

    public void shrink() {
        if(settings.isStatic) {
            Pubgmc.logger.warn("Attempted to shrink bluezone which has static size!");
            return;
        } else {
            // first zone
            if(nextCorners == null) {
                Pubgmc.logger.error("Cannot shrink zone, since it doesn't know where to shrink! Call 'BlueZone::notifyFirstZoneCreation' first!");
                return;
            }
            shrinking = true;
        }
    }

    public void bluezoneTick(World world) {
        this.damagePlayersOutsideZone();
        if(shrinking) {
            this.shrinkCurrentZone();
        }
    }

    public boolean isShrinking() {
        return shrinking;
    }

    @Override
    public NBTTagCompound serializeNBT() {
        NBTTagCompound nbt = new NBTTagCompound();
        nbt.setTag("settings", settings.serializeNBT());
        return nbt;
    }

    @Override
    public void deserializeNBT(NBTTagCompound nbt) {
        settings.deserializeNBT(nbt.getCompoundTag("settings"));
    }

    protected void shrinkZonePartially() {
        final float baseSpeed = settings.speedModifier;
    }

    protected void damagePlayersOutsideZone() {

    }

    protected void onShrinkingFinished() {
        shrinking = false;
        if(currentStage < 7) {
            ++currentStage;
            this.calculateNextZone();
        } else {
            BlockPos prevStart = currentCorners.getLeft();
            BlockPos prevEnd = currentCorners.getRight();
            int xCenter = (prevEnd.getX() - prevStart.getX()) / 2;
            int zCenter = (prevEnd.getZ() - prevStart.getZ()) / 2;
            BlockPos centered = new BlockPos(xCenter, 256, zCenter);
            BlockPos centerStartModified = new BlockPos(centered.getX() - 0.25, 256, centered.getZ() - 0.25);
            BlockPos centerEndModified = new BlockPos(centered.getX() + 0.25, 256, centered.getZ() + 0.25);
            nextCorners = new MutablePair<>(centerStartModified, centerEndModified);
        }
    }

    private NBTTagCompound cornersToNBT(Pair<BlockPos, BlockPos> pair) {
        NBTTagCompound nbt = new NBTTagCompound();
        nbt.setTag("left", NBTUtil.createPosTag(pair.getLeft()));
        nbt.setTag("right", NBTUtil.createPosTag(pair.getRight()));
        return nbt;
    }

    private Pair<BlockPos, BlockPos> cornersFromNBT(NBTTagCompound nbt) {
        BlockPos left = NBTUtil.getPosFromTag(nbt.getCompoundTag("left"));
        BlockPos right = NBTUtil.getPosFromTag(nbt.getCompoundTag("right"));
        return new MutablePair<>(left, right);
    }

    private void calculateNextZone() {
        int newDiameter = game.gameData.getMapSize() / (2^currentStage);
        BlockPos startPoint = currentCorners.getLeft();
        BlockPos endPoint = currentCorners.getRight();
        int xMax = endPoint.getX() - startPoint.getX() - newDiameter / 2;
        int zMax = endPoint.getZ() - startPoint.getZ() - newDiameter / 2;
        int x = Pubgmc.rng().nextInt(xMax);
        int z = Pubgmc.rng().nextInt(zMax);
        BlockPos newStartPoint = new BlockPos(startPoint.getX() + x, 256, startPoint.getZ() + z);
        BlockPos newEndPoint = new BlockPos(newStartPoint.getX()+newDiameter, 256, newStartPoint.getZ()+newDiameter);
        nextCorners = new MutablePair<>(newStartPoint, newEndPoint);
        this.calculateShrinkModifiers(x, z, xMax, zMax);
    }

    private void shrinkCurrentZone() {
        currentCorners.setLeft(new BlockPos(currentCorners.getLeft().getX() + shrinkX, 256, currentCorners.getLeft().getZ() + shrinkZ));
        currentCorners.setRight(new BlockPos(currentCorners.getRight().getX() - shrinkXn, 256, currentCorners.getRight().getZ() - shrinkZn));
        if(hasZoneArrivedToFinalPos()) {
            this.onShrinkingFinished();
        }
    }

    private boolean hasZoneArrivedToFinalPos() {
        BlockPos moving = currentCorners.getLeft();
        BlockPos next = nextCorners.getLeft();
        return Math.abs(next.getX() - moving.getX()) < 1 || Math.abs(next.getZ() - moving.getZ()) < 1;
    }

    private void calculateShrinkModifiers(int xOffset, int zOffset, int xOffsetMax, int zOffsetMax) {
        int mode = 0;
        boolean isStartXDistLonger = (xOffsetMax / 2) - xOffset >= 0;
        boolean isStartZDistLonger = (zOffsetMax / 2) - zOffset >= 0;
        boolean isXDistLonger = (xOffsetMax / 2) - xOffset >= (zOffsetMax / 2) - zOffset;
        // capital == longest dist
        // x z -x -z
        /*if(isStartXDistLonger) {
            if(isXDistLonger) {
                // X z -x -z

            } else {
                // x Z -x -z
            }
        }*/

        shrinkX = settings.speedModifier;
        shrinkZ = shrinkX * ((float)(zOffset/xOffset));
        shrinkXn = shrinkX * ((float)((xOffsetMax - xOffset) / xOffset));
        shrinkZn = shrinkX * ((float)((zOffsetMax - zOffset) / xOffset));
    }
}

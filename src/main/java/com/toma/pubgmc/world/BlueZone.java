package com.toma.pubgmc.world;

import com.toma.pubgmc.Pubgmc;
import com.toma.pubgmc.api.Game;
import com.toma.pubgmc.common.capability.IGameData;
import com.toma.pubgmc.util.game.ZoneSettings;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.apache.commons.lang3.tuple.MutablePair;
import org.apache.commons.lang3.tuple.Pair;

public final class BlueZone {

    private Game game;
    private IGameData gameData;
    private ZoneSettings settings;
    public Pair<BlockPos, BlockPos> currentCorners;
    public Pair<BlockPos, BlockPos> nextCorners;
    public int currentStage;
    public int diameter;

    private boolean shrinking;
    private float shrinkX, shrinkZ, shrinkXn, shrinkZn;

    public BlueZone(ZoneSettings settings, IGameData gameData) {
        this.settings = settings;
        this.gameData = gameData;
        if(gameData != null) {
            this.game = gameData.getCurrentGame();
            BlockPos center = gameData.getMapCenter();
            int offset = gameData.getMapSize()/2;
            this.diameter = offset;
            this.currentCorners = new MutablePair<>(new BlockPos(center.getX()-offset, 256, center.getZ()-offset), new BlockPos(center.getX()+offset, 256, center.getZ()+offset));
        }
    }

    public void notifyFirstZoneCreation(World world) {
        if(nextCorners == null) {
            ++currentStage;
            calculateNextZone(world);
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
        if(gameData == null) {
            this.gameData = world.getCapability(IGameData.GameDataProvider.GAMEDATA, null);
            this.game = gameData.getCurrentGame();
            BlockPos center = gameData.getMapCenter();
            int offset = gameData.getMapSize()/2;
            this.diameter = offset;
            this.currentCorners = new MutablePair<>(new BlockPos(center.getX()-offset, 256, center.getZ()-offset), new BlockPos(center.getX()+offset, 256, center.getZ()+offset));
        }
        this.damagePlayersOutsideZone();
        if(shrinking) {
            this.shrinkCurrentZone(world);
        }
    }

    public boolean isShrinking() {
        return shrinking;
    }

    public NBTTagCompound serializeNBT() {
        NBTTagCompound nbt = new NBTTagCompound();
        nbt.setTag("settings", settings.serializeNBT());
        nbt.setInteger("stage", currentStage);
        nbt.setBoolean("shrinking", shrinking);
        nbt.setTag("current", cornersToNBT(currentCorners));
        if(nextCorners != null)
            nbt.setTag("planned", cornersToNBT(nextCorners));
        return nbt;
    }

    public static BlueZone fromNBT(NBTTagCompound nbt) {
        BlueZone zone = new BlueZone(null, null);
        zone.settings = new ZoneSettings();
        zone.settings.deserializeNBT(nbt.getCompoundTag("settings"));
        zone.currentStage = nbt.getInteger("stage");
        zone.shrinking = nbt.getBoolean("shrinking");
        zone.currentCorners = cornersFromNBT(nbt.getCompoundTag("current"));
        if(nbt.hasKey("planned")) {
            zone.nextCorners = cornersFromNBT(nbt.getCompoundTag("planned"));
        }
        return zone;
    }

    protected void shrinkZonePartially() {
        final float baseSpeed = settings.speedModifier;
    }

    protected void damagePlayersOutsideZone() {

    }

    protected void onShrinkingFinished(World world) {
        shrinking = false;
        if(currentStage < 7) {
            ++currentStage;
            this.calculateNextZone(world);
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

    private static NBTTagCompound cornersToNBT(Pair<BlockPos, BlockPos> pair) {
        NBTTagCompound nbt = new NBTTagCompound();
        nbt.setTag("left", NBTUtil.createPosTag(pair.getLeft()));
        nbt.setTag("right", NBTUtil.createPosTag(pair.getRight()));
        return nbt;
    }

    private static Pair<BlockPos, BlockPos> cornersFromNBT(NBTTagCompound nbt) {
        BlockPos left = NBTUtil.getPosFromTag(nbt.getCompoundTag("left"));
        BlockPos right = NBTUtil.getPosFromTag(nbt.getCompoundTag("right"));
        return new MutablePair<>(left, right);
    }

    private void calculateNextZone(World world) {
        int newDiameter = game.getGameData(world).getMapSize() / (2^currentStage);
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

    private void shrinkCurrentZone(World world) {
        currentCorners = new MutablePair<>(new BlockPos(currentCorners.getLeft().getX() + shrinkX, 256, currentCorners.getLeft().getZ() + shrinkZ), new BlockPos(currentCorners.getRight().getX() - shrinkXn, 256, currentCorners.getRight().getZ() - shrinkZn));
        if(hasZoneArrivedToFinalPos()) {
            this.onShrinkingFinished(world);
        }
    }

    private boolean hasZoneArrivedToFinalPos() {
        BlockPos moving = currentCorners.getLeft();
        BlockPos next = nextCorners.getLeft();
        return Math.abs(next.getX() - moving.getX()) < 1 || Math.abs(next.getZ() - moving.getZ()) < 1;
    }

    private void calculateShrinkModifiers(int xOffset, int zOffset, int xOffsetMax, int zOffsetMax) {
        BlockPos currentX = currentCorners.getLeft();
        BlockPos currentZ = currentCorners.getRight();
        BlockPos nextX = nextCorners.getLeft();
        BlockPos nextZ = nextCorners.getRight();
        float xMax = currentZ.getX() - currentX.getX();
        float zMax = currentZ.getZ() - currentX.getZ();
        shrinkX = ((nextX.getX() - currentX.getX())/xMax) * settings.speedModifier;
        shrinkZ = ((nextX.getZ() - currentX.getZ())/zMax) * settings.speedModifier;
        shrinkXn = ((currentZ.getX() - nextX.getX())/xMax) * settings.speedModifier;
        shrinkZn = ((currentZ.getZ() - nextX.getZ())/zMax) * settings.speedModifier;
    }
}

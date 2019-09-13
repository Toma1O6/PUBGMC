package com.toma.pubgmc.world;

import com.toma.pubgmc.Pubgmc;
import com.toma.pubgmc.api.Game;
import com.toma.pubgmc.common.capability.IGameData;
import com.toma.pubgmc.util.game.ZoneSettings;
import com.toma.pubgmc.util.math.ZoneBounds;
import com.toma.pubgmc.util.math.ZonePos;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public final class BlueZone {

    private Game game;
    private IGameData gameData;
    private ZoneSettings settings;
    public ZoneBounds currentBounds;
    public ZoneBounds nextBounds;
    public int currentStage;
    public int diameter;

    private boolean shrinking;
    private double shrinkX, shrinkZ, shrinkXn, shrinkZn;

    public BlueZone(ZoneSettings settings, IGameData gameData) {
        this.settings = settings;
        this.gameData = gameData;
        if(gameData != null) {
            this.game = gameData.getCurrentGame();
            BlockPos center = gameData.getMapCenter();
            int offset = gameData.getMapSize()/2;
            this.diameter = offset;
            this.currentBounds = new ZoneBounds(center.getX() - offset, center.getZ() - offset, center.getX() + offset, center.getZ() + offset);
        }
    }

    public void notifyFirstZoneCreation(World world) {
        if(nextBounds == null) {
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
            if(nextBounds == null) {
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
            this.diameter = gameData.getMapSize();
            this.currentBounds = new ZoneBounds(center.getX() - diameter, center.getZ() - diameter, center.getX() + diameter, center.getZ() + diameter);
        }
        this.damagePlayersOutsideZone();
        if(shrinking) {
            if(currentStage == 7) {
                if(game.getJoinedPlayers().size() < 2) {
                    game.notifyAllPlayers(TextFormatting.ITALIC + "Match finished!");
                    game.stopGame(world);
                }
                return;
            }
            this.shrinkCurrentZone(world);
        }
    }

    public boolean isShrinking() {
        return shrinking;
    }

    public double minX() {
        return currentBounds.min().x;
    }

    public double minZ() {
        return currentBounds.min().z;
    }

    public double maxX() {
        return currentBounds.max().x;
    }

    public double maxZ() {
        return currentBounds.max().z;
    }

    public double getClosestDistance(double x, double z) {
        double startx = x - minX();
        double endx = maxX() - x;
        double startz = z - minZ();
        double endz = maxZ() - z;
        double min = Math.min(startx, endx);
        min = Math.min(min, endx);
        return Math.min(min, startz);
    }

    public double getClosestDistance(Entity entity) {
        return this.getClosestDistance(entity.posX, entity.posZ);
    }

    public NBTTagCompound serializeNBT() {
        NBTTagCompound nbt = new NBTTagCompound();
        nbt.setTag("settings", settings.serializeNBT());
        nbt.setInteger("stage", currentStage);
        nbt.setBoolean("shrinking", shrinking);
        nbt.setTag("current", ZoneBounds.toNBT(currentBounds));
        nbt.setDouble("xMin", shrinkX);
        nbt.setDouble("zMin", shrinkZ);
        nbt.setDouble("xMax", shrinkXn);
        nbt.setDouble("zMax", shrinkZn);
        if(nextBounds != null)
            nbt.setTag("planned", ZoneBounds.toNBT(nextBounds));
        return nbt;
    }

    public static BlueZone fromNBT(NBTTagCompound nbt) {
        BlueZone zone = new BlueZone(null, null);
        zone.settings = new ZoneSettings();
        zone.settings.deserializeNBT(nbt.getCompoundTag("settings"));
        zone.currentStage = nbt.getInteger("stage");
        zone.shrinking = nbt.getBoolean("shrinking");
        zone.currentBounds = ZoneBounds.fromNBT(nbt.getCompoundTag("current"));
        zone.shrinkX = nbt.getDouble("xMin");
        zone.shrinkZ = nbt.getDouble("zMin");
        zone.shrinkXn = nbt.getDouble("xMax");
        zone.shrinkZn = nbt.getDouble("zMax");
        if(nbt.hasKey("planned")) {
            zone.nextBounds = ZoneBounds.fromNBT(nbt.getCompoundTag("planned"));
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
            ZonePos prevStart = currentBounds.min();
            ZonePos prevEnd = currentBounds.max();
            int xCenter = (int)(prevEnd.x - prevStart.x) / 2;
            int zCenter = (int)(prevEnd.z - prevStart.z) / 2;
            ZonePos centered = new ZonePos(xCenter, zCenter);
            ZonePos centerStartModified = new ZonePos(centered.x - 0.25F, centered.z - 0.25F);
            ZonePos centerEndModified = new ZonePos(centered.x + 0.25F, centered.z + 0.25F);
            nextBounds = new ZoneBounds(centerStartModified, centerEndModified);
        }
    }

    // TODO: Always centered option implementation
    private void calculateNextZone(World world) {
        int newDiameter = game.getGameData(world).getMapSize() / (int)(Math.pow(2, currentStage));
        ZonePos startPoint = currentBounds.min();
        ZonePos endPoint = currentBounds.max();
        int xMax = (int)Math.abs((endPoint.x - startPoint.x) - newDiameter * 2);
        int zMax = (int)Math.abs((endPoint.z - startPoint.z) - newDiameter * 2);
        int x = Pubgmc.rng().nextInt(Math.abs(xMax));
        int z = Pubgmc.rng().nextInt(Math.abs(zMax));
        ZonePos newStartPoint = new ZonePos(startPoint.x + x, startPoint.z + z);
        ZonePos newEndPoint = new ZonePos(newStartPoint.x+newDiameter*2, newStartPoint.z+newDiameter*2);
        nextBounds = new ZoneBounds(newStartPoint, newEndPoint);
        this.calculateShrinkModifiers();
    }

    private void shrinkCurrentZone(World world) {
        currentBounds.shrink(shrinkX, shrinkZ, shrinkXn, shrinkZn);
        if(hasZoneArrivedToFinalPos()) {
            this.onShrinkingFinished(world);
        }
    }

    private boolean hasZoneArrivedToFinalPos() {
        ZonePos movingMin = currentBounds.min();
        ZonePos nextMin = nextBounds.min();
        ZonePos movingMax = currentBounds.max();
        ZonePos nextMax = nextBounds.max();
        boolean b0 = movingMin.x >= nextMin.x;
        boolean b1 = movingMin.z >= nextMin.z;
        boolean b2 = movingMax.x <= nextMax.x;
        boolean b3 = movingMax.z <= nextMax.z;
        return b0 && b1 && b2 && b3;
    }

    private void calculateShrinkModifiers() {
        float base = this.settings.speedModifier;
        double d0 = Math.abs(nextBounds.min().x - currentBounds.min().x);
        double d1 = Math.abs(nextBounds.min().z - currentBounds.min().z);
        double d2 = Math.abs(currentBounds.max().x - nextBounds.max().x);
        double d3 = Math.abs(currentBounds.max().z - nextBounds.max().z);
        boolean isXDiffBigger = Math.abs(d2 - d0) >= Math.abs(d3 - d1);
        boolean xs = d0 <= d2;
        boolean zs = d1 <= d3;
        if(isXDiffBigger) {
            if(xs) {
                shrinkX = Math.abs((base * d0) / d2);
                shrinkZ = Math.abs((base * d1) / d2);
                shrinkXn = base;
                shrinkZn = Math.abs((base * d3) / d2);
            } else {
                shrinkX = base;
                shrinkZ = Math.abs((base * d1) / d0);
                shrinkXn = Math.abs((base * d2) / d0);
                shrinkZn = Math.abs((base * d3) / d0);
            }
        } else {
            if(zs) {
                shrinkX = Math.abs((base * d0) / d3);
                shrinkZ = Math.abs((base * d1) / d3);
                shrinkXn = Math.abs((base * d2) / d3);
                shrinkZn = base;
            } else {
                shrinkX = Math.abs((base * d0) / d1);
                shrinkZ = base;
                shrinkXn = Math.abs((base * d2) / d1);
                shrinkZn = Math.abs((base * d3) / d1);
            }
        }
    }
}

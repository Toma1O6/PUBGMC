package com.toma.pubgmc.world;

import com.toma.pubgmc.Pubgmc;
import com.toma.pubgmc.api.Game;
import com.toma.pubgmc.common.capability.IGameData;
import com.toma.pubgmc.init.PMCDamageSources;
import com.toma.pubgmc.util.PUBGMCUtil;
import com.toma.pubgmc.util.game.ZoneSettings;
import com.toma.pubgmc.util.math.ZoneBounds;
import com.toma.pubgmc.util.math.ZonePos;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public final class BlueZone {

    private ZoneSettings settings;
    public ZoneBounds prevBounds;
    public ZoneBounds currentBounds;
    public ZoneBounds nextBounds;
    public int currentStage;
    public BlockPos origin;

    private boolean shrinking;
    private double shrinkX, shrinkZ, shrinkXn, shrinkZn;
    private float currentDamageMultiplier;

    public BlueZone() {
        this(0, new BlockPos(0, 0, 0), ZoneSettings.Builder.create().speed(0.1F).damage(0.1F).build());
    }

    public BlueZone(int mapSize, BlockPos center, ZoneSettings settings) {
        this.settings = settings;
        this.origin = center;
        this.currentBounds = new ZoneBounds(center.getX() - mapSize, center.getZ() - mapSize, center.getX() + mapSize, center.getZ() + mapSize);
        this.prevBounds = new ZoneBounds(currentBounds);
        this.currentDamageMultiplier = settings.damagePerSecond;
    }

    public BlueZone(IGameData gameData, ZoneSettings settings) {
        this(gameData.getMapSize(), gameData.getMapCenter(), settings);
    }

    public void notifyFirstZoneCreation(World world) {
        if(nextBounds == null) {
            calculateNextZone(world);
            ++currentStage;
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
        IGameData gameData = world.getCapability(IGameData.GameDataProvider.GAMEDATA, null);
        Game game = gameData.getCurrentGame();
        this.prevBounds = new ZoneBounds(currentBounds);
        if(game.getGameTimer() % 20 == 0) {
            this.damagePlayersOutsideZone(world);
        }
        if(shrinking) {
            if(currentStage == 7) {
                if(game.getJoinedPlayers().size() < 2) {
                    game.notifyAllPlayers(world, TextFormatting.ITALIC + "Match finished!");
                    game.stopGame(world);
                }
            }
            this.shrinkCurrentZone(world);
        }
    }

    public boolean isShrinking() {
        return shrinking;
    }

    public double minX(double partialTicks) {
        return PUBGMCUtil.interpolate(prevBounds.min().x, currentBounds.min().x, partialTicks);
    }

    public double minZ(double partialTicks) {
        return PUBGMCUtil.interpolate(prevBounds.min().z, currentBounds.min().z, partialTicks);
    }

    public double maxX(double partialTicks) {
        return PUBGMCUtil.interpolate(prevBounds.max().x, currentBounds.max().x, partialTicks);
    }

    public double maxZ(double partialTicks) {
        return PUBGMCUtil.interpolate(prevBounds.max().z, currentBounds.max().z, partialTicks);
    }

    public double getClosestDistance(double x, double z) {
        double startx = x - minX(1.0F);
        double endx = maxX(1.0F) - x;
        double startz = z - minZ(1.0F);
        double endz = maxZ(1.0F) - z;
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
        nbt.setTag("origin", NBTUtil.createPosTag(origin));
        nbt.setInteger("stage", currentStage);
        nbt.setBoolean("shrinking", shrinking);
        nbt.setTag("current", ZoneBounds.toNBT(currentBounds));
        nbt.setTag("prev", ZoneBounds.toNBT(prevBounds == null ? currentBounds : prevBounds));
        nbt.setDouble("xMin", shrinkX);
        nbt.setDouble("zMin", shrinkZ);
        nbt.setDouble("xMax", shrinkXn);
        nbt.setDouble("zMax", shrinkZn);
        if(nextBounds != null)
            nbt.setTag("planned", ZoneBounds.toNBT(nextBounds));
        return nbt;
    }

    public static BlueZone fromNBT(NBTTagCompound nbt) {
        ZoneSettings settings = new ZoneSettings();
        settings.deserializeNBT(nbt.getCompoundTag("settings"));
        BlueZone zone = new BlueZone();
        zone.settings = settings;
        zone.origin = NBTUtil.getPosFromTag(nbt.getCompoundTag("origin"));
        zone.currentStage = nbt.getInteger("stage");
        zone.shrinking = nbt.getBoolean("shrinking");
        zone.currentBounds = ZoneBounds.fromNBT(nbt.getCompoundTag("current"));
        zone.prevBounds = ZoneBounds.fromNBT(nbt.getCompoundTag("prev"));
        zone.shrinkX = nbt.getDouble("xMin");
        zone.shrinkZ = nbt.getDouble("zMin");
        zone.shrinkXn = nbt.getDouble("xMax");
        zone.shrinkZn = nbt.getDouble("zMax");
        zone.currentDamageMultiplier = (float)(zone.settings.damagePerSecond * Math.pow(2, zone.currentStage));
        if(nbt.hasKey("planned")) {
            zone.nextBounds = ZoneBounds.fromNBT(nbt.getCompoundTag("planned"));
        }
        return zone;
    }

    // doesn't care if each player is member of current game or not, as long as he can be damaged, he will be damaged
    // called once per second
    protected void damagePlayersOutsideZone(World world) {
        for (EntityPlayer player : world.playerEntities) {
            boolean inLobby = world.getCapability(IGameData.GameDataProvider.GAMEDATA, null).getLobby().isInLobby(player);
            if (inLobby || player.posX >= minX(1.0F) && player.posX <= maxX(1.0F) && player.posZ >= minZ(1.0F) && player.posZ <= maxZ(1.0f)) {
                continue;
            }
            if (!player.getIsInvulnerable()) {
                player.attackEntityFrom(PMCDamageSources.ZONE, currentDamageMultiplier);
            }
        }
    }

    protected void onShrinkingFinished(World world) {
        shrinking = false;
        currentBounds = new ZoneBounds(nextBounds);
        if(currentStage < 7) {
            this.calculateNextZone(world);
            ++currentStage;
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
        this.currentDamageMultiplier = (float)(settings.damagePerSecond * Math.pow(2, currentStage));
    }

    private void calculateNextZone(World world) {
        float modifier = settings.shrinkModifiers[currentStage];
        int newDiameter = (int)(world.getCapability(IGameData.GameDataProvider.GAMEDATA, null).getMapSize() * modifier);
        System.out.println(newDiameter + " /// " + modifier);
        if(settings.alwaysCentered) {
            ZonePos start = new ZonePos(origin.getX() - newDiameter, origin.getZ() - newDiameter);
            ZonePos end = new ZonePos(origin.getX() + newDiameter, origin.getZ() + newDiameter);
            nextBounds = new ZoneBounds(start, end);
        } else {
            ZonePos startPoint = currentBounds.min();
            ZonePos endPoint = currentBounds.max();
            int xMax = (int)Math.abs((endPoint.x - startPoint.x) - newDiameter * 2);
            int zMax = (int)Math.abs((endPoint.z - startPoint.z) - newDiameter * 2);
            int x = Pubgmc.rng().nextInt(Math.abs(xMax));
            int z = Pubgmc.rng().nextInt(Math.abs(zMax));
            ZonePos newStartPoint = new ZonePos(startPoint.x + x, startPoint.z + z);
            ZonePos newEndPoint = new ZonePos(newStartPoint.x+newDiameter*2, newStartPoint.z+newDiameter*2);
            nextBounds = new ZoneBounds(newStartPoint, newEndPoint);
        }
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

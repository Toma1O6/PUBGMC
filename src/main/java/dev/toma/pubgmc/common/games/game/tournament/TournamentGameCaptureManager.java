package dev.toma.pubgmc.common.games.game.tournament;

import dev.toma.pubgmc.Pubgmc;
import dev.toma.pubgmc.api.game.map.GameMap;
import dev.toma.pubgmc.api.game.map.GameMapPointType;
import dev.toma.pubgmc.api.game.team.TeamManager;
import dev.toma.pubgmc.api.game.util.Team;
import dev.toma.pubgmc.common.games.map.CaptureZonePoint;
import dev.toma.pubgmc.common.games.map.GameMapPoints;
import dev.toma.pubgmc.util.PUBGMCUtil;
import dev.toma.pubgmc.util.helper.GameHelper;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.util.Constants;

import java.util.*;

public final class TournamentGameCaptureManager {

    private CaptureZonePoint captureZone;
    private Team capturingTeam;
    private int capturingTime;

    public void applyCapturePenalty(TournamentGameConfiguration.MatchConfiguration configuration) {
        this.capturingTime = Math.max(0, capturingTime - configuration.hitCapturePenalty);
    }

    public void clearData() {
        Pubgmc.logger.debug("Resetting capture zone data");
        this.captureZone = null;
        this.capturingTeam = null;
        this.capturingTime = 0;
    }

    public void selectRandomCaptureZone(World world, GameMap map) {
        Random random = world.rand;
        List<CaptureZonePoint> captureZonePoints = new ArrayList<>(map.getPoints(GameMapPoints.CAPTURE_ZONE));
        this.captureZone = PUBGMCUtil.randomListElement(captureZonePoints, random);
        Objects.requireNonNull(captureZone);
        Pubgmc.logger.debug("Created capture zone at {}", captureZone.getPointPosition());
    }

    public void doParticles(World world) {
        if (captureZone == null) {
            return;
        }
        BlockPos pos = captureZone.getPointPosition();
        Random random = world.rand;
        double multiplier = 0.05;
        double x = (random.nextDouble() - random.nextDouble()) * multiplier;
        double z = (random.nextDouble() - random.nextDouble()) * multiplier;
        world.spawnParticle(EnumParticleTypes.CLOUD, true, pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5, x, 0.1, z);
    }

    public void updateCapture(WorldServer world, TournamentTeamManager teamManager, TournamentMatch match) {
        Team oldTeam = capturingTeam;
        int oldCapturingTime = capturingTime;
        List<Entity> entityList = teamManager.getActiveMatchEntities(world, match);
        boolean isEmpty = true;
        int captureProgress = 0;
        for (Entity entity : entityList) {
            if (captureZone.isWithin(entity) && entity.isEntityAlive()) {
                isEmpty = false;
                Team entityTeam = teamManager.getEntityTeam(entity);
                if (capturingTeam != null && !capturingTeam.equals(entityTeam)) {
                    this.capturingTeam = null;
                    this.capturingTime = 0;
                    captureProgress = 0;
                } else if (Objects.equals(capturingTeam, entityTeam)) {
                    captureProgress = 1;
                } else {
                    this.capturingTeam = entityTeam;
                    this.capturingTime = 0;
                    captureProgress = 0;
                }
            }
        }
        this.capturingTime += captureProgress;
        if (isEmpty) {
            this.capturingTime = 0;
            this.capturingTeam = null;
        }

        if (!Objects.equals(oldTeam, capturingTeam) || oldCapturingTime != capturingTime) {
            GameHelper.requestClientGameDataSynchronization(world);
        }
    }

    public CaptureZonePoint getCaptureZone() {
        return captureZone;
    }

    public int getCapturingTime() {
        return capturingTime;
    }

    public Team getCapturingTeam() {
        return capturingTeam;
    }

    public NBTTagCompound serialize() {
        NBTTagCompound nbt = new NBTTagCompound();
        if (captureZone != null) {
            nbt.setTag("captureZone", GameMapPointType.serializePointData(captureZone));
        }
        if (capturingTeam != null) {
            nbt.setUniqueId("capturingTeam", capturingTeam.getTeamId());
            nbt.setInteger("captureTime", capturingTime);
        }
        return nbt;
    }

    public void deserialize(NBTTagCompound nbt, TeamManager manager, World world) {
        capturingTime = nbt.getInteger("captureTime");
        if (nbt.hasKey("capturingTeamMost", Constants.NBT.TAG_LONG)) {
            UUID captureTeamId = nbt.getUniqueId("capturingTeam");
            capturingTeam = manager.getTeamById(captureTeamId);
        }
        captureZone = nbt.hasKey("captureZone") ? GameMapPointType.deserializePointData(nbt.getCompoundTag("captureZone"), GameHelper.getActiveGameMap(world)) : null;
    }
}

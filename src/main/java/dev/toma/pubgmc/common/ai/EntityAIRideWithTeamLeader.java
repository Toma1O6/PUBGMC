package dev.toma.pubgmc.common.ai;

import dev.toma.pubgmc.api.capability.GameDataProvider;
import dev.toma.pubgmc.api.game.Game;
import dev.toma.pubgmc.api.game.team.TeamGame;
import dev.toma.pubgmc.api.game.team.TeamManager;
import dev.toma.pubgmc.api.game.util.Team;
import dev.toma.pubgmc.common.entity.controllable.EntityVehicle;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.world.WorldServer;

public class EntityAIRideWithTeamLeader extends EntityAIBase {

    private final EntityLiving entity;
    private final float moveSpeed;
    private Entity teamLeader;

    private int updateInterval;

    public EntityAIRideWithTeamLeader(EntityLiving livingEntity) {
        this(livingEntity, 1.0F);
    }

    public EntityAIRideWithTeamLeader(EntityLiving livingEntity, float moveSpeed) {
        this.entity = livingEntity;
        this.moveSpeed = moveSpeed;
        setMutexBits(3);
    }

    @Override
    public boolean isInterruptible() {
        return false;
    }

    @Override
    public boolean shouldExecute() {
        updateTeamLeader();
        return hasTlDifferentVehicle();
    }

    @Override
    public boolean shouldContinueExecuting() {
        return (teamLeader != null && teamLeader.getRidingEntity() == entity.getRidingEntity()) || hasTlDifferentVehicle();
    }

    @Override
    public void startExecuting() {
        tryRideOrMove();
    }

    @Override
    public void resetTask() {
        entity.getNavigator().clearPath();
        entity.dismountRidingEntity();
        entity.setSprinting(false);
    }

    @Override
    public void updateTask() {
        if (--updateInterval <= 0) {
            updateInterval = 15;
            updateTeamLeader();
            if (this.hasTlDifferentVehicle()) {
                entity.dismountRidingEntity();
                tryRideOrMove();
            }
        }
    }

    private boolean hasTlDifferentVehicle() {
        if (teamLeader != null && teamLeader.isEntityAlive()) {
            Entity teamLeaderVehicle = teamLeader.getRidingEntity();
            Entity myVehicle = entity.getRidingEntity();
            return teamLeaderVehicle != myVehicle && teamLeaderVehicle instanceof EntityVehicle && ((EntityVehicle) teamLeaderVehicle).canFitPassenger(entity);
        }
        return false;
    }

    private void tryRideOrMove() {
        Entity vehicle = teamLeader.getRidingEntity();
        double distanceToVehicle = entity.getDistanceSq(vehicle.posX, vehicle.posY, vehicle.posZ);
        if (distanceToVehicle <= 16) {
            entity.startRiding(vehicle);
        } else {
            entity.getNavigator().tryMoveToXYZ(vehicle.posX, vehicle.posY, vehicle.posZ, moveSpeed);
            if (moveSpeed > 1.0F) {
                entity.setSprinting(true);
            }
        }
    }

    private void updateTeamLeader() {
        GameDataProvider.getGameData(entity.world).ifPresent(data -> {
            Game<?> game = data.getCurrentGame();
            if (game.isStarted() && game instanceof TeamGame<?>) {
                TeamGame<?> teamGame = (TeamGame<?>) game;
                TeamManager teamManager = teamGame.getTeamManager();
                Team team = teamManager.getEntityTeam(entity);
                if (team != null && !team.isTeamLeader(entity)) {
                    Team.Member teamLead = team.getTeamLeader();
                    WorldServer server = (WorldServer) entity.world;
                    teamLeader = teamLead.getEntity(server);
                }
            }
        });
    }
}

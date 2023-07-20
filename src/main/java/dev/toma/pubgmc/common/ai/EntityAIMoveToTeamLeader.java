package dev.toma.pubgmc.common.ai;

import dev.toma.pubgmc.api.game.util.Team;
import dev.toma.pubgmc.util.helper.GameHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.world.WorldServer;

public class EntityAIMoveToTeamLeader extends EntityAIBase {

    private final EntityLiving living;
    private final int maxAllowedDistance;
    private final float moveSpeed;

    private Entity teamLeader;
    private int delay;

    public EntityAIMoveToTeamLeader(EntityLiving entity, int maxAllowedDistance, float moveSpeed) {
        this.living = entity;
        this.maxAllowedDistance = maxAllowedDistance;
        this.moveSpeed = moveSpeed;
    }

    @Override
    public boolean shouldExecute() {
        return GameHelper.getEntityTeam(living).map(team -> {
            if (team.isTeamLeader(living)) {
                return false;
            }
            Team.Member teamLeaderMember = team.getTeamLeader();
            if (teamLeaderMember != null && !living.world.isRemote) {
                WorldServer server = (WorldServer) living.world;
                teamLeader = teamLeaderMember.getEntity(server);
                if (teamLeader == null) {
                    return false;
                }
                double distance = living.getDistanceSq(teamLeader);
                return distance > (maxAllowedDistance * maxAllowedDistance);
            }
            return false;
        }).orElse(false);
    }

    @Override
    public boolean shouldContinueExecuting() {
        int quarterDistance = maxAllowedDistance / 4;
        return teamLeader != null && teamLeader.isEntityAlive() && living.getDistanceSq(teamLeader) > (quarterDistance * quarterDistance);
    }

    @Override
    public void startExecuting() {
        delay = 0;
        if (moveSpeed > 1.0F) {
            living.setSprinting(true);
        }
    }

    @Override
    public void resetTask() {
        teamLeader = null;
        living.getNavigator().clearPath();
        living.setSprinting(false);
    }

    @Override
    public void updateTask() {
        if (--delay <= 0) {
            delay = 10;
            living.getNavigator().tryMoveToEntityLiving(teamLeader, moveSpeed);
        }
    }
}

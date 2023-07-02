package dev.toma.pubgmc.common.entity.bot.ai;

import dev.toma.pubgmc.api.game.util.Team;
import dev.toma.pubgmc.util.helper.GameHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.world.WorldServer;

public class EntityAIMoveToTeamLeader extends EntityAIBase {

    private final EntityLiving living;
    private final int maxAllowedDistance;

    private Entity teamLeader;
    private int delay;

    public EntityAIMoveToTeamLeader(EntityLiving entity, int maxAllowedDistance) {
        this.living = entity;
        this.maxAllowedDistance = maxAllowedDistance;
    }

    @Override
    public boolean shouldExecute() {
        return GameHelper.getEntityTeam(living).map(team -> {
            if (team.isTeamLeader(living)) {
                return false;
            }
            Team.Member teamLeaderMember = team.getTeamLeader();
            if (!living.world.isRemote) {
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
    }

    @Override
    public void resetTask() {
        teamLeader = null;
    }

    @Override
    public void updateTask() {
        if (--delay <= 0) {
            delay = 10;
            living.getNavigator().tryMoveToEntityLiving(teamLeader, 1.0D);
        }
    }
}

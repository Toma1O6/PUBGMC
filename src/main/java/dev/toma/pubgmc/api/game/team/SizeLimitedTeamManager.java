package dev.toma.pubgmc.api.game.team;

import net.minecraft.entity.Entity;

public class SizeLimitedTeamManager extends SimpleTeamManager {

    protected final int teamSizeLimit;

    public SizeLimitedTeamManager(int teamSizeLimit) {
        this.teamSizeLimit = teamSizeLimit;
    }

    @Override
    public boolean canJoinTeam(Entity entity, Team team) {
        int members = team.getSize();
        return super.canJoinTeam(entity, team) && members < teamSizeLimit;
    }
}

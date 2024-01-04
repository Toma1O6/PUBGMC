package dev.toma.pubgmc.common.games.game.tournament;

import dev.toma.pubgmc.api.game.team.SizeLimitedTeamManager;
import dev.toma.pubgmc.api.game.util.Team;
import dev.toma.pubgmc.common.games.util.TeamType;
import dev.toma.pubgmc.util.Pair;
import net.minecraft.entity.Entity;
import net.minecraft.world.WorldServer;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TournamentTeamManager extends SizeLimitedTeamManager {

    public TournamentTeamManager(int teamSizeLimit) {
        super(teamSizeLimit);
    }

    public List<Entity> getActiveMatchEntities(WorldServer world, TournamentMatch match) {
        Team red = match.getTeam(TeamType.RED);
        Team blue = match.getTeam(TeamType.BLUE);
        List<Entity> entities = new ArrayList<>();
        red.getActiveMemberStream().map(member -> member.getEntity(world)).filter(Objects::nonNull).forEach(entities::add);
        blue.getActiveMemberStream().map(member -> member.getEntity(world)).filter(Objects::nonNull).forEach(entities::add);
        return entities;
    }

    public Pair<Team, TeamType> getTeamFromActiveMatch(Entity entity, TournamentMatch match) {
        if (match.getTeam(TeamType.RED).getMember(entity.getUniqueID()).isPresent()) {
            return Pair.of(match.getTeam(TeamType.RED), TeamType.RED);
        } else if (match.getTeam(TeamType.BLUE).getMember(entity.getUniqueID()).isPresent()) {
            return Pair.of(match.getTeam(TeamType.BLUE), TeamType.BLUE);
        }
        return null;
    }
}

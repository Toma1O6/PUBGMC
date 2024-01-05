package dev.toma.pubgmc.common.games.game.tournament;

import dev.toma.pubgmc.api.game.team.SizeLimitedTeamManager;
import dev.toma.pubgmc.api.game.team.TeamRelations;
import dev.toma.pubgmc.api.game.util.Team;
import dev.toma.pubgmc.common.games.util.TeamType;
import dev.toma.pubgmc.util.Pair;
import net.minecraft.entity.Entity;
import net.minecraft.world.WorldServer;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

public class TournamentTeamManager extends SizeLimitedTeamManager {

    private TournamentMatch match;

    public TournamentTeamManager(int teamSizeLimit) {
        super(teamSizeLimit);
    }

    public List<UUID> getRegisteredMembersOfType(Team.MemberType type) {
        return getTeams().stream()
                .flatMap(team -> team.getAllMembers().values().stream().filter(member -> member.getMemberType() == type))
                .map(Team.Member::getId)
                .collect(Collectors.toList());
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

    public void setMatch(@Nullable TournamentMatch match) {
        this.match = match;
    }

    @Override
    public TeamRelations getTeamRelationship(@Nullable Team team1, @Nullable Team team2) {
        if (match == null) {
            return TeamRelations.NEUTRAL;
        }
        if (team1 == null || team2 == null) {
            return TeamRelations.ENEMY;
        }
        if (team1.equals(team2)) {
            return TeamRelations.FRIENDLY;
        } else if (match.containsTeam(team1) && match.containsTeam(team2)) {
            return TeamRelations.ENEMY;
        } else {
            return TeamRelations.NEUTRAL;
        }
    }
}

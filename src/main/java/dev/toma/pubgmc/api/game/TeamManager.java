package dev.toma.pubgmc.api.game;

import dev.toma.pubgmc.api.game.util.Team;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

import java.util.Collection;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Stream;

public interface TeamManager {

    Collection<Team> getTeams();

    Team getTeamById(UUID teamId);

    void removeTeam(UUID teamId);

    Team getEntityTeamByEntityId(UUID entityId);

    Team createNewTeam(Entity entity);

    void join(Team team, Entity entity);

    void eliminate(Entity entity);

    void disbandAndTransferMembers(Team team);

    default Team getEntityTeam(Entity entity) {
        return getEntityTeamByEntityId(entity.getUniqueID());
    }

    default boolean shouldRemoveFreshlyLoadedEntity(Entity entity) {
        return true;
    }

    default Stream<Entity> getAllActiveEntities(WorldServer worldServer) {
        return getTeams().stream()
                .flatMap(team -> team.getAllMembers().values().stream())
                .map(member -> member.getEntity(worldServer))
                .filter(Objects::nonNull);
    }

    default Stream<EntityPlayer> getAllActivePlayers(World world) {
        return getTeams().stream()
                .flatMap(team -> team.getAllMembers().values().stream())
                .filter(member -> member.getMemberType().isPlayer())
                .map(member -> member.getPlayer(world))
                .filter(Objects::nonNull);
    }
}

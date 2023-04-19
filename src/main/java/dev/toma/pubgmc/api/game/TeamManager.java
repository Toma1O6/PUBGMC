package dev.toma.pubgmc.api.game;

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

    Team getEntityTeam(Entity entity);

    void createNewTeam(Entity entity);

    void eliminate(Entity entity);

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

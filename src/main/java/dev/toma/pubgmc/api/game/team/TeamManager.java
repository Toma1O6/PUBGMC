package dev.toma.pubgmc.api.game.team;

import dev.toma.pubgmc.api.game.util.Team;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.util.INBTSerializable;

import java.util.Collection;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Stream;

public interface TeamManager extends INBTSerializable<NBTTagCompound> {

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

    default boolean canJoinTeam(Entity entity, Team team) {
        return !team.getAllMembers().containsKey(entity.getUniqueID());
    }

    default TeamRelations getTeamRelationship(Team team1, Team team2) {
        return team1.getTeamId().equals(team2.getTeamId()) ? TeamRelations.FRIENDLY : TeamRelations.ENEMY;
    }

    default Stream<Entity> getAllActiveEntities(WorldServer worldServer) {
        return getTeams().stream()
                .flatMap(Team::getActiveMemberStream)
                .map(member -> member.getEntity(worldServer))
                .filter(Objects::nonNull);
    }

    default Stream<EntityPlayer> getAllActivePlayers(World world) {
        return getTeams().stream()
                .flatMap(Team::getActiveMemberStream)
                .filter(member -> member.getMemberType().isPlayer())
                .map(member -> member.getPlayer(world))
                .filter(Objects::nonNull);
    }
}

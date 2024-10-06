package dev.toma.pubgmc.api.game.team;

import dev.toma.pubgmc.api.game.groups.Group;
import dev.toma.pubgmc.api.game.groups.GroupManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.util.INBTSerializable;

import javax.annotation.Nullable;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public interface TeamManager extends INBTSerializable<NBTTagCompound>, GroupManager {

    Collection<Team> getTeams();

    @Nullable
    Team getTeamById(UUID teamId);

    void removeTeam(UUID teamId);

    @Nullable
    Team getEntityTeamByEntityId(UUID entityId);

    Team createNewTeam(Entity entity);

    void join(Team team, Entity entity);

    default void eliminate(Entity entity) {
        eliminate(entity.getUniqueID());
    }

    void eliminate(UUID memberId);

    void disbandAndTransferMembers(Team team);

    boolean tryLeaveTeam(EntityPlayer player, Team team, boolean kicked);

    @Nullable
    default Team getEntityTeam(@Nullable Entity entity) {
        return entity != null ? getEntityTeamByEntityId(entity.getUniqueID()) : null;
    }

    default boolean canJoinTeam(Entity entity, Team team) {
        return !team.getAllMembers().containsKey(entity.getUniqueID());
    }

    default TeamRelations getTeamRelationship(@Nullable Team team1, @Nullable Team team2) {
        if (team1 == null || team2 == null) {
            return TeamRelations.UNKNOWN;
        }
        return team1.getId().equals(team2.getId()) ? TeamRelations.FRIENDLY : TeamRelations.ENEMY;
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

    default int getAlivePlayerCount() {
        return (int) this.getTeams().stream()
                .flatMap(Team::getActiveMemberStream)
                .filter(Team.Member::isPlayer)
                .count();
    }

    default List<Team> getActiveTeams() {
        return this.getTeams().stream().filter(team -> !team.isTeamEliminated())
                .collect(Collectors.toList());
    }

    @Override
    default Group getGroupForPlayer(UUID playerId) {
        return this.getEntityTeamByEntityId(playerId);
    }
}

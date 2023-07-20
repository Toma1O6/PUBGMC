package dev.toma.pubgmc.common.games.game.domination;

import dev.toma.pubgmc.api.game.team.TeamManager;
import dev.toma.pubgmc.api.game.util.Team;
import dev.toma.pubgmc.common.games.util.TeamType;
import dev.toma.pubgmc.util.helper.SerializationHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.util.text.TextComponentTranslation;

import javax.annotation.Nullable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class DominationTeamManager implements TeamManager {

    public static final UUID RED_TEAM  = UUID.fromString("f0c5ebcb-8b09-42b7-b08a-e27c77797a40");
    public static final UUID BLUE_TEAM = UUID.fromString("d40d6ae7-fa86-41c9-a840-bc90713b0dd8");

    private final Map<UUID, Team> teamMap = new HashMap<>();
    private final Map<UUID, Team> entityTeamMap = new HashMap<>();

    public DominationTeamManager() {
        teamMap.put(RED_TEAM, new Team(RED_TEAM));
        teamMap.put(BLUE_TEAM, new Team(BLUE_TEAM));
    }

    public TeamType getTeamType(Entity entity) {
        return getTeamType(entity.getUniqueID());
    }

    public TeamType getTeamType(UUID uuid) {
        Team team = entityTeamMap.get(uuid);
        if (team == null) {
            return null;
        }
        return getTeamType(team);
    }

    public TeamType getTeamType(Team team) {
        return team.getTeamId().equals(RED_TEAM) ? TeamType.RED : TeamType.BLUE;
    }

    public void autoJoinTeam(EntityLivingBase livingBase) {
        int red = teamMap.get(RED_TEAM).getSize();
        int blue = teamMap.get(BLUE_TEAM).getSize();
        Team team = red < blue ? teamMap.get(RED_TEAM) : teamMap.get(BLUE_TEAM);
        join(team, livingBase);
        livingBase.sendMessage(new TextComponentTranslation("message.pubgmc.game.domination.team_joined", getTeamType(team).getTitle()));
    }

    @Override
    public Collection<Team> getTeams() {
        return teamMap.values();
    }

    @Nullable
    @Override
    public Team getTeamById(UUID teamId) {
        return teamMap.get(teamId);
    }

    @Override
    public void removeTeam(UUID teamId) {
        throw new UnsupportedOperationException("Unable to delete predefined team");
    }

    @Nullable
    @Override
    public Team getEntityTeamByEntityId(UUID entityId) {
        return entityTeamMap.get(entityId);
    }

    @Override
    public Team createNewTeam(Entity entity) {
        throw new UnsupportedOperationException("Unable to create new team");
    }

    @Override
    public void join(Team team, Entity entity) {
        team.add(entity);
        entityTeamMap.put(entity.getUniqueID(), team);
    }

    @Override
    public void eliminate(Entity entity) {
        UUID uuid = entity.getUniqueID();
        Team team = entityTeamMap.get(uuid);
        if (team != null) {
            team.removeMemberById(uuid);
            entityTeamMap.remove(uuid);
        }
    }

    @Override
    public void disbandAndTransferMembers(Team team) {
        throw new UnsupportedOperationException("Team cannot be disbanded");
    }

    @Override
    public boolean tryLeaveTeam(EntityPlayer player, Team team, boolean kicked) {
        if (!isValidTeam(team)) {
            return false;
        }
        Team enemyTeam = getOpposingTeam(team);
        if (enemyTeam.getSize() - team.getSize() < 2) {
            team.removeMemberById(player.getUniqueID());
            enemyTeam.add(player);
            entityTeamMap.put(player.getUniqueID(), enemyTeam);
            player.sendMessage(new TextComponentTranslation("message.pubgmc.game.domination.team_joined", getTeamType(enemyTeam).getTitle()));
            return true;
        }
        return false;
    }

    @Override
    public NBTTagCompound serializeNBT() {
        NBTTagCompound nbt = new NBTTagCompound();
        nbt.setTag("teams", SerializationHelper.mapToNbt(teamMap, UUID::toString, Team::serialize));
        nbt.setTag("entityTeams", SerializationHelper.mapToNbt(entityTeamMap, UUID::toString, team -> new NBTTagString(team.getTeamId().toString())));
        return nbt;
    }

    @Override
    public void deserializeNBT(NBTTagCompound nbt) {
        SerializationHelper.mapFromNbt(teamMap, nbt.getCompoundTag("teams"), UUID::fromString, data -> Team.deserialize((NBTTagCompound) data));
        SerializationHelper.mapFromNbt(entityTeamMap, nbt.getCompoundTag("entityTeams"), UUID::fromString, data -> {
            UUID teamId = UUID.fromString(((NBTTagString) data).getString());
            return teamMap.get(teamId);
        });
    }

    public Team selectTeam() {
        int red = teamMap.get(RED_TEAM).getSize();
        int blue = teamMap.get(RED_TEAM).getSize();
        return blue < red ? teamMap.get(BLUE_TEAM) : teamMap.get(RED_TEAM);
    }

    private boolean isValidTeam(Team team) {
        return team.getTeamId().equals(RED_TEAM) || team.getTeamId().equals(BLUE_TEAM);
    }

    private Team getOpposingTeam(Team current) {
        UUID opposingTeam = current.getTeamId().equals(RED_TEAM) ? BLUE_TEAM : RED_TEAM;
        return teamMap.get(opposingTeam);
    }
}

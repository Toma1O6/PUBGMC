package dev.toma.pubgmc.api.game.team;

import dev.toma.pubgmc.api.game.util.Team;
import dev.toma.pubgmc.util.helper.SerializationHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagString;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class SimpleTeamManager implements TeamManager {

    protected final Map<UUID, Team> teamById;
    protected final Map<UUID, Team> teamByEntityId;

    public SimpleTeamManager() {
        this.teamById = new HashMap<>();
        this.teamByEntityId = new HashMap<>();
    }

    @Override
    public Team getTeamById(UUID teamId) {
        return teamById.get(teamId);
    }

    @Override
    public void removeTeam(UUID teamId) {
        teamById.remove(teamId);
    }

    @Override
    public Team getEntityTeamByEntityId(UUID entityId) {
        return teamByEntityId.get(entityId);
    }

    @Override
    public Team createNewTeam(Entity entity) {
        Team team = new Team(entity);
        teamById.put(team.getTeamId(), team);
        teamByEntityId.put(entity.getUniqueID(), team);
        return team;
    }

    @Override
    public void join(Team team, Entity entity) {
        if (getTeamById(team.getTeamId()) == null) {
            return;
        }
        team.add(entity);
        teamByEntityId.put(entity.getUniqueID(), team);
    }

    @Override
    public void eliminate(Entity entity) {
        UUID entityId = entity.getUniqueID();
        Team team = getEntityTeam(entity);
        if (team != null && team.isMember(entityId)) {
            team.eliminate(entityId);
            if (team.isTeamEliminated()) {
                teamById.remove(team.getTeamId());
            }
        }
    }

    @Override
    public boolean tryLeaveTeam(EntityPlayer player, Team team, boolean kicked) {
        if (team.isTeamLeader(player)) {
            disbandAndTransferMembers(team);
        } else {
            team.removeMemberById(player.getUniqueID());
        }
        createNewTeam(player);
        return true;
    }

    @Override
    public void disbandAndTransferMembers(Team oldTeam) {
        Map<UUID, Team.Member> memberMap = new HashMap<>(oldTeam.getAllMembers());
        memberMap.remove(oldTeam.getTeamId());
        Team team = null;
        for (Map.Entry<UUID, Team.Member> entry : memberMap.entrySet()) {
            UUID memberId = entry.getKey();
            Team.Member member = entry.getValue();
            if (team == null) {
                team = new Team(member);
                teamById.put(team.getTeamId(), team);
            }
            if (oldTeam.isMember(memberId)) {
                team.addActiveMember(memberId);
            }
            team.addUsername(memberId, oldTeam.getUsername(memberId));
            teamByEntityId.put(memberId, team);
        }
    }

    @Override
    public Collection<Team> getTeams() {
        return teamById.values();
    }

    @Override
    public NBTTagCompound serializeNBT() {
        NBTTagCompound nbt = new NBTTagCompound();
        nbt.setTag("teams", SerializationHelper.mapToNbt(teamById, UUID::toString, Team::serialize));
        nbt.setTag("entityTeams", SerializationHelper.mapToNbt(teamByEntityId, UUID::toString, team -> new NBTTagString(team.getTeamId().toString())));
        return nbt;
    }

    @Override
    public void deserializeNBT(NBTTagCompound nbt) {
        SerializationHelper.mapFromNbt(teamById, nbt.getCompoundTag("teams"), UUID::fromString, nbtBase -> Team.deserialize((NBTTagCompound) nbtBase));
        SerializationHelper.mapFromNbt(teamByEntityId, nbt.getCompoundTag("entityTeams"), UUID::fromString, nbtBase -> {
            UUID teamId = UUID.fromString(((NBTTagString) nbtBase).getString());
            return teamById.get(teamId);
        });
    }
}

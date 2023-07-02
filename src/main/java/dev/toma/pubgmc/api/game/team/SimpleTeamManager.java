package dev.toma.pubgmc.api.game.team;

import dev.toma.pubgmc.api.game.util.Team;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraftforge.common.util.Constants;

import java.util.*;

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
        NBTTagList teamByIdTag = new NBTTagList();
        for (Map.Entry<UUID, Team> entry : teamById.entrySet()) {
            NBTTagCompound tag = new NBTTagCompound();
            tag.setUniqueId("teamId", entry.getKey());
            tag.setTag("team", entry.getValue().serialize());
            teamByIdTag.appendTag(tag);
        }
        nbt.setTag("teams", teamByIdTag);
        NBTTagCompound entityTeams = new NBTTagCompound();
        for (Map.Entry<UUID, Team> entry : teamByEntityId.entrySet()) {
            entityTeams.setString(entry.getKey().toString(), entry.getValue().getTeamId().toString());
        }
        nbt.setTag("entityTeams", entityTeams);
        return nbt;
    }

    @Override
    public void deserializeNBT(NBTTagCompound nbt) {
        teamById.clear();
        teamByEntityId.clear();
        NBTTagList teamList = nbt.getTagList("teams", Constants.NBT.TAG_COMPOUND);
        for (int i = 0; i < teamList.tagCount(); i++) {
            NBTTagCompound tag = teamList.getCompoundTagAt(i);
            UUID teamId = tag.getUniqueId("teamId");
            Team team = Team.deserialize(tag.getCompoundTag("team"));
            teamById.put(teamId, team);
        }
        NBTTagCompound entityTag = nbt.getCompoundTag("entityTeams");
        Set<String> keys = entityTag.getKeySet();
        for (String key : keys) {
            UUID entityId = UUID.fromString(key);
            UUID teamId = UUID.fromString(entityTag.getString(key));
            Team team = teamById.get(teamId);
            if (team != null) {
                teamByEntityId.put(entityId, team);
            }
        }
    }
}

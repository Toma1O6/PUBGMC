package dev.toma.pubgmc.common.games.game.battleroyale;

import dev.toma.pubgmc.api.game.Team;
import dev.toma.pubgmc.api.game.TeamManager;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraftforge.common.util.Constants;

import java.util.*;

public class BattleRoyaleTeamManager implements TeamManager {

    private final BattleRoyaleGame game;
    private final int teamSize;
    private final Map<UUID, Team> teamById;
    private final Map<UUID, Team> entityTeamMap;

    public BattleRoyaleTeamManager(BattleRoyaleGame game, int teamSize) {
        this.game = game;
        this.teamById = new HashMap<>();
        this.entityTeamMap = new HashMap<>();
        this.teamSize = teamSize;
    }

    @Override
    public Collection<Team> getTeams() {
        return teamById.values();
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
        return entityTeamMap.get(entityId);
    }

    @Override
    public Team createNewTeam(Entity entity) {
        Team team = new Team(entity);
        teamById.put(team.getTeamId(), team);
        entityTeamMap.put(entity.getUniqueID(), team);
        return team;
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
            entityTeamMap.put(memberId, team);
        }
    }

    @Override
    public boolean shouldRemoveFreshlyLoadedEntity(Entity entity) {
        return true;
    }

    public NBTTagCompound serialize() {
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
        for (Map.Entry<UUID, Team> entry : entityTeamMap.entrySet()) {
            entityTeams.setString(entry.getKey().toString(), entry.getValue().getTeamId().toString());
        }
        nbt.setTag("entityTeams", entityTeams);
        return nbt;
    }

    public void deserialize(NBTTagCompound nbt) {
        teamById.clear();
        entityTeamMap.clear();
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
                entityTeamMap.put(entityId, team);
            }
        }
    }
}

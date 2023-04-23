package dev.toma.pubgmc.common.games.util;

import dev.toma.pubgmc.api.game.LivingGameEntity;
import dev.toma.pubgmc.api.game.Team;
import dev.toma.pubgmc.api.game.TeamManager;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.util.Constants;

import java.util.*;

public class TeamAIManager {

    private final TeamManager teamManager;
    private int totalAiCount;
    private int allowedAiSpawnCount;
    private int deadEntities;
    private final Set<UUID> spawnedEntities = new HashSet<>();
    private final Set<UUID> despawnedEntities = new HashSet<>();

    public TeamAIManager(TeamManager teamManager) {
        this.teamManager = teamManager;
    }

    public void entitySpawned(Entity entity) {
        spawnedEntities.add(entity.getUniqueID());
        allowedAiSpawnCount--;
    }

    public void onAiEntityDied(Entity entity) {
        UUID uuid = entity.getUniqueID();
        spawnedEntities.remove(uuid);
        despawnedEntities.remove(uuid);
        ++deadEntities;
    }

    public void checkForUnloadedEntities(WorldServer server) {
        Iterator<UUID> iterator = spawnedEntities.iterator();
        // Check active entities
        while (iterator.hasNext()) {
            UUID memberId = iterator.next();
            Team team = teamManager.getEntityTeamByEntityId(memberId);
            if (team == null) {
                // Entity is eliminated
                allowedAiSpawnCount++;
                iterator.remove();
                continue;
            }
            Optional<Team.Member> memberOptional = team.getMember(memberId);
            if (!memberOptional.isPresent()) {
                // Should not really happen if team manager is implemented correctly
                iterator.remove();
                allowedAiSpawnCount++;
                continue;
            }
            Entity entity = server.getEntityFromUuid(memberId);
            if (entity == null) {
                // Entity was unloaded
                despawnedEntities.add(memberId);
                allowedAiSpawnCount++;
                iterator.remove();
                if (team.getTeamId().equals(memberId)) {
                    // unloaded entity was team leader, new team needs to be created and original members transferred
                    teamManager.disbandAndTransferMembers(team);
                }
                team.removeMemberById(memberId);
                if (team.isTeamEliminated()) {
                    teamManager.removeTeam(team.getTeamId());
                }
            }
        }

        // Check if despawned entity got loaded again
        Iterator<UUID> despawnedEntityIterator = despawnedEntities.iterator();
        while (despawnedEntityIterator.hasNext()) {
            UUID entityId = despawnedEntityIterator.next();
            Entity entity = server.getEntityFromUuid(entityId);
            if (entity instanceof LivingGameEntity) {
                if (teamManager.shouldRemoveFreshlyLoadedEntity(entity)) {
                    entity.setDead();
                    despawnedEntityIterator.remove();
                }
            }
        }
    }

    public void setAllowedAiSpawnCount(int allowedAiSpawnCount) {
        this.totalAiCount = allowedAiSpawnCount;
        this.allowedAiSpawnCount = allowedAiSpawnCount;
    }

    public boolean canSpawnEntity() {
        return allowedAiSpawnCount > 0;
    }

    public int getRemainingAliveEntityCount() {
        return totalAiCount - deadEntities;
    }

    public NBTTagCompound serialize() {
        NBTTagCompound nbt = new NBTTagCompound();
        nbt.setInteger("total", totalAiCount);
        nbt.setInteger("toSpawn", allowedAiSpawnCount);
        nbt.setInteger("dead", deadEntities);
        NBTTagList spawned = new NBTTagList();
        spawnedEntities.forEach(uuid -> spawned.appendTag(new NBTTagString(uuid.toString())));
        NBTTagList despawned = new NBTTagList();
        despawnedEntities.forEach(uuid -> despawned.appendTag(new NBTTagString(uuid.toString())));
        nbt.setTag("spawned", spawned);
        nbt.setTag("despawned", despawned);
        return nbt;
    }

    public void deserialize(NBTTagCompound nbt) {
        spawnedEntities.clear();
        despawnedEntities.clear();
        totalAiCount = nbt.getInteger("total");
        allowedAiSpawnCount = nbt.getInteger("toSpawn");
        deadEntities = nbt.getInteger("dead");
        NBTTagList spawned = nbt.getTagList("spawned", Constants.NBT.TAG_STRING);
        NBTTagList despawned = nbt.getTagList("despawned", Constants.NBT.TAG_STRING);
        spawned.forEach(tag -> spawnedEntities.add(UUID.fromString(((NBTTagString) tag).getString())));
        despawned.forEach(tag -> despawnedEntities.add(UUID.fromString(((NBTTagString) tag).getString())));
    }
}

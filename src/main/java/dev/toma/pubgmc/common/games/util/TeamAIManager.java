package dev.toma.pubgmc.common.games.util;

import dev.toma.pubgmc.api.game.team.TeamManager;
import dev.toma.pubgmc.api.game.util.Team;
import dev.toma.pubgmc.util.helper.SerializationHelper;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.util.Constants;

import java.util.*;
import java.util.function.Consumer;

public class TeamAIManager {

    private final TeamManager teamManager;
    private int totalAiCount;
    private int allowedAiSpawnCount;
    private int deadEntities;
    private final Set<UUID> spawnedEntities = new HashSet<>();
    private Consumer<UUID> unloadListener = uuid -> {};

    public TeamAIManager(TeamManager teamManager) {
        this.teamManager = teamManager;
    }

    public void onEntityUnloaded(Consumer<UUID> listener) {
        this.unloadListener = listener;
    }

    public void entitySpawned(Entity entity) {
        spawnedEntities.add(entity.getUniqueID());
        allowedAiSpawnCount--;
    }

    public void onAiEntityDied(UUID entity) {
        spawnedEntities.remove(entity);
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
            Optional<Team.Member> memberOptional = team.getActiveMember(memberId);
            if (!memberOptional.isPresent()) {
                // Should not really happen if team manager is implemented correctly
                iterator.remove();
                allowedAiSpawnCount++;
                continue;
            }
            Entity entity = server.getEntityFromUuid(memberId);
            if (entity == null) {
                // Entity was unloaded
                teamManager.eliminate(memberId);
                deadEntities++;
                unloadListener.accept(memberId);
                iterator.remove();
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

    public int getAiEntitiesToSpawn() {
        return allowedAiSpawnCount;
    }

    public NBTTagCompound serialize() {
        NBTTagCompound nbt = new NBTTagCompound();
        nbt.setInteger("total", totalAiCount);
        nbt.setInteger("toSpawn", allowedAiSpawnCount);
        nbt.setInteger("dead", deadEntities);
        nbt.setTag("spawned", SerializationHelper.collectionToNbt(spawnedEntities, uuid -> new NBTTagString(uuid.toString())));
        return nbt;
    }

    public void deserialize(NBTTagCompound nbt) {
        spawnedEntities.clear();
        totalAiCount = nbt.getInteger("total");
        allowedAiSpawnCount = nbt.getInteger("toSpawn");
        deadEntities = nbt.getInteger("dead");
        SerializationHelper.collectionFromNbt(spawnedEntities, nbt.getTagList("spawned", Constants.NBT.TAG_STRING), base -> UUID.fromString(((NBTTagString) base).getString()));
    }
}

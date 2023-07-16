package dev.toma.pubgmc.common.games.game.ffa;

import com.google.common.collect.ImmutableSet;
import dev.toma.pubgmc.util.helper.SerializationHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.util.Constants;

import java.util.*;
import java.util.stream.Collectors;

public final class FFAParticipantManager {

    private final Map<UUID, NBTTagCompound> aiData;
    private final Set<UUID> players;
    private final Set<UUID> respawnAi;

    public FFAParticipantManager() {
        this.aiData = new HashMap<>();
        this.players = new HashSet<>();
        this.respawnAi = new HashSet<>();
    }

    public void registerPlayer(EntityPlayer player) {
        players.add(player.getUniqueID());
    }

    public void removePlayer(UUID player) {
        players.remove(player);
    }

    public boolean isParticipant(EntityPlayer player) {
        return players.contains(player.getUniqueID());
    }

    public int getPlayerParticipantsCount() {
        return players.size();
    }

    public List<EntityPlayer> getPlayerParticipants(World world) {
        return players.stream().map(world::getPlayerEntityByUUID)
                .filter(Objects::nonNull).collect(Collectors.toList());
    }

    public List<Entity> getLoadedParticipants(WorldServer server) {
        List<Entity> result = new ArrayList<>();
        result.addAll(getPlayerParticipants(server));
        result.addAll(aiData.keySet().stream()
                .map(server::getEntityFromUuid)
                .filter(Objects::nonNull)
                .collect(Collectors.toList())
        );
        return result;
    }

    public void registerAi(EntityLiving ai) {
        UUID uuid = ai.getUniqueID();
        NBTTagCompound nbt = new NBTTagCompound();
        ai.writeEntityToNBT(nbt);
        aiData.put(uuid, nbt);
    }

    public void markAiAsDead(UUID ai) {
        respawnAi.add(ai);
    }

    public Set<UUID> getAiForRespawn() {
        return ImmutableSet.copyOf(respawnAi);
    }

    public void markRespawned(UUID ai) {
        respawnAi.remove(ai);
    }

    public NBTTagCompound serialize() {
        NBTTagCompound nbt = new NBTTagCompound();
        nbt.setTag("aiData", SerializationHelper.mapToNbt(aiData, UUID::toString, entityNbt -> entityNbt));
        nbt.setTag("players", SerializationHelper.collectionToNbt(players, uuid -> new NBTTagString(uuid.toString())));
        nbt.setTag("respawnAi", SerializationHelper.collectionToNbt(respawnAi, uuid -> new NBTTagString(uuid.toString())));
        return nbt;
    }

    public void deserialize(NBTTagCompound nbt) {
        SerializationHelper.mapFromNbt(aiData, nbt.getCompoundTag("aiData"), UUID::fromString, base -> (NBTTagCompound) base);
        SerializationHelper.collectionFromNbt(players, nbt.getTagList("players", Constants.NBT.TAG_STRING), base -> UUID.fromString(((NBTTagString) base).getString()));
        SerializationHelper.collectionFromNbt(respawnAi, nbt.getTagList("respawnAi", Constants.NBT.TAG_STRING), base -> UUID.fromString(((NBTTagString) base).getString()));
    }
}

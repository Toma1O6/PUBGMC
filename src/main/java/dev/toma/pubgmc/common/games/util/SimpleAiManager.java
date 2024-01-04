package dev.toma.pubgmc.common.games.util;

import com.google.common.collect.ImmutableList;
import dev.toma.pubgmc.Pubgmc;
import dev.toma.pubgmc.common.entity.EntityAIPlayer;
import dev.toma.pubgmc.util.helper.SerializationHelper;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagString;
import net.minecraftforge.common.util.Constants;

import java.util.*;

public final class SimpleAiManager {

    private final Map<UUID, NBTTagCompound> data;
    private final Set<UUID> dead;

    public SimpleAiManager() {
        this.data = new HashMap<>();
        this.dead = new HashSet<>();
    }

    public void transfer(UUID old, UUID newId) {
        NBTTagCompound nbt = data.get(old);
        data.remove(old);
        data.put(newId, nbt);
    }

    public void register(EntityAIPlayer entity) {
        UUID uuid = entity.getUniqueID();
        NBTTagCompound nbt = new NBTTagCompound();
        entity.writeToNBT(nbt);
        data.put(uuid, nbt);
    }

    public void despawn(Entity entity) {
        if (!contains(entity.getUniqueID())) {
            Pubgmc.logger.warn("Attempted to despawn AI which is not registered to game - {}", entity);
            return;
        }
        markDead(entity.getUniqueID());
        entity.setDead();
    }

    public boolean contains(UUID entity) {
        return data.containsKey(entity);
    }

    public NBTTagCompound getNBTData(UUID ai) {
        return data.get(ai);
    }

    public void markDead(UUID ai) {
        dead.add(ai);
    }

    public void markAlive(UUID ai) {
        dead.remove(ai);
    }

    public Collection<UUID> getDeadAIs() {
        return ImmutableList.copyOf(dead);
    }

    public NBTTagCompound serialize() {
        NBTTagCompound nbt = new NBTTagCompound();
        nbt.setTag("data", SerializationHelper.mapToNbt(data, UUID::toString, entityNbt -> entityNbt));
        nbt.setTag("dead", SerializationHelper.collectionToNbt(dead, uuid -> new NBTTagString(uuid.toString())));
        return nbt;
    }

    public void deserialize(NBTTagCompound nbt) {
        SerializationHelper.mapFromNbt(data, nbt.getCompoundTag("data"), UUID::fromString, base -> (NBTTagCompound) base);
        SerializationHelper.collectionFromNbt(dead, nbt.getTagList("dead", Constants.NBT.TAG_STRING), base -> UUID.fromString(((NBTTagString) base).getString()));
    }
}

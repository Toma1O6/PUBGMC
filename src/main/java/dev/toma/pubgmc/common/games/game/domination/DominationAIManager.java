package dev.toma.pubgmc.common.games.game.domination;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import dev.toma.pubgmc.common.entity.EntityAIPlayer;
import dev.toma.pubgmc.util.helper.SerializationHelper;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagString;
import net.minecraftforge.common.util.Constants;

import java.util.*;

public final class DominationAIManager {

    private final Map<UUID, NBTTagCompound> data;
    private final Set<UUID> dead;

    public DominationAIManager() {
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

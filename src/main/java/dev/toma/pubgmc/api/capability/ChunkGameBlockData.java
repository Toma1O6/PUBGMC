package dev.toma.pubgmc.api.capability;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.INBTSerializable;

import java.util.Collection;
import java.util.UUID;

public interface ChunkGameBlockData extends INBTSerializable<NBTTagCompound> {

    boolean isOutdated();

    boolean isSameGameId(UUID gameId);

    void updateGameId(UUID gameId);

    void markUpToDate();

    Collection<Short> getBlockData();

    void set(Collection<Short> data);

    void setBlockData(short index, boolean exists);
}

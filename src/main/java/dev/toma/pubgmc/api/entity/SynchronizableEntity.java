package dev.toma.pubgmc.api.entity;

import net.minecraft.nbt.NBTTagCompound;

public interface SynchronizableEntity {

    NBTTagCompound encodeNetworkData();

    void decodeNetworkData(NBTTagCompound nbt);
}

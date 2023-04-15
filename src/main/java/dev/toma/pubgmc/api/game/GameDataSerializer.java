package dev.toma.pubgmc.api.game;

import net.minecraft.nbt.NBTTagCompound;

public interface GameDataSerializer<G extends Game<?>> {

    NBTTagCompound serializeGameData(G game);

    G deserializeGameData(NBTTagCompound nbt);
}

package dev.toma.pubgmc.api.game;

import net.minecraft.nbt.NBTTagCompound;

public interface GameDataSerializer<CFG extends GameConfiguration, G extends Game<CFG>> {

    NBTTagCompound serializeGameData(G game);

    G deserializeGameData(NBTTagCompound nbt, CFG configuration);

    NBTTagCompound serializeGameConfiguration(CFG configuration);

    CFG deserializeGameConfiguration(NBTTagCompound nbt);
}

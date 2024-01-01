package dev.toma.pubgmc.api.game;

import dev.toma.pubgmc.api.data.DataReader;
import dev.toma.pubgmc.api.data.DataWriter;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public interface GameDataSerializer<CFG extends GameConfiguration, G extends Game<CFG>> {

    NBTTagCompound serializeGameData(G game);

    G deserializeGameData(NBTTagCompound nbt, CFG configuration, World world);

    void serializeGameConfiguration(CFG configuration, DataWriter<?> writer);

    CFG deserializeGameConfiguration(DataReader<?> reader);
}

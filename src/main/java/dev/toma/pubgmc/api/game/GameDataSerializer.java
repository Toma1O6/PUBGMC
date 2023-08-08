package dev.toma.pubgmc.api.game;

import com.google.gson.JsonObject;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public interface GameDataSerializer<CFG extends GameConfiguration, G extends Game<CFG>> {

    NBTTagCompound serializeGameData(G game);

    G deserializeGameData(NBTTagCompound nbt, CFG configuration, World world);

    NBTTagCompound serializeGameConfiguration(CFG configuration);

    CFG deserializeGameConfiguration(NBTTagCompound nbt);

    JsonObject serializeConfigurationToJson(CFG configuration);

    CFG deserializeConfigurationFromJson(JsonObject object);
}

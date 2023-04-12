package dev.toma.pubgmc.api.capability;

import dev.toma.pubgmc.api.game.Game;
import dev.toma.pubgmc.api.game.GameType;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.INBTSerializable;

public interface GameData extends INBTSerializable<NBTTagCompound> {

    Game getCurrentGame();

    GameType<?> getSelectedGameType();

    void setSelectedGameType(GameType<?> gameType);

    void sendGameDataToClients();
}

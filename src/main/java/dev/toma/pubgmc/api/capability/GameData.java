package dev.toma.pubgmc.api.capability;

import dev.toma.pubgmc.api.game.Game;
import dev.toma.pubgmc.api.game.GameType;
import dev.toma.pubgmc.api.game.map.GameLobby;
import dev.toma.pubgmc.api.game.map.GameMap;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.INBTSerializable;

import javax.annotation.Nullable;
import java.util.Map;

public interface GameData extends INBTSerializable<NBTTagCompound> {

    void tick();

    Game<?> getCurrentGame();

    GameType<?, ?> getSelectedGameType();

    @Nullable
    GameLobby getGameLobby();

    @Nullable
    GameMap getGameMap(String mapName);

    void registerGameMap(GameMap map);

    void deleteGameMap(String name);

    Map<String, GameMap> getRegisteredGameMaps();

    void setGameLobby(GameLobby lobby);

    void setSelectedGameType(GameType<?, ?> gameType);

    void setActiveGame(Game<?> game);

    void sendGameDataToClients();
}

package dev.toma.pubgmc.api.capability;

import dev.toma.pubgmc.api.game.Game;
import dev.toma.pubgmc.api.game.GameType;
import dev.toma.pubgmc.api.game.map.GameLobby;
import dev.toma.pubgmc.api.game.map.GameMap;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.INBTSerializable;

import javax.annotation.Nullable;
import java.util.Map;
import java.util.Optional;

/**
 * Capability API for complete game and game data management.
 *
 * @author Toma
 * @since 1.9.0
 */
public interface GameData extends INBTSerializable<NBTTagCompound> {

    /**
     * General tick method
     */
    void tick();

    /**
     * @return Current game or {@link dev.toma.pubgmc.common.games.NoGame#INSTANCE} when no game is active.
     */
    Game<?> getCurrentGame();

    /**
     * @return Currently selected game type
     */
    GameType<?, ?> getSelectedGameType();

    /**
     * @return {@link GameLobby} instance which is defined for this world
     */
    @Nullable
    GameLobby getGameLobby();

    /**
     * Tries to lookup registered map by name
     * @param mapName Name of map
     * @return Registered {@link GameMap} or {@code null} when no map exists for this name
     */
    @Nullable
    GameMap getGameMap(String mapName);

    /**
     * Registers new map
     * @param map New {@link GameMap} instance
     */
    void registerGameMap(GameMap map);

    /**
     * Deletes game map by name or silently ignores when no map exists for such name
     * @param name Name of map for deletion
     */
    void deleteGameMap(String name);

    /**
     * @return Mapping of all maps
     */
    Map<String, GameMap> getRegisteredGameMaps();

    /**
     * Assigns new GameLobby for this world instance
     * @param lobby GameLobby instance
     */
    void setGameLobby(GameLobby lobby);

    /**
     * Selects new game type
     * @param gameType GameType to select
     */
    void setSelectedGameType(GameType<?, ?> gameType);

    /**
     * Assigns new active game instance
     * @param game Currently active game
     */
    void setActiveGame(Game<?> game);

    /**
     * Assigns name of map which is currently active
     * @param mapName Name of currently active game map
     */
    void setActiveGameMapName(@Nullable String mapName);

    /**
     * @return Name of currently active map. Can be {@code null}
     */
    @Nullable
    String getActiveGameMapName();

    /**
     * Used for synchronization of server data to all suitable clients
     */
    void sendGameDataToClients();

    /**
     * @return Optional holding active GameMap instance
     */
    @Nullable
    default Optional<GameMap> getActiveGameMap() {
        return Optional.ofNullable(getActiveGameMapName()).map(this::getGameMap);
    }
}

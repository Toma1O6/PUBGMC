package dev.toma.pubgmc.common.game.data;

import dev.toma.pubgmc.common.game.AbstractGame;
import dev.toma.pubgmc.common.game.GameLaunchConfig;
import dev.toma.pubgmc.common.game.map.Area3D;
import net.minecraft.world.World;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class GameData implements IGameData {

    private final World world;
    private Map<Long, AbstractGame> gameMap;
    private Area3D globalLobby;

    public GameData(World world) {
        this.world = world;
        gameMap = new HashMap<>();
    }

    @Override
    public void createGame(GameLaunchConfig<?> config) {
        AbstractGame game = config.launch();
        long gameID = UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE;
        gameMap.put(gameID, game);
    }

    @Override
    public void tickGames() {

    }

    @Override
    public Area3D getGlobalLobby() {
        return globalLobby;
    }

    @Override
    public Map<Long, AbstractGame> getGames() {
        return gameMap;
    }

    @Override
    public World getWorld() {
        return world;
    }

    public static IGameData getData(World world) {
        return null;
    }
}

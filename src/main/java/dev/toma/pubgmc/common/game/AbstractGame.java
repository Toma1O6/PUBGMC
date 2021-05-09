package dev.toma.pubgmc.common.game;

import dev.toma.pubgmc.api.common.game.IKeyHolder;
import dev.toma.pubgmc.common.game.argument.ArgumentMap;
import dev.toma.pubgmc.common.game.map.GameMap;
import net.minecraft.world.World;

public abstract class AbstractGame implements IKeyHolder {

    private final World world;
    private final GameType<?> type;
    private final ArgumentMap argumentMap;
    private GameMap map;
    private long gameID;

    public AbstractGame(GameLaunchConfig<?> launchConfig) {
        this.world = launchConfig.world;
        this.type = launchConfig.type;
        this.argumentMap = launchConfig.argumentMap;
    }

    public void startGame() {

    }

    public void endGame() {

    }

    public GameType<?> getGameType() {
        return type;
    }

    @Override
    public void setGameID(long gameID) {
        this.gameID = gameID;
    }

    @Override
    public long getGameID() {
        return gameID;
    }
}

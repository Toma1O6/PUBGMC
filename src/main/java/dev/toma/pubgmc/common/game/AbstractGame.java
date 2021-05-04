package dev.toma.pubgmc.common.game;

import dev.toma.pubgmc.api.common.game.IKeyHolder;
import dev.toma.pubgmc.common.game.argument.ArgumentMap;
import dev.toma.pubgmc.common.game.interfaces.IGameStage;
import dev.toma.pubgmc.common.game.map.Area2D;
import dev.toma.pubgmc.common.game.map.GameMap;
import net.minecraft.world.World;

public abstract class AbstractGame implements IKeyHolder {

    private final World world;
    private final GameType<?> type;
    private final GameStageHandler stageHandler;
    private final ArgumentMap argumentMap;
    private GameMap map;
    private long gameID;

    public AbstractGame(GameLaunchConfig<?> launchConfig) {
        this.world = launchConfig.world;
        this.type = launchConfig.type;
        this.argumentMap = launchConfig.argumentMap;
        this.stageHandler = initializeStageHandler();
    }

    @Override
    public void setGameID(long gameID) {
        this.gameID = gameID;
    }

    @Override
    public long getGameID() {
        return gameID;
    }

    public GameStageHandler getStageHandler() {
        return stageHandler;
    }

    protected GameStageHandler initializeStageHandler() {
        return new GameStageHandler();
    }
}

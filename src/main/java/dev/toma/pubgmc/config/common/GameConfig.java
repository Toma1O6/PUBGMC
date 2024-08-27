package dev.toma.pubgmc.config.common;

import dev.toma.configuration.api.ConfigCreator;
import dev.toma.configuration.api.type.BooleanType;
import dev.toma.configuration.api.type.IntType;
import dev.toma.configuration.api.type.ObjectType;

public final class GameConfig extends ObjectType {

    public BooleanType allowFullChunkScans;
    public IntType backgroundThreadPoolSize;

    public GameConfig() {
        super("Game");
    }

    @Override
    public void buildStructure(ConfigCreator configCreator) {
        this.allowFullChunkScans = configCreator.createBoolean("Allow full chunk block scans", false, "This will scan blocks such as vanilla doors and so on so that game system can operate with it");
        this.backgroundThreadPoolSize = configCreator.createInt("Background thread pool size", 4, 1, 32, "Thread count of background worker", "The bigger the number, the quicker will be scan of newly generated chunk", "Best is to use your core count - 1 for servers and core count - 2 for singleplayer worlds");
    }
}

package dev.toma.pubgmc.common.game;

import dev.toma.pubgmc.common.game.argument.ArgumentMap;
import dev.toma.pubgmc.common.game.data.GameData;
import dev.toma.pubgmc.common.game.data.IGameData;
import dev.toma.pubgmc.common.game.exception.GameException;
import dev.toma.pubgmc.common.game.map.GameMap;
import dev.toma.pubgmc.common.game.map.MapCollection;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public final class GameLaunchConfig<G extends AbstractGame> {

    public final World world;
    public GameType<G> type;
    public GameMap map;
    public ArgumentMap argumentMap;

    private GameLaunchConfig(World world) {
        this.world = world;
    }

    public static <G extends AbstractGame> GameLaunchConfig<G> newConfig(World world) {
        return new GameLaunchConfig<>(world);
    }

    public static <G extends AbstractGame> GameLaunchConfig<G> parse(World world, GameType<G> type, NBTTagCompound data) throws GameException {
        GameLaunchConfig<G> config = newConfig(world);
        config.type = type;
        String mapName = data.getString("map");
        IGameData gameData = GameData.getData(world);
        MapCollection collection = gameData.getMaps();
        config.map = collection.getMapByName(mapName);
        // TODO load arg map
        return config;
    }

    public G launch() {
        GameType.InstanceFactory<G> instanceFactory = type.getInstanceFactory();
        return instanceFactory.newInstance(this);
    }
}

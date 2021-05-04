package dev.toma.pubgmc.common.game;

import dev.toma.pubgmc.common.game.argument.ArgumentMap;
import dev.toma.pubgmc.common.game.map.GameMap;
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

    public G launch() {
        GameType.InstanceFactory<G> instanceFactory = type.getInstanceFactory();
        return instanceFactory.newInstance(this);
    }
}

package dev.toma.pubgmc.common.game.data;

import dev.toma.pubgmc.common.game.AbstractGame;
import dev.toma.pubgmc.common.game.GameLaunchConfig;
import dev.toma.pubgmc.common.game.map.Area3D;
import net.minecraft.world.World;

import java.util.List;
import java.util.Map;

public interface IGameData {

    void tickGames();

    void createGame(GameLaunchConfig<?> config);

    Area3D getGlobalLobby();

    Map<Long, AbstractGame> getGames();

    World getWorld();
}

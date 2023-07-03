package dev.toma.pubgmc.api.game;

import dev.toma.pubgmc.api.capability.GameData;
import dev.toma.pubgmc.api.game.map.GameMap;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

import java.util.UUID;
import java.util.function.Consumer;

public interface Game<CFG extends GameConfiguration> {

    // Unique game identifier
    UUID getGameId();

    // Game type used for serialization of data
    GameType<CFG, ?> getGameType();

    CFG getConfiguration();

    void performGameMapValidations(World world, GameMap map) throws GameException;

    boolean isStarted();

    // Call when /game prepare command is executed. Should teleport all players to lobby etc
    void onGameInit(World world);

    // Call when /game start command is executed. Should start the game
    void onGameStart(World world);

    // Called every world tick on client/server
    void onGameTick(World world);

    // Call when /game stop command is executed. Should stop the game and return everyone to lobby, reset world options and more
    void onGameStopped(World world, GameData data);

    // Called when player leaves via /game leave command. Return false when player is not between active participants
    boolean playerLeaveGame(EntityPlayer player);

    // Called when player attempts to join via /game join command. Return false to decline join request
    boolean playerJoinGame(EntityPlayer player);

    void addListener(GameEventListener listener);

    void invokeEvent(Consumer<GameEventListener> consumer);
}

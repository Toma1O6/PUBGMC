package dev.toma.pubgmc.api.game;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

import java.util.UUID;

public interface Game {

    // Unique game identifier
    UUID getGameId();

    // Game type used for serialization of data
    GameType<?> getGameType();

    // Call when /game prepare command is executed. Should teleport all players to lobby etc
    void onGameInit(World world);

    // Call when /game start command is executed. Should start the game
    void onGameStart(World world);

    // Called every world tick on client/server
    void onGameTick(World world);

    // Call when /game stop command is executed. Should stop the game and return everyone to lobby, reset world options and more
    void onGameStopped(World world);

    // TODO game event listeners

    default void onPlayerLoggedIn(EntityPlayerMP player) {}

    default void onPlayerLoggedOut(EntityPlayerMP player) {}

    default void onEntityDeath(EntityLivingBase entity, DamageSource source) {}

    default void onPlayerRespawn(EntityPlayer player) {}
}

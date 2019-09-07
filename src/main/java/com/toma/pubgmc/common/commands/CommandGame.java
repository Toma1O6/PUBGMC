package com.toma.pubgmc.common.commands;

import com.toma.pubgmc.api.Game;
import com.toma.pubgmc.api.Lobby;
import com.toma.pubgmc.common.capability.IGameData;
import com.toma.pubgmc.common.capability.IGameData.GameDataProvider;
import com.toma.pubgmc.init.GameRegistry;
import joptsimple.internal.Strings;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;

import java.util.Collections;
import java.util.List;

/*
    TODO in order to start you have to select game first
    TODO let the selected game setup it's playzone with it's bluezone settings
 */
public class CommandGame extends CommandBase {

    private static final String[] completions = {"start","stop","help","info","mode","map","lobby"};

    @Override
    public String getName() {
        return "game";
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return "/game";
    }

    @Override
    public int getRequiredPermissionLevel() {
        return 2;
    }

    @Override
    public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args, BlockPos targetPos) {
        return args.length == 1 ? getListOfStringsMatchingLastWord(args, completions) : Collections.EMPTY_LIST;
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        IGameData gameData = sender.getEntityWorld().getCapability(GameDataProvider.GAMEDATA, null);
        if(gameData == null) {
            throw new CommandException("Unable to get game data!");
        }
        if(args.length == 0) {
            throw new WrongUsageException("Unknown argument! Execute '/game help' for more info.");
        }
        switch(args[0]) {
            case "start": {
                if(gameData.getLobby() == null) {
                    throw new CommandException("You must create lobby first! Add it using the '/game lobby' command");
                }
                Game game = gameData.getCurrentGame();
                if(game == null) {
                    throw new CommandException("Unknown game mode! Select one - /game mode [game]");
                } else if(gameData.isInactiveGame()) {
                    throw new CommandException("Cannot start this type of game!");
                }
                if(!game.startGame(sender.getEntityWorld())) {
                    throw new CommandException("Error occured when launching game! Contact GAME AUTHOR about this issue!");
                }
                gameData.setPlaying(true);
                sendCommandFeedback(sender, "Started game");
                break;
            }

            case "stop": {
                if(!gameData.isPlaying() || gameData.isInactiveGame() || gameData.getLobby() == null) {
                    throw new CommandException("There is no active game!");
                }
                gameData.getCurrentGame().stopGame(sender.getEntityWorld());
                gameData.setPlaying(false);
                sendCommandFeedback(sender, "Stopped game");
                break;
            }

            case "help": {
                if(!(sender instanceof EntityPlayer)) {
                    throw new WrongUsageException("Only players can execute this command!");
                }
                EntityPlayer player = (EntityPlayer)sender;
                sendMessage(player, "Commands:");
                sendMessage(player, "start -> starts game");
                sendMessage(player, "stop -> stops game");
                sendMessage(player, "info -> information about current game settings");
                sendMessage(player, "game [name: String] -> binds the game mode to this world");
                sendMessage(player, "map [x: int, z: int, size: int] -> creates map border");
                sendMessage(player, "lobby [x: int, y: int, z: int, radius: int] -> creates lobby for this world");
                sendMessage(player, "");
                sendMessage(player, "Available game modes:");
                for(ResourceLocation location : GameRegistry.REGISTRY.keySet()) {
                    sendMessage(player, "- " + location.getResourcePath() + " [" + location.getResourceDomain().toUpperCase() + "]");
                }
                break;
            }

            case "info": {
                if(!(sender instanceof EntityPlayer)) {
                    throw new WrongUsageException("Only players can execute this command!");
                }
                EntityPlayer player = (EntityPlayer)sender;
                sendMessage(player, "=====[ Game information ]=====");
                sendMessage(player, "Active: " + (gameData.isPlaying() ? "yes" : "no"));
                sendMessage(player, "Map: [" + gameData.getMapCenter().getX() + ", " + gameData.getMapCenter().getZ() + "]; Size: " + gameData.getMapSize() + " blocks");
                sendMessage(player, "Locations: " + gameData.getSpawnLocations().size());
                Game game = gameData.getCurrentGame();
                sendMessage(player, "Game: " + (gameData.isInactiveGame() ? "none" : game.registryName.getResourcePath()));
                if(gameData.isInactiveGame()) {
                    break;
                }
                sendMessage(player, "Alive players: " + game.getJoinedPlayers().size());
                sendMessage(player, "Time: " + (game.getGameTimer() / 20) + "s");
                sendMessage(player, "Mode: " + game.registryName.getResourcePath());
                if(gameData.isPlaying()) sendMessage(player, "Zone stage: " + game.zone.currentStage);
                break;
            }

            case "mode": {
                if(args.length < 2) {
                    throw new WrongUsageException("You must specify game mode! For all game modes use /game help");
                }
                Game game = GameRegistry.findGameInRegistry(args[1]);
                if(game == null) {
                    throw new CommandException("Unknown game!");
                }
                gameData.setGame(game);
                sendCommandFeedback(sender, "Successfully selected game " + game.registryName.getResourcePath());
                break;
            }

            case "map": {
                if(args.length < 4) {
                    throw new WrongUsageException("You must specify X, Z position and map size!");
                }
                int x = Integer.parseInt(args[1]);
                int z = Integer.parseInt(args[2]);
                int size = Integer.parseInt(args[3]);
                gameData.setMapCenter(x, z, size);
                sendCommandFeedback(sender, "Successfully created game map");
                break;
            }

            case "lobby": {
                if(args.length < 5) {
                    throw new WrongUsageException("You must specify X,Y,Z position and radius!");
                }
                int x = Integer.parseInt(args[1]);
                int y = Integer.parseInt(args[2]);
                int z = Integer.parseInt(args[3]);
                int radius = Integer.parseInt(args[4]);
                Lobby lobby = new Lobby(new BlockPos(x, y, z), radius);
                gameData.setLobby(lobby);
                sendCommandFeedback(sender, "Created new game lobby. Now you can use '/leave' to get there");
                break;
            }

            default: {
                throw new WrongUsageException("Unknown argument! Execute '/game help' for more info.");
            }
        }
    }

    private void sendMessage(EntityPlayer player, String text) {
        player.sendMessage(new TextComponentString(text));
    }

    private void sendCommandFeedback(ICommandSender sender, String feedback) {
        if(sender.sendCommandFeedback()) {
            sender.sendMessage(new TextComponentString(feedback));
        }
    }
}

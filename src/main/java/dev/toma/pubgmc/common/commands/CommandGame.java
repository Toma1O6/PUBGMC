package dev.toma.pubgmc.common.commands;

import dev.toma.pubgmc.api.Lobby;
import dev.toma.pubgmc.api.games.Game;
import dev.toma.pubgmc.common.capability.IGameData;
import dev.toma.pubgmc.init.GameRegistry;
import dev.toma.pubgmc.world.MapLocation;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CommandGame extends CommandBase {

    private static final String[] completions = {"start", "stop", "help", "info", "mode", "map", "lobby", "location"};

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
        if (args.length > 1) {
            if (args[0].equalsIgnoreCase("mode")) {
                List<ResourceLocation> list = GameRegistry.getValuesPaths();
                List<String> paths = new ArrayList<>();
                list.forEach(rl -> paths.add(rl.getResourcePath()));
                return paths;
            } else if (args[0].equalsIgnoreCase("location")) {
                return getListOfStringsMatchingLastWord(args, "add", "remove", "list");
            }
        }
        return args.length == 1 ? getListOfStringsMatchingLastWord(args, completions) : Collections.EMPTY_LIST;
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        IGameData gameData = sender.getEntityWorld().getCapability(IGameData.GameDataProvider.GAMEDATA, null);
        if (gameData == null) {
            throw new CommandException("Unable to get game data!");
        }
        if (args.length == 0) {
            throw new WrongUsageException("Unknown argument! Execute '/game help' for more info.");
        }
        switch (args[0]) {
            case "start": {
                if (gameData.getMapSize() <= 0) {
                    throw new CommandException("You must setup your map first! Use /game map [centerX] [centerZ] [size]");
                } else if (gameData.getLobby() == null) {
                    throw new CommandException("You must create lobby first! Add it using the '/game lobby' command");
                } else if (gameData.getCurrentGame().isRunning()) {
                    throw new CommandException("There is already one active game!");
                }
                Game game = gameData.getCurrentGame();
                if (game == null) {
                    throw new CommandException("Unknown game mode! Select one - /game mode [game]");
                } else if (gameData.isInactiveGame()) {
                    throw new CommandException("Cannot start this type of game! Select new mode: /game mode [mode]");
                }
                String[] addtionalArgs = args.length == 1 ? new String[0] : new String[args.length - 1];
                if (addtionalArgs.length > 0) {
                    System.arraycopy(args, 1, addtionalArgs, 0, args.length - 1);
                }
                CommandException exception = game.onGameStartCommandExecuted(sender, server, addtionalArgs);
                if (exception != null) {
                    throw exception;
                }
                if (!game.prepareStart(sender.getEntityWorld())) {
                    throw new CommandException("Error occured when launching game! Check logs and contact GAME AUTHOR about this issue!");
                }
                game.updateDataToClients(sender.getEntityWorld());
                sendCommandFeedback(sender, "Started game");
                break;
            }

            case "stop": {
                if (!gameData.getCurrentGame().isRunning() || gameData.isInactiveGame() || gameData.getLobby() == null) {
                    throw new CommandException("There is no active game!");
                }
                gameData.getCurrentGame().stopGame(sender.getEntityWorld());
                sendCommandFeedback(sender, "Stopped game");
                gameData.getCurrentGame().updateDataToClients(sender.getEntityWorld());
                break;
            }

            case "help": {
                if (!(sender instanceof EntityPlayer)) {
                    throw new WrongUsageException("Only players can execute this command!");
                }
                EntityPlayer player = (EntityPlayer) sender;
                sendMessage(player, "Commands:");
                sendMessage(player, "start -> starts game");
                sendMessage(player, "stop -> stops game");
                sendMessage(player, "info -> information about current game settings");
                sendMessage(player, "mode [name: String] -> binds the game mode to this world");
                sendMessage(player, "map [x: int, z: int, size: int] -> creates map border");
                sendMessage(player, "lobby [x: int, y: int, z: int, radius: int] -> creates lobby for this world");
                sendMessage(player, "");
                sendMessage(player, "Available game modes:");
                for (ResourceLocation location : GameRegistry.getValuesPaths()) {
                    sendMessage(player, "- " + location.getResourcePath());
                }
                break;
            }

            case "info": {
                if (!(sender instanceof EntityPlayer)) {
                    throw new WrongUsageException("Only players can execute this command!");
                }
                EntityPlayer player = (EntityPlayer) sender;
                sendMessage(player, "=====[ Game information ]=====");
                sendMessage(player, "Active: " + (gameData.getCurrentGame().isRunning() ? "yes" : "no"));
                sendMessage(player, "Map: [" + gameData.getMapCenter().getX() + ", " + gameData.getMapCenter().getZ() + "]; Size: " + gameData.getMapSize() + " blocks");
                sendMessage(player, "Locations: " + gameData.getSpawnLocations().size());
                Game game = gameData.getCurrentGame();
                sendMessage(player, "Game: " + (gameData.isInactiveGame() ? "none" : game.registryName));
                if (gameData.isInactiveGame()) {
                    break;
                }
                sendMessage(player, "Alive players: " + game.onlinePlayers);
                sendMessage(player, "Time: " + (game.getGameTimer() / 20) + "s");
                String[] data = game.getGameInformation() != null ? game.getGameInformation().gameInformation : null;
                if (data == null) break;
                sendMessage(player, "Game mode author: " + game.getGameInformation().author);
                for (int i = 0; i < data.length; i++) {
                    sendMessage(player, data[i]);
                }
                String[] arguments = game.getGameInformation().commandArguments;
                if (arguments == null) break;
                sendMessage(player, "Optional launch arguments:");
                for (int i = 0; i < arguments.length; i++) {
                    sendMessage(player, (i + 1) + ": " + arguments[i]);
                }
                break;
            }

            case "mode": {
                if (args.length < 2) {
                    throw new WrongUsageException("You must specify game mode! For all game modes use /game help");
                }
                Game game = GameRegistry.findGameInRegistry(args[1]);
                if (game == null) {
                    throw new CommandException("Unknown game!");
                }
                gameData.setGame(game);
                sendCommandFeedback(sender, "Successfully selected game " + game.registryName);
                break;
            }

            case "map": {
                if (args.length < 4) {
                    throw new WrongUsageException("You must specify X, Z position and map size!");
                }
                int x;
                int z;
                int size;
                try {
                    x = Integer.parseInt(args[1]);
                    z = Integer.parseInt(args[2]);
                    size = Integer.parseInt(args[3]);
                } catch (NumberFormatException e) {
                    throw new CommandException("Invalid number!");
                }
                gameData.setMapCenter(x, z, size);
                sendCommandFeedback(sender, "Successfully created game map");
                break;
            }

            case "lobby": {
                if (args.length < 5) {
                    throw new WrongUsageException("You must specify X,Y,Z position and radius!");
                }
                int x;
                int y;
                int z;
                int radius;
                try {
                    x = Integer.parseInt(args[1]);
                    y = Integer.parseInt(args[2]);
                    z = Integer.parseInt(args[3]);
                    radius = Integer.parseInt(args[4]);
                } catch (NumberFormatException e) {
                    throw new CommandException("Invalid number!");
                }
                Lobby lobby = new Lobby(new BlockPos(x, y, z), radius);
                gameData.setLobby(lobby);
                sendCommandFeedback(sender, "Created new game lobby. Now you can use '/leave' to get there");
                break;
            }

            case "location": {
                if (args.length < 2) {
                    throw new CommandException("Unknown operation! Use /game location [add;remove;list]");
                }
                switch (args[1]) {
                    case "add": {
                        if (args.length < 6) {
                            throw new WrongUsageException("Unknown arguments! Use /game location add [x] [y] [z] [name]");
                        }
                        int x;
                        int y;
                        int z;
                        try {
                            x = Integer.parseInt(args[2]);
                            y = Integer.parseInt(args[3]);
                            z = Integer.parseInt(args[4]);
                        } catch (NumberFormatException e) {
                            throw new CommandException("Invalid number!");
                        }
                        String name = args[5];
                        gameData.addSpawnLocation(new MapLocation(name, new BlockPos(x, y, z)));
                        sendCommandFeedback(sender, "Added new map location into this world");
                        break;
                    }

                    case "remove": {
                        if (args.length < 3) {
                            throw new WrongUsageException("Unknown arguments! Use /game location remove [locationName]");
                        }
                        String locName = args[2];
                        MapLocation location = MapLocation.findLocation(locName, gameData);
                        if (location == null) {
                            throw new CommandException("There is no such location with name " + locName);
                        }
                        gameData.getSpawnLocations().remove(location);
                        sendCommandFeedback(sender, "Removed map location: " + location);
                        break;
                    }

                    case "list": {
                        for (int i = 0; i < gameData.getSpawnLocations().size(); i++) {
                            MapLocation location = gameData.getSpawnLocations().get(i);
                            sender.sendMessage(new TextComponentString("- " + location.toString()));
                        }
                        break;
                    }

                    default:
                        throw new WrongUsageException("Unknown argument! Use /game location [add;remove;list]");
                }
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
        if (sender.sendCommandFeedback()) {
            sender.sendMessage(new TextComponentString(feedback));
        }
    }
}

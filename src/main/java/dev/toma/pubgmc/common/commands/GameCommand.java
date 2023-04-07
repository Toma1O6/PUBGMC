package dev.toma.pubgmc.common.commands;

import dev.toma.pubgmc.api.Lobby;
import dev.toma.pubgmc.api.games.Game;
import dev.toma.pubgmc.common.capability.IGameData;
import dev.toma.pubgmc.common.commands.core.*;
import dev.toma.pubgmc.common.commands.core.arg.*;
import dev.toma.pubgmc.init.GameRegistry;
import dev.toma.pubgmc.util.helper.TextComponentHelper;
import dev.toma.pubgmc.world.MapLocation;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.event.ClickEvent;
import net.minecraft.world.World;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.regex.Pattern;

public class GameCommand extends AbstractCommand {

    private static final Pattern NAME_PATTERN = Pattern.compile(".+");
    private static final String NAME_ERROR = "Name cannot be empty!";

    private static final ITextComponent STARTED_FEEDBACK = new TextComponentTranslation("commands.pubgmc.game.started");
    private static final ITextComponent STOPPED_FEEDBACK = new TextComponentTranslation("commands.pubgmc.game.stopped");
    private static final ITextComponent TYPE_CHANGE_FEEDBACK = new TextComponentTranslation("commands.pubgmc.game.type_selected");
    private static final ITextComponent MAP_FEEDBACK = new TextComponentTranslation("commands.pubgmc.game.map_created");
    private static final ITextComponent LOBBY_FEEDBACK = new TextComponentTranslation("commands.pubgmc.game.lobby_created");
    private static final ITextComponent LOCATION_ADD_FEEDBACK = new TextComponentTranslation("commands.pubgmc.game.location_added");
    private static final ITextComponent LOCATION_REMOVE_FEEDBACK = new TextComponentTranslation("commands.pubgmc.game.location_removed");
    private static final ITextComponent LOCATION_LIST_HEADER = new TextComponentTranslation("commands.pubgmc.game.location_list");
    private static final ITextComponent MAP_INFO_HEADER = new TextComponentTranslation("commands.pubgmc.game.map.header");
    private static final ITextComponent LOBBY_INFO_HEADER = new TextComponentTranslation("commands.pubgmc.game.lobby.header");
    private static final ITextComponent[] HELP_TEXT = {
            new TextComponentTranslation("commands.pubgmc.game.help.header"),
            new TextComponentTranslation("commands.pubgmc.game.help.start"),
            new TextComponentTranslation("commands.pubgmc.game.help.stop"),
            new TextComponentTranslation("commands.pubgmc.game.help.lobby"),
            new TextComponentTranslation("commands.pubgmc.game.help.map")
    };

    private static final String ARG_START_PARAMS = "startParameters";
    private static final String ARG_GAME_TYPE = "gameType";
    private static final String ARG_MAP_X = "mapX";
    private static final String ARG_MAP_Z = "mapZ";
    private static final String ARG_POSITION = "pos";
    private static final String ARG_SIZE = "size";
    private static final String ARG_NAME = "name";

    private static final CommandTree COMMAND = CommandTree.Builder.command("game")
            .usage("/game [start|stop|type|map|lobby|locations|help]")
            .permissionLevel(2)
            .defaultExecutorPropagationStrategy(DefaultExecutorPropagationStrategy.LAST_NODE)
            .executes(GameCommand::executeDefault)
            .node(
                    CommandNodeProvider.literal("start")
                            .executes(GameCommand::startGame)
                            .node(
                                    CommandNodeProvider.argument(ARG_START_PARAMS, CustomStringArguments.arguments())
                            )
            )
            .node(
                    CommandNodeProvider.literal("stop")
                            .executes(GameCommand::stopGame)
            )
            .node(
                    CommandNodeProvider.literal("type")
                            .executes(GameCommand::selectGameType)
                            .node(
                                    CommandNodeProvider.argument(ARG_GAME_TYPE, MapLookupArgument.map(GameRegistry.REGISTRY, ResourceLocation::new))
                            )
            )
            .node(
                    CommandNodeProvider.literal("map")
                            .executes(GameCommand::configureMap)
                            .node(
                                    CommandNodeProvider.argument(ARG_MAP_X, IntArgument.unboundedInt())
                                            .suggests(GameCommand::suggestPositionX)
                                            .node(
                                                    CommandNodeProvider.argument(ARG_MAP_Z, IntArgument.unboundedInt())
                                                            .suggests(GameCommand::suggestPositionZ)
                                                            .node(
                                                                    CommandNodeProvider.argument(ARG_SIZE, IntArgument.min(16))
                                                            )
                                            )
                            )
            )
            .node(
                    CommandNodeProvider.literal("lobby")
                            .executes(GameCommand::configureLobby)
                            .node(
                                    CommandNodeProvider.argument(ARG_POSITION, BlockPosArgument.blockpos())
                                            .node(
                                                    CommandNodeProvider.argument(ARG_SIZE, IntArgument.min(1))
                                            )
                            )
            )
            .node(
                    CommandNodeProvider.literal("location")
                            .executes(GameCommand::listLocations)
                            .node(
                                    CommandNodeProvider.literal("add")
                                            .executes(GameCommand::addNewLocation)
                                            .node(
                                                    CommandNodeProvider.argument(ARG_POSITION, BlockPosArgument.blockpos())
                                                            .node(
                                                                    CommandNodeProvider.argument(ARG_NAME, StringArgument.stringArgument(NAME_PATTERN, NAME_ERROR))
                                                            )
                                            )
                            )
                            .node(
                                    CommandNodeProvider.literal("remove")
                                            .executes(GameCommand::removeLocation)
                                            .node(
                                                    CommandNodeProvider.argument(ARG_NAME, StringArgument.stringArgument(NAME_PATTERN, NAME_ERROR))
                                            )
                            )
            )
            .node(
                    CommandNodeProvider.literal("help")
                            .executes(GameCommand::displayUserHelp)
            )
            .build();

    public GameCommand() {
        super(COMMAND);
    }

    private static void executeDefault(CommandContext context) throws CommandException {
        throw new WrongUsageException("Missing arguments. See /help game for more details");
    }

    private static void startGame(CommandContext context) throws CommandException {
        IGameData gameData = getGameData(context);
        if (gameData.getMapSize() <= 0) {
            throw new WrongUsageException("You must first set up a map. Use '/game map' command for that");
        }
        if (gameData.getLobby() == null) {
            throw new WrongUsageException("You must define lobby. Use '/game lobby' command for that");
        }
        Game game = gameData.getCurrentGame();
        if (game.isRunning()) {
            throw new WrongUsageException("There is already game in progress");
        }
        if (gameData.isInactiveGame()) {
            throw new WrongUsageException("This game type cannot be started. Select different one via '/game type' command");
        }
        MinecraftServer server = context.getServer();
        ICommandSender sender = context.getSender();
        World world = sender.getEntityWorld();
        String[] startArgs = context.<String[]>getArgument(ARG_START_PARAMS)
                .orElse(new String[0]);
        game.onGameStartCommandExecuted(sender, server, startArgs);
        if (!game.prepareStart(world)) {
            throw new WrongUsageException("Error occured when launching game! Check logs and contact GAME AUTHOR about this issue!");
        }
        game.updateDataToClients(world);
        sender.sendMessage(STARTED_FEEDBACK);
    }

    private static void stopGame(CommandContext context) throws CommandException {
        IGameData gameData = getGameData(context);
        Game game = gameData.getCurrentGame();
        if (gameData.getLobby() == null || gameData.isInactiveGame() || !game.isRunning()) {
            throw new WrongUsageException("There is no active game");
        }
        ICommandSender sender = context.getSender();
        World world = sender.getEntityWorld();
        game.stopGame(world);
        game.updateDataToClients(world);
        sender.sendMessage(STOPPED_FEEDBACK);
    }

    private static void selectGameType(CommandContext context) throws CommandException {
        IGameData gameData = getGameData(context);
        Game game = context.getArgumentMandatory(ARG_GAME_TYPE);
        gameData.setGame(game);
        context.getSender().sendMessage(TYPE_CHANGE_FEEDBACK);
    }

    private static void configureMap(CommandContext context) throws CommandException {
        IGameData gameData = getGameData(context);
        ICommandSender sender = context.getSender();
        Optional<Integer> optionalX = context.getArgument(ARG_MAP_X);
        if (optionalX.isPresent()) {
            int mapX = optionalX.get();
            int mapZ = context.getArgumentMandatory(ARG_MAP_Z);
            int size = context.getArgumentMandatory(ARG_SIZE);
            gameData.setMapCenter(mapX, mapZ, size);
            sender.sendMessage(MAP_FEEDBACK);
        } else {
            BlockPos mapCenter = gameData.getMapCenter();
            sender.sendMessage(MAP_INFO_HEADER);
            if (mapCenter == null) {
                sender.sendMessage(TextComponentHelper.applyColor(TextComponentHelper.UNDEFINED, TextFormatting.RED));
            } else {
                ITextComponent mapPosComponent = new TextComponentTranslation("commands.pubgmc.game.map.center", mapCenter.getX(), mapCenter.getZ());
                mapPosComponent.getStyle().setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, getTeleportCommandAsString(mapCenter)));
                sender.sendMessage(mapPosComponent);
                sender.sendMessage(new TextComponentTranslation("commands.pubgmc.game.map.size", gameData.getMapSize()));
            }
        }
    }

    private static void configureLobby(CommandContext context) throws CommandException {
        IGameData gameData = getGameData(context);
        ICommandSender sender = context.getSender();
        Optional<BlockPos> optional = context.getArgument(ARG_POSITION);
        if (optional.isPresent()) {
            BlockPos pos = optional.get();
            int size = context.getArgumentMandatory(ARG_SIZE);
            Lobby lobby = new Lobby(pos, size);
            gameData.setLobby(lobby);
            sender.sendMessage(LOBBY_FEEDBACK);
        } else {
            sender.sendMessage(LOBBY_INFO_HEADER);
            Lobby lobby = gameData.getLobby();
            if (lobby != null) {
                BlockPos pos = lobby.center;
                ITextComponent component = new TextComponentTranslation("commands.pubgmc.game.lobby.center", pos.getX(), pos.getY(), pos.getZ());
                component.getStyle().setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, getTeleportCommandAsString(pos)));
                sender.sendMessage(component);
                sender.sendMessage(new TextComponentTranslation("commands.pubgmc.game.lobby.size", lobby.radius));
            } else {
                sender.sendMessage(TextComponentHelper.applyColor(TextComponentHelper.UNDEFINED, TextFormatting.RED));
            }
        }
    }

    private static void listLocations(CommandContext context) throws CommandException {
        IGameData gameData = getGameData(context);
        List<MapLocation> locations = gameData.getSpawnLocations();
        ICommandSender sender = context.getSender();
        sender.sendMessage(LOCATION_LIST_HEADER);
        for (MapLocation location : locations) {
            BlockPos pos = location.pos();
            ITextComponent component = new TextComponentString(String.format("%s - at [%d, %d, %d]", location.name(), pos.getX(), pos.getY(), pos.getZ()));
            component.getStyle().setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, getTeleportCommandAsString(pos)));
            sender.sendMessage(component);
        }
    }

    private static void addNewLocation(CommandContext context) throws CommandException {
        IGameData gameData = getGameData(context);
        BlockPos pos = context.getArgumentMandatory(ARG_POSITION);
        String name = context.getArgumentMandatory(ARG_NAME);
        gameData.addSpawnLocation(new MapLocation(name, pos));
        context.getSender().sendMessage(LOCATION_ADD_FEEDBACK);
    }

    private static void removeLocation(CommandContext context) throws CommandException {
        IGameData gameData = getGameData(context);
        String name = context.getArgumentMandatory(ARG_NAME);
        MapLocation location = MapLocation.findLocation(name, gameData);
        if (location == null) {
            throw new WrongUsageException("There is no such location with name '" + name + "'");
        }
        gameData.getSpawnLocations().remove(location);
        context.getSender().sendMessage(LOCATION_REMOVE_FEEDBACK);
    }

    private static void displayUserHelp(CommandContext context) {
        ICommandSender sender = context.getSender();
        for (ITextComponent component : HELP_TEXT) {
            sender.sendMessage(component);
        }
    }

    private static List<String> suggestPositionX(SuggestionProvider.Context context) {
        return getCoordinateForPositionSuggestion(context, Vec3i::getX);
    }

    private static List<String> suggestPositionZ(SuggestionProvider.Context context) {
        return getCoordinateForPositionSuggestion(context, Vec3i::getZ);
    }

    private static List<String> getCoordinateForPositionSuggestion(SuggestionProvider.Context context, Function<BlockPos, Integer> provider) {
        ICommandSender sender = context.getSender();
        BlockPos pos = sender.getPosition();
        int coordinate = provider.apply(pos);
        return Collections.singletonList(String.valueOf(coordinate));
    }

    private static String getTeleportCommandAsString(BlockPos pos) {
        return "/tp @p " + pos.getX() + " " + pos.getY() + " " + pos.getZ();
    }
}

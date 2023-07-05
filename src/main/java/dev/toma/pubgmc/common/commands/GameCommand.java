package dev.toma.pubgmc.common.commands;

import dev.toma.pubgmc.api.PubgmcRegistries;
import dev.toma.pubgmc.api.capability.GameData;
import dev.toma.pubgmc.api.game.Game;
import dev.toma.pubgmc.api.game.GameConfiguration;
import dev.toma.pubgmc.api.game.GameException;
import dev.toma.pubgmc.api.game.GameType;
import dev.toma.pubgmc.api.game.map.GameLobby;
import dev.toma.pubgmc.api.game.map.GameMap;
import dev.toma.pubgmc.api.game.map.GameMapPoint;
import dev.toma.pubgmc.api.game.map.GameMapPointType;
import dev.toma.pubgmc.api.util.Position2;
import dev.toma.pubgmc.common.commands.core.*;
import dev.toma.pubgmc.common.commands.core.arg.BlockPosArgument;
import dev.toma.pubgmc.common.commands.core.arg.IntArgument;
import dev.toma.pubgmc.common.commands.core.arg.PubgmcRegistryArgument;
import dev.toma.pubgmc.common.commands.core.arg.StringArgument;
import dev.toma.pubgmc.common.games.GameTypes;
import dev.toma.pubgmc.common.games.NoGame;
import dev.toma.pubgmc.common.games.util.GameConfigurationManager;
import dev.toma.pubgmc.util.helper.GameHelper;
import dev.toma.pubgmc.util.helper.TextComponentHelper;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.SyntaxErrorException;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.event.ClickEvent;
import net.minecraft.util.text.event.HoverEvent;
import net.minecraft.world.World;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class GameCommand extends AbstractCommand {

    private static final ITextComponent TEXT_EXECUTE_CMD = new TextComponentTranslation("commands.pubgmc.game.leave.confirm.hover_cmd_text");
    private static final String ARG_GAME_TYPE = "gameType";
    private static final String ARG_MAP_NAME = "mapName";
    private static final String ARG_MAP_CENTER_X = "mapCreateCenterX";
    private static final String ARG_MAP_CENTER_Z = "mapCreateCenterZ";
    private static final String ARG_MAP_SIZE = "mapCreateSize";
    private static final String ARG_MAP_CORNER_AX = "mapCreateCornerAX";
    private static final String ARG_MAP_CORNER_AZ = "mapCreateCornerAZ";
    private static final String ARG_MAP_CORNER_BX = "mapCreateCornerBX";
    private static final String ARG_MAP_CORNER_BZ = "mapCreateCornerBZ";
    private static final String ARG_LOBBY_CENTER = "lobbyCenter";
    private static final String ARG_POINT_TYPE = "pointType";

    private static final CommandTree COMMAND = CommandTree.Builder.command("game")
            .usage("/game [join|leave|select|init|start|stop|map|lobby]")
            .permissionLevel(0)
            .defaultExecutorPropagationStrategy(NodePropagationStrategy.LAST_NODE)
            .executes(GameCommand::executeDefault)
            .node(
                    CommandNodeProvider.literal("select")
                            .permissionLevel(2)
                            .executes(GameCommand::selectGameType)
                            .node(
                                    CommandNodeProvider.argument(ARG_GAME_TYPE, PubgmcRegistryArgument.registry(PubgmcRegistries.GAME_TYPES))
                            )
            )
            .node(
                    CommandNodeProvider.literal("init")
                            .permissionLevel(2)
                            .executes(GameCommand::initGame)
            )
            .node(
                    CommandNodeProvider.literal("start")
                            .permissionLevel(2)
                            .executes(GameCommand::startGame)
                            .node(
                                    CommandNodeProvider.argument(ARG_MAP_NAME, StringArgument.stringArgument(GameMap.ALLOWED_NAME_PATTERN, "Invalid map name format"))
                            )
            )
            .node(
                    CommandNodeProvider.literal("stop")
                            .permissionLevel(2)
                            .executes(GameCommand::stopGame)
            )
            .node(
                    CommandNodeProvider.literal("map")
                            .permissionLevel(2)
                            .executes(GameCommand::executeMapNoArguments)
                            .node(
                                    CommandNodeProvider.argument(ARG_MAP_NAME, StringArgument.stringArgument(GameMap.ALLOWED_NAME_PATTERN, "Invalid map name format"))
                                            .node(
                                                    CommandNodeProvider.literal("delete")
                                                            .executes(GameCommand::deleteMapByName)
                                            )
                                            .node(
                                                    CommandNodeProvider.literal("clearPois")
                                                            .executes(GameCommand::deleteMapPoints)
                                            )
                                            .node(
                                                    CommandNodeProvider.literal("addPoint")
                                                            .executes(GameCommand::addMapPoint)
                                                            .node(
                                                                    CommandNodeProvider.argument(ARG_POINT_TYPE, PubgmcRegistryArgument.registry(PubgmcRegistries.GAME_MAP_POINTS))
                                                                            .node(
                                                                                    CommandNodeProvider.argument(ARG_MAP_NAME, StringArgument.stringArgument(GameMap.ALLOWED_NAME_PATTERN, "Invalid map name format"))
                                                                            )
                                                            )
                                            )
                            )
                            .node(
                                    CommandNodeProvider.literal("create")
                                            .node(
                                                    CommandNodeProvider.argument(ARG_MAP_NAME, StringArgument.stringArgument(GameMap.ALLOWED_NAME_PATTERN, "Map name must consist of atleast one a-zA-Z0-9 or _ character"))
                                                            .node(
                                                                    CommandNodeProvider.literal("center")
                                                                            .executes(GameCommand::registerMapByCenter)
                                                                            .node(
                                                                                    CommandNodeProvider.argument(ARG_MAP_CENTER_X, IntArgument.unboundedInt())
                                                                                            .suggests(GameCommand::suggestCurrentX)
                                                                                            .node(
                                                                                                    CommandNodeProvider.argument(ARG_MAP_CENTER_Z, IntArgument.unboundedInt())
                                                                                                            .suggests(GameCommand::suggestCurrentZ)
                                                                                                            .node(
                                                                                                                    CommandNodeProvider.argument(ARG_MAP_SIZE, IntArgument.min(1))
                                                                                                            )
                                                                                            )
                                                                            )
                                                            )
                                                            .node(
                                                                    CommandNodeProvider.literal("corners")
                                                                            .executes(GameCommand::registerMapByCorners)
                                                                            .node(
                                                                                    CommandNodeProvider.argument(ARG_MAP_CORNER_AX, IntArgument.unboundedInt())
                                                                                            .suggests(GameCommand::suggestCurrentX)
                                                                                            .node(
                                                                                                    CommandNodeProvider.argument(ARG_MAP_CORNER_AZ, IntArgument.unboundedInt())
                                                                                                            .suggests(GameCommand::suggestCurrentZ)
                                                                                                            .node(
                                                                                                                    CommandNodeProvider.argument(ARG_MAP_CORNER_BX, IntArgument.unboundedInt())
                                                                                                                            .suggests(GameCommand::suggestCurrentX)
                                                                                                                            .node(
                                                                                                                                    CommandNodeProvider.argument(ARG_MAP_CORNER_BZ, IntArgument.unboundedInt())
                                                                                                                                            .suggests(GameCommand::suggestCurrentZ)
                                                                                                                            )
                                                                                                            )
                                                                                            )
                                                                            )
                                                            )
                                            )
                            )
            )
            .node(
                    CommandNodeProvider.literal("lobby")
                            .permissionLevel(2)
                            .executes(GameCommand::printLobbyInformation)
                            .node(
                                    CommandNodeProvider.argument(ARG_LOBBY_CENTER, BlockPosArgument.blockpos())
                                            .executes(GameCommand::createLobby)
                            )
            )
            .node(
                    CommandNodeProvider.literal("leave")
                            .permissionLevel(0)
                            .executes(context -> leaveGame(context, false))
                            .node(
                                    CommandNodeProvider.literal("confirm")
                                            .executes(context -> leaveGame(context, true))
                            )
            )
            .node(
                    CommandNodeProvider.literal("join")
                            .permissionLevel(0)
                            .executes(GameCommand::joinGame)
            )
            .node(
                    CommandNodeProvider.literal("reloadConfigs")
                            .permissionLevel(2)
                            .executes(GameCommand::reloadGameConfigurations)
            )
            .build();

    public GameCommand() {
        super(COMMAND);
    }

    private static void executeDefault(CommandContext context) throws CommandException {
        throw new WrongUsageException("Not enough arguments. See /help game for more details");
    }

    private static void executeMapNoArguments(CommandContext context) throws CommandException {
        throw new WrongUsageException("Not enough arguments. Use '/game map create <name> ...' or '/game map <name> [delete|clearPois]'");
    }

    private static void reloadGameConfigurations(CommandContext context) throws CommandException {
        GameConfigurationManager.loadConfigurations();
        context.getSender().sendMessage(new TextComponentTranslation("commands.pubgmc.game.config.reloaded"));
    }

    private static void selectGameType(CommandContext context) throws CommandException {
        GameType<?, ?> gameType = context.getArgumentMandatory(ARG_GAME_TYPE);
        GameData gameData = getGameData(context);
        gameData.setSelectedGameType(gameType);
        gameData.sendGameDataToClients();
        context.getSender().sendMessage(new TextComponentTranslation("commands.pubgmc.game.type_selected", gameType.getIdentifier()));
    }

    @SuppressWarnings("unchecked")
    private static <CFG extends GameConfiguration, G extends Game<CFG>> void initGame(CommandContext context) throws CommandException {
        GameData data = getGameData(context);
        GameType<CFG, G> type = (GameType<CFG, G>) data.getSelectedGameType();
        if (type == GameTypes.NO_GAME) {
            throw new WrongUsageException("You must first select game type. Use /game select <gameType>");
        }
        Game<?> current = data.getCurrentGame();
        if (current != NoGame.INSTANCE && current.isStarted()) {
            throw new WrongUsageException("There is already active game");
        }
        validateGameLobbyDefined(data);
        CFG config = GameConfigurationManager.getConfiguration(type);
        if (config == null) {
            throw new WrongUsageException("Selected game type has no configuration. Create one by command '/game configure " + type.getIdentifier().toString() + "'");
        }
        GameType.GameConstructor<CFG, G> constructor = type.getConstructor();
        G game;
        try {
            game = constructor.constructGameInstance(UUID.randomUUID(), config);
        } catch (GameException e) {
            throw new SyntaxErrorException("Unable to initialize game: " + e.getMessage());
        }
        ICommandSender sender = context.getSender();
        World world = sender.getEntityWorld();
        game.onGameInit(world);
        data.setActiveGame(game);
        data.sendGameDataToClients();
        sender.sendMessage(new TextComponentTranslation("commands.pubgmc.game.initialized"));
    }

    private static void startGame(CommandContext context) throws CommandException {
        GameData data = getGameData(context);
        Optional<String> mapNameOpt = context.getArgument(ARG_MAP_NAME);
        String actualMapName = mapNameOpt.orElseGet(() -> {
            List<String> mapNames = new ArrayList<>(data.getRegisteredGameMaps().keySet());
            if (mapNames.size() == 1) {
                return mapNames.get(0);
            }
            return null;
        });
        Game<?> game = data.getCurrentGame();
        if (game == NoGame.INSTANCE) {
            throw new WrongUsageException("No game is currenly prepared for starting. Use '/game init' command");
        }
        if (game.isStarted()) {
            throw new WrongUsageException("Game is already started");
        }
        if (actualMapName == null) {
            throw new WrongUsageException("You must specify game map. Use /game start <mapName>");
        }
        GameMap map = data.getGameMap(actualMapName);
        if (map == null) {
            throw new WrongUsageException("No map is registered under " + actualMapName + " name");
        }
        data.setActiveGameMapName(actualMapName);
        ICommandSender sender = context.getSender();
        World world = sender.getEntityWorld();
        try {
            game.performGameMapValidations(world, map);
            game.onGameStart(world);
        } catch (GameException e) {
            data.setActiveGameMapName(actualMapName);
            throw new WrongUsageException("Unable to start game: " + e.getMessage());
        }
        sender.sendMessage(new TextComponentTranslation("commands.pubgmc.game.started"));
        data.sendGameDataToClients();
    }

    private static void stopGame(CommandContext context) throws CommandException {
        GameData data = getGameData(context);
        Game<?> game = data.getCurrentGame();
        if (!game.isStarted()) {
            throw new WrongUsageException("There is no active game");
        }
        ICommandSender sender = context.getSender();
        GameHelper.stopGame(sender.getEntityWorld());
        sender.sendMessage(new TextComponentTranslation("commands.pubgmc.game.stopped"));
    }

    private static void registerMapByCenter(CommandContext context) throws CommandException {
        String mapName = context.getArgumentMandatory(ARG_MAP_NAME);
        int centerX = context.getArgumentMandatory(ARG_MAP_CENTER_X);
        int centerZ = context.getArgumentMandatory(ARG_MAP_CENTER_Z);
        int size = context.getArgumentMandatory(ARG_MAP_SIZE);
        double x = centerX + 0.5;
        double z = centerZ + 0.5;
        Position2 min = new Position2(x - size, z - size);
        Position2 max = new Position2(x + size, z + size);
        registerMap(context, mapName, min, max);
    }

    private static void registerMapByCorners(CommandContext context) throws CommandException {
        String mapName = context.getArgumentMandatory(ARG_MAP_NAME);
        int ax = context.getArgumentMandatory(ARG_MAP_CORNER_AX);
        int az = context.getArgumentMandatory(ARG_MAP_CORNER_AZ);
        int bx = context.getArgumentMandatory(ARG_MAP_CORNER_BX);
        int bz = context.getArgumentMandatory(ARG_MAP_CORNER_BZ);
        int minX = Math.min(ax, bx);
        int minZ = Math.min(az, bz);
        int maxX = Math.max(ax, bx);
        int maxZ = Math.max(az, bz);
        Position2 min = new Position2(minX + 0.5, minZ + 0.5);
        Position2 max = new Position2(maxX + 0.5, maxZ + 0.5);
        registerMap(context, mapName, min, max);
    }

    private static void registerMap(CommandContext context, String name, Position2 min, Position2 max) throws CommandException {
        GameData data = getGameData(context);
        GameMap map = data.getGameMap(name);
        if (map != null) {
            throw new WrongUsageException("Map with name '" + name + "' already exists!");
        }
        GameMap gameMap = new GameMap(name, min, max);
        data.registerGameMap(gameMap);
        data.sendGameDataToClients();
        context.getSender().sendMessage(new TextComponentTranslation("commands.pubgmc.game.map.created", name));
    }

    private static void deleteMapByName(CommandContext context) throws CommandException {
        String mapName = context.getArgumentMandatory(ARG_MAP_NAME);
        GameData data = getGameData(context);
        GameMap map = data.getGameMap(mapName);
        if (map == null) {
            throw new WrongUsageException("No map found by name '" + mapName + "'");
        }
        data.deleteGameMap(mapName);
        data.sendGameDataToClients();
        context.getSender().sendMessage(new TextComponentTranslation("commands.pubgmc.game.map.deleted", mapName));
    }

    private static void deleteMapPoints(CommandContext context) throws CommandException {
        String mapName = context.getArgumentMandatory(ARG_MAP_NAME);
        GameData data = getGameData(context);
        GameMap map = data.getGameMap(mapName);
        if (map == null) {
            throw new WrongUsageException("No map found by name '" + mapName + "'");
        }
        map.deletePoints();
        data.sendGameDataToClients();
        context.getSender().sendMessage(new TextComponentTranslation("commands.pubgmc.game.map.pois_deleted", mapName));
    }

    private static void addMapPoint(CommandContext context) throws CommandException {
        GameData gameData = getGameData(context);
        GameMapPointType<?> pointType = context.getArgumentMandatory(ARG_POINT_TYPE);
        ICommandSender sender = context.getSender();
        BlockPos pos = sender.getPosition();
        Optional<String> providedMapName = context.getArgument(ARG_MAP_NAME);
        GameMap map;
        if (providedMapName.isPresent()) {
            map = gameData.getGameMap(providedMapName.get());
            if (map == null) {
                throw new WrongUsageException("No map found by name '" + providedMapName.get() + "'");
            }
        } else {
            List<GameMap> insideMaps = gameData.getRegisteredGameMaps().values().stream()
                    .filter(gameMap -> gameMap.isWithin(pos))
                    .collect(Collectors.toList());
            if (insideMaps.size() != 1) {
                throw new WrongUsageException("You must specify map name");
            }
            map = insideMaps.get(0);
        }
        GameMapPoint point = pointType.createPointInstance(pos, sender.getEntityWorld(), map);
        map.setMapPoint(pos, point);
        sender.sendMessage(new TextComponentTranslation("commands.pubgmc.game.map.poi_added", pointType.getIdentifier(), map.getMapName()));
        gameData.sendGameDataToClients();
    }

    private static void printLobbyInformation(CommandContext context) throws CommandException {
        GameData data = getGameData(context);
        GameLobby lobby = data.getGameLobby();
        ICommandSender sender = context.getSender();
        if (lobby == null) {
            sender.sendMessage(new TextComponentTranslation("commands.pubgmc.game.lobby.not_created"));
        } else {
            BlockPos pos = lobby.get();
            ITextComponent component = new TextComponentTranslation("commands.pubgmc.game.lobby.info", pos.getX(), pos.getY(), pos.getZ());
            Style style = component.getStyle();
            style.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/tp @p " + pos.getX() + " " + pos.getY() + " " + pos.getZ()));
            style.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, TextComponentHelper.CLICK_TO_TELEPORT));
            sender.sendMessage(component);
        }
    }

    private static void createLobby(CommandContext context) throws CommandException {
        GameData data = getGameData(context);
        BlockPos pos = context.getArgumentMandatory(ARG_LOBBY_CENTER);
        GameLobby lobby = new GameLobby(pos);
        data.setGameLobby(lobby);
        data.sendGameDataToClients();
        context.getSender().sendMessage(new TextComponentTranslation("commands.pubgmc.game.lobby.created"));
    }

    private static void leaveGame(CommandContext context, boolean confirmed) throws CommandException {
        GameData gameData = getGameData(context);
        Game<?> game = gameData.getCurrentGame();
        if (game == NoGame.INSTANCE || !game.isStarted()) {
            throw new WrongUsageException("There is no active game");
        }
        ICommandSender sender = context.getSender();
        Entity entity = sender.getCommandSenderEntity();
        if (!(entity instanceof EntityPlayer)) {
            throw new WrongUsageException("This subcommand can be only executed by player");
        }
        EntityPlayer player = (EntityPlayer) entity;
        if (confirmed) {
            GameLobby lobby = gameData.getGameLobby();
            if (lobby == null) {
                throw new WrongUsageException("There is no lobby defined");
            }
            if (game.playerLeaveGame(player)) {
                GameHelper.resetPlayerData(player);
                lobby.teleport(player);
                player.sendMessage(new TextComponentTranslation("commands.pubgmc.game.leave.success"));
            } else {
                player.sendMessage(new TextComponentTranslation("commands.pubgmc.game.leave.fail"));
            }
        } else {
            String command = "/game leave confirm";
            String commandText = TextFormatting.GREEN + command;
            ITextComponent component = new TextComponentTranslation("commands.pubgmc.game.leave.confirm", commandText);
            Style style = component.getStyle();
            style.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, command));
            style.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, TEXT_EXECUTE_CMD));
            player.sendMessage(component);
        }
    }

    private static void joinGame(CommandContext context) throws CommandException {
        GameData gameData = getGameData(context);
        Game<?> game = gameData.getCurrentGame();
        if (game == NoGame.INSTANCE) {
            throw new WrongUsageException("There is no active game");
        }
        ICommandSender sender = context.getSender();
        Entity entity = sender.getCommandSenderEntity();
        if (!(entity instanceof EntityPlayer)) {
            throw new WrongUsageException("This subcommand can be only executed by player");
        }
        EntityPlayer player = (EntityPlayer) entity;
        if (game.playerJoinGame(player)) {
            player.sendMessage(new TextComponentTranslation("commands.pubgmc.game.join.success"));
        } else {
            player.sendMessage(new TextComponentTranslation("commands.pubgmc.game.join.fail"));
        }
    }

    private static List<String> suggestCurrentX(SuggestionProvider.Context context) {
        return suggestCoordinate(context, Vec3i::getX);
    }

    private static List<String> suggestCurrentZ(SuggestionProvider.Context context) {
        return suggestCoordinate(context, Vec3i::getZ);
    }

    private static List<String> suggestCoordinate(SuggestionProvider.Context context, Function<BlockPos, Integer> provider) {
        BlockPos pos = context.getSender().getPosition();
        return Collections.singletonList(String.valueOf(provider.apply(pos)));
    }

    private static void validateGameLobbyDefined(GameData data) throws CommandException {
        if (data.getGameLobby() == null) {
            throw new WrongUsageException("You must first create game lobby. Use /game lobby <position>");
        }
    }
}

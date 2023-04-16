package dev.toma.pubgmc.common.commands;

import dev.toma.pubgmc.api.PubgmcRegistries;
import dev.toma.pubgmc.api.capability.GameData;
import dev.toma.pubgmc.api.game.*;
import dev.toma.pubgmc.api.game.map.GameMap;
import dev.toma.pubgmc.api.util.Position2;
import dev.toma.pubgmc.common.commands.core.*;
import dev.toma.pubgmc.common.commands.core.arg.BlockPosArgument;
import dev.toma.pubgmc.common.commands.core.arg.IntArgument;
import dev.toma.pubgmc.common.commands.core.arg.PubgmcRegistryArgument;
import dev.toma.pubgmc.common.commands.core.arg.StringArgument;
import dev.toma.pubgmc.common.games.GameTypes;
import dev.toma.pubgmc.common.games.NoGame;
import dev.toma.pubgmc.util.helper.GameHelper;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.SyntaxErrorException;
import net.minecraft.command.WrongUsageException;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.World;

import java.util.*;
import java.util.function.Function;

public class GameCommand extends AbstractCommand {

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
    // TODO config
    private static final CommandTree COMMAND = CommandTree.Builder.command("game")
            .usage("/game ") // TODO
            .permissionLevel(2)
            .defaultExecutorPropagationStrategy(DefaultExecutorPropagationStrategy.LAST_NODE)
            .executes(GameCommand::executeDefault)
            .node(
                    CommandNodeProvider.literal("select")
                            .executes(GameCommand::selectGameType)
                            .node(
                                    CommandNodeProvider.argument(ARG_GAME_TYPE, PubgmcRegistryArgument.registry(PubgmcRegistries.GAME_TYPES))
                            )
            )
            .node(
                    CommandNodeProvider.literal("configure")
            )
            .node(
                    CommandNodeProvider.literal("init")
                            .executes(GameCommand::initGame)
            )
            .node(
                    CommandNodeProvider.literal("start")
                            .executes(GameCommand::startGame)
                            .node(
                                    CommandNodeProvider.argument(ARG_MAP_NAME, StringArgument.stringArgument(GameMap.ALLOWED_NAME_PATTERN, "Invalid map name format"))
                            )
            )
            .node(
                    CommandNodeProvider.literal("stop")
                            .executes(GameCommand::stopGame)
            )
            .node(
                    CommandNodeProvider.literal("map")
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
                            .executes(GameCommand::printLobbyInformation)
                            .node(
                                    CommandNodeProvider.argument(ARG_LOBBY_CENTER, BlockPosArgument.blockpos())
                                            .executes(GameCommand::createLobby)
                            )
            )
            .build();

    public GameCommand() {
        super(COMMAND);
    }

    private static void executeDefault(CommandContext context) throws CommandException {
        throw new WrongUsageException("Not enough arguments. See /help game for more details"); // TODO open gui
    }

    private static void executeMapNoArguments(CommandContext context) throws CommandException {
        throw new WrongUsageException("Not enough arguments. Use '/game map create <name> ...' or '/game map <name> [delete|clearPois]'");
    }

    private static void selectGameType(CommandContext context) throws CommandException {
        GameType<?, ?> gameType = context.getArgumentMandatory(ARG_GAME_TYPE);
        GameData gameData = getGameData(context);
        gameData.setSelectedGameType(gameType);
        gameData.sendGameDataToClients();
        // TODO feedback
    }

    @SuppressWarnings("unchecked")
    private static <CFG extends GameConfiguration, G extends Game<CFG>> void initGame(CommandContext context) throws CommandException {
        GameData data = getGameData(context);
        GameType<CFG, G> type = (GameType<CFG, G>) data.getSelectedGameType();
        if (type == GameTypes.NO_GAME) {
            throw new WrongUsageException("You must first select game type. Use /game select <gameType>");
        }
        validateGameLobbyDefined(data);
        CFG config = data.getGameConfiguration(type);
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
        // TODO send feedback
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
        if (game == null || game == NoGame.INSTANCE) {
            throw new WrongUsageException("No game is currenly prepared for starting. Use '/game init' command");
        }
        if (actualMapName == null) {
            throw new WrongUsageException("You must specify game map. Use /game start <mapName>");
        }
        GameMap map = data.getGameMap(actualMapName);
        if (map == null) {
            throw new WrongUsageException("No map is registered under " + actualMapName + " name");
        }
        ICommandSender sender = context.getSender();
        World world = sender.getEntityWorld();
        try {
            game.performGameMapValidations(world, map);
            game.onGameStart(world);
        } catch (GameException e) {
            throw new WrongUsageException("Unable to start game: " + e.getMessage());
        }
        // TODO feedback
    }

    private static void stopGame(CommandContext context) throws CommandException {
        GameData data = getGameData(context);
        Game<?> game = data.getCurrentGame();
        if (game == null) {
            throw new WrongUsageException("There is no active game");
        }
        ICommandSender sender = context.getSender();
        GameHelper.stopGame(sender.getEntityWorld());
        // TODO feedback
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
        // TODO feedback
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
        // TODO feedback
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
        // TODO feedback
    }

    private static void printLobbyInformation(CommandContext context) throws CommandException {
        GameData data = getGameData(context);
        GameLobby lobby = data.getGameLobby();
        if (lobby == null) {
            // TODO no lobby info
        } else {
            // TODO send feedback with possibility to teleport to lobby position
        }
    }

    private static void createLobby(CommandContext context) throws CommandException {
        GameData data = getGameData(context);
        BlockPos pos = context.getArgumentMandatory(ARG_LOBBY_CENTER);
        GameLobby lobby = new GameLobby(pos);
        data.setGameLobby(lobby);
        data.sendGameDataToClients();
        // TODO send feedback
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
            throw new WrongUsageException("You must first create game lobby. Use /game lobby <position> <radius>");
        }
    }
}

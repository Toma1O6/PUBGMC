package dev.toma.pubgmc.common.commands;

import dev.toma.pubgmc.api.PubgmcRegistries;
import dev.toma.pubgmc.api.capability.GameData;
import dev.toma.pubgmc.api.game.GameType;
import dev.toma.pubgmc.api.game.map.GameMap;
import dev.toma.pubgmc.common.commands.core.*;
import dev.toma.pubgmc.common.commands.core.arg.IntArgument;
import dev.toma.pubgmc.common.commands.core.arg.PubgmcRegistryArgument;
import dev.toma.pubgmc.common.commands.core.arg.StringArgument;
import net.minecraft.command.CommandException;
import net.minecraft.command.WrongUsageException;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;

import java.util.Collections;
import java.util.List;
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
    // TODO config, init, start, stop
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
            )
            .node(
                    CommandNodeProvider.literal("start")
            )
            .node(
                    CommandNodeProvider.literal("stop")
            )
            .node(
                    CommandNodeProvider.literal("map")
                            .node(
                                    CommandNodeProvider.argument(ARG_MAP_NAME, StringArgument.stringArgument(GameMap.ALLOWED_NAME_PATTERN))
                                            .node(
                                                    CommandNodeProvider.literal("delete")
                                            )
                                            .node(
                                                    CommandNodeProvider.literal("clearPois")
                                            )
                            )
                            .node(
                                    CommandNodeProvider.literal("create")
                                            .node(
                                                    CommandNodeProvider.argument(ARG_MAP_NAME, StringArgument.stringArgument(GameMap.ALLOWED_NAME_PATTERN))
                                                            .node(
                                                                    CommandNodeProvider.literal("center")
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
            .build();

    public GameCommand() {
        super(COMMAND);
    }

    private static void executeDefault(CommandContext context) throws CommandException {
        throw new WrongUsageException("Not enough arguments. See /help game for more details");
    }

    private static void selectGameType(CommandContext context) throws CommandException {
        GameType<?, ?> gameType = context.getArgumentMandatory(ARG_GAME_TYPE);
        GameData gameData = getGameData(context);
        gameData.setSelectedGameType(gameType);
        // TODO feedback
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
}

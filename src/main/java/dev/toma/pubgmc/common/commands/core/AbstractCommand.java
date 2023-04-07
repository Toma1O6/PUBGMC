package dev.toma.pubgmc.common.commands.core;

import dev.toma.pubgmc.common.capability.IGameData;
import dev.toma.pubgmc.common.capability.IWorldData;
import net.minecraft.command.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;
import java.util.stream.Collectors;

public abstract class AbstractCommand extends CommandBase {

    private final CommandTree commandTree;

    public AbstractCommand(CommandTree commandTree) {
        this.commandTree = commandTree;
    }

    @Override
    public final String getName() {
        return commandTree.getName();
    }

    @Override
    public final String getUsage(ICommandSender sender) {
        return commandTree.getUsage(sender);
    }

    @Override
    public final int getRequiredPermissionLevel() {
        return commandTree.getPermissionLevel();
    }

    @Override
    public final List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args, @Nullable BlockPos targetPos) {
        List<String> list = commandTree.suggest(server, sender, args, targetPos);
        return getListOfStringsMatchingLastWord(args, list);
    }

    @Override
    public final void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        commandTree.executeCommand(server, sender, args);
    }

    public static EntityPlayerMP getPlayerByName(CommandContext context, String name) throws CommandException {
        List<EntityPlayerMP> list = context.getServer().getPlayerList().getPlayers();
        EntityPlayerMP player = list.stream()
                .filter(p -> p.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
        if (player == null) {
            throw new SyntaxErrorException("Unknown player: " + name);
        }
        return player;
    }

    public static List<String> suggestPlayer(SuggestionProvider.Context context) {
        MinecraftServer server = context.getServer();
        return server.getPlayerList().getPlayers().stream()
                .map(EntityPlayer::getName)
                .collect(Collectors.toList());
    }

    public static IWorldData getWorldData(CommandContext context) throws CommandException {
        ICommandSender sender = context.getSender();
        World world = sender.getEntityWorld();
        IWorldData worldData = world.getCapability(IWorldData.WorldDataProvider.WORLD_DATA, null);
        if (worldData == null) {
            throw new WrongUsageException("No world data are loaded. Report this issue.");
        }
        return worldData;
    }

    public static IGameData getGameData(CommandContext context) throws CommandException {
        ICommandSender sender = context.getSender();
        World world = sender.getEntityWorld();
        IGameData gameData = world.getCapability(IGameData.GameDataProvider.GAMEDATA, null);
        if (gameData == null) {
            throw new WrongUsageException("No game data are loaded. Report this issue.");
        }
        return gameData;
    }
}

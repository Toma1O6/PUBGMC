package dev.toma.pubgmc.common.commands.core;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.SyntaxErrorException;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public abstract class AbstractCommand extends CommandBase {

    private final CommandTree commandTree;

    public AbstractCommand(CommandTree commandTree) {
        this.commandTree = commandTree;
    }

    @Override
    public String getName() {
        return commandTree.getName();
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return commandTree.getUsage(sender);
    }

    @Override
    public int getRequiredPermissionLevel() {
        return commandTree.getPermissionLevel();
    }

    @Override
    public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args, @Nullable BlockPos targetPos) {
        List<String> list = commandTree.suggest(server, sender, args, targetPos);
        return getListOfStringsMatchingLastWord(args, list);
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
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
}

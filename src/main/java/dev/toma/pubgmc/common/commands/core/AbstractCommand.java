package dev.toma.pubgmc.common.commands.core;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;

import javax.annotation.Nullable;
import java.util.List;

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
}

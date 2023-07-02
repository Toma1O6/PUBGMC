package dev.toma.pubgmc.common.commands.core;

import dev.toma.pubgmc.common.commands.core.arg.ArgumentReader;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;

import javax.annotation.Nullable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface CommandNode extends ChildNodeProvider {

    String key();

    CommandNodeExecutor getExecutor();

    Map<String, CommandNode> children();

    int getPermissionLevel();

    void process(ArgumentReader reader, Map<String, Object> argumentMap, MinecraftServer server, ICommandSender sender) throws CommandException;

    List<String> suggest(MinecraftServer server, ICommandSender sender, @Nullable BlockPos targetPos, int cursor);

    boolean matches(String value);

    @Override
    default CommandNode getChildNode(String value) {
        return CommandTree.findFirstChildNodeByKey(value, children().values());
    }

    @Override
    default Collection<CommandNode> listNodes() {
        return children().values();
    }

    default int getSortOrder() {
        return 0;
    }

    default boolean shouldUseChildNodesForSuggestions(int cursorOffset) {
        return true;
    }
}

package dev.toma.pubgmc.common.commands.core;

import com.google.common.collect.ImmutableMap;
import dev.toma.pubgmc.common.commands.core.arg.ArgumentReader;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.SyntaxErrorException;
import net.minecraft.command.WrongUsageException;
import net.minecraft.server.MinecraftServer;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public final class CommandContext {

    private final MinecraftServer server;
    private final ICommandSender sender;
    private final CommandNode node;
    private final Map<String, Object> argumentMap;
    private final @Nullable CommandNodeExecutor defaultExecutor;

    public CommandContext(MinecraftServer server, ICommandSender sender, CommandNode node, Map<String, Object> argumentMap, CommandNodeExecutor executor) {
        this.server = server;
        this.sender = sender;
        this.node = node;
        this.argumentMap = ImmutableMap.copyOf(argumentMap);
        this.defaultExecutor = executor;
    }

    public static CommandContext parse(CommandTree tree, MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        ArgumentReader reader = new ArgumentReader(args);
        Map<String, Object> argumentMap = new HashMap<>();
        CommandNode node = null;
        CommandNodeExecutor executor = tree.getDefaultExecutor();
        String value;
        while ((value = reader.peek()) != null) {
            ChildNodeProvider provider = node != null ? node : tree;
            CommandNode commandNode = provider.getChildNode(value);
            if (commandNode == null) {
                throw new SyntaxErrorException(String.format("Unknown command argument: %s", value));
            }
            CommandNodeExecutor nodeExecutor = commandNode.getExecutor();
            if (tree.getExecutorPropagationStrategy().shouldReadNodeExecutors() && nodeExecutor != null) {
                executor = nodeExecutor;
            }
            commandNode.process(reader, argumentMap, server, sender);
            node = commandNode;
        }
        return new CommandContext(server, sender, node, argumentMap, executor);
    }

    @SuppressWarnings("unchecked")
    public <T> Optional<T> getArgument(String key) {
        Object object = argumentMap.get(key);
        if (object == null) {
            return Optional.empty();
        }
        try {
            T t = (T) object;
            return Optional.of(t);
        } catch (ClassCastException e) {
            return Optional.empty();
        }
    }

    public <T> T getArgumentMandatory(String key) throws CommandException {
        Optional<T> optional = getArgument(key);
        if (!optional.isPresent()) {
            throw new WrongUsageException("Argument " + key + " is not defined");
        }
        return optional.get();
    }

    public MinecraftServer getServer() {
        return server;
    }

    public ICommandSender getSender() {
        return sender;
    }

    CommandNode getNode() {
        return node;
    }

    CommandNodeExecutor getDefaultExecutor() {
        return defaultExecutor;
    }
}

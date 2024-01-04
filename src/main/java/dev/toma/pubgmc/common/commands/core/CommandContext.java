package dev.toma.pubgmc.common.commands.core;

import com.google.common.collect.ImmutableMap;
import dev.toma.pubgmc.common.commands.core.arg.ArgumentReader;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.SyntaxErrorException;
import net.minecraft.command.WrongUsageException;
import net.minecraft.server.MinecraftServer;

import javax.annotation.Nullable;
import java.util.*;

public final class CommandContext {

    private final MinecraftServer server;
    private final ICommandSender sender;
    private final CommandNode node;
    private final Map<String, Object> argumentMap;
    private final @Nullable CommandNodeExecutor defaultExecutor;
    private final int permissionLevel;

    public CommandContext(MinecraftServer server, ICommandSender sender, CommandNode node, Map<String, Object> argumentMap, CommandNodeExecutor executor, int permissionLevel) {
        this.server = server;
        this.sender = sender;
        this.node = node;
        this.argumentMap = ImmutableMap.copyOf(argumentMap);
        this.defaultExecutor = executor;
        this.permissionLevel = permissionLevel;
    }

    public static CommandContext parse(CommandTree tree, MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        String cmdText = String.join(" ", args);
        CommandStringReader commandReader = new CommandStringReader(cmdText);
        ArgumentReader reader = new ArgumentReader(commandReader.toArgumentArray());
        Map<String, Object> argumentMap = new HashMap<>();
        CommandNode node = null;
        CommandNodeExecutor executor = tree.getDefaultExecutor();
        String value;
        int permissionLevel = tree.getPermissionLevel();
        while ((value = reader.peek()) != null) {
            ChildNodeProvider provider = node != null ? node : tree;
            CommandNode commandNode = provider.getChildNode(value);
            if (commandNode == null) {
                throw new SyntaxErrorException(String.format("Unknown command argument: %s", value));
            }
            CommandNodeExecutor nodeExecutor = commandNode.getExecutor();
            if (tree.getExecutorPropagationStrategy().shouldReadNodeExecutors()) {
                if (nodeExecutor != null) {
                    executor = nodeExecutor;
                }
                if (commandNode.getPermissionLevel() >= 0) {
                    permissionLevel = commandNode.getPermissionLevel();
                }
            }
            commandNode.process(reader, argumentMap, server, sender);
            node = commandNode;
        }
        return new CommandContext(server, sender, node, argumentMap, executor, permissionLevel);
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

    int getPermissionLevel() {
        return permissionLevel;
    }

    public static final class CommandStringReader {

        private final String cmd;
        private int pos;
        private boolean multiWord;

        public CommandStringReader(String cmd) {
            this.cmd = cmd;
        }

        public String[] toArgumentArray() throws CommandException {
            List<String> list = new ArrayList<>();
            String text;
            while ((text = tryReadArgument()) != null) {
                if (!text.isEmpty()) {
                    list.add(text);
                }
            }
            return list.toArray(new String[0]);
        }

        public String tryReadArgument() throws CommandException {
            StringBuilder builder = new StringBuilder();
            if (pos == cmd.length()) {
                return null;
            }
            while (true) {
                char character = read();
                if (character == '"') {
                    if (multiWord) {
                        char following = peek();
                        if (following != ' ' && following != Character.MIN_VALUE) {
                            // Not empty space after closing quote
                            throw new SyntaxErrorException("Whitespace character must be present after closing quote");
                        }
                        multiWord = false;
                        break;
                    } else {
                        multiWord = true;
                    }
                } else if (character == ' ') {
                    if (multiWord) {
                        builder.append(character);
                    } else {
                        break;
                    }
                } else if (character == Character.MIN_VALUE) {
                    return builder.toString();
                } else {
                    builder.append(character);
                }
            }
            String result = builder.toString();
            if (result.isEmpty()) {
                throw new SyntaxErrorException("Argument value cannot be empty");
            }
            return result;
        }

        public char read() {
            if (pos == cmd.length()) {
                return Character.MIN_VALUE;
            }
            return cmd.charAt(pos++);
        }

        public char peek() {
            if ((pos + 1) >= cmd.length()) {
                return Character.MIN_VALUE;
            }
            return cmd.charAt(pos + 1);
        }
    }
}

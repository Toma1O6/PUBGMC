package dev.toma.pubgmc.common.commands.core;

import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;

import javax.annotation.Nullable;
import java.util.*;
import java.util.function.Function;

public final class CommandTree implements ChildNodeProvider {

    private final String name;
    private final Function<ICommandSender, String> usageProvider;
    private final int permissionLevel;
    private final CommandNodeExecutor defaultExecutor;
    private final Map<String, CommandNode> children;

    private CommandTree(Builder builder) {
        name = builder.name;
        usageProvider = builder.usageProvider;
        permissionLevel = builder.permissionLevel;
        defaultExecutor = builder.defaultExecutor;
        children = builder.children;
    }

    public String getName() {
        return name;
    }

    public String getUsage(ICommandSender sender) {
        return usageProvider.apply(sender);
    }

    public int getPermissionLevel() {
        return permissionLevel;
    }

    public void executeCommand(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        CommandContext context = CommandContext.parse(this, server, sender, args);
        CommandNode node = context.getNode();
        CommandNodeExecutor executor = defaultExecutor;
        if (node != null && node.getExecutor() != null) {
            executor = node.getExecutor();
        }
        executor.executeCommand(context);
    }

    public List<String> suggest(MinecraftServer server, ICommandSender sender, String[] args, @Nullable BlockPos lookingAt) {
        int maxDepth = args.length - 1;
        int depth = 0;
        int treeDepth = 0;
        ChildNodeProvider childNodeProvider = this;
        while (depth < maxDepth) {
            String value = args[depth++];
            CommandNode commandNode = childNodeProvider.getChildNode(value);
            if (commandNode == null)
                break;
            if (commandNode.getChildNode(value) == null)
                break;
            childNodeProvider = commandNode;
            ++treeDepth;
        }
        int offset = maxDepth - treeDepth;
        List<String> values = new ArrayList<>();
        for (CommandNode commandNode : childNodeProvider.listNodes()) {
            values.addAll(commandNode.suggest(server, sender, lookingAt, offset));
        }
        return values;
    }

    @Override
    public CommandNode getChildNode(String value) {
        return findFirstChildNodeByKey(value, children.values());
    }

    @Override
    public Collection<CommandNode> listNodes() {
        return children.values();
    }

    public static CommandNode findFirstChildNodeByKey(String value, Collection<CommandNode> nodes) {
        return nodes.stream()
                .sorted(Comparator.comparingInt(CommandNode::getSortOrder))
                .filter(node -> node.matches(value))
                .findFirst()
                .orElse(null);
    }

    public static final class Builder {

        private final String name;
        private Function<ICommandSender, String> usageProvider;
        private int permissionLevel = 4;
        private CommandNodeExecutor defaultExecutor;
        private final Map<String, CommandNode> children = new HashMap<>();

        private Builder(String name) {
            this.name = name;
        }

        public static Builder command(String command) {
            return new Builder(Objects.requireNonNull(command));
        }

        public Builder usage(Function<ICommandSender, String> function) {
            this.usageProvider = function;
            return this;
        }

        public Builder usage(String usageString) {
            return usage(sender -> usageString);
        }

        public Builder usage(ITextComponent component) {
            return usage(sender -> component.getFormattedText());
        }

        public Builder permissionLevel(int permissionLevel) {
            this.permissionLevel = permissionLevel;
            return this;
        }

        public Builder executes(CommandNodeExecutor executor) {
            this.defaultExecutor = executor;
            return this;
        }

        public Builder node(CommandNodeProvider nodeProvider) {
            CommandNode node = nodeProvider.getNode();
            children.put(node.key(), node);
            return this;
        }

        public CommandTree build() {
            if (name == null || name.isEmpty()) {
                throw new IllegalArgumentException("Name must be defined");
            }
            if (usageProvider == null) {
                throw new IllegalArgumentException("Usage provider must be defined");
            }
            if (defaultExecutor == null) {
                throw new IllegalArgumentException("Default command executor must be defined");
            }
            return new CommandTree(this);
        }
    }
}

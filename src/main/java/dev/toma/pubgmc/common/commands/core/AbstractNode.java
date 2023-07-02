package dev.toma.pubgmc.common.commands.core;

import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;

import javax.annotation.Nullable;
import java.util.*;

public abstract class AbstractNode implements CommandNode {

    private final String key;
    private final int permissionLevel;
    private final CommandNodeExecutor executor;
    private final SuggestionProvider suggestionProvider;
    private final Map<String, CommandNode> children;

    public AbstractNode(String key, int permissionLevel, CommandNodeExecutor executor, SuggestionProvider suggestionProvider, Map<String, CommandNode> children) {
        this.key = key;
        this.permissionLevel = permissionLevel;
        this.executor = executor;
        this.suggestionProvider = suggestionProvider;
        this.children = children;
    }

    @Override
    public String key() {
        return key;
    }

    @Override
    public int getPermissionLevel() {
        return permissionLevel;
    }

    @Override
    public CommandNodeExecutor getExecutor() {
        return executor;
    }

    @Override
    public Map<String, CommandNode> children() {
        return children;
    }

    @Override
    public List<String> suggest(MinecraftServer server, ICommandSender sender, @Nullable BlockPos targetPos, int cursor) {
        List<String> suggestions = new ArrayList<>();
        suggestions.add(key);
        if (suggestionProvider != null) {
            suggestions.addAll(suggestionProvider.suggest(new SuggestionProvider.Context(server, sender, targetPos, cursor)));
        }
        return suggestions;
    }

    protected SuggestionProvider getSuggestionProvider() {
        return suggestionProvider;
    }

    public static abstract class AbstractBuilder<B extends AbstractBuilder<?>> implements CommandNodeProvider {

        protected final String key;
        protected final Map<String, CommandNode> children;
        protected int permissionLevel = -1;
        protected CommandNodeExecutor executor;
        protected SuggestionProvider suggestionProvider;

        protected AbstractBuilder(String key) {
            this.key = key;
            this.children = new HashMap<>();
        }

        public abstract B self();

        public B permissionLevel(int permissionLevel) {
            this.permissionLevel = permissionLevel;
            return self();
        }

        public B executes(CommandNodeExecutor executor) {
            this.executor = executor;
            return self();
        }

        public B node(CommandNodeProvider provider) {
            CommandNode node = provider.getNode();
            children.put(node.key(), node);
            return self();
        }

        public B suggests(SuggestionProvider provider) {
            this.suggestionProvider = provider;
            return self();
        }
    }
}

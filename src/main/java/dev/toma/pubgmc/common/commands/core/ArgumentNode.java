package dev.toma.pubgmc.common.commands.core;

import dev.toma.pubgmc.common.commands.core.arg.ArgumentContext;
import dev.toma.pubgmc.common.commands.core.arg.ArgumentParser;
import dev.toma.pubgmc.common.commands.core.arg.ArgumentReader;
import dev.toma.pubgmc.common.commands.core.arg.ArgumentType;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Map;

public class ArgumentNode<T> extends AbstractNode {

    private final ArgumentType<T> type;

    public ArgumentNode(String key, int permissionLevel, CommandNodeExecutor executor, SuggestionProvider suggestionProvider,  Map<String, CommandNode> children, ArgumentType<T> type) {
        super(key, permissionLevel, executor, suggestionProvider, children);
        this.type = type;
    }

    @Override
    public void process(ArgumentReader reader, Map<String, Object> argumentMap, MinecraftServer server, ICommandSender sender) throws CommandException {
        ArgumentParser<T> parser = type.getParser();
        T value = parser.parse(new ArgumentContext<>(type, reader, server, sender));
        argumentMap.put(key(), value);
    }

    @Override
    public List<String> suggest(MinecraftServer server, ICommandSender sender, @Nullable BlockPos targetPos, int cursor) {
        SuggestionProvider.Context context = new SuggestionProvider.Context(server, sender, targetPos, cursor);
        return getSuggestionProvider() != null ? getSuggestionProvider().suggest(context) : type.suggest(context);
    }

    @Override
    public boolean matches(String value) {
        return true;
    }

    @Override
    public int getSortOrder() {
        return 1;
    }

    @Override
    public boolean shouldUseChildNodesForSuggestions(int cursorOffset) {
        int argumentCount = type.getArgumentCount();
        return cursorOffset >= argumentCount;
    }

    public static final class Builder<T> extends AbstractBuilder<Builder<T>> {

        private final ArgumentType<T> type;

        public Builder(String key, ArgumentType<T> type) {
            super(key);
            this.type = type;
        }

        @Override
        public Builder<T> self() {
            return this;
        }

        @Override
        public CommandNode getNode() {
            return new ArgumentNode<>(key, permissionLevel, executor, suggestionProvider, children, type);
        }
    }
}

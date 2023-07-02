package dev.toma.pubgmc.common.commands.core;

import dev.toma.pubgmc.common.commands.core.arg.ArgumentReader;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;

import java.util.Map;

public final class LiteralNode extends AbstractNode {

    public LiteralNode(String key, int permissionLevel, CommandNodeExecutor executor, SuggestionProvider suggestionProvider, Map<String, CommandNode> children) {
        super(key, permissionLevel, executor, suggestionProvider, children);
    }

    @Override
    public void process(ArgumentReader reader, Map<String, Object> argumentMap, MinecraftServer server, ICommandSender sender) {
        reader.read(); // make sure to skip to next value
    }

    @Override
    public boolean matches(String value) {
        return key().equalsIgnoreCase(value);
    }

    public static final class Builder extends AbstractBuilder<Builder> {

        public Builder(String key) {
            super(key);
        }

        @Override
        public Builder self() {
            return this;
        }

        @Override
        public CommandNode getNode() {
            return new LiteralNode(key, permissionLevel, executor, suggestionProvider, children);
        }
    }
}

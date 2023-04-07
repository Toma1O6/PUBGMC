package dev.toma.pubgmc.common.commands.core;

import dev.toma.pubgmc.common.commands.core.arg.ArgumentType;

@FunctionalInterface
public interface CommandNodeProvider {

    CommandNode getNode();

    static LiteralNode.Builder literal(String literal) {
        return new LiteralNode.Builder(literal);
    }

    static <T> ArgumentNode.Builder<T> argument(String key, ArgumentType<T> type) {
        return new ArgumentNode.Builder<>(key, type);
    }
}

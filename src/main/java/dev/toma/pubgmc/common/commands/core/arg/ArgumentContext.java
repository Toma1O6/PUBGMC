package dev.toma.pubgmc.common.commands.core.arg;

import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;

public final class ArgumentContext<T> {

    private final ArgumentType<T> argumentType;
    private final ArgumentReader reader;
    private final MinecraftServer server;
    private final ICommandSender sender;

    public ArgumentContext(ArgumentType<T> argumentType, ArgumentReader reader, MinecraftServer server, ICommandSender sender) {
        this.argumentType = argumentType;
        this.reader = reader;
        this.server = server;
        this.sender = sender;
    }

    @SuppressWarnings("unchecked")
    public <A extends ArgumentType<T>> A castArgument() {
        return (A) argumentType;
    }

    public ArgumentType<T> getArgumentType() {
        return argumentType;
    }

    public ArgumentReader getReader() {
        return reader;
    }

    public MinecraftServer getServer() {
        return server;
    }

    public ICommandSender getSender() {
        return sender;
    }
}

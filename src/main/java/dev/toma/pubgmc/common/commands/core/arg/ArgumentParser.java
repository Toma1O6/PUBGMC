package dev.toma.pubgmc.common.commands.core.arg;

import net.minecraft.command.CommandException;

public interface ArgumentParser<T> {

    T parse(ArgumentReader reader, ArgumentType<T> argumentType) throws CommandException;
}

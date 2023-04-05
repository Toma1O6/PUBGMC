package dev.toma.pubgmc.common.commands.core.arg;

import net.minecraft.command.CommandException;

public interface ArgumentParser<T> {

    T parse(ArgumentContext<T> context) throws CommandException;
}

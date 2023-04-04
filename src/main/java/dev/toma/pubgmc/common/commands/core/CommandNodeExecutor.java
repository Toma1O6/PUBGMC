package dev.toma.pubgmc.common.commands.core;

import net.minecraft.command.CommandException;

@FunctionalInterface
public interface CommandNodeExecutor {

    void executeCommand(CommandContext context) throws CommandException;
}

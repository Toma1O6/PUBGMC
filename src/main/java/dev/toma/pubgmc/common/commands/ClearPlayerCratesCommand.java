package dev.toma.pubgmc.common.commands;

import dev.toma.pubgmc.common.commands.core.AbstractCommand;
import dev.toma.pubgmc.common.commands.core.CommandContext;
import dev.toma.pubgmc.common.commands.core.CommandTree;
import dev.toma.pubgmc.common.tileentity.TileEntityPlayerCrate;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;

import java.util.List;
import java.util.stream.Collectors;

// TODO rework to clear all game objects
public class ClearPlayerCratesCommand extends AbstractCommand {

    private static final CommandTree COMMAND = CommandTree.Builder.command("clearplayercrates")
            .usage("/clearplayercrates")
            .permissionLevel(2)
            .executes(ClearPlayerCratesCommand::clearPlayerCrates)
            .build();

    public ClearPlayerCratesCommand() {
        super(COMMAND);
    }

    private static void clearPlayerCrates(CommandContext context) {
        ICommandSender sender = context.getSender();
        World world = sender.getEntityWorld();
        List<TileEntityPlayerCrate> crates = world.loadedTileEntityList.stream()
                .filter(tile -> tile instanceof TileEntityPlayerCrate)
                .map(tile -> (TileEntityPlayerCrate) tile)
                .collect(Collectors.toList());
        crates.forEach(position -> removeCrate(world, position));
        sender.sendMessage(new TextComponentTranslation("commands.pubgmc.clearplayercrates.success", crates.size()));
    }

    private static void removeCrate(World world, TileEntityPlayerCrate crate) {
        crate.clear();
        world.destroyBlock(crate.getPos(), false);
    }
}

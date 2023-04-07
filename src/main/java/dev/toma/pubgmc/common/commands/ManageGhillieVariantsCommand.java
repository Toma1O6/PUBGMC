package dev.toma.pubgmc.common.commands;

import dev.toma.pubgmc.common.capability.IWorldData;
import dev.toma.pubgmc.common.commands.core.AbstractCommand;
import dev.toma.pubgmc.common.commands.core.CommandContext;
import dev.toma.pubgmc.common.commands.core.CommandNodeProvider;
import dev.toma.pubgmc.common.commands.core.CommandTree;
import dev.toma.pubgmc.common.commands.core.arg.ColorArgument;
import dev.toma.pubgmc.common.commands.core.arg.IntArgument;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;

import java.util.List;

public class ManageGhillieVariantsCommand extends AbstractCommand {

    private static final String ARG_COLOR = "color";
    private static final String ARG_COLOR_INDEX = "colorIndex";

    private static final ITextComponent LIST_HEADER = new TextComponentTranslation("commands.pubgmc.ghillie_colors.list_header");

    private static final CommandTree COMMAND = CommandTree.Builder.command("ghillie")
            .usage("/ghillie [add, remove, list] <colorRGB|colorIndex>")
            .permissionLevel(2)
            .executes(ManageGhillieVariantsCommand::executeDefault)
            .node(
                    CommandNodeProvider.literal("add")
                            .executes(ManageGhillieVariantsCommand::addNewVariant)
                            .node(
                                    CommandNodeProvider.argument(ARG_COLOR, ColorArgument.rgb())
                                            .executes(ManageGhillieVariantsCommand::addNewVariant)
                            )
            )
            .node(
                    CommandNodeProvider.literal("remove")
                            .executes(ManageGhillieVariantsCommand::removeVariant)
                            .node(
                                    CommandNodeProvider.argument(ARG_COLOR_INDEX, IntArgument.min(0))
                                            .executes(ManageGhillieVariantsCommand::removeVariant)
                            )
            )
            .node(
                    CommandNodeProvider.literal("list")
                            .executes(ManageGhillieVariantsCommand::listRegisteredVariants)
            )
            .build();

    public ManageGhillieVariantsCommand() {
        super(COMMAND);
    }

    private static void executeDefault(CommandContext context) throws CommandException {
        throw new WrongUsageException("Missing arguments. See /help ghillie for more details");
    }

    private static void addNewVariant(CommandContext context) throws CommandException {
        IWorldData worldData = getWorldData(context);
        int color = ColorArgument.getColor(ARG_COLOR, context);
        worldData.addColorVariant(color);
        context.getSender().sendMessage(new TextComponentTranslation("commands.pubgmc.ghillie_colors.added", "#" + Integer.toHexString(color)));
    }

    private static void removeVariant(CommandContext context) throws CommandException {
        IWorldData worldData = getWorldData(context);
        int colorIndex = context.<Integer>getArgument(ARG_COLOR_INDEX)
                .orElse(0);
        List<Integer> colorList = worldData.getGhillieSuitsColorVariants();
        if (colorIndex < colorList.size()) {
            int color = colorList.remove(colorIndex);
            context.getSender().sendMessage(new TextComponentTranslation("commands.pubgmc.ghillie_colors.removed", colorIndex, "#" + Integer.toHexString(color)));
        } else {
            throw new WrongUsageException("No color is defined for index " + colorIndex);
        }
    }

    private static void listRegisteredVariants(CommandContext context) throws CommandException {
        IWorldData worldData = getWorldData(context);
        ICommandSender sender = context.getSender();
        sender.sendMessage(LIST_HEADER);
        List<Integer> colorList = worldData.getGhillieSuitsColorVariants();
        for (int i = 0; i < colorList.size(); i++) {
            sender.sendMessage(new TextComponentString(String.format("[%d] - #%s", i, Integer.toHexString(colorList.get(i)))));
        }
    }
}

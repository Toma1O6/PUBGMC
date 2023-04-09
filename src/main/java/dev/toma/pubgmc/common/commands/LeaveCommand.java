package dev.toma.pubgmc.common.commands;

import dev.toma.pubgmc.api.Lobby;
import dev.toma.pubgmc.common.capability.game.IGameData;
import dev.toma.pubgmc.common.commands.core.AbstractCommand;
import dev.toma.pubgmc.common.commands.core.CommandContext;
import dev.toma.pubgmc.common.commands.core.CommandTree;
import dev.toma.pubgmc.util.helper.TextComponentHelper;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;

public class LeaveCommand extends AbstractCommand {

    private static final CommandTree COMMAND = CommandTree.Builder.command("leave")
            .usage("/leave")
            .permissionLevel(0)
            .executes(LeaveCommand::performLobbyTeleport)
            .build();

    public LeaveCommand() {
        super(COMMAND);
    }

    private static void performLobbyTeleport(CommandContext context) throws CommandException {
        ICommandSender sender = context.getSender();
        Entity entity = sender.getCommandSenderEntity();
        if (entity instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) entity;
            IGameData gameData = getGameData(context);
            if (gameData.isInactiveGame()) {
                throw new WrongUsageException("There is no active game");
            }
            Lobby lobby = gameData.getLobby();
            if (lobby == null || lobby.center == null) {
                throw new WrongUsageException("No lobby is defined");
            }
            BlockPos pos = lobby.center;
            player.attemptTeleport(pos.getX() + 0.5, pos.getY() + 1.0, pos.getZ() + 0.5);
            player.sendMessage(TextComponentHelper.applyColor(new TextComponentTranslation("commands.pubgmc.leave.success"), TextFormatting.GREEN));
        }
    }
}

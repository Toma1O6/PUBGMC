package dev.toma.pubgmc.common.commands;

import dev.toma.pubgmc.api.capability.GameData;
import dev.toma.pubgmc.api.game.Game;
import dev.toma.pubgmc.api.game.GameLobby;
import dev.toma.pubgmc.common.commands.core.AbstractCommand;
import dev.toma.pubgmc.common.commands.core.CommandContext;
import dev.toma.pubgmc.common.commands.core.CommandNodeProvider;
import dev.toma.pubgmc.common.commands.core.CommandTree;
import dev.toma.pubgmc.common.commands.core.arg.BooleanArgument;
import net.minecraft.command.CommandException;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.event.ClickEvent;

public class LobbyCommand extends AbstractCommand {

    private static final String ARG_FORCE = "force";
    private static final CommandTree COMMAND = CommandTree.Builder.command("lobby")
            .usage("/lobby <force> - will leave current game and teleport you back to lobby")
            .permissionLevel(0)
            .executes(LobbyCommand::leaveGameAndTeleportToLobby)
            .node(
                    CommandNodeProvider.argument(ARG_FORCE, BooleanArgument.booleanArgument())
            )
            .build();

    public LobbyCommand() {
        super(COMMAND);
    }

    private static void leaveGameAndTeleportToLobby(CommandContext context) throws CommandException {
        GameData data = getGameData(context);
        Game<?> game = data.getCurrentGame();
        boolean isForce = context.<Boolean>getArgument(ARG_FORCE)
                .orElse(false);
        if (game == null) {
            throw new WrongUsageException("There is no active game");
        }
        Entity entity = context.getSender().getCommandSenderEntity();
        if (!(entity instanceof EntityPlayer)) {
            throw new WrongUsageException("This command can be only executed by player");
        }
        EntityPlayer player = (EntityPlayer) entity;
        if (isForce) {
            game.onPlayerLeft(player);
            GameLobby lobby = data.getGameLobby();
            if (lobby == null) {
                throw new WrongUsageException("There is no lobby defined");
            }
            lobby.teleport(player);
            player.sendMessage(new TextComponentTranslation("commands.pubgmc.lobby.success"));
        } else {
            String command = "/lobby true";
            String commandText = TextFormatting.GREEN + command;
            ITextComponent component = new TextComponentTranslation("commands.pubgmc.lobby.confirm", commandText);
            component.getStyle().setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, command));
            player.sendMessage(component);
        }
    }
}

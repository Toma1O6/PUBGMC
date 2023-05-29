package dev.toma.pubgmc.common.commands;

import dev.toma.pubgmc.api.capability.GameData;
import dev.toma.pubgmc.api.capability.GameDataProvider;
import dev.toma.pubgmc.api.game.*;
import dev.toma.pubgmc.api.game.util.Team;
import dev.toma.pubgmc.common.commands.core.*;
import dev.toma.pubgmc.common.commands.core.arg.StringArgument;
import dev.toma.pubgmc.util.helper.TextComponentHelper;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.*;
import net.minecraft.util.text.event.ClickEvent;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class TeamCommand extends AbstractCommand {

    private static final String ARG_TEAM_NAME = "teamName";
    private static final String ARG_PLAYER_NAME = "player";

    private static final CommandTree COMMAND = CommandTree.Builder.command("team")
            .usage("/team [join|leave|invite|kick]")
            .permissionLevel(0)
            .defaultExecutorPropagationStrategy(NodePropagationStrategy.LAST_NODE)
            .executes(TeamCommand::executeDefault)
            .node(
                    CommandNodeProvider.literal("leave")
                            .executes(TeamCommand::leaveTeam)
            )
            .node(
                    CommandNodeProvider.literal("invite")
                            .executes(TeamCommand::inviteToTeam)
                            .node(
                                    CommandNodeProvider.argument(ARG_PLAYER_NAME, StringArgument.stringArgument())
                                            .suggests(AbstractCommand::suggestPlayer)
                            )
                            .node(
                                    CommandNodeProvider.literal("list")
                                            .executes(TeamCommand::listInvites)
                            )
                            .node(
                                    CommandNodeProvider.literal("cancel")
                                            .executes(TeamCommand::cancelInvite)
                                            .node(
                                                    CommandNodeProvider.argument(ARG_PLAYER_NAME, StringArgument.stringArgument())
                                                            .suggests(AbstractCommand::suggestPlayer)
                                            )
                            )
                            .node(
                                    CommandNodeProvider.literal("accept")
                                            .executes(TeamCommand::acceptInviteToTeam)
                                            .node(
                                                    CommandNodeProvider.argument(ARG_TEAM_NAME, StringArgument.stringArgument())
                                                            .suggests(TeamCommand::suggestTeamNameFromInvites)
                                            )
                            )
                            .node(
                                    CommandNodeProvider.literal("decline")
                                            .executes(TeamCommand::declineInviteToTeam)
                                            .node(
                                                    CommandNodeProvider.argument(ARG_TEAM_NAME, StringArgument.stringArgument())
                                                            .suggests(TeamCommand::suggestTeamNameFromInvites)
                                            )
                            )
            )
            .node(
                    CommandNodeProvider.literal("kick")
                            .executes(TeamCommand::kickFromTeam)
                            .node(
                                    CommandNodeProvider.argument(ARG_PLAYER_NAME, StringArgument.stringArgument())
                                            .suggests(AbstractCommand::suggestPlayer)
                            )
            )
            .build();

    public TeamCommand() {
        super(COMMAND);
    }

    private static void executeDefault(CommandContext context) throws CommandException {
        throw new WrongUsageException("Not enough arguments. See /help team for more details");
    }

    private static void acceptInviteToTeam(CommandContext context) throws CommandException {

    }

    private static void declineInviteToTeam(CommandContext context) throws CommandException {

    }

    private static void leaveTeam(CommandContext context) throws CommandException {
        GameData gameData = getGameData(context);
        TeamGame<?> teamGame = getActiveTeamGame(context);
        EntityPlayer sender = getSenderAsPlayer(context);
        TeamManager teamManager = teamGame.getTeamManager();
        Team team = teamManager.getEntityTeam(sender);
        if (team == null) {
            throw new WrongUsageException("You are not member of any team");
        }
        if (teamGame.isStarted()) {
            throw new WrongUsageException("You cannot leave team while participating in active game");
        }
        if (team.isTeamLeader(sender)) {
            teamManager.disbandAndTransferMembers(team);
        } else {
            team.removeMemberById(sender.getUniqueID());
        }
        teamManager.createNewTeam(sender);
        // TODO notifications
        gameData.sendGameDataToClients();
    }

    private static void inviteToTeam(CommandContext context) throws CommandException {
        GameData gameData = getGameData(context);
        String inviteeName = context.getArgumentMandatory(ARG_PLAYER_NAME);
        TeamGame<?> game = getActiveTeamGame(context);
        EntityPlayer sender = getSenderAsPlayer(context);
        TeamInviteManager inviteHandler = game.getInviteHandler();
        EntityPlayer invitee = getPlayerByName(context, inviteeName);
        TeamInvite teamInvite = inviteHandler.invite(sender, invitee);
        if (teamInvite == null) {
            throw new WrongUsageException("Unable to invite player");
        }
        sender.sendMessage(new TextComponentTranslation("commands.pubgmc.team.invite.sent", invitee.getDisplayName()));
        ITextComponent inviteReceivedText = new TextComponentTranslation("commands.pubgmc.team.invite.received", teamInvite.getTeamName());
        addInviteControllerOptions(inviteReceivedText, teamInvite);
        // TODO send message to invitee with information about the invite
        gameData.sendGameDataToClients();
    }

    private static void listInvites(CommandContext context) throws CommandException {

    }

    private static void cancelInvite(CommandContext context) throws CommandException {

    }

    private static void kickFromTeam(CommandContext context) throws CommandException {
        GameData gameData = getGameData(context);
        String targetName = context.getArgumentMandatory(ARG_PLAYER_NAME);
        TeamGame<?> game = getActiveTeamGame(context);
        EntityPlayer sender = getSenderAsPlayer(context);
        EntityPlayer target = getPlayerByName(context, targetName);
        TeamManager teamManager = game.getTeamManager();
        Team senderTeam = teamManager.getEntityTeam(sender);
        Team targetTeam = teamManager.getEntityTeam(target);
        if (senderTeam == null || !Objects.equals(senderTeam, targetTeam)) {
            throw new WrongUsageException("You can kick only players from your team");
        }
        if (!senderTeam.isTeamLeader(sender)) {
            throw new WrongUsageException("Only team leader can kick other members");
        }
        if (sender.getUniqueID().equals(target.getUniqueID())) {
            throw new WrongUsageException("You cannot kick yourself from team");
        }
        if (game.isStarted()) {
            throw new WrongUsageException("You cannot kick players during active game");
        }
        senderTeam.removeMemberById(target.getUniqueID());
        teamManager.createNewTeam(target);
        // TODO send notification messages
        gameData.sendGameDataToClients();
    }

    private static List<String> suggestTeamNameFromInvites(SuggestionProvider.Context context) {
        ICommandSender sender = context.getSender();
        Entity entity = sender.getCommandSenderEntity();
        if (!(entity instanceof EntityPlayer))
            return Collections.emptyList();
        EntityPlayer player = (EntityPlayer) entity;
        return GameDataProvider.getGameData(player.world).<List<String>>map(data -> {
            Game<?> game = data.getCurrentGame();
            if (!(game instanceof TeamGame<?>)) {
                return Collections.emptyList();
            }
            TeamGame<?> teamGame = (TeamGame<?>) game;
            TeamInviteManager handler = teamGame.getInviteHandler();
            return handler.getPlayerInvites(player).stream().map(TeamInvite::getTeamName)
                    .collect(Collectors.toList());
        }).orElseGet(Collections::emptyList);
    }

    private static TeamGame<?> getActiveTeamGame(CommandContext context) throws CommandException {
        GameData gameData = getGameData(context);
        Game<?> game = gameData.getCurrentGame();
        if (!(game instanceof TeamGame<?>)) {
            throw new WrongUsageException("There is no game running or game does not support teams");
        }
        return (TeamGame<?>) game;
    }

    private static void addInviteControllerOptions(ITextComponent text, TeamInvite invite) {
        text.appendText(" ");
        ITextComponent acceptText = new TextComponentString("[" + TextComponentHelper.ACCEPT.getFormattedText() + "]");
        Style acceptTextStyle = acceptText.getStyle();
        acceptTextStyle.setColor(TextFormatting.GREEN);
        acceptTextStyle.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/team join " + invite.getTeamName()));
        text.appendSibling(acceptText);
    }
}

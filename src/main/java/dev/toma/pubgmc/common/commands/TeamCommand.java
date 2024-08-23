package dev.toma.pubgmc.common.commands;

import dev.toma.pubgmc.api.capability.GameData;
import dev.toma.pubgmc.api.capability.GameDataProvider;
import dev.toma.pubgmc.api.game.Game;
import dev.toma.pubgmc.api.game.team.TeamGame;
import dev.toma.pubgmc.api.game.team.TeamInvite;
import dev.toma.pubgmc.api.game.team.TeamInviteManager;
import dev.toma.pubgmc.api.game.team.TeamManager;
import dev.toma.pubgmc.api.game.team.Team;
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
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class TeamCommand extends AbstractCommand {

    private static final String ARG_TEAM_NAME = "teamName";
    private static final String ARG_PLAYER_NAME = "player";

    private static final CommandTree COMMAND = CommandTree.Builder.command("team")
            .usage("/team [leave|invite|kick]")
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
        GameData gameData = getGameData(context);
        TeamGame<?> teamGame = getActiveTeamGame(context);
        EntityPlayer sender = getSenderAsPlayer(context);
        TeamManager teamManager = teamGame.getTeamManager();
        TeamInviteManager inviteManager = teamGame.getInviteManager();
        String teamName = context.getArgumentMandatory(ARG_TEAM_NAME);
        if (teamGame.isStarted()) {
            throw new WrongUsageException("You cannot join teams in active games");
        }
        TeamInvite teamInvite = findInvite(inviteManager, sender, teamName);
        Team team = teamManager.getTeamById(teamInvite.getTeamId());
        Collection<Team.Member> members = team != null ? team.getAllMembers().values() : Collections.emptyList();
        if (inviteManager.inviteAccepted(teamInvite, sender)) {
            ITextComponent message = new TextComponentTranslation("commands.pubgmc.team.invite.accepted.team", sender.getDisplayName());
            forEachPlayerTeamMember(members, sender.world, player -> player.sendMessage(message));
            sender.sendMessage(new TextComponentTranslation("commands.pubgmc.team.invite.accepted.self", teamInvite.getSenderName()));
            gameData.sendGameDataToClients();
        } else {
            gameData.sendGameDataToClients();
            throw new WrongUsageException("Invite is no longer valid");
        }
    }

    private static void declineInviteToTeam(CommandContext context) throws CommandException {
        GameData gameData = getGameData(context);
        TeamGame<?> teamGame = getActiveTeamGame(context);
        EntityPlayer sender = getSenderAsPlayer(context);
        TeamManager teamManager = teamGame.getTeamManager();
        TeamInviteManager inviteManager = teamGame.getInviteManager();
        String teamName = context.getArgumentMandatory(ARG_TEAM_NAME);
        TeamInvite teamInvite = findInvite(inviteManager, sender, teamName);
        Team team = teamManager.getTeamById(teamInvite.getTeamId());
        Collection<Team.Member> members = team != null ? team.getAllMembers().values() : Collections.emptyList();
        inviteManager.inviteDeclined(teamInvite, sender);
        ITextComponent message = new TextComponentTranslation("commands.pubgmc.team.invite.rejected.team", sender.getDisplayName());
        forEachPlayerTeamMember(members, sender.world, player -> player.sendMessage(message));
        sender.sendMessage(new TextComponentTranslation("commands.pubgmc.team.invite.rejected.self", teamInvite.getSenderName()));
        gameData.sendGameDataToClients();
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
        if (teamManager.tryLeaveTeam(sender, team, false)) {
            ITextComponent message = new TextComponentTranslation("commands.pubgmc.team.member_left.team", sender.getDisplayName());
            forEachPlayerTeamMember(team.getAllMembers().values(), sender.world, player -> player.sendMessage(message));
            sender.sendMessage(new TextComponentTranslation("commands.pubgmc.team.member_left.self"));
        } else {
            throw new WrongUsageException("Team leaving is blocked by game settings");
        }
        gameData.sendGameDataToClients();
    }

    private static void inviteToTeam(CommandContext context) throws CommandException {
        GameData gameData = getGameData(context);
        String inviteeName = context.getArgumentMandatory(ARG_PLAYER_NAME);
        TeamGame<?> game = getActiveTeamGame(context);
        EntityPlayer sender = getSenderAsPlayer(context);
        TeamManager teamManager = game.getTeamManager();
        Team team = teamManager.getEntityTeam(sender);
        if (team == null) {
            throw new WrongUsageException("You are not member of any team");
        }
        if (!team.isTeamLeader(sender)) {
            throw new WrongUsageException("Only team leader can send invites");
        }
        if (game.isStarted()) {
            throw new WrongUsageException("You cannot invite players to your team in active games");
        }
        TeamInviteManager inviteHandler = game.getInviteManager();
        EntityPlayer invitee = getPlayerByName(context, inviteeName);
        if (sender.getUniqueID().equals(invitee.getUniqueID())) {
            throw new WrongUsageException("You cannot invite yourself");
        }
        TeamInvite teamInvite = inviteHandler.invite(sender, invitee);
        if (teamInvite == null) {
            throw new WrongUsageException("Unable to invite player");
        }
        sender.sendMessage(new TextComponentTranslation("commands.pubgmc.team.invite.sent", invitee.getDisplayName()));
        ITextComponent inviteReceivedText = new TextComponentTranslation("commands.pubgmc.team.invite.received.new", teamInvite.getSenderName());
        addInviteControllerOptions(inviteReceivedText, teamInvite);
        invitee.sendMessage(inviteReceivedText);
        gameData.sendGameDataToClients();
    }

    private static void listInvites(CommandContext context) throws CommandException {
        TeamGame<?> teamGame = getActiveTeamGame(context);
        EntityPlayer sender = getSenderAsPlayer(context);
        TeamInviteManager inviteManager = teamGame.getInviteManager();
        List<TeamInvite> invites = inviteManager.getPlayerInvites(sender);
        sender.sendMessage(new TextComponentTranslation("commands.pubgmc.team.invite.list.header"));
        for (TeamInvite teamInvite : invites) {
            ITextComponent component = new TextComponentTranslation("commands.pubgmc.team.invite.received", teamInvite.getSenderName());
            addInviteControllerOptions(component, teamInvite);
            sender.sendMessage(component);
        }
    }

    private static void cancelInvite(CommandContext context) throws CommandException {
        GameData gameData = getGameData(context);
        TeamGame<?> teamGame = getActiveTeamGame(context);
        EntityPlayer sender = getSenderAsPlayer(context);
        String targetName = context.getArgumentMandatory(ARG_PLAYER_NAME);
        EntityPlayer target = getPlayerByName(context, targetName);
        TeamManager teamManager = teamGame.getTeamManager();
        Team myTeam = teamManager.getEntityTeam(sender);
        if (myTeam == null) {
            throw new WrongUsageException("You are not member of any team");
        }
        if (!myTeam.isTeamLeader(sender)) {
            throw new WrongUsageException("Only team leader can manage invites");
        }
        TeamInviteManager inviteManager = teamGame.getInviteManager();
        TeamInvite teamInvite = findInvite(inviteManager, target, sender.getName());
        inviteManager.cancelInvite(teamInvite);
        sender.sendMessage(new TextComponentTranslation("commands.pubgmc.team.invite.cancelled.sender", target.getDisplayName()));
        target.sendMessage(new TextComponentTranslation("commands.pubgmc.team.invite.cancelled.invitee", sender.getDisplayName()));
        gameData.sendGameDataToClients();
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
        if (teamManager.tryLeaveTeam(target, senderTeam, true)) {
            ITextComponent message = new TextComponentTranslation("commands.pubgmc.team.member_kick.team", target.getDisplayName());
            forEachPlayerTeamMember(senderTeam.getAllMembers().values(), sender.world, player -> player.sendMessage(message));
            target.sendMessage(new TextComponentTranslation("commands.pubgmc.team.member_kick.target", sender.getDisplayName()));
        }
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
            TeamInviteManager handler = teamGame.getInviteManager();
            return handler.getPlayerInvites(player).stream().map(TeamInvite::getSenderName)
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
        acceptTextStyle.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/team invite accept " + invite.getSenderName()));
        text.appendSibling(acceptText);
        text.appendText(" ");
        ITextComponent declineText = new TextComponentString("[" + TextComponentHelper.DECLINE.getFormattedText() + "]");
        Style declineTextStyle = declineText.getStyle();
        declineTextStyle.setColor(TextFormatting.RED);
        declineTextStyle.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/team invite decline " + invite.getSenderName()));
        text.appendSibling(declineText);
    }

    @Nullable
    private static TeamInvite findInvite(TeamInviteManager manager, EntityPlayer sender, String teamName) throws CommandException {
        List<TeamInvite> invites = manager.getPlayerInvites(sender);
        for (TeamInvite invite : invites) {
            if (invite.getSenderName().equalsIgnoreCase(teamName)) {
                return invite;
            }
        }
        throw new WrongUsageException("No invite has been found from team " + teamName);
    }

    private static void forEachPlayerTeamMember(Collection<Team.Member> members, World world, Consumer<EntityPlayer> action) {
        members.forEach(member -> {
            if (member.getMemberType().isPlayer()) {
                EntityPlayer player = member.getPlayer(world);
                if (player != null) {
                    action.accept(player);
                }
            }
        });
    }
}

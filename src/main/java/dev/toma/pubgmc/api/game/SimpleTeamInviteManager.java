package dev.toma.pubgmc.api.game;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import dev.toma.pubgmc.api.game.util.Team;
import net.minecraft.entity.player.EntityPlayer;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class SimpleTeamInviteManager implements TeamInviteManager {

    private final TeamManager teamManager;
    private final Multimap<UUID, TeamInvite> inviteByTeamId;
    private final Multimap<UUID, TeamInvite> inviteByInviteeId;


    public SimpleTeamInviteManager(TeamManager teamManager) {
        this.teamManager = teamManager;
        this.inviteByTeamId = ArrayListMultimap.create();
        this.inviteByInviteeId = ArrayListMultimap.create();
    }

    @Nullable
    @Override
    public TeamInvite invite(EntityPlayer sender, EntityPlayer invitee) {
        Team team = teamManager.getEntityTeam(sender);
        if (team == null)
            return null;
        if (!canMemberIntoTeam(invitee, team)) {
            return null;
        }
        TeamInvite teamInvite = new TeamInvite(team.getTeamId(), invitee.getUniqueID(), team.getUsername(sender.getUniqueID()));
        inviteByTeamId.put(team.getTeamId(), teamInvite);
        inviteByInviteeId.put(invitee.getUniqueID(), teamInvite);
        return teamInvite;
    }

    @Override
    public boolean inviteAccepted(TeamInvite invite, EntityPlayer player) {
        return false; // TODO
    }

    @Override
    public void inviteDeclined(TeamInvite invite, EntityPlayer player) {
    }

    @Override
    public List<TeamInvite> getPlayerInvites(EntityPlayer player) {
        return null;
    }

    @Override
    public Optional<TeamInvite> getInviteByTeamId(EntityPlayer player, UUID teamId) {
        return Optional.empty();
    }

    @Override
    public Optional<TeamInvite> getInviteByTeamName(EntityPlayer player, String teamName) {
        return Optional.empty();
    }

    protected boolean canMemberIntoTeam(EntityPlayer invitee, Team team) {
        return true;
    }
}

package dev.toma.pubgmc.api.game.team;

import dev.toma.pubgmc.util.helper.SerializationHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

public class SimpleTeamInviteManager implements TeamInviteManager {

    private final TeamManager teamManager;
    private final Map<String, TeamInvite> invites;


    public SimpleTeamInviteManager(TeamManager teamManager) {
        this.teamManager = teamManager;
        this.invites = new HashMap<>();
    }

    @Nullable
    @Override
    public TeamInvite invite(EntityPlayer sender, EntityPlayer invitee) {
        Team team = teamManager.getEntityTeam(sender);
        if (team == null)
            return null;
        if (!teamManager.canJoinTeam(invitee, team)) {
            return null;
        }
        TeamInvite teamInvite = new TeamInvite(team.getId(), invitee.getUniqueID(), team.getUsername(sender.getUniqueID()));
        String key = teamInvite.getUniqueKey();
        TeamInvite invite = invites.getOrDefault(key, teamInvite);
        invites.put(key, invite);
        return invite;
    }

    @Override
    public boolean inviteAccepted(TeamInvite invite, EntityPlayer player) {
        Team team = teamManager.getTeamById(invite.getTeamId());
        if (team == null) {
            invites.remove(invite.getUniqueKey());
            return false;
        }
        String key = invite.getUniqueKey();
        if (!invites.containsKey(key)) {
            return false;
        }
        invites.remove(invite.getUniqueKey());
        if (teamManager.canJoinTeam(player, team)) {
            Team previousTeam = teamManager.getEntityTeam(player);
            if (previousTeam != null) {
                if (previousTeam.isTeamLeader(player)) {
                    teamManager.disbandAndTransferMembers(previousTeam);
                } else {
                    team.removeMemberById(player.getUniqueID());
                }
            }
            teamManager.join(team, player);
            return true;
        }
        return false;
    }

    @Override
    public void inviteDeclined(TeamInvite invite, EntityPlayer player) {
        invites.remove(invite.getUniqueKey());
    }

    @Override
    public void cancelInvite(TeamInvite invite) {
        invites.remove(invite.getUniqueKey());
    }

    @Override
    public void cancelPendingInvites() {
        this.invites.clear();
    }

    @Override
    public List<TeamInvite> getPlayerInvites(EntityPlayer player) {
        UUID playerId = player.getUniqueID();
        return invites.values().stream()
                .filter(invite -> invite.getInviteeId().equals(playerId))
                .collect(Collectors.toList());
    }

    @Override
    public NBTTagCompound serializeNBT() {
        NBTTagCompound nbt = new NBTTagCompound();
        nbt.setTag("inviteList", SerializationHelper.mapToNbt(invites, Function.identity(), TeamInvite::serialize));
        return nbt;
    }

    @Override
    public void deserializeNBT(NBTTagCompound nbt) {
        SerializationHelper.mapFromNbt(invites, nbt.getCompoundTag("inviteList"), Function.identity(), base -> TeamInvite.deserialize((NBTTagCompound) base));
    }
}

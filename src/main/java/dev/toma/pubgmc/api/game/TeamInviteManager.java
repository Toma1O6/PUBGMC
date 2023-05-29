package dev.toma.pubgmc.api.game;

import net.minecraft.entity.player.EntityPlayer;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TeamInviteManager {

    @Nullable
    TeamInvite invite(EntityPlayer sender, EntityPlayer invitee);

    // True if player can actually join the team
    boolean inviteAccepted(TeamInvite invite, EntityPlayer player);

    void inviteDeclined(TeamInvite invite, EntityPlayer player);

    List<TeamInvite> getPlayerInvites(EntityPlayer player);

    Optional<TeamInvite> getInviteByTeamId(EntityPlayer player, UUID teamId);

    Optional<TeamInvite> getInviteByTeamName(EntityPlayer player, String teamName);
}

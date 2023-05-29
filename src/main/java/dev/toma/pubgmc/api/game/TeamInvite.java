package dev.toma.pubgmc.api.game;

import java.util.UUID;

public final class TeamInvite {

    private final UUID teamId;
    private final UUID inviteeId;
    private final String senderName;

    public TeamInvite(UUID teamId, UUID inviteeId, String senderName) {
        this.teamId = teamId;
        this.inviteeId = inviteeId;
        this.senderName = senderName;
    }

    public UUID getTeamId() {
        return teamId;
    }
}

package dev.toma.pubgmc.api.game.team;

import net.minecraft.nbt.NBTTagCompound;

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

    public UUID getInviteeId() {
        return inviteeId;
    }

    public String getSenderName() {
        return senderName;
    }

    public String getUniqueKey() {
        return String.join("%s-%s", teamId.toString(), inviteeId.toString());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TeamInvite that = (TeamInvite) o;

        if (!teamId.equals(that.teamId)) return false;
        return inviteeId.equals(that.inviteeId);
    }

    @Override
    public int hashCode() {
        int result = teamId.hashCode();
        result = 31 * result + inviteeId.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return String.format("Invite [%s], Sent by %s", getUniqueKey(), getSenderName());
    }

    public NBTTagCompound serialize() {
        NBTTagCompound nbt = new NBTTagCompound();
        nbt.setUniqueId("teamId", teamId);
        nbt.setUniqueId("inviteeId", inviteeId);
        nbt.setString("sender", senderName);
        return nbt;
    }

    public static TeamInvite deserialize(NBTTagCompound nbt) {
        UUID teamId = nbt.getUniqueId("teamId");
        UUID inviteeId = nbt.getUniqueId("inviteeId");
        String sender = nbt.getString("sender");
        return new TeamInvite(teamId, inviteeId, sender);
    }
}

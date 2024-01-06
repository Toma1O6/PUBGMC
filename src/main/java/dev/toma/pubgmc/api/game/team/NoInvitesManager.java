package dev.toma.pubgmc.api.game.team;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;

import javax.annotation.Nullable;
import java.util.Collections;
import java.util.List;

public final class NoInvitesManager implements TeamInviteManager {

    private static final NoInvitesManager INSTANCE = new NoInvitesManager();

    private NoInvitesManager() {
    }

    public static TeamInviteManager noInvites() {
        return INSTANCE;
    }

    @Nullable
    @Override
    public TeamInvite invite(EntityPlayer sender, EntityPlayer invitee) {
        return null;
    }

    @Override
    public boolean inviteAccepted(TeamInvite invite, EntityPlayer player) {
        return false;
    }

    @Override
    public void inviteDeclined(TeamInvite invite, EntityPlayer player) {
    }

    @Override
    public void cancelInvite(TeamInvite invite) {
    }

    @Override
    public void cancelPendingInvites() {
    }

    @Override
    public List<TeamInvite> getPlayerInvites(EntityPlayer player) {
        return Collections.emptyList();
    }

    @Override
    public NBTTagCompound serializeNBT() {
        return new NBTTagCompound();
    }

    @Override
    public void deserializeNBT(NBTTagCompound nbt) {
    }
}

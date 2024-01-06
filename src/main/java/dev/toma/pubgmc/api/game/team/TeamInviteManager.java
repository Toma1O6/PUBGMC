package dev.toma.pubgmc.api.game.team;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.INBTSerializable;

import javax.annotation.Nullable;
import java.util.List;

public interface TeamInviteManager extends INBTSerializable<NBTTagCompound> {

    @Nullable
    TeamInvite invite(EntityPlayer sender, EntityPlayer invitee);

    // True if player can actually join the team
    boolean inviteAccepted(TeamInvite invite, EntityPlayer player);

    void inviteDeclined(TeamInvite invite, EntityPlayer player);

    void cancelInvite(TeamInvite invite);

    void cancelPendingInvites();

    List<TeamInvite> getPlayerInvites(EntityPlayer player);
}

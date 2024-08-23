package dev.toma.pubgmc.api.capability;

import dev.toma.pubgmc.api.game.groups.GroupManager;
import dev.toma.pubgmc.api.game.groups.Party;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.INBTSerializable;

import java.util.UUID;

public interface PartyData extends INBTSerializable<NBTTagCompound>, GroupManager {

    Party createParty(UUID owner);

    Party getPartyById(UUID owner);

    @Override
    Party getGroupForPlayer(UUID playerUUID);

    void synchronize();
}

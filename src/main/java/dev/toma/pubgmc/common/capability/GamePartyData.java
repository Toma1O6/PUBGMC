package dev.toma.pubgmc.common.capability;

import dev.toma.pubgmc.api.capability.PartyData;
import dev.toma.pubgmc.api.game.groups.Party;
import dev.toma.pubgmc.network.PacketHandler;
import dev.toma.pubgmc.network.s2c.S2C_PacketSendPartyData;
import dev.toma.pubgmc.util.helper.SerializationHelper;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.world.World;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class GamePartyData implements PartyData {

    private final World world;
    private final Map<UUID, Party> parties = new HashMap<>();
    private final Map<UUID, Party> playerParties = new HashMap<>();

    public GamePartyData() {
        this(null);
    }

    public GamePartyData(World world) {
        this.world = world;
    }

    @Override
    public Party createParty(UUID owner) {
        if (this.getPartyById(owner) != null) {
            throw new IllegalArgumentException("Party already exists for ID " + owner);
        }
        if (this.getGroupForPlayer(owner) != null) {
            throw new IllegalArgumentException("Player is already leader of another party " + owner);
        }
        final Party party = new Party(owner);
        parties.put(owner, party);
        return party;
    }

    @Override
    public Party getPartyById(UUID owner) {
        return this.parties.get(owner);
    }

    @Override
    public Party getGroupForPlayer(UUID playerUUID) {
        return this.playerParties.get(playerUUID);
    }

    @Override
    public NBTTagCompound serializeNBT() {
        NBTTagCompound tag = new NBTTagCompound();
        tag.setTag("parties", SerializationHelper.mapToNbt(this.parties, UUID::toString, Party::serialize));
        tag.setTag("playerParties", SerializationHelper.mapToNbt(this.playerParties, UUID::toString, party -> new NBTTagString(party.getId().toString())));
        return tag;
    }

    @Override
    public void deserializeNBT(NBTTagCompound nbt) {

    }

    @Override
    public void synchronize() {
        if (!world.isRemote) {
            PacketHandler.sendToAllClients(new S2C_PacketSendPartyData(this.serializeNBT()));
        }
    }
}

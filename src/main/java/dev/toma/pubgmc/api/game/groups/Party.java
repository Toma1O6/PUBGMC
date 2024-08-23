package dev.toma.pubgmc.api.game.groups;

import dev.toma.pubgmc.util.helper.SerializationHelper;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagString;
import net.minecraftforge.common.util.Constants;

import java.util.*;

public final class Party implements Group {

    private final UUID partyId;
    private final UUID owner;
    private final Set<UUID> members;
    private final Map<UUID, String> usernames;
    private final PartyOptions options;

    public Party(UUID owner) {
        this(UUID.randomUUID(), owner, new PartyOptions());
    }

    private Party(UUID partyId, UUID owner, PartyOptions options) {
        this.partyId = partyId;
        this.owner = owner;
        this.members = new LinkedHashSet<>();
        this.usernames = new HashMap<>();
        this.options = options;
    }

    @Override
    public UUID getId() {
        return this.partyId;
    }

    @Override
    public UUID getLeader() {
        return this.owner;
    }

    @Override
    public String getUsername(UUID uuid) {
        return this.usernames.getOrDefault(uuid, "<< Unknown player >>");
    }

    public PartyOptions getOptions() {
        return options;
    }

    public NBTTagCompound serialize() {
        NBTTagCompound tag = new NBTTagCompound();
        tag.setString("partyId", this.partyId.toString());
        tag.setString("owner", this.owner.toString());
        tag.setTag("members", SerializationHelper.collectionToNbt(members, uuid -> new NBTTagString(uuid.toString())));
        tag.setTag("usernames", SerializationHelper.mapToNbt(this.usernames, UUID::toString, NBTTagString::new));
        // TODO options
        return tag;
    }

    public static Party deserialize(NBTTagCompound tag) {
        UUID partyId = UUID.fromString(tag.getString("partyId"));
        UUID owner = UUID.fromString(tag.getString("owner"));
        Party party = new Party(partyId, owner, new PartyOptions());
        // TODO options
        SerializationHelper.collectionFromNbt(party.members, tag.getTagList("members", Constants.NBT.TAG_STRING), base -> UUID.fromString(((NBTTagString) base).getString()));
        SerializationHelper.mapFromNbt(party.usernames, tag.getCompoundTag("usernames"), UUID::fromString, nbt -> ((NBTTagString) nbt).getString());
        return party;
    }
}

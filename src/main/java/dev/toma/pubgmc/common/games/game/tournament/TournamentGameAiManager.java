package dev.toma.pubgmc.common.games.game.tournament;

import dev.toma.pubgmc.common.games.util.SimpleAiManager;
import dev.toma.pubgmc.util.helper.SerializationHelper;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagString;
import net.minecraftforge.common.util.Constants;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TournamentGameAiManager extends SimpleAiManager {

    private final List<UUID> additionalEntities;

    public TournamentGameAiManager() {
        this.additionalEntities = new ArrayList<>();
    }

    public void addExtraEntity(UUID uuid) {
        this.additionalEntities.add(uuid);
    }

    public List<UUID> getAdditionalEntities() {
        return additionalEntities;
    }

    @Override
    public NBTTagCompound serialize() {
        NBTTagCompound nbt = super.serialize();
        nbt.setTag("additionals", SerializationHelper.collectionToNbt(additionalEntities, uuid -> new NBTTagString(uuid.toString())));
        return nbt;
    }

    @Override
    public void deserialize(NBTTagCompound nbt) {
        super.deserialize(nbt);
        SerializationHelper.collectionFromNbt(additionalEntities, nbt.getTagList("additionals", Constants.NBT.TAG_STRING), base -> UUID.fromString(((NBTTagString) base).getString()));
    }
}

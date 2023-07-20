package dev.toma.pubgmc.common.games.game;

import dev.toma.pubgmc.api.game.loadout.EntityLoadout;
import dev.toma.pubgmc.api.game.loadout.GameLoadoutManager;
import dev.toma.pubgmc.api.game.loadout.LoadoutManager;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.world.World;
import net.minecraftforge.common.util.Constants;

import java.util.*;

public final class SimpleLoadoutManager implements GameLoadoutManager {

    private final Map<UUID, EntityLoadout> selectedLoadouts;
    private final EntityLoadout defaultLoadout;
    private final List<EntityLoadout> available;

    public SimpleLoadoutManager(EntityLoadout defaultLoadout, List<EntityLoadout> available) {
        this.defaultLoadout = defaultLoadout;
        this.available = available;
        this.selectedLoadouts = new HashMap<>();
    }

    public EntityLoadout getLoadout(UUID uuid) {
        return selectedLoadouts.getOrDefault(uuid, defaultLoadout);
    }

    @Override
    public boolean hasSelectedLoadout(UUID uuid) {
        return selectedLoadouts.containsKey(uuid);
    }

    @Override
    public void selectLoadout(UUID uuid, int loadout, World world) {
        if (loadout >= 0 && loadout < available.size()) {
            selectedLoadouts.put(uuid, available.get(loadout));
        }
    }

    @Override
    public void applyLoadout(EntityLivingBase entity) {
        EntityLoadout loadout = getLoadout(entity.getUniqueID());
        LoadoutManager.apply(entity, loadout);
    }

    public void select(UUID uuid, EntityLoadout loadout) {
        selectedLoadouts.put(uuid, loadout);
    }

    public List<EntityLoadout> getAvailableLoadouts() {
        return available;
    }

    public NBTTagCompound serialize() {
        NBTTagCompound nbt = new NBTTagCompound();
        NBTTagCompound selected = new NBTTagCompound();
        for (Map.Entry<UUID, EntityLoadout> loadout : selectedLoadouts.entrySet()) {
            selected.setString(loadout.getKey().toString(), LoadoutManager.loadoutToString(loadout.getValue()));
        }
        nbt.setTag("selected", selected);
        NBTTagList list = new NBTTagList();
        available.forEach(loadout -> list.appendTag(new NBTTagString(LoadoutManager.loadoutToString(loadout))));
        nbt.setTag("available", list);
        return nbt;
    }

    public void deserialize(NBTTagCompound nbt) {
        selectedLoadouts.clear();
        available.clear();
        NBTTagCompound selected = nbt.getCompoundTag("selected");
        Set<String> keys = selected.getKeySet();
        keys.forEach(key -> {
            UUID uuid = UUID.fromString(key);
            String json = selected.getString(key);
            EntityLoadout loadout = LoadoutManager.loadoutFromString(json);
            selectedLoadouts.put(uuid, loadout);
        });
        NBTTagList list = nbt.getTagList("available", Constants.NBT.TAG_STRING);
        for (int i = 0; i < list.tagCount(); i++) {
            available.add(LoadoutManager.loadoutFromString(list.getStringTagAt(i)));
        }
    }
}

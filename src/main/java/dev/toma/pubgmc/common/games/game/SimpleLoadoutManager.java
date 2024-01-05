package dev.toma.pubgmc.common.games.game;

import com.google.common.collect.ImmutableList;
import dev.toma.pubgmc.Pubgmc;
import dev.toma.pubgmc.api.game.loadout.EntityLoadout;
import dev.toma.pubgmc.api.game.loadout.GameLoadoutManager;
import dev.toma.pubgmc.api.game.loadout.LoadoutManager;
import dev.toma.pubgmc.util.PUBGMCUtil;
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
    private final boolean applyLoadoutImmediately;
    private boolean locked;

    public SimpleLoadoutManager(EntityLoadout defaultLoadout, List<EntityLoadout> available) {
        this(defaultLoadout, available, true);
    }

    public SimpleLoadoutManager(EntityLoadout defaultLoadout, List<EntityLoadout> available, boolean applyLoadoutImmediately) {
        this.defaultLoadout = defaultLoadout;
        this.available = available;
        this.applyLoadoutImmediately = applyLoadoutImmediately;
        this.selectedLoadouts = new HashMap<>();
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
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
            select(uuid, available.get(loadout));
        }
    }

    @Override
    public void applyLoadout(EntityLivingBase entity) {
        EntityLoadout loadout = getLoadout(entity.getUniqueID());
        LoadoutManager.apply(entity, loadout);
    }

    @Override
    public boolean shouldApplyLoadoutAfterSelection(EntityLivingBase entity) {
        return applyLoadoutImmediately;
    }

    public void select(UUID uuid, EntityLoadout loadout) {
        if (locked) {
            Pubgmc.logger.warn("{} entity has attempted to select loadout while selection was locked", uuid);
            return;
        }
        selectedLoadouts.put(uuid, loadout);
    }

    public void selectRandomly(UUID uuid, Random random) {
        EntityLoadout loadout = PUBGMCUtil.randomListElement(available, random);
        if (loadout != null) {
            select(uuid, loadout);
        }
    }

    public void selectRandomly(Collection<UUID> collection, Random random, boolean onlyMissing) {
        for (UUID uuid : collection) {
            boolean selectFlag = !hasSelectedLoadout(uuid) || !onlyMissing;
            if (selectFlag) {
                selectRandomly(uuid, random);
            }
        }
    }

    public List<EntityLoadout> getAvailableLoadouts() {
        return ImmutableList.copyOf(available);
    }

    public int getSelectedLoadoutsCount() {
        return selectedLoadouts.size();
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
        nbt.setBoolean("locked", locked);
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
        locked = nbt.getBoolean("locked");
    }
}

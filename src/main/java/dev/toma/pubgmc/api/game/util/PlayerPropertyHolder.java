package dev.toma.pubgmc.api.game.util;

import dev.toma.pubgmc.Pubgmc;
import dev.toma.pubgmc.api.properties.Properties;
import dev.toma.pubgmc.api.properties.PropertyType;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;

import java.util.*;
import java.util.stream.Collectors;

public final class PlayerPropertyHolder {

    private static final ITextComponent DEFAULT_USERNAME = new TextComponentString("<Unknown>");
    private final Map<UUID, ScoreEntry> playerScoreEntries = new HashMap<>();
    private final Map<PropertyType<?>, Object> registeredProperties = new HashMap<>();

    public void register(Entity entity) {
        UUID entityId = entity.getUniqueID();
        ITextComponent username = entity.getDisplayName();
        ScoreEntry entry = new ScoreEntry(username.getFormattedText());
        playerScoreEntries.put(entityId, entry);
    }

    public void delete(UUID uuid) {
        playerScoreEntries.remove(uuid);
    }

    public <T> List<UUID> getSortedOwners(PropertyType<T> type, Comparator<T> comparator, T defaultValue) {
        List<UUID> list = new ArrayList<>(playerScoreEntries.keySet());
        return list.stream()
                .sorted(Comparator.comparing(uuid -> getProperty(uuid, type, defaultValue), comparator))
                .collect(Collectors.toList());
    }

    public <V> void registerProperty(PropertyType<V> type, V initialValue) {
        registeredProperties.put(type, initialValue);
    }

    public <V> void setProperty(UUID ownerId, PropertyType<V> propertyId, V value) {
        ScoreEntry entry = playerScoreEntries.get(ownerId);
        if (entry == null) {
            Pubgmc.logger.error("Attempted to modify property '{}' of unregistered entity with ID {}. Value {}", propertyId, ownerId, value);
            return;
        }
        entry.properties.put(propertyId, value);
    }

    public <T> T getProperty(UUID ownerId, PropertyType<T> property) {
        return getProperty(ownerId, property, null);
    }

    @SuppressWarnings("unchecked")
    public <T> T getProperty(UUID ownerId, PropertyType<T> property, T fallback) {
        ScoreEntry entry = playerScoreEntries.get(ownerId);
        if (entry == null) {
            return fallback;
        }
        Object prop = entry.properties.get(property);
        try {
            T t = (T) prop;
            if (t == null) {
                T initialValue = (T) registeredProperties.get(property);
                if (initialValue != null) {
                    t = initialValue;
                    setProperty(ownerId, property, initialValue);
                } else {
                    return fallback;
                }
            }
            return t;
        } catch (ClassCastException e) {
            return fallback;
        }
    }

    public int increaseInt(UUID ownerId, PropertyType<Integer> property, int amount) {
        Integer value = getProperty(ownerId, property, 0);
        if (value == null) {
            return -1;
        }
        int newValue = value + amount;
        ScoreEntry entry = playerScoreEntries.get(ownerId);
        if (entry == null) {
            return -1;
        }
        entry.properties.put(property, newValue);
        return newValue;
    }

    public int increaseInt(UUID ownerId, PropertyType<Integer> property) {
        return increaseInt(ownerId, property, 1);
    }

    public <T> T compareAndGet(PropertyType<T> type, T defaultValue, Comparator<T> comparator) {
        List<T> list = new ArrayList<>();
        Set<UUID> keys = playerScoreEntries.keySet();
        keys.forEach(uuid -> list.add(getProperty(uuid, type, defaultValue)));
        list.sort(comparator);
        return list.isEmpty() ? defaultValue : list.get(0);
    }

    public String getUsername(UUID ownerId) {
        ScoreEntry entry = playerScoreEntries.get(ownerId);
        return entry != null ? entry.username : DEFAULT_USERNAME.getFormattedText();
    }

    public NBTTagCompound serialize() {
        NBTTagCompound compound = new NBTTagCompound();
        for (Map.Entry<UUID, ScoreEntry> entry : playerScoreEntries.entrySet()) {
            NBTTagCompound data = entry.getValue().serialize();
            compound.setTag(entry.getKey().toString(), data);
        }
        return compound;
    }

    public void deserialize(NBTTagCompound nbt) {
        playerScoreEntries.clear();
        Set<String> keys = nbt.getKeySet();
        for (String key : keys) {
            NBTTagCompound data = nbt.getCompoundTag(key);
            UUID uuid = UUID.fromString(key);
            ScoreEntry entry = ScoreEntry.deserialize(data);
            playerScoreEntries.put(uuid, entry);
        }
    }

    private static final class ScoreEntry {

        private final String username;
        private final Map<PropertyType<?>, Object> properties = new HashMap<>();

        public ScoreEntry(String username) {
            this.username = username;
        }

        @SuppressWarnings("unchecked")
        <T> NBTTagCompound serialize() {
            NBTTagCompound nbt = new NBTTagCompound();
            nbt.setString("username", username);
            NBTTagCompound props = new NBTTagCompound();
            for (Map.Entry<PropertyType<?>, Object> entry : properties.entrySet()) {
                PropertyType<T> type = (PropertyType<T>) entry.getKey();
                T value = (T) entry.getValue();
                type.serializeTo(props, value);
            }
            nbt.setTag("props", props);
            return nbt;
        }

        static <T> ScoreEntry deserialize(NBTTagCompound nbt) {
            String username = nbt.getString("username");
            NBTTagCompound props = nbt.getCompoundTag("props");
            Set<String> keys = props.getKeySet();
            ScoreEntry entry = new ScoreEntry(username);
            for (String key : keys) {
                PropertyType<T> type = Properties.getProperty(new ResourceLocation(key));
                if (type == null) {
                    Pubgmc.logger.warn("Unknown property type '{}', skipping...", key);
                    continue;
                }
                T value = type.deserializeFrom(props, key);
                entry.properties.put(type, value);
            }
            return entry;
        }
    }
}

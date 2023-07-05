package dev.toma.pubgmc.api.game.util;

import dev.toma.pubgmc.Pubgmc;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import org.apache.logging.log4j.util.TriConsumer;

import java.util.*;
import java.util.function.BiFunction;

public final class PlayerPropertyHolder {

    private static final ITextComponent DEFAULT_USERNAME = new TextComponentString("<Unknown>");
    private final Map<UUID, ScoreEntry> playerScoreEntries = new HashMap<>();

    public void register(Entity entity) {
        UUID entityId = entity.getUniqueID();
        ITextComponent username = entity.getDisplayName();
        ScoreEntry entry = new ScoreEntry(username.getFormattedText());
        playerScoreEntries.put(entityId, entry);
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
            return (T) prop;
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
        playerScoreEntries.get(ownerId).properties.put(property, newValue);
        return newValue;
    }

    public int increaseInt(UUID ownerId, PropertyType<Integer> property) {
        return increaseInt(ownerId, property, 1);
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

    public static final class PropertyType<V> {

        private static final Map<ResourceLocation, PropertyType<?>> REGISTERED_PROPERTIES = new HashMap<>();

        private final ResourceLocation identifier;
        private final PropertyValueSerializer<V> serializer;

        public PropertyType(ResourceLocation identifier, PropertyValueSerializer<V> serializer) {
            this.identifier = identifier;
            this.serializer = serializer;
        }

        public static void registerProperty(PropertyType<?> type) {
            REGISTERED_PROPERTIES.put(type.identifier, type);
        }

        @SuppressWarnings("unchecked")
        public static <T> PropertyType<T> getProperty(ResourceLocation identifier) {
            return (PropertyType<T>) REGISTERED_PROPERTIES.get(identifier);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            PropertyType<?> that = (PropertyType<?>) o;
            return Objects.equals(identifier, that.identifier);
        }

        @Override
        public int hashCode() {
            return Objects.hash(identifier);
        }

        @Override
        public String toString() {
            return identifier.toString();
        }
    }

    public interface PropertyValueSerializer<V> {
        void toNbt(NBTTagCompound nbt, String propertyId, V value);
        V fromNbt(NBTTagCompound nbt, String propertyId);

        static <T> PropertyValueSerializer<T> primitive(TriConsumer<NBTTagCompound, String, T> nbtSerialize,
                BiFunction<NBTTagCompound, String, T> nbtDeserialize) {
            return new PropertyValueSerializer<T>() {
                @Override
                public void toNbt(NBTTagCompound nbt, String propertyId, T value) {
                    nbtSerialize.accept(nbt, propertyId, value);
                }
                @Override
                public T fromNbt(NBTTagCompound nbt, String propertyId) {
                    return nbtDeserialize.apply(nbt, propertyId);
                }
            };
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
                PropertyValueSerializer<T> serializer = type.serializer;
                serializer.toNbt(props, type.identifier.toString(), value);
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
                PropertyType<T> type = PropertyType.getProperty(new ResourceLocation(key));
                if (type == null) {
                    Pubgmc.logger.warn("Unknown property type '{}', skipping...", key);
                    continue;
                }
                PropertyValueSerializer<T> serializer = type.serializer;
                T value = serializer.fromNbt(props, key);
                entry.properties.put(type, value);
            }
            return entry;
        }
    }
}

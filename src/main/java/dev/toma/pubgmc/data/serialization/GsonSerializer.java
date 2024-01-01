package dev.toma.pubgmc.data.serialization;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import dev.toma.pubgmc.api.data.DataReader;
import dev.toma.pubgmc.api.data.DataWriter;
import net.minecraft.util.JsonUtils;

import javax.annotation.Nullable;
import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Function;

public class GsonSerializer implements DataWriter<JsonObject>, DataReader<JsonObject> {

    private final JsonObject root;

    public GsonSerializer(JsonObject root) {
        this.root = Objects.requireNonNull(root);
    }

    public GsonSerializer() {
        this(new JsonObject());
    }

    @Override
    public void writeBoolean(String name, boolean value) {
        root.addProperty(name, value);
    }

    @Override
    public void writeByte(String name, byte value) {
        root.addProperty(name, value);
    }

    @Override
    public void writeShort(String name, short value) {
        root.addProperty(name, value);
    }

    @Override
    public void writeInt(String name, int value) {
        root.addProperty(name, value);
    }

    @Override
    public void writeLong(String name, long value) {
        root.addProperty(name, value);
    }

    @Override
    public void writeFloat(String name, float value) {
        root.addProperty(name, value);
    }

    @Override
    public void writeDouble(String name, double value) {
        root.addProperty(name, value);
    }

    @Override
    public void writeString(String name, String value) {
        root.addProperty(name, value);
    }

    @Override
    public void writeBooleanArray(String name, boolean[] array) {
        JsonArray jsonArray = new JsonArray();
        for (boolean value : array) {
            jsonArray.add(value);
        }
        root.add(name, jsonArray);
    }

    @Override
    public void writeByteArray(String name, byte[] array) {
        JsonArray jsonArray = new JsonArray();
        for (byte value : array) {
            jsonArray.add(value);
        }
        root.add(name, jsonArray);
    }

    @Override
    public void writeShortArray(String name, short[] array) {
        JsonArray jsonArray = new JsonArray();
        for (short value : array) {
            jsonArray.add(value);
        }
        root.add(name, jsonArray);
    }

    @Override
    public void writeIntArray(String name, int[] array) {
        JsonArray jsonArray = new JsonArray();
        for (int value : array) {
            jsonArray.add(value);
        }
        root.add(name, jsonArray);
    }

    @Override
    public void writeLongArray(String name, long[] array) {
        JsonArray jsonArray = new JsonArray();
        for (long value : array) {
            jsonArray.add(value);
        }
        root.add(name, jsonArray);
    }

    @Override
    public void writeFloatArray(String name, float[] array) {
        JsonArray jsonArray = new JsonArray();
        for (float value : array) {
            jsonArray.add(value);
        }
        root.add(name, jsonArray);
    }

    @Override
    public void writeDoubleArray(String name, double[] array) {
        JsonArray jsonArray = new JsonArray();
        for (double value : array) {
            jsonArray.add(value);
        }
        root.add(name, jsonArray);
    }

    @Override
    public void writeStringArray(String name, String[] array) {
        JsonArray jsonArray = new JsonArray();
        for (String value : array) {
            jsonArray.add(value);
        }
        root.add(name, jsonArray);
    }

    @Override
    public <V> void write(String name, V object, BiConsumer<V, DataWriter<JsonObject>> objectSerializer) {
        GsonSerializer serializer = new GsonSerializer();
        objectSerializer.accept(object, serializer);
        root.add(name, serializer.getJson());
    }

    @Override
    public <V> void writeArray(String name, V[] array, BiConsumer<V, DataWriter<JsonObject>> objectSerializer) {
        JsonArray jsonArray = new JsonArray();
        for (V v : array) {
            GsonSerializer serializer = new GsonSerializer();
            objectSerializer.accept(v, serializer);
            jsonArray.add(serializer.getJson());
        }
        root.add(name, jsonArray);
    }

    @Override
    public <V> void writeCollection(String name, Collection<V> collection, BiConsumer<V, DataWriter<JsonObject>> objectSerializer) {
        JsonArray array = new JsonArray();
        collection.stream().map(v -> {
            GsonSerializer serializer = new GsonSerializer();
            objectSerializer.accept(v, serializer);
            return serializer.getJson();
        }).forEach(array::add);
        root.add(name, array);
    }

    @Override
    public <K, V> void writeMap(String name, Map<K, V> map, Function<K, String> keySerializer, BiConsumer<V, DataWriter<JsonObject>> valueSerializer) {
        JsonObject object = new JsonObject();
        for (Map.Entry<K, V> entry : map.entrySet()) {
            String key = keySerializer.apply(entry.getKey());
            GsonSerializer serializer = new GsonSerializer();
            valueSerializer.accept(entry.getValue(), serializer);
            object.add(key, serializer.getJson());
        }
        root.add(name, object);
    }

    @Override
    public boolean readBoolean(String name, @Nullable Boolean defaultValue) {
        return !root.has(name) && defaultValue != null ? defaultValue : root.getAsJsonPrimitive(name).getAsBoolean();
    }

    @Override
    public byte readByte(String name, @Nullable Byte defaultValue) {
        return !root.has(name) && defaultValue != null ? defaultValue : root.getAsJsonPrimitive(name).getAsByte();
    }

    @Override
    public short readShort(String name, @Nullable Short defaultValue) {
        return !root.has(name) && defaultValue != null ? defaultValue : root.getAsJsonPrimitive(name).getAsShort();
    }

    @Override
    public int readInt(String name, @Nullable Integer defaultValue) {
        return !root.has(name) && defaultValue != null ? defaultValue : root.getAsJsonPrimitive(name).getAsInt();
    }

    @Override
    public long readLong(String name, @Nullable Long defaultValue) {
        return !root.has(name) && defaultValue != null ? defaultValue : root.getAsJsonPrimitive(name).getAsLong();
    }

    @Override
    public float readFloat(String name, @Nullable Float defaultValue) {
        return !root.has(name) && defaultValue != null ? defaultValue : root.getAsJsonPrimitive(name).getAsFloat();
    }

    @Override
    public double readDouble(String name, @Nullable Double defaultValue) {
        return !root.has(name) && defaultValue != null ? defaultValue : root.getAsJsonPrimitive(name).getAsDouble();
    }

    @Override
    public String readString(String name, @Nullable String defaultValue) {
        return !root.has(name) && defaultValue != null ? defaultValue : root.getAsJsonPrimitive(name).getAsString();
    }

    @Override
    public boolean[] readBooleanArray(String name, @Nullable boolean[] defaultValue) {
        if (!root.has(name) && defaultValue != null) {
            return defaultValue;
        }
        JsonArray array = JsonUtils.getJsonArray(root, name);
        boolean[] result = new boolean[array.size()];
        for (int i = 0; i < array.size(); i++) {
            result[i] = array.get(i).getAsBoolean();
        }
        return result;
    }

    @Override
    public byte[] readByteArray(String name, @Nullable byte[] defaultValue) {
        if (!root.has(name) && defaultValue != null) {
            return defaultValue;
        }
        JsonArray array = JsonUtils.getJsonArray(root, name);
        byte[] result = new byte[array.size()];
        for (int i = 0; i < array.size(); i++) {
            result[i] = array.get(i).getAsByte();
        }
        return result;
    }

    @Override
    public short[] readShortArray(String name, @Nullable short[] defaultValue) {
        if (!root.has(name) && defaultValue != null) {
            return defaultValue;
        }
        JsonArray array = JsonUtils.getJsonArray(root, name);
        short[] result = new short[array.size()];
        for (int i = 0; i < array.size(); i++) {
            result[i] = array.get(i).getAsShort();
        }
        return result;
    }

    @Override
    public int[] readIntArray(String name, @Nullable int[] defaultValue) {
        if (!root.has(name) && defaultValue != null) {
            return defaultValue;
        }
        JsonArray array = JsonUtils.getJsonArray(root, name);
        int[] result = new int[array.size()];
        for (int i = 0; i < array.size(); i++) {
            result[i] = array.get(i).getAsInt();
        }
        return result;
    }

    @Override
    public long[] readLongArray(String name, @Nullable long[] defaultValue) {
        if (!root.has(name) && defaultValue != null) {
            return defaultValue;
        }
        JsonArray array = JsonUtils.getJsonArray(root, name);
        long[] result = new long[array.size()];
        for (int i = 0; i < array.size(); i++) {
            result[i] = array.get(i).getAsLong();
        }
        return result;
    }

    @Override
    public float[] readFloatArray(String name, @Nullable float[] defaultValue) {
        if (!root.has(name) && defaultValue != null) {
            return defaultValue;
        }
        JsonArray array = JsonUtils.getJsonArray(root, name);
        float[] result = new float[array.size()];
        for (int i = 0; i < array.size(); i++) {
            result[i] = array.get(i).getAsFloat();
        }
        return result;
    }

    @Override
    public double[] readDoubleArray(String name, @Nullable double[] defaultValue) {
        if (!root.has(name) && defaultValue != null) {
            return defaultValue;
        }
        JsonArray array = JsonUtils.getJsonArray(root, name);
        double[] result = new double[array.size()];
        for (int i = 0; i < array.size(); i++) {
            result[i] = array.get(i).getAsDouble();
        }
        return result;
    }

    @Override
    public String[] readStringArray(String name, @Nullable String[] defaultValue) {
        if (!root.has(name) && defaultValue != null) {
            return defaultValue;
        }
        JsonArray array = JsonUtils.getJsonArray(root, name);
        String[] result = new String[array.size()];
        for (int i = 0; i < array.size(); i++) {
            result[i] = array.get(i).getAsString();
        }
        return result;
    }

    @Override
    public <V> V read(String name, Function<DataReader<JsonObject>, V> parser, @Nullable V defaultValue) {
        if (!root.has(name) && defaultValue != null) {
            return defaultValue;
        }
        JsonObject object = JsonUtils.getJsonObject(root, name);
        GsonSerializer serializer = new GsonSerializer(object);
        return parser.apply(serializer);
    }

    @Override
    public <V> V[] readArray(String name, Function<Integer, V[]> arrayFactory, Function<DataReader<JsonObject>, V> parser, @Nullable V[] defaultValue) {
        if (!root.has(name) && defaultValue != null) {
            return defaultValue;
        }
        JsonArray array = JsonUtils.getJsonArray(root, name);
        V[] result = arrayFactory.apply(array.size());
        for (int i = 0; i < array.size(); i++) {
            GsonSerializer serializer = new GsonSerializer(array.get(i).getAsJsonObject());
            result[i] = parser.apply(serializer);
        }
        return result;
    }

    @Override
    public <E extends Enum<E>> void writeEnum(String name, E enumVal) {
        writeString(name, enumVal.name());
    }

    @Override
    public <E extends Enum<E>> E readEnum(String name, Class<E> enumType, @Nullable E defaultValue) {
        try {
            return Enum.valueOf(enumType, name);
        } catch (Exception e) {
            // assume that atleast one enum constant is always defined
            return defaultValue != null ? defaultValue : enumType.getEnumConstants()[0];
        }
    }

    @Override
    public <V, C extends Collection<V>> C readCollection(String name, Function<Integer, C> collectionConstructor, Function<DataReader<JsonObject>, V> parser, @Nullable C defaultValue) {
        if (!root.has(name) && defaultValue != null) {
            return defaultValue;
        }
        JsonArray array = JsonUtils.getJsonArray(root, name);
        int length = array.size();
        C collection = collectionConstructor.apply(length);
        for (JsonElement element : array) {
            GsonSerializer serializer = new GsonSerializer(element.getAsJsonObject());
            V value = parser.apply(serializer);
            collection.add(value);
        }
        return collection;
    }

    @Override
    public <K, V, M extends Map<K, V>> M readMap(String name, Function<Integer, M> mapConstructor, Function<String, K> keyParser, Function<DataReader<JsonObject>, V> valueParser, @Nullable M defaultValue) {
        if (!root.has(name) && defaultValue != null) {
            return defaultValue;
        }
        JsonObject object = JsonUtils.getJsonObject(root, name);
        Set<Map.Entry<String, JsonElement>> entries = object.entrySet();
        M map = mapConstructor.apply(entries.size());
        for (Map.Entry<String, JsonElement> entry : entries) {
            K key = keyParser.apply(entry.getKey());
            GsonSerializer serializer = new GsonSerializer(entry.getValue().getAsJsonObject());
            V value = valueParser.apply(serializer);
            map.put(key, value);
        }
        return map;
    }

    public JsonObject getJson() {
        return root;
    }
}

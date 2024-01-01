package dev.toma.pubgmc.data.serialization;

import dev.toma.pubgmc.api.data.DataReader;
import dev.toma.pubgmc.api.data.DataWriter;
import net.minecraft.nbt.*;
import net.minecraftforge.common.util.Constants;

import javax.annotation.Nullable;
import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Function;

public class NbtSerializer implements DataWriter<NBTTagCompound>, DataReader<NBTTagCompound> {

    private final NBTTagCompound root;

    public NbtSerializer(NBTTagCompound root) {
        this.root = Objects.requireNonNull(root);
    }

    public NbtSerializer() {
        this(new NBTTagCompound());
    }

    @Override
    public void writeBoolean(String name, boolean value) {
        root.setBoolean(name, value);
    }

    @Override
    public void writeByte(String name, byte value) {
        root.setByte(name, value);
    }

    @Override
    public void writeShort(String name, short value) {
        root.setShort(name, value);
    }

    @Override
    public void writeInt(String name, int value) {
        root.setInteger(name, value);
    }

    @Override
    public void writeLong(String name, long value) {
        root.setLong(name, value);
    }

    @Override
    public void writeFloat(String name, float value) {
        root.setFloat(name, value);
    }

    @Override
    public void writeDouble(String name, double value) {
        root.setDouble(name, value);
    }

    @Override
    public void writeString(String name, String value) {
        root.setString(name, value);
    }

    @Override
    public void writeBooleanArray(String name, boolean[] array) {
        NBTTagList list = new NBTTagList();
        for (boolean bool : array) {
            byte value = (byte) (bool ? 1 : 0);
            list.appendTag(new NBTTagByte(value));
        }
        root.setTag(name, list);
    }

    @Override
    public void writeByteArray(String name, byte[] array) {
        NBTTagList list = new NBTTagList();
        for (byte value : array) {
            list.appendTag(new NBTTagByte(value));
        }
        root.setTag(name, list);
    }

    @Override
    public void writeShortArray(String name, short[] array) {
        NBTTagList list = new NBTTagList();
        for (short value : array) {
            list.appendTag(new NBTTagShort(value));
        }
        root.setTag(name, list);
    }

    @Override
    public void writeIntArray(String name, int[] array) {
        NBTTagList list = new NBTTagList();
        for (int value : array) {
            list.appendTag(new NBTTagInt(value));
        }
        root.setTag(name, list);
    }

    @Override
    public void writeLongArray(String name, long[] array) {
        NBTTagList list = new NBTTagList();
        for (long value : array) {
            list.appendTag(new NBTTagLong(value));
        }
        root.setTag(name, list);
    }

    @Override
    public void writeFloatArray(String name, float[] array) {
        NBTTagList list = new NBTTagList();
        for (float value : array) {
            list.appendTag(new NBTTagFloat(value));
        }
        root.setTag(name, list);
    }

    @Override
    public void writeDoubleArray(String name, double[] array) {
        NBTTagList list = new NBTTagList();
        for (double value : array) {
            list.appendTag(new NBTTagDouble(value));
        }
        root.setTag(name, list);
    }

    @Override
    public void writeStringArray(String name, String[] array) {
        NBTTagList list = new NBTTagList();
        for (String value : array) {
            list.appendTag(new NBTTagString(value));
        }
        root.setTag(name, list);
    }

    @Override
    public <V> void write(String name, V object, BiConsumer<V, DataWriter<NBTTagCompound>> objectSerializer) {
        NbtSerializer serializer = new NbtSerializer();
        objectSerializer.accept(object, serializer);
        root.setTag(name, serializer.getNbt());
    }

    @Override
    public <V> void writeArray(String name, V[] array, BiConsumer<V, DataWriter<NBTTagCompound>> objectSerializer) {
        NBTTagList list = new NBTTagList();
        for (V v : array) {
            NbtSerializer serializer = new NbtSerializer();
            objectSerializer.accept(v, serializer);
            list.appendTag(serializer.getNbt());
        }
        root.setTag(name, list);
    }

    @Override
    public <V> void writeCollection(String name, Collection<V> collection, BiConsumer<V, DataWriter<NBTTagCompound>> objectSerializer) {
        NBTTagList list = new NBTTagList();
        for (V v : collection) {
            NbtSerializer serializer = new NbtSerializer();
            objectSerializer.accept(v, serializer);
            list.appendTag(serializer.getNbt());
        }
        root.setTag(name, list);
    }

    @Override
    public <K, V> void writeMap(String name, Map<K, V> map, Function<K, String> keySerializer, BiConsumer<V, DataWriter<NBTTagCompound>> valueSerializer) {
        NBTTagCompound compound = new NBTTagCompound();
        for (Map.Entry<K, V> entry : map.entrySet()) {
            String key = keySerializer.apply(entry.getKey());
            NbtSerializer serializer = new NbtSerializer();
            valueSerializer.accept(entry.getValue(), serializer);
            compound.setTag(key, serializer.getNbt());
        }
        root.setTag(name, compound);
    }

    @Override
    public boolean readBoolean(String name, @Nullable Boolean defaultValue) {
        return !root.hasKey(name, Constants.NBT.TAG_BYTE) && defaultValue != null ? defaultValue : root.getBoolean(name);
    }

    @Override
    public byte readByte(String name, @Nullable Byte defaultValue) {
        return !root.hasKey(name, Constants.NBT.TAG_BYTE) && defaultValue != null ? defaultValue : root.getByte(name);
    }

    @Override
    public short readShort(String name, @Nullable Short defaultValue) {
        return !root.hasKey(name, Constants.NBT.TAG_SHORT) && defaultValue != null ? defaultValue : root.getShort(name);
    }

    @Override
    public int readInt(String name, @Nullable Integer defaultValue) {
        return !root.hasKey(name, Constants.NBT.TAG_INT) && defaultValue != null ? defaultValue : root.getInteger(name);
    }

    @Override
    public long readLong(String name, @Nullable Long defaultValue) {
        return !root.hasKey(name, Constants.NBT.TAG_LONG) && defaultValue != null ? defaultValue : root.getLong(name);
    }

    @Override
    public float readFloat(String name, @Nullable Float defaultValue) {
        return !root.hasKey(name, Constants.NBT.TAG_FLOAT) && defaultValue != null ? defaultValue : root.getFloat(name);
    }

    @Override
    public double readDouble(String name, @Nullable Double defaultValue) {
        return !root.hasKey(name, Constants.NBT.TAG_DOUBLE) && defaultValue != null ? defaultValue : root.getDouble(name);
    }

    @Override
    public String readString(String name, @Nullable String defaultValue) {
        return !root.hasKey(name, Constants.NBT.TAG_STRING) && defaultValue != null ? defaultValue : root.getString(name);
    }

    @Override
    public boolean[] readBooleanArray(String name, @Nullable boolean[] defaultValue) {
        if (!root.hasKey(name, Constants.NBT.TAG_LIST) && defaultValue != null) {
            return defaultValue;
        }
        NBTTagList list = root.getTagList(name, Constants.NBT.TAG_BYTE);
        boolean[] array = new boolean[list.tagCount()];
        for (int i = 0; i < list.tagCount(); i++) {
            array[i] = ((NBTTagByte) list.get(i)).getByte() != 0;
        }
        return array;
    }

    @Override
    public byte[] readByteArray(String name, @Nullable byte[] defaultValue) {
        if (!root.hasKey(name, Constants.NBT.TAG_LIST) && defaultValue != null) {
            return defaultValue;
        }
        NBTTagList list = root.getTagList(name, Constants.NBT.TAG_BYTE);
        byte[] array = new byte[list.tagCount()];
        for (int i = 0; i < list.tagCount(); i++) {
            array[i] = ((NBTTagByte) list.get(i)).getByte();
        }
        return array;
    }

    @Override
    public short[] readShortArray(String name, @Nullable short[] defaultValue) {
        if (!root.hasKey(name, Constants.NBT.TAG_LIST) && defaultValue != null) {
            return defaultValue;
        }
        NBTTagList list = root.getTagList(name, Constants.NBT.TAG_SHORT);
        short[] array = new short[list.tagCount()];
        for (int i = 0; i < list.tagCount(); i++) {
            array[i] = ((NBTTagShort) list.get(i)).getShort();
        }
        return array;
    }

    @Override
    public int[] readIntArray(String name, @Nullable int[] defaultValue) {
        if (!root.hasKey(name, Constants.NBT.TAG_LIST) && defaultValue != null) {
            return defaultValue;
        }
        NBTTagList list = root.getTagList(name, Constants.NBT.TAG_INT);
        int[] array = new int[list.tagCount()];
        for (int i = 0; i < list.tagCount(); i++) {
            array[i] = ((NBTTagInt) list.get(i)).getInt();
        }
        return array;
    }

    @Override
    public long[] readLongArray(String name, @Nullable long[] defaultValue) {
        if (!root.hasKey(name, Constants.NBT.TAG_LIST) && defaultValue != null) {
            return defaultValue;
        }
        NBTTagList list = root.getTagList(name, Constants.NBT.TAG_LONG);
        long[] array = new long[list.tagCount()];
        for (int i = 0; i < list.tagCount(); i++) {
            array[i] = ((NBTTagLong) list.get(i)).getLong();
        }
        return array;
    }

    @Override
    public float[] readFloatArray(String name, @Nullable float[] defaultValue) {
        if (!root.hasKey(name, Constants.NBT.TAG_LIST) && defaultValue != null) {
            return defaultValue;
        }
        NBTTagList list = root.getTagList(name, Constants.NBT.TAG_FLOAT);
        float[] array = new float[list.tagCount()];
        for (int i = 0; i < list.tagCount(); i++) {
            array[i] = ((NBTTagFloat) list.get(i)).getFloat();
        }
        return array;
    }

    @Override
    public double[] readDoubleArray(String name, @Nullable double[] defaultValue) {
        if (!root.hasKey(name, Constants.NBT.TAG_LIST) && defaultValue != null) {
            return defaultValue;
        }
        NBTTagList list = root.getTagList(name, Constants.NBT.TAG_DOUBLE);
        double[] array = new double[list.tagCount()];
        for (int i = 0; i < list.tagCount(); i++) {
            array[i] = ((NBTTagDouble) list.get(i)).getDouble();
        }
        return array;
    }

    @Override
    public String[] readStringArray(String name, @Nullable String[] defaultValue) {
        if (!root.hasKey(name, Constants.NBT.TAG_LIST) && defaultValue != null) {
            return defaultValue;
        }
        NBTTagList list = root.getTagList(name, Constants.NBT.TAG_STRING);
        String[] array = new String[list.tagCount()];
        for (int i = 0; i < list.tagCount(); i++) {
            array[i] = ((NBTTagString) list.get(i)).getString();
        }
        return array;
    }

    @Override
    public <V> V read(String name, Function<DataReader<NBTTagCompound>, V> parser, @Nullable V defaultValue) {
        if (!root.hasKey(name, Constants.NBT.TAG_COMPOUND) && defaultValue != null) {
            return defaultValue;
        }
        NBTTagCompound compound = root.getCompoundTag(name);
        NbtSerializer serializer = new NbtSerializer(compound);
        return parser.apply(serializer);
    }

    @Override
    public <V> V[] readArray(String name, Function<Integer, V[]> arrayFactory, Function<DataReader<NBTTagCompound>, V> parser, @Nullable V[] defaultValue) {
        if (!root.hasKey(name, Constants.NBT.TAG_LIST) && defaultValue != null) {
            return defaultValue;
        }
        NBTTagList list = root.getTagList(name, Constants.NBT.TAG_COMPOUND);
        V[] array = arrayFactory.apply(list.tagCount());
        for (int i = 0; i < list.tagCount(); i++) {
            NBTTagCompound compound = list.getCompoundTagAt(i);
            NbtSerializer serializer = new NbtSerializer(compound);
            array[i] = parser.apply(serializer);
        }
        return array;
    }

    @Override
    public <V, C extends Collection<V>> C readCollection(String name, Function<Integer, C> collectionConstructor, Function<DataReader<NBTTagCompound>, V> parser, @Nullable C defaultValue) {
        if (!root.hasKey(name, Constants.NBT.TAG_LIST) && defaultValue != null) {
            return defaultValue;
        }
        NBTTagList list = root.getTagList(name, Constants.NBT.TAG_COMPOUND);
        int length = list.tagCount();
        C collection = collectionConstructor.apply(length);
        for (int i = 0; i < length; i++) {
            NBTTagCompound compound = list.getCompoundTagAt(i);
            NbtSerializer serializer = new NbtSerializer(compound);
            collection.add(parser.apply(serializer));
        }
        return collection;
    }

    @Override
    public <K, V, M extends Map<K, V>> M readMap(String name, Function<Integer, M> mapConstructor, Function<String, K> keyParser, Function<DataReader<NBTTagCompound>, V> valueParser, @Nullable M defaultValue) {
        if (!root.hasKey(name, Constants.NBT.TAG_COMPOUND) && defaultValue != null) {
            return defaultValue;
        }
        NBTTagCompound compound = root.getCompoundTag(name);
        Set<String> keySet = compound.getKeySet();
        M map = mapConstructor.apply(keySet.size());
        for (String keyString : keySet) {
            K key = keyParser.apply(keyString);
            NBTTagCompound nbt = compound.getCompoundTag(keyString);
            NbtSerializer serializer = new NbtSerializer(nbt);
            V value = valueParser.apply(serializer);
            map.put(key, value);
        }
        return map;
    }

    public NBTTagCompound getNbt() {
        return root;
    }
}

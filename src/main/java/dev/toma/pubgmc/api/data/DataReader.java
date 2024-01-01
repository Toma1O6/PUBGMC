package dev.toma.pubgmc.api.data;

import net.minecraft.util.math.MathHelper;

import javax.annotation.Nullable;
import java.util.Collection;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;

public interface DataReader<T> {

    default boolean readBoolean(String name) {
        return readBoolean(name, null);
    }

    boolean readBoolean(String name, @Nullable Boolean defaultValue);

    default byte readByte(String name) {
        return readByte(name, null);
    }

    byte readByte(String name, @Nullable Byte defaultValue);

    default short readShort(String name) {
        return readShort(name, null);
    }

    short readShort(String name, @Nullable Short defaultValue);

    default int readInt(String name) {
        return readInt(name, null);
    }

    int readInt(String name, @Nullable Integer defaultValue);

    default long readLong(String name) {
        return readLong(name, null);
    }

    long readLong(String name, @Nullable Long defaultValue);

    default float readFloat(String name) {
        return readFloat(name, null);
    }

    float readFloat(String name, @Nullable Float defaultValue);

    default double readDouble(String name) {
        return readDouble(name, null);
    }

    double readDouble(String name, @Nullable Double defaultValue);

    default String readString(String name) {
        return readString(name, null);
    }

    String readString(String name, @Nullable String defaultValue);


    default boolean[] readBooleanArray(String name) {
        return readBooleanArray(name, null);
    }

    boolean[] readBooleanArray(String name, @Nullable boolean[] defaultValue);

    default byte[] readByteArray(String name) {
        return readByteArray(name, null);
    }

    byte[] readByteArray(String name, @Nullable byte[] defaultValue);

    default short[] readShortArray(String name) {
        return readShortArray(name, null);
    }

    short[] readShortArray(String name, @Nullable short[] defaultValue);

    default int[] readIntArray(String name) {
        return readIntArray(name, null);
    }

    int[] readIntArray(String name, @Nullable int[] defaultValue);

    default long[] readLongArray(String name) {
        return readLongArray(name, null);
    }

    long[] readLongArray(String name, @Nullable long[] defaultValue);

    default float[] readFloatArray(String name) {
        return readFloatArray(name, null);
    }

    float[] readFloatArray(String name, @Nullable float[] defaultValue);

    default double[] readDoubleArray(String name) {
        return readDoubleArray(name, null);
    }

    double[] readDoubleArray(String name, @Nullable double[] defaultValue);

    default String[] readStringArray(String name) {
        return readStringArray(name, null);
    }

    String[] readStringArray(String name, @Nullable String[] defaultValue);

    default <V> V read(String name, Function<DataReader<T>, V> parser) {
        return read(name, parser, null);
    }

    <V> V read(String name, Function<DataReader<T>, V> parser, @Nullable V defaultValue);

    default <V> V[] readArray(String name, Function<Integer, V[]> arrayFactory, Function<DataReader<T>, V> parser) {
        return readArray(name, arrayFactory, parser, null);
    }

    <V> V[] readArray(String name, Function<Integer, V[]> arrayFactory, Function<DataReader<T>, V> parser, @Nullable V[] defaultValue);

    default <E extends Enum<E>> E readEnum(String name, Class<E> enumType) {
        return readEnum(name, enumType, null);
    }

    default <E extends Enum<E>> E readEnum(String name, Class<E> enumType, @Nullable E defaultValue) {
        try {
            E[] constants = enumType.getEnumConstants();
            int value = readInt(name);
            return constants[MathHelper.clamp(value, 0, constants.length - 1)];
        } catch (Exception e) {
            if (defaultValue != null) {
                return defaultValue;
            }
            throw e;
        }
    }

    default UUID readUuid(String name) {
        return readUuid(name, null);
    }

    default UUID readUuid(String name, @Nullable UUID defaultValue) {
        return defaultValue != null ? UUID.fromString(readString(name, defaultValue.toString())) : UUID.fromString(readString(name));
    }

    default <V, C extends Collection<V>> C readCollection(String name, Function<Integer, C> collectionConstructor, Function<DataReader<T>, V> parser) {
        return readCollection(name, collectionConstructor, parser, null);
    }

    <V, C extends Collection<V>> C readCollection(String name, Function<Integer, C> collectionConstructor, Function<DataReader<T>, V> parser, @Nullable C defaultValue);

    default <K, V, M extends Map<K, V>> M readMap(String name, Function<Integer, M> mapConstructor, Function<String, K> keyParser, Function<DataReader<T>, V> valueParser) {
        return readMap(name, mapConstructor, keyParser, valueParser, null);
    }

    <K, V, M extends Map<K, V>> M readMap(String name, Function<Integer, M> mapConstructor, Function<String, K> keyParser, Function<DataReader<T>, V> valueParser, @Nullable M defaultValue);
}

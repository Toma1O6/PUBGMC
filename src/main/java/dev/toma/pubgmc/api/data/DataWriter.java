package dev.toma.pubgmc.api.data;

import java.util.Collection;
import java.util.Map;
import java.util.UUID;
import java.util.function.BiConsumer;
import java.util.function.Function;

public interface DataWriter<T> {

    void writeBoolean(String name, boolean value);

    void writeByte(String name, byte value);

    void writeShort(String name, short value);

    void writeInt(String name, int value);

    void writeLong(String name, long value);

    void writeFloat(String name, float value);

    void writeDouble(String name, double value);

    void writeString(String name, String value);

    void writeBooleanArray(String name, boolean[] array);

    void writeByteArray(String name, byte[] array);

    void writeShortArray(String name, short[] array);

    void writeIntArray(String name, int[] array);

    void writeLongArray(String name, long[] array);

    void writeFloatArray(String name, float[] array);

    void writeDoubleArray(String name, double[] array);

    void writeStringArray(String name, String[] array);

    <V> void write(String name, V object, BiConsumer<V, DataWriter<T>> objectSerializer);

    <V> void writeArray(String name, V[] array, BiConsumer<V, DataWriter<T>> objectSerializer);

    default <E extends Enum<E>> void writeEnum(String name, E enumVal) {
        writeInt(name, enumVal.ordinal());
    }

    default void writeUuid(String name, UUID value) {
        writeString(name, value.toString());
    }

    <V> void writeCollection(String name, Collection<V> collection, BiConsumer<V, DataWriter<T>> objectSerializer);

    <K, V> void writeMap(String name, Map<K, V> map, Function<K, String> keySerializer, BiConsumer<V, DataWriter<T>> valueSerializer);
}

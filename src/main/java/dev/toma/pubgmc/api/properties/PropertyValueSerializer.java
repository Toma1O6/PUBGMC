package dev.toma.pubgmc.api.properties;

import dev.toma.pubgmc.api.game.util.PlayerPropertyHolder;
import net.minecraft.nbt.NBTTagCompound;
import org.apache.logging.log4j.util.TriConsumer;

import java.util.function.BiFunction;

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

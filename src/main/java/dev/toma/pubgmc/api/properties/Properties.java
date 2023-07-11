package dev.toma.pubgmc.api.properties;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;

import java.util.HashMap;
import java.util.Map;

public final class Properties {

    private static final Map<ResourceLocation, PropertyType<?>> REGISTERED_PROPERTIES = new HashMap<>();

    public static void registerProperty(PropertyType<?> type) {
        REGISTERED_PROPERTIES.put(type.getIdentifier(), type);
    }

    @SuppressWarnings("unchecked")
    public static <T> PropertyType<T> getProperty(ResourceLocation identifier) {
        return (PropertyType<T>) REGISTERED_PROPERTIES.get(identifier);
    }

    public static PropertyType<Boolean> booleanProperty(ResourceLocation identifier) {
        return new PropertyType<>(identifier, PropertyValueSerializer.primitive(
                NBTTagCompound::setBoolean, NBTTagCompound::getBoolean
        ));
    }

    public static PropertyType<Integer> intProperty(ResourceLocation identifier) {
        return new PropertyType<>(identifier, PropertyValueSerializer.primitive(
                NBTTagCompound::setInteger, NBTTagCompound::getInteger
        ));
    }

    public static PropertyType<Long> longProperty(ResourceLocation identifier) {
        return new PropertyType<>(identifier, PropertyValueSerializer.primitive(
                NBTTagCompound::setLong, NBTTagCompound::getLong
        ));
    }

    public static PropertyType<Float> floatProperty(ResourceLocation identifier) {
        return new PropertyType<>(identifier, PropertyValueSerializer.primitive(
                NBTTagCompound::setFloat, NBTTagCompound::getFloat
        ));
    }

    public static PropertyType<Double> doubleProperty(ResourceLocation identifier) {
        return new PropertyType<>(identifier, PropertyValueSerializer.primitive(
                NBTTagCompound::setDouble, NBTTagCompound::getDouble
        ));
    }

    public static PropertyType<String> stringProperty(ResourceLocation identifier) {
        return new PropertyType<>(identifier, PropertyValueSerializer.primitive(
                NBTTagCompound::setString, NBTTagCompound::getString
        ));
    }

    private Properties() {}
}

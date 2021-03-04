package dev.toma.pubgmc.config.type;

import com.google.common.base.Preconditions;
import dev.toma.configuration.api.ConfigCreator;
import dev.toma.configuration.api.ConfigPlugin;
import dev.toma.configuration.api.type.*;
import dev.toma.configuration.api.util.Nameable;
import dev.toma.configuration.api.util.Restriction;
import net.minecraft.util.math.MathHelper;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.regex.Pattern;

public class PMCConfigCreator implements ConfigCreator {

    private ObjectType config;

    @Override
    public void assignTo(ObjectType type) {
        this.config = type;
    }

    @Override
    public BooleanType createBoolean(String name, boolean value, String... desc) {
        BooleanType type = new BooleanType(name, value, desc);
        config.get().put(name, type);
        return type;
    }

    @Override
    public IntType createInt(String name, int value, String... desc) {
        return createInt(name, value, Integer.MIN_VALUE, Integer.MAX_VALUE, desc);
    }

    @Override
    public IntType createInt(String name, int value, int min, int max, String... desc) throws IllegalArgumentException {
        if(max < min)
            throw new IllegalArgumentException("Max value cannot be smaller than Min value");
        IntType type = new IntType(name, value, min, max, desc);
        config.get().put(name, type);
        return type;
    }

    @Override
    public DoubleType createDouble(String name, double value, String... desc) {
        return createDouble(name, value, -Double.MAX_VALUE, Double.MAX_VALUE, desc);
    }

    @Override
    public DoubleType createDouble(String name, double value, double min, double max, String... desc) throws IllegalArgumentException {
        if(max < min)
            throw new IllegalArgumentException("Max value cannot be smaller than Min value");
        DoubleType type = new DoubleType(name, value, min, max, desc);
        config.get().put(name, type);
        return type;
    }

    @Override
    public StringType createString(String name, String value, String... desc) {
        return createString(name, value, null, desc);
    }

    @Override
    public StringType createString(String name, String value, @Nullable Restriction restriction, String... desc) {
        if(restriction != null) {
            Preconditions.checkState(restriction.isStringValid(value), "Invalid default value");
        }
        StringType type = new StringType(name, value, restriction, desc);
        config.get().put(name, type);
        return type;
    }

    @Override
    public ColorType createColorRGB(String name, String colorRgb, String... desc) {
        return createColor(name, colorRgb, Pattern.compile("#[0-9a-fA-F]{1,6}"), desc);
    }

    @Override
    public ColorType createColorARGB(String name, String colorArgb, String... desc) {
        return createColor(name, colorArgb, Pattern.compile("#[0-9a-fA-F]{1,8}"), desc);
    }

    @Override
    public ColorType createColor(String name, String color, Pattern pattern, String... desc) {
        Preconditions.checkNotNull(pattern, "Pattern cannot be null");
        Preconditions.checkState(pattern.matcher(color).matches(), "Invalid default color definition");
        ColorType type = new ColorType(name, color, pattern, desc);
        config.get().put(name, type);
        return type;
    }

    @Override
    public <T extends Enum<T> & Nameable> EnumType<T> createEnum(String name, T value, String... desc) {
        EnumType<T> type = new EnumType<>(name, value, desc);
        config.get().put(name, type);
        return type;
    }

    @Override
    public <T extends Nameable> FixedCollectionType<T> createArray(String name, int initialValueIndex, T[] values, String... desc) {
        return createArray(name, values[MathHelper.clamp(initialValueIndex, 0, values.length - 1)], values, desc);
    }

    @Override
    public <T extends Nameable> FixedCollectionType<T> createArray(String name, T value, T[] values, String... desc) {
        FixedCollectionType<T> type = new FixedCollectionType<>(name, value, values, desc);
        config.get().put(name, type);
        return type;
    }

    @Override
    public <T extends AbstractConfigType<?>> CollectionType<T> createList(String name, Supplier<T> factory, String... desc) {
        return createList(name, new ArrayList<>(), factory, desc);
    }

    @Override
    public <T extends AbstractConfigType<?>> CollectionType<T> createList(String name, List<T> entry, Supplier<T> factory, String... desc) {
        CollectionType<T> collectionType = new CollectionType<>(name, entry, factory, desc);
        config.get().put(name, collectionType);
        return collectionType;
    }

    @Override
    public <T extends AbstractConfigType<?>> CollectionType<T> createFillList(String name, Supplier<T> factory, Consumer<CollectionType<T>> consumer, String... desc) {
        CollectionType<T> collectionType = new CollectionType<>(name, factory, desc);
        consumer.accept(collectionType);
        config.get().put(name, collectionType);
        return collectionType;
    }

    @Override
    public <T extends ObjectType> T createObject(T object, ConfigPlugin plugin) {
        ConfigCreator creator = plugin.builder(object);
        object.buildStructure(creator);
        config.get().put(object.getId(), object);
        return object;
    }

    public TextureType createTextureArray(String name, int value, TextureType.Entry[] values, Supplier<Integer> supplier, String... lines) {
        TextureType textureType = new TextureType(name, values[value], values, supplier, lines);
        config.get().put(textureType.getId(), textureType);
        return textureType;
    }
}

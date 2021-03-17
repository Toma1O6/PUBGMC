package dev.toma.pubgmc.config.type;

import dev.toma.configuration.api.DefaultConfigCreatorImpl;

import java.util.function.Supplier;

public class PMCConfigCreator extends DefaultConfigCreatorImpl {

    public TextureType createTextureArray(String name, int value, TextureType.Entry[] values, Supplier<Integer> supplier, String... lines) {
        TextureType textureType = new TextureType(name, values[value], values, supplier, lines);
        config.get().put(textureType.getId(), textureType);
        return textureType;
    }
}

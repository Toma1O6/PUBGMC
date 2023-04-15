package dev.toma.pubgmc.api.game.map;

import dev.toma.pubgmc.api.util.RegistryObject;
import net.minecraft.util.ResourceLocation;

import java.util.Objects;

public final class GameMapPointType<P extends GameMapPoint> extends RegistryObject {

    private final GameMapPointSerializer<P> serializer;

    private GameMapPointType(ResourceLocation identifier, GameMapPointSerializer<P> serializer) {
        super(identifier);
        this.serializer = serializer;
    }

    public static <P extends GameMapPoint> GameMapPointType<P> create(ResourceLocation identifier, GameMapPointSerializer<P> serializer) {
        return new GameMapPointType<>(identifier, Objects.requireNonNull(serializer));
    }
}

package dev.toma.pubgmc.api.game;

import dev.toma.pubgmc.api.PubgmcRegistries;
import dev.toma.pubgmc.api.util.RegistryObject;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;
import java.util.Objects;
import java.util.UUID;
import java.util.function.Supplier;

public final class GameType<CFG extends GameConfiguration, G extends Game<CFG>> extends RegistryObject {

    private final GameConstructor<CFG, G> constructor;
    private final GameDataSerializer<CFG, G> serializer;
    @Nullable
    private final Supplier<CFG> defaultConfigurationProvider;

    private GameType(ResourceLocation registryId, GameConstructor<CFG, G> constructor, GameDataSerializer<CFG, G> serializer, @Nullable Supplier<CFG> defaultConfigurationProvider) {
        super(registryId);
        this.constructor = constructor;
        this.serializer = serializer;
        this.defaultConfigurationProvider = defaultConfigurationProvider;
    }

    public static <CFG extends GameConfiguration, G extends Game<CFG>> GameType<CFG, G> create(ResourceLocation identifier, GameConstructor<CFG, G> constructor, GameDataSerializer<CFG, G> serializer) {
        return create(identifier, constructor, serializer, null);
    }

    @SuppressWarnings("unchecked")
    public static <CFG extends GameConfiguration, G extends Game<CFG>> GameType<CFG, G> create(ResourceLocation identifier, GameConstructor<CFG, G> constructor, GameDataSerializer<CFG, G> serializer, @Nullable Supplier<CFG> configurationProvider) {
        return new GameType<>(identifier, constructor, Objects.requireNonNull(serializer), configurationProvider);
    }

    public GameConstructor<CFG, G> getConstructor() {
        return constructor;
    }

    @Nullable
    public GameConfiguration getGameConfiguration() {
        return defaultConfigurationProvider != null ? defaultConfigurationProvider.get() : null;
    }

    @SuppressWarnings("unchecked")
    public static <CFG extends GameConfiguration, G extends Game<CFG>> NBTTagCompound serialize(G game) {
        NBTTagCompound nbt = new NBTTagCompound();
        GameType<CFG, G> gameType = (GameType<CFG, G>) game.getGameType();
        nbt.setString("type", gameType.getIdentifier().toString());
        nbt.setTag("game", gameType.serializer.serializeGameData(game));
        return nbt;
    }

    @Nullable
    public static <CFG extends GameConfiguration, G extends Game<CFG>> G deserialize(NBTTagCompound nbt) {
        ResourceLocation location = new ResourceLocation(nbt.getString("type"));
        GameType<CFG, G> gameType = PubgmcRegistries.GAME_TYPES.getUnsafeGenericValue(location);
        if (gameType == null) {
            return null;
        }
        return gameType.serializer.deserializeGameData(nbt.getCompoundTag("game"));
    }

    public static <CFG extends GameConfiguration> NBTTagCompound serializeConfiguration(GameType<CFG, ?> gameType, CFG config) {
        return gameType.serializer.serializeGameConfiguration(config);
    }

    public static <CFG extends GameConfiguration> CFG deserializeConfiguration(GameType<CFG, ?> gameType, NBTTagCompound nbt) {
        return gameType.serializer.deserializeGameConfiguration(nbt);
    }

    @FunctionalInterface
    public interface GameConstructor<CFG extends GameConfiguration, G extends Game<CFG>> {
        G constructGameInstance(UUID gameId, CFG config) throws GameException;
    }
}

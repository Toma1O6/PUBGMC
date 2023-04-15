package dev.toma.pubgmc.api.game;

import dev.toma.pubgmc.api.PubgmcRegistries;
import dev.toma.pubgmc.api.util.RegistryObject;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.WorldServer;

import javax.annotation.Nullable;
import java.util.Objects;
import java.util.function.Function;

public final class GameType<G extends Game<?>> extends RegistryObject {

    private final GameDataSerializer<G> serializer;
    @Nullable
    private final Function<WorldServer, GameConfiguration> defaultConfigurationProvider;

    private GameType(ResourceLocation registryId, GameDataSerializer<G> serializer, @Nullable Function<WorldServer, GameConfiguration> defaultConfigurationProvider) {
        super(registryId);
        this.serializer = serializer;
        this.defaultConfigurationProvider = defaultConfigurationProvider;
    }

    public static <CFG extends GameConfiguration, G extends Game<CFG>> GameType<G> create(ResourceLocation identifier, GameDataSerializer<G> serializer) {
        return create(identifier, serializer, null);
    }

    @SuppressWarnings("unchecked")
    public static <CFG extends GameConfiguration, G extends Game<CFG>> GameType<G> create(ResourceLocation identifier, GameDataSerializer<G> serializer, @Nullable Function<WorldServer, CFG> configurationProvider) {
        return new GameType<>(identifier, Objects.requireNonNull(serializer), (Function<WorldServer, GameConfiguration>) configurationProvider);
    }

    @Nullable
    public GameConfiguration getGameConfiguration(WorldServer worldServer) {
        return defaultConfigurationProvider != null ? defaultConfigurationProvider.apply(worldServer) : null;
    }

    @SuppressWarnings("unchecked")
    public static <G extends Game<?>> NBTTagCompound serialize(G game) {
        NBTTagCompound nbt = new NBTTagCompound();
        GameType<G> gameType = (GameType<G>) game.getGameType();
        nbt.setString("type", gameType.getIdentifier().toString());
        nbt.setTag("game", gameType.serializer.serializeGameData(game));
        return nbt;
    }

    @Nullable
    public static <G extends Game<?>> G deserialize(NBTTagCompound nbt) {
        ResourceLocation location = new ResourceLocation(nbt.getString("type"));
        GameType<G> gameType = PubgmcRegistries.GAME_TYPES.getUnsafeGenericValue(location);
        if (gameType == null) {
            return null;
        }
        return gameType.serializer.deserializeGameData(nbt.getCompoundTag("game"));
    }
}

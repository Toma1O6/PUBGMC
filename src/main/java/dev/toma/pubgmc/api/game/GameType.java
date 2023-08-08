package dev.toma.pubgmc.api.game;

import com.google.gson.JsonObject;
import dev.toma.pubgmc.api.PubgmcRegistries;
import dev.toma.pubgmc.api.util.RegistryObject;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.Objects;
import java.util.UUID;
import java.util.function.Supplier;

public final class GameType<CFG extends GameConfiguration, G extends Game<CFG>> extends RegistryObject {

    private final GameConstructor<CFG, G> constructor;
    private final GameDataSerializer<CFG, G> serializer;
    private final Supplier<CFG> defaultConfigurationProvider;

    private GameType(ResourceLocation registryId, GameConstructor<CFG, G> constructor, GameDataSerializer<CFG, G> serializer, Supplier<CFG> defaultConfigurationProvider) {
        super(registryId);
        this.constructor = constructor;
        this.serializer = serializer;
        this.defaultConfigurationProvider = defaultConfigurationProvider;
    }

    public static <CFG extends GameConfiguration, G extends Game<CFG>> GameType<CFG, G> create(ResourceLocation identifier, GameConstructor<CFG, G> constructor, GameDataSerializer<CFG, G> serializer, Supplier<CFG> configurationProvider) {
        return new GameType<>(identifier, constructor, Objects.requireNonNull(serializer), configurationProvider);
    }

    public GameConstructor<CFG, G> getConstructor() {
        return constructor;
    }


    public CFG getGameConfiguration() {
        return defaultConfigurationProvider.get();
    }

    @SuppressWarnings("unchecked")
    public static <CFG extends GameConfiguration, G extends Game<CFG>> NBTTagCompound serialize(G game) {
        NBTTagCompound nbt = new NBTTagCompound();
        GameType<CFG, G> gameType = (GameType<CFG, G>) game.getGameType();
        nbt.setString("type", gameType.getIdentifier().toString());
        nbt.setTag("config", serializeConfiguration(gameType, game.getConfiguration()));
        nbt.setTag("game", gameType.serializer.serializeGameData(game));
        return nbt;
    }

    @Nullable
    public static <CFG extends GameConfiguration, G extends Game<CFG>> G deserialize(NBTTagCompound nbt, World world) {
        ResourceLocation location = new ResourceLocation(nbt.getString("type"));
        GameType<CFG, G> gameType = PubgmcRegistries.GAME_TYPES.getUnsafeGenericValue(location);
        if (gameType == null) {
            return null;
        }
        CFG configuration = deserializeConfiguration(gameType, nbt.getCompoundTag("config"));
        return gameType.serializer.deserializeGameData(nbt.getCompoundTag("game"), configuration, world);
    }

    public static <CFG extends GameConfiguration> NBTTagCompound serializeConfiguration(GameType<CFG, ?> gameType, CFG config) {
        return gameType.serializer.serializeGameConfiguration(config);
    }

    public static <CFG extends GameConfiguration> CFG deserializeConfiguration(GameType<CFG, ?> gameType, NBTTagCompound nbt) {
        return gameType.serializer.deserializeGameConfiguration(nbt);
    }

    public static <CFG extends GameConfiguration> JsonObject serializeConfigurationToJson(GameType<CFG, ?> gameType, CFG config) {
        return gameType.serializer.serializeConfigurationToJson(config);
    }

    public static <CFG extends GameConfiguration> CFG deserializeConfigurationFromJson(GameType<CFG, ?> gameType, JsonObject object) {
        return gameType.serializer.deserializeConfigurationFromJson(object);
    }

    @FunctionalInterface
    public interface GameConstructor<CFG extends GameConfiguration, G extends Game<CFG>> {
        G constructGameInstance(UUID gameId, CFG config) throws GameException;
    }
}

package dev.toma.pubgmc.api.game;

import dev.toma.pubgmc.api.PubgmcRegistries;
import dev.toma.pubgmc.api.util.RegistryObject;
import dev.toma.pubgmc.common.games.NoGame;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;
import java.util.Objects;

public final class GameType<G extends Game> extends RegistryObject {

    private final GameDataSerializer<G> serializer;

    private GameType(ResourceLocation registryId, GameDataSerializer<G> serializer) {
        super(registryId);
        this.serializer = serializer;
    }

    public static <G extends Game> GameType<G> create(ResourceLocation identifier, GameDataSerializer<G> serializer) {
        return new GameType<>(identifier, Objects.requireNonNull(serializer));
    }

    @SuppressWarnings("unchecked")
    public static <G extends Game> NBTTagCompound serialize(G game) {
        NBTTagCompound nbt = new NBTTagCompound();
        GameType<G> gameType = (GameType<G>) game.getGameType();
        nbt.setString("type", gameType.getIdentifier().toString());
        nbt.setTag("game", gameType.serializer.serializeGameData(game));
        return nbt;
    }

    @Nullable
    public static <G extends Game> G deserialize(NBTTagCompound nbt) {
        ResourceLocation location = new ResourceLocation(nbt.getString("type"));
        GameType<G> gameType = PubgmcRegistries.GAME_TYPES.getUnsafeGenericValue(location);
        if (gameType == null) {
            return null;
        }
        return gameType.serializer.deserializeGameData(nbt.getCompoundTag("game"));
    }
}

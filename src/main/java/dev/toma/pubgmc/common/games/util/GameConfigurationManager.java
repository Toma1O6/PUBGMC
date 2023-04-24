package dev.toma.pubgmc.common.games.util;

import com.google.gson.*;
import dev.toma.pubgmc.api.PubgmcRegistries;
import dev.toma.pubgmc.api.game.GameConfiguration;
import dev.toma.pubgmc.api.game.GameType;
import dev.toma.pubgmc.common.games.GameTypes;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.IdentityHashMap;
import java.util.Map;

public final class GameConfigurationManager {

    private static final File DIRECTORY = new File("./pubgmc/game_configs");
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final Map<GameType<?, ?>, GameConfiguration> CONFIGURATION_MAP = new IdentityHashMap<>();

    public static void loadConfigurations() {
        try {
            DIRECTORY.mkdirs();
            Collection<GameType<?, ?>> types = PubgmcRegistries.GAME_TYPES.getValues();
            for (GameType<?, ?> type : types) {
                if (type == GameTypes.NO_GAME)
                    continue;
                loadGameConfiguration(type);
            }
        } catch (IOException e) {
            // TODO log error
        }
    }

    @SuppressWarnings("unchecked")
    public static <CFG extends GameConfiguration> CFG getConfiguration(GameType<CFG, ?> gameType) {
        return (CFG) CONFIGURATION_MAP.getOrDefault(gameType, gameType.getGameConfiguration());
    }

    private static <CFG extends GameConfiguration> void loadGameConfiguration(GameType<CFG, ?> type) throws IOException {
        File file = new File(DIRECTORY, type.getIdentifier().toString().replaceAll(":", "_") + ".json");
        if (!file.exists()) {
            createDefaultGameConfiguration(type, file);
            return;
        }
        try (FileReader reader = new FileReader(file)) {
            JsonElement element = new JsonParser().parse(reader);
            CFG config = GameType.deserializeConfigurationFromJson(type, element.getAsJsonObject());
            CONFIGURATION_MAP.put(type, config);
        } catch (JsonParseException e) {
            createDefaultGameConfiguration(type, file);
        }
    }

    private static <CFG extends GameConfiguration> void createDefaultGameConfiguration(GameType<CFG, ?> type, File file) throws IOException {
        file.createNewFile();
        CFG configuration = type.getGameConfiguration();
        JsonObject object = GameType.serializeConfigurationToJson(type, configuration);
        try (FileWriter writer = new FileWriter(file)) {
            writer.write(GSON.toJson(object));
        }
    }
}

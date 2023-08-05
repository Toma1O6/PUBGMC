package dev.toma.pubgmc.data.entity;

import com.google.gson.*;
import dev.toma.pubgmc.Pubgmc;
import dev.toma.pubgmc.api.data.DataVersion;
import dev.toma.pubgmc.api.data.Recreatable;
import dev.toma.pubgmc.api.entity.EntityProvider;
import dev.toma.pubgmc.api.entity.EntityProviderType;
import dev.toma.pubgmc.util.PUBGMCUtil;

import javax.annotation.Nullable;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("ResultOfMethodCallIgnored")
public final class EntityProviderManager implements Recreatable {

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final File DIRECTORY = new File("./pubgmc/entity_generator");
    public static final EntityProviderManager INSTANCE = new EntityProviderManager();

    private final Map<String, EntityProvider> providers = new HashMap<>();
    private final Map<String, EntityProvider> defaultConfigurations = new HashMap<>();

    private EntityProviderManager() {}

    public static void load() {
        try {
            INSTANCE.loadData();
        } catch (IOException e) {
            Pubgmc.logger.fatal("Entity provider initialization failed!");
            throw new RuntimeException("Entity provider load failed", e);
        }
    }

    public void registerDefaultConfiguration(String path, EntityProvider provider) {
        defaultConfigurations.put(path, provider);
    }

    @Nullable
    public EntityProvider getEntityProviderById(String path) {
        return providers.get(path);
    }

    @Override
    public void recreateData() {
        try {
            createDefaultFiles(true);
            load();
        } catch (IOException e) {
            throw new IllegalStateException("Data recreate failed", e);
        }
    }

    @Override
    public DataVersion getVersion() {
        return Pubgmc.ENTITY_PROVIDER_VERSION;
    }

    private void loadData() throws IOException {
        PUBGMCUtil.createDataDirectory(DIRECTORY);
        providers.clear();
        File[] files = DIRECTORY.listFiles();
        for (File file : files) {
            if (!file.isDirectory()) {
                loadFile(file);
            }
        }
        createDefaultFiles(false);
    }

    private void createDefaultFiles(boolean forceRewrite) throws IOException {
        for (Map.Entry<String, EntityProvider> entry : defaultConfigurations.entrySet()) {
            if (!providers.containsKey(entry.getKey()) || forceRewrite) {
                EntityProvider provider = entry.getValue();
                JsonObject json = EntityProviderType.serialize(provider);
                String jsonRaw = GSON.toJson(json);
                File file = new File(DIRECTORY, entry.getKey() + ".json");
                file.createNewFile();
                try (FileWriter writer = new FileWriter(file)) {
                    writer.write(jsonRaw);
                }
                providers.put(entry.getKey(), provider);
            }
        }
    }

    private void loadFile(File file) throws IOException {
        if (file.getName().endsWith(".json")) {
            try (FileReader reader = new FileReader(file)) {
                JsonParser parser = new JsonParser();
                JsonElement json = parser.parse(reader);
                EntityProvider provider = EntityProviderType.parse(json.getAsJsonObject());
                String path = file.getName().replaceAll("\\.json$", "");
                providers.put(path, provider);
            } catch (JsonParseException e) {
                Pubgmc.logger.error("Error loading entity provider file {} due to error {}", file.getAbsolutePath(), e.getMessage());
            }
        }
    }
}

package dev.toma.pubgmc.api.data;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import dev.toma.pubgmc.Pubgmc;
import dev.toma.pubgmc.api.data.DataVersion.CompareResult;
import dev.toma.pubgmc.util.PUBGMCUtil;
import net.minecraft.util.ResourceLocation;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public final class DataVersionManager {

    private static final Map<ResourceLocation, Recreatable> RECREATABLES = new HashMap<>();
    private static final File FILE = new File("./pubgmc/data_versions");
    private static final Gson GSON = new Gson();
    private static LoadResult lastLoadingResult;

    public static void register(ResourceLocation identifier, Recreatable recreatable) {
        RECREATABLES.put(identifier, recreatable);
    }

    public static void load() {
        LoadResult result = new LoadResult();
        PUBGMCUtil.createDataDirectory(FILE.getParentFile());
        try {
            if (!FILE.exists()) {
                createDefaultVersionsFile();
            }
            load(result);
            lastLoadingResult = result;
            addNewEntries();
            writeFile();
        } catch (IOException e) {
            Pubgmc.logger.error("Data version file loading failed", e);
        }
    }

    public static Map<ResourceLocation, DataVersion.CompareResult> getListOfMismatchedData() {
        if (lastLoadingResult == null)
            return Collections.emptyMap();
        return new HashMap<>(lastLoadingResult.mismatched);
    }

    public static void markCorrected(Collection<ResourceLocation> collection) {
        ResourceLocation[] locations = collection.toArray(new ResourceLocation[0]);
        if (lastLoadingResult != null) {
            lastLoadingResult.markFixed(locations);
        }
        try {
            writeFile();
        } catch (IOException e) {
            throw new IllegalStateException("Data file save failed", e);
        }
    }

    private static void addNewEntries() {
        Set<ResourceLocation> loaded = lastLoadingResult.keys();
        Map<ResourceLocation, DataVersion> missing = new HashMap<>();
        for (Map.Entry<ResourceLocation, Recreatable> entry : RECREATABLES.entrySet()) {
            ResourceLocation location = entry.getKey();
            if (!loaded.contains(location)) {
                missing.put(location, entry.getValue().getVersion());
            }
        }
        loaded.removeIf(loadedLoc -> !RECREATABLES.containsKey(loadedLoc));
        missing.forEach((id, ver) -> lastLoadingResult.addResult(id, ver, ver));
    }

    private static void writeFile() throws IOException {
        if (lastLoadingResult == null)
            return;
        FILE.createNewFile(); // just in case the file gets deleted by some user
        try (FileWriter writer = new FileWriter(FILE)) {
            JsonObject object = new JsonObject();
            for (Map.Entry<ResourceLocation, LoadResult.Entry> entry : lastLoadingResult.entrySet()) {
                object.addProperty(entry.getKey().toString(), entry.getValue().getVersion().toString());
            }
            String content = GSON.toJson(object);
            writer.write(content);
        }
    }

    private static void load(LoadResult container) throws IOException {
        try (FileReader reader = new FileReader(FILE)) {
            JsonParser parser = new JsonParser();
            JsonElement element = parser.parse(reader);
            JsonObject object = element.getAsJsonObject();
            for (Map.Entry<String, JsonElement> entry : object.entrySet()) {
                ResourceLocation identifier = new ResourceLocation(entry.getKey());
                Recreatable recreatable = RECREATABLES.get(identifier);
                if (recreatable == null)
                    continue;
                DataVersion targetVersion = recreatable.getVersion();
                DataVersion version = DataVersion.parse(entry.getValue().getAsString());
                container.addResult(identifier, version, targetVersion);
            }
        }
    }

    private static void createDefaultVersionsFile() throws IOException {
        JsonObject object = new JsonObject();
        for (Map.Entry<ResourceLocation, Recreatable> entry : RECREATABLES.entrySet()) {
            object.addProperty(entry.getKey().toString(), entry.getValue().getVersion().toString());
        }
        String content = GSON.toJson(object);
        FILE.createNewFile();
        try (FileWriter writer = new FileWriter(FILE)) {
            writer.write(content);
        }
    }

    private DataVersionManager() {
    }

    public static final class LoadResult {

        private final Map<ResourceLocation, Entry> entries = new HashMap<>();
        private final Map<ResourceLocation, DataVersion.CompareResult> mismatched = new LinkedHashMap<>();

        void markFixed(ResourceLocation... locations) {
            for (ResourceLocation location : locations) {
                Recreatable recreatable = RECREATABLES.get(location);
                if (recreatable != null) {
                    recreatable.recreateData();
                }
            }
            for (ResourceLocation mismatched : mismatched.keySet()) {
                Entry old = entries.get(mismatched);
                if (old != null) {
                    DataVersion target = old.getTargetVersion();
                    entries.put(mismatched, new Entry(target, target));
                }
            }
            mismatched.clear();
        }

        void addResult(ResourceLocation identifier, DataVersion version, DataVersion target) {
            entries.put(identifier, new Entry(version, target));
            DataVersion.CompareResult result = version.compare(target);
            if (result != CompareResult.SAME) {
                mismatched.put(identifier, result);
            }
        }

        Set<Map.Entry<ResourceLocation, Entry>> entrySet() {
            return entries.entrySet();
        }

        Set<ResourceLocation> keys() {
            return entries.keySet();
        }

        public static final class Entry {

            private final DataVersion version;
            private final DataVersion targetVersion;

            public Entry(DataVersion version, DataVersion targetVersion) {
                this.version = version;
                this.targetVersion = targetVersion;
            }

            public DataVersion getVersion() {
                return version;
            }

            public DataVersion getTargetVersion() {
                return targetVersion;
            }
        }
    }
}

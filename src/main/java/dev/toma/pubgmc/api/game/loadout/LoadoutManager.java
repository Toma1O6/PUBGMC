package dev.toma.pubgmc.api.game.loadout;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import dev.toma.pubgmc.Pubgmc;
import net.minecraft.entity.EntityLivingBase;

import javax.annotation.Nullable;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public final class LoadoutManager {

    private static final Map<Class<? extends EntityLivingBase>, LoadoutApplicator<?>> ENTITY_LOADOUT_HANDLERS = new HashMap<>();
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().registerTypeAdapter(EntityLoadout.class, new EntityLoadout.Adapter()).create();

    private static final Map<String, EntityLoadout> LOADOUT_MAP = new HashMap<>();
    private static final Set<String> DYNAMIC_LOADOUT_DIR_KEYS = new HashSet<>();

    public static void register(String path, EntityLoadout loadout) {
        LOADOUT_MAP.put(path, loadout);
    }

    public static void registerLoadoutDirectory(String dir) {
        DYNAMIC_LOADOUT_DIR_KEYS.add(dir);
    }

    public static void load() {
        File directory = new File("./pubgmc/loadout");
        directory.mkdirs();
        Map<String, EntityLoadout> loadedValues = new HashMap<>();
        for (Map.Entry<String, EntityLoadout> entry : LOADOUT_MAP.entrySet()) {
            File file = new File(directory, entry.getKey() + ".json");
            File parent = file.getParentFile();
            parent.mkdirs();
            if (!file.exists()) {
                createDefaultLoadoutFile(file, entry.getValue());
            } else {
                loadLoadoutFile(entry.getKey(), file, entry.getValue(), loadedValues);
            }
        }
        for (String dir : DYNAMIC_LOADOUT_DIR_KEYS) {
            File subDirectory = new File(directory, dir);
            subDirectory.mkdirs();
            loadDirectoryRecursively(subDirectory, dir);
        }
        LOADOUT_MAP.putAll(loadedValues);
    }

    @SuppressWarnings("unchecked")
    public static <E extends EntityLivingBase> void apply(E entity, String path) {
        EntityLoadout loadout = LOADOUT_MAP.get(path);
        if (loadout == null) {
            Pubgmc.logger.warn("No such loadout found, empty loadout will be used");
            loadout = EntityLoadout.EMPTY;
            LOADOUT_MAP.put(path, loadout);
        }
        Class<E> entityClass = (Class<E>) entity.getClass();
        LoadoutApplicator<E> applicator = (LoadoutApplicator<E>) ENTITY_LOADOUT_HANDLERS.get(entityClass);
        if (applicator != null) {
            applicator.applyLoadoutOn(entity, loadout);
        }
    }

    private static void loadDirectoryRecursively(File directory, String key) {
        File[] files = directory.listFiles((dir, filename) -> filename.contains(".json"));
        for (File file : files) {
            if (file.isDirectory()) {
                String newKey = key + "/" + file.getName();
                loadDirectoryRecursively(file, newKey);
            } else {
                loadLoadoutFile(key + "/" + file.getName().replace(".json", ""), file, null, LOADOUT_MAP);
            }
        }
    }

    private static void loadLoadoutFile(String key, File loadoutFile, @Nullable EntityLoadout defaultLoadoutFile, Map<String, EntityLoadout> loadedValues) {
        try {
            EntityLoadout loadout = GSON.fromJson(new FileReader(loadoutFile), EntityLoadout.class);
            loadedValues.put(key, loadout);
        } catch (JsonParseException e) {
            if (defaultLoadoutFile != null) {
                createDefaultLoadoutFile(loadoutFile, defaultLoadoutFile);
            }
            Pubgmc.logger.error("Unable to load loadout file {} due to error {}", key, e.toString());
        } catch (IOException e) {
            throw new RuntimeException(key + " file loading failed", e);
        }
    }

    private static void createDefaultLoadoutFile(File file, EntityLoadout loadout) {
        try {
            if (!file.exists())
                file.createNewFile();
            JsonElement element = GSON.toJsonTree(loadout, EntityLoadout.class);
            String content = GSON.toJson(element);
            try (FileWriter writer = new FileWriter(file)) {
                writer.write(content);
            }
        } catch (JsonParseException e) {
            Pubgmc.logger.error("Unable to create loadout file {} due to error {}", file.getPath(), e.toString());
        } catch (IOException e) {
            throw new RuntimeException("Loadout file create failed");
        }
    }

    public static <E extends EntityLivingBase> void registerLoadoutHandler(Class<E> type, LoadoutApplicator<E> applicator) {
        ENTITY_LOADOUT_HANDLERS.put(type, applicator);
    }
}

package dev.toma.pubgmc.api.game.loadout;

import com.google.gson.*;
import dev.toma.pubgmc.Pubgmc;
import net.minecraft.entity.EntityLivingBase;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public final class LoadoutManager {

    private static final Map<Class<? extends EntityLivingBase>, LoadoutApplicator<?>> ENTITY_LOADOUT_HANDLERS = new HashMap<>();
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().registerTypeAdapter(EntityLoadout.class, new EntityLoadout.Adapter()).create();

    private static final Map<String, EntityLoadout> LOADOUT_MAP = new HashMap<>();

    public static void register(String path, EntityLoadout loadout) {
        LOADOUT_MAP.put(path, loadout);
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
                try {
                    EntityLoadout loadout = GSON.fromJson(new FileReader(file), EntityLoadout.class);
                    loadedValues.put(entry.getKey(), loadout);
                } catch (JsonParseException e) {
                    createDefaultLoadoutFile(file, entry.getValue());
                    Pubgmc.logger.error("Unable to load loadout file {} due to error {}", entry.getKey(), e.toString());
                } catch (IOException e) {
                    throw new RuntimeException(entry.getKey() + " file loading failed", e);
                }
            }
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

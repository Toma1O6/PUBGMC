package dev.toma.pubgmc.data.loot;

import com.google.gson.*;
import dev.toma.pubgmc.Pubgmc;
import dev.toma.pubgmc.api.data.DataVersion;
import dev.toma.pubgmc.api.data.Recreatable;
import dev.toma.pubgmc.api.event.LootEvent;
import dev.toma.pubgmc.api.game.Generator;
import dev.toma.pubgmc.api.game.loot.LootProvider;
import dev.toma.pubgmc.util.EventDispatcher;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class LootManager implements Recreatable {

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final File DIRECTORY = new File("./pubgmc/loot");
    private static final LootManager INSTANCE = new LootManager();
    private static final Marker MARKER = MarkerManager.getMarker("LootManager");

    private final Map<String, LootConfiguration> lootConfigurations = new HashMap<>();

    private LootManager() {
    }

    public static LootManager getInstance() {
        return INSTANCE;
    }

    public static void load() {
        try {
            INSTANCE.loadData();
        } catch (IOException e) {
            Pubgmc.logger.fatal(MARKER, "Loot initialization failed!");
            throw new RuntimeException("Loot load failed", e);
        }
    }

    public static void generateLootInGenerator(Generator generator, World world, BlockPos pos) {
        LootManager manager = getInstance();
        String configuration = generator.getLootConfigurationId();
        List<ItemStack> items = manager.generateFromConfiguration(configuration, world, pos);
        generator.fillWithLoot(EventDispatcher.getModifiedLoot(generator, items));
    }

    public List<ItemStack> generateFromConfiguration(String configurationKey, World world, BlockPos pos) {
        LootConfiguration configuration = lootConfigurations.get(configurationKey);
        if (configuration == null) {
            Pubgmc.logger.error(MARKER, "Attempted to generate loot with non-existent loot configuration: " + configurationKey);
            return Collections.emptyList();
        }
        LootGenerationContext context = new LootGenerationContext(world, pos, configuration.getGroups());
        LootProvider provider = configuration.getPool();
        return provider.generateItems(context);
    }

    public LootConfiguration getConfigurationById(String key) {
        return lootConfigurations.get(key);
    }

    @Override
    public void recreateData() {
        try {
            createDefaultLootConfigFiles(true);
            load();
        } catch (IOException e) {
            throw new IllegalStateException("Data recreate failed", e);
        }
    }

    @Override
    public DataVersion getVersion() {
        return Pubgmc.LOOT_DATA_VERSION;
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    private void loadData() throws IOException {
        DIRECTORY.mkdirs();
        lootConfigurations.clear();
        File[] files = DIRECTORY.listFiles();
        for (File file : files) {
            loadFile(file);
        }
        createDefaultLootConfigFiles(false);
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    public void createDefaultLootConfigFiles(boolean forceReload) throws IOException {
        Map<String, LootConfiguration> createdDefault = LootConfigurations.registerDefaultLootConfigurations(this::registerDefaultLootConfiguration, forceReload);
        for (Map.Entry<String, LootConfiguration> entry : createdDefault.entrySet()) {
            String filename = entry.getKey() + ".json";
            File file = new File(DIRECTORY, filename);
            file.createNewFile();
            try (FileWriter writer = new FileWriter(file)) {
                writer.write(GSON.toJson(entry.getValue().serialize()));
            }
        }
    }

    private void loadFile(File file) throws IOException {
        if (!file.isDirectory() && file.getName().endsWith(".json")) {
            try (FileReader reader = new FileReader(file)) {
                JsonParser parser = new JsonParser();
                JsonElement element = parser.parse(reader);
                LootConfiguration configuration = LootConfiguration.parse(element);
                String fileName = file.getName().replaceAll("\\.json$", "");
                LootEvent.Loaded event = new LootEvent.Loaded(fileName, configuration, false);
                MinecraftForge.EVENT_BUS.post(event);
                lootConfigurations.put(fileName, event.getConfiguration());
            } catch (JsonParseException e) {
                Pubgmc.logger.error(MARKER, "Error loading loot configuration file: " + file.getAbsolutePath() + " due to error: " + e.getMessage());
            }
        }
    }

    private boolean registerDefaultLootConfiguration(String key, LootConfiguration configuration) {
        boolean result = lootConfigurations.putIfAbsent(key, configuration) == null;
        if (result) {
            LootEvent.Loaded event = new LootEvent.Loaded(key, configuration, true);
            MinecraftForge.EVENT_BUS.post(event);
            lootConfigurations.put(key, event.getConfiguration());
        }
        return result;
    }
}

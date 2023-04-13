package dev.toma.pubgmc.data.loot;

import com.google.gson.*;
import dev.toma.pubgmc.Pubgmc;
import dev.toma.pubgmc.api.PubgmcRegistries;
import dev.toma.pubgmc.api.game.LootGenerator;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
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

public final class LootManager {

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final File DIRECTORY = new File("./pubgmc/loot");
    private static final LootManager INSTANCE = new LootManager();
    private static final Marker MARKER = MarkerManager.getMarker("LootManager");

    private final Map<String, LootConfiguration> lootConfigurations = new HashMap<>();

    private LootManager() {}

    public static LootManager getInstance() {
        return INSTANCE;
    }

    public static void load() {
        try {
            PubgmcRegistries.LOOT_PROVIDERS.lock();
            PubgmcRegistries.LOOT_PROCESSORS.lock();
            INSTANCE.loadData();
        } catch (IOException e) {
            Pubgmc.logger.fatal(MARKER, "Loot initialization failed!");
            throw new RuntimeException("Loot load failed", e);
        }
    }

    public static <G extends IInventory & LootGenerator> void generateLootInGenerator(G object, World world, BlockPos pos) {
        LootManager manager = getInstance();
        String configuration = object.getLootConfigurationId();
        List<ItemStack> items = manager.generateFromConfiguration(configuration, world, object, pos);
        object.fillWithLoot(items);
    }

    public List<ItemStack> generateFromConfiguration(String configurationKey, World world, IInventory inventory, BlockPos pos) {
        LootConfiguration configuration = lootConfigurations.get(configurationKey);
        if (configuration == null) {
            Pubgmc.logger.error("Attempted to generate loot with non-existent loot configuration: " + configurationKey);
            return Collections.emptyList();
        }
        LootGenerationContext context = new LootGenerationContext(world, inventory, pos, configuration.getGroups());
        LootProvider provider = configuration.getPool();
        return provider.generateItems(context);
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    private void loadData() throws IOException {
        DIRECTORY.mkdirs();
        lootConfigurations.clear();
        File[] files = DIRECTORY.listFiles();
        for (File file : files) {
            loadFile(file);
        }
        Map<String, LootConfiguration> createdDefault = LootConfigurations.registerDefaultLootConfigurations(this::registerDefaultLootConfiguration);
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
                lootConfigurations.put(file.getName().replaceAll("\\.json$", ""), configuration);
            } catch (JsonParseException e) {
                Pubgmc.logger.error(MARKER, "Error loading loot configuration file: " + file.getAbsolutePath() + " due to error: " + e.getMessage());
            }
        }
    }

    private boolean registerDefaultLootConfiguration(String key, LootConfiguration configuration) {
        return lootConfigurations.putIfAbsent(key, configuration) == null;
    }
}

package dev.toma.pubgmc.api.event;

import com.google.common.collect.ImmutableList;
import dev.toma.pubgmc.api.game.LootGenerator;
import dev.toma.pubgmc.data.loot.LootConfiguration;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.eventhandler.Event;

import java.util.List;

public abstract class LootEvent extends Event {

    private final String configurationId;
    private final LootConfiguration configuration;

    public LootEvent(String configurationId, LootConfiguration configuration) {
        this.configurationId = configurationId;
        this.configuration = configuration;
    }

    public LootConfiguration getConfiguration() {
        return configuration;
    }

    public String getConfigurationId() {
        return configurationId;
    }

    public static final class Loaded extends LootEvent {

        private final boolean isDefaultConfiguration;
        private LootConfiguration lootConfiguration;

        public Loaded(String configurationId, LootConfiguration configuration, boolean isDefault) {
            super(configurationId, configuration);
            this.lootConfiguration = configuration;
            this.isDefaultConfiguration = isDefault;
        }

        public boolean isDefaultConfiguration() {
            return isDefaultConfiguration;
        }

        public void setConfiguration(LootConfiguration configuration) {
            this.lootConfiguration = configuration;
        }

        @Override
        public LootConfiguration getConfiguration() {
            return lootConfiguration != null ? lootConfiguration : super.getConfiguration();
        }
    }

    public static final class Generated<T extends LootGenerator> extends LootEvent {

        private final T getLootGenerator;
        private List<ItemStack> loot;

        public Generated(String configurationId, LootConfiguration configuration, T getLootGenerator, List<ItemStack> loot) {
            super(configurationId, configuration);
            this.getLootGenerator = getLootGenerator;
            this.loot = ImmutableList.copyOf(loot);
        }

        public T getGetLootGenerator() {
            return getLootGenerator;
        }

        public List<ItemStack> getLoot() {
            return loot;
        }

        public void setLoot(List<ItemStack> loot) {
            this.loot = loot;
        }
    }
}

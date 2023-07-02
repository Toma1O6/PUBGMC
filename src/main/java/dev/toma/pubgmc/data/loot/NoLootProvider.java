package dev.toma.pubgmc.data.loot;

import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import dev.toma.pubgmc.api.game.loot.LootProvider;
import dev.toma.pubgmc.api.game.loot.LootProviderSerializer;
import dev.toma.pubgmc.api.game.loot.LootProviderType;
import net.minecraft.item.ItemStack;

import java.util.Collections;
import java.util.List;

public class NoLootProvider implements LootProvider {

    public static final NoLootProvider EMPTY = new NoLootProvider();

    private NoLootProvider() {}

    @Override
    public LootProviderType<?> getType() {
        return LootProviders.NONE;
    }

    @Override
    public List<ItemStack> generateItems(LootGenerationContext context) {
        return Collections.emptyList();
    }

    public static final class Serializer implements LootProviderSerializer<NoLootProvider> {

        @Override
        public void serializeData(JsonObject object, NoLootProvider provider) {
        }

        @Override
        public NoLootProvider parse(JsonObject object) throws JsonParseException {
            return EMPTY;
        }
    }
}

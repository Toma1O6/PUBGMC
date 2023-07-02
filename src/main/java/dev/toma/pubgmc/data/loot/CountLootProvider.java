package dev.toma.pubgmc.data.loot;

import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import dev.toma.pubgmc.api.game.loot.LootProvider;
import dev.toma.pubgmc.api.game.loot.LootProviderSerializer;
import dev.toma.pubgmc.api.game.loot.LootProviderType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.JsonUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CountLootProvider implements LootProvider {

    private final int minCount;
    private final int maxCount;
    private final LootProvider provider;

    public CountLootProvider(int minCount, int maxCount, LootProvider provider) {
        this.minCount = minCount;
        this.maxCount = maxCount;
        this.provider = provider;
    }

    @Override
    public List<ItemStack> generateItems(LootGenerationContext context) {
        Random random = context.getWorld().rand;
        int repeats = minCount + random.nextInt(maxCount - minCount + 1);
        List<ItemStack> items = new ArrayList<>();
        for (int i = 0; i < repeats; i++) {
            List<ItemStack> generated = provider.generateItems(context);
            items.addAll(generated);
        }
        return items;
    }

    @Override
    public LootProviderType<?> getType() {
        return LootProviders.COUNT;
    }

    public static final class Serializer implements LootProviderSerializer<CountLootProvider> {

        @Override
        public CountLootProvider parse(JsonObject object) throws JsonParseException {
            int min = JsonUtils.getInt(object, "min");
            int max = JsonUtils.getInt(object, "max");
            if (min < 0) {
                throw new JsonParseException("Min count cannot be lower than 0");
            }
            if (min > max) {
                throw new JsonParseException("Min count cannot be higher than max count");
            }
            JsonObject providerObj = JsonUtils.getJsonObject(object, "entry");
            return new CountLootProvider(min, max, LootProviderType.parse(providerObj));
        }

        @Override
        public void serializeData(JsonObject object, CountLootProvider provider) {
            object.addProperty("min", provider.minCount);
            object.addProperty("max", provider.maxCount);
            object.add("entry", LootProviderType.serialize(provider.provider));
        }
    }
}

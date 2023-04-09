package dev.toma.pubgmc.data.loot;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSyntaxException;
import dev.toma.pubgmc.util.helper.SerializationHelper;
import dev.toma.pubgmc.util.math.WeightedRandom;
import net.minecraft.item.ItemStack;
import net.minecraft.util.JsonUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class WeightedLootProvider implements LootProvider {

    private final WeightedRandom<WeightedRandom.Entry<LootProvider>> weightedRandom;

    public WeightedLootProvider(List<WeightedRandom.Entry<LootProvider>> list) {
        this.weightedRandom = new WeightedRandom<>(WeightedRandom.Entry::getWeight, list);
    }

    @Override
    public List<ItemStack> generateItems(LootGenerationContext context) {
        WeightedRandom.Entry<LootProvider> providerEntry = weightedRandom.getElement();
        if (providerEntry == null) {
            return Collections.emptyList();
        }
        return providerEntry.getElement().generateItems(context);
    }

    @Override
    public LootProviderType<?> getType() {
        return LootProviders.WEIGHTED_ITEM;
    }

    public static final class Serializer implements LootProviderSerializer<WeightedLootProvider> {

        @Override
        public WeightedLootProvider parse(JsonObject object) throws JsonParseException {
            JsonArray array = JsonUtils.getJsonArray(object, "items");
            if (array.size() == 0) {
                throw new JsonSyntaxException("Atleast one element must be defined in weighted entry list");
            }
            List<WeightedRandom.Entry<LootProvider>> entries = new ArrayList<>();
            array.forEach(element -> {
                JsonObject obj = SerializationHelper.asObject(element);
                entries.add(WeightedRandom.Entry.deserializeJson(obj, LootProviderType::parse));
            });
            return new WeightedLootProvider(entries);
        }

        @Override
        public void serializeData(JsonObject object, WeightedLootProvider provider) {
            JsonArray array = new JsonArray();
            for (WeightedRandom.Entry<LootProvider> entry : provider.weightedRandom.getValues()) {
                array.add(entry.serializeJson(LootProviderType::serialize));
            }
            object.add("items", array);
        }
    }
}

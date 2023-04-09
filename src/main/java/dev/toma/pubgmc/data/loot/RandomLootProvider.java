package dev.toma.pubgmc.data.loot;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import dev.toma.pubgmc.util.PUBGMCUtil;
import dev.toma.pubgmc.util.helper.SerializationHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.util.JsonUtils;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RandomLootProvider implements LootProvider {

    private final List<LootProvider> providers;

    public RandomLootProvider(List<LootProvider> providers) {
        this.providers = providers;
    }

    @Override
    public List<ItemStack> generateItems(LootGenerationContext context) {
        if (providers.isEmpty()) {
            return Collections.emptyList();
        }
        World world = context.getWorld();
        return PUBGMCUtil.randomListElement(providers, world.rand).generateItems(context);
    }

    @Override
    public LootProviderType<?> getType() {
        return LootProviders.RANDOM_ITEM;
    }

    public static final class Serializer implements LootProviderSerializer<RandomLootProvider> {

        @Override
        public RandomLootProvider parse(JsonObject object) throws JsonParseException {
            JsonArray array = JsonUtils.getJsonArray(object, "items", new JsonArray());
            List<LootProvider> list = new ArrayList<>();
            array.forEach(element -> {
                JsonObject itemObj = SerializationHelper.asObject(element);
                list.add(LootProviderType.parse(itemObj));
            });
            return new RandomLootProvider(list);
        }

        @Override
        public void serializeData(JsonObject object, RandomLootProvider provider) {
            JsonArray array = new JsonArray();
            provider.providers.forEach(lootProvider -> array.add(LootProviderType.serialize(lootProvider)));
            object.add("items", array);
        }
    }
}

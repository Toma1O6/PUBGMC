package dev.toma.pubgmc.data.loot;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import dev.toma.pubgmc.util.helper.SerializationHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.util.JsonUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MultiValueLootProvider implements LootProvider {

    private final List<LootProvider> providerList;

    public MultiValueLootProvider(List<LootProvider> providerList) {
        this.providerList = providerList;
    }

    @Override
    public List<ItemStack> generateItems(LootGenerationContext context) {
        if (providerList.isEmpty()) {
            return Collections.emptyList();
        }
        List<ItemStack> outputs = new ArrayList<>();
        providerList.forEach(provider -> {
            List<ItemStack> generated = provider.generateItems(context);
            outputs.addAll(generated);
        });
        return outputs;
    }

    @Override
    public LootProviderType<?> getType() {
        return LootProviders.MULTI_VALUE;
    }

    public static final class Serializer implements LootProviderSerializer<MultiValueLootProvider> {

        @Override
        public MultiValueLootProvider parse(JsonObject object) throws JsonParseException {
            JsonArray array = JsonUtils.getJsonArray(object, "items");
            List<LootProvider> list = new ArrayList<>();
            array.forEach(el -> {
                JsonObject obj = SerializationHelper.asObject(el);
                list.add(LootProviderType.parse(obj));
            });
            return new MultiValueLootProvider(list);
        }

        @Override
        public void serializeData(JsonObject object, MultiValueLootProvider provider) {
            JsonArray array = new JsonArray();
            provider.providerList.forEach(prov -> array.add(LootProviderType.serialize(prov)));
            object.add("items", array);
        }
    }
}

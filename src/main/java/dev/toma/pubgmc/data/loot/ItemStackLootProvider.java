package dev.toma.pubgmc.data.loot;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import dev.toma.pubgmc.data.loot.processor.LootProcessor;
import dev.toma.pubgmc.data.loot.processor.LootProcessorType;
import dev.toma.pubgmc.util.helper.SerializationHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.util.JsonUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ItemStackLootProvider implements LootProvider {

    private final ItemStack itemStack;
    private final List<LootProcessor> processors;

    public ItemStackLootProvider(ItemStack itemStack, List<LootProcessor> processors) {
        this.itemStack = itemStack;
        this.processors = processors;
    }

    public ItemStackLootProvider(ItemStack stack) {
        this(stack, Collections.emptyList());
    }

    @Override
    public List<ItemStack> generateItems(LootGenerationContext context) {
        List<ItemStack> list = new ArrayList<>();
        list.add(itemStack.copy());
        processors.forEach(processor -> processor.processItems(context, list));
        return list;
    }

    @Override
    public LootProviderType<?> getType() {
        return LootProviders.ITEM;
    }

    public static final class Serializer implements LootProviderSerializer<ItemStackLootProvider> {

        @Override
        public ItemStackLootProvider parse(JsonObject object) throws JsonParseException {
            JsonObject itemJson = JsonUtils.getJsonObject(object, "output");
            JsonArray processors = JsonUtils.getJsonArray(object, "processors", new JsonArray());
            ItemStack stack = SerializationHelper.readItemStack(itemJson);
            List<LootProcessor> processorList = LootProcessorType.parseMany(processors);
            return new ItemStackLootProvider(stack, processorList);
        }

        @Override
        public void serializeData(JsonObject object, ItemStackLootProvider provider) {
            object.add("output", SerializationHelper.writeItemStack(provider.itemStack));
            if (!provider.processors.isEmpty()) {
                object.add("processors", LootProcessorType.serializeMany(provider.processors));
            }
        }
    }
}

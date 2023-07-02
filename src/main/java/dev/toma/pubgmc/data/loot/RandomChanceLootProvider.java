package dev.toma.pubgmc.data.loot;

import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSyntaxException;
import dev.toma.pubgmc.api.game.loot.LootProvider;
import dev.toma.pubgmc.api.game.loot.LootProviderSerializer;
import dev.toma.pubgmc.api.game.loot.LootProviderType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.JsonUtils;

import java.util.Collections;
import java.util.List;
import java.util.Random;

public class RandomChanceLootProvider implements LootProvider {

    private final float chance;
    private final LootProvider provider;

    public RandomChanceLootProvider(float chance, LootProvider provider) {
        this.chance = chance;
        this.provider = provider;
    }

    @Override
    public List<ItemStack> generateItems(LootGenerationContext context) {
        Random random = context.getWorld().rand;
        if (random.nextFloat() >= chance) {
            return Collections.emptyList();
        }
        return provider.generateItems(context);
    }

    @Override
    public LootProviderType<?> getType() {
        return LootProviders.RANDOM_CHANCE;
    }

    public static final class Serializer implements LootProviderSerializer<RandomChanceLootProvider> {

        @Override
        public RandomChanceLootProvider parse(JsonObject object) throws JsonParseException {
            float chance = JsonUtils.getFloat(object, "chance");
            if (chance < 0 || chance > 1) {
                throw new JsonSyntaxException("Chance value must be between 0 and 1");
            }
            JsonObject provider = JsonUtils.getJsonObject(object, "entry");
            return new RandomChanceLootProvider(chance, LootProviderType.parse(provider));
        }

        @Override
        public void serializeData(JsonObject object, RandomChanceLootProvider provider) {
            object.addProperty("chance", provider.chance);
            object.add("entry", LootProviderType.serialize(provider.provider));
        }
    }
}

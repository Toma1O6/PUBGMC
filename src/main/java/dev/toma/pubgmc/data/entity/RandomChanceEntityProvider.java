package dev.toma.pubgmc.data.entity;

import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import dev.toma.pubgmc.api.entity.EntityProvider;
import dev.toma.pubgmc.api.entity.EntityProviderSerializer;
import dev.toma.pubgmc.api.entity.EntityProviderType;
import net.minecraft.entity.Entity;
import net.minecraft.util.JsonUtils;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class RandomChanceEntityProvider implements EntityProvider {

    private final float chance;
    private final EntityProvider provider;

    public RandomChanceEntityProvider(float chance, EntityProvider provider) {
        this.chance = chance;
        this.provider = provider;
    }

    @Override
    public @Nullable Entity spawnEntity(World world, BlockPos pos) {
        if (world.rand.nextFloat() < chance) {
            return provider.spawnEntity(world, pos);
        }
        return null;
    }

    @Override
    public EntityProviderType<?> getType() {
        return EntityProviders.RANDOM_CHANCE_ENTITY_PROVIDER;
    }

    public static final class Serializer implements EntityProviderSerializer<RandomChanceEntityProvider> {

        @Override
        public void serializeData(JsonObject object, RandomChanceEntityProvider provider) {
            object.addProperty("chance", provider.chance);
            object.add("provider", EntityProviderType.serialize(provider.provider));
        }

        @Override
        public RandomChanceEntityProvider parse(JsonObject data) throws JsonParseException {
            float chance = JsonUtils.getFloat(data, "chance");
            EntityProvider provider = EntityProviderType.parse(JsonUtils.getJsonObject(data, "provider"));
            return new RandomChanceEntityProvider(chance, provider);
        }
    }
}

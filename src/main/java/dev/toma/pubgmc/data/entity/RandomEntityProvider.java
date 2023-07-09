package dev.toma.pubgmc.data.entity;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import dev.toma.pubgmc.api.entity.EntityProvider;
import dev.toma.pubgmc.api.entity.EntityProviderSerializer;
import dev.toma.pubgmc.api.entity.EntityProviderType;
import dev.toma.pubgmc.util.PUBGMCUtil;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class RandomEntityProvider implements EntityProvider {

    private final List<EntityProvider> providers;

    public RandomEntityProvider(List<EntityProvider> providers) {
        this.providers = providers;
    }

    @Override
    public @Nullable Entity spawnEntity(World world, BlockPos pos) {
        if (!providers.isEmpty()) {
            EntityProvider provider = PUBGMCUtil.randomListElement(providers, world.rand);
            return provider.spawnEntity(world, pos);
        }
        return null;
    }

    @Override
    public EntityProviderType<?> getType() {
        return EntityProviders.RANDOM_ENTITY_PROVIDER;
    }

    public static final class Serializer implements EntityProviderSerializer<RandomEntityProvider> {

        @Override
        public void serializeData(JsonObject object, RandomEntityProvider provider) {
            JsonArray array = new JsonArray();
            provider.providers.forEach(prov -> array.add(EntityProviderType.serialize(prov)));
            object.add("providers", array);
        }

        @Override
        public RandomEntityProvider parse(JsonObject data) throws JsonParseException {
            JsonArray array = new JsonArray();
            List<EntityProvider> providers = new ArrayList<>();
            array.forEach(el -> {
                JsonObject obj = el.getAsJsonObject();
                providers.add(EntityProviderType.parse(obj));
            });
            return new RandomEntityProvider(providers);
        }
    }
}

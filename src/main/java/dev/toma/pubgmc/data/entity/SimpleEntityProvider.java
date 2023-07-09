package dev.toma.pubgmc.data.entity;

import com.google.gson.*;
import dev.toma.pubgmc.Pubgmc;
import dev.toma.pubgmc.api.entity.*;
import net.minecraft.entity.Entity;
import net.minecraft.util.JsonUtils;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SimpleEntityProvider implements EntityProvider {

    private final Class<? extends Entity> entityClass;
    private final List<EntityProcessor> processors;

    public SimpleEntityProvider(Class<? extends Entity> entityClass, List<EntityProcessor> processors) {
        this.entityClass = entityClass;
        this.processors = processors;
    }

    public SimpleEntityProvider(Class<? extends Entity> entityClass) {
        this(entityClass, Collections.emptyList());
    }

    @Override
    public @Nullable Entity spawnEntity(World world, BlockPos pos) {
        try {
            Constructor<? extends Entity> constructor = entityClass.getDeclaredConstructor(World.class);
            Entity instance = constructor.newInstance(world);
            instance.setPosition(pos.getX() + 0.5, pos.getY() + 1.0, pos.getZ() + 0.5);
            processors.forEach(processor -> processor.processEntityPreSpawn(instance));
            world.spawnEntity(instance);
            processors.forEach(processor -> processor.processEntityPostSpawn(instance));
            return instance;
        } catch (Exception e) {
            Pubgmc.logger.error("Entity spawn from simple entity provider failed", e);
            return null;
        }
    }

    @Override
    public EntityProviderType<?> getType() {
        return EntityProviders.SIMPLE_ENTITY_PROVIDER;
    }

    public static final class Serializer implements EntityProviderSerializer<SimpleEntityProvider> {

        @Override
        public void serializeData(JsonObject object, SimpleEntityProvider provider) {
            object.addProperty("entityClass", provider.entityClass.getName());
            JsonArray processors = new JsonArray();
            provider.processors.forEach(processor -> processors.add(EntityProcessorType.serialize(processor)));
            object.add("processors", processors);
        }

        @SuppressWarnings("unchecked")
        @Override
        public SimpleEntityProvider parse(JsonObject data) throws JsonParseException {
            String className = JsonUtils.getString(data, "entityClass");
            Class<? extends Entity> entityClass;
            try {
                entityClass = (Class<? extends Entity>) Class.forName(className);
            } catch (ClassNotFoundException e) {
                throw new JsonSyntaxException("Class not found for name: " + className);
            } catch (ClassCastException e) {
                throw new JsonSyntaxException("Non-entity class provided: " + className);
            }
            List<EntityProcessor> list = new ArrayList<>();
            JsonArray array = JsonUtils.getJsonArray(data, "processors", new JsonArray());
            for (JsonElement e : array) {
                JsonObject obj = e.getAsJsonObject();
                list.add(EntityProcessorType.parse(obj));
            }
            return new SimpleEntityProvider(entityClass, list);
        }
    }
}

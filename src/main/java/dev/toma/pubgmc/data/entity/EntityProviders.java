package dev.toma.pubgmc.data.entity;

import dev.toma.pubgmc.Pubgmc;
import dev.toma.pubgmc.api.entity.EntityProvider;
import dev.toma.pubgmc.api.entity.EntityProviderSerializer;
import dev.toma.pubgmc.api.entity.EntityProviderType;

public final class EntityProviders {

    public static final EntityProviderType<SimpleEntityProvider> SIMPLE_ENTITY_PROVIDER = create("entity", new SimpleEntityProvider.Serializer());
    public static final EntityProviderType<RandomChanceEntityProvider> RANDOM_CHANCE_ENTITY_PROVIDER = create("random_chance", new RandomChanceEntityProvider.Serializer());
    public static final EntityProviderType<RandomEntityProvider> RANDOM_ENTITY_PROVIDER = create("random_entity", new RandomEntityProvider.Serializer());

    private static <E extends EntityProvider> EntityProviderType<E> create(String id, EntityProviderSerializer<E> serializer) {
        return EntityProviderType.create(Pubgmc.getResource(id), serializer);
    }

    private EntityProviders() {}
}

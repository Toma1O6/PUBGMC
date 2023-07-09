package dev.toma.pubgmc.api.entity;

import net.minecraft.entity.Entity;

public interface EntityProcessor {

    void processEntityPreSpawn(Entity entity);

    void processEntityPostSpawn(Entity entity);

    EntityProcessorType<?> getType();
}

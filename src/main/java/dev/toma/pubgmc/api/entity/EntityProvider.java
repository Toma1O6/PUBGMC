package dev.toma.pubgmc.api.entity;

import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public interface EntityProvider {

    @Nullable
    Entity spawnEntity(World world, BlockPos pos);

    EntityProviderType<?> getType();
}

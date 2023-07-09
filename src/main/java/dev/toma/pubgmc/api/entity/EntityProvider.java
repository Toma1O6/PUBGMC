package dev.toma.pubgmc.api.entity;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public interface EntityProvider {

    void spawnEntity(World world, BlockPos pos);

    EntityProviderType<?> getType();
}

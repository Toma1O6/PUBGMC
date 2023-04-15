package dev.toma.pubgmc.data.loot;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Map;
import java.util.Optional;

public final class LootGenerationContext {

    private final World world;
    private final BlockPos pos;
    private final Map<String, LootProvider> groupMappings;

    public LootGenerationContext(World world, BlockPos pos, Map<String, LootProvider> groupMappings) {
        this.world = world;
        this.pos = pos;
        this.groupMappings = groupMappings;
    }

    public World getWorld() {
        return world;
    }

    public BlockPos getPos() {
        return pos;
    }

    public Optional<LootProvider> getGroup(String key) {
        return Optional.ofNullable(groupMappings.get(key));
    }
}

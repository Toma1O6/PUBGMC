package dev.toma.pubgmc.data.loot;

import net.minecraft.inventory.IInventory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Map;
import java.util.Optional;

public final class LootGenerationContext {

    private final World world;
    private final IInventory inventory;
    private final BlockPos pos;
    private final Map<String, LootProvider> groupMappings;

    public LootGenerationContext(World world, IInventory inventory, BlockPos pos, Map<String, LootProvider> groupMappings) {
        this.world = world;
        this.inventory = inventory;
        this.pos = pos;
        this.groupMappings = groupMappings;
    }

    public World getWorld() {
        return world;
    }

    public IInventory getInventory() {
        return inventory;
    }

    public BlockPos getPos() {
        return pos;
    }

    public Optional<LootProvider> getGroup(String key) {
        return Optional.ofNullable(groupMappings.get(key));
    }
}

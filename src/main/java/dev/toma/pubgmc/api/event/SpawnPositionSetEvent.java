package dev.toma.pubgmc.api.event;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.eventhandler.Event;

public class SpawnPositionSetEvent extends Event {

    private final World world;
    private BlockPos pos;

    public SpawnPositionSetEvent(BlockPos originalPosition, World world) {
        this.world = world;
        this.pos = originalPosition;
    }

    public World getWorld() {
        return world;
    }

    public void setSpawnPosition(BlockPos pos) {
        this.pos = pos;
    }

    public BlockPos getSpawnPosition() {
        return pos;
    }
}

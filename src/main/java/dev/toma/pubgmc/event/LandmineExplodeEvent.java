package dev.toma.pubgmc.event;

import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.relauncher.Side;

import java.util.List;

/**
 * This is called when landmine explodes in world
 * for both sides
 */
@Deprecated
public class LandmineExplodeEvent extends Event {
    public final Side side;
    private final BlockPos pos;
    private final World world;
    private final List<Entity> affectedEntities;

    public LandmineExplodeEvent(Side side, BlockPos pos, World world, List<Entity> affectedEntities) {
        this.side = side;
        this.pos = pos;
        this.world = world;
        this.affectedEntities = affectedEntities;
    }

    @Override
    public boolean isCancelable() {
        return false;
    }

    public BlockPos getExplosionPosition() {
        return pos;
    }

    public List<Entity> getAffectedEntities() {
        return affectedEntities;
    }

    public World getWorld() {
        return world;
    }
}

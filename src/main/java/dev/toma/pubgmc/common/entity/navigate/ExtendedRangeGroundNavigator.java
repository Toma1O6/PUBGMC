package dev.toma.pubgmc.common.entity.navigate;

import net.minecraft.entity.EntityLiving;
import net.minecraft.pathfinding.PathNavigateGround;
import net.minecraft.world.World;

public class ExtendedRangeGroundNavigator extends PathNavigateGround {

    private final float range;

    public ExtendedRangeGroundNavigator(EntityLiving entitylivingIn, World worldIn, float range) {
        super(entitylivingIn, worldIn);
        this.range = range;
    }

    public ExtendedRangeGroundNavigator(EntityLiving entityLiving, World world) {
        this(entityLiving, world, 1024);
    }

    @Override
    public float getPathSearchRange() {
        return range;
    }
}

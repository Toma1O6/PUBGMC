package dev.toma.pubgmc.api.block;

import dev.toma.pubgmc.common.entity.EntityBullet;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

/**
 * Allows additional interaction when impacted by bullet entity
 *
 * @author Toma
 * @since 1.9.0
 */
public interface IBulletReaction {

    /**
     * Handles projectile impact interaction
     * @param bullet Impacted bullet entity
     * @param hit Exact coordinates of hit on this block
     * @param pos Position of this block
     */
    void onHit(EntityBullet bullet, Vec3d hit, BlockPos pos);

    /**
     * Determines whether {@link IBulletReaction#onHit(EntityBullet, Vec3d, BlockPos)} can be called for this specific blockstate.
     * @param world Interaction world
     * @param pos Interaction block pos
     * @param state Interaction block state
     * @return If further processing of this interface is allowed
     */
    boolean allowBulletInteraction(World world, BlockPos pos, IBlockState state);
}

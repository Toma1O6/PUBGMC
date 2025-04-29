package dev.toma.pubgmc.api.block;

import dev.toma.pubgmc.common.entity.EntityBullet;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.entity.Entity;

import javax.annotation.Nullable;

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
     * @param state Interaction block state
     * @param entity Intraction entity
     */
    void onHit(EntityBullet bullet, Vec3d hit, @Nullable IBlockState state, @Nullable Entity entity);

    /**
     * Determines whether {@link IBulletReaction#onHit(EntityBullet, Vec3d, IBlockState, Entity)} can be called.
     * @param world Interaction world
     * @param state Interaction block state
     * @param entity Intraction entity
     * @return If further processing of this interface is allowed
     */
    boolean allowBulletInteraction(World world, @Nullable IBlockState state, @Nullable Entity entity);
}

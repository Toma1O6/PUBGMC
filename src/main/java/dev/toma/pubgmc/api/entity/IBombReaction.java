package dev.toma.pubgmc.api.entity;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import javax.annotation.Nullable;

/**
 * Allows additional interaction when impacted by explodable entity
 *
 * @author XiaoColorful
 * @since 1.10.8
 */
public interface IBombReaction {

    /**
     * Handles explode interaction
     * @param exploder Impacted explode entity
     * @param vec3d explosion vector
     * @param state Interaction block state
     * @param entity Interaction entity
     */
    void onBomb(Entity exploder, Vec3d vec3d, @Nullable IBlockState state, @Nullable Entity entity);

    /**
     * Determines whether {@link IBombReaction#onBomb(Entity, Vec3d, IBlockState, Entity)}  can be called.}
     * @param world Interaction world
     * @param state Interaction block state
     * @param entity Interaction entity
     * @return If further processing of this interface is allowed
     */
    boolean allowBombInteraction(World world, @Nullable IBlockState state, @Nullable Entity entity);
}

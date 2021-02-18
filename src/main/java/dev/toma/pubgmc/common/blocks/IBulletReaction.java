package dev.toma.pubgmc.common.blocks;

import dev.toma.pubgmc.common.entity.EntityBullet;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public interface IBulletReaction {

    void onHit(EntityBullet bullet, Vec3d hit);

    boolean canReceiveFeedBack(World world, BlockPos pos, IBlockState state);
}

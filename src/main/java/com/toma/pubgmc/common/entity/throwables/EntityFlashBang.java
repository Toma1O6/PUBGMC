package com.toma.pubgmc.common.entity.throwables;

import com.toma.pubgmc.Pubgmc;
import com.toma.pubgmc.util.PUBGMCUtil;
import com.toma.pubgmc.util.handlers.FlashHandler;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.List;

public class EntityFlashBang extends EntityThrowableExplodeable {

    public EntityFlashBang(World world) {
        super(world);
    }

    public EntityFlashBang(World world, EntityLivingBase thrower, EnumEntityThrowState state) {
        super(world, thrower, state);
    }

    public EntityFlashBang(World world, EntityLivingBase thrower, EnumEntityThrowState state, int timeLeft) {
        super(world, thrower, state, timeLeft);
    }

    @Override
    public void onExplode() {
        if(world.isRemote) {
            Pubgmc.proxy.playDelayedSound(SoundEvents.ENTITY_GENERIC_EXPLODE, posX, posY, posZ, 5f);
            world.spawnParticle(EnumParticleTypes.EXPLOSION_LARGE, posX, posY, posZ, 0, 0, 0);
            for (int i = 0; i < 7; i++) {
                world.spawnParticle(EnumParticleTypes.CLOUD, posX, posY, posZ, rand.nextDouble() / 8 - rand.nextDouble() / 8, rand.nextDouble() / 4, rand.nextDouble() / 8 - rand.nextDouble() / 8);
            }
        }
        List<EntityPlayer> entityList = world.getEntitiesWithinAABB(EntityPlayer.class, Block.FULL_BLOCK_AABB.offset(getPosition()).grow(30));
        Vec3d start = PUBGMCUtil.getPositionVec(this);
        entityList.forEach(e -> {
            Vec3d entityVec = new Vec3d(e.posX, e.posY + e.getEyeHeight() + 0.25, e.posZ);
            RayTraceResult rayTrace = this.rayTraceBlocks(start, entityVec);
            if (rayTrace == null) {
                FlashHandler.flashPlayer(e, this);
            }
        });
        this.setDead();
    }

    protected RayTraceResult rayTraceBlocks(Vec3d from, Vec3d to) {
        if(Double.isNaN(from.x) || Double.isNaN(from.y) || Double.isNaN(from.z) || Double.isNaN(to.x) || Double.isNaN(to.y) || Double.isNaN(to.z)) {
            int toX = MathHelper.floor(from.x);
            int toY = MathHelper.floor(from.y);
            int toZ = MathHelper.floor(from.z);
            int fromX = MathHelper.floor(to.x);
            int fromY = MathHelper.floor(to.y);
            int fromZ = MathHelper.floor(to.z);
            BlockPos current = new BlockPos(fromX, fromY, fromZ);
            IBlockState state = this.world.getBlockState(current);
            if(state.getCollisionBoundingBox(this.world, current) != Block.NULL_AABB && state.getBlock().canCollideCheck(state, false)) {
                RayTraceResult traceResult = state.collisionRayTrace(this.world, current, from, to);
                if(traceResult != null) {
                    return traceResult;
                }
            }
            RayTraceResult rayTraceResult = null;
            int partialChecks = 200;
            while (partialChecks-- >= 0) {
                if (Double.isNaN(from.x) || Double.isNaN(from.y) || Double.isNaN(from.z)) {
                    return null;
                }
                if (toX == fromX && toY == fromY && toZ == fromZ) {
                    return null;
                }
                boolean flag0 = true;
                boolean flag1 = true;
                boolean flag2 = true;
                double d0 = 999D;
                double d1 = 999D;
                double d2 = 999D;
                if (toX > fromX) {
                    d0 = (double) fromX + 1.0D;
                } else if (toX < fromX) {
                    d0 = (double) fromX + 0.0D;
                } else {
                    flag2 = false;
                }

                if (toY > fromY) {
                    d1 = (double) fromY + 1.0D;
                } else if (toY < fromY) {
                    d1 = (double) fromY + 0.0D;
                } else {
                    flag0 = false;
                }

                if (toZ > fromZ) {
                    d2 = (double) fromZ + 1.0D;
                } else if (toZ < fromZ) {
                    d2 = (double) fromZ + 0.0D;
                } else {
                    flag1 = false;
                }

                double d3 = 999.0D;
                double d4 = 999.0D;
                double d5 = 999.0D;
                double d6 = to.x - from.x;
                double d7 = to.y - from.y;
                double d8 = to.z - from.z;

                if (flag2) {
                    d3 = (d0 - from.x) / d6;
                }

                if (flag0) {
                    d4 = (d1 - from.y) / d7;
                }

                if (flag1) {
                    d5 = (d2 - from.z) / d8;
                }

                if (d3 == -0.0D) {
                    d3 = -1.0E-4D;
                }

                if (d4 == -0.0D) {
                    d4 = -1.0E-4D;
                }

                if (d5 == -0.0D) {
                    d5 = -1.0E-4D;
                }

                EnumFacing enumfacing;

                if (d3 < d4 && d3 < d5) {
                    enumfacing = toX > fromX ? EnumFacing.WEST : EnumFacing.EAST;
                    from = new Vec3d(d0, from.y + d7 * d3, from.z + d8 * d3);
                } else if (d4 < d5) {
                    enumfacing = toY > fromY ? EnumFacing.DOWN : EnumFacing.UP;
                    from = new Vec3d(from.x + d6 * d4, d1, from.z + d8 * d4);
                } else {
                    enumfacing = toZ > fromZ ? EnumFacing.NORTH : EnumFacing.SOUTH;
                    from = new Vec3d(from.x + d6 * d5, from.y + d7 * d5, d2);
                }

                fromX = MathHelper.floor(from.x) - (enumfacing == EnumFacing.EAST ? 1 : 0);
                fromY = MathHelper.floor(from.y) - (enumfacing == EnumFacing.UP ? 1 : 0);
                fromZ = MathHelper.floor(from.z) - (enumfacing == EnumFacing.SOUTH ? 1 : 0);
                current = new BlockPos(fromX, fromZ, fromZ);
                IBlockState state1 = this.world.getBlockState(current);
                if(state1.getMaterial() != Material.GLASS && state1.getCollisionBoundingBox(this.world, current) != Block.NULL_AABB) {
                    if(state1.getBlock().canCollideCheck(state1, false)) {
                        return state1.collisionRayTrace(this.world, current, from, to);
                    }
                }
            }
            return null;
        }
        return null;
    }
}

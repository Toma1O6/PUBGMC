package dev.toma.pubgmc.common.entity.throwables;

import dev.toma.pubgmc.Pubgmc;
import dev.toma.pubgmc.util.PUBGMCUtil;
import dev.toma.pubgmc.util.handlers.FlashHandler;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.List;
import java.util.function.Predicate;

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
        if (world.isRemote) {
            Pubgmc.proxy.playDelayedSound(SoundEvents.ENTITY_GENERIC_EXPLODE, posX, posY, posZ, 5f);
            world.spawnParticle(EnumParticleTypes.EXPLOSION_LARGE, posX, posY, posZ, 0, 0, 0);
            for (int i = 0; i < 7; i++) {
                world.spawnParticle(EnumParticleTypes.CLOUD, posX, posY, posZ, rand.nextDouble() / 8 - rand.nextDouble() / 8, rand.nextDouble() / 4, rand.nextDouble() / 8 - rand.nextDouble() / 8);
            }
        }
        List<EntityLivingBase> entityList = world.getEntitiesWithinAABB(EntityLivingBase.class, Block.FULL_BLOCK_AABB.offset(getPosition()).grow(30));
        Vec3d start = PUBGMCUtil.getPositionVec(this);
        entityList.forEach(e -> {
            Vec3d entityVec = new Vec3d(e.posX, e.posY + e.getEyeHeight() + 0.25, e.posZ);
            RayTraceResult rayTrace = this.rayTraceBlocks(start, entityVec, iBlockState -> iBlockState.getBlock().isOpaqueCube(iBlockState));
            if (rayTrace == null) {
                FlashHandler.flashEntity(e, this);
            }
        });
        this.setDead();
    }

    protected RayTraceResult rayTraceBlocks(Vec3d from, Vec3d to, Predicate<IBlockState> predicate) {
        if (Double.isNaN(from.x) || Double.isNaN(from.y) || Double.isNaN(from.z) || Double.isNaN(to.x) || Double.isNaN(to.y) || Double.isNaN(to.z)) {
            return null;
        }
        BlockPos current = new BlockPos(from);
        IBlockState stateAtStart = this.world.getBlockState(current);
        if (stateAtStart.getCollisionBoundingBox(this.world, current) != Block.NULL_AABB && stateAtStart.getBlock().canCollideCheck(stateAtStart, false) && predicate.test(stateAtStart)) {
            RayTraceResult rayTraceResult = stateAtStart.collisionRayTrace(world, current, from, to);
            if (rayTraceResult != null) {
                return rayTraceResult;
            }
        }
        int checks = this.getPartialChecksAmount(from, to);
        double x = (to.x - from.x) / checks;
        double y = (to.y - from.y) / checks;
        double z = (to.z - from.z) / checks;
        for (int i = 1; i <= checks; i++) {
            BlockPos pos = new BlockPos(from.x + x * i, from.y + y * i, from.z + z * i);
            IBlockState state = this.world.getBlockState(pos);
            Block block = state.getBlock();
            if (predicate.test(state) && state.getCollisionBoundingBox(this.world, pos) != Block.NULL_AABB && block.canCollideCheck(state, false)) {
                RayTraceResult rayTraceResult = state.collisionRayTrace(world, pos, new Vec3d(from.x + x, from.y + y, from.z + z), to);
                if (rayTraceResult != null) {
                    return rayTraceResult;
                }
            }
        }
        return null;
    }

    private int getPartialChecksAmount(Vec3d from, Vec3d to) {
        double distX = this.sqr(from.x - to.x);
        double distY = this.sqr(from.y - to.y);
        double distZ = this.sqr(from.z - to.z);
        double distance = Math.sqrt(distX + distY + distZ);
        return (int) Math.max(1.0D, distance * 2);
    }

    private double sqr(double num) {
        return num * num;
    }
}

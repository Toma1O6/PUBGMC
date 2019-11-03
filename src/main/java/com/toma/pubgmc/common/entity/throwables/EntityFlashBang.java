package com.toma.pubgmc.common.entity.throwables;

import com.toma.pubgmc.Pubgmc;
import com.toma.pubgmc.util.PUBGMCUtil;
import com.toma.pubgmc.util.handlers.FlashHandler;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.EnumParticleTypes;
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
            RayTraceResult rayTrace = this.world.rayTraceBlocks(start, entityVec, false, true, false);
            // TODO improve raytracing for opaque and non solid blocks
            if (rayTrace == null) {
                FlashHandler.flashPlayer(e, this);
            }
        });
        this.setDead();
    }
}

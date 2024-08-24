package dev.toma.pubgmc.common.ai;

import dev.toma.pubgmc.api.game.playzone.Playzone;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class EntityAIMoveIntoPlayzone extends EntityAIBase {

    private final EntityLiving living;
    private final PlayzoneProvider playzoneProvider;
    private final double moveSpeed;

    private int delay;

    public EntityAIMoveIntoPlayzone(EntityLiving living, PlayzoneProvider playzoneProvider, double moveSpeed) {
        this.living = living;
        this.playzoneProvider = playzoneProvider;
        this.moveSpeed = moveSpeed;
        this.setMutexBits(1);
    }

    public EntityAIMoveIntoPlayzone(EntityLiving living, PlayzoneProvider playzoneProvider) {
        this(living, playzoneProvider, 1.0);
    }

    @Override
    public boolean shouldExecute() {
        Playzone playzone = playzoneProvider.getPlayzone(living.world);
        return living.getAttackTarget() == null && playzone != null && !playzone.isWithin(living.posX, living.posZ) && playzone.length() > 8;
    }

    @Override
    public boolean shouldContinueExecuting() {
        Playzone playzone = playzoneProvider.getPlayzone(living.world);
        return playzone != null && !playzone.isWithin(living.posX, living.posZ);
    }

    @Override
    public void startExecuting() {
        delay = 0;
        if (moveSpeed > 1.0F) {
            living.setSprinting(true);
        }
    }

    @Override
    public void resetTask() {
        living.setSprinting(false);
    }

    @Override
    public void updateTask() {
        if (--delay <= 0) {
            Playzone playzone = playzoneProvider.getPlayzone(living.world);
            if (playzone != null) {
                BlockPos pos = playzone.findNearestPositionWithin(living.getPositionVector(), living.world);
                if (living.getDistanceSq(pos) >= 1024) { // split the path into smaller segments
                    double diffX = pos.getX() - living.posX;
                    double diffZ = pos.getZ() - living.posZ;
                    double targetX = living.posX + MathHelper.clamp(diffX, -128, 128);
                    double targetZ = living.posZ + MathHelper.clamp(diffZ, -128, 128);
                    double targetY = living.world.getHeight((int) targetX, (int) targetZ);
                    living.getNavigator().tryMoveToXYZ(targetX, targetY, targetZ, moveSpeed);
                } else {
                    living.getNavigator().tryMoveToXYZ(pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5, moveSpeed);
                }
            }
        }
    }

    @FunctionalInterface
    public interface PlayzoneProvider {
        @Nullable
        Playzone getPlayzone(World world);
    }
}

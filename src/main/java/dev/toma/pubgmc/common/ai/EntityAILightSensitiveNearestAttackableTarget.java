package dev.toma.pubgmc.common.ai;

import dev.toma.pubgmc.api.capability.SpecialEquipmentSlot;
import dev.toma.pubgmc.api.entity.EntityDebuffs;
import dev.toma.pubgmc.api.inventory.SpecialInventoryProvider;
import dev.toma.pubgmc.api.item.NightVisionGoggles;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAITarget;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EntitySelectors;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class EntityAILightSensitiveNearestAttackableTarget<E extends EntityLivingBase> extends EntityAITarget {

    protected final Class<E> targetClass;
    protected final EntityAINearestAttackableTarget.Sorter sorter;

    @Nullable
    protected E targetEntity;

    public EntityAILightSensitiveNearestAttackableTarget(EntityCreature creature, Class<E> classTarget, boolean checkSight) {
        super(creature, checkSight);
        this.targetClass = classTarget;
        this.sorter = new EntityAINearestAttackableTarget.Sorter(creature);
        setMutexBits(1);
    }

    @Override
    public boolean shouldExecute() {
        if (taskOwner instanceof EntityDebuffs) {
            if (((EntityDebuffs) taskOwner).isBlind()) {
                return false;
            }
        }
        List<E> list = this.taskOwner.world.getEntitiesWithinAABB(this.targetClass, this.getTargetableArea(this.getTargetDistance()), entity -> {
            if (entity == null)
                return false;
            return shouldAttack(entity);
        });
        if (list.isEmpty()) {
            return false;
        } else {
            list.sort(this.sorter);
            this.targetEntity = list.get(0);
            return true;
        }
    }

    @Override
    public void startExecuting() {
        this.taskOwner.setAttackTarget(this.targetEntity);
        super.startExecuting();
    }

    protected AxisAlignedBB getTargetableArea(double targetDistance) {
        return this.taskOwner.getEntityBoundingBox().grow(targetDistance, targetDistance, targetDistance);
    }

    protected boolean shouldAttack(E targetEntity) {
        return EntitySelectors.NOT_SPECTATING.apply(targetEntity) && isSuitableTarget(targetEntity, false) && isVisible(targetEntity);
    }

    protected boolean isVisible(Entity targetEntity) {
        World world = targetEntity.world;
        BlockPos pos = targetEntity.getPosition();
        long timeOfDay = world.getWorldTime() % 24000L;
        if (timeOfDay >= 23000L || timeOfDay <= 13000L) {
            return true;
        }
        int blockLight = Math.max(world.getLightFor(EnumSkyBlock.BLOCK, pos), 1);
        int fullVisibility = 11;
        if (blockLight >= fullVisibility) {
            return true;
        }
        double lightFactor = blockLight / (double) fullVisibility;
        double extraLight = 0.0D;
        if (taskOwner instanceof SpecialInventoryProvider) {
            SpecialInventoryProvider provider = (SpecialInventoryProvider) taskOwner;
            ItemStack nightVisionStack = provider.getSpecialItemFromSlot(SpecialEquipmentSlot.NIGHT_VISION);
            if (!nightVisionStack.isEmpty() && nightVisionStack.getItem() instanceof NightVisionGoggles) {
                extraLight = ((NightVisionGoggles) nightVisionStack.getItem()).getViewClarityFactor();
            }
        }
        double distance = taskOwner.getDistanceSq(pos);
        double targetDistance = this.getTargetDistance();
        double distanceFactor = Math.max(distance / (targetDistance * targetDistance), 0.125F);
        return lightFactor + extraLight >= distanceFactor;
    }
}

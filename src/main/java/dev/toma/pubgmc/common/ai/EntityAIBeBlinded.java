package dev.toma.pubgmc.common.ai;

import dev.toma.pubgmc.api.entity.EntityDebuffs;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAIBase;

public class EntityAIBeBlinded<E extends EntityLiving & EntityDebuffs> extends EntityAIBase {

    private final E entity;

    public EntityAIBeBlinded(E entity) {
        this.entity = entity;
        setMutexBits(3);
    }

    @Override
    public boolean shouldExecute() {
        return entity.isBlind();
    }

    @Override
    public boolean shouldContinueExecuting() {
        return entity.isBlind();
    }

    @Override
    public void startExecuting() {
        entity.setSneaking(true);
        entity.setSprinting(false);
        entity.getNavigator().clearPath();
    }

    @Override
    public void resetTask() {
        entity.setSneaking(false);
    }

    @Override
    public boolean isInterruptible() {
        return false;
    }
}

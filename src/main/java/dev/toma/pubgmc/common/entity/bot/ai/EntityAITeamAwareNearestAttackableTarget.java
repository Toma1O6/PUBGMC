package dev.toma.pubgmc.common.entity.bot.ai;

import dev.toma.pubgmc.api.game.team.TeamRelations;
import dev.toma.pubgmc.util.helper.GameHelper;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAITarget;
import net.minecraft.util.EntitySelectors;
import net.minecraft.util.math.AxisAlignedBB;

import javax.annotation.Nullable;
import java.util.List;
import java.util.function.Predicate;

public class EntityAITeamAwareNearestAttackableTarget<E extends EntityLivingBase> extends EntityAITarget {

    private final Class<E> targetClass;
    private final EntityAINearestAttackableTarget.Sorter sorter;
    private final Predicate<TeamRelations> teamRelationsPredicate;

    @Nullable
    private E targetEntity;

    public EntityAITeamAwareNearestAttackableTarget(EntityCreature creature, Class<E> classTarget, Predicate<TeamRelations> teamRelationsPredicate, boolean checkSight) {
        super(creature, checkSight);
        this.targetClass = classTarget;
        this.teamRelationsPredicate = teamRelationsPredicate;
        this.sorter = new EntityAINearestAttackableTarget.Sorter(creature);
        setMutexBits(1);
    }

    public EntityAITeamAwareNearestAttackableTarget(EntityCreature creature, Class<E> classTarget, boolean checkSight) {
        this(creature, classTarget, relations -> relations != TeamRelations.FRIENDLY, checkSight);
    }

    @Override
    public boolean shouldExecute() {
        List<E> list = this.taskOwner.world.getEntitiesWithinAABB(this.targetClass, this.getTargetableArea(this.getTargetDistance()), entity -> {
            if (entity == null)
                return false;
            TeamRelations relations = GameHelper.getEntityRelations(taskOwner, entity);
            return teamRelationsPredicate.test(relations) && EntitySelectors.NOT_SPECTATING.apply(entity) && isSuitableTarget(entity, false);
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
}

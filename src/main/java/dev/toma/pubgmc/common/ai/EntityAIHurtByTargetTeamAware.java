package dev.toma.pubgmc.common.ai;

import dev.toma.pubgmc.api.game.team.TeamRelations;
import dev.toma.pubgmc.util.helper.GameHelper;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIHurtByTarget;

import java.util.function.Predicate;

public class EntityAIHurtByTargetTeamAware extends EntityAIHurtByTarget {

    private final Predicate<TeamRelations> relationsPredicate;

    public EntityAIHurtByTargetTeamAware(EntityCreature creatureIn, boolean entityCallsForHelpIn, Class<?>... excludedReinforcementTypes) {
        this(creatureIn, entityCallsForHelpIn, TeamRelations::isDefaultAttackable, excludedReinforcementTypes);
    }

    public EntityAIHurtByTargetTeamAware(EntityCreature creatureIn, boolean entityCallsForHelpIn, Predicate<TeamRelations> relationsPredicate, Class<?>... excludedReinforcementTypes) {
        super(creatureIn, entityCallsForHelpIn, excludedReinforcementTypes);
        this.relationsPredicate = relationsPredicate;
    }

    @Override
    public boolean shouldExecute() {
        EntityLivingBase livingBase = taskOwner.getRevengeTarget();
        TeamRelations relations = GameHelper.getEntityRelations(taskOwner, livingBase);
        return super.shouldExecute() && this.relationsPredicate.test(relations);
    }
}

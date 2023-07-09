package dev.toma.pubgmc.common.ai;

import dev.toma.pubgmc.api.game.team.TeamRelations;
import dev.toma.pubgmc.util.helper.GameHelper;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;

import java.util.function.Predicate;

public class EntityAITeamAwareNearestAttackableTarget<E extends EntityLivingBase> extends EntityAILightSensitiveNearestAttackableTarget<E> {

    private final Predicate<TeamRelations> teamRelationsPredicate;


    public EntityAITeamAwareNearestAttackableTarget(EntityCreature creature, Class<E> classTarget, Predicate<TeamRelations> teamRelationsPredicate, boolean checkSight) {
        super(creature, classTarget, checkSight);
        this.teamRelationsPredicate = teamRelationsPredicate;
    }

    public EntityAITeamAwareNearestAttackableTarget(EntityCreature creature, Class<E> classTarget, boolean checkSight) {
        this(creature, classTarget, relations -> relations != TeamRelations.FRIENDLY, checkSight);
    }

    @Override
    protected boolean shouldAttack(E targetEntity) {
        TeamRelations relations = GameHelper.getEntityRelations(taskOwner, targetEntity);
        return super.shouldAttack(targetEntity) && teamRelationsPredicate.test(relations);
    }
}

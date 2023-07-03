package dev.toma.pubgmc.common.ai;

import dev.toma.pubgmc.api.game.team.TeamRelations;
import dev.toma.pubgmc.util.helper.GameHelper;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAITarget;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

import java.util.Objects;

public class EntityAICallTeamForHelp extends EntityAITarget {

    private int revengeTimerOld;

    public EntityAICallTeamForHelp(EntityCreature creature) {
        super(creature, false);
    }

    @Override
    public boolean shouldExecute() {
        int timer = taskOwner.getRevengeTimer();
        EntityLivingBase revengeTarget = taskOwner.getRevengeTarget();
        if (timer != revengeTimerOld && revengeTarget != null && isSuitableTarget(revengeTarget, false)) {
            TeamRelations relations = GameHelper.getEntityRelations(taskOwner, revengeTarget);
            return relations != TeamRelations.FRIENDLY;
        }
        return false;
    }

    @Override
    public void startExecuting() {
        taskOwner.setAttackTarget(taskOwner.getRevengeTarget());
        this.target = this.taskOwner.getAttackTarget();
        this.revengeTimerOld = this.taskOwner.getRevengeTimer();
        this.unseenMemoryTicks = 300;
        alertTeamMembers(target);
        super.startExecuting();
    }

    private void alertTeamMembers(EntityLivingBase attackTarget) {
        World world = attackTarget.world;
        if (!world.isRemote) {
            WorldServer worldServer = (WorldServer) world;
            GameHelper.getEntityTeam(taskOwner).ifPresent(team -> {
                team.getActiveMemberStream().map(member -> member.getEntity(worldServer))
                        .filter(Objects::nonNull)
                        .forEach(entity -> {
                            if (entity instanceof EntityCreature) {
                                EntityCreature creature = (EntityCreature) entity;
                                EntityLivingBase oldAttackTarget = creature.getAttackTarget();
                                if (oldAttackTarget == null && isSuitableTarget(attackTarget, false)) {
                                    creature.setAttackTarget(attackTarget);
                                }
                            }
                        });
            });
        }
    }
}

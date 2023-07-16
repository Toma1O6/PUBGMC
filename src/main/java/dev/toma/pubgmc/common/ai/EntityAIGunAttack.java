package dev.toma.pubgmc.common.ai;

import dev.toma.pubgmc.api.game.team.TeamRelations;
import dev.toma.pubgmc.common.entity.EntityAIPlayer;
import dev.toma.pubgmc.common.entity.EntityBullet;
import dev.toma.pubgmc.common.items.attachment.AttachmentType;
import dev.toma.pubgmc.common.items.attachment.ItemMuzzle;
import dev.toma.pubgmc.common.items.guns.GunBase;
import dev.toma.pubgmc.network.PacketHandler;
import dev.toma.pubgmc.network.client.PacketDelayedSound;
import dev.toma.pubgmc.util.helper.GameHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityMoveHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EntitySelectors;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;
import java.util.function.Predicate;

public class EntityAIGunAttack extends EntityAIBase {

    private static final int[] EFFECTIVE_RANGE_TABLE = {40, 20, 10, 20, 40, 60, 60};
    private final EntityAIPlayer aiPlayer;
    private final Predicate<TeamRelations> teamRelationsPredicate;
    private int timeWatching;

    private int shootCooldown;
    private int firedTotal;
    private int firedBurst;

    public EntityAIGunAttack(EntityAIPlayer player) {
        this(player, relations -> relations != TeamRelations.FRIENDLY);
    }

    public EntityAIGunAttack(final EntityAIPlayer aiPlayer, Predicate<TeamRelations> teamRelationsPredicate) {
        this.aiPlayer = aiPlayer;
        this.teamRelationsPredicate = teamRelationsPredicate;
        this.setMutexBits(3);
    }

    @Override
    public boolean shouldExecute() {
        if (aiPlayer.hasNoWeapon()) {
            return false;
        }
        EntityLivingBase target = aiPlayer.getAttackTarget();
        return target != null && target.isEntityAlive() && !aiPlayer.hasNoWeapon();
    }

    @Override
    public boolean shouldContinueExecuting() {
        return this.shouldExecute();
    }

    @Override
    public void resetTask() {
        timeWatching = 0;
        shootCooldown = 10;
        firedBurst = 0;
    }

    @Override
    public void updateTask() {
        EntityLivingBase target = aiPlayer.getAttackTarget();
        double distanceToTarget = Math.sqrt(aiPlayer.getDistanceSq(target));
        boolean canSee = aiPlayer.getEntitySenses().canSee(target);
        if (canSee) {
            timeWatching++;
        } else timeWatching = 0;
        if (aiPlayer.hasNoWeapon()) {
            return;
        }
        ItemStack stack = aiPlayer.getHeldItemMainhand();
        if (stack.getItem() instanceof GunBase) {
            GunBase gun = (GunBase) stack.getItem();
            int tableIndex = gun.getGunType().ordinal();
            if (canSee && distanceToTarget <= EFFECTIVE_RANGE_TABLE[tableIndex] * 3.0) {
                aiPlayer.getNavigator().clearPath();
            } else aiPlayer.getNavigator().tryMoveToEntityLiving(target, 1.0D);
            aiPlayer.getLookHelper().setLookPositionWithEntity(target, 30, 30);
            if (timeWatching >= 15 && --shootCooldown <= 0) {
                if (!canSee) {
                    return;
                }
                Vec3d start = aiPlayer.getPositionVector().addVector(0, aiPlayer.getEyeHeight(), 0);
                Vec3d end = start.add(getVectorForRotation(aiPlayer).scale(32.0));
                EntityLivingBase entityInPath = findEntityInPath(start, end);
                TeamRelations relations = entityInPath == null ? TeamRelations.UNKNOWN : GameHelper.getEntityRelations(aiPlayer, entityInPath);
                if (teamRelationsPredicate.test(relations)) {
                    shoot(gun, stack, distanceToTarget);
                } else {
                    float direction = aiPlayer.world.rand.nextBoolean() ? 1.0F : -1.0F;
                    EntityMoveHelper moveHelper = aiPlayer.getMoveHelper();
                    moveHelper.strafe(1.0F, direction);
                    aiPlayer.getLookHelper().setLookPositionWithEntity(target, 30, 30);
                }
            }
        }
    }

    private void shoot(GunBase gun, ItemStack stack, double distanceToTarget) {
        if (distanceToTarget > EFFECTIVE_RANGE_TABLE[gun.getGunType().ordinal()] * 10) {
            return;
        }
        ++firedTotal;
        boolean burstFire = distanceToTarget < EFFECTIVE_RANGE_TABLE[gun.getGunType().ordinal()];
        int shotAmount = gun.getGunType() == GunBase.GunType.SHOTGUN ? 8 : 1;
        ItemMuzzle muzzle = gun.getAttachment(AttachmentType.MUZZLE, stack);
        boolean isSilenced = muzzle != null && muzzle.isSilenced();
        SoundEvent event = isSilenced ? gun.getGunSilencedSound() : gun.getGunSound();
        float volume = isSilenced ? gun.getGunSilencedVolume() : gun.getGunVolume();
        PacketHandler.sendToDimension(new PacketDelayedSound(event, volume, aiPlayer.posX, aiPlayer.posY, aiPlayer.posZ), aiPlayer.dimension);
        for (int i = 0; i < shotAmount; i++) {
            EntityBullet bullet = new EntityBullet(aiPlayer.world, aiPlayer, gun);
            bullet.setPosition(bullet.posX, bullet.posY - 0.5, bullet.posZ);
            if (shotAmount > 1) {
                double d0 = distanceToTarget / 150;
                bullet.motionY += d0;
            }
            aiPlayer.world.spawnEntity(bullet);
        }
        shootCooldown = gun.getFireRate() + (gun.getGunType() == GunBase.GunType.DMR ? 10 : 0) + aiPlayer.getRNG().nextInt(5);
        if (burstFire && ++firedBurst > 3) {
            firedBurst = 0;
            shootCooldown = 15;
        }
        if (firedTotal >= gun.getWeaponAmmoLimit(stack)) {
            firedTotal = 0;
            firedBurst = 0;
            shootCooldown = gun.getReloadTime(stack);
        }
    }

    @Nullable
    private EntityLivingBase findEntityInPath(Vec3d start, Vec3d end) {
        Entity foundEntity = null;
        World world = aiPlayer.world;
        EntityLivingBase attackTarget = aiPlayer.getAttackTarget();
        AxisAlignedBB aabb = getBoundsBetween(aiPlayer.getEntityBoundingBox(), attackTarget.getEntityBoundingBox());
        List<Entity> entityList = world.getEntitiesInAABBexcluding(aiPlayer, aabb, entity -> entity instanceof EntityLivingBase && EntitySelectors.NOT_SPECTATING.apply(entity) && entity.isEntityAlive() && entity.canBeCollidedWith());
        double closestDistance = 0;
        for (Entity entity : entityList) {
            AxisAlignedBB axisalignedbb = entity.getEntityBoundingBox().grow(0.3D);
            RayTraceResult raytraceresult = axisalignedbb.calculateIntercept(start, end);
            if (raytraceresult != null) {
                double distance = start.squareDistanceTo(raytraceresult.hitVec);
                if (distance < closestDistance || closestDistance == 0.0D) {
                    foundEntity = entity;
                    closestDistance = distance;
                }
            }
        }
        return (EntityLivingBase) foundEntity;
    }

    private AxisAlignedBB getBoundsBetween(AxisAlignedBB source, AxisAlignedBB target) {
        double minX = Math.min(source.minX, target.minX);
        double minY = Math.min(source.minY, target.minY);
        double minZ = Math.min(source.minZ, target.minZ);
        double maxX = Math.max(source.maxX, target.maxX);
        double maxY = Math.max(source.maxY, target.maxY);
        double maxZ = Math.max(source.maxZ, target.maxZ);
        return new AxisAlignedBB(minX, minY, minZ, maxX, maxY, maxZ);
    }

    private Vec3d getVectorForRotation(Entity entity) {
        float yaw = entity.getRotationYawHead();
        float pitch = entity.rotationPitch;
        float f = MathHelper.cos(-yaw * 0.017453292F - (float)Math.PI);
        float f1 = MathHelper.sin(-yaw * 0.017453292F - (float)Math.PI);
        float f2 = -MathHelper.cos(-pitch * 0.017453292F);
        float f3 = MathHelper.sin(-pitch * 0.017453292F);
        return new Vec3d(f1 * f2, f3, f * f2);
    }
}

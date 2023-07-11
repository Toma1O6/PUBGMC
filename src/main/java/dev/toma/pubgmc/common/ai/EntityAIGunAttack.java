package dev.toma.pubgmc.common.ai;

import dev.toma.pubgmc.common.entity.EntityAIPlayer;
import dev.toma.pubgmc.common.entity.EntityBullet;
import dev.toma.pubgmc.common.items.attachment.AttachmentType;
import dev.toma.pubgmc.common.items.attachment.ItemMuzzle;
import dev.toma.pubgmc.common.items.guns.GunBase;
import dev.toma.pubgmc.network.PacketHandler;
import dev.toma.pubgmc.network.client.PacketDelayedSound;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundEvent;

public class EntityAIGunAttack extends EntityAIBase {

    private static final int[] EFFECTIVE_RANGE_TABLE = {40, 20, 10, 20, 40, 60, 60};
    private final EntityAIPlayer aiPlayer;
    private int timeWatching;

    private int shootCooldown;
    private int firedTotal;
    private int firedBurst;

    public EntityAIGunAttack(final EntityAIPlayer aiPlayer) {
        this.aiPlayer = aiPlayer;
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
        boolean flag = aiPlayer.getEntitySenses().canSee(target);
        if (flag) {
            timeWatching++;
        } else timeWatching = 0;
        if (aiPlayer.hasNoWeapon()) {
            return;
        }
        ItemStack stack = aiPlayer.getHeldItemMainhand();
        if (stack.getItem() instanceof GunBase) {
            GunBase gun = (GunBase) stack.getItem();
            int tableIndex = gun.getGunType().ordinal();
            if (distanceToTarget <= EFFECTIVE_RANGE_TABLE[tableIndex] * 3.0) {
                aiPlayer.getNavigator().clearPath();
            } else aiPlayer.getNavigator().tryMoveToEntityLiving(target, 1.0D);
            aiPlayer.getLookHelper().setLookPositionWithEntity(target, 30, 30);
            if (timeWatching >= 15 && --shootCooldown <= 0) {
                if (!flag) {
                    return;
                }
                shoot(gun, stack, distanceToTarget);
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
}

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
    private EntityLivingBase target;

    private int shootCooldown;
    private int firedTotal;
    private int firedBurst;

    public EntityAIGunAttack(final EntityAIPlayer aiPlayer) {
        this.aiPlayer = aiPlayer;
        this.setMutexBits(3);
    }

    @Override
    public boolean shouldExecute() {
        if (this.aiPlayer.hasNoWeapon()) {
            return false;
        }
        this.target = this.aiPlayer.getAttackTarget();
        return target != null && target.isEntityAlive();
    }

    @Override
    public boolean shouldContinueExecuting() {
        return this.shouldExecute() || !this.aiPlayer.getNavigator().noPath() || this.aiPlayer.hasNoWeapon();
    }

    @Override
    public void resetTask() {
        timeWatching = 0;
        target = null;
        shootCooldown = 10;
        firedTotal = 0;
        firedBurst = 0;
    }

    @Override
    public void updateTask() {
        if (target == null) return;
        double distanceToTarget = Math.sqrt(this.aiPlayer.getDistanceSq(this.target));
        boolean flag = this.aiPlayer.getEntitySenses().canSee(this.target);
        if (flag) {
            this.timeWatching++;
        } else timeWatching = 0;
        if (this.aiPlayer.hasNoWeapon()) {
            return;
        }
        ItemStack stack = aiPlayer.getHeldItemMainhand();
        if (stack.getItem() instanceof GunBase) {
            GunBase gun = (GunBase) stack.getItem();
            int tableIndex = gun.getGunType().ordinal();
            if (distanceToTarget <= EFFECTIVE_RANGE_TABLE[tableIndex] * 3.0 && this.timeWatching >= 40) {
                this.aiPlayer.getNavigator().clearPath();
            } else this.aiPlayer.getNavigator().tryMoveToEntityLiving(this.target, 1.0D);
            this.aiPlayer.getLookHelper().setLookPositionWithEntity(this.target, 30, 30);
            if (--this.shootCooldown <= 0) {
                if (!flag) {
                    return;
                }
                this.shoot(gun, stack, distanceToTarget);
            }
        }
    }

    private void shoot(GunBase gun, ItemStack stack, double distanceToTarget) {
        if (distanceToTarget > EFFECTIVE_RANGE_TABLE[gun.getGunType().ordinal()] * 10) {
            return;
        }
        ++this.firedTotal;
        boolean burstFire = distanceToTarget < EFFECTIVE_RANGE_TABLE[gun.getGunType().ordinal()];
        int shotAmount = gun.getGunType() == GunBase.GunType.SHOTGUN ? 8 : 1;
        ItemMuzzle muzzle = gun.getAttachment(AttachmentType.MUZZLE, stack);
        boolean isSilenced = muzzle != null && muzzle.isSilenced();
        SoundEvent event = isSilenced ? gun.getGunSilencedSound() : gun.getGunSound();
        float volume = isSilenced ? gun.getGunSilencedVolume() : gun.getGunVolume();
        PacketHandler.sendToDimension(new PacketDelayedSound(event, volume, this.aiPlayer.posX, this.aiPlayer.posY, this.aiPlayer.posZ), this.aiPlayer.dimension);
        for (int i = 0; i < shotAmount; i++) {
            EntityBullet bullet = new EntityBullet(this.aiPlayer.world, this.aiPlayer, gun);
            bullet.setPosition(bullet.posX, bullet.posY - 0.5, bullet.posZ);
            if (shotAmount > 1) {
                double d0 = distanceToTarget / 150;
                bullet.motionY += d0;
            }
            this.aiPlayer.world.spawnEntity(bullet);
        }
        this.shootCooldown = gun.getFireRate() + (gun.getGunType() == GunBase.GunType.DMR ? 10 : 0) + aiPlayer.getRNG().nextInt(5);
        if (burstFire && ++firedBurst > 3) {
            firedBurst = 0;
            this.shootCooldown = 15;
        }
        if (firedTotal >= gun.getWeaponAmmoLimit(stack)) {
            this.firedTotal = 0;
            this.firedBurst = 0;
            this.shootCooldown = gun.getReloadTime(stack);
        }
    }
}

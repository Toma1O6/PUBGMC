package com.toma.pubgmc.common.entity.ai;

import com.toma.pubgmc.common.entity.EntityAIPlayer;
import com.toma.pubgmc.common.entity.EntityBullet;
import com.toma.pubgmc.common.items.guns.GunBase;
import com.toma.pubgmc.network.PacketHandler;
import com.toma.pubgmc.network.sp.PacketDelayedSound;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.util.SoundEvent;

public class EntityAIGunAttack extends EntityAIBase {

    private static final int[] MAX_ATTACK_RANGE_TABLE = {40, 20, 10, 20, 40, 60, 60};
    private final EntityAIPlayer aiPlayer;
    private int timeRemaining;
    private int shotsFired;
    private int timeWatching;

    private EntityLivingBase target;

    public EntityAIGunAttack(final EntityAIPlayer aiPlayer) {
        this.aiPlayer = aiPlayer;
        this.setMutexBits(3);
    }

    @Override
    public boolean shouldExecute() {
        if(!this.aiPlayer.hasGun()) {
            return false;
        }
        this.target = this.aiPlayer.getAttackTarget();
        return target != null;
    }

    @Override
    public boolean shouldContinueExecuting() {
        return this.shouldExecute() || !this.aiPlayer.getNavigator().noPath() || !this.aiPlayer.hasGun();
    }

    @Override
    public void resetTask() {
        this.timeWatching = 0;
        this.timeRemaining = 5;
        this.shotsFired = 0;
        this.target = null;
    }

    @Override
    public void updateTask() {
        if(target == null) return;
        double distanceToTarget = Math.sqrt(this.aiPlayer.getDistanceSq(this.target));
        boolean flag = this.aiPlayer.getEntitySenses().canSee(this.target);
        if(flag) {
            this.timeWatching++;
        } else timeWatching = 0;
        if(!this.aiPlayer.hasGun()) {
            return;
        }
        GunBase gun = this.aiPlayer.getGun();
        int tableIndex = gun.getGunType().ordinal();
        if(distanceToTarget <= MAX_ATTACK_RANGE_TABLE[tableIndex] * 1.5 && this.timeWatching >= 20) {
            this.aiPlayer.getNavigator().clearPath();
        } else this.aiPlayer.getNavigator().tryMoveToEntityLiving(this.target, 1.0D);
        this.aiPlayer.getLookHelper().setLookPositionWithEntity(this.target, 30, 30);
        if(--this.timeRemaining <= 0) {
            if(!flag) {
                return;
            }
            this.shoot(gun, distanceToTarget);
        }
    }

    private void shoot(GunBase gun, double distanceToTarget) {
        if(distanceToTarget > MAX_ATTACK_RANGE_TABLE[gun.getGunType().ordinal()] * 10) {
            return;
        }
        ++this.shotsFired;
        int shotAmount = gun.getGunType() == GunBase.GunType.SHOTGUN ? 8 : 1;
        boolean isSilenced = this.aiPlayer.getHeldItemMainhand().hasTagCompound() && this.aiPlayer.getHeldItemMainhand().getTagCompound().getInteger("barrel") == 1;
        SoundEvent event = isSilenced ? gun.getGunSilencedSound() : gun.getGunSound();
        float volume = isSilenced ? gun.getGunSilencedVolume() : gun.getGunVolume();
        PacketHandler.sendToDimension(new PacketDelayedSound(event, volume, this.aiPlayer.posX, this.aiPlayer.posY, this.aiPlayer.posZ), this.aiPlayer.dimension);
        for(int i = 0; i < shotAmount; i++) {
            EntityBullet bullet = new EntityBullet(this.aiPlayer.world, this.aiPlayer, gun);
            bullet.setPosition(bullet.posX, bullet.posY - 0.5, bullet.posZ);
            if(shotAmount > 1) {
                double d0 = distanceToTarget / 150;
                bullet.motionY += d0;
            }
            this.aiPlayer.world.spawnEntity(bullet);
        }
        boolean effectiveRange = distanceToTarget < MAX_ATTACK_RANGE_TABLE[gun.getGunType().ordinal()];
        this.timeRemaining = gun.getFireRate() + (effectiveRange ? 6 : 18);
        if(shotsFired >= gun.getWeaponAmmoLimit(this.aiPlayer.getHeldItemMainhand())) {
            this.shotsFired = 0;
            this.timeRemaining = 80;
        }
    }
}

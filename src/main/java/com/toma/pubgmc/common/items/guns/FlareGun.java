package com.toma.pubgmc.common.items.guns;

import com.toma.pubgmc.common.capability.IPlayerData;
import com.toma.pubgmc.common.capability.IPlayerData.PlayerDataProvider;
import com.toma.pubgmc.common.entity.EntityFlare;
import com.toma.pubgmc.config.common.CFGWeapon;
import com.toma.pubgmc.init.PMCSounds;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.CooldownTracker;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

public class FlareGun extends GunBase {
    public FlareGun(String name) {
        super(name);
        this.setStats(new CFGWeapon(0f, 0f, 0f, 0));
        this.setFiremode(Firemode.SINGLE);
        this.setReloadType(ReloadType.MAGAZINE);
        this.setAmmoType(AmmoType.FLARE);
        this.setGunType(GunType.PISTOL);
        this.setReloadTime(70);
        this.setFireRate(110);
        this.setVerticalRecoil(1);
        this.setHorizontalRecoil(0);

        this.setGunSound(PMCSounds.gun_flare);
        this.setGunSoundVolume(25f);
    }

    @Override
    public int getWeaponAmmoLimit(ItemStack stack) {
        return 1;
    }

    @Override
    public void shoot(World world, EntityPlayer player, ItemStack stack) {
        IPlayerData data = player.getCapability(PlayerDataProvider.PLAYER_DATA, null);
        CooldownTracker tr = player.getCooldownTracker();
        if (this.hasAmmo(stack) || player.capabilities.isCreativeMode && !data.isReloading() && !tr.hasCooldown(this)) {
            world.playSound(null, player.posX, player.posY, player.posZ, this.getGunSound(), SoundCategory.PLAYERS, this.getGunVolume(), 1.0f);

            if (!world.isRemote) {
                EntityFlare bullet = new EntityFlare(world, player);
                world.spawnEntity(bullet);
                if (!player.capabilities.isCreativeMode) {
                    stack.getTagCompound().setInteger("ammo", stack.getTagCompound().getInteger("ammo") - 1);
                }
            }

            tr.setCooldown(this, getFireRate());
        }
    }

    @Override
    public SoundEvent getWeaponReloadSound() {
        return PMCSounds.reload_flare;
    }
}

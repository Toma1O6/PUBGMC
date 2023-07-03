package dev.toma.pubgmc.common.items.guns;

import dev.toma.pubgmc.api.capability.IPlayerData;
import dev.toma.pubgmc.api.capability.PlayerDataProvider;
import dev.toma.pubgmc.common.entity.EntityFlare;
import dev.toma.pubgmc.init.PMCSounds;
import dev.toma.pubgmc.util.helper.GameHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

public class FlareGun extends GunBase {

    public FlareGun(GunBuilder builder) {
        super(builder);
    }

    @Override
    public int getWeaponAmmoLimit(ItemStack stack) {
        return 1;
    }

    @Override
    public void shoot(World world, EntityPlayer player, ItemStack stack) {
        IPlayerData data = player.getCapability(PlayerDataProvider.PLAYER_DATA, null);
        if (this.hasAmmo(stack) || player.capabilities.isCreativeMode && !data.getReloadInfo().isReloading()) {
            world.playSound(null, player.posX, player.posY, player.posZ, this.getGunSound(), SoundCategory.PLAYERS, this.getGunVolume(), 1.0f);
            if (!world.isRemote) {
                EntityFlare bullet = new EntityFlare(world, player);
                bullet.assignGameId(GameHelper.getGameUUID(world));
                world.spawnEntity(bullet);
                if (!player.capabilities.isCreativeMode) {
                    stack.getTagCompound().setInteger("ammo", stack.getTagCompound().getInteger("ammo") - 1);
                }
            }
        }
    }

    @Override
    public SoundEvent getWeaponReloadSound() {
        return PMCSounds.reload_flare;
    }

    @Override
    public float getVerticalRecoil() {
        return 0.6F;
    }

    @Override
    public float getHorizontalRecoil() {
        return 0.6F;
    }
}

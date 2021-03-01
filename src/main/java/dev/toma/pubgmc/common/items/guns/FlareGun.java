package dev.toma.pubgmc.common.items.guns;

import dev.toma.pubgmc.common.capability.player.IPlayerData;
import dev.toma.pubgmc.common.capability.player.PlayerDataProvider;
import dev.toma.pubgmc.common.entity.EntityFlare;
import dev.toma.pubgmc.config.common.CFGWeapon;
import dev.toma.pubgmc.init.PMCSounds;
import dev.toma.pubgmc.util.game.loot.LootManager;
import dev.toma.pubgmc.util.game.loot.LootType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.CooldownTracker;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

public class FlareGun extends GunBase {

    public FlareGun(GunBuilder builder) {
        super(builder);
    }

    @Override
    public void registerToGlobalLootPool(boolean airdropOnly) {
        LootManager.register(LootType.GUN, new LootManager.LootEntry(this, 1, false));
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

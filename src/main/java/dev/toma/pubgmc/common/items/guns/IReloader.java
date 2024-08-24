package dev.toma.pubgmc.common.items.guns;

import dev.toma.pubgmc.DevUtil;
import dev.toma.pubgmc.Pubgmc;
import dev.toma.pubgmc.api.game.mutator.GameMutatorHelper;
import dev.toma.pubgmc.api.game.mutator.GameMutators;
import dev.toma.pubgmc.client.animation.AnimationLoader;
import dev.toma.pubgmc.common.items.attachment.AttachmentType;
import dev.toma.pubgmc.common.items.attachment.ItemMagazine;
import dev.toma.pubgmc.common.items.attachment.ItemStock;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IntHashMap;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public interface IReloader {

    IReloader MAGAZINE = new Magazine();
    IReloader SINGLE = new SingleBullet();

    static IReloader magazine() {
        return MAGAZINE;
    }

    static IReloader single() {
        return SINGLE;
    }

    static IReloader stripperClip(int stripperClipReloadTime) {
        return new StripperClip(stripperClipReloadTime);
    }

    @SideOnly(Side.CLIENT)
    IntHashMap<ResourceLocation> registerReloadAnimations(GunBase gun, String prefix, AnimationLoader loader);

    boolean finishCycle(GunBase gun, ItemStack stack, EntityPlayer player);

    boolean canInterrupt(GunBase gun, ItemStack stack);

    default int getReloadTime(GunBase gun, ItemStack stack) {
        return gun.getReloadTime(stack);
    }

    default int getReloadAnimationTime(GunBase gun, ItemStack stack, EntityPlayer player) {
        return getReloadTime(gun, stack);
    }

    default boolean canReload(EntityPlayer player, GunBase gun, ItemStack stack) {
        int count = gun.getAmmo(stack);
        int max = gun.getWeaponAmmoLimit(stack);
        if (count >= max)
            return false;
        AmmoType type = gun.getAmmoType();
        Item item = type.ammo();
        if (isFreeReload(player)) {
            return true;
        }
        for (int i = 0; i < player.inventory.getSizeInventory(); i++) {
            ItemStack itemStack = player.inventory.getStackInSlot(i);
            if (itemStack.getItem() == item && itemStack.getCount() > 0) {
                return true;
            }
        }
        return false;
    }

    static boolean isFreeReload(EntityPlayer player) {
        return player.isCreative() || GameMutatorHelper.getMutator(player.world, GameMutators.FREE_AMMO).isPresent();
    }

    class Magazine implements IReloader {

        @Override
        public boolean finishCycle(GunBase gun, ItemStack stack, EntityPlayer player) {
            int actual = gun.getAmmo(stack);
            int max = gun.getWeaponAmmoLimit(stack);
            int left = max - actual;
            AmmoType type = gun.getAmmoType();
            Item target = type.ammo();
            if (isFreeReload(player)) {
                gun.setAmmo(stack, max);
            } else {
                for (int i = 0; i < player.inventory.getSizeInventory(); i++) {
                    ItemStack itemStack = player.inventory.getStackInSlot(i);
                    if (!itemStack.isEmpty() && itemStack.getItem() == target) {
                        int count = Math.min(left, itemStack.getCount());
                        left -= count;
                        itemStack.shrink(count);
                        if (left <= 0)
                            break;
                    }
                }
                gun.setAmmo(stack, max - left);
            }

            return true;
        }

        @Override
        public boolean canInterrupt(GunBase gun, ItemStack stack) {
            return false;
        }

        @SideOnly(Side.CLIENT)
        @Override
        public IntHashMap<ResourceLocation> registerReloadAnimations(GunBase gun, String prefix, AnimationLoader loader) {
            IntHashMap<ResourceLocation> map = new IntHashMap<>();
            int maxAmmo = gun.getMaxAmmoExtended();
            if (maxAmmo > 1) {
                ResourceLocation reload = Pubgmc.getResource(prefix + "_reload");
                loader.registerEntry(reload);
                map.addKey(1, reload);
            }
            ResourceLocation reloadEmpty = Pubgmc.getResource(prefix + "_reload_empty");
            loader.registerEntry(reloadEmpty);
            map.addKey(0, reloadEmpty);
            return map;
        }
    }

    class SingleBullet implements IReloader {

        @Override
        public boolean finishCycle(GunBase gun, ItemStack stack, EntityPlayer player) {
            int actual = gun.getAmmo(stack);
            int max = gun.getWeaponAmmoLimit(stack);
            int left = max - actual;
            AmmoType type = gun.getAmmoType();
            Item target = type.ammo();
            boolean freeAmmo = isFreeReload(player);
            for (int i = 0; i < player.inventory.getSizeInventory(); i++) {
                ItemStack itemStack = player.inventory.getStackInSlot(i);
                if (freeAmmo || (!itemStack.isEmpty() && itemStack.getItem() == target)) {
                    --left;
                    if (!freeAmmo)
                        itemStack.shrink(1);
                    gun.setAmmo(stack, actual + 1);
                    boolean b = left <= 0;
                    if (!b) {
                        player.world.playSound(null, player.posX, player.posY + 1, player.posZ, gun.getWeaponReloadSound(), SoundCategory.MASTER, 1.0F, 1.0F);
                    }
                    return b;
                }
            }
            return true;
        }

        @Override
        public boolean canInterrupt(GunBase gun, ItemStack stack) {
            return true;
        }

        @Override
        public int getReloadAnimationTime(GunBase gun, ItemStack stack, EntityPlayer player) {
            int ammoInInventory = DevUtil.getItemCount(gun.getAmmoType().ammo(), player.inventory);
            int left = Math.min(ammoInInventory, gun.getWeaponAmmoLimit(stack) - gun.getAmmo(stack));
            int reloadTime = getReloadTime(gun, stack);
            return left * reloadTime;
        }

        @SideOnly(Side.CLIENT)
        @Override
        public IntHashMap<ResourceLocation> registerReloadAnimations(GunBase gun, String prefix, AnimationLoader loader) {
            IntHashMap<ResourceLocation> map = new IntHashMap<>();
            int maxAmmo = gun.getMaxAmmoExtended();
            for (int i = 0; i < maxAmmo; i++) {
                ResourceLocation location = Pubgmc.getResource(prefix + "_reload_" + i);
                loader.registerEntry(location);
                map.addKey(i, location);
            }
            return map;
        }
    }

    class StripperClip implements IReloader {

        final int stripperClipTime;

        public StripperClip(int stripperClipTime) {
            this.stripperClipTime = stripperClipTime;
        }

        @Override
        public boolean finishCycle(GunBase gun, ItemStack stack, EntityPlayer player) {
            int actual = gun.getAmmo(stack);
            return actual == 0 ? MAGAZINE.finishCycle(gun, stack, player) : SINGLE.finishCycle(gun, stack, player);
        }

        @Override
        public boolean canInterrupt(GunBase gun, ItemStack stack) {
            return gun.getAmmo(stack) > 0;
        }

        @Override
        public int getReloadTime(GunBase gun, ItemStack stack) {
            ItemMagazine mag = gun.getAttachment(AttachmentType.MAGAZINE, stack);
            ItemStock stock = gun.getAttachment(AttachmentType.STOCK, stack);
            boolean quickdraw = mag != null && mag.isQuickdraw() || stock != null && stock.isFasterReload();
            int count = gun.getAmmo(stack);
            return count == 0 ? (quickdraw ? (int) (stripperClipTime * 0.7) : stripperClipTime) : gun.getReloadTime(quickdraw);
        }

        @Override
        public int getReloadAnimationTime(GunBase gun, ItemStack stack, EntityPlayer player) {
            return gun.getAmmo(stack) == 0 ? stripperClipTime : SINGLE.getReloadAnimationTime(gun, stack, player);
        }

        @SideOnly(Side.CLIENT)
        @Override
        public IntHashMap<ResourceLocation> registerReloadAnimations(GunBase gun, String prefix, AnimationLoader loader) {
            IntHashMap<ResourceLocation> map = new IntHashMap<>();
            int maxAmmo = gun.getMaxAmmoExtended();
            for (int i = 0; i < maxAmmo; i++) {
                ResourceLocation location = Pubgmc.getResource(prefix + "_reload_" + i);
                loader.registerEntry(location);
                map.addKey(i, location);
            }
            return map;
        }
    }
}

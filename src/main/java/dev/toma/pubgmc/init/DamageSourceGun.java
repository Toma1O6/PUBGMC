package dev.toma.pubgmc.init;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EntityDamageSourceIndirect;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;

import javax.annotation.Nullable;

public class DamageSourceGun extends EntityDamageSourceIndirect {

    private final ItemStack weapon;
    private final boolean headshot;

    public DamageSourceGun(Entity shooter, Entity bullet, ItemStack weapon, boolean headshot) {
        super("gun", bullet, shooter);
        this.weapon = weapon;
        this.headshot = headshot;
    }

    public ItemStack getWeapon() {
        return weapon;
    }

    public boolean wasHeadshot() {
        return headshot;
    }

    @Override
    public ITextComponent getDeathMessage(EntityLivingBase victim) {
        String label = "death.attack." + getDamageType();
        Entity trueSource = getTrueSource();
        if (trueSource == null) {
            return new TextComponentTranslation(label + ".generic", victim.getName());
        }
        if (headshot) {
            return new TextComponentTranslation(label + ".headshot", trueSource.getName(), victim.getName(), weapon.getDisplayName());
        }
        return new TextComponentTranslation(label, trueSource.getName(), victim.getName(), weapon.getDisplayName());
    }

    @Nullable
    @Override
    public Vec3d getDamageLocation() {
        Entity shooter = getTrueSource();
        return shooter != null ? new Vec3d(shooter.posX, shooter.posY, shooter.posZ) : null;
    }
}

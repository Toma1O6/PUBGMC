package dev.toma.pubgmc.init;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EntityDamageSourceIndirect;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;

public class DamageSourceGun extends EntityDamageSourceIndirect {

    private final ItemStack weapon;
    private final boolean headshot;

    public DamageSourceGun(Entity shooter, Entity bullet, ItemStack weapon, boolean headshot) {
        super("gun", bullet, shooter);
        this.weapon = weapon;
        this.headshot = headshot;
    }

    @Override
    public ITextComponent getDeathMessage(EntityLivingBase victim) {
        String label = "death.attack." + getDamageType();
        if (damageSourceEntity == null) {
            return new TextComponentTranslation(label + ".generic", victim.getName());
        }
        if (headshot) {
            return new TextComponentTranslation(label + ".headshot", damageSourceEntity.getName(), victim.getName(), weapon.getDisplayName());
        }
        return new TextComponentTranslation(label, damageSourceEntity.getName(), victim.getName(), weapon.getDisplayName());
    }
}

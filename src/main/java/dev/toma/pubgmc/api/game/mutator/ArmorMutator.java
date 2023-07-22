package dev.toma.pubgmc.api.game.mutator;

import dev.toma.pubgmc.api.item.BulletproofArmor;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;

public class ArmorMutator implements GameMutator {

    public static final ArmorMutator DEFAULT = new ArmorMutator(BulletproofArmor::getItemDamageMultiplier, BulletproofArmor::getDamageMultiplier);
    public static final ArmorMutator NO_DAMAGE = new ArmorMutator((armor, area, stack, hitEntity) -> 0.0F, BulletproofArmor::getDamageMultiplier);

    private final MultiplierProvider armorDestructionMultiplier;
    private final MultiplierProvider damageMultiplier;

    public ArmorMutator(MultiplierProvider armorDestructionMultiplier, MultiplierProvider damageMultiplier) {
        this.armorDestructionMultiplier = armorDestructionMultiplier;
        this.damageMultiplier = damageMultiplier;
    }

    public float getDestructionMultiplier(BulletproofArmor armor, BulletproofArmor.DamageArea area, ItemStack stack, EntityLivingBase hitEntity) {
        return armorDestructionMultiplier.getMultiplier(armor, area, stack, hitEntity);
    }

    public float getDamageMultiplier(BulletproofArmor armor, BulletproofArmor.DamageArea area, ItemStack stack, EntityLivingBase hitEntity) {
        return damageMultiplier.getMultiplier(armor, area, stack, hitEntity);
    }

    @FunctionalInterface
    public interface MultiplierProvider {
        float getMultiplier(BulletproofArmor armor, BulletproofArmor.DamageArea area, ItemStack stack, EntityLivingBase hitEntity);
    }
}

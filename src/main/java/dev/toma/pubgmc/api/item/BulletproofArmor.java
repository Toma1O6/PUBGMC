package dev.toma.pubgmc.api.item;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public interface BulletproofArmor {

    float getDamageMultiplier(DamageArea affectedArea, ItemStack stack, EntityLivingBase hitEntity);

    ResourceLocation getArmorIcon(DamageArea area, float durabilityPercentage);

    default float getItemDamageMultiplier(DamageArea area, ItemStack stack, EntityLivingBase hitEntity) {
        return 0.8F;
    }

    enum DamageArea {

        HEAD,
        OTHER;

        public boolean isHead() {
            return this == HEAD;
        }
    }
}

package dev.toma.pubgmc.api.item;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public interface BulletproofArmor {

    float getDamageMultiplier(ProtectionArea affectedArea, ItemStack stack, EntityLivingBase hitEntity);

    ResourceLocation getArmorIcon(ProtectionArea area, float durabilityPercentage);

    default float getItemDamageMultiplier(ProtectionArea area, ItemStack stack, EntityLivingBase hitEntity) {
        return 0.8F;
    }

    enum ProtectionArea {

        HEAD,
        OTHER;

        public boolean isHead() {
            return this == HEAD;
        }
    }
}

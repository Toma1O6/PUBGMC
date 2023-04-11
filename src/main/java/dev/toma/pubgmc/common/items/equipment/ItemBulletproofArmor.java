package dev.toma.pubgmc.common.items.equipment;

import dev.toma.pubgmc.PMCTabs;
import dev.toma.pubgmc.Pubgmc;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class ItemBulletproofArmor extends ItemArmor implements BulletproofArmor {

    private final ArmorLevel armorLevel;

    public ItemBulletproofArmor(String name, ArmorMaterial materialIn, int renderIndexIn, EntityEquipmentSlot equipmentSlotIn, ArmorLevel armorLevel) {
        super(materialIn, renderIndexIn, equipmentSlotIn);
        setUnlocalizedName(name);
        setRegistryName(name);
        setCreativeTab(PMCTabs.TAB_ITEMS);
        this.armorLevel = armorLevel;
    }

    @Override
    public float getDamageMultiplier(ProtectionArea affectedArea, ItemStack stack, EntityLivingBase hitEntity) {
        return armorLevel.getProtectionMultiplier(affectedArea);
    }

    @Override
    public ResourceLocation getArmorIcon(ProtectionArea area, float durabilityPercentage) {
        return armorLevel.getIcon(area);
    }

    public enum ArmorLevel {

        LEVEL_ONE(0.7F, 0.7F),
        LEVEL_TWO(0.6F, 0.6F),
        LEVEL_THREE(0.4F, 0.5F);

        private final float headProtection;
        private final float bodyProtection;
        private final ResourceLocation[] icons;

        ArmorLevel(float headProtection, float bodyProtection) {
            this.headProtection = headProtection;
            this.bodyProtection = bodyProtection;
            int level = ordinal() + 1;
            this.icons = new ResourceLocation[] {
                    Pubgmc.getResource("textures/overlay/helmet_level" + level + ".png"),
                    Pubgmc.getResource("textures/overlay/vest_level" + level + ".png")
            };
        }

        public float getProtectionMultiplier(ProtectionArea area) {
            return area.isHead() ? headProtection : bodyProtection;
        }

        public ResourceLocation getIcon(ProtectionArea area) {
            return icons[area.ordinal()];
        }
    }
}

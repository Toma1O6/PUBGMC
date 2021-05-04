package dev.toma.pubgmc.common.items.armor;

import dev.toma.pubgmc.PMCTabs;
import dev.toma.pubgmc.Pubgmc;
import dev.toma.pubgmc.util.game.loot.LootManager;
import dev.toma.pubgmc.util.game.loot.LootType;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import dev.toma.pubgmc.api.common.IBulletproofArmor;

public class ItemBulletProofArmor extends ItemArmor implements IBulletproofArmor {

    private final ResourceLocation texture;
    private final ArmorLevel level;

    public ItemBulletProofArmor(String name, ArmorMaterial materialIn, int renderIndexIn, EntityEquipmentSlot equipmentSlotIn, ArmorLevel level) {
        super(materialIn, renderIndexIn, equipmentSlotIn);
        this.texture = Pubgmc.getResource("textures/overlay/" + name + ".png");
        this.level = level;
        setUnlocalizedName(name);
        setRegistryName(name);
        setCreativeTab(PMCTabs.TAB_ITEMS);
        LootManager.register(LootType.ARMOR, new LootManager.LootEntry(this, (3 - level.ordinal()) * 30, false));
    }

    @Override
    public float getDamageMultiplier() {
        return level.damageMultiplier;
    }

    @Override
    public ResourceLocation getTexture(ItemStack stack) {
        return texture;
    }

    @Override
    public int getColorByDamage(float durability) {
        return 0xFF << 16 | (int)((0xFF << 8) * durability) | (int)(0xFF * durability);
    }

    public enum ArmorLevel {

        LEVEL_ONE(0.3F),
        LEVEL_TWO(0.4F),
        LEVEL_THREE(0.6F);

        private final float damageMultiplier;

        ArmorLevel(float damageMultiplier) {
            this.damageMultiplier = damageMultiplier;
        }
    }
}

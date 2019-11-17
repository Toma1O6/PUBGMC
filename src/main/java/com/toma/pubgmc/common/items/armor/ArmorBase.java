package com.toma.pubgmc.common.items.armor;

import com.toma.pubgmc.PMCTabs;
import com.toma.pubgmc.Pubgmc;
import com.toma.pubgmc.util.game.loot.LootManager;
import com.toma.pubgmc.util.game.loot.LootType;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.util.ResourceLocation;

public class ArmorBase extends ItemArmor {
    private ArmorLevel level;

    public ArmorBase(String name, ArmorMaterial materialIn, int renderIndexIn, EntityEquipmentSlot equipmentSlotIn, ArmorLevel armorLevel) {
        super(materialIn, renderIndexIn, equipmentSlotIn);
        setUnlocalizedName(name);
        setRegistryName(name);
        setCreativeTab(PMCTabs.TAB_ITEMS);
        LootManager.register(LootType.ARMOR, new LootManager.LootEntry(this, (3 - armorLevel.ordinal()) * 30, false));
        this.level = armorLevel;
    }

    public ArmorLevel armorLevel() {
        return level;
    }

    public enum ArmorLevel {
        LEVEL_ONE(0),
        LEVEL_TWO(1),
        LEVEL_THREE(2);

        static final ResourceLocation[][] ICONS =
                {
                        {
                                new ResourceLocation(Pubgmc.MOD_ID + ":textures/overlay/vest1_full.png"),
                                new ResourceLocation(Pubgmc.MOD_ID + ":textures/overlay/vest1_damaged.png"),
                                new ResourceLocation(Pubgmc.MOD_ID + ":textures/overlay/vest1_broken.png"),
                                new ResourceLocation(Pubgmc.MOD_ID + ":textures/overlay/helmet1_full.png"),
                                new ResourceLocation(Pubgmc.MOD_ID + ":textures/overlay/helmet1_damaged.png"),
                                new ResourceLocation(Pubgmc.MOD_ID + ":textures/overlay/helmet1_broken.png")
                        },

                        {
                                new ResourceLocation(Pubgmc.MOD_ID + ":textures/overlay/vest2_full.png"),
                                new ResourceLocation(Pubgmc.MOD_ID + ":textures/overlay/vest2_damaged.png"),
                                new ResourceLocation(Pubgmc.MOD_ID + ":textures/overlay/vest2_broken.png"),
                                new ResourceLocation(Pubgmc.MOD_ID + ":textures/overlay/helmet2_full.png"),
                                new ResourceLocation(Pubgmc.MOD_ID + ":textures/overlay/helmet2_damaged.png"),
                                new ResourceLocation(Pubgmc.MOD_ID + ":textures/overlay/helmet2_broken.png")
                        },

                        {
                                new ResourceLocation(Pubgmc.MOD_ID + ":textures/overlay/vest3_full.png"),
                                new ResourceLocation(Pubgmc.MOD_ID + ":textures/overlay/vest3_damaged.png"),
                                new ResourceLocation(Pubgmc.MOD_ID + ":textures/overlay/vest3_broken.png"),
                                new ResourceLocation(Pubgmc.MOD_ID + ":textures/overlay/helmet3_full.png"),
                                new ResourceLocation(Pubgmc.MOD_ID + ":textures/overlay/helmet3_damaged.png"),
                                new ResourceLocation(Pubgmc.MOD_ID + ":textures/overlay/helmet3_broken.png")
                        }
                };
        int level;

        ArmorLevel(int level) {
            this.level = level;
        }

        public ResourceLocation[][] getIcons() {
            return ICONS;
        }

        /**
         * @param helmet
         * @param armorLevel  [0-2]
         * @param damagePhase [0-2] (full_hp, damaged, broken)
         * @return icon
         */
        public ResourceLocation getIcon(boolean helmet, int armorLevel, int damagePhase) {
            int gear = helmet ? 3 : 0;
            return ICONS[armorLevel][gear + damagePhase];
        }

        public int getArmorLevel() {
            return level;
        }
    }
}

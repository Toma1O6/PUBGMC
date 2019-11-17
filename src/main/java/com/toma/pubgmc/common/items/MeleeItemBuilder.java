package com.toma.pubgmc.common.items;

import com.toma.pubgmc.PMCTabs;
import com.toma.pubgmc.util.IBuilder;
import com.toma.pubgmc.util.game.loot.LootManager;
import com.toma.pubgmc.util.game.loot.LootType;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemSword;
import net.minecraftforge.common.util.EnumHelper;

public class MeleeItemBuilder implements IBuilder<ItemSword> {
    private ToolMaterial material;
    private String registryName, materialName;
    private float damage;

    private MeleeItemBuilder() {
    }

    public static MeleeItemBuilder create(String name) {
        MeleeItemBuilder builder = new MeleeItemBuilder();
        builder.registryName = name;
        return builder;
    }

    public MeleeItemBuilder materialName(String name) {
        this.materialName = name;
        return this;
    }

    public MeleeItemBuilder damage(float damage) {
        this.damage = damage;
        return this;
    }

    @Override
    public ItemSword build() {
        checkNotNull(materialName);
        checkNotNull(registryName);
        checkBoolean(materialName.isEmpty(), false);
        checkBoolean(registryName.isEmpty(), false);
        checkFloat(damage, 1.0F, 30.0F);
        material = EnumHelper.addToolMaterial(materialName, 0, -1, 0.0F, damage, 0);
        checkNotNull(material);
        ItemSword sword = new ItemSword(material);
        sword.setUnlocalizedName(registryName).setRegistryName(registryName).setMaxStackSize(1).setCreativeTab(PMCTabs.TAB_ITEMS);
        LootManager.register(LootType.AMMO, new LootManager.LootEntry(sword, 10, false));
        return sword;
    }
}

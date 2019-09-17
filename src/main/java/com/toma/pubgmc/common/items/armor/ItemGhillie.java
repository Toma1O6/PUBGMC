package com.toma.pubgmc.common.items.armor;

import com.toma.pubgmc.Pubgmc;
import com.toma.pubgmc.init.PMCRegistry;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

import java.util.List;

public class ItemGhillie extends ItemArmor {

    private static final ResourceLocation texture = new ResourceLocation(Pubgmc.MOD_ID + ":textures/empty_texture.png");

    public ItemGhillie(String name) {
        super(PMCRegistry.ToolMaterials.GHILLIE_SUIT, 1, EntityEquipmentSlot.LEGS);
        setUnlocalizedName(name);
        setRegistryName(name);
        this.setMaxStackSize(1);
        this.setCreativeTab(Pubgmc.TAB_ITEMS);
    }

    @Override
    public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add(TextFormatting.GREEN + "Use for leg slot for ghillie suit");
    }

    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, EntityEquipmentSlot slot, String type) {
        return texture.toString();
    }
}

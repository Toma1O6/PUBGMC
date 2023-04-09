package dev.toma.pubgmc.common.items.armor;

import dev.toma.pubgmc.PMCTabs;
import dev.toma.pubgmc.Pubgmc;
import dev.toma.pubgmc.init.ToolMaterials;
import dev.toma.pubgmc.util.game.loot.LootManager;
import dev.toma.pubgmc.util.game.loot.LootType;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

import java.util.List;

public class ItemGhillie extends ItemArmor {

    private static final ResourceLocation texture = new ResourceLocation(Pubgmc.MOD_ID + ":textures/empty_texture.png");
    public static final int DEFAULT_COLOR = 0x52D900;

    public ItemGhillie(String name) {
        super(ToolMaterials.GHILLIE_SUIT, 1, EntityEquipmentSlot.LEGS);
        setUnlocalizedName(name);
        setRegistryName(name);
        this.setMaxStackSize(1);
        this.setCreativeTab(PMCTabs.TAB_ITEMS);
        LootManager.register(LootType.ARMOR, new LootManager.LootEntry(this, 25, true));
    }

    @Override
    public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add(TextFormatting.GREEN + "Use the leg slot for ghillie suit");
    }

    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, EntityEquipmentSlot slot, String type) {
        return texture.toString();
    }

    public static void setFoliageColor(ItemStack stack, int color) {
        NBTTagCompound compound = stack.getTagCompound();
        if (compound == null) {
            compound = new NBTTagCompound();
            stack.setTagCompound(compound);
        }
        compound.setInteger("ghillieColor", color);
    }
}

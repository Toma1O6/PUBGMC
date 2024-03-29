package dev.toma.pubgmc.common.items.equipment;

import dev.toma.pubgmc.Pubgmc;
import dev.toma.pubgmc.api.capability.SpecialEquipmentSlot;
import dev.toma.pubgmc.api.item.Backpack;
import dev.toma.pubgmc.common.items.PMCItem;
import dev.toma.pubgmc.util.PUBGMCUtil;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import java.util.Locale;

public final class ItemBackpack extends PMCItem implements Backpack {

    private static final ResourceLocation[] ICONS = {
            Pubgmc.getResource("textures/overlay/backpack1.png"),
            Pubgmc.getResource("textures/overlay/backpack2.png"),
            Pubgmc.getResource("textures/overlay/backpack3.png")
    };
    private final Variant variant;
    private final int backpackLevel;

    public ItemBackpack(String name, int backpackLevel, Variant variant) {
        super(name);
        this.backpackLevel = backpackLevel;
        this.variant = variant;
        this.setMaxStackSize(1);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        ItemStack stack = playerIn.getHeldItem(handIn);
        if (!worldIn.isRemote && PUBGMCUtil.tryQuickEquip(playerIn, SpecialEquipmentSlot.BACKPACK, stack)) {
            return ActionResult.newResult(EnumActionResult.SUCCESS, stack);
        }
        return ActionResult.newResult(EnumActionResult.PASS, stack);
    }

    @Override
    public int unlockSlotCount() {
        return backpackLevel * 9;
    }

    @Override
    public ResourceLocation getHotbarIconPath() {
        return ICONS[backpackLevel - 1];
    }

    public Variant getVariant() {
        return variant;
    }

    public enum Variant {

        FOREST,
        DESERT,
        SNOW;

        private final ResourceLocation texture;

        Variant() {
            this.texture = Pubgmc.getResource("textures/models/backpack_" + name().toLowerCase(Locale.ROOT) + ".png");
        }

        public ResourceLocation getTexture() {
            return texture;
        }
    }
}

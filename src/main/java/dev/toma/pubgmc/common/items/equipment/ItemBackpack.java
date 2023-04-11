package dev.toma.pubgmc.common.items.equipment;

import dev.toma.pubgmc.Pubgmc;
import dev.toma.pubgmc.common.capability.player.SpecialEquipmentSlot;
import dev.toma.pubgmc.common.items.PMCItem;
import dev.toma.pubgmc.util.PUBGMCUtil;
import dev.toma.pubgmc.util.game.loot.LootManager;
import dev.toma.pubgmc.util.game.loot.LootType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

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
        LootManager.register(LootType.ARMOR, new LootManager.LootEntry(this, 10, false));
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
            this.texture = Pubgmc.getResource("textures/models/backpack_" + name().toLowerCase() + ".png");
        }

        public ResourceLocation getTexture() {
            return texture;
        }
    }
}

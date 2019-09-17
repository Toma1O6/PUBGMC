package com.toma.pubgmc.common.items.armor;

import com.toma.pubgmc.Pubgmc;
import com.toma.pubgmc.common.capability.IPlayerData;
import com.toma.pubgmc.common.capability.IPlayerData.PlayerDataProvider;
import com.toma.pubgmc.common.items.PMCItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class ItemNVGoggles extends PMCItem {
    public ItemNVGoggles(String name) {
        super(name);
        this.setMaxStackSize(1);
        this.setCreativeTab(Pubgmc.TAB_ITEMS);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        ItemStack stack = playerIn.getHeldItem(handIn);
        IPlayerData data = playerIn.getCapability(PlayerDataProvider.PLAYER_DATA, null);

        if (!data.getEquippedNV()) {
            data.hasEquippedNV(true);
            playerIn.playSound(SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 1.0f, 0f);

            if (!playerIn.capabilities.isCreativeMode) {
                stack.shrink(1);
            }

            return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, stack);
        }

        return new ActionResult<ItemStack>(EnumActionResult.FAIL, stack);
    }
}

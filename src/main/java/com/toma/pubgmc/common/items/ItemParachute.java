package com.toma.pubgmc.common.items;

import com.toma.pubgmc.common.entity.EntityParachute;
import com.toma.pubgmc.init.PMCSounds;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class ItemParachute extends PMCItem {
    public ItemParachute(String name) {
        super(name);
        setMaxStackSize(1);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        ItemStack stack = playerIn.getHeldItem(handIn);
        if (!playerIn.isRiding()) {
            if (!worldIn.isRemote) {
                EntityParachute chute = new EntityParachute(worldIn, playerIn);
                worldIn.spawnEntity(chute);
                playerIn.startRiding(chute);
                if (!playerIn.capabilities.isCreativeMode) {
                    stack.shrink(1);
                }
            } else playerIn.playSound(PMCSounds.chute_open, 1f, 1f);
            return new ActionResult<>(EnumActionResult.SUCCESS, stack);
        }
        return new ActionResult<>(EnumActionResult.FAIL, stack);
    }
}

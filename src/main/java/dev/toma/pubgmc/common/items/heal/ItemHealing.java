package dev.toma.pubgmc.common.items.heal;

import dev.toma.pubgmc.api.item.Consumable;
import dev.toma.pubgmc.api.item.HealingItem;
import dev.toma.pubgmc.common.items.PMCItem;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public abstract class ItemHealing extends PMCItem implements HealingItem, Consumable {

    public static final String UNREACHED_THRESHOLD_KEY = "message.pubgmc.healing.unreached_threshold";

    public ItemHealing(String name) {
        super(name);
        setMaxStackSize(1);
    }

    @Override
    public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityLivingBase entityLiving) {
        if (canHeal(entityLiving, stack)) {
            heal(entityLiving, stack, worldIn);
        }
        return stack;
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        ItemStack stack = playerIn.getHeldItem(handIn);
        if (canHeal(playerIn, stack)) {
            playerIn.setActiveHand(handIn);
            return ActionResult.newResult(EnumActionResult.SUCCESS, stack);
        }
        return ActionResult.newResult(EnumActionResult.FAIL, stack);
    }

    @Override
    public int getMaxItemUseDuration(ItemStack stack) {
        return getUsageTime(stack);
    }
}

package dev.toma.pubgmc.common.items;

import dev.toma.pubgmc.common.entity.controllable.EntityVehicle;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

import java.util.List;

public class ItemFuelCan extends PMCItem {

    public ItemFuelCan() {
        super("fuelcan");
        setMaxStackSize(1);
    }

    @Override
    public EnumAction getItemUseAction(ItemStack stack) {
        return EnumAction.NONE;
    }

    @Override
    public int getMaxItemUseDuration(ItemStack stack) {
        return 80;
    }

    @Override
    public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityLivingBase entityLiving) {
        if (entityLiving instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) entityLiving;

            if (player.isRiding() && player.getRidingEntity() instanceof EntityVehicle) {
                EntityVehicle vehicle = (EntityVehicle) player.getRidingEntity();
                vehicle.refill(player);
                if (!player.capabilities.isCreativeMode) {
                    stack.shrink(1);
                }
            }
        }
        return stack;
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        ItemStack stack = playerIn.getHeldItem(handIn);
        if (playerIn.isRiding() && playerIn.getRidingEntity() instanceof EntityVehicle) {
            if (((EntityVehicle) playerIn.getRidingEntity()).currentSpeed == 0) {
                playerIn.setActiveHand(handIn);
                return new ActionResult<>(EnumActionResult.PASS, stack);
            } else warnPlayer(playerIn, "Vehicle must be stationary!");
        } else warnPlayer(playerIn, "You must sit inside vehicle to refill fuel!");
        return new ActionResult(EnumActionResult.FAIL, stack);
    }

    @Override
    public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add("Hold right click while driving vehicle");
        tooltip.add("Vehicle must be stationary!");
    }
}

package dev.toma.pubgmc.common.items;

import dev.toma.pubgmc.api.item.Consumable;
import dev.toma.pubgmc.common.entity.controllable.EntityVehicle;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

public class ItemFuelCan extends PMCItem implements Consumable {

    public ItemFuelCan(String name) {
        super(name);
        setMaxStackSize(1);
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
        if (!playerIn.isRiding() || !(playerIn.getRidingEntity() instanceof EntityVehicle)) {
            this.sendError(playerIn, worldIn, "label.pubgmc.fuel_can.not_in_vehicle");
            return ActionResult.newResult(EnumActionResult.FAIL, stack);
        }
        EntityVehicle vehicle = (EntityVehicle) playerIn.getRidingEntity();
        if (vehicle.currentSpeed != 0) {
            this.sendError(playerIn, worldIn, "label.pubgmc.fuel_can.vehicle_not_stationary");
            return ActionResult.newResult(EnumActionResult.FAIL, stack);
        }
        playerIn.setActiveHand(handIn);
        return ActionResult.newResult(EnumActionResult.PASS, stack);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add(I18n.format("label.pubgmc.fuel_can.tooltip.usage"));
        tooltip.add(I18n.format("label.pubgmc.fuel_can.vehicle_not_stationary"));
    }

    private void sendError(EntityPlayer player, World world, String key) {
        if (world.isRemote)
            return;
        ITextComponent component = new TextComponentTranslation(key);
        component.getStyle().setColor(TextFormatting.RED);
        player.sendStatusMessage(component, true);
    }
}

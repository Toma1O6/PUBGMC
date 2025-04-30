package dev.toma.pubgmc.common.items;

import dev.toma.pubgmc.api.item.Consumable;
import dev.toma.pubgmc.common.entity.EntityFuelCan;
import dev.toma.pubgmc.common.entity.controllable.EntityVehicle;
import dev.toma.pubgmc.init.PMCItems;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.*;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

public class ItemFuelCan extends PMCItem implements Consumable {
    public static float initHealth = 40.0F;
    public static float fuelPercentage = 0.3F;

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
                vehicle.refill(player, getHealth(stack) / initHealth);
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
        if (!worldIn.isRemote && playerIn.isSneaking()) {
            placeFuelCanEntity(worldIn, playerIn, stack);
            return ActionResult.newResult(EnumActionResult.PASS, stack);
        }
        if (!playerIn.isRiding() || !(playerIn.getRidingEntity() instanceof EntityVehicle)) {
            this.sendError(playerIn, worldIn, "label.pubgmc.fuel_can.not_in_vehicle");
            return ActionResult.newResult(EnumActionResult.FAIL, stack);
        }
        EntityVehicle vehicle = (EntityVehicle) playerIn.getRidingEntity();
        if (vehicle.getSpeedPerTick() != 0) {
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
        tooltip.add(I18n.format("label.pubgmc.fuel_can.fuel", (int)(fuelPercentage * 100 * (getHealth(stack) / initHealth))));
    }

    private void sendError(EntityPlayer player, World world, String key) {
        if (world.isRemote)
            return;
        ITextComponent component = new TextComponentTranslation(key);
        component.getStyle().setColor(TextFormatting.RED);
        player.sendStatusMessage(component, true);
    }

    public void placeFuelCanEntity(World worldIn, EntityPlayer playerIn, ItemStack stack) {
        if (!worldIn.isRemote) {
            EntityFuelCan fuelCan = new EntityFuelCan(worldIn, playerIn, this.getHealth(stack));
            worldIn.spawnEntity(fuelCan);
            stack.shrink(1); // actually doesn't shrink in creative mode
            if (stack.isEmpty()) {
                playerIn.setHeldItem(EnumHand.MAIN_HAND, ItemStack.EMPTY);
            }
            worldIn.playSound(null, playerIn.posX, playerIn.posY, playerIn.posZ, SoundEvents.BLOCK_WOOD_PLACE, SoundCategory.BLOCKS ,1.0F, 1.0F);
        }
    }

    public float getHealth(ItemStack stack) {
        if (stack == null) {
            return 0;
        }
        NBTTagCompound nbt;
        if (!stack.hasTagCompound()) {
            stack.setTagCompound(new NBTTagCompound());
        }
        nbt = stack.getTagCompound();
        if (!nbt.hasKey("health") || nbt.getTagId("health") != 5) {
            nbt.setFloat("health", initHealth);
        }
        return nbt.getFloat("health");
    }
}

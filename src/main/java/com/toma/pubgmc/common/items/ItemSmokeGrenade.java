package com.toma.pubgmc.common.items;

import com.toma.pubgmc.Pubgmc;
import com.toma.pubgmc.common.entity.EntitySmokeGrenade;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

import java.util.List;

public class ItemSmokeGrenade extends PMCItem {
    public ItemSmokeGrenade(String name) {
        super(name);
        this.setMaxStackSize(1);
        this.setCreativeTab(Pubgmc.pmcitemstab);
    }

    @Override
    public boolean onEntitySwing(EntityLivingBase entityLiving, ItemStack stack) {
        if (entityLiving instanceof EntityPlayer) {
            EntityPlayer playerIn = (EntityPlayer) entityLiving;
            World worldIn = playerIn.world;

            BlockPos pos = new BlockPos(playerIn.posX, playerIn.posY, playerIn.posZ);

            worldIn.playSound(playerIn, pos.getX(), pos.getY(), pos.getZ(), SoundEvents.ENTITY_SNOWBALL_THROW, SoundCategory.NEUTRAL, 0.3f, 1f);

            if (!worldIn.isRemote) {
                EntitySmokeGrenade grenade = new EntitySmokeGrenade(worldIn, playerIn, false);
                worldIn.spawnEntity(grenade);

                if (!playerIn.capabilities.isCreativeMode) {
                    stack.shrink(1);
                }
            }
        }

        return true;
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        ItemStack stack = playerIn.getHeldItem(handIn);
        BlockPos pos = new BlockPos(playerIn.posX, playerIn.posY, playerIn.posZ);

        worldIn.playSound(playerIn, pos.getX(), pos.getY(), pos.getZ(), SoundEvents.ENTITY_SNOWBALL_THROW, SoundCategory.NEUTRAL, 0.3f, 0f);

        if (!worldIn.isRemote) {
            EntitySmokeGrenade grenade = new EntitySmokeGrenade(worldIn, playerIn, true);
            worldIn.spawnEntity(grenade);

            if (!playerIn.capabilities.isCreativeMode) {
                stack.shrink(1);
            }

            return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, stack);
        }

        return new ActionResult<ItemStack>(EnumActionResult.FAIL, stack);
    }

    @Override
    public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add(TextFormatting.BOLD + I18n.format("grenade.he.fuse") + ": " + TextFormatting.RESET + "" + TextFormatting.RED + I18n.format("grenade.he.fuse.time"));
        tooltip.add(TextFormatting.BOLD + I18n.format("grenade.smoke.effect") + ": " + TextFormatting.RESET + "" + TextFormatting.RED + I18n.format("grenade.smoke.effect.duration"));
        tooltip.add(TextFormatting.RED + I18n.format("grenade.smoke.warn"));
        tooltip.add(TextFormatting.ITALIC + I18n.format("grenade.desc.info"));
    }
}

package com.toma.pubgmc.common.items;

import com.toma.pubgmc.common.entity.EntityFlashbang;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemFlashBang extends PMCItem {

    public ItemFlashBang(String name) {
        super(name);
        setMaxStackSize(1);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        ItemStack stack = playerIn.getHeldItem(handIn);
        BlockPos pos = new BlockPos(playerIn.posX, playerIn.posY, playerIn.posZ);

        worldIn.playSound(playerIn, pos.getX(), pos.getY(), pos.getZ(), SoundEvents.ENTITY_SNOWBALL_THROW, SoundCategory.NEUTRAL, 0.3f, 0f);

        if (!worldIn.isRemote) {
            EntityFlashbang grenade = new EntityFlashbang(worldIn, playerIn, true);
            worldIn.spawnEntity(grenade);

            if (!playerIn.capabilities.isCreativeMode) {
                stack.shrink(1);
            }

            return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, stack);
        }

        return new ActionResult<ItemStack>(EnumActionResult.FAIL, stack);
    }

    @Override
    public boolean onEntitySwing(EntityLivingBase entityLiving, ItemStack stack) {
        if (entityLiving instanceof EntityPlayer) {
            EntityPlayer playerIn = (EntityPlayer) entityLiving;
            World worldIn = playerIn.world;

            BlockPos pos = new BlockPos(playerIn.posX, playerIn.posY, playerIn.posZ);

            worldIn.playSound(playerIn, pos.getX(), pos.getY(), pos.getZ(), SoundEvents.ENTITY_SNOWBALL_THROW, SoundCategory.NEUTRAL, 0.3f, 1f);

            if (!worldIn.isRemote) {
                EntityFlashbang grenade = new EntityFlashbang(worldIn, playerIn, false);
                worldIn.spawnEntity(grenade);

                if (!playerIn.capabilities.isCreativeMode) {
                    stack.shrink(1);
                }
            }
        }
        return true;
    }
}

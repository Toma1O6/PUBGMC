package com.toma.pubgmc.common.items;

import com.toma.pubgmc.Pubgmc;
import com.toma.pubgmc.common.capability.IPlayerData;
import com.toma.pubgmc.common.capability.IPlayerData.PlayerDataProvider;
import com.toma.pubgmc.common.entity.EntityGrenade;
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
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

import java.util.List;

public class ItemGrenade extends PMCItem {
    public ItemGrenade(String name) {
        super(name);
        setMaxStackSize(1);
        setCreativeTab(Pubgmc.pmcitemstab);
    }

    @Override
    public boolean onEntitySwing(EntityLivingBase entityLiving, ItemStack stack) {
        if (entityLiving instanceof EntityPlayer) {
            EntityPlayer playerIn = (EntityPlayer) entityLiving;
            World worldIn = playerIn.world;
            IPlayerData data = playerIn.getCapability(PlayerDataProvider.PLAYER_DATA, null);

            BlockPos pos = new BlockPos(playerIn.posX, playerIn.posY, playerIn.posZ);

            EntityGrenade grenade = new EntityGrenade(worldIn, playerIn, false);
            if (!data.isGrenadeCooking()) {
                data.setGrenadeCooking(true);
                data.setCookingTime(0);
                worldIn.playSound(null, playerIn.posX, playerIn.posY, playerIn.posZ, SoundEvents.BLOCK_LEVER_CLICK, SoundCategory.BLOCKS, 1.0f, 1f);
            } else {
                worldIn.playSound(null, playerIn.posX, playerIn.posY, playerIn.posZ, SoundEvents.ENTITY_SNOWBALL_THROW, SoundCategory.NEUTRAL, 0.5f, 1.0f);

                if (!worldIn.isRemote) {
                    data.setGrenadeCooking(false);
                    data.setCookingTime(0);
                    worldIn.spawnEntity(grenade);

                    if (!playerIn.capabilities.isCreativeMode) {
                        stack.shrink(1);
                    }

                    if (worldIn.getDifficulty() == EnumDifficulty.PEACEFUL) {
                        playerIn.sendMessage(new TextComponentString(TextFormatting.RED + "Your difficulty is PEACEFUL, explosion won't do damage to players!"));
                    }
                }
            }
        }

        return true;
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        ItemStack stack = playerIn.getHeldItem(handIn);
        BlockPos pos = new BlockPos(playerIn.posX, playerIn.posY, playerIn.posZ);
        IPlayerData data = playerIn.getCapability(PlayerDataProvider.PLAYER_DATA, null);

        if (!data.isGrenadeCooking()) {
            data.setGrenadeCooking(true);
            data.setCookingTime(0);
            worldIn.playSound(null, playerIn.posX, playerIn.posY, playerIn.posZ, SoundEvents.BLOCK_LEVER_CLICK, SoundCategory.BLOCKS, 1.0f, 1.0f);
        } else {
            worldIn.playSound(null, playerIn.posX, playerIn.posY, playerIn.posZ, SoundEvents.ENTITY_SNOWBALL_THROW, SoundCategory.NEUTRAL, 0.5f, 1.0f);

            if (!worldIn.isRemote) {
                EntityGrenade grenade = new EntityGrenade(worldIn, playerIn, true);
                worldIn.spawnEntity(grenade);
                data.setGrenadeCooking(false);
                data.setCookingTime(0);

                if (!playerIn.capabilities.isCreativeMode) {
                    stack.shrink(1);
                }
            }
        }

        return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, stack);
    }

    @Override
    public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add(TextFormatting.BOLD + I18n.format("grenade.he.fuse") + ": " + TextFormatting.RESET + "" + TextFormatting.RED + I18n.format("grenade.he.fuse.time"));
        tooltip.add(TextFormatting.ITALIC + I18n.format("grenade.desc.info"));
    }
}

package com.toma.pubgmc.common.items.heal;

import com.toma.pubgmc.common.capability.IPlayerData;
import com.toma.pubgmc.common.capability.IPlayerData.PlayerDataProvider;
import com.toma.pubgmc.common.items.PMCItem;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public abstract class ItemHealing extends PMCItem {
    public float health;
    public float boost;

    public ItemHealing(String name) {
        super(name);
        setMaxStackSize(1);
    }

    public abstract Action getAction();

    public abstract int getUseTime();

    public abstract EnumAction getUseAction();

    @Override
    public int getMaxItemUseDuration(ItemStack stack) {
        return getUseTime();
    }

    @Override
    public EnumAction getItemUseAction(ItemStack stack) {
        return getUseAction();
    }

    @Override
    public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityLivingBase entityLiving) {
        if (entityLiving instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) entityLiving;
            IPlayerData data = player.getCapability(PlayerDataProvider.PLAYER_DATA, null);

            if (getAction() == Action.HEAL && canPlayerHeal(player)) {
                float health = getHealAmount(player);
                health = player.getHealth() + health > 20 ? 20 - player.getHealth() : health;
                player.heal(health);
            } else if (getAction() == Action.BOOST) {
                data.addBoost(getBoostAmount());
            }

            if (!player.capabilities.isCreativeMode)
                stack.shrink(1);
        }
        return stack;
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        if (canPlayerHeal(playerIn)) playerIn.setActiveHand(handIn);
        else if (!worldIn.isRemote)
            playerIn.sendMessage(new TextComponentString(TextFormatting.RED + "Conditions to use item not met!"));
        return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, playerIn.getHeldItem(handIn));
    }

    public float getHealAmount(EntityPlayer player) {
        return health;
    }

    public float getBoostAmount() {
        return boost;
    }

    public boolean canPlayerHeal(EntityPlayer player) {
        return true;
    }

    public enum Action {
        HEAL,
        BOOST;
    }
}

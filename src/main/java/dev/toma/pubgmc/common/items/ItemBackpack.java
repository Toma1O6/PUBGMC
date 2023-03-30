package dev.toma.pubgmc.common.items;

import dev.toma.pubgmc.common.capability.player.IPlayerData;
import dev.toma.pubgmc.common.capability.player.PlayerDataProvider;
import dev.toma.pubgmc.init.PMCItems;
import dev.toma.pubgmc.util.game.loot.LootManager;
import dev.toma.pubgmc.util.game.loot.LootType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class ItemBackpack extends PMCItem {
    public ItemBackpack(String name) {
        super(name);
        this.setMaxStackSize(1);
        LootManager.register(LootType.ARMOR, new LootManager.LootEntry(this, 10, false));
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        ItemStack stack = playerIn.getHeldItem(handIn);
        IPlayerData data = playerIn.getCapability(PlayerDataProvider.PLAYER_DATA, null);

        if (stack.getItem() == PMCItems.BACKPACK1 && data.getBackpackLevel() == 0) {
            if (!playerIn.capabilities.isCreativeMode) {
                stack.shrink(1);
            }

            data.setBackpackLevel(1);
        }

        if (stack.getItem() == PMCItems.BACKPACK2 && (data.getBackpackLevel() == 0 || data.getBackpackLevel() == 1)) {
            if (!playerIn.capabilities.isCreativeMode) {
                stack.shrink(1);
            }

            if (data.getBackpackLevel() == 1) {
                playerIn.addItemStackToInventory(new ItemStack(PMCItems.BACKPACK1));
            }

            data.setBackpackLevel(2);
        }

        if (stack.getItem() == PMCItems.BACKPACK3 && data.getBackpackLevel() < 3) {
            if (!playerIn.capabilities.isCreativeMode) {
                stack.shrink(1);
            }

            if (data.getBackpackLevel() == 1) {
                playerIn.addItemStackToInventory(new ItemStack(PMCItems.BACKPACK1));
            }

            if (data.getBackpackLevel() == 2) {
                playerIn.addItemStackToInventory(new ItemStack(PMCItems.BACKPACK2));
            }

            data.setBackpackLevel(3);
        }

        return new ActionResult<ItemStack>(EnumActionResult.FAIL, stack);
    }
}

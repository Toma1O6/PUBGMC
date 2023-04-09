package dev.toma.pubgmc.common.tileentity;

import dev.toma.pubgmc.common.blocks.BlockLootSpawner;
import dev.toma.pubgmc.data.loot.LootConfigurations;
import dev.toma.pubgmc.data.loot.LootManager;
import dev.toma.pubgmc.util.TileEntitySync;
import dev.toma.pubgmc.util.game.loot.ILootSpawner;
import net.minecraft.block.state.IBlockState;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;

import java.util.List;

// TODO stop using IInventory
public class TileEntityLootGenerator extends TileEntitySync implements IInventoryTileEntity, ILootSpawner {

    private NonNullList<ItemStack> inventory = NonNullList.withSize(9, ItemStack.EMPTY);
    private String customName;
    private String gameID = "EMPTY";

    @Override
    public String getName() {
        return this.hasCustomName() ? this.customName : "container.lootspawner";
    }

    @Override
    public boolean hasCustomName() {
        return this.customName != null && !this.customName.isEmpty();
    }

    public void setCustomName(String customName) {
        this.customName = customName;
    }

    @Override
    public ITextComponent getDisplayName() {
        return this.hasCustomName() ? new TextComponentString(this.getName()) : new TextComponentTranslation(this.getName());
    }

    //To keep all items when state changes
    @Override
    public boolean shouldRefresh(World world, BlockPos pos, IBlockState oldState, IBlockState newSate) {
        return false;
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        clear();
        ItemStackHelper.loadAllItems(compound, this.inventory);
        if (compound.hasKey("CustomName", 8)) this.setCustomName(compound.getString("CustomName"));
        gameID = compound.hasKey("gameID") ? compound.getString("gameID") : "";
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);
        ItemStackHelper.saveAllItems(compound, this.inventory);
        if (this.hasCustomName()) compound.setString("CustomName", this.customName);
        compound.setString("gameID", gameID);
        return compound;
    }

    @Override
    public String getGameHash() {
        return gameID;
    }

    @Override
    public void setGameHash(String hash) {
        this.gameID = hash;
    }

    @Override
    public void onLoaded() {
        clear();
        int tier = world.getBlockState(pos).getValue(BlockLootSpawner.LOOT);
        String configurationKey = LootConfigurations.LOOT_SPAWNER[tier];
        List<ItemStack> generated = LootManager.getInstance().generateFromConfiguration(configurationKey, world, this, pos);
        for (int i = 0; i < Math.min(generated.size(), inventory.size()); i++) {
            setInventorySlotContents(i, generated.get(i));
        }
    }

    @Override
    public NonNullList<ItemStack> getInventory() {
        return inventory;
    }

    @Override
    public boolean isAirdropContainer() {
        return false;
    }
}

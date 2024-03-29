package dev.toma.pubgmc.common.tileentity;

import dev.toma.pubgmc.Pubgmc;
import dev.toma.pubgmc.api.game.GameObject;
import dev.toma.pubgmc.api.game.loot.LootableContainer;
import dev.toma.pubgmc.util.TileEntityUtil;
import dev.toma.pubgmc.util.helper.GameHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;

import java.util.UUID;

public class TileEntityPlayerCrate extends TileEntity implements IInventory, LootableContainer, GameObject {

    private NonNullList<ItemStack> inv = NonNullList.withSize(45, ItemStack.EMPTY);
    private String customName;
    private UUID gameId = GameHelper.DEFAULT_UUID;

    @Override
    public String getName() {
        return this.hasCustomName() ? this.customName : "container.player_crate";
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

    @Override
    public int getSizeInventory() {
        return this.inv.size();
    }

    @Override
    public boolean isEmpty() {
        for (ItemStack stack : this.inv) {
            if (!stack.isEmpty()) return false;
        }
        return true;
    }

    @Override
    public ItemStack getStackInSlot(int index) {
        return this.inv.get(index);
    }

    @Override
    public ItemStack decrStackSize(int index, int count) {
        return ItemStackHelper.getAndSplit(this.inv, index, count);
    }

    @Override
    public ItemStack removeStackFromSlot(int index) {
        return ItemStackHelper.getAndRemove(this.inv, index);
    }

    @Override
    public void setInventorySlotContents(int index, ItemStack stack) {
        this.inv.set(index, stack);
        if (!stack.isEmpty() && stack.getCount() > this.getInventoryStackLimit()) {
            stack.setCount(this.getInventoryStackLimit());
        }
        this.markDirty();
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        this.inv = NonNullList.withSize(this.getSizeInventory(), ItemStack.EMPTY);
        ItemStackHelper.loadAllItems(compound, this.inv);
        gameId = compound.getUniqueId("gameId");
        if (compound.hasKey("CustomName", 8)) this.setCustomName(compound.getString("CustomName"));
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);
        ItemStackHelper.saveAllItems(compound, this.inv);
        compound.setUniqueId("gameId", gameId);
        if (this.hasCustomName()) compound.setString("CustomName", this.customName);
        return compound;
    }

    @Override
    public int getInventoryStackLimit() {
        return 64;
    }

    @Override
    public boolean isUsableByPlayer(EntityPlayer player) {
        return this.world.getTileEntity(this.pos) == this && player.getDistanceSq((double) this.pos.getX() + 0.5D, (double) this.pos.getY() + 0.5D, (double) this.pos.getZ() + 0.5D) <= 64.0D;
    }

    @Override
    public void openInventory(EntityPlayer player) {

    }

    @Override
    public void closeInventory(EntityPlayer player) {

    }

    public String getGuiID() {
        return Pubgmc.MOD_ID + "player_crate";
    }

    @Override
    public int getField(int id) {
        return 0;
    }

    @Override
    public void setField(int id, int value) {

    }

    @Override
    public int getFieldCount() {
        return 45;
    }

    @Override
    public void clear() {
        this.inv.clear();
    }

    @Override
    public boolean isItemValidForSlot(int index, ItemStack stack) {
        return true;
    }

    @Override
    public UUID getCurrentGameId() {
        return gameId;
    }

    @Override
    public void assignGameId(UUID gameId) {
        this.gameId = gameId;
        markDirty();
    }

    @Override
    public void onNewGameDetected(UUID newGameId) {
        inv.clear();
        world.destroyBlock(pos, false);
    }

    @Override
    public BlockPos getWorldPosition() {
        return pos;
    }

    @Override
    public int getSize() {
        return getSizeInventory();
    }

    @Override
    public ItemStack getItemStackInSlot(int index) {
        return getStackInSlot(index);
    }

    @Override
    public void setItemStackToSlot(int index, ItemStack stack) {
        setInventorySlotContents(index, stack);
    }

    @Override
    public void onLootContentsChanged() {
        TileEntityUtil.syncToClient(this);
    }
}

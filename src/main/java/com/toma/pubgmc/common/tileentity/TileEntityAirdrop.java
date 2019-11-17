package com.toma.pubgmc.common.tileentity;

import com.toma.pubgmc.util.game.loot.ILootSpawner;
import com.toma.pubgmc.util.game.loot.LootManager;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.NonNullList;

public class TileEntityAirdrop extends TileEntity implements IInventoryTileEntity, ILootSpawner, ITickable {

    private String hash = "empty";
    private final NonNullList<ItemStack> inventory;
    private static LootManager manager;

    public TileEntityAirdrop(final int size) {
        this.inventory = NonNullList.withSize(size, ItemStack.EMPTY);
    }

    public void onLanded() {
        this.getManager(false).generateLootIn(this, this.getInventory().size() / 9);
    }

    @Override
    public String getName() {
        return "airdrop";
    }

    @Override
    public NonNullList<ItemStack> getInventory() {
        return inventory;
    }

    @Override
    public String getGameHash() {
        return hash;
    }

    @Override
    public void setGameHash(String hash) {
        this.hash = hash;
    }

    @Override
    public void onLoaded() {
        this.world.scheduleBlockUpdate(this.pos, this.world.getBlockState(pos).getBlock(), 2, 1);
    }

    @Override
    public boolean isAirdropContainer() {
        return true;
    }

    @Override
    public boolean generateLootOnCommand() {
        return false;
    }

    @Override
    public void update() {

    }

    public LootManager getManager(boolean needsUpdate) {
        if(manager == null || needsUpdate) manager = new LootManager(this.world);
        return manager;
    }
}

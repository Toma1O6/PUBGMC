package dev.toma.pubgmc.common.tileentity;

import dev.toma.pubgmc.data.loot.LootConfigurations;
import dev.toma.pubgmc.data.loot.LootManager;
import dev.toma.pubgmc.util.TileEntitySync;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ITickable;
import net.minecraft.util.NonNullList;

import java.util.List;

public class TileEntityAirdrop extends TileEntitySync implements IInventoryTileEntity, ITickable {

    private NonNullList<ItemStack> inventory;

    public TileEntityAirdrop() {
        this.inventory = NonNullList.withSize(9, ItemStack.EMPTY);
    }

    public TileEntityAirdrop withInventory(int size) {
        this.inventory = NonNullList.withSize(size, ItemStack.EMPTY);
        return this;
    }

    public void onLanded() {
        String configuration = inventory.size() > 9 ? LootConfigurations.AIRDROP_LARGE : LootConfigurations.AIRDROP;
        List<ItemStack> generatedItems = LootManager.getInstance().generateFromConfiguration(configuration, world, this, pos);
        for (int i = 0; i < Math.min(inventory.size(), generatedItems.size()); i++) {
            setInventorySlotContents(i, generatedItems.get(i));
        }
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
    public void update() {
        if(world.isRemote && !this.isEmpty()) {
            if(world.getTotalWorldTime() % 3 == 0) {
                world.spawnParticle(EnumParticleTypes.CLOUD, this.pos.getX() + 0.5, this.pos.getY() + 1, this.pos.getZ() + 0.5, 0, 0.2, 0);
            }
        }
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);
        ItemStackHelper.saveAllItems(compound, inventory);
        return compound;
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        ItemStackHelper.loadAllItems(compound, inventory);
    }
}

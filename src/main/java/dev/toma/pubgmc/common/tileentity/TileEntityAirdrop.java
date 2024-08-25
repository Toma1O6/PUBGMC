package dev.toma.pubgmc.common.tileentity;

import dev.toma.pubgmc.api.game.GenerationType;
import dev.toma.pubgmc.api.game.Generator;
import dev.toma.pubgmc.api.game.loot.LootProvider;
import dev.toma.pubgmc.api.game.loot.LootableContainer;
import dev.toma.pubgmc.data.loot.LootConfiguration;
import dev.toma.pubgmc.data.loot.LootConfigurations;
import dev.toma.pubgmc.data.loot.LootGenerationContext;
import dev.toma.pubgmc.data.loot.LootManager;
import dev.toma.pubgmc.util.TileEntitySync;
import dev.toma.pubgmc.util.TileEntityUtil;
import dev.toma.pubgmc.util.helper.GameHelper;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ITickable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;

import javax.annotation.Nullable;
import java.util.List;
import java.util.UUID;

public class TileEntityAirdrop extends TileEntitySync implements IInventoryTileEntity, ITickable, Generator, LootableContainer {

    private NonNullList<ItemStack> inventory;
    private UUID gameId = GameHelper.DEFAULT_UUID;

    public TileEntityAirdrop() {
        this.inventory = NonNullList.withSize(9, ItemStack.EMPTY);
    }

    public TileEntityAirdrop withInventory(int size) {
        this.inventory = NonNullList.withSize(size, ItemStack.EMPTY);
        return this;
    }

    @Override
    public String getName() {
        return "container.airdrop";
    }

    @Nullable
    @Override
    public ITextComponent getDisplayName() {
        return this.hasCustomName() ? new TextComponentString(this.getName()) : new TextComponentTranslation(this.getName());
    }

    @Override
    public NonNullList<ItemStack> getInventory() {
        return inventory;
    }

    @Override
    public void update() {
        if (world.isRemote && !this.isEmpty()) {
            if (world.getTotalWorldTime() % 3 == 0) {
                world.spawnParticle(EnumParticleTypes.CLOUD, this.pos.getX() + 0.5, this.pos.getY() + 1, this.pos.getZ() + 0.5, 0, 0.2, 0);
            }
        }
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);
        ItemStackHelper.saveAllItems(compound, inventory);
        compound.setUniqueId("gameId", gameId);
        return compound;
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        ItemStackHelper.loadAllItems(compound, inventory);
        gameId = compound.getUniqueId("gameId");
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
        world.scheduleBlockUpdate(pos, world.getBlockState(pos).getBlock(), 2, 0);
    }

    @Override
    public void generate(GenerationType.Context context) {
        if (context.has(GenerationType.ITEMS)) {
            clear();
            String configurationPath = inventory.size() > 9 ? LootConfigurations.AIRDROP_LARGE : LootConfigurations.AIRDROP;
            LootConfiguration configuration = LootManager.getInstance().getConfigurationById(configurationPath);
            if (configuration != null) {
                LootProvider provider = configuration.getPool();
                LootGenerationContext generationContext = LootGenerationContext.tileEntity(this);
                List<ItemStack> itemStacks = provider.generateItems(generationContext);
                for (int i = 0; i < Math.min(this.getSizeInventory(), itemStacks.size()); i++) {
                    setInventorySlotContents(i, itemStacks.get(i));
                }
            }
            TileEntityUtil.syncToClient(this);
        }
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

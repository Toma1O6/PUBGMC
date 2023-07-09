package dev.toma.pubgmc.common.tileentity;

import dev.toma.pubgmc.api.game.GenerationType;
import dev.toma.pubgmc.api.game.Generator;
import dev.toma.pubgmc.api.game.loot.LootProvider;
import dev.toma.pubgmc.api.game.loot.LootableContainer;
import dev.toma.pubgmc.common.blocks.BlockLootSpawner;
import dev.toma.pubgmc.data.loot.LootConfiguration;
import dev.toma.pubgmc.data.loot.LootConfigurations;
import dev.toma.pubgmc.data.loot.LootGenerationContext;
import dev.toma.pubgmc.data.loot.LootManager;
import dev.toma.pubgmc.util.TileEntitySync;
import dev.toma.pubgmc.util.TileEntityUtil;
import dev.toma.pubgmc.util.helper.GameHelper;
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
import java.util.UUID;

public class TileEntityLootGenerator extends TileEntitySync implements IInventoryTileEntity, Generator, LootableContainer {

    private final NonNullList<ItemStack> inventory = NonNullList.withSize(9, ItemStack.EMPTY);
    private String customName;
    private UUID gameId = GameHelper.DEFAULT_UUID;

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
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);
        ItemStackHelper.saveAllItems(compound, this.inventory);
        if (this.hasCustomName()) compound.setString("CustomName", this.customName);
        return compound;
    }

    @Override
    public NonNullList<ItemStack> getInventory() {
        return inventory;
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
        clear();
        assignGameId(newGameId);
        TileEntityUtil.syncToClient(this);
    }

    @Override
    public void generate(GenerationType.Context context) {
        if (context.has(GenerationType.ITEMS)) {
            clear();
            String configurationPath = this.getLootConfigurationId();
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

    private String getLootConfigurationId() {
        int tier = world.getBlockState(pos).getValue(BlockLootSpawner.LOOT);
        return LootConfigurations.LOOT_SPAWNER[tier];
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

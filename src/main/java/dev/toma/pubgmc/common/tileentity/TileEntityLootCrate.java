package dev.toma.pubgmc.common.tileentity;

import dev.toma.pubgmc.api.game.GenerationType;
import dev.toma.pubgmc.api.game.Generator;
import dev.toma.pubgmc.api.game.loot.LootProvider;
import dev.toma.pubgmc.api.game.loot.LootableContainer;
import dev.toma.pubgmc.common.blocks.BlockLootCrate;
import dev.toma.pubgmc.data.loot.LootConfiguration;
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

import javax.annotation.Nullable;
import java.util.List;
import java.util.UUID;

public class TileEntityLootCrate extends TileEntitySync implements IInventoryTileEntity, Generator, LootableContainer {

    private final NonNullList<ItemStack> inventory = NonNullList.withSize(9, ItemStack.EMPTY);
    private UUID gameId = GameHelper.DEFAULT_UUID;

    @Override
    public NonNullList<ItemStack> getInventory() {
        return inventory;
    }

    @Override
    public String getName() {
        return "container.crate";
    }

    @Nullable
    @Override
    public ITextComponent getDisplayName() {
        return this.hasCustomName() ? new TextComponentString(this.getName()) : new TextComponentTranslation(this.getName());
    }

    @Override
    public boolean shouldRefresh(World world, BlockPos pos, IBlockState oldState, IBlockState newSate) {
        return false;
    }

    @Override
    public void generate(GenerationType.Context context) {
        if (context.has(GenerationType.ITEMS)) {
            clear();
            String configurationPath = getLootConfigurationId();
            if (configurationPath == null) {
                return;
            }
            LootConfiguration lootConfiguration = LootManager.getInstance().getConfigurationById(configurationPath);
            if (lootConfiguration != null) {
                LootProvider provider = lootConfiguration.getPool();
                LootGenerationContext generationContext = LootGenerationContext.tileEntity(this);
                List<ItemStack> loot = provider.generateItems(generationContext);
                for (int i = 0; i < Math.min(this.getSizeInventory(), loot.size()); i++) {
                    setInventorySlotContents(i, loot.get(i));
                }
            }
            TileEntityUtil.syncToClient(this);
        }
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

        IBlockState state = world.getBlockState(pos);
        world.scheduleBlockUpdate(pos, state.getBlock(), 5, 0);
    }

    @Override
    public void onLootContentsChanged() {
        IBlockState state = world.getBlockState(pos);
        world.setBlockState(pos, state.withProperty(BlockLootCrate.OPEN, true));
        TileEntityUtil.syncToClient(this);
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

    @Nullable
    private String getLootConfigurationId() {
        IBlockState state = world.getBlockState(pos);
        if (state.getBlock() instanceof BlockLootCrate) {
            BlockLootCrate.EnumCrateType type = ((BlockLootCrate) state.getBlock()).crateType;
            return type.canBeLooted() ? "crate_" + type.name().toLowerCase() : null;
        }
        return null;
    }
}

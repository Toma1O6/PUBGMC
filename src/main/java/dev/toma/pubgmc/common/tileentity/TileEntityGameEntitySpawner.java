package dev.toma.pubgmc.common.tileentity;

import dev.toma.pubgmc.api.entity.EntityProvider;
import dev.toma.pubgmc.api.game.GameObject;
import dev.toma.pubgmc.api.game.GenerationType;
import dev.toma.pubgmc.api.game.Generator;
import dev.toma.pubgmc.common.blocks.PMCBlockHorizontal;
import dev.toma.pubgmc.data.entity.EntityProviderManager;
import dev.toma.pubgmc.util.helper.GameHelper;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;

import java.util.List;
import java.util.UUID;

public class TileEntityGameEntitySpawner extends TileEntity implements Generator {

    private final String entityProviderConfigPath;
    private UUID gameId = GameHelper.DEFAULT_UUID;

    public TileEntityGameEntitySpawner(String entityProviderConfigPath) {
        this.entityProviderConfigPath = entityProviderConfigPath;
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);
        compound.setUniqueId("gameId", gameId);
        return compound;
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
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
        assignGameId(newGameId);
    }

    @Override
    public String getLootConfigurationId() {
        return ""; // TODO remove
    }

    @Override
    public void fillWithLoot(List<ItemStack> items) {
        // TODO remove
    }

    @Override
    public void generate(GenerationType.Context context) {
        if (!context.has(GenerationType.ENTITIES))
            return;
        EntityProvider provider = EntityProviderManager.INSTANCE.getEntityProviderById(entityProviderConfigPath);
        if (provider != null) {
            Entity entity = provider.spawnEntity(world, pos);
            if (entity instanceof GameObject) {
                GameObject object = (GameObject) entity;
                object.assignGameId(gameId);
                IBlockState state = world.getBlockState(pos);
                EnumFacing facing = state.getValue(PMCBlockHorizontal.FACING);
                // TODO rotate entity
            }
        }
    }
}

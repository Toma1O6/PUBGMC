package dev.toma.pubgmc.common.blocks;

import dev.toma.pubgmc.common.tileentity.TileEntityGameEntitySpawner;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import javax.annotation.Nullable;

@SuppressWarnings("deprecation")
public class BlockGameEntitySpawner extends PMCBlockHorizontal {

    private final String spawnConfigurationPath;

    public BlockGameEntitySpawner(String identifier, String spawnConfig) {
        super(identifier, Material.ROCK);
        this.spawnConfigurationPath = spawnConfig;
        this.setBlockUnbreakable();
    }

    @Override
    public BlockRenderLayer getBlockLayer() {
        return BlockRenderLayer.CUTOUT;
    }

    @Override
    public boolean isFullCube(IBlockState state) {
        return false;
    }

    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        return BlockLootSpawner.BB;
    }

    @Nullable
    @Override
    public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos) {
        return NULL_AABB;
    }

    @Override
    public boolean hasTileEntity(IBlockState state) {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(World world, IBlockState state) {
        TileEntityGameEntitySpawner spawner = new TileEntityGameEntitySpawner();
        spawner.setEntityProviderConfigPath(spawnConfigurationPath);
        return spawner;
    }

    @Override
    public boolean isPassable(IBlockAccess worldIn, BlockPos pos) {
        return true;
    }
}

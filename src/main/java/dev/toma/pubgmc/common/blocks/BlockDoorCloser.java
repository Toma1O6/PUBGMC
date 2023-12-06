package dev.toma.pubgmc.common.blocks;

import dev.toma.pubgmc.common.tileentity.RecoverableStateTile;
import dev.toma.pubgmc.common.tileentity.TileEntityDoorCloser;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockDoorCloser extends AbstractRecoveableStateBlock {

    public BlockDoorCloser(String name, Material material) {
        super(name, material);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T extends TileEntity & RecoverableStateTile> T createRecoverableTileEntity(World world, IBlockState state) {
        return (T) new TileEntityDoorCloser();
    }
}

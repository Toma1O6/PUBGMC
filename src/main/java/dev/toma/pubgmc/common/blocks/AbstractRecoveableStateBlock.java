package dev.toma.pubgmc.common.blocks;

import dev.toma.pubgmc.common.tileentity.RecoverableStateTile;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

import javax.annotation.Nullable;

/**
 * Used for recovery of states of various vanilla blocks within game system such as doors etc
 */
public abstract class AbstractRecoveableStateBlock extends PMCBlock {

    public AbstractRecoveableStateBlock(String name, Material material) {
        super(name, material);
        setBlockUnbreakable();
    }

    public abstract <T extends TileEntity & RecoverableStateTile> T createRecoverableTileEntity(World world, IBlockState state);

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (playerIn.isCreative() && !worldIn.isRemote && hand == EnumHand.MAIN_HAND) {
            TileEntity tileEntity = worldIn.getTileEntity(pos);
            if (tileEntity instanceof RecoverableStateTile) {
                RecoverableStateTile recoverableStateTile = (RecoverableStateTile) tileEntity;
                int direction = playerIn.isSneaking() ? -1 : 1;
                recoverableStateTile.walk(direction);
                int offset = recoverableStateTile.getOffset();
                IBlockState iBlockState = worldIn.getBlockState(pos.up(offset));
                TextFormatting formatting = recoverableStateTile.canLink(iBlockState) ? TextFormatting.GREEN : TextFormatting.RED;
                playerIn.sendStatusMessage(new TextComponentString(formatting.toString() + iBlockState.getBlock() + ", Offset: " + offset), true);
                return true;
            }
        }
        return false;
    }

    @Override
    public final boolean hasTileEntity(IBlockState state) {
        return true;
    }

    @Nullable
    @Override
    public final TileEntity createTileEntity(World world, IBlockState state) {
        return createRecoverableTileEntity(world, state);
    }
}

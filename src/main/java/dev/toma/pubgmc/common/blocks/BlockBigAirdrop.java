package dev.toma.pubgmc.common.blocks;

import dev.toma.pubgmc.Pubgmc;
import dev.toma.pubgmc.api.capability.IPlayerData;
import dev.toma.pubgmc.api.capability.PlayerDataProvider;
import dev.toma.pubgmc.common.tileentity.TileEntityAirdrop;
import dev.toma.pubgmc.util.handlers.GuiHandler;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockBigAirdrop extends BlockAirdrop {
    private static final AxisAlignedBB COLLISION_BOX = new AxisAlignedBB(-1.4d, 0d, -1.4d, 1.5d, 1.4d, 1.4d);

    public BlockBigAirdrop(String name) {
        super(name, Material.IRON);
    }

    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @Override
    public boolean isFullCube(IBlockState state) {
        return false;
    }

    @Override
    public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos) {
        return COLLISION_BOX;
    }

    @Override
    public EnumBlockRenderType getRenderType(IBlockState state) {
        return EnumBlockRenderType.MODEL;
    }

    @Override
    public TileEntity createTileEntity(World world, IBlockState state) {
        return new TileEntityAirdrop().withInventory(18);
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (!worldIn.isRemote) {
            IPlayerData data = playerIn.getCapability(PlayerDataProvider.PLAYER_DATA, null);
            data.getAimInfo().setAiming(false, 1.0F);
            playerIn.openGui(Pubgmc.instance, GuiHandler.GUI_BIG_AIRDROP, worldIn, pos.getX(), pos.getY(), pos.getZ());
        }

        return true;
    }
}

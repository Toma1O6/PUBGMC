package dev.toma.pubgmc.common.blocks;

import dev.toma.pubgmc.Pubgmc;
import dev.toma.pubgmc.api.capability.IPlayerData;
import dev.toma.pubgmc.api.capability.PlayerDataProvider;
import dev.toma.pubgmc.common.tileentity.TileEntityGunWorkbench;
import dev.toma.pubgmc.util.handlers.GuiHandler;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockGunWorkbench extends PMCBlock {

    public BlockGunWorkbench(String name) {
        super(name, Material.IRON);
        setHardness(1.2f);
        setResistance(5f);
        setHarvestLevel("pickaxe", 0);
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (!playerIn.isSneaking() && !worldIn.isRemote) {
            IPlayerData data = playerIn.getCapability(PlayerDataProvider.PLAYER_DATA, null);
            data.getAimInfo().setAiming(false, 1.0F);
            playerIn.openGui(Pubgmc.instance, GuiHandler.GUI_GUNCRAFTINGTABLE, worldIn, pos.getX(), pos.getY(), pos.getZ());
        }

        return true;
    }

    @Override
    public boolean hasTileEntity(IBlockState state) {
        return true;
    }

    @Override
    public TileEntity createTileEntity(World world, IBlockState state) {
        return new TileEntityGunWorkbench();
    }

    @Override
    public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
        if (worldIn.getTileEntity(pos) instanceof TileEntityGunWorkbench) {
            TileEntityGunWorkbench te = (TileEntityGunWorkbench) worldIn.getTileEntity(pos);
            InventoryHelper.dropInventoryItems(worldIn, pos, te);
        }
    }
}

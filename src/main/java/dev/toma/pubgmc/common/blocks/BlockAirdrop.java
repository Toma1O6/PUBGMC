package dev.toma.pubgmc.common.blocks;

import dev.toma.pubgmc.Pubgmc;
import dev.toma.pubgmc.api.capability.IPlayerData;
import dev.toma.pubgmc.api.capability.PlayerDataProvider;
import dev.toma.pubgmc.common.tileentity.TileEntityAirdrop;
import dev.toma.pubgmc.util.handlers.GuiHandler;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

public class BlockAirdrop extends PMCBlock {

    public BlockAirdrop(String name, Material material) {
        super(name, material);
        this.setSoundType(SoundType.METAL);
        setHardness(0.75f);
        setResistance(15f);
        setHarvestLevel("pickaxe", 1);
        setLightOpacity(0);
    }

    @Override
    public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
        worldIn.setBlockToAir(pos);
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (!worldIn.isRemote && !playerIn.isSneaking()) {
            IPlayerData data = playerIn.getCapability(PlayerDataProvider.PLAYER_DATA, null);
            data.getAimInfo().setAiming(false, 1.0F);
            playerIn.openGui(Pubgmc.instance, GuiHandler.GUI_AIRDROP, worldIn, pos.getX(), pos.getY(), pos.getZ());
        }
        return true;
    }

    @Override
    public boolean hasTileEntity(IBlockState state) {
        return true;
    }

    @Override
    public TileEntity createTileEntity(World world, IBlockState state) {
        return new TileEntityAirdrop();
    }
}

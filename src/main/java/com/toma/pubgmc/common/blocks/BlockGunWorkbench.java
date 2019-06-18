package com.toma.pubgmc.common.blocks;

import com.toma.pubgmc.Pubgmc;
import com.toma.pubgmc.common.network.PacketHandler;
import com.toma.pubgmc.common.network.sp.PacketSyncTileEntity;
import com.toma.pubgmc.common.tileentity.TileEntityGunWorkbench;
import com.toma.pubgmc.util.handlers.GuiHandler;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class BlockGunWorkbench extends PMCBlock
{
	
	public BlockGunWorkbench(String name) 
	{
		super(name, Material.IRON);
		setHardness(1.2f);
		setResistance(5f);
		setHarvestLevel("pickaxe", 0);
		setCreativeTab(Pubgmc.pmcblockstab);
	}
	
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
	{
		if(!playerIn.isSneaking() && !worldIn.isRemote)
		{
			if(worldIn.getGameRules().getBoolean("weaponCrafting"))
			{
				playerIn.openGui(Pubgmc.instance, GuiHandler.GUI_GUNCRAFTINGTABLE, worldIn, pos.getX(), pos.getY(), pos.getZ());
			}
			else
			{
				playerIn.sendMessage(new TextComponentString(TextFormatting.RED + "Crafting is disabled!"));
			}
		}
		
		return true;
	}
	
	@Override
	public boolean hasTileEntity(IBlockState state)
	{
		return true;
	}
	
	@Override
	public TileEntity createTileEntity(World world, IBlockState state)
	{
		return new TileEntityGunWorkbench();
	}
	
	@Override
	public void breakBlock(World worldIn, BlockPos pos, IBlockState state) 
	{
		if(worldIn.getTileEntity(pos) instanceof TileEntityGunWorkbench)
		{
			TileEntityGunWorkbench te = (TileEntityGunWorkbench)worldIn.getTileEntity(pos);
			InventoryHelper.dropInventoryItems(worldIn, pos, te);
		}
	}
}

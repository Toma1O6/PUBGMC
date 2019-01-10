package com.toma.pubgmc.common.blocks;

import java.util.Random;

import com.toma.pubgmc.Pubgmc;
import com.toma.pubgmc.common.tileentity.TileEntityBigAirdrop;
import com.toma.pubgmc.util.handlers.GuiHandler;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockBigAirdrop extends PMCBlock
{
	private static final AxisAlignedBB COLLISION_BOX = new AxisAlignedBB(-1.4d, 0d, -1.4d, 1.5d, 1.4d, 1.4d);
	
	public BlockBigAirdrop(String name)
	{
		super(name, Material.IRON);
		setHardness(0.75f);
		setResistance(15f);
		setHarvestLevel("pickaxe", 1);
		setLightOpacity(0);
		setCreativeTab(Pubgmc.pmcblockstab);
	}
	
	@Override
	public boolean isOpaqueCube(IBlockState state)
	{
		return false;
	}
	
	@Override
	public boolean isFullCube(IBlockState state)
	{
		return false;
	}
	
	@Override
	public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos)
	{
		return COLLISION_BOX;
	}
	
	@Override
	public EnumBlockRenderType getRenderType(IBlockState state) 
	{
		return EnumBlockRenderType.MODEL;
	}
	
	@Override
	public boolean hasTileEntity(IBlockState state)
	{
		return true;
	}
	
	@Override
	public TileEntity createTileEntity(World world, IBlockState state)
	{
		return new TileEntityBigAirdrop();
	}
	
	@Override
	public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state)
	{
		super.onBlockAdded(worldIn, pos, state);
		
		TileEntityBigAirdrop te = (TileEntityBigAirdrop)worldIn.getTileEntity(pos);
		te.generateLoot();
	}
	
	@Override
	public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state)
	{
		return new ItemStack(Items.IRON_INGOT);
	}
	
	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune)
	{
		return Items.IRON_INGOT;
	}
	
	@Override
	public int quantityDropped(Random random)
	{
		return 3 + random.nextInt(8);
	}
	
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
	{
		if(!worldIn.isRemote)
		{
			playerIn.openGui(Pubgmc.instance, GuiHandler.GUI_BIG_AIRDROP, worldIn, pos.getX(), pos.getY(), pos.getZ());
		}
		
		return true;
	}
}

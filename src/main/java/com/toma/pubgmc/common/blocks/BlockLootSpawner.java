package com.toma.pubgmc.common.blocks;

import java.util.Random;

import com.toma.pubgmc.ConfigPMC;
import com.toma.pubgmc.Pubgmc;
import com.toma.pubgmc.common.tileentity.TileEntityLootSpawner;
import com.toma.pubgmc.util.handlers.GuiHandler;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockLootSpawner extends PMCBlock
{
	private MapColor color;
	private Random rand = new Random();
	public LootType type;
	
	private static final AxisAlignedBB BB = new AxisAlignedBB(0.0, 0.0, 0.0, 1.0, 0.25, 1.0);
	public static final PropertyBool DEBUG = PropertyBool.create("debug");

	public BlockLootSpawner(String name, Material material, SoundType sound, MapColor color, LootType type)
	{
		super(name, material);
		setSoundType(sound);
		this.setBlockUnbreakable();
		this.setDefaultState(this.blockState.getBaseState().withProperty(DEBUG, false));
		
		this.color = color;
		this.type = type;
	}
	
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) 
	{
		if(!worldIn.isRemote && !playerIn.isSneaking())
		{
			playerIn.openGui(Pubgmc.instance, GuiHandler.LOOT_SPAWNER, worldIn, pos.getX(), pos.getY(), pos.getZ());
		}
		
		return true;
	}
	
	@Override
	public boolean isFullCube(IBlockState state)
	{
		return false;
	}
	
	@Override
	public boolean isOpaqueCube(IBlockState state)
	{
		return false;
	}
	
	@Override
	public BlockRenderLayer getBlockLayer()
	{
		return ConfigPMC.playerSettings.lootRenderType > 0 ? BlockRenderLayer.CUTOUT : BlockRenderLayer.SOLID;
	}
	
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
	{
		if(state.getValue(DEBUG))
		{
			return FULL_BLOCK_AABB;
		}
		
		return BB;
	}
	
	@Override
	public AxisAlignedBB getCollisionBoundingBox(IBlockState state, IBlockAccess worldIn, BlockPos pos)
	{
		return state.getValue(DEBUG) ? FULL_BLOCK_AABB : NULL_AABB;
	}
	
	@Override
	public boolean hasTileEntity(IBlockState state)
	{
		return true;
	}
	
	@Override
	public TileEntity createTileEntity(World world, IBlockState state)
	{
		return new TileEntityLootSpawner();
	}
	
	public LootType getLootType()
	{
		return this.type;
	}
	
	public LootType setLootType(LootType type)
	{
		return this.type = type;
	}
	
	public int lootChanceModifier()
	{
		int i = 1;
		
		switch(type)
		{
			case COMMON: return i;
			case RARE: return i = 2;
			case VERY_RARE: return i = 4;
			default: return i;
		}
	}
	
	public enum LootType
	{
		COMMON, RARE, VERY_RARE;
	}
	
	//We don't want to save anything since it's just for debug and could cause some weird stuff
	@Override
	public IBlockState getStateFromMeta(int meta)
	{
		return this.getDefaultState();
	}
	
	@Override
	public int getMetaFromState(IBlockState state) 
	{
		return 0;
	}
	
	@Override
	protected BlockStateContainer createBlockState()
	{
		return new BlockStateContainer(this, DEBUG);
	}
}

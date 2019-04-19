package com.toma.pubgmc.common;

import com.toma.pubgmc.common.blocks.PMCBlock;
import com.toma.pubgmc.util.IBuilder;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockBuilder implements IBuilder<PMCBlock>
{
	private String name;
	private Material blockMaterial;
	private SoundType soundType;
	private BlockRenderLayer renderLayer;
	private MapColor mapColor;
	private boolean opaque, fullCube;
	private int lightValue = 0;
	private AxisAlignedBB[] boxes;
	
	private BlockBuilder() {}
	
	public static BlockBuilder create(String name, Material material)
	{
		BlockBuilder builder = new BlockBuilder();
		builder.name = name;
		builder.blockMaterial = material;
		builder.initialize();
		return builder;
	}
	
	private void initialize()
	{
		mapColor = blockMaterial.getMaterialMapColor();
		renderLayer = BlockRenderLayer.SOLID;
		opaque = false;
		fullCube = false;
		boxes = new AxisAlignedBB[] {Block.FULL_BLOCK_AABB, Block.FULL_BLOCK_AABB};
	}
	
	public BlockBuilder renderType(BlockRenderLayer layerRender)
	{
		this.renderLayer = layerRender;
		return this;
	}
	
	public BlockBuilder mapColor(MapColor color)
	{
		this.mapColor = color;
		return this;
	}
	
	public BlockBuilder cubeRenders(boolean opaque, boolean fullCube)
	{
		this.opaque = opaque;
		this.fullCube = fullCube;
		return this;
	}
	
	public BlockBuilder light(int light)
	{
		this.lightValue = light;
		return this;
	}
	
	public BlockBuilder aabb(AxisAlignedBB aabb)
	{
		this.boxes = new AxisAlignedBB[] {aabb, aabb};
		return this;
	}
	
	public BlockBuilder aabb(AxisAlignedBB bounding, AxisAlignedBB collision)
	{
		this.boxes = new AxisAlignedBB[] {bounding, collision};
		return this;
	}
	
	public BlockBuilder soundType(SoundType type)
	{
		this.soundType = type;
		return this;
	}
	
	@Override
	public PMCBlock build()
	{
		checkInt(lightValue, 0, 15);
		checkNotNull(soundType);
		
		PMCBlock builtBlock = new PMCBlock(name, blockMaterial)
		{
			@Override
			public boolean isOpaqueCube(IBlockState state)
			{
				return opaque;
			}
			
			@Override
			public boolean isFullCube(IBlockState state)
			{
				return fullCube;
			}
			
			@Override
			public MapColor getMapColor(IBlockState state, IBlockAccess worldIn, BlockPos pos)
			{
				return mapColor;
			}
			
			@Override
			public BlockRenderLayer getBlockLayer()
			{
				return renderLayer;
			}
			
			@Override
			public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) 
			{
				return boxes[0];
			}
			
			@Override
			public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos)
			{
				return boxes[1];
			}
			
			@Override
			public SoundType getSoundType(IBlockState state, World world, BlockPos pos, Entity entity)
			{
				return soundType;
			}
		};
		
		return builtBlock;
	}
}

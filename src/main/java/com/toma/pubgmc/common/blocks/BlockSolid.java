package com.toma.pubgmc.common.blocks;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;

public class BlockSolid extends PMCBlock
{
	public BlockSolid(String name, Material material, SoundType sound, MapColor color)
	{
		super(name, material);
		setSoundType(sound);
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
}

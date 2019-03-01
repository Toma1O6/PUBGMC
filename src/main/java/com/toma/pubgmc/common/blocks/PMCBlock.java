package com.toma.pubgmc.common.blocks;

import java.util.Random;

import com.toma.pubgmc.Pubgmc;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class PMCBlock extends Block
{
	public PMCBlock(String name, Material material)
	{
		super(material);
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(Pubgmc.pmcblockstab);
		
		this.setHardness(0.7f);
	}
}

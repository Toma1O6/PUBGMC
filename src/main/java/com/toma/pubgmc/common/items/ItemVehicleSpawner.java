package com.toma.pubgmc.common.items;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.toma.pubgmc.common.entity.EntityVehicle;
import com.toma.pubgmc.common.entity.vehicles.EntityVehicleUAZ;
import com.toma.pubgmc.common.tileentity.TileEntityGunWorkbench;
import com.toma.pubgmc.common.tileentity.TileEntityGunWorkbench.CraftMode;
import com.toma.pubgmc.init.PMCRegistry.PMCItems;
import com.toma.pubgmc.util.ICraftable;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class ItemVehicleSpawner extends PMCItem implements ICraftable
{
	private Vehicles car;
	
	public ItemVehicleSpawner(String name, Vehicles vehicle)
	{
		super(name);
		this.car = vehicle;
		TileEntityGunWorkbench.OTHER.add(this);
	}
	
	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) 
	{
		ItemStack stack = player.getHeldItem(hand);
		
		if(!worldIn.isRemote)
		{
			car.spawnEntity(worldIn, pos);
			
			if(!player.capabilities.isCreativeMode)
			{
				stack.shrink(1);
			}
		}
		return EnumActionResult.PASS;
	}
	
	private String formattedInfo(String s1, String value)
	{
		return TextFormatting.GRAY + s1 + ": " + TextFormatting.YELLOW + value;
	}
	
	public enum Vehicles
	{
		UAZ;
		
		public void spawnEntity(World world, BlockPos pos)
		{
			EntityVehicle vehicle = null;
			switch(this)
			{
				case UAZ: vehicle = new EntityVehicleUAZ(world, pos.getX(), pos.getY() + 1, pos.getZ()); break;
				default: break;
			}
			
			if(!world.isRemote)
			{
				if(vehicle == null)
				{
					throw new IllegalArgumentException("Fatal error occured while spawning vehicle!");
				}
				
				world.spawnEntity(vehicle);
			}
		}
	}
	
	@Override
	public List<ItemStack> getCraftingRecipe() 
	{
		List<ItemStack> rec = new ArrayList<ItemStack>();
		rec.clear();
		
		if(this == PMCItems.VEHICLE_UAZ)
		{
			rec.add(new ItemStack(PMCItems.STEEL_INGOT, 50));
			rec.add(new ItemStack(Blocks.IRON_BLOCK, 10));
			rec.add(new ItemStack(Items.LEATHER, 20));
			rec.add(new ItemStack(Blocks.GLASS, 10));
			rec.add(new ItemStack(Items.REDSTONE, 40));
			rec.add(new ItemStack(Items.GOLD_INGOT, 45));
			return rec;
		}
		
		return Collections.EMPTY_LIST;
	}
	
	@Override
	public CraftMode getCraftMode()
	{
		return CraftMode.OTHER;
	}
}

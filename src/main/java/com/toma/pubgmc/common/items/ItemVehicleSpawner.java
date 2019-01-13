package com.toma.pubgmc.common.items;

import com.toma.pubgmc.common.entity.EntityVehicle;
import com.toma.pubgmc.util.VehicleSpawnerRegistry;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemVehicleSpawner extends PMCItem
{
	private int vehicleID;
	
	public ItemVehicleSpawner(String name, int vehicleID)
	{
		super(name);
		setHasSubtypes(true);
	}
	
	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) 
	{
		/*ItemStack stack = player.getHeldItem(hand);
		int meta = this.getMetadata(stack);
		
		if(!worldIn.isRemote)
		{
			EntityVehicle vehicle = EntityVehicle.getVehicleByID(meta, worldIn, pos);
			vehicle.setMaxHealth(100f);
			worldIn.spawnEntity(vehicle);
			
			if(!player.capabilities.isCreativeMode)
			{
				stack.shrink(1);
			}
			
			return EnumActionResult.SUCCESS;
		}*/
		
		return EnumActionResult.PASS;
	}
	
	@Override
	public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items)
	{
		if(this.isInCreativeTab(tab))
		{
			for(int i = 0; i < VehicleSpawnerRegistry.getVehicleRegistry().size(); i++)
			{
				items.add(new ItemStack(this, 1, VehicleSpawnerRegistry.getVehicleRegistry().get(i).getVehicleID()));
			}
		}
	}
}

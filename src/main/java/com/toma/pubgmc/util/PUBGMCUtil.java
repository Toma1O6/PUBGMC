package com.toma.pubgmc.util;

import com.toma.pubgmc.common.network.PacketHandler;
import com.toma.pubgmc.common.network.sp.PacketSound;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.network.NetworkRegistry.TargetPoint;

public class PUBGMCUtil 
{
	/**
	 * This class contains some basic helper functions for me
	 */
	
	/**
	 * @param stack
	 * @return the stack NBT
	 */
	public static NBTTagCompound createNBT(ItemStack stack)
	{
		if(!stack.hasTagCompound())
		{
			stack.setTagCompound(new NBTTagCompound());
		}
		
		return stack.getTagCompound();
	}
	
	/**
	 * Validates stack as a weapon
	 * @param stack
	 * @param maxAmmo - the ammo the weapon will get
	 * @return new NBT tag
	 */
	public static NBTTagCompound createWeaponNBT(ItemStack stack, int maxAmmo)
	{
		NBTTagCompound compound = new NBTTagCompound();
		compound.setInteger("ammo", maxAmmo);
		compound.setBoolean("isValidWeapon", true);
		stack.setTagCompound(compound);
		return compound;
	}
	
	public static NBTTagCompound clearNBTTag(ItemStack stack)
	{
		NBTTagCompound compound = new NBTTagCompound();
		stack.setTagCompound(compound);
		return compound;
	}
	
	public static void sendSoundPacket(SoundEvent event, float volume, float pitch, BlockPos pos, TargetPoint target)
	{
		PacketHandler.INSTANCE.sendToAllAround(new PacketSound(event, volume, 1f, pos.getX(), pos.getY(), pos.getZ()), target);
	}
}

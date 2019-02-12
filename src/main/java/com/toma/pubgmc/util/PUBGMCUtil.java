package com.toma.pubgmc.util;

import com.toma.pubgmc.common.capability.IGameData;
import com.toma.pubgmc.common.entity.EntityVehicle;
import com.toma.pubgmc.common.network.PacketHandler;
import com.toma.pubgmc.common.network.sp.PacketSound;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
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
		PacketHandler.INSTANCE.sendToAllAround(new PacketSound(event, volume, pitch, pos.getX(), pos.getY(), pos.getZ()), target);
	}
	
	public static boolean shouldSendCommandFeedback(World world)
	{
		return world.getGameRules().getBoolean("sendCommandFeedback");
	}
	
	public static boolean isPlayerDrivingVehicle(EntityPlayer player)
	{
		return player.getRidingEntity() instanceof EntityVehicle;
	}
	
	public static boolean isPlayerDriverOfVehicle(EntityPlayer player)
	{
		return player.getRidingEntity() instanceof EntityVehicle && player.getRidingEntity().getPassengers().get(0) == player;
	}
	
	public static boolean isValidNumber(String text)
	{
		char[] num = text.toCharArray();
		boolean valid = true;
		if(num[0] == '-' || Character.isDigit(num[0]))
		{
			for(int i = 0; i < num.length; i++)
			{	
				if(i > 0)
				{
					if(Character.isDigit(num[i]))
					{
						continue;
					}
					
					else valid = false;
				}
			}
		}
		return valid;
	}
	
	public static boolean isStringDoubleOrFloat(String text)
	{
		char[] c = text.toCharArray();
		boolean valid = true;
		boolean alreadyUsedDot = false;
		for(int i = 0; i < c.length; i++)
		{
			if(Character.isDigit(c[i]) || c[i] == '.')
			{	
				if(alreadyUsedDot && c[i] == '.')
				{
					valid = false;
				}
				
				if(c[i] == '.' && !alreadyUsedDot)
				{
					alreadyUsedDot = true;
				}
				
				continue;
			}
			
			else
			{
				valid = false;
			}
		}
		
		return valid;
	}
	
	public static double getZoneSizeMultiplier(IGameData data)
	{
		int zone = data.getCurrentZone();
		int zoneModifier = 100 / data.getZonePhaseCount();
		
		return (100 - zone*zoneModifier + 5)/100;
	}
	
	public static int getZoneTimer(IGameData data)
	{
		int mapSize = data.getMapSize();
		int zoneCount = data.getZonePhaseCount();
		int zone = data.getCurrentZone();
		
		return mapSize * (100/zoneCount);
	}
	
	public static double getDistanceToBlockPos(BlockPos pos1, BlockPos pos2)
	{
		return Math.sqrt(sqr(Math.abs(pos1.getX() - pos2.getX())) + sqr(Math.abs(pos1.getZ() - pos2.getZ())));
	}
	
	public static double sqr(double num)
	{
		return num*num;
	}
}

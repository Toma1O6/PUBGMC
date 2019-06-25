package com.toma.pubgmc.util;

import org.apache.commons.lang3.RandomStringUtils;

import com.toma.pubgmc.Pubgmc;
import com.toma.pubgmc.common.capability.IGameData;
import com.toma.pubgmc.common.entity.EntityAirdrop;
import com.toma.pubgmc.common.entity.EntityVehicle;
import com.toma.pubgmc.common.network.PacketHandler;
import com.toma.pubgmc.common.network.sp.PacketSound;

import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
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
	
	public static void writeBasicEntityNBT(NBTTagCompound compound, Entity entity)
	{
		compound.setDouble("posX", entity.posX);
		compound.setDouble("posY", entity.posY);
		compound.setDouble("posZ", entity.posZ);
		compound.setDouble("motionX", entity.motionX);
		compound.setDouble("motionY", entity.motionY);
		compound.setDouble("motionZ", entity.motionZ);
	}
	
	public static void readBasicEntityNBT(NBTTagCompound compound, Entity entity)
	{
		entity.posX = compound.getDouble("posX");
		entity.posY = compound.getDouble("posY");
		entity.posZ = compound.getDouble("posZ");
		entity.motionX = compound.getDouble("motionX");
		entity.motionY = compound.getDouble("motionY");
		entity.motionZ = compound.getDouble("motionZ");
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
	
	/** Position calculated between X and Z coordinate of given positions **/
	public static double getDistanceToBlockPos(BlockPos pos1, BlockPos pos2)
	{
		return Math.sqrt(sqr(Math.abs(pos1.getX() - pos2.getX())) + sqr(Math.abs(pos1.getZ() - pos2.getZ())));
	}
	
	/** Position calculated between [xyz] of pos1 and pos2 **/
	public static double getDistanceToBlockPos3D(BlockPos pos1, BlockPos pos2)
	{
		return Math.sqrt(sqr(Math.sqrt(sqr(Math.abs(pos1.getX() - pos2.getX())) + sqr(Math.abs(pos1.getZ() - pos2.getZ())))) + sqr(Math.abs(pos1.getY() - pos2.getY())));
	}
	
	public static boolean isMapSetupProperly(IGameData data)
	{
		boolean properSize = data.getMapSize() > 0;
		boolean hasLocations = !data.getSpawnLocations().isEmpty();
		
		if(!properSize) Pubgmc.logger.error("Ivalid map size, setup your map!");
		if(!hasLocations) Pubgmc.logger.warn("No locations, add some! (Plane won't spawn)");
		
		return properSize;
	}
	
	public static float getAngleBetween2Points(Entity entityToRotate, BlockPos targetPos)
	{
		float angle = (float) (MathHelper.atan2(entityToRotate.posZ - targetPos.getZ(), entityToRotate.posX - targetPos.getX()) * (180D / Math.PI)) - 90f;
		return angle;
	}
	
	public static float updateRotation(float prevRotation, float additionalRotation)
	{
		float f = MathHelper.wrapDegrees(additionalRotation - prevRotation);
		return f;
	}
	
	public static void updateEntityRotation(Entity entity, BlockPos targetPos)
	{
		entity.rotationYaw = updateRotation(entity.rotationYaw, getAngleBetween2Points(entity, targetPos));
	}
	
	public static double sqr(double num)
	{
		return num*num;
	}
	
	public static Vec3d getPositionVec(Entity entity)
	{
		return new Vec3d(entity.posX, entity.posY, entity.posZ);
	}
	
	public static Vec3d getMotionVec(Entity entity)
	{
		Vec3d base = getPositionVec(entity);
		return new Vec3d(base.x + entity.motionX, base.y + entity.motionY, base.z + entity.motionZ);
	}
	
	public static String generateID(int length)
	{
		return RandomStringUtils.random(length, true, true);
	}
	
	public static void setModelPosition(ModelRenderer model, float x, float y, float z)
	{
		model.offsetX = x;
		model.offsetY = y;
		model.offsetZ = z;
	}
	
	public static void setModelRotation(ModelRenderer model, float x, float y, float z)
	{
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}
	
	public static void spawnAirdrop(World world, BlockPos pos, boolean bigDrop)
	{
		if(!world.isRemote && world.isBlockLoaded(pos)) {
			EntityAirdrop drop = new EntityAirdrop(world, pos, bigDrop);
			world.spawnEntity(drop);
		}
	}
	
	public static float interpolate(float prev, float current, float partial) 
	{
		return prev + partial * (current - prev);
	}
	
	public static Vec3d multiply(Vec3d vec, double amount) {
		return new Vec3d(vec.x*amount, vec.y*amount, vec.z*amount);
	}
}

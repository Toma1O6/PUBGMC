package com.toma.pubgmc.common.capability;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;

import com.google.common.primitives.Ints;
import com.toma.pubgmc.client.renderer.WeaponTEISR;
import com.toma.pubgmc.common.items.guns.GunBase.GunType;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

public interface IWorldData
{
	public void toggleAirdropWeapons(boolean enable);
	public boolean hasAirdropWeapons();
	public void toggleAmmoLoot(boolean enableAmmo);
	public boolean isAmmoLootEnabled();
	public void toggleRandomAmmoCount(boolean randomAmmo);
	public boolean isRandomAmmoCountEnabled();
	public void setLootChanceMultiplier(double chance);
	public double getLootChanceMultiplier();
	public void setWeaponList(int[] enumIDs);
	public void addWeaponTypeToLootGeneration(GunType typeToAdd);
	public void removeWeaponTypeFromLootGeneration(GunType typeToRemove);
	public List<GunType> getWeaponList();
	public void resetWeaponLootGeneration();
	
	public class WorldDataStorage implements IStorage<IWorldData>
	{
		@Override
		public NBTBase writeNBT(Capability<IWorldData> cap, IWorldData i, EnumFacing side)
		{
			NBTTagCompound c = new NBTTagCompound();
			c.setBoolean("airdropWeapons", i.hasAirdropWeapons());
			c.setBoolean("ammoLoot", i.isAmmoLootEnabled());
			c.setBoolean("randomAmmo", i.isRandomAmmoCountEnabled());
			c.setDouble("chance", i.getLootChanceMultiplier());
			
			return c;
		}
		
		@Override
		public void readNBT(Capability<IWorldData> capability, IWorldData instance, EnumFacing side, NBTBase nbt) 
		{
			instance.toggleAirdropWeapons(((NBTTagCompound)nbt).getBoolean("airdropWeapons"));
			instance.toggleAmmoLoot(((NBTTagCompound)nbt).getBoolean("ammoLoot"));
			instance.toggleRandomAmmoCount(((NBTTagCompound)nbt).getBoolean("randomAmmo"));
			instance.setLootChanceMultiplier(((NBTTagCompound)nbt).getDouble("chance"));
		}
	}
	
	public class WorldData implements IWorldData
	{
		private boolean airdropWep, ammoLoot, randomAmmoCount;
		private double chance;
		private List<GunType> weaponTypes = new ArrayList<GunType>();
		
		@Override
		public void toggleAirdropWeapons(boolean enable)
		{
			this.airdropWep = enable;
		}
		
		@Override
		public boolean hasAirdropWeapons()
		{
			return airdropWep;
		}
		
		@Override
		public void toggleAmmoLoot(boolean enableAmmo)
		{
			this.ammoLoot = enableAmmo;
		}
		
		@Override
		public boolean isAmmoLootEnabled()
		{
			return ammoLoot;
		}
		
		@Override
		public void toggleRandomAmmoCount(boolean randomAmmo)
		{
			this.randomAmmoCount = randomAmmo;
		}
		
		@Override
		public boolean isRandomAmmoCountEnabled()
		{
			return randomAmmoCount;
		}
		
		@Override
		public void setLootChanceMultiplier(double chance)
		{
			this.chance = chance;
		}
		
		@Override
		public double getLootChanceMultiplier()
		{
			return chance;
		}
		
		@Override
		public void setWeaponList(int[] enumIDs)
		{
			if(weaponTypes != null && !weaponTypes.isEmpty())
			{
				weaponTypes.clear();
				for(int i = 0; i < enumIDs.length; i++)
				{
					GunType type = GunType.values()[i];
					if(!weaponTypes.contains(type))
					{
						weaponTypes.add(type);
					}
				}
			}
		}
		
		@Override
		public void addWeaponTypeToLootGeneration(GunType typeToAdd)
		{
			if(!weaponTypes.contains(typeToAdd))
				weaponTypes.add(typeToAdd);
		}
		
		@Override
		public void removeWeaponTypeFromLootGeneration(GunType typeToRemove)
		{
			if(weaponTypes.contains(typeToRemove))
				weaponTypes.remove(typeToRemove);
		}
		
		@Override
		public List<GunType> getWeaponList()
		{
			return weaponTypes;
		}
		
		@Override
		public void resetWeaponLootGeneration() 
		{
			weaponTypes.clear();
			for(int i = 0; i < GunType.values().length; i++)
			{
				weaponTypes.add(GunType.values()[i]);
			}
			
			//serves no purpose for loot gen
			if(weaponTypes.contains(GunType.LMG))
				removeWeaponTypeFromLootGeneration(GunType.LMG);
		}
	}
	
	public class WorldDataProvider implements ICapabilitySerializable<NBTBase>
	{
		@CapabilityInject(IWorldData.class)
		public static final Capability<IWorldData> WORLD_DATA = null;
		
		private IWorldData instance = WORLD_DATA.getDefaultInstance();
		
		@Override
		public boolean hasCapability(Capability<?> capability, EnumFacing facing)
		{
			return capability == WORLD_DATA;
		}
		
		@Override
		public <T> T getCapability(Capability<T> capability, EnumFacing facing)
		{
			return capability == WORLD_DATA ? WORLD_DATA.<T>cast(instance) : null;
		}
		
		@Override
		public NBTBase serializeNBT() 
		{
			return WORLD_DATA.getStorage().writeNBT(WORLD_DATA, instance, null);
		}
		
		@Override
		public void deserializeNBT(NBTBase nbt)
		{
			WORLD_DATA.getStorage().readNBT(WORLD_DATA, instance, null, nbt);
		}
	}
}

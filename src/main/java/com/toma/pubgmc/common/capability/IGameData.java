package com.toma.pubgmc.common.capability;

import java.util.ArrayList;
import java.util.List;

import com.toma.pubgmc.world.PlayZone;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

/**
 * Preparation for customizable map zone etc
 */
public interface IGameData
{
	//TODO zone customization
	public void setPlaying(boolean play);
	public boolean isPlaying();
	public void addSpawnLocation(BlockPos pos, String name);
	public int getSpawnLocationCount();
	public void setSpawnLocationCount(int count);
	public void removeSpawnLocation(BlockPos pos);
	public List<BlockPos> getSpawnLocations();
	public List<String> getLocationNames();
	public void setMapCenter(int x, int z, int size);
	public BlockPos getMapCenter();
	public int getMapSize();
	
	//zones
	public void setZonePhaseCount(int count);
	public int getZonePhaseCount();
	public void addZonePhase(PlayZone zone);
	public PlayZone getZoneByID(int ID);
	public List<PlayZone> getAllZones();
	public void setZones(List<PlayZone> zones);
	public void setCurrentZone(int zone);
	public int getCurrentZone();
	
	public void setTimer(int time);
	public void increaseTimer();
	public void resetTimer();
	public int getTimer();
	
	
	public class GameDataStorage implements IStorage<IGameData>
	{
		@Override
		public void readNBT(Capability<IGameData> capability, IGameData instance, EnumFacing side, NBTBase nbt) 
		{
			instance.setPlaying(((NBTTagCompound)nbt).getBoolean("isPlaying"));
			int x = ((NBTTagCompound)nbt).getInteger("mapCenterX");
			int z = ((NBTTagCompound)nbt).getInteger("mapCenterZ");
			int size = ((NBTTagCompound)nbt).getInteger("mapSize");
			instance.setMapCenter(x, z, size);
			instance.setSpawnLocationCount(((NBTTagCompound)nbt).getInteger("locationsSize"));
			
			for(int i = 0; i < instance.getSpawnLocationCount(); i++)
			{
				int px = ((NBTTagCompound)nbt).getInteger("posX" + i);
				int py = ((NBTTagCompound)nbt).getInteger("posY" + i);
				int pz = ((NBTTagCompound)nbt).getInteger("posZ" + i);
				String name = ((NBTTagCompound)nbt).getString("locationName" + i);
				
				instance.addSpawnLocation(new BlockPos(px, py, pz), name);
			}
			
			instance.setZonePhaseCount(((NBTTagCompound)nbt).getInteger("phaseCount"));
			
			for(int i = 0; i < instance.getZonePhaseCount(); i++)
			{
				int ID = ((NBTTagCompound)nbt).getInteger("zoneID" + i);
				int startShrink = ((NBTTagCompound)nbt).getInteger("shrinkStart" + ID);
				int shrinkTime = ((NBTTagCompound)nbt).getInteger("shrinkTime" + ID);
				int finalSize = ((NBTTagCompound)nbt).getInteger("finalSize" + ID);
				float damage = ((NBTTagCompound)nbt).getInteger("zoneDamage" + ID);
				
				PlayZone zone = new PlayZone(ID);
				zone.setup(startShrink, shrinkTime, finalSize, damage);
				instance.addZonePhase(zone);
			}
			
			instance.setTimer(((NBTTagCompound)nbt).getInteger("timer"));
			instance.setCurrentZone(((NBTTagCompound)nbt).getInteger("currentZone"));
		}
		
		@Override
		public NBTBase writeNBT(Capability<IGameData> capability, IGameData instance, EnumFacing side)
		{
			NBTTagCompound c = new NBTTagCompound();
			
			if(instance.getMapCenter() == null)
			{
				instance.setMapCenter(0, 0, 0);
			}
			
			c.setBoolean("isPlaying", instance.isPlaying());
			c.setInteger("mapCenterX", instance.getMapCenter().getX());
			c.setInteger("mapCenterZ", instance.getMapCenter().getZ());
			c.setInteger("mapSize", instance.getMapSize());
			c.setInteger("locationsSize", instance.getSpawnLocationCount());
			
			for(int i = 0; i < instance.getSpawnLocationCount(); i++)
			{
				BlockPos pos = instance.getSpawnLocations().get(i);
				String name = instance.getLocationNames().get(i);
				c.setInteger("posX" + i, pos.getX());
				c.setInteger("posY" + i, pos.getY());
				c.setInteger("posZ" + i, pos.getZ());
				c.setString("locationName" + i, name);
			}
			
			c.setInteger("phaseCount", instance.getZonePhaseCount());
			
			for(int i = 0; i < instance.getZonePhaseCount(); i++)
			{
				PlayZone zone = instance.getAllZones().get(i);
				c.setInteger("zoneID" + i, zone.getID());
				c.setInteger("shrinkStart" + zone.getID(), zone.getStartOfShrinking());
				c.setInteger("shrinkTime" + zone.getID(), zone.getShrinkingTime());
				c.setInteger("finalSize" + zone.getID(), zone.getFinalSize());
				c.setFloat("zoneDamage" + zone.getID(), zone.getDamage());
			}
			
			c.setInteger("timer", instance.getTimer());
			c.setInteger("currentZone", instance.getCurrentZone());
			
			return c;
		}
	}
	
	public class GameData implements IGameData
	{
		boolean isPlaying;
		List<BlockPos> locations = new ArrayList<BlockPos>();
		List<String> names = new ArrayList<String>();
		int numLocations;
		BlockPos gameZoneCenter;
		int mapSize;
		List<PlayZone> zones = new ArrayList<PlayZone>();
		int zoneCount;
		int timer;
		int currentZone;
		
		@Override
		public void setPlaying(boolean play) 
		{
			this.isPlaying = play;
		}
		
		@Override
		public boolean isPlaying() 
		{
			return isPlaying;
		}
		
		@Override
		public void addSpawnLocation(BlockPos pos, String name)
		{
			locations.add(pos);
			names.add(name);
			numLocations++;
		}
		
		@Override
		public void removeSpawnLocation(BlockPos pos)
		{
			if(locations.contains(pos))
			{
				locations.remove(pos);
				numLocations--;
			}
		}
		
		@Override
		public int getSpawnLocationCount() 
		{
			return numLocations;
		}
		
		@Override
		public List<BlockPos> getSpawnLocations()
		{
			return locations;
		}
		
		@Override
		public void setMapCenter(int x, int z, int size)
		{
			gameZoneCenter = new BlockPos(x, 0, z);
			this.mapSize = size;
		}
		
		@Override
		public BlockPos getMapCenter()
		{
			return gameZoneCenter;
		}
		
		@Override
		public int getMapSize()
		{
			return mapSize;
		}
		
		@Override
		public List<String> getLocationNames()
		{
			return names;
		}
		
		@Override
		public void setSpawnLocationCount(int count)
		{
			this.numLocations = count;
		}
		
		@Override
		public void addZonePhase(PlayZone zone) 
		{
			if(zones.size() < 10)
				zones.add(zone);
		}
		
		@Override
		public List<PlayZone> getAllZones()
		{
			return zones;
		}
		
		@Override
		public PlayZone getZoneByID(int ID)
		{
			return zones.get(ID);
		}
		
		@Override
		public int getZonePhaseCount() 
		{
			return zoneCount;
		}
		
		@Override
		public void setZonePhaseCount(int count)
		{
			this.zoneCount = count;
		}
		
		@Override
		public void setZones(List<PlayZone> zones) 
		{
			this.zones = zones;
		}
		
		@Override
		public void setTimer(int time) 
		{
			this.timer = time;
		}
		
		@Override
		public void increaseTimer()
		{
			timer++;
		}
		
		@Override
		public void resetTimer()
		{
			timer = 0;
		}
		
		@Override
		public int getTimer() 
		{
			return timer;
		}
		
		@Override
		public int getCurrentZone()
		{
			return currentZone;
		}
		
		@Override
		public void setCurrentZone(int zone)
		{
			this.currentZone = zone;
		}
	}
	
	public class GameDataProvider implements ICapabilitySerializable<NBTBase>
	{
		@CapabilityInject(IGameData.class)
		public static final Capability<IGameData> GAMEDATA = null;
		
		private IGameData instance = GAMEDATA.getDefaultInstance();
		
		@Override
		public boolean hasCapability(Capability<?> capability, EnumFacing facing)
		{
			return capability == GAMEDATA;
		}
		
		@Override
		public <T> T getCapability(Capability<T> capability, EnumFacing facing)
		{
			return capability == GAMEDATA ? GAMEDATA.<T>cast(instance) : null;
		}
		
		@Override
		public NBTBase serializeNBT() 
		{
			return GAMEDATA.getStorage().writeNBT(GAMEDATA, instance, null);
		}
		
		@Override
		public void deserializeNBT(NBTBase nbt)
		{
			GAMEDATA.getStorage().readNBT(GAMEDATA, instance, null, nbt);
		}
	}
}

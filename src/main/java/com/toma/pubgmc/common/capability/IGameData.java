package com.toma.pubgmc.common.capability;

import java.util.ArrayList;
import java.util.List;

import com.toma.pubgmc.util.PUBGMCUtil;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTUtil;
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
	public void setMapCenter(double x, double z, int size, int count);
	public BlockPos getMapCenter();
	public int getMapSize();
	
	//zones
	public void setZonePhaseCount(int count);
	public int getZonePhaseCount();
	public void setCurrentZone(int zone);
	public int getCurrentZone();
	
	public void setTimer(int time);
	public void increaseTimer();
	public void resetTimer();
	public int getTimer();
	
	public void setGameID(String id);
	public String getGameID();
	public void createGameID();
	
	
	public class GameDataStorage implements IStorage<IGameData>
	{
		@Override
		public void readNBT(Capability<IGameData> capability, IGameData instance, EnumFacing side, NBTBase nbt) 
		{
			instance.setPlaying(((NBTTagCompound)nbt).getBoolean("isPlaying"));
			double x = ((NBTTagCompound)nbt).getDouble("mapCenterX");
			double z = ((NBTTagCompound)nbt).getDouble("mapCenterZ");
			int size = ((NBTTagCompound)nbt).getInteger("mapSize");
			int zones = ((NBTTagCompound)nbt).getInteger("zoneCount");
			instance.setMapCenter(x, z, size, zones);
			instance.setSpawnLocationCount(((NBTTagCompound)nbt).getInteger("locationsSize"));
			instance.setTimer(((NBTTagCompound)nbt).getInteger("timer"));
			instance.setCurrentZone(((NBTTagCompound)nbt).getInteger("currentZone"));
			
			NBTTagList list = ((NBTTagCompound)nbt).getTagList("list", 10);
			
			for(int i = 0; i < list.tagCount(); i++)
			{
				instance.addSpawnLocation(NBTUtil.getPosFromTag((NBTTagCompound)list.get(i)), ((NBTTagCompound)nbt).getString("name" + i));
			}
			
			instance.setGameID(((NBTTagCompound)nbt).getString("gameID"));
		}
		
		@Override
		public NBTBase writeNBT(Capability<IGameData> capability, IGameData instance, EnumFacing side)
		{
			NBTTagCompound c = new NBTTagCompound();
			NBTTagList list = new NBTTagList();
			
			if(instance.getMapCenter() == null)
			{
				instance.setMapCenter(0, 0, 0, 1);
			}
			
			c.setBoolean("isPlaying", instance.isPlaying());
			c.setDouble("mapCenterX", instance.getMapCenter().getX());
			c.setDouble("mapCenterZ", instance.getMapCenter().getZ());
			c.setInteger("mapSize", instance.getMapSize());
			c.setInteger("zoneCount", instance.getZonePhaseCount());
			c.setInteger("locationsSize", instance.getSpawnLocationCount());
			c.setInteger("timer", instance.getTimer());
			c.setInteger("currentZone", instance.getCurrentZone());
			
			for(int i = 0; i < instance.getSpawnLocations().size(); i++)
			{
				list.appendTag(NBTUtil.createPosTag(instance.getSpawnLocations().get(i)));
				c.setString("name" + i, instance.getLocationNames().get(i));
			}
			
			c.setTag("list", list);
			c.setString("gameID", instance.getGameID());
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
		int zoneCount;
		int timer;
		int currentZone;
		String gameID;
		
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
		public void setMapCenter(double x, double z, int size, int count)
		{
			gameZoneCenter = new BlockPos(x, 0d, z);
			this.mapSize = size;
			this.zoneCount = count;
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
		
		@Override
		public void setGameID(String id)
		{
			this.gameID = id;
		}
		
		@Override
		public String getGameID() 
		{
			return gameID == null || gameID.isEmpty() ? "undefined" : gameID;
		}
		
		@Override
		public void createGameID() 
		{
			gameID = PUBGMCUtil.generateID(16);
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

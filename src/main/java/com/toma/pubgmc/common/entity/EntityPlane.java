package com.toma.pubgmc.common.entity;

import java.text.DecimalFormat;
import java.util.HashMap;

import com.toma.pubgmc.Pubgmc;
import com.toma.pubgmc.common.capability.IGameData;
import com.toma.pubgmc.common.capability.IPlayerData;
import com.toma.pubgmc.common.capability.IPlayerData.PlayerDataProvider;
import com.toma.pubgmc.common.network.PacketHandler;
import com.toma.pubgmc.common.network.sp.PacketUpdatePlayerRotation;
import com.toma.pubgmc.common.capability.IGameData.GameDataProvider;
import com.toma.pubgmc.util.PUBGMCUtil;
import com.toma.pubgmc.util.handlers.ConfigHandler;

import net.minecraft.entity.Entity;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class EntityPlane extends Entity
{
	public HashMap<EntityPlayer, BlockPos> dropLoc = new HashMap<EntityPlayer, BlockPos>();
	protected IGameData gameData;
	protected BlockPos endPos;
	private BlockPos startPos;
	private boolean hasReachedDestination;
	private short timeSinceDestination;
	private boolean canFly;
	
	public EntityPlane(World world)
	{
		super(world);
		setSize(15f, 5f);
		this.ignoreFrustumCheck = true;
	}
	
	public EntityPlane(World world, IGameData data)
	{
		this(world);
		gameData = data;
		
		this.onCreated();
	}
	
	@Override
	public double getMountedYOffset() 
	{
		return 2.3;
	}
	
	@Override
	protected boolean canFitPassenger(Entity passenger)
	{
		return this.getPassengers().size() < 32;
	}
	
	@Override
	public void updatePassenger(Entity passenger)
	{
		if(this.isPassenger(passenger))
		{
			if(!this.getPassengers().isEmpty())
			{
				float f1 = (float)((this.isDead ? 0.009999999776482582D : this.getMountedYOffset()) + passenger.getYOffset());
				int id = this.getPassengers().indexOf(passenger);
				float x = id >= 16 ? -6 + (id-16) *3 : -6 + id*3;
				float z = id < 16 ? 3 : -3;
	            Vec3d vec3d = (new Vec3d((double)x, 0.0D, (double)z)).rotateYaw(-this.rotationYaw * 0.017453292F - ((float)Math.PI / 2F));
	            passenger.setPosition(this.posX + vec3d.x, this.posY + (double)f1, this.posZ + vec3d.z);
			}
		}
	}
	
	@Override
	public boolean isInRangeToRenderDist(double distance)
	{
		return true;
	}
	
	@Override
	public boolean isInRangeToRender3d(double x, double y, double z)
	{
		return true;
	}
	
	//Responsible for setting right position and facing direction
	public void onCreated()
	{
		dropLoc.clear();
		BlockPos mapCenter = gameData.getMapCenter();
		short startSide = (short)rand.nextInt(4);
		
		int startX = 0;
		int startZ = 0;
		int endX = 0;
		int endZ = 0;
		
		switch(startSide)
		{
			//NORTH side of map
			case 0:
			{
				startX = edgeCoordinate(mapCenter.getX(), false);
				startZ = generateRandomPosition(mapCenter.getZ());
				endX = edgeCoordinate(mapCenter.getX(), true);
				endZ = generateRandomPosition(mapCenter.getZ());
				break;
			}
			
			//WEST side of map
			case 1: 
			{
				startZ = edgeCoordinate(mapCenter.getZ(), true);
				endZ = edgeCoordinate(mapCenter.getZ(), false);
				startX = generateRandomPosition(mapCenter.getX());
				endX = generateRandomPosition(mapCenter.getX());
				break;
			}
			
			//SOUTH side of map
			case 2:
			{
				startX = edgeCoordinate(mapCenter.getX(), true);
				startZ = generateRandomPosition(mapCenter.getZ());
				endX = edgeCoordinate(mapCenter.getX(), false);
				endZ = generateRandomPosition(mapCenter.getZ());
				break;
			}
			
			//EAST side of map
			case 3:
			{
				startZ = edgeCoordinate(mapCenter.getZ(), false);
				endZ = edgeCoordinate(mapCenter.getZ(), true);
				startX = generateRandomPosition(mapCenter.getX());
				endX = generateRandomPosition(mapCenter.getX());
				break;
			}
		}
		
		endPos = new BlockPos(endX, ConfigHandler.planeHeight, endZ);
		setPosition(startX, ConfigHandler.planeHeight, startZ);
		startPos = new BlockPos(startX, ConfigHandler.planeHeight, startZ);
		rotationYaw = 180f;
		updateHeading(startX, startZ, endPos);
		
		for(EntityPlayer player : world.playerEntities)
		{
			if(player.hasCapability(PlayerDataProvider.PLAYER_DATA, null))
			{
				player.getCapability(PlayerDataProvider.PLAYER_DATA, null).setDistance(Double.MAX_VALUE);;
			}
		}
		
		Pubgmc.logger.info("Spawning plane with start position of " + startX + ", 256, " + startZ
				+ " and end position of " + endX + ", 256, " + endZ + ".");
	}
	
	@Override
	public void onUpdate()
	{
		super.onUpdate();
		
		canFly = this.ticksExisted >= (ConfigHandler.planeWaitTime * 20);
		
		if(gameData == null)
		{
			Pubgmc.logger.fatal("Couldn't load gamedata for plane, getting new instance...");
			gameData = world.hasCapability(GameDataProvider.GAMEDATA, null) ? world.getCapability(GameDataProvider.GAMEDATA, null) : null;
		}
		
		if(canFly)
		{
			if(!world.isRemote)
			{
				hasReachedDestination = Math.abs(posX - endPos.getX()) < 10 && Math.abs(posZ - endPos.getZ()) < 10;
				
				if(hasReachedDestination)
				{
					++timeSinceDestination;
					
					if(this.isBeingRidden())
					{
						removePassengers();
					}
					
					if(timeSinceDestination >= 50)
					{
						setDead();
					}
				}
				
				if(!gameData.isPlaying()) setDead();
				
				if(motionX == 0 && motionZ == 0 && ticksExisted >= ConfigHandler.planeWaitTime * 20 + 15)
				{
					setDead();
					Pubgmc.logger.error("Plane is in invalid position, despawning...");
				}
				
				if(!this.getPassengers().isEmpty() && ticksExisted % 10 == 0 && !this.dropLoc.isEmpty())
				{
					for(Entity e : this.getPassengers())
					{
						if(e instanceof EntityPlayer)
						{
							EntityPlayer player = (EntityPlayer)e;
							
							if(dropLoc.containsKey(player))
							{
								BlockPos pos = dropLoc.get(player);
								IPlayerData data = player.getCapability(PlayerDataProvider.PLAYER_DATA, null);

								if(PUBGMCUtil.getDistanceToBlockPos(getPosition(), pos) < data.getDistance())
								{
									data.setDistance(PUBGMCUtil.getDistanceToBlockPos(getPosition(), pos));
								}
								
								else
								{
									final DecimalFormat num = new DecimalFormat("####.##");
									dropLoc.remove(player);
									player.dismountRidingEntity();
									setPlayerFaceTheirDropLocation(player, pos);
									player.sendMessage(new TextComponentString(TextFormatting.BOLD + "" + TextFormatting.GREEN + "You have been dropped closest to your selected location."));
									player.sendMessage(new TextComponentString(TextFormatting.GREEN + "Distance on map to your location: " + num.format(PUBGMCUtil.getDistanceToBlockPos(player.getPosition(), pos)) + " m"));
									player.sendMessage(new TextComponentString(TextFormatting.GREEN + "Actual distance: " + num.format(PUBGMCUtil.getDistanceToBlockPos3D(player.getPosition(), pos)) + " m"));
								}
							}
						}
					}
				}
			}
			
			Vec3d look = getLookVec();
			float f = this.getMovementMultiplier();
			motionX = look.x * 0.4d * f;
			motionZ = look.z * 0.4d * f;
			
			move(MoverType.SELF, motionX, motionY, motionZ);
		}
	}
	
	private float getMovementMultiplier()
	{
		float f = gameData.getMapSize() / 500f;
		f = f < 1 ? 1f : f;
		return f;
	}
	
	@Override
	protected void entityInit() {}
	
	@Override
	protected void writeEntityToNBT(NBTTagCompound compound)
	{
		compound.setDouble("posX", posX);
		compound.setDouble("posY", posY);
		compound.setDouble("posZ", posZ);
		compound.setDouble("motX", motionX);
		compound.setDouble("motY", motionY);
		compound.setDouble("motZ", motionZ);
		compound.setFloat("rotYaw", rotationYaw);
		compound.setDouble("endX", endPos.getX());
		compound.setDouble("endZ", endPos.getZ());
		compound.setBoolean("reachedDest", hasReachedDestination);
		compound.setShort("timeSinceDest", timeSinceDestination);
		compound.setUniqueId("UUID", getUniqueID());
	}
	
	@Override
	protected void readEntityFromNBT(NBTTagCompound compound)
	{
		posX = compound.getDouble("posX");
		posY = compound.getDouble("posY");
		posZ = compound.getDouble("posZ");
		motionX = compound.getDouble("motX");
		motionY = compound.getDouble("motY");
		motionZ = compound.getDouble("motZ");
		rotationYaw = compound.getFloat("rotYaw");
		endPos = new BlockPos(compound.getDouble("endX"), ConfigHandler.planeHeight, compound.getDouble("endZ"));
		hasReachedDestination = compound.getBoolean("reachedDest");
		timeSinceDestination = compound.getShort("timeSinceDest");
		setUniqueId(compound.getUniqueId("UUID"));
	}
	
	private void updateHeading(int x, int z, BlockPos endPos)
	{
		PUBGMCUtil.updateEntityRotation(this, endPos);
	}
	
	protected static void setPlayerFaceTheirDropLocation(EntityPlayer player, BlockPos finalDest)
	{
		player.rotationYaw = 0f;
		PUBGMCUtil.updateEntityRotation(player, finalDest);
		player.rotationYaw += 180f;
		
		if(player instanceof EntityPlayerMP)
		{
			PacketHandler.sendToClient(new PacketUpdatePlayerRotation(player.rotationYaw), (EntityPlayerMP)player);
		}
	}
	
	private int edgeCoordinate(int center, boolean endPos)
	{
		return endPos ? center + gameData.getMapSize() - 15 : center - gameData.getMapSize() + 15;
	}
	
	private int generateRandomPosition(int center)
	{
		return center - gameData.getMapSize() + 15 + rand.nextInt(gameData.getMapSize() * 2 - 30);
	}
	
	public BlockPos getStartingPosition()
	{
		return startPos;
	}
}

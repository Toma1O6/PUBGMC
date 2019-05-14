package com.toma.pubgmc.common.entity;

import com.toma.pubgmc.ConfigPMC;
import com.toma.pubgmc.common.tileentity.IAirdropTileEntity;
import com.toma.pubgmc.init.PMCRegistry.PMCBlocks;
import com.toma.pubgmc.util.PUBGMCUtil;

import io.netty.buffer.ByteBuf;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.MoverType;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;

public class EntityAirdrop extends Entity implements IEntityAdditionalSpawnData
{
	private byte type;
	
	public EntityAirdrop(World world)
	{
		super(world);
		this.setSize(1f, 1f);
		type = 0;
	}
	
	public EntityAirdrop(World world, BlockPos pos, byte type)
	{
		this(world);
		this.setPosition(pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5);
		this.type = type;
	}
	
	@Override
	public void onUpdate()
	{
		super.onUpdate();
		this.handleMotion(0.15);
		this.move(MoverType.SELF, motionX, motionY, motionZ);
	}
	
	public void onEntityLanded()
	{
		IBlockState state = type == 1 ? PMCBlocks.BIG_AIRDROP.getDefaultState() : PMCBlocks.AIRDROP.getDefaultState();
		world.setBlockState(this.getPosition(), state, 3);
		
		if(world.getTileEntity(this.getPosition()) instanceof IAirdropTileEntity && ConfigPMC.common.worldSettings.airdropLootGen > 0)
		{
			((IAirdropTileEntity)world.getTileEntity(getPosition())).generateLoot();
		}
	}
	
	public byte getType()
	{
		return type;
	}
	
	@Override
	protected void readEntityFromNBT(NBTTagCompound compound)
	{
		PUBGMCUtil.readBasicEntityNBT(compound, this);
		compound.setByte("dropType", type);
	}
	
	@Override
	protected void writeEntityToNBT(NBTTagCompound compound)
	{
		PUBGMCUtil.writeBasicEntityNBT(compound, this);
		type = compound.getByte("dropType");
	}
	
	@Override
	public void readSpawnData(ByteBuf buf)
	{
		type = buf.readByte();
	}
	
	@Override
	public void writeSpawnData(ByteBuf buffer)
	{
		buffer.writeByte(type);
	}
	
	@Override
	protected void entityInit() {}
	
	private void handleMotion(double motion)
	{
		if(!onGround) {
			motionY = -motion;
		}
		else {
			if(!world.isRemote)
				this.onEntityLanded();
			
			this.setDead();
		}
	}
}

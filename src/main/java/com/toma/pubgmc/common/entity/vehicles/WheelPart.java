package com.toma.pubgmc.common.entity.vehicles;

import com.toma.pubgmc.common.entity.EntityVehicle;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.IEntityMultiPart;
import net.minecraft.entity.MultiPartEntityPart;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;

public class WheelPart extends MultiPartEntityPart implements IEntityAdditionalSpawnData
{
	private Vec3d pos = Vec3d.ZERO;
	private final EntityVehicle parent;
	private IEntityMultiPart parentPart;
	private boolean isFlattened;
	private float health;
	
	public WheelPart(IEntityMultiPart parent, EntityVehicle vehicle, String name, Vec3d posRelatedToVehicle)
	{
		super(parent, name, 0.2f, 0.3f);
		this.parent = vehicle;
		this.pos = posRelatedToVehicle;
		isFlattened = false;
		health = 10f;
		setPosition(vehicle.posX, vehicle.posY, vehicle.posZ);
	}
	
	@Override
	public void onUpdate()
	{
		System.out.println();
		isFlattened = health <= 0;
		super.onUpdate();
		Vec3d vec = (new Vec3d(pos.x, pos.y, pos.z)).rotateYaw(-parent.rotationYaw * 0.017453292F - ((float)Math.PI / 2F));
		setPositionAndRotation(parent.posX + vec.x, parent.posY + vec.y, parent.posZ + vec.z, parent.rotationYaw, parent.rotationPitch);
	}
	
	@Override
	public boolean attackEntityFrom(DamageSource source, float amount)
	{
		health -= amount;
		return true;
	}
	
	public boolean isFlattened()
	{
		return isFlattened;
	}
	
	public void updatePos(Vec3d newPos)
	{
		this.pos = newPos;
	}
	
	@Override
	public void readSpawnData(ByteBuf b) 
	{
		isFlattened = b.readBoolean();
		health = b.readFloat();
	}
	
	@Override
	public void writeSpawnData(ByteBuf b)
	{
		b.writeBoolean(isFlattened);
		b.writeFloat(health);
	}
	
	@Override
	protected void writeEntityToNBT(NBTTagCompound compound)
	{
		super.writeToNBT(compound);
		compound.setBoolean("flattened", isFlattened);
		compound.setFloat("health", health);
	}
	
	@Override
	protected void readEntityFromNBT(NBTTagCompound compound)
	{
		super.readFromNBT(compound);
		isFlattened = compound.getBoolean("flattened");
		health = compound.getFloat("health");
	}
}

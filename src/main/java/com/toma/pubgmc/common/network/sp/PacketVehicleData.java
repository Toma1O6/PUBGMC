package com.toma.pubgmc.common.network.sp;

import com.toma.pubgmc.common.entity.EntityVehicle;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketVehicleData implements IMessage, IMessageHandler<PacketVehicleData, IMessage>
{
	private int ID;
	private float fuel, health, turnMod, currentSpeed;
	private boolean healthData, fuelData;
	
	public PacketVehicleData() {}
	
	public PacketVehicleData(EntityVehicle car)
	{
		ID = car.getEntityId();
		turnMod = car.turnModifier;
		currentSpeed = car.currentSpeed;
	}
	
	@Override
	public void toBytes(ByteBuf buf) 
	{
		buf.writeInt(ID);
		buf.writeFloat(currentSpeed);
		buf.writeFloat(turnMod);
		buf.writeBoolean(healthData);
		buf.writeBoolean(fuelData);
		
		if(healthData) buf.writeFloat(health);
		if(fuelData) buf.writeFloat(fuel);
	}
	
	@Override
	public void fromBytes(ByteBuf buf)
	{
		ID = buf.readInt();
		currentSpeed = buf.readFloat();
		turnMod = buf.readFloat();
		healthData = buf.readBoolean();
		fuelData = buf.readBoolean();
		
		if(healthData) health = buf.readFloat();
		if(fuelData) fuel = buf.readFloat();
	}
	
	@Override
	public IMessage onMessage(PacketVehicleData m, MessageContext ctx)
	{
		if(ctx.side.isClient())
		{
			Minecraft.getMinecraft().addScheduledTask(() ->
			{
				World world = Minecraft.getMinecraft().player.world;
				if(world.getEntityByID(m.ID) instanceof EntityVehicle)
				{
					EntityVehicle car = (EntityVehicle)world.getEntityByID(m.ID);
					car.currentSpeed = m.currentSpeed;
					car.turnModifier = m.turnMod;
					
					if(m.healthData) car.health = m.health;
					
					if(m.fuelData) car.fuel = m.fuel;
				}
			});
		}
		return null;
	}
	
	public PacketVehicleData health(float health)
	{
		healthData = true;
		this.health = health;
		return this;
	}
	
	public PacketVehicleData fuel(float fuel)
	{
		fuelData = true;
		this.fuel = fuel;
		return this;
	}
}

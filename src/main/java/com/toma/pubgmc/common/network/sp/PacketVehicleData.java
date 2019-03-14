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
	private boolean isBroken;
	
	public PacketVehicleData() {}
	
	public PacketVehicleData(EntityVehicle car)
	{
		ID = car.getEntityId();
		turnMod = car.turnModifier;
		currentSpeed = car.currentSpeed;
		fuel = car.fuel;
		health = car.health;
		isBroken = car.isBroken;
	}
	
	@Override
	public void toBytes(ByteBuf buf) 
	{
		buf.writeInt(ID);
		buf.writeFloat(currentSpeed);
		buf.writeFloat(turnMod);
		buf.writeFloat(health);
		buf.writeFloat(fuel);
		buf.writeBoolean(isBroken);
	}
	
	@Override
	public void fromBytes(ByteBuf buf)
	{
		ID = buf.readInt();
		currentSpeed = buf.readFloat();
		turnMod = buf.readFloat();
		health = buf.readFloat();
		fuel = buf.readFloat();
		isBroken = buf.readBoolean();
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
					car.health = m.health;
					car.fuel = m.fuel;
					car.isBroken = m.isBroken;
				}
			});
		}
		return null;
	}
}

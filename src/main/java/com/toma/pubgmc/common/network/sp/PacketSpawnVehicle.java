package com.toma.pubgmc.common.network.sp;

import com.toma.pubgmc.common.entity.EntityVehicle;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

/**
 * TODO: Implement variants - colors
 */
public class PacketSpawnVehicle implements IMessage, IMessageHandler<PacketSpawnVehicle, IMessage>
{
	int entityID;
	float maxHealth, maxSpeed, acceleration, turnSpeed, fuel;
	float health;
	
	@Override
	public void toBytes(ByteBuf buf)
	{
		buf.writeInt(entityID);
		buf.writeFloat(maxHealth);
		buf.writeFloat(maxSpeed);
		buf.writeFloat(acceleration);
		buf.writeFloat(turnSpeed);
		buf.writeFloat(fuel);
		buf.writeFloat(health);
	}
	
	@Override
	public void fromBytes(ByteBuf buf)
	{
		entityID = buf.readInt();
		maxHealth = buf.readFloat();
		maxSpeed = buf.readFloat();
		acceleration = buf.readFloat();
		turnSpeed = buf.readFloat();
		fuel = buf.readFloat();
		health = buf.readFloat();
	}
	
	@Override
	public IMessage onMessage(PacketSpawnVehicle m, MessageContext ctx)
	{
		if(ctx.side.isClient())
		{
			Minecraft.getMinecraft().addScheduledTask(() ->
			{
				World world = Minecraft.getMinecraft().player.world;
				
				if(world.getEntityByID(m.entityID) instanceof EntityVehicle)
				{
					((EntityVehicle)world.getEntityByID(m.entityID)).setAllRequiredValues(m.maxHealth, m.health, m.maxSpeed, m.acceleration, m.turnSpeed);
					((EntityVehicle)world.getEntityByID(m.entityID)).fuel = m.fuel;
				}
			});
		}
		return null;
	}
	
	
	public PacketSpawnVehicle id(int id)
	{
		this.entityID = id;
		return this;
	}
	
	/**
	 * Call when fuel value is changed on server to update client overlay
	 * @param fuel
	 * @return
	 */
	public PacketSpawnVehicle fuel(float fuel)
	{
		this.fuel = fuel;
		return this;
	}
	
	public PacketSpawnVehicle damage(EntityVehicle vehicle, float damage)
	{
		vehicle.health -= damage;
		this.health = vehicle.health;
		return this;
	}
	
	/**
	 * Should be called at the end, always!
	 * @param vehicleToSync
	 * @return
	 */
	public PacketSpawnVehicle syncAll(EntityVehicle vehicleToSync)
	{
		entityID = vehicleToSync.getEntityId();
		maxHealth = vehicleToSync.maxHealth;
		maxSpeed = vehicleToSync.maxSpeed;
		acceleration = vehicleToSync.acceleration;
		turnSpeed = vehicleToSync.turnSpeed;
		fuel = vehicleToSync.fuel;
		health = vehicleToSync.health;
		return this;
	}
}

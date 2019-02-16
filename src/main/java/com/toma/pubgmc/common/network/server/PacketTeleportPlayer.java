package com.toma.pubgmc.common.network.server;

import java.util.Random;

import com.toma.pubgmc.Pubgmc;
import com.toma.pubgmc.common.capability.IGameData;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketTeleportPlayer implements IMessage, IMessageHandler<PacketTeleportPlayer, IMessage>
{
	private double x,z;
	
	public PacketTeleportPlayer()
	{
	}
	
	public PacketTeleportPlayer(double x, double z)
	{
		this.x = x;
		this.z = z;
	}
	
	@Override
	public void toBytes(ByteBuf buf)
	{
		buf.writeDouble(x);
		buf.writeDouble(z);
	}
	
	@Override
	public void fromBytes(ByteBuf buf)
	{
		x = buf.readDouble();
		z = buf.readDouble();
	}
	
	@Override
	public IMessage onMessage(PacketTeleportPlayer message, MessageContext ctx)
	{
		ctx.getServerHandler().player.getServer().addScheduledTask(() ->
		{
			Random rand = new Random();
			EntityPlayerMP player = ctx.getServerHandler().player;
			double x = message.x + rand.nextInt(16) - rand.nextInt(16);
			double z = message.z + rand.nextInt(16) - rand.nextInt(16);
			player.setPositionAndUpdate(x, 256, z);
			Pubgmc.logger.info("Player " + player.getName() + " has been teleported to [" + x + ", 256 ," + z + "].");
		});
		
		return null;
	}
}

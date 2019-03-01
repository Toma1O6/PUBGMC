package com.toma.pubgmc.common.network.server;

import com.toma.pubgmc.common.entity.EntityPlane;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketChooseLocation implements IMessage, IMessageHandler<PacketChooseLocation, IMessage>
{
	private BlockPos pos;
	
	public PacketChooseLocation() {}
	
	public PacketChooseLocation(BlockPos loc)
	{
		pos = loc;
	}
	
	@Override
	public void toBytes(ByteBuf buf)
	{
		buf.writeDouble(pos.getX());
		buf.writeDouble(pos.getY());
		buf.writeDouble(pos.getZ());
	}
	
	@Override
	public void fromBytes(ByteBuf buf)
	{
		pos = new BlockPos(buf.readDouble(), buf.readDouble(), buf.readDouble());
	}
	
	@Override
	public IMessage onMessage(PacketChooseLocation message, MessageContext ctx)
	{
		EntityPlayerMP player = ctx.getServerHandler().player;
		player.getServer().addScheduledTask(() ->
		{
			EntityPlane.dropLoc.put(player, message.pos);
		});
		return null;
	}
}

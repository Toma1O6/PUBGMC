package com.toma.pubgmc.common.network.server;

import com.toma.pubgmc.common.capability.IPlayerData;
import com.toma.pubgmc.common.capability.IPlayerData.PlayerDataProvider;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketReloading implements IMessage, IMessageHandler<PacketReloading, IMessage>
{
	private boolean reload;
	
	public PacketReloading()
	{
		
	}
	
	public PacketReloading(boolean reloading)
	{
		this.reload = reloading;
	}
	
	@Override
	public void toBytes(ByteBuf buf)
	{
		buf.writeBoolean(reload);
	}
	
	@Override
	public void fromBytes(ByteBuf buf) 
	{
		reload = buf.readBoolean();
	}
	
	@Override
	public IMessage onMessage(PacketReloading message, MessageContext ctx)
	{
		IPlayerData data = ctx.getServerHandler().player.getCapability(PlayerDataProvider.PLAYER_DATA, null);
		data.setReloading(message.reload);
		return null;
	}
}

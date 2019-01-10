package com.toma.pubgmc.common.network.server;

import com.toma.pubgmc.common.capability.IPlayerData;
import com.toma.pubgmc.common.capability.IPlayerData.PlayerDataProvider;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketAim implements IMessage, IMessageHandler<PacketAim, IMessage>
{
	private boolean aim;
	
	public PacketAim()
	{
	}
	
	public PacketAim(boolean aiming)
	{
		this.aim = aiming;
	}
	
	@Override
	public void toBytes(ByteBuf buf)
	{
		buf.writeBoolean(aim);
	}
	
	@Override
	public void fromBytes(ByteBuf buf)
	{
		aim = buf.readBoolean();
	}
	
	@Override
	public IMessage onMessage(PacketAim message, MessageContext ctx)
	{
		IPlayerData data = ctx.getServerHandler().player.getCapability(PlayerDataProvider.PLAYER_DATA, null);
		data.setAiming(message.aim);
		return null;
	}
}

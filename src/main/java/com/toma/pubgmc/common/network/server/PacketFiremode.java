package com.toma.pubgmc.common.network.server;

import com.toma.pubgmc.common.items.guns.GunBase;
import com.toma.pubgmc.common.items.guns.GunBase.Firemode;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketFiremode implements IMessage, IMessageHandler<PacketFiremode, IMessage>
{
	private Firemode mode;
	private int value;
	
	public PacketFiremode()
	{
		
	}
	
	public PacketFiremode(Firemode mode)
	{
		this.mode = mode;
		value = mode.valueOf(mode.toString()).ordinal();
	}
	
	@Override
	public void toBytes(ByteBuf buf)
	{
		buf.writeByte(value);
	}
	
	@Override
	public void fromBytes(ByteBuf buf)
	{
		value = buf.readableBytes();
	}
	
	@Override
	public IMessage onMessage(PacketFiremode message, MessageContext ctx)
	{
		EntityPlayerMP player = ctx.getServerHandler().player;
		player.getServer().addScheduledTask(() ->
		{
			GunBase gun = (GunBase)player.getHeldItemMainhand().getItem();
			gun.getNextFiremode(player);
		});
		return null;
	}
}

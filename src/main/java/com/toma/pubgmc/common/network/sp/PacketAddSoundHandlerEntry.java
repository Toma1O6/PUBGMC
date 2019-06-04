package com.toma.pubgmc.common.network.sp;

import com.toma.pubgmc.util.sound.SoundEntry;
import com.toma.pubgmc.util.sound.SoundHandler;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketAddSoundHandlerEntry implements IMessage, IMessageHandler<PacketAddSoundHandlerEntry, IMessage>
{
	private SoundEvent event;
	private int time;
	private float volume;
	
	public PacketAddSoundHandlerEntry() 
	{
	}
	
	public PacketAddSoundHandlerEntry(SoundEntry entry)
	{
		event = entry.getSound();
		time = entry.getTime();
		volume = entry.getVolume();
	}
	
	@Override
	public void toBytes(ByteBuf buf)
	{
		buf.writeInt(SoundEvent.REGISTRY.getIDForObject(event));
		buf.writeInt(time);
		buf.writeFloat(volume);
	}
	
	@Override
	public void fromBytes(ByteBuf buf)
	{
		event = SoundEvent.REGISTRY.getObjectById(buf.readInt());
		time = buf.readInt();
		volume = buf.readFloat();
	}
	
	@Override
	public IMessage onMessage(PacketAddSoundHandlerEntry message, MessageContext ctx)
	{
		if(ctx.side.isClient())
		{
			Minecraft.getMinecraft().addScheduledTask(() -> handle(message));
		}
		return null;
	}
	
	private static void handle(PacketAddSoundHandlerEntry p)
	{
		SoundHandler.putEntry(p.event, p.time, p.volume);
	}
}

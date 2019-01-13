package com.toma.pubgmc.common.network.sp;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketSound implements IMessage, IMessageHandler<PacketSound, IMessage>
{
	private SoundEvent event;
	private float volume;
	private float pitch;
	private double x, y, z;
	
	public PacketSound()
	{
		// TODO Auto-generated constructor stub
	}
	
	public PacketSound(SoundEvent sound, float volume, float pitch, double x, double y, double z)
	{
		this.event = sound;
		this.volume = volume;
		this.pitch = pitch;
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	@Override
	public void toBytes(ByteBuf buf)
	{
		buf.writeInt(SoundEvent.REGISTRY.getIDForObject(this.event));
		buf.writeFloat(this.volume);
		buf.writeFloat(this.pitch);
		buf.writeDouble(this.x);
		buf.writeDouble(this.y);
		buf.writeDouble(this.z);
	}
	
	@Override
	public void fromBytes(ByteBuf buf)
	{
		event = SoundEvent.REGISTRY.getObjectById(buf.readInt());
		volume = buf.readFloat();
		pitch = buf.readFloat();
		x = buf.readDouble();
		y = buf.readDouble();
		z = buf.readDouble();
	}
	
	@Override
	public IMessage onMessage(PacketSound message, MessageContext ctx)
	{
		if(ctx.side.isClient())
		{
			EntityPlayerSP player = Minecraft.getMinecraft().player;
			
			Minecraft.getMinecraft().addScheduledTask(() ->
			{
				player.world.playSound(message.x, message.y, message.z, message.event, SoundCategory.PLAYERS, message.volume, message.pitch, false);
			});
		}
		return null;
	}
}

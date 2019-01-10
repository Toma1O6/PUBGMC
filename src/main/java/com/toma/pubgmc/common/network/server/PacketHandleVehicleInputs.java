package com.toma.pubgmc.common.network.server;

import com.toma.pubgmc.common.entity.EntityVehicle;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketHandleVehicleInputs implements IMessage, IMessageHandler<PacketHandleVehicleInputs, IMessage>
{
	private boolean forward,back,right,left,boost;
	
	public PacketHandleVehicleInputs()
	{
	}
	
	public PacketHandleVehicleInputs(boolean forward, boolean braking, boolean right, boolean left, boolean isBoosting)
	{
		this.forward = forward;
		this.back = braking;
		this.right = right;
		this.left = left;
		this.boost = isBoosting;
	}
	
	@Override
	public void fromBytes(ByteBuf buf)
	{
		forward = buf.readBoolean();
		back = buf.readBoolean();
		right = buf.readBoolean();
		left = buf.readBoolean();
		boost = buf.readBoolean();
	}
	
	@Override
	public void toBytes(ByteBuf buf)
	{
		buf.writeBoolean(forward);
		buf.writeBoolean(back);
		buf.writeBoolean(right);
		buf.writeBoolean(left);
		buf.writeBoolean(boost);
	}
	
	@Override
	public IMessage onMessage(PacketHandleVehicleInputs message, MessageContext ctx)
	{
		if(ctx.side.isServer())
		{
			ctx.getServerHandler().player.getServer().addScheduledTask(() ->
			{
				EntityPlayerMP player = ctx.getServerHandler().player;
				
				if(player.isRiding() && player.getRidingEntity() instanceof EntityVehicle)
				{
					EntityVehicle vehicle = (EntityVehicle)player.getRidingEntity();
					vehicle.handleInputs(message.forward, message.back, message.right, message.left, message.boost, player);
				}
			});
		}
		return null;
	}
}

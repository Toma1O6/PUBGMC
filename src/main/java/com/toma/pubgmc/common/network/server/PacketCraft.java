package com.toma.pubgmc.common.network.server;

import com.toma.pubgmc.common.tileentity.TileEntityGunWorkbench;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketCraft implements IMessage, IMessageHandler<PacketCraft, IMessage>
{
	private int id,x,y,z;
	private CraftMode mode;
	
	public PacketCraft() {
		// TODO Auto-generated constructor stub
	}
	
	public PacketCraft(int recipeID, int x, int y, int z, CraftMode mode)
	{
		id = recipeID;
		this.x = x;
		this.y = y;
		this.z = z;
		this.mode = mode;
	}
	
	@Override
	public void toBytes(ByteBuf buf)
	{
		buf.writeInt(id);
		buf.writeInt(x);
		buf.writeInt(y);
		buf.writeInt(z);
		buf.writeInt(mode.ordinal());
	}
	
	@Override
	public void fromBytes(ByteBuf buf) 
	{
		id = buf.readInt();
		x = buf.readInt();
		y = buf.readInt();
		z = buf.readInt();
		mode = CraftMode.values()[buf.readInt()];
	}
	
	@Override
	public IMessage onMessage(PacketCraft message, MessageContext ctx)
	{
		ctx.getServerHandler().player.getServer().addScheduledTask(() ->
		{
			EntityPlayerMP player = ctx.getServerHandler().player;
			World world = player.world;
			TileEntity tileEntity = world.getTileEntity(new BlockPos(message.x, message.y, message.z));
			if(tileEntity instanceof TileEntityGunWorkbench)
			{
				((TileEntityGunWorkbench)tileEntity).craft(message.id, message.mode);
			}
		});
		return null;
	}
}

package com.toma.pubgmc.common.network.sp;

import java.util.Random;

import io.netty.buffer.ByteBuf;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketParticle implements IMessage, IMessageHandler<PacketParticle, IMessage>
{
	private EnumParticleTypes particle;
	private double x,y,z;
	private Block hitBlock;
	private int amount;
	
	public PacketParticle()
	{
	}
	
	public PacketParticle(EnumParticleTypes particle, int amountOfParticles, Vec3d hitVec, Block block)
	{
		this.particle = particle;
		this.x = hitVec.x;
		this.y = hitVec.y;
		this.z = hitVec.z;
		this.hitBlock = block;
		this.amount = amountOfParticles;
	}
	
	public PacketParticle(EnumParticleTypes particle, int amountOfParticles, double x, double y, double z, Block block)
	{
		this.particle = particle;
		this.x = x;
		this.y = y;
		this.z = z;
		this.hitBlock = block;
		this.amount = amountOfParticles;
	}
	
	@Override
	public void toBytes(ByteBuf buf) 
	{
		buf.writeInt(particle.ordinal());
		buf.writeDouble(x);
		buf.writeDouble(y);
		buf.writeDouble(z);
		buf.writeInt(Block.getIdFromBlock(hitBlock));
		buf.writeInt(amount);
		
	}
	
	@Override
	public void fromBytes(ByteBuf buf)
	{
		particle = EnumParticleTypes.values()[buf.readInt()];
		x = buf.readDouble();
		y = buf.readDouble();
		z = buf.readDouble();
		hitBlock = Block.getBlockById(buf.readInt());
		amount = buf.readInt();
	}
	
	@Override
	public IMessage onMessage(PacketParticle message, MessageContext ctx) 
	{
		if(ctx.side.isClient())
		{
			Minecraft.getMinecraft().addScheduledTask(() ->
			{
				World world = Minecraft.getMinecraft().player.world;
				final Random rand = new Random();
				
				for(int i = 0; i < message.amount; i++)
					world.spawnParticle(message.particle, true, message.x, message.y, message.z, rand.nextDouble()/5, rand.nextDouble()/5, rand.nextDouble()/5, Block.getIdFromBlock(message.hitBlock));
			});
		}
		return null;
	}
}

package com.toma.pubgmc.network.sp;

import io.netty.buffer.ByteBuf;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

import java.util.Random;

public class PacketParticle implements IMessage {
    private EnumParticleTypes particle;
    private double x, y, z;
    private Block hitBlock;
    private int amount;
    private ParticleAction action;
    private int parameter;

    public PacketParticle() {
    }

    public PacketParticle(EnumParticleTypes particle, int amountOfParticles, Vec3d hitVec, Block block, ParticleAction action, int par) {
        this(particle, amountOfParticles, hitVec.x, hitVec.y, hitVec.z, block, action, par);
    }

    public PacketParticle(EnumParticleTypes particle, int amountOfParticles, double x, double y, double z, Block block, ParticleAction action, int par) {
        this.particle = particle;
        this.x = x;
        this.y = y;
        this.z = z;
        this.hitBlock = block;
        this.amount = amountOfParticles;
        this.action = action;
        this.parameter = par;
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(particle.ordinal());
        buf.writeInt(action.ordinal());
        buf.writeDouble(x);
        buf.writeDouble(y);
        buf.writeDouble(z);
        buf.writeInt(Block.getIdFromBlock(hitBlock));
        buf.writeInt(amount);
        buf.writeInt(parameter);

    }

    @Override
    public void fromBytes(ByteBuf buf) {
        particle = EnumParticleTypes.values()[buf.readInt()];
        action = ParticleAction.values()[buf.readInt()];
        x = buf.readDouble();
        y = buf.readDouble();
        z = buf.readDouble();
        hitBlock = Block.getBlockById(buf.readInt());
        amount = buf.readInt();
        parameter = buf.readInt();
    }

    public static class Handler implements IMessageHandler<PacketParticle, IMessage> {
        @Override
        public IMessage onMessage(PacketParticle message, MessageContext ctx) {
            if (ctx.side.isClient()) {
                Minecraft.getMinecraft().addScheduledTask(() ->
                {
                    World world = Minecraft.getMinecraft().player.world;
                    final Random rand = new Random();
                    switch(message.action) {
                        case SPREAD_RANDOMLY: {
                            for (int i = 0; i < message.amount; i++)
                                world.spawnParticle(message.particle, true, message.x, message.y, message.z, rand.nextDouble() / 5, rand.nextDouble() / 5, rand.nextDouble() / 5, Block.getIdFromBlock(message.hitBlock));
                            break;
                        }
                        case CREATE_LINE: {
                            Vec3d start = new Vec3d(message.x, message.y, message.z);
                            boolean eastWest = message.parameter > 0;
                            for(int i = 0; i < message.amount; i++) {
                                world.spawnParticle(message.particle, eastWest ? start.x + rand.nextDouble() : start.x + 0.5, start.y + 1, eastWest ? start.z + 0.5 : start.z + rand.nextDouble(), 0, -0.25, 0, Block.getIdFromBlock(message.hitBlock));
                            }
                            break;
                        }
                    }

                });
            }
            return null;
        }
    }

    public enum ParticleAction {
        SPREAD_RANDOMLY,
        CREATE_LINE
    }
}

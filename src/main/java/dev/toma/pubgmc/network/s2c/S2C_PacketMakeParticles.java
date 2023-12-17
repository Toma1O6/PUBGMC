package dev.toma.pubgmc.network.s2c;

import dev.toma.pubgmc.Pubgmc;
import dev.toma.pubgmc.util.helper.PacketHelper;
import io.netty.buffer.ByteBuf;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Random;

public class S2C_PacketMakeParticles implements IMessage {
    private EnumParticleTypes particle;
    private double x, y, z;
    private BlockPos hitBlock;
    private int amount;
    private ParticleAction action;
    private int parameter;
    private int additional;

    public S2C_PacketMakeParticles() {
    }

    public S2C_PacketMakeParticles(EnumParticleTypes particle, int amountOfParticles, double x, double y, double z, Block block, ParticleAction action, int par) {
        this(particle, amountOfParticles, x, y, z, new BlockPos(0, 0, 0), action, par);
        this.additional = Block.getIdFromBlock(block);
    }

    public S2C_PacketMakeParticles(EnumParticleTypes particle, int amountOfParticles, Vec3d hitVec, BlockPos block, ParticleAction action, int par) {
        this(particle, amountOfParticles, hitVec.x, hitVec.y, hitVec.z, block, action, par);
    }

    public S2C_PacketMakeParticles(EnumParticleTypes particle, int amountOfParticles, double x, double y, double z, BlockPos block, ParticleAction action, int par) {
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
        PacketHelper.writeBlockPos(hitBlock, buf);
        buf.writeInt(amount);
        buf.writeInt(parameter);
        buf.writeInt(additional);
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        particle = EnumParticleTypes.values()[buf.readInt()];
        action = ParticleAction.values()[buf.readInt()];
        x = buf.readDouble();
        y = buf.readDouble();
        z = buf.readDouble();
        hitBlock = PacketHelper.readBlockPos(buf);
        amount = buf.readInt();
        parameter = buf.readInt();
        additional = buf.readInt();
    }

    public enum ParticleAction {
        SPREAD_RANDOMLY,
        CREATE_LINE,
        HIT_EFFECT
    }

    public static class Handler implements IMessageHandler<S2C_PacketMakeParticles, IMessage> {

        @SideOnly(Side.CLIENT)
        @Override
        public IMessage onMessage(S2C_PacketMakeParticles message, MessageContext ctx) {
            Minecraft mc = Minecraft.getMinecraft();
            mc.addScheduledTask(() -> {
                EntityPlayer player = mc.player;
                World world = mc.world;
                Random rand = Pubgmc.rng();
                switch (message.action) {
                    case SPREAD_RANDOMLY: {
                        for (int i = 0; i < message.amount; i++)
                            world.spawnParticle(message.particle, true, message.x, message.y, message.z, rand.nextDouble() / 5, rand.nextDouble() / 5, rand.nextDouble() / 5, Block.getStateId(world.getBlockState(message.hitBlock)));
                        break;
                    }
                    case CREATE_LINE: {
                        Vec3d start = new Vec3d(message.x, message.y, message.z);
                        boolean eastWest = message.parameter > 0;
                        for (int i = 0; i < message.amount; i++) {
                            world.spawnParticle(message.particle, eastWest ? start.x + rand.nextDouble() : start.x + 0.5, start.y + 1, eastWest ? start.z + 0.5 : start.z + rand.nextDouble(), 0, -0.25, 0, Block.getStateId(world.getBlockState(message.hitBlock)));
                        }
                        break;
                    }
                    case HIT_EFFECT: {
                        for (int i = 0; i < message.amount; i++) {
                            world.spawnParticle(message.particle, true, message.x, message.y, message.z, rand.nextDouble() / 5, rand.nextDouble() / 5, rand.nextDouble() / 5, message.additional);
                        }
                        break;
                    }
                }
            });
            return null;
        }
    }
}

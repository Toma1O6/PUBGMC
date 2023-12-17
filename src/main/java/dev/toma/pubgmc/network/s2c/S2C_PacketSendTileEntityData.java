package dev.toma.pubgmc.network.s2c;

import dev.toma.pubgmc.util.helper.PacketHelper;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class S2C_PacketSendTileEntityData implements IMessage {

    private NBTTagCompound nbt;
    private BlockPos pos;

    public S2C_PacketSendTileEntityData() {
    }

    public S2C_PacketSendTileEntityData(NBTTagCompound nbt, BlockPos pos) {
        this.nbt = nbt;
        this.pos = pos;
    }

    @Override
    public void toBytes(ByteBuf buf) {
        ByteBufUtils.writeTag(buf, nbt);
        PacketHelper.writeBlockPos(pos, buf);
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        nbt = ByteBufUtils.readTag(buf);
        pos = PacketHelper.readBlockPos(buf);
    }

    public static class Handler implements IMessageHandler<S2C_PacketSendTileEntityData, IMessage> {

        @SideOnly(Side.CLIENT)
        @Override
        public IMessage onMessage(S2C_PacketSendTileEntityData message, MessageContext ctx) {
            Minecraft.getMinecraft().addScheduledTask(() -> {
                TileEntity te = Minecraft.getMinecraft().player.world.getTileEntity(message.pos);
                if (te != null) {
                    te.readFromNBT(message.nbt);
                }
            });
            return null;
        }
    }
}

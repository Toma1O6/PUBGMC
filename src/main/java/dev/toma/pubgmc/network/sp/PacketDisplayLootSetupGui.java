package dev.toma.pubgmc.network.sp;

import dev.toma.pubgmc.client.gui.GuiLootSetup;
import dev.toma.pubgmc.common.capability.IWorldData;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketDisplayLootSetupGui implements IMessage {

    private NBTTagCompound nbt;

    public PacketDisplayLootSetupGui() {}

    public PacketDisplayLootSetupGui(NBTTagCompound nbt) {
        this.nbt = nbt;
    }

    @Override
    public void toBytes(ByteBuf buf) {
        ByteBufUtils.writeTag(buf, nbt);
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        nbt = ByteBufUtils.readTag(buf);
    }

    public static class Handler implements IMessageHandler<PacketDisplayLootSetupGui, IMessage> {
        @Override
        public IMessage onMessage(PacketDisplayLootSetupGui message, MessageContext ctx) {
            if (ctx.side.isClient()) {
                Minecraft mc = Minecraft.getMinecraft();
                mc.addScheduledTask(new Runnable() {
                    @Override
                    public void run() {
                        IWorldData data = mc.world.getCapability(IWorldData.WorldDataProvider.WORLD_DATA, null);
                        data.deserializeNBT(message.nbt);
                        mc.displayGuiScreen(new GuiLootSetup(data));
                    }
                });
            }
            return null;
        }
    }
}

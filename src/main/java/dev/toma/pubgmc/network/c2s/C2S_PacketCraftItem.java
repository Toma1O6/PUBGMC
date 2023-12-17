package dev.toma.pubgmc.network.c2s;

import dev.toma.pubgmc.common.tileentity.TileEntityGunWorkbench;
import dev.toma.pubgmc.util.helper.PacketHelper;
import dev.toma.pubgmc.util.recipes.RecipeRegistry;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class C2S_PacketCraftItem implements IMessage {

    BlockPos pos;
    int id;

    public C2S_PacketCraftItem() {
    }

    public C2S_PacketCraftItem(BlockPos pos, int id) {
        this.pos = pos;
        this.id = id;
    }

    @Override
    public void toBytes(ByteBuf buf) {
        PacketHelper.writeBlockPos(pos, buf);
        buf.writeInt(id);
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        pos = PacketHelper.readBlockPos(buf);
        id = buf.readInt();
    }

    public static class Handler implements IMessageHandler<C2S_PacketCraftItem, IMessage> {

        @Override
        public IMessage onMessage(C2S_PacketCraftItem message, MessageContext ctx) {
            EntityPlayerMP player = ctx.getServerHandler().player;
            player.getServer().addScheduledTask(() -> {
                TileEntity te = player.world.getTileEntity(message.pos);
                if (te instanceof TileEntityGunWorkbench) {
                    ((TileEntityGunWorkbench) te).craft(RecipeRegistry.RECIPES.get(message.id));
                }
            });
            return null;
        }
    }
}

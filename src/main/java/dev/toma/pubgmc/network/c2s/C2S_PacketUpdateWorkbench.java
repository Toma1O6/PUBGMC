package dev.toma.pubgmc.network.c2s;

import dev.toma.pubgmc.common.tileentity.TileEntityGunWorkbench;
import dev.toma.pubgmc.util.recipes.PMCRecipe.CraftingCategory;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class C2S_PacketUpdateWorkbench implements IMessage {

    BlockPos pos;
    CraftingCategory cat;
    int page;

    public C2S_PacketUpdateWorkbench() {
    }

    public C2S_PacketUpdateWorkbench(TileEntityGunWorkbench te, CraftingCategory cat, int page) {
        this.pos = te.getPos();
        this.cat = cat;
        this.page = page;
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(pos.getX());
        buf.writeInt(pos.getY());
        buf.writeInt(pos.getZ());
        buf.writeInt(cat.ordinal());
        buf.writeInt(page);
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        pos = new BlockPos(buf.readInt(), buf.readInt(), buf.readInt());
        cat = CraftingCategory.values()[buf.readInt()];
        page = buf.readInt();
    }

    public static class Handler implements IMessageHandler<C2S_PacketUpdateWorkbench, IMessage> {

        @Override
        public IMessage onMessage(C2S_PacketUpdateWorkbench message, MessageContext ctx) {
            EntityPlayerMP player = ctx.getServerHandler().player;
            player.getServer().addScheduledTask(() -> {
                TileEntity t = player.world.getTileEntity(message.pos);
                if (t instanceof TileEntityGunWorkbench) {
                    TileEntityGunWorkbench te = (TileEntityGunWorkbench) t;
                    te.selectedCat = message.cat;
                    te.selectedIndex = message.page;
                }
            });
            return null;
        }
    }
}

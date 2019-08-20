package com.toma.pubgmc.common.network.sp;

import com.toma.pubgmc.common.items.guns.attachments.GuiAttachments;
import com.toma.pubgmc.common.items.guns.attachments.InventoryAttachments;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

import java.util.ArrayList;
import java.util.List;

public class PacketUpdateAttachmentGUI implements IMessage, IMessageHandler<PacketUpdateAttachmentGUI, IMessage> {
    private InventoryAttachments inv;
    private List<Item> items = new ArrayList<Item>();

    public PacketUpdateAttachmentGUI() {
    }

    public PacketUpdateAttachmentGUI(InventoryAttachments inv) {
        this.inv = inv;
    }

    @Override
    public void toBytes(ByteBuf buf) {
        for (int i = 0; i < inv.getSizeInventory(); i++) {
            buf.writeInt(Item.getIdFromItem(inv.getStackInSlot(i).getItem()));
        }
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        for (int i = 0; i < 7; i++) {
            items.add(Item.getItemById(buf.readInt()));
        }
    }

    @Override
    public IMessage onMessage(PacketUpdateAttachmentGUI message, MessageContext ctx) {
        if (ctx.side.isClient()) {
        }
        return null;
    }
}

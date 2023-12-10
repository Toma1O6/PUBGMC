package dev.toma.pubgmc.network.server;

import dev.toma.pubgmc.common.items.attachment.AttachmentType;
import dev.toma.pubgmc.common.items.attachment.ItemAttachment;
import dev.toma.pubgmc.common.items.guns.GunAttachments;
import dev.toma.pubgmc.common.items.guns.GunBase;
import dev.toma.pubgmc.network.PacketHandler;
import dev.toma.pubgmc.network.client.S2C_SendExternalGuiEvent;
import dev.toma.pubgmc.util.PUBGMCUtil;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

import java.util.Set;
import java.util.function.BiConsumer;

public class C2S_AttachmentRequestPacket implements IMessage {

    private RequestType requestType;
    private AttachmentType<?> attachmentType;
    private ItemStack itemStack;

    public C2S_AttachmentRequestPacket() {}

    private C2S_AttachmentRequestPacket(RequestType requestType, AttachmentType<?> attachmentType, ItemStack itemStack) {
        this.requestType = requestType;
        this.attachmentType = attachmentType;
        this.itemStack = itemStack;
    }

    public static C2S_AttachmentRequestPacket detachAllRequest() {
        return new C2S_AttachmentRequestPacket(RequestType.DETACH_ALL, null, null);
    }

    public static C2S_AttachmentRequestPacket detachByType(AttachmentType<?> type) {
        return new C2S_AttachmentRequestPacket(RequestType.DETACH_TYPE, type, null);
    }

    public static C2S_AttachmentRequestPacket attach(AttachmentType<?> attachmentType, ItemStack stack) {
        return new C2S_AttachmentRequestPacket(RequestType.ATTACH, attachmentType, stack);
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(requestType.ordinal());
        requestType.encoder.accept(this, buf);
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        requestType = RequestType.values()[buf.readInt()];
        requestType.decoder.accept(this, buf);
    }

    public enum RequestType {

        DETACH_ALL((pkt, buffer) -> {}, (pkt, buffer) -> {}),
        DETACH_TYPE((pkt, buffer) -> {
            int index = pkt.attachmentType.getIndex();
            buffer.writeInt(index);
        }, (pkt, buffer) -> {
            int index = buffer.readInt();
            pkt.attachmentType = AttachmentType.allTypes[index];
        }),
        ATTACH((pkt, buffer) -> {
            buffer.writeInt(pkt.attachmentType.getIndex());
            ByteBufUtils.writeItemStack(buffer, pkt.itemStack);
        }, (pkt, buffer) -> {
            pkt.attachmentType = AttachmentType.allTypes[buffer.readInt()];
            pkt.itemStack = ByteBufUtils.readItemStack(buffer);
        });

        private final BiConsumer<C2S_AttachmentRequestPacket, ByteBuf> encoder;
        private final BiConsumer<C2S_AttachmentRequestPacket, ByteBuf> decoder;

        RequestType(BiConsumer<C2S_AttachmentRequestPacket, ByteBuf> encoder, BiConsumer<C2S_AttachmentRequestPacket, ByteBuf> decoder) {
            this.encoder = encoder;
            this.decoder = decoder;
        }
    }

    public static final class Handler implements IMessageHandler<C2S_AttachmentRequestPacket, IMessage> {

        @Override
        public IMessage onMessage(C2S_AttachmentRequestPacket message, MessageContext ctx) {
            EntityPlayerMP player = ctx.getServerHandler().player;
            player.getServer().addScheduledTask(() -> {
                ItemStack heldStack = player.getHeldItemMainhand();
                if (!(heldStack.getItem() instanceof GunBase)) {
                    return;
                }
                GunBase gun = (GunBase) heldStack.getItem();
                GunAttachments attachments = gun.getAttachments();
                if (message.requestType == RequestType.DETACH_ALL) {
                    Set<AttachmentType<?>> weaponTypes = attachments.getCompatibilityMap().keySet();
                    for (AttachmentType<?> type : weaponTypes) {
                        ItemAttachment itemAttachment = gun.getAttachment(type, heldStack);
                        if (itemAttachment != null) {
                            PUBGMCUtil.giveItemOrDrop(player, new ItemStack(itemAttachment));
                            attachments.detach(heldStack, type);
                        }
                    }
                } else if (message.requestType == RequestType.DETACH_TYPE) {
                    AttachmentType<?> type = message.attachmentType;
                    ItemAttachment attachment = gun.getAttachment(type, heldStack);
                    if (attachment != null) {
                        PUBGMCUtil.giveItemOrDrop(player, new ItemStack(attachment));
                        attachments.detach(heldStack, type);
                    }
                } else {
                    AttachmentType<?> type = message.attachmentType;
                    ItemAttachment oldAttachment = gun.getAttachment(type, heldStack);
                    if (oldAttachment != null) {
                        PUBGMCUtil.giveItemOrDrop(player, new ItemStack(oldAttachment));
                    }
                    ItemStack inInventoryStack = findItemFromInventory(player, message.itemStack);
                    if (!inInventoryStack.isEmpty() && inInventoryStack.getItem() instanceof ItemAttachment) {
                        attachments.attach(heldStack, (ItemAttachment) inInventoryStack.getItem());
                        inInventoryStack.shrink(1);
                    }
                }
                PacketHandler.sendToClient(new S2C_SendExternalGuiEvent(), player);
            });
            return null;
        }

        private ItemStack findItemFromInventory(EntityPlayer player, ItemStack itemStack) {
            for (int i = 0; i < player.inventory.getSizeInventory(); i++) {
                ItemStack stack = player.inventory.getStackInSlot(i);
                if (!stack.isEmpty() && ItemStack.areItemsEqual(stack, itemStack)) {
                    return stack;
                }
            }
            return ItemStack.EMPTY;
        }
    }
}

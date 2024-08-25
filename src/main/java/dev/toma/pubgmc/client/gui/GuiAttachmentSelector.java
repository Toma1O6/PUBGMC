package dev.toma.pubgmc.client.gui;

import dev.toma.pubgmc.Pubgmc;
import dev.toma.pubgmc.client.gui.menu.GuiWidgets;
import dev.toma.pubgmc.client.gui.widget.ButtonWidget;
import dev.toma.pubgmc.client.gui.widget.Widget;
import dev.toma.pubgmc.common.items.attachment.AttachmentType;
import dev.toma.pubgmc.common.items.attachment.ItemAttachment;
import dev.toma.pubgmc.common.items.guns.GunAttachments;
import dev.toma.pubgmc.common.items.guns.GunBase;
import dev.toma.pubgmc.network.PacketHandler;
import dev.toma.pubgmc.network.c2s.C2S_PacketAttachmentRequest;
import dev.toma.pubgmc.util.helper.ImageUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GuiAttachmentSelector extends GuiWidgets implements ExternalGuiEventListener {

    private ItemStack itemStack;
    private GunBase gun;

    private ITextComponent guiTitle = new TextComponentString("");

    // Kind of ugly workaround, but should work in most cases
    private Long scheduledUpdate;

    @Override
    public void handleUpdate() {
        this.scheduledUpdate = System.currentTimeMillis() + 100L;
    }

    @Override
    public void init() {
        ItemStack stack = mc.player.getHeldItemMainhand();
        if (stack.getItem() instanceof GunBase) {
            this.gun = (GunBase) stack.getItem();
            this.itemStack = stack;

            GunAttachments attachments = gun.getAttachments();
            Map<AttachmentType<?>, List<ItemAttachment>> availableAttachments = attachments.getCompatibilityMap();

            int slots = availableAttachments.size();
            int slotsWidth = slots * (AttachmentWidget.SIZE + AttachmentWidget.OFFSET) - AttachmentWidget.OFFSET;
            int posX = (width - slotsWidth) / 2;

            int index = 0;
            for (Map.Entry<AttachmentType<?>, List<ItemAttachment>> entry : availableAttachments.entrySet()) {
                AttachmentType<?> attachmentType = entry.getKey();
                List<ItemStack> compatibleAttachments = entry.getValue().stream().filter(this::hasInInventory).map(ItemStack::new).collect(Collectors.toList());
                ItemAttachment attached = gun.getAttachment(attachmentType, itemStack);
                AttachmentWidget widget = addWidget(new AttachmentWidget(posX + (index++ * (AttachmentWidget.SIZE + AttachmentWidget.OFFSET)), 20, attachmentType, compatibleAttachments, attached != null ? attached : Items.AIR));
                widget.setDetachSingleListener(this::detachSingleClicked);
                widget.setAttachItemListener(this::attachClicked);
            }

            this.guiTitle = new TextComponentTranslation("gui.pubgmc.attachments.title", itemStack.getDisplayName());

            addWidget(new ButtonWidget(width - 125, height - 50, 120, 20, I18n.format("gui.pubgmc.attachments.detach_all"), (btn, mx, my, mb) -> detachAllClicked(false)));
            addWidget(new ButtonWidget(width - 125, height - 25, 120, 20, I18n.format("gui.pubgmc.attachments.detach_all_close"), (btn, mx, my, mb) -> detachAllClicked(true)));
        }
    }

    private boolean hasInInventory(Item item) {
        InventoryPlayer inventory = mc.player.inventory;
        for (int i = 0; i < inventory.getSizeInventory(); i++) {
            ItemStack stack = inventory.getStackInSlot(i);
            if (stack.getItem() == item) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        drawDefaultBackground();
        drawWidgets(mc, mouseX, mouseY, partialTicks);
        String title = guiTitle.getFormattedText();
        int titleWidth = fontRenderer.getStringWidth(title);
        fontRenderer.drawString(title, (width - titleWidth) / 2.0F, 5, 0xFFFFFF, false);
        super.drawScreen(mouseX, mouseY, partialTicks);

        if (scheduledUpdate != null && System.currentTimeMillis() >= scheduledUpdate) {
            scheduledUpdate = null;
            initGui();
        }
    }

    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }

    private void detachAllClicked(boolean closeGui) {
        PacketHandler.sendToServer(C2S_PacketAttachmentRequest.detachAllRequest());
        if (closeGui) {
            mc.displayGuiScreen(null);
        }
    }

    private void detachSingleClicked(AttachmentType<?> type) {
        PacketHandler.sendToServer(C2S_PacketAttachmentRequest.detachByType(type));
    }

    private void attachClicked(AttachmentType<?> type, ItemStack itemStack) {
        PacketHandler.sendToServer(C2S_PacketAttachmentRequest.attach(type, itemStack));
    }

    private static final class AttachmentWidget extends Widget {

        private static final ResourceLocation REMOVE_SINGLE_ICON = Pubgmc.getResource("textures/overlay/down_arrow.png");
        private static final int SIZE = 50;
        private static final int AVAILABLE_SIZE = 24;
        private static final int OFFSET = 5;
        private static final int AVAILABLE_OFFSET = 2;

        private final AttachmentType<?> type;
        private final List<ItemStack> available;
        private final ItemStack attached;

        private DetachSingleListener detachSingleListener;
        private AttachItemListener attachItemListener;

        public AttachmentWidget(int x, int y, AttachmentType<?> type, List<ItemStack> available, Item attached) {
            super(x, y, SIZE, SIZE + OFFSET + available.size() * (AVAILABLE_SIZE + AVAILABLE_OFFSET));
            this.type = type;
            this.available = available;
            this.attached = attached == Items.AIR ? ItemStack.EMPTY : new ItemStack(attached);
        }

        public void setDetachSingleListener(DetachSingleListener detachSingleListener) {
            this.detachSingleListener = detachSingleListener;
        }

        public void setAttachItemListener(AttachItemListener attachItemListener) {
            this.attachItemListener = attachItemListener;
        }

        @Override
        public void render(Minecraft mc, int mouseX, int mouseY, float partialTicks) {
            RenderItem renderer = mc.getRenderItem();
            ImageUtil.drawShape(x, y, x + SIZE, y + SIZE, 0x66 << 24);
            boolean hoveredMain = mouseX >= x && mouseX <= x + width && mouseY >= y && mouseY <= y + SIZE;
            String displayedText = type.getTranslatedName();
            if (!attached.isEmpty()) {
                ImageUtil.drawShape(x + 17, y + 17, x + 33, y + 33, 0x66FFFFFF);
                // Draw equipped item
                renderer.renderItemIntoGUI(attached, x + 17, y + 17);

                if (hoveredMain) {
                    displayedText = I18n.format("label.pubgmc.remove_attachment");
                    GlStateManager.pushMatrix();
                    GlStateManager.translate(0, 0, 101);
                    ImageUtil.drawCustomSizedImage(mc, REMOVE_SINGLE_ICON, x + 17, y + 17, 16, 16, true);
                    GlStateManager.popMatrix();
                }
            }

            for (int i = 0; i < available.size(); i++) {
                int startY = y + SIZE + OFFSET + i * (AVAILABLE_SIZE + AVAILABLE_OFFSET);
                boolean hovered = mouseX >= x && mouseX <= x + width && mouseY >= startY && mouseY <= startY + AVAILABLE_SIZE;
                ImageUtil.drawShape(x, startY, x + width, startY + AVAILABLE_SIZE, 0x66 << 24);
                ImageUtil.drawShape(x + 17, startY + 4, x + 33, startY + 20, hovered ? 0xBBFFFFFF : 0x66FFFFFF);
                renderer.renderItemIntoGUI(available.get(i), x + 17, startY + 4);
            }

            FontRenderer font = mc.fontRenderer;
            font.drawString(displayedText, x + (width - font.getStringWidth(displayedText)) / 2.0F, y + 2, attached.isEmpty() ? 0x777777 : hoveredMain ? 0xFF0000 : 0xFFFFFF, false);
        }

        @Override
        public void onClick(int mouseX, int mouseY, int button) {
            // clicking on attached item
            if (mouseX >= x && mouseX <= x + SIZE && mouseY >= y && mouseY <= y + SIZE) {
                if (!attached.isEmpty() && detachSingleListener != null) {
                    detachSingleListener.onClick(type);
                    return;
                }
            }

            for (int i = 0; i < available.size(); i++) {
                int startY = y + SIZE + OFFSET + i * (AVAILABLE_SIZE + AVAILABLE_OFFSET);
                if (mouseX >= x && mouseX <= x + width && mouseY >= startY && mouseY <= startY + AVAILABLE_SIZE) {
                    if (attachItemListener != null) {
                        ItemStack itemStack = available.get(i);
                        attachItemListener.onClick(type, itemStack);
                        return;
                    }
                }
            }
        }

        @FunctionalInterface
        public interface DetachSingleListener {
            void onClick(AttachmentType<?> type);
        }

        @FunctionalInterface
        public interface AttachItemListener {
            void onClick(AttachmentType<?> type, ItemStack itemStack);
        }
    }
}

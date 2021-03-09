package dev.toma.pubgmc.client.gui.menu;

import dev.toma.pubgmc.client.gui.widget.Widget;
import dev.toma.pubgmc.common.items.attachment.AttachmentType;
import dev.toma.pubgmc.common.items.attachment.ItemAttachment;
import dev.toma.pubgmc.common.items.guns.GunAttachments;
import dev.toma.pubgmc.common.items.guns.GunBase;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GuiGunConfig extends GuiWidgets {

    final List<GunBase> gunList = new ArrayList<>();
    int selectedWeapon;
    ItemStack displayWeapon;

    @Override
    public void init() {
        if (gunList.isEmpty()) {
            gunList.addAll(
                    ForgeRegistries.ITEMS.getValuesCollection().stream()
                            .filter(it -> it instanceof GunBase)
                            .map(it -> (GunBase) it)
                            .collect(Collectors.toList())
            );
        }
        GunBase weapon = gunList.get(selectedWeapon);
        this.displayWeapon = new ItemStack(weapon);
        GunAttachments attachments = weapon.getAttachments();
        if(!attachments.isLoaded()) {
            attachments.load();
        }
        Map<AttachmentType<?>, List<ItemAttachment>> map = attachments.getCompatibilityMap();
        int i = 0;
        for (Map.Entry<AttachmentType<?>, List<ItemAttachment>> entry : map.entrySet()) {
            addWidget(new AttachmentTypeWidget(5, 35 + i * 35, 30, 30, entry.getKey(), entry.getValue()));
        }
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        Widget.drawColorShape(0, 0, width, height, 0.6F, 0.6F, 0.6F, 1.0F);
        Widget.drawColorShape(0, 0, width, 30, 0.0F, 0.0F, 0.0F, 0.25F);
        Widget.drawColorShape(0, 30, 40, height, 0.0F, 0.0F, 0.0F, 0.25F);
        drawWidgets(mc, mouseX, mouseY, partialTicks);
    }

    static class MultiWidget extends Widget {

        final List<Widget> containedWidgets = new ArrayList<>();

        public MultiWidget(int x, int y, int width, int height) {
            super(x, y, width, height);
        }

        public void addWidget(Widget widget) {
            containedWidgets.add(widget);
        }

        @Override
        public void render(Minecraft mc, int mouseX, int mouseY, float partialTicks) {
            for (Widget widget : containedWidgets) {
                widget.render(mc, mouseX, mouseY, partialTicks);
            }
        }

        @Override
        public boolean handleClicked(int mouseX, int mouseY, int button) {
            for (Widget widget : containedWidgets) {
                if (widget.handleClicked(mouseX, mouseY, button)) {
                    Minecraft.getMinecraft().getSoundHandler().playSound(PositionedSoundRecord.getMasterRecord(SoundEvents.UI_BUTTON_CLICK, 1.0F));
                    return true;
                }
            }
            return false;
        }
    }

    static class AttachmentTypeWidget extends MultiWidget {

        final AttachmentType<?> type;
        final List<ItemAttachment> list;

        public AttachmentTypeWidget(int x, int y, int width, int height, AttachmentType<?> type, List<ItemAttachment> list) {
            super(x, y, width, height);
            this.type = type;
            this.list = list;
            this.addWidget(new TextWidget(x, y, width, 13, type.getName()));
        }

        static class TextWidget extends Widget {
            final String text;
            int textWidth;

            TextWidget(int x, int y, int width, int height, String text) {
                super(x, y, width, height);
                this.text = text;
                this.textWidth = Minecraft.getMinecraft().fontRenderer.getStringWidth(text);
            }

            @Override
            public void render(Minecraft mc, int mouseX, int mouseY, float partialTicks) {
                drawColorShape(x, y, x + width, y + height, 0.0F, 0.0F, 0.0F, 1.0F);
                mc.fontRenderer.drawStringWithShadow(text, x + (width - textWidth) / 2.0F, y + (height - mc.fontRenderer.FONT_HEIGHT) / 2.0F, 0xFFFFFF);
            }
        }
    }
}

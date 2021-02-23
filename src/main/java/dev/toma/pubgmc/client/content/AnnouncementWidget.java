package dev.toma.pubgmc.client.content;

import dev.toma.pubgmc.client.gui.menu.GuiMenu;
import dev.toma.pubgmc.client.gui.widget.Widget;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiUtilRenderComponents;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.common.ForgeHooks;

import java.util.List;

public class AnnouncementWidget extends Widget {

    final GuiMenu parent;
    final Announcement announcement;

    public AnnouncementWidget(GuiMenu parent, int x, int y, int width, int height, Announcement announcement) {
        super(x, y, width, height);
        this.parent = parent;
        this.announcement = announcement;
    }

    @Override
    public void render(Minecraft mc, int mouseX, int mouseY, float partialTicks) {
        drawColorShape(x, y, x + width, y + height, 0.0F, 0.0F, 0.0F, 0.25F);
        FontRenderer renderer = mc.fontRenderer;
        renderer.drawString(TextFormatting.YELLOW.toString() + TextFormatting.BOLD + announcement.getTitle(), x + 3, y + 4, 0xffffff);
        ITextComponent component = ForgeHooks.newChatWithLinks(announcement.getText());
        List<ITextComponent> list = GuiUtilRenderComponents.splitText(component, width - 6, renderer, true, false);
        for (int i = 0; i < list.size(); i++) {
            renderer.drawString(list.get(i).getFormattedText(), x + 3, y + 16 + i * 12, 0xffffff);
        }
    }

    @Override
    public void onClick(int mouseX, int mouseY, int button) {
        if(announcement.hasLink()) {
            parent.openWebLink(announcement.getUrl());
        }
    }
}

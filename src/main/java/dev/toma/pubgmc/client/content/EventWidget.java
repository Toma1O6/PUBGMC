package dev.toma.pubgmc.client.content;

import dev.toma.pubgmc.client.gui.widget.Widget;
import dev.toma.pubgmc.util.helper.ImageUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.client.FMLClientHandler;

import java.time.format.DateTimeFormatter;

public class EventWidget extends Widget {

    static DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    static DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
    private final GuiScreen parent;
    private final CommunityEvent event;
    private final String stringCache;

    public EventWidget(GuiScreen parent, int x, int y, int width, int height, CommunityEvent event) {
        super(x, y, width, height);
        this.parent = parent;
        this.event = event;
        this.stringCache = event.getComparationContext().trimmedString();
    }

    @Override
    public void onClick(int mouseX, int mouseY, int button) {
        if(event.isLive()) {
            FMLClientHandler.instance().connectToServer(parent, new ServerData(
                    event.getName(),
                    event.getIP(),
                    false
            ));
        }
    }

    @Override
    public void render(Minecraft mc, int mouseX, int mouseY, float partialTicks) {
        boolean hovered = isMouseOver(mouseX, mouseY);
        ImageUtil.drawShape(x, y, x + width, y + height, 0.0F, 0.0F, 0.0F, 0.25F);
        if(hovered) {
            drawColorShape(x, y, x + width, y + height, 1.0F, 1.0F, 1.0F, 0.2F);
        }
        FontRenderer renderer = mc.fontRenderer;
        renderer.drawString(TextFormatting.YELLOW.toString() + TextFormatting.UNDERLINE + event.getName(), x + 3, y + 4, 0xffffff);
        renderer.drawString(stringCache != null ? stringCache : event.isLive() ? "Live" : "Ended", x + 3, y + 16, 0xffffff);
        String date = event.getScheduledDateTime().toLocalDate().format(dateFormatter);
        int dateWidth = renderer.getStringWidth(date) + 3;
        renderer.drawString(date, x + width - dateWidth, y + 16, 0xffffff);
        String time = event.getScheduledDateTime().toLocalTime().format(timeFormatter) + " UTC";
        int timeWidth = renderer.getStringWidth(time) + 3;
        renderer.drawString(time, x + width - timeWidth, y + 28, 0xffffff);
        if(event.isLive()) {
            long sysTime = System.currentTimeMillis() % 2500L;
            boolean b = sysTime > 1250L;
            float value = b ? 1.0F - (sysTime - 1250F) / 1250F : sysTime / 1250F;
            int red = (int)(value * 255) << 16;
            int color = red | 0xff00;
            renderer.drawString("LIVE! Click to join!", x + 3, y + 28, color);
        } else {
            renderer.drawString("Host: " + event.getHost(), x + 3, y + 28, 0xffffff);
        }
    }
}

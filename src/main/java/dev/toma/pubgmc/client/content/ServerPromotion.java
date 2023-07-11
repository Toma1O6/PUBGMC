package dev.toma.pubgmc.client.content;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import dev.toma.pubgmc.client.gui.menu.GuiMenu;
import dev.toma.pubgmc.client.gui.widget.Widget;
import dev.toma.pubgmc.util.helper.ImageUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.network.ServerStatusResponse;
import net.minecraft.util.JsonUtils;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.client.FMLClientHandler;

public class ServerPromotion extends MenuDisplayContent {

    private final String serverName;
    private final String serverIp;

    public ServerPromotion(String serverName, String serverIp) {
        this.serverName = serverName;
        this.serverIp = serverIp;
    }

    public static ServerPromotion deserialize(JsonObject object, JsonDeserializationContext context) {
        String serverName = JsonUtils.getString(object, "serverName");
        String serverIp = JsonUtils.getString(object, "serverIp");
        return new ServerPromotion(serverName, serverIp);
    }

    @Override
    public Widget createWidget(GuiMenu parent, int x, int y, int width, int height) {
        return new ServerPromotionWidget(parent, x, y, width, height, serverName, serverIp);
    }

    private static final class ServerPromotionWidget extends Widget {

        private static final ITextComponent TITLE = new TextComponentTranslation("label.pubgmc.server_promo");
        private static final ITextComponent CLICK_TO_JOIN_TEXT = new TextComponentTranslation("label.pubgmc.click_to_join");
        private static final ServerStatusResponse EMPTY_RESPONSE = new ServerStatusResponse();
        private final GuiScreen parentScreen;
        private final String serverName;
        private final ServerData serverData;

        public ServerPromotionWidget(GuiScreen parentScreen, int x, int y, int width, int height, String serverName, String serverIp) {
            super(x, y, width, height);
            this.parentScreen = parentScreen;
            this.serverName = serverName;
            this.serverData = new ServerData(serverName, serverIp, false);
        }

        @Override
        public void onClick(int mouseX, int mouseY, int button) {
            FMLClientHandler clientHandler = FMLClientHandler.instance();
            clientHandler.setupServerList();
            clientHandler.bindServerListData(serverData, EMPTY_RESPONSE);
            clientHandler.connectToServer(parentScreen, serverData);
        }

        @Override
        public void render(Minecraft mc, int mouseX, int mouseY, float partialTicks) {
            boolean hovered = isMouseOver(mouseX, mouseY);
            ImageUtil.drawShape(x, y, x + width, y + height, 0.0F, 0.0F, 0.0F, 0.25F);
            if (hovered) {
                drawColorShape(x, y, x + width, y + height, 1.0F, 1.0F, 1.0F, 0.2F);
            }
            FontRenderer font = mc.fontRenderer;
            font.drawString(TITLE.getFormattedText(), x + 3, y + 4, 0xFFFFFF);
            font.drawString(serverName, x + 3, y + 16, 0xFFFFFF);
            font.drawString(CLICK_TO_JOIN_TEXT.getFormattedText(), x + 3, y + 27, 0xFFFFFF);
        }

        static {
            TITLE.getStyle().setColor(TextFormatting.YELLOW);
            TITLE.getStyle().setBold(true);
            CLICK_TO_JOIN_TEXT.getStyle().setUnderlined(true);
            EMPTY_RESPONSE.setFavicon("");
            EMPTY_RESPONSE.setServerDescription(new TextComponentString(""));
            EMPTY_RESPONSE.setVersion(new ServerStatusResponse.Version("forge", 1));
            EMPTY_RESPONSE.setPlayers(new ServerStatusResponse.Players(1, 0));
        }
    }
}

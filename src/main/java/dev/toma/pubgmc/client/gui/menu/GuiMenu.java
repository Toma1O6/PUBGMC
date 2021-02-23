package dev.toma.pubgmc.client.gui.menu;

import dev.toma.pubgmc.Pubgmc;
import dev.toma.pubgmc.client.content.ContentManager;
import dev.toma.pubgmc.client.content.ContentResult;
import dev.toma.pubgmc.client.content.MenuDisplayContent;
import dev.toma.pubgmc.client.content.RefreshListener;
import dev.toma.pubgmc.client.gui.widget.ButtonWidget;
import dev.toma.pubgmc.client.gui.widget.ImageButtonWidget;
import dev.toma.pubgmc.client.gui.widget.Widget;
import dev.toma.pubgmc.util.helper.ImageUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.*;
import net.minecraft.util.ITickable;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.fml.client.GuiModList;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GuiMenu extends GuiWidgets implements RefreshListener {

    static final ResourceLocation DISCORD_ICON = Pubgmc.getResource("textures/gui/menu/discord.png");
    static final ResourceLocation CF_ICON = Pubgmc.getResource("textures/gui/menu/curseforge.png");
    static final ResourceLocation PAYPAL_ICON = Pubgmc.getResource("textures/gui/menu/paypal.png");
    static final ResourceLocation TITLE = Pubgmc.getResource("textures/gui/menu/title.png");
    static final ResourceLocation BACKGROUND_TEXTURE = Pubgmc.getResource("textures/gui/menu/main_menu.png");
    protected String clickedUrl;

    @Override
    public void onRefresh() {
        initGui();
    }

    @Override
    public void init() {
        int wd4 = width / 4;
        int w = 2 * wd4 - 30;
        boolean splitModsAndQuitButtons = height < 280;
        int initialHeight = !splitModsAndQuitButtons ? 80 : 0;
        // anouncements
        addWidget(new EventPanelComponent(15, initialHeight + 20, w, 68));
        // singleplayer
        int hw = w / 2;
        // TODO
        ButtonWidget community = new ButtonWidget(15, initialHeight + 100, hw, 20, "Community maps", (c, x, y, b) -> mc.displayGuiScreen(new GuiWIP(this)));
        ButtonWidget myWorlds = new ButtonWidget(15 + hw, initialHeight + 100, hw, 20, "My Worlds", (c, x, y, b) -> mc.displayGuiScreen(new GuiWorldSelection(this)));
        addWidget(new HoverSplitButton(15, initialHeight + 100, w, 20, "Singleplayer", community, myWorlds));
        // multiplayer
        // TODO
        ButtonWidget official = new ButtonWidget(15, initialHeight + 125, hw, 20, "Official servers", (c, x, y, b) -> mc.displayGuiScreen(new GuiWIP(this)));
        ButtonWidget myServers = new ButtonWidget(15 + hw, initialHeight + 125, hw, 20, "Server list", (c, x, y, b) -> mc.displayGuiScreen(new GuiMultiplayer(this)));
        addWidget(new HoverSplitButton(15, initialHeight + 125, w, 20, "Multiplayer", official, myServers));
        // settings
        addWidget(new ButtonWidget(15, initialHeight + 150, w, 20, "Settings", (c, x, y, b) -> mc.displayGuiScreen(new GuiOptions(this, mc.gameSettings))));
        int lowestPoint;
        // mods & quit buttons
        if (splitModsAndQuitButtons) {
            int half = w / 2;
            addWidget(new ButtonWidget(15, 175, half - 5, 20, "Mods", (c, x, y, b) -> mc.displayGuiScreen(new GuiModList(this))));
            addWidget(new ButtonWidget(15 + half + 5, 175, half - 5, 20, "Quit", (c, x, y, b) -> mc.shutdown()));
            lowestPoint = 195;
        } else {
            addWidget(new ButtonWidget(15, initialHeight + 175, w, 20, "Mods", (c, x, y, b) -> mc.displayGuiScreen(new GuiModList(this))));
            addWidget(new ButtonWidget(15, initialHeight + 200, w, 20, "Quit", (c, x, y, b) -> mc.shutdown()));
            lowestPoint = 220;
        }
        // news panel
        addWidget(new InfoPanelComponent(15 + 2 * wd4, 20, 2 * wd4 - 30, lowestPoint - 20 + initialHeight, this));
        // title
        if (!splitModsAndQuitButtons) addWidget(new Widget(40, 25, w - 50, 50) {
            @Override
            public void render(Minecraft mc, int mouseX, int mouseY, float partialTicks) {
                mc.getTextureManager().bindTexture(TITLE);
                Widget.drawTexturedShape(x, y, x + width, y + height);
            }
        });
        // discord server ad
        addWidget(new LinkImageComponent(0, height - 20, 20, 20, DISCORD_ICON, "https://discord.gg/WmdUKZz", this).withInfo("Official discord server"));
        // official mod host site
        addWidget(new LinkImageComponent(20, height - 20, 20, 20, CF_ICON, "https://www.curseforge.com/minecraft/mc-mods/pubgmc-mod", this).withInfo("CurseForge").notificationOn(Pubgmc.isOutdated()));
        // paypal donation
        addWidget(new LinkImageComponent(40, height - 20, 20, 20, PAYPAL_ICON, "https://www.paypal.com/cgi-bin/webscr?return=https://www.curseforge.com/projects/297074&cn=Add+special+instructions+to+the+addon+author()&business=novotny.tom96%40gmail.com&bn=PP-DonationsBF:btn_donateCC_LG.gif:NonHosted&cancel_return=https://www.curseforge.com/projects/297074&lc=US&item_name=Support+PUBGMC+project+development&cmd=_donations&rm=1&no_shipping=1&currency_code=USD", this).withInfo("Donations"));
    }

    @Override
    public void confirmClicked(boolean result, int id) {
        if (id == 31102009) {
            if (result) {
                this.openWebLink(clickedUrl);
            }

            this.clickedUrl = null;
            this.mc.displayGuiScreen(this);
        }
    }

    void openWebLink(String link) {
        try {
            URI url = new URI(link);
            Class<?> oclass = Class.forName("java.awt.Desktop");
            Object object = oclass.getMethod("getDesktop").invoke(null);
            oclass.getMethod("browse", URI.class).invoke(object, url);
        } catch (Throwable throwable1) {
            Pubgmc.logger.error("Couldn't open link: {}", String.valueOf(link));
        }
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        FontRenderer renderer = mc.fontRenderer;
        mc.getTextureManager().bindTexture(BACKGROUND_TEXTURE);
        Widget.drawTexturedShape(0, 0, width, height);
        drawWidgets(mc, mouseX, mouseY, partialTicks);
        renderer.drawString("Copyright Mojang AB. Do not distribute!", width - 200, height - 10, 0xffffff);
    }

    static class HoverSplitButton extends ButtonWidget {

        private final ButtonWidget b1, b2;

        public HoverSplitButton(int x, int y, int w, int h, String text, ButtonWidget b1, ButtonWidget b2) {
            super(x, y, w, h, text, null);
            this.b1 = b1;
            this.b2 = b2;
        }

        @Override
        public void render(Minecraft mc, int mouseX, int mouseY, float partialTicks) {
            boolean hovered = isMouseOver(mouseX, mouseY);
            if (hovered) {
                b1.render(mc, mouseX, mouseY, partialTicks);
                b2.render(mc, mouseX, mouseY, partialTicks);
            } else {
                drawColorShape(x, y, x + width, y + height, 0.0F, 0.0F, 0.0F, 0.5F);
                if (hovered) {
                    drawColorShape(x, y, x + width, y + height, 1.0F, 1.0F, 1.0F, 0.5F);
                }
                int w = mc.fontRenderer.getStringWidth(text);
                mc.fontRenderer.drawStringWithShadow(text, x + (width - w) / 2.0f, y + (height - mc.fontRenderer.FONT_HEIGHT) / 2.0f, hovered ? 0xffff00 : 0xffffff);
            }
        }

        @Override
        public boolean handleClicked(int mouseX, int mouseY, int mouseButton) {
            return b1.handleClicked(mouseX, mouseY, mouseButton) || b2.handleClicked(mouseX, mouseY, mouseButton);
        }

        @Override
        public void onClick(int mouseX, int mouseY, int button) {
        }
    }

    static class InfoPanelComponent extends Widget {

        private final List<ITextComponent> textComponents = new ArrayList<>();
        private final GuiScreen parent;

        public InfoPanelComponent(int x, int y, int width, int height, GuiScreen screen) {
            super(x, y, width, height);
            ContentResult result = Pubgmc.getContentManager().getCachedResult();
            String[] strings = new String[0];
            if (result != null) {
                strings = result.getNews();
            }
            int max = width - 20;
            FontRenderer renderer = Minecraft.getMinecraft().fontRenderer;
            for (String string : strings) {
                ITextComponent component = ForgeHooks.newChatWithLinks(string, false);
                if (max >= 0) {
                    textComponents.addAll(GuiUtilRenderComponents.splitText(component, max, renderer, true, true));
                }
            }
            if (textComponents.isEmpty()) {
                TextComponentString stc = new TextComponentString(TextFormatting.RED + "Unable to receive latest news, check your internet connection");
                textComponents.addAll(GuiUtilRenderComponents.splitText(stc, max, renderer, true, true));
            }
            int lineLimit = height / (renderer.FONT_HEIGHT + 1) - 1;
            boolean flag = false;
            if (textComponents.size() > lineLimit) {
                lineLimit -= 1;
                flag = true;
            }
            while (textComponents.size() > lineLimit) {
                textComponents.remove(textComponents.size() - 1);
            }
            if (flag)
                textComponents.add(new TextComponentString(TextFormatting.YELLOW.toString() + TextFormatting.ITALIC + "Click to read more..."));
            this.parent = screen;
        }

        @Override
        public void render(Minecraft mc, int mouseX, int mouseY, float partialTicks) {
            drawColorShape(x, y, x + width, y + height, 0.0F, 0.0F, 0.0F, isMouseOver(mouseX, mouseY) ? 0.7F : 0.5F);
            for (int i = 0; i < textComponents.size(); i++) {
                if (i >= textComponents.size()) break;
                ITextComponent component = textComponents.get(i);
                mc.fontRenderer.drawString(component.getFormattedText(), x + 8, y + 5 + i * 10, 0xffffff);
            }
        }

        @Override
        public void onClick(int mouseX, int mouseY, int button) {
            Minecraft.getMinecraft().displayGuiScreen(new GuiNews(parent, Pubgmc.getContentManager()));
        }
    }

    public static class EventPanelComponent extends Widget implements ITickable {

        final int count;
        short timer = 100;
        int currentMsg = 0;
        private final List<Widget> msgComponents = new ArrayList<>();
        private final List<Widget> children = new ArrayList<>();

        public EventPanelComponent(int x, int y, int width, int height) {
            super(x, y, width, height);
            this.count = getMessageCount();
            if (count > 0) {
                Optional<MenuDisplayContent[]> optional = allContent();
                optional.ifPresent(arr -> {
                    for (MenuDisplayContent mdc : arr) {
                        msgComponents.add(mdc.createWidget(x + 4, y + 15, width - 8, 40));
                    }
                });
            }
            updateChilds();
        }

        @Override
        public boolean handleClicked(int mouseX, int mouseY, int mouseButton) {
            for (Widget component : children) {
                if (component.handleClicked(mouseX, mouseY, mouseButton)) {
                    return true;
                }
            }
            return false;
        }

        public void updateChilds() {
            children.clear();
            if (count > 1) {
                children.add(msgComponents.get(currentMsg));
            }
            boolean canFitAll = (width - 8) / 10 > count;
            if (canFitAll) {
                for (int i = 0; i < count; i++) {
                    int px = x + 4 + i * 10;
                    int py = y + height - 9;
                    children.add(new MessageIndicatorComponent(i, this, px, py, 5, 5));
                }
            }
        }

        @Override
        public void update() {
            if (count > 1) {
                if (--timer <= 0) {
                    timer = 100;
                    int nm = currentMsg + 1;
                    currentMsg = nm >= count ? 0 : nm;
                    updateChilds();
                }
            }
        }

        @Override
        public void render(Minecraft mc, int mouseX, int mouseY, float partialTicks) {
            Widget.drawColorShape(x, y, x + width, y + height, 0.0F, 0.0F, 0.0F, 0.5F);
            FontRenderer renderer = mc.fontRenderer;
            renderer.drawString(TextFormatting.BOLD + "Events & Announcements", x + 4, y + 4, 0xffffff);
            if (count > 0) {
                msgComponents.get(currentMsg).render(mc, mouseX, mouseY, partialTicks);
            } else {
                String text = TextFormatting.BOLD + "No events";
                renderer.drawStringWithShadow(text, x + (width - renderer.getStringWidth(text)) / 2.0F, y + (height - renderer.FONT_HEIGHT) / 2.0F, 0xff3333);
            }
            for (Widget child : children) {
                child.render(mc, mouseX, mouseY, partialTicks);
            }
        }

        int getMessageCount() {
            ContentResult cr = Pubgmc.getContentManager().getCachedResult();
            if (cr != null) {
                return cr.getMenuDisplayContents().length;
            }
            return 0;
        }

        Optional<MenuDisplayContent[]> allContent() {
            ContentManager contentManager = Pubgmc.getContentManager();
            ContentResult result = contentManager.getCachedResult();
            if (result == null) {
                return Optional.empty();
            } else return Optional.ofNullable(result.getMenuDisplayContents());
        }

        public void setCurrentMsg(int i) {
            timer = 100;
            currentMsg = i;
            updateChilds();
        }

        public int getCurrentMsg() {
            return currentMsg;
        }

        static class MessageIndicatorComponent extends Widget {

            final EventPanelComponent component;
            final boolean selected;
            final int messageID;

            public MessageIndicatorComponent(int messageID, EventPanelComponent eventComponent, int x, int y, int width, int height) {
                super(x, y, width, height);
                component = eventComponent;
                selected = eventComponent.currentMsg == messageID;
                this.messageID = messageID;
            }

            @Override
            public void onClick(int mouseX, int mouseY, int button) {
                component.setCurrentMsg(messageID);
            }

            @Override
            public void render(Minecraft mc, int mouseX, int mouseY, float partialTicks) {
                drawColorShape(x, y, x + width, y + height, 1.0F, 1.0F, 1.0F, 1.0F);
                if (!selected) {
                    drawColorShape(x + 1, y + 1, x + width - 1, y + height - 1, 0.0F, 0.0F, 0.0F, 1.0F);
                }
            }
        }
    }

    static class LinkImageComponent extends ImageButtonWidget {

        final GuiMenu parent;
        final String link;
        final ResourceLocation texture;
        String info;
        boolean notify;

        LinkImageComponent(int x, int y, int width, int height, ResourceLocation texture, String link, GuiMenu parent) {
            super(x, y, width, height, texture, null);
            this.parent = parent;
            this.link = link;
            this.texture = texture;
        }

        LinkImageComponent notificationOn(boolean notify) {
            this.notify = notify;
            return this;
        }

        public LinkImageComponent withInfo(String info) {
            this.info = info;
            return this;
        }

        @Override
        public void render(Minecraft mc, int mouseX, int mouseY, float partialTicks) {
            super.render(mc, mouseX, mouseY, partialTicks);
            if (notify) {
                long time = System.currentTimeMillis() % 2000L;
                boolean flag = time >= 1000L;
                float f = flag ? 1.0F : 0.0F;
                ImageUtil.drawShape(x + width - 4, y, x + width, y + 4, f, f, f, 1.0F);
                ImageUtil.drawShape(x + width - 3, y + 1, x + width - 1, y + 3, 1.0F, 1.0F, 0.0F, 1.0F);
            }
            if (info != null && isMouseOver(mouseX, mouseY)) {
                String astring = notify ? info + " - Update available" : info;
                int tw = mc.fontRenderer.getStringWidth(astring);
                drawColorShape(x + 10, y - 11, x + 16 + tw, y, 0.0F, 0.0F, 0.0F, 1.0F);
                mc.fontRenderer.drawString(astring, x + 13, y - 9, 0xffffff);
            }
        }

        @Override
        public void onClick(int mouseX, int mouseY, int button) {
            Minecraft mc = Minecraft.getMinecraft();
            parent.clickedUrl = link;
            mc.displayGuiScreen(new GuiConfirmOpenLink(parent, link, 31102009, false));
        }
    }
}

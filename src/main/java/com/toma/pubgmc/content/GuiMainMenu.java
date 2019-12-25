package com.toma.pubgmc.content;

import com.toma.pubgmc.Pubgmc;
import com.toma.pubgmc.util.helper.ImageUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiMultiplayer;
import net.minecraft.client.gui.GuiOptions;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.realms.RealmsBridge;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.GuiModList;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GuiMainMenu extends GuiScreen {

    protected static final ResourceLocation MENU_ICONS = new ResourceLocation(Pubgmc.MOD_ID + ":textures/gui/menu_icons.png");
    protected static final ResourceLocation SP = new ResourceLocation(Pubgmc.MOD_ID + ":img");
    protected static final ResourceLocation MULTIPLAYER = new ResourceLocation(Pubgmc.MOD_ID + ":img");
    protected static final ResourceLocation SETTINGS = new ResourceLocation(Pubgmc.MOD_ID + ":img");
    public static Map<ResourceLocation, MapData[]> DATA;
    private DisplayMenu previous;
    private DisplayMenu displayMenu;

    public GuiMainMenu() {
        this.setDisplayMenu(DisplayMenuTypes.MAIN_MENU);
    }

    @Override
    public void initGui() {
        this.buttonList.clear();
        this.displayMenu.init(this);
    }

    @Override
    protected void keyTyped(char typedChar, int keyCode) {
        if(keyCode == 1) {
            if(previous != null) {
                this.displayMenu = previous;
                this.previous = null;
                this.initGui();
            }
        }
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        ImageUtil.drawShape(0, 0, this.width, this.height, 1.0F, 1.0F, 1.0F);
        this.displayMenu.draw(this, this.mc, mouseX, mouseY, partialTicks);
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
        this.displayMenu.onButtonClicked(this, button);
    }

    private void setDisplayMenu(DisplayMenu menu) {
        this.previous = this.displayMenu;
        this.displayMenu = menu;
        this.initGui();
    }

    public static void createData(final Map<ResourceLocation, List<MapData>> data) {
        DATA = new HashMap<>();
        for(Map.Entry<ResourceLocation, List<MapData>> entry : data.entrySet()) {
            DATA.put(entry.getKey(), entry.getValue().toArray(new MapData[0]));
        }
    }

    private static class DisplayMenuTypes {

        static final DisplayMenu MAIN_MENU = new DisplayMenu() {
            @Override
            public void init(GuiMainMenu menu) {
                int x = menu.width / 4;
                int width = x - 10;
                int height = (int)(menu.height / 1.5);
                menu.addButton(menu.new MenuButtonImage(0, x - width/2, 10, width, height, "Singleplayer", SP));
                menu.addButton(menu.new MenuButtonImage(1, 2*x - width/2, 10, width, height, "Multiplayer", MULTIPLAYER));
                menu.addButton(menu.new MenuButtonImage(2, 3*x - width/2, 10, width, height, "Settings", SETTINGS));
                menu.addButton(menu.new MenuButton(3, x - width/2, height + 20, width, 40, "Mods", true));
                menu.addButton(menu.new MenuButton(4, 2*x - width/2, height + 20, width, 40, "Realms", true));
                menu.addButton(menu.new MenuButton(5, 3*x - width/2, height + 20, width, 40, "Exit", true));
            }

            @Override
            public void onButtonClicked(GuiMainMenu menu, GuiButton button) {
                switch (button.id) {
                    case 0: {
                        menu.setDisplayMenu(SINGLEPLAYER);
                        break;
                    }
                    case 1: {
                        menu.mc.displayGuiScreen(new GuiMultiplayer(menu));
                        break;
                    }
                    case 2: {
                        menu.mc.displayGuiScreen(new GuiOptions(menu, menu.mc.gameSettings));
                        break;
                    }
                    case 3: {
                        menu.mc.displayGuiScreen(new GuiModList(menu));
                        break;
                    }
                    case 4: {
                        RealmsBridge realmsBridge = new RealmsBridge();
                        realmsBridge.switchToRealms(menu);
                        break;
                    }
                    case 5: {
                        menu.mc.shutdown();
                        break;
                    }
                }
            }

            @Override
            public void draw(GuiMainMenu gui, Minecraft mc, int mx, int my, float partialTicks) {
                gui.buttonList.forEach(btn -> btn.drawButton(mc, mx, my, partialTicks));
            }
        };

        static final DisplayMenu SINGLEPLAYER = new DisplayMenu() {
            @Override
            public void init(GuiMainMenu menu) {

            }

            @Override
            public void onButtonClicked(GuiMainMenu menu, GuiButton button) {

            }

            @Override
            public void draw(GuiMainMenu gui, Minecraft mc, int mx, int my, float partialTicks) {

            }
        };

        static final DisplayMenu GAMEMODES = new DisplayMenu() {
            @Override
            public void init(GuiMainMenu menu) {

            }

            @Override
            public void onButtonClicked(GuiMainMenu menu, GuiButton button) {

            }

            @Override
            public void draw(GuiMainMenu gui, Minecraft mc, int mx, int my, float partialTicks) {

            }
        };
    }

    private class MenuButton extends GuiButton {

        private final boolean centeredText;

        public MenuButton(int idx, int x, int y, int w, int h, String text, boolean centered) {
            super(idx, x, y, w, h, text);
            this.centeredText = centered;
        }

        protected void postRender(Minecraft mc) {

        }

        @Override
        public void drawButton(Minecraft mc, int mouseX, int mouseY, float partialTicks) {
            ImageUtil.drawImageWithUV(mc, MENU_ICONS, this.x, this.y, this.width, this.height, 0, this.centeredText ? 64/256D : 0, 64/256D, this.centeredText ? 96/256D : 64/256D, false);
            this.postRender(mc);
            boolean flag = mouseX >= this.x && mouseX <= this.x + this.width && mouseY >= this.y && mouseY <= this.y + this.height;
            if(flag) {
                Tessellator tessellator = Tessellator.getInstance();
                BufferBuilder builder = tessellator.getBuffer();
                builder.begin(7, DefaultVertexFormats.POSITION_COLOR);
                GlStateManager.disableTexture2D();
                GlStateManager.enableBlend();
                float r = 0.0F;
                float g = 1.0F;
                float b = 1.0F;
                float a = 0.25F;
                builder.pos(this.x, this.y + this.height, 0).color(r, g, b, a).endVertex();
                builder.pos(this.x + this.width, this.y + this.height, 0).color(r, g, b, a).endVertex();
                builder.pos(this.x + this.width, this.y, 0).color(r, g, b, a).endVertex();
                builder.pos(this.x, this.y, 0).color(r, g, b, a).endVertex();
                tessellator.draw();
                GlStateManager.enableTexture2D();
                GlStateManager.disableBlend();
            }
            int y = this.centeredText ? this.y + this.height / 2 - 4 : this.y + this.height - 30;
            mc.fontRenderer.drawStringWithShadow(this.displayString, this.x + (this.width - mc.fontRenderer.getStringWidth(this.displayString)) / 2, y, flag ? 0xFFFFFF00 : 0xFFFFFFFF);
        }
    }

    private class MenuButtonImage extends MenuButton {

        private final ResourceLocation texture;

        public MenuButtonImage(int idx, int x, int y, int w, int h, String text, ResourceLocation texture) {
            super(idx, x, y, w, h, text, false);
            this.texture = texture;
        }

        @Override
        protected void postRender(Minecraft mc) {
            ImageUtil.drawCustomSizedImage(mc, this.texture, this.x + 2, this.y + 2, this.width - 4, this.height - 4, false);
        }
    }
}

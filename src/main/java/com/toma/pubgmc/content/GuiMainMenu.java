package com.toma.pubgmc.content;

import com.toma.pubgmc.Pubgmc;
import com.toma.pubgmc.init.GameRegistry;
import com.toma.pubgmc.util.PUBGMCUtil;
import com.toma.pubgmc.util.helper.ImageUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.*;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.realms.RealmsBridge;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.world.storage.SaveFormatOld;
import net.minecraft.world.storage.WorldInfo;
import net.minecraft.world.storage.WorldSummary;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.GuiModList;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GuiMainMenu extends GuiScreen {

    protected static final ResourceLocation MENU_ICONS = new ResourceLocation(Pubgmc.MOD_ID + ":textures/gui/menu_icons.png");
    protected static final ResourceLocation SP = new ResourceLocation(Pubgmc.MOD_ID + ":img");
    protected static final ResourceLocation MULTIPLAYER = new ResourceLocation(Pubgmc.MOD_ID + ":img");
    protected static final ResourceLocation SETTINGS = new ResourceLocation(Pubgmc.MOD_ID + ":img");

    public static Map<ResourceLocation, MapData[]> DATA;
    public Map<String, WorldSummary> MAPS = new HashMap<>();

    private DisplayMenu previous;
    private DisplayMenu displayMenu;
    private long time = System.currentTimeMillis();
    private MenuButtonMap clickedButton;

    public GuiMainMenu() {
        this.setDisplayMenu(DisplayMenuTypes.MAIN_MENU);
    }

    @Override
    public void initGui() {
        this.buttonList.clear();
        this.displayMenu.init(this);
        this.remapFiles();
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        if(mouseButton == 0) {
            for(int i = 0; i < this.buttonList.size(); i++) {
                GuiButton button = this.buttonList.get(i);
                if(button.mousePressed(this.mc, mouseX, mouseY)) {
                    GuiScreenEvent.ActionPerformedEvent.Pre event = new GuiScreenEvent.ActionPerformedEvent.Pre(this, button, buttonList);
                    if(MinecraftForge.EVENT_BUS.post(event)) break;
                    button = event.getButton();
                    this.selectedButton = button;
                    if(this.selectedButton instanceof MenuButtonMap) {
                        this.clickedButton = (MenuButtonMap) button;
                    }
                    button.playPressSound(this.mc.getSoundHandler());
                    this.actionPerformed(button);
                    if(this == this.mc.currentScreen) {
                        MinecraftForge.EVENT_BUS.post(new GuiScreenEvent.ActionPerformedEvent.Post(this, button, buttonList));
                    }
                }
            }
            if(this.selectedButton == null) this.clickedButton = null;
            this.displayMenu.mouseClick();
        }
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
        if(System.currentTimeMillis() - time < 300) {
            return;
        }
        this.displayMenu.onButtonClicked(this, button);
        this.time = System.currentTimeMillis();
    }

    @Override
    public void handleMouseInput() throws IOException {
        super.handleMouseInput();
        this.displayMenu.onMouseScroll();
    }

    public boolean hasSelectedButton() {
        return this.clickedButton != null;
    }

    public List<GuiButton> getButtonList() {
        return this.buttonList;
    }

    public MenuButtonMap getClickedButton() {
        return clickedButton;
    }

    public void setDisplayMenu(DisplayMenu menu) {
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

    public void remapFiles() {
        MAPS = new HashMap<>();
        File file = new File(mc.mcDataDir, "pubgmc-content");
        File[] files = file.listFiles();
        for(File f : files) {
            if(f.isDirectory()) {
                String s = f.getName();
                WorldInfo worldInfo = this.getWorldInfo(s, file.getAbsolutePath());
                if(worldInfo != null && (worldInfo.getSaveVersion() == 19132 || worldInfo.getSaveVersion() == 19133)) {
                    boolean flag = worldInfo.getSaveVersion() != 19133;
                    String name = worldInfo.getWorldName();
                    if(StringUtils.isEmpty(name)) {
                        name = s;
                    }
                    MAPS.put(name, new WorldSummary(worldInfo, s, name, 0L, flag));
                }
            }
        }
    }

    private WorldInfo getWorldInfo(String mapDir, String contentDir) {
        File file = new File(contentDir, mapDir);
        DataFixer dataFixer = mc.getDataFixer();
        if(!file.exists()) {
            return null;
        } else {
            File map = new File(file, "level.dat");
            if(map.exists()) {
                WorldInfo worldInfo = SaveFormatOld.getWorldData(map, dataFixer);
                if(worldInfo != null) {
                    return worldInfo;
                }
            }
            map = new File(file, "level.dat_old");
            return map.exists() ? SaveFormatOld.getWorldData(map, dataFixer) : null;
        }
    }

    public static final class DisplayMenuTypes {

        public static final DisplayMenu MAIN_MENU = new DisplayMenu() {
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
                ImageUtil.drawShape(0, 0, gui.width, gui.height, 0.2F, 0.2F, 0.2F);
                gui.buttonList.forEach(btn -> btn.drawButton(mc, mx, my, partialTicks));
            }
        };

        public static final DisplayMenu SINGLEPLAYER = new DisplayMenu() {
            @Override
            public void init(GuiMainMenu menu) {
                int x = menu.width / 3;
                int w = x - 10;
                int height = (int)(menu.height / 1.5);
                menu.buttonList.add(menu.new MenuButtonImage(0, x - w / 2, 10, w, height, "Normal", SP));
                menu.buttonList.add(menu.new MenuButtonImage(1, 2*x - w / 2, 10, w, height, "Gamemodes", SP));
                menu.buttonList.add(menu.new MenuButton(2, (int)(1.5*x) - w / 2, height + 20, w, 40, "Back", true));
            }

            @Override
            public void onButtonClicked(GuiMainMenu menu, GuiButton button) {
                switch (button.id) {
                    case 0: {
                        menu.mc.displayGuiScreen(new GuiWorldSelection(menu));
                        break;
                    }
                    case 1: {
                        menu.setDisplayMenu(GAMEMODES);
                        break;
                    }
                    case 2: {
                        menu.setDisplayMenu(MAIN_MENU);
                        break;
                    }
                }
            }

            @Override
            public void draw(GuiMainMenu gui, Minecraft mc, int mx, int my, float partialTicks) {
                ImageUtil.drawShape(0, 0, gui.width, gui.height, 0.2F, 0.2F, 0.2F);
                gui.buttonList.forEach(btn -> btn.drawButton(mc, mx, my, partialTicks));
            }
        };

        public static final DisplayMenu GAMEMODES = new DisplayMenu() {

            private static final int PER_PAGE = 0x4;
            private List<ResourceLocation> modeList;
            private int page = 0;

            @Override
            public void init(GuiMainMenu menu) {
                menu.buttonList.clear();
                this.modeList = new ArrayList<>(DATA.keySet());
                this.fill(menu);
            }

            @Override
            public void onButtonClicked(GuiMainMenu menu, GuiButton button) {
                int id = button.id;
                if(id >= 0 && id < PER_PAGE) {
                    // TODO
                    // never should be anything else, so safe to cast
                    MenuButtonMode btn = (MenuButtonMode) button;
                    menu.setDisplayMenu(new DisplayMenuMaps(btn.modeName, DATA.get(btn.modeName), menu));
                } else if(id == PER_PAGE) {
                    // descrease page num
                    if(this.page == 0) {
                        this.init(menu);
                        return;
                    }
                    this.page--;
                    this.init(menu);
                } else if(id == PER_PAGE + 1) {
                    if(this.page == this.modeList.size() - PER_PAGE) {
                        this.init(menu);
                        return;
                    }
                    this.page++;
                    this.init(menu);
                } else if(id == PER_PAGE + 2) {
                    menu.setDisplayMenu(SINGLEPLAYER);
                }
            }

            @Override
            public void draw(GuiMainMenu gui, Minecraft mc, int mx, int my, float partialTicks) {
                ImageUtil.drawShape(0, 0, gui.width, gui.height, 0.2f, 0.2f, 0.2f);
                gui.buttonList.forEach(btn -> btn.drawButton(mc, mx, my, partialTicks));
            }

            private void fill(GuiMainMenu instance) {
                int x = instance.width / (PER_PAGE);
                int w = x - 5;
                int h = (int)(instance.height / 1.2);
                for(int i = this.page; i < this.page + PER_PAGE; i++) {
                    if(this.modeList.size() > i) {
                        ResourceLocation rl = this.modeList.get(i);
                        try {
                            instance.buttonList.add(instance.new MenuButtonMode(i % PER_PAGE, (1 + i % PER_PAGE)*x, 10, w, h, rl));
                        } catch (NullPointerException e) {
                            Pubgmc.logger.fatal("No gamemode exists under {} id! Report this error!", rl);
                        }
                    } else break;
                }
                MenuButtonArrow left = instance.new MenuButtonArrow(PER_PAGE, 10, h - 55, 30, 30, ArrowDirection.LEFT);
                MenuButtonArrow right = instance.new MenuButtonArrow(PER_PAGE + 1, 10, h - 20, 30, 30, ArrowDirection.RIGHT);
                left.visible = this.page > 0;
                right.visible = this.page < this.modeList.size() - (PER_PAGE + 1);
                instance.buttonList.add(left);
                instance.buttonList.add(right);
                instance.buttonList.add(instance.new MenuButton(PER_PAGE + 2, 10, h + 15, 60, 20, "Back", true));
            }
        };

        public static final DisplayMenu MAP_ADD = new DisplayMenu() {
            @Override
            public void init(GuiMainMenu menu) {

            }

            @Override
            public void draw(GuiMainMenu gui, Minecraft mc, int mx, int my, float partialTicks) {

            }

            @Override
            public void onButtonClicked(GuiMainMenu menu, GuiButton button) throws IOException {

            }
        };
    }

    public class MenuButton extends GuiButton {

        private final boolean centeredText;

        public MenuButton(int idx, int x, int y, int w, int h, String text, boolean centered) {
            super(idx, x, y, w, h, text);
            this.centeredText = centered;
        }

        protected void render(Minecraft mc) {

        }

        @Override
        public void drawButton(Minecraft mc, int mouseX, int mouseY, float partialTicks) {
            ImageUtil.drawImageWithUV(mc, MENU_ICONS, this.x, this.y, this.width, this.height, 0, this.centeredText ? 64/256D : 0, 64/256D, this.centeredText ? 96/256D : 64/256D, false);
            this.render(mc);
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

    public class MenuButtonImage extends MenuButton {

        private final ResourceLocation texture;

        public MenuButtonImage(int idx, int x, int y, int w, int h, String text, ResourceLocation texture) {
            super(idx, x, y, w, h, text, false);
            this.texture = texture;
        }

        @Override
        protected void render(Minecraft mc) {
            ImageUtil.drawCustomSizedImage(mc, this.texture, this.x + 2, this.y + 2, this.width - 4, this.height - 4, false);
        }
    }

    public class MenuButtonArrow extends MenuButton {
        private final ArrowDirection arrowDirection;

        public MenuButtonArrow(int idx, int x, int y, int w, int h, ArrowDirection direction) {
            super(idx, x, y, w, h, "", false);
            this.arrowDirection = direction;
        }

        @Override
        protected void render(Minecraft mc) {
            Tessellator tessellator = Tessellator.getInstance();
            BufferBuilder builder = tessellator.getBuffer();
            GlStateManager.disableTexture2D();
            builder.begin(4, DefaultVertexFormats.POSITION_COLOR);
            float x = this.width / 4F;
            float y = this.height / 4F;
            this.arrowDirection.getConsumer().apply(this.x + x, this.y + y, this.x + x * 3, this.y + y * 3, builder);
            tessellator.draw();
            GlStateManager.enableTexture2D();
        }
    }

    public class MenuButtonMode extends MenuButtonImage {

        private final ResourceLocation modeName;

        /**
         * @param idx - button ID
         * @param x - X coord
         * @param y - Y coord
         * @param w - Width
         * @param h - Height
         * @param mode - Full game registry name
         * @throws NullPointerException - when no gamemode exists under specified ResourceLocation
         */
        public MenuButtonMode(int idx, int x, int y, int w, int h, ResourceLocation mode) throws NullPointerException {
            super(idx, x, y, w, h, mode.getResourcePath().toUpperCase(), GameRegistry.getGame(mode).guiImage);
            this.modeName = mode;
        }

        @Override
        protected void render(Minecraft mc) {

        }
    }

    public class MenuButtonMap extends MenuButton {

        private final MapData data;
        private MapDownloader downloader;
        private MapButtonState state = MapButtonState.NORMAL;
        private GuiButton download;

        public MenuButtonMap(int idx, int x, int y, int w, int h, MapData data) {
            super(idx, x, y, w, h, "", false);
            this.data = data;
            if(!data.isDownloaded) {
                this.downloader = new MapDownloader(data, this);
                this.downloader.initThread();
            }
            this.download = new GuiButton(-1, this.x + this.width - 90, this.y + (this.height - 20) / 2, 50, 20, "Download");
        }

        @Override
        public boolean mousePressed(Minecraft mc, int mouseX, int mouseY) {
            if(this.download.mousePressed(mc, mouseX, mouseY) && this.download.visible) {
                this.downloader.download();
                this.download.playPressSound(mc.getSoundHandler());
                return false;
            }
            return super.mousePressed(mc, mouseX, mouseY);
        }

        public void onDownloadFinished() {
            this.downloader = null;
            GuiMainMenu.this.remapFiles();
        }

        public synchronized void updateState(MapButtonState state) {
            this.state = state;
        }

        @Override
        public void drawButton(Minecraft mc, int mouseX, int mouseY, float partialTicks) {
            boolean selected = GuiMainMenu.this.clickedButton == this;
            if(selected) {
                ImageUtil.drawShape(this.x - 2, this.y - 2, this.x + this.width + 2, this.y + this.height + 2, 1.0F, 1.0F, 1.0F);
            }
            ImageUtil.drawShape(this.x, this.y, this.x + this.width, this.y + this.height, 0.1F, 0.1F, 0.1F);
            this.hovered = mouseX >= this.x && mouseX <= this.x + this.width && mouseY >= this.y && mouseY <= this.y + this.height;
            if(data.isNew) {
                ImageUtil.drawShape(this.x + this.width - 30, this.y, this.x + this.width, this.y + 15, 1.0F, 0.0F, 0.0F);
                mc.fontRenderer.drawStringWithShadow("NEW!", this.x + this.width - 24.5F, this.y + 4, 0xFFFFFFFF);
            }
            mc.fontRenderer.drawStringWithShadow(this.data.displayName, this.x + 40, this.y + 5, 0xFFFFFFFF);
            mc.fontRenderer.drawStringWithShadow("Version: " + this.data.version, this.x + 40, this.y + 16, 0xFFFFFFFF);
            mc.fontRenderer.drawStringWithShadow("Author" + (this.data.authors.length > 1 ? "s: " : ": ") + PUBGMCUtil.convertStringArray(this.data.authors), this.x + 40, this.y + 27, 0xFFFFFFFF);
            this.state.drawButtonOverlay(this.x, this.y, this.width, this.height, mc);
            if(this.hovered) {
                if(!selected) {
                    GlStateManager.disableTexture2D();
                    GlStateManager.enableBlend();
                    Tessellator tessellator = Tessellator.getInstance();
                    BufferBuilder builder = tessellator.getBuffer();
                    builder.begin(7, DefaultVertexFormats.POSITION_COLOR);
                    builder.pos(this.x, this.y + this.height, 0).color(1.0F, 1.0F, 1.0F, 0.15F).endVertex();
                    builder.pos(this.x + this.width, this.y + this.height, 0).color(1.0F, 1.0F, 1.0F, 0.15F).endVertex();
                    builder.pos(this.x + this.width, this.y, 0).color(1.0F, 1.0F, 1.0F, 0.15F).endVertex();
                    builder.pos(this.x, this.y, 0).color(1.0F, 1.0F, 1.0F, 0.15F).endVertex();
                    tessellator.draw();
                    GlStateManager.disableBlend();
                    GlStateManager.enableTexture2D();
                }
            }
            this.download.visible = false;
            if(this.downloader != null && !this.downloader.isDownloading()) {
                this.download.visible = true;
                this.download.drawButton(mc, mouseX, mouseY, partialTicks);
            }
        }

        public MapData getData() {
            return data;
        }
    }
}

package dev.toma.pubgmc.content;

import dev.toma.pubgmc.Pubgmc;
import dev.toma.pubgmc.util.helper.ImageUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiWorldSelection;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.FMLClientHandler;
import org.lwjgl.input.Mouse;

import java.io.File;

/* design idea
mode name
---------
maps
..
..
---------
some random buttons
*/
public class DisplayMenuMaps implements DisplayMenu {

    private final GuiMainMenu menu;
    private final String name;
    private final MapData[] modeMaps;
    // for animation I guess
    private boolean firstOpen;
    private int scrollIdx;
    private int displayMapListSize;

    public DisplayMenuMaps(ResourceLocation resourceLocation, MapData[] maps, GuiMainMenu menu) {
        this.menu = menu;
        String str = resourceLocation.getResourcePath();
        this.name = str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase();
        this.modeMaps = maps;
        this.firstOpen = false;
        this.scrollIdx = 0;
    }

    @Override
    public void onMouseScroll() {
        int i = -Integer.signum(Mouse.getEventDWheel());
        if(i > 0 && this.scrollIdx < this.modeMaps.length - this.displayMapListSize) {
            this.scrollIdx++;
            this.init(this.menu);
        } else if(i < 0 && this.scrollIdx > 0) {
            this.scrollIdx--;
            this.init(this.menu);
        }
    }

    @Override
    public void mouseClick() {
        if(this.menu.getButtonList().isEmpty()) return;
        boolean b = this.menu.hasSelectedButton() && this.modeMaps[this.scrollIdx + this.menu.getButtonList().indexOf(this.menu.getClickedButton())].isDownloaded;
        GuiButton button = this.menu.getButtonList().get(this.displayMapListSize);
        button.displayString = "Play";
        button.enabled = b;
    }

    @Override
    public void init(GuiMainMenu menu) {
        menu.getButtonList().clear();
        this.displayMapListSize = (menu.height - 80) / 40;
        if(this.displayMapListSize == 0) {
            Pubgmc.logger.fatal("Cannot display maps, gui is too small!");
            return;
        }
        for(int i = scrollIdx; i < scrollIdx + this.displayMapListSize; i++) {
            if(i >= this.modeMaps.length) break;
            int idx = i - this.scrollIdx;
            MapData data = this.modeMaps[i];
            menu.getButtonList().add(menu.new MenuButtonMap(idx, 20, 40 + idx * 45, menu.width - 40, 40, data));
        }
        int x = 10;
        int y = menu.height - 25;
        int w = (menu.width - 20) / 3;
        GuiButton playButton = new GuiButton(this.displayMapListSize, x, y, w - 10, 20, "Play");
        playButton.enabled = false;
        menu.getButtonList().add(playButton);
        menu.getButtonList().add(new GuiButton(this.displayMapListSize + 1, x + w, y, w - 10, 20, "Add your map"));
        menu.getButtonList().add(new GuiButton(this.displayMapListSize + 2, x + 2 * w, y, w, 20, "Back"));
    }

    @Override
    public void onButtonClicked(GuiMainMenu menu, GuiButton button) {
        boolean b = false;
        if(button.id < this.displayMapListSize) {
            b = true;
        }
        menu.getButtonList().get(this.displayMapListSize).enabled = b;
        if(button.id == this.displayMapListSize) {
            boolean isDownloaded = this.checkDownloaded();
            if(isDownloaded) {
                String mapFile = menu.getClickedButton().getData().displayName;
                boolean canLoad = menu.mc.getSaveLoader().canLoadWorld(mapFile);
                if(canLoad(menu, mapFile)) {
                    FMLClientHandler.instance().tryLoadExistingWorld(new GuiWorldSelection(menu), menu.MAPS.get(mapFile));
                }
            } else {
                new MapDownloader(menu.getClickedButton().getData(), menu.getClickedButton());
            }
        } else if(button.id == this.displayMapListSize + 1) {
            menu.setDisplayMenu(GuiMainMenu.DisplayMenuTypes.MAP_ADD);
        } else if(button.id == this.displayMapListSize + 2) {
            menu.setDisplayMenu(GuiMainMenu.DisplayMenuTypes.GAMEMODES);
        }
    }

    @Override
    public void draw(GuiMainMenu gui, Minecraft mc, int mx, int my, float partialTicks) {
        ImageUtil.drawShape(0, 0, gui.width, gui.height, 0.2F, 0.2F, 0.2F);
        GlStateManager.disableTexture2D();
        GlStateManager.enableBlend();
        double hx = gui.width / 2.0D;
        float min = 0.25F;
        float max = 0.50F;
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder builder = tessellator.getBuffer();
        builder.begin(7, DefaultVertexFormats.POSITION_COLOR);
        builder.pos(0, 30, 0).color(0.0F, 0.0F, 0.0F, min).endVertex();
        builder.pos(hx, 30, 0).color(0.0F, 0.0F, 0.0F, max).endVertex();
        builder.pos(hx, 0, 0).color(0.0F, 0.0F, 0.0F, max).endVertex();
        builder.pos(0, 0, 0).color(0.0F, 0.0F, 0.0F, min).endVertex();
        builder.pos(hx, 30, 0).color(0.0F, 0.0F, 0.0F, max).endVertex();
        builder.pos(gui.width, 30, 0).color(0.0F, 0.0F, 0.0F, min).endVertex();
        builder.pos(gui.width, 0, 0).color(0.0F, 0.0F, 0.0F, min).endVertex();
        builder.pos(hx, 0, 0).color(0.0F, 0.0F, 0.0F, max).endVertex();
        builder.pos(0, gui.height, 0).color(0.0F, 0.0F, 0.0F, min).endVertex();
        builder.pos(hx, gui.height, 0).color(0.0F, 0.0F, 0.0F, max).endVertex();
        builder.pos(hx, gui.height - 30, 0).color(0.0F, 0.0F, 0.0F, max).endVertex();
        builder.pos(0, gui.height - 30, 0).color(0.0F, 0.0F, 0.0F, min).endVertex();
        builder.pos(hx, gui.height, 0).color(0.0F, 0.0F, 0.0F, max).endVertex();
        builder.pos(gui.width, gui.height, 0).color(0.0F, 0.0F, 0.0F, min).endVertex();
        builder.pos(gui.width, gui.height - 30, 0).color(0.0F, 0.0F, 0.0F, min).endVertex();
        builder.pos(hx, gui.height - 30, 0).color(0.0F, 0.0F, 0.0F, max).endVertex();
        tessellator.draw();
        builder.begin(3, DefaultVertexFormats.POSITION_COLOR);
        GlStateManager.glLineWidth(5);
        builder.pos(0, 29, 0).color(0.0F, 0.0F, 0.0F, 1.0F).endVertex();
        builder.pos(gui.width, 29, 0).color(0.0F, 0.0F, 0.0F, 1.0F).endVertex();
        tessellator.draw();
        builder.begin(3, DefaultVertexFormats.POSITION_COLOR);
        builder.pos(0, gui.height - 30, 0).color(0.0F, 0.0F, 0.0F, 1.0F).endVertex();
        builder.pos(gui.width, gui.height - 30, 0).color(0.0F, 0.0F, 0.0F, 1.0F).endVertex();
        tessellator.draw();
        GlStateManager.disableBlend();
        GlStateManager.enableTexture2D();
        int i = mc.fontRenderer.getStringWidth(this.name);
        mc.fontRenderer.drawStringWithShadow(this.name, (float)hx - (i / 2.0F), 12, 0xFFFFFFFF);
        gui.getButtonList().forEach(btn -> btn.drawButton(mc, mx, my, partialTicks));
    }

    private boolean checkDownloaded() {
        return this.menu.getClickedButton() == null ? false : this.menu.getClickedButton().getData().isDownloaded;
    }

    private boolean canLoad(GuiMainMenu menu, String mapFile) {
        File f = new File(GuiLoadCommunityContent.contentFolder.getAbsolutePath() + File.separator + mapFile);
        return f.isDirectory();
    }
}

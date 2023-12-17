package dev.toma.pubgmc.client.games.screen;

import dev.toma.pubgmc.api.game.loadout.EntityLoadout;
import dev.toma.pubgmc.client.gui.menu.GuiWidgets;
import dev.toma.pubgmc.client.gui.widget.VanillaButtonWidget;
import dev.toma.pubgmc.client.gui.widget.Widget;
import dev.toma.pubgmc.network.PacketHandler;
import dev.toma.pubgmc.network.c2s.C2S_PacketSelectLoadout;
import dev.toma.pubgmc.util.helper.ImageUtil;
import dev.toma.pubgmc.util.helper.TextComponentHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;

import javax.annotation.Nullable;
import java.util.List;
import java.util.function.BiConsumer;

public class LoadoutSelectGui extends GuiWidgets {

    private static final ITextComponent HEADER = new TextComponentTranslation("label.pubgmc.select_loadout");

    private static final int COLUMN_COUNT = 6;
    private static final int WIDTH = 60;
    private static final int HEIGHT = 50;
    private static final int MARGIN = 10;
    private final List<EntityLoadout> loadoutList;

    private VanillaButtonWidget confirmButton;
    private EntityLoadout selected;
    private int index;

    public LoadoutSelectGui(List<EntityLoadout> loadoutList) {
        this.loadoutList = loadoutList;
    }

    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }

    @Override
    public void init() {
        int loadoutCount = loadoutList.size();
        int rows = (loadoutCount - 1) / COLUMN_COUNT + 1;
        int rowCount = Math.min(loadoutCount, COLUMN_COUNT);
        int width = Math.min(loadoutCount, COLUMN_COUNT) * (WIDTH + MARGIN) - MARGIN;
        int height = rows * (HEIGHT + MARGIN) - MARGIN;
        int left = (this.width - width) / 2;
        int top = (this.height - height) / 2;
        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < rowCount; x++) {
                int loadoutIndex = x + y * COLUMN_COUNT;
                if (loadoutIndex >= loadoutCount)
                    break;
                EntityLoadout loadout = loadoutList.get(loadoutIndex);
                addWidget(new LoadoutWidget(left + x * (WIDTH + MARGIN), top + y * (HEIGHT + MARGIN), WIDTH, HEIGHT, loadoutIndex, loadout, this::loadoutSelected));
            }
        }

        confirmButton = addWidget(new VanillaButtonWidget(this.width - 110, this.height - 30, 100, 20, TextComponentHelper.CONFIRM.getFormattedText(), (widget, mouseX, mouseY, button) -> confirmed()));
        setSelected(selected);
    }

    @Override
    protected void keyTyped(char typedChar, int keyCode) {
        // Do nothing
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        ImageUtil.drawShape(0, 0, width, height, 0x66 << 24);
        ImageUtil.drawGradient(0, 0, width, 20, 0x66 << 24, 0);
        String header = HEADER.getFormattedText();
        int headerWidth = fontRenderer.getStringWidth(header);
        fontRenderer.drawString(header, (width - headerWidth) / 2.0F, 5, 0xFFFFFF, false);
        drawWidgets(mc, mouseX, mouseY, partialTicks);
    }

    private void loadoutSelected(EntityLoadout loadout, int index) {
        if (this.selected == loadout) {
            setSelected(null);
            this.index = -1;
        } else {
            setSelected(loadout);
            this.index = index;
        }
    }

    private void setSelected(@Nullable EntityLoadout loadout) {
        selected = loadout;
        confirmButton.setActive(selected != null);
    }

    private void confirmed() {
        if (selected == null)
            return;
        PacketHandler.sendToServer(new C2S_PacketSelectLoadout(index));
        mc.displayGuiScreen(null);
    }

    private final class LoadoutWidget extends Widget {

        private final EntityLoadout loadout;
        private final int loadoutIndex;
        private final BiConsumer<EntityLoadout, Integer> clickConsumer;

        public LoadoutWidget(int x, int y, int width, int height, int index, EntityLoadout loadout, BiConsumer<EntityLoadout, Integer> onClick) {
            super(x, y, width, height);
            this.loadoutIndex = index;
            this.loadout = loadout;
            this.clickConsumer = onClick;
        }

        @Override
        public void onClick(int mouseX, int mouseY, int button) {
            clickConsumer.accept(loadout, loadoutIndex);
        }

        @Override
        public void render(Minecraft mc, int mouseX, int mouseY, float partialTicks) {
            boolean selected = LoadoutSelectGui.this.selected == this.loadout;
            boolean hovered = isMouseOver(mouseX, mouseY);
            boolean highlight = selected || hovered;
            ImageUtil.drawShape(x - 1, y - 1, x + width + 1, y + height + 1, 0xFF << 24);
            if (highlight) {
                ImageUtil.drawShape(x - 2, y - 2, x + width + 2, y + height + 2, 0xFFFFFFFF);
            }
            int colorMax = selected ? 0xFFAAAA00 : 0xFF00AA00;
            int colorMin = selected ? 0xFF666600 : 0xFF006600;
            ImageUtil.drawGradient(x, y, x + width, y + height, colorMax, colorMin);
            if (!loadout.getIcon().isEmpty()) {
                RenderItem itemRenderer = mc.getRenderItem();
                int iconLeft = x + width / 2 - 8;
                int iconTop = y + 5;
                ImageUtil.drawShape(iconLeft - 3, iconTop - 3, iconLeft + 19, iconTop + 19, 0xFF << 24);
                ImageUtil.drawGradient(iconLeft - 1, iconTop - 1, iconLeft + 17, iconTop + 17, 0xFF333333, 0xFF777777);
                itemRenderer.renderItemIntoGUI(loadout.getIcon(), x + (width / 2) - 8, y + 5);
            }
            String name = loadout.getName();
            if (name != null && !name.isEmpty()) {
                int nameWidth = mc.fontRenderer.getStringWidth(name);
                mc.fontRenderer.drawString(name, x + (width - nameWidth) / 2.0F, y + height - 12, 0xFFFFFF, true);
            }
            GlStateManager.color(1.0F, 1.0F, 1.0F);
        }
    }
}

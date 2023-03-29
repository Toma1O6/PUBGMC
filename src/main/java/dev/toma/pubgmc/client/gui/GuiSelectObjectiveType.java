package dev.toma.pubgmc.client.gui;

import dev.toma.pubgmc.Pubgmc;
import dev.toma.pubgmc.api.objectives.types.GameArea;
import dev.toma.pubgmc.util.helper.ImageUtil;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.input.Mouse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GuiSelectObjectiveType extends GuiScreenCentered {

    private static final ResourceLocation TEXTURE = new ResourceLocation(Pubgmc.MOD_ID, "textures/gui/select_objective.png");
    private final ItemStack stack;
    private int scrollIndex = 0;
    private final int pageAmount;
    private final List<TypeButton> displayList = new ArrayList<>();
    private final List<GameArea.AreaType> types = new ArrayList<>(GameArea.Types.TYPE_MAP.values());

    public GuiSelectObjectiveType(ItemStack stack) {
        this.stack = stack;
        this.pageAmount = types.size() / 5 + (types.size() % 5 > 0 ? 1 : 0);
    }

    @Override
    public void initGui() {
        this.setDimension(125, 166).calculateGuiPosition();
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.mc.getTextureManager().bindTexture(TEXTURE);
        this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.getGuiWidth(), this.getGuiHeight());
        this.displayList.forEach(button -> button.drawButton(this.mc, mouseX, mouseY, partialTicks));
        this.renderScrollIcon();
    }

    public void updateDisplayList() {
        this.displayList.clear();
        int j = 0;
        for (int i = scrollIndex * 5; i < (scrollIndex + 1) * 5; i++) {
            if (i >= types.size()) return;
            this.displayList.add(new TypeButton(j, guiLeft, guiTop, types.get(i)));
            ++j;
        }
    }

    @Override
    public void handleMouseInput() throws IOException {
        super.handleMouseInput();
        int i = Integer.signum(Mouse.getEventDWheel());
        this.tryScroll(i);
    }

    private void tryScroll(int i) {
        if (this.scrollIndex + i >= 0 && this.scrollIndex + i < this.pageAmount) {
            this.scrollIndex += i;
            this.updateDisplayList();
        }
    }

    private void renderScrollIcon() {
        // TODO check and improve
        double d0 = pageAmount == 1 ? 0 : 150 - 1F / pageAmount;
        double d1 = 1 / (pageAmount + 1D);
        ImageUtil.drawImageWithUV(this.mc, TEXTURE, guiLeft + 111, guiTop + 8 + (int) (d0 == 1 ? 0 : d0), 8, 150 * d1, 125 / 256D, 0, 133 / 256D, 50 / 256D, false);
    }

    private class TypeButton extends GuiButton {

        private final GameArea.AreaType areaType;

        public TypeButton(int idx, int x, int y, GameArea.AreaType type) {
            super(idx, x + 8, y + 8 + idx * 30, 100, 20, type.getDisplayName());
            this.areaType = type;
        }
    }
}

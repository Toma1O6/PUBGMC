package dev.toma.pubgmc.client.gui.menu;

import dev.toma.pubgmc.Pubgmc;
import dev.toma.pubgmc.client.content.ContentManager;
import dev.toma.pubgmc.client.gui.widget.ButtonWidget;
import dev.toma.pubgmc.client.gui.widget.Widget;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.text.TextFormatting;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

import java.io.IOException;

public class GuiHallOfFame extends GuiWidgets {

    final GuiScreen lastScreen;
    String[] vipPatrons;
    int scrollIndex;
    int displayOnPage;

    public GuiHallOfFame(GuiScreen lastScreen) {
        this.lastScreen = lastScreen;
    }

    @Override
    public void handleMouseInput() throws IOException {
        int n = -(Integer.signum(Mouse.getEventDWheel()));
        int m = scrollIndex + n;
        if (m >= 0 && m <= vipPatrons.length - displayOnPage) {
            scrollIndex = m;
            initGui();
        }
        super.handleMouseInput();
    }

    @Override
    public void init() {
        ContentManager cm = Pubgmc.getContentManager();
        if (cm.getCachedResult() != null) {
            vipPatrons = cm.getCachedResult().getVipPatrons();
        }
        displayOnPage = (height - 60) / 12;
        int center = width / 2;
        FontRenderer renderer = mc.fontRenderer;
        for (int i = scrollIndex; i < Math.min(scrollIndex + displayOnPage, vipPatrons.length); i++) {
            int j = i - scrollIndex;
            String name = vipPatrons[i];
            int textWidth = renderer.getStringWidth(name) + 20;
            addWidget(new NameWidget(name, center - textWidth / 2, 30 + j * 12, textWidth, 30));
        }
        addWidget(new ButtonWidget(center - 60, height - 30, 120, 20, "Back to menu", (w, x, y, b) -> mc.displayGuiScreen(lastScreen)));
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        mc.getTextureManager().bindTexture(GuiMenu.BACKGROUND_TEXTURE);
        Widget.drawTexturedShape(0, 0, width, height);
        boolean mustFix = width % 2 != 0;
        int shadeWidth = width / 2;
        float minA = 0.75F;
        float maxA = 0.05F;
        GlStateManager.color(1.0F, 1.0F, 1.0F);
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder builder = tessellator.getBuffer();
        GlStateManager.disableTexture2D();
        GlStateManager.enableBlend();
        GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
        GlStateManager.shadeModel(GL11.GL_SMOOTH);
        builder.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_COLOR);
        builder.pos(0, height, 0).color(0.0F, 0.0F, 0.0F, maxA).endVertex();
        builder.pos(shadeWidth + (mustFix ? 1 : 0), height, 0).color(0.0F, 0.0F, 0.0F, minA).endVertex();
        builder.pos(shadeWidth + (mustFix ? 1 : 0), 0, 0).color(0.0F, 0.0F, 0.0F, minA).endVertex();
        builder.pos(0, 0, 0).color(0.0F, 0.0F, 0.0F, maxA).endVertex();
        builder.pos(width - shadeWidth, height, 0).color(0.0F, 0.0F, 0.0F, minA).endVertex();
        builder.pos(width, height, 0).color(0.0F, 0.0F, 0.0F, maxA).endVertex();
        builder.pos(width, 0, 0).color(0.0F, 0.0F, 0.0F, maxA).endVertex();
        builder.pos(width - shadeWidth, 0, 0).color(0.0F, 0.0F, 0.0F, minA).endVertex();
        tessellator.draw();
        GlStateManager.shadeModel(GL11.GL_FLAT);
        GlStateManager.disableBlend();
        GlStateManager.enableTexture2D();
        drawWidgets(mc, mouseX, mouseY, partialTicks);
        renderHeader();
        if (vipPatrons.length > 0) {
            String text = String.format("Showing %d-%d out of %d VIP Patrons", scrollIndex, Math.min(scrollIndex + displayOnPage, vipPatrons.length), vipPatrons.length);
            int w = fontRenderer.getStringWidth(text);
            fontRenderer.drawStringWithShadow(text, width - 10 - w, height - 15, 0xffffff);
        } else {
            String text = "There are no VIP patreons. Maybe become the first? *wink*";
            int tw = fontRenderer.getStringWidth(text);
            fontRenderer.drawStringWithShadow(text, (width - tw) / 2f, (height - fontRenderer.FONT_HEIGHT) / 2f, 0xffffff);
        }
    }

    void renderHeader() {
        int height = 30;
        Widget.drawColorShape(0, 0, width, height, 0.0F, 0.0F, 0.0F, 0.4F);
        String title = "Hall Of Fame";
        FontRenderer fr = mc.fontRenderer;
        int titleW = fr.getStringWidth(title);
        mc.fontRenderer.drawStringWithShadow(TextFormatting.ITALIC + title, (width - titleW) / 2f, 7, 0xffffff);
        String th = "Huge thanks to everyone listed here";
        int thw = fr.getStringWidth(th);
        mc.fontRenderer.drawStringWithShadow(TextFormatting.ITALIC + th, (width - thw) / 2f, 17, 0xffffff);
    }

    static class NameWidget extends Widget {

        final String name;

        NameWidget(String name, int x, int y, int width, int height) {
            super(x, y, width, height);
            this.name = name;
        }

        @Override
        public void render(Minecraft mc, int mouseX, int mouseY, float partialTicks) {
            FontRenderer renderer = mc.fontRenderer;
            int w = renderer.getStringWidth(name);
            renderer.drawStringWithShadow(TextFormatting.ITALIC + name, x + (width - w) / 2f, y + (height - renderer.FONT_HEIGHT) / 2f, 0xffff00);
        }
    }
}

package dev.toma.pubgmc.client.gui.menu;

import dev.toma.pubgmc.client.content.ContentManager;
import dev.toma.pubgmc.client.content.ContentResult;
import dev.toma.pubgmc.client.content.RefreshListener;
import dev.toma.pubgmc.client.gui.widget.ButtonWidget;
import dev.toma.pubgmc.client.gui.widget.Widget;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiUtilRenderComponents;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.common.ForgeHooks;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GuiNews extends GuiWidgets implements RefreshListener {

    final GuiScreen lastScreen;
    final ContentManager manager;
    final List<ITextComponent> list = new ArrayList<>();
    int atIndex;
    int lineCount;

    public GuiNews(GuiScreen lastScreen, ContentManager manager) {
        this.lastScreen = lastScreen;
        this.manager = manager;
    }

    @Override
    public void init() {
        int panelHeight = height - 50;
        atIndex = 0;
        list.clear();
        lineCount = panelHeight / (mc.fontRenderer.FONT_HEIGHT + 1);
        int quarter = width / 4;
        int w = quarter * 2;
        addWidget(new ButtonWidget(quarter, height - 30, w, 30, I18n.format("gui.back"), (c, x, y, b) -> mc.displayGuiScreen(lastScreen)));
        ContentResult result = manager.getCachedResult();
        String[] news = new String[0];
        int max = w - 20;
        if (result != null) {
            news = result.getNews();
        }
        if (news.length == 0) {
            news = new String[]{I18n.format("pubgmc.menu.failed_to_load_data")};
        }
        FontRenderer renderer = mc.fontRenderer;
        for (String string : news) {
            ITextComponent component = ForgeHooks.newChatWithLinks(string, false);
            if (max >= 0) {
                list.addAll(GuiUtilRenderComponents.splitText(component, max, renderer, true, true));
            }
        }
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        mc.getTextureManager().bindTexture(GuiMenu.BACKGROUND_TEXTURE);
        Widget.drawTexturedShape(0, 0, width, height);
        boolean mustFix = width % 2 != 0;
        int shadeWidth = width / 2;
        float minA = 0.05F;
        float maxA = 0.95F;
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
        int quarter = width / 4;
        int w = quarter * 2;
        Widget.drawColorShape(quarter, 0, quarter + w, height - 30, 0.0F, 0.0F, 0.0F, 0.5F);
        for (int i = atIndex; i < atIndex + lineCount; i++) {
            if (i >= list.size()) break;
            String text = list.get(i).getFormattedText();
            int j = i - atIndex;
            mc.fontRenderer.drawString(text, quarter + 10, 10 + j * 10, 0xffffff);
        }
        if (list.size() > lineCount) {
            drawScrollbar(quarter + w);
        }
        drawWidgets(mc, mouseX, mouseY, partialTicks);
        Widget.drawColorShape(quarter, height - 31, quarter + w, height - 29, 1.0F, 1.0F, 1.0F, 1.0F);
    }

    @Override
    public void handleMouseInput() throws IOException {
        int n = -(int) (Integer.signum(Mouse.getEventDWheel()));
        int m = atIndex + n;
        if (m >= 0 && m <= list.size() - lineCount) {
            atIndex = m;
        }
        super.handleMouseInput();
    }

    @Override
    public void onRefresh() {
        initGui();
    }

    private void drawScrollbar(int edge) {
        Widget.drawColorShape(edge - 2, 0, edge, height - 30, 0.0F, 0.0F, 0.0F, 1.0F);
        double step = (height - 30) / (double) list.size();
        double start = atIndex * step;
        double end = Math.min(height - 30, (atIndex + lineCount) * step);
        Widget.drawColorShape(edge - 2, (int) start, edge, (int) end, 1.0F, 1.0F, 1.0F, 1.0F);
    }
}

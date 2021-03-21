package dev.toma.pubgmc.client.gui.animator;

import dev.toma.pubgmc.DevUtil;
import dev.toma.pubgmc.client.animation.AnimationElement;
import dev.toma.pubgmc.client.animation.AnimationSpec;
import dev.toma.pubgmc.client.gui.menu.GuiWidgets;
import dev.toma.pubgmc.client.gui.widget.Widget;
import dev.toma.pubgmc.proxy.ClientProxy;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GuiAnimator extends GuiWidgets implements IPopupHandler {

    final PopupHandler handler;
    ListWidget<String> animationList;
    ListWidget<AnimationElement> elements;
    TimelineWidget timeline;

    public GuiAnimator() {
        handler = new PopupHandler().withLifetime(100);
        AnimatorCache.refreshAnimations(ClientProxy.getAnimationLoader(), handler);
    }

    @Override
    public void init() {
        timeline = addWidget(new TimelineWidget(150, height - 55, width - 150, 55));
        animationList = addWidget(new ListWidget<>(this, 5, 30, 140, height / 2 - 5, new ArrayList<>(AnimatorCache.animations.keySet()), "Animations", (confirmed, parent) -> {
            GuiAnimator.this.mc.displayGuiScreen(GuiAnimator.this);
            if(confirmed) {
                IPopupHandler handler = (IPopupHandler) parent;
                handler.sendText("Loading new animation project");
                String key = GuiAnimator.this.animationList.getSelectedElement();
                WrappedAnimationSpec spec = AnimatorCache.animations.get(key);
                if(spec == null) {
                    handler.sendError("File " + key + " not found");
                    AnimatorCache.refreshAnimations(ClientProxy.getAnimationLoader(), handler);
                    GuiAnimator.this.animationList.refresh(new ArrayList<>(AnimatorCache.animations.keySet()));
                    return;
                }
                handler.sendText("New project has been created");
            }
        }).requireConfirm());
        elements = addWidget(new ListWidget<>(this, 5, height / 2 + 30, 140, 75, new ArrayList<>(), "Elements", (confirmed, parent) -> {
            AnimationElement element = elements.getSelectedElement();
            // TODO place into timeline
            elements.getList().remove(element);
        }));

        timeline.setProject(AnimatorCache.project);
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        Widget.drawColorShape(0, 0, width, height, 0.0F, 0.0F, 0.0F, 0.15F);
        Widget.drawColorShape(0, 0, 150, height, 0.0F, 0.0F, 0.0F, 0.15F);
        drawWidgets(mc, mouseX, mouseY, partialTicks);
        handler.draw(mc.fontRenderer, width, height, partialTicks);
    }

    @Override
    public void update() {
        handler.update();
        super.update();
    }

    @Override
    public void sendError(String error) {
        handler.sendError(error);
    }

    @Override
    public void sendWarning(String warning) {
        handler.sendWarning(warning);
    }

    @Override
    public void sendText(String text) {
        handler.sendText(text);
    }

    static class TimelineWidget extends Widget {

        AnimationProject project;

        public TimelineWidget(int x, int y, int width, int height) {
            super(x, y, width, height);
        }

        @Override
        public void render(Minecraft mc, int mouseX, int mouseY, float partialTicks) {
            drawColorShape(x, y, x + width, y + height, 0.0F, 0.0F, 0.0F, 0.3F);
        }

        public void setProject(AnimationProject project) {
            this.project = project;
        }
    }

    static class WrappedAnimationSpec {

        final String name;
        final File dir;
        final AnimationSpec spec;
        final boolean isModAnimation;

        public WrappedAnimationSpec(String name, AnimationSpec spec) {
            this(name, new File("./export/animations"), spec, true);
        }

        public WrappedAnimationSpec(String name, File dir, AnimationSpec spec, boolean isModAnimation) {
            this.name = name;
            this.dir = dir;
            this.spec = spec;
            this.isModAnimation = isModAnimation;
        }
    }

    static class PopupHandler implements IPopupHandler {
        final List<Popup> popups = new ArrayList<>();
        int popupLifetime;
        int popupWidth;

        public PopupHandler withLifetime(int lifetime) {
            this.popupLifetime = lifetime;
            return this;
        }

        public void draw(FontRenderer renderer, int width, int height, float partialTicks) {
            for (int i = popups.size() - 1; i >= 0; i--) {
                int j = popups.size() - i - 1;
                Popup popup = popups.get(i);
                float a = DevUtil.lerp(popup.getOpacity(0), popup.getOpacity(1), partialTicks);
                int rgb = popup.color | ((int)(a * 255) << 24);
                int y = height - (j + 1) * 15;
                float bgAlpha = 0.6F * a;
                int x2 = width - popupWidth;
                int x1 = x2 - 10;
                int y1 = y + 15;
                Widget.drawColorShape(width - popupWidth, y, width, y + 15, 0.0F, 0.0F, 0.0F, bgAlpha);
                Widget.drawColorShape(x1, y1, x2, y1, x2, y, x1, y1, 0.0F, 0.0F, 0.0F, bgAlpha);
                int textWidth = renderer.getStringWidth(popup.message);
                GlStateManager.enableBlend();
                renderer.drawString(popup.message, width - textWidth - 3, y + 3, rgb);
                GlStateManager.disableBlend();
            }
        }

        @Override
        public void sendError(String error) {
            send(2, error);
        }

        @Override
        public void sendWarning(String warning) {
            send(1, warning);
        }

        @Override
        public void sendText(String text) {
            send(0, text);
        }

        public void update() {
            Iterator<Popup> iterator = popups.iterator();
            while (iterator.hasNext()) {
                Popup popup = iterator.next();
                popup.update();
                if(popup.ticksLeft < 0) {
                    iterator.remove();
                }
            }
        }

        void send(int flag, String text) {
            popups.add(new Popup(flag, popupLifetime, text));
            updateWidth();
        }

        void updateWidth() {
            FontRenderer renderer = Minecraft.getMinecraft().fontRenderer;
            int max = 0;
            for (Popup popup : popups) {
                int width = renderer.getStringWidth(popup.message);
                if(width > max) {
                    max = width;
                }
            }
            popupWidth = max + 10;
        }
    }

    static class Popup {
        int ticksLeft;
        final int color;
        final String message;

        Popup(int flag, int ticksLeft, String message) {
            this.color = getFlagColor(flag);
            this.ticksLeft = ticksLeft;
            this.message = message;
        }

        public float getOpacity(int i) {
            int j = ticksLeft + i;
            if(j > 40)
                return 1.0F;
            return j / 40.0F;
        }

        void update() {
            --ticksLeft;
        }

        int getFlagColor(int flag) {
            switch (flag) {
                case 0:
                default:
                    return 0xFFFFFF;
                case 1:
                    return 0xFFFF00;
                case 2:
                    return 0xFF0000;
            }
        }
    }
}

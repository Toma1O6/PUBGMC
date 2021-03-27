package dev.toma.pubgmc.client.gui.animator;

import dev.toma.pubgmc.DevUtil;
import dev.toma.pubgmc.client.animation.AnimationElement;
import dev.toma.pubgmc.client.animation.AnimationProcessor;
import dev.toma.pubgmc.client.animation.AnimationSpec;
import dev.toma.pubgmc.client.animation.AnimationType;
import dev.toma.pubgmc.client.animation.impl.AnimatorAnimation;
import dev.toma.pubgmc.client.animation.interfaces.ElementProvider;
import dev.toma.pubgmc.client.gui.GuiConfirm;
import dev.toma.pubgmc.client.gui.menu.GuiWidgets;
import dev.toma.pubgmc.client.gui.widget.ButtonWidget;
import dev.toma.pubgmc.client.gui.widget.CheckboxWidget;
import dev.toma.pubgmc.client.gui.widget.TextFieldWidget;
import dev.toma.pubgmc.client.gui.widget.Widget;
import dev.toma.pubgmc.proxy.ClientProxy;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.io.File;
import java.util.*;
import java.util.regex.Pattern;

public class GuiAnimator extends GuiWidgets implements IPopupHandler {

    final PopupHandler handler;
    ListWidget<String> animationList;
    TimelineWidget timeline;

    public GuiAnimator() {
        handler = new PopupHandler().withLifetime(100);
        AnimatorCache.refreshAnimations(ClientProxy.getAnimationLoader(), handler);
    }

    @Override
    public void init() {
        int third = height / 3;
        timeline = addWidget(new TimelineWidget(this, 0, 2 * third, width, third));
        addWidget(
                new CheckboxWidget(width - 80, 5, 75, 20, "Pause", (state, mouseX, mouseY, widget) -> AnimationProcessor.instance().getAnimation(AnimationType.ANIMATOR_TYPE).ifPresent(an -> an.setPaused(state)))
                        .initialState(true)
        );
        addWidget(
                new ButtonWidget(5, 5, 40, 20, "New", (widget, mouseX, mouseY, button) -> mc.displayGuiScreen(new GuiCreateProject(this)))
        );
        addWidget(
                new ButtonWidget(50, 5, 40, 20, "Save", (widget, mouseX, mouseY, button) -> {
                    AnimationProject project = AnimatorCache.project;
                    File file = new File(project.workingFile, project.name + ".json");
                    if(file.exists()) {
                        project.save();
                        if(project.isSaved) {
                            sendText("Saving successful");
                        } else {
                            sendError("Saving failed");
                        }
                    }
                    else mc.displayGuiScreen(new GuiSaveProject(this));
                })
        );
        addWidget(
                new ButtonWidget(95, 5, 55, 20, "Save as", (widget, mouseX, mouseY, button) -> mc.displayGuiScreen(new GuiSaveProject(this)))
        );
        animationList = addWidget(new ListWidget<>(this, 5, 30, 140, 2 * third - 35, new ArrayList<>(AnimatorCache.animations.keySet()), "Animations", (confirmed, parent) -> {
            if(confirmed) {
                IPopupHandler handler = (IPopupHandler) parent;
                handler.sendText("Loading new animation project");
                String key = GuiAnimator.this.animationList.getSelectedElement();
                WrappedAnimationSpec spec = AnimatorCache.animations.get(key);
                if(spec == null) {
                    handler.sendError("File " + key + " not found");
                    AnimatorCache.refreshAnimations(ClientProxy.getAnimationLoader(), handler);
                    GuiAnimator.this.animationList.refresh(new ArrayList<>(AnimatorCache.animations.keySet()));
                } else {
                    AnimatorCache.project = new AnimationProject(spec);
                    handler.sendText("New project has been created");
                }
                GuiAnimator.this.mc.displayGuiScreen(GuiAnimator.this);
            }
        }).requireConfirm(() -> !AnimatorCache.project.isSaved));
        timeline.setProject(AnimatorCache.project);
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        Widget.drawColorShape(0, 0, width, height, 0.0F, 0.0F, 0.0F, 0.15F);
        Widget.drawColorShape(0, 30, 150, 2 * (height / 3), 0.0F, 0.0F, 0.0F, 0.5F);
        drawWidgets(mc, mouseX, mouseY, partialTicks);
        handler.draw(mc.fontRenderer, width, height, partialTicks);
    }

    @Override
    public void update() {
        handler.update();
        super.update();
    }

    @Override
    public void sendError(String error, Object... objects) {
        handler.sendError(error, objects);
    }

    @Override
    public void sendWarning(String warning, Object... objects) {
        handler.sendWarning(warning, objects);
    }

    @Override
    public void sendText(String text, Object... objects) {
        handler.sendText(text, objects);
    }

    static class TimelineWidget extends Widget {

        AnimationProject project;
        ListWidget<AnimationElement> listWidget;
        GuiAnimator animator;
        int length = 40;
        Map<AnimationElement, List<TimelineObject>> timelineObjects;
        AnimationElement clickedElementTimeline;

        public <G extends GuiAnimator & IPopupHandler> TimelineWidget(G parent, int x, int y, int width, int height) {
            super(x, y, width, height);
            this.animator = parent;
            this.timelineObjects = new TreeMap<>(Comparator.comparingInt(AnimationElement::getIndex));
            ItemStack stack = Minecraft.getMinecraft().player.getHeldItemMainhand();
            Item item = stack.getItem();
            List<AnimationElement> elements = new ArrayList<>();
            if(item.getTileEntityItemStackRenderer() instanceof ElementProvider) {
                elements.addAll(((ElementProvider) item.getTileEntityItemStackRenderer()).getElements());
            }
            elements.addAll(AnimationElement.getBaseElements());
            elements.sort((o1, o2) -> o2.getIndex() - o1.getIndex());
            this.listWidget = new ListWidget<>(parent, x, y - 15, 100, height + 15, elements, null, (confirmed, parent1) -> {
                AnimationElement element = TimelineWidget.this.listWidget.getSelectedElement();
                TimelineWidget.this.insertElement(element);
            }).withFormatter(AnimationElement::getLocalizedName);
        }

        @Override
        public void render(Minecraft mc, int mouseX, int mouseY, float partialTicks) {
            drawColorShape(x, y, x + width, y + height, 0.0F, 0.0F, 0.0F, 0.5F);
            listWidget.render(mc, mouseX, mouseY, partialTicks);
            FontRenderer renderer = mc.fontRenderer;
            AnimationProject project = AnimatorCache.project;
            Optional<AnimatorAnimation> optional = AnimationProcessor.instance().getAnimation(AnimationType.ANIMATOR_TYPE);
            optional.ifPresent(anim -> {
                if(!anim.isPaused())
                    project.setAnimationProgress(anim.getProgressSmooth());
            });
            drawLine(x + 165, y + 10, x + 165, y + height, 1.0F, 0.0F, 0.0F, 0.5F, 2);
            renderer.drawString("0", x + 162.5F, y + 1, 0xFFFFFF, false);
            drawLine(x + width - 10, y + 10, x + width - 10, y + height, 1.0F, 0.0F, 0.0F, 0.5F, 2);
            renderer.drawString("100", x + width - 20, y + 1, 0xFFFFFF, false);
            int center = x + 165 + (width - 175) / 2;
            drawLine(center, y + 10, center, y + height, 1.0F, 0.0F, 0.0F, 0.5F, 2);
            renderer.drawString("50", center - 6, y + 1, 0xFFFFFF, false);
            int j = 0;
            for (Map.Entry<AnimationElement, List<TimelineObject>> entry : timelineObjects.entrySet()) {
                renderer.drawString(entry.getKey().getLocalizedName(), 103, y + 10 + j * 10, 0xFFFFFF);
                drawColorShape(this.x + 165, 15 + y + j * 10, this.x + width - 10, 16 + y + j * 10, 1.0F, 0.0F, 0.0F, 0.5F);
                for (TimelineObject object : entry.getValue()) {
                    object.setY(10 + this.y + j * 10);
                    object.render(mc, mouseX, mouseY, partialTicks);
                }
                ++j;
            }
            int timelineX = this.x + 165 + (int)((width - 175) * project.animationProgress);
            drawColorShape(timelineX - 1, y, timelineX + 1, y + height, 1.0F, 1.0F, 1.0F, 1.0F);
            drawColorShape(timelineX - 5, y, timelineX, y + 10, timelineX + 5, y, timelineX - 5, y, 1.0F, 1.0F, 1.0F, 1.0F);
        }

        public void setProject(AnimationProject project) {
            this.project = project;
            timelineObjects.clear();
            for (Map.Entry<AnimationElement, List<MutableKeyFrame>> entry : project.animation.entrySet()) {
                List<TimelineObject> objects = new ArrayList<>();
                for (MutableKeyFrame frame : entry.getValue()) {
                    int ox = 165 + (int)(this.x + (this.width - this.x - 175) * frame.endPoint() - 5);
                    TimelineObject object = new TimelineObject(ox, 0, 10, 10, this, frame);
                    objects.add(object);
                }
                objects.sort((o1, o2) -> Float.compare(o1.frame.endpoint, o2.frame.endpoint));
                timelineObjects.put(entry.getKey(), objects);
            }
            playAnimation();
        }

        @Override
        public boolean handleClicked(int mouseX, int mouseY, int button) {
            for (Map.Entry<AnimationElement, List<TimelineObject>> entry : timelineObjects.entrySet()) {
                for (TimelineObject object : entry.getValue()) {
                    clickedElementTimeline = entry.getKey();
                    if(object.handleClicked(mouseX, mouseY, button)) {
                        return false;
                    }
                }
            }
            if(listWidget.handleClicked(mouseX, mouseY, button)) {
                return false;
            }
            return super.handleClicked(mouseX, mouseY, button);
        }

        @Override
        public boolean processClicked(int mouseX, int mouseY, int button) {
            return mouseX >= x + 155 && mouseX <= x + width && mouseY >= y && mouseY <= y + 10;
        }

        @Override
        public void onClick(int mouseX, int mouseY, int button) {
            int x1 = x + 165;
            int x2 = width - 10;
            float f = DevUtil.wrap((mouseX - x1) / (float) (x2 - x1), 0.0F, 1.0F);
            float f1 = f * 100;
            int i = (int) f1;
            float f2 = f1 - i;
            if(f2 < 0.5) {
                f = i;
            } else {
                f = i + 1;
            }
            f /= 100.0F;
            AnimatorCache.project.setAnimationProgress(f);
            animator.sendText("Set animation progress to {}%", DevUtil.formatToTwoDecimals(f * 100.0F));
            Optional<AnimatorAnimation> optional = AnimationProcessor.instance().getAnimation(AnimationType.ANIMATOR_TYPE);
            if(optional.isPresent()) {
                optional.get().set(f);
            }
        }

        @Override
        public boolean handleDragged(int mouseX, int mouseY, int button, long time) {
            for (Map.Entry<AnimationElement, List<TimelineObject>> entry : timelineObjects.entrySet()) {
                for (TimelineObject object : entry.getValue()) {
                    if(object.handleDragged(mouseX, mouseY, button, time))
                        return true;
                }
            }
            return false;
        }

        @Override
        public boolean handleMouseScrolled(int mouseX, int mouseY, int delta) {
            return listWidget.handleMouseScrolled(mouseX, mouseY, delta);
        }

        public void insertElement(AnimationElement element) {
            MutableKeyFrame keyFrame = new MutableKeyFrame();
            keyFrame.setEndpoint(AnimatorCache.project.animationProgress);
            insertElement(element, keyFrame);
        }

        public void insertElement(AnimationElement element, MutableKeyFrame frame) {
            List<TimelineObject> list = timelineObjects.getOrDefault(element, new ArrayList<>());
            float progress = frame.endpoint;
            if(hasElementAt(progress, list)) {
                animator.sendError("Frame is already defined for this location");
                return;
            }
            frame.setEndpoint(progress);
            AnimatorCache.project.add(element, frame);
            playAnimation();
            int ox = 165 + (int)(this.x + (this.width - this.x - 175) * progress - 5);
            TimelineObject object = new TimelineObject(ox, 0, 10, 10, this, frame);
            list.add(object);
            list.sort((o1, o2) -> Float.compare(o1.frame.endpoint, o2.frame.endpoint));
            timelineObjects.put(element, list);
            animator.sendText("Added new frame for {} element", element.getLocalizedName());
        }

        void playAnimation() {
            AnimationProcessor processor = AnimationProcessor.instance();
            AnimatorAnimation animatorAnimation = new AnimatorAnimation(length);
            animatorAnimation.set(AnimatorCache.project.animationProgress);
            animatorAnimation.setPaused(true);
            processor.play(AnimationType.ANIMATOR_TYPE, animatorAnimation);
        }

        boolean hasElementAt(float f, List<TimelineObject> list) {
            if(list == null || list.isEmpty())
                return false;
            for (TimelineObject object : list) {
                if(object.frame.endpoint == f)
                    return true;
            }
            return false;
        }

        static class TimelineObject extends Widget {

            final TimelineWidget timeline;
            final MutableKeyFrame frame;

            public TimelineObject(int x, int y, int width, int height, TimelineWidget widget, MutableKeyFrame frame) {
                super(x, y, width, height);
                this.timeline = widget;
                this.frame = frame;
            }

            public TimelineObject(int x, int y, int width, int height, TimelineWidget widget, float endpoint) {
                super(x, y, width, height);
                this.timeline = widget;
                this.frame = new MutableKeyFrame();
                this.frame.setEndpoint(endpoint);
            }

            public void setY(int y) {
                this.y = y;
            }

            @Override
            public void render(Minecraft mc, int mouseX, int mouseY, float partialTicks) {
                drawColorShape(x, y, x + width, y + height, 0.0F, 0.75F, 0.75F, 0.6F);
                drawColorShape(x + 2, y + 2, x + width - 2, y + height - 2, 0.0F, 0.75F, 0.75F, 1.0F);
            }

            @Override
            public boolean processClicked(int mouseX, int mouseY, int button) {
                return isMouseOver(mouseX, mouseY);
            }

            @Override
            public void onClick(int mouseX, int mouseY, int button) {
                if(button == 1) {
                    Minecraft.getMinecraft().displayGuiScreen(new GuiModifyFrame(timeline.animator, frame, timeline.clickedElementTimeline));
                }
            }

            @Override
            public void onDrag(int mouseX, int mouseY, int button, long time) {
                int x1 = timeline.x + 165;
                int x2 = timeline.x + timeline.width - 10;
                float f = DevUtil.wrap((mouseX - x1) / (float) (x2 - x1), 0.0F, 1.0F);
                frame.setEndpoint(f);
                x = 165 + (int)(timeline.x + (timeline.width - timeline.x - 175) * f - 5);
                AnimatorCache.project.markModified();
            }
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
        public void sendError(String error, Object... objects) {
            send(2, error, objects);
        }

        @Override
        public void sendWarning(String warning, Object... objects) {
            send(1, warning, objects);
        }

        @Override
        public void sendText(String text, Object... objects) {
            send(0, text, objects);
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

        void send(int flag, String text, Object... objects) {
            if(objects.length != 0) {
                int j = 0;
                while (text.contains("{}") && j < objects.length) {
                    text = text.replaceFirst("[{}].", objects[j].toString());
                    ++j;
                }
            }
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

    static class GuiConfirmTextInput extends GuiConfirm {

        static final Pattern PATTERN = Pattern.compile("[a-zA-Z0-9_]");
        protected TextFieldWidget textField;

        GuiConfirmTextInput(GuiAnimator parent, String text) {
            super(parent, text, null, null);
        }

        @Override
        public void init() {
            super.init();
            textField = addWidget(
                    new TextFieldWidget(guiLeft + 5, guiTop + 30, xSize - 10, 20, "", 40)
                        .withValidator(character -> PATTERN.matcher(String.valueOf(character)).matches())
                        .ghostText("Filename")
            );
        }
    }

    static class GuiCreateProject extends GuiConfirmTextInput {

        public GuiCreateProject(GuiAnimator animator) {
            super(animator, "Create new project");
        }

        @Override
        protected void onConfirm() {
            AnimationProject project = AnimatorCache.project;
            String name = textField.getText();
            if(name.isEmpty()) {
                ((IPopupHandler) parent).sendError("You must define project name");
                return;
            }
            if(!project.isSaved) {
                GuiConfirm.display(this, "Close project without saving?", "", (confirmed, parent1) -> {
                    if(confirmed) {
                        AnimatorCache.project = new AnimationProject().named(name);
                        mc.displayGuiScreen(((GuiConfirm) parent1).parent);
                        ((IPopupHandler) parent).sendText("Project created");
                    } else
                        mc.displayGuiScreen(parent1);
                });
            } else {
                AnimatorCache.project = new AnimationProject().named(name);
                ((IPopupHandler) parent).sendText("Project created");
                mc.displayGuiScreen(parent);
            }
        }

        @Override
        protected void onCancel() {
            mc.displayGuiScreen(parent);
        }
    }

    static class GuiSaveProject extends GuiConfirmTextInput {

        public GuiSaveProject(GuiAnimator parent) {
            super(parent, "Save as");
        }

        @Override
        protected void onConfirm() {
            AnimationProject project = AnimatorCache.project;
            String text = textField.getText();
            if(text.isEmpty()) {
                ((IPopupHandler) parent).sendError("You must define file name");
                return;
            }
            File file = new File(project.workingFile, text + ".json");
            if(file.exists()) {
                GuiConfirm.display(this, "File already exists. Overwrite?", "", (confirmed, parent1) -> {
                    if(confirmed) {
                        project.saveAs(text);
                        mc.displayGuiScreen(((GuiConfirm) parent1).parent);
                        if(project.isSaved) {
                            ((IPopupHandler) parent).sendText("Saving successful");
                        } else {
                            ((IPopupHandler) parent).sendError("Saving failed");
                        }
                    } else {
                        mc.displayGuiScreen(parent1);
                    }
                });
            } else {
                project.saveAs(text);
                mc.displayGuiScreen(parent);
                if(project.isSaved) {
                    ((IPopupHandler) parent).sendText("Saving successful");
                } else {
                    ((IPopupHandler) parent).sendError("Saving failed");
                }
            }
        }

        @Override
        protected void onCancel() {
            mc.displayGuiScreen(parent);
        }
    }
}

package dev.toma.pubgmc.client.gui.animator;

import dev.toma.pubgmc.DevUtil;
import dev.toma.pubgmc.client.animation.AnimationElement;
import dev.toma.pubgmc.client.animation.AnimationProcessor;
import dev.toma.pubgmc.client.animation.AnimationSpec;
import dev.toma.pubgmc.client.animation.AnimationType;
import dev.toma.pubgmc.client.animation.impl.AnimatorAnimation;
import dev.toma.pubgmc.client.animation.interfaces.ElementProvider;
import dev.toma.pubgmc.client.animation.interfaces.KeyFrame;
import dev.toma.pubgmc.client.gui.GuiConfirm;
import dev.toma.pubgmc.client.gui.menu.GuiWidgets;
import dev.toma.pubgmc.client.gui.widget.*;
import dev.toma.pubgmc.proxy.ClientProxy;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.Vec3d;

import java.io.File;
import java.util.*;
import java.util.regex.Pattern;

public class GuiAnimator extends GuiWidgets implements IPopupHandler {

    final PopupHandler handler;
    ListWidget<String> animationList;
    TimelineWidget timeline;
    ModifyFrameWidget frameWidget;

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
                new ButtonWidget(50, 5, 40, 20, "Open", (widget, mouseX, mouseY, button) -> mc.displayGuiScreen(new GuiOpenAnimation(this)))
        );
        addWidget(
                new ButtonWidget(95, 5, 40, 20, "Save", (widget, mouseX, mouseY, button) -> {
                    AnimationProject project = AnimatorCache.project;
                    File file = new File(project.workingFile, project.name + ".json");
                    if (file.exists()) {
                        project.save();
                        if (project.isSaved) {
                            sendText("Saving successful");
                        } else {
                            sendError("Saving failed");
                        }
                    } else mc.displayGuiScreen(new GuiSaveProject(this));
                })
        );
        addWidget(
                new ButtonWidget(140, 5, 55, 20, "Save as", (widget, mouseX, mouseY, button) -> mc.displayGuiScreen(new GuiSaveProject(this)))
        );
        addWidget(
                new ButtonWidget(200, 5, 120, 20, "Generate reset frames", (widget, mouseX, mouseY, button) -> {
                    Map<AnimationElement, List<MutableKeyFrame>> map = AnimatorCache.project.animation;
                    for (Map.Entry<AnimationElement, List<MutableKeyFrame>> entry : map.entrySet()) {
                        List<MutableKeyFrame> list = entry.getValue();
                        MutableKeyFrame lastElement = list.get(list.size() - 1);
                        Vec3d pos = lastElement.getPositionStart().add(lastElement.moveTarget());
                        Vec3d rot = lastElement.getRotationStart().add(lastElement.rotateTarget());
                        MutableKeyFrame frame = new MutableKeyFrame();
                        frame.setEndpoint(1.0F);
                        frame.setPositionStart(pos);
                        frame.setRotationStart(rot);
                        frame.setMove(new Vec3d(-pos.x, -pos.y, -pos.z));
                        frame.setRotate(new Vec3d(-rot.x, -rot.y, -rot.z));
                        timeline.insertElement(entry.getKey(), frame);
                    }
                })
        );
        addWidget(
                new ButtonWidget(325, 5, 65, 20, "Connector", (widget, mouseX, mouseY, button) -> mc.displayGuiScreen(new GuiConnectAnimations(this)))
        );
        addWidget(new AnimationLengthWidget(5, 30, 165, 15, timeline));
        frameWidget = new ModifyFrameWidget(0, height - third - 100, 170, 100, this);
        for (Widget widget1 : frameWidget.widgets) {
            addWidget(widget1);
        }
        addWidget(frameWidget);
        timeline.setProject(AnimatorCache.project);
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        Widget.drawColorShape(0, 0, width, height, 0.0F, 0.0F, 0.0F, 0.15F);
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

    static class AnimationLengthWidget extends Widget {

        final ButtonWidget decr, incr;
        final TimelineWidget timelineRef;

        AnimationLengthWidget(int x, int y, int width, int height, TimelineWidget timelineRef) {
            super(x, y, width, height);
            this.timelineRef = timelineRef;
            decr = new ButtonWidget(x + width - 65, y, 15, 15, "<<", (widget, mouseX, mouseY, button) -> grow(-1));
            incr = new ButtonWidget(x + width - 15, y, 15, 15, ">>", (widget, mouseX, mouseY, button) -> grow(1));
        }

        @Override
        public void render(Minecraft mc, int mouseX, int mouseY, float partialTicks) {
            FontRenderer renderer = mc.fontRenderer;
            drawColorShape(x, y, x + width, y + height, 0.0F, 0.0F, 0.0F, 0.4F);
            renderer.drawStringWithShadow("Animation length", x + 5, y + (height - 9) / 2f, 0xFFFFFF);
            renderer.drawStringWithShadow(String.valueOf(AnimatorCache.project.length), x + width - 45, y + (height - 9) / 2.0F, 0x00FF00);
            decr.render(mc, mouseX, mouseY, partialTicks);
            incr.render(mc, mouseX, mouseY, partialTicks);
        }

        @Override
        public boolean handleClicked(int mouseX, int mouseY, int button) {
            return decr.handleClicked(mouseX, mouseY, button) || incr.handleClicked(mouseX, mouseY, button);
        }

        void grow(int value) {
            int amount = 5;
            if (isShiftKeyDown()) {
                amount = 20;
            } else if (isCtrlKeyDown()) {
                amount = 1;
            }
            amount *= value;
            AnimatorCache.project.addLength(amount);
            timelineRef.playAnimation();
        }
    }

    static class ModifyFrameWidget extends Widget {

        final GuiAnimator animator;
        final List<DecimalInputWidget> inputs = new ArrayList<>();
        final List<Widget> widgets = new ArrayList<>();
        MutableKeyFrame frame;
        AnimationElement frameOwner;

        ModifyFrameWidget(int x, int y, int width, int height, GuiAnimator animator) {
            super(x, y, width, height);
            this.animator = animator;

            inputs.add(new DecimalInputWidget(x + 5, y + 15, 50, 20, frame, () -> frame.move.x, (frame1, value) -> frame1.setMove(new Vec3d(value, frame1.move.y, frame1.move.z))));
            inputs.add(new DecimalInputWidget(x + 60, y + 15, 50, 20, frame, () -> frame.move.y, (frame1, value) -> frame1.setMove(new Vec3d(frame1.move.x, value, frame1.move.z))));
            inputs.add(new DecimalInputWidget(x + 115, y + 15, 50, 20, frame, () -> frame.move.z, (frame1, value) -> frame1.setMove(new Vec3d(frame1.move.x, frame1.move.y, value))));
            inputs.add(new DecimalInputWidget(x + 5, y + 50, 50, 20, frame, () -> frame.rotate.x, (frame1, value) -> frame1.setRotate(new Vec3d(value, frame1.rotate.y, frame1.rotate.z))));
            inputs.add(new DecimalInputWidget(x + 60, y + 50, 50, 20, frame, () -> frame.rotate.y, (frame1, value) -> frame1.setRotate(new Vec3d(frame1.rotate.x, value, frame1.rotate.z))));
            inputs.add(new DecimalInputWidget(x + 115, y + 50, 50, 20, frame, () -> frame.rotate.z, (frame1, value) -> frame1.setRotate(new Vec3d(frame1.rotate.x, frame1.rotate.y, value))));
            widgets.addAll(inputs);
            widgets.add(new ButtonWidget(x + 5, y + height - 25, 50, 20, "Continue", (widget, mouseX, mouseY, button) -> Minecraft.getMinecraft().displayGuiScreen(animator)));
            widgets.add(new ButtonWidget(x + 60, y + height - 25, 50, 20, "Delete", (widget, mouseX, mouseY, button) -> {
                AnimatorCache.project.remove(frameOwner, frame);
                Minecraft.getMinecraft().displayGuiScreen(animator);
            }));
            widgets.add(new ButtonWidget(x + 115, y + height - 25, 50, 20, "Copy", (widget, mouseX, mouseY, button) -> {
                Minecraft mc = Minecraft.getMinecraft();
                Item item = mc.player.getHeldItemMainhand().getItem();
                List<AnimationElement> list = new ArrayList<>(AnimationElement.getBaseElements());
                if (item.getTileEntityItemStackRenderer() instanceof ElementProvider) {
                    ElementProvider provider = (ElementProvider) item.getTileEntityItemStackRenderer();
                    list.addAll(provider.getElements());
                }
                list.removeIf(element -> element == frameOwner);
                mc.displayGuiScreen(new ElementList(animator, list, frame).onListInit(listWidget -> listWidget.withFormatter(AnimationElement::getLocalizedName)));
            }));
        }

        @Override
        public void render(Minecraft mc, int mouseX, int mouseY, float partialTicks) {
            drawColorShape(x, y - 10, x + width, y + height, 0.0F, 0.0F, 0.0F, 0.5F);
            FontRenderer renderer = mc.fontRenderer;
            renderer.drawStringWithShadow("Position", x + 5, y + 5, 0xFFFFFF);
            renderer.drawStringWithShadow("Rotation", x + 5, y + 40, 0xFFFFFF);
            String ownerName = frameOwner != null ? frameOwner.getLocalizedName() : "null";
            renderer.drawStringWithShadow("Frame owner: " + ownerName, x + 5, y - 6, 0xFFFFFF);
            widgets.forEach(widget -> widget.render(mc, mouseX, mouseY, partialTicks));
        }

        void setFrame(MutableKeyFrame frame, AnimationElement frameOwner) {
            this.frame = frame;
            this.frameOwner = frameOwner;
            inputs.forEach(widget -> widget.setFrame(frame));
        }

        @Override
        public boolean handleClicked(int mouseX, int mouseY, int button) {
            return false;
        }

        static class ElementList extends GuiSelectList<AnimationElement> {

            final KeyFrame frame;

            ElementList(GuiAnimator parent, List<AnimationElement> elementList, KeyFrame frame) {
                super(parent, "Select target element", "", elementList, null);
                this.frame = frame;
            }

            @Override
            protected void onConfirm() {
                GuiAnimator animator = (GuiAnimator) parent;
                AnimationElement element = widget.getSelectedElement();
                if (element != null) {
                    animator.timeline.insertElement(element, MutableKeyFrame.fromImmutable(frame));
                }
                mc.displayGuiScreen(parent);
            }

            @Override
            protected void onCancel() {
                mc.displayGuiScreen(parent);
            }
        }
    }

    static class TimelineWidget extends Widget {

        AnimationProject project;
        ListWidget<AnimationElement> listWidget;
        GuiAnimator animator;
        Map<AnimationElement, List<TimelineObject>> timelineObjects;
        AnimationElement clickedElementTimeline;

        public <G extends GuiAnimator & IPopupHandler> TimelineWidget(G parent, int x, int y, int width, int height) {
            super(x, y, width, height);
            this.animator = parent;
            this.timelineObjects = new TreeMap<>(Comparator.comparingInt(AnimationElement::getIndex));
            ItemStack stack = Minecraft.getMinecraft().player.getHeldItemMainhand();
            Item item = stack.getItem();
            List<AnimationElement> elements = new ArrayList<>();
            if (item.getTileEntityItemStackRenderer() instanceof ElementProvider) {
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
                if (!anim.isPaused())
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
            int timelineX = this.x + 165 + (int) ((width - 175) * project.animationProgress);
            drawColorShape(timelineX - 1, y, timelineX + 1, y + height, 1.0F, 1.0F, 1.0F, 1.0F);
            drawColorShape(timelineX - 5, y, timelineX, y + 10, timelineX + 5, y, timelineX - 5, y, 1.0F, 1.0F, 1.0F, 1.0F);
        }

        public void setProject(AnimationProject project) {
            this.project = project;
            timelineObjects.clear();
            for (Map.Entry<AnimationElement, List<MutableKeyFrame>> entry : project.animation.entrySet()) {
                List<TimelineObject> objects = new ArrayList<>();
                for (MutableKeyFrame frame : entry.getValue()) {
                    int ox = 165 + (int) (this.x + (this.width - this.x - 175) * frame.endPoint() - 5);
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
                    if (object.handleClicked(mouseX, mouseY, button)) {
                        return false;
                    }
                }
            }
            if (listWidget.handleClicked(mouseX, mouseY, button)) {
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
            if (f2 < 0.5) {
                f = i;
            } else {
                f = i + 1;
            }
            f /= 100.0F;
            AnimatorCache.project.setAnimationProgress(f);
            animator.sendText("Set animation progress to {}%", DevUtil.formatToTwoDecimals(f * 100.0F));
            Optional<AnimatorAnimation> optional = AnimationProcessor.instance().getAnimation(AnimationType.ANIMATOR_TYPE);
            if (optional.isPresent()) {
                optional.get().set(f);
            }
        }

        @Override
        public boolean handleDragged(int mouseX, int mouseY, int button, long time) {
            for (Map.Entry<AnimationElement, List<TimelineObject>> entry : timelineObjects.entrySet()) {
                for (TimelineObject object : entry.getValue()) {
                    if (object.handleDragged(mouseX, mouseY, button, time))
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
            if (hasElementAt(progress, list)) {
                animator.sendError("Frame is already defined for this location");
                return;
            }
            frame.setEndpoint(progress);
            AnimatorCache.project.add(element, frame);
            playAnimation();
            int ox = 165 + (int) (this.x + (this.width - this.x - 175) * progress - 5);
            TimelineObject object = new TimelineObject(ox, 0, 10, 10, this, frame);
            list.add(object);
            list.sort((o1, o2) -> Float.compare(o1.frame.endpoint, o2.frame.endpoint));
            timelineObjects.put(element, list);
            animator.sendText("Added new frame for {} element", element.getLocalizedName());
        }

        void playAnimation() {
            AnimationProcessor processor = AnimationProcessor.instance();
            AnimatorAnimation animatorAnimation = new AnimatorAnimation(project.length);
            animatorAnimation.set(AnimatorCache.project.animationProgress);
            animatorAnimation.setPaused(true);
            processor.play(AnimationType.ANIMATOR_TYPE, animatorAnimation);
        }

        boolean hasElementAt(float f, List<TimelineObject> list) {
            if (list == null || list.isEmpty())
                return false;
            for (TimelineObject object : list) {
                if (object.frame.endpoint == f)
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
                boolean editing = timeline.animator.frameWidget.frame == frame;
                drawColorShape(x, y, x + width, y + height, 0.0F, 0.75F, editing ? 0.0F : 0.75F, 0.6F);
                drawColorShape(x + 2, y + 2, x + width - 2, y + height - 2, 0.0F, 0.75F, editing ? 0.0F : 0.75F, 1.0F);
            }

            @Override
            public boolean processClicked(int mouseX, int mouseY, int button) {
                return isMouseOver(mouseX, mouseY);
            }

            @Override
            public void onClick(int mouseX, int mouseY, int button) {
                if (button == 1) {
                    timeline.animator.frameWidget.setFrame(frame, timeline.clickedElementTimeline);
                }
            }

            @Override
            public void onDrag(int mouseX, int mouseY, int button, long time) {
                int x1 = timeline.x + 165;
                int x2 = timeline.x + timeline.width - 10;
                float f = DevUtil.wrap((mouseX - x1) / (float) (x2 - x1), 0.0F, 1.0F);
                frame.setEndpoint(f);
                x = 165 + (int) (timeline.x + (timeline.width - timeline.x - 175) * f - 5);
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
                int rgb = popup.color | ((int) (a * 255) << 24);
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
                if (popup.ticksLeft < 0) {
                    iterator.remove();
                }
            }
        }

        void send(int flag, String text, Object... objects) {
            if (objects.length != 0) {
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
                if (width > max) {
                    max = width;
                }
            }
            popupWidth = max + 10;
        }
    }

    static class Popup {
        final int color;
        final String message;
        int ticksLeft;

        Popup(int flag, int ticksLeft, String message) {
            this.color = getFlagColor(flag);
            this.ticksLeft = ticksLeft;
            this.message = message;
        }

        public float getOpacity(int i) {
            int j = ticksLeft + i;
            if (j > 40)
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
            if (name.isEmpty()) {
                ((IPopupHandler) parent).sendError("You must define project name");
                return;
            }
            if (!project.isSaved) {
                GuiConfirm.display(this, "Close project without saving?", "", (confirmed, parent1) -> {
                    if (confirmed) {
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
            if (text.isEmpty()) {
                ((IPopupHandler) parent).sendError("You must define file name");
                return;
            }
            File file = new File(project.workingFile, text + ".json");
            if (file.exists()) {
                GuiConfirm.display(this, "File already exists. Overwrite?", "", (confirmed, parent1) -> {
                    if (confirmed) {
                        project.saveAs(text);
                        mc.displayGuiScreen(((GuiConfirm) parent1).parent);
                        if (project.isSaved) {
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
                if (project.isSaved) {
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

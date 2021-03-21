package dev.toma.pubgmc.client.gui.menu;

import dev.toma.pubgmc.Pubgmc;
import dev.toma.pubgmc.client.gui.widget.ButtonWidget;
import dev.toma.pubgmc.client.gui.widget.CheckboxWidget;
import dev.toma.pubgmc.client.gui.widget.Widget;
import dev.toma.pubgmc.client.renderer.IRenderConfig;
import dev.toma.pubgmc.client.renderer.MutableRenderConfig;
import dev.toma.pubgmc.client.renderer.item.attachment.AttachmentRenderer;
import dev.toma.pubgmc.client.renderer.item.gun.WeaponRenderer;
import dev.toma.pubgmc.common.items.attachment.AttachmentType;
import dev.toma.pubgmc.common.items.attachment.ItemAttachment;
import dev.toma.pubgmc.common.items.guns.GunAttachments;
import dev.toma.pubgmc.common.items.guns.GunBase;
import dev.toma.pubgmc.proxy.ClientProxy;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.resources.I18n;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import org.lwjgl.input.Keyboard;

import java.io.File;
import java.io.FileWriter;
import java.time.LocalDateTime;
import java.util.*;
import java.util.function.BooleanSupplier;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class GuiGunConfig extends GuiWidgets {

    static final Pattern DECIMAL_PATTERN = Pattern.compile("-?[0-9]+(\\.[0-9]+)?");
    final List<ItemAttachment> compatibleAttachments = new ArrayList<>();
    final Set<MutableRenderConfig> activeConfigs = new HashSet<>();
    Map<ItemAttachment, IRenderConfig> map = new HashMap<>();
    SelectionWidget<GunBase> gunSelector;
    AttachmentListWidget attachmentListWidget;
    PropertyArrayWidget translateWidget;
    PropertyArrayWidget scaleWidget;
    PropertyArrayWidget rotateWidget;
    ItemStack displayWeapon;
    boolean lightTheme;

    @Override
    public void init() {
        if(gunSelector == null) {
            gunSelector = new SelectionWidget<>(5, 5, 150, 20, ForgeRegistries.ITEMS.getValuesCollection().stream()
                    .filter(it -> it instanceof GunBase)
                    .map(it -> (GunBase) it)
                    .collect(Collectors.toList()), gun -> gun.getRegistryName().toString(), this::updateWeapon);
        }
        if(attachmentListWidget == null) {
            attachmentListWidget = new AttachmentListWidget(5, 30, 80, height - 35);
        }
        addWidget(gunSelector);
        addWidget(new GunDisplayWidget(90, 30, width - 90, height - 90));
        addWidget(attachmentListWidget);
        addWidget(new CheckboxWidget(160, 5, 80, 20, "Light theme", (state, mouseX, mouseY, widget) -> lightTheme = state, 11).lightThemeSupplier(() -> lightTheme));
        int third = (width - 90) / 3;
        translateWidget = addWidget(new PropertyArrayWidget(90, height - 75, third, 90, "Translation", MutableRenderConfig::setTranslation, 0.0F));
        scaleWidget = addWidget(new PropertyArrayWidget(90 + third, height - 75, third, 90, "Scale", MutableRenderConfig::setScale, 1.0F));
        rotateWidget = addWidget(new PropertyArrayWidget(90 + 2 * third, height - 75, third, 90, "Rotation", MutableRenderConfig::setRotation, 0.0F, 15.0F, 1.0F, 0.1F));
        addWidget(new ButtonWidget(width - 60, 5, 55, 20, "Export", (widget, mouseX, mouseY, button) -> exportFile()));
        addWidget(new ButtonWidget(width - 85, 5, 20, 20, "R", (widget, mouseX, mouseY, button) -> map.clear()));
        GunBase element = gunSelector.getElement();
        updateWeapon(element, element, gunSelector);
    }

    public void updateWeapon(GunBase prev, GunBase gun, SelectionWidget<GunBase> selector) {
        if(!map.isEmpty()) {
            ((WeaponRenderer) prev.getTileEntityItemStackRenderer()).setRenderConfigsTempt(map);
            map.clear();
        }
        activeConfigs.clear();
        map = ((WeaponRenderer) gun.getTileEntityItemStackRenderer()).getRenderConfigs();
        this.displayWeapon = new ItemStack(gun);
        compatibleAttachments.clear();
        GunAttachments attachments = gun.getAttachments();
        if(!attachments.isLoaded()) {
            attachments.load();
        }
        Map<AttachmentType<?>, List<ItemAttachment>> map = attachments.getCompatibilityMap();
        int i = 0;
        for (Map.Entry<AttachmentType<?>, List<ItemAttachment>> entry : map.entrySet()) {
            compatibleAttachments.addAll(entry.getValue().stream().filter(AttachmentRenderer::hasRender).collect(Collectors.toList()));
        }
        attachmentListWidget.setListAndUpdate(compatibleAttachments);
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        float backgroundColor = lightTheme ? 1.0F : 0.0F;
        Widget.drawColorShape(0, 0, width, height, backgroundColor, backgroundColor, backgroundColor, 0.3F);
        Widget.drawColorShape(0, 0, width, 30, backgroundColor, backgroundColor, backgroundColor, 0.25F);
        Widget.drawColorShape(0, 30, 90, height, backgroundColor, backgroundColor, backgroundColor, 0.25F);
        drawWidgets(mc, mouseX, mouseY, partialTicks);
    }

    void addAttachment(ItemAttachment attachment) {
        NBTTagCompound nbt = displayWeapon.getTagCompound();
        if(nbt == null) {
            nbt = new NBTTagCompound();
            nbt.setTag("attachments", new NBTTagCompound());
            displayWeapon.setTagCompound(nbt);
        }
        AttachmentType<?> type = attachment.getType();
        NBTTagCompound attachments;
        if(!nbt.hasKey("attachments")) {
            attachments = new NBTTagCompound();
            nbt.setTag("attachments", attachments);
        } else {
            attachments = nbt.getCompoundTag("attachments");
        }
        String key = type.getName();
        attachments.setString(key, attachment.getRegistryName().toString());
        if(!map.containsKey(attachment)) {
            map.put(attachment, new MutableRenderConfig());
        }
        IRenderConfig cfg = map.get(attachment);
        if(cfg instanceof MutableRenderConfig) {
            activeConfigs.add((MutableRenderConfig) cfg);
        }
        translateWidget.reset();
        scaleWidget.reset();
        rotateWidget.reset();
    }

    void removeAttachment(ItemAttachment attachment) {
        NBTTagCompound nbt = displayWeapon.getTagCompound();
        if(nbt == null) {
            nbt = new NBTTagCompound();
            nbt.setTag("attachments", new NBTTagCompound());
            displayWeapon.setTagCompound(nbt);
        }
        AttachmentType<?> type = attachment.getType();
        NBTTagCompound attachments;
        if(!nbt.hasKey("attachments")) {
            attachments = new NBTTagCompound();
            nbt.setTag("attachments", attachments);
        } else {
            attachments = nbt.getCompoundTag("attachments");
        }
        String key = type.getName();
        attachments.removeTag(key);
        IRenderConfig cfg = map.get(attachment);
        if(cfg instanceof MutableRenderConfig) {
            activeConfigs.remove(cfg);
        }
        translateWidget.reset();
        scaleWidget.reset();
        rotateWidget.reset();
    }

    void renderItem(int x, int y, float scale, float yaw, float pitch) {
        GlStateManager.pushMatrix();
        IBakedModel bakedModel = mc.getRenderItem().getItemModelWithOverrides(displayWeapon, null, null);
        mc.getTextureManager().bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
        mc.getTextureManager().getTexture(TextureMap.LOCATION_BLOCKS_TEXTURE).setBlurMipmap(false, false);
        GlStateManager.enableRescaleNormal();
        GlStateManager.enableAlpha();
        GlStateManager.alphaFunc(516, 0.1F);
        GlStateManager.enableBlend();
        GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        setupGuiTransform(x, y, bakedModel.isGui3d(), scale, yaw, pitch);
        bakedModel = net.minecraftforge.client.ForgeHooksClient.handleCameraTransforms(bakedModel, ItemCameraTransforms.TransformType.GUI, false);
        mc.getRenderItem().renderItem(displayWeapon, bakedModel);
        GlStateManager.disableAlpha();
        GlStateManager.disableRescaleNormal();
        GlStateManager.disableLighting();
        GlStateManager.popMatrix();
        mc.getTextureManager().bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
        mc.getTextureManager().getTexture(TextureMap.LOCATION_BLOCKS_TEXTURE).restoreLastBlurMipmap();
    }

    void setupGuiTransform(int xPosition, int yPosition, boolean isGui3d, float scale, float yaw, float pitch) {
        GlStateManager.translate((float)xPosition, (float)yPosition, 400.0F + this.zLevel);
        GlStateManager.translate(8.0F, 8.0F, 0.0F);
        GlStateManager.rotate(yaw, 0.0F, 1.0F, 0.0F);
        GlStateManager.rotate(-30.0F + pitch, 0.0F, 0.0F, 1.0F);
        GlStateManager.scale(1.0F, -1.0F, 1.0F);
        GlStateManager.scale(80.0F, 80.0F, 80.0F);
        GlStateManager.scale(scale, scale, scale);
        if (isGui3d) {
            GlStateManager.enableLighting();
        } else {
            GlStateManager.disableLighting();
        }
    }

    void exportFile() {
        File dir = new File("./export");
        try {
            dir.mkdir();
            String fileName = "cfg_" + displayWeapon.getItem().getRegistryName().getResourcePath() + "_" + LocalDateTime.now().toString().replaceAll("[:.]", "-") + ".txt";
            File out = new File(dir, fileName);
            out.createNewFile();
            FileWriter writer = new FileWriter(out);
            StringBuilder builder = new StringBuilder();
            for (Map.Entry<ItemAttachment, IRenderConfig> entry : map.entrySet()) {
                ItemAttachment attachment = entry.getKey();
                IRenderConfig config = entry.getValue();
                String itemName = "PMCItems." + attachment.getRegistryName().getResourcePath().toUpperCase();
                if(config instanceof MutableRenderConfig) {
                    MutableRenderConfig mcfg = (MutableRenderConfig) config;
                    String configDef = mcfg.toString();
                    builder.append(String.format("registerRenderConfig(%s, %s);\n", itemName, configDef));
                }
            }
            writer.write(builder.toString());
            writer.close();
            mc.player.sendMessage(new TextComponentString("File has been exported as " + out.getAbsolutePath()));
        } catch (Exception e) {
            Pubgmc.logger.error("Error occurred while exporting attachment render config: ", e);
        }
    }

    class AttachmentListWidget extends Widget {

        List<ItemAttachment> list = new ArrayList<>();
        List<Widget> widgets = new ArrayList<>();
        int displayCount;
        int scrollIndex;

        AttachmentListWidget(int x, int y, int width, int height) {
            super(x, y, width, height);
        }

        @Override
        public void render(Minecraft mc, int mouseX, int mouseY, float partialTicks) {
            float bgColor = GuiGunConfig.this.lightTheme ? 1.0F : 0.0F;
            drawColorShape(x, y, x + width, y + height, bgColor, bgColor, bgColor, 0.2F);
            for (Widget widget : widgets) {
                widget.render(mc, mouseX, mouseY, partialTicks);
            }
        }

        @Override
        public boolean handleClicked(int mouseX, int mouseY, int button) {
            for (Widget widget : widgets) {
                if (widget.handleClicked(mouseX, mouseY, button)) {
                    Minecraft.getMinecraft().getSoundHandler().playSound(PositionedSoundRecord.getMasterRecord(SoundEvents.UI_BUTTON_CLICK, 1.0F));
                    return true;
                }
            }
            return false;
        }

        @Override
        public boolean canScrollTo(int delta) {
            int j = scrollIndex + delta;
            return j >= 0 && j < list.size() - displayCount;
        }

        void setListAndUpdate(List<ItemAttachment> list) {
            this.list = list;
            this.displayCount = height / 20;
            updateWidgets();
        }

        void updateWidgets() {
            widgets.clear();
            for (int i = scrollIndex; i < Math.min(scrollIndex + displayCount, list.size()); i++) {
                int j = i - scrollIndex;
                ItemAttachment attachment = list.get(i);
                BooleanSupplier supplier = () -> GuiGunConfig.this.lightTheme;
                widgets.add(new CheckboxWidget(x, y + j * 20, width, 20, I18n.format(attachment.getUnlocalizedName() + ".name"), (state, mouseX, mouseY, widget) -> {
                    if(state) {
                        GuiGunConfig.this.addAttachment(attachment);
                    } else GuiGunConfig.this.removeAttachment(attachment);
                }).lightThemeSupplier(supplier));
            }
        }
    }

    static class SelectionWidget<T> extends Widget {

        final List<T> list;
        final Function<T, String> toStringFunction;
        final ButtonWidget decrement;
        final ButtonWidget increment;
        final ElementChangeCallback<T> callback;
        int selected;

        SelectionWidget(int x, int y, int width, int height, List<T> list, Function<T, String> toStringFunction, ElementChangeCallback<T> callback) {
            super(x, y, width, height);
            this.list = list;
            this.list.sort(Comparator.comparing(toStringFunction));
            this.toStringFunction = toStringFunction;
            this.callback = callback;
            this.decrement = new ButtonWidget(x, y, 20, height, "<<", (widget, mouseX, mouseY, button) -> incr(-1));
            this.increment = new ButtonWidget(x + width - 20, y, 20, height, ">>", (widget, mouseX, mouseY, button) -> incr(1));
        }

        public T getElement() {
            return list.get(selected);
        }

        @Override
        public void render(Minecraft mc, int mouseX, int mouseY, float partialTicks) {
            decrement.render(mc, mouseX, mouseY, partialTicks);
            increment.render(mc, mouseX, mouseY, partialTicks);
            String text = toStringFunction.apply(getElement());
            int textFieldStart = x + 20;
            int textFieldWidth = width - 40;
            int textWidth = mc.fontRenderer.getStringWidth(text);
            drawColorShape(textFieldStart, y, textFieldStart + textFieldWidth, y + height, 0.0F, 0.0F, 0.0F, 0.5F);
            mc.fontRenderer.drawStringWithShadow(text, textFieldStart + (textFieldWidth - textWidth) / 2.0F, y + (height - mc.fontRenderer.FONT_HEIGHT) / 2.0F, 0xFFFFFF);
        }

        @Override
        public boolean handleClicked(int mouseX, int mouseY, int button) {
            if(increment.handleClicked(mouseX, mouseY, button) || decrement.handleClicked(mouseX, mouseY, button)) {
                ClientProxy.playButtonPressSound();
                return true;
            }
            return false;
        }

        void incr(int i) {
            int prev = selected;
            int j = prev + i;
            if(j < 0) {
                selected = list.size() - 1;
            } else if(j >= list.size()) {
                selected = 0;
            } else {
                selected = j;
            }
            callback.call(list.get(prev), list.get(selected), this);
        }

        interface ElementChangeCallback<T> {
            void call(T previous, T current, SelectionWidget<T> widget);
        }
    }

    class GunDisplayWidget extends Widget {

        float scale = 1.0F;
        float yaw = 0.0F;
        float pitch = 0.0F;
        int clickX, clickY;

        GunDisplayWidget(int x, int y, int width, int height) {
            super(x, y, width, height);
        }

        @Override
        public boolean canScrollTo(int delta) {
            return true;
        }

        @Override
        public void onScroll(int delta) {
            this.scale = MathHelper.clamp(scale + 0.25F * delta, 1.0F, 5.0F);
        }

        @Override
        public void onDrag(int mouseX, int mouseY, int button, long time) {
            int diffX = this.clickX - mouseX;
            int diffY = this.clickY - mouseY;
            this.yaw -= diffX;
            this.pitch = MathHelper.clamp(this.pitch + diffY, -90.0F, 90.0F);
            this.clickX = mouseX;
            this.clickY = mouseY;
        }

        @Override
        public void onClick(int mouseX, int mouseY, int button) {
            this.clickX = mouseX;
            this.clickY = mouseY;
        }

        @Override
        public void render(Minecraft mc, int mouseX, int mouseY, float partialTicks) {
            if(GuiGunConfig.this.lightTheme)
                drawColorShape(x, y, x + width, y + height, 1.0F, 1.0F, 1.0F, 0.6F);
            else
                drawColorShape(x, y, x + width, y + height, 0.0F, 0.0F, 0.0F, 0.6F);
            GuiGunConfig.this.renderItem(x + width / 2, y + height / 2 - 40, scale, yaw, pitch);
        }
    }

    class PropertyArrayWidget extends Widget {

        final String propertyName;
        final Setter setter;
        FloatFieldWidget xValue;
        FloatFieldWidget yValue;
        FloatFieldWidget zValue;

        PropertyArrayWidget(int x, int y, int width, int height, String propertyName, Setter setter, float value, float f, float shiftF, float controlF) {
            super(x, y, width, height);
            this.propertyName = propertyName;
            this.setter = setter;
            FloatFieldWidget.Callback callback = val -> GuiGunConfig.this.activeConfigs.forEach(mcfg -> setter.set(mcfg, xValue.f, yValue.f, zValue.f));
            xValue = new FloatFieldWidget(x, y + 15, width, 20, value, f, shiftF, controlF).withCallback(callback);
            yValue = new FloatFieldWidget(x, y + 35, width, 20, value, f, shiftF, controlF).withCallback(callback);
            zValue = new FloatFieldWidget(x, y + 55, width, 20, value, f, shiftF, controlF).withCallback(callback);
        }

        PropertyArrayWidget(int x, int y, int width, int height, String propertyName, Setter setter, float f) {
            this(x, y, width, height, propertyName, setter, f, 0.1F, 0.025F, 0.01F);
        }

        void reset() {
            xValue.reset();
            yValue.reset();
            zValue.reset();
        }

        @Override
        public void render(Minecraft mc, int mouseX, int mouseY, float partialTicks) {
            FontRenderer renderer = mc.fontRenderer;
            renderer.drawString(propertyName, x, y + 6, 0xFFFFFF);
            xValue.render(mc, mouseX, mouseY, partialTicks);
            yValue.render(mc, mouseX, mouseY, partialTicks);
            zValue.render(mc, mouseX, mouseY, partialTicks);
        }

        @Override
        public boolean handleClicked(int mouseX, int mouseY, int button) {
            return xValue.handleClicked(mouseX, mouseY, button) || yValue.handleClicked(mouseX, mouseY, button) || zValue.handleClicked(mouseX, mouseY, button);
        }
    }

    interface Setter {
        void set(MutableRenderConfig cfg, float f1, float f2, float f3);
    }

    static class FloatFieldWidget extends Widget {

        final float valueStepNormal;
        final float valueStepLShift;
        final float valueStepLControl;
        final float defaultValue;
        float f;
        String value;
        ButtonWidget decrease;
        ButtonWidget increase;
        Callback callback;

        FloatFieldWidget(int x, int y, int width, int height, float value) {
            this(x, y, width, height, value, 0.1F);
        }

        FloatFieldWidget(int x, int y, int width, int height, float value, float stepValue) {
            this(x, y, width, height, value, stepValue, stepValue / 4.0F, stepValue / 10.0F);
        }

        FloatFieldWidget(int x, int y, int width, int height, float value, float valueStepNormal, float valueStepLShift, float valueStepLControl) {
            super(x, y, width, height);
            this.value = String.valueOf(value);
            this.f = value;
            this.defaultValue = value;
            decrease = new ButtonWidget(x, y, 20, height, "<<", (widget, mouseX, mouseY, button) -> increase(-1));
            increase = new ButtonWidget(x + width - 20, y, 20, height, ">>", (widget, mouseX, mouseY, button) -> increase(1));
            this.valueStepNormal = valueStepNormal;
            this.valueStepLShift = valueStepLShift;
            this.valueStepLControl = valueStepLControl;
        }

        public FloatFieldWidget withCallback(Callback callback) {
            this.callback = callback;
            return this;
        }

        public void reset() {
            f = defaultValue;
            value = String.valueOf(f);
            validateAndSet();
        }

        @Override
        public void render(Minecraft mc, int mouseX, int mouseY, float partialTicks) {
            drawColorShape(x + 25, y, x + width - 25, y + height, 1.0F, 1.0F, focused ? 0.0F : 1.0F, 1.0F);
            drawColorShape(x + 26, y + 1, x + width - 26, y + height - 1, 0.0F, 0.0F, 0.0F, 1.0F);
            FontRenderer renderer = mc.fontRenderer;
            renderer.drawString(value, x + 27, y + (height - renderer.FONT_HEIGHT) / 2.0F, 0xFFFFFF, false);
            decrease.render(mc, mouseX, mouseY, partialTicks);
            increase.render(mc, mouseX, mouseY, partialTicks);
        }

        @Override
        public void onClick(int mouseX, int mouseY, int button) {
            if(!validateAndSet()) {
                value = String.valueOf(f);
            }
        }

        @Override
        public boolean handleClicked(int mouseX, int mouseY, int button) {
            if(decrease.handleClicked(mouseX, mouseY, button) || increase.handleClicked(mouseX, mouseY, button)) {
                return true;
            }
            return super.handleClicked(mouseX, mouseY, button);
        }

        @Override
        public boolean isMouseOver(int mouseX, int mouseY) {
            return mouseX >= x + 25 && mouseX <= x + width - 25 && mouseY >= y && mouseY <= y + height;
        }

        @Override
        public void onKeyPress(char character, int keycode) {
            if(!isFocused())
                return;
            if(Character.isDigit(character) || character == '.' || character == '-') {
                addChar(character);
            } else {
                if (keycode == Keyboard.KEY_BACK) {
                    if (!value.isEmpty())
                        removeChar();
                }
            }
        }

        public void addChar(char character) {
            value += character;
            validateAndSet();
        }

        public void removeChar() {
            value = value.substring(0, value.length() - 1);
            validateAndSet();
        }

        @Override
        public void unfocus() {
            super.unfocus();
            validateAndSet();
        }

        boolean validateAndSet() {
            if(DECIMAL_PATTERN.matcher(value).matches()) {
                f = Float.parseFloat(value);
                if(callback != null)
                    callback.onSet(f);
                return true;
            }
            return false;
        }

        void increase(int mod) {
            if(GuiScreen.isCtrlKeyDown()) {
                f += valueStepLControl * mod;
            } else if(GuiScreen.isShiftKeyDown()) {
                f += valueStepLShift * mod;
            } else {
                f += valueStepNormal * mod;
            }
            value = String.valueOf(f);
            if(callback != null)
                callback.onSet(f);
        }

        interface Callback {
            void onSet(float value);
        }
    }
}

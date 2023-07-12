package dev.toma.pubgmc.client.gui;

import dev.toma.pubgmc.api.data.DataVersion.CompareResult;
import dev.toma.pubgmc.api.data.DataVersionManager;
import dev.toma.pubgmc.client.gui.menu.GuiWidgets;
import dev.toma.pubgmc.client.gui.widget.ButtonWidget;
import dev.toma.pubgmc.client.gui.widget.CheckboxWidget;
import dev.toma.pubgmc.client.gui.widget.VanillaButtonWidget;
import dev.toma.pubgmc.util.helper.ImageUtil;
import dev.toma.pubgmc.util.helper.TextComponentHelper;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;

import java.util.*;
import java.util.stream.Collectors;

public class GuiUpdateDataFiles extends GuiWidgets {

    private static final ITextComponent HEADER = new TextComponentTranslation("gui.pubgmc.update_data_files.header");
    private static final ITextComponent WARNING = new TextComponentTranslation("gui.pubgmc.update_data_files.warning");
    private final GuiScreen parent;
    private final Map<ResourceLocation, CompareResult> filesToUpdate;

    private final Set<ResourceLocation> selected = new HashSet<>();

    public GuiUpdateDataFiles(GuiScreen parent, Map<ResourceLocation, CompareResult> filesToUpdate) {
        this.parent = parent;
        this.filesToUpdate = filesToUpdate;
    }

    @Override
    public void init() {
        List<ResourceLocation> entries = new ArrayList<>();
        entries.addAll(filesToUpdate.entrySet().stream()
                .filter(entry -> entry.getValue() == CompareResult.MAJOR_MISMATCH)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList())
        );
        entries.addAll(filesToUpdate.entrySet().stream()
                .filter(entry -> entry.getValue() == CompareResult.MINOR_MISMATCH)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList())
        );

        addWidget(new VanillaButtonWidget(width - 75, height - 25, 70, 20, TextComponentHelper.CONFIRM.getFormattedText(), this::confirmButtonClicked));

        for (int i = 0; i < entries.size(); i++) {
            ResourceLocation identifier = entries.get(i);
            CompareResult compareResult = filesToUpdate.get(identifier);
            TextFormatting color = compareResult == CompareResult.MAJOR_MISMATCH ? TextFormatting.RED : TextFormatting.YELLOW;
            ITextComponent entryName = new TextComponentTranslation(String.format("data.%s", identifier.toString().replaceAll(":", ".")));
            ITextComponent widget = new TextComponentTranslation("label.pubgmc.data.mismatch", entryName);
            widget.getStyle().setColor(color);
            CheckboxWidget checkbox = new CheckboxWidget(5, 25 + i * 20, width - 10, 20, widget.getFormattedText(), (state, x, y, clickedCheckbox) -> checkboxStateChanged(state, identifier));
            if (compareResult == CompareResult.MAJOR_MISMATCH) {
                selected.add(identifier);
                checkbox.initialState(true);
            }
            addWidget(checkbox);
        }
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        drawDefaultBackground();
        ImageUtil.drawShape(0, 0, width, 20, 0.0F, 0.0F, 0.0F, 0.5F);
        ImageUtil.drawShape(0, height - 40, width, height, 0.0F, 0.0F, 0.0F, 0.5F);
        String headerText = HEADER.getFormattedText();
        fontRenderer.drawString(headerText, (width - fontRenderer.getStringWidth(headerText)) / 2.0F, (20 - fontRenderer.FONT_HEIGHT) / 2.0F, 0xFFFFFF, false);
        String warningText = WARNING.getFormattedText();
        fontRenderer.drawString(warningText, (width - fontRenderer.getStringWidth(warningText)) / 2.0F, height - 37, 0xFFFFFF, false);
        drawWidgets(mc, mouseX, mouseY, partialTicks);
    }

    private void confirmButtonClicked(ButtonWidget widget, int x, int y, int button) {
        if (!selected.isEmpty()) {
            DataVersionManager.markCorrected(selected);
        }
        mc.displayGuiScreen(parent);
    }

    private void checkboxStateChanged(boolean selected, ResourceLocation identifier) {
        if (selected) {
            this.selected.add(identifier);
        } else {
            this.selected.remove(identifier);
        }
    }

    static {
        HEADER.getStyle().setBold(true);
        WARNING.getStyle().setItalic(true);
        WARNING.getStyle().setColor(TextFormatting.YELLOW);
    }
}

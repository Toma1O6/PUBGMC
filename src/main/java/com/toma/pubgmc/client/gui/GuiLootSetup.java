package com.toma.pubgmc.client.gui;

import com.toma.pubgmc.Pubgmc;
import com.toma.pubgmc.common.capability.IWorldData;
import com.toma.pubgmc.util.ImageUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;

public class GuiLootSetup extends GuiScreenCentered {

    private static final ResourceLocation TEXTURE = new ResourceLocation(Pubgmc.MOD_ID + ":textures/gui/lootsetup.png");
    private IWorldData data;

    public GuiLootSetup(IWorldData lootData) {
        mc = Minecraft.getMinecraft();
        if(lootData == null) {
            mc.player.sendMessage(new TextComponentString(TextFormatting.RED + "Couldn't receive world data for gui"));
            mc.displayGuiScreen(null);
            return;
        }
        if(mc.player.getPermissionLevel() < 2) {
            mc.displayGuiScreen(null);
            mc.player.sendMessage(new TextComponentString(TextFormatting.RED + "You don't have permission to edit loot data"));
            return;
        }
        this.data = lootData;
    }

    @Override
    public void initGui() {
        this.setDimension(176, 170).calculateGuiPosition();
        this.buttonList.add(this.new BTNLootBool(0, guiLeft + 90, guiTop + 10, 75, 20, data.hasAirdropWeapons()));
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        mc.getTextureManager().bindTexture(TEXTURE);
        this.drawTexturedModalRect(guiLeft, guiTop, 0, 0, getGuiWidth(), getGuiHeight());
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    public void onGuiClosed() {

    }

    private class BTNLootBool extends GuiButton {

        private boolean buttonState;

        public BTNLootBool(int index, int x, int y, int width, int height, boolean initialVal) {
            super(index, x, y, width, height, "");
            this.buttonState = initialVal;
            this.displayString = initialVal + "";
        }

        @Override
        public void drawButton(Minecraft mc, int mouseX, int mouseY, float partialTicks) {
            if (this.visible) {
                FontRenderer fontrenderer = mc.fontRenderer;
                GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
                this.hovered = mouseX >= this.x && mouseY >= this.y && mouseX < this.x + this.width && mouseY < this.y + this.height;
                ImageUtil.drawImageWithUV(mc, GuiLootSetup.this.TEXTURE, this.x, this.y, this.width, this.height, 176/256.0D, hovered ? 20/256.0D : 0D, 250/256.0D, hovered ? 40/256.0D : 20/256.0D, true);
                int color = hovered ? 0x009B00 : 0xC20000;
                this.drawCenteredString(fontrenderer, this.displayString, this.x + this.width / 2, this.y + (this.height - 8) / 2, color);
            }
        }

        public void buttonClicked() {
            this.buttonState = !this.buttonState;
            this.displayString = buttonState ? "true" : "false";
        }

        public boolean getButtonState() {
            return buttonState;
        }
    }
}

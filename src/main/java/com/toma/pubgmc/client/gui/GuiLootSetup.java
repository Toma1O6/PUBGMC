package com.toma.pubgmc.client.gui;

import com.toma.pubgmc.Pubgmc;
import com.toma.pubgmc.common.capability.IWorldData;
import com.toma.pubgmc.common.items.guns.GunBase;
import com.toma.pubgmc.util.ImageUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;

import java.util.List;

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
        this.setDimension(176, 221).calculateGuiPosition();
        this.buttonList.add(this.new BTNLootBool(0, guiLeft + 90, guiTop + 10, 75, 20, data.hasAirdropWeapons()));
        this.buttonList.add(this.new BTNLootBool(1, guiLeft + 90, guiTop + 35, 75, 20, data.isAmmoLootEnabled()));
        this.buttonList.add(this.new BTNLootBool(2, guiLeft + 90, guiTop + 60, 75, 20, data.isRandomAmmoCountEnabled()));
        this.buttonList.add(this.new ChanceModifierButton(data, 3, true, guiLeft + 90, guiTop + 85));
        this.buttonList.add(this.new ChanceModifierButton(data, 4, false, guiLeft + 145, guiTop + 85));
        int j = 0;
        for(int i = 0; i < GunBase.GunType.values().length; i++) {
            GunBase.GunType type = GunBase.GunType.values()[i];
            if(type == GunBase.GunType.LMG) {
                continue;
            }
            this.buttonList.add(this.new BTNWeaponType(this, type, j % 2 == 0 ? guiLeft + 10 : guiLeft + 90, guiTop + 125 + ((j / 2) * 25) + 10));
            j++;
        }
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        mc.getTextureManager().bindTexture(TEXTURE);
        this.drawTexturedModalRect(guiLeft, guiTop, 0, 0, getGuiWidth(), getGuiHeight());
        mc.fontRenderer.drawString("Airdrop guns", guiLeft + 10, guiTop + 15, 0x3F3F3F);
        mc.fontRenderer.drawString("Ammo", guiLeft + 10, guiTop + 40, 0x3F3F3F);
        mc.fontRenderer.drawString("Random ammo", guiLeft + 10, guiTop + 65, 0x3F3F3F);
        mc.fontRenderer.drawString("Chance", guiLeft + 10, guiTop + 90, 0x3F3F3F);
        mc.fontRenderer.drawString("Weapon classes:", guiLeft + 10, guiTop + 115, 0x3F3F3F);
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    protected void actionPerformed(GuiButton button) {
        if (button instanceof BTNWeaponType) {
            ((BTNWeaponType)button).buttonClicked();
        } else if(button instanceof BTNLootBool) {
            ((BTNLootBool)button).buttonClicked();
            switch(button.id) {
                case 0: {
                    data.toggleAirdropWeapons(!data.hasAirdropWeapons());
                    break;
                }
                case 1: {
                    data.toggleAmmoLoot(!data.isAmmoLootEnabled());
                    break;
                }
                case 2: {
                    data.toggleRandomAmmoCount(!data.isRandomAmmoCountEnabled());
                    break;
                }
            }
        } else if(button instanceof BTNClickable) {
            ((BTNClickable) button).buttonClicked();
        }
    }

    @Override
    public void onGuiClosed() {

    }

    private class ChanceModifierButton extends GuiButton implements BTNClickable {

        private final boolean isLeft;
        private final IWorldData ins;

        public ChanceModifierButton(IWorldData instance, int index, boolean left, int x, int y) {
            super(index, x, y, 20, 20, "");
            this.isLeft = left;
            this.ins = instance;
        }

        @Override
        public void drawButton(Minecraft mc, int mouseX, int mouseY, float partialTicks) {
            if (this.visible) {
                FontRenderer fontrenderer = mc.fontRenderer;
                GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
                this.hovered = mouseX >= this.x && mouseY >= this.y && mouseX < this.x + this.width && mouseY < this.y + this.height;
                ImageUtil.drawImageWithUV(mc, TEXTURE, this.x, this.y, this.width, this.height, isLeft ? 176/256.0D : 231/256.0D, hovered ? 60/256.0D : 40/256.0D, isLeft ? 196/256.0D : 251/256.0D, hovered ? 80/256.0D : 60/256.0D, true);
            }
        }

        @Override
        public void buttonClicked() {
            // decrease
            double amount = GuiScreen.isShiftKeyDown() ? 0.25D : GuiScreen.isCtrlKeyDown() ? 0.1D : 1.0D;
            if(isLeft) {
                amount = -amount;
                ins.setLootChanceMultiplier(this.decrease(amount));
            } else {
                ins.setLootChanceMultiplier(this.increase(amount));
            }
        }

        private double decrease(double amount) {
            if(ins.getLootChanceMultiplier() - amount < 0) {
                return 0;
            }
            return ins.getLootChanceMultiplier() - amount;
        }

        private double increase(double amount) {
            return ins.getLootChanceMultiplier() + amount;
        }
    }

    private class BTNLootBool extends GuiButton implements BTNClickable {

        protected boolean buttonState;

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
                ImageUtil.drawImageWithUV(mc, TEXTURE, this.x, this.y, this.width, this.height, 176/256.0D, hovered ? 20/256.0D : 0D, 251/256.0D, hovered ? 40/256.0D : 20/256.0D, true);
                int color = buttonState ? 0x009B00 : 0xC20000;
                this.drawCenteredString(fontrenderer, this.displayString, this.x + this.width / 2, this.y + (this.height - 8) / 2, color);
            }
        }

        @Override
        public void buttonClicked() {
            this.buttonState = !this.buttonState;
            this.displayString = buttonState ? "true" : "false";
        }

        public boolean getButtonState() {
            return buttonState;
        }
    }

    private class BTNWeaponType extends BTNLootBool {

        private final GunBase.GunType weaponType;
        private final GuiLootSetup parent;

        public BTNWeaponType(GuiLootSetup parent, GunBase.GunType type, int x, int y) {
            super(type.ordinal(), x, y, 75, 20, parent.data.getWeaponList().contains(type));
            this.weaponType = type;
            this.parent = parent;
            this.displayString = type.name().toUpperCase();
        }

        @Override
        public void drawButton(Minecraft mc, int mouseX, int mouseY, float partialTicks) {
            if (this.visible) {
                FontRenderer fontrenderer = mc.fontRenderer;
                GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
                this.hovered = mouseX >= this.x && mouseY >= this.y && mouseX < this.x + this.width && mouseY < this.y + this.height;
                ImageUtil.drawImageWithUV(mc, TEXTURE, this.x, this.y, this.width, this.height, 176/256.0D, hovered ? 20/256.0D : 0D, 251/256.0D, hovered ? 40/256.0D : 20/256.0D, true);
                int color = buttonState ? 0x009B00 : 0xC20000;
                this.drawCenteredString(fontrenderer, this.displayString, this.x + this.width / 2, this.y + (this.height - 8) / 2, color);
            }
        }

        @Override
        public void buttonClicked() {
            List<GunBase.GunType> wepList = parent.data.getWeaponList();
            if(wepList.contains(weaponType)) {
                wepList.remove(weaponType);
            } else {
                wepList.add(weaponType);
            }
            buttonState = wepList.contains(weaponType);
        }

        public GunBase.GunType getWeaponType() {
            return weaponType;
        }
    }

    private interface BTNClickable {
        void buttonClicked();
    }
}

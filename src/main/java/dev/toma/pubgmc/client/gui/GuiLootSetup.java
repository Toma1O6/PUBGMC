package dev.toma.pubgmc.client.gui;

import dev.toma.pubgmc.Pubgmc;
import dev.toma.pubgmc.common.capability.IWorldData;
import dev.toma.pubgmc.common.items.guns.GunBase;
import dev.toma.pubgmc.network.PacketHandler;
import dev.toma.pubgmc.network.server.PacketUpdateLootData;
import dev.toma.pubgmc.util.helper.ImageUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;

import java.text.DecimalFormat;
import java.util.List;

public class GuiLootSetup extends GuiScreenCentered {

    private static final ResourceLocation TEXTURE = new ResourceLocation(Pubgmc.MOD_ID + ":textures/gui/lootsetup.png");
    private final DecimalFormat decFormat = new DecimalFormat("##,##0.00");
    private IWorldData data;
    private ChanceLabel label;

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
        this.buttonList.add(new BTNLootBool(0, guiLeft + 90, guiTop + 10, 75, 20, data.hasAirdropWeapons()));
        this.buttonList.add(new BTNLootBool(1, guiLeft + 90, guiTop + 35, 75, 20, data.isAmmoLootEnabled()));
        this.buttonList.add(new BTNLootBool(2, guiLeft + 90, guiTop + 60, 75, 20, data.isRandomAmmoCountEnabled()));
        this.buttonList.add(new ChanceModifierButton(data, 3, true, guiLeft + 90, guiTop + 85));
        this.buttonList.add(new ChanceModifierButton(data, 4, false, guiLeft + 145, guiTop + 85));
        label = new ChanceLabel(this, guiLeft + 110, guiTop + 85, 35, 20);
        int j = 0;
        for(int i = 0; i < GunBase.GunType.values().length; i++) {
            GunBase.GunType type = GunBase.GunType.values()[i];
            if(type == GunBase.GunType.LMG) {
                continue;
            }
            this.buttonList.add(new BTNWeaponType(this, type, j % 2 == 0 ? guiLeft + 10 : guiLeft + 90, guiTop + 125 + ((j / 2) * 25) + 10));
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
        label.draw(mc, mouseX, mouseY);
        String chanceAmount = decFormat.format(data.getLootChanceMultiplier());
        int chancePosModified = (label.width - mc.fontRenderer.getStringWidth(chanceAmount)) / 2;
        mc.fontRenderer.drawString(chanceAmount, guiLeft + 111 + chancePosModified, guiTop + 91, 0xFFFFFF);
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
        PacketHandler.sendToServer(new PacketUpdateLootData(data.serializeNBT()));
    }

    private static class ChanceLabel {

        private final int x;
        private final int y;
        private final int width;
        private final int height;
        private boolean hovered;
        private int hoverTime;
        private int fpsCounter;
        private final GuiLootSetup parent;

        public ChanceLabel(GuiLootSetup parent, int x, int y, int width, int height) {
            this.parent = parent;
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
            hoverTime = 0;
            fpsCounter = Minecraft.getDebugFPS();
        }

        public void draw(Minecraft mc, int mouseX, int mouseY) {
            this.hovered = mouseX >= x && mouseX <= x + width && mouseY >= y && mouseY <= y + height;
            this.updateCounters();
            ImageUtil.drawImageWithUV(mc, TEXTURE, this.x, this.y, this.width, this.height, 196/256D, hovered ? 60/256D : 40/256D, 231/256D, hovered ? 80/256D : 60/256D, false);
            if (hovered && hoverTime < 15) {
                this.drawHoveredText(mc, mouseX, mouseY);
            }
        }

        private void drawHoveredText(Minecraft mc, int mouseX, int mouseY) {
            int x = mouseX + 243 > parent.width ? mouseX - 243 : mouseX + 3;
            FontRenderer font = mc.fontRenderer;
            GlStateManager.pushMatrix();
            GlStateManager.translate(0, 0, 1);
            ImageUtil.drawImageWithUV(mc, TEXTURE, x, mouseY + 3, 240, 86, 176/256D, 80/256D, 251/256D, 100/256D, false);
            mc.fontRenderer.drawString("Weapon spawn chances (tier 1/tier 2/tier 3)", x + 3, mouseY + 8, 0xFFFFFF);
            mc.fontRenderer.drawString(this.getGunDescSpawnChanceString("Flare gun", 0.5D), x + 3, mouseY + 18, 0xFFFFFF);
            mc.fontRenderer.drawString(this.getGunDescSpawnChanceString("Snipers", 2D), x + 3, mouseY + 28, 0xFFFFFF);
            mc.fontRenderer.drawString(this.getGunDescSpawnChanceString("DMRs", 3D), x + 3, mouseY + 38, 0xFFFFFF);
            mc.fontRenderer.drawString(this.getGunDescSpawnChanceString("Assault rifles", 15D), x + 3, mouseY + 48, 0xFFFFFF);
            mc.fontRenderer.drawString(this.getGunDescSpawnChanceString("Sub-machine guns", 20D), x + 3, mouseY + 58, 0xFFFFFF);
            mc.fontRenderer.drawString(this.getGunDescSpawnChanceString("Shotguns", 35D), x + 3, mouseY + 68, 0xFFFFFF);
            mc.fontRenderer.drawString(this.getGunDescSpawnChanceString("Pistols", 25D), x + 3, mouseY + 78, 0xFFFFFF);
            GlStateManager.popMatrix();
        }

        private String getGunDescSpawnChanceString(String className, double baseAmount) {
            double d = parent.data.getLootChanceMultiplier();
            return className + ": " + TextFormatting.RED + parent.decFormat.format(baseAmount*d) + "%" + TextFormatting.WHITE + "/"
                    + TextFormatting.YELLOW + parent.decFormat.format(baseAmount*1.4F*d) + "%" + TextFormatting.WHITE + "/"
                    + TextFormatting.GREEN + parent.decFormat.format(baseAmount*2.0F*d) + "%";
        }

        private void updateCounters() {
            if (hovered) {
                --fpsCounter;
                if (fpsCounter <= 0) {
                    hoverTime++;
                    fpsCounter = Minecraft.getDebugFPS();
                }
            } else {
                fpsCounter = Minecraft.getDebugFPS();
                hoverTime = 0;
            }
        }
    }

    private static class ChanceModifierButton extends GuiButton implements BTNClickable {

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
            double amount = isShiftKeyDown() ? 0.25D : isCtrlKeyDown() ? 0.1D : 1.0D;
            if(isLeft) {
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
            return Math.min(ins.getLootChanceMultiplier() + amount, 99.99D);
        }
    }

    private static class BTNLootBool extends GuiButton implements BTNClickable {

        protected boolean buttonState;

        public BTNLootBool(int index, int x, int y, int width, int height, boolean initialVal) {
            super(index, x, y, width, height, "");
            this.buttonState = initialVal;
            this.displayString = initialVal ? "enabled" : "disabled";
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
            this.displayString = buttonState ? "enabled" : "disabled";
            /*IWorldData data = GuiLootSetup.this.data;
            switch (id) {
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

            GuiLootSetup.this.data = data;*/
        }

        public boolean getButtonState() {
            return buttonState;
        }
    }

    private static class BTNWeaponType extends BTNLootBool {

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
                parent.data.getWeaponList().remove(weaponType);
            } else {
                parent.data.getWeaponList().add(weaponType);
            }
            buttonState = parent.data.getWeaponList().contains(weaponType);
        }

        public GunBase.GunType getWeaponType() {
            return weaponType;
        }
    }

    private interface BTNClickable {
        void buttonClicked();
    }
}

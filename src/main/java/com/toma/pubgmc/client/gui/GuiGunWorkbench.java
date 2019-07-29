package com.toma.pubgmc.client.gui;

import com.toma.pubgmc.Pubgmc;
import com.toma.pubgmc.client.util.PageButton;
import com.toma.pubgmc.client.util.RecipeButton;
import com.toma.pubgmc.common.container.ContainerGunWorkbench;
import com.toma.pubgmc.common.network.PacketHandler;
import com.toma.pubgmc.common.network.server.PacketCraft;
import com.toma.pubgmc.common.network.server.PacketUpdateWorkbench;
import com.toma.pubgmc.common.tileentity.TileEntityGunWorkbench;
import com.toma.pubgmc.util.recipes.PMCRecipe;
import com.toma.pubgmc.util.recipes.PMCRecipe.CraftingCategory;
import com.toma.pubgmc.util.recipes.RecipeRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

import java.io.IOException;
import java.util.List;

public class GuiGunWorkbench extends GuiContainer {
    private static final ResourceLocation TEXTURES = new ResourceLocation(Pubgmc.MOD_ID + ":textures/gui/gun_workbench.png");
    private final InventoryPlayer player;
    private final TileEntityGunWorkbench tileentity;
    private int timeSinceCraft;

    public GuiGunWorkbench(TileEntityGunWorkbench te, InventoryPlayer playerInv) {
        super(new ContainerGunWorkbench(te, playerInv));
        this.tileentity = te;
        this.player = playerInv;
        xSize = 176;
        ySize = 193;
    }

    @Override
    public void initGui() {
        super.initGui();
        this.updateButtons();
    }

    public void updateButtons() {
        List<PMCRecipe> list = tileentity.RECIPES.get(tileentity.selectedCat.ordinal());
        this.buttonList.clear();
        this.buttonList.add(createPageButton(0, guiLeft + 8, guiTop + 85, false));
        this.buttonList.add(createPageButton(1, guiLeft + 90, guiTop + 85, true));
        this.buttonList.add(createPageButton(2, guiLeft + 120, guiTop + 85, false, list));
        this.buttonList.add(createPageButton(3, guiLeft + 150, guiTop + 85, true, list));
        int index = 3;
        for (int i = tileentity.selectedIndex * 4 + 3; i >= tileentity.selectedIndex * 4; i--) {
            if (i < list.size()) {
                this.buttonList.add(new RecipeButton(buttonList.size(), guiLeft + 45, guiTop + index * 18 + 8, list.get(i), tileentity));
                --index;
            }
        }
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        super.drawScreen(mouseX, mouseY, partialTicks);
        mc.fontRenderer.drawString(tileentity.selectedCat.getCategoryName(), guiLeft + 26, guiTop + 90, 4210752);
        this.renderHoveredToolTip(mouseX, mouseY);
        if (timeSinceCraft > 0) {
            --this.timeSinceCraft;
            if (timeSinceCraft == 0) {
                this.updateButtons();
            }
        }
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        mc.getTextureManager().bindTexture(TEXTURES);
        this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
        if (button instanceof RecipeButton) {
            RecipeButton btn = (RecipeButton) button;
            PMCRecipe recipe = btn.recipe;
            if (btn.active && timeSinceCraft == 0) {
                PacketHandler.sendToServer(new PacketCraft(tileentity.getPos(), RecipeRegistry.RECIPES.indexOf(recipe)));
                this.updateButtons();
                timeSinceCraft = Minecraft.getDebugFPS();
            }
        } else if (button instanceof PageButton) {
            PageButton btn = (PageButton) button;
            switch (btn.id) {
                // category
                case 0:
                case 1: {
                    CraftingCategory cat = btn.isRight ? CraftingCategory.getNextCategory(tileentity.selectedCat) : CraftingCategory.getPrevCategory(tileentity.selectedCat);
                    int page = 0;
                    update(page, cat);
                    this.updateButtons();
                    break;
                }

                case 2:
                case 3: {
                    List<PMCRecipe> list = TileEntityGunWorkbench.RECIPES.get(tileentity.selectedCat.ordinal());
                    int currentPage = btn.visible ? btn.isRight ? tileentity.selectedIndex + 1 : tileentity.selectedIndex - 1 : tileentity.selectedIndex;
                    update(currentPage, tileentity.selectedCat);
                    this.updateButtons();
                    break;
                }
            }
            if (btn.visible) {
                btn.playPressSound(mc.getSoundHandler());
            }
        }
    }

    public List<GuiButton> getButtonList() {
        return this.buttonList;
    }

    private PageButton createPageButton(int index, int x, int y, boolean right, List<PMCRecipe> recipeCollection) {
        PageButton button = new PageButton(index, x, y, right);
        int i = tileentity.selectedIndex + 1;
        boolean flag = right ? recipeCollection.size() > i * 4 : tileentity.selectedIndex > 0;
        button.visible = flag;
        return button;
    }

    private PageButton createPageButton(int index, int x, int y, boolean right) {
        PageButton button = new PageButton(index, x, y, right);
        button.visible = true;
        return button;
    }

    private void update(int page, CraftingCategory category) {
        PacketHandler.sendToServer(new PacketUpdateWorkbench(tileentity, category, page));
        tileentity.selectedIndex = page;
        tileentity.selectedCat = category;
    }
}

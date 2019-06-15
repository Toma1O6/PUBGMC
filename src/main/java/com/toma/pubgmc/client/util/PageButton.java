package com.toma.pubgmc.client.util;

import com.toma.pubgmc.Pubgmc;
import com.toma.pubgmc.util.ImageUtil;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.util.ResourceLocation;

public class PageButton extends GuiButton {

	public final boolean isRight;
	private static final ResourceLocation TEXTURE = new ResourceLocation(Pubgmc.MOD_ID + ":textures/gui/listbutton.png");
	
	public PageButton(int id, int x, int y, boolean isRightArrow) {
		super(id, x, y, 16, 16, "");
		this.isRight = isRightArrow;
	}

	@Override
	public void drawButton(Minecraft mc, int mouseX, int mouseY, float partialTicks) {
		hovered = mouseX >= x && mouseX <= x + width && mouseY >= y && mouseY <= y + height;
		double startU = isRight ? 0.5D : 0;
		double startV = visible ? hovered ? 1/3d : 0 : 2/3d;
		double endU = isRight ? 1 : 0.5D;
		double endV = visible ? hovered ? 2/3D : 1/3D : 1D;
		ImageUtil.drawImageWithUV(mc, TEXTURE, x, y, width, height, startU, startV, endU, endV, false);
	}
	
	public void setVisible(boolean visible) {
		this.visible = visible;
	}
}

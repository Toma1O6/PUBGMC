package dev.toma.pubgmc.config;

import dev.toma.configuration.api.client.ClientHandles;
import dev.toma.configuration.api.client.screen.ComponentScreen;
import dev.toma.pubgmc.client.gui.menu.GuiMenu;
import dev.toma.pubgmc.client.gui.widget.Widget;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class PMCClientHandles extends ClientHandles.DefaultClientHandles {

    public static final PMCClientHandles PMC_HANDLES = new PMCClientHandles();

    @Override
    public void drawConfigBackground(ComponentScreen screen, Minecraft mc) {
        mc.getTextureManager().bindTexture(GuiMenu.BACKGROUND_TEXTURE);
        Widget.drawTexturedShape(0, 0, screen.width, screen.height);
        Widget.drawColorShape(0, 0, screen.width, screen.height, 0.0F, 0.0F, 0.0F, 0.4F);
    }

    @Override
    public int getTextColor() {
        return 0xFFFFFF;
    }
}

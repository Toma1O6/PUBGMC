package dev.toma.pubgmc.mixin.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiIngame;
import net.minecraftforge.client.GuiIngameForge;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(GuiIngameForge.class)
public abstract class GuiIngameForgeMixin extends GuiIngame {

    public GuiIngameForgeMixin(Minecraft mcIn) {
        super(mcIn);
    }

    @ModifyConstant(method = "renderPlayerList", constant = @Constant(intValue = 1), remap = false)
    private int pubgmc$getPlayerLimitForTabOverlayRender(int constant) {
        return 0;
    }
}

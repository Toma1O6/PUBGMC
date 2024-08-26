package dev.toma.pubgmc.mixin.client;

import dev.toma.pubgmc.client.MixinClientHooks;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLivingBase;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(RenderPlayer.class)
public abstract class RenderPlayerMixin extends RenderLivingBase<AbstractClientPlayer> {

    public RenderPlayerMixin(RenderManager renderManagerIn, ModelBase modelBaseIn, float shadowSizeIn) {
        super(renderManagerIn, modelBaseIn, shadowSizeIn);
    }

    @Inject(method = "preRenderCallback(Lnet/minecraft/client/entity/AbstractClientPlayer;F)V", at = @At("RETURN"))
    private void pubgmc$preRenderCallback(AbstractClientPlayer entityIn, float partialTicks, CallbackInfo ci) {
        RenderPlayer renderPlayer = (RenderPlayer) (Object) this;
        MixinClientHooks.player_preRenderCallback(renderPlayer, entityIn, partialTicks);
    }

    @Inject(method = "<init>(Lnet/minecraft/client/renderer/entity/RenderManager;Z)V", at = @At("RETURN"))
    private void pubgmc$renderPlayerInit(RenderManager manager, boolean useSmallArms, CallbackInfo ci) {
        RenderPlayer renderPlayer = (RenderPlayer) (Object) this;
        MixinClientHooks.player_constructRender(renderPlayer, manager, useSmallArms);
    }
}

package dev.toma.pubgmc.mixin.client;

import dev.toma.pubgmc.client.MixinClientHooks;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.resources.IResourceManagerReloadListener;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityRenderer.class)
public abstract class EntityRendererMixin implements IResourceManagerReloadListener {

    @Shadow @Final private int[] lightmapColors;

    @Inject(method = "updateLightmap", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/texture/DynamicTexture;updateDynamicTexture()V", shift = At.Shift.BEFORE))
    private void pubgmc$updateLightmap(CallbackInfo ci) {
        MixinClientHooks.updateLightmap(lightmapColors);
    }
}

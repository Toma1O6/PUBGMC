package dev.toma.pubgmc.mixin.client;

import dev.toma.pubgmc.client.MixinClientHooks;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraftforge.client.ForgeHooksClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ForgeHooksClient.class)
public abstract class ForgeHooksClientMixin {

    @Inject(method = "handleCameraTransforms", at = @At("HEAD"), remap = false)
    private static void pubgmc$handleCameraTransforms(IBakedModel model, ItemCameraTransforms.TransformType cameraTransformType, boolean leftHandHackery, CallbackInfoReturnable<IBakedModel> cir) {
        MixinClientHooks.preRenderItem(cameraTransformType);
    }
}

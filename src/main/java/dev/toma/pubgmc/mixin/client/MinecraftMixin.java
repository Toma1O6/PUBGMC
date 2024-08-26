package dev.toma.pubgmc.mixin.client;

import dev.toma.pubgmc.client.MixinClientHooks;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.profiler.ISnooperInfo;
import net.minecraft.util.IThreadListener;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Minecraft.class)
public abstract class MinecraftMixin implements IThreadListener, ISnooperInfo {

    @Shadow public WorldClient world;

    @Inject(method = "runTick", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/multiplayer/WorldClient;tick()V", shift = At.Shift.BEFORE))
    private void pubgmc$preWorldClientTick(CallbackInfo ci) {
        MixinClientHooks.dispatchPreWorldClientTickEvent(world);
    }

    @Inject(method = "runTick", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/multiplayer/WorldClient;tick()V", shift = At.Shift.AFTER))
    private void pubgmc$postWorldClientTick(CallbackInfo ci) {
        MixinClientHooks.dispatchPostWorldClientTickEvent(world);
    }
}

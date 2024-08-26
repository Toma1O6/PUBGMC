package dev.toma.pubgmc.mixin.client;

import dev.toma.pubgmc.client.MixinClientHooks;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ModelBiped.class)
public abstract class ModelBipedMixin extends ModelBase {

    @Inject(method = "setRotationAngles", at = @At("RETURN"))
    private void pubgmc$setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn, CallbackInfo ci) {
        ModelBiped model = (ModelBiped) (Object) this;
        MixinClientHooks.model_setupModelAngles(model, entityIn);
    }
}

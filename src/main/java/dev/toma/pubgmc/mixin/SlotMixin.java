package dev.toma.pubgmc.mixin;

import dev.toma.pubgmc.MixinHooks;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Slot.class)
public abstract class SlotMixin {

    @Inject(method = "isItemValid", at = @At("HEAD"), cancellable = true)
    public void pubgmc$isItemValid(ItemStack itemStack, CallbackInfoReturnable<Boolean> ci) {
        Slot slot = (Slot) (Object) this;
        ci.setReturnValue(MixinHooks.isItemValid(slot, itemStack));
    }
}

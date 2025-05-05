package dev.toma.pubgmc.mixin;

import dev.toma.pubgmc.common.entity.vehicles.EntityDriveable;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityLivingBase.class)
public abstract class EntityLivingBaseMixin extends Entity {

    public EntityLivingBaseMixin(World worldIn) {
        super(worldIn);
    }

    // Disables automatic dismount position calculation - allows vehicles to implement more realistic dismount positions
    @Inject(method = "dismountEntity", at = @At("HEAD"), cancellable = true)
    private void pubgmc$dismountEntity(Entity entity, CallbackInfo ci) {
        if (entity instanceof EntityDriveable) {
            ci.cancel();
        }
    }
}
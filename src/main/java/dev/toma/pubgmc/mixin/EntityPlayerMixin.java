package dev.toma.pubgmc.mixin;

import dev.toma.pubgmc.api.capability.PlayerDataProvider;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.FMLCommonHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EntityPlayer.class)
public abstract class EntityPlayerMixin extends EntityLivingBase {

    public EntityPlayerMixin(World worldIn) {
        super(worldIn);
    }

    @Inject(method = "updateSize", at = @At("HEAD"), cancellable = true)
    private void pubgmc$playerUpdateSize(CallbackInfo ci) {
        EntityPlayer player = (EntityPlayer) (Object) this;

        float width = 0.6F;
        float height = 1.8F;
        if (isElytraFlying()) {
            height = 0.6F;
        } else if (isPlayerSleeping()) {
            width = 0.2F;
            height = 0.2F;
        } else if (isSneaking()) {
            height = 1.51F;
        } else if (PlayerDataProvider.get(player).isProne()) {
            width = 1.2F;
            height = 0.5F;
        }

        if (width != this.width || height != this.height) {
            this.setSize(width, height);
            this.onGround = true; // fix for immediate prone cancellation
        }

        FMLCommonHandler.instance().onPlayerPostTick(player);
        ci.cancel();
    }

    @Inject(method = "getEyeHeight", at = @At("HEAD"), cancellable = true)
    public void pubgmc$playerGetEyeHeight(CallbackInfoReturnable<Float> cir) {
        AxisAlignedBB aabb = getEntityBoundingBox();
        double diff = aabb.maxY - aabb.minY;
        cir.setReturnValue((float) diff - 0.08F);
    }
}

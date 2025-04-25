package dev.toma.pubgmc.mixin;

import dev.toma.pubgmc.api.capability.PlayerDataProvider;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
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

            // this.onGround = true;

            // this.onGround = player.fallDistance <= 4.0; // fix for immediate prone cancellation
            // // Cannot be set to TRUE as that would break fall damage calculation - player could take damage in midair when crouching for some time

            // Bug1: "occasional immediate prone cancellation"
            // 1.1 even manually reset this.onGround to true is useless for fixing Bug1
            // Bug2: "this.onGround = true" will cause "calculate fall damage while sneaking in air"
            // 2.1 "this.onGround = player.fallDistance < x" is needed
            // Bug3: add 2.1 will allow player to use "alternate sneak and jump" to going up vertically
            // 3.1 even x is 0.001, only reduces the margin for mistake.
            // 3.2 in net.minecraft.entity.EntityLivingBase.java, this.jumpTicks is private, isJumping is protected:
//            else if (this.onGround && this.jumpTicks == 0)
//            {
//                this.jump();
//                this.jumpTicks = 10;
//            }
            // The possible fix attempt below considered:
            // 1. stepping on mid-air stairs, half bricks
            // 2. prevent going up by rubbing against iron bar
            // 3. assume player sneaks at the tick he lands on the ground, there was air directly below, but didn't cancel fallDamage
            boolean isCurrentlySneaking = isSneaking();
            if (isCurrentlySneaking) {
                BlockPos belowPos = player.getPosition().down();
                IBlockState belowState = world.getBlockState(belowPos);
                boolean isSolidGround = belowState.isFullCube() && belowState.getMaterial().isSolid();
                this.onGround = !isCurrentlySneaking || isSolidGround;
            }
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

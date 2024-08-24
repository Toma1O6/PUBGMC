package dev.toma.pubgmc.common.items;

import dev.toma.pubgmc.api.event.ParachuteEvent;
import dev.toma.pubgmc.common.entity.EntityParachute;
import dev.toma.pubgmc.init.PMCSounds;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;

public class ItemParachute extends PMCItem {

    public ItemParachute(String name) {
        super(name);
        setMaxStackSize(1);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        ItemStack stack = playerIn.getHeldItem(handIn);
        if (!playerIn.isRiding()) {
            if (!worldIn.isRemote) {
                float currentFallDistance = playerIn.fallDistance;
                if (currentFallDistance <= 8.0F) {
                    playerIn.sendStatusMessage(new TextComponentTranslation("message.pubgmc.parachute.cannot_open_yet"), true);
                    playerIn.getCooldownTracker().setCooldown(this, 10);
                    return ActionResult.newResult(EnumActionResult.FAIL, stack);
                }
                EntityParachute chute = new EntityParachute(worldIn, playerIn);
                if (MinecraftForge.EVENT_BUS.post(new ParachuteEvent.Open(chute, playerIn))) {
                    return ActionResult.newResult(EnumActionResult.PASS, stack);
                }
                worldIn.playSound(null, playerIn.getPosition(), PMCSounds.chute_open, SoundCategory.MASTER, 1.0F, 1.0F);
                worldIn.spawnEntity(chute);
                playerIn.startRiding(chute);
                if (!playerIn.capabilities.isCreativeMode) {
                    stack.shrink(1);
                    playerIn.getCooldownTracker().setCooldown(this, 10);
                }
            }
        }
        return ActionResult.newResult(EnumActionResult.PASS, stack);
    }
}

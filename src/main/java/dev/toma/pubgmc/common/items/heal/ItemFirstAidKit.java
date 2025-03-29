package dev.toma.pubgmc.common.items.heal;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class ItemFirstAidKit extends ItemHealing {

    public ItemFirstAidKit(String name) {
        super(name);
        setMaxStackSize(2);
    }

    @Override
    public int getUsageTime(ItemStack stack) {
        return 120;
    }

    @Override
    public boolean canHeal(EntityLivingBase entity, ItemStack stack) {
        int health = Math.round(entity.getHealth());
        if (health >= 15) {
            if (entity instanceof EntityPlayer) {
                ITextComponent message = new TextComponentTranslation(UNREACHED_THRESHOLD_KEY, "7.5");
                message.getStyle().setColor(TextFormatting.RED);
                ((EntityPlayer) entity).sendStatusMessage(message, true);
            }
            return false;
        }
        return true;
    }

    @Override
    public void heal(EntityLivingBase entity, ItemStack stack, World world) {
        entity.setHealth(15.0F);
        if (!(entity instanceof EntityPlayer) || !((EntityPlayer) entity).isCreative()) {
            stack.shrink(1);
        }
    }
}

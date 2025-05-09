package dev.toma.pubgmc.common.items.heal;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class ItemMedkit extends ItemHealing {

    public ItemMedkit(String name) {
        super(name);
        setMaxStackSize(1);
    }

    @Override
    public int getUsageTime(ItemStack stack) {
        return 160;
    }

    @Override
    public boolean canHeal(EntityLivingBase entity, ItemStack stack) {
        float healthLimit = entity.getMaxHealth();
        float health = entity.getHealth();
        if (health >= healthLimit) {
            if (entity instanceof EntityPlayer) {
                ITextComponent message = new TextComponentTranslation("message.pubgmc.healing.max_health");
                message.getStyle().setColor(TextFormatting.RED);
                ((EntityPlayer) entity).sendStatusMessage(message, true);
            }
            return false;
        }
        return true;
    }

    @Override
    public void heal(EntityLivingBase entity, ItemStack stack, World world) {
        float healthLimit = entity.getMaxHealth();
        float health = entity.getHealth();
        float toHeal = healthLimit - health;
        entity.heal(toHeal);
        if (!(entity instanceof EntityPlayer) || !((EntityPlayer) entity).isCreative()) {
            stack.shrink(1);
        }
    }
}

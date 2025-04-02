package dev.toma.pubgmc.common.items.attachment;

import dev.toma.pubgmc.PMCTabs;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

public class ItemStock extends ItemAttachment {

    final boolean fastReload;
    final float ads;

    public ItemStock(String name, boolean fastReload, float ads) {
        super(name);
        setCreativeTab(PMCTabs.TAB_ACCESSORIES);
        this.fastReload = fastReload;
        this.ads = ads;
    }

    @Override
    public AttachmentType<?> getType() {
        return AttachmentType.STOCK;
    }

    public boolean isFasterReload() {
        return fastReload;
    }

    public float applyAdsSpeedMultiplier(float in) {
        return in * ads;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        if (fastReload)
            tooltip.add(TextFormatting.AQUA + I18n.format("gun.attachment.magazine.quickdraw"));
        if (ads != 1) {
            int time = Math.round((1/ads - 1) * 100);
            tooltip.add(formatProperty(I18n.format("gun.attachment.ads_time"), (time > 0 ? "+" : "") + time) + "%");
        }
    }
}

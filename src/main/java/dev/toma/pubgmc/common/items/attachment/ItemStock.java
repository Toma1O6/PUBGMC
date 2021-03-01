package dev.toma.pubgmc.common.items.attachment;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

import java.util.List;

public class ItemStock extends ItemAttachment implements Stock {

    final boolean fastReload;
    final float ads;

    public ItemStock(String name, boolean fastReload, float ads) {
        super(name);
        this.fastReload = fastReload;
        this.ads = ads;
    }

    @Override
    public AttachmentType<?> getType() {
        return AttachmentType.STOCK;
    }

    @Override
    public boolean isFasterReload() {
        return fastReload;
    }

    @Override
    public float applyAdsSpeedMultiplier(float in) {
        return in * ads;
    }

    @Override
    public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        if(fastReload)
            tooltip.add(TextFormatting.AQUA + "Faster reload");
        if(ads < 1)
            tooltip.add(formatProperty("ADS speed", "-" + (int)((1.0F - ads) * 100)) + "%");
    }
}
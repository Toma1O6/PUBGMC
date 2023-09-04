package dev.toma.pubgmc.common.items.attachment;

import dev.toma.pubgmc.PMCTabs;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import java.util.List;

public class ItemGrip extends ItemAttachment {

    final float verticalRecoil;
    final float horizontalRecoil;
    final float ads;

    public ItemGrip(String name, float verticalRecoil, float horizontalRecoil) {
        this(name, verticalRecoil, horizontalRecoil, 1.0F);
    }

    public ItemGrip(String name, float verticalRecoil, float horizontalRecoil, float ads) {
        super(name);
        setCreativeTab(PMCTabs.TAB_ACCESSORIES);
        this.verticalRecoil = verticalRecoil;
        this.horizontalRecoil = horizontalRecoil;
        this.ads = ads;
    }

    @Override
    public AttachmentType<?> getType() {
        return AttachmentType.GRIP;
    }

    public float applyVerticalRecoilMultiplier(float in) {
        return in * verticalRecoil;
    }

    public float applyHorizontalRecoilMultiplier(float in) {
        return in * horizontalRecoil;
    }

    public float applyAdsSpeedMultiplier(float in) {
        return in * ads;
    }

    @Override
    public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        if (verticalRecoil < 1) {
            int i = Math.round((1.0F - verticalRecoil) * 100);
            tooltip.add(formatProperty(I18n.format("accessories.vertical.tooltip"), "-" + i) + "%");
        }
        if (horizontalRecoil < 1) {
            int i = Math.round((1.0F - horizontalRecoil) * 100);
            tooltip.add(formatProperty(I18n.format("accessories.horizontal.tooltip"), "-" + i) + "%");
        }
        if (ads < 1) {
            int i = Math.round((1.0F - ads) * 100);
            tooltip.add(formatProperty(I18n.format("accessories.ads.tooltip"), "-" + i) + "%");
        }
    }
}

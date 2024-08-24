package dev.toma.pubgmc.common.items.attachment;

import dev.toma.pubgmc.PMCTabs;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import java.util.List;
import java.util.function.Supplier;

public class ItemGrip extends ItemAttachment {

    private final Supplier<Float> verticalRecoil;
    private final Supplier<Float> horizontalRecoil;
    final float ads;

    public ItemGrip(String name, Supplier<Float> verticalRecoil, Supplier<Float> horizontalRecoil) {
        this(name, verticalRecoil, horizontalRecoil, 1.0F);
    }

    public ItemGrip(String name, Supplier<Float> verticalRecoil, Supplier<Float> horizontalRecoil, float ads) {
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
        return in * verticalRecoil.get();
    }

    public float applyHorizontalRecoilMultiplier(float in) {
        return in * horizontalRecoil.get();
    }

    public float applyAdsSpeedMultiplier(float in) {
        return in * ads;
    }

    @Override
    public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        float verticalRecoil = this.verticalRecoil.get();
        float horizontalRecoil = this.horizontalRecoil.get();
        if (verticalRecoil < 1) {
            int i = Math.round((1.0F - verticalRecoil) * 100);
            tooltip.add(formatProperty("Vertical recoil", "-" + i) + "%");
        }
        if (horizontalRecoil < 1) {
            int i = Math.round((1.0F - horizontalRecoil) * 100);
            tooltip.add(formatProperty("Horizontal recoil", "-" + i) + "%");
        }
        if (ads < 1) {
            int i = Math.round((1.0F - ads) * 100);
            tooltip.add(formatProperty("ADS speed", "-" + i) + "%");
        }
    }
}

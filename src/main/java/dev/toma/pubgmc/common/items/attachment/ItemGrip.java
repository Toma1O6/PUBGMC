package dev.toma.pubgmc.common.items.attachment;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import java.util.List;

public class ItemGrip extends ItemAttachment implements Grip {

    final float verticalRecoil;
    final float horizontalRecoil;
    final float ads;

    public ItemGrip(String name, float verticalRecoil, float horizontalRecoil) {
        this(name, verticalRecoil, horizontalRecoil, 1.0F);
    }

    public ItemGrip(String name, float verticalRecoil, float horizontalRecoil, float ads) {
        super(name);
        this.verticalRecoil = verticalRecoil;
        this.horizontalRecoil = horizontalRecoil;
        this.ads = ads;
    }

    @Override
    public AttachmentType<?> getType() {
        return AttachmentType.GRIP;
    }

    @Override
    public float applyVerticalRecoilMultiplier(float in) {
        return in * verticalRecoil;
    }

    @Override
    public float applyHorizontalRecoilMultiplier(float in) {
        return in * horizontalRecoil;
    }

    @Override
    public float applyAdsSpeedMultiplier(float in) {
        return in * ads;
    }

    @Override
    public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        if(verticalRecoil < 1)
            tooltip.add(formatProperty("Vertical recoil", "-" + (int)((1.0F - verticalRecoil) * 100)) + "%");
        if(horizontalRecoil < 1)
            tooltip.add(formatProperty("Horizontal recoil", "-" + (int)((1.0F - horizontalRecoil) * 100)) + "%");
        if(ads < 1)
            tooltip.add(formatProperty("ADS speed", "-" + (int)((1.0F - ads) * 100)) + "%");
    }
}

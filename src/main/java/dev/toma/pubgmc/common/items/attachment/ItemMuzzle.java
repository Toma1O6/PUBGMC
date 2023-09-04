package dev.toma.pubgmc.common.items.attachment;

import dev.toma.pubgmc.PMCTabs;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

import java.util.List;

public class ItemMuzzle extends ItemAttachment {

    final float verticalRecoil;
    final float horizontalRecoil;
    final boolean silent;

    public ItemMuzzle(String name, boolean silent) {
        this(name, 1.0F, 1.0F, silent);
    }

    public ItemMuzzle(String name, float verticalRecoil, float horizontalRecoil) {
        this(name, verticalRecoil, horizontalRecoil, false);
    }

    public ItemMuzzle(String name, float verticalRecoil, float horizontalRecoil, boolean silent) {
        super(name);
        setCreativeTab(PMCTabs.TAB_ACCESSORIES);
        this.verticalRecoil = verticalRecoil;
        this.horizontalRecoil = horizontalRecoil;
        this.silent = silent;
    }

    @Override
    public AttachmentType<?> getType() {
        return AttachmentType.MUZZLE;
    }

    public float applyVerticalRecoilMultiplier(float in) {
        return in * verticalRecoil;
    }

    public float applyHorizontalRecoilMultiplier(float in) {
        return in * horizontalRecoil;
    }

    public boolean isSilenced() {
        return silent;
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
        if (silent)
            tooltip.add(TextFormatting.AQUA + I18n.format("accessories.silences.tooltip"));
    }
}

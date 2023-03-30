package dev.toma.pubgmc.common.items.attachment;

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
        if(verticalRecoil < 1) {
            int i = Math.round((1.0F - verticalRecoil) * 100);
            tooltip.add(formatProperty("Vertical recoil", "-" + i) + "%");
        }
        if(horizontalRecoil < 1) {
            int i = Math.round((1.0F - horizontalRecoil) * 100);
            tooltip.add(formatProperty("Horizontal recoil", "-" + i) + "%");
        }
        if(silent)
            tooltip.add(TextFormatting.AQUA + "Silences weapon");
    }
}

package dev.toma.pubgmc.common.items.attachment;

import dev.toma.pubgmc.PMCTabs;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

import java.util.List;
import java.util.function.Supplier;

public class ItemMuzzle extends ItemAttachment {

    private final Supplier<Float> verticalRecoil;
    private final Supplier<Float> horizontalRecoil;
    private final boolean silent;

    public ItemMuzzle(String name, boolean silent) {
        this(name, () -> 1.0F, () -> 1.0F, silent);
    }

    public ItemMuzzle(String name, Supplier<Float> verticalRecoil, Supplier<Float> horizontalRecoil) {
        this(name, verticalRecoil, horizontalRecoil, false);
    }

    public ItemMuzzle(String name, Supplier<Float> verticalRecoil, Supplier<Float> horizontalRecoil, boolean silent) {
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
        return in * verticalRecoil.get();
    }

    public float applyHorizontalRecoilMultiplier(float in) {
        return in * horizontalRecoil.get();
    }

    public boolean isSilenced() {
        return silent;
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
        if (silent)
            tooltip.add(TextFormatting.AQUA + "Silences weapon");
    }
}

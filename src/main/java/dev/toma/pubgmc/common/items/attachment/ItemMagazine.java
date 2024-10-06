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

public class ItemMagazine extends ItemAttachment {

    final boolean extended;
    final boolean quickdraw;

    public ItemMagazine(String name, boolean extended, boolean quickdraw) {
        super(name);
        setCreativeTab(PMCTabs.TAB_ACCESSORIES);
        this.extended = extended;
        this.quickdraw = quickdraw;
    }

    @Override
    public AttachmentType<?> getType() {
        return AttachmentType.MAGAZINE;
    }

    public boolean isExtended() {
        return extended;
    }

    public boolean isQuickdraw() {
        return quickdraw;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        if (extended)
            tooltip.add(TextFormatting.AQUA + I18n.format("gun.attachment.magazine.extended"));
        if (quickdraw)
            tooltip.add(TextFormatting.AQUA + I18n.format("gun.attachment.magazine.quickdraw"));
    }
}

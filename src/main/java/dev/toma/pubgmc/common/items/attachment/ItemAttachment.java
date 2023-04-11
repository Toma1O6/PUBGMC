package dev.toma.pubgmc.common.items.attachment;

import dev.toma.pubgmc.PMCTabs;
import dev.toma.pubgmc.common.items.PMCItem;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

import java.util.List;

public abstract class ItemAttachment extends PMCItem {

    public ItemAttachment(String name) {
        this(name, false);
    }

    public ItemAttachment(String name, boolean airdropOnly) {
        super(name);
        setCreativeTab(PMCTabs.TAB_ACCESSORIES);
        setMaxStackSize(1);
    }

    public abstract AttachmentType<?> getType();

    @Override
    public abstract void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn);

    public String formatProperty(String property, String value) {
        return TextFormatting.GRAY + property + ": " + TextFormatting.AQUA + value;
    }
}

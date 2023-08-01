package dev.toma.pubgmc.common.items.attachment;

import net.minecraft.item.Item;
import net.minecraft.util.text.TextFormatting;

public abstract class ItemAttachment extends Item {

    public ItemAttachment(String name) {
        setUnlocalizedName(name);
        setRegistryName(name);
        setMaxStackSize(1);
    }

    public abstract AttachmentType<?> getType();

    public String formatProperty(String property, String value) {
        return TextFormatting.GRAY + property + ": " + TextFormatting.AQUA + value;
    }
}

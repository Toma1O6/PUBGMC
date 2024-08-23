package dev.toma.pubgmc.common.items.attachment;

import dev.toma.pubgmc.PMCTabs;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

public class ItemScope extends ItemAttachment {

    private final ScopeZoom scopeZoom;

    public ItemScope(String name) {
        this(name, null);
    }

    public ItemScope(String name, ScopeZoom scopeZoom) {
        super(name);
        setCreativeTab(PMCTabs.TAB_ACCESSORIES);
        this.scopeZoom = scopeZoom;
    }

    @Override
    public AttachmentType<?> getType() {
        return AttachmentType.SCOPE;
    }

    public boolean hasCustomZoom() {
        return this.scopeZoom != null;
    }

    public ScopeZoom getZoomSettings() {
        return this.scopeZoom;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        if (this.hasCustomZoom()) {
            tooltip.add(formatProperty("FOV", this.scopeZoom.toString()));
            if (this.scopeZoom.hasMouseScrollOverrides()) {
                tooltip.add(TextFormatting.DARK_GRAY + "LeftAlt + Mouse scroll to change zoom");
            }
        }
    }
}

package dev.toma.pubgmc.common.items.attachment;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import java.util.List;

public class ItemScope extends ItemAttachment implements Scope {

    final ScopeData data;

    public ItemScope(String name, ScopeData data) {
        super(name);
        this.data = data;
    }

    @Override
    public AttachmentType<?> getType() {
        return AttachmentType.SCOPE;
    }

    @Override
    public int getZoom(int fov) {
        return Math.min(fov, data.getZoom());
    }

    @Override
    public float getMouseSensMultiplier() {
        return data.getMouseSens();
    }

    public ScopeData getData() {
        return data;
    }

    @Override
    public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add(formatProperty("FOV", data.getZoom() + ""));
    }
}

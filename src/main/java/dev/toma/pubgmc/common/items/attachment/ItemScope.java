package dev.toma.pubgmc.common.items.attachment;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import java.util.List;

public class ItemScope extends ItemAttachment implements Scope {

    final int zoom;
    final float mouseSens;

    public ItemScope(String name, int zoom) {
        this(name, zoom, 1.0F);
    }

    public ItemScope(String name, int zoom, float mouseSens) {
        super(name);
        this.zoom = zoom;
        this.mouseSens = mouseSens;
    }

    @Override
    public AttachmentType<?> getType() {
        return AttachmentType.SCOPE;
    }

    @Override
    public int getZoom(int fov) {
        return Math.min(fov, zoom);
    }

    @Override
    public float getMouseSensMultiplier() {
        return mouseSens;
    }

    @Override
    public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add(formatProperty("FOV", zoom + ""));
    }
}

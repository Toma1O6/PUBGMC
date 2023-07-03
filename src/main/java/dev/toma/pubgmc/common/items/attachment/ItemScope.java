package dev.toma.pubgmc.common.items.attachment;

import dev.toma.pubgmc.PMCTabs;
import net.minecraft.client.Minecraft;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

public class ItemScope extends ItemAttachment {

    final ScopeData data;

    public ItemScope(String name, ScopeData data) {
        super(name);
        setCreativeTab(PMCTabs.TAB_ACCESSORIES);
        this.data = data;
    }

    @Override
    public AttachmentType<?> getType() {
        return AttachmentType.SCOPE;
    }

    public int getZoom(int fov) {
        return data.getZoom() < 0 ? fov : data.getZoom();
    }

    public ScopeData getData() {
        return data;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        int fov = this.getZoom((int) Minecraft.getMinecraft().gameSettings.fovSetting);
        tooltip.add(formatProperty("FOV", fov + ""));
    }
}

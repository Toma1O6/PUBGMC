package dev.toma.pubgmc.api.client;

import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public interface IIconRender {

    ResourceLocation getTexture(ItemStack stack);

    int getColorByDamage(float durability);
}

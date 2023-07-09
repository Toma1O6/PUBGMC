package dev.toma.pubgmc.api.game;

import net.minecraft.item.ItemStack;

import java.util.List;

public interface Generator extends GameObject {

    @Deprecated
    String getLootConfigurationId();

    // TODO all logic to be handled within the generate method
    @Deprecated
    void fillWithLoot(List<ItemStack> items);

    void generate(GenerationType.Context context);
}

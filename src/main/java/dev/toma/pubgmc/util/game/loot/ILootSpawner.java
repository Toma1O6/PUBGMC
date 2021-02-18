package dev.toma.pubgmc.util.game.loot;

import dev.toma.pubgmc.api.interfaces.IGameTileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

public interface ILootSpawner extends IGameTileEntity {

    NonNullList<ItemStack> getInventory();

    boolean isAirdropContainer();

    default boolean generateLootOnCommand() {
        return true;
    }
}

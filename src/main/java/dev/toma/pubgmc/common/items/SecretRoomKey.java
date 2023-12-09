package dev.toma.pubgmc.common.items;

import net.minecraft.item.ItemStack;

import java.util.UUID;

public interface SecretRoomKey {

    UUID getLinkedDoorId(ItemStack stack);
}

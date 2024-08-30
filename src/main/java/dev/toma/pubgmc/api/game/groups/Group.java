package dev.toma.pubgmc.api.game.groups;

import net.minecraft.util.text.ITextComponent;

import java.util.UUID;

public interface Group {

    UUID getId();

    UUID getLeader();

    ITextComponent getUsername(UUID uuid);
}

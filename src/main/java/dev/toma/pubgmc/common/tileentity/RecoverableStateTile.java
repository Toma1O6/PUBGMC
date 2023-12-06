package dev.toma.pubgmc.common.tileentity;

import dev.toma.pubgmc.api.game.GameObject;
import net.minecraft.block.state.IBlockState;

public interface RecoverableStateTile extends GameObject {

    void walk(int direction);

    int getOffset();

    boolean canLink(IBlockState state);
}

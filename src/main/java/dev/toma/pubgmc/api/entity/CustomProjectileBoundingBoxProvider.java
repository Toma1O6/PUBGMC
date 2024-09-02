package dev.toma.pubgmc.api.entity;

import net.minecraft.util.math.AxisAlignedBB;

import javax.annotation.Nullable;

public interface CustomProjectileBoundingBoxProvider {

    @Nullable
    AxisAlignedBB getBoundingBoxForProjectiles();
}

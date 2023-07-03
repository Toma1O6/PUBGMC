package dev.toma.pubgmc.api.entity;

import net.minecraft.client.settings.GameSettings;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public interface IControllable {

    void handle(byte inputs);

    @SideOnly(Side.CLIENT)
    int encode(GameSettings settings);
}

package com.toma.pubgmc.config.client;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.util.INBTSerializable;

public class CFGOtherSettings implements INBTSerializable<NBTTagCompound> {

    @Config.Name("On-join world notifications")
    public boolean messagesOnJoin = true;

    @Config.Name("Lootbox content render")
    @Config.RequiresMcRestart
    public CFGLootRenderStyle lootRenderStyle = CFGLootRenderStyle.FANCY;

    @Override
    public NBTTagCompound serializeNBT() {
        NBTTagCompound c = new NBTTagCompound();
        c.setBoolean("joinMsg", messagesOnJoin);
        c.setInteger("lootRender", lootRenderStyle.ordinal());
        return c;
    }

    @Override
    public void deserializeNBT(NBTTagCompound nbt) {
        messagesOnJoin = nbt.getBoolean("joinMsg");
        lootRenderStyle = CFGLootRenderStyle.values()[nbt.getInteger("lootRender")];
    }
}

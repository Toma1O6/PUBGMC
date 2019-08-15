package com.toma.pubgmc.config.client;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.util.INBTSerializable;

public class CFGOverlaySettings implements INBTSerializable<NBTTagCompound> {

    @Config.Name("Use image overlay for boost rendering")
    @Config.Comment("Your boost overlay will be rendered instead of the XP bar. If this == TEXT, you'll be able to see numbers above hunger bar which will indicate your boost value")
    public CFGEnumOverlayStyle imageBoostOverlay = CFGEnumOverlayStyle.IMAGE;

    @Config.Name("Boost (text) position")
    public CFG2DCoords textBoostOverlayPos = new CFG2DCoords(0, 0);

    @Config.Name("Boost (image) position")
    public CFG2DCoords imgBoostOverlayPos = new CFG2DCoords(0, 0);

    @Config.Name("Armor icons render")
    public boolean renderArmorIcons = true;

    @Override
    public NBTTagCompound serializeNBT() {
        NBTTagCompound c = new NBTTagCompound();
        c.setInteger("overlayStyle", imageBoostOverlay.ordinal());
        c.setTag("textPos", textBoostOverlayPos.serializeNBT());
        c.setTag("imgPos", imgBoostOverlayPos.serializeNBT());
        c.setBoolean("armorIcons", renderArmorIcons);
        return c;
    }

    @Override
    public void deserializeNBT(NBTTagCompound nbt) {
        if(!nbt.hasKey("textPos")) {
            return;
        }
        imageBoostOverlay = CFGEnumOverlayStyle.values()[nbt.getInteger("overlayStyle")];
        textBoostOverlayPos.deserializeNBT(nbt.getCompoundTag("textPos"));
        imgBoostOverlayPos.deserializeNBT(nbt.getCompoundTag("imgPos"));
        renderArmorIcons = nbt.getBoolean("armorIcons");
    }
}

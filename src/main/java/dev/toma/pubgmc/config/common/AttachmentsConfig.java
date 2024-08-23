package dev.toma.pubgmc.config.common;

import dev.toma.configuration.api.ConfigCreator;
import dev.toma.configuration.api.ConfigPlugin;
import dev.toma.configuration.api.type.ObjectType;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.INBTSerializable;

public class AttachmentsConfig extends ObjectType implements INBTSerializable<NBTTagCompound> {

    public AttachmentsConfig(ConfigPlugin plugin) {
        super("Attachments");
    }

    @Override
    public void buildStructure(ConfigCreator configCreator) {

    }

    @Override
    public NBTTagCompound serializeNBT() {
        return new NBTTagCompound();
    }

    @Override
    public void deserializeNBT(NBTTagCompound nbt) {

    }
}

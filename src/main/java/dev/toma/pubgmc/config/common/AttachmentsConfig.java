package dev.toma.pubgmc.config.common;

import dev.toma.configuration.api.ConfigCreator;
import dev.toma.configuration.api.ConfigPlugin;
import dev.toma.configuration.api.type.DoubleType;
import dev.toma.configuration.api.type.ObjectType;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.INBTSerializable;

public class AttachmentsConfig extends ObjectType implements INBTSerializable<NBTTagCompound> {

    public DoubleType verticalGripRecoil;
    public DoubleType angledGripRecoil;
    public DoubleType compensatorVerticalRecoil;
    public DoubleType compensatorHorizontalRecoil;

    public AttachmentsConfig(ConfigPlugin plugin) {
        super("Attachments");
    }

    @Override
    public void buildStructure(ConfigCreator configCreator) {
        verticalGripRecoil = configCreator.createDouble("Vertical Grip Recoil", 0.8F, 0.0F, 1.0F);
        angledGripRecoil = configCreator.createDouble("Angled Grip Recoil", 0.8F, 0.0F, 1.0F);
        compensatorVerticalRecoil = configCreator.createDouble("Compensator Vertical Recoil", 0.7F, 0.0F, 1.0F);
        compensatorHorizontalRecoil = configCreator.createDouble("Compensator Horizontal Recoil", 0.7F, 0.0F, 1.0F);
    }

    @Override
    public NBTTagCompound serializeNBT() {
        NBTTagCompound nbt = new NBTTagCompound();
        nbt.setDouble("verticalGrip", verticalGripRecoil.get());
        nbt.setDouble("angledGrip", angledGripRecoil.get());
        nbt.setDouble("compensatorVertical", compensatorVerticalRecoil.get());
        nbt.setDouble("compensatorHorizontal", compensatorHorizontalRecoil.get());
        return nbt;
    }

    @Override
    public void deserializeNBT(NBTTagCompound nbt) {
        verticalGripRecoil.set(nbt.getDouble("verticalGrip"));
        angledGripRecoil.set(nbt.getDouble("angledGrip"));
        compensatorVerticalRecoil.set(nbt.getDouble("compensatorVertical"));
        compensatorHorizontalRecoil.set(nbt.getDouble("compensatorHorizontal"));
    }
}
